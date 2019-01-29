head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeClosingContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionChangeClosingContractServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 孫洪江 (中訊) 新規作成
Revision History : 2007/08/25 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionChangeClosingContractServiceImplTest extends TestBaseForMock
{
    private WEB3OptionsCloseMarginChangeConfirmRequest l_request = null;
    private WEB3OptionsCloseMarginChangeConfirmResponse l_response = null;
    private WEB3OptionChangeClosingContractServiceImpl l_service = null;
    
    private WEB3OptionsCloseMarginChangeCompleteRequest l_completeRequest = null; 
    private WEB3OptionsCloseMarginChangeCompleteResponse l_completeResponse = null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3OptionChangeClosingContractServiceImplTest.class);
    
    public WEB3OptionChangeClosingContractServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3OptionsCloseMarginChangeConfirmRequestForTest(); 
        this.l_service = new WEB3OptionChangeClosingContractServiceImplForTest();
        this.l_completeRequest = new WEB3OptionsCloseMarginChangeCompleteRequestForTest();
        
    }

    protected void tearDown() throws Exception
    {
        this.l_request = null;
        this.l_service = null;
        this.l_response = null;
        this.l_completeRequest = null;
        this.l_completeResponse = null;
        super.tearDown();
    }

    /*
     * 夕場前繰越対象フラグ：　@
     * 先物OPデータアダプタ.get夕場前繰越対象フラグ(リクエストデータ.注文期限区分、注文単位.部店ID
     */
    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "validateOrderTest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.id = "1005";
            l_request.execCondType = "1";
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[0];
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("2");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "555";
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            this.l_response = this.l_service.validateOrder(this.l_request);
            
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder", 
                new Class[] {WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class});
            assertEquals(false,((WEB3IfoChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
            assertEquals(1001L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            assertEquals(0,((Double)l_paramsValue2.getFirstCalled()[1]).doubleValue(),0);
            assertEquals("555",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue2.getFirstCalled()[2])[0].id);            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "validateOrderTest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.id = "1005";
            l_request.execCondType = "1";
            l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
            
            
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(555L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[0];
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("2");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.commit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "555";
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1234L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");

            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(10D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class,
                            double.class,
                            SideEnum.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class},
                            l_calcResult);
            
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            this.l_response = l_impl.validateOrder(this.l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"),"20071122");
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 返済建玉の要素數 = 1
     */
    public void testValidateOrder_C0003()
    {
        final String STR_METHOD_NAME = "validateOrderTest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.id = "1005";
            l_request.execCondType = "1";
            l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
            
            
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(555L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("1");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("2");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.commit();
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new SettleContractEntry(555L,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "555";
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateChangeSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1234L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");

            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(10D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[] {WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class,
                            double.class,
                            SideEnum.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class},
                            l_calcResult);
            
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            this.l_response = l_impl.validateOrder(this.l_request);

            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"),"20071122");
            assertEquals("114074100", l_response.contractUnits[0].contractCommission);
            assertEquals("-2016509594100", l_response.contractUnits[0].incomeCost);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private class WEB3OptionsCloseMarginChangeConfirmRequestForTest extends WEB3OptionsCloseMarginChangeConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3OptionsCloseMarginChangeConfirmRequestForTest.validate()");
        }
    }
    
    private class WEB3OptionChangeClosingContractServiceImplForTest extends WEB3OptionChangeClosingContractServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            try
            {
                return new WEB3GentradeSubAccount(333812512203L, 33381251220301L);
            }
            catch (DataQueryException e)
            {
                // TODO Auto-generated catch block
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, "");
            }
            catch (DataNetworkException e)
            {
                // TODO Auto-generated catch block
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, "");
            }
        }             
    }
    
    /*
     * 夕場前繰越対象フラグ：　@
     * 先物OPデータアダプタ.get夕場前繰越対象フラグ(リクエストデータ.注文期限区分、注文単位.部店ID
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);        
        try
        {
            this.l_completeRequest.id = "1001";
            this.l_completeRequest.expirationDateType = "1";
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
     
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            this.l_completeRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.l_completeRequest.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            this.l_completeRequest.closeMarginContractUnits[0].id = "22";
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue1 =TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class });
            assertEquals(false, ((WEB3IfoChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
            
            WEB3MockObjectParamsValue l_paramsValue2 =TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
            assertEquals(1001L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            assertEquals(0,((Double)l_paramsValue2.getFirstCalled()[1]).doubleValue(),0);
            assertEquals("22",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue2.getFirstCalled()[2])[0].id);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);        
        try
        {
            this.l_completeRequest.id = "1001";
            this.l_completeRequest.expirationDateType = "1";
            this.l_completeRequest.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
     
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            this.l_completeRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.l_completeRequest.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            this.l_completeRequest.closeMarginContractUnits[0].id = "22";
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getExpirationDate",
                new Class[] {Date.class, String.class, String.class, String.class},
                WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue1 =TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class });
            assertEquals(false, ((WEB3IfoChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
            
            WEB3MockObjectParamsValue l_paramsValue2 =TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
            assertEquals(1001L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            assertEquals(0,((Double)l_paramsValue2.getFirstCalled()[1]).doubleValue(),0);
            assertEquals("22",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue2.getFirstCalled()[2])[0].id);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * 予約注文確認要（is予約注文確認要() == true）の場合
         レスポンスデータにプロパティをセットする。
     */
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);        
        try
        {
            this.l_completeRequest.id = "1001";
            this.l_completeRequest.expirationDateType = "1";
            this.l_completeRequest.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "1003";
            l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
            this.l_completeRequest.closeMarginContractUnits = l_closeMarginContractUnits;
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
     
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TradedProductParams l_TradedProductParams = TestDBUtility.getTradedProductRow();
            l_TradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_TradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_IfoTradedProductParams.setTradedProductId(l_TradedProductParams.getTradedProductId());
            l_IfoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoContractParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoContractParams.setContractId(1003);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_TradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(l_ifoOrderUnitParams.getOrderId());
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                new OrderValidationResult(
                    ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getExpirationDate",
                new Class[] {Date.class, String.class, String.class, String.class},
                WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeSettleContractOrder", 
                    new Class[]{ SubAccount.class,
                        IfoChangeSettleContractOrderSpec.class,
                        String.class, boolean.class },
                        new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
 
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);

            assertEquals(0, WEB3DateUtility.compareToDay(
                    this.l_completeResponse.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));

            assertTrue(this.l_completeResponse.succSettingFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 予約注文確認要（is予約注文確認要() == false）の場合
         レスポンスデータにプロパティをセットする。
     */
    public void testSubmitOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);        
        try
        {
            this.l_completeRequest.id = "1001";
            this.l_completeRequest.expirationDateType = "1";
            this.l_completeRequest.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "1003";
            l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
            this.l_completeRequest.closeMarginContractUnits = l_closeMarginContractUnits;
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
     
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            
            TradedProductParams l_TradedProductParams = TestDBUtility.getTradedProductRow();
            l_TradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_TradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_IfoTradedProductParams.setTradedProductId(l_TradedProductParams.getTradedProductId());
            l_IfoTradedProductParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            
            TestDBUtility.insertWithDel(l_TradedProductParams);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoContractParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoContractParams.setContractId(1003);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_TradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(l_ifoOrderUnitParams.getOrderId());
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                new OrderValidationResult(
                    ProcessingResult.newSuccessResultInstance()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getExpirationDate",
                new Class[] {Date.class, String.class, String.class, String.class},
                WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(0.2D);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                        WEB3IfoTradedProductImpl.class, double.class, SideEnum.class, boolean.class, double.class,
                        double.class, boolean.class },
                        l_calcResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeSettleContractOrder", 
                    new Class[]{ SubAccount.class,
                        IfoChangeSettleContractOrderSpec.class,
                        String.class, boolean.class },
                        new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
 
            this.l_completeResponse = this.l_service.submitOrder(this.l_completeRequest);

            assertEquals(0, WEB3DateUtility.compareToDay(
                    this.l_completeResponse.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));

            assertFalse(this.l_completeResponse.succSettingFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3OptionsCloseMarginChangeCompleteRequestForTest extends WEB3OptionsCloseMarginChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3OptionsCloseMarginChangeCompleteRequestForTest.validate()");
        }
    }
}

@
