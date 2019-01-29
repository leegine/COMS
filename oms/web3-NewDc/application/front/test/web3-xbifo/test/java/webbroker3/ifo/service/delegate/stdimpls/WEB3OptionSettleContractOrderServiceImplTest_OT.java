head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionSettleContractOrderServiceImplTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionSettleContractOrderServiceImplTest.javaテスト
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/10 呉艶傑 周捷(中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （株価指数オプション返済注文サービス実装クラステスト）<BR>
 * 
 * @@author 呉艶傑 周捷
 * @@version 1.0
 */

public class WEB3OptionSettleContractOrderServiceImplTest_OT extends TestBaseForMock
{
	private WEB3OptionSettleContractOrderServiceImpl l_serviceImpl = new WEB3OptionSettleContractOrderServiceImpl();

    private WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();

    private WEB3OptionsCloseMarginConfirmResponse l_response = null;
    
    private WEB3OptionsCloseMarginCompleteRequest l_optionsRequest = new WEB3OptionsCloseMarginCompleteRequestForTest();
    
    private WEB3OptionsCloseMarginCompleteResponse l_optionsResponse = null;
    

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderServiceImplTest.class);
       
	public WEB3OptionSettleContractOrderServiceImplTest_OT(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		l_request = new WEB3OptionsCloseMarginConfirmRequest();	    
	    l_serviceImpl = new WEB3OptionSettleContractOrderServiceImpl(); 
	    TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
	}

	protected void tearDown() throws Exception
	{
	
		super.tearDown();
	}

	public void test_validateOrde_case0001()
	{
		final String STR_METHOD_NAME = "test_validateOrde_case0001()";
	    log.entering(TEST_START + STR_METHOD_NAME);
	    try
	    {
		    l_serviceImpl.validateOrder(l_request);
		    fail();	        	
		}
	    catch(WEB3BaseException l_ex)
	    {
	        log.debug(STR_METHOD_NAME, l_ex);
	        assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00184);
	        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

	    }
	    catch (Exception l_ex)
	    {
	         log.error(ERROR + l_ex.getMessage(), l_ex);
	         fail();
	 	}       

	}
	
	public void test_validateOrde_case0002()
	{
		final String STR_METHOD_NAME = "test_validateOrde_case0002()";
	    log.entering(TEST_START + STR_METHOD_NAME);
	    try
	    {
            Date l_datBusinessTime = WEB3DateUtility.getDate(
                    "2004070710:00:00",
                    "yyyyMMddHH:mm:ss");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdates", new Timestamp(l_datBusinessTime.getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp", new Timestamp(l_datBusinessTime.getTime()));
            
	    	TestDBUtility.deleteAll(IfoContractParams.TYPE);
	    	IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
	    	l_ifoContractParams.setContractId(1005);
	    	l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setProductId(1006169090018L);
	    	TestDBUtility.insertWithDel(l_ifoContractParams);
	            
	    	TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	    	IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	    	l_ifoOrderUnitParams.setOrderId(1005);
	    	l_ifoOrderUnitParams.setClosingOrder("0");
	    	l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
	    	l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
	    	TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	    	TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	    	SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
	    	
	    	TestDBUtility.deleteAll(ProductRow.TYPE);
	    	ProductParams l_productParams = TestDBUtility.getProductRow();
	    	l_productParams.setProductId(l_ifoContractParams.getProductId());
	    	l_productParams.setProductType(ProductTypeEnum.IFO);
	    	TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_insParams);
	    	
	    	TestDBUtility.deleteAll(SubAccountRow.TYPE);
	    	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
	    	l_subAccountParams.setAccountId(101001010010L);
	    	l_subAccountParams.setSubAccountId(10100101001007L);
	    	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
	    	TestDBUtility.insertWithDel(l_subAccountParams);
	    	
	    	TestDBUtility.deleteAll(MainAccountRow.TYPE);
	    	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
	    	l_mainAccountParams.setAccountId(101001010010L);
	    	l_mainAccountParams.setHtSettlementWay("1");
	    	l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
	    	TestDBUtility.insertWithDel(l_mainAccountParams);
	    	
	    	TestDBUtility.deleteAll(IfoProductRow.TYPE);
	    	IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
	    	l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
	    	l_ifoProductParams.setProductCode("149081018");
	    	l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
	    	l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
	    	l_ifoProductParams.setUnderlyingProductCode("0");
	    	TestDBUtility.insertWithDel(l_ifoProductParams);
	    	
	    	TestDBUtility.deleteAll(MarketRow.TYPE);
	    	MarketParams l_marketParams = TestDBUtility.getMarketRow();
	    	l_marketParams.setInstitutionCode("10");
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
	    	l_ifoTradedProductParams.setValidForBizDate(null);
	    	TestDBUtility.insertWithDel(l_ifoTradedProductParams);
	    	
	    	TestDBUtility.deleteAll(BranchRow.TYPE);
	        BranchParams l_branchParams = TestDBUtility.getBranchRow();
	        l_branchParams.setInstitutionCode("10");
	        l_branchParams.setBranchId(33381);
	        l_branchParams.setBranchCode("387");
	        TestDBUtility.insertWithDel(l_branchParams);
            
	    	TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
	    	EnableOrderConditionParams l_enableOrderConditionParams =
	    		TestDBUtility.getEnableOrderConditionRow();
	    	l_enableOrderConditionParams.setInstitutionCode("10");
	    	l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
	    	l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
	        l_enableOrderConditionParams.setMarginTradingDiv("0");
	        l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
	    	TestDBUtility.insertWithDel(l_enableOrderConditionParams);
    	
	    	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl", 
            		"createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_eqOrderEntry);    
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            SettleContractEntry l_entry = new SettleContractEntry(l_ifoContractParams.getContractId(), 10D);
            l_eqOrderEntry[0] = l_entry;
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
                        
            WEB3IfoEstimateDeliveryAmountCalcResult l_sadresult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_sadresult.setCalcUnitPrice(10);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                    l_sadresult);

            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            
            this.setMockMethod();
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            this.l_request.orderPriceDiv = "0";
            this.l_request.execCondType = "1";
            this.l_request.expirationDateType = "2";
            this.l_request.expirationDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            this.l_request.orderCondType = "0";
            this.l_request.opOrderQuantity = "10";
            this.l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            this.l_request.closeMarginContractUnits[0].id = "1005";
            this.l_response = this.l_serviceImpl.validateOrder(this.l_request);
            
           
	    }
	    
	    catch(WEB3BaseException l_ex)
	    { 
	        log.debug(STR_METHOD_NAME, l_ex);
	        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	        log.exiting(STR_METHOD_NAME);
	        assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00275);

	    }
	    catch (Exception l_ex)
	    {
	         log.error(ERROR + l_ex.getMessage(), l_ex);
	         fail();
	 	}   
   		
	}
	
	public void test_validateOrde_case0003()
	{
		final String STR_METHOD_NAME = "test_validateOrde_case0003()";
	    log.entering(TEST_START + STR_METHOD_NAME);
	    try
	    {
            Date l_datBusinessTime = WEB3DateUtility.getDate(
                    "2004070710:00:00",
                    "yyyyMMddHH:mm:ss");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdates", new Timestamp(l_datBusinessTime.getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp", new Timestamp(l_datBusinessTime.getTime()));
            

	    	TestDBUtility.deleteAll(IfoContractParams.TYPE);
	    	
	    	IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
	    	l_ifoContractParams.setContractId(1005);
	    	l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setProductId(1006169090018L);
	    	TestDBUtility.insertWithDel(l_ifoContractParams);
	            
	    	TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	    	IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	    	l_ifoOrderUnitParams.setOrderId(1005);
	    	l_ifoOrderUnitParams.setClosingOrder("0");
	    	l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
	    	l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
	    	TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	    	TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	    	SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
	    	
	    	TestDBUtility.deleteAll(ProductRow.TYPE);
	    	ProductParams l_productParams = TestDBUtility.getProductRow();
	    	l_productParams.setProductId(l_ifoContractParams.getProductId());
	    	l_productParams.setProductType(ProductTypeEnum.IFO);
	    	TestDBUtility.insertWithDel(l_productParams);
	    	
	    	TestDBUtility.deleteAll(SubAccountRow.TYPE);
	    	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
	    	l_subAccountParams.setAccountId(101001010010L);
	    	l_subAccountParams.setSubAccountId(10100101001007L);
	    	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
	    	TestDBUtility.insertWithDel(l_subAccountParams);
	    	
	    	TestDBUtility.deleteAll(MainAccountRow.TYPE);
	    	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
	    	l_mainAccountParams.setAccountId(101001010010L);
	    	l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
	    	TestDBUtility.insertWithDel(l_mainAccountParams);
	    	
	    	TestDBUtility.deleteAll(IfoProductRow.TYPE);
	    	IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
	    	l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
	    	l_ifoProductParams.setProductCode("149081018");
	    	l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
	    	l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
	    	l_ifoProductParams.setUnderlyingProductCode("0");
	    	TestDBUtility.insertWithDel(l_ifoProductParams);
	    	
	    	TestDBUtility.deleteAll(MarketRow.TYPE);
	    	MarketParams l_marketParams = TestDBUtility.getMarketRow();
	    	l_marketParams.setInstitutionCode("10");
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
	    	l_ifoTradedProductParams.setValidForBizDate(null);
	    	TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_insParams);
	    	
	    	TestDBUtility.deleteAll(BranchRow.TYPE);
	        BranchParams l_branchParams = TestDBUtility.getBranchRow();
	        l_branchParams.setInstitutionCode("10");
	        l_branchParams.setBranchId(33381);
	        l_branchParams.setBranchCode("387");
	        TestDBUtility.insertWithDel(l_branchParams);
            
	    	TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
	    	EnableOrderConditionParams l_enableOrderConditionParams =
	    		TestDBUtility.getEnableOrderConditionRow();
	    	l_enableOrderConditionParams.setInstitutionCode("10");
	    	l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
	    	l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
	        l_enableOrderConditionParams.setMarginTradingDiv("0");
	        l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
	    	TestDBUtility.insertWithDel(l_enableOrderConditionParams);
    	
	    	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl", 
            		"createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_eqOrderEntry);    
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            SettleContractEntry l_entry = new SettleContractEntry(l_ifoContractParams.getContractId(), 10D);
            l_eqOrderEntry[0] = l_entry;
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
                        
            WEB3IfoEstimateDeliveryAmountCalcResult l_sadresult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_sadresult.setCalcUnitPrice(10);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                    l_sadresult);

            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            
            this.setMockMethod();
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            this.l_request.orderPriceDiv = "0";
            this.l_request.execCondType = "1";
            this.l_request.expirationDateType = "2";
            this.l_request.expirationDate = WEB3DateUtility.getDate("20040716","yyyyMMdd");
            this.l_request.orderCondType = "0";
            this.l_request.opOrderQuantity = "10";
            this.l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            this.l_request.closeMarginContractUnits[0].id = "1005";
            this.l_response = this.l_serviceImpl.validateOrder(this.l_request);
            
            assertEquals("1005",l_response.contractUnits[0].id);
            assertEquals("20040702",WEB3DateUtility.formatDate(l_response.contractUnits[0].openDate,"yyyyMMdd"));
            assertEquals("3720",l_response.contractUnits[0].contractPrice);
            assertEquals("1",l_response.contractUnits[0].contractQuantity);
            assertEquals("1050000",l_response.contractUnits[0].contractCommission);
            assertEquals("-18560000000",l_response.contractUnits[0].income);
            assertEquals("-18561050000",l_response.contractUnits[0].incomeCost);
            assertEquals("500",l_response.contractUnits[0].contractOrderQuantity);
            assertEquals(null,l_response.contractUnits[0].contractExecQuantity);
            assertEquals("1",l_response.contractUnits[0].settlePriority);
            assertEquals(null,l_response.contractUnits[0].sessionType);
            
	    }
	    
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.error("", l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    		
	}
		
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
        	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123L));
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(101001010010L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L,10100101001007L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, SubAccountTypeEnum.class },
                    l_subAccount);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("10");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123456L));
            
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_settleContractOrderEntries);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getWeb3GenBranch",
                    new Class[] {},
                    l_branch);
            
            NewOrderValidationResult l_result = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT,123);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class,String.class,String.class,String.class},
                    WEB3DateUtility.getDate("20040716","yyyyMMdd"));
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /****************************************************************************************************/    
    /****************************************************************************************************/    
    /********************************************zhou------jie********************************************/    
    /****************************************************************************************************/    
    /****************************************************************************************************/    
    
//    private WEB3OptionSettleContractOrderServiceImpl l_serviceImpl = null;
//
//    private WEB3OptionsCloseMarginCompleteRequest l_optionsRequest = null;
//
//    private WEB3OptionsCloseMarginCompleteResponse l_optionsResponse = null;
//    private long l_lngContractId;
    
    /**
     * 1.1.validate( )????儘乕???
     */
    public void test_submitOrder_case1()
    {
        final String STR_METHOD_NAME = "test_submitOrder_case1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_optionsRequest = new WEB3OptionsCloseMarginCompleteRequest();
            l_serviceImpl.submitOrder(l_optionsRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_submitOrder_case2()
    {
        final String STR_METHOD_NAME = "test_submitOrder_case2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_optionsRequest.orderPriceDiv = "1";
        l_optionsRequest.limitPrice = "100";
        l_optionsRequest.expirationDateType = "1";
        l_optionsRequest.wlimitExecCondType = "1";
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "1005";
        WEB3FuturesOptionsCloseMarginContractUnit l_units[] = {l_unit};
        l_optionsRequest.closeMarginContractUnits = l_units;
        l_optionsRequest.orderId = null;

        try
        {
//            initData();
            setMockMethodZJ();
            
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512266L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512266L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setUnderlyingProductCode("0");
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
            l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setValidForBizDate(WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            NewOrderValidationResult l_result =
                new NewOrderValidationResult(
                    ProcessingResult.newSuccessResultInstance());
           
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                l_result);
            
            this.l_optionsResponse = this.l_serviceImpl.submitOrder(this.l_optionsRequest);
            assertNotNull(l_optionsResponse.orderActionId);
            log.debug("l_optionsResponse.orderActionId>>>>>>" + l_optionsResponse.orderActionId);
            log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.pass!!!");
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_submitOrder_case3()
    {
        final String STR_METHOD_NAME = "test_submitOrder_case3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_optionsRequest.orderPriceDiv = "1";
        l_optionsRequest.limitPrice = "100";
        l_optionsRequest.expirationDateType = "1";
        l_optionsRequest.wlimitExecCondType = "1";
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "1005";
        WEB3FuturesOptionsCloseMarginContractUnit l_units[] = {l_unit};
        l_optionsRequest.closeMarginContractUnits = l_units;
        l_optionsRequest.orderId = "1001";

        try
        {
//            initData();
            setMockMethodZJ();
            
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512266L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512266L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setUnderlyingProductCode("0");
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
            l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setValidForBizDate(WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            NewOrderValidationResult l_result =
                new NewOrderValidationResult(
                    ProcessingResult.newSuccessResultInstance());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                l_result);
            
            this.l_optionsResponse = this.l_serviceImpl.submitOrder(this.l_optionsRequest);
            assertEquals(l_optionsResponse.orderActionId, "1001");
            log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.pass!!!");
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_submitOrder_case4()
    {
        final String STR_METHOD_NAME = "test_submitOrder_case4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_optionsRequest.orderPriceDiv = "1";
        l_optionsRequest.limitPrice = "100";
        l_optionsRequest.expirationDateType = "1";
        l_optionsRequest.wlimitExecCondType = "1";
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_unit.id = "555";
        WEB3FuturesOptionsCloseMarginContractUnit l_units[] = {l_unit};
        l_optionsRequest.closeMarginContractUnits = l_units;

        try
        {
            initData();
            setMockMethodZJ();
            
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
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512266L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512266L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setUnderlyingProductCode("0");
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
            l_ifoTradedProductParams.setValidForBizDate(
                WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductUpdqParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductUpdqParams.setValidForBizDate(
                WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            log.debug(">>>>>>>>>" + WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            NewOrderValidationResult l_result =
                new NewOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00184));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateSettleContractOrder", 
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                l_result);
            
            this.l_optionsResponse = this.l_serviceImpl.submitOrder(this.l_optionsRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512266L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220366L);
            l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512266L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33387);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            this.setExpectedDate(new Date(),"1");

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockMethodZJ()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(333812512266L));
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "orderChannel");
            
            SettleContractEntry l_eqOrderEntry = new SettleContractEntry(1005L, 100D);
            SettleContractEntry[] l_eqOrderEntrys = new SettleContractEntry[]{l_eqOrderEntry};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntrys);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                "getExpirationDate", 
                new Class[]{Date.class, String.class, String.class, String.class},
                new Date("2004/07/16"));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                "calcEstimateDeliveryAmount", 
                new Class[]{WEB3GentradeCommission.class, 
                    double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class},
                l_ifoEstimateDeliveryAmountCalcResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                "submitSettleContractOrder", 
                new Class[]{SubAccount.class,
                    IfoSettleContractOrderSpec.class, long.class,
                    String.class, boolean.class},
                new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "reCalcTradingPower", 
                new Class[]{WEB3GentradeSubAccount.class},
                null);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);  
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3OptionsCloseMarginCompleteRequestForTest extends WEB3OptionsCloseMarginCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3OptionsCloseMarginCompleteRequestForTest.validate()");
        }
    }
}
@
