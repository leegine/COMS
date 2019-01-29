head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionSettleContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP返済入力サービスImplTest(WEB3OptionSettleContractInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 張騰宇 (中訊) 新規作成
Revision History : 2007/08/26 劉剣 (中訊) IFO小数点対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SubmitMarketTriggerDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsCloseMarginGroup;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListResponse;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionSettleContractInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionSettleContractInputServiceImplTest.class);
    public WEB3OptionSettleContractInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TradingCalendarDetails tradingCalendarDetails =
            new WEB3GentradeTradingClendarDetailsImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getTradingCalendarDetails",
                new Class[] {long.class},
                tradingCalendarDetails);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                WEB3DateUtility.getDate("20070619","yyyyMMdd"));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public class WEB3OptionsCloseMarginInputRequestForMock 
    extends WEB3OptionsCloseMarginInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3OptionsCloseMarginInputRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl.getSettleContractInputScreen(WEB3OptionsCloseMarginInputRequest)'
     */
    public void testGetSettleContractInputScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreenCase1()";
        log.entering(STR_METHOD_NAME);
        
  
        
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams1.setSessionType("1");
        l_ifoContractParams1.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams1.setProductId(1006160060005L);
        l_ifoContractParams1.setMarketId(3303L);
        l_ifoContractParams1.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams1.setContractId(1002);
        l_ifoContractParams1.setAccountId(333812512203L);
        l_ifoContractParams1.setSubAccountId(33381251220302L);
        l_ifoContractParams1.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams1.setContractType(ContractTypeEnum.SHORT);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        l_subAccountParams.setInstitutionCode("0D");
        
        SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
        l_subAccountParams1.setAccountId(333812512203L);
        l_subAccountParams1.setSubAccountId(33381251220302L);
        l_subAccountParams1.setInstitutionCode("0D");
        
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("0006");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionName("信用アルファ@証券株式会社");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
//        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday

        EnableOrderConditionParams l_enableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("2");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0005");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("5");
        try
        {

            TestDBUtility.deleteAllAndCommit(IfoContractRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams);
            
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams1);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.commit();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class, String.class},
                null);

        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setProductCode("0005");
        l_clendarContext.setMarketCode("0");
        l_clendarContext.setBizDateType("1");
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl = 
                new WEB3IfoTradedProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{
                        WEB3IfoProductImpl.class,
                        WEB3GentradeMarket.class,
                        boolean.class,
                        boolean.class},
                        l_tradedProductImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,18);
            
            Date date = ca.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);

            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsCloseMarginInputRequestForMock l_request =
                new WEB3OptionsCloseMarginInputRequestForMock();
            String[] l_strID = new String[2];
            l_strID[0] = "1001";
            l_strID[1] = "1002";
            l_request.id = l_strID;
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey = new WEB3FuturesOptionsSortKey();
            l_sortKey.keyItem = "contractPrice";
            l_sortKey.ascDesc = "A";
            l_sortKeys[0] = l_sortKey;
            l_request.futOpSortKeys = l_sortKeys;
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"0006","0007","0008"},"2");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            WEB3OptionsCloseMarginInputResponse l_response =
                l_impl.getSettleContractInputScreen(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationStartDate, "yyyyMMdd"),
                    "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationEndDate, "yyyyMMdd"),
                    "20070622");
            assertEquals(null,l_response.sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testGetSettleContractInputScreenCase2()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreenCase2()";
        log.entering(STR_METHOD_NAME);

        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        l_subAccountParams.setInstitutionCode("0D");
        
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("0006");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionName("信用アルファ@証券株式会社");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
//        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        EnableOrderConditionParams l_enableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("2");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setCarriedOrder("0");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0006");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
                        
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.commit();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class, String.class},
                null);

        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setProductCode("0005");
        l_clendarContext.setMarketCode("0");
        l_clendarContext.setBizDateType("1");
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl = 
                new WEB3IfoTradedProductImpl(100106139070605L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{
                        WEB3IfoProductImpl.class,
                        WEB3GentradeMarket.class,
                        boolean.class,
                        boolean.class},
                        l_tradedProductImpl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,19);
            
            Date date = ca.getTime();


            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsCloseMarginInputRequestForMock l_request =
                new WEB3OptionsCloseMarginInputRequestForMock();
            String[] l_strID = new String[1];
            l_strID[0] = "1001";
            l_request.id = l_strID;
             
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"0006","0007","0008"},"2");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            WEB3OptionsCloseMarginInputResponse l_response =
                l_impl.getSettleContractInputScreen(l_request);
            assertNull(l_response.expirationStartDate);
            assertNull(l_response.expirationEndDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl.createSettleContractList(WEB3OptionsContractReferenceUnit[])'
     */
    public void testCreateSettleContractListCase1()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractListCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("03");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,7-1,5);
            
            Date l_bizDat1 = ca.getTime();
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDat1);
            
            ca.set(2007,7-1,6);
            
            Date l_dat = ca.getTime();
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setTradingTimeType("03");
            WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails = new WEB3OptionsContractReferenceUnit[5];
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.id = "1001";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.opProductCode = "0";
            l_unit1.contractType = "1";
            l_unit1.opProductType = "P";
            l_unit1.settlementState = "0";
            l_unit1.contractOrderQuantity = "111";
            l_unit1.contractExecPrice = "111";
            l_unit1.contractCommission = "111";
            l_unit1.contractPrice = "111";
            l_unit1.income = "111";
            l_unit1.incomeCost = "111";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.id = "1001";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.opProductCode = "0";
            l_unit2.contractType = "1";
            l_unit2.opProductType = "P";
            l_unit2.settlementState = "0";
            l_unit2.contractOrderQuantity = "111";
            l_unit2.contractExecPrice = "111";
            l_unit2.contractCommission = "111";
            l_unit2.contractPrice = "111";
            l_unit2.income = "111";
            l_unit2.incomeCost = "111";
            WEB3OptionsContractReferenceUnit l_unit3 = new WEB3OptionsContractReferenceUnit();
            l_unit3.id = "1001";
            l_unit3.openDate = l_dat;
            l_unit3.sessionType = "1";
            l_unit3.opProductCode = "0";
            l_unit3.contractType = "1";
            l_unit3.opProductType = "P";
            l_unit3.settlementState = "0";
            l_unit3.contractOrderQuantity = "111";
            l_unit3.contractExecPrice = "111";
            l_unit3.contractCommission = "111";
            l_unit3.contractPrice = "111";
            l_unit3.income = "111";
            l_unit3.incomeCost = "111";
            WEB3OptionsContractReferenceUnit l_unit4 = new WEB3OptionsContractReferenceUnit();
            l_unit4.id = "1001";
            l_unit4.openDate = l_dat;
            l_unit4.sessionType = "2";
            l_unit4.opProductCode = "0";
            l_unit4.contractType = "1";
            l_unit4.opProductType = "P";
            l_unit4.settlementState = "0";
            l_unit4.contractOrderQuantity = "111";
            l_unit4.contractExecPrice = "111";
            l_unit4.contractCommission = "111";
            l_unit4.contractPrice = "111";
            l_unit4.income = "111";
            l_unit4.incomeCost = "111";
            WEB3OptionsContractReferenceUnit l_unit5 = new WEB3OptionsContractReferenceUnit();
            l_unit5.id = "1001";
            l_unit5.openDate = l_dat;
            l_unit5.sessionType = "2";
            l_unit5.opProductCode = "0";
            l_unit5.contractType = "1";
            l_unit5.opProductType = "P";
            l_unit5.settlementState = "1";
            l_unit5.contractOrderQuantity = "111";
            l_unit5.contractExecPrice = "111";
            l_unit5.contractCommission = "111";
            l_unit5.contractPrice = "111";
            l_unit5.income = "111";
            l_unit5.incomeCost = "111";
            l_contractReferenceDetails[0] = l_unit1;
            l_contractReferenceDetails[1] = l_unit2;
            l_contractReferenceDetails[2] = l_unit3;
            l_contractReferenceDetails[3] = l_unit4;
            l_contractReferenceDetails[4] = l_unit5;
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups =
                l_impl.createSettleContractList(l_contractReferenceDetails);
            assertEquals(l_closeMarginGroups.length, 4);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreateSettleContractListCase2()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractListCase3()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("03");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("2");
        l_tradingTimeParams.setSubmitMarketTrigger(WEB3SubmitMarketTriggerDef.TRIGGER);
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,7-1,5);
            
            Date l_bizDat1 = ca.getTime();
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDat1);
            
            ca.set(2007,7-1,6);
            
            Date l_dat = ca.getTime();
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setTradingTimeType("03");
            WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails = new WEB3OptionsContractReferenceUnit[2];
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.id = "1001";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.opProductCode = "0";
            l_unit1.contractType = "1";
            l_unit1.opProductType = "P";
            l_unit1.settlementState = "0";
            l_unit1.contractOrderQuantity = "111";
            l_unit1.contractExecPrice = "111";
            l_unit1.contractCommission = "111";
            l_unit1.contractPrice = "111";
            l_unit1.income = "111";
            l_unit1.incomeCost = "111";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.id = "1001";
            l_unit2.openDate = l_dat;
            l_unit2.opProductCode = "0";
            l_unit2.contractType = "1";
            l_unit2.opProductType = "P";
            l_unit2.settlementState = "0";
            l_unit2.contractOrderQuantity = "111";
            l_unit2.contractExecPrice = "111";
            l_unit2.contractCommission = "111";
            l_unit2.contractPrice = "111";
            l_unit2.income = "111";
            l_unit2.incomeCost = "111";
            l_contractReferenceDetails[0] = l_unit1;
            l_contractReferenceDetails[1] = l_unit2;
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups =
                l_impl.createSettleContractList(l_contractReferenceDetails);
            assertEquals(l_closeMarginGroups.length, 1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl.createSettleContractListLine(WEB3OptionsContractReferenceUnit)'
     */
    public void testCreateSettleContractListLineCase1()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractListLineCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        try
        {
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsContractReferenceUnit l_unit = new WEB3OptionsContractReferenceUnit();
            l_unit.id = "1001";
            l_unit.sessionType = "1";
            WEB3OptionsCloseMarginGroup l_group =
                l_impl.createSettleContractListLine(l_unit);
            assertEquals(l_group.sessionType, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl.createContractDetails(WEB3OptionsContractReferenceUnit)'
     */
    public void testCreateContractDetailsCase1()
    {
        final String STR_METHOD_NAME = "testCreateContractDetailsCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsContractReferenceUnit l_unit = new WEB3OptionsContractReferenceUnit();
            l_unit.id = "1001";
            l_unit.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_contractUnit =
                l_impl.createContractDetails(l_unit);
            assertEquals(l_contractUnit.sessionType, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl.sortContractReferenceDetails(WEB3OptionsContractReferenceUnit[], WEB3FuturesOptionsSortKey[])'
     */
    public void testSortContractReferenceDetailsCase1()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceDetailsCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails = new WEB3OptionsContractReferenceUnit[3];
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
//            l_unit1.income = "10";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
//            l_unit2.income = "11";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3OptionsContractReferenceUnit l_unit3 = new WEB3OptionsContractReferenceUnit();
//            l_unit3.income = "11";
            l_unit3.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit3.sessionType = "1";
            l_contractReferenceDetails[0] = l_unit1;
            l_contractReferenceDetails[1] = l_unit2;
            l_contractReferenceDetails[2] = l_unit3;
            
            
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "A";
            l_sortKey1.keyItem = "openDate";
//            WEB3FuturesOptionsSortKey l_sortKey2 = new WEB3FuturesOptionsSortKey();
//            l_sortKey2.ascDesc = "A";
//            l_sortKey2.keyItem = "income";
            l_sortKeys[0] = l_sortKey1;
            l_contractReferenceDetails = l_impl.sortContractReferenceDetails(l_contractReferenceDetails,l_sortKeys);
            WEB3OptionsContractReferenceUnit l_unit11 = l_contractReferenceDetails[0];
            WEB3OptionsContractReferenceUnit l_unit12 = l_contractReferenceDetails[1];
            WEB3OptionsContractReferenceUnit l_unit13 = l_contractReferenceDetails[2];
            assertNull(l_unit11.sessionType);
            assertEquals(l_unit12.sessionType, "1");
            assertEquals(l_unit13.sessionType, "1");
            
            assertEquals(WEB3DateUtility.formatDate(l_unit11.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit12.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit13.openDate, "yyyyMMdd"), "20070619");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
       
    }
    public void testSortContractReferenceDetailsCase2()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceDetailsCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails = new WEB3OptionsContractReferenceUnit[3];
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.income = "10";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.income = "12";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3OptionsContractReferenceUnit l_unit3 = new WEB3OptionsContractReferenceUnit();
            l_unit3.income = "11";
            l_unit3.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit3.sessionType = "1";
            l_contractReferenceDetails[0] = l_unit1;
            l_contractReferenceDetails[1] = l_unit2;
            l_contractReferenceDetails[2] = l_unit3;
            
            
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
//            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
//            l_sortKey1.ascDesc = "A";
//            l_sortKey1.keyItem = "openDate";
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "D";
            l_sortKey1.keyItem = "income";
            l_sortKeys[0] = l_sortKey1;
            l_contractReferenceDetails = l_impl.sortContractReferenceDetails(l_contractReferenceDetails,l_sortKeys);
            WEB3OptionsContractReferenceUnit l_unit11 = l_contractReferenceDetails[0];
            WEB3OptionsContractReferenceUnit l_unit12 = l_contractReferenceDetails[1];
            WEB3OptionsContractReferenceUnit l_unit13 = l_contractReferenceDetails[2];
            assertEquals(l_unit11.sessionType, "1");
            assertEquals(l_unit12.sessionType, "1");
            assertNull(l_unit13.sessionType);
            
            assertEquals(WEB3DateUtility.formatDate(l_unit11.openDate, "yyyyMMdd"), "20070619");
            assertEquals(WEB3DateUtility.formatDate(l_unit12.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit13.openDate, "yyyyMMdd"), "20070618");
            
            assertEquals(l_unit11.income, "12");
            assertEquals(l_unit12.income, "11");
            assertEquals(l_unit13.income, "10");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
       
    }
    
    public void testSortContractReferenceDetailsCase3()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceDetailsCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3OptionsContractReferenceUnit[] l_contractReferenceDetails = new WEB3OptionsContractReferenceUnit[3];
            WEB3OptionsContractReferenceUnit l_unit1 = new WEB3OptionsContractReferenceUnit();
            l_unit1.opProductCode = "13";
            l_unit1.settlementState = "20";
            l_unit1.contractType = "1";
            WEB3OptionsContractReferenceUnit l_unit2 = new WEB3OptionsContractReferenceUnit();
            l_unit2.opProductCode = "12";
            l_unit2.settlementState = "21";
            l_unit2.contractType = "2";
            WEB3OptionsContractReferenceUnit l_unit3 = new WEB3OptionsContractReferenceUnit();
            l_unit3.opProductCode = "11";
            l_unit3.settlementState = "22";
            l_unit3.contractType = "3";
            l_contractReferenceDetails[0] = l_unit1;
            l_contractReferenceDetails[1] = l_unit2;
            l_contractReferenceDetails[2] = l_unit3;
            
            
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[3];
            l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[0].ascDesc = "A";
            l_sortKeys[0].keyItem = "opProductCode";
            l_sortKeys[1] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[1].ascDesc = "D";
            l_sortKeys[1].keyItem = "settlementType";
            l_sortKeys[2] = new WEB3FuturesOptionsSortKey();
            l_sortKeys[2].ascDesc = "A";
            l_sortKeys[2].keyItem = "contractType";
            WEB3OptionsContractReferenceUnit[] l_result = l_impl.sortContractReferenceDetails(l_contractReferenceDetails,l_sortKeys);
            WEB3OptionsContractReferenceUnit l_unit11 = l_result[0];
            WEB3OptionsContractReferenceUnit l_unit12 = l_result[1];
            WEB3OptionsContractReferenceUnit l_unit13 = l_result[2];
            
            assertEquals("3", l_unit11.contractType);
            assertEquals("2", l_unit12.contractType);
            assertEquals("1", l_unit13.contractType);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
       
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl.sortContractDetails(WEB3FuturesOptionsContractUnit[], WEB3FuturesOptionsSortKey[])'
     */
    public void testSortContractDetailsCase1()
    {
        final String STR_METHOD_NAME = "testSortContractDetailsCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[3];
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.contractPrice = "10";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.contractPrice = "12";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_unit3 = new WEB3FuturesOptionsContractUnit();
            l_unit3.contractPrice = "11";
            l_unit3.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit3.sessionType = "1";
            l_contractUnits[0] = l_unit1;
            l_contractUnits[1] = l_unit2;
            l_contractUnits[2] = l_unit3;
            
            
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "A";
            l_sortKey1.keyItem = "openDate";
            l_sortKeys[0] = l_sortKey1;
            l_contractUnits = l_impl.sortContractDetails(l_contractUnits,l_sortKeys);
            WEB3FuturesOptionsContractUnit l_unit11 = l_contractUnits[0];
            WEB3FuturesOptionsContractUnit l_unit12 = l_contractUnits[1];
            WEB3FuturesOptionsContractUnit l_unit13 = l_contractUnits[2];
            assertNull(l_unit11.sessionType);
            assertEquals(l_unit12.sessionType, "1");
            assertEquals(l_unit13.sessionType, "1");
            
            assertEquals(WEB3DateUtility.formatDate(l_unit11.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit12.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit13.openDate, "yyyyMMdd"), "20070619");
            
            assertEquals(l_unit11.contractPrice, "10");
            assertEquals(l_unit12.contractPrice, "11");
            assertEquals(l_unit13.contractPrice, "12");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public void testSortContractDetailsCase2()
    {
        final String STR_METHOD_NAME = "testSortContractDetailsCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[3];
            WEB3FuturesOptionsContractUnit l_unit1 = new WEB3FuturesOptionsContractUnit();
            l_unit1.contractPrice = "10";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3FuturesOptionsContractUnit l_unit2 = new WEB3FuturesOptionsContractUnit();
            l_unit2.contractPrice = "12";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_unit3 = new WEB3FuturesOptionsContractUnit();
            l_unit3.contractPrice = "11";
            l_unit3.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit3.sessionType = "1";
            l_contractUnits[0] = l_unit1;
            l_contractUnits[1] = l_unit2;
            l_contractUnits[2] = l_unit3;
            
            
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "D";
            l_sortKey1.keyItem = "contractPrice";
            l_sortKeys[0] = l_sortKey1;
            l_contractUnits = l_impl.sortContractDetails(l_contractUnits,l_sortKeys);
            WEB3FuturesOptionsContractUnit l_unit11 = l_contractUnits[0];
            WEB3FuturesOptionsContractUnit l_unit12 = l_contractUnits[1];
            WEB3FuturesOptionsContractUnit l_unit13 = l_contractUnits[2];
            assertEquals(l_unit11.sessionType, "1");
            assertEquals(l_unit12.sessionType, "1");
            assertNull(l_unit13.sessionType);
            
            assertEquals(WEB3DateUtility.formatDate(l_unit11.openDate, "yyyyMMdd"), "20070619");
            assertEquals(WEB3DateUtility.formatDate(l_unit12.openDate, "yyyyMMdd"), "20070618");
            assertEquals(WEB3DateUtility.formatDate(l_unit13.openDate, "yyyyMMdd"), "20070618");
            
            assertEquals(l_unit11.contractPrice, "12");
            assertEquals(l_unit12.contractPrice, "11");
            assertEquals(l_unit13.contractPrice, "10");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImplForTest();
        
        WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequestForTest();
        try
        {
            WEB3OptionsCloseMarginListResponse l_expectedResponse = null;
            l_expectedResponse = (WEB3OptionsCloseMarginListResponse)l_serviceImpl.execute(l_request);
            assertEquals("1", l_expectedResponse.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImplForTest();

        WEB3OptionsCloseMarginInputRequest l_request = new WEB3OptionsCloseMarginInputRequestForTest();

        try
        {
            WEB3OptionsCloseMarginInputResponse l_expectedResponse = null;
            l_expectedResponse = (WEB3OptionsCloseMarginInputResponse)l_serviceImpl.execute(l_request);
            
            assertEquals("1", l_expectedResponse.opProductType);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        WEB3OptionsCloseMarginInputRequest l_request = null;

        try
        {
            l_serviceImpl.execute(l_request);
        }
        catch (WEB3BaseException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsInstitutionHandlingProduct_0001()
    {
        final String STR_METHOD_NAME = "testIsInstitutionHandlingProduct_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAcctParams = TestDBUtility.getSubAccountRow();
            l_subAcctParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAcctParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAcctParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAcctParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
                        
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            l_branchIndexDealtCondParams.setBranchCode("381");
            l_branchIndexDealtCondParams.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProduct);
            BranchParams l_bcparams = TestDBUtility.getBranchRow();
            l_bcparams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_bcparams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAcctParams);
            String l_strProductCode = null;
            
            assertTrue(l_serviceImpl.isInstitutionHandlingProduct(l_subAccount, l_strProductCode));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsInstitutionHandlingProduct_0002()
    {
        final String STR_METHOD_NAME = "testIsInstitutionHandlingProduct_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAcctParams = TestDBUtility.getSubAccountRow();
            l_subAcctParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAcctParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAcctParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAcctParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
                        
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProduct);
            BranchParams l_bcparams = TestDBUtility.getBranchRow();
            l_bcparams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_bcparams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAcctParams);
            String l_strProductCode = null;
            
            assertFalse(l_serviceImpl.isInstitutionHandlingProduct(l_subAccount, l_strProductCode));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsInstitutionHandlingProduct_0003()
    {
        final String STR_METHOD_NAME = "testIsInstitutionHandlingProduct_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAcctParams = TestDBUtility.getSubAccountRow();
            l_subAcctParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAcctParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAcctParams);
            String l_strProductCode = null;
            
            l_serviceImpl.isInstitutionHandlingProduct(l_subAccount, l_strProductCode);
        }
        catch (WEB3BaseException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSortSettleContractListLine_0001()
    {
        final String STR_METHOD_NAME = "testSortSettleContractListLine_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {        
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[2];
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[0];
            
            l_closeMarginGroups[0] = new WEB3OptionsCloseMarginGroup();
            l_closeMarginGroups[1] = new WEB3OptionsCloseMarginGroup();
            l_closeMarginGroups[0].contractType = "25";
            l_closeMarginGroups[1].contractType = "30";

            WEB3OptionsCloseMarginGroup[] l_resultGroups =
                l_serviceImpl.sortSettleContractListLine(l_closeMarginGroups, l_sortKey);
            
            assertEquals("25",l_resultGroups[0].contractType);
            assertEquals("30",l_resultGroups[1].contractType);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSortSettleContractListLine_0002()
    {
        final String STR_METHOD_NAME = "testSortSettleContractListLine_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {        
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[2];
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[1];

            l_closeMarginGroups[0] = new WEB3OptionsCloseMarginGroup();
            l_closeMarginGroups[1] = new WEB3OptionsCloseMarginGroup();
            l_closeMarginGroups[0].opProductCode = "25";
            l_closeMarginGroups[1].opProductCode = "30";
            
            l_sortKey[0] = new WEB3FuturesOptionsSortKey();
            l_sortKey[0].keyItem = "opProductCode";
            l_sortKey[0].ascDesc = "D";

            WEB3OptionsCloseMarginGroup[] l_resultGroups =
                l_serviceImpl.sortSettleContractListLine(l_closeMarginGroups, l_sortKey);
            
            assertEquals("30",l_resultGroups[0].opProductCode);
            assertEquals("25",l_resultGroups[1].opProductCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSortSettleContractListLine_0003()
    {
        final String STR_METHOD_NAME = "testSortSettleContractListLine_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {        
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[3];
            WEB3FuturesOptionsSortKey[] l_sortKey = new WEB3FuturesOptionsSortKey[3];
            for (int i = 0; i< l_sortKey.length; i++)
            {
                l_sortKey[i] = new WEB3FuturesOptionsSortKey();
                l_closeMarginGroups[i] = new WEB3OptionsCloseMarginGroup();
            }

            l_closeMarginGroups[0].income = "1";
            l_closeMarginGroups[0].incomeCost = "6";
            l_closeMarginGroups[0].contractType = "7";
            l_closeMarginGroups[1].income = "2";
            l_closeMarginGroups[1].incomeCost = "5";
            l_closeMarginGroups[1].contractType = "8";
            l_closeMarginGroups[2].income = "3";
            l_closeMarginGroups[2].incomeCost = "4";
            l_closeMarginGroups[2].contractType = "9";

            l_sortKey[0].keyItem = "income";
            l_sortKey[1].keyItem = "incomeCost";
            l_sortKey[2].keyItem = "pp";

            l_sortKey[0].ascDesc = "D";
            l_sortKey[1].ascDesc = "A";
            l_sortKey[2].ascDesc = "D";
            WEB3OptionsCloseMarginGroup[] l_resultGroups =
                l_serviceImpl.sortSettleContractListLine(l_closeMarginGroups, l_sortKey);

            assertEquals("9",l_resultGroups[0].contractType);
            assertEquals("8",l_resultGroups[1].contractType);
            assertEquals("7",l_resultGroups[2].contractType);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreatePage_0001()
    {
        final String STR_METHOD_NAME = "testCreatePage_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {        
            WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequest();
            l_request.pageIndex = "1";
            l_request.pageSize = "20";
            WEB3OptionsCloseMarginGroup[] l_settleContractListLines =
                new WEB3OptionsCloseMarginGroup[2];
            l_settleContractListLines[0] = new WEB3OptionsCloseMarginGroup();
            l_settleContractListLines[1] = new WEB3OptionsCloseMarginGroup();
            
            l_serviceImpl.createPage(l_request, l_settleContractListLines);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {        
            WEB3IfoProductImpl l_ifoProduct = null;            
            String l_strResult = l_serviceImpl.createQueryString(l_ifoProduct);
            assertNull(l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {   
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_ifoProductParams);            
            String l_strResult = l_serviceImpl.createQueryString(l_ifoProduct);
            assertEquals(" and product_id = ? ", l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryContainer_0001()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {        
            WEB3IfoProductImpl l_ifoProduct = null;            
            String[] l_strResult = l_serviceImpl.createQueryContainer(l_ifoProduct);
            assertNull(l_strResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryContainer_0002()
    {
        final String STR_METHOD_NAME = "testCreateQueryContainer_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();
        try
        {   
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_ifoProductParams);            
            String[] l_strResults = l_serviceImpl.createQueryContainer(l_ifoProduct);
            assertEquals("1006160060005", l_strResults[0]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractList_0001()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_0001";
        log.entering(STR_METHOD_NAME);
        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    null);

            WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequestForTest();
            l_request.opProductCode = "1";
            l_serviceImpl.getSettleContractList(l_request);
        }
        catch (WEB3BaseException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractList_0002()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_0002";
        log.entering(STR_METHOD_NAME);
        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {}, new Long(333812512246L));
            BranchParams l_bcparams = TestDBUtility.getBranchRow();
            l_bcparams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_bcparams);
            WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequestForTest();
            l_request.opProductCode = "1";
            l_serviceImpl.getSettleContractList(l_request);
        }
        catch (WEB3BaseException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractList_0003()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_0003";
        log.entering(STR_METHOD_NAME);
        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class,
                            String.class,
                            String.class,
                            IfoDerivativeTypeEnum.class,
                            double.class,
                            String.class,
                            String.class },
                            null);
            
            WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequestForTest();
            l_request.opProductCode = "1";
            l_request.opProductType = "C";
            l_request.marketCode = "20";
            l_request.targetProductCode = "30"; 
            l_request.strikePrice = "10";
            l_request.delivaryMonth = "200709"; 
            l_serviceImpl.getSettleContractList(l_request);
        }
        catch (WEB3BaseException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractList_0004()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_0004";
        log.entering(STR_METHOD_NAME);
        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {}, new Long(333812512246L));
            
            WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequestForTest();
            l_request.opProductCode = "1";
            l_request.opProductType = "C";
            l_request.marketCode = "20";
            l_request.targetProductCode = "30"; 
            l_request.strikePrice = "10";
            l_request.delivaryMonth = "200709"; 
            l_serviceImpl.getSettleContractList(l_request);
        }
        catch (WEB3BaseException l_expectedException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_expectedException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractList_0005()
    {
        final String STR_METHOD_NAME = "testGetSettleContractList_0005";
        log.entering(STR_METHOD_NAME);
        WEB3OptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3OptionSettleContractInputServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            l_ifoProductParams.setSplitType("000");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {}, new Long(333812512246L));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"0006","0007","0008"},"2");
            WEB3OptionsCloseMarginListRequest l_request = new WEB3OptionsCloseMarginListRequestForTest();
            l_request.opProductCode = "1";
            l_request.opProductType = "C";
            l_request.marketCode = "1";
            l_request.targetProductCode = "0005";
            l_request.strikePrice = "0";
            l_request.delivaryMonth = "200503";
            
            BranchParams l_bcparams = TestDBUtility.getBranchRow();
            l_bcparams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_bcparams);
            WEB3OptionsCloseMarginListResponse l_response =
                l_serviceImpl.getSettleContractList(l_request);
            
            assertNull(l_response.closeMarginGroups);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.pageIndex);
            assertNull(l_response.futOpProductCodeNames);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testSetSettleContractListLine_C0001()
    {
        final String STR_METHOD_NAME = "testSetSettleContractListLine_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3OptionsCloseMarginGroup l_closeMarginGroup = new WEB3OptionsCloseMarginGroup();
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[1];
            l_contractUnits[0] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[0].contractQuantity = "10";
            l_contractUnits[0].contractPrice = "20";
            l_contractUnits[0].contractExecPrice = "30";
            l_contractUnits[0].contractCommission = "40";
            l_contractUnits[0].income = "50";
            l_contractUnits[0].incomeCost = "60";
            
            WEB3OptionSettleContractInputServiceImpl l_impl = new WEB3OptionSettleContractInputServiceImpl();
            WEB3OptionsCloseMarginGroup l_group =
                l_impl.setSettleContractListLine(l_closeMarginGroup, l_contractUnits);
            
            assertEquals("20", l_group.contractPrice);
            assertEquals("20", l_group.contractUnits[0].contractPrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private class WEB3OptionClientRequestServiceForMock extends WEB3OptionSettleContractInputServiceImpl
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
            l_tradingTimeParams.setMarketCode("N1");
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
    
    private class WEB3OptionsCloseMarginListRequestForTest extends WEB3OptionsCloseMarginListRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3OptionsCloseMarginInputRequestForTest extends WEB3OptionsCloseMarginInputRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3OptionSettleContractInputServiceImplForTest extends WEB3OptionSettleContractInputServiceImpl
    {
        protected WEB3OptionsCloseMarginInputResponse getSettleContractInputScreen(WEB3OptionsCloseMarginInputRequest l_request) throws WEB3BaseException
        {
            WEB3OptionsCloseMarginInputResponse l_inputResponse = new WEB3OptionsCloseMarginInputResponse();
            l_inputResponse.opProductType = "1";
            return l_inputResponse;
        }
        
        protected WEB3OptionsCloseMarginListResponse getSettleContractList(WEB3OptionsCloseMarginListRequest l_request) throws WEB3BaseException
        {
            WEB3OptionsCloseMarginListResponse l_listResponse = new WEB3OptionsCloseMarginListResponse();
            l_listResponse.pageIndex = "1";
            return l_listResponse;
        }
        
    }
    
    private class WEB3OptionSettleContractInputServiceImplForTest1 extends WEB3OptionSettleContractInputServiceImpl
    {
        protected boolean isInstitutionHandlingProduct(WEB3GentradeSubAccount l_subAccount, String l_strProductCode) throws WEB3BaseException
        {
            return true;
        }
        
        protected WEB3OptionsCloseMarginGroup[] createSettleContractList(WEB3OptionsContractReferenceUnit[] l_contractReferenceUnit) throws WEB3BaseException
        {
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[1];
            return l_closeMarginGroups;
        }
        
        protected WEB3OptionsCloseMarginGroup[] sortSettleContractListLine(WEB3OptionsCloseMarginGroup[] l_closeMarginGroups, WEB3FuturesOptionsSortKey[] l_sortKey)
        {
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroup = new WEB3OptionsCloseMarginGroup[1];
            return l_closeMarginGroup;
        }
        
        protected WEB3OptionsCloseMarginGroup[] createPage(WEB3OptionsCloseMarginListRequest l_request, WEB3OptionsCloseMarginGroup[] l_settleContractListLine)
        {
            WEB3OptionsCloseMarginGroup[] l_closeMarginGroups = new WEB3OptionsCloseMarginGroup[1];
            l_closeMarginGroups[0] = new WEB3OptionsCloseMarginGroup();
            return l_closeMarginGroups;
        }
    }
}
@
