head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeClosingContractServiceImplTest_OT.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済確認リクエスト(WEB3OptionsCloseMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/06/11 侯翠ナ 張少傑 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.TradingTimeParams;
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
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * @@author 侯翠ナ 張少傑
 * @@version 1.0 
 */
public class WEB3OptionChangeClosingContractServiceImplTest_OT extends TestBaseForMock
{

    private WEB3OptionsCloseMarginChangeCompleteResponse l_completeResponse = null;
    
	WEB3OptionChangeClosingContractServiceImpl l_impl = 
		new WEB3OptionChangeClosingContractServiceImpl();
	
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionChangeClosingContractServiceImpl.class);
    
	public WEB3OptionChangeClosingContractServiceImplTest_OT(String arg0) {
		super(arg0);
	}
	protected void setUp() throws Exception {
		super.setUp();
		TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * this.注文単価区分がnullの値であれば例外をスローする。
	 */
	public void testvalidateOrder_case001()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case001()";
		WEB3OptionsCloseMarginChangeConfirmRequest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequest();
		try
		{
			l_impl.validateOrder(l_request);
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00184);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 決済順序 ! = 0：ランダム and リクエストデータ .注文数量==0の場合、例外をスローする。
	 */
	public void testvalidateOrder_case002()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case002()";
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = "0";
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_request.closeMarginContractUnits[0] = l_unit;
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("1");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
			l_impl.validateOrder(l_request);
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00074);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 決済順序 ! = 0：ランダム and リクエストデータ .注文数量==nullの場合、例外をスローする。
	 */
	public void testvalidateOrder_case003()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case003()";
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = null;
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_request.closeMarginContractUnits[0] = l_unit;
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("1");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
			l_impl.validateOrder(l_request);
			fail();
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00074);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 返済建玉[0].数量<返済約定済数量の場合、例外をスローする。
	 */
	public void testvalidateOrder_case004()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case004()";
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = "0";
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_unit.id = "2008";
	        l_request.closeMarginContractUnits[0] = l_unit;
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("0");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
			l_impl.validateOrder(l_request);
			fail();
		}
		catch(WEB3MockObjectRuntimeException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
                assertEquals(1001L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
                assertEquals(0.0,((Double)l_paramsValue1.getFirstCalled()[1]).doubleValue(),0);
                assertEquals("2008",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue1.getFirstCalled()[2])[0].id);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 注文有効状態のチェック
	 * 注文有効状態がOPEN以外の場合は訂正不可とし、例外をスローする。
	 */
	public void testvalidateOrder_case005()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case005()";
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
        
        log.entering(STR_METHOD_NAME);
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = "0";
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_unit.id = "2008";
	        l_request.closeMarginContractUnits[0] = l_unit;
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("0");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
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
	        
	        SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[0];
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_eqOrderEntry);
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1234L));
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
			l_impl.validateOrder(l_request);
			fail();
		}
		catch(WEB3MockObjectRuntimeException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateChangeSettleContractOrder", 
                    new Class[]{WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class});
            assertEquals(false,((WEB3IfoChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
            assertEquals(1001L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            assertEquals(0.0,((Double)l_paramsValue2.getFirstCalled()[1]).doubleValue(),0);
            assertEquals("2008",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue2.getFirstCalled()[2])[0].id);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		}
		catch(Exception l_ex)
		{
			l_ex.printStackTrace();
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}
	}
	
	/**
	 * 正常終了<BR>
	 * 返済建玉一條<BR>
	 * 訂正失効日
	 * リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値 
	 */
	public void testvalidateOrder_case006()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case006()";
        log.entering(TEST_START + STR_METHOD_NAME);
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
        
        
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = "0";
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_unit.id = "1001";
	        l_request.closeMarginContractUnits[0] = l_unit;
	        TestDBUtility.deleteAll(CalendarParams.TYPE);
	        SystemPreferencesParams l_sys = TestDBUtility.getSystemPreferencesRow();
	        l_sys.setValue("20040707");
	        TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("0");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("10");
            l_tradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.commit();
	        SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
	        SettleContractEntry l_eqOrderEntry1 = new SettleContractEntry(1001,8001);
	        l_eqOrderEntry[0] = l_eqOrderEntry1;
	        
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_eqOrderEntry);
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1234L));
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(10D);
            l_calcResult.setEstimateDeliveryAmount(200D);
            l_calcResult.setCommissionCourse("02");
            l_calcResult.setCommission(260D);
            l_calcResult.setCommissionTax(35.5D);
            l_calcResult.setCalcUnitPrice(50.2D);
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
            

  
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20040712", "yyyyMMdd"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new OrderValidationResult(
                            ProcessingResult.newSuccessResultInstance()));
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
            		                    "1");
            
            
            String[] l_strValues = new String[2];
            l_strValues[0] = "1";
            l_strValues[1] = "2";
            String l_strProductDiv = "2";
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, l_strProductDiv);
            Date l_datBusinessTime = WEB3DateUtility.getDate(
                "2004070714:58:00",
                "yyyyMMddHH:mm:ss");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBusinessTime);
            WEB3GentradeTradingTimeManagementForMock.setTimestampTag(new Timestamp(l_datBusinessTime.getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"xblocks.gtl.attributes.systemtimestamp", new Timestamp(l_datBusinessTime.getTime()));

            
            WEB3OptionsCloseMarginChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals("1001",l_response.contractUnits[0].id);
            // 概算受渡代金
            assertEquals(WEB3StringTypeUtility.formatNumber(200), l_response.estimatedPrice);
            // 手数料コース
            assertEquals("02", l_response.commissionCourse);
            // 手数料
            assertEquals(WEB3StringTypeUtility.formatNumber(260), l_response.commission);
            // 手数料消費税
            assertEquals(WEB3StringTypeUtility.formatNumber(35.5), l_response.commissionConsumptionTax);
            assertEquals("2",l_response.messageSuspension[0]);
            assertEquals(WEB3StringTypeUtility.formatNumber(50.2), l_response.checkPrice);
            Date l_datBusinessdate = WEB3DateUtility.getDate(
                    "20040707",
                    "yyyyMMdd");
            assertEquals(l_datBusinessdate,l_response.checkDate);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"),"20040707");
		}
		catch(Exception l_ex)
		{
			l_ex.printStackTrace();
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 正常終了
	 * 返済建玉0條
	 */
	public void testvalidateOrder_case007()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case007()";
        log.entering(TEST_START + STR_METHOD_NAME);
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "1";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = "0";
	        
	        l_request.execCondType = "1";
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_unit.id = "1001";
	        l_request.closeMarginContractUnits[0] = l_unit;
	        TestDBUtility.deleteAll(CalendarParams.TYPE);
	        SystemPreferencesParams l_sys = TestDBUtility.getSystemPreferencesRow();
	        l_sys.setValue("20040707");
	        TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("0");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("10");
            l_tradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
	        
	        SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[0];	        
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_eqOrderEntry);
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1234L));
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(10D);
            l_calcResult.setEstimateDeliveryAmount(200D);
            l_calcResult.setCommissionCourse("02");
            l_calcResult.setCommission(260D);
            l_calcResult.setCommissionTax(35.5D);
            l_calcResult.setCalcUnitPrice(50.2D);
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
            

  
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20040712", "yyyyMMdd"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new OrderValidationResult(
                            ProcessingResult.newSuccessResultInstance()));
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
            		                    "1");
            
            
            String[] l_strValues = new String[2];
            l_strValues[0] = "1";
            l_strValues[1] = "2";
            String l_strProductDiv = "2";
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, l_strProductDiv);
            Date l_datBusinessTime = WEB3DateUtility.getDate(
                "2004070714:58:00",
                "yyyyMMddHH:mm:ss");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBusinessTime);
            WEB3GentradeTradingTimeManagementForMock.setTimestampTag(new Timestamp(l_datBusinessTime.getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"xblocks.gtl.attributes.systemtimestamp", new Timestamp(l_datBusinessTime.getTime()));

            WEB3OptionsCloseMarginChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals(0,l_response.contractUnits.length);
            // 概算受渡代金
            assertEquals(WEB3StringTypeUtility.formatNumber(200), l_response.estimatedPrice);
            // 手数料コース
            assertEquals("02", l_response.commissionCourse);
            // 手数料
            assertEquals(WEB3StringTypeUtility.formatNumber(260), l_response.commission);
            // 手数料消費税
            assertEquals(WEB3StringTypeUtility.formatNumber(35.5), l_response.commissionConsumptionTax);
            assertEquals("2",l_response.messageSuspension[0]);
            assertEquals(WEB3StringTypeUtility.formatNumber(50.2), l_response.checkPrice);
            Date l_datBusinessdate = WEB3DateUtility.getDate(
                    "20040707",
                    "yyyyMMdd");
            assertEquals(l_datBusinessdate,l_response.checkDate);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"),"20040707");
		}
		catch(Exception l_ex)
		{
			l_ex.printStackTrace();
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	
	/**
	 * 正常終了
	 * 返済建玉多條
	 * 訂正失効日<BR>
	 * リクエストデータ.注文有効期限 != nullの場合、<BR>
	 * OP注文マネージャ.get注文有効期限(リクエストデータ.注文有効期限,先物OP銘柄.銘柄コード,市場.getMarketCode(),”オプション”)の戻り値<BR> 
	 */
	public void testvalidateOrder_case008()
	{
		final String STR_METHOD_NAME = "testvalidateOrder_case008()";
        log.entering(TEST_START + STR_METHOD_NAME);
		WEB3OptionsCloseMarginChangeConfirmRequestForTest l_request = 
			new WEB3OptionsCloseMarginChangeConfirmRequestForTest();
		try
		{
            // 注文単価区分
	        l_request.orderPriceDiv = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        //注文期限区分
	        l_request.expirationDateType = "2";
	        // 発注条件区分
	        l_request.orderCondType = "0";
	        
	        l_request.id = "1001";
	        // 注文数量
	        l_request.opOrderQuantity = "0";
	        // 執行条件
	        l_request.execCondType = "1";
	        // 注文有効期限
	        l_request.expirationDate = WEB3DateUtility.getDate("20071122", "yyyyMMdd");
            // 返済建玉
	        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[3];
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit2 = new WEB3FuturesOptionsCloseMarginContractUnit();
	        WEB3FuturesOptionsCloseMarginContractUnit l_unit3 = new WEB3FuturesOptionsCloseMarginContractUnit();
	        l_unit1.id = "1001";
	        l_unit2.id = "1002";
	        l_unit3.id = "1003";
	        l_request.closeMarginContractUnits[0] = l_unit1;
	        l_request.closeMarginContractUnits[1] = l_unit2;
	        l_request.closeMarginContractUnits[2] = l_unit3;
	        TestDBUtility.deleteAll(CalendarParams.TYPE);
	        SystemPreferencesParams l_sys = TestDBUtility.getSystemPreferencesRow();
	        l_sys.setValue("20040707");
	        TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams2.setContractId(1002);
            l_ifoContractParams2.setContractPrice(230);
            TestDBUtility.insertWithDel(l_ifoContractParams2);
            IfoContractParams l_ifoContractParams3 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams3.setContractId(1003);
            l_ifoContractParams3.setContractPrice(330);
            l_ifoContractParams3.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams3);
	        
	        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
	        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
	        l_ifoOrderUnitParams.setClosingOrder("0");
	        TestDBUtility.deleteAll(IfoOrderRow.TYPE);
	        l_ifoOrderUnitParams.setProductId(l_ifoContractParams1.getProductId());
	        TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
	        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
	        l_ifoOrderParams.setOrderId(1001);
	        
	        TestDBUtility.insertWithDel(l_ifoOrderParams);
	        TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
	        
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams1.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams1.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            l_productParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("10");
            l_tradedProductParams.setProductId(l_ifoContractParams1.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams1.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams1.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams1.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams1.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
	        
	        SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[3];
	        SettleContractEntry l_eqOrderEntry1 = new SettleContractEntry(1001,8001);
	        SettleContractEntry l_eqOrderEntry2 = new SettleContractEntry(1002,8001);
	        SettleContractEntry l_eqOrderEntry3 = new SettleContractEntry(1003,8001);
	        l_eqOrderEntry[0] = l_eqOrderEntry1;
	        l_eqOrderEntry[1] = l_eqOrderEntry2;
	        l_eqOrderEntry[2] = l_eqOrderEntry3;
	        
	        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            		"webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_eqOrderEntry);
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(1234L));
	        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCalcUnitPrice(10D);
            l_calcResult.setEstimateDeliveryAmount(200D);
            l_calcResult.setCommissionCourse("02");
            l_calcResult.setCommission(260D);
            l_calcResult.setCommissionTax(35.5D);
            l_calcResult.setCalcUnitPrice(50.2D);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateChangeSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class},
                    new OrderValidationResult(
                            ProcessingResult.newSuccessResultInstance()));
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
            		                    "1");
            
            String[] l_strValues = new String[2];
            l_strValues[0] = "1";
            l_strValues[1] = "2";
            String l_strProductDiv = "2";
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, l_strProductDiv);
            Date l_datBusinessTime = WEB3DateUtility.getDate(
                "2004070714:58:00",
                "yyyyMMddHH:mm:ss");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datBusinessTime);
            WEB3GentradeTradingTimeManagementForMock.setTimestampTag(new Timestamp(l_datBusinessTime.getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("1");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
            	"xblocks.gtl.attributes.systemtimestamp", new Timestamp(l_datBusinessTime.getTime()));

            
            WEB3OptionsCloseMarginChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals("1001",l_response.contractUnits[0].id);
            assertEquals("1002",l_response.contractUnits[1].id);
            assertEquals("1003",l_response.contractUnits[2].id);
            // 概算受渡代金
            assertEquals(WEB3StringTypeUtility.formatNumber(200), l_response.estimatedPrice);
            // 手数料コース
            assertEquals("02", l_response.commissionCourse);
            // 手数料
            assertEquals(WEB3StringTypeUtility.formatNumber(260), l_response.commission);
            // 手数料消費税
            assertEquals(WEB3StringTypeUtility.formatNumber(35.5), l_response.commissionConsumptionTax);
            assertEquals("2",l_response.messageSuspension[0]);
            assertEquals(WEB3StringTypeUtility.formatNumber(50.2), l_response.checkPrice);
            Date l_datBusinessdate = WEB3DateUtility.getDate(
                    "20040707",
                    "yyyyMMdd");
            assertEquals(l_datBusinessdate,l_response.checkDate);
            assertEquals("20071122",WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
		}
		catch(Exception l_ex)
		{
			l_ex.printStackTrace();
			fail();
			log.error(ERROR + l_ex.getMessage(), l_ex);
		}
	}
	protected class WEB3OptionsCloseMarginChangeConfirmRequestForTest extends WEB3OptionsCloseMarginChangeConfirmRequest
	{
		 public void validate() throws WEB3BaseException 
		 {
			 
		 }
	}
    

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractServiceImpl.submitOrder(WEB3OptionsCloseMarginChangeCompleteRequest)'
     */
    public void testSubmitOrder_case001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            WEB3OptionsCloseMarginChangeCompleteRequest l_request =
                new WEB3OptionsCloseMarginChangeCompleteRequest();
            l_impl.submitOrder(l_request);
            fail();   
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    public void testSubmitOrder_case002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp", null);
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", null);
            
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            WEB3OptionsCloseMarginChangeCompleteRequest l_request =
                new WEB3OptionsCloseMarginChangeCompleteRequest();
            //注文数量
            l_request.opOrderQuantity = "2";
            
            //注文単価区分
            l_request.orderPriceDiv = "0";
            
            //注文単価
            l_request.limitPrice = null;
            
            //執行条件
            l_request.execCondType = "1";
            
            //注文期限区分
            l_request.expirationDateType = "1";
            
            //注文有効期限
            l_request.expirationDate = null;
            
            //発注条件区分
            l_request.orderCondType = "0";
            
            //逆指値用プレミアム/原資産価格
            l_request.stopPremium_underlyingAssets = null;
            
            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;
            
            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;
            
            //W指値用プレミアム/原資産価格
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W指値用発注条件単価
            l_request.wlimitOrderCondPrice = null;
            
            //W指値用発注条件演算子
            l_request.wlimitOrderCondOperator = null;
            
            //W指値用注文単価区分
            l_request.wLimitOrderPriceDiv = null;
            
            //W指値用注文単価
            l_request.wLimitPrice = null;
            
            //W指値用執行条件
            l_request.wlimitExecCondType = null;
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = null;
            
            //ＩＤ
            l_request.id = "1001";
            
            //確認時単価
            l_request.checkPrice = "100";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("20040101","yyyyMMdd");
            
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.id = "10011";
            l_futuresOptionsCloseMarginContractUnit.settlePriority = "1";
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "100";
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = 
            {l_futuresOptionsCloseMarginContractUnit};
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
      
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoContractParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoContractParams.setContractId(10011L);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));
            
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            l_impl.submitOrder(l_request);
            fail();   
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue1 =TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "validateChangeSettleContractOrder",
                new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class });
            assertEquals(false, ((WEB3IfoChangeSettleContractOrderSpec)l_paramsValue1.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    public void testSubmitOrder_case003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            WEB3OptionsCloseMarginChangeCompleteRequest l_request =
                new WEB3OptionsCloseMarginChangeCompleteRequest();
            //注文数量
            l_request.opOrderQuantity = "2";
            
            //注文単価区分
            l_request.orderPriceDiv = "0";
            
            //注文単価
            l_request.limitPrice = null;
            
            //執行条件
            l_request.execCondType = "1";
            
            //注文期限区分
            l_request.expirationDateType = "1";
            
            //注文有効期限
            l_request.expirationDate = null;
            
            //発注条件区分
            l_request.orderCondType = "0";
            
            //逆指値用プレミアム/原資産価格
            l_request.stopPremium_underlyingAssets = null;
            
            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;
            
            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;
            
            //W指値用プレミアム/原資産価格
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W指値用発注条件単価
            l_request.wlimitOrderCondPrice = null;
            
            //W指値用発注条件演算子
            l_request.wlimitOrderCondOperator = null;
            
            //W指値用注文単価区分
            l_request.wLimitOrderPriceDiv = null;
            
            //W指値用注文単価
            l_request.wLimitPrice = null;
            
            //W指値用執行条件
            l_request.wlimitExecCondType = null;
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = null;
            
            //ＩＤ
            l_request.id = "1001";
            
            //確認時単価
            l_request.checkPrice = "100";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("19700101","yyyyMMdd");
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "1003";
            l_closeMarginContractUnit.contractOrderQuantity = "2";
            l_closeMarginContractUnit.settlePriority = "0";
            l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;

      
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoContractParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoContractParams.setContractId(1003L);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams  l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams  l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams  l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductId(1006169090018L);     
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("19700101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams  l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setValidForBizDate("19700101");
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("10");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20040917));
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateChangeSettleContractOrder",
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
//                    "getExpirationDate",
//                    new Class[] {Date.class, String.class, String.class, String.class},
//                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
//            
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
            
            this.l_completeResponse = l_impl.submitOrder(l_request);

            assertEquals(0, WEB3DateUtility.compareToDay(
                    this.l_completeResponse.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));

            assertTrue(this.l_completeResponse.succSettingFlag);
  
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitOrder_case004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            WEB3OptionsCloseMarginChangeCompleteRequest l_request =
                new WEB3OptionsCloseMarginChangeCompleteRequest();
            //注文数量
            l_request.opOrderQuantity = "2";
            
            //注文単価区分
            l_request.orderPriceDiv = "0";
            
            //注文単価
            l_request.limitPrice = null;
            
            //執行条件
            l_request.execCondType = "1";
            
            //注文期限区分
            l_request.expirationDateType = "1";
            
            //注文有効期限
            l_request.expirationDate = null;
            
            //発注条件区分
            l_request.orderCondType = "0";
            
            //逆指値用プレミアム/原資産価格
            l_request.stopPremium_underlyingAssets = null;
            
            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;
            
            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;
            
            //W指値用プレミアム/原資産価格
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W指値用発注条件単価
            l_request.wlimitOrderCondPrice = null;
            
            //W指値用発注条件演算子
            l_request.wlimitOrderCondOperator = null;
            
            //W指値用注文単価区分
            l_request.wLimitOrderPriceDiv = null;
            
            //W指値用注文単価
            l_request.wLimitPrice = null;
            
            //W指値用執行条件
            l_request.wlimitExecCondType = null;
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = null;
            
            //ＩＤ
            l_request.id = "1001";
            
            //確認時単価
            l_request.checkPrice = "100";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("19700101","yyyyMMdd");
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "1003";
            l_closeMarginContractUnit.contractOrderQuantity = "2";
            l_closeMarginContractUnit.settlePriority = "0";
            l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;

      
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoContractParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoContractParams.setContractId(1003L);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams  l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams  l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductId(1006169090018L);     
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("19700101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams  l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setValidForBizDate("19700101");
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("10");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
//            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20040917));
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateChangeSettleContractOrder",
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
//                    "getExpirationDate",
//                    new Class[] {Date.class, String.class, String.class, String.class},
//                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
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
            
            this.l_completeResponse = l_impl.submitOrder(l_request);

            assertEquals(0, WEB3DateUtility.compareToDay(
                    this.l_completeResponse.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));

            assertFalse(this.l_completeResponse.succSettingFlag);
  
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitOrder_case005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_case003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionChangeClosingContractServiceImpl l_impl =
                new WEB3OptionChangeClosingContractServiceImpl();
            WEB3OptionsCloseMarginChangeCompleteRequest l_request =
                new WEB3OptionsCloseMarginChangeCompleteRequest();
            //注文数量
            l_request.opOrderQuantity = "2";
            
            //注文単価区分
            l_request.orderPriceDiv = "0";
            
            //注文単価
            l_request.limitPrice = null;
            
            //執行条件
            l_request.execCondType = "1";
            
            //注文期限区分
            l_request.expirationDateType = "1";
            
            //注文有効期限
            l_request.expirationDate = null;
            
            //発注条件区分
            l_request.orderCondType = "0";
            
            //逆指値用プレミアム/原資産価格
            l_request.stopPremium_underlyingAssets = null;
            
            //逆指値用発注条件単価
            l_request.stopOrderCondPrice = null;
            
            //逆指値用発注条件演算子
            l_request.stopOrderCondOperator = null;
            
            //W指値用プレミアム/原資産価格
            l_request.wlimitPremium_underlyingAssets = null;
            
            //W指値用発注条件単価
            l_request.wlimitOrderCondPrice = null;
            
            //W指値用発注条件演算子
            l_request.wlimitOrderCondOperator = null;
            
            //W指値用注文単価区分
            l_request.wLimitOrderPriceDiv = null;
            
            //W指値用注文単価
            l_request.wLimitPrice = null;
            
            //W指値用執行条件
            l_request.wlimitExecCondType = null;
            
            //W指値用有効状態区分
            l_request.wlimitEnableStatusDiv = null;
            
            //ＩＤ
            l_request.id = "1001";
            
            //確認時単価
            l_request.checkPrice = "100";
            
            //確認時発注日
            l_request.checkDate = WEB3DateUtility.getDate("19700101","yyyyMMdd");
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_closeMarginContractUnit = 
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_closeMarginContractUnit.id = "1003";
            l_closeMarginContractUnit.contractOrderQuantity = "2";
            l_closeMarginContractUnit.settlePriority = "0";
            l_closeMarginContractUnits[0] = l_closeMarginContractUnit;
            l_request.closeMarginContractUnits = l_closeMarginContractUnits;

      
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoContractParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoContractParams.setContractId(1003L);
            l_ifoContractParams.setAccountId(333812512203L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            l_ifoOrderUnitParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams  l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams  l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductId(1006169090018L);     
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("19700101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams  l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setValidForBizDate("19700101");
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("10");
            l_ifoTradedProductUpdqParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(20040917));
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "createSettleContractEntry", 
                new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_eqOrderEntry);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateChangeSettleContractOrder",
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class },
                    new OrderValidationResult(
                        ProcessingResult.newSuccessResultInstance()));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
//                    "getExpirationDate",
//                    new Class[] {Date.class, String.class, String.class, String.class},
//                    WEB3DateUtility.getDate("20071122", "yyyyMMdd"));
            
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
            
            this.l_completeResponse = l_impl.submitOrder(l_request);

            assertEquals(0, WEB3DateUtility.compareToDay(
                    this.l_completeResponse.lastUpdatedTimestamp, GtlUtils.getSystemTimestamp()));

            assertFalse(this.l_completeResponse.succSettingFlag);
  
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    
}
@
