head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityTypeOrderManagerReusableValidationsTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式発注審査個別チェック(WEB3EquityTypeOrderManagerReusableValidationsTest.java)
Revesion History : 2007/06/08 何文敏 (中訊)
*/
package webbroker3.equity;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.define.WEB3EquityBackServiceOnlineDef;
import webbroker3.equity.define.WEB3MarginAttributeNameDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingCalendarModelImplForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchListmarketDealtCondParams;
import webbroker3.gentrade.data.BranchListmarketDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.InstBranchProductParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.define.WEB3GentradePriceCondDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3EquityTypeOrderManagerReusableValidationsTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityTypeOrderManagerReusableValidationsTest.class);
    WEB3EquityTypeOrderManagerReusableValidations l_validation =
        new WEB3EquityTypeOrderManagerReusableValidations();

    public WEB3EquityTypeOrderManagerReusableValidationsTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateOrderForChangeability_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            l_eqtypeOrderUnitParams.setApproveStatusType("2");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForChangeability(l_order, true);
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals("代理入力者は、未承認の強制決済注文は訂正できません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateOrderForChangeability_Case0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            l_eqtypeOrderUnitParams.setApproveStatusType("2");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(333811223L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForChangeability(l_order, true);
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals("代理入力者は、未承認の強制決済注文は訂正できません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderForChangeability_Case0003()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForChangeability(l_order, true);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderForChangeability_Case0004()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(333811223L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForChangeability(l_order, true);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderForCancellation_Case0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            l_eqtypeOrderUnitParams.setApproveStatusType("2");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals("代理入力者は、未承認の強制決済注文は訂正できません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateOrderForCancellation_Case0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            l_eqtypeOrderUnitParams.setApproveStatusType("2");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(333811223L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals("代理入力者は、未承認の強制決済注文は訂正できません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderForCancellation_Case0003()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_Case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForCancellation(l_order);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderForCancellation_Case0004()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_Case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET_IN_OUT);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setApproveStatusType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(333811223L));
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);

            Order l_order = l_validation.validateOrderIdForExistence(123456789L);
            l_validation.validateOrderForCancellation(l_order);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    public void testValidateCloseDateExcess_Case0001()
//    {
//        final String STR_METHOD_NAME = "testValidateCloseDateExcess_Case0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
//            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
//            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_eqtypeContractParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
//
//            WEB3EquityPositionManager l_positionManager =
//                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
//            WEB3EquityContract l_contract =
//                (WEB3EquityContract) l_positionManager.getContract(2134566345L);
//            l_validation.validateCloseDateExcess(l_contract);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00748, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidateCloseDateExcess_Case0002()
//    {
//        final String STR_METHOD_NAME = "testValidateCloseDateExcess_Case0002()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
//            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
//            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_eqtypeContractParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
//
//            WEB3EquityPositionManager l_positionManager =
//                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
//            WEB3EquityContract l_contract =
//                (WEB3EquityContract) l_positionManager.getContract(2134566345L);
//            l_validation.validateCloseDateExcess(l_contract);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidateCloseDateExcess_Case0003()
//    {
//        final String STR_METHOD_NAME = "testValidateCloseDateExcess_Case0003()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
//            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
//            l_eqtypeContractParams.setCloseDate(WEB3DateUtility.getDate("20040203","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_eqtypeContractParams);
//
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
//
//            WEB3EquityPositionManager l_positionManager =
//                (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
//            WEB3EquityContract l_contract =
//                (WEB3EquityContract) l_positionManager.getContract(2134566345L);
//            l_validation.validateCloseDateExcess(l_contract);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidateManualForcedSettleFlag_Case0001()
//    {
//        final String STR_METHOD_NAME = "testValidateManualForcedSettleFlag_Case0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
//            
////            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
////            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
////            Trader l_trader = l_finObjMgr.getTraderByLoginId(333811123L);
//            l_validation.validateManualForcedSettlePossible(false, null);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidateManualForcedSettleFlag_Case0002()
//    {
//        final String STR_METHOD_NAME = "testValidateManualForcedSettleFlag_Case0002()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
//            
////            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
////            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
////            Trader l_trader = l_finObjMgr.getTraderByLoginId(333811123L);
//            l_validation.validateManualForcedSettlePossible(true, null);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02809, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidateManualForcedSettleFlag_Case0003()
//    {
//        final String STR_METHOD_NAME = "testValidateManualForcedSettleFlag_Case0003()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
//            
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
//            Trader l_trader = l_finObjMgr.getTraderByLoginId(3338111123L);
//            l_validation.validateManualForcedSettlePossible(true, l_trader);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    

    public void test_validateOrderCondition_C0001()
    {
        final String STR_METHOD_NAME = " test_validateOrderCondition_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(1L);
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33l);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarketCode("3");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303l);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1001L);
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20070701","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setTradedProductId(1001L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1001L);
            l_eqtypeTradedProductParams.setProductId(1001L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
            long l_lngOrderUnitId = 1L;
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
            EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
            boolean l_isCarriedOrder = false;
            String l_strMarginTradeType = null;
            String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
            String l_strMarketCode = "3";
            WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                    l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                    l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                    l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
        }
        catch (Exception l_ex)
        {
            log.error("ssoss");
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    public void test_validateOrderCondition_C0002()
    {
        final String STR_METHOD_NAME = " test_validateOrderCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,12);// 注意月?要減1
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc." + 
                    "gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                st);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",st);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",st);
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setBizDateType("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(1L);
            l_subAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33l);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
            l_enableOrderConditionParams.setMarketCode("3");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303l);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountRow);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setTradedProductId(1001L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1001L);
            l_eqtypeTradedProductParams.setProductId(1001L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
            long l_lngOrderUnitId = 1L;
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            Date l_datOrderBizDate = null;
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
            String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
            EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
            boolean l_isCarriedOrder = false;
            String l_strMarginTradeType = null;
            String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
            String l_strMarketCode = "3";
            WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                    l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                    l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                    l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


   public void test_validateOrderCondition_C0003()
   {
       final String STR_METHOD_NAME = " test_validateOrderCondition_C0003()";
       log.entering(TEST_START + STR_METHOD_NAME);

       try
       {
           TestDBUtility.deleteAll(BranchParams.TYPE);
           BranchParams l_branchParams = TestDBUtility.getBranchRow();
           l_branchParams.setBranchId(33381);
           TestDBUtility.insertWithDel(l_branchParams);

           TestDBUtility.deleteAll(SubAccountParams.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setInstitutionId(1L);
           l_subAccountParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_subAccountParams);

           TestDBUtility.deleteAll(SubAccountParams.TYPE);
           InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
           l_institutionParams.setInstitutionId(33l);
           l_institutionParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_institutionParams);

           TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
           EnableOrderConditionParams l_enableOrderConditionParams =
               TestDBUtility.getEnableOrderConditionParamsRow();
           l_enableOrderConditionParams.setInstitutionCode("0D");
           l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
           l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
           l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
           l_enableOrderConditionParams.setMarketCode("3");
           l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(
               WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES);
           TestDBUtility.insertWithDel(l_enableOrderConditionParams);

           TestDBUtility.deleteAll(MarketParams.TYPE);
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           l_marketParams.setMarketId(3303l);
           TestDBUtility.insertWithDel(l_marketParams);

           TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
           EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
           l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
           l_eqtypeProductParams.setProductId(1001L);
           TestDBUtility.insertWithDel(l_eqtypeProductParams);

           TestDBUtility.deleteAll(ProductParams.TYPE);
           ProductParams l_productParams = TestDBUtility.getProductRow();
           l_productParams.setProductId(1001L);
           l_productParams.setProductType(ProductTypeEnum.EQUITY);
           TestDBUtility.insertWithDel(l_productParams);
           
           TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
           BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
           l_branchPreferencesParams.setValue("1");
           l_branchPreferencesParams.setBranchId(33381);
           l_branchPreferencesParams.setName("eqtype.final.day.with.right");
           l_branchPreferencesParams.setNameSerialNo(1);
           TestDBUtility.insertWithDel(l_branchPreferencesParams);

           TestDBUtility.deleteAll(MainAccountParams.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(333812512203L);
           TestDBUtility.insertWithDel(l_mainAccountParams);

           TestDBUtility.deleteAll(SubAccountParams.TYPE);
           SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
           TestDBUtility.insertWithDel(l_subAccountRow);

           TestDBUtility.deleteAll(TradedProductParams.TYPE);
           TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
           l_tradedProductParams.setProductId(1001L);
           l_tradedProductParams.setTradedProductId(1001L);
           l_tradedProductParams.setMarketId(3303L);
           l_tradedProductParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_tradedProductParams);

           TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
           EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
           l_eqtypeTradedProductParams.setTradedProductId(1001L);
           l_eqtypeTradedProductParams.setProductId(1001L);
           l_eqtypeTradedProductParams.setMarketId(3303);
           TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

           WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
           long l_lngOrderUnitId = 1L;
           WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
           Date l_datOrderBizDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
           Date l_datExpirationDate = WEB3DateUtility.getDate("20070619","yyyyMMdd");
           String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
           EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
           boolean l_isCarriedOrder = true;
           String l_strMarginTradeType = null;
           String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
           String l_strMarketCode = "3";
           WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
               new WEB3EquityTypeOrderManagerReusableValidations();
           l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                   l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                   l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                   l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
       }
       catch (WEB3BusinessLayerException l_web3SystemException)
       {
           assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02836,l_web3SystemException.getErrorInfo());
       }
       catch (Exception l_ex)
       {
           log.error(STR_METHOD_NAME, l_ex);
           fail();
       }

       log.exiting(TEST_END + STR_METHOD_NAME);
   }


  public void test_validateOrderCondition_C0004()
  {
      final String STR_METHOD_NAME = " test_validateOrderCondition_C0004()";
      log.entering(TEST_START + STR_METHOD_NAME);

      try
      {
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          l_branchParams.setBranchId(33381);
          TestDBUtility.insertWithDel(l_branchParams);

          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setInstitutionId(1L);
          l_subAccountParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_subAccountParams);

          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionId(33l);
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_institutionParams);

          TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
          EnableOrderConditionParams l_enableOrderConditionParams =
              TestDBUtility.getEnableOrderConditionParamsRow();
          l_enableOrderConditionParams.setInstitutionCode("0D");
          l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
          l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
          l_enableOrderConditionParams.setMarginTradingDiv(WEB3MarginTradingDivDef.DEFAULT);
          l_enableOrderConditionParams.setMarketCode("3");
          l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(
              WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES);
          TestDBUtility.insertWithDel(l_enableOrderConditionParams);

          TestDBUtility.deleteAll(MarketParams.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          l_marketParams.setMarketId(3303l);
          TestDBUtility.insertWithDel(l_marketParams);

          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeProductParams.setProductId(1001L);
          TestDBUtility.insertWithDel(l_eqtypeProductParams);

          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductId(1001L);
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
          BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
          l_branchPreferencesParams.setValue("1");
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("eqtype.final.day.with.right");
          l_branchPreferencesParams.setNameSerialNo(1);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountId(333812512203L);
          TestDBUtility.insertWithDel(l_mainAccountParams);

          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
          TestDBUtility.insertWithDel(l_subAccountRow);

          TestDBUtility.deleteAll(TradedProductParams.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductId(1001L);
          l_tradedProductParams.setTradedProductId(1001L);
          l_tradedProductParams.setMarketId(3303L);
          l_tradedProductParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradedProductParams);

          TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setTradedProductId(1001L);
          l_eqtypeTradedProductParams.setProductId(1001L);
          l_eqtypeTradedProductParams.setMarketId(3303);
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountRow);
          long l_lngOrderUnitId = 1L;
          WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
          Date l_datOrderBizDate = WEB3DateUtility.getDate("20070613","yyyyMMdd");
          Date l_datExpirationDate = WEB3DateUtility.getDate("20070613","yyyyMMdd");
          String l_strOrderConditionType = WEB3OrderingConditionDef.DEFAULT;
          EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_OPEN;
          boolean l_isCarriedOrder = true;
          String l_strMarginTradeType = null;
          String l_strPriceConditionType = WEB3GentradePriceCondDef.DEFAULT;
          String l_strMarketCode = "3";
          WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          l_equityTypeOrderManagerReusableValidations.validateOrderCondition(        
                  l_subAccount, l_lngOrderUnitId, l_tradedProduct, l_datOrderBizDate,
                  l_datExpirationDate, l_strOrderConditionType, l_executionCondition, l_isCarriedOrder,
                  l_strMarginTradeType, l_strPriceConditionType, l_strMarketCode);
      }
      catch (Exception l_ex)
      {
          log.error(STR_METHOD_NAME, l_ex);
          fail();
      }

      log.exiting(TEST_END + STR_METHOD_NAME);
   }

  public void testValidateMaxOpenMarginQuantity_case0001()
  {
      final String STR_METHOD_NAME = "testValidateMaxOpenMarginQuantity_case0001()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(false);
          TestDBUtility.deleteAll(MarketRow.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512246L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          
          TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
          l_eqtypeTradedProductParams.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          
          TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
          BranchListmarketDealtCondParams l_branchListmarketDealtCond = new BranchListmarketDealtCondParams();
          l_branchListmarketDealtCond.setBranchId(33381L);
          l_branchListmarketDealtCond.setCreatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setListmarketName("tokyo");
          l_branchListmarketDealtCond.setListType("1");
          l_branchListmarketDealtCond.setMarginSecCheckRate(0.12);
          l_branchListmarketDealtCond.setMarketId(l_marketParams.getMarketId());
          l_branchListmarketDealtCond.setMaxContPriceCorp(12);
          l_branchListmarketDealtCond.setMaxContPriceInd(13);
          l_branchListmarketDealtCond.setMaxContUnitCorp(10);
          l_branchListmarketDealtCond.setNewListType("1");
          l_branchListmarketDealtCond.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_branchListmarketDealtCond);
          
          TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
          
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams1.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams1.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams1.setProductId(l_productParams.getProductId());
          l_eqtypeOrderUnitParams1.setOrderUnitId(33041480801L);
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
          
          TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
          EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
          l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeContractParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeContractParams.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeContractParams.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeContractParams.setProductId(l_productParams.getProductId());
          l_eqtypeContractParams.setQuantity(100);
          TestDBUtility.insertWithDel(l_eqtypeContractParams);
          
          WEB3EquityTypeOrderManagerReusableValidations l_validations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          
          MainAccount mainAccount = null;
          FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
          AccountManager l_accountMgr = l_finApp.getAccountManager();
          mainAccount = l_accountMgr.getMainAccount(l_mainAccountParams.getAccountId());

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(mainAccount, l_subAccountParams);
          TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
          WEB3EquityProductManager l_productManager =
              (WEB3EquityProductManager)l_tradingModule.getProductManager();
          WEB3GentradeFinObjectManager l_finObjectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
          double l_dblQuantity = 100;
          OrderTypeEnum l_orderType = OrderTypeEnum.ASSET_IN;
          WEB3EquityTradedProduct l_tradedProduct = null;
          Market l_market = null;
          
          Product l_product = l_productManager.getProduct(l_productParams.getProductId());
          l_market = l_finObjectManager.getMarket(l_marketParams.getMarketId());
          l_tradedProduct =
              (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_product, l_market);

          EqtypeOrderUnitRow l_changeOrderUnit = l_eqtypeOrderUnitParams;
          
          l_validations.validateMaxOpenMarginQuantity(
              l_subAccount,
              l_dblQuantity,
              l_orderType,
              l_tradedProduct,
              l_changeOrderUnit);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(STR_METHOD_NAME);
  }
  
  public void testValidateMaxOpenMarginQuantity_case0002()
  {
      final String STR_METHOD_NAME = "testValidateMaxOpenMarginQuantity_case0002()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(false);
          TestDBUtility.deleteAll(MarketRow.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512246L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          
          TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
          l_eqtypeTradedProductParams.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          
          TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
          BranchListmarketDealtCondParams l_branchListmarketDealtCond = new BranchListmarketDealtCondParams();
          l_branchListmarketDealtCond.setBranchId(33381L);
          l_branchListmarketDealtCond.setCreatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setListmarketName("tokyo");
          l_branchListmarketDealtCond.setListType("1");
          l_branchListmarketDealtCond.setMarginSecCheckRate(0.12);
          l_branchListmarketDealtCond.setMarketId(l_marketParams.getMarketId());
          l_branchListmarketDealtCond.setMaxContPriceCorp(12);
          l_branchListmarketDealtCond.setMaxContPriceInd(13);
//          l_branchListmarketDealtCond.setMaxContUnitCorp(10);
          l_branchListmarketDealtCond.setNewListType("1");
          l_branchListmarketDealtCond.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_branchListmarketDealtCond);
          
          TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
          EqtypeOrderUnitParams l_EqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
          l_eqtypeOrderUnitParams.setRepaymentType("1");
          l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
          l_eqtypeOrderUnitParams.setForcedExpireType("5");
          TestDBUtility.insertWithDel(l_EqtypeOrderUnitParams);
          
          WEB3EquityTypeOrderManagerReusableValidations l_validations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          
          MainAccount mainAccount = null;
          FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
          AccountManager l_accountMgr = l_finApp.getAccountManager();
          mainAccount = l_accountMgr.getMainAccount(l_mainAccountParams.getAccountId());

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(mainAccount, l_subAccountParams);
          TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
          WEB3EquityProductManager l_productManager =
              (WEB3EquityProductManager)l_tradingModule.getProductManager();
          WEB3GentradeFinObjectManager l_finObjectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
          double l_dblQuantity = 100;
          OrderTypeEnum l_orderType = OrderTypeEnum.ASSET_IN;
          WEB3EquityTradedProduct l_tradedProduct = null;
          Market l_market = null;
          
          Product l_product = l_productManager.getProduct(l_productParams.getProductId());
          l_market = l_finObjectManager.getMarket(l_marketParams.getMarketId());
          l_tradedProduct =
              (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_product, l_market);

          EqtypeOrderUnitRow l_changeOrderUnit = l_EqtypeOrderUnitParams;
          
          l_validations.validateMaxOpenMarginQuantity(
              l_subAccount,
              l_dblQuantity,
              l_orderType,
              l_tradedProduct,
              l_changeOrderUnit);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(STR_METHOD_NAME);
  }
  
  public void testValidateMaxOpenMarginQuantity_case0003()
  {
      final String STR_METHOD_NAME = "testValidateMaxOpenMarginQuantity_case0003()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(false);
          TestDBUtility.deleteAll(MarketRow.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512246L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          
          TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
          l_eqtypeTradedProductParams.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          
          TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
          BranchListmarketDealtCondParams l_branchListmarketDealtCond = new BranchListmarketDealtCondParams();
          l_branchListmarketDealtCond.setBranchId(33381L);
          l_branchListmarketDealtCond.setCreatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setListmarketName("tokyo");
          l_branchListmarketDealtCond.setListType("1");
          l_branchListmarketDealtCond.setMarginSecCheckRate(0.12);
          l_branchListmarketDealtCond.setMarketId(l_marketParams.getMarketId());
          l_branchListmarketDealtCond.setMaxContPriceCorp(12);
          l_branchListmarketDealtCond.setMaxContPriceInd(13);
          l_branchListmarketDealtCond.setMaxContUnitCorp(100);
          l_branchListmarketDealtCond.setNewListType("1");
          l_branchListmarketDealtCond.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_branchListmarketDealtCond);
          
          TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
          
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams1.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams1.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams1.setProductId(l_productParams.getProductId());
          l_eqtypeOrderUnitParams1.setOrderUnitId(33041480801L);
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
          
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams2.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams2.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams2.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams2.setProductId(l_productParams.getProductId());
          l_eqtypeOrderUnitParams2.setConfirmedQuantity(100);
          l_eqtypeOrderUnitParams2.setOrderUnitId(33041480801122L);
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
          
          TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
          EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
          l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeContractParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeContractParams.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeContractParams.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeContractParams.setProductId(l_productParams.getProductId());
          l_eqtypeContractParams.setQuantity(100);
          TestDBUtility.insertWithDel(l_eqtypeContractParams);
          
          EqtypeContractParams l_eqtypeContractParams1 = TestDBUtility.getEqtypeContractRow();
          l_eqtypeContractParams1.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeContractParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeContractParams1.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeContractParams1.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeContractParams1.setProductId(l_productParams.getProductId());
          l_eqtypeContractParams1.setQuantity(100);
          l_eqtypeContractParams1.setContractId(2134566300L);
          TestDBUtility.insertWithDel(l_eqtypeContractParams1);
          
          WEB3EquityTypeOrderManagerReusableValidations l_validations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          
          MainAccount mainAccount = null;
          FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
          AccountManager l_accountMgr = l_finApp.getAccountManager();
          mainAccount = l_accountMgr.getMainAccount(l_mainAccountParams.getAccountId());

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(mainAccount, l_subAccountParams);
          TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
          WEB3EquityProductManager l_productManager =
              (WEB3EquityProductManager)l_tradingModule.getProductManager();
          WEB3GentradeFinObjectManager l_finObjectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
          double l_dblQuantity = 100;
          OrderTypeEnum l_orderType = OrderTypeEnum.ASSET_IN;
          WEB3EquityTradedProduct l_tradedProduct = null;
          Market l_market = null;
          
          Product l_product = l_productManager.getProduct(l_productParams.getProductId());
          l_market = l_finObjectManager.getMarket(l_marketParams.getMarketId());
          l_tradedProduct =
              (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_product, l_market);

          EqtypeOrderUnitRow l_changeOrderUnit = l_eqtypeOrderUnitParams;
          
          l_validations.validateMaxOpenMarginQuantity(
              l_subAccount,
              l_dblQuantity,
              l_orderType,
              l_tradedProduct,
              l_changeOrderUnit);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(STR_METHOD_NAME);
  }
  
  public void testValidateMaxOpenMarginQuantity_case0004()
  {
      final String STR_METHOD_NAME = "testValidateMaxOpenMarginQuantity_case0004()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(false);
          TestDBUtility.deleteAll(MarketRow.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512246L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          
          TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
          l_eqtypeTradedProductParams.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          
          TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
          BranchListmarketDealtCondParams l_branchListmarketDealtCond = new BranchListmarketDealtCondParams();
          l_branchListmarketDealtCond.setBranchId(33381L);
          l_branchListmarketDealtCond.setCreatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setListmarketName("tokyo");
          l_branchListmarketDealtCond.setListType("1");
          l_branchListmarketDealtCond.setMarginSecCheckRate(0.12);
          l_branchListmarketDealtCond.setMarketId(l_marketParams.getMarketId());
          l_branchListmarketDealtCond.setMaxContPriceCorp(12);
          l_branchListmarketDealtCond.setMaxContPriceInd(13);
          l_branchListmarketDealtCond.setMaxContUnitCorp(10);
          l_branchListmarketDealtCond.setNewListType("1");
          l_branchListmarketDealtCond.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_branchListmarketDealtCond);
          
          TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
          
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams1.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams1.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams1.setProductId(l_productParams.getProductId());
          l_eqtypeOrderUnitParams1.setOrderUnitId(33041480801L);
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
          
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeOrderUnitParams2.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeOrderUnitParams2.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeOrderUnitParams2.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeOrderUnitParams2.setProductId(l_productParams.getProductId());
          l_eqtypeOrderUnitParams2.setOrderUnitId(33041480801122L);
          TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);
          
          TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
          EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
          l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeContractParams.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeContractParams.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeContractParams.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeContractParams.setProductId(l_productParams.getProductId());
          l_eqtypeContractParams.setQuantity(100);
          TestDBUtility.insertWithDel(l_eqtypeContractParams);
          
          EqtypeContractParams l_eqtypeContractParams1 = TestDBUtility.getEqtypeContractRow();
          l_eqtypeContractParams1.setProductType(ProductTypeEnum.EQUITY);
          l_eqtypeContractParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
          l_eqtypeContractParams1.setAccountId(l_subAccountParams.getAccountId());
          l_eqtypeContractParams1.setMarketId(l_tradedProductParams.getMarketId());
          l_eqtypeContractParams1.setProductId(l_productParams.getProductId());
          l_eqtypeContractParams1.setQuantity(100);
          l_eqtypeContractParams1.setContractId(2134566300L);
          TestDBUtility.insertWithDel(l_eqtypeContractParams1);
          
          WEB3EquityTypeOrderManagerReusableValidations l_validations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          
          MainAccount mainAccount = null;
          FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
          AccountManager l_accountMgr = l_finApp.getAccountManager();
          mainAccount = l_accountMgr.getMainAccount(l_mainAccountParams.getAccountId());

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(mainAccount, l_subAccountParams);
          TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
          WEB3EquityProductManager l_productManager =
              (WEB3EquityProductManager)l_tradingModule.getProductManager();
          WEB3GentradeFinObjectManager l_finObjectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
          double l_dblQuantity = 100;
          OrderTypeEnum l_orderType = OrderTypeEnum.ASSET_IN;
          WEB3EquityTradedProduct l_tradedProduct = null;
          Market l_market = null;
          
          Product l_product = l_productManager.getProduct(l_productParams.getProductId());
          l_market = l_finObjectManager.getMarket(l_marketParams.getMarketId());
          l_tradedProduct =
              (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_product, l_market);

          EqtypeOrderUnitRow l_changeOrderUnit = l_eqtypeOrderUnitParams;
          
          l_validations.validateMaxOpenMarginQuantity(
              l_subAccount,
              l_dblQuantity,
              l_orderType,
              l_tradedProduct,
              l_changeOrderUnit);
      }
      catch (WEB3BaseException l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02871);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(STR_METHOD_NAME);
  }
  
  public void testValidateMaxOpenMarginQuantity_case0005()
  {
      final String STR_METHOD_NAME = "testValidateMaxOpenMarginQuantity_case0005()";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          MOCK_MANAGER.setIsMockUsed(false);
          TestDBUtility.deleteAll(MarketRow.TYPE);
          MarketParams l_marketParams = TestDBUtility.getMarketRow();
          TestDBUtility.insertWithDel(l_marketParams);
          
          TestDBUtility.deleteAll(BranchParams.TYPE);
          BranchParams l_branchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_branchParams);
          
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
          l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          
          TestDBUtility.deleteAll(SubAccountParams.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(333812512246L);
          l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(ProductParams.TYPE);
          ProductParams l_productParams = TestDBUtility.getProductRow();
          l_productParams.setProductType(ProductTypeEnum.EQUITY);
          TestDBUtility.insertWithDel(l_productParams);
          
          TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
          EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
          TestDBUtility.insertWithDel(l_eqtypeProductParams);
          
          TestDBUtility.deleteAll(TradedProductRow.TYPE);
          TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
          l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
          l_tradedProductParams.setProductId(l_productParams.getProductId());
          TestDBUtility.insertWithDel(l_tradedProductParams);
          
          
          TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
          EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
          l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
          l_eqtypeTradedProductParams.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
          
          TestDBUtility.deleteAll(BranchListmarketDealtCondRow.TYPE);
          BranchListmarketDealtCondParams l_branchListmarketDealtCond = new BranchListmarketDealtCondParams();
          l_branchListmarketDealtCond.setBranchId(33381L);
          l_branchListmarketDealtCond.setCreatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070821", "yyyyMMdd"));
          l_branchListmarketDealtCond.setListmarketName("tokyo");
          l_branchListmarketDealtCond.setListType("1");
          l_branchListmarketDealtCond.setMarginSecCheckRate(0.12);
          l_branchListmarketDealtCond.setMarketId(l_marketParams.getMarketId());
          l_branchListmarketDealtCond.setMaxContPriceCorp(12);
          l_branchListmarketDealtCond.setMaxContPriceInd(13);
          l_branchListmarketDealtCond.setMaxContUnitCorp(10000);
          l_branchListmarketDealtCond.setNewListType("1");
          l_branchListmarketDealtCond.setOpenOtcDiv("0");
          TestDBUtility.insertWithDel(l_branchListmarketDealtCond);
          
          TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
          EqtypeOrderUnitParams l_EqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
          l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
          l_eqtypeOrderUnitParams.setRepaymentType("1");
          l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
          l_eqtypeOrderUnitParams.setForcedExpireType("5");
          TestDBUtility.insertWithDel(l_EqtypeOrderUnitParams);
          
          WEB3EquityTypeOrderManagerReusableValidations l_validations =
              new WEB3EquityTypeOrderManagerReusableValidations();
          
          MainAccount mainAccount = null;
          FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
          AccountManager l_accountMgr = l_finApp.getAccountManager();
          mainAccount = l_accountMgr.getMainAccount(l_mainAccountParams.getAccountId());

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(mainAccount, l_subAccountParams);
          TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
          WEB3EquityProductManager l_productManager =
              (WEB3EquityProductManager)l_tradingModule.getProductManager();
          WEB3GentradeFinObjectManager l_finObjectManager =
              (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
          double l_dblQuantity = 100;
          OrderTypeEnum l_orderType = OrderTypeEnum.ASSET_IN;
          WEB3EquityTradedProduct l_tradedProduct = null;
          Market l_market = null;
          
          Product l_product = l_productManager.getProduct(l_productParams.getProductId());
          l_market = l_finObjectManager.getMarket(l_marketParams.getMarketId());
          l_tradedProduct =
              (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_product, l_market);

          EqtypeOrderUnitRow l_changeOrderUnit = null;

          l_validations.validateMaxOpenMarginQuantity(
              l_subAccount,
              l_dblQuantity,
              l_orderType,
              l_tradedProduct,
              l_changeOrderUnit);
      }
      catch (Exception l_ex)
      {
          log.debug(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(STR_METHOD_NAME);
  }

    /**
     *
     */
    public void testValidateMaxOpenMarginAmount_case0001()
    {
        final String STR_METHOD_NAME = " testValidateMaxOpenMarginAmount_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_validation = new WEB3EquityTypeOrderManagerReusableValidations();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());

        InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
        l_instBranchProductParams.setBranchId(l_mainAccountParams.getBranchId());
        l_instBranchProductParams.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
        l_instBranchProductParams.setPremiumRestraintRate(1L);
        l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070824", "yyyyMMdd"));
        l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070824", "yyyyMMdd"));

        ProductParams l_productParams = TestDBUtility.getProductRow();

        EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();

        EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
        l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
        l_eqtypeTradedProductParams.setOpenOtcDiv("0");

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
        l_eqtypeOrderUnitParams.setOrderUnitId(123L);
        l_eqtypeOrderUnitParams.setConfirmedQuantity(5L);
        l_eqtypeOrderUnitParams.setQuantity(100L);
        l_eqtypeOrderUnitParams.setPrice(100L);

        FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeContractParams.setSubAccountId(l_mainAccountParams.getAccountId());
        l_eqtypeContractParams.setAccountId(l_subAccountParams.getAccountId());
        l_eqtypeContractParams.setMarketId(l_tradedProductParams.getMarketId());
        l_eqtypeContractParams.setProductId(l_productParams.getProductId());
        l_eqtypeContractParams.setQuantity(100D);

        try
        {

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_feqProductParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeContractParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            fail();
        }

        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_mainAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            double l_dblAmount = 0.0D;
            OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_SHORT;
            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            EqTypeContractOpenOrderUnitImpl l_eqTypeContractOpenOrderUnitImpl;
            Constructor l_onstructor =
                EqTypeContractOpenOrderUnitImpl.class.getDeclaredConstructor(
                    new Class[] {PersistenceManagerImpl.class, EqtypeOrderUnitRow.class});
            l_onstructor.setAccessible(true);
            l_eqTypeContractOpenOrderUnitImpl =
                (EqTypeContractOpenOrderUnitImpl)l_onstructor.newInstance(
                    new Object[] {null, l_eqtypeOrderUnitParams});

            List l_lisArrayList = new ArrayList();
            l_lisArrayList.add(l_eqTypeContractOpenOrderUnitImpl);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] {WEB3GentradeSubAccount.class,
                    ProductTypeEnum.class,
                    String.class,
                    Object[].class,
                    String.class},
                    l_lisArrayList);

            l_validation.validateMaxOpenMarginAmount(
                l_subAccount,
                l_dblAmount,
                l_orderType,
                l_tradedProduct,
                l_eqtypeOrderUnitParams);

            fail();
        }
        catch (DataNetworkException l_exDNE)
        {
            log.error(STR_METHOD_NAME, l_exDNE);
            fail();
        }
        catch (DataQueryException l_exDQE)
        {
            log.error(STR_METHOD_NAME, l_exDQE);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(STR_METHOD_NAME, l_exBE);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02870, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testValidateMaxOpenMarginAmount_case0002()
    {
        final String STR_METHOD_NAME = " testValidateMaxOpenMarginAmount_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_validation = new WEB3EquityTypeOrderManagerReusableValidations();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());

        InstBranchProductParams l_instBranchProductParams = new InstBranchProductParams();
        l_instBranchProductParams.setBranchId(l_mainAccountParams.getBranchId());
        l_instBranchProductParams.setCommissionProductCode(WEB3CommisionProductCodeDef.LISTING_STOCK);
        l_instBranchProductParams.setPremiumRestraintRate(1L);
        l_instBranchProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070824", "yyyyMMdd"));
        l_instBranchProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070824", "yyyyMMdd"));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        //l_productParams.setProductType(ProductTypeEnum.EQUITY);

        EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();

        EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
        l_eqtypeTradedProductParams.setProductId(l_productParams.getProductId());
        l_eqtypeTradedProductParams.setOpenOtcDiv("1");

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
        l_eqtypeOrderUnitParams.setOrderUnitId(123L);
        l_eqtypeOrderUnitParams.setConfirmedQuantity(5L);
        l_eqtypeOrderUnitParams.setQuantity(100L);
        l_eqtypeOrderUnitParams.setPrice(100L);

        FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();

        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeContractParams.setSubAccountId(l_mainAccountParams.getAccountId());
        l_eqtypeContractParams.setAccountId(l_subAccountParams.getAccountId());
        l_eqtypeContractParams.setMarketId(l_tradedProductParams.getMarketId());
        l_eqtypeContractParams.setProductId(l_productParams.getProductId());
        l_eqtypeContractParams.setQuantity(100D);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_instBranchProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_feqProductParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeContractParams);
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            fail();
        }

        try
        {
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_mainAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            double l_dblAmount = 0.0D;
            OrderTypeEnum l_orderType = OrderTypeEnum.MARGIN_SHORT;
            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            EqTypeContractOpenOrderUnitImpl l_eqTypeContractOpenOrderUnitImpl;
            Constructor l_onstructor =
                EqTypeContractOpenOrderUnitImpl.class.getDeclaredConstructor(
                    new Class[] {PersistenceManagerImpl.class, EqtypeOrderUnitRow.class});
            l_onstructor.setAccessible(true);
            l_eqTypeContractOpenOrderUnitImpl =
                (EqTypeContractOpenOrderUnitImpl)l_onstructor.newInstance(
                    new Object[] {null, l_eqtypeOrderUnitParams});

            List l_lisArrayList = new ArrayList();
            l_lisArrayList.add(l_eqTypeContractOpenOrderUnitImpl);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] {WEB3GentradeSubAccount.class,
                    ProductTypeEnum.class,
                    String.class,
                    Object[].class,
                    String.class},
                    l_lisArrayList);

            l_validation.validateMaxOpenMarginAmount(
                l_subAccount,
                l_dblAmount,
                l_orderType,
                l_tradedProduct,
                l_eqtypeOrderUnitParams);

            assertTrue(true);
        }
        catch (DataNetworkException l_exDNE)
        {
            log.error(STR_METHOD_NAME, l_exDNE);
            fail();
        }
        catch (DataQueryException l_exDQE)
        {
            log.error(STR_METHOD_NAME, l_exDQE);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(STR_METHOD_NAME, l_exBE);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02870, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.error(STR_METHOD_NAME, l_exE);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 參數補助口座 == null時，抛異常
     */
    public void testValidateMechanismDepositAgreeCase0001()
    {
        final String STR_METHOD_NAME = "testValidateMechanismDepositAgreeCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SubAccount l_subAccount = null;
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            
            l_validations.validateMechanismDepositAgree(l_subAccount);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 取得した顧客.is保振（機@構預託）同意() ＝ falseの場合、 
     *  「機@構預託に同意されていないため、取引できません」の例外をthrowする。
     */
    public void testValidateMechanismDepositAgreeCase0002()
    {
        final String STR_METHOD_NAME = "testValidateMechanismDepositAgreeCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            
            l_validations.validateMechanismDepositAgree(l_subAccount);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02964);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 取得した顧客.is保振（機@構預託）同意() ＝ true,正常結束。
     */
    public void testValidateMechanismDepositAgreeCase0003()
    {
        final String STR_METHOD_NAME = "testValidateMechanismDepositAgreeCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            
            l_validations.validateMechanismDepositAgree(l_subAccount);
            
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
     * 単位未満株数 < 株数
     *
     */
    public void testValidateMiniStockSellDeregTermQuantity0001()
    {
        final String STR_METHOD_NAME = "testValidateMiniStockSellDeregTermQuantity0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setBaseDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeProductParams.setYearlyBooksClosingDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            double l_dblQuantity = 1D;
            l_validations.validateMiniStockSellDeregTermQuantity(
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00725, l_ex.getErrorInfo());
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
     * 単位未満株数 > 株数
     *
     */
    public void testValidateMiniStockSellDeregTermQuantity0002()
    {
        final String STR_METHOD_NAME = "testValidateMiniStockSellDeregTermQuantity0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setBaseDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeProductParams.setYearlyBooksClosingDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            double l_dblQuantity = -1D;
            l_validations.validateMiniStockSellDeregTermQuantity(
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity);
            
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
     * 
     *
     */
    public void testValidateMiniStockBuyDeregTermQuantity0001()
    {
        final String STR_METHOD_NAME = "testValidateMiniStockBuyDeregTermQuantity0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            Timestamp l_tsNext = new Timestamp(GtlUtils.getSystemTimestamp().getTime());
            WEB3GentradeBizDate l_bizDateNext = new WEB3GentradeBizDate(l_tsNext);
            Date l_datNext = l_bizDateNext.roll(-5);
            l_tradedProductParams.setBaseDate(l_datNext);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeTradedProductParams.setLotSize(0.1);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeProductParams.setYearlyBooksClosingDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            double l_dblQuantity = 1D;
            l_validations.validateMiniStockBuyDeregTermQuantity(
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00724, l_ex.getErrorInfo());
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
     * 
     *
     */
    public void testValidateMiniStockBuyDeregTermQuantity0002()
    {
        final String STR_METHOD_NAME = "testValidateMiniStockBuyDeregTermQuantity0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            Timestamp l_tsNext = new Timestamp(GtlUtils.getSystemTimestamp().getTime());
            WEB3GentradeBizDate l_bizDateNext = new WEB3GentradeBizDate(l_tsNext);
            Date l_datNext = l_bizDateNext.roll(-5);
            l_tradedProductParams.setBaseDate(l_datNext);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeTradedProductParams.setLotSize(1.1);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeProductParams.setYearlyBooksClosingDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
            
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            double l_dblQuantity = 1D;
            l_validations.validateMiniStockBuyDeregTermQuantity(
                l_subAccount,
                l_tradedProduct,
                l_dblQuantity);
            
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
     * 
     *
     */
    public void testIsDevidendRightDate0001()
    {
        final String STR_METHOD_NAME = "testIsDevidendRightDate0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeBizDate l_bizDateNext = new WEB3GentradeBizDate(l_tsOrderAcceptTime);
            Date l_datNext = l_bizDateNext.roll(-2);
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            assertTrue(l_validations.isDevidendRightDate(
                new Timestamp(l_datNext.getTime()), l_tsOrderAcceptTime));
            
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
     * 
     *
     */
    public void testIsDevidendRightDate0002()
    {
        final String STR_METHOD_NAME = "testIsDevidendRightDate0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Timestamp l_tsNext = new Timestamp(GtlUtils.getSystemTimestamp().getTime());
            WEB3GentradeBizDate l_bizDateNext = new WEB3GentradeBizDate(l_tsNext);
            Date l_datNext = l_bizDateNext.roll(-4);
            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            assertTrue(!l_validations.isDevidendRightDate(
                new Timestamp(l_datNext.getTime()), l_tsNext));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0001()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setBasePrice(0.1);
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            assertEquals("0.1", l_validations.calcBasePrice(l_tradedProduct) + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0002()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, WEB3EquityBackServiceOnlineDef.ONLINE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setBasePrice(0);
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0003()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setBasePrice(0);
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            l_eqtypeTradedProductParams.setValidUntilBizDate("20091013");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0004()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0005()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0006()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0007()
    {
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
        final String STR_METHOD_NAME = "test_calcBasePrice_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0008()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0009()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_calcBasePrice_case0010()
    {
        final String STR_METHOD_NAME = "test_calcBasePrice_case0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3MarginAttributeNameDef.BACK_SERVICE_IN_ONLINE, "1");
                
                WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20091013", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setValidUntilBizDate("20091012");
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3EquityTypeOrderManagerReusableValidations l_validations =
                new WEB3EquityTypeOrderManagerReusableValidations();
            l_validations.calcBasePrice(l_tradedProduct);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
