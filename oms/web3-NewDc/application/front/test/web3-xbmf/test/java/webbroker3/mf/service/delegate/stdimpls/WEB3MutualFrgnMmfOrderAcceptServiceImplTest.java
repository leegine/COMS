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

            /*õ*/
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
        //      f[^R[h      request_code    VARCHAR2  5     NotNull 
        l_params.setRequestCode("1234");
        //      ØïÐR[h     institution_code    VARCHAR2  3     NotNull 
        l_params.setInstitutionCode("60");
        //      XR[h   branch_code     VARCHAR2  3     NotNull 
        l_params.setBranchCode("624");
        //      ÚqR[h   account_code    VARCHAR2  7     NotNull 
        l_params.setAccountCode("123");
        //      µÒR[h   trader_code     VARCHAR2  5     NULL    
        //      ¯ÊR[h   order_request_number    VARCHAR2  9     NotNull 
        l_params.setOrderRequestNumber("1");
        //      ¶ótÊ      accept_status   VARCHAR2  1     NULL    
        //      G[bZ[W    error_message   VARCHAR2  4     NULL    
        //      æª    status      VARCHAR2  1     NULL    
        return l_params;
    }
    
    public MutualFundOrderUnitParams getMutualFundOrderUnitParams()
    {
        MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
        //      ¶PÊhc    order_unit_id     NUMBER  18 NotNull 
        l_params.setOrderUnitId(1001);
        //      ûÀhc    account_id     NUMBER  18 NotNull  
        l_params.setAccountId(101001010010L);
        //      âûÀhc    sub_account_id     NUMBER  18 NotNull  
        l_params.setSubAccountId(10100101001007L);
        //      Xhc    branch_id     NUMBER  18 NotNull  
        l_params.setBranchId(33381);
        //      æøÒhc    trader_id     NUMBER  18 NULL  
        //      ¶hc    order_id     NUMBER  18 NotNull  
        l_params.setOrderId(1001);
        //      ¶íÊ    order_type     NUMBER  6 NotNull  
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      ¶JeS    order_categ     NUMBER  6 NotNull  
        l_params.setOrderCateg(OrderCategEnum.ASSET);
        //      ¶ðÅIÊÔ    last_order_action_serial_no     NUMBER  8 NotNull  
        l_params.setLastOrderActionSerialNo(1);
        //      Á¿^Cv    product_type     NUMBER  6 NotNull  
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      sêhc    market_id     NUMBER  18 NULL  
        //      ¶Ê    quantity     DECIMAL  18 12 6 NotNull  
        l_params.setQuantity(123d);
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        //      ónú    delivery_date     DATE   NotNull  
        l_params.setDeliveryDate(l_timestamp);
        //      ¶¸øút    expiration_date     DATE   NULL  
        //      sê©çmFÏÝÌÊ    confirmed_quantity     DECIMAL  18 12 6 NULL  
        //      ñèÊ    executed_quantity     DECIMAL  18 12 6 NULL  
        //      vñèàz    executed_amount     DECIMAL  18 12 6 NULL  
        //      ¶óÔ    order_status     NUMBER  6 NotNull 
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //      ¶LøóÔ    order_open_status     NUMBER  6 NotNull  
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      ¸øæª    expiration_status     NUMBER  6 NotNull  
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      Åæª    tax_type     NUMBER  6 NotNull  
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      ­ú    biz_date     VARCHAR2  8 NotNull  
        l_params.setBizDate("20010201");
        //      Á¿hc    product_id     NUMBER  18 NotNull  
        l_params.setProductId(1006169090018L);
        //      ¶Ê^Cv    quantity_type     NUMBER  6 NotNull  
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);
        //      ñ¶Ì¶`l    order_chanel     VARCHAR2  1 NULL  
        //      óú    received_date_time     DATE   NULL  
        //      µÒR[hiSONARj    sonar_trader_code     VARCHAR2  5 NULL  
        //      ¯ÊR[h    order_request_number     VARCHAR2  9 NULL  
        //      vZî¿z    calc_constant_value     DECIMAL  18 12 6 NULL  
        //      vZî¿ziæ·æj    swt_calc_constant_value     DECIMAL  18 12 6 NULL  
        //      î¿zKpú    constant_value_app_date     DATE   NULL  
        //      TZónãà    estimated_price     DECIMAL  18 12 6 NULL  
        //      TZû    estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      TZtûiæ·æj    swt_estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      Åæªiæ·æj    swt_tax_type     NUMBER  6 NULL  
        //      Á¿R[hiæ·æj    swt_product_code     VARCHAR2  10 NULL  
        //      ónû@@    payment_method     VARCHAR2  1 NULL  
        //      M^Cv    fund_type     NUMBER  6 NULL  
        //      Mðñæª    fund_sell_div     VARCHAR2  1 NULL  
        //      ñèú    exec_date     DATE   NULL  
        //      ñèóÔ    exec_status     VARCHAR2  1 NULL  
        //      Ïæª    settlement_div     VARCHAR2  1 NULL  
        //      ³è¿æª    no_contract_commission_div     VARCHAR2  1 NULL  
        //      ¿æª    request_div     VARCHAR2  1 NULL  
        //      ¶oHæª    order_root_div     VARCHAR2  1 NULL  
        //      ¶G[RR[h    error_reason_code     VARCHAR2  4 NULL  
        //      ì¬út    created_timestamp     DATE   NotNull  
        l_params.setCreatedTimestamp(l_timestamp);
        //      XVút    last_updated_timestamp     DATE   NotNull 
        l_params.setLastUpdatedTimestamp(l_timestamp);
        //      üàú    payment_date     DATE   NULL  
        //      ¹ò¥ûS©à    withholding_tax_restriction     DECIMAL  18 12 6 NULL  
        //      oà¶¯ÊR[h    payment_order_req_number     VARCHAR2  9 NULL  
        //      CPU No.    cpu_no     VARCHAR2  5 NULL  
        //      æ·³ñèú    swt_exec_date     DATE   NULL  
        return l_params;
    }
}
@
