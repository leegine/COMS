head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderVoucherListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListCondUnit;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqOrderVoucherListServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListServiceImpl.class);
    
    WEB3AdminFeqOrderVoucherListServiceImpl l_impl =
        new WEB3AdminFeqOrderVoucherListServiceImpl();
    
    public WEB3AdminFeqOrderVoucherListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetListScreen()
    {

    }

    public void testGetObject_T01()
    {
        final String STR_METHOD_NAME = "testGetObject_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();

            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
                TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode(l_feqOrderUnitParams.getInstitutionCode());
            l_genCurrencyParams.setCurrencyCode(l_feqOrderUnitParams.getCurrencyCode());
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3GentradeCurrency l_currency1 =
                WEB3GentradeCurrency.genCurrency(
                    l_feqOrderUnitParams.getInstitutionCode(),
                    l_feqOrderUnitParams.getCurrencyCode());
            
            WEB3GentradeCurrency l_currency3 =
                WEB3GentradeCurrency.genCurrency(
                    l_feqOrderUnitParams.getInstitutionCode(),
                    l_feqOrderUnitParams.getCurrencyCode());

            HashMap jiddk = new HashMap();
            WEB3GentradeCurrency l_currency2 =
                (WEB3GentradeCurrency)l_impl.getObject(
                    l_feqOrderUnitParams.getInstitutionCode() + l_feqOrderUnitParams.getCurrencyCode(),
                    jiddk,
                    WEB3GentradeCurrency.class,
                    l_feqOrderUnitParams);

            assertEquals(l_currency1.getInstitutionCode(), l_currency2.getInstitutionCode());
            assertEquals(l_currency1.getCurrencyCode(), l_currency2.getCurrencyCode());
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetListScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0001()";
        log.entering(STR_METHOD_NAME);
 
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams= TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                new WEB3Administrator(l_administratorParams),
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                false,
                true);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBranchId(33381);
            l_feqOrderUnitParams.setMarketId(123);
            l_feqOrderUnitParams.setExecutedQuantity(1.1);
            l_feqOrderUnitParams.setQuantity(2.2);
            l_feqOrderUnitParams.setExecutedAmount(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3AdminFeqOrderVoucherListRequest l_request = new WEB3AdminFeqOrderVoucherListRequest();
            l_request.condList = new WEB3AdminFeqOrderVoucherListCondUnit[1];
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = new WEB3AdminFeqOrderVoucherListCondUnit();
            l_unit.marketCode = "123";
            l_unit.orderBizDateFrom = WEB3DateUtility.getDate("20080601", "yyyyMMdd");
            l_unit.orderBizDateTo = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_request.condList[0] = l_unit;

            WEB3AdminFeqOrderVoucherListServiceImplTest1 l_impl = new WEB3AdminFeqOrderVoucherListServiceImplTest1();
            l_impl.getListScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0002()";
        log.entering(STR_METHOD_NAME);
 
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams= TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                new WEB3Administrator(l_administratorParams),
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                false,
                true);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBranchId(33381);
            l_feqOrderUnitParams.setMarketId(123);
            l_feqOrderUnitParams.setExecutedQuantity(1.1);
            l_feqOrderUnitParams.setQuantity(2.2);
            l_feqOrderUnitParams.setExecutedAmount(1.1);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3AdminFeqOrderVoucherListRequest l_request = new WEB3AdminFeqOrderVoucherListRequest();
            l_request.condList = new WEB3AdminFeqOrderVoucherListCondUnit[1];
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = new WEB3AdminFeqOrderVoucherListCondUnit();
            l_unit.marketCode = "123";
            l_unit.orderBizDateFrom = WEB3DateUtility.getDate("20080601", "yyyyMMdd");
            l_unit.orderBizDateTo = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_request.condList[0] = l_unit;

            WEB3AdminFeqOrderVoucherListServiceImplTest1 l_impl = new WEB3AdminFeqOrderVoucherListServiceImplTest1();
            l_impl.getListScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }


    public void testGetListScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0003()";
        log.entering(STR_METHOD_NAME);
 
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams= TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                new WEB3Administrator(l_administratorParams),
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                false,
                true);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBranchId(33381);
            l_feqOrderUnitParams.setMarketId(123);
            l_feqOrderUnitParams.setExecutedQuantity(1.1);
            l_feqOrderUnitParams.setQuantity(1.1);
            l_feqOrderUnitParams.setExecutedAmount(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3AdminFeqOrderVoucherListRequest l_request = new WEB3AdminFeqOrderVoucherListRequest();
            l_request.condList = new WEB3AdminFeqOrderVoucherListCondUnit[1];
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = new WEB3AdminFeqOrderVoucherListCondUnit();
            l_unit.marketCode = "123";
            l_unit.orderBizDateFrom = WEB3DateUtility.getDate("20080601", "yyyyMMdd");
            l_unit.orderBizDateTo = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_request.condList[0] = l_unit;

            WEB3AdminFeqOrderVoucherListServiceImplTest1 l_impl = new WEB3AdminFeqOrderVoucherListServiceImplTest1();
            l_impl.getListScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetListScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetListScreen_C0004()";
        log.entering(STR_METHOD_NAME);
 
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);


            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            AdministratorParams l_administratorParams= TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            WEB3AdministratorForMock.mockValidateAuthority(
                new WEB3Administrator(l_administratorParams),
                WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                false,
                true);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBranchId(33381);
            l_feqOrderUnitParams.setMarketId(123);
            l_feqOrderUnitParams.setExecutedQuantity(1.1);
            l_feqOrderUnitParams.setQuantity(1.1);
            l_feqOrderUnitParams.setExecutedAmount(1.1);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);

            WEB3AdminFeqOrderVoucherListRequest l_request = new WEB3AdminFeqOrderVoucherListRequest();
            l_request.condList = new WEB3AdminFeqOrderVoucherListCondUnit[1];
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = new WEB3AdminFeqOrderVoucherListCondUnit();
            l_unit.marketCode = "123";
            l_unit.orderBizDateFrom = WEB3DateUtility.getDate("20080601", "yyyyMMdd");
            l_unit.orderBizDateTo = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
            l_request.condList[0] = l_unit;

            WEB3AdminFeqOrderVoucherListServiceImplTest1 l_impl = new WEB3AdminFeqOrderVoucherListServiceImplTest1();
            l_impl.getListScreen(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3AdminFeqOrderVoucherListServiceImplTest1 extends WEB3AdminFeqOrderVoucherListServiceImpl
    {
        protected Object[] createQueryContainer(String l_strInstitutionCode, WEB3AdminFeqOrderVoucherListCondUnit l_listCond) 
            throws WEB3BaseException
        {
                return new Object[]{new Long(123)};
        }
        protected String createQueryString(WEB3AdminFeqOrderVoucherListCondUnit l_listCond) 
            throws WEB3BaseException
        {
            return " market_id = ?";
        }

        protected Object getObject(
            String l_strKeyInfo, HashMap l_hmObjectList,
            Class l_classInfo, FeqOrderUnitRow l_feqOrderUnitRow) throws WEB3BaseException
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_gentradeAccountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            WEB3FeqProductManager l_web3FeqProductManager =
                (WEB3FeqProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            if (l_classInfo == WEB3GentradeBranch.class)
            {
                TestDBUtility.deleteAll(BranchParams.TYPE);
                BranchParams l_branchParams = TestDBUtility.getBranchRow();
                TestDBUtility.insertWithDel(l_branchParams);
                 WEB3GentradeBranch l_branch = null;;
                try
                {
                    l_branch = (WEB3GentradeBranch)l_gentradeAccountManager.getBranch(
                        l_branchParams.getBranchId());
                } catch (NotFoundException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 return  l_branch;
            }
            else if (l_classInfo == WEB3GentradeMainAccount.class)
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                MainAccountParams l_mainAccountParams =
                    TestDBUtility.getMainAccountRow();
                TestDBUtility.insertWithDel(l_mainAccountParams);
                return new WEB3GentradeMainAccount(l_mainAccountParams);
            }
            else if (l_classInfo == WEB3GentradeTrader.class)
            {
                TestDBUtility.deleteAll(TraderParams.TYPE);
                TraderParams l_traderParams = TestDBUtility.getTraderRow();
                TestDBUtility.insertWithDel(l_traderParams);
                WEB3GentradeTrader l_trader = null;
                try
                {
                    l_trader = (WEB3GentradeTrader)l_finObjectManager.getTrader(
                        l_traderParams.getTraderId());
                } catch (NotFoundException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return l_trader;
            }
            else if (l_classInfo == WEB3FeqProduct.class)
            {
                TestDBUtility.deleteAll(ProductParams.TYPE);
                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setProductId(3304148080000L);
                TestDBUtility.insertWithDel(l_productParams);
                TestDBUtility.deleteAll(FeqProductParams.TYPE);
                FeqProductParams l_feqProduct = TestDBUtility.getFeqProductRow();
                TestDBUtility.insertWithDel(l_feqProduct);
                WEB3FeqProduct l_product =
                    (WEB3FeqProduct)l_web3FeqProductManager.getFeqProduct(
                        l_feqProduct.getProductId());
                return l_product;
            }
            else if (l_classInfo == WEB3GentradeMarket.class)
            {
                TestDBUtility.deleteAll(MarketParams.TYPE);
                MarketParams l_marketParams = TestDBUtility.getMarketRow();
                TestDBUtility.insertWithDel(l_marketParams);
                WEB3GentradeMarket l_market = null;;
                try
                {
                    l_market = (WEB3GentradeMarket)l_finObjectManager.getMarket(
                            l_marketParams.getMarketId());
                } catch (NotFoundException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return l_market;
            }
            else if (l_classInfo == WEB3GentradeCurrency.class)
            {
                TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
                GenCurrencyParams l_genCurrencyParams =
                    TestDBUtility.getGenCurrencyRow();
                TestDBUtility.insertWithDel(l_genCurrencyParams);
                WEB3GentradeCurrency l_currency =
                    WEB3GentradeCurrency.genCurrency(
                        l_genCurrencyParams.getInstitutionCode(),
                        l_genCurrencyParams.getCurrencyCode());
                return l_currency;
            }
            else
            {
                return null;
            }
        }
    }
}
@
