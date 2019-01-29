head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundOrderManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFrgnMmfOrderAcceptServiceImplTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFundOrderManagerTest extends TestBaseForMock
{

    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFrgnMmfOrderAcceptServiceImplTest.class);
    WEB3MutualFundOrderManager l_manager = new WEB3MutualFundOrderManager();
    public WEB3MutualFundOrderManagerTest(String arg0)
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

    public void testCalcFrgnMmfEstimateDeliveryAmount_T01()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimateDeliveryAmount_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setProductId(l_productParams.getProductId());
            
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            l_manager.calcFrgnMmfEstimateDeliveryAmount(
                null,
                l_fundTradedProduct,
                "123",
                123d,
                "123",
                "123");
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimateDeliveryAmount_T02()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimateDeliveryAmount_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            l_manager.calcFrgnMmfEstimateDeliveryAmount(
                l_subAccount,
                null,
                "123",
                123d,
                "123",
                "123");
            fail();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimateDeliveryAmount_T03()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimateDeliveryAmount_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            FrgnMmfExchangeRateParams l_rateParams =
                TestDBUtility.getFrgnMmfExchangeRateRow();
            l_rateParams.setInstitutionCode("0D");
            l_rateParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_rateParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            String l_strDesignateMethod = "4";   //4�F�����w��
            WEB3MutualFundEstimatedPrice l_price =
                l_manager.calcFrgnMmfEstimateDeliveryAmount(
                    l_subAccount,
                    l_fundTradedProduct,
                    "123",
                    123d,
                    l_strDesignateMethod,
                    "456");
            assertEquals(123d, l_price.getEstimatedQty(), 0);
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimateDeliveryAmount_T04()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimateDeliveryAmount_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            FrgnMmfExchangeRateParams l_rateParams =
                TestDBUtility.getFrgnMmfExchangeRateRow();
            l_rateParams.setInstitutionCode("0D");
            l_rateParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_rateParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            String l_strDesignateMethod = "3";   //3�F���z�w��
            WEB3MutualFundEstimatedPrice l_price =
                l_manager.calcFrgnMmfEstimateDeliveryAmount(
                    l_subAccount,
                    l_fundTradedProduct,
                    "123",
                    123d,
                    l_strDesignateMethod,
                    "456");
            assertEquals(123d, l_price.getEstimatedPrice(), 0);
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcFrgnMmfEstimateDeliveryAmount_T05()
    {
        final String STR_METHOD_NAME = "testCalcFrgnMmfEstimateDeliveryAmount_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            String l_strDesignateMethod = "2";
            WEB3MutualFundEstimatedPrice l_price =
                l_manager.calcFrgnMmfEstimateDeliveryAmount(
                    l_subAccount,
                    l_fundTradedProduct,
                    "123",
                    123d,
                    l_strDesignateMethod,
                    "456");

            assertEquals(0, l_price.getEstimatedPrice(),0);
            assertEquals(0, l_price.getEstimatedQty(),0);
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCalcEstimateDeliveryAmount()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount()";
        log.entering(STR_METHOD_NAME);
        try
        {

            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            FrgnMmfExchangeRateParams l_rateParams =
                TestDBUtility.getFrgnMmfExchangeRateRow();
            l_rateParams.setInstitutionCode("0D");
            l_rateParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_rateParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            String l_strDesignateMethod = "3";   //3�F���z�w��
            WEB3MutualFundEstimatedPrice l_price =
                l_manager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_fundTradedProduct,
                    l_fundTradedProduct,
                    l_strDesignateMethod,
                    123d,
                    l_strDesignateMethod,
                    "123",
                    "123",
                    "123",
                    "123",
                    new Date(2007-1900,02,11));
            assertEquals(123d, l_price.getEstimatedPrice(), 0);
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetOrderUnitList_T01()
    {
        final String STR_MEHTOD_NAME = "testGetOrderUnitList_T01()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();

            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strBizDate = l_format.format(l_dateBizDate);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_fundOrderUnitParams.setExecStatus(WEB3ExecStatusDef.EXECUTED_IN_PROCESS);
            l_fundOrderUnitParams.setBizDate(l_strBizDate);
            TestDBUtility.deleteAll( MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
           List l_list = l_manager.getOrderUnitList(l_subAccount, "3");
           assertEquals(1, l_list.size());
           log.info(STR_MEHTOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_MEHTOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }

    public void testGetOrderUnitList_T02()
    {
        final String STR_MEHTOD_NAME = "testGetOrderUnitList_T02()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();

            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strBizDate = l_format.format(l_dateBizDate);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_fundOrderUnitParams.setExecStatus(WEB3ExecStatusDef.EXECUTED_IN_PROCESS);
            l_fundOrderUnitParams.setBizDate(l_strBizDate);
            l_fundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
           List l_list = l_manager.getOrderUnitList(l_subAccount, "0");
           assertEquals(0, l_list.size());
           log.info(STR_MEHTOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_MEHTOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }

    public void testGetOrderUnitList_T03()
    {
        final String STR_MEHTOD_NAME = "testGetOrderUnitList_T03()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();

            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strBizDate = l_format.format(l_dateBizDate);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_fundOrderUnitParams.setExecStatus(WEB3ExecStatusDef.EXECUTED_IN_PROCESS);
            l_fundOrderUnitParams.setBizDate(l_strBizDate);
            l_fundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
           List l_list = l_manager.getOrderUnitList(l_subAccount, "0");
           assertEquals(1, l_list.size());
           log.info(STR_MEHTOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_MEHTOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }
    
    public void testGetOrderUnitList_T04()
    {
        final String STR_MEHTOD_NAME = "testGetOrderUnitList_T04()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();

            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strBizDate = l_format.format(l_dateBizDate);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_fundOrderUnitParams.setExecStatus(WEB3ExecStatusDef.EXECUTED_IN_PROCESS);
            l_fundOrderUnitParams.setBizDate(l_strBizDate);
            l_fundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
           List l_list = l_manager.getOrderUnitList(l_subAccount, "1");
           assertEquals(0, l_list.size());
           log.info(STR_MEHTOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_MEHTOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }
    
    public void testGetOrderUnitList_T05()
    {
        final String STR_MEHTOD_NAME = "testGetOrderUnitList_T05()";
        log.entering(STR_MEHTOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();

            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strBizDate = l_format.format(l_dateBizDate);
            
            MutualFundOrderUnitParams l_fundOrderUnitParams =
                this.getMutualFundOrderUnitParams();
            l_fundOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_fundOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_fundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_fundOrderUnitParams.setExecStatus(WEB3ExecStatusDef.EXECUTED_IN_PROCESS);
            l_fundOrderUnitParams.setBizDate(l_strBizDate);
            l_fundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_fundOrderUnitParams);
            
           List l_list = l_manager.getOrderUnitList(l_subAccount, "1");
           assertEquals(1, l_list.size());
           log.info(STR_MEHTOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_MEHTOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_MEHTOD_NAME);
    }
    
    
    public void testValidateSellNewOrder_T01()
    {
        final String STR_METHOD_NAME = "testValidateSellNewOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setSellSwtStartDate(l_dateBizDate);
            l_fundProductParams.setSellSwtEndDate(l_dateBizDate);
            l_fundProductParams.setSellMinQty(123L);
            l_fundProductParams.setSellUnitQty(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_manager.validateSellNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                123d,
                "0",
                "0",
                "4",
                TaxTypeEnum.NORMAL,
                "1");
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateSellNewOrder_T02()
    {
        final String STR_METHOD_NAME = "testValidateSellNewOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setSellSwtStartDate(l_dateBizDate);
            l_fundProductParams.setSellSwtEndDate(l_dateBizDate);
            l_fundProductParams.setSellMinQty(123L);
            l_fundProductParams.setSellUnitQty(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "test error 8005"));

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_manager.validateSellNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                123d,
                "0",
                "0",
                "4",
                TaxTypeEnum.NORMAL,
                "1");
            fail();
        }
        catch(WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBuyNewOrder_T01()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                123d,
                "0",
                "3",
                "1");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.info(STR_METHOD_NAME + "---------------------->ok");
        log.exiting(STR_METHOD_NAME);
    }  
    
    public void testValidateBuyNewOrder_T02()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, "test error 8005"));

            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                123d,
                "0",
                "3",
                "1");
            fail();
        }
        catch(WEB3BaseException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }  
    
    public void testValidateBuyNewOrder_T03()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrder_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                123d,
                "0",
                "3",
                "1");
            log.info(STR_METHOD_NAME + "---------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBuyNewOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strDesignateMethod = "3";
            String l_strSettleDiv = "1";
            
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strDesignateMethod,
                l_strSettleDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateBuyNewOrderCase0002()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            l_fundProductParams.setFrgnNewBuyMinAmt(123L);
            l_fundProductParams.setFrgnNewBuyUnitAmt(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strDesignateMethod = "3";
            String l_strSettleDiv = "2";
            
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strDesignateMethod,
                l_strSettleDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateBuyNewOrderCase0003()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrderCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            l_fundProductParams.setAddBuyMinAmt(123L);
            l_fundProductParams.setAddBuyUnitAmt(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setProductId(l_fundProductParams.getProductId());
            TestDBUtility.insertWithDel(l_assetParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strDesignateMethod = "3";
            String l_strSettleDiv = "1";
            
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strDesignateMethod,
                l_strSettleDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateBuyNewOrderCase0004()
    {
        final String STR_METHOD_NAME = "testValidateBuyNewOrderCase0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setBuyStartDate(l_dateBizDate);
            l_fundProductParams.setBuyEndDate(l_dateBizDate);
            l_fundProductParams.setNewBuyMinAmt(123L);
            l_fundProductParams.setNewBuyUnitAmt(123L);
            l_fundProductParams.setBuyLimitDiv("0");
            l_fundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_fundProductParams.setPlowbackProductDiv("0");
            l_fundProductParams.setAddBuyMinAmt(123L);
            l_fundProductParams.setAddBuyUnitAmt(123L);
            l_fundProductParams.setFrgnAddBuyMinAmt(123L);
            l_fundProductParams.setFrgnAddBuyUnitAmt(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateEmergencyStop",
                new Class[] {WEB3MutualFundProduct.class, String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                "validateFrgnMmfDoubleOrder",
                new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(l_subAccountParams.getAccountId());
            l_assetParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_assetParams.setProductId(l_fundProductParams.getProductId());
            TestDBUtility.insertWithDel(l_assetParams);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strDesignateMethod = "3";
            String l_strSettleDiv = "2";
            
            l_manager.validateBuyNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strDesignateMethod,
                l_strSettleDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateSellNewOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateSellNewOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            //mainAccount
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setBankAccountRegi("0");
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setSellSwtStartDate(l_dateBizDate);
            l_fundProductParams.setSellSwtEndDate(l_dateBizDate);
            l_fundProductParams.setSellMinAmt(123L);
            l_fundProductParams.setSellUnitAmt(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strPaymentMethod = "1";
            String l_strDesignateMethod = "3";
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            String l_strSettleDiv = "1";
            l_manager.validateSellNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strPaymentMethod,
                l_strDesignateMethod,
                l_taxType,
                l_strSettleDiv);
        }
        catch(WEB3BusinessLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02751, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateSellNewOrderCase0002()
    {
        final String STR_METHOD_NAME = "testValidateSellNewOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setSellSwtStartDate(l_dateBizDate);
            l_fundProductParams.setSellSwtEndDate(l_dateBizDate);
            l_fundProductParams.setSellMinAmt(123L);
            l_fundProductParams.setSellUnitAmt(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strPaymentMethod = "0";
            String l_strDesignateMethod = "3";
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            String l_strSettleDiv = "1";
            l_manager.validateSellNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strPaymentMethod,
                l_strDesignateMethod,
                l_taxType,
                l_strSettleDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateSellNewOrderCase0003()
    {
        final String STR_METHOD_NAME = "testValidateSellNewOrderCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccount
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            WEB3GentradeTradingTimeManagementForMock.getOrderBizDate();
            Date l_dateBizDate =
                GtlUtils.getTradingSystem().getBizDate();
            
            //WEB3MutualFundProduct
            ProductParams l_productParams = TestDBUtility.getProductRow();
            TestDBUtility.insertWithDel(l_productParams);
            MutualFundProductParams l_fundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_fundProductParams.setInstitutionCode("0D");
            l_fundProductParams.setProductId(l_productParams.getProductId());
            l_fundProductParams.setCurrencyCode("01");
            l_fundProductParams.setSystemHandlingDiv("1");
            l_fundProductParams.setSellSwtStartDate(l_dateBizDate);
            l_fundProductParams.setSellSwtEndDate(l_dateBizDate);
            l_fundProductParams.setFrgnSellMinAmt(123L);
            l_fundProductParams.setFrgnSellUnitAmt(123L);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_fundProductParams);
            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct =
                    new WEB3MutualFundProduct(l_fundProductParams);
            }
            catch(Exception l_exc)
            {
                log.error(STR_METHOD_NAME, l_exc);
            }

            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(l_dateBizDate);
            WEB3GentradeTradingTimeManagementForMock.validateOrderAccept();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateEmergencyStop",
                    new Class[] {WEB3MutualFundProduct.class, String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mf.WEB3MutualFundOrderManagerReusableValidationsCheck",
                    "validateFrgnMmfDoubleOrder",
                    new Class[] {SubAccount.class, WEB3MutualFundProduct.class, Date.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            double l_dblOrderQuantity = 123;
            String l_strProcessDiv = "0";
            String l_strPaymentMethod = "0";
            String l_strDesignateMethod = "3";
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            String l_strSettleDiv = "2";
            l_manager.validateSellNewOrder(
                l_subAccount,
                l_fundTradedProduct,
                l_dblOrderQuantity,
                l_strProcessDiv,
                l_strPaymentMethod,
                l_strDesignateMethod,
                l_taxType,
                l_strSettleDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public MutualFundOrderUnitParams getMutualFundOrderUnitParams()
    {
        MutualFundOrderUnitParams l_params = new MutualFundOrderUnitParams();
        //      �����P�ʂh�c    order_unit_id     NUMBER  18 NotNull 
        l_params.setOrderUnitId(1001);
        //      �����h�c    account_id     NUMBER  18 NotNull  
        l_params.setAccountId(101001010010L);
        //      �⏕�����h�c    sub_account_id     NUMBER  18 NotNull  
        l_params.setSubAccountId(10100101001007L);
        //      ���X�h�c    branch_id     NUMBER  18 NotNull  
        l_params.setBranchId(33381);
        //      ����҂h�c    trader_id     NUMBER  18 NULL  
        //      �����h�c    order_id     NUMBER  18 NotNull  
        l_params.setOrderId(1001);
        //      �������    order_type     NUMBER  6 NotNull  
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //      �����J�e�S��    order_categ     NUMBER  6 NotNull  
        l_params.setOrderCateg(OrderCategEnum.ASSET);
        //      ���������ŏI�ʔ�    last_order_action_serial_no     NUMBER  8 NotNull  
        l_params.setLastOrderActionSerialNo(1);
        //      �����^�C�v    product_type     NUMBER  6 NotNull  
        l_params.setProductType(ProductTypeEnum.MUTUAL_FUND);
        //      �s��h�c    market_id     NUMBER  18 NULL  
        //      ��������    quantity     DECIMAL  18 12 6 NotNull  
        l_params.setQuantity(123d);
        Timestamp l_timestamp = GtlUtils.getSystemTimestamp();
        //      ��n��    delivery_date     DATE   NotNull  
        l_params.setDeliveryDate(l_timestamp);
        //      �����������t    expiration_date     DATE   NULL  
        //      �s�ꂩ��m�F�ς݂̐���    confirmed_quantity     DECIMAL  18 12 6 NULL  
        //      ��萔��    executed_quantity     DECIMAL  18 12 6 NULL  
        //      ���v�����z    executed_amount     DECIMAL  18 12 6 NULL  
        //      �������    order_status     NUMBER  6 NotNull 
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        //      �����L�����    order_open_status     NUMBER  6 NotNull  
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        //      �����敪    expiration_status     NUMBER  6 NotNull  
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        //      �ŋ敪    tax_type     NUMBER  6 NotNull  
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        //      ������    biz_date     VARCHAR2  8 NotNull  
        l_params.setBizDate("20010201");
        //      �����h�c    product_id     NUMBER  18 NotNull  
        l_params.setProductId(1006169090018L);
        //      �������ʃ^�C�v    quantity_type     NUMBER  6 NotNull  
        l_params.setQuantityType(QuantityTypeEnum.AMOUNT);
        //      ���񒍕��̒����`���l��    order_chanel     VARCHAR2  1 NULL  
        //      �󒍓���    received_date_time     DATE   NULL  
        //      ���҃R�[�h�iSONAR�j    sonar_trader_code     VARCHAR2  5 NULL  
        //      ���ʃR�[�h    order_request_number     VARCHAR2  9 NULL  
        //      �v�Z����z    calc_constant_value     DECIMAL  18 12 6 NULL  
        //      �v�Z����z�i�抷��j    swt_calc_constant_value     DECIMAL  18 12 6 NULL  
        //      ����z�K�p��    constant_value_app_date     DATE   NULL  
        //      �T�Z��n���    estimated_price     DECIMAL  18 12 6 NULL  
        //      �T�Z��������    estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      �T�Z���t�����i�抷��j    swt_estimate_dealing_qty     DECIMAL  18 12 6 NULL  
        //      �ŋ敪�i�抷��j    swt_tax_type     NUMBER  6 NULL  
        //      �����R�[�h�i�抷��j    swt_product_code     VARCHAR2  10 NULL  
        //      ��n���@@    payment_method     VARCHAR2  1 NULL  
        //      ���M�^�C�v    fund_type     NUMBER  6 NULL  
        //      ���M���敪    fund_sell_div     VARCHAR2  1 NULL  
        //      ����    exec_date     DATE   NULL  
        //      �����    exec_status     VARCHAR2  1 NULL  
        //      ���ϋ敪    settlement_div     VARCHAR2  1 NULL  
        //      ���萔���敪    no_contract_commission_div     VARCHAR2  1 NULL  
        //      �����敪    request_div     VARCHAR2  1 NULL  
        //      �����o�H�敪    order_root_div     VARCHAR2  1 NULL  
        //      �����G���[���R�R�[�h    error_reason_code     VARCHAR2  4 NULL  
        //      �쐬���t    created_timestamp     DATE   NotNull  
        l_params.setCreatedTimestamp(l_timestamp);
        //      �X�V���t    last_updated_timestamp     DATE   NotNull 
        l_params.setLastUpdatedTimestamp(l_timestamp);
        //      ������    payment_date     DATE   NULL  
        //      ���򒥎��S����    withholding_tax_restriction     DECIMAL  18 12 6 NULL  
        //      �o���������ʃR�[�h    payment_order_req_number     VARCHAR2  9 NULL  
        //      CPU No.    cpu_no     VARCHAR2  5 NULL  
        //      �抷������    swt_exec_date     DATE   NULL  
        return l_params;
    }
    
    /**
     * �w����@@�� �����̏ꍇ�A�T�Z��n������Z�o����B
     * 
     * �w����@@ = WEB3SellDivDef.COUNT_DESIGNATE
     * 
     * ���팋��
     */
    public void testCalcEstimateDeliveryAmount_0001()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            this.initData();

            //MutualFundProductRow
            MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
            l_MutualFundProductParams.setProductId(3304148080000L);
            l_MutualFundProductParams.setConstantValueCalcUnit(1000);
            l_MutualFundProductParams.setInstitutionCode("0D");
            l_MutualFundProductParams.setCurrencyCode("01");
            l_MutualFundProductParams.setProductCode("0001000");
            l_MutualFundProductParams.setProductIssueCode("0");
            l_MutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_MutualFundProductParams.setInitPurchaseMinQty(0L);
            l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
            l_MutualFundProductParams.setLastUpdater("1001");
            l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_MutualFundProductParams);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = l_gentradeManager.getSubAccount(333812512203L, 33381251220301L);

            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct = new WEB3MutualFundProduct(l_MutualFundProductParams);
            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
                    
            String l_strDesignateMethod = WEB3SellDivDef.COUNT_DESIGNATE; 

            WEB3MutualFundEstimatedPrice l_price =
                l_manager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_fundTradedProduct,
                    "1",
                    123d,
                    l_strDesignateMethod,
                    "123");
            
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �w����@@�����z�w��̏ꍇ�A�T�Z�����������Z�o����B
     * 
     * �w����@@ = WEB3SellDivDef.MONEY_DESIGNATE
     * 
     * ���팋��
     */
    public void testCalcEstimateDeliveryAmount_0002()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            this.initData();

            //MutualFundProductRow
            MutualFundProductParams l_MutualFundProductParams = new MutualFundProductParams();
            l_MutualFundProductParams.setProductId(3304148080000L);
            l_MutualFundProductParams.setConstantValueCalcUnit(1000);
            l_MutualFundProductParams.setSellConstantValue(1000);
            l_MutualFundProductParams.setRecruitConstantValue(1000);
            l_MutualFundProductParams.setBuyConstantValue(1000);
            l_MutualFundProductParams.setInstitutionCode("0D");
            l_MutualFundProductParams.setCurrencyCode("01");
            l_MutualFundProductParams.setProductCode("0001000");
            l_MutualFundProductParams.setProductIssueCode("0");
            l_MutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_MutualFundProductParams.setInitPurchaseMinQty(0L);
            l_MutualFundProductParams.setAddtlPurchaseMinQty(0L);
            l_MutualFundProductParams.setLastUpdater("1001");
            l_MutualFundProductParams.setOnlineUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            l_MutualFundProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_MutualFundProductParams);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = l_gentradeManager.getSubAccount(333812512203L, 33381251220301L);

            WEB3MutualFundProduct l_fundTradedProduct = null;
            try
            {
                l_fundTradedProduct = new WEB3MutualFundProduct(l_MutualFundProductParams);
            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
                    
            String l_strDesignateMethod = WEB3SellDivDef.MONEY_DESIGNATE;

            WEB3MutualFundEstimatedPrice l_price =
                l_manager.calcEstimateDeliveryAmount(
                    l_subAccount,
                    l_fundTradedProduct,
                    "1",
                    123d,
                    l_strDesignateMethod,
                    "123");
            
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {   
            //SubAccountRow
            SubAccountParams l_subAccountParams = new SubAccountParams();
            //�����h�c]
            l_subAccountParams.setAccountId(333812512203L);
            //�⏕�����h�c
            l_subAccountParams.setSubAccountId(33381251220301L);
            //�⏕�����^�C�v
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            //�،���ЃR�[�h
            l_subAccountParams.setInstitutionCode("0D");
            //�،����ID
            l_subAccountParams.setInstitutionId(33);
            //���X�h�c
            l_subAccountParams.setBranchId(33381L);
            //�⏕�����X�e�[�^�X
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
            //�����o�^��
            l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //��������
            l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20081211","yyyyMMdd"));
            //�c��(�����j
            l_subAccountParams.setCashBalance(13456.0);
            //�쐬���t
            l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //�X�V���t
            l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //ProductRow
            ProductParams l_productParams = new ProductParams();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            l_productParams.setStandardName("�V���Z���e���X");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
  
            //FrgnMmfExchangeRateRow
            FrgnMmfExchangeRateParams l_FrgnMmfExchangeRateParams = new FrgnMmfExchangeRateParams();
            l_FrgnMmfExchangeRateParams.setInstitutionCode("0D");
            l_FrgnMmfExchangeRateParams.setCurrencyCode("01");
            l_FrgnMmfExchangeRateParams.setExecTimestamp(Calendar.getInstance().getTime()); 
            l_FrgnMmfExchangeRateParams.setTtSellingRate(1D);
            l_FrgnMmfExchangeRateParams.setTtBuyingRate(50D);
            l_FrgnMmfExchangeRateParams.setSubCurrencyRatio(50);
            l_FrgnMmfExchangeRateParams.setRestrictRate(10D);
            l_FrgnMmfExchangeRateParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_FrgnMmfExchangeRateParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
            TestDBUtility.insertWithDel(l_FrgnMmfExchangeRateParams);
            
            //InstitutionRow
            InstitutionParams l_institutionParams = new InstitutionParams();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstBranchProductRow
            InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
            l_instBranchProductParams.setBranchId(33381L);
            l_instBranchProductParams.setCommissionProductCode("20");
            TestDBUtility.deleteAll(InstBranchProductRow.TYPE);
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
