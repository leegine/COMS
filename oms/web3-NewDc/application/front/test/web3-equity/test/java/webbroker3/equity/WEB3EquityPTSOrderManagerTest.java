head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSOrderManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityPTSOrderManagerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/24 金傑（中訊）新規作成
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.data.EquityLimitPriceRangeMstParams;
import webbroker3.equity.data.EquityLimitPriceRangeMstRow;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSOrderManagerTest extends TestBaseForMock
{

    private WEB3EquityPTSOrderManager l_manager = null;
    
    private boolean l_blnIsPTSAccountOpen = true;
    
    private boolean l_blnIsSpecialAccountEstablished = true;
        
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSOrderManagerTest.class);

    public WEB3EquityPTSOrderManagerTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_manager = new WEB3EquityPTSOrderManager();
        this.getData();
        this.getMock();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_manager = null;
        super.tearDown();
    }
    
    /**
     * 「PTS発注審査個別チェック.validatePTS注文訂正可能状態()」抛出異常
     * 抛出異常信息：SYSTEM_ERROR_80017
     *
     */
    public void testValidatePTSOrderForChangeability_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeability_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            this.l_manager.validatePTSOrderForChangeability(null);
            fail(); 
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
                        
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「PTS発注審査個別チェック.validatePTS注文訂正可能状態()」正常結束
     *
     */
    public void testValidatePTSOrderForChangeability_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForChangeability_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSOrderForChangeability", 
                    new Class[]{ Order.class },
                    null);
            
            this.l_manager.validatePTSOrderForChangeability(new OrderImpl());
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSOrderForChangeability", 
                    new Class[] { Order.class });
            
            assertEquals(1012,((Order)l_paramsValue.getFirstCalled()[0]).getOrderId());
            
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「PTS発注審査個別チェック.validatePTS注文取消可能状態()」抛出異常
     * 抛出異常信息：SYSTEM_ERROR_80017
     *
     */
    public void testValidatePTSOrderForCancellation_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellation_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            this.l_manager.validatePTSOrderForCancellation(null);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「PTS発注審査個別チェック.validatePTS注文取消可能状態()」正常結束
     *
     */
    public void testValidatePTSOrderForCancellation_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrderForCancellation_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSOrderForCancellation", new Class[]
                    { Order.class },
                    null);
            
            this.l_manager.validatePTSOrderForCancellation(new OrderImpl());
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSOrderForCancellation", 
                    new Class[] { Order.class });
            
            assertEquals(1234567,((Order)l_paramsValue.getFirstCalled()[0]).getAccountId());
            
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ値不正の場合
     * フローのメッセージ:SYSTEM_ERROR_80017
     *
     */
    public void testValidatePTSOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            this.l_manager.validatePTSOrder(null, null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate取引可能顧客」失敗的場合: 抛出異常信息：BUSINESS_ERROR_00275
     * 出現bug:不應該抛出異常，應該reutrn失敗：
     * 錯誤的：
     * if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        正確的：
                if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug(l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_validationResult.getProcessingResult().getErrorInfo()));
        }
        
        返回失敗信息:WEB3ErrorCatalog.BUSINESS_ERROR_00275
     */
    public void testValidatePTSOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setYellowCustomer("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275,l_result.getProcessingResult().getErrorInfo());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();           
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate取引可能顧客」正常通過
     * isPTS口座開設()の戻り値=false）例外をthrowする
     * 
     * 返回失敗信息：BUSINESS_ERROR_02967
     */
    public void testValidatePTSOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            
            this.l_blnIsPTSAccountOpen = false;
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02967,l_result.getProcessingResult().getErrorInfo());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();         
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate銘柄コード」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00301
     */
    public void testValidatePTSOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            

            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301,l_ex.getErrorInfo());          
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate市場コード」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_01747
     */
    public void testValidatePTSOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("01");
            l_marketParams.setSuspension("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            

            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01747,l_ex.getErrorInfo());          
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validateインサイダー」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_01356
     * 
     */
    public void testValidatePTSOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setInstitutionCode("1D");
            l_insiderParams.setBranchId(33381);
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setProductId(3304148080000L);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setRegistDiv("2");
            l_insiderParams.setLastUpdater("123456");
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_insiderParams);
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            

            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01356,l_ex.getErrorInfo());          
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate顧客銘柄別取引停止（PTS）」（売注文）失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_01357
     * 
     */
    public void testValidatePTSOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
                        
            TestDBUtility.deleteAll(AccountProductOrderStopRow.TYPE);
            AccountProductOrderStopParams l_accountProductOrderStopParams = 
                TestDBUtility.getAccountProductOrderStopRow();
            l_accountProductOrderStopParams.setInstitutionCode("1D");
            l_accountProductOrderStopParams.setBranchId(33381L);
            l_accountProductOrderStopParams.setAccountId(333812512246L);
            l_accountProductOrderStopParams.setProductId(3304148080000L);
            l_accountProductOrderStopParams.setStopTradeDivSellCash("1");
            l_accountProductOrderStopParams.setApplyStartDate(WEB3DateUtility.getDate("20000701","yyyyMMdd"));
            l_accountProductOrderStopParams.setApplyEndDate(WEB3DateUtility.getDate("20100901","yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_accountProductOrderStopParams);
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            

            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01357,l_ex.getErrorInfo());          
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate取引銘柄」（売注文）失敗的場合
     * 抛出異常信息：BUSINESS_ERROR_01966
     */
    public void testValidatePTSOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
                        
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            

            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01966,l_ex.getErrorInfo());          
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate取扱可能PTS市場」失敗的場合
     * 抛出異常信息：SYSTEM_ERROR_80005
     */
    public void testValidatePTSOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = null;
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());          
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「税区分」== 2:特定 且つ「is特定口座開設」失敗的場合
     * 返回錯誤信息：BUSINESS_ERROR_00637
     */
    public void testValidatePTSOrder_C0010()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL; // 2:特定
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            
            this.l_blnIsSpecialAccountEstablished = false;
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00637, l_result.getProcessingResult().getErrorInfo()); 

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「税区分」== 3：特定口座かつ源泉徴収 且つ「is特定口座開設」正常通過
     * 「validate特定口座取扱規制」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     */
    public void testValidatePTSOrder_C0011()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateCapitalGainTaxDealingsReg", 
                    new Class[]{ TaxTypeEnum.class, EqTypeProduct.class, boolean.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = null;
            
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
             

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue1.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue1.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateCapitalGainTaxDealingsReg",
                    new Class[]
                    {  TaxTypeEnum.class, EqTypeProduct.class, boolean.class  });
            
            assertEquals(3,((TaxTypeEnum)l_paramsValue2.getFirstCalled()[0]).intValue());
            assertEquals(3304148080000L,((EqTypeProduct)l_paramsValue2.getFirstCalled()[1]).getProductId());
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_ex.getErrorInfo());  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「get初回注文の注文単位ID>0」の場合,原注文発注日==null
     * 「株式注文単位テーブル」檢索失敗的場合
     * 
     * 抛出異常信息：SYSTEM_ERROR_80005
     */
    public void testValidatePTSOrder_C0012()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId = new Long(10);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_result.getProcessingResult().getErrorInfo());
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate注文条件」失敗の場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     */
    public void testValidatePTSOrder_C0013()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(3304148080001L);
            
            
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));

            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_ex.getErrorInfo());
  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate株数（PTS）」失敗の場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     */
    public void testValidatePTSOrder_C0014()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(0);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(2000,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);

            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_ex.getErrorInfo());
  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate指値注文（PTS）」失敗の場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     */
    public void testValidatePTSOrder_C0015()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                    
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(0);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(2000,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());

            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_ex.getErrorInfo());
  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate注文単価（PTS）」返回為falseの場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00293
     */
    public void testValidatePTSOrder_C0016()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(false));
                    
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = false;//現物買注文
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(0);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(2000,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());

            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice",
                    new Class[]
                    {double.class, WEB3EquityTradedProduct.class, SubAccount.class });
            
            assertEquals(5000,((Double)l_paramsValue5.getFirstCalled()[0]).doubleValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00293, l_result.getProcessingResult().getErrorInfo());
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 「validate売付可能数量」失敗の場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     */
    public void testValidatePTSOrder_C0017()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateSellableAssetQuantity",
                    new Class[]
                    { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                    
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;//売付
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(0);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            fail();
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(2000,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());

            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice",
                    new Class[]
                    {double.class, WEB3EquityTradedProduct.class, SubAccount.class });
            
            assertEquals(5000,((Double)l_paramsValue5.getFirstCalled()[0]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateSellableAssetQuantity",
                    new Class[]
                    {SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class });
            
            assertEquals(2000,((Double)l_paramsValue6.getFirstCalled()[2]).doubleValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005, l_ex.getErrorInfo());
  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate機@構預託同意」異常
     */
    public void testValidatePTSOrder_C0018()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateSellableAssetQuantity",
                    new Class[]
                    { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class },
                    null);
                    
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;//売付
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(0);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(2000,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());

            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice",
                    new Class[]
                    {double.class, WEB3EquityTradedProduct.class, SubAccount.class });
            
            assertEquals(5000,((Double)l_paramsValue5.getFirstCalled()[0]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateSellableAssetQuantity",
                    new Class[]
                    {SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class });
            
            assertEquals(2000,((Double)l_paramsValue6.getFirstCalled()[2]).doubleValue(), 0);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02964, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 正常返回
     * 
     */
    public void testValidatePTSOrder_C0019()
    {
        final String STR_METHOD_NAME = "testValidatePTSOrder_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateSellableAssetQuantity",
                    new Class[]
                    { SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateMechanismDepositAgree",
                    new Class[]{SubAccount.class},
                    null);
                    
            String l_strInstitutionCode = "1D";
            WEB3GentradeTrader l_trader = null;
            String l_strMarketCode = "01";
            String l_strProductCode = "0001";
            double l_dblOrderQuantity = 2000;
            double l_dblLimitPrice = 5000;
            EqTypeExecutionConditionType l_execCondType = null;
            TaxTypeEnum l_taxTypeEnum = TaxTypeEnum.SPECIAL_WITHHOLD; // 3：特定口座かつ源泉徴収
            Timestamp l_tsOrderLaspeDate = null;
            boolean l_blnIsSellOrder = true;//売付
            String l_strOrderChannel = null;
            String l_strPriceConditionType = null;
            String l_strOrderCond = null;
            String l_strOrderCondOperator = null;
            double l_dblStopOrderBasePrice = 0;
            double l_dblWLimitOrderChange = 0;
            Long l_firstOrderUnitId =new Long(0);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqTypeNewOrderValidationResult l_result = this.l_manager.validatePTSOrder(l_subAccount, 
                WEB3EquityNewCashBasedOrderSpec.createPTSEquityOrderSpec(
                    l_strInstitutionCode,
                    l_trader,
                    l_strMarketCode,
                    l_strProductCode,
                    l_dblOrderQuantity,
                    l_dblLimitPrice,
                    l_execCondType,
                    l_taxTypeEnum,
                    l_tsOrderLaspeDate,
                    l_blnIsSellOrder,
                    l_strOrderChannel,
                    l_strPriceConditionType,
                    l_strOrderCond,
                    l_strOrderCondOperator,
                    l_dblStopOrderBasePrice,
                    l_dblWLimitOrderChange,
                    l_firstOrderUnitId));
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class });
            
            assertEquals("0001",((String)l_paramsValue.getFirstCalled()[0]));
            assertEquals("1D",((String)l_paramsValue.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class  });
            assertEquals("01",((String)l_paramsValue2.getFirstCalled()[10]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(2000,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());

            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice",
                    new Class[]
                    {double.class, WEB3EquityTradedProduct.class, SubAccount.class });
            
            assertEquals(5000,((Double)l_paramsValue5.getFirstCalled()[0]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateSellableAssetQuantity",
                    new Class[]
                    {SubAccount.class, TradedProduct.class, double.class, TaxTypeEnum.class });
            
            assertEquals(2000,((Double)l_paramsValue6.getFirstCalled()[2]).doubleValue(), 0);
            
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
             
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
  
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ値不正の場合
     * フローのメッセージ:SYSTEM_ERROR_80017
     *
     */
    public void testValidatePTSChangeOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_manager.validatePTSChangeOrder(null,null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * isPTS口座開設()の戻り値＝false
     * 
     * 返回失敗信息：WEB3ErrorCatalog.BUSINESS_ERROR_02967
     *
     */
    public void testValidatePTSChangeOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            this.l_blnIsPTSAccountOpen = false;
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02967,l_result.getProcessingResult().getErrorInfo());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validateOrderIdForExistence」失敗的場合
     * 
     * 返回失敗信息
     *
     */
    public void testValidatePTSChangeOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
                                
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            assertTrue(l_result.getProcessingResult().isFailedResult());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validatePTS注文訂正可能状態(注文)」失敗的場合
     * 
     * 返回失敗信息:BUSINESS_ERROR_02968
     *
     */
    public void testValidatePTSChangeOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00032,l_result.getProcessingResult().getErrorInfo());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「市場オブジェクトを取得」失敗的場合
     * 
     * 抛出異常信息:WEB3ErrorCatalog.SYSTEM_ERROR_80005
     *
     */
    public void testValidatePTSChangeOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3306L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(12000);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_result.getProcessingResult().getErrorInfo());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate市場コード」失敗的場合
     * 
     * 抛出異常信息:BUSINESS_ERROR_01747
     *
     */
    public void testValidatePTSChangeOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("01");
            l_marketParams.setSuspension("1");
            TestDBUtility.insertWithDel(l_marketParams);
            

            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01747,l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validateインサイダー」失敗的場合
     * 
     * 抛出異常信息:BUSINESS_ERROR_01356
     *
     */
    public void testValidatePTSChangeOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
                        
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setInstitutionCode("1D");
            l_insiderParams.setBranchId(33381);
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setProductId(3304148080000L);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setRegistDiv("2");
            l_insiderParams.setLastUpdater("123456");
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_insiderParams);
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01356,l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate顧客銘柄別取引停止（PTS）」失敗的場合
     * 
     * 抛出異常信息:BUSINESS_ERROR_00005
     *
     */
    public void testValidatePTSChangeOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                        

            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate取引銘柄」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_01966
     *
     */
    public void testValidatePTSChangeOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);            
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01966,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate取扱可能PTS市場」失敗的場合
     * 
     * 抛出異常信息:SYSTEM_ERROR_80005
     *
     */
    public void testValidatePTSChangeOrder_C0010()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
                        
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            

            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate注文条件」失敗的場合
     * 
     * 抛出異常信息:BUSINESS_ERROR_00005
     *
     */
    public void testValidatePTSChangeOrder_C0011()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                        
            
            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「税区分」== 2:特定 且つ「is特定口座開設」失敗的場合
     * 返回錯誤信息：BUSINESS_ERROR_00637
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0012()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            
            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        

            
            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
            this.l_blnIsSpecialAccountEstablished = false;
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00637,l_result.getProcessingResult().getErrorInfo());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();


        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「税区分」== 3：特定口座かつ源泉徴収 且つ「is特定口座開設」正常通過
     * 「validate株数（PTS）」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0013()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                        
            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(1200,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);

            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());


        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate指値注文（PTS）」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0014()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            
            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(1200,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());

            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());


        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate訂正項目（PTS）」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0015()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class },
                            new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
            
            
            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(1200,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class });
            
            assertEquals(3304148080001L,((EqTypeOrderUnit)l_paramsValue5.getFirstCalled()[0]).getOrderUnitId(), 0);

            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice",
                    new Class[]
                    {double.class, WEB3EquityTradedProduct.class, SubAccount.class});
            
            assertEquals(0,((Double)l_paramsValue6.getFirstCalled()[0]).doubleValue(), 0);
            
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());


        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate訂正時注文Rev上限（PTS）」失敗的場合
     * 
     * 抛出異常信息：BUSINESS_ERROR_00005
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0016()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeOrderRevUpperLimit",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class },
                            new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                            

            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(1200,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class });
            
            assertEquals(3304148080001L,((EqTypeOrderUnit)l_paramsValue5.getFirstCalled()[0]).getOrderUnitId(), 0);

            
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSChangeOrderRevUpperLimit",
                    new Class[]
                    {  EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class });
            
            assertEquals(3304148080001L,((EqTypeOrderUnit)l_paramsValue6.getFirstCalled()[0]).getOrderUnitId(), 0);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());


        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 
     * 正常返回
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0017()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class },
                            null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSChangeOrderRevUpperLimit",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class },
                            null);
                            

            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(1200,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSChangeItem",
                    new Class[]
                    { EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class, String.class, String.class, double.class, double.class,
                            EqTypeExecutionConditionType.class, boolean.class, Date.class,
                            EqTypeSettleContractOrderEntry[].class });
            
            assertEquals(3304148080001L,((EqTypeOrderUnit)l_paramsValue5.getFirstCalled()[0]).getOrderUnitId(), 0);

            
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSChangeOrderRevUpperLimit",
                    new Class[]
                    {  EqTypeOrderUnit.class, double.class, double.class, EqTypeExecutionConditionType.class,
                            String.class });
            
            assertEquals(3304148080001L,((EqTypeOrderUnit)l_paramsValue6.getFirstCalled()[0]).getOrderUnitId(), 0);
            
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate注文単価（PTS）」返回失敗信息:BUSINESS_ERROR_00293
     * 
     * 
     * 
     *
     */
    public void testValidatePTSChangeOrder_C0018()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            OrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(null,3304148080001L);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            WEB3EquityChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = 
                new WEB3EquityChangeOrderUnitEntry
                    (null,l_eqTypeOrderUnit,true,null,null,null,0,0,new Date(),null,null);
            
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,"1D",null,null);
            
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateAccountForTrading",
                    new Class[] {WEB3GentradeMainAccount.class, Timestamp.class},
                    l_orderValidationResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", 
                    new Class[]{ SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateOrderCondition",
                    new Class[]
                    { WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class },
                            null);
                        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSQuantity",
                    new Class[]{ TradedProduct.class, WEB3GentradeBranch.class, double.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSPrice", new Class[]
                    { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                    new Boolean(false));
            

            
            EqTypeOrderImpl l_eqTypeOrderUnitImpl = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnitImpl);
            
                    
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]{SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderCondition",
                    new Class[]{ WEB3GentradeSubAccount.class, long.class, WEB3EquityTradedProduct.class, Date.class, Date.class,
                            String.class, EqTypeExecutionConditionType.class, boolean.class, String.class,
                            String.class, String.class});
            
            assertEquals("01",(String)l_paramsValue2.getFirstCalled()[10]);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSQuantity",
                    new Class[]
                    { TradedProduct.class, WEB3GentradeBranch.class, double.class  });
            
            assertEquals(1200,((Double)l_paramsValue3.getFirstCalled()[2]).doubleValue(), 0);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSLimitOrder",
                    new Class[]
                    { WEB3EquityNewCashBasedOrderSpec.class });
            
            assertEquals("01",((WEB3EquityNewCashBasedOrderSpec)l_paramsValue4.getFirstCalled()[0]).getMarketCode());
            
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00293, l_result.getProcessingResult().getErrorInfo());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate取引可能顧客」失敗的場合
     * 
     * 返回失敗信息：BUSINESS_ERROR_00275
     *
     */
    public void testValidatePTSChangeOrder_C0019()
    {
        final String STR_METHOD_NAME = "testValidatePTSChangeOrder_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setYellowCustomer("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = new EqTypeChangeOrderUnitEntry(1000,0,0);
            WEB3EquityChangeOrderSpec l_eqChangeOrderSpec =
                new WEB3EquityChangeOrderSpec(1000,l_eqTypeChangeOrderUnitEntry,null,null,null);
            
            EqTypeOrderValidationResult l_result =
                this.l_manager.validatePTSChangeOrder(l_subAccount,l_eqChangeOrderSpec);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275,l_result.getProcessingResult().getErrorInfo());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * パラメータ値不正の場合
     * フローのメッセージ:SYSTEM_ERROR_80017
     *
     */
    public void testValidatePTSCancelOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_manager.validatePTSCancelOrder(null,null);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validate取引可能顧客」失敗的場合:
     *
     * 返回失敗信息：BUSINESS_ERROR_00275
     */
    public void testValidatePTSCancelOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setYellowCustomer("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275, l_result.getProcessingResult().getErrorInfo());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();           
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validateOrderIdForExistence」失敗的場合:
     *
     * 返回失敗信息
     */
    public void testValidatePTSCancelOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456788L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            assertTrue(l_result.getProcessingResult().isFailedResult());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();           
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validatePTS注文取消可能状態(注文)」失敗的場合:
     *
     * 返回失敗信息:BUSINESS_ERROR_00820
     */
    public void testValidatePTSCancelOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(12000);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]{long.class });
            
            assertEquals(123456789L,((Long)l_paramsValue.getFirstCalled()[0]).longValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00820, l_result.getProcessingResult().getErrorInfo());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();           
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     *「市場コードを取得」失敗的場合:
     *
     * 返回失敗信息:SYSTEM_ERROR_80005
     */
    public void testValidatePTSCancelOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3306L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(12000);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]{long.class });
            
            assertEquals(123456789L,((Long)l_paramsValue.getFirstCalled()[0]).longValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_result.getProcessingResult().getErrorInfo());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();           
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validate市場コード」失敗的場合:
     *
     * 抛出異常信息:BUSINESS_ERROR_01747
     */
    public void testValidatePTSCancelOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("01");
            l_marketParams.setSuspension("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]{long.class });
            
            assertEquals(123456789L,((Long)l_paramsValue.getFirstCalled()[0]).longValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01747, l_ex.getErrorInfo());           
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validate取引銘柄」失敗的場合:
     *
     * 抛出異常信息：BUSINESS_ERROR_01966
     */
    public void testValidatePTSCancelOrder_C0007()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
 
            TestDBUtility.deleteAll(TradedProductRow.TYPE);

            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]{long.class });
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01966,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validate取扱可能PTS市場」失敗的場合:
     *
     * 返回失敗信息:SYSTEM_ERROR_80005
     */
    public void testValidatePTSCancelOrder_C0008()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            fail();

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]{long.class });
            
            assertEquals(123456789L,((Long)l_paramsValue.getFirstCalled()[0]).longValue(), 0);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *
     * 正常返回
     */
    public void testValidatePTSCancelOrder_C0009()
    {
        final String STR_METHOD_NAME = "testValidatePTSCancelOrder_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            EqTypeOrderImpl l_eqTypeOrderUnit = new EqTypeOrderImpl(123456789L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]
                    { long.class },
                    l_eqTypeOrderUnit);
                    
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            
            EqTypeCancelOrderSpec l_eqTypeCancelOrderSpec = new EqTypeCancelOrderSpec(123456789L);
            EqTypeOrderValidationResult l_result = 
                this.l_manager.validatePTSCancelOrder(l_subAccount,l_eqTypeCancelOrderSpec);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validateOrderIdForExistence",
                    new Class[]{long.class });
            
            assertEquals(123456789L,((Long)l_paramsValue.getFirstCalled()[0]).longValue(), 0);
            
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validatePTS市場別取引可能上限金額」失敗的場合:
     *
     * 返回失敗信息:SYSTEM_ERROR_80005
     */
    public void testValidatePTSMarketMaxHandlingPrice_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSMarketMaxHandlingPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            this.l_manager.validatePTSMarketMaxHandlingPrice(l_branch,l_market,50000);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validatePTS市場別取引可能上限金額」正常通過
     *
     */
    public void testValidatePTSMarketMaxHandlingPrice_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSMarketMaxHandlingPrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
                        
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setInstitutionCode("1D");
            l_branchMarketPtsDealtCondParams.setBranchCode("381");
            l_branchMarketPtsDealtCondParams.setMarketCode("01");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_branchMarketPtsDealtCondParams.setMaxHandlingPrice(70000);
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams); 
            
            this.l_manager.validatePTSMarketMaxHandlingPrice(l_branch,l_market,50000);
            assertTrue(true);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「(validate顧客銘柄別取引停止（PTS）)」失敗的場合:
     *
     * 抛出異常信息：BUSINESS_ERROR_00005
     */
    public void testValidatePTSAccountProductOrderStop_C0001()
    {
        final String STR_METHOD_NAME = "testValidatePTSAccountProductOrderStop_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005,STR_METHOD_NAME));
                    
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            this.l_manager.validatePTSAccountProductOrderStop(l_subAccount,3304148080000L,OrderTypeEnum.EQUITY_SELL);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue());
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「(validate顧客銘柄別取引停止（PTS）)」正常通過的場合
     *
     */
    public void testValidatePTSAccountProductOrderStop_C0002()
    {
        final String STR_METHOD_NAME = "testValidatePTSAccountProductOrderStop_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validatePTSAccountProductOrderStop", new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class },
                    null);
                    
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccountForTest(TestDBUtility.getSubAccountRow());
            
            this.l_manager.validatePTSAccountProductOrderStop(l_subAccount,3304148080000L,OrderTypeEnum.EQUITY_SELL);
           WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", 
                    "validatePTSAccountProductOrderStop",
                    new Class[]
                    { SubAccount.class, long.class, OrderTypeEnum.class });
            
            assertEquals(3304148080000L,((Long)l_paramsValue.getFirstCalled()[1]).longValue());
            
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「validate取扱可能PTS市場」失敗的場合:
     *
     * 抛出異常信息：SYSTEM_ERROR_80005
     */
    public void testValidateHandlingPossiblePTSMarket_C0001()
    {
        final String STR_METHOD_NAME = "testValidateHandlingPossiblePTSMarket_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);

            
            this.l_manager.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     *「validate取扱可能PTS市場」正常結束:
     *
     */
    public void testValidateHandlingPossiblePTSMarket_C0002()
    {
        final String STR_METHOD_NAME = "testValidateHandlingPossiblePTSMarket_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);

            
            this.l_manager.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
        
    }
    
    /**
     * 発注日＝権利落ち日の場合はtrue
     * 有bug，時間上的
     *
     */
    public void testIsPTSDevidendRightDate_C0001()
    {
        final String STR_METHOD_NAME = "testIsPTSDevidendRightDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calOrderBizDate = Calendar.getInstance();
            l_calOrderBizDate.set(2007,11,17);
            
            Calendar l_calYearlyBooksClosingDate = Calendar.getInstance();
            l_calYearlyBooksClosingDate.set(2007,11,20,10,0,0);
            
            Timestamp l_tsOrderBizDate = new Timestamp(l_calOrderBizDate.getTimeInMillis());
            Timestamp l_tsYearlyBooksClosingDate = new Timestamp(l_calYearlyBooksClosingDate.getTimeInMillis());
            
            assertTrue(this.l_manager.isPTSDevidendRightDate(l_tsOrderBizDate,l_tsYearlyBooksClosingDate));
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 発注日＝権利落ち日の場合はfalse
     *
     */
    public void testIsPTSDevidendRightDate_C0002()
    {
        final String STR_METHOD_NAME = "testIsPTSDevidendRightDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calOrderBizDate = Calendar.getInstance();
            l_calOrderBizDate.set(2007,11,17);
            
            Calendar l_calYearlyBooksClosingDate = Calendar.getInstance();
            l_calYearlyBooksClosingDate.set(2007,11,21);
            
            Timestamp l_tsOrderBizDate = new Timestamp(l_calOrderBizDate.getTimeInMillis());
            Timestamp l_tsYearlyBooksClosingDate = new Timestamp(l_calYearlyBooksClosingDate.getTimeInMillis());
            
            assertFalse(this.l_manager.isPTSDevidendRightDate(l_tsOrderBizDate,l_tsYearlyBooksClosingDate));
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「get値幅上限値（PTS）」正常返回
     * 
     * 値幅上限値（PTS）= 270000
     *
     */
    public void testGetPTSStopHighPrice_C0001()
    {
        final String STR_METHOD_NAME = "testGetPTSStopHighPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
           
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setInstitutionCode("1D");
            l_eqtypeTradedProductParams.setLastClosingPrice(90000);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            
            l_equityLimitPriceRangeMstParams.setMarketCode("01");
            l_equityLimitPriceRangeMstParams.setRange(100000);
            l_equityLimitPriceRangeMstParams.setLowPrice(60000);
            l_equityLimitPriceRangeMstParams.setCapPrice(1200000);
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager",
                    "getTickValue", new Class[]
                    { WEB3EquityTradedProduct.class, double.class },
                    new Double(90000));
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            
            assertEquals(270000, this.l_manager.getPTSStopHighPrice(l_tradedProduct), 0);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get値幅下限値（PTS）
     * （基準値－値幅）< 0の場合
     *
     */
    public void testGetPTSStopLowPrice_C0001()
    {
        final String STR_METHOD_NAME = "testGetPTSStopHighPrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
           
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setInstitutionCode("1D");
            l_eqtypeTradedProductParams.setLastClosingPrice(80000);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            
            l_equityLimitPriceRangeMstParams.setMarketCode("01");
            l_equityLimitPriceRangeMstParams.setRange(160000);
            l_equityLimitPriceRangeMstParams.setLowPrice(60000);
            l_equityLimitPriceRangeMstParams.setCapPrice(1200000);
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            assertEquals(1.0D, this.l_manager.getPTSStopLowPrice(l_tradedProduct),0);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get値幅下限値（PTS）
     * （基準値－値幅）= 0の場合
     *
     */
    public void testGetPTSStopLowPrice_C0002()
    {
        final String STR_METHOD_NAME = "testGetPTSStopHighPrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
           
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setInstitutionCode("1D");
            l_eqtypeTradedProductParams.setLastClosingPrice(80000);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            
            l_equityLimitPriceRangeMstParams.setMarketCode("01");
            l_equityLimitPriceRangeMstParams.setRange(80000);
            l_equityLimitPriceRangeMstParams.setLowPrice(60000);
            l_equityLimitPriceRangeMstParams.setCapPrice(1200000);
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            assertEquals(1.0D, this.l_manager.getPTSStopLowPrice(l_tradedProduct),0);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get値幅下限値（PTS）
     * （基準値－値幅）= 80000の場合
     *
     */
    public void testGetPTSStopLowPrice_C0003()
    {
        final String STR_METHOD_NAME = "testGetPTSStopHighPrice_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME); 
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
           
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setInstitutionCode("1D");
            l_eqtypeTradedProductParams.setLastClosingPrice(160000);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstRow.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            
            l_equityLimitPriceRangeMstParams.setMarketCode("01");
            l_equityLimitPriceRangeMstParams.setRange(80000);
            l_equityLimitPriceRangeMstParams.setLowPrice(60000);
            l_equityLimitPriceRangeMstParams.setCapPrice(1200000);
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            assertEquals(80000D, this.l_manager.getPTSStopLowPrice(l_tradedProduct),0);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private void getData()
    {
        final String STR_METHOD_NAME = "getData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("1D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("01");
            l_clendarContext.setOrderAcceptProduct("0");
            l_clendarContext.setOrderAcceptTransaction("1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("5");
            l_clendarContext.setBizdateCalcParameter("0");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("1D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("01");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");

            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("1D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setMarketCode("01");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("0");

            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("1D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setMarketCode("01");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("5");

            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("1D");
            l_tradingTimeParams4.setBranchCode("381");
            l_tradingTimeParams4.setMarketCode("01");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("4");

            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setInstitutionCode("1D");
            l_tradingTimeParams5.setBranchCode("381");
            l_tradingTimeParams5.setMarketCode("01");
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setProductCode("0");
            l_tradingTimeParams5.setBizDateType("3");

            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);
            
            
            
            Calendar l_calendar1 = Calendar.getInstance();
            l_calendar1.set(Calendar.YEAR,2007);
            l_calendar1.set(Calendar.MONTH,11);
            l_calendar1.set(Calendar.DAY_OF_MONTH,24);
            l_calendar1.set(Calendar.HOUR_OF_DAY,10);
            l_calendar1.set(Calendar.MINUTE,00);
            l_calendar1.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar1.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp", l_tsBizDate);
            
            Calendar l_calendar2 = Calendar.getInstance();
            l_calendar2.set(Calendar.YEAR,2007);
            l_calendar2.set(Calendar.MONTH,11);
            l_calendar2.set(Calendar.DAY_OF_MONTH,25);
            l_calendar2.set(Calendar.HOUR_OF_DAY,10);
            l_calendar2.set(Calendar.MINUTE,00);
            l_calendar2.set(Calendar.SECOND,01);
            
            Timestamp l_tsBaseBizDate = new Timestamp(l_calendar2.getTimeInMillis());
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate", l_tsBaseBizDate);
            
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setYellowCustomer("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("1D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams =TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setCapitalGainTaxDealingsReg(1);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("1D");
            l_marketParams.setMarketCode("01");
            l_marketParams.setSuspension("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInsiderRow());
            
            TestDBUtility.deleteAll(AccountProductOrderStopRow.TYPE);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("1D");
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setInstitutionCode("1D");
            l_eqtypeTradedProductParams.setNewListType("9");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams.setInstitutionCode("1D");
            l_branchMarketPtsDealtCondParams.setBranchCode("381");
            l_branchMarketPtsDealtCondParams.setMarketCode("01");
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            l_branchMarketPtsDealtCondParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchMarketPtsDealtCondParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams); 
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_eqtypeOrderUnitParams.setConfirmedQuantity(12000);
            l_eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512246L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            
            TestDBUtility.deleteAll(InsiderRow.TYPE);
            InsiderParams l_insiderParams = new InsiderParams();
            l_insiderParams.setInstitutionCode("1D");
            l_insiderParams.setBranchId(33381);
            l_insiderParams.setAccountId(333812512246L);
            l_insiderParams.setProductId(3304148080000L);
            l_insiderParams.setRelationDiv("1");
            l_insiderParams.setRegistDiv("1");
            l_insiderParams.setLastUpdater("123456");
            l_insiderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_insiderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_insiderParams);
            
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private void getMock()
    {
        final String STR_METHOD_NAME = "getMock()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            
            WEB3EquityProduct l_web3EquityProduct = new WEB3EquityProduct(3304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations", "validateProductCode",
                    new Class[]
                    { String.class, String.class },
                    l_web3EquityProduct);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("1D");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch", "getInstitution", 
                    new Class[]{},
                    l_institution);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.entering(TEST_END + STR_METHOD_NAME);
    }
    
    private class OrderImpl implements Order
    {

        public long getOrderId()
        {

            return 1012;
        }
        

        public long getAccountId()
        {

            return 1234567;
        }

        public long getSubAccountId()
        {

            return 0;
        }

        public OrderUnit[] getOrderUnits()
        {

            return null;
        }

        public SubAccount getSubAccount()
        {

            return null;
        }

        public ProductTypeEnum getProductType()
        {

            return null;
        }

        public Object getDataSourceObject()
        {

            return null;
        }
        
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(SubAccountRow l_subAcctRow)
        {
            super(l_subAcctRow);
        }
        
        public MainAccount getMainAccount()
        {
            WEB3GentradeMainAccountForTest l_mainAccount = null;
            try
            {
                l_mainAccount = new WEB3GentradeMainAccountForTest(333812512246L);
            }
            catch(Exception l_ex)
            {
                log.error(ERROR + l_ex.getMessage(), l_ex);
                fail();
            }
            return l_mainAccount;
        }
        
        public Institution getInstitution()
        {
            WEB3GentradeInstitution l_institution = null;
            try
            {
                l_institution = new WEB3GentradeInstitution(33);
            }
            catch(Exception l_ex)
            {
                log.error(ERROR + l_ex.getMessage(), l_ex);
                fail();
            }
            return l_institution;
        }
        
    }
    
    private class WEB3GentradeMainAccountForTest extends WEB3GentradeMainAccount
    {

        public WEB3GentradeMainAccountForTest(long l_lngAccountLd) throws DataQueryException, DataNetworkException
        {
            super(l_lngAccountLd);
        }
        
        public boolean isPTSAccountOpen()
        {
            return l_blnIsPTSAccountOpen;
        }
        
        public boolean isSpecialAccountEstablished(Date l_deliveryDate, SubAccount l_subAccount)
                throws WEB3BaseException
        {
            return l_blnIsSpecialAccountEstablished;
        }
    }
    
    

}
@
