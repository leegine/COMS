head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.30.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3RuitoTradeOrderNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.xbruito.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoTradedOrderNotifyDecisionInterceptor;
import webbroker3.xbruito.data.HostRuitoOrderNotifyParams;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoProductParams;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyServiceImpl.WEB3RuitoTradeOrderNotifyTransactionCallback;

public class WEB3RuitoTradeOrderNotifyUnitServiceImplTest extends
        TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoTradeOrderNotifyUnitServiceImplTest.class);

    
    public WEB3RuitoTradeOrderNotifyUnitServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    WEB3RuitoTradeOrderNotifyUnitServiceImpl l_impl = 
        new WEB3RuitoTradeOrderNotifyUnitServiceImpl();
    
//    
//    /*
//     * Test method for 'webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyUnitServiceImpl.notifyTradeOrderNotify(HostRuitoOrderNotifyParams, WEB3RuitoTradedOrderNotifyDecisionInterceptor)'
//     */
//    public void testNotifyTradeOrderNotify_T001() {
//        final String STR_METHOD_NAME = "testNotifyTradeOrderNotify_T001";
//        log.entering(STR_METHOD_NAME);
//        
//        HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams = 
//            new HostRuitoOrderNotifyParams();
//
//        
//        WEB3RuitoTradedOrderNotifyDecisionInterceptor l_ruitoTradedOrderNotifyDecisionInterceptor = 
//            new WEB3RuitoTradedOrderNotifyDecisionInterceptor();
//       
//      
//        
//        l_hostRuitoOrderNotifyParams.setAccountCode("234");
//        l_hostRuitoOrderNotifyParams.setBranchCode("381");
//        l_hostRuitoOrderNotifyParams.setInstitutionCode("0D");
//        
//        
//        try
//        {
//           
// 
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
// 
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchCode("381");
//            l_branchParams.setBranchId(1001L);
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setBranchCode("381");
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setAccountCode("123");
//            l_mainAccountParams.setInstitutionId(33L);
//            l_mainAccountParams.setBranchId(1001L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            
//            l_impl.notifyTradeOrderNotify(l_hostRuitoOrderNotifyParams,
//                    l_ruitoTradedOrderNotifyDecisionInterceptor);
//
//        }
//
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(l_ex.getMessage(),l_ex);
//            
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//        
//        
//    }
//    
    /*
     * Test method for 'webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyUnitServiceImpl.notifyTradeOrderNotify(HostRuitoOrderNotifyParams, WEB3RuitoTradedOrderNotifyDecisionInterceptor)'
     */
    public void testNotifyTradeOrderNotify_case0001() {
        final String STR_METHOD_NAME = "testNotifyTradeOrderNotify_case0001";
        log.entering(STR_METHOD_NAME);
        
        HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams = 
            new HostRuitoOrderNotifyParams();

        
        WEB3RuitoTradedOrderNotifyDecisionInterceptor l_ruitoTradedOrderNotifyDecisionInterceptor = 
            new WEB3RuitoTradedOrderNotifyDecisionInterceptor();
       

        l_hostRuitoOrderNotifyParams.setAccountCode("123");
        l_hostRuitoOrderNotifyParams.setBranchCode("381");
        l_hostRuitoOrderNotifyParams.setInstitutionCode("0D");
        l_hostRuitoOrderNotifyParams.setCourse("1");
        l_hostRuitoOrderNotifyParams.setPlan("2");
        l_hostRuitoOrderNotifyParams.setOrderDiv("3");
        l_hostRuitoOrderNotifyParams.setAmount(123L);
        
        
        try
        {

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_branchParams);

            
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            l_MarketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_MarketParams);
            
            
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
 
//            
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);

            
///////////////////////////////////////////////////////////////////////////////////////////           
            
//            RuitoProductParams l_ruitoProductParams = TestDBUtility.getRuitoProductRow();
//            TestDBUtility.deleteAll(l_ruitoProductParams.getRowType());
//            l_ruitoProductParams.setCourse("1");
//            l_ruitoProductParams.setInstitutionCode("0D");
//            l_ruitoProductParams.setPlan("2");
//            TestDBUtility.insertWithDel(l_ruitoProductParams);
//            
//
//            
// 
//            TradedProductParams  l_TradeProductParams  = TestDBUtility.getTradedProductRow();
//            TestDBUtility.deleteAll(l_TradeProductParams.getRowType());
//            TestDBUtility.insertWithDel(l_TradeProductParams);
//            
//
//            
//            RuitoOrderUnitParams l_ruitoOrderUnitParmas = TestDBUtility.getRuitoOrderUnitRow();
//            TestDBUtility.deleteAll(l_ruitoOrderUnitParmas.getRowType());
//            TestDBUtility.insertWithDel(l_ruitoOrderUnitParmas);
//            
//            
//            RuitoTradedProductParams l_ruitoTradedProductParams = new RuitoTradedProductParams();
//            TestDBUtility.deleteAll(l_ruitoTradedProductParams.getRowType());
//            l_ruitoTradedProductParams.setMarketId(3303L);
//            l_ruitoTradedProductParams.setLastUpdater("AD");
//            l_ruitoTradedProductParams.setInstitutionCode("0D");
//            l_ruitoTradedProductParams.setProductId(3304148080000L);
//            l_ruitoTradedProductParams.setTradedProductId(l_TradeProductParams.getTradedProductId());
//       
//            l_ruitoTradedProductParams.setOnlineUpdatedTimestamp(new Date(20071224));
//            l_ruitoTradedProductParams.setCreatedTimestamp(new Date(20071224));
//            l_ruitoTradedProductParams.setLastUpdatedTimestamp(new Date(20071224));
//            TestDBUtility.insertWithDel(l_ruitoTradedProductParams);
//            
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setMarketCode("N1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
            
            WEB3RuitoTradeOrderNotifyUnitServiceImplForTest l_fortest =
                new WEB3RuitoTradeOrderNotifyUnitServiceImplForTest();
            
            
            l_fortest.notifyTradeOrderNotify(l_hostRuitoOrderNotifyParams,
                    l_ruitoTradedOrderNotifyDecisionInterceptor);
           

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00250,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(),l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
        
    }
    
    public void testProcessCase0003()
    {
        final String STR_METHOD_NAME = "testProcessCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3RuitoTradeOrderNotifyServiceImpl l_impl = new WEB3RuitoTradeOrderNotifyServiceImpl();
            WEB3RuitoTradeOrderNotifyTransactionCallback l_callback =
                l_impl.new WEB3RuitoTradeOrderNotifyTransactionCallback();
            WEB3RuitoDealingOrderNotifyRequest l_request = new WEB3RuitoDealingOrderNotifyRequest();

            //HostRuitoOrderNotifyParams
            TestDBUtility.deleteAll(HostRuitoOrderNotifyParams.TYPE);
            HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams =
                this.insertHostRuitoOrderNotifyParams();
            l_hostRuitoOrderNotifyParams.setInstitutionCode("12");
            TestDBUtility.insertWithDel(l_hostRuitoOrderNotifyParams);

            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            l_callback.process();
            
            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_listResult = l_qp.doFindAllQuery(
                HostRuitoOrderNotifyParams.TYPE,
                "status=?",
                new Object[]{"9"});
            assertEquals(1, l_listResult.size());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }   
    
    public void testProcessCase0004()
    {
        final String STR_METHOD_NAME = "testProcessCase0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3RuitoTradeOrderNotifyServiceImpl l_impl = new WEB3RuitoTradeOrderNotifyServiceImpl();
            WEB3RuitoTradeOrderNotifyTransactionCallback l_callback =
                l_impl.new WEB3RuitoTradeOrderNotifyTransactionCallback();
            WEB3RuitoDealingOrderNotifyRequest l_request = new WEB3RuitoDealingOrderNotifyRequest();

            //HostRuitoOrderNotifyParams
            TestDBUtility.deleteAll(HostRuitoOrderNotifyParams.TYPE);
            HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams =
                this.insertHostRuitoOrderNotifyParams();
            l_hostRuitoOrderNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostRuitoOrderNotifyParams);

            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode(l_hostRuitoOrderNotifyParams.getInstitutionCode());
            l_mainAccountParams.setBranchCode(l_hostRuitoOrderNotifyParams.getBranchCode());
            l_mainAccountParams.setAccountCode(l_hostRuitoOrderNotifyParams.getAccountCode());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(RuitoProductParams.TYPE);
            
            l_callback.process();

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_listResult = l_qp.doFindAllQuery(
                HostRuitoOrderNotifyParams.TYPE,
                "status=?",
                new Object[]{"1"});
            assertEquals(1, l_listResult.size());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }   
    
    public void testProcessCase0005()
    {
        final String STR_METHOD_NAME = "testProcessCase0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3RuitoTradeOrderNotifyServiceImpl l_impl = new WEB3RuitoTradeOrderNotifyServiceImpl();
            WEB3RuitoTradeOrderNotifyTransactionCallback l_callback =
                l_impl.new WEB3RuitoTradeOrderNotifyTransactionCallback();
            WEB3RuitoDealingOrderNotifyRequest l_request = new WEB3RuitoDealingOrderNotifyRequest();

            //HostRuitoOrderNotifyParams
            TestDBUtility.deleteAll(HostRuitoOrderNotifyParams.TYPE);
            HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams =
                this.insertHostRuitoOrderNotifyParams();
            l_hostRuitoOrderNotifyParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_hostRuitoOrderNotifyParams);

            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode(l_hostRuitoOrderNotifyParams.getInstitutionCode());
            l_mainAccountParams.setBranchCode(l_hostRuitoOrderNotifyParams.getBranchCode());
            l_mainAccountParams.setAccountCode(l_hostRuitoOrderNotifyParams.getAccountCode());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //RuitoProductParams
            RuitoProductParams l_ruitoProductParams = getRuitoProductParams();
            l_ruitoProductParams.setCourse("1");
            l_ruitoProductParams.setPlan("2");
            TestDBUtility.insertWithDel(l_ruitoProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);

            l_callback.process();

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            List l_listResult = l_qp.doFindAllQuery(
                HostRuitoOrderNotifyParams.TYPE,
                "status=?",
                new Object[]{"9"});
            assertEquals(1, l_listResult.size());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    } 

    class WEB3RuitoTradeOrderNotifyUnitServiceImplForTest extends WEB3RuitoTradeOrderNotifyUnitServiceImpl
    {
        
        public RuitoTypeEnum getRuitoType(HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams) throws WEB3BaseException
        {        
            String STR_METHOD_NAME = 
                "getRuitoSellDivision(HostRuitoOrderNotifyParams)";

            RuitoTypeEnum l_ruitoTypeEnum = null;

            l_ruitoTypeEnum = RuitoTypeEnum.MMF;//MMFのセット
          
            log.exiting(STR_METHOD_NAME); 
            return l_ruitoTypeEnum;
        }
        
        
        public String getRuitoSellDivision(HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams) throws WEB3BaseException
        {        
           
            String STR_METHOD_NAME = 
                "getRuitoSellDivision(HostRuitoOrderNotifyParams)";

            log.entering(STR_METHOD_NAME);

            String l_sellDiv = null;
   
            l_sellDiv = WEB3SellDivDef.ALL_DESIGNATE;

          
            log.exiting(STR_METHOD_NAME); 
            return l_sellDiv;        
        }
    }
    
    public HostRuitoOrderNotifyParams insertHostRuitoOrderNotifyParams()
    {

        HostRuitoOrderNotifyParams l_hostRuitoOrderNotifyParams = 
            new HostRuitoOrderNotifyParams();
        l_hostRuitoOrderNotifyParams.setAccountCode("123");
        l_hostRuitoOrderNotifyParams.setBranchCode("381");
        l_hostRuitoOrderNotifyParams.setInstitutionCode("0D");
        l_hostRuitoOrderNotifyParams.setCourse("1");
        l_hostRuitoOrderNotifyParams.setPlan("2");
        l_hostRuitoOrderNotifyParams.setOrderDiv("3");
        l_hostRuitoOrderNotifyParams.setAmount(123L);
        l_hostRuitoOrderNotifyParams.setStatus("0");
        l_hostRuitoOrderNotifyParams.setRequestCode("123");
        l_hostRuitoOrderNotifyParams.setBizdateDiv("1");
        l_hostRuitoOrderNotifyParams.setOrderRequestNumber("2");
        return l_hostRuitoOrderNotifyParams;
    }

    public RuitoProductParams getRuitoProductParams()
    {
        RuitoProductParams l_ruitoProductParams = new RuitoProductParams();
        //銘柄ＩＤ      product_id  NUMBER    18    NotNull
        l_ruitoProductParams.setProductId(123456789l);
        //証券会社コード   institution_code  VARCHAR2    3     NotNull
        l_ruitoProductParams.setInstitutionCode("0D");
        //銘柄コード     product_code  VARCHAR2    10    NotNull
        l_ruitoProductParams.setProductCode("ruit");
        //回号コード     product_issue_code  VARCHAR2    10      NotNull
        l_ruitoProductParams.setProductIssueCode("12");
        //累投タイプ     ruito_type  NUMBER    6     NotNull
        l_ruitoProductParams.setRuitoType(RuitoTypeEnum.MRF);
        //初期買付最小注文数量    init_purchase_min_qty  NUMBER    10     NotNull
        l_ruitoProductParams.setInitPurchaseMinQty(123);
        //追加買付最小注文数量    addtl_purchase_min_qty  NUMBER    10    NotNull
        l_ruitoProductParams.setAddtlPurchaseMinQty(456l);
        //銘柄名   standard_name  VARCHAR2    64   NULL
        //コース   course  VARCHAR2    1   NULL
        //プラン   plan  VARCHAR2    1     NULL
        //ＭＲＦコード    mrf_fund_code  VARCHAR2    1    NULL
        //買付上限金額    buy_max_price  NUMBER    11     NULL
        //買付下限金額    buy_min_price  NUMBER    11     NULL
        //解約上限金額    sell_max_price  NUMBER    11    NULL
        //解約下限金額    sell_min_price  NUMBER    11    NULL
        //指定方法@（買付）      buy_designate_method  VARCHAR2    1     NULL
        //指定方法@（解約）      sell_designate_method  VARCHAR2    1    NULL
        //受渡方法@      payment_method  VARCHAR2    1   NULL
        //買付開始日     buy_start_date  DATENULL
        //買付終了日     buy_end_date  DATENULL
        //解約開始日     sell_start_date  DATENULL
        //解約終了日     sell_end_date  DATENULL
        //最終更新者コード      last_updater  VARCHAR2    20    NotNull
        l_ruitoProductParams.setLastUpdater("789");
        //更新日付（オンライン）   online_updated_timestamp   DATENotNull
        l_ruitoProductParams.setOnlineUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        //作成日付      created_timestamp  DATENotNull
        l_ruitoProductParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付      last_updated_timestamp  DATENotNull
        l_ruitoProductParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_ruitoProductParams;
    }

    public void testGetOrderQuantityType_C0001()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantityType_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3RuitoTradeOrderNotifyUnitServiceImpl l_impl =
                new WEB3RuitoTradeOrderNotifyUnitServiceImpl();

            HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams =
                new HostRuitoOrderNotifyParams();
            l_ruitoOrderNotifyCueParams.setOrderDiv("1");
            l_ruitoOrderNotifyCueParams.setSpecifyDiv("1");

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3RuitoProduct l_ruitoProduct = new WEB3RuitoProduct(TestDBUtility.getRuitoProductRow());

            QuantityTypeEnum l_quantityTypeEnum =
                l_impl.getOrderQuantityType(l_ruitoOrderNotifyCueParams, l_ruitoProduct);

            assertEquals(QuantityTypeEnum.QUANTITY, l_quantityTypeEnum);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testGetOrderQuantityType_C0002()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantityType_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3RuitoTradeOrderNotifyUnitServiceImpl l_impl =
                new WEB3RuitoTradeOrderNotifyUnitServiceImpl();

            HostRuitoOrderNotifyParams l_ruitoOrderNotifyCueParams =
                new HostRuitoOrderNotifyParams();
            l_ruitoOrderNotifyCueParams.setOrderDiv("1");
            l_ruitoOrderNotifyCueParams.setSpecifyDiv("2");

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3RuitoProduct l_ruitoProduct = new WEB3RuitoProduct(TestDBUtility.getRuitoProductRow());

            QuantityTypeEnum l_quantityTypeEnum =
                l_impl.getOrderQuantityType(l_ruitoOrderNotifyCueParams, l_ruitoProduct);

            assertEquals(QuantityTypeEnum.AMOUNT, l_quantityTypeEnum);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage());
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
