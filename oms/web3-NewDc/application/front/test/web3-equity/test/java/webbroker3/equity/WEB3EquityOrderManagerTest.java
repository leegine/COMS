head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 拡張株式注文マネージャのテストクラス(WEB3EquityOrderManagerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/08/08 周墨洋 (中訊) 仕様変更・ＤＢ更新仕様 モデルNo.1191
*/

package webbroker3.equity;

import java.lang.reflect.Constructor;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContactInfoDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Participant;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Preferences;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ResourceBusyException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerErrorInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderManagerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderManagerTest.class);

    /**
     * 拡張株式注文マネージャ
     */
    WEB3EquityOrderManager l_equityOrderManager = null;

    public WEB3EquityOrderManagerTest(String arg0)
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
  
    public void testIsForcedSettleOrder_0001()
    {
        String STR_METHOD_NAME = "testIsForcedSettleOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        PersistenceManagerImpl l_PersistenceManagerImpl = new PersistenceManagerImpl();
        try
        {
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
            TestDBUtility.deleteAll(l_eqtypeOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(l_PersistenceManagerImpl , 3304148080001L);
            
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            assertTrue(l_equityOrderManager.isForcedSettleOrder(l_eqTypeOrderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testIsForcedSettleOrder_0002()
    {
        String STR_METHOD_NAME = "testIsForcedSettleOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqTypeOrderUnit l_eqTypeOrderUnit = null;
            
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            assertFalse(l_equityOrderManager.isForcedSettleOrder(l_eqTypeOrderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    public void testIsApproveForcedSettleOrder_0001()
    {
        String STR_METHOD_NAME = "testIsApproveForcedSettleOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        PersistenceManagerImpl l_PersistenceManagerImpl = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqTypeOrderUnitParams = new EqtypeOrderUnitParams();
        l_eqTypeOrderUnitParams.setApproveStatusType("2");
        l_eqTypeOrderUnitParams.setForcedSettleReasonType("2");
        try
        {
            EqTypeOrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(l_PersistenceManagerImpl , l_eqTypeOrderUnitParams);
            
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            
            assertTrue(l_equityOrderManager.isApproveForcedSettleOrder(l_eqTypeOrderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testIsApproveForcedSettleOrder_0002()
    {
        String STR_METHOD_NAME = "testIsApproveForcedSettleOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            EqTypeOrderUnit l_eqTypeOrderUnit = null;
            
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            
            assertFalse(l_equityOrderManager.isApproveForcedSettleOrder(l_eqTypeOrderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testIsApproveForcedSettleOrder_0003()
    {
        String STR_METHOD_NAME = "testIsApproveForcedSettleOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        PersistenceManagerImpl l_PersistenceManagerImpl = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqTypeOrderUnitParams = new EqtypeOrderUnitParams();
        l_eqTypeOrderUnitParams.setApproveStatusType("1");
        try
        {
            EqTypeOrderUnit l_eqTypeOrderUnit = new EqTypeOrderUnitImpl(l_PersistenceManagerImpl , l_eqTypeOrderUnitParams);
            
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            
            assertFalse(l_equityOrderManager.isApproveForcedSettleOrder(l_eqTypeOrderUnit));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testGetErrorReasonCode_0001()
    {
        String STR_METHOD_NAME = "testIsApproveForcedSettleOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
        try
        {
            assertEquals("0016" , l_equityOrderManager.getErrorReasonCode("748"));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void test_getExpirationDate_C0001()
    {
        final String STR_METHOD_NAME = " test_getExpirationDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1002L));
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                WEB3ChannelDef.BRANCH);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("3D");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            Date l_dat = WEB3DateUtility.getDate("20070504","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070620","yyyyMMdd");
            String l_strProductCode = "1";
            String l_strMarketCode = "1";
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            Date l_datExpirationDate1 =
                l_equityOrderManager.getExpirationDate(
                    l_datExpirationDate, l_strProductCode, l_strMarketCode);
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070620","yyyyMMdd"), l_datExpirationDate1);
            assertEquals(0, l_intCompareToDay);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_getExpirationDate_C0002()
    {
        final String STR_METHOD_NAME = " test_getExpirationDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1002L));

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                WEB3ChannelDef.BRANCH);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("3D");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20070601","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            Date l_dat = WEB3DateUtility.getDate("20070504","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070620","yyyyMMdd");
            String l_strProductCode = "1";
            String l_strMarketCode = "1";
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            Date l_datExpirationDate1 =
                l_equityOrderManager.getExpirationDate(
                    l_datExpirationDate, l_strProductCode, l_strMarketCode);
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                    WEB3DateUtility.getDate("20070620","yyyyMMdd"), l_datExpirationDate1);
            assertEquals(0, l_intCompareToDay);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_getExpirationDate_C0003()
    {
        final String STR_METHOD_NAME = " test_getExpirationDate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1002L));

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                WEB3ChannelDef.BRANCH);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("3D");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20070401","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            Date l_dat = WEB3DateUtility.getDate("20070504","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070620","yyyyMMdd");
            String l_strProductCode = "1";
            String l_strMarketCode = "1";
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            Date l_datExpirationDate1 =
                l_equityOrderManager.getExpirationDate(
                    l_datExpirationDate, l_strProductCode, l_strMarketCode);
            Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_tsSystemTimestamp);
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                l_gentradeBizDate.roll(-4), l_datExpirationDate1);
            assertTrue(l_intCompareToDay > 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    public void test_getExpirationDate_C0004()
    {
        final String STR_METHOD_NAME = " test_getExpirationDate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));

            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImpl);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1002L));

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getSessionProperty",
//                new Class[] {String.class},
//                WEB3ChannelDef.BRANCH);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("eqtype.final.day.with.right");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1002L);
            l_traderParams.setBranchCode("3D");
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("0");
            l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20070504","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            l_eqtypeProductParams.setProductId(3304148080000L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("1");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("1");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(1000);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);

            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            Date l_dat = WEB3DateUtility.getDate("20070430","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_dat);
            
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070620","yyyyMMdd");
            String l_strProductCode = "1";
            String l_strMarketCode = "1";
            WEB3EquityOrderManager l_equityOrderManager = new WEB3EquityOrderManager();
            Date l_datExpirationDate1 =
                l_equityOrderManager.getExpirationDate(
                    l_datExpirationDate, l_strProductCode, l_strMarketCode);
            Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
            WEB3GentradeBizDate l_gentradeBizDate =
                new WEB3GentradeBizDate(l_tsSystemTimestamp);
            int l_intCompareToDay = WEB3DateUtility.compareToDay(
                l_gentradeBizDate.roll(-4), l_datExpirationDate1);
            assertTrue(l_intCompareToDay > 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testThrowOrderValidationResultErrorInfo_case0001()
    {

        String STR_METHOD_NAME = " testThrowOrderValidationResultErrorInfo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_equityOrderManager = new WEB3EquityOrderManager();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            ProcessingResult l_processingResult;

            boolean l_blnIsResultOk = true;
            ErrorInfo l_errorInfo = new ErrorInfo();

            Constructor l_constructor =
                ProcessingResult.class.getDeclaredConstructor(
                    new Class[] {boolean.class, ErrorInfo.class});
            l_constructor.setAccessible(true);
            l_processingResult =
                (ProcessingResult)l_constructor.newInstance(
                    new Object[] {new Boolean(l_blnIsResultOk), l_errorInfo});

            boolean l_blnIsShortSellingRestraint = true;
            WEB3EquityOrderValidationResult l_equityOrderValidationResult =
                new WEB3EquityOrderValidationResult(
                    l_processingResult,
                    l_blnIsShortSellingRestraint);

            String l_institutionCode = l_institutionParams.getInstitutionCode();

            WEB3GentradeInstitution l_gentradeInstitution =
                new WEB3GentradeInstitution(l_institutionCode);

            String l_strProductCode = "";

            this.l_equityOrderManager.throwOrderValidationResultErrorInfo(
                l_equityOrderValidationResult,
                l_gentradeInstitution,
                l_strProductCode);

            assertTrue(true);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testThrowOrderValidationResultErrorInfo_case0002()
    {

        String STR_METHOD_NAME = " testThrowOrderValidationResultErrorInfo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_equityOrderManager = new WEB3EquityOrderManager();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            ProcessingResult l_processingResult;

            boolean l_blnIsResultOk = false;
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_00036;

            Constructor l_constructor =
                ProcessingResult.class.getDeclaredConstructor(
                    new Class[] {boolean.class, ErrorInfo.class});
            l_constructor.setAccessible(true);
            l_processingResult =
                (ProcessingResult)l_constructor.newInstance(
                    new Object[] {new Boolean(l_blnIsResultOk), l_errorInfo});

            boolean l_blnIsShortSellingRestraint = true;
            WEB3EquityOrderValidationResult l_equityOrderValidationResult =
                new WEB3EquityOrderValidationResult(
                    l_processingResult,
                    l_blnIsShortSellingRestraint);

            String l_institutionCode = l_institutionParams.getInstitutionCode();

            WEB3GentradeInstitution l_gentradeInstitution =
                new WEB3GentradeInstitution(l_institutionCode);

            String l_strProductCode = "";

            this.l_equityOrderManager.throwOrderValidationResultErrorInfo(
                l_equityOrderValidationResult,
                l_gentradeInstitution,
                l_strProductCode);

            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00036, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testThrowOrderValidationResultErrorInfo_case0003()
    {

        String STR_METHOD_NAME = " testThrowOrderValidationResultErrorInfo_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_equityOrderManager = new WEB3EquityOrderManager();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
        l_eqtypeProductParams.setProductId(l_productParams.getProductId());
        l_eqtypeProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
        l_eqtypeProductParams.setProductCode("123");
        l_eqtypeProductParams.setProductType(l_productParams.getProductType());
        l_eqtypeProductParams.setYearlyBooksClosingDate(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_eqtypeProductParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
        l_eqtypeProductParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070803", "yyyyMMdd"));

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            ProcessingResult l_processingResult;

            boolean l_blnIsResultOk = false;
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_02836;

            Constructor l_constructor =
                ProcessingResult.class.getDeclaredConstructor(
                    new Class[] {boolean.class, ErrorInfo.class});
            l_constructor.setAccessible(true);
            l_processingResult =
                (ProcessingResult)l_constructor.newInstance(
                    new Object[] {new Boolean(l_blnIsResultOk), l_errorInfo});

            boolean l_blnIsShortSellingRestraint = true;
            WEB3EquityOrderValidationResult l_equityOrderValidationResult =
                new WEB3EquityOrderValidationResult(
                    l_processingResult,
                    l_blnIsShortSellingRestraint);

            String l_institutionCode = l_institutionParams.getInstitutionCode();

            WEB3GentradeInstitution l_gentradeInstitution =
                new WEB3GentradeInstitution(l_institutionCode);

            String l_strProductCode = "456";

            this.l_equityOrderManager.throwOrderValidationResultErrorInfo(
                l_equityOrderValidationResult,
                l_gentradeInstitution,
                l_strProductCode);

            fail();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testThrowOrderValidationResultErrorInfo_case0004()
    {

        String STR_METHOD_NAME = " testThrowOrderValidationResultErrorInfo_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_equityOrderManager = new WEB3EquityOrderManager();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
        l_eqtypeProductParams.setProductId(l_productParams.getProductId());
        l_eqtypeProductParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
        l_eqtypeProductParams.setProductCode("123");
        l_eqtypeProductParams.setProductType(l_productParams.getProductType());
        l_eqtypeProductParams.setYearlyBooksClosingDate(
            WEB3DateUtility.getDate("20070810", "yyyyMMdd"));
        l_eqtypeProductParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
        l_eqtypeProductParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070803", "yyyyMMdd"));

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            ProcessingResult l_processingResult;

            boolean l_blnIsResultOk = false;
            ErrorInfo l_errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_02836;

            Constructor l_constructor =
                ProcessingResult.class.getDeclaredConstructor(
                    new Class[] {boolean.class, ErrorInfo.class});
            l_constructor.setAccessible(true);
            l_processingResult =
                (ProcessingResult)l_constructor.newInstance(
                    new Object[] {new Boolean(l_blnIsResultOk), l_errorInfo});

            boolean l_blnIsShortSellingRestraint = true;
            WEB3EquityOrderValidationResult l_equityOrderValidationResult =
                new WEB3EquityOrderValidationResult(
                    l_processingResult,
                    l_blnIsShortSellingRestraint);

            String l_institutionCode = l_institutionParams.getInstitutionCode();

            WEB3GentradeInstitution l_gentradeInstitution =
                new WEB3GentradeInstitution(l_institutionCode);

            String l_strProductCode = l_eqtypeProductParams.getProductCode();

            this.l_equityOrderManager.throwOrderValidationResultErrorInfo(
                l_equityOrderValidationResult,
                l_gentradeInstitution,
                l_strProductCode);

            fail();

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02836, l_exBE.getErrorInfo());
            assertEquals(
                "webbroker3.equity.WEB3EquityOrderManager" +
                " throwOrderValidationResultErrorInfo(" +
                "OrderValidationResult, Institution, String)",
                l_exBE.getErrorMethod());
            assertEquals(
                "2007年8月7日（火）",
                l_exBE.getErrorMessage());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(ProductParams.TYPE);
                TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    /**
     * get注文単価区分一覧
     * 引数の取引銘柄.市場IDに該当する市場オブジェクト.isPTS市場( )がtrueの場合 
     *  　@１：指値 を返却する。 
     */
    public void testGetOrderPriceDivsCase0001()
    {
        final String STR_METHOD_NAME = "testGetOrderPriceDivsCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_MarketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesRow.setName("equity.pts.market.div");
            l_MarketPreferencesRow.setValue("1");
            TestDBUtility.insertWithDel(l_MarketPreferencesRow);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketRow = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketRow);
            
            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            Branch l_branch = null;
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            
            String[] l_strGetOrderPriceDivs = l_manager.getOrderPriceDivs(l_branch,l_tradedProduct);
            
            assertEquals("1", l_strGetOrderPriceDivs[0]);
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
     * throw現物株式余力エラー詳細情報
     * 參數取引余力結果為null
     */
    public void testThrowEquityTpErrorDetailInfoCase0001()
    {
        final String STR_METHOD_NAME = "testThrowEquityTpErrorDetailInfoCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = null;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
            double l_dblOrderQuantity = 100;

            l_manager.throwEquityTpErrorDetailInfo(l_tPTradingPowerResult,l_orderTypeEnum,l_dblOrderQuantity);
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
     * throw現物株式余力エラー詳細情報
     * 取引余力結果.is判定フラグ() == true の場合
     * 何もせずreturnする。
     */
    public void testThrowEquityTpErrorDetailInfoCase0002()
    {
        final String STR_METHOD_NAME = "testThrowEquityTpErrorDetailInfoCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            
            l_tPTradingPowerResult.setResultFlg(true);
            
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
            double l_dblOrderQuantity = 100;

            l_manager.throwEquityTpErrorDetailInfo(
                    l_tPTradingPowerResult,l_orderTypeEnum,l_dblOrderQuantity);
            
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
     * throw現物株式余力エラー詳細情報
     * 取引余力結果.is判定フラグ() == false の場合
     * 二階建エラー（取引余力結果.取引余力エラー情報.取引余力エラー区分 == "二階建エラー"）の場合）
     */
    public void testThrowEquityTpErrorDetailInfoCase0003()
    {
        final String STR_METHOD_NAME = "testThrowEquityTpErrorDetailInfoCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = "2";
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            
            l_tPTradingPowerResult.setResultFlg(false);
            
            l_tPTradingPowerResult.setTpErrorInfo(l_tpErrorInfo);
            
            
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
            double l_dblOrderQuantity = 100;

            l_manager.throwEquityTpErrorDetailInfo(
                    l_tPTradingPowerResult,l_orderTypeEnum,l_dblOrderQuantity);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01928);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * throw現物株式余力エラー詳細情報
     * 取引余力結果.is判定フラグ() == false の場合
     * 二階建エラー（取引余力結果.取引余力エラー情報.取引余力エラー区分 ≠ "二階建エラー"）の場合）
     * 買注文の場合
     * get預り金不足情報（買付）取得した文字列は例外オブジェクトに設定
     */
    public void testThrowEquityTpErrorDetailInfoCase0004()
    {
        final String STR_METHOD_NAME = "testThrowEquityTpErrorDetailInfoCase0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = "1";
            l_tpErrorInfo.buyOrderPossibleAmount = 100;
            l_tpErrorInfo.lackAccountBalance = 200;
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            
            l_tPTradingPowerResult.setResultFlg(false);
            
            l_tPTradingPowerResult.setTpErrorInfo(l_tpErrorInfo);
            
            
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
            double l_dblOrderQuantity = 100;

            l_manager.throwEquityTpErrorDetailInfo(
                    l_tPTradingPowerResult,l_orderTypeEnum,l_dblOrderQuantity);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01929);
            assertEquals(l_ex.getErrorMessage(),"100,200");
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * throw現物株式余力エラー詳細情報
     * 取引余力結果.is判定フラグ() == false の場合
     * 二階建エラー（取引余力結果.取引余力エラー情報.取引余力エラー区分 ≠ "二階建エラー"）の場合）
    //売注文の場合
    //get預り金不足情報（売付）取得した文字列は例外オブジェクトに設定
     */
    public void testThrowEquityTpErrorDetailInfoCase0005()
    {
        final String STR_METHOD_NAME = "testThrowEquityTpErrorDetailInfoCase0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            
            WEB3TPTradingPowerErrorInfo l_tpErrorInfo = new WEB3TPTradingPowerErrorInfo();
            l_tpErrorInfo.tradinPowerErrorDiv = "1";
            l_tpErrorInfo.lackAccountBalance = 200;
            l_tpErrorInfo.sellOrderPossibleQuantity =300;
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = new WEB3TPTradingPowerResult();
            
            l_tPTradingPowerResult.setResultFlg(false);
            
            l_tPTradingPowerResult.setTpErrorInfo(l_tpErrorInfo);
            
            
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.EQUITY_SELL;
            double l_dblOrderQuantity = 400;

            l_manager.throwEquityTpErrorDetailInfo(
                    l_tPTradingPowerResult,l_orderTypeEnum,l_dblOrderQuantity);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01930);
            assertEquals(l_ex.getErrorMessage(),"200,300,400");
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /*
     * validate機@構預託同意(補助口座)失敗
     */
    public void testValidateOpenContractOrder_C0001()
    {
        final String STR_METHOD_NAME = "testvalidateOpenContractOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateInsider",
                new Class[]
                { SubAccount.class, EqTypeProduct.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateAccountProductOrderStop",
                new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarketOrderRestraint",
                new Class[]
                { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarginSpecialAccountOpen",
                new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateHandlingMarket",
                new Class[]
                { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateCapitalGainTaxDealingsReg", new Class[]
                { TaxTypeEnum.class, EqTypeProduct.class, boolean.class },
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
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateQuantity",
                new Class[]
                { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePrice",
                new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                new Boolean(true));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateWLimitPriceOrder",
                new Class[]
                { WEB3GentradeSubAccount.class,
                        long.class,
                        double.class,
                        String.class,
                        double.class,
                        String.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        WEB3EquityTradedProduct.class,
                        boolean.class,
                        String.class,
                        OrderCategEnum.class,
                        double.class,
                        String .class,
                        OrderTypeEnum.class },
                        null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "isShortSellingRestraint",
                new Class[]
                          { WEB3GentradeSubAccount.class,
                                WEB3EquityTradedProduct.class,
                                double.class,
                                OrderTypeEnum.class,
                                boolean.class,
                                EqTypeExecutionConditionType.class,
                                String.class,
                                EqtypeOrderUnitRow.class },
                                new Boolean(true));
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManagerForTest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_gentradeMainAccount,
                    l_subAccountParams);
            
            Trader trader = new TraderForTest();
            boolean isLongOrder = false;
            String productCode = "N8080";
            String marketCode = "SP";
            double quantity = 1.0;
            double price = 15.0;
            EqTypeExecutionConditionType execType = EqTypeExecutionConditionType.UNDEFINED;
            Date orderExpDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            TaxTypeEnum taxType = TaxTypeEnum.UNDEFINED;
            EqTypeOpenContractOrderSpec l_openContractOrderSpec =
                new WEB3MarginOpenContractOrderSpec(
                    trader,
                    isLongOrder,
                    productCode,
                    marketCode,
                    quantity,
                    price,
                    execType,
                    orderExpDate,
                    taxType);
            
            EqTypeOrderUnit l_eqtypeOrderUnit = new EqTypeOrderUnitForTest();
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            WEB3MarginNewOrderValidationResult l_marginNewOrderValidationResult = 
                (WEB3MarginNewOrderValidationResult)l_manager.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec, l_eqtypeOrderUnit);
            
            assertEquals(true, l_marginNewOrderValidationResult.getShortSellingRestraint());
            assertEquals(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02964),
                l_marginNewOrderValidationResult.getProcessingResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    
    /*
     * validate機@構預託同意(補助口座)正常通過
     */
    public void testValidateOpenContractOrder_C0002()
    {
        final String STR_METHOD_NAME = "testvalidateOpenContractOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateInsider",
                new Class[]
                { SubAccount.class, EqTypeProduct.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateAccountProductOrderStop",
                new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarketOrderRestraint",
                new Class[]
                { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarginSpecialAccountOpen",
                new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateHandlingMarket",
                new Class[]
                { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateCapitalGainTaxDealingsReg", new Class[]
                { TaxTypeEnum.class, EqTypeProduct.class, boolean.class },
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
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateQuantity",
                new Class[]
                { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePrice",
                new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                new Boolean(true));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateWLimitPriceOrder",
                new Class[]
                { WEB3GentradeSubAccount.class,
                        long.class,
                        double.class,
                        String.class,
                        double.class,
                        String.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        WEB3EquityTradedProduct.class,
                        boolean.class,
                        String.class,
                        OrderCategEnum.class,
                        double.class,
                        String .class,
                        OrderTypeEnum.class },
                        null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "isShortSellingRestraint",
                new Class[]
                          { WEB3GentradeSubAccount.class,
                                WEB3EquityTradedProduct.class,
                                double.class,
                                OrderTypeEnum.class,
                                boolean.class,
                                EqTypeExecutionConditionType.class,
                                String.class,
                                EqtypeOrderUnitRow.class },
                                new Boolean(true));
        
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManagerForTest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_gentradeMainAccount,
                    l_subAccountParams);
            
            Trader trader = new TraderForTest();
            boolean isLongOrder = false;
            String productCode = "N8080";
            String marketCode = "SP";
            double quantity = 1.0;
            double price = 15.0;
            EqTypeExecutionConditionType execType = EqTypeExecutionConditionType.UNDEFINED;
            Date orderExpDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            TaxTypeEnum taxType = TaxTypeEnum.UNDEFINED;
            EqTypeOpenContractOrderSpec l_openContractOrderSpec =
                new WEB3MarginOpenContractOrderSpec(
                    trader,
                    isLongOrder,
                    productCode,
                    marketCode,
                    quantity,
                    price,
                    execType,
                    orderExpDate,
                    taxType);
            
            EqTypeOrderUnit l_eqtypeOrderUnit = new EqTypeOrderUnitForTest();
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            l_marketParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_branchMarketRepayDealtCondParams =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_branchMarketRepayDealtCondParams.setInstitutionCode("0D");
            l_branchMarketRepayDealtCondParams.setBranchCode("381");
            l_branchMarketRepayDealtCondParams.setMarketCode("SP");
            l_branchMarketRepayDealtCondParams.setRepaymentDiv("1");
            l_branchMarketRepayDealtCondParams.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_branchMarketRepayDealtCondParams);
            
            WEB3MarginNewOrderValidationResult l_marginNewOrderValidationResult = 
                (WEB3MarginNewOrderValidationResult)l_manager.validateOpenContractOrder(l_subAccount, l_openContractOrderSpec, l_eqtypeOrderUnit);
            
            assertEquals(true, l_marginNewOrderValidationResult.getShortSellingRestraint());
            assertEquals(ProcessingResult.SUCCESS_RESULT, l_marginNewOrderValidationResult.getProcessingResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    
    /*
     * validate機@構預託同意(補助口座)失敗
     */
    public void testValidateSettleContractOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateSettleContractOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateInsider",
                new Class[]
                { SubAccount.class, EqTypeProduct.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateAccountProductOrderStop",
                new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarketOrderRestraint",
                new Class[]
                { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateHandlingMarket",
                new Class[]
                { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarginSpecialAccountOpen",
                new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateCapitalGainTaxDealingsReg", new Class[]
                { TaxTypeEnum.class, EqTypeProduct.class, boolean.class },
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
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateQuantity",
                new Class[]
                { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateEverySettleContractOrderEntryLotSize",
                new Class[]
                { WEB3EquityTradedProduct.class, EqTypeSettleContractOrderEntry[].class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePrice",
                new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                new Boolean(true));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateWLimitPriceOrder",
                new Class[]
                { WEB3GentradeSubAccount.class,
                        long.class,
                        double.class,
                        String.class,
                        double.class,
                        String.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        WEB3EquityTradedProduct.class,
                        boolean.class,
                        String.class,
                        OrderCategEnum.class,
                        double.class,
                        String .class,
                        OrderTypeEnum.class },
                        null);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManagerForTest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_gentradeMainAccount,
                    l_subAccountParams);
            
            Trader trader = new TraderForTest();
            EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry = new EqTypeSettleContractOrderEntry[1];
            l_closeMarginContractEntry[0] = new EqTypeSettleContractOrderEntry(2134566345L, 1.0);
            double l_dblLimitPrice = 15.0;
            EqTypeExecutionConditionType execType = EqTypeExecutionConditionType.UNDEFINED;
            Date orderExpDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            TaxTypeEnum taxType = TaxTypeEnum.UNDEFINED;
            EqTypeSettleContractOrderSpec l_settleContractOrderSpec =
                new WEB3MarginSettleContractOrderSpec(
                    trader,
                    l_closeMarginContractEntry,
                    l_dblLimitPrice,
                    execType,
                    orderExpDate,
                    taxType);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(2134566345L);
            l_eqtypeContractParams.setMarketId(3303L);
            l_eqtypeContractParams.setProductId(12342);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(12342);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            WEB3EquityTradedProduct l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateTradedProductForMarginTrading",
                    new Class[]
                    { SubAccount.class, WEB3EquityProduct.class, WEB3GentradeMarket.class, WEB3GentradeBranch.class, String.class, OrderCategEnum.class, boolean.class },
                    l_equityTradedProduct);
            
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = 
                l_manager.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_contract);
            
            assertEquals(ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_02964),
                    l_eqTypeNewOrderValidationResult.getProcessingResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    
    /*
     * validate機@構預託同意(補助口座)正常通過
     */
    public void testValidateSettleContractOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateSettleContractOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateInsider",
                new Class[]
                { SubAccount.class, EqTypeProduct.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateAccountProductOrderStop",
                new Class[]
                { SubAccount.class, long.class, OrderTypeEnum.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarketOrderRestraint",
                new Class[]
                { WEB3EquityTradedProduct.class, String.class, OrderCategEnum.class, boolean.class, boolean.class, EqTypeExecutionConditionType.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateHandlingMarket",
                new Class[]
                { WEB3GentradeBranch.class, WEB3EquityTradedProduct.class, String.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateMarginSpecialAccountOpen",
                new Class[]
                { WEB3GentradeSubAccount.class, TaxTypeEnum.class, Date.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateCapitalGainTaxDealingsReg", new Class[]
                { TaxTypeEnum.class, EqTypeProduct.class, boolean.class },
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
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateQuantity",
                new Class[]
                { WEB3EquityTradedProduct.class, WEB3GentradeBranch.class, double.class, OrderTypeEnum.class, String.class, double.class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateEverySettleContractOrderEntryLotSize",
                new Class[]
                { WEB3EquityTradedProduct.class, EqTypeSettleContractOrderEntry[].class },
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validatePrice",
                new Class[]
                { double.class, WEB3EquityTradedProduct.class, SubAccount.class },
                new Boolean(true));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                "validateWLimitPriceOrder",
                new Class[]
                { WEB3GentradeSubAccount.class,
                        long.class,
                        double.class,
                        String.class,
                        double.class,
                        String.class,
                        EqTypeExecutionConditionType.class,
                        String.class,
                        WEB3EquityTradedProduct.class,
                        boolean.class,
                        String.class,
                        OrderCategEnum.class,
                        double.class,
                        String .class,
                        OrderTypeEnum.class },
                        null);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(12342);
            
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManagerForTest();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setOrgDepositDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                    l_gentradeMainAccount,
                    l_subAccountParams);
            
            Trader trader = new TraderForTest();
            EqTypeSettleContractOrderEntry[] l_closeMarginContractEntry = new EqTypeSettleContractOrderEntry[1];
            l_closeMarginContractEntry[0] = new EqTypeSettleContractOrderEntry(2134566345L, 1.0);
            double l_dblLimitPrice = 15.0;
            EqTypeExecutionConditionType execType = EqTypeExecutionConditionType.UNDEFINED;
            Date orderExpDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            TaxTypeEnum taxType = TaxTypeEnum.UNDEFINED;
            EqTypeSettleContractOrderSpec l_settleContractOrderSpec =
                new WEB3MarginSettleContractOrderSpec(
                    trader,
                    l_closeMarginContractEntry,
                    l_dblLimitPrice,
                    execType,
                    orderExpDate,
                    taxType);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(2134566345L);
            l_eqtypeContractParams.setMarketId(3303L);
            l_eqtypeContractParams.setProductId(12342);
            l_eqtypeContractParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(12342);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(12342);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3EquityTradedProduct l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManagerReusableValidations",
                    "validateTradedProductForMarginTrading",
                    new Class[]
                    { SubAccount.class, WEB3EquityProduct.class, WEB3GentradeMarket.class, WEB3GentradeBranch.class, String.class, OrderCategEnum.class, boolean.class },
                    l_equityTradedProduct);
            
            EqTypeNewOrderValidationResult l_eqTypeNewOrderValidationResult = 
                l_manager.validateSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_contract);
            
            assertEquals(ProcessingResult.SUCCESS_RESULT, l_eqTypeNewOrderValidationResult.getProcessingResult());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    //取引余力結果.取引余力エラー情報.取引余力エラー区分=="受入保証金占有率超過エラー"の場合
    public void testThrowTpErrorInfo_C0001()
    {
        final String STR_METHOD_NAME = "testThrowTpErrorInfo_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(false);
            WEB3TPTradingPowerErrorInfo l_info = new WEB3TPTradingPowerErrorInfo();
            l_info.tradinPowerErrorDiv = "12";
            l_tpResult.setTpErrorInfo(l_info);
            l_manager.throwTpErrorInfo(l_tpResult, OrderTypeEnum.EQUITY_BUY);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03143, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(""+l_ex);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    //取引余力結果.取引余力エラー情報.取引余力エラー区分!="受入保証金占有率超過エラー"の場合
    public void testThrowTpErrorInfo_C0002()
    {
        final String STR_METHOD_NAME = "testThrowTpErrorInfo_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3EquityOrderManager l_manager = new WEB3EquityOrderManager();
            WEB3TPTradingPowerResult l_tpResult = new WEB3TPTradingPowerResult();
            l_tpResult.setResultFlg(false);
            WEB3TPTradingPowerErrorInfo l_info = new WEB3TPTradingPowerErrorInfo();
            l_info.tradinPowerErrorDiv = "1";
            l_tpResult.setTpErrorInfo(l_info);
            l_manager.throwTpErrorInfo(l_tpResult, OrderTypeEnum.EQUITY_BUY);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01929, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(""+l_ex);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    /**
     * WEB3EquityOrderManagerForTest
     */
    public class WEB3EquityOrderManagerForTest extends WEB3EquityOrderManager
    {
        public void validateMarginOrder(WEB3GentradeSubAccount l_genSubAccount, String l_strRepaymentType) 
        throws WEB3BaseException
        {
            
        }
    }
    
    /**
     * TraderForTest
     */
    public class TraderForTest implements Trader
    {

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getTraderCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Branch getBranch()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    /**
     * MainAccountForTest
     */
    public class MainAccountForTest implements MainAccount
    {

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getAccountCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Branch getBranch()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public ContactInfoDetails getContactInfoDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getTradingPassword()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SubAccount[] getSubAccounts()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SubAccount getSubAccount(SubAccountTypeEnum arg0) throws NotFoundException
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Participant[] getParticipants()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Preferences getPreferences()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public void serializeOperationsWithWait()
        {
            // TODO Auto-generated method stub
            
        }

        public void serializeOperationsWithNoWait() throws ResourceBusyException
        {
            // TODO Auto-generated method stub
            
        }

        public MainAccountStatusEnum getAccountStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    /**
     * EqTypeOrderUnitForTest
     */
    public class EqTypeOrderUnitForTest implements EqTypeOrderUnit
    {

        public EqTypeOrder getEqTypeOrder()
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
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
@
