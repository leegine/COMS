head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesSettleContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済入力サービスTest(WEB3FuturesSettleContractInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/25 張騰宇(中訊)
Revision History : 2007/08/25 劉剣(中訊) IFO小数点対応
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
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductUpdqRow;
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
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginGroup;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesSettleContractInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractInputServiceImplTest.class);

    public WEB3FuturesSettleContractInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public class WEB3FuturesCloseMarginInputRequestForMock 
        extends WEB3FuturesCloseMarginInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCloseMarginInputRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImpl.getColseMarginInput(WEB3FuturesCloseMarginInputRequest)'
     */
    public void testGetColseMarginInputCase1()
    {
        final String STR_METHOD_NAME = "testGetColseMarginInputCase1()";
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
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
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
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
      
//        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//        l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        
        EnableOrderConditionParams l_enableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("1");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0005");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
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
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginInputRequestForMock l_request = new WEB3FuturesCloseMarginInputRequestForMock();
            String[] l_strID = new String[1];
            l_strID[0] = "1001";
            l_request.id = l_strID;
            WEB3FuturesCloseMarginInputResponse l_response =
                l_impl.getColseMarginInput(l_request);
            assertEquals("20070619", WEB3DateUtility.formatDate(l_response.expirationStartDate,"yyyyMMdd"));
            assertEquals("20070622", WEB3DateUtility.formatDate(l_response.expirationEndDate,"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetColseMarginInputCase2()
    {
        final String STR_METHOD_NAME = "testGetColseMarginInputCase2()";
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
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
//        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//        l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        
        EnableOrderConditionParams l_enableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("1");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setCarriedOrder("0");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0005");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
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
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginInputRequestForMock l_request = new WEB3FuturesCloseMarginInputRequestForMock();
            String[] l_strID = new String[1];
            l_strID[0] = "1001";
            l_request.id = l_strID;
            WEB3FuturesCloseMarginInputResponse l_response =
                l_impl.getColseMarginInput(l_request);
            assertNull(l_response.expirationStartDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //create返済一覧
    public void testCreateCloseMarginListC1()
    {
        final String STR_METHOD_NAME = "testCreateCloseMarginListC1()";
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
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        Calendar l_date1 =  Calendar.getInstance();
        l_date1.add(Calendar.DATE, 1);
        Date l_dat1 = l_date1.getTime();
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
//        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//        l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        
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
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
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
            WEB3FuturesContractReferenceUnit[] l_contractReferenceDetails = new WEB3FuturesContractReferenceUnit[5];
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.id = "1001";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.futProductCode = "0";
            l_unit1.contractType = "1";
//            l_unit1.opProductType = "P";
            l_unit1.settlementState = "0";
            l_unit1.contractOrderQuantity = "111";
            l_unit1.contractExecPrice = "111";
            l_unit1.contractCommission = "111";
            l_unit1.contractPrice = "111";
            l_unit1.income = "111";
            l_unit1.incomeCost = "111";
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.id = "1001";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.futProductCode = "0";
            l_unit2.contractType = "1";
//            l_unit2.opProductType = "P";
            l_unit2.settlementState = "0";
            l_unit2.contractOrderQuantity = "111";
            l_unit2.contractExecPrice = "111";
            l_unit2.contractCommission = "111";
            l_unit2.contractPrice = "111";
            l_unit2.income = "111";
            l_unit2.incomeCost = "111";
            WEB3FuturesContractReferenceUnit l_unit3 = new WEB3FuturesContractReferenceUnit();
            l_unit3.id = "1001";
            l_unit3.openDate = l_dat;
            l_unit3.sessionType = "1";
            l_unit3.futProductCode = "0";
            l_unit3.contractType = "1";
//            l_unit3.opProductType = "P";
            l_unit3.settlementState = "0";
            l_unit3.contractOrderQuantity = "111";
            l_unit3.contractExecPrice = "111";
            l_unit3.contractCommission = "111";
            l_unit3.contractPrice = "111";
            l_unit3.income = "111";
            l_unit3.incomeCost = "111";
            WEB3FuturesContractReferenceUnit l_unit4 = new WEB3FuturesContractReferenceUnit();
            l_unit4.id = "1001";
            l_unit4.openDate = l_dat;
            l_unit4.sessionType = "2";
            l_unit4.futProductCode = "0";
            l_unit4.contractType = "1";
//            l_unit4.opProductType = "P";
            l_unit4.settlementState = "0";
            l_unit4.contractOrderQuantity = "111";
            l_unit4.contractExecPrice = "111";
            l_unit4.contractCommission = "111";
            l_unit4.contractPrice = "111";
            l_unit4.income = "111";
            l_unit4.incomeCost = "111";
            WEB3FuturesContractReferenceUnit l_unit5 = new WEB3FuturesContractReferenceUnit();
            l_unit5.id = "1001";
            l_unit5.openDate = l_dat;
//            l_unit5.sessionType = "2";
            l_unit5.futProductCode = "0";
            l_unit5.contractType = "1";
//            l_unit5.futProductType = "P";
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
            WEB3FuturesCloseMarginGroup[] l_closeMarginGroups =
                l_impl.createCloseMarginList(l_contractReferenceDetails);
            assertEquals(l_closeMarginGroups.length, 4);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateCloseMarginListC2()
    {
        final String STR_METHOD_NAME = "testCreateCloseMarginListC2()";
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
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        Calendar l_date1 =  Calendar.getInstance();
        l_date1.add(Calendar.DATE, 1);
        Date l_dat1 = l_date1.getTime();
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
//        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//        l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        
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
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
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
            WEB3FuturesContractReferenceUnit[] l_contractReferenceDetails = new WEB3FuturesContractReferenceUnit[2];
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.id = "1001";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.futProductCode = "0";
            l_unit1.contractType = "1";
//            l_unit1.opProductType = "P";
            l_unit1.settlementState = "0";
            l_unit1.contractOrderQuantity = "111";
            l_unit1.contractExecPrice = "111";
            l_unit1.contractCommission = "111";
            l_unit1.contractPrice = "111";
            l_unit1.income = "111";
            l_unit1.incomeCost = "111";
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.id = "1001";
            l_unit2.openDate = l_dat;
            l_unit2.futProductCode = "0";
            l_unit2.contractType = "1";
//            l_unit2.opProductType = "P";
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
            WEB3FuturesCloseMarginGroup[] l_closeMarginGroups =
                l_impl.createCloseMarginList(l_contractReferenceDetails);
            assertEquals(l_closeMarginGroups.length, 1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    ///create返済一覧行
    public void testCreateCloseMarginListLineCase1()
    {
        final String STR_METHOD_NAME = "testCreateCloseMarginListLineCase1()";
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
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        Calendar l_date1 =  Calendar.getInstance();
        l_date1.add(Calendar.DATE, 1);
        Date l_dat1 = l_date1.getTime();
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(100106139070605L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
//        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//        l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesContractReferenceUnit l_unit = new WEB3FuturesContractReferenceUnit();
            l_unit.id = "1001";
            l_unit.sessionType = "1";
            WEB3FuturesCloseMarginGroup l_group =
                l_impl.createCloseMarginListLine(l_unit);
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
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImpl.createContractUnit(WEB3FuturesContractReferenceUnit)'
     */
    //create建玉明細
    public void testCreateContractUnitCase1()
    {
        final String STR_METHOD_NAME = "testCreateContractUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesContractReferenceUnit l_unit = new WEB3FuturesContractReferenceUnit();
            l_unit.id = "1001";
            l_unit.sessionType = "1";
            WEB3FuturesOptionsContractUnit l_contractUnit =
                l_impl.createContractUnit(l_unit);
            assertEquals(l_contractUnit.sessionType, "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    ///sort建玉照会明細
    public void testSortContractReferenceUnitCase1()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesContractReferenceUnit[] l_contractReferenceDetails = new WEB3FuturesContractReferenceUnit[3];
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
//            l_unit1.income = "10";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
//            l_unit2.income = "11";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3FuturesContractReferenceUnit l_unit3 = new WEB3FuturesContractReferenceUnit();
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
            l_sortKeys[0] = l_sortKey1;
            l_contractReferenceDetails = l_impl.sortContractReferenceUnit(l_contractReferenceDetails,l_sortKeys);
            WEB3FuturesContractReferenceUnit l_unit11 = l_contractReferenceDetails[0];
            WEB3FuturesContractReferenceUnit l_unit12 = l_contractReferenceDetails[1];
            WEB3FuturesContractReferenceUnit l_unit13 = l_contractReferenceDetails[2];
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
    
    public void testSortContractReferenceUnitCase2()
    {
        final String STR_METHOD_NAME = "testSortContractReferenceDetailsCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesContractReferenceUnit[] l_contractReferenceDetails = new WEB3FuturesContractReferenceUnit[3];
            WEB3FuturesContractReferenceUnit l_unit1 = new WEB3FuturesContractReferenceUnit();
            l_unit1.income = "10";
            l_unit1.openDate = WEB3DateUtility.getDate("20070618","yyyyMMdd");
            l_unit1.sessionType = null;
            WEB3FuturesContractReferenceUnit l_unit2 = new WEB3FuturesContractReferenceUnit();
            l_unit2.income = "12";
            l_unit2.openDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            l_unit2.sessionType = "1";
            WEB3FuturesContractReferenceUnit l_unit3 = new WEB3FuturesContractReferenceUnit();
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
            l_contractReferenceDetails = l_impl.sortContractReferenceUnit(l_contractReferenceDetails,l_sortKeys);
            WEB3FuturesContractReferenceUnit l_unit11 = l_contractReferenceDetails[0];
            WEB3FuturesContractReferenceUnit l_unit12 = l_contractReferenceDetails[1];
            WEB3FuturesContractReferenceUnit l_unit13 = l_contractReferenceDetails[2];
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
    
    ///sort建玉明細
    public void testSortContractUnitCase1()
    {
        final String STR_METHOD_NAME = "testSortContractUnitCase1()";
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
            l_contractUnits = l_impl.sortContractUnit(l_contractUnits,l_sortKeys);
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
    public void testSortContractUnitCase2()
    {
        final String STR_METHOD_NAME = "testSortContractUnitCase2()";
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
            l_contractUnits = l_impl.sortContractUnit(l_contractUnits,l_sortKeys);
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
    
    /**
     * 「get建玉」取到的場合
     *
     */
    public void testGetContract_C0001()
    {
        final String STR_METHOD_NAME = "testGetContract_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoContractRow());
            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
            l_request.id = new String[]{"1001"};
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            
            WEB3IfoContractImpl l_ifoContract = l_service.getContract(l_request);
            
            assertEquals(1001,l_ifoContract.getContractId(),0);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 「get建玉」沒有取到的場合
     *
     */
    public void testGetContract_C0002()
    {
        final String STR_METHOD_NAME = "testGetContract_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoContractRow());
            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
            l_request.id = new String[]{"1002"};
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            
            WEB3IfoContractImpl l_ifoContract = l_service.getContract(l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 「create建玉照会明細一覧」沒有取到的場合
     *
     */
    public void testCreateContractReferenceUnitList_C0001()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnitList_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);            
            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
                    
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
            l_request.id = new String[]{"2008"};
            WEB3FuturesContractReferenceUnit[] l_ifoFuturesContractReferenceUnit =
                l_service.createContractReferenceUnitList(l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 「create建玉照会明細一覧」取得的場合(0件)
     *
     */
    public void testCreateContractReferenceUnitList_C0002()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnitList_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
                    
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
            l_request.id = new String[0];
            WEB3FuturesContractReferenceUnit[] l_ifoFuturesContractReferenceUnit =
                l_service.createContractReferenceUnitList(l_request);
            
            assertEquals(0,l_ifoFuturesContractReferenceUnit.length);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 「create建玉照会明細一覧」取得的場合(一件)
     *
     */
    public void testCreateContractReferenceUnitList_C0003()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnitList_C0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
                    
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
            l_request.id = new String[]{"1001"};
            WEB3FuturesContractReferenceUnit[] l_ifoFuturesContractReferenceUnit =
                l_service.createContractReferenceUnitList(l_request);
            
            assertEquals(1,l_ifoFuturesContractReferenceUnit.length);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 「create建玉照会明細一覧」取得的場合(三件)
     *
     */
    public void testCreateContractReferenceUnitList_C0004()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnitList_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3IfoTradedProductImplForTest l_tradedProduct = new WEB3IfoTradedProductImplForTest(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class },
                    l_tradedProduct);
                    
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setContractId(1001);
            l_ifoContractParams1.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002);
            l_ifoContractParams2.setMarketId(3304L);
            TestDBUtility.insertWithDel(l_ifoContractParams2);
            
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3304L);
            l_marketParams2.setMarketCode("S2");
            TestDBUtility.insertWithDel(l_marketParams2);
            
            IfoContractParams l_ifoContractParams3 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams3.setContractId(1003);
            l_ifoContractParams3.setMarketId(3305L);
            TestDBUtility.insertWithDel(l_ifoContractParams3);
            
            MarketParams l_marketParams3 = TestDBUtility.getMarketRow();
            l_marketParams3.setMarketId(3305L);
            l_marketParams3.setMarketCode("S3");
            TestDBUtility.insertWithDel(l_marketParams3);
            
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
            l_request.id = new String[]{"1001","1002","1003"};
            WEB3FuturesContractReferenceUnit[] l_ifoFuturesContractReferenceUnit =
                l_service.createContractReferenceUnitList(l_request);
            
            assertEquals(3,l_ifoFuturesContractReferenceUnit.length);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 建日の場合
     * 「建単価」降順 「建日」リクエストデータ.ソートキー.昇順
     *
     */
    public void testSortContractUnitList_C0001()
    {
        final String STR_METHOD_NAME = "testSortContractUnitList_C0001()";
        log.entering(STR_METHOD_NAME); 
        try
        {
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[4];
            
            l_contractUnits[0] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[0].contractPrice = "9000";
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2008,2,25);
            l_contractUnits[0].openDate = l_cal.getTime();
            
            l_contractUnits[1] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[1].contractPrice = "6000";
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,24);
            l_contractUnits[1].openDate = l_cal.getTime();
            
            l_contractUnits[2] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[2].contractPrice = "18000";
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,25);
            l_contractUnits[2].openDate = l_cal.getTime();
            
            l_contractUnits[3] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[3].contractPrice = "18000";
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,21);
            l_contractUnits[3].openDate = l_cal.getTime();

            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
//            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
//            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
//            l_request.futOpSortKeys[0].keyItem = "openDate";
//            l_request.futOpSortKeys[0].ascDesc = "A";
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "A";
            l_sortKey1.keyItem = "openDate";
            l_sortKeys[0] = l_sortKey1;
            l_request.futOpSortKeys = l_sortKeys;
            
            l_service.sortContractUnitList(l_contractUnits,l_request);
            
            // results
            // 18000    3.21
            // 6000     3.24
            // 18000    3.25
            // 9000     3.25
            assertEquals("18000",l_contractUnits[0].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,21);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[0].openDate,l_cal.getTime()));
            
            assertEquals("6000",l_contractUnits[1].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,24);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[1].openDate,l_cal.getTime()));
            
            assertEquals("18000",l_contractUnits[2].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,25);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[2].openDate,l_cal.getTime()));
            
            assertEquals("9000",l_contractUnits[3].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,25);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[3].openDate,l_cal.getTime()));
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 建単価の場合
     * 「建日」昇順 「建単価」リクエストデータ.ソートキー.降順
     *
     */
    public void testSortContractUnitList_C0002()
    {
        final String STR_METHOD_NAME = "testSortContractUnitList_C0002()";
        log.entering(STR_METHOD_NAME); 
        try
        {
            WEB3FuturesSettleContractInputServiceImpl l_service = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[4];
            
            l_contractUnits[0] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[0].contractPrice = "6000";
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2008,2,21);
            l_contractUnits[0].openDate = l_cal.getTime();
            
            l_contractUnits[1] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[1].contractPrice = "9000";
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,13);
            l_contractUnits[1].openDate = l_cal.getTime();
            
            l_contractUnits[2] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[2].contractPrice = "18000";
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,25);
            l_contractUnits[2].openDate = l_cal.getTime();
            
            l_contractUnits[3] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[3].contractPrice = "8000";
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,12);
            l_contractUnits[3].openDate = l_cal.getTime();

            WEB3FuturesCloseMarginInputRequest l_request = new WEB3FuturesCloseMarginInputRequest();
//            l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
//            l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
//            l_request.futOpSortKeys[0].keyItem = "openDate";
//            l_request.futOpSortKeys[0].ascDesc = "A";
            
            WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
            WEB3FuturesOptionsSortKey l_sortKey1 = new WEB3FuturesOptionsSortKey();
            l_sortKey1.ascDesc = "D";
            l_sortKey1.keyItem = "contractPrice";
            l_sortKeys[0] = l_sortKey1;
            l_request.futOpSortKeys = l_sortKeys;
            
            l_service.sortContractUnitList(l_contractUnits,l_request);
            
            // results
            // 18000    3.25
            // 9000     3.13
            // 8000     3.12
            // 6000     3.21
            assertEquals("18000",l_contractUnits[0].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,25);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[0].openDate,l_cal.getTime()));
            
            assertEquals("9000",l_contractUnits[1].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,13);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[1].openDate,l_cal.getTime()));
            
            assertEquals("8000",l_contractUnits[2].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,12);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[2].openDate,l_cal.getTime()));
            
            assertEquals("6000",l_contractUnits[3].contractPrice);
            
            l_cal = Calendar.getInstance();
            l_cal.set(2008,2,21);
            assertEquals(0,WEB3DateUtility.compareToDay(l_contractUnits[3].openDate,l_cal.getTime()));
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testSetCloseMarginListLine_C0001()
    {
        final String STR_METHOD_NAME = "testSetCloseMarginListLine_C0001()";
        log.entering(STR_METHOD_NAME); 
        try
        {
            WEB3FuturesCloseMarginGroup l_closeMarginListLine = new WEB3FuturesCloseMarginGroup();
            WEB3FuturesOptionsContractUnit[] l_contractUnit = new WEB3FuturesOptionsContractUnit[1];
            l_contractUnit[0] = new WEB3FuturesOptionsContractUnit();
            l_contractUnit[0].contractQuantity = "100";
            l_contractUnit[0].contractPrice = "10";
            l_contractUnit[0].contractExecPrice = "20";
            l_contractUnit[0].contractCommission = "30";
            l_contractUnit[0].income = "40";
            l_contractUnit[0].incomeCost = "50";
            
            WEB3FuturesSettleContractInputServiceImpl l_impl = new WEB3FuturesSettleContractInputServiceImpl();
            WEB3FuturesCloseMarginGroup l_group = l_impl.setCloseMarginListLine(l_closeMarginListLine, l_contractUnit);
            
            assertEquals("10", l_group.contractPrice);
            assertEquals("20", l_group.contractExecPrice);
            assertEquals("30", l_group.contractCommission);
            assertEquals("40", l_group.income);
            assertEquals("50", l_group.incomeCost);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();

            l_productParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setTargetMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductUpdqRow.TYPE);
            TradedProductUpdqParams l_tradedProductUpdqParams = TestDBUtility.getTradedProductUpdqRow();
            l_tradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_tradedProductUpdqParams.setProductId(1006149081018L);
            l_tradedProductUpdqParams.setMarketId(3303L);
            l_tradedProductUpdqParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setProductId(1006149081018L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3OptionClientRequestServiceForMock extends WEB3FuturesSettleContractInputServiceImpl
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
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
            // TODO Auto-generated constructor stub
        }
        
    }
    
}
@
