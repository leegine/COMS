head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqOrderManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitPK;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.EquityCommAccountCondMstParams;
import webbroker3.gentrade.data.EquityCommAccountCondMstRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.TaxRateTableParams;
import webbroker3.gentrade.data.TaxRateTableRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqOrderManagerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderManagerTest.class);

    WEB3FeqOrderManager l_manager = new WEB3FeqOrderManager();
    public WEB3FeqOrderManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        deleteDB();
        insertDB();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        deleteDB();
    }

    public void testUpdateEstimatedPrice_T01()
    {
        final String STR_METHOD_NAME = "testUpdateEstimatedPrice_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitRow3304148080000
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_unitParams = TestDBUtility.getFeqOrderUnitRow();
            l_unitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_unitParams.setProductId(3304148080000L);
            l_unitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_unitParams);
            
            WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_unitParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            //productParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            
            l_manager.updateEstimatedPrice(l_orderUnit,new Date(2007-1900, 10,9));
            fail();
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEstimatedPrice_T02()
    {
        final String STR_METHOD_NAME = "testUpdateEstimatedPrice_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_unitParams = TestDBUtility.getFeqOrderUnitRow();
            l_unitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_unitParams.setProductId(3304148080000L);
            l_unitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_unitParams);
            
            WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_unitParams);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            //productParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            //WEB3FeqBizLogicProviderForMock
            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    WEB3FeqProduct.class,
                    WEB3GentradeMarket.class,
                    Date.class,
                    Date.class,
                    double.class,
                    double.class,
                    boolean.class,
                    boolean.class,
                    boolean.class,
                    String.class},
                    l_result);

            l_manager.updateEstimatedPrice(l_orderUnit,new Date(2007-1900, 10,9));

            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[]{
                    WEB3GentradeSubAccount.class,
                    WEB3FeqProduct.class,
                    WEB3GentradeMarket.class,
                    Date.class,
                    Date.class,
                    double.class,
                    double.class,
                    boolean.class,
                    boolean.class,
                    boolean.class,
                    String.class});
            Object[] l_returnValue = l_value.getFirstCalled();
            assertEquals(101, ((Double)l_returnValue[6]).doubleValue(), 0);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEstimatedPrice_T03() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "testUpdateEstimatedPrice_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_unitParams = TestDBUtility.getFeqOrderUnitRow();
            l_unitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_unitParams.setProductId(3304148080000L);
            l_unitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_unitParams.setPrice(2);
            l_unitParams.setLimitPrice(1);
            l_unitParams.setWLimitPrice(0);
            l_unitParams.setConfirmedOrderPrice(5);
            l_unitParams.setConfirmedQuantity(10D);
            TestDBUtility.insertWithDel(l_unitParams);
            
            WEB3FeqOrderUnit l_orderUnit = new WEB3FeqOrderUnit(l_unitParams);
            Date l_date = WEB3DateUtility.getDate("20070228", "yyyyMMdd");
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProduct
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("01");
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //GenCurrencyRow
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(123456789L);
            l_feqFinTransactionParams.setOrderUnitId(1234L);
            l_feqFinTransactionParams.setFxRate(5);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //FeqTradedProductRow
            TestDBUtility.deleteAll(FeqTradedProductRow.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);

            //FeqOrderExecutionRow
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            //EquityCommAccountCondMstDao
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            EquityCommAccountCondMstParams l_ecacmParams = TestDBUtility.getEquityCommAccountCondMstRow();
            l_ecacmParams.setBranchId(33381);
            l_ecacmParams.setValidUntilBizDate("20070228");
            TestDBUtility.insertWithDel(l_ecacmParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcCommission",
                    new Class[]{
                            WEB3GentradeCommission.class,
                            SubAccount.class
                        },
                        null);
            
            //TaxRateTableRow
            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            
            l_manager.updateEstimatedPrice(l_orderUnit,l_date);

            FeqOrderUnitRow l_feqOrderUnitRow = FeqOrderUnitDao.findRowByPk(1234);
            assertEquals(1000,l_feqOrderUnitRow.getEstimatedPrice(), 0);
            assertEquals(200,l_feqOrderUnitRow.getFEstimatedPrice(),0);
            assertEquals(250,l_feqOrderUnitRow.getConfirmedEstimatedPrice(),0);
            assertEquals(50,l_feqOrderUnitRow.getConfirmedFEstimatedPrice(),0);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strWhere = " order_unit_id = ?  and order_action_serial_no = ? ";
            Object[] l_objVars = {
                new Long(l_unitParams.getOrderUnitId()),
                new Integer(l_unitParams.getLastOrderActionSerialNo())};
            List l_lisOrderActions = l_queryProcessor.doFindAllQuery(
                FeqOrderActionRow.TYPE,
                l_strWhere,
                l_objVars);
            
            FeqOrderActionParams l_feqOrderActionParams = (FeqOrderActionParams)l_lisOrderActions.get(0);
            assertEquals(1000,l_feqOrderActionParams.getEstimatedPrice(),0);
            assertEquals(200,l_feqOrderActionParams.getFEstimatedPrice(),0);
            assertEquals(250,l_feqOrderActionParams.getConfirmedEstimatedPrice(),0);
            assertEquals(50,l_feqOrderActionParams.getConfirmedFEstimatedPrice(),0);
            
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEstimatedPrice_T04()
    {
        final String STR_METHOD_NAME = "testUpdateEstimatedPrice_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnit
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_orderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(111);
            l_orderUnitParams.setOrderId(333);
            l_orderUnitParams.setLastOrderActionSerialNo(11111);
            l_orderUnitParams.setSubAccountId(33381251220301L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setProductId(3304148080000L);
            l_orderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_orderUnitParams.setConfirmedQuantity(6000.0);
            l_orderUnitParams.setConfirmedOrderPrice(1200.234);
            l_orderUnitParams.setQuantity(2000.0);
            l_orderUnitParams.setExecutedQuantity(2001.0);
            l_orderUnitParams.setConfirmedPrice(234.50);
            l_orderUnitParams.setLimitPrice(3333.3);
            l_orderUnitParams.setBizDate("20070216");
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            //FeqOrderExecution
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams = 
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(111);
            l_feqOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);
            
            //FeqOrderAction
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = 
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(111);
            l_feqOrderActionParams.setOrderActionSerialNo(11111);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccount
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //Branch
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
            l_branchtParams.setInstitutionCode("0D");
            l_branchtParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchtParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProduct
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("01");
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //GenCurrencyRow
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(333);
            l_feqFinTransactionParams.setOrderUnitId(111);
            l_feqFinTransactionParams.setFxRate(5);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //FeqTradedProductRow
            TestDBUtility.deleteAll(FeqTradedProductRow.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);           
            
            //EquityCommAccountCondMstDao
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            EquityCommAccountCondMstParams l_ecacmParams = TestDBUtility.getEquityCommAccountCondMstRow();
            l_ecacmParams.setBranchId(33381);
            l_ecacmParams.setValidUntilBizDate("20070216");
            TestDBUtility.insertWithDel(l_ecacmParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcCommission",
                    new Class[]{
                            WEB3GentradeCommission.class,
                            SubAccount.class
                        },
                        null);
            
            //TaxRateTableRow
            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("2007/02/02","yyyy/MM/dd"));
            l_taxRateTableParams.setTaxType("10");
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_orderUnitParams);
            Date l_ExecDate = WEB3DateUtility.getDate("2007/02/16","yyyy/MM/dd");
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            l_feqOrderManager.updateEstimatedPrice(l_feqOrderUnit,l_ExecDate);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEstimatedPrice_T05()
    {
        final String STR_METHOD_NAME = "testUpdateEstimatedPrice_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnit
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_orderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(111);
            l_orderUnitParams.setOrderId(333);
            l_orderUnitParams.setLastOrderActionSerialNo(11111);
            l_orderUnitParams.setSubAccountId(33381251220301L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setProductId(3304148080000L);
            l_orderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_orderUnitParams.setConfirmedQuantity(6000.0);
            l_orderUnitParams.setConfirmedOrderPrice(1200.234);
            l_orderUnitParams.setQuantity(2001.0);
            l_orderUnitParams.setExecutedQuantity(2000.0);
            l_orderUnitParams.setConfirmedPrice(234.50);
            l_orderUnitParams.setLimitPrice(3333.3);
            l_orderUnitParams.setBizDate("20070216");
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            //FeqOrderExecution
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams = 
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(111);
            l_feqOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);
            
            //FeqOrderAction
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = 
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(111);
            l_feqOrderActionParams.setOrderActionSerialNo(11111);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccount
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //Branch
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
            l_branchtParams.setInstitutionCode("0D");
            l_branchtParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchtParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProduct
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("01");
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //GenCurrencyRow
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(333);
            l_feqFinTransactionParams.setOrderUnitId(111);
            l_feqFinTransactionParams.setFxRate(5);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //FeqTradedProductRow
            TestDBUtility.deleteAll(FeqTradedProductRow.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);           
            
            //EquityCommAccountCondMstDao
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            EquityCommAccountCondMstParams l_ecacmParams = TestDBUtility.getEquityCommAccountCondMstRow();
            l_ecacmParams.setBranchId(33381);
            l_ecacmParams.setValidUntilBizDate("20070216");
            TestDBUtility.insertWithDel(l_ecacmParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcCommission",
                    new Class[]{
                            WEB3GentradeCommission.class,
                            SubAccount.class
                        },
                        null);
            
            //TaxRateTableRow
            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("2007/02/02","yyyy/MM/dd"));
            l_taxRateTableParams.setTaxType("10");
            TestDBUtility.insertWithDel(l_taxRateTableParams);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_orderUnitParams);
            Date l_ExecDate = WEB3DateUtility.getDate("2007/02/16","yyyy/MM/dd");
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            l_feqOrderManager.updateEstimatedPrice(l_feqOrderUnit,l_ExecDate);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateEstimatedPrice_T06()
    {
        final String STR_METHOD_NAME = "testUpdateEstimatedPrice_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnit
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_orderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_orderUnitParams.setOrderUnitId(111);
            l_orderUnitParams.setOrderId(333);
            l_orderUnitParams.setLastOrderActionSerialNo(11111);
            l_orderUnitParams.setSubAccountId(33381251220301L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setProductId(3304148080000L);
            l_orderUnitParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_orderUnitParams.setConfirmedQuantity(6000.0);
            l_orderUnitParams.setConfirmedOrderPrice(1200.234);
            l_orderUnitParams.setQuantity(2000.0);
            l_orderUnitParams.setExecutedQuantity(2000.0);
            l_orderUnitParams.setConfirmedPrice(234.50);
            l_orderUnitParams.setLimitPrice(3333.3);
            l_orderUnitParams.setBizDate("20070216");
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            //FeqOrderExecution
            TestDBUtility.deleteAll(FeqOrderExecutionRow.TYPE);
            FeqOrderExecutionParams l_feqOrderExecutionParams = 
                TestDBUtility.getFeqOrderExecutionParams();
            l_feqOrderExecutionParams.setOrderUnitId(111);
            l_feqOrderExecutionParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_feqOrderExecutionParams);
            
            //FeqOrderAction
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams = 
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(111);
            l_feqOrderActionParams.setOrderActionSerialNo(11111);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //SubAccount
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //MainAccount
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //Branch
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchtParams = TestDBUtility.getBranchRow();
            l_branchtParams.setInstitutionCode("0D");
            l_branchtParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchtParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProduct
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("01");
            l_feqProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_feqProductParams);

            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //GenCurrencyRow
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(333);
            l_feqFinTransactionParams.setOrderUnitId(111);
            l_feqFinTransactionParams.setFxRate(5);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //FeqTradedProductRow
            TestDBUtility.deleteAll(FeqTradedProductRow.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqTradedProductParams);           
            
            //EquityCommAccountCondMstDao
            TestDBUtility.deleteAll(EquityCommAccountCondMstRow.TYPE);
            EquityCommAccountCondMstParams l_ecacmParams = TestDBUtility.getEquityCommAccountCondMstRow();
            l_ecacmParams.setBranchId(33381);
            l_ecacmParams.setValidUntilBizDate("20070216");
            TestDBUtility.insertWithDel(l_ecacmParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.feq.WEB3FeqBizLogicProvider",
                    "calcCommission",
                    new Class[]{
                            WEB3GentradeCommission.class,
                            SubAccount.class
                        },
                        null);
            
            //TaxRateTableRow
            TestDBUtility.deleteAll(TaxRateTableRow.TYPE);
            TaxRateTableParams l_taxRateTableParams = this.getTaxRateTableRow();
            l_taxRateTableParams.setInstitutionCode("0D");
            l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("2007/02/02","yyyy/MM/dd"));
            l_taxRateTableParams.setTaxType("10");
            TestDBUtility.insertWithDel(l_taxRateTableParams);
            
            WEB3FeqAmountCalcResult l_result = new WEB3FeqAmountCalcResult();
            l_result.setNetAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqBizLogicProvider",
                "calcFeqAmount",
                new Class[] {
                        WEB3GentradeSubAccount.class,
                        WEB3FeqProduct.class,
                        WEB3GentradeMarket.class,
                        Date.class,
                        Date.class,
                        double.class,
                        double.class,
                        boolean.class,
                        boolean.class,
                        boolean.class,
                        String.class},
                        l_result);

            WEB3FeqOrderUnit l_feqOrderUnit = new WEB3FeqOrderUnit(l_orderUnitParams);
            Date l_ExecDate = WEB3DateUtility.getDate("2007/02/16","yyyy/MM/dd");
            WEB3FeqOrderManager l_feqOrderManager = new WEB3FeqOrderManager();
            l_feqOrderManager.updateEstimatedPrice(l_feqOrderUnit,l_ExecDate);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateExecutedAmountYen_Case001()
    {
        final String STR_METHOD_NAME = "testUpdateExecutedAmountYen_Case001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderUnitId(1234L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setPrice(2.523417);
            l_feqFinTransactionParams.setQuantity(100);
            l_feqFinTransactionParams.setFxRate(101);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductRow
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(400300090000000000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //GenCurrencyDao
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setScale(3);
            l_genCurrencyParams.setChangeJpyRoundDiv("1");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            l_manager.updateExecutedAmountYen(l_feqOrderUnitParams.getOrderUnitId());
            
            FeqOrderUnitRow l_orderUnitRow = FeqOrderUnitDao.findRowByPk(
                    l_feqOrderUnitParams.getOrderUnitId());
            
            FeqOrderUnitParams l_orderUnitParams = (FeqOrderUnitParams)l_orderUnitRow;
            assertEquals(25486,l_orderUnitParams.getExecutedAmountYen(),0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateExecutedAmountYen_Case002()
    {
        final String STR_METHOD_NAME = "testUpdateExecutedAmountYen_Case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderUnitId(1234L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            FeqFinTransactionParams l_feqFinTransactionParams = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams.setPrice(2.523417);
            l_feqFinTransactionParams.setQuantity(100);
            l_feqFinTransactionParams.setFxRate(101);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams);
            
            FeqFinTransactionParams l_feqFinTransactionParams2 = this.getFeqFinTransactionParams();
            l_feqFinTransactionParams2.setFinTransactionId(1234562);
            l_feqFinTransactionParams2.setOrderId(l_feqOrderUnitParams.getOrderId());
            l_feqFinTransactionParams2.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqFinTransactionParams2.setDeleteFlag(BooleanEnum.FALSE);
            l_feqFinTransactionParams2.setPrice(2.523412);
            l_feqFinTransactionParams2.setQuantity(100);
            l_feqFinTransactionParams2.setFxRate(110);
            TestDBUtility.insertWithDel(l_feqFinTransactionParams2);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductRow
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(400300090000000000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //GenCurrencyDao
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setScale(3);
            l_genCurrencyParams.setChangeJpyRoundDiv("1");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            l_manager.updateExecutedAmountYen(l_feqOrderUnitParams.getOrderUnitId());
            
            FeqOrderUnitRow l_orderUnitRow = FeqOrderUnitDao.findRowByPk(
                    l_feqOrderUnitParams.getOrderUnitId());
            
            FeqOrderUnitParams l_orderUnitParams = (FeqOrderUnitParams)l_orderUnitRow;
            assertEquals(53243,l_orderUnitParams.getExecutedAmountYen(),0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateExecutedAmountYen_Case003()
    {
        final String STR_METHOD_NAME = "testUpdateExecutedAmountYen_Case003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderUnitId(1234L);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            //FeqFinTransactionRow
            TestDBUtility.deleteAll(FeqFinTransactionRow.TYPE);
            
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductRow
            TestDBUtility.deleteAll(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(400300090000000000L);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //GenCurrencyDao
            TestDBUtility.deleteAll(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("001");
            l_genCurrencyParams.setScale(3);
            l_genCurrencyParams.setChangeJpyRoundDiv("1");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            l_manager.updateExecutedAmountYen(l_feqOrderUnitParams.getOrderUnitId());
            
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateExecutedAmountYen_Case004()
    {
        final String STR_METHOD_NAME = "testUpdateExecutedAmountYen_Case004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
                        
            l_manager.updateExecutedAmountYen(1234L);
            
            fail();
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 1)検索条件に口座ID == nullの場合
     * ※データを作る備考：証券会社コード != パラメータ.証券会社コード
     * 2)該当データなしの場合、nullを返却する
     */
    public void testGetNettingOrderUnit_case008()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");//
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams1.setExecutedQuantity(100d);
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams1);
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0E", l_tsBizDateTime);
            assertEquals(null, l_arrayWEB3FeqOrderUnit);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID == nullの場合
     * ※データを作る備考：銘柄タイプ != ProductTypeEnum.外国株式
     * 2)該当データなしの場合、nullを返却する
     */
    public void testGetNettingOrderUnit_case001()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.OTHER);// 銘柄タイプ != ProductTypeEnum.外国株式
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams1.setExecutedQuantity(100d);
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams1);
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0D", l_tsBizDateTime);
            assertEquals(null, l_arrayWEB3FeqOrderUnit);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID == null場合
     * ※データを作る備考：発注日 != パラメータ.発注日
     * 2)該当データなしの場合、nullを返却する
     */
    public void testGetNettingOrderUnit_case002()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            l_feqOrderUnitParams1.setBizDate("20070228");//
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams1.setExecutedQuantity(100d);
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams1);
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0D", l_tsBizDateTime);
            assertEquals(null, l_arrayWEB3FeqOrderUnit);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID == nullの場合
     * ※データを作る備考：注文有効状態 != "クローズ" 
     * 2)該当データなしの場合、nullを返却する
     */
    public void testGetNettingOrderUnit_case003()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);//
            l_feqOrderUnitParams1.setExecutedQuantity(100d);
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams1);
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0D", l_tsBizDateTime);
            assertEquals(null, l_arrayWEB3FeqOrderUnit);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID == nullの場合
     * ※データを作る備考：約定数量 == 0
     * 2)該当データなしの場合、nullを返却する
     */
    public void testGetNettingOrderUnit_case004()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams1.setExecutedQuantity(0d);//
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams1);
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0D", l_tsBizDateTime);
            assertEquals(null, l_arrayWEB3FeqOrderUnit);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID == nullの場合
     * ※データを作る備考：約定数量 is null
     * 2)該当データなしの場合、nullを返却する
     */
    public void testGetNettingOrderUnit_case005()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = new FeqOrderUnitParams();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            l_feqOrderUnitParams1.setSubAccountId(33381251220301L);
            l_feqOrderUnitParams1.setBranchId(40625L);
            l_feqOrderUnitParams1.setOrderId(123456789L);
            l_feqOrderUnitParams1.setOrderType(OrderTypeEnum.FEQ_BUY);
            l_feqOrderUnitParams1.setOrderCateg(OrderCategEnum.FEQ_TRANSFER);
            l_feqOrderUnitParams1.setLastOrderActionSerialNo(0);
            l_feqOrderUnitParams1.setLastExecutionSerialNo(0);
            l_feqOrderUnitParams1.setQuantity(100D);
            l_feqOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            l_feqOrderUnitParams1.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_feqOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_feqOrderUnitParams1.setTaxType(TaxTypeEnum.NORMAL);
            l_feqOrderUnitParams1.setProductId(400300090000000000L);
            l_feqOrderUnitParams1.setCurrencyCode("01");
            l_feqOrderUnitParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            l_feqOrderUnitParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_feqOrderUnitParams1);
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0D", l_tsBizDateTime);
            assertEquals(null, l_arrayWEB3FeqOrderUnit);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID == nullの場合
     * 2)検索結果を返却する
     */
    public void testGetNettingOrderUnit_case006()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            
            // right data
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderEmpCode("NWxxxxx");
            l_feqOrderUnitParams1.setOrderRequestNumber("1");
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams1.setExecutedQuantity(100d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams1);
            
            // wrong data
            FeqOrderUnitParams l_feqOrderUnitParams2 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams2.setOrderUnitId(1235L);
            l_feqOrderUnitParams2.setAccountId(333812512246L);
            l_feqOrderUnitParams2.setInstitutionCode("0E");
            l_feqOrderUnitParams2.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams2.setBizDate(l_strBizDate);
            l_feqOrderUnitParams2.setOrderEmpCode("NW*****");
            l_feqOrderUnitParams2.setOrderRequestNumber("2");
            l_feqOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams2.setExecutedQuantity(100d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams2);
            
            FeqOrderUnitParams l_feqOrderUnitParams3 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams3.setOrderUnitId(1236L);
            l_feqOrderUnitParams3.setAccountId(333812512247L);
            l_feqOrderUnitParams3.setInstitutionCode("0E");
            l_feqOrderUnitParams3.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams3.setBizDate(l_strBizDate);
            l_feqOrderUnitParams3.setOrderEmpCode("NW@@@@@@@@@@");
            l_feqOrderUnitParams3.setOrderRequestNumber("3");
            l_feqOrderUnitParams3.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams3.setExecutedQuantity(100d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams3);
            
            // right data
            FeqOrderUnitParams l_feqOrderUnitParams4 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams4.setOrderUnitId(1237L);
            l_feqOrderUnitParams4.setAccountId(333812512246L);
            l_feqOrderUnitParams4.setInstitutionCode("0D");
            l_feqOrderUnitParams4.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams4.setBizDate(l_strBizDate);
            l_feqOrderUnitParams4.setOrderEmpCode("NW$$$$$");
            l_feqOrderUnitParams4.setOrderRequestNumber("4");
            l_feqOrderUnitParams4.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams4.setExecutedQuantity(200d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams4);
            
            FeqOrderUnitParams l_feqOrderUnitParams5 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams5.setOrderUnitId(1238L);
            l_feqOrderUnitParams5.setAccountId(333812512248L);
            l_feqOrderUnitParams5.setInstitutionCode("0D");
            l_feqOrderUnitParams5.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams5.setBizDate(l_strBizDate);
            l_feqOrderUnitParams5.setOrderEmpCode("NW^^^^^");
            l_feqOrderUnitParams5.setOrderRequestNumber("5");
            l_feqOrderUnitParams5.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams5.setExecutedQuantity(300d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams5);
                        
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(null, "0D", l_tsBizDateTime);
            assertEquals(3, l_arrayWEB3FeqOrderUnit.length);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)検索条件に口座ID != nullの場合
     * 2)検索結果を返却する。
     */
    public void testGetNettingOrderUnit_case007()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testGetNettingOrderUnit_case007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            
            // right data
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setAccountId(333812512246L);
            l_feqOrderUnitParams1.setInstitutionCode("0D");
            l_feqOrderUnitParams1.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            Timestamp l_tsBizDateTime = GtlUtils.getSystemTimestamp();
            String l_strBizDate = WEB3DateUtility.formatDate(l_tsBizDateTime, "yyyyMMdd");
            l_feqOrderUnitParams1.setBizDate(l_strBizDate);
            l_feqOrderUnitParams1.setOrderEmpCode("NWxxxxx");
            l_feqOrderUnitParams1.setOrderRequestNumber("1");
            l_feqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams1.setExecutedQuantity(100d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams1);
            
            FeqOrderUnitParams l_feqOrderUnitParams2 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams2.setOrderUnitId(1235L);
            l_feqOrderUnitParams2.setAccountId(333812512246L);
            l_feqOrderUnitParams2.setInstitutionCode("0D");
            l_feqOrderUnitParams2.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams2.setBizDate(l_strBizDate);
            l_feqOrderUnitParams2.setOrderEmpCode("NW*****");
            l_feqOrderUnitParams2.setOrderRequestNumber("2");
            l_feqOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams2.setExecutedQuantity(100d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams2);
            
            // wrong data
            FeqOrderUnitParams l_feqOrderUnitParams3 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams3.setOrderUnitId(1236L);
            l_feqOrderUnitParams3.setAccountId(333812512247L);
            l_feqOrderUnitParams3.setInstitutionCode("0D");
            l_feqOrderUnitParams3.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams3.setBizDate(l_strBizDate);
            l_feqOrderUnitParams3.setOrderEmpCode("NW@@@@@@@@@@");
            l_feqOrderUnitParams3.setOrderRequestNumber("3");
            l_feqOrderUnitParams3.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams3.setExecutedQuantity(100d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams3);
            
            // right data
            FeqOrderUnitParams l_feqOrderUnitParams4 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams4.setOrderUnitId(1237L);
            l_feqOrderUnitParams4.setAccountId(333812512246L);
            l_feqOrderUnitParams4.setInstitutionCode("0D");
            l_feqOrderUnitParams4.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams4.setBizDate(l_strBizDate);
            l_feqOrderUnitParams4.setOrderEmpCode("NW$$$$$");
            l_feqOrderUnitParams4.setOrderRequestNumber("4");
            l_feqOrderUnitParams4.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams4.setExecutedQuantity(200d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams4);
            
            // wrong data
            FeqOrderUnitParams l_feqOrderUnitParams5 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams5.setOrderUnitId(1238L);
            l_feqOrderUnitParams5.setAccountId(333812512248L);
            l_feqOrderUnitParams5.setInstitutionCode("0D");
            l_feqOrderUnitParams5.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_feqOrderUnitParams5.setBizDate(l_strBizDate);
            l_feqOrderUnitParams5.setOrderEmpCode("NW^^^^^");
            l_feqOrderUnitParams5.setOrderRequestNumber("5");
            l_feqOrderUnitParams5.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams5.setExecutedQuantity(300d);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams5);
            
            
            WEB3FeqOrderUnit[] l_arrayWEB3FeqOrderUnit = l_manager.getNettingOrderUnit(new Long(333812512246L), "0D", l_tsBizDateTime);
            assertEquals(3, l_arrayWEB3FeqOrderUnit.length);
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.info("----------------------------------->test fail!");
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * getOrderUnit( )をコールし、注文単位を取得し、該当データなしの場合は、例外をスローする。
     */
    public void testUpdateNettingOrderLastUpdatedTimestamp_case001()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testUpdateNettingOrderLastUpdatedTimestamp_case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setLastOrderActionSerialNo(0);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams1);
            
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            FeqOrderActionParams l_feqOrderActionParams1 = TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams1.setOrderUnitId(1234L);
            l_feqOrderActionParams1.setOrderActionSerialNo(0);
            TestDBUtility.insertWithDel(l_feqOrderActionParams1);
            
            TestDBUtility.deleteAll(FeqOrderRow.TYPE);
            FeqOrderParams l_feqOrderParams1 = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams1.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_feqOrderParams1);
            
            l_manager.updateNettingOrderLastUpdatedTimestamp(new Long(1235L));
            fail();
            log.info("----------------------------------->test fail!");
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
            log.info("----------------------------------->test fail!");
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
                TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
                TestDBUtility.deleteAll(FeqOrderRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * 1)注文単位テーブル.注文単位ID = パラメータ.注文単位ID の条件に該当するネッティング注文の注文単位レコードをupdateする
     * 2)履歴テーブル.注文単位ID = パラメータ.注文単位ID　@かつ 履歴テーブル.注文履歴番号 = １）で取得した注文単位.注文履歴最終通番
     *   の条件に該当するネッティング注文の注文履歴の、履歴レコードの更新日付をupdateする
     * 3)注文（ヘッダ）テーブル.注文ID = １）で取得した注文単位.注文ID
     *   の条件に該当する、出来終了注文の注文（ヘッダ）の更新日時をupdateする。
     * 4)更新日付 = 現在日時
     * 
     * ※データを作る備考：
     * 1)注文単位テーブルに　@注文単位ID != パラメータ.注文単位ID のレコードの更新日付を更新しない
     * 2)履歴テーブルに　@注文単位ID != パラメータ.注文単位ID　@or 注文履歴番号 != 注文単位.注文履歴最終通番 のレコードの更新日付を更新しない
     * 3)注文（ヘッダ）テーブル.注文ID != 注文単位.注文ID のレコードの更新日付を更新しない
     */
    public void testUpdateNettingOrderLastUpdatedTimestamp_case002()
    {
        log.info(TEST_START);
        final String STR_METHOD_NAME = "testUpdateNettingOrderLastUpdatedTimestamp_case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Timestamp l_tsDateTime = new Timestamp(WEB3DateUtility.getDate("2007/10/1", "yyyy/MM/dd").getTime());
            
            //注文単位テーブル
            TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);     
            //right data
            FeqOrderUnitParams l_feqOrderUnitParams1 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams1.setOrderUnitId(1234L);
            l_feqOrderUnitParams1.setLastUpdatedTimestamp(l_tsDateTime);
            l_feqOrderUnitParams1.setLastOrderActionSerialNo(1);
            l_feqOrderUnitParams1.setOrderId(123456789L);
            l_feqOrderUnitParams1.setOrderEmpCode("NWxxxxx");
            l_feqOrderUnitParams1.setOrderRequestNumber("8");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams1);
            
            //wrong data
            FeqOrderUnitParams l_feqOrderUnitParams2 = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams2.setOrderUnitId(1235L);
            l_feqOrderUnitParams2.setLastUpdatedTimestamp(l_tsDateTime);
            l_feqOrderUnitParams2.setLastOrderActionSerialNo(1);
            l_feqOrderUnitParams2.setOrderId(123456789L);
            l_feqOrderUnitParams2.setOrderEmpCode("NW@@@@@@@@@@");
            l_feqOrderUnitParams2.setOrderRequestNumber("9");
            TestDBUtility.insertWithDel(l_feqOrderUnitParams2);
            
            //履歴テーブル
            TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
            //right data
            FeqOrderActionParams l_feqOrderActionParams1 = TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams1.setOrderActionId(111111111);
            l_feqOrderActionParams1.setOrderUnitId(1234L);
            l_feqOrderActionParams1.setOrderActionSerialNo(1);
            l_feqOrderActionParams1.setLastUpdatedTimestamp(l_tsDateTime);
            TestDBUtility.insertWithDel(l_feqOrderActionParams1);
            
            //wrong date1
            FeqOrderActionParams l_feqOrderActionParams2 = TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams2.setOrderActionId(222222222);
            l_feqOrderActionParams2.setOrderUnitId(1235L);//wrong注文単位ID
            l_feqOrderActionParams2.setOrderActionSerialNo(1);
            l_feqOrderActionParams2.setLastUpdatedTimestamp(l_tsDateTime);
            TestDBUtility.insertWithDel(l_feqOrderActionParams2);
            //wrong date2
            FeqOrderActionParams l_feqOrderActionParams3 = TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams3.setOrderActionId(333333333);
            l_feqOrderActionParams3.setOrderUnitId(1234L);
            l_feqOrderActionParams3.setOrderActionSerialNo(2);//wrong注文履歴番号
            l_feqOrderActionParams3.setLastUpdatedTimestamp(l_tsDateTime);
            TestDBUtility.insertWithDel(l_feqOrderActionParams3);
            
            //注文（ヘッダ）テーブル
            TestDBUtility.deleteAll(FeqOrderRow.TYPE);
            //right data
            FeqOrderParams l_feqOrderParams1 = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams1.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_feqOrderParams1);
            
            //wrong data
            FeqOrderParams l_feqOrderParams2 = TestDBUtility.getFeqOrderRow();
            l_feqOrderParams2.setOrderId(666666666L);//wrong注文ID
            TestDBUtility.insertWithDel(l_feqOrderParams2);
            
            l_manager.updateNettingOrderLastUpdatedTimestamp(new Long(1234L));
            
            //
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            PrimaryKey l_pkOfFeqOrderUnitTable = new FeqOrderUnitPK(1234L);
            FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pkOfFeqOrderUnitTable);
            
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss"),
                WEB3DateUtility.formatDate(l_feqOrderUnitRow.getLastUpdatedTimestamp(), "yyyy/MM/dd HH:mm:ss"));
            //
            List l_lisFeqOrderActionRow = l_queryProcessor.doFindAllQuery(
                FeqOrderActionRow.TYPE,
                "order_unit_id = ? and order_action_serial_no = ?",
                new Object[]{new Long(1234L), new Integer(1)});
            
            if (l_lisFeqOrderActionRow.size() != 1)
            {
                fail();
                log.info("----------------------------------->test fail!");
                try
                {
                    TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
                    TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
                    TestDBUtility.deleteAll(FeqOrderRow.TYPE);
                }
                catch (Exception l_ex)
                {
                    log.error(l_ex.getMessage(), l_ex);
                }
                finally
                {
                    log.info(TEST_END);
                    log.exiting(STR_METHOD_NAME);
                }
                return;
            }
            FeqOrderActionRow l_feqOrderActionRow = (FeqOrderActionRow)l_lisFeqOrderActionRow.get(0);
            
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss"),
                WEB3DateUtility.formatDate(l_feqOrderActionRow.getLastUpdatedTimestamp(), "yyyy/MM/dd HH:mm:ss"));
            assertEquals(
                111111111, l_feqOrderActionRow.getOrderActionId());
            
            //
            PrimaryKey l_pkOfFeqOrderTable = new FeqOrderPK(123456789L);
            FeqOrderRow l_feqOrderRow = (FeqOrderRow)l_queryProcessor.doFindByPrimaryKeyQuery(l_pkOfFeqOrderTable);
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss"),
                WEB3DateUtility.formatDate(l_feqOrderRow.getLastUpdatedTimestamp(), "yyyy/MM/dd HH:mm:ss"));
            
            log.info("----------------------------------->test pass!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
            log.info("----------------------------------->test fail!");
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(FeqOrderUnitRow.TYPE);
                TestDBUtility.deleteAll(FeqOrderActionRow.TYPE);
                TestDBUtility.deleteAll(FeqOrderRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
            }
            finally
            {
                log.info(TEST_END);
                log.exiting(STR_METHOD_NAME);
            }
        }
    }
    
    
    
    public void insertDB()
    {
        final String STR_METHOD_NAME = "insertDB()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccountParams
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(333812512246L);
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.OTHER);
            l_subAccountParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            l_subAccountParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_subAccountParams);

            //FeqProductParams
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_feqProductParams);

            //MarketParams
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N2");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //CurrencyParams
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            TestDBUtility.insertWithDel(l_currencyParams);

            //FeqFinTransactionParams
            FeqFinTransactionParams l_ransactionParams = this.getFeqFinTransactionParams();
            l_ransactionParams.setOrderId(123456789L);
            l_ransactionParams.setOrderUnitId(1234L);
            l_ransactionParams.setFxRate(101);
            TestDBUtility.insertWithDel(l_ransactionParams);
            
            //FeqTradedProductParams
            FeqTradedProductParams l_tradeProductParams = TestDBUtility.getFeqTradedProductRow();
            l_tradeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradeProductParams);

            //TradedProductParams
            TradedProductParams l_tradeParams = TestDBUtility.getTradedProductRow();
            l_tradeParams.setTradedProductId(1006160060005l);
            TestDBUtility.insertWithDel(l_tradeParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            //FeqOrderActionParams
            FeqOrderActionParams l_orderActionParams = getFeqOrderActionParams();
            TestDBUtility.insertWithDel(l_orderActionParams);
        }
        
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void deleteDB()
    {
        final String STR_METHOD_NAME = "deleteDB()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            //CurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            
            //FeqFinTransactionParams
            TestDBUtility.deleteAll(FeqFinTransactionParams.TYPE);
            
            //FeqTradedProductParams
            TestDBUtility.deleteAll(FeqTradedProductParams.TYPE);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public FeqFinTransactionParams getFeqFinTransactionParams()
    {
        FeqFinTransactionParams l_params = new FeqFinTransactionParams();
        l_params.setFinTransactionId(123456l);
        l_params.setAccountId(333812512246L);
        l_params.setSubAccountId(33381251220301L);
        l_params.setProductId(3304148080000L);
        l_params.setFinTransactionType(FinTransactionType.OTHER);
        l_params.setFinTransactionCateg(FinTransactionCateg.ASSET_IN_OUT);
        l_params.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20070102");
        l_params.setDeliveryDate(GtlUtils.getSystemTimestamp());
        l_params.setCurrencyCode("01");
        l_params.setNetAmount(100l);
        l_params.setNetAmountFc(120l);
        l_params.setFxRate(10l);
        l_params.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
        l_params.setQuantity(200l);
        l_params.setCommissionFee(100l);
        l_params.setCommissionFeeFc(100l);
        l_params.setBalanceAmount(100l);
        l_params.setBalanceAmountFc(100l);
        l_params.setForeignCommissionFee(100l);
        l_params.setForeignTax(100l);
        l_params.setForeignFeeExt1(100l);
        l_params.setForeignFeeExt2(100l);
        l_params.setCapitalGain(100l);
        l_params.setCapitalGainFc(100l);
        l_params.setCapitalGainTax(100l);
        l_params.setCapitalGainTaxFc(100l);
        l_params.setDeleteFlag(BooleanEnum.FALSE);
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }
    
    public FeqOrderActionParams getFeqOrderActionParams()
    {
        FeqOrderActionParams l_params = new FeqOrderActionParams();
        l_params.setOrderActionId(123456789l);
        l_params.setAccountId(333812512246L);
        l_params.setSubAccountId(33381251220301L);
        l_params.setOrderId(123456l);
        l_params.setOrderUnitId(1234L);
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        l_params.setOrderEventType(OrderEventTypeEnum.APPROVED);
        l_params.setQuantity(100l);
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setOrderActionSerialNo(0);
        l_params.setProductId(3304148080000L);
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }
    
    public class WEB3FeqOrderManagerForTest extends WEB3FeqOrderManager
    {
        public double getUnitPrice(
            WEB3FeqTradedProduct l_feqProduct,
            WEB3GentradeBranch l_branch,
            String l_strOrderPriceDiv,
            double l_dblPrice,
            double l_dblChangePrice,
            boolean l_blnIsBuy) throws WEB3BaseException
        {
            return 111l;
        }
        
    }
    
    public static TaxRateTableParams getTaxRateTableRow()
    {
        TaxRateTableParams l_taxRateTableParams = new TaxRateTableParams();
        l_taxRateTableParams.setInstitutionCode("0D");
        l_taxRateTableParams.setAppliStartDate(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
        l_taxRateTableParams.setAppliEndDate(WEB3DateUtility.getDate("20070228", "yyyyMMdd"));
        l_taxRateTableParams.setTaxRate(10);
        l_taxRateTableParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_taxRateTableParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_taxRateTableParams.setTaxType("10");
        return l_taxRateTableParams;
    }

}
@
