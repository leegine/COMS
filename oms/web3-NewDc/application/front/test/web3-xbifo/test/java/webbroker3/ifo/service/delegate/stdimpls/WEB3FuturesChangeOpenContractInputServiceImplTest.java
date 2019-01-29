head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesChangeOpenContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FuturesChangeOpenContractInputServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/27 金傑 (中訊) 新規作成 仕様変更モデルNo.705
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CarriedOrderDef;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.stdimpls.WEB3IfoQuoteDataImplForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesChangeOpenContractInputServiceImplTest extends TestBaseForMock
{
    private WEB3FuturesChangeOpenContractInputServiceImpl l_serviceImpl = null;
    
    private WEB3FuturesOpenMarginChangeInputRequest l_request = null;
    
    private WEB3FuturesOpenMarginChangeInputResponse l_response = null;
    
    private boolean l_blnIsMarketOrder = false;
    
    private boolean l_blnIsMockUseGetChangeOrderUnit = false;
    
    private boolean l_blnIsMockValidateOrder = false;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3FuturesChangeOpenContractInputServiceImplTest.class);
    
    public WEB3FuturesChangeOpenContractInputServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3FuturesChangeOpenContractInputServiceImplForTest();
        this.l_request = new WEB3FuturesOpenMarginChangeInputRequestForTest();
        this.initData();
        this.setMockMethod();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_request = null;
//        super.checkMethodValue();
        super.tearDown();
    }
    
    /**
     * 
     * set取引最終日を取得する
     * is出来るまで注文取扱可能 == false
     * is夕場まで注文 == false
     * 注文期限区分一覧 = "2"
     * 立会区分 = "2"
     * 注文期限区分 = "1"(当日限り)
     * 注文有効期限 = null
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setBizDate("20071202");
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setSessionType("2");
            l_ifoOrderUnitParams.setLimitPrice(0);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder",
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            this.l_request.id = "1005";
            this.l_response = (WEB3FuturesOpenMarginChangeInputResponse) this.l_serviceImpl.execute(this.l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrder", 
                    new Class[] {long.class});
            assertEquals(1005,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", 
                    new Class[]{ Order.class });
            assertEquals(33381,((IfoOrderImpl)l_paramsValue2.getFirstCalled()[0]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", 
                    new Class[]{ SubAccount.class, String.class });
            assertEquals(WEB3GentradeSubAccountForMock.class,((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getClass());
            assertEquals("1",(String)l_paramsValue3.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class});
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[]{long.class});
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class});
            
            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class});
            
            WEB3MockObjectParamsValue l_paramsValue8 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder",
                    new Class[]{ IfoOrderUnit.class });
            
            WEB3MockObjectParamsValue l_paramsValue9 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getHandlingPossibleExecConds", 
                    new Class[]{ String[].class, String[].class });
            assertEquals("0",this.l_response.orderPriceDivList[0]);
            assertEquals("2",this.l_response.execCondList[0]);
            assertEquals("2",this.l_response.sessionType);
            assertEquals("1",this.l_response.expirationDateType);
            assertNull(this.l_response.expirationDate);
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * set取引最終日を取得する
     * is出来るまで注文取扱可能 == false
     * is夕場まで注文 == false
     * 注文期限区分一覧 = "2" 
     * 立会区分 = "2"
     * 注文期限区分 = "2"(出来るまで注文)
     * 注文有効期限 = 當前日期
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setBizDate("20071202");
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setSessionType("2");
            l_ifoOrderUnitParams.setLimitPrice(10);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,26);
            ca.set(Calendar.HOUR_OF_DAY, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
            Date date = ca.getTime();
            l_ifoOrderUnitParams.setExpirationDate(new Timestamp(date.getTime()));
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder",
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(true));
            
            this.l_request.id = "1005";
            this.l_response = (WEB3FuturesOpenMarginChangeInputResponse) this.l_serviceImpl.execute(this.l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrder", 
                    new Class[] {long.class});
            assertEquals(1005,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", 
                    new Class[]{ Order.class });
            assertEquals(33381,((IfoOrderImpl)l_paramsValue2.getFirstCalled()[0]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", 
                    new Class[]{ SubAccount.class, String.class });
            assertEquals(WEB3GentradeSubAccountForMock.class,((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getClass());
            assertEquals("1",(String)l_paramsValue3.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class});
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[]{long.class});
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class});
            
            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class});
            
            WEB3MockObjectParamsValue l_paramsValue8 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder",
                    new Class[]{ IfoOrderUnit.class });
            
            WEB3MockObjectParamsValue l_paramsValue9 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getHandlingPossibleExecConds", 
                    new Class[]{ String[].class, String[].class });
            
            WEB3MockObjectParamsValue l_paramsValue10 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class });
            assertEquals("1",this.l_response.orderPriceDivList[0]);
            assertEquals("2",this.l_response.execCondList[0]);
            assertEquals("2",this.l_response.sessionType);
            assertEquals("2",this.l_response.expirationDateType);
            assertEquals(0,WEB3DateUtility.compareToDay(date,l_response.expirationDate));
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            log.error("", l_web3BaseException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * 777
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setBizDate("20071202");
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setSessionType("2");
            l_ifoOrderUnitParams.setFirstOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = 
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("1D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder(WEB3CarriedOrderDef.CAN_NOT_DEALT);
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            this.l_request.id = "1005";
            this.l_response = (WEB3FuturesOpenMarginChangeInputResponse) this.l_serviceImpl.execute(this.l_request);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder",
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrder", 
                    new Class[] {long.class});
            assertEquals(1005,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());

            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", 
                    new Class[]{ Order.class });
            assertEquals(33381,((IfoOrderImpl)l_paramsValue2.getFirstCalled()[0]).getOrderId());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", 
                    new Class[]{ SubAccount.class, String.class });
            assertEquals(WEB3GentradeSubAccountForMock.class,((WEB3GentradeSubAccount)l_paramsValue3.getFirstCalled()[0]).getClass());
            assertEquals("1",(String)l_paramsValue3.getFirstCalled()[1]);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class});
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[]{long.class});
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class});
            
            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class});
            
            WEB3MockObjectParamsValue l_paramsValue8 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isEveningSessionOrder",
                    new Class[]{ IfoOrderUnit.class });
            
            WEB3MockObjectParamsValue l_paramsValue9 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getHandlingPossibleExecConds", 
                    new Class[]{ String[].class, String[].class });
            

            fail();
        }
        catch(WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413,l_web3BaseException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }    
    
    /**
     * 「訂正対象注文単位」取得場合
     *
     */
    public void testGetChangeOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = "testGetChangeOrderUnit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoOrderUnitRow());
            
            this.l_request.id = "1001";
            IfoOrderUnit l_orderUnit = this.l_serviceImpl.getChangeOrderUnit(this.l_request);
            
            assertEquals(1001,l_orderUnit.getOrderUnitId());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「訂正対象注文単位」沒有取得場合
     *  抛出異常信息:WEB3ErrorCatalog.SYSTEM_ERROR_80005
     *
     */
    public void testGetChangeOrderUnit_C0002()
    {
        final String STR_METHOD_NAME = "testGetChangeOrderUnit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoOrderUnitRow());
            
            this.l_request.id = "1002";
            IfoOrderUnit l_orderUnit = this.l_serviceImpl.getChangeOrderUnit(this.l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate注文訂正可能」訂正失敗的場合
     *
     */
    public void testValidateOrderForChangeability_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,STR_METHOD_NAME));
                    
            IfoOrderUnit l_orderUnit = new IfoOrderUnitForTest();
            this.l_serviceImpl.validateOrderForChangeability(l_orderUnit);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            assertTrue(true);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate注文訂正可能」訂正成功的場合
     *
     */
    public void testValidateOrderForChangeability_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class },
                    null);
                    
            IfoOrderUnit l_orderUnit = new IfoOrderUnitForTest();
            this.l_serviceImpl.validateOrderForChangeability(l_orderUnit);
            assertTrue(true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 注文単位.isMarketOrder == false
     *
     */
    public void testCreateInputScreen_C0001()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsMockUseGetChangeOrderUnit = true;
            this.l_blnIsMockValidateOrder = true;
//          OrderexecutionEndRow
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            WEB3FuturesOpenMarginChangeInputResponse l_response = 
                this.l_serviceImpl.createInputScreen(this.l_request);
            
            assertEquals("1",l_response.orderPriceDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 注文単位.isMarketOrder == true
     *
     */
    public void testCreateInputScreen_C0002()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsMockUseGetChangeOrderUnit = true;
            this.l_blnIsMockValidateOrder = true;
//          OrderexecutionEndRow
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            this.l_blnIsMarketOrder = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            WEB3FuturesOpenMarginChangeInputResponse l_response = 
                this.l_serviceImpl.createInputScreen(this.l_request);
            
            assertEquals("0",l_response.orderPriceDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
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
            

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
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
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20050101", "yyyyMMdd"));
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
            
            
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,26);
            ca.set(Calendar.HOUR_OF_DAY, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
            Date date = ca.getTime();
            
            ThreadLocalSystemAttributesRegistry.setAttribute
                (WEB3GentradeTradingTimeManagementForMock.TIMESTAMP_TAG,new Timestamp(date.getTime()));
            


            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20070716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = 
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("1D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            this.setExpectedDate(date,"1");            
                        
        }
        catch (Exception l_ex)
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
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(TestDBUtility.getIfoOrderRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrder", new Class[]{ long.class },
                    l_ifoOrder);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", 
                    new Class[]{ SubAccount.class, String.class },
                    null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    l_market);
            
            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(1006160060009L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[]{long.class},
                    l_productImpl);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_tradedProduct);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33387);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getWeb3GenBranch",
                    new Class[] {},
                    l_branch);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512246L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", new Class[] {},
                    l_branch);
            
            
            WEB3IfoQuoteDataImplForMock l_ifoQuoteDataForMock = new WEB3IfoQuoteDataImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteDataForMock);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("1D");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getInstitution", new Class[]{},
                    l_institution);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getHandlingPossibleExecConds", 
                    new Class[]{ String[].class, String[].class },
                    new String[]{"2"});
            
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3FuturesOpenMarginChangeInputRequestForTest extends WEB3FuturesOpenMarginChangeInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            
        }
    }
    
    private class WEB3FuturesChangeOpenContractInputServiceImplForTest extends WEB3FuturesChangeOpenContractInputServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
             
            return new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow());
        }
        
        public IfoOrderUnit getChangeOrderUnit(WEB3FuturesOpenMarginChangeInputRequest l_request)
            throws WEB3BaseException
        {
                if(!l_blnIsMockUseGetChangeOrderUnit)
                {
                    return super.getChangeOrderUnit(l_request);
                }
                else
                {
                    return new IfoOrderUnitForTest();
                }
        }
        
        protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
            throws WEB3BaseException
        {
                if(!l_blnIsMockValidateOrder)
                {
                    super.validateOrderForChangeability(l_orderUnit);
                }
        }
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
        }
        
    }
    
    private class IfoOrderUnitForTest implements IfoOrderUnit
    {

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isFuturesOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isCallOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPutOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOpenContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSettleContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public IfoOrderExecutionConditionType getExecutionConditionType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return l_blnIsMarketOrder;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return TestDBUtility.getIfoOrderUnitRow();
        }
        
    }

}
@
