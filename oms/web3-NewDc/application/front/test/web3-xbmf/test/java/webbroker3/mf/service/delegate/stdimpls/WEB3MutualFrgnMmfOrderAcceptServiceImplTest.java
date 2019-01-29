head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFrgnMmfOrderAcceptServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.mf.WEB3MutualFundAcceptConfirmInterceptor;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptDao;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptParams;
import webbroker3.mf.data.HostFrgnMmfOrderAcceptRow;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptRequest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFrgnMmfOrderAcceptServiceImpl.WB3MutualFrgnMmfOrderAcceptTransactionCallback;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFrgnMmfOrderAcceptServiceImplTest extends TestBaseForMock
{

    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptServiceImplTest.class);
    WEB3MutualFrgnMmfOrderAcceptServiceImpl l_imple = new WEB3MutualFrgnMmfOrderAcceptServiceImpl();
    WB3MutualFrgnMmfOrderAcceptTransactionCallback l_transactionCallback =
        l_imple.new WB3MutualFrgnMmfOrderAcceptTransactionCallback();
    public WEB3MutualFrgnMmfOrderAcceptServiceImplTest(String arg0)
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

    public void testExecute_T01()
    {
        final String STR_METHOD_NAME = "testExecute_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3BackRequest l_request = null;
            l_imple.execute(l_request);
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
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

    public void testExecute_T02()
    {
        final String STR_METHOD_NAME = "testExecute_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualFrgnMmfOrderAcceptRequest l_request =
                new WEB3MutualFrgnMmfOrderAcceptRequest();
            TestDBUtility.deleteAllAndCommit(HostFrgnMmfOrderAcceptRow.TYPE);

            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("2");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_orderAcceptParams);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDelAndCommit(l_fundOrderUnitParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            MutualFundOrderUnit l_fundOrderUnit =
                (MutualFundOrderUnit)l_orderManager.getOrderUnit(
                    l_fundOrderUnitParams.getOrderUnitId());

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class},
                l_fundOrderUnit);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                null);

            l_imple.execute(l_request);

            HostFrgnMmfOrderAcceptRow l_orderAcceptRow =
                HostFrgnMmfOrderAcceptDao.findRowByPk(
                    l_orderAcceptParams.getInstitutionCode(),
                    l_orderAcceptParams.getBranchCode(),
                    l_orderAcceptParams.getOrderRequestNumber());
            assertEquals("1", l_orderAcceptRow.getStatus());
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

    public void testExecute_T03()
    {
        final String STR_METHOD_NAME = "testExecute_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualFrgnMmfOrderAcceptRequest l_request =
                new WEB3MutualFrgnMmfOrderAcceptRequest();
            TestDBUtility.deleteAllAndCommit(HostFrgnMmfOrderAcceptRow.TYPE);

            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("3");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_orderAcceptParams);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDelAndCommit(l_fundOrderUnitParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            MutualFundOrderUnit l_fundOrderUnit =
                (MutualFundOrderUnit)l_orderManager.getOrderUnit(
                    l_fundOrderUnitParams.getOrderUnitId());

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class},
                l_fundOrderUnit);

            l_imple.execute(l_request);

            HostFrgnMmfOrderAcceptRow l_orderAcceptRow =
                HostFrgnMmfOrderAcceptDao.findRowByPk(
                    l_orderAcceptParams.getInstitutionCode(),
                    l_orderAcceptParams.getBranchCode(),
                    l_orderAcceptParams.getOrderRequestNumber());
            assertEquals("9", l_orderAcceptRow.getStatus());
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
    
    public void testProcess_T01()
    {
        final String STR_METHOD_NAME = "testProcess_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFrgnMmfOrderAcceptRow.TYPE);
            Object l_object = l_transactionCallback.process();
            assertNull(l_object);
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
            TestDBUtility.deleteAllAndCommit(HostFrgnMmfOrderAcceptRow.TYPE);

            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams.setAcceptStatus("2");
            l_orderAcceptParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_orderAcceptParams);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDelAndCommit(l_fundOrderUnitParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            MutualFundOrderUnit l_fundOrderUnit =
                (MutualFundOrderUnit)l_orderManager.getOrderUnit(
                    l_fundOrderUnitParams.getOrderUnitId());

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class},
                l_fundOrderUnit);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                null);

            l_transactionCallback.process();

            HostFrgnMmfOrderAcceptRow l_orderAcceptRow =
                HostFrgnMmfOrderAcceptDao.findRowByPk(
                    l_orderAcceptParams.getInstitutionCode(),
                    l_orderAcceptParams.getBranchCode(),
                    l_orderAcceptParams.getOrderRequestNumber());
            assertEquals("1", l_orderAcceptRow.getStatus());
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
            TestDBUtility.deleteAllAndCommit(HostFrgnMmfOrderAcceptRow.TYPE);

            //HostFrgnMmfOrderAcceptParams
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams1 =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams1.setAcceptStatus("2");
            l_orderAcceptParams1.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_orderAcceptParams1);

            HostFrgnMmfOrderAcceptParams l_orderAcceptParams2 =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams2.setAcceptStatus("3");
            l_orderAcceptParams2.setStatus("0");
            l_orderAcceptParams2.setInstitutionCode("61");
            l_orderAcceptParams2.setBranchCode("623");
            l_orderAcceptParams2.setOrderRequestNumber("2");
            TestDBUtility.insertWithDelAndCommit(l_orderAcceptParams2);
            
            HostFrgnMmfOrderAcceptParams l_orderAcceptParams3 =
                this.getHostFrgnMmfOrderAcceptParams();
            l_orderAcceptParams3.setAcceptStatus("2");
            l_orderAcceptParams3.setStatus("0");
            l_orderAcceptParams3.setInstitutionCode("62");
            l_orderAcceptParams3.setBranchCode("622");
            l_orderAcceptParams2.setOrderRequestNumber("3");
            TestDBUtility.insertWithDelAndCommit(l_orderAcceptParams3);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            TestDBUtility.insertWithDelAndCommit(l_fundOrderUnitParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundOrderManager l_orderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            MutualFundOrderUnit l_fundOrderUnit =
                (MutualFundOrderUnit)l_orderManager.getOrderUnit(
                    l_fundOrderUnitParams.getOrderUnitId());

            //zao jia
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManager",
                "getOrderUnit",
                new Class[] {String.class, String.class, String.class},
                l_fundOrderUnit);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl",
                "notifyOrderAcceptFail",
                new Class[] {MutualFundOrderUnit.class,
                    WEB3MutualFundAcceptConfirmInterceptor.class},
                null);

            l_transactionCallback.process();

            String l_strWhere = " status = ? ";
            Object[] l_objWhereValues = {WEB3StatusDef.DEALT};
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            /*検索*/
            List l_lisMFOrderAccepResult =
                l_queryProcessor.doFindAllQuery(
                    HostFrgnMmfOrderAcceptRow.TYPE,
                    l_strWhere,
                    l_objWhereValues);
            assertEquals(2, l_lisMFOrderAccepResult.size());
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
    
    public MutualFundOrderUnitParams getMutualFundOrderUnitParams()
    {
        MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
        //      注文単位ＩＤ    order_unit_id     NUMBER  18 NotNull 
        l_params.setOrderUnitId(1001);
        //      口座ＩＤ    account_id     NUMBER  18 NotNull  
        l_params.setAccountId(101001010010L);
        //      補助口座ＩＤ    sub_account_id     NUMBER  18 NotNull  
        l_params.setSubAccountId(10100101001007L);
        //      部店ＩＤ    branch_id     NUMBER  18 NotNull  
        l_params.setBranchId(33381);
        //      取引者ＩＤ    trader_id     NUMBER  18 NULL  
        //      注文ＩＤ    order_id     NUMBER  18 NotNull  
        l_params.setOrderId(1001);
        //      注文種別    order_type     NUMBER  6 NotNull  
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      注文カテゴリ    order_categ     NUMBER  6 NotNull  
        l_params.setOrderCateg(OrderCategEnum.ASSET);
        //      注文履歴最終通番    last_order_action_serial_no     NUMBER  8 NotNull  
        l_params.setLastOrderActionSerialNo(1);
        //      銘柄タイプ    product_type     NUMBER  6 NotNull  
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      市場ＩＤ    market_id     NUMBER  18 NULL  
        //      注文数量    quantity     DECIMAL  18 12 6 NotNull  
        l_params.setQuantity(123d);
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        //      受渡日    delivery_date     DATE   NotNull  
        l_params.setDeliveryDate(l_timestamp);
        //      注文失効日付    expiration_date     DATE   NULL  
        //      市場から確認済みの数量    confirmed_quantity     DECIMAL  18 12 6 NULL  
        //      約定数量    executed_quantity     DECIMAL  18 12 6 NULL  
        //      合計約定金額    executed_amount     DECIMAL  18 12 6 NULL  
        //      注文状態    order_status     NUMBER  6 NotNull 
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //      注文有効状態    order_open_status     NUMBER  6 NotNull  
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      失効区分    expiration_status     NUMBER  6 NotNull  
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      税区分    tax_type     NUMBER  6 NotNull  
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      発注日    biz_date     VARCHAR2  8 NotNull  
        l_params.setBizDate("20010201");
        //      銘柄ＩＤ    product_id     NUMBER  18 NotNull  
        l_params.setProductId(1006169090018L);
        //      注文数量タイプ    quantity_type     NUMBER  6 NotNull  
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);
        //      初回注文の注文チャネル    order_chanel     VARCHAR2  1 NULL  
        //      受注日時    received_date_time     DATE   NULL  
        //      扱者コード（SONAR）    sonar_trader_code     VARCHAR2  5 NULL  
        //      識別コード    order_request_number     VARCHAR2  9 NULL  
        //      計算基準価額    calc_constant_value     DECIMAL  18 12 6 NULL  
        //      計算基準価額（乗換先）    swt_calc_constant_value     DECIMAL  18 12 6 NULL  
        //      基準価額適用日    constant_value_app_date     DATE   NULL  
        //      概算受渡代金    estimated_price     DECIMAL  18 12 6 NULL  
        //      概算売買口数    estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      概算買付口数（乗換先）    swt_estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      税区分（乗換先）    swt_tax_type     NUMBER  6 NULL  
        //      銘柄コード（乗換先）    swt_product_code     VARCHAR2  10 NULL  
        //      受渡方法@    payment_method     VARCHAR2  1 NULL  
        //      投信タイプ    fund_type     NUMBER  6 NULL  
        //      投信解約区分    fund_sell_div     VARCHAR2  1 NULL  
        //      約定日    exec_date     DATE   NULL  
        //      約定状態    exec_status     VARCHAR2  1 NULL  
        //      決済区分    settlement_div     VARCHAR2  1 NULL  
        //      無手数料区分    no_contract_commission_div     VARCHAR2  1 NULL  
        //      請求区分    request_div     VARCHAR2  1 NULL  
        //      注文経路区分    order_root_div     VARCHAR2  1 NULL  
        //      注文エラー理由コード    error_reason_code     VARCHAR2  4 NULL  
        //      作成日付    created_timestamp     DATE   NotNull  
        l_params.setCreatedTimestamp(l_timestamp);
        //      更新日付    last_updated_timestamp     DATE   NotNull 
        l_params.setLastUpdatedTimestamp(l_timestamp);
        //      入金日    payment_date     DATE   NULL  
        //      源泉徴収拘束金    withholding_tax_restriction     DECIMAL  18 12 6 NULL  
        //      出金注文識別コード    payment_order_req_number     VARCHAR2  9 NULL  
        //      CPU No.    cpu_no     VARCHAR2  5 NULL  
        //      乗換元約定日    swt_exec_date     DATE   NULL  
        return l_params;
    }
}
@
