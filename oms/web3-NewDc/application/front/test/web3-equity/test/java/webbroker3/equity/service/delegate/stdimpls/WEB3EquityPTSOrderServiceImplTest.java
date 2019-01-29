head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityBookPriceRegistRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSOrderServiceImplTest extends TestBaseForMock
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3EquityPTSOrderServiceImplTest.class);
    
    public WEB3EquityPTSOrderServiceImplTest(String name)
    {
        super(name);
    }

    public void setUp() throws WEB3BaseException
    {
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
        TestDBUtility.deleteAll(AssetParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
        TestDBUtility.insertWithDel(l_InstitutionParams);
    }

    public void tearDown() throws WEB3BaseException
    {
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
        TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
        TestDBUtility.deleteAll(AssetParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
    }
    
    /**
     * execute
     * request =  null
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        try
        {
            l_orderServiceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request !=  null
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * WEB3ErrorCatalog.BUSINESS_ERROR_00126
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        try
        {
            l_orderServiceImpl.execute(l_buyConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request !=  null
     * �����������t�����������N�G�X�g
     * WEB3EquityBuyCompleteRequest
     * WEB3ErrorCatalog.BUSINESS_ERROR_00126
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        try
        {
            l_orderServiceImpl.execute(l_buyCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request !=  null
     * �����������t�����m�F���N�G�X�g
     * WEB3EquitySellConfirmRequest
     * WEB3ErrorCatalog.BUSINESS_ERROR_00126
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        try
        {
            l_orderServiceImpl.execute(l_sellConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request !=  null
     * �����������t�����������N�G�X�g
     * WEB3EquitySellCompleteRequest
     * WEB3ErrorCatalog.BUSINESS_ERROR_00126
     */
    public void test_execute_0005()
    {
        final String STR_METHOD_NAME = "test_execute_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
        try
        {
            l_orderServiceImpl.execute(l_sellCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * request !=  null
     * WEB3EquityBookPriceRegistRequest
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void test_execute_0006()
    {
        final String STR_METHOD_NAME = "test_execute_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBookPriceRegistRequest l_bookPriceRegistRequest = new WEB3EquityBookPriceRegistRequest();
        try
        {
            l_orderServiceImpl.execute(l_bookPriceRegistRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�gvalidate
     * WEB3EquityBuyConfirmRequest
     */
    public void test_validateOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        try
        {
            l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.error("", e);         
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * ���������敪 = 0
     * validatePTS����
     */
    public void test_validateOrder_0002()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        //��������
        l_buyConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_buyConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_buyConfirmRequest.priceCondType = "3";
        //���s����
        l_buyConfirmRequest.execCondType = "4";
        //���������敪
        l_buyConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_buyConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_buyConfirmRequest.marketCode = "11";
        //�����敪
        l_buyConfirmRequest.taxType = "1";
        //����敪
        l_buyConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorCode("111");
            l_errorInfo.setErrorMessage("12312");
            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = l_ex.getValidationResult().getProcessingResult();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            
            l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("12312", e.getErrorInfo().getErrorMessage());
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * ���������敪 = 0
     * validatePTS�s��ʎ���\������z
     */
    public void test_validateOrder_0003()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        //��������
        l_buyConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_buyConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_buyConfirmRequest.priceCondType = "3";
        //���s����
        l_buyConfirmRequest.execCondType = "4";
        //���������敪
        l_buyConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_buyConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_buyConfirmRequest.marketCode = "11";
        //�����敪
        l_buyConfirmRequest.taxType = "1";
        //����敪
        l_buyConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            
            l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * ���������敪 = 0
     * validate����]��
     */
    public void test_validateOrder_0004()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        //��������
        l_buyConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_buyConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_buyConfirmRequest.priceCondType = "3";
        //���s����
        l_buyConfirmRequest.execCondType = "4";
        //���������敪
        l_buyConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_buyConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_buyConfirmRequest.marketCode = "11";
        //�����敪
        l_buyConfirmRequest.taxType = "1";
        //����敪
        l_buyConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorCode("11");
            l_errorInfo.setErrorMessage("123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    new WEB3SystemLayerException(l_errorInfo,"333"));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("11",e.getErrorInfo().getErrorCode());
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * ���������敪 = 0
     * ������
     */
    public void test_validateOrder_0005()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        //��������
        l_buyConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_buyConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_buyConfirmRequest.priceCondType = "3";
        //���s����
        l_buyConfirmRequest.execCondType = "4";
        //���������敪
        l_buyConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_buyConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_buyConfirmRequest.marketCode = "11";
        //�����敪
        l_buyConfirmRequest.taxType = "1";
        //����敪
        l_buyConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_buyConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_buyConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_buyConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_buyConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_buyConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_buyConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("2", l_buyConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertNull(l_buyConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_buyConfirmResponse.marketCode);
            //������
            assertEquals("ggg", l_buyConfirmResponse.productName);
            // �����L������
            assertNull(l_buyConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * ���������敪 = 0
     * ����]�͌���.get���ӕ����\���敪 == "1�F�����s�����ӕ����\��"�̏ꍇ
     * ���X�|���X.�a����s���z
     * ������
     */
    public void test_validateOrder_0006()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        //��������
        l_buyConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_buyConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_buyConfirmRequest.priceCondType = "3";
        //���s����
        l_buyConfirmRequest.execCondType = "4";
        //���������敪
        l_buyConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_buyConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_buyConfirmRequest.marketCode = "11";
        //�����敪
        l_buyConfirmRequest.taxType = "1";
        //����敪
        l_buyConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("1");
            l_tpResult.setLackAccountBalance(23.6);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_buyConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_buyConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_buyConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_buyConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_buyConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_buyConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("1", l_buyConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertEquals("23.6", l_buyConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_buyConfirmResponse.marketCode);
            //������
            assertEquals("ggg", l_buyConfirmResponse.productName);
            // �����L������
            assertNull(l_buyConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquityBuyConfirmRequest
     * ���������敪 = 0
     * ����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��"
     * ���X�|���X.�a����s���z
     * ������
     */
    public void test_validateOrder_0007()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        //��������
        l_buyConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_buyConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_buyConfirmRequest.priceCondType = "3";
        //���s����
        l_buyConfirmRequest.execCondType = "4";
        //���������敪
        l_buyConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_buyConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_buyConfirmRequest.marketCode = "11";
        //�����敪
        l_buyConfirmRequest.taxType = "1";
        //����敪
        l_buyConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("3");
            l_tpResult.setLackAccountBalance(21.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquityBuyConfirmResponse l_buyConfirmResponse =
                (WEB3EquityBuyConfirmResponse)l_orderServiceImpl.validateOrder(l_buyConfirmRequest);
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_buyConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_buyConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_buyConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_buyConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_buyConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_buyConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("3", l_buyConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertEquals("21", l_buyConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_buyConfirmResponse.marketCode);
            //������
            assertEquals("ggg", l_buyConfirmResponse.productName);
            // �����L������
            assertNull(l_buyConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquitySellConfirmRequest
     * ���������敪 = 0
     * ������
     */
    public void test_validateOrder_0008()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        //��������
        l_sellConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_sellConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellConfirmRequest.priceCondType = "3";
        //���s����
        l_sellConfirmRequest.execCondType = "4";
        //���������敪
        l_sellConfirmRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_sellConfirmRequest.marketCode = "11";
        l_sellConfirmRequest.id = "1001";
        //�����敪
//        l_sellConfirmRequest.taxType = "1";
        //����敪
//        l_sellConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_orderServiceImpl.validateOrder(l_sellConfirmRequest);
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_sellConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_sellConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_sellConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_sellConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_sellConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("2", l_sellConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertNull(l_sellConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_sellConfirmResponse.marketCode);
            //�T�Z�뉿�P��
            assertEquals("1", "" + l_sellConfirmResponse.estimatedBookPrice);
            // �����L������
            assertNull(l_sellConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquitySellConfirmRequest
     * ���������敪 = 0
     * ����]�͌���.get���ӕ����\���敪 == "1�F�����s�����ӕ����\��"�̏ꍇ
     * ���X�|���X.�a����s���z
     * ������
     */
    public void test_validateOrder_0009()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        //��������
        l_sellConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_sellConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellConfirmRequest.priceCondType = "3";
        //���s����
        l_sellConfirmRequest.execCondType = "4";
        //���������敪
        l_sellConfirmRequest.expirationDateType = "1";
        
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_sellConfirmRequest.marketCode = "11";
        l_sellConfirmRequest.id = "1001";
        //�����敪
//        l_sellConfirmRequest.taxType = "1";
        //����敪
//        l_sellConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("1");
            l_tpResult.setLackAccountBalance(19);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_orderServiceImpl.validateOrder(l_sellConfirmRequest);
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_sellConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_sellConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_sellConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_sellConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_sellConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("1", l_sellConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertEquals("19", l_sellConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_sellConfirmResponse.marketCode);
            //�T�Z�뉿�P��
            assertEquals("1", "" + l_sellConfirmResponse.estimatedBookPrice);
            // �����L������
            assertNull(l_sellConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquitySellConfirmRequest
     * ���������敪 = 0
     * ����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��"
     * ���X�|���X.�a����s���z
     * ������
     */
    public void test_validateOrder_0010()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        //��������
        l_sellConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_sellConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellConfirmRequest.priceCondType = "3";
        //���s����
        l_sellConfirmRequest.execCondType = "4";
        //���������敪
        l_sellConfirmRequest.expirationDateType = "1";
        
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellConfirmRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_sellConfirmRequest.marketCode = "11";
        l_sellConfirmRequest.id = "1001";
        //�����敪
//        l_sellConfirmRequest.taxType = "1";
        //����敪
//        l_sellConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("3");
            l_tpResult.setLackAccountBalance(18.00);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_orderServiceImpl.validateOrder(l_sellConfirmRequest);
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_sellConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_sellConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_sellConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_sellConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_sellConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("3", l_sellConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertEquals("18", l_sellConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_sellConfirmResponse.marketCode);
            //�T�Z�뉿�P��
            assertEquals("1", "" + l_sellConfirmResponse.estimatedBookPrice);
            // �����L������
            assertNull(l_sellConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquitySellConfirmRequest
     * ���������敪 = 1
     * ����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��"
     * ���X�|���X.�a����s���z
     * ������
     */
    public void test_validateOrder_0011()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        //��������
        l_sellConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_sellConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellConfirmRequest.priceCondType = "0";
        //���s����
        l_sellConfirmRequest.execCondType = "1";
        //���������敪
        l_sellConfirmRequest.expirationDateType = "1";
        
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellConfirmRequest.orderCondType = "1";
        //�t�w�l�p���������P��
        l_sellConfirmRequest.stopOrderCondPrice = "23";
        //�t�w�l�p�����������Z�q
        l_sellConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_sellConfirmRequest.marketCode = "11";
        l_sellConfirmRequest.id = "1001";
        //�����敪
//        l_sellConfirmRequest.taxType = "1";
        //����敪
//        l_sellConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("3");
            l_tpResult.setLackAccountBalance(18.00);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_orderServiceImpl.validateOrder(l_sellConfirmRequest);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class});
            
//            assertEquals(((WEB3GentradeCommission)l_paramsValue.getFirstCalled()[0]));
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(8)[1]).toString());
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(8)[2]).toString());
            assertEquals("23.0","" + ((Double)l_paramsValue.getCalled(8)[3]).toString());
            assertEquals("1","" + ((EqTypeExecutionConditionType)l_paramsValue.getCalled(8)[4]).intValue());
            assertNull(((EqTypeExecutionConditionType)l_paramsValue.getCalled(8)[5]));
            assertEquals("0","" + ((String)l_paramsValue.getCalled(8)[6]));
            assertEquals("1","" + ((String)l_paramsValue.getCalled(8)[7]));
            assertNull(((String)l_paramsValue.getCalled(8)[8]));
            assertFalse(((Boolean)l_paramsValue.getCalled(8)[9]).booleanValue());
//            assertEquals(,((SubAccount)l_paramsValue.getCalled(8)[10]));
//            assertEquals(,((WEB3EquityTradedProduct)l_paramsValue.getCalled(8)[11]));
            assertEquals("1.0","" + ((Double)l_paramsValue.getCalled(8)[12]));
            assertTrue(((Boolean)l_paramsValue.getCalled(8)[13]).booleanValue());
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(8)[14]));
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(8)[15]));
            assertFalse(((Boolean)l_paramsValue.getCalled(8)[16]).booleanValue());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class });

            assertEquals(WEB3GentradeSubAccount.class,((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals(WEB3EquityNewCashBasedOrderSpec.class,((WEB3EquityNewCashBasedOrderSpec)l_paramsValue1.getFirstCalled()[1]).getClass());
     
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            assertEquals(Object[].class,((Object[])l_paramsValue2.getFirstCalled()[1]).getClass());
            assertEquals(Object[].class,((Object[])l_paramsValue2.getFirstCalled()[2]).getClass());
            assertEquals(OrderTypeEnum.class,((OrderTypeEnum)l_paramsValue2.getFirstCalled()[3]).getClass());
            assertEquals(Boolean.class,((Boolean)l_paramsValue2.getFirstCalled()[4]).getClass());
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_sellConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_sellConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_sellConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_sellConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_sellConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("3", l_sellConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertEquals("18", l_sellConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_sellConfirmResponse.marketCode);
            //�T�Z�뉿�P��
            assertEquals("1", "" + l_sellConfirmResponse.estimatedBookPrice);
            // �����L������
            assertNull(l_sellConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validateOrder
     * �����������t�����m�F���N�G�X�g
     * WEB3EquitySellConfirmRequest
     * ���������敪 = 2
     * ����]�͌���.get���ӕ����\���敪 == "3�F�a����s�����ӕ����\��"
     * ���X�|���X.�a����s���z
     * ������
     */
    public void test_validateOrder_0012()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        //��������
        l_sellConfirmRequest.orderQuantity = "1";
        //�����P���敪
        l_sellConfirmRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellConfirmRequest.priceCondType = "0";
        //���s����
        l_sellConfirmRequest.execCondType = "1";
        //���������敪
        l_sellConfirmRequest.expirationDateType = "1";
        
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellConfirmRequest.orderCondType = "2";
        //�t�w�l�p���������P��
//        l_sellConfirmRequest.stopOrderCondPrice = "23";
        //�t�w�l�p�����������Z�q
//        l_sellConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
        l_sellConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
        l_sellConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
        l_sellConfirmRequest.wLimitOrderPriceDiv = "1";
//        //W�w�l�p�����P��
        l_sellConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
        l_sellConfirmRequest.wlimitExecCondType = "7";
//        //�v�w�l�p�L����ԋ敪
        l_sellConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellConfirmRequest.productCode = "45";
        //�s��R�[�h
        l_sellConfirmRequest.marketCode = "11";
        l_sellConfirmRequest.id = "1001";
        //�����敪
//        l_sellConfirmRequest.taxType = "1";
        //����敪
//        l_sellConfirmRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("3");
            l_tpResult.setLackAccountBalance(18.00);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellConfirmResponse l_sellConfirmResponse =
                (WEB3EquitySellConfirmResponse)l_orderServiceImpl.validateOrder(l_sellConfirmRequest);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class});
            
//            assertEquals(((WEB3GentradeCommission)l_paramsValue.getFirstCalled()[0]));
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(9)[1]).toString());
            assertEquals("222.0","" + ((Double)l_paramsValue.getCalled(9)[2]).toString());
            assertEquals("232.0","" + ((Double)l_paramsValue.getCalled(9)[3]).toString());
            assertEquals("1","" + ((EqTypeExecutionConditionType)l_paramsValue.getCalled(9)[4]).intValue());
            assertNull(((EqTypeExecutionConditionType)l_paramsValue.getCalled(9)[5]));
            assertEquals("0","" + ((String)l_paramsValue.getCalled(9)[6]));
            assertEquals("2","" + ((String)l_paramsValue.getCalled(9)[7]));
            assertNull(((String)l_paramsValue.getCalled(9)[8]));
            assertFalse(((Boolean)l_paramsValue.getCalled(9)[9]).booleanValue());
//            assertEquals(,((SubAccount)l_paramsValue.getCalled(10)[10]));
//            assertEquals(,((WEB3EquityTradedProduct)l_paramsValue.getCalled(10)[11]));
            assertEquals("1.0","" + ((Double)l_paramsValue.getCalled(9)[12]));
            assertTrue(((Boolean)l_paramsValue.getCalled(9)[13]).booleanValue());
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(9)[14]));
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(9)[15]));
            assertFalse(((Boolean)l_paramsValue.getCalled(9)[16]).booleanValue());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class });

            assertEquals(WEB3GentradeSubAccount.class,((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals(WEB3EquityNewCashBasedOrderSpec.class,((WEB3EquityNewCashBasedOrderSpec)l_paramsValue1.getFirstCalled()[1]).getClass());
     
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            assertEquals(Object[].class,((Object[])l_paramsValue2.getFirstCalled()[1]).getClass());
            assertEquals(Object[].class,((Object[])l_paramsValue2.getFirstCalled()[2]).getClass());
            assertEquals(OrderTypeEnum.class,((OrderTypeEnum)l_paramsValue2.getFirstCalled()[3]).getClass());
            assertEquals(Boolean.class,((Boolean)l_paramsValue2.getFirstCalled()[4]).getClass());
            
            //�m�F��������
//            assertEquals(l_buyConfirmResponse.checkDate);
            //�T�Z��n���
            assertEquals("5", "" + l_sellConfirmResponse.estimatedPrice); 
            //����I���x���s��R�[�h�ꗗ
            assertEquals("0", "" + l_sellConfirmResponse.messageSuspension.length);
            //�萔�����
            WEB3EquityCommissionInfoUnit l_commissionInfo = l_sellConfirmResponse.commissionInfo;
            assertEquals("6", "" + l_commissionInfo.commission);
            assertNull(l_commissionInfo.commissionCourse);
            assertEquals("8", "" + l_commissionInfo.commissionConsumptionTax);
            //�m�F���P��
            assertEquals("3", "" + l_sellConfirmResponse.checkPrice);
            //����ID
            assertEquals("5001", l_sellConfirmResponse.orderId);
            //�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellConfirmResponse.insiderWarningFlag);
            //���ӕ����\���敪
            assertEquals("3", l_sellConfirmResponse.attentionObjectionType);
            //���X�|���X.�a����s���z
            assertEquals("18", l_sellConfirmResponse.accountBalanceInsufficiency);
            //�s��R�[�h
            assertEquals("11", l_sellConfirmResponse.marketCode);
            //�T�Z�뉿�P��
            assertEquals("1", "" + l_sellConfirmResponse.estimatedBookPrice);
            // �����L������
            assertNull(l_sellConfirmResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�gvalidate
     * WEB3ErrorCatalog.BUSINESS_ERROR_00126
     */
    public void test_submitOrder_0001()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        try
        {
            l_orderServiceImpl.submitOrder(l_buyCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126,e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * ���������敪 = 0
     * validatePTS����
     */
    public void test_submitOrder_0002()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        //��������
        l_buyCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_buyCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyCompleteRequest.limitPrice = "21";
        //�l�i����
        l_buyCompleteRequest.priceCondType = "3";
        //���s����
        l_buyCompleteRequest.execCondType = "4";
        //���������敪
        l_buyCompleteRequest.expirationDateType = "1";
        //�����L������
//        l_buyCompleteRequest.expirationDate = "";
        //���������敪
        l_buyCompleteRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyCompleteRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyCompleteRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyCompleteRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyCompleteRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyCompleteRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyCompleteRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyCompleteRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_buyCompleteRequest.marketCode = "11";
        //�����敪
        l_buyCompleteRequest.taxType = "1";
        //����敪
        l_buyCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorCode("111");
            l_errorInfo.setErrorMessage("12312");
            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = l_ex.getValidationResult().getProcessingResult();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            
            l_orderServiceImpl.submitOrder(l_buyCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("12312", e.getErrorInfo().getErrorMessage());
        }
        catch (Exception e)
        {
            log.error("", e);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquityBuyCompleteRequest
     * ���������敪 = 0
     * validatePTS�s��ʎ���\������z
     */
    public void test_submitOrder_0003()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        //��������
        l_buyCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_buyCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyCompleteRequest.limitPrice = "21";
        //�l�i����
        l_buyCompleteRequest.priceCondType = "3";
        //���s����
        l_buyCompleteRequest.execCondType = "4";
        //���������敪
        l_buyCompleteRequest.expirationDateType = "1";
        //�����L������
//        l_buyCompleteRequest.expirationDate = "";
        //���������敪
        l_buyCompleteRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyCompleteRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyCompleteRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyCompleteRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyCompleteRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyCompleteRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyCompleteRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyCompleteRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_buyCompleteRequest.marketCode = "11";
        //�����敪
        l_buyCompleteRequest.taxType = "1";
        //����敪
        l_buyCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            
            l_orderServiceImpl.submitOrder(l_buyCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquityBuyCompleteRequest
     * ���������敪 = 0
     * validate����]��
     */
    public void test_submitOrder_0004()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        //��������
        l_buyCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_buyCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyCompleteRequest.limitPrice = "21";
        //�l�i����
        l_buyCompleteRequest.priceCondType = "3";
        //���s����
        l_buyCompleteRequest.execCondType = "4";
        //���������敪
        l_buyCompleteRequest.expirationDateType = "1";
        //�����L������
//        l_buyCompleteRequest.expirationDate = "";
        //���������敪
        l_buyCompleteRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyCompleteRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyCompleteRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyCompleteRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyCompleteRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyCompleteRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyCompleteRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyCompleteRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_buyCompleteRequest.marketCode = "11";
        //�����敪
        l_buyCompleteRequest.taxType = "1";
        //����敪
        l_buyCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorCode("11");
            l_errorInfo.setErrorMessage("123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    new WEB3SystemLayerException(l_errorInfo,"333"));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            l_orderServiceImpl.submitOrder(l_buyCompleteRequest);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("11",e.getErrorInfo().getErrorCode());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquityBuyCompleteRequest
     * ���������敪 = 0
     * submitNewCashBasedOrder
     * 
     */
    public void test_submitOrder_0005()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        //��������
        l_buyCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_buyCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyCompleteRequest.limitPrice = "21";
        //�l�i����
        l_buyCompleteRequest.priceCondType = "3";
        //���s����
        l_buyCompleteRequest.execCondType = "4";
        //���������敪
        l_buyCompleteRequest.expirationDateType = "1";
        //�����L������
//        l_buyCompleteRequest.expirationDate = "";
        //���������敪
        l_buyCompleteRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyCompleteRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyCompleteRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyCompleteRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyCompleteRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyCompleteRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyCompleteRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyCompleteRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_buyCompleteRequest.marketCode = "11";
        //�����敪
        l_buyCompleteRequest.taxType = "1";
        //����敪
        l_buyCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(5001));
            
            ErrorInfo l_errorInfo = new ErrorInfo();
            l_errorInfo.setErrorCode("111");
            l_errorInfo.setErrorMessage("12345");
            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult1 = l_ex.getValidationResult().getProcessingResult();
            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "submitNewCashBasedOrder", new Class[]
                    { SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            l_orderServiceImpl.submitOrder(l_buyCompleteRequest);
            fail();
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("12345", e.getErrorInfo().getErrorMessage());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquityBuyCompleteRequest
     * ���������敪 = 0
     * submitNewCashBasedOrder
     * ������
     */
    public void test_submitOrder_0006()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        //��������
        l_buyCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_buyCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyCompleteRequest.limitPrice = "21";
        //�l�i����
        l_buyCompleteRequest.priceCondType = "3";
        //���s����
        l_buyCompleteRequest.execCondType = "4";
        //���������敪
        l_buyCompleteRequest.expirationDateType = "1";
        //�����L������
//        l_buyCompleteRequest.expirationDate = "";
        //���������敪
        l_buyCompleteRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyCompleteRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyCompleteRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyCompleteRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyCompleteRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyCompleteRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyCompleteRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyCompleteRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
        l_buyCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_buyCompleteRequest.marketCode = "11";
        //�����敪
        l_buyCompleteRequest.taxType = "1";
        //����敪
        l_buyCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(10));
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "submitNewCashBasedOrder", new Class[]
                    { SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquityBuyCompleteResponse l_buyCompleteResponse =
                (WEB3EquityBuyCompleteResponse)l_orderServiceImpl.submitOrder(l_buyCompleteRequest);
            
            //���X�|���X.�X�V����
            assertEquals(WEB3DateUtility.formatDate(l_buyCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"),
                    "20070612");
            //���X�|���X.���ʔԍ�
            assertEquals("10", "" + l_buyCompleteResponse.orderActionId);
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            assertFalse(l_buyCompleteResponse.insiderWarningFlag);
            //�s��R�[�h
            assertEquals("11", l_buyCompleteResponse.marketCode);
            // ���X�|���X.�����L������
            assertNull(l_buyCompleteResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquitySellCompleteRequest
     * ���������敪 = 0
     * submitNewCashBasedOrder
     * ������
     */
    public void test_submitOrder_0007()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
        //��������
        l_sellCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_sellCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellCompleteRequest.priceCondType = "3";
        //���s����
        l_sellCompleteRequest.execCondType = "4";
        //���������敪
        l_sellCompleteRequest.expirationDateType = "1";
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellCompleteRequest.orderCondType = "0";
//        //�t�w�l�p���������P��
//        l_buyConfirmRequest.stopOrderCondPrice = "23";
//        //�t�w�l�p�����������Z�q
//        l_buyConfirmRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_sellCompleteRequest.marketCode = "11";
        l_sellCompleteRequest.id = "1001";
        //�����敪
//        l_sellCompleteRequest.taxType = "1";
        //����敪
//        l_sellCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(10));
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "submitNewCashBasedOrder", new Class[]
                    { SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellCompleteResponse l_sellCompleteResponse =
                (WEB3EquitySellCompleteResponse)l_orderServiceImpl.submitOrder(l_sellCompleteRequest);
            
            //���X�|���X.�X�V����
            assertEquals(WEB3DateUtility.formatDate(l_sellCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"),
                    "20070612");
            //���X�|���X.���ʔԍ�
            assertEquals("10", "" + l_sellCompleteResponse.orderActionId);
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellCompleteResponse.insiderWarningFlag);
            //�s��R�[�h
            assertEquals("11", l_sellCompleteResponse.marketCode);
            // ���X�|���X.�����L������
            assertNull(l_sellCompleteResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquitySellCompleteRequest
     * ���������敪 = 1
     * submitNewCashBasedOrder
     * ������
     */
    public void test_submitOrder_0008()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
//      ��������
        l_sellCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_sellCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellCompleteRequest.priceCondType = "0";
        //���s����
        l_sellCompleteRequest.execCondType = "1";
        //���������敪
        l_sellCompleteRequest.expirationDateType = "1";
        
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellCompleteRequest.orderCondType = "1";
        //�t�w�l�p���������P��
        l_sellCompleteRequest.stopOrderCondPrice = "23";
        //�t�w�l�p�����������Z�q
        l_sellCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
//        l_buyConfirmRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
//        l_buyConfirmRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
//        l_buyConfirmRequest.wLimitOrderPriceDiv = "2";
//        //W�w�l�p�����P��
//        l_buyConfirmRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
//        l_buyConfirmRequest.wlimitExecCondType = "121";
//        //�v�w�l�p�L����ԋ敪
//        l_buyConfirmRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_sellCompleteRequest.marketCode = "11";
        l_sellCompleteRequest.id = "1001";
        //�����敪
//        l_sellCompleteRequest.taxType = "1";
        //����敪
//        l_sellCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(10));
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "submitNewCashBasedOrder", new Class[]
                    { SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellCompleteResponse l_sellCompleteResponse =
                (WEB3EquitySellCompleteResponse)l_orderServiceImpl.submitOrder(l_sellCompleteRequest);
            
            //���X�|���X.�X�V����
            assertEquals(WEB3DateUtility.formatDate(l_sellCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"),
                    "20070612");
            //���X�|���X.���ʔԍ�
            assertEquals("10", "" + l_sellCompleteResponse.orderActionId);
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellCompleteResponse.insiderWarningFlag);
            //�s��R�[�h
            assertEquals("11", l_sellCompleteResponse.marketCode);
            // ���X�|���X.�����L������
            assertNull(l_sellCompleteResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit����
     * �����������t�����������N�G�X�g
     * WEB3EquitySellCompleteRequest
     * ���������敪 = 2
     * submitNewCashBasedOrder
     * ������
     */
    public void test_submitOrder_0009()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityPTSOrderServiceImpl l_orderServiceImpl = new WEB3EquityPTSOrderServiceImpl();
        WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
        //��������
        l_sellCompleteRequest.orderQuantity = "1";
        //�����P���敪
        l_sellCompleteRequest.orderPriceDiv = "0";
        //�����P��
//        l_buyConfirmRequest.limitPrice = "21";
        //�l�i����
        l_sellCompleteRequest.priceCondType = "0";
        //���s����
        l_sellCompleteRequest.execCondType = "1";
        //���������敪
        l_sellCompleteRequest.expirationDateType = "1";
        
        //�����L������
//        l_buyConfirmRequest.expirationDate = "";
        //���������敪
        l_sellCompleteRequest.orderCondType = "2";
        //�t�w�l�p���������P��
//        l_sellCompleteRequest.stopOrderCondPrice = "23";
        //�t�w�l�p�����������Z�q
//        l_sellCompleteRequest.stopOrderCondOperator = "2";
//        //�v�w�l�p���������P��
        l_sellCompleteRequest.wlimitOrderCondPrice = "232";
//        //�v�w�l�p�����������Z�q
        l_sellCompleteRequest.wlimitOrderCondOperator = "1";
//        //�v�w�l�p�����P���敪
        l_sellCompleteRequest.wLimitOrderPriceDiv = "1";
//        //W�w�l�p�����P��
        l_sellCompleteRequest.wLimitPrice = "222";
//        //�v�w�l�p���s����
        l_sellCompleteRequest.wlimitExecCondType = "7";
//        //�v�w�l�p�L����ԋ敪
        l_sellCompleteRequest.wlimitEnableStatusDiv = "0";

        //�����R�[�h
//        l_sellCompleteRequest.productCode = "45";
        //�s��R�[�h
        l_sellCompleteRequest.marketCode = "11";
        l_sellCompleteRequest.id = "1001";
        //�����敪
//        l_sellCompleteRequest.taxType = "1";
        //����敪
//        l_sellCompleteRequest.tradingType = "1";
        
        
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("0");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("0");
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "web3.tradingcalendarcontext", l_clendarContext);
        
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// ���ӌ�?�v��1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.tc." + 
//                "gentrade.stdimpls.TradingSystemImpl", 
//            "getSystemTimestamp",
//            new Class[]{}, 
//            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
                
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                st);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                 new Class[] {}, new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
//            ErrorInfo l_errorInfo = new ErrorInfo();
//            l_errorInfo.setErrorCode("111");
//            l_errorInfo.setErrorMessage("12312");
//            OrderValidationException l_ex = new OrderValidationException(l_errorInfo);
            ProcessingResult l_processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = new EqTypeNewOrderValidationResult(l_processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class },
                    l_eqTypeNewOrderValidationResult);
            
            WEB3EquityEstimatedDeliveryPrice l_deliveryPrice = new WEB3EquityEstimatedDeliveryPrice();
            l_deliveryPrice.setCalcUnitPrice(9.0);
            l_deliveryPrice.setEstimateDeliveryAmount(5.0);
            l_deliveryPrice.setCommissionFee(6.0);
            l_deliveryPrice.setCommissionFeeTax(8.0);
            l_deliveryPrice.setCheckGetCurrentPrice(3.0);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class},
                    l_deliveryPrice);
            
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(true);
            l_tpResult.setAttentionObjectionType("2");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                    l_tpResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "isInsiderMessageSuspension", 
                    new Class[]
                    { SubAccount.class, long.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "createNewOrderId",
                    new Class[]{},
                    new Long(10));
            
            ProcessingResult l_processingResult1 = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = new EqTypeOrderSubmissionResult(l_processingResult1);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "submitNewCashBasedOrder", new Class[]
                    { SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class },
                    l_eqTypeOrderSubmissionResult);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setAccountId(333812512246L);
            l_assetParams.setSubAccountId(33381251220301L);
            l_assetParams.setProductId(3304148080001L);
            l_assetParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_assetParams.setProductType(ProductTypeEnum.EQUITY);
            l_assetParams.setMiniStockDiv("0");
            TestDBUtility.insertWithDel(l_assetParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setPtsAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductCode("45");
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setStandardName("ggg");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            BranchMarketPtsDealtCondParams l_branchMarketPTSDealtCondParams = new BranchMarketPtsDealtCondParams();
            l_branchMarketPTSDealtCondParams.setInstitutionCode("0D");
            l_branchMarketPTSDealtCondParams.setBranchCode("381");
            l_branchMarketPTSDealtCondParams.setMarketCode("11");
            l_branchMarketPTSDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPTSDealtCondParams);
            
            WEB3EquitySellCompleteResponse l_sellCompleteResponse =
                (WEB3EquitySellCompleteResponse)l_orderServiceImpl.submitOrder(l_sellCompleteRequest);
            
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                    WEB3GentradeCommission.class, double.class,
                    double.class, double.class,
                    EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                    String.class,
                    String.class,
                    String.class,
                    boolean.class,
                    SubAccount.class,
                    WEB3EquityTradedProduct.class,
                    double.class,
                    boolean.class,
                    double.class,
                    double.class,
                    boolean.class});
            
//            assertEquals(((WEB3GentradeCommission)l_paramsValue.getFirstCalled()[0]));
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(16)[1]).toString());
            assertEquals("222.0","" + ((Double)l_paramsValue.getCalled(16)[2]).toString());
            assertEquals("232.0","" + ((Double)l_paramsValue.getCalled(16)[3]).toString());
            assertEquals("1","" + ((EqTypeExecutionConditionType)l_paramsValue.getCalled(16)[4]).intValue());
            assertNull(((EqTypeExecutionConditionType)l_paramsValue.getCalled(16)[5]));
            assertEquals("0","" + ((String)l_paramsValue.getCalled(16)[6]));
            assertEquals("2","" + ((String)l_paramsValue.getCalled(16)[7]));
            assertNull(((String)l_paramsValue.getCalled(16)[8]));
            assertFalse(((Boolean)l_paramsValue.getCalled(16)[9]).booleanValue());
//            assertEquals(,((SubAccount)l_paramsValue.getCalled(10)[10]));
//            assertEquals(,((WEB3EquityTradedProduct)l_paramsValue.getCalled(10)[11]));
            assertEquals("1.0","" + ((Double)l_paramsValue.getCalled(16)[12]));
            assertTrue(((Boolean)l_paramsValue.getCalled(16)[13]).booleanValue());
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(16)[14]));
            assertEquals("0.0","" + ((Double)l_paramsValue.getCalled(16)[15]));
            assertFalse(((Boolean)l_paramsValue.getCalled(16)[16]).booleanValue());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "validatePTSOrder", new Class[]
                    { SubAccount.class, WEB3EquityNewCashBasedOrderSpec.class });

            assertEquals(WEB3GentradeSubAccount.class,((SubAccount)l_paramsValue1.getFirstCalled()[0]).getClass());
            assertEquals(WEB3EquityNewCashBasedOrderSpec.class,((WEB3EquityNewCashBasedOrderSpec)l_paramsValue1.getFirstCalled()[1]).getClass());
     
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                    new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class});
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue2.getFirstCalled()[0]).getClass());
            assertEquals(Object[].class,((Object[])l_paramsValue2.getFirstCalled()[1]).getClass());
            assertEquals(Object[].class,((Object[])l_paramsValue2.getFirstCalled()[2]).getClass());
            assertEquals(OrderTypeEnum.class,((OrderTypeEnum)l_paramsValue2.getFirstCalled()[3]).getClass());
            assertEquals(Boolean.class,((Boolean)l_paramsValue2.getFirstCalled()[4]).getClass());
            
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager", "submitNewCashBasedOrder", new Class[]
                    { SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class });
            
            assertEquals(WEB3GentradeSubAccount.class,((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getClass());
            assertEquals(WEB3EquityNewCashBasedOrderSpec.class,((EqTypeNewCashBasedOrderSpec)l_paramsValue3.getFirstCalled()[1]).getClass());
            assertEquals("5001",((Long)l_paramsValue3.getFirstCalled()[2]).toString());
            assertNull(((String)l_paramsValue3.getFirstCalled()[3]));
            assertTrue(((Boolean)l_paramsValue3.getFirstCalled()[4]).booleanValue());
            
            //���X�|���X.�X�V����
            assertEquals(WEB3DateUtility.formatDate(l_sellCompleteResponse.lastUpdatedTimestamp,"yyyyMMdd"),
                    "20070612");
            //���X�|���X.���ʔԍ�
            assertEquals("10", "" + l_sellCompleteResponse.orderActionId);
            //���X�|���X.�C���T�C�_�[�x���\���t���O
            assertFalse(l_sellCompleteResponse.insiderWarningFlag);
            //�s��R�[�h
            assertEquals("11", l_sellCompleteResponse.marketCode);
            // ���X�|���X.�����L������
            assertNull(l_sellCompleteResponse.expirationDate);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
