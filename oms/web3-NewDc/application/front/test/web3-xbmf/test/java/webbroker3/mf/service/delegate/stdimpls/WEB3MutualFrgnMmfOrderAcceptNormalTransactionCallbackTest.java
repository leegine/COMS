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
        //      ¶PÊhc   order_unit_id      NUMBER  18  NotNull 
        l_params.setOrderUnitId(1001);
        //      ûÀhc   account_id       NUMBER  18  NotNull 
        l_params.setAccountId(101001010010L);
        //      âûÀhc   sub_account_id     NUMBER  18  NotNull 
        l_params.setSubAccountId(10100101001007L);
        //      Xhc   branch_id        NUMBER  18  NotNull 
        l_params.setBranchId(33381);
        //      æøÒhc   trader_id       NUMBER  18  NULL    
        l_params.setTraderId(null);
        //      ¶hc   order_id     NUMBER  18  NotNull 
        l_params.setOrderId(1001);
        //      ¶íÊ   order_type       NUMBER  6   NotNull 
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      ¶JeS   order_categ        NUMBER  6   NotNull 
        l_params.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
        //      ¶ðÅIÊÔ   last_order_action_serial_no      NUMBER  8   NotNull 
        l_params.setLastOrderActionSerialNo(1);
        //      Á¿^Cv   product_type        NUMBER  6   NotNull 
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      sêhc   market_id        NUMBER  18  NULL   
        //      ¶Ê   quantity     DECIMAL  18 12  6   NotNull 
        l_params.setQuantity(123);
        //      ónú   delivery_date     DATE    NotNull 
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        //      ¶¸øút   expiration_date        DATE    NULL    
        //      sê©çmFÏÝÌÊ   confirmed_quantity        DECIMAL  18 12  6   NULL    
        //      ñèÊ   executed_quantity        DECIMAL  18 12  6   NULL    
        //      vñèàz   executed_amount        DECIMAL  18 12  6   NULL    
        //      ¶óÔ   order_status     NUMBER  6   NotNull 
        l_params.setOrderStatus(OrderStatusEnum.CANCELLED);
        //      ¶LøóÔ   order_open_status      NUMBER  6   NotNull 
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      ¸øæª   expiration_status        NUMBER  6   NotNull
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      Åæª   tax_type      NUMBER  6   NotNull 
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      ­ú   biz_date      VARCHAR2  8     NotNull 
        l_params.setBizDate("20040101");
        //      Á¿hc   product_id       NUMBER  18  NotNull 
        l_params.setProductId(1006169090018L);
        //      ¶Ê^Cv   quantity_type     NUMBER  6   NotNull
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);        
        //      ñ¶Ì¶`l   order_chanel      VARCHAR2  1     NULL    
        //      óú   received_date_time       DATE    NULL    
        //      µÒR[hiSONARj   sonar_trader_code        VARCHAR2  5     NULL    
        //      ¯ÊR[h   order_request_number        VARCHAR2  9     NULL    
        //      vZî¿z   calc_constant_value        DECIMAL  18 12  6   NULL    
        //      vZî¿ziæ·æj   swt_calc_constant_value       DECIMAL  18 12  6   NULL    
        //      î¿zKpú   constant_value_app_date       DATE    NULL    
        //      TZónãà   estimated_price        DECIMAL  18 12  6   NULL    
        //      TZû   estimate_dealing_qty       DECIMAL  18 12  6   NULL    
        //      TZtûiæ·æj   swt_estimate_dealing_qty      DECIMAL  18 12  6   NULL    
        //      Åæªiæ·æj   swt_tax_type     NUMBER  6   NULL    
        //      Á¿R[hiæ·æj   swt_product_code       VARCHAR2  10    NULL    
        //      ónû@@   payment_method       VARCHAR2  1     NULL    
        //      M^Cv   fund_type       NUMBER  6   NULL    
        //      Mðñæª   fund_sell_div      VARCHAR2  1     NULL    
        //      ñèú   exec_date     DATE    NULL    
        //      ñèóÔ   exec_status      VARCHAR2  1     NULL    
        //      Ïæª   settlement_div       VARCHAR2  1     NULL    
        //      ³è¿æª   no_contract_commission_div     VARCHAR2  1     NULL    
        //      ¿æª   request_div      VARCHAR2  1     NULL    
        //      ¶oHæª   order_root_div     VARCHAR2  1     NULL    
        //      ¶G[RR[h   error_reason_code      VARCHAR2  4     NULL    
        //      ì¬út   created_timestamp        DATE    NotNull
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        //      XVút   last_updated_timestamp       DATE    NotNull 
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        //      üàú   payment_date      DATE    NULL    
        //      ¹ò¥ûS©à   withholding_tax_restriction       DECIMAL  18 12  6   NULL    
        //      oà¶¯ÊR[h   payment_order_req_number        VARCHAR2  9     NULL    
        //      CPU No.   cpu_no        VARCHAR2  5     NULL    
        //      æ·³ñèú   swt_exec_date      DATE    NULL    
        return l_params;
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

}
@
