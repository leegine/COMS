head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP新規建入力サービスImplTest(WEB3OptionOpenContractInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImplForMock;
import webbroker3.ifo.data.IfoDeliveryMonthMasterParams;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.ifo.data.IfoIndexMasterParams;
import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3OptionsOrderHistoryRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.PutAndCall;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractInputServiceImplTest.class);
    
    private boolean l_blnIsCallSuper = false;
    
    private String l_strQuotoType = null;
    
    public WEB3OptionOpenContractInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        WEB3IfoTradedProductImplForMock.clearIfoTraded();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public static String TIMESTAMP_TAG = "xblocks.gtl.attributes.systemtimestamp";
    public class WEB3OptionsOpenMarginInputRequestForMock 
    extends WEB3OptionsOpenMarginInputRequest
{
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3OptionsOpenMarginInputRequestForMock + validate()";
        log.entering(STR_METHOD_NAME);
                  
        log.exiting(STR_METHOD_NAME);
    }
}
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl.createInputScreen(WEB3OptionsOpenMarginInputRequest)'
     */
    //is出来るまで注文取扱可能<取引最終日考慮>( )がtrue
//    public void testCreateInputScreenCase1()
//    {
//        final String STR_METHOD_NAME = "testCreateInputScreenCase1()";
//        log.entering(STR_METHOD_NAME);
//
//        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//        l_ifoContractParams.setSessionType("1");
//        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
//        l_ifoContractParams.setProductId(1006160060005L);
//        l_ifoContractParams.setMarketId(3303L);
//        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
//        l_ifoContractParams.setContractId(1001);
//        l_ifoContractParams.setAccountId(333812512203L);
//        l_ifoContractParams.setSubAccountId(33381251220301L);
//        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
//        
//        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512203L);
//        
//        BranchParams l_branchParams = TestDBUtility.getBranchRow();
//        l_branchParams.setBranchCode("123");
//        
//        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512203L);
//        l_subAccountParams.setSubAccountId(33381251220301L);
//        l_subAccountParams.setInstitutionCode("0D");
//        
//        
//        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//        l_ifoProductParams.setProductId(1006160060005L);
//        l_ifoProductParams.setProductCode("05");
//        l_ifoProductParams.setTargetMarketId(3303L);
//        l_ifoProductParams.setInstitutionCode("0D");
//        l_ifoProductParams.setFutureOptionDiv("2");
//        l_ifoProductParams.setUnderlyingProductCode("0005");
//        
//        ProductParams l_productParams = TestDBUtility.getProductRow();
//        l_productParams.setProductId(1006160060005L);
//
//        MarketParams l_marketParams = TestDBUtility.getMarketRow();
//        l_marketParams.setMarketId(3303L);
//        l_marketParams.setMarketCode("0");
//        
//        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//        l_institutionParams.setInstitutionName("信用アルファ@証券株式会社");
//        
//        IfoTradedProductParams l_ifoTradedProductParams =
//            TestDBUtility.getIfoTradedProductRow();
//        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductParams.setProductId(1006160060005L);
//        l_ifoTradedProductParams.setMarketId(3303L);
//        Calendar l_date =  Calendar.getInstance();
//        l_date.add(Calendar.DATE, 1);
//        Date l_dat = l_date.getTime();
//        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
//        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
//        
//        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//        l_tradedProductParams.setProductId(1006160060005L);
//        l_tradedProductParams.setMarketId(3303L);
//        l_tradedProductParams.setTradedProductId(100106139070605L);
//        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
//        
//        EnableOrderConditionParams l_enableOrderConditionParams =
//            TestDBUtility.getEnableOrderConditionParamsRow();
//        l_enableOrderConditionParams.setInstitutionCode("0D");
//        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
//        l_enableOrderConditionParams.setFutureOptionDiv("2");
//        l_enableOrderConditionParams.setMarginTradingDiv("0");
//        l_enableOrderConditionParams.setMarketCode("0");
//        
//        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//        l_tradingTimeParams.setInstitutionCode("0D");
//        l_tradingTimeParams.setBranchCode("123");
//        l_tradingTimeParams.setMarketCode("0");
//        l_tradingTimeParams.setTradingTimeType("01");
//        l_tradingTimeParams.setProductCode("0005");
//        l_tradingTimeParams.setBizDateType("1");
//        l_tradingTimeParams.setSessionType("0");
//        
//        
//        BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
//        l_branchIndexDealtCondParams.setInstitutionCode("0D");
//        l_branchIndexDealtCondParams.setBranchCode("123");
//        l_branchIndexDealtCondParams.setMarketCode("0");
//        l_branchIndexDealtCondParams.setFutureOptionDiv("2");
//        l_branchIndexDealtCondParams.setTargetProductCode("0005");
//        l_branchIndexDealtCondParams.setEnableOrder("1");
//        
//        IfoIndexMasterParams l_indexMasterParams = new IfoIndexMasterParams();
//        l_indexMasterParams.setIndexId(1);
//        l_indexMasterParams.setUnderlyingProductCode("0005");
//        l_indexMasterParams.setFutureOptionDiv("2");
//        
//        try
//        {
//            TestDBUtility.deleteAll(CalendarRow.TYPE);
//            
//            TestDBUtility.deleteAll(IfoIndexMasterRow.TYPE);
//            TestDBUtility.insertWithDel(l_indexMasterParams);
//            
//            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            
//            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_productParams);
//           
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
//            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
//            
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//            l_ifoTradedProductUpdqParams.setMarketId(3306L);
//            
//            
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
//            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
//                "validateOrder",
//                new Class[] {SubAccount.class, String.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
//                "getOptionBuyTradingPower",
//                new Class[] {WEB3GentradeSubAccount.class, IfoProduct.class},
//                new Double(0.5));
//
//        WEB3GentradeTradingClendarContext l_clendarContext =
//            (WEB3GentradeTradingClendarContext)
//                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
//        l_clendarContext.setInstitutionCode("0D");
//        l_clendarContext.setProductCode("0005");
//        l_clendarContext.setMarketCode("0");
//        l_clendarContext.setBizDateType("1");
//        
//        try
//        {
//            WEB3IfoTradedProductImpl l_tradedProductImpl = 
//                new WEB3IfoTradedProductImpl(100106139070605L);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//                    "validateTradedProduct",
//                    new Class[]{
//                        WEB3IfoProductImpl.class,
//                        WEB3GentradeMarket.class,
//                        boolean.class,
//                        boolean.class},
//                        l_tradedProductImpl);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        try
//        {
//            Calendar ca =  Calendar.getInstance();
//            ca.set(2007,6-1,18);
//            
//            Date date = ca.getTime();
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
//            WEB3OptionClientRequestServiceForMock l_impl =
//                new WEB3OptionClientRequestServiceForMock();
//            WEB3OptionsOpenMarginInputRequestForMock l_request =
//                new WEB3OptionsOpenMarginInputRequestForMock();
//            l_request.contractType = "1";
//            l_request.opProductCode = "05";
//
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            WEB3OptionsOpenMarginInputResponse l_marginInputResponse =
//                l_impl.createInputScreen(l_request);
//            //有効期限開始日
//            assertEquals(WEB3DateUtility.formatDate(l_marginInputResponse.expirationStartDate,"yyyyMMdd"), "20070619");
//            //有効期限最終日
//            assertEquals(WEB3DateUtility.formatDate(l_marginInputResponse.expirationEndDate,"yyyyMMdd"), "20070622");
//            //有効期限内祝日一覧
//            assertNull(l_marginInputResponse.holidayList);
//            //立会区分
//            assertEquals("0",l_marginInputResponse.sessionType);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //is出来るまで注文取扱可能<取引最終日考慮>( )がfalse
//    public void testCreateInputScreenCase2()
//    {
//        final String STR_METHOD_NAME = "testCreateInputScreenCase2()";
//        log.entering(STR_METHOD_NAME);
//
//        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//        l_ifoContractParams.setSessionType("1");
//        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
//        l_ifoContractParams.setProductId(1006160060005L);
//        l_ifoContractParams.setMarketId(3303L);
//        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
//        l_ifoContractParams.setContractId(1001);
//        l_ifoContractParams.setAccountId(333812512203L);
//        l_ifoContractParams.setSubAccountId(33381251220301L);
//        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
//        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
//        
//        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512203L);
//        
//        BranchParams l_branchParams = TestDBUtility.getBranchRow();
//        l_branchParams.setBranchCode("123");
//        
//        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512203L);
//        l_subAccountParams.setSubAccountId(33381251220301L);
//        l_subAccountParams.setInstitutionCode("0D");
//        
//        
//        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//        l_ifoProductParams.setProductId(1006160060005L);
//        l_ifoProductParams.setProductCode("05");
//        l_ifoProductParams.setTargetMarketId(3303L);
//        l_ifoProductParams.setInstitutionCode("0D");
//        l_ifoProductParams.setFutureOptionDiv("2");
//        l_ifoProductParams.setUnderlyingProductCode("0005");
//        
//        ProductParams l_productParams = TestDBUtility.getProductRow();
//        l_productParams.setProductId(1006160060005L);
//
//        MarketParams l_marketParams = TestDBUtility.getMarketRow();
//        l_marketParams.setMarketId(3303L);
//        l_marketParams.setMarketCode("0");
//        
//        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//        l_institutionParams.setInstitutionName("信用アルファ@証券株式会社");
//        
//        IfoTradedProductParams l_ifoTradedProductParams =
//            TestDBUtility.getIfoTradedProductRow();
//        l_ifoTradedProductParams.setTradedProductId(100106139070605L);
//        l_ifoTradedProductParams.setProductId(1006160060005L);
//        l_ifoTradedProductParams.setMarketId(3303L);
//        Calendar l_date =  Calendar.getInstance();
//        l_date.add(Calendar.DATE, 1);
//        Date l_dat = l_date.getTime();
//        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
//        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));
//        
//        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//        l_tradedProductParams.setProductId(1006160060005L);
//        l_tradedProductParams.setMarketId(3303L);
//        l_tradedProductParams.setTradedProductId(100106139070605L);
//        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
//        
//        EnableOrderConditionParams l_enableOrderConditionParams =
//            TestDBUtility.getEnableOrderConditionParamsRow();
//        l_enableOrderConditionParams.setInstitutionCode("0D");
//        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
//        l_enableOrderConditionParams.setFutureOptionDiv("2");
//        l_enableOrderConditionParams.setMarginTradingDiv("0");
//        l_enableOrderConditionParams.setMarketCode("0");
//        l_enableOrderConditionParams.setCarriedOrder("0");
//        
//        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//        l_tradingTimeParams.setInstitutionCode("0D");
//        l_tradingTimeParams.setBranchCode("123");
//        l_tradingTimeParams.setMarketCode("0");
//        l_tradingTimeParams.setTradingTimeType("01");
//        l_tradingTimeParams.setProductCode("0005");
//        l_tradingTimeParams.setBizDateType("1");
//        
//        
//        BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
//        l_branchIndexDealtCondParams.setInstitutionCode("0D");
//        l_branchIndexDealtCondParams.setBranchCode("123");
//        l_branchIndexDealtCondParams.setMarketCode("0");
//        l_branchIndexDealtCondParams.setFutureOptionDiv("2");
//        l_branchIndexDealtCondParams.setTargetProductCode("0005");
//        l_branchIndexDealtCondParams.setEnableOrder("1");
//        
//        IfoIndexMasterParams l_indexMasterParams = new IfoIndexMasterParams();
//        l_indexMasterParams.setIndexId(1);
//        l_indexMasterParams.setUnderlyingProductCode("0005");
//        l_indexMasterParams.setFutureOptionDiv("2");
//        try
//        {
//            TestDBUtility.deleteAll(IfoIndexMasterRow.TYPE);
//            TestDBUtility.insertWithDel(l_indexMasterParams);
//            
//            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            
//            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_productParams);
//           
//            TestDBUtility.deleteAll(MarketRow.TYPE);
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
//            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
//            
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            l_ifoTradedProductUpdqParams.setTradedProductId(100106139070605L);
//            l_ifoTradedProductUpdqParams.setMarketId(3306L);
//            
//            
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
//            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
//                "validateOrder",
//                new Class[] {SubAccount.class, String.class},
//                null);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
//                "getOptionBuyTradingPower",
//                new Class[] {WEB3GentradeSubAccount.class, IfoProduct.class},
//                new Double(0.5));
//
//        WEB3GentradeTradingClendarContext l_clendarContext =
//            (WEB3GentradeTradingClendarContext)
//                ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
//        l_clendarContext.setInstitutionCode("0D");
//        l_clendarContext.setProductCode("0005");
//        l_clendarContext.setMarketCode("0");
//        l_clendarContext.setBizDateType("1");
//        try
//        {
//            WEB3IfoTradedProductImpl l_tradedProductImpl = 
//                new WEB3IfoTradedProductImpl(100106139070605L);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
//                    "validateTradedProduct",
//                    new Class[]{
//                        WEB3IfoProductImpl.class,
//                        WEB3GentradeMarket.class,
//                        boolean.class,
//                        boolean.class},
//                        l_tradedProductImpl);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//
//        try
//        {
//            Calendar ca =  Calendar.getInstance();
//            ca.set(2007,6-1,19);
//            
//            Date date = ca.getTime();
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
//            WEB3OptionClientRequestServiceForMock l_impl =
//                new WEB3OptionClientRequestServiceForMock();
//            WEB3OptionsOpenMarginInputRequestForMock l_request =
//                new WEB3OptionsOpenMarginInputRequestForMock();
//            l_request.contractType = "1";
//            l_request.opProductCode = "05";
//            WEB3OptionsOpenMarginInputResponse l_marginInputResponse =
//                l_impl.createInputScreen(l_request);
//            //有効期限開始日
//            assertNull(l_marginInputResponse.expirationStartDate);
//            //有効期限最終日
//            assertNull(l_marginInputResponse.expirationEndDate);
//            //有効期限内祝日一覧
//            assertNull(l_marginInputResponse.holidayList);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /**
     * パラメータタイプ不正
     *
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImpl();
        WEB3OptionsOrderHistoryRequest l_request = new WEB3OptionsOrderHistoryRequest();
        try
        {
            l_seriveImpl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 株価指数オプション新規建注文入力画面リクエストの場合
     *
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
        WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
        try
        {
            this.l_blnIsCallSuper = true;
            WEB3OptionsOpenMarginInputResponse l_response = (WEB3OptionsOpenMarginInputResponse)l_seriveImpl.execute(l_request);
            assertEquals("20.6",l_response.currentPrice);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 株価指数オプション新規建注文入力画面リクエストの場合
     *
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
        WEB3OptionsProductSelectRequest l_request = new WEB3OptionsProductSelectRequest();
        try
        {
            this.l_blnIsCallSuper = true;
            WEB3OptionsProductSelectResponse l_response = (WEB3OptionsProductSelectResponse)l_seriveImpl.execute(l_request);
            assertEquals("opTradingPower",l_response.opTradingPower);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * get銘柄 = null
     * 抛出異常信息：BUSINESS_ERROR_00735
     * 
     *
     */
    
    public void testCreateInputScreen_C0001()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            this.getMockData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    null);
                    
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_response = l_seriveImpl.createInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * get取引銘柄沒有取到
     * 抛出異常信息：BUSINESS_ERROR_00735
     * 
     *
     */
    public void testCreateInputScreen_C0002()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            this.getMockData();
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_response = l_seriveImpl.createInputScreen(l_request);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00735,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * get取引銘柄 = null
     * 抛出異常信息：BUSINESS_ERROR_00735
     * 
     *
     */
    public void testCreateInputScreen_C0003()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0003()";
        log.entering(TEST_START+STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            this.getMockData();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    null);
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_response = l_seriveImpl.createInputScreen(l_request);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00735,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * 不是「出来るまで注文」の場合
     * 不是「isリアル顧客」の場合
     * (部店指数別)取扱条件オブジェクト.length=3
     *
     */
    public void testCreateInputScreen_C0004()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"0006","0007","0008"},"2");
            this.getMockData();
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoTradedProduct",
            new Class[] { Institution.class, String.class, String.class },
            l_tradedProduct);
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            
            assertNotNull(l_marginInputResponse);
            assertEquals("1",l_marginInputResponse.orderPriceDivList[0]);
            assertEquals("0006",l_marginInputResponse.targetProductList[0]);
            assertEquals("0007",l_marginInputResponse.targetProductList[1]);
            assertEquals("0008",l_marginInputResponse.targetProductList[2]);
            assertEquals("160030005",l_marginInputResponse.opProductCode);
            assertEquals("0005",l_marginInputResponse.targetProductCode);
            assertEquals("SP",l_marginInputResponse.marketCode);
            assertEquals("200503",l_marginInputResponse.delivaryMonth);
            assertEquals("0",l_marginInputResponse.strikePrice);
            assertEquals("0",l_marginInputResponse.comparedPreviousDay);
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * 不是「出来るまで注文」の場合
     * 不是「isリアル顧客」の場合
     * (部店指数別)取扱条件オブジェクト.length=0
     *
     */
    public void testCreateInputScreen_C0005()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
             WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            this.getMockData();

            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0385");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl", 
            "getIfoTradedProduct",
            new Class[] { Institution.class, String.class, String.class },
            l_tradedProduct);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            
            assertNotNull(l_marginInputResponse);
            assertEquals("1",l_marginInputResponse.orderPriceDivList[0]);
            assertEquals("160030005",l_marginInputResponse.opProductCode);
            assertEquals("0005",l_marginInputResponse.targetProductCode);
            assertEquals("SP",l_marginInputResponse.marketCode);
            assertEquals("200503",l_marginInputResponse.delivaryMonth);
            assertEquals("0",l_marginInputResponse.strikePrice);
            assertEquals("0",l_marginInputResponse.comparedPreviousDay);
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * リクエストデータの取引市場!=null
     * リクエストデータの指数種別!=null
     * リクエストデータの行使価格!=null
     * リクエストデータの限月!=null
     * リクエストデータのオプション商品区分 = コールオプション
     * 先物OP取引銘柄オブジェクト沒有取到
     * 
     * 抛出異常信息：BUSINESS_ERROR_00735
     *
     */
    public void testCreateInputScreen_C0006()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            this.getMockData();
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = "05";
            l_request.targetProductCode = "0018";
            l_request.strikePrice = "";
            l_request.delivaryMonth = "20070906";
            l_request.opProductType = "P";
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            fail();
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00735,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * リクエストデータの取引市場!=null
     * リクエストデータの指数種別!=null
     * リクエストデータの行使価格==""
     * リクエストデータの限月!=null
     * リクエストデータのオプション商品区分 = プットオプション
     * 先物OP取引銘柄オブジェクト= null
     * 
     * 抛出異常信息：BUSINESS_ERROR_00735
     *
     */
    public void testCreateInputScreen_C0007()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            this.getMockData();
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            null);
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = "05";
            l_request.targetProductCode = "0018";
            l_request.strikePrice = "1560";
            l_request.delivaryMonth = "20070906";
            l_request.opProductType = "C";
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            fail();
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00735,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * リクエストデータの取引市場!=null
     * リクエストデータの指数種別!=null
     * リクエストデータの行使価格==""
     * リクエストデータの限月!=null
     * リクエストデータのオプション商品区分 = プットオプション
     * 先物OP取引銘柄オブジェクト!= null
     * 
     *
     */
    public void testCreateInputScreen_C0008()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            this.getMockData();
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isRealCustomer", 
                    new Class[]{},
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getOptionBuyTradingPower",
                    new Class[]{ WEB3GentradeSubAccount.class, IfoProduct.class },
                    new Double(-5000000));
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = "05";
            l_request.targetProductCode = "0018";
            l_request.strikePrice = "1560";
            l_request.delivaryMonth = "20070906";
            l_request.opProductType = "C";
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            
            assertNotNull(l_marginInputResponse);
            assertEquals("1",l_marginInputResponse.orderPriceDivList[0]);
            assertEquals("160030005",l_marginInputResponse.opProductCode);
            assertEquals("0018",l_marginInputResponse.targetProductCode);
            assertEquals("05",l_marginInputResponse.marketCode);
            assertEquals("20070906",l_marginInputResponse.delivaryMonth);
            assertEquals("1560",l_marginInputResponse.strikePrice);
            assertEquals("0",l_marginInputResponse.comparedPreviousDay);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * get銘柄 發生異常信息
     * 抛出異常信息：BUSINESS_ERROR_00301
     * 
     *
     */
    
    public void testCreateInputScreen_C0009()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
//            this.getMockData();
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512203L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
                    
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_response = l_seriveImpl.createInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * リクエストデータの取引市場!=null
     * リクエストデータの指数種別!=null
     * リクエストデータの行使価格==""
     * リクエストデータの限月!=null
     * リクエストデータのオプション商品区分 = プットオプション
     * 先物OP銘柄オブジェクト發生異常信息
     * 
     * 抛出異常信息：BUSINESS_ERROR_00301
     *
     */
    public void testCreateInputScreen_C0010()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0010()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
//            this.getMockData();
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512203L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = "05";
            l_request.targetProductCode = "0018";
            l_request.strikePrice = "1560";
            l_request.delivaryMonth = "20070906";
            l_request.opProductType = "C";
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            fail();
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * リクエストデータの取引市場!=null
     * リクエストデータの指数種別!=null
     * リクエストデータの行使価格==""
     * リクエストデータの限月!=null
     * リクエストデータのオプション商品区分 = プットオプション
     * 先物OP銘柄オブジェクト= null
     * 
     * 抛出異常信息：BUSINESS_ERROR_00301
     *
     */
    public void testCreateInputScreen_C0011()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
//            this.getMockData();
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512203L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class },
                            null);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = "05";
            l_request.targetProductCode = "0018";
            l_request.strikePrice = "1560";
            l_request.delivaryMonth = "20070906";
            l_request.opProductType = "C";
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            fail();
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 買建
     * 銘柄コードがnullでない場合は
     * リクエストデータの取引市場==null
     * リクエストデータの指数種別==null
     * リクエストデータの行使価格==null
     * リクエストデータの限月==null
     * リクエストデータのオプション商品区分 ==null
     * 先物OP取引銘柄オブジェクト!= null
     * 
     *
     */
    public void testCreateInputScreen_C0012()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0012()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            this.getMockData();
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isRealCustomer", 
                    new Class[]{},
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getOptionBuyTradingPower",
                    new Class[]{ WEB3GentradeSubAccount.class, IfoProduct.class },
                    null);
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = null;
            l_request.targetProductCode = null;
            l_request.strikePrice = null;
            l_request.delivaryMonth = null;
            l_request.opProductType = null;
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            
            assertNotNull(l_marginInputResponse);
            assertEquals("1",l_marginInputResponse.orderPriceDivList[0]);
            assertNull(l_marginInputResponse.opProductCode);
            assertNull(l_marginInputResponse.targetProductCode);
            assertNull(l_marginInputResponse.marketCode);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     * 売建
     * 銘柄コードがnullでない場合は
     * 不是「出来るまで注文」の場合
     * 不是「isリアル顧客」の場合
     *
     */
    public void testCreateInputScreen_C0013()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0013()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
             WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            this.getMockData();

            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0385");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);

            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl", 
            "getIfoTradedProduct",
            new Class[] { Institution.class, String.class, String.class },
            l_tradedProduct);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingOpenContractOrder", 
                    new Class[]{ SubAccount.class, boolean.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getFuturesOptionTradingPower",
                    new Class[]
                    { WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class },
                    new Double(6000000));
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "2";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            
            assertNotNull(l_marginInputResponse);
            assertEquals("1",l_marginInputResponse.orderPriceDivList[0]);
            assertEquals("160030005",l_marginInputResponse.opProductCode);
            assertEquals("0005",l_marginInputResponse.targetProductCode);
            assertEquals("SP",l_marginInputResponse.marketCode);
            assertEquals("200503",l_marginInputResponse.delivaryMonth);
            assertEquals("0",l_marginInputResponse.strikePrice);
            assertEquals("0",l_marginInputResponse.comparedPreviousDay);
            
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * リクエストデータ.銘柄コード == nullの場合
     *
     */
    public void testCreateInputScreen_C0014()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0014()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2007,10,1);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_cal.getTime());
          
            this.getMockData();
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isRealCustomer", 
                    new Class[]{},
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getOptionBuyTradingPower",
                    new Class[]{ WEB3GentradeSubAccount.class, IfoProduct.class },
                    null);
            
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = null;
            l_request.marketCode = "05";
            l_request.targetProductCode = "0018";
            l_request.strikePrice = "1560";
            l_request.delivaryMonth = "20070906";
            l_request.opProductType = "C";
            
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            assertEquals(WEB3DateUtility.getDate("20071102", "yyyyMMdd"), l_marginInputResponse.expirationStartDate);
            assertEquals(WEB3DateUtility.getDate("20071102", "yyyyMMdd"), l_marginInputResponse.expirationEndDate);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエストデータ.銘柄コード != nullの場合
     *
     */
    public void testCreateInputScreen_C0015()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0015()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2007,10,1);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_cal.getTime());
          
            this.getMockData();
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setProductCode("0018");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setOrderAcceptProduct("1");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setEnableOrder("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setSubmitMarketTrigger(l_clendarContext.getSubmitMarketTrigger());
            l_tradingTimeParams.setEnableOrder(l_clendarContext.getEnableOrder());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);


            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,
                        String.class,
                        String.class},
                        new Long(0));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isRealCustomer", 
                    new Class[]{},
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getOptionBuyTradingPower",
                    new Class[]{ WEB3GentradeSubAccount.class, IfoProduct.class },
                    null);
            
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "33";
            
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            assertNull(l_marginInputResponse.expirationStartDate);
            assertNull(l_marginInputResponse.expirationEndDate);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //先物OP限月追加対応
    public void testCreateInputScreen_C0016()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0016()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            this.initData();
            WEB3IfoTradedProductImplForMock.getIfoTradedProduct(1006160060008L,3303L,330304148080002L);
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(new String[]{"0006","0007","0008"},"2");
            this.getMockData();
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "getIfoTradedProduct",
            new Class[] { Institution.class, String.class, String.class },
            l_tradedProduct);
                                
            WEB3OptionOpenContractInputServiceImpl l_seriveImpl = new WEB3OptionOpenContractInputServiceImplForTest();
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequestForTest();
            l_request.contractType = "1";
            l_request.opProductCode = "0215";
            WEB3OptionsOpenMarginInputResponse l_marginInputResponse = l_seriveImpl.createInputScreen(l_request);
            
            assertNotNull(l_marginInputResponse);
            assertEquals(5, l_marginInputResponse.delivaryMonthList.length);
            assertEquals("200710", l_marginInputResponse.delivaryMonthList[0]);
            assertEquals("200711", l_marginInputResponse.delivaryMonthList[1]);
            assertEquals("200712", l_marginInputResponse.delivaryMonthList[2]);
            assertEquals("200708", l_marginInputResponse.delivaryMonthList[3]);
            assertEquals("200709", l_marginInputResponse.delivaryMonthList[4]);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
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
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060008L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060008L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_marketParams);
//            
//            TestDBUtility.deleteAll(TradedProductRow.TYPE);
//            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
//            l_tradedProductParams.setTradedProductId(330304148080002L);
//            l_tradedProductParams.setProductId(1006160060008L);
//            l_tradedProductParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_tradedProductParams);
//            
//            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
//            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
//            l_IfoTradedProductParams.setProductId(1006160060008L);
//            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
//            l_IfoTradedProductParams.setMarketId(3303L);
//            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
//            
//            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
//            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
//            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
//            l_ifoTradedProductUpdqParams.setMarketId(3306L);
//            
//            
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
//            
//            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
//            l_ifoTradedProductUpdqParams.setProductId(1006160060008L);
//            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
//            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
//            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
//            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
//            l_branchIndexDealtCondParams1.setBranchCode("381");
//            l_branchIndexDealtCondParams1.setMarketCode("0");
//            l_branchIndexDealtCondParams1.setFutureOptionDiv("2");
//            l_branchIndexDealtCondParams1.setTargetProductCode("0005");
//            l_branchIndexDealtCondParams1.setEnableOrder("1");
//            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
////            
//            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
//            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
//            l_branchIndexDealtCondParams2.setBranchCode("381");
//            l_branchIndexDealtCondParams2.setMarketCode("0");
//            l_branchIndexDealtCondParams2.setFutureOptionDiv("2");
//            l_branchIndexDealtCondParams2.setTargetProductCode("0006");
//            l_branchIndexDealtCondParams2.setEnableOrder("1");
//            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams1.setInstitutionCode("0D");
//            l_tradingTimeParams1.setBranchCode("381");
//            l_tradingTimeParams1.setMarketCode("0");
//            l_tradingTimeParams1.setTradingTimeType("26");
//            l_tradingTimeParams1.setProductCode("0005");
//            l_tradingTimeParams1.setBizDateType("1");
//            l_tradingTimeParams1.setSubmitMarketTrigger("1");
//            l_tradingTimeParams1.setEnableOrder("0");
//            l_tradingTimeParams1.setBizdateCalcParameter("0");
//            l_tradingTimeParams1.setStartTime("000000");
//            l_tradingTimeParams1.setEndTime("150001");
////            
//            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//            
//            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams2.setInstitutionCode("0D");
//            l_tradingTimeParams2.setBranchCode("381");
//            l_tradingTimeParams2.setMarketCode("0");
//            l_tradingTimeParams2.setTradingTimeType("26");
//            l_tradingTimeParams2.setProductCode("0006");
//            l_tradingTimeParams2.setBizDateType("1");
//            l_tradingTimeParams2.setSubmitMarketTrigger("1");
//            l_tradingTimeParams2.setEnableOrder("0");
//            l_tradingTimeParams2.setBizdateCalcParameter("0");
//            l_tradingTimeParams2.setStartTime("000000");
//            l_tradingTimeParams2.setEndTime("150001");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            

          
//          EnableOrderConditionDao
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("2");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            // IfoIndexMasterDao
            
            TestDBUtility.deleteAll(IfoIndexMasterRow.TYPE);
            IfoIndexMasterParams l_ifoIndexMasterParams1 = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams1.setIndexId(123L);
            l_ifoIndexMasterParams1.setUnderlyingProductCode("0006");
            l_ifoIndexMasterParams1.setFutureOptionDiv("2");
            l_ifoIndexMasterParams1.setStandardName("日経３００");
//            l_ifoIndexMasterParams1.setDelivaliyMonth1("200710");
//            l_ifoIndexMasterParams1.setDelivaliyMonth1("200711");
//            l_ifoIndexMasterParams1.setDelivaliyMonth1("200712");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams1);
            
            IfoIndexMasterParams l_ifoIndexMasterParams2 = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams2.setIndexId(124L);
            l_ifoIndexMasterParams2.setUnderlyingProductCode("0007");
            l_ifoIndexMasterParams2.setFutureOptionDiv("2");
            l_ifoIndexMasterParams2.setStandardName("日経２５５");
//            l_ifoIndexMasterParams2.setDelivaliyMonth1("200708");
//            l_ifoIndexMasterParams2.setDelivaliyMonth1("200709");
//            l_ifoIndexMasterParams2.setDelivaliyMonth1("200710");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams2);
            
            IfoIndexMasterParams l_ifoIndexMasterParams3 = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams3.setIndexId(125L);
            l_ifoIndexMasterParams3.setUnderlyingProductCode("0008");
            l_ifoIndexMasterParams3.setFutureOptionDiv("2");
            l_ifoIndexMasterParams3.setStandardName("TOPIX");
//            l_ifoIndexMasterParams3.setDelivaliyMonth1("200709");
//            l_ifoIndexMasterParams3.setDelivaliyMonth1("200710");
//            l_ifoIndexMasterParams3.setDelivaliyMonth1("200711");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams3);
            
            //IfoDeliveryMonthMasterRow
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);
            
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams1 =
                new IfoDeliveryMonthMasterParams();
            l_ifoDeliveryMonthMasterParams1.setIndexId(123L);
            l_ifoDeliveryMonthMasterParams1.setUnderlyingProductCode("0006");
            l_ifoDeliveryMonthMasterParams1.setFutureOptionDiv("2");
            l_ifoDeliveryMonthMasterParams1.setDeliveryMonth("200710");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams1);
            l_ifoDeliveryMonthMasterParams1.setDeliveryMonth("200711");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams1);
            l_ifoDeliveryMonthMasterParams1.setDeliveryMonth("200712");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams1);
          
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams2 =
                new IfoDeliveryMonthMasterParams();
            l_ifoDeliveryMonthMasterParams2.setIndexId(124L);
            l_ifoDeliveryMonthMasterParams2.setUnderlyingProductCode("0007");
            l_ifoDeliveryMonthMasterParams2.setFutureOptionDiv("2");
            l_ifoDeliveryMonthMasterParams2.setDeliveryMonth("200708");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams2);
            l_ifoDeliveryMonthMasterParams2.setDeliveryMonth("200709");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams2);
            l_ifoDeliveryMonthMasterParams2.setDeliveryMonth("200710");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams2);
          
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams3 =
                new IfoDeliveryMonthMasterParams();
            l_ifoDeliveryMonthMasterParams3.setIndexId(125L);
            l_ifoDeliveryMonthMasterParams3.setUnderlyingProductCode("0008");
            l_ifoDeliveryMonthMasterParams3.setFutureOptionDiv("2");
            l_ifoDeliveryMonthMasterParams3.setDeliveryMonth("200709");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams3);
            l_ifoDeliveryMonthMasterParams3.setDeliveryMonth("200710");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams3);
            l_ifoDeliveryMonthMasterParams3.setDeliveryMonth("200711");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams3);
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void getMockData()
    {
        final String STR_METHOD_NAME = "getMockData()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512203L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            WEB3IfoProductImpl l_ifoProcut = new WEB3IfoProductImplForTest(1006160060008L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProcut);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                            double.class, String.class, String.class },
                            l_ifoProcut);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateHandlingIndex",
                    new Class[]{String.class, WEB3IfoTradedProductImpl.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                    "getOptionBuyTradingPower",
                    new Class[]{ WEB3GentradeSubAccount.class, IfoProduct.class },
                    new Double(5000000));
            
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setBranchCode("381");
//            l_clendarContext.setMarketCode("0");
//            l_clendarContext.setProductCode("0005");
//            l_clendarContext.setTradingTimeType("26");
//            l_clendarContext.setOrderAcceptProduct("1");
//            l_clendarContext.setBizDateType("1");
//            l_clendarContext.setEnableOrder("0");
//            l_clendarContext.setBizdateCalcParameter("0");
//            l_clendarContext.setSubmitMarketTrigger("1");
//            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
     
          
          
          WEB3IfoQuoteDataForTest l_quoteData = new WEB3IfoQuoteDataForTest();
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                  "getIfoQuote",
                  new Class[]
                  {IfoTradedProduct.class,RealType.class},
                  l_quoteData);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3IfoQuoteDataForTest implements WEB3IfoQuoteData
    {

        public double getCurrentPrice()
        {
            return 0;
        }

        public double getBidPrice()
        {
            return 0;
        }

        public double getAskPrice()
        {
            return 0;
        }

        public double getOpenPrice()
        {
            return 0;
        }

        public double getLastClosingPrice()
        {
            return 0;
        }

        public Timestamp getQuoteTimestamp()
        {
            return null;
        }

        public Date getQuoteDate()
        {
            return null;
        }

        public RealType getRealType()
        {
            return null;
        }

        public DataType getDataType()
        {
            return null;
        }

        public String getMarketCode()
        {
            return null;
        }

        public String getProductCode()
        {
            return null;
        }

        public String getMonthOfDelivery()
        {
            return null;
        }

        public PutAndCall getPutAndCall()
        {
            return null;
        }

        public double getStrikePrice()
        {
            return 0;
        }

        public Timestamp getOpenPriceTime()
        {
            return null;
        }

        public double getHighPrice()
        {
            return 0;
        }

        public Timestamp getHighPriceTime()
        {
            return null;
        }

        public double getLowPrice()
        {
            return 0;
        }

        public Timestamp getLowPriceTime()
        {
            return null;
        }

        public Timestamp getCurrentPriceTime()
        {
            return null;
        }

        public CurrentPriceFlag getCurrentPriceFlag()
        {
            return null;
        }

        public double getChange()
        {
            return 0;
        }

        public double getVolume()
        {
            return 0;
        }

        public Timestamp getVolumeTime()
        {
            return null;
        }

        public AskPriceTitle getAskPriceTitle()
        {
            return null;
        }

        public Timestamp getAskPriceTime()
        {
            return null;
        }

        public BidPriceTitle getBidPriceTitle()
        {
            return null;
        }

        public Timestamp getBidPriceTime()
        {
            return null;
        }

        public double getBasePrice()
        {
            return 0;
        }
        
    }
    private class WEB3OptionClientRequestServiceForMock extends WEB3OptionOpenContractInputServiceImpl
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
    
    private class WEB3OptionOpenContractInputServiceImplForTest extends WEB3OptionOpenContractInputServiceImpl
    {
        public WEB3OptionsOpenMarginInputResponse createInputScreen(WEB3OptionsOpenMarginInputRequest l_request) throws WEB3BaseException
        {
            if(l_blnIsCallSuper)
            {
                WEB3OptionsOpenMarginInputResponse l_response = (WEB3OptionsOpenMarginInputResponse)l_request.createResponse();
                l_response.currentPrice = "20.6";
                return l_response;
            }
            return super.createInputScreen(l_request);
        }
        
        protected WEB3OptionsProductSelectResponse createProductSelectScreen(WEB3OptionsProductSelectRequest l_request) throws WEB3BaseException
        {
            if(l_blnIsCallSuper)
            {
                WEB3OptionsProductSelectResponse l_response = (WEB3OptionsProductSelectResponse)l_request.createResponse();
                l_response.opTradingPower = "opTradingPower";
                return l_response;
            }
            return super.createProductSelectScreen(l_request);

        }
        
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            return new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow());
        }
    }
    
    private class WEB3OptionsOpenMarginInputRequestForTest extends WEB3OptionsOpenMarginInputRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3IfoProductImplForTest extends WEB3IfoProductImpl
    {

        public WEB3IfoProductImplForTest(long l_lngProductID) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_lngProductID);
        }
        
        public Market getPrimaryMarket()           
        {  
            WEB3GentradeMarket l_market = null;
            try
            {
                l_market = new WEB3GentradeMarket(3303L);
            }
            catch (DataFindException e)
            {
                
            }
            catch (DataQueryException e)
            {
                
            }
            catch (DataNetworkException e)
            {
                
            }
            return l_market;
        }
        
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
        }
        
        public Date getLastTradingDate()
        {
            return new Date();
        }
        
    }
    
    /*
     * 取得した全ての(部店指数別)取扱条件オブジェクトに対しループする.
     * 部店指数別.length=1
     */
    public void testCreateProductSelectScreen_0001()
    {
        final String STR_METHOD_NAME = "testCreateProductSelectScreen_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsProductSelectRequestForMock l_request=
            new WEB3OptionsProductSelectRequestForMock();

        WEB3OptionClientRequestServiceForMock l_impl =
            new WEB3OptionClientRequestServiceForMock();
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            l_impl.createProductSelectScreen(l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class});
            assertEquals(WEB3GentradeSubAccountForMock.class, ((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals("2",l_paramsValue1.getFirstCalled()[1]);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 取得した全ての(部店指数別)取扱条件オブジェクトに対しループする.
     * 部店指数別.length=0
     */
    public void testCreateProductSelectScreen_0002()
    {
        final String STR_METHOD_NAME = "testCreateProductSelectScreen_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsProductSelectRequestForMock l_request=
            new WEB3OptionsProductSelectRequestForMock();

        WEB3OptionClientRequestServiceForMock l_impl =
            new WEB3OptionClientRequestServiceForMock();
        
        
        BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
        l_branchIndexDealtCondParams.setInstitutionCode("0D");
        l_branchIndexDealtCondParams.setBranchCode("111");
        l_branchIndexDealtCondParams.setMarketCode("0");
        l_branchIndexDealtCondParams.setFutureOptionDiv("2");
        l_branchIndexDealtCondParams.setTargetProductCode("0005");
        l_branchIndexDealtCondParams.setEnableOrder("1");
        
        
        try
        {
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            l_impl.createProductSelectScreen(l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class});
            assertEquals(WEB3GentradeSubAccountForMock.class, ((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals("2",l_paramsValue1.getFirstCalled()[1]);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 取得した全ての(部店指数別)取扱条件オブジェクトに対しループする.
     * 部店指数別.length=3
     */
    public void testCreateProductSelectScreen_0003()
    {

        final String STR_METHOD_NAME = "testCreateProductSelectScreen_0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsProductSelectRequestForMock l_request=
            new WEB3OptionsProductSelectRequestForMock();

        WEB3OptionClientRequestServiceForMock l_impl =
            new WEB3OptionClientRequestServiceForMock();

        try
        {
            //BranchIndexDealtCondRow
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setMarketCode("0");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams1.setTargetProductCode("0006");
            l_branchIndexDealtCondParams1.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);          
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setMarketCode("0");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams2.setTargetProductCode("0007");
            l_branchIndexDealtCondParams2.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams3 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams3.setInstitutionCode("0D");
            l_branchIndexDealtCondParams3.setBranchCode("381");
            l_branchIndexDealtCondParams3.setMarketCode("0");
            l_branchIndexDealtCondParams3.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams3.setTargetProductCode("0008");
            l_branchIndexDealtCondParams3.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams3);
          
            //IfoIndexMasterRow
            TestDBUtility.deleteAll(IfoIndexMasterRow.TYPE);
            IfoIndexMasterParams l_ifoIndexMasterParams1 = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams1.setIndexId(123L);
            l_ifoIndexMasterParams1.setUnderlyingProductCode("0006");
            l_ifoIndexMasterParams1.setFutureOptionDiv("2");
            l_ifoIndexMasterParams1.setStandardName("日経３００");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams1);
          
            IfoIndexMasterParams l_ifoIndexMasterParams2 = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams2.setIndexId(124L);
            l_ifoIndexMasterParams2.setUnderlyingProductCode("0007");
            l_ifoIndexMasterParams2.setFutureOptionDiv("2");
            l_ifoIndexMasterParams2.setStandardName("日経２５５");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams2);
          
            IfoIndexMasterParams l_ifoIndexMasterParams3 = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams3.setIndexId(125L);
            l_ifoIndexMasterParams3.setUnderlyingProductCode("0008");
            l_ifoIndexMasterParams3.setFutureOptionDiv("2");
            l_ifoIndexMasterParams3.setStandardName("TOPIX");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams3);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            l_impl.createProductSelectScreen(l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class});
            assertEquals(WEB3GentradeSubAccountForMock.class, ((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals("2",l_paramsValue1.getFirstCalled()[1]);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    
    }
    
    //先物OP限月追加対応
    public void testCreateProductSelectScreen_0004()
    {

        final String STR_METHOD_NAME = "testCreateProductSelectScreen_0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3OptionsProductSelectRequestForMock l_request=
            new WEB3OptionsProductSelectRequestForMock();

        WEB3OptionClientRequestServiceForMock l_impl =
            new WEB3OptionClientRequestServiceForMock();

        try
        {
            //BranchIndexDealtCondRow
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setMarketCode("0");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams1.setTargetProductCode("0006");
            l_branchIndexDealtCondParams1.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);          
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setMarketCode("0");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams2.setTargetProductCode("0007");
            l_branchIndexDealtCondParams2.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams3 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams3.setInstitutionCode("0D");
            l_branchIndexDealtCondParams3.setBranchCode("381");
            l_branchIndexDealtCondParams3.setMarketCode("0");
            l_branchIndexDealtCondParams3.setFutureOptionDiv("2");
            l_branchIndexDealtCondParams3.setTargetProductCode("0008");
            l_branchIndexDealtCondParams3.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams3);
          
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngOption(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //IfoDeliveryMonthMasterRow
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);
            
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams1 =
                new IfoDeliveryMonthMasterParams();
            l_ifoDeliveryMonthMasterParams1.setIndexId(123L);
            l_ifoDeliveryMonthMasterParams1.setUnderlyingProductCode("0006");
            l_ifoDeliveryMonthMasterParams1.setFutureOptionDiv("2");
            l_ifoDeliveryMonthMasterParams1.setDeliveryMonth("200710");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams1);
            l_ifoDeliveryMonthMasterParams1.setDeliveryMonth("200711");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams1);
            l_ifoDeliveryMonthMasterParams1.setDeliveryMonth("200712");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams1);
          
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams2 =
                new IfoDeliveryMonthMasterParams();
            l_ifoDeliveryMonthMasterParams2.setIndexId(124L);
            l_ifoDeliveryMonthMasterParams2.setUnderlyingProductCode("0007");
            l_ifoDeliveryMonthMasterParams2.setFutureOptionDiv("2");
            l_ifoDeliveryMonthMasterParams2.setDeliveryMonth("200708");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams2);
            l_ifoDeliveryMonthMasterParams2.setDeliveryMonth("200709");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams2);
            l_ifoDeliveryMonthMasterParams2.setDeliveryMonth("200710");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams2);
          
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams3 =
                new IfoDeliveryMonthMasterParams();
            l_ifoDeliveryMonthMasterParams3.setIndexId(125L);
            l_ifoDeliveryMonthMasterParams3.setUnderlyingProductCode("0008");
            l_ifoDeliveryMonthMasterParams3.setFutureOptionDiv("2");
            l_ifoDeliveryMonthMasterParams3.setDeliveryMonth("200709");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams3);
            l_ifoDeliveryMonthMasterParams3.setDeliveryMonth("200710");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams3);
            l_ifoDeliveryMonthMasterParams3.setDeliveryMonth("200711");
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams3);

            
            
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);

            WEB3OptionsProductSelectResponse l_marginSelectResponse =
                l_impl.createProductSelectScreen(l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class});
            assertEquals(WEB3GentradeSubAccountForMock.class, ((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals("2",l_paramsValue1.getFirstCalled()[1]);
            
            assertEquals(5, l_marginSelectResponse.delivaryMonthList.length);
            assertEquals("200710", l_marginSelectResponse.delivaryMonthList[0]);
            assertEquals("200711", l_marginSelectResponse.delivaryMonthList[1]);
            assertEquals("200712", l_marginSelectResponse.delivaryMonthList[2]);
            assertEquals("200708", l_marginSelectResponse.delivaryMonthList[3]);
            assertEquals("200709", l_marginSelectResponse.delivaryMonthList[4]);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    
    }
    
    public class WEB3OptionsProductSelectRequestForMock extends WEB3OptionsProductSelectRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3OptionsOpenMarginInputRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                  
            log.exiting(STR_METHOD_NAME);
        }
    }

}
@
