head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityMarginExecuteReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/16　@金傑(中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityMarginExecuteGroup;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityMarginExecuteUnit;
import webbroker3.equity.message.WEB3EquityMarginSortKey;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.OrderStatus;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchForMock;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 株式・信用注文約定照会サービスImplクラスのテスト<BR>
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest extends TestBaseForMock
{

    private WEB3EquityMarginExecuteReferenceServiceImpl l_serviceImpl = null;
    
    private WEB3EquityMarginExecuteReferenceRequest l_request = null;
    
    private WEB3EquityMarginExecuteReferenceResponse l_response = null;
    
    private List l_lisRecords = null;
    
    private OrderStatus l_orderStatus = null;
    
    private EqTypeOrderUnit[] l_arrOrderUnit = null;
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest.class);
    
    public WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3EquityMarginExecuteReferenceServiceImplForTest();
        this.l_orderStatus = this.l_serviceImpl.new OrderStatus();
        this.l_request = new WEB3EquityMarginExecuteReferenceRequest();
        this.l_response = new WEB3EquityMarginExecuteReferenceResponse(this.l_request);
        this.l_lisRecords = new ArrayList();
        getDataMock();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_serviceImpl = null;
        this.l_request = null;
        this.l_response = null;
        this.l_lisRecords = null;
    }
    
    /**
     * 測試l_lisRecords為空的情況
     *
     */
    public void test_createExecuteGroup_C0001()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_lisRecords.clear();
            log.debug("l_lisRecords.size() "+String.valueOf(this.l_lisRecords.size()));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[] {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                            String.class },
                            l_lisRecords);
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
            assertNull(l_response.executeGroups);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(STR_METHOD_NAME,l_web3BaseException);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 測試l_lisRecords = 1的情況<br>
     * 引数.リクエスト.約定状態区分 != null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が0になった場合は<br>
     *
     */
    public void test_createExecuteGroup_C0002()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            this.l_lisRecords.clear();
            this.l_lisRecords.add(l_orderUnit);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[] {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                            String.class },
                            this.l_lisRecords);
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
            assertNull(l_response.executeGroups);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(STR_METHOD_NAME,l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 != null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得しない場合<br>
     *
     */
    public void test_createExecuteGroup_C0003()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            assertEquals(WEB3SystemLayerException.class,l_web3SystemException.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 != null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * 株式注文単位テーブル.合計約定金額 != 0<br>
     * ページごとに、三つのレコードをディスプレーする<br>
     * 
     *
     */
    public void test_createExecuteGroup_C0004()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket",
                    new Class[] { long.class },
                    l_market); 
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.equity.WEB3EquityOrderManager",
          "validateTradedProduct",
          new Class[] {EqTypeProduct.class,Market.class},
          null);
                               
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
             
            Date l_datExpectOpenDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            
            
            Date l_datOrderBizDate1 = WEB3DateUtility.getDate("20070117","yyyyMMdd");
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(0);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate1 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datExecution1 = WEB3DateUtility.getDate("20070102","yyyyMMdd");
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();
            
            l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(1);
            l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate2 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datOrderBizDate2 = WEB3DateUtility.getDate("20070118","yyyyMMdd");
            Date l_datExecution2 = WEB3DateUtility.getDate("20070103","yyyyMMdd");
            
            l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(2);
            l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate3 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datOrderBizDate3 = WEB3DateUtility.getDate("20070119","yyyyMMdd");

            
            assertEquals(3,l_response.executeGroups.length);
            
            WEB3EquityMarginExecuteGroup[] l_arrExpect = new WEB3EquityMarginExecuteGroup[l_response.executeGroups.length];
            l_arrExpect[0] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("0",l_response.executeGroups[0].taxType); 
            assertEquals("0",l_response.executeGroups[0].swapTaxType);
            assertEquals("99",l_response.executeGroups[0].tradingType);
            assertEquals("3",l_response.executeGroups[0].execCondType);
            assertEquals("0002",l_response.executeGroups[0].transactionStateType);
            assertEquals("200",l_response.executeGroups[0].deliveryPrice);
            assertEquals("20.6",l_response.executeGroups[0].estimatedProfitLoss);
            assertEquals("527",l_response.executeGroups[0].execQuantity);
            assertEquals("4",l_response.executeGroups[0].execPrice);
            assertEquals("200",l_response.executeGroups[0].deliveryPrice);
            assertEquals("1",l_response.executeGroups[0].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[0].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[0].contractPrice);
            assertEquals("1",l_response.executeGroups[0].priceCondType);
            assertEquals("1",l_response.executeGroups[0].priceCondType);
            assertEquals("3",l_response.executeGroups[0].execCondType);
            assertEquals("1",l_response.executeGroups[0].orderCondType);
            assertEquals("2000",l_response.executeGroups[0].stopOrderCondPrice);
            assertEquals("1",l_response.executeGroups[0].stopOrderCondOperator);
            assertTrue(l_response.executeGroups[0].cancelFlag);
            assertFalse(l_response.executeGroups[0].changeFlag);
            assertNull(l_response.executeGroups[0].wlimitExecCondType);
            assertNull(l_response.executeGroups[0].wlimitEnableStatusDiv);
            assertNull(l_response.executeGroups[0].wlimitBefChgLimitPrice);
            assertNull(l_response.executeGroups[0].wlimitBefChgExecCondType);
            assertEquals("1",l_response.executeGroups[0].orgOrderCondType);
            assertEquals("2200",l_response.executeGroups[0].orgOrderCondPrice);
            assertEquals("1",l_response.executeGroups[0].orgOrderCondOperator);
            assertNull(l_response.executeGroups[0].orgWlimitOrderPriceDiv);
            assertNull(l_response.executeGroups[0].orgWlimitPrice);
            assertNull(l_response.executeGroups[0].orgWlimitExecCondType);
            assertTrue(l_response.executeGroups[0].cancelFlag);
            assertFalse(l_response.executeGroups[0].changeFlag);
            assertEquals("1200",l_response.executeGroups[0].orderQuantity);
            assertEquals("0",l_response.executeGroups[0].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[0].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].orderDate,l_datExpectOrderDate1));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].orderBizDate,l_datOrderBizDate1));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].expirationDate,l_datExpirationDate));
            assertEquals("0002",l_response.executeGroups[0].transactionStateType); 
            assertNull(l_response.executeGroups[0].operatorCode);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].executeUnits[0].executionTimestamp,l_datExecution1));
            assertEquals("100",l_response.executeGroups[0].executeUnits[0].execPrice);
            assertEquals("200",l_response.executeGroups[0].executeUnits[0].execQuantity);
            assertEquals("0", l_response.executeGroups[0].delayDiv);
            assertFalse(l_response.executeGroups[0].manualFlag);
            assertEquals("A12",l_response.executeGroups[0].productCode);
            assertEquals("名前A12",l_response.executeGroups[0].productName);
            
            l_arrExpect[1] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("1",l_response.executeGroups[1].taxType); 
            assertNull(l_response.executeGroups[1].swapTaxType);
            assertEquals("7",l_response.executeGroups[1].tradingType);
            assertEquals("4",l_response.executeGroups[1].execCondType);
            assertEquals("0002",l_response.executeGroups[1].transactionStateType);
            assertEquals("200",l_response.executeGroups[1].deliveryPrice);
            assertNull(l_response.executeGroups[1].estimatedProfitLoss);
            assertEquals("435",l_response.executeGroups[1].execQuantity);
            assertEquals("12",l_response.executeGroups[1].execPrice);
            assertEquals("200",l_response.executeGroups[1].deliveryPrice);
            assertEquals("1",l_response.executeGroups[1].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[1].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[1].contractPrice);
            assertEquals("1",l_response.executeGroups[1].priceCondType);
            assertEquals("1",l_response.executeGroups[1].priceCondType);
            assertEquals("4",l_response.executeGroups[1].execCondType);
            assertEquals("2",l_response.executeGroups[1].orderCondType);
            assertEquals("4000",l_response.executeGroups[1].wlimitOrderCondPrice);
            assertEquals("1",l_response.executeGroups[1].wlimitOrderCondOperator);
            assertEquals("0",l_response.executeGroups[1].wLimitOrderPriceDiv);
//          //assertNull(l_response.executeGroups[1].wlimitExecCondType);
//          //assertNull(l_response.executeGroups[1].wlimitEnableStatusDiv);
//          assertNull(l_response.executeGroups[1].wlimitBefChgLimitPrice);
//          assertNull(l_response.executeGroups[1].wlimitBefChgExecCondType);
//          assertEquals("2",l_response.executeGroups[1].orgOrderCondType);
//          assertEquals("2400",l_response.executeGroups[1].orgOrderCondPrice);
//          assertEquals("1",l_response.executeGroups[1].orgOrderCondOperator);
//          assertNull(l_response.executeGroups[1].orgWlimitOrderPriceDiv);
//          assertNull(l_response.executeGroups[1].orgWlimitPrice);
//          assertNull(l_response.executeGroups[1].orgWlimitExecCondType);
            assertTrue(l_response.executeGroups[1].cancelFlag);
            assertTrue(l_response.executeGroups[1].changeFlag);
            assertEquals("1200",l_response.executeGroups[1].orderQuantity);
            assertEquals("0",l_response.executeGroups[1].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[1].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].orderDate,l_datExpectOrderDate2));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].orderBizDate,l_datOrderBizDate2));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].executeUnits[1].executionTimestamp,l_datExecution2));
            assertEquals("101",l_response.executeGroups[1].executeUnits[1].execPrice);
            assertEquals("201",l_response.executeGroups[1].executeUnits[1].execQuantity);
            assertEquals("0002",l_response.executeGroups[1].transactionStateType); 
            assertNull(l_response.executeGroups[1].operatorCode);
//          assertNull(l_response.executeGroups[1].delayDiv);
            assertFalse(l_response.executeGroups[1].manualFlag);
            assertEquals("A14",l_response.executeGroups[1].productCode);
            assertEquals("名前A14",l_response.executeGroups[1].productName);
            
            l_arrExpect[2] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("1",l_response.executeGroups[2].taxType); 
            assertNull(l_response.executeGroups[2].swapTaxType);
            assertEquals("7",l_response.executeGroups[2].tradingType);
            assertEquals("3",l_response.executeGroups[2].execCondType);
            assertEquals("0002",l_response.executeGroups[2].transactionStateType);
            assertNull(l_response.executeGroups[2].estimatedProfitLoss);
            assertNull(l_response.executeGroups[2].execQuantity);
            assertNull(l_response.executeGroups[2].execPrice);
            assertNull(l_response.executeGroups[2].deliveryPrice);
            assertEquals("1",l_response.executeGroups[2].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[2].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[2].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[2].contractPrice);
            assertEquals("1",l_response.executeGroups[2].priceCondType);
            assertEquals("1",l_response.executeGroups[2].priceCondType);
            assertEquals("3",l_response.executeGroups[2].execCondType);
            assertEquals("2",l_response.executeGroups[2].orderCondType);
            assertEquals("5000",l_response.executeGroups[2].wlimitOrderCondPrice);
            assertEquals("1",l_response.executeGroups[2].wlimitOrderCondOperator);
            assertEquals("1",l_response.executeGroups[2].wLimitOrderPriceDiv);
//          //assertNull(l_response.executeGroups[2].wlimitExecCondType);
//          assertNull(l_response.executeGroups[2].wlimitEnableStatusDiv);
//          assertNull(l_response.executeGroups[2].wlimitBefChgLimitPrice);
//          assertNull(l_response.executeGroups[2].wlimitBefChgExecCondType);
//          assertEquals("2",l_response.executeGroups[2].orgOrderCondType);
//          assertEquals("2500",l_response.executeGroups[2].orgOrderCondPrice);
//          assertEquals("1",l_response.executeGroups[2].orgOrderCondOperator);
//          assertNull(l_response.executeGroups[2].orgWlimitOrderPriceDiv);
//          assertNull(l_response.executeGroups[2].orgWlimitPrice);
//          assertNull(l_response.executeGroups[2].orgWlimitExecCondType);
            assertTrue(l_response.executeGroups[2].cancelFlag);
            assertTrue(l_response.executeGroups[2].changeFlag);
            assertEquals("1200",l_response.executeGroups[2].orderQuantity);
            assertEquals("0",l_response.executeGroups[2].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[2].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[2].orderDate,l_datExpectOrderDate3));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[2].orderBizDate,l_datOrderBizDate3));
            assertEquals("0002",l_response.executeGroups[2].transactionStateType); 
            assertNull(l_response.executeGroups[2].operatorCode);
//          assertNull(l_response.executeGroups[2].delayDiv);
            assertFalse(l_response.executeGroups[2].manualFlag);
            assertEquals("A15",l_response.executeGroups[2].productCode);
            assertEquals("名前A15",l_response.executeGroups[2].productName);
            
            assertEquals("22334455",l_response.idList[0].id);
            assertEquals("2",l_response.idList[0].productDiv);
            
            assertEquals("44556677",l_response.idList[1].id);
            assertEquals("2",l_response.idList[1].productDiv);
            
            assertEquals("55667788",l_response.idList[2].id);
            assertEquals("2",l_response.idList[2].productDiv);
            
            assertEquals("1",l_response.pageIndex);
            assertEquals("1",l_response.totalPages);
            assertEquals("3",l_response.totalRecords);

        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 != null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * ページごとに、二つのレコードをディスプレーする<br>
     *
     */
    public void test_createExecuteGroup_C0005()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.equity.WEB3EquityOrderManager",
          "validateTradedProduct",
          new Class[] {EqTypeProduct.class,Market.class},
          null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket",
                    new Class[] { long.class },
                    l_market); 
            
            WEB3GentradeTrader l_trader = new WEB3GentradeTrader(3338111130L,false);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getTrader", new Class[] { long.class },l_trader);
                   
            this.l_request.pageSize = "2";
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
             
            Date l_datExpectOpenDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            
            
            Date l_datOrderBizDate1 = WEB3DateUtility.getDate("20070117","yyyyMMdd");
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(0);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate1 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();
            log.debug("l_datExpirationDate "+l_datExpirationDate);
            Date l_datExecution1 = WEB3DateUtility.getDate("20070102","yyyyMMdd");
            
            l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(1);
            l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate2 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datOrderBizDate2 = WEB3DateUtility.getDate("20070118","yyyyMMdd");
            Date l_datExecution2 = WEB3DateUtility.getDate("20070103","yyyyMMdd");
            
            l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(2);
            l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            
            assertEquals(2,l_response.executeGroups.length);
            
            WEB3EquityMarginExecuteGroup[] l_arrExpect = new WEB3EquityMarginExecuteGroup[l_response.executeGroups.length];
            l_arrExpect[0] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("0",l_response.executeGroups[0].taxType); 
            assertEquals("0",l_response.executeGroups[0].swapTaxType);
            assertEquals("99",l_response.executeGroups[0].tradingType);
            assertEquals("3",l_response.executeGroups[0].execCondType);
            assertEquals("0002",l_response.executeGroups[0].transactionStateType);
            assertEquals("200",l_response.executeGroups[0].deliveryPrice);
            assertEquals("20.6",l_response.executeGroups[0].estimatedProfitLoss);
            assertEquals("527",l_response.executeGroups[0].execQuantity);
            assertEquals("4",l_response.executeGroups[0].execPrice);
            assertEquals("200",l_response.executeGroups[0].deliveryPrice);
            assertEquals("1",l_response.executeGroups[0].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[0].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[0].contractPrice);
            assertEquals("1",l_response.executeGroups[0].priceCondType);
            assertEquals("1",l_response.executeGroups[0].priceCondType);
            assertEquals("3",l_response.executeGroups[0].execCondType);
            assertEquals("1",l_response.executeGroups[0].orderCondType);
            assertEquals("2000",l_response.executeGroups[0].stopOrderCondPrice);
            assertEquals("1",l_response.executeGroups[0].stopOrderCondOperator);
            assertNull(l_response.executeGroups[0].wlimitExecCondType);
            assertNull(l_response.executeGroups[0].wlimitEnableStatusDiv);
            assertNull(l_response.executeGroups[0].wlimitBefChgLimitPrice);
            assertNull(l_response.executeGroups[0].wlimitBefChgExecCondType);
            assertEquals("1",l_response.executeGroups[0].orgOrderCondType);
            assertEquals("2200",l_response.executeGroups[0].orgOrderCondPrice);
            assertEquals("1",l_response.executeGroups[0].orgOrderCondOperator);
            assertNull(l_response.executeGroups[0].orgWlimitOrderPriceDiv);
            assertNull(l_response.executeGroups[0].orgWlimitPrice);
            assertNull(l_response.executeGroups[0].orgWlimitExecCondType);
            assertTrue(l_response.executeGroups[0].cancelFlag);
            assertFalse(l_response.executeGroups[0].changeFlag);
            assertEquals("1200",l_response.executeGroups[0].orderQuantity);
            assertEquals("0",l_response.executeGroups[0].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[0].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].orderDate,l_datExpectOrderDate1));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].orderBizDate,l_datOrderBizDate1));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].expirationDate,l_datExpirationDate));
            assertEquals("0002",l_response.executeGroups[0].transactionStateType); 
            assertEquals("52125",l_response.executeGroups[0].operatorCode);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].executeUnits[0].executionTimestamp,l_datExecution1));
            assertEquals("100",l_response.executeGroups[0].executeUnits[0].execPrice);
            assertEquals("200",l_response.executeGroups[0].executeUnits[0].execQuantity);
            //assertNull(l_response.executeGroups[0].delayDiv);
            assertFalse(l_response.executeGroups[0].manualFlag);
            
            l_arrExpect[1] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("1",l_response.executeGroups[1].taxType); 
            assertNull(l_response.executeGroups[1].swapTaxType);
            assertEquals("7",l_response.executeGroups[1].tradingType);
            assertEquals("4",l_response.executeGroups[1].execCondType);
            assertEquals("0002",l_response.executeGroups[1].transactionStateType);
            assertEquals("200",l_response.executeGroups[1].deliveryPrice);
            assertNull(l_response.executeGroups[1].estimatedProfitLoss);
            assertEquals("435",l_response.executeGroups[1].execQuantity);
            assertEquals("12",l_response.executeGroups[1].execPrice);
            assertEquals("200",l_response.executeGroups[1].deliveryPrice);
            assertEquals("1",l_response.executeGroups[1].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[1].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[1].contractPrice);
            assertEquals("1",l_response.executeGroups[1].priceCondType);
            assertEquals("1",l_response.executeGroups[1].priceCondType);
            assertEquals("4",l_response.executeGroups[1].execCondType);
            assertEquals("2",l_response.executeGroups[1].orderCondType);
            assertEquals("4000",l_response.executeGroups[1].wlimitOrderCondPrice);
            assertEquals("1",l_response.executeGroups[1].wlimitOrderCondOperator);
            assertEquals("0",l_response.executeGroups[1].wLimitOrderPriceDiv);
            //assertNull(l_response.executeGroups[1].wlimitExecCondType);
            //assertNull(l_response.executeGroups[1].wlimitEnableStatusDiv);
            //assertNull(l_response.executeGroups[1].wlimitBefChgLimitPrice);
            //assertNull(l_response.executeGroups[1].wlimitBefChgExecCondType);
            //assertEquals("2",l_response.executeGroups[1].orgOrderCondType);
            //assertEquals("2400",l_response.executeGroups[1].orgOrderCondPrice);
            //assertEquals("1",l_response.executeGroups[1].orgOrderCondOperator);
            //assertNull(l_response.executeGroups[1].orgWlimitOrderPriceDiv);
            //assertNull(l_response.executeGroups[1].orgWlimitPrice);
            //assertNull(l_response.executeGroups[1].orgWlimitExecCondType);
            assertTrue(l_response.executeGroups[1].cancelFlag);
            assertTrue(l_response.executeGroups[1].changeFlag);
            assertEquals("1200",l_response.executeGroups[1].orderQuantity);
            assertEquals("0",l_response.executeGroups[1].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[1].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].orderDate,l_datExpectOrderDate2));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].orderBizDate,l_datOrderBizDate2));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].executeUnits[1].executionTimestamp,l_datExecution2));
            assertEquals("101",l_response.executeGroups[1].executeUnits[1].execPrice);
            assertEquals("201",l_response.executeGroups[1].executeUnits[1].execQuantity);
            assertEquals("0002",l_response.executeGroups[1].transactionStateType); 
            assertEquals("52125",l_response.executeGroups[1].operatorCode);
            //assertNull(l_response.executeGroups[1].delayDiv);
            assertFalse(l_response.executeGroups[1].manualFlag);
            
            assertEquals("22334455",l_response.idList[0].id);
            assertEquals("2",l_response.idList[0].productDiv);
            
            assertEquals("44556677",l_response.idList[1].id);
            assertEquals("2",l_response.idList[1].productDiv);
                        
            assertEquals("1",l_response.pageIndex);
            assertEquals("2",l_response.totalPages);
            assertEquals("3",l_response.totalRecords);

        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 == null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * 引数.リクエスト.照会区分 == ”訂正取消一覧” and validate取引銘柄例外がスローされた場合<br>
     * 
     *
     */
    public void test_createExecuteGroup_C0006()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            this.l_lisRecords.clear();
            this.getEqTypeOrderUnits();
            
            this.l_arrOrderUnit =
                (EqTypeOrderUnit[]) this.l_lisRecords.toArray(new EqTypeOrderUnit[this.l_lisRecords.size()]);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateTradedProduct",
                    new Class[] {EqTypeProduct.class,Market.class},
                    new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[] {EqTypeOrderUnit[].class},
                    l_arrOrderUnit);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket",
                    new Class[] { long.class },
                    l_market);

            this.l_arrOrderUnit =
                (EqTypeOrderUnit[]) this.l_lisRecords.toArray(new EqTypeOrderUnit[this.l_lisRecords.size()]);
            
            log.debug("this.l_lisRecords.size() "+this.l_lisRecords.size());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[] {EqTypeOrderUnit[].class},
                    l_arrOrderUnit);
            
            l_request.execType = null;
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
            assertEquals(0,l_response.executeGroups.length);
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 == null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * 引数.リクエスト.照会区分 == ”訂正取消一覧” and validate取扱可能市場例外がスローされた場合<br>
     * 引数.リクエスト.照会区分 == ”訂正取消一覧” and validate取扱可能市場（信用）例外がスローされた場合<br>
     * 
     *
     */
    public void test_createExecuteGroup_C0007()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            this.l_lisRecords.clear();
            this.getEqTypeOrderUnits();
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams6 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams6.setOrderUnitId(3304148080012L);
            l_eqtypeOrderUnitParams6.setOrderId(66778899L);
            l_eqtypeOrderUnitParams6.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams6.setOrderCateg(OrderCategEnum.ASSET);//
            l_eqtypeOrderUnitParams6.setProductId(3304148080012L);
            l_eqtypeOrderUnitParams6.setMarketId(3306L);
            l_eqtypeOrderUnitParams6.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams6.setExecutedAmount(200);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams6);
            
            ProductParams l_productParams6 = TestDBUtility.getProductRow();
            l_productParams6.setProductId(3304148080012L);
            l_productParams6.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams6);
            
            EqtypeProductParams l_eqProductParams6 = new EqtypeProductParams();
            l_eqProductParams6.setProductId(3304148080012L);
            l_eqProductParams6.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams6.setInstitutionCode("A20");
            l_eqProductParams6.setProductCode("A20");
            l_eqProductParams6.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams6.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams6.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams6);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080012L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080012L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams); 
            
            
            
            EqTypeOrderUnit l_orderUnit6 = new EqTypeOrderUnitImpl(null, 3304148080012L);
            this.l_lisRecords.add(l_orderUnit6);
            
            this.l_arrOrderUnit =
                (EqTypeOrderUnit[]) this.l_lisRecords.toArray(new EqTypeOrderUnit[this.l_lisRecords.size()]);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateHandlingMarket",
                    new Class[] {WEB3GentradeBranch.class,
                            TradedProduct.class}, 
                            new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateHandlingMarket",
                    new Class[] {WEB3GentradeBranch.class,
                            WEB3EquityTradedProduct.class,
                            String.class,
                            String.class,
                            double.class}, 
                            new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[] {EqTypeOrderUnit[].class},
                    l_arrOrderUnit);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket",
                    new Class[] { long.class },
                    l_market);
            
            l_request.execType = null;
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
            assertEquals(0,l_response.executeGroups.length);
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 != null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * 信用注文約定照会注文単位.逆指値用発注条件単価 == nullの場合<br>
     * ページごとに、二つのレコードをディスプレーする<br>
     *
     */
    public void test_createExecuteGroup_C0008()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080008L);
            l_eqtypeOrderUnitParams2.setOrderId(22334455L);
            l_eqtypeOrderUnitParams2.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams2.setProductId(3304148080008L);
            l_eqtypeOrderUnitParams2.setMarketId(3304L);
            l_eqtypeOrderUnitParams2.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams2.setSonarTradedCode(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            l_eqtypeOrderUnitParams2.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams2.setSwapTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams2.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams2.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);      
            l_eqtypeOrderUnitParams2.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            l_eqtypeOrderUnitParams2.setOrgOrderConditionType("1");
            l_eqtypeOrderUnitParams2.setExecutedAmount(2300);
            l_eqtypeOrderUnitParams2.setExecutedQuantity(527);
            l_eqtypeOrderUnitParams2.setOrgOrderCondOperator("1");
            l_eqtypeOrderUnitParams2.setBranchId(33382L);
            l_eqtypeOrderUnitParams2.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            l_eqtypeOrderUnitParams2.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            l_eqtypeOrderUnitParams2.setFirstOrderUnitId(123789L);
            l_eqtypeOrderUnitParams2.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            l_eqtypeOrderUnitParams2.setOrderChanel("0");
            l_eqtypeOrderUnitParams2.setTraderId(3338111157L);
            l_eqtypeOrderUnitParams2.setExpirationDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams2.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams2.setConfirmedExecConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
            
            ProductParams l_productParams2 = TestDBUtility.getProductRow();
            l_productParams2.setProductId(3304148080008L);
            l_productParams2.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams2);
            
            EqtypeProductParams l_eqProductParams2 = new EqtypeProductParams();
            l_eqProductParams2.setProductId(3304148080008L);
            l_eqProductParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams2.setInstitutionCode("A12");
            l_eqProductParams2.setProductCode("A12");
            l_eqProductParams2.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams2);
 
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams4= TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_eqtypeOrderUnitParams4.setOrderId(44556677L);
            l_eqtypeOrderUnitParams4.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams4.setProductId(3304148080004L);
            l_eqtypeOrderUnitParams4.setMarketId(3306L);
            l_eqtypeOrderUnitParams4.setSonarTradedCode(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
            l_eqtypeOrderUnitParams4.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
            l_eqtypeOrderUnitParams4.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            l_eqtypeOrderUnitParams4.setWLimitPrice(4200);
            l_eqtypeOrderUnitParams4.setLimitPrice(200);
            l_eqtypeOrderUnitParams4.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            l_eqtypeOrderUnitParams4.setOrgOrderConditionType("2");
            l_eqtypeOrderUnitParams4.setOrgStopOrderPrice(2400);
            l_eqtypeOrderUnitParams4.setOrgOrderCondOperator("1");
            l_eqtypeOrderUnitParams4.setExecutedAmount(5210);
            l_eqtypeOrderUnitParams4.setExecutedQuantity(435);
            l_eqtypeOrderUnitParams4.setBranchId(33384L);
            l_eqtypeOrderUnitParams4.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            l_eqtypeOrderUnitParams4.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            l_eqtypeOrderUnitParams4.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            l_eqtypeOrderUnitParams4.setExpirationDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams4.setOrderChanel("2");
            l_eqtypeOrderUnitParams4.setBizDate("20070118");
            l_eqtypeOrderUnitParams4.setTraderId(3338111157L); 
            l_eqtypeOrderUnitParams4.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams4.setConfirmedExecConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams4);
            
            this.l_lisRecords.clear();
            EqTypeOrderUnit l_orderUnit2 = new EqTypeOrderUnitImpl(null, 3304148080008L);
            this.l_lisRecords.add(l_orderUnit2);
            
            EqTypeOrderUnit l_orderUnit4 = new EqTypeOrderUnitImpl(null, 3304148080004L);
            this.l_lisRecords.add(l_orderUnit4);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[] {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                            String.class },
                            this.l_lisRecords);
            
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.equity.WEB3EquityOrderManager",
          "validateTradedProduct",
          new Class[] {EqTypeProduct.class,Market.class},
          null);
            
            Iterator l_itRecords = l_lisRecords.iterator();
            if (!WEB3StringTypeUtility.isEmpty(this.l_request.execType))
            {
                while (l_itRecords.hasNext())
                {
                    //is指定約定状態 == false
                    if (!this.l_serviceImpl.isDesignateExecutedStatus
                            (l_request.execType, (EqTypeOrderUnit) l_itRecords.next()))
                    {
                        l_itRecords.remove();
                    }
                }

            }
            log.debug("this.l_lisRecords.size()(after) "+this.l_lisRecords.size());
            this.l_arrOrderUnit =
                (EqTypeOrderUnit[]) this.l_lisRecords.toArray(new EqTypeOrderUnit[this.l_lisRecords.size()]);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[] {EqTypeOrderUnit[].class},
                    l_arrOrderUnit);
            
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket",
                    new Class[] { long.class },
                    l_market); 
            
            WEB3GentradeTrader l_trader = new WEB3GentradeTrader(3338111130L,false);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getTrader", new Class[] { long.class },l_trader);
                   
            this.l_request.pageSize = "2";

            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
             
            Date l_datExpectOpenDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");
            
            
            Date l_datOrderBizDate1 = WEB3DateUtility.getDate("20070117","yyyyMMdd");
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(0);
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate1 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();
            log.debug("l_datExpirationDate "+l_datExpirationDate);
            Date l_datExecution1 = WEB3DateUtility.getDate("20070102","yyyyMMdd");
            

            
            l_orderUnit = (EqTypeOrderUnit) this.l_lisRecords.get(1);
            l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            Date l_datExpectOrderDate2 = l_orderUnitRow.getCreatedTimestamp();
            Date l_datOrderBizDate2 = WEB3DateUtility.getDate("20070118","yyyyMMdd");
            Date l_datExecution2 = WEB3DateUtility.getDate("20070103","yyyyMMdd");
            
            assertEquals(2,l_response.executeGroups.length);
            
            WEB3EquityMarginExecuteGroup[] l_arrExpect = new WEB3EquityMarginExecuteGroup[l_response.executeGroups.length];
            l_arrExpect[0] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("0",l_response.executeGroups[0].taxType); 
            assertEquals("0",l_response.executeGroups[0].swapTaxType);
            assertEquals("99",l_response.executeGroups[0].tradingType);
            assertEquals("3",l_response.executeGroups[0].execCondType);
            assertEquals("0002",l_response.executeGroups[0].transactionStateType);
            assertEquals("200",l_response.executeGroups[0].deliveryPrice);
            assertEquals("20.6",l_response.executeGroups[0].estimatedProfitLoss);
            assertEquals("527",l_response.executeGroups[0].execQuantity);
            assertEquals("4",l_response.executeGroups[0].execPrice);
            assertEquals("200",l_response.executeGroups[0].deliveryPrice);
            assertEquals("1",l_response.executeGroups[0].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[0].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[0].contractPrice);
            assertEquals("1",l_response.executeGroups[0].priceCondType);
            assertEquals("1",l_response.executeGroups[0].priceCondType);
            assertEquals("3",l_response.executeGroups[0].execCondType);
            assertEquals("1",l_response.executeGroups[0].orderCondType);
            assertNull(l_response.executeGroups[0].stopOrderCondPrice);
            assertEquals("1",l_response.executeGroups[0].stopOrderCondOperator);
            assertNull(l_response.executeGroups[0].wlimitExecCondType);
            assertNull(l_response.executeGroups[0].wlimitEnableStatusDiv);
            assertNull(l_response.executeGroups[0].wlimitBefChgLimitPrice);
            assertNull(l_response.executeGroups[0].wlimitBefChgExecCondType);
            assertEquals("1",l_response.executeGroups[0].orgOrderCondType);
            assertNull(l_response.executeGroups[0].orgOrderCondPrice);
            assertEquals("1",l_response.executeGroups[0].orgOrderCondOperator);
            assertNull(l_response.executeGroups[0].orgWlimitOrderPriceDiv);
            assertNull(l_response.executeGroups[0].orgWlimitPrice);
            assertNull(l_response.executeGroups[0].orgWlimitExecCondType);
            assertTrue(l_response.executeGroups[0].cancelFlag);
            assertFalse(l_response.executeGroups[0].changeFlag);
            assertEquals("1200",l_response.executeGroups[0].orderQuantity);
            assertEquals("0",l_response.executeGroups[0].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[0].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].orderDate,l_datExpectOrderDate1));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].orderBizDate,l_datOrderBizDate1));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].expirationDate,l_datExpirationDate));
            assertEquals("0002",l_response.executeGroups[0].transactionStateType); 
            assertEquals("52125",l_response.executeGroups[0].operatorCode);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[0].executeUnits[0].executionTimestamp,l_datExecution1));
            assertEquals("100",l_response.executeGroups[0].executeUnits[0].execPrice);
            assertEquals("200",l_response.executeGroups[0].executeUnits[0].execQuantity);
            //assertNull(l_response.executeGroups[0].delayDiv);
            assertFalse(l_response.executeGroups[0].manualFlag);
            
            l_arrExpect[1] = new WEB3EquityMarginExecuteGroup();
            
            assertEquals("1",l_response.executeGroups[1].taxType); 
            assertNull(l_response.executeGroups[1].swapTaxType);
            assertEquals("7",l_response.executeGroups[1].tradingType);
            assertEquals("4",l_response.executeGroups[1].execCondType);
            assertEquals("0002",l_response.executeGroups[1].transactionStateType);
            assertEquals("200",l_response.executeGroups[1].deliveryPrice);
            assertNull(l_response.executeGroups[1].estimatedProfitLoss);
            assertEquals("435",l_response.executeGroups[1].execQuantity);
            assertEquals("12",l_response.executeGroups[1].execPrice);
            assertEquals("200",l_response.executeGroups[1].deliveryPrice);
            assertEquals("1",l_response.executeGroups[1].repayment.repaymentDiv);
            assertEquals("0",l_response.executeGroups[1].repayment.repaymentTimeLimit);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].openDate,l_datExpectOpenDate));
            assertEquals("2000",l_response.executeGroups[1].contractPrice);
            assertEquals("1",l_response.executeGroups[1].priceCondType);
            assertEquals("1",l_response.executeGroups[1].priceCondType);
            assertEquals("4",l_response.executeGroups[1].execCondType);
            assertEquals("2",l_response.executeGroups[1].orderCondType);
            assertNull(l_response.executeGroups[1].wlimitOrderCondPrice);
            assertEquals("4200",l_response.executeGroups[1].wLimitPrice);
            assertEquals("1",l_response.executeGroups[1].wlimitOrderCondOperator);
            assertEquals("1",l_response.executeGroups[1].wLimitOrderPriceDiv);
            //assertNull(l_response.executeGroups[1].wlimitExecCondType);
            //assertNull(l_response.executeGroups[1].wlimitEnableStatusDiv);
//          assertNull(l_response.executeGroups[1].wlimitBefChgLimitPrice);
//          assertNull(l_response.executeGroups[1].wlimitBefChgExecCondType);
//          assertEquals("2",l_response.executeGroups[1].orgOrderCondType);
//          assertEquals("2400",l_response.executeGroups[1].orgOrderCondPrice);
//          assertEquals("1",l_response.executeGroups[1].orgOrderCondOperator);
//          assertNull(l_response.executeGroups[1].orgWlimitOrderPriceDiv);
//          assertNull(l_response.executeGroups[1].orgWlimitPrice);
//          assertNull(l_response.executeGroups[1].orgWlimitExecCondType);
            assertNull(l_response.executeGroups[1].limitPrice);
            assertTrue(l_response.executeGroups[1].cancelFlag);
            assertTrue(l_response.executeGroups[1].changeFlag);
            assertEquals("1200",l_response.executeGroups[1].orderQuantity);
            assertEquals("1",l_response.executeGroups[1].orderPriceDiv);
            assertEquals("0",l_response.executeGroups[1].estimatedPrice);
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].orderDate,l_datExpectOrderDate2));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].orderBizDate,l_datOrderBizDate2));
            assertEquals(0,WEB3DateUtility.compareToDay(l_response.executeGroups[1].executeUnits[1].executionTimestamp,l_datExecution2));
            assertEquals("101",l_response.executeGroups[1].executeUnits[1].execPrice);
            assertEquals("201",l_response.executeGroups[1].executeUnits[1].execQuantity);
            assertEquals("0002",l_response.executeGroups[1].transactionStateType); 
            assertEquals("52125",l_response.executeGroups[1].operatorCode);
            //assertNull(l_response.executeGroups[1].delayDiv);
            assertFalse(l_response.executeGroups[1].manualFlag);
            
            assertEquals("22334455",l_response.idList[0].id);
            assertEquals("2",l_response.idList[0].productDiv);
            
            assertEquals("44556677",l_response.idList[1].id);
            assertEquals("2",l_response.idList[1].productDiv);
                        
            assertEquals("1",l_response.pageIndex);
            assertEquals("1",l_response.totalPages);
            assertEquals("2",l_response.totalRecords);

        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 == null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * 引数.リクエスト.照会区分 == ”訂正取消一覧” and<br>
     * <pre> （is訂正取消可能時間帯()の戻り値 == false or<br>
     *    （validate注文訂正可能状態()のチェック結果がNG（例外をスロー） and<br>
     *     validate注文取消可能状態()のチェック結果がNG（例外をスロー））<br>
     *   or 該当注文単位.注文経路区分 == ”HOST” の場合(該当注文単位.取引コード（SONAR） == ”立会外分売”の場合)<br>
     *   or validate立会外分売受付可能()のチェック結果がNG（例外をスロー）の場合 (該当注文単位.取引コード（SONAR） == ”立会外分売”の場合)<br>
     *
     */
    public void test_createExecuteGroup_C0009()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            this.l_lisRecords.clear();
            this.getEqTypeOrderUnits();
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams6 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams6.setOrderUnitId(3304148080012L);
            l_eqtypeOrderUnitParams6.setOrderId(66778899L);
            l_eqtypeOrderUnitParams6.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams6.setProductId(3304148080012L);
            l_eqtypeOrderUnitParams6.setMarketId(3306L);
            l_eqtypeOrderUnitParams6.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams6.setSonarTradedCode(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            l_eqtypeOrderUnitParams6.setOrderRootDiv(WEB3OrderRootDivDef.HOST);
            l_eqtypeOrderUnitParams6.setExecutedAmount(200);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams6);
            
            ProductParams l_productParams6 = TestDBUtility.getProductRow();
            l_productParams6.setProductId(3304148080012L);
            l_productParams6.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams6);
            
            EqtypeProductParams l_eqProductParams6 = new EqtypeProductParams();
            l_eqProductParams6.setProductId(3304148080012L);
            l_eqProductParams6.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams6.setInstitutionCode("A20");
            l_eqProductParams6.setProductCode("A20");
            l_eqProductParams6.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams6.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams6.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams6);
            
            EqTypeOrderUnit l_orderUnit6 = new EqTypeOrderUnitImpl(null, 3304148080012L);
            this.l_lisRecords.add(l_orderUnit6);
            
            this.l_arrOrderUnit =
                (EqTypeOrderUnit[]) this.l_lisRecords.toArray(new EqTypeOrderUnit[this.l_lisRecords.size()]);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateHandlingMarket",
                    new Class[] {WEB3GentradeBranch.class,
                            WEB3EquityTradedProduct.class,
                            String.class,
                            String.class,
                            double.class}, 
                            new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class},
                    new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOffFloorOrderPossible",
                    new Class[] {long.class, long.class, SubAccount.class},
                    new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[] {EqTypeOrderUnit[].class},
                    l_arrOrderUnit);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket",
                    new Class[] { long.class },
                    l_market);
            
            l_request.execType = null;
            l_serviceImpl.createExecuteGroup(l_request,l_response,l_orderStatus);
            assertEquals(0,l_response.executeGroups.length);
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 測試l_lisRecords = n的情況<br>
     * 引数.リクエスト.約定状態区分 == null の場合<br>
     * is指定約定状態 == false 最終的に、注文単位のリストの要素数が０でない場合<br>
     * 照会区分==訂正取消一覧<br>
     * 該当注文単位が信用取引注文の場合<br>
     * 市場オブジェクトを取得場合<br>
     * 引数.リクエスト.照会区分 == ”訂正取消一覧” and<br>
     * <pre> （is訂正取消可能時間帯()の戻り値 == false or<br>
     *    （validate注文訂正可能状態()のチェック結果がNG（例外をスロー） and<br>
     *     validate注文取消可能状態()のチェック結果がNG（例外をスロー））<br>
     *   or 該当注文単位.注文経路区分 != ”HOST” の場合(該当注文単位.取引コード（SONAR） == ”立会外分売”の場合)<br>
     *   or validate立会外分売受付可能()のチェック結果がOKの場合 (該当注文単位.取引コード（SONAR） == ”立会外分売”の場合)<br>
     *
     */
    public void test_createExecuteGroup_C0010()
    {
        final String STR_METHOD_NAME = "test_createExecuteGroup_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
          "webbroker3.equity.WEB3EquityOrderManager",
          "validateTradedProduct",
          new Class[] {EqTypeProduct.class,Market.class},
          null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation", new Class[] {Order.class}, new WEB3BaseException(new ErrorInfo(),
                            STR_METHOD_NAME));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit", new Class[] {EqTypeOrderUnit[].class}, l_arrOrderUnit);

            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeFinObjectManager",
                    "getMarket", new Class[] {long.class}, l_market);

            l_serviceImpl.createExecuteGroup(l_request, l_response, l_orderStatus);
            assertEquals(2, l_response.executeGroups.length);            
        }
        catch (WEB3SystemLayerException l_web3SystemException)
        {
            log.error(STR_METHOD_NAME, l_web3SystemException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void getData(){
        final String STR_METHOD_NAME = "getData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setMarginSysDiv(WEB3EnforcementDef.NOT_ENFORCEMENT);
            TestDBUtility.insertWithDel(l_branchParams1);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(3304148080001L);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);
            
            ProductParams l_productParams2 = TestDBUtility.getProductRow();
            l_productParams2.setProductId(3304148080002L);
            l_productParams2.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams2);
            
            ProductParams l_productParams3 = TestDBUtility.getProductRow();
            l_productParams3.setProductId(3304148080003L);
            l_productParams3.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams3);
            
            ProductParams l_productParams4 = TestDBUtility.getProductRow();
            l_productParams4.setProductId(3304148080004L);
            l_productParams4.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams4);
            
            ProductParams l_productParams5 = TestDBUtility.getProductRow();
            l_productParams5.setProductId(3304148080005L);
            l_productParams5.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams5);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(3304148080001L);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("A11");
            l_eqProductParams1.setProductCode("A11");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            EqtypeProductParams l_eqProductParams2 = new EqtypeProductParams();
            l_eqProductParams2.setProductId(3304148080002L);
            l_eqProductParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams2.setInstitutionCode("A12");
            l_eqProductParams2.setProductCode("A12");
            l_eqProductParams2.setStandardName("名前A12");
            l_eqProductParams2.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams2);
            
            EqtypeProductParams l_eqProductParams3 = new EqtypeProductParams();
            l_eqProductParams3.setProductId(3304148080003L);
            l_eqProductParams3.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams3.setInstitutionCode("A13");
            l_eqProductParams3.setProductCode("A13");
            l_eqProductParams3.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams3);
            
            EqtypeProductParams l_eqProductParams4 = new EqtypeProductParams();
            l_eqProductParams4.setProductId(3304148080004L);
            l_eqProductParams4.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams4.setInstitutionCode("A14");
            l_eqProductParams4.setProductCode("A14");
            l_eqProductParams4.setStandardName("名前A14");
            l_eqProductParams4.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams4);
            
            EqtypeProductParams l_eqProductParams5 = new EqtypeProductParams();
            l_eqProductParams5.setProductId(3304148080005L);
            l_eqProductParams5.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams5.setInstitutionCode("A15");
            l_eqProductParams5.setProductCode("A15");
            l_eqProductParams5.setStandardName("名前A15");
            l_eqProductParams5.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams5);
                                    
            TraderParams l_traderParams1 = TestDBUtility.getTraderRow();
            l_traderParams1.setTraderId(3338111130L);
            l_traderParams1.setTraderCode("52125");
            TestDBUtility.insertWithDel(l_traderParams1);

            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams2.setOrderId(22334455L);
            l_eqtypeOrderUnitParams2.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams2.setProductId(3304148080002L);
            l_eqtypeOrderUnitParams2.setMarketId(3304L);
            l_eqtypeOrderUnitParams2.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams2.setSonarTradedCode(WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET);
            l_eqtypeOrderUnitParams2.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams2.setSwapTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams2.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams2.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams2.setOrderConditionType(WEB3OrderingConditionDef.STOP_LIMIT_PRICE);      
            l_eqtypeOrderUnitParams2.setStopOrderPrice(2000);
            l_eqtypeOrderUnitParams2.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            l_eqtypeOrderUnitParams2.setOrgOrderConditionType("1");
            l_eqtypeOrderUnitParams2.setExecutedAmount(2300);
            l_eqtypeOrderUnitParams2.setExecutedQuantity(527);
            l_eqtypeOrderUnitParams2.setOrgStopOrderPrice(2200);
            l_eqtypeOrderUnitParams2.setOrgOrderCondOperator("1");
            l_eqtypeOrderUnitParams2.setBranchId(33382L);
            l_eqtypeOrderUnitParams2.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            l_eqtypeOrderUnitParams2.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            l_eqtypeOrderUnitParams2.setFirstOrderUnitId(123789L);
            l_eqtypeOrderUnitParams2.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            l_eqtypeOrderUnitParams2.setOrderChanel("0");
            l_eqtypeOrderUnitParams2.setTraderId(3338111157L);
            l_eqtypeOrderUnitParams2.setExpirationDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams2.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams3= TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams3.setOrderUnitId(3304148080003L);
            l_eqtypeOrderUnitParams3.setOrderId(33445566L);
            l_eqtypeOrderUnitParams3.setProductId(3304148080003L);
            l_eqtypeOrderUnitParams3.setMarketId(3305L);
            l_eqtypeOrderUnitParams3.setSonarTradedCode(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
            l_eqtypeOrderUnitParams3.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams3.setBranchId(33383L);
            l_eqtypeOrderUnitParams3.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            l_eqtypeOrderUnitParams3.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams3);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams4= TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams4.setOrderUnitId(3304148080004L);
            l_eqtypeOrderUnitParams4.setOrderId(44556677L);
            l_eqtypeOrderUnitParams4.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams4.setProductId(3304148080004L);
            l_eqtypeOrderUnitParams4.setMarketId(3306L);
            l_eqtypeOrderUnitParams4.setSonarTradedCode(WEB3TransactionTypeSONARDef.SWAP_CONTRACT);
            l_eqtypeOrderUnitParams4.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
            l_eqtypeOrderUnitParams4.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            l_eqtypeOrderUnitParams4.setStopOrderPrice(4000);
            l_eqtypeOrderUnitParams4.setWLimitPrice(0);
            l_eqtypeOrderUnitParams4.setLimitPrice(0);
            l_eqtypeOrderUnitParams4.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            l_eqtypeOrderUnitParams4.setOrgOrderConditionType("2");
            l_eqtypeOrderUnitParams4.setOrgStopOrderPrice(2400);
            l_eqtypeOrderUnitParams4.setOrgOrderCondOperator("1");
            l_eqtypeOrderUnitParams4.setExecutedAmount(5210);
            l_eqtypeOrderUnitParams4.setExecutedQuantity(435);
            l_eqtypeOrderUnitParams4.setBranchId(33384L);
            l_eqtypeOrderUnitParams4.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            l_eqtypeOrderUnitParams4.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            l_eqtypeOrderUnitParams4.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            l_eqtypeOrderUnitParams4.setExpirationDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams4.setOrderChanel("2");
            l_eqtypeOrderUnitParams4.setBizDate("20070118");
            l_eqtypeOrderUnitParams4.setTraderId(3338111157L);
            l_eqtypeOrderUnitParams4.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams4.setConfirmedExecConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams4);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams5= TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams5.setOrderUnitId(3304148080005L);
            l_eqtypeOrderUnitParams5.setOrderId(55667788L);
            l_eqtypeOrderUnitParams5.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_eqtypeOrderUnitParams5.setProductId(3304148080005L);
            l_eqtypeOrderUnitParams5.setMarketId(3307L);
            l_eqtypeOrderUnitParams5.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams5.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            l_eqtypeOrderUnitParams5.setStopOrderPrice(5000);
            l_eqtypeOrderUnitParams5.setWLimitPrice(3000);
            l_eqtypeOrderUnitParams5.setOrderCondOperator(WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE);
            l_eqtypeOrderUnitParams5.setOrgOrderConditionType("2");
            l_eqtypeOrderUnitParams5.setOrgStopOrderPrice(2500);
            l_eqtypeOrderUnitParams5.setOrgOrderCondOperator("1");
            l_eqtypeOrderUnitParams5.setBranchId(33385L);
            l_eqtypeOrderUnitParams5.setExecutedQuantity(0);
            l_eqtypeOrderUnitParams5.setModifyCancelType(WEB3ModifyCancelTypeDef.PART_CANCELED);
            l_eqtypeOrderUnitParams5.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            l_eqtypeOrderUnitParams5.setPriceConditionType(WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER);
            l_eqtypeOrderUnitParams5.setExpirationDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams5.setOrderChanel("3");
            l_eqtypeOrderUnitParams5.setBizDate("20070119");
            l_eqtypeOrderUnitParams5.setTraderId(3338111157L);
            l_eqtypeOrderUnitParams5.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams5.setConfirmedExecConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams5);
            
            EqtypeOrderParams l_eqtypeOrderParams1 = new EqtypeOrderParams();
            l_eqtypeOrderParams1.setOrderId(123456789L);
            l_eqtypeOrderParams1.setSubAccountId(333812512203L);
            l_eqtypeOrderParams1.setAccountId(333812512203L);
            l_eqtypeOrderParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeOrderParams1);
            
            EqtypeOrderParams l_eqtypeOrderParams2 = new EqtypeOrderParams();
            l_eqtypeOrderParams2.setOrderId(22334455L);
            l_eqtypeOrderParams2.setSubAccountId(333812512203L);
            l_eqtypeOrderParams2.setAccountId(333812512203L);
            l_eqtypeOrderParams2.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeOrderParams2);
            
            EqtypeOrderParams l_eqtypeOrderParams3 = new EqtypeOrderParams();
            l_eqtypeOrderParams3.setOrderId(33445566L);
            l_eqtypeOrderParams3.setSubAccountId(333812512203L);
            l_eqtypeOrderParams3.setAccountId(333812512203L);
            l_eqtypeOrderParams3.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams3.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderParams3.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeOrderParams3);
            
            EqtypeOrderParams l_eqtypeOrderParams4 = new EqtypeOrderParams();
            l_eqtypeOrderParams4.setOrderId(44556677L);
            l_eqtypeOrderParams4.setSubAccountId(333812512203L);
            l_eqtypeOrderParams4.setAccountId(333812512203L);
            l_eqtypeOrderParams4.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams4.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderParams4.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeOrderParams4);
            
            EqtypeOrderParams l_eqtypeOrderParams5 = new EqtypeOrderParams();
            l_eqtypeOrderParams5.setOrderId(55667788L);
            l_eqtypeOrderParams5.setSubAccountId(333812512203L);
            l_eqtypeOrderParams5.setAccountId(333812512203L);
            l_eqtypeOrderParams5.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderParams5.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderParams5.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqtypeOrderParams5);
            
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            
            l_context.setTradingTimeType("03");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());

            l_context.setTradingTimeType("19");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());

        }
        catch (WEB3BaseException l_web3BaseEx)
        {
            log.error(STR_METHOD_NAME, l_web3BaseEx);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
   private void getEqTypeOrderUnits()
    {
        final String STR_METHOD_NAME = "getEqTypeOrderUnits()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            EqTypeOrderUnit l_orderUnit1 = new EqTypeOrderUnitImpl(null, 3304148080001L);
            this.l_lisRecords.add(l_orderUnit1);
            EqTypeOrderUnit l_orderUnit2 = new EqTypeOrderUnitImpl(null, 3304148080002L);
            this.l_lisRecords.add(l_orderUnit2);
            EqTypeOrderUnit l_orderUnit3 = new EqTypeOrderUnitImpl(null, 3304148080003L);
            this.l_lisRecords.add(l_orderUnit3);
            EqTypeOrderUnit l_orderUnit4 = new EqTypeOrderUnitImpl(null, 3304148080004L);
            this.l_lisRecords.add(l_orderUnit4);
            EqTypeOrderUnit l_orderUnit5 = new EqTypeOrderUnitImpl(null, 3304148080005L);
            this.l_lisRecords.add(l_orderUnit5);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
   private void getRequestValue()
    {
        final String STR_METHOD_NAME = "getEqTypeOrderUnits()";
        log.entering(TEST_START + STR_METHOD_NAME);
        l_request.execType = "1";
        l_request.referenceType = "1";
        l_request.pageIndex = "1";
        l_request.pageSize = "3";
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
   private void getDataMock()
    {
        final String STR_METHOD_NAME = "getDataMock()";
        log.entering(TEST_START + STR_METHOD_NAME);
        this.getData();
        this.getEqTypeOrderUnits();
        this.getRequestValue();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long(333812512203L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount",
                    "isMarginAccountEstablished",
                    new Class[] {String.class},
                    new Boolean(true));
            
            MainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512203L);
            SubAccount l_subAccount = new WEB3GentradeSubAccountForMock(333812512203L,33381251220301L);
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getSubAccount",
                    new Class[] {long.class,SubAccountTypeEnum.class},
                    l_subAccount);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "isMarginTradeEnforcement",
                    new Class[] {String.class},
                    new Boolean(true));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getWeb3GenBranch",
                    new Class[] {},
                    l_branch);
        
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[] {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                            String.class },
                            this.l_lisRecords);
                                    
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.equity.WEB3EquityOrderManager",
//                    "validateTradedProduct",
//                    new Class[] {EqTypeProduct.class,Market.class},
//                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateHandlingMarket",
                    new Class[] {WEB3GentradeBranch.class,
                            WEB3EquityTradedProduct.class,
                            String.class,
                            String.class,
                            double.class}, "");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class},
                    "");
            
            log.debug("this.l_lisRecords.size()(before) "+this.l_lisRecords.size());
            Iterator l_itRecords = l_lisRecords.iterator();
            if (!WEB3StringTypeUtility.isEmpty(this.l_request.execType))
            {
                while (l_itRecords.hasNext())
                {
                    //is指定約定状態 == false
                    if (!this.l_serviceImpl.isDesignateExecutedStatus
                            (l_request.execType, (EqTypeOrderUnit) l_itRecords.next()))
                    {
                        l_itRecords.remove();
                    }
                }

            }
          List l_lisTransactions = new ArrayList();
          l_lisTransactions.add(STR_METHOD_NAME);

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityFinTransactionManager",
                "getTransactions", new Class[] { EqTypeOrderUnit.class },
                l_lisTransactions);
          
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityFinTransactionManager",
                "getEstimatedProfitLoss",
                new Class[] { List.class },
                "20.6");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getEstimateDeliveryAmountForClose",
                new Class[] {EqTypeOrderUnit.class},
                new Double(200L));

            this.l_arrOrderUnit =
                (EqTypeOrderUnit[]) this.l_lisRecords.toArray(new EqTypeOrderUnit[this.l_lisRecords.size()]);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "removeCarryOverOriginalOrderUnit",
                    new Class[] {EqTypeOrderUnit[].class},
                    l_arrOrderUnit);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOffFloorOrderPossible",
                    new Class[] {long.class, long.class, SubAccount.class},
                    null);

        }
        catch (Exception l_e)
        {
            log.error(STR_METHOD_NAME, l_e);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3EquityMarginExecuteReferenceServiceImplForTest extends
            WEB3EquityMarginExecuteReferenceServiceImpl
    {

        public String createQueryString(String l_strProductCode, String l_strMarketCode, Date l_datOrderBizDate,
                OrderStatus l_orderStatus) throws WEB3BaseException
        {
            return "createQueryStringForMock";
        }

        public Object[] createQueryDataContainer(WEB3GentradeSubAccount l_subAccount, String l_strProductCode,
                String l_strMarketCode, Date l_datOrderBizDate, OrderStatus l_orderStatus) throws WEB3BaseException
        {
            Object[] obj = { "createQueryDataContainerForMock" };
            return obj;
        }

        public String createSortCond(WEB3EquityMarginSortKey[] l_sortKeys) throws WEB3BaseException
        {
            return "createSortCondForMock";
        }

        public boolean isDesignateExecutedStatus(String l_strExecutedStatus, EqTypeOrderUnit l_orderUnit)
        {

            if (OrderExpirationStatusEnum.EXPIRED.equals(l_orderUnit.getExpirationStatus()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        
        public boolean isChangeCancelEnableTimeZone(EqTypeOrderUnit l_orderUnit)
        {
             EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        
        public void validateOrderForChangeability(WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
                WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit)
                throws WEB3BaseException
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            if(OrderTypeEnum.EQUITY_SELL.equals(l_orderUnitRow.getOrderType()))
            {
                throw new WEB3BaseException(new ErrorInfo(),"validateOrderForChangeability");
            }
        }
        
        public WEB3EquityMarginExecuteUnit[] createExecuteUnits(EqTypeOrderUnit l_orderUnit)
        {
            WEB3EquityMarginExecuteUnit[] l_MarginExecuteUnit = new WEB3EquityMarginExecuteUnit[3];
            l_MarginExecuteUnit[0] = new WEB3EquityMarginExecuteUnit();
            l_MarginExecuteUnit[0].execPrice ="100";
            l_MarginExecuteUnit[0].executionTimestamp = WEB3DateUtility.getDate("20070102","yyyyMMdd");
            l_MarginExecuteUnit[0].execQuantity = "200";
            
            l_MarginExecuteUnit[1] = new WEB3EquityMarginExecuteUnit();
            l_MarginExecuteUnit[1].execPrice ="101";
            l_MarginExecuteUnit[1].executionTimestamp = WEB3DateUtility.getDate("20070103","yyyyMMdd");
            l_MarginExecuteUnit[1].execQuantity = "201";
            
            l_MarginExecuteUnit[2] = new WEB3EquityMarginExecuteUnit();
            l_MarginExecuteUnit[2].execPrice ="102";
            l_MarginExecuteUnit[2].executionTimestamp = WEB3DateUtility.getDate("20070104","yyyyMMdd");
            l_MarginExecuteUnit[2].execQuantity = "202";

            
            return l_MarginExecuteUnit;
            
        }
        
        public Date getOpenDate(EqTypeOrderUnit l_orderUnit)throws WEB3BaseException
        {
            return WEB3DateUtility.getDate("20070101","yyyyMMdd");
        }
        
        public String getContractPrice(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
        {
            return "2000";
        }
    }
}
@
