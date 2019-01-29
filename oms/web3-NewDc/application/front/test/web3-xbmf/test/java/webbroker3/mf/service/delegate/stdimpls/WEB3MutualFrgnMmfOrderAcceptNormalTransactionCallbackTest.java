head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptParams;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallbackTest extends TestBaseForMock
{

    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallbackTest.class);
    WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback l_callBack = null;
    public WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallbackTest(String arg0)
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

    public void testProcess_T01()
    {
        final String STR_METHOD_NAME = "testProcess_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            // MutualFundOrderUnit get
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_mutualFundOrderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            OrderUnit[] l_orderUnits =
                l_mutualFundOrderManager.getOrderUnits(1001);
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_orderUnits[0];
                        
            //Interceptor get
            WEB3MutualFundAcceptConfirmInterceptor l_interceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();
            
            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("1");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptComplete",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                null);
            
            l_callBack = new WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
                l_mutualFundOrderUnit,
                l_interceptor,
                l_orderAcceptParams);
            l_callBack.process();
            
            String l_strUpdateWhere =
                " institution_code = ? " +
                " and branch_code = ? " +
                " and order_request_number = ? ";
            String[] l_strUpdateParams =
                {"60","624","1"};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List list = l_queryProcessor.doFindAllQuery(
                HostFrgnMmfOrderAcceptRow.TYPE,
                l_strUpdateWhere,
                l_strUpdateParams);
            assertEquals(1, list.size());
            HostFrgnMmfOrderAcceptRow l_ros =
                (HostFrgnMmfOrderAcceptRow)list.get(0);
            assertEquals("1", l_ros.getStatus());
            log.info(STR_METHOD_NAME + "-------------->ok");            
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcess_T02()
    {
        final String STR_METHOD_NAME = "testProcess_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            // MutualFundOrderUnit get
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_mutualFundOrderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            OrderUnit[] l_orderUnits =
                l_mutualFundOrderManager.getOrderUnits(1001);
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_orderUnits[0];
                        
            //Interceptor get
            WEB3MutualFundAcceptConfirmInterceptor l_interceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();
            
            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("2");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                null);
            
            l_callBack = new WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
                l_mutualFundOrderUnit,
                l_interceptor,
                l_orderAcceptParams);
            l_callBack.process();
            
            String l_strUpdateWhere =
                " institution_code = ? " +
                " and branch_code = ? " +
                " and order_request_number = ? ";
            String[] l_strUpdateParams =
                {"60","624","1"};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List list = l_queryProcessor.doFindAllQuery(
                HostFrgnMmfOrderAcceptRow.TYPE,
                l_strUpdateWhere,
                l_strUpdateParams);
            assertEquals(1, list.size());
            HostFrgnMmfOrderAcceptRow l_ros =
                (HostFrgnMmfOrderAcceptRow)list.get(0);
            assertEquals("1", l_ros.getStatus());
            log.info(STR_METHOD_NAME + "-------------->ok");            
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcess_T03()
    {
        final String STR_METHOD_NAME = "testProcess_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            // MutualFundOrderUnit get
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_mutualFundOrderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            OrderUnit[] l_orderUnits =
                l_mutualFundOrderManager.getOrderUnits(1001);
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_orderUnits[0];
                        
            //Interceptor get
            WEB3MutualFundAcceptConfirmInterceptor l_interceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();
            
            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("3");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptComplete",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                null);
            
            l_callBack = new WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
                l_mutualFundOrderUnit,
                l_interceptor,
                l_orderAcceptParams);
            l_callBack.process();
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testProcess_T04()
    {
        final String STR_METHOD_NAME = "testProcess_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            // MutualFundOrderUnit get
            MutualFundOrderUnitParams l_mutualFundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_mutualFundOrderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            OrderUnit[] l_orderUnits =
                l_mutualFundOrderManager.getOrderUnits(1001);
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_orderUnits[0];
                        
            //Interceptor get
            WEB3MutualFundAcceptConfirmInterceptor l_interceptor =
                new WEB3MutualFundAcceptConfirmInterceptor();
            
            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("2");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00003, "test error"));
            
            l_callBack = new WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallback(
                l_mutualFundOrderUnit,
                l_interceptor,
                l_orderAcceptParams);
            l_callBack.process();
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "-------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public MutualFundOrderUnitParams getMutualFundOrderUnitParams()
    {
        MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
        //      注文単位ＩＤ   order_unit_id      NUMBER  18  NotNull 
        l_params.setOrderUnitId(1001);
        //      口座ＩＤ   account_id       NUMBER  18  NotNull 
        l_params.setAccountId(101001010010L);
        //      補助口座ＩＤ   sub_account_id     NUMBER  18  NotNull 
        l_params.setSubAccountId(10100101001007L);
        //      部店ＩＤ   branch_id        NUMBER  18  NotNull 
        l_params.setBranchId(33381);
        //      取引者ＩＤ   trader_id       NUMBER  18  NULL    
        l_params.setTraderId(null);
        //      注文ＩＤ   order_id     NUMBER  18  NotNull 
        l_params.setOrderId(1001);
        //      注文種別   order_type       NUMBER  6   NotNull 
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      注文カテゴリ   order_categ        NUMBER  6   NotNull 
        l_params.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
        //      注文履歴最終通番   last_order_action_serial_no      NUMBER  8   NotNull 
        l_params.setLastOrderActionSerialNo(1);
        //      銘柄タイプ   product_type        NUMBER  6   NotNull 
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      市場ＩＤ   market_id        NUMBER  18  NULL   
        //      注文数量   quantity     DECIMAL  18 12  6   NotNull 
        l_params.setQuantity(123);
        //      受渡日   delivery_date     DATE    NotNull 
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        //      注文失効日付   expiration_date        DATE    NULL    
        //      市場から確認済みの数量   confirmed_quantity        DECIMAL  18 12  6   NULL    
        //      約定数量   executed_quantity        DECIMAL  18 12  6   NULL    
        //      合計約定金額   executed_amount        DECIMAL  18 12  6   NULL    
        //      注文状態   order_status     NUMBER  6   NotNull 
        l_params.setOrderStatus(OrderStatusEnum.CANCELLED);
        //      注文有効状態   order_open_status      NUMBER  6   NotNull 
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      失効区分   expiration_status        NUMBER  6   NotNull
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      税区分   tax_type      NUMBER  6   NotNull 
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      発注日   biz_date      VARCHAR2  8     NotNull 
        l_params.setBizDate("20040101");
        //      銘柄ＩＤ   product_id       NUMBER  18  NotNull 
        l_params.setProductId(1006169090018L);
        //      注文数量タイプ   quantity_type     NUMBER  6   NotNull
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);        
        //      初回注文の注文チャネル   order_chanel      VARCHAR2  1     NULL    
        //      受注日時   received_date_time       DATE    NULL    
        //      扱者コード（SONAR）   sonar_trader_code        VARCHAR2  5     NULL    
        //      識別コード   order_request_number        VARCHAR2  9     NULL    
        //      計算基準価額   calc_constant_value        DECIMAL  18 12  6   NULL    
        //      計算基準価額（乗換先）   swt_calc_constant_value       DECIMAL  18 12  6   NULL    
        //      基準価額適用日   constant_value_app_date       DATE    NULL    
        //      概算受渡代金   estimated_price        DECIMAL  18 12  6   NULL    
        //      概算売買口数   estimate_dealing_qty       DECIMAL  18 12  6   NULL    
        //      概算買付口数（乗換先）   swt_estimate_dealing_qty      DECIMAL  18 12  6   NULL    
        //      税区分（乗換先）   swt_tax_type     NUMBER  6   NULL    
        //      銘柄コード（乗換先）   swt_product_code       VARCHAR2  10    NULL    
        //      受渡方法@   payment_method       VARCHAR2  1     NULL    
        //      投信タイプ   fund_type       NUMBER  6   NULL    
        //      投信解約区分   fund_sell_div      VARCHAR2  1     NULL    
        //      約定日   exec_date     DATE    NULL    
        //      約定状態   exec_status      VARCHAR2  1     NULL    
        //      決済区分   settlement_div       VARCHAR2  1     NULL    
        //      無手数料区分   no_contract_commission_div     VARCHAR2  1     NULL    
        //      請求区分   request_div      VARCHAR2  1     NULL    
        //      注文経路区分   order_root_div     VARCHAR2  1     NULL    
        //      注文エラー理由コード   error_reason_code      VARCHAR2  4     NULL    
        //      作成日付   created_timestamp        DATE    NotNull
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        //      更新日付   last_updated_timestamp       DATE    NotNull 
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        //      入金日   payment_date      DATE    NULL    
        //      源泉徴収拘束金   withholding_tax_restriction       DECIMAL  18 12  6   NULL    
        //      出金注文識別コード   payment_order_req_number        VARCHAR2  9     NULL    
        //      CPU No.   cpu_no        VARCHAR2  5     NULL    
        //      乗換元約定日   swt_exec_date      DATE    NULL    
        return l_params;
    }

    public HostFrgnMmfOrderAcceptParams getHostFrgnMmfOrderAcceptParams()
    {
        HostFrgnMmfOrderAcceptParams l_params = new HostFrgnMmfOrderAcceptParams();
        //      データコード      request_code    VARCHAR2  5     NotNull 
        l_params.setRequestCode("1234");
        //      証券会社コード     institution_code    VARCHAR2  3     NotNull 
        l_params.setInstitutionCode("60");
        //      部店コード   branch_code     VARCHAR2  3     NotNull 
        l_params.setBranchCode("624");
        //      顧客コード   account_code    VARCHAR2  7     NotNull 
        l_params.setAccountCode("123");
        //      扱者コード   trader_code     VARCHAR2  5     NULL    
        //      識別コード   order_request_number    VARCHAR2  9     NotNull 
        l_params.setOrderRequestNumber("1");
        //      注文受付結果      accept_status   VARCHAR2  1     NULL    
        //      エラーメッセージ    error_message   VARCHAR2  4     NULL    
        //      処理区分    status      VARCHAR2  1     NULL    
        return l_params;
    }

}
@
