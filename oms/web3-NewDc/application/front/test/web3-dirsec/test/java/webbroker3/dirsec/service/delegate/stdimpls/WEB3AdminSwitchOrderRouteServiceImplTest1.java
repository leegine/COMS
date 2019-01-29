head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminSwitchOrderRouteServiceImplTest1.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChangeCancelEnableFlag;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderEngineDivDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SubmitMqTriggerDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3ValidFlag;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.dirsec.message.WEB3AdminFrontProcessNumberInfoUnit;
import webbroker3.dirsec.message.WEB3AdminOrderRouteSwitchingInfo;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.HostEqtypeOrderAllRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeAccountManagerForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.OrderSwitchingPK;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminSwitchOrderRouteServiceImplTest1 extends TestBaseForMock
{
    /**
     * Log Variable
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSwitchOrderRouteServiceImplTest1.class);
    
    public static StringBuffer l_sbfInterface;

    public WEB3AdminSwitchOrderRouteServiceImplTest1(String arg0)
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
    
    public void testSelectIfoOrderUnit_0001() 
    {
        final String STR_METHOD_NAME = " testSelectIfoOrderUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        String l_strInstitutionCode = "0D";
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.marketCode = "SP";
        l_adminOrderRouteSwitchingInfo.productType = "6";
        

     try
     {
         IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
         TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
         TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
         InstitutionParams  l_institutionParams = TestDBUtility.getInstitutionRow();
         TestDBUtility.deleteAll(l_institutionParams.getRowType());
         TestDBUtility.insertWithDel(l_institutionParams);
         MarketParams l_marketParams = TestDBUtility.getMarketRow();
         TestDBUtility.deleteAll(l_marketParams.getRowType());
         l_marketParams.setMarketId(1002L);
         TestDBUtility.insertWithDel(l_marketParams);
         
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeAccountManager",
             "getInstitution",
             new Class[] {String.class},
             new WEB3GentradeInstitution(l_strInstitutionCode));
         
         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
             "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
             "getMarket", 
             new Class[] { long.class },
             new WEB3GentradeMarket(new WEB3GentradeInstitution(l_strInstitutionCode), "SP"));
         
         Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderUnit",
             new Class[] {String.class,WEB3AdminOrderRouteSwitchingInfo.class});
         method.setAccessible(true);
         List l_lisResult =  (List) method.invoke(l_adminSwitchOrderRouteServiceImpl,
             new Object[]{l_strInstitutionCode,l_adminOrderRouteSwitchingInfo});
         assertEquals("101001010010","" + ((IfoOrderUnitRow)l_lisResult.get(0)).getAccountId());
     }
     catch (Exception l_ex)
     {
         log.debug(TEST_END + STR_METHOD_NAME , l_ex);
         log.exiting(TEST_END + STR_METHOD_NAME);
         fail();
     }
        
    }

    public void testUpdateIfoOrderUnit_0001()
    {
        final String STR_METHOD_NAME = " testUpdateIfoOrderUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = 
            new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.submitOrderRouteDiv = "9";
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.deleteAllAndCommit(l_ifoOrderUnitParams.getRowType());
            l_ifoOrderUnitParams.setSubmitOrderRouteDiv("3");
            l_ifoOrderUnitParams.setOrderUnitId(12345L);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateIfoOrderUnit",
                new Class[] {IfoOrderUnitRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
            method.setAccessible(true);
            method.invoke(l_adminSwitchOrderRouteServiceImpl,
                new Object[]{l_ifoOrderUnitParams,l_adminOrderRouteSwitchingInfo});
            
            List IfoOrderUnitList = new ArrayList();  
            // 更新条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" order_unit_id = ? ");

            // 更新条件コンテナの生成
            Object[] l_objWhere =
                {
                    Long.toString(l_ifoOrderUnitParams.getOrderUnitId())
                };

            //注文単位レコードの発注経路区分を切替対象の発注経路、更新日付を現在日時にそれぞれ更新する。
            QueryProcessor qp = Processors.getDefaultProcessor();

            IfoOrderUnitList = qp.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
            assertEquals("9",((IfoOrderUnitParams)IfoOrderUnitList.get(0)).getSubmitOrderRouteDiv());
            
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
     }
    
    public void testIsOrderUnitUpdateObjSwitchOrderRoute_0001()
    {
        final String STR_METHOD_NAME = " testIsOrderUnitUpdateObjSwitchOrderRoute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.productType = "1";
        try
        {
            
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAllAndCommit(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setOrderEngineDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_adminSwitchOrderRouteServiceImpl,
                    new Object[]{l_strInstitutionCode,l_adminOrderRouteSwitchingInfo});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "isOrderUnitUpdateObjSwitchOrderRoute",
                new Class[] {WEB3GentradeOrderSwitching.class});
            method.setAccessible(true);
            boolean l_lbnResult = ((Boolean)method.invoke(l_adminSwitchOrderRouteServiceImpl,
                new Object[]{l_gentradeOrderSwitching})).booleanValue();
            assertTrue(l_lbnResult);
        } 
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testIsOrderUnitUpdateObjSwitchOrderRoute_0002()
    {
        final String STR_METHOD_NAME = " testIsOrderUnitUpdateObjSwitchOrderRoute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.productType = "1";
        l_adminOrderRouteSwitchingInfo.marketCode= "N1";
        l_adminOrderRouteSwitchingInfo.frontOrderSystemCode = "1";
        try
        {
            
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAllAndCommit(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setOrderEngineDiv("1");
            l_orderSwitchingParams.setValidFlag("1");
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_adminSwitchOrderRouteServiceImpl,
                    new Object[]{l_strInstitutionCode,l_adminOrderRouteSwitchingInfo});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "isOrderUnitUpdateObjSwitchOrderRoute",
                new Class[] {WEB3GentradeOrderSwitching.class});
            method.setAccessible(true);
            boolean l_lbnResult = ((Boolean)method.invoke(l_adminSwitchOrderRouteServiceImpl,
                new Object[]{l_gentradeOrderSwitching})).booleanValue();
            assertTrue(l_lbnResult);
        } 
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testIsOrderUnitUpdateObjSwitchOrderRoute_0003()
    {
        final String STR_METHOD_NAME = " testIsOrderUnitUpdateObjSwitchOrderRoute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.productType = "1";
        l_adminOrderRouteSwitchingInfo.marketCode= "N1";
        l_adminOrderRouteSwitchingInfo.frontOrderSystemCode = "1";
        try
        {
            
            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            TestDBUtility.deleteAllAndCommit(l_orderSwitchingParams.getRowType());
            l_orderSwitchingParams.setOrderEngineDiv("2");
            l_orderSwitchingParams.setValidFlag("1");
            l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_adminSwitchOrderRouteServiceImpl,
                    new Object[]{l_strInstitutionCode,l_adminOrderRouteSwitchingInfo});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "isOrderUnitUpdateObjSwitchOrderRoute",
                new Class[] {WEB3GentradeOrderSwitching.class});
            method.setAccessible(true);
            boolean l_lbnResult = ((Boolean)method.invoke(l_adminSwitchOrderRouteServiceImpl,
                new Object[]{l_gentradeOrderSwitching})).booleanValue();
            assertFalse(l_lbnResult);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    public void testUpdateEqtypeOrderAll_0001()
    {
        final String STR_METHOD_NAME = " .testUpdateEqtypeOrderAll_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.changedValidFlag = "1";
        l_adminOrderRouteSwitchingInfo.submitOrderRouteDiv = "3";
        
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        try
        {
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = TestDBUtility.getHostEqtypeOrderAllRow();
            TestDBUtility.deleteAllAndCommit(l_hostEqtypeOrderAllParams.getRowType());
            l_hostEqtypeOrderAllParams.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            l_hostEqtypeOrderAllParams.setAccountId(213L);
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams1.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            l_hostEqtypeOrderAllParams1.setSubmitOrderRouteDiv("6");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            List l_lisHostEqtypeOrderAll = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and order_request_number = ? ");
            l_sbWhere.append(" and status = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere =
                {
                    Long.toString(l_hostEqtypeOrderAllParams.getAccountId()),
                    l_hostEqtypeOrderAllParams.getOrderRequestNumber(),
                    l_hostEqtypeOrderAllParams.getStatus()
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            l_lisHostEqtypeOrderAll = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                            l_sbWhere.toString(),
                                            l_objWhere);
            
            l_adminSwitchOrderRouteServiceImpl.updateEqtypeOrderAll(
                l_hostEqtypeOrderAllParams,l_adminOrderRouteSwitchingInfo);
            
            List l_lisHostEqtypeOrderAllList = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere1 = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere1 =
                {
                    Long.toString(213L)
                };
            QueryProcessor qp1 = Processors.getDefaultProcessor();
            l_lisHostEqtypeOrderAllList = qp1.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                l_sbWhere1.toString(),
                                                l_objWhere1
                                                );
            assertEquals("3",((HostEqtypeOrderAllParams)l_lisHostEqtypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
        
    }
    
    public void testUpdateEqtypeOrderAll_0002()
    {
        final String STR_METHOD_NAME = " .testUpdateEqtypeOrderAll_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.changedValidFlag = "1";
        l_adminOrderRouteSwitchingInfo.submitOrderRouteDiv = "3";
        
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        try
        {
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = TestDBUtility.getHostEqtypeOrderAllRow();
            TestDBUtility.deleteAllAndCommit(l_hostEqtypeOrderAllParams.getRowType());
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("11111");
            l_hostEqtypeOrderAllParams.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("9");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams1.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            l_hostEqtypeOrderAllParams1.setSubmitOrderRouteDiv("6");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            List l_lisHostEqtypeOrderAll = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and order_request_number = ? ");
            l_sbWhere.append(" and status = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere =
                {
                    Long.toString(l_hostEqtypeOrderAllParams.getAccountId()),
                    l_hostEqtypeOrderAllParams.getOrderRequestNumber(),
                    l_hostEqtypeOrderAllParams.getStatus()
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            l_lisHostEqtypeOrderAll = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                            l_sbWhere.toString(),
                                            l_objWhere);
            
            l_adminSwitchOrderRouteServiceImpl.updateEqtypeOrderAll(
                l_hostEqtypeOrderAllParams,l_adminOrderRouteSwitchingInfo);
            
            List l_lisHostEqtypeOrderAllList = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere1 = new StringBuffer();
            l_sbWhere.append(" order_request_number = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere1 =
                {
                    "11111"
                };
            QueryProcessor qp1 = Processors.getDefaultProcessor();
            l_lisHostEqtypeOrderAllList = qp1.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                l_sbWhere1.toString(),
                                                l_objWhere1
                                                );
            assertEquals("3",((HostEqtypeOrderAllParams)l_lisHostEqtypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
    }
 
    public void testUpdateEqtypeOrderAll_0003()
    {
        final String STR_METHOD_NAME = " .testUpdateEqtypeOrderAll_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.changedValidFlag = "1";
        l_adminOrderRouteSwitchingInfo.submitOrderRouteDiv = "3";
        
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        try
        {
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = TestDBUtility.getHostEqtypeOrderAllRow();
            TestDBUtility.deleteAllAndCommit(l_hostEqtypeOrderAllParams.getRowType());
            l_hostEqtypeOrderAllParams.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("9");
            l_hostEqtypeOrderAllParams.setStatus("1");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams1.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            l_hostEqtypeOrderAllParams1.setSubmitOrderRouteDiv("6");
            l_hostEqtypeOrderAllParams1.setStatus("7");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            List l_lisHostEqtypeOrderAll = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");
            l_sbWhere.append(" and order_request_number = ? ");
            l_sbWhere.append(" and status = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere =
                {
                    Long.toString(l_hostEqtypeOrderAllParams.getAccountId()),
                    l_hostEqtypeOrderAllParams.getOrderRequestNumber(),
                    l_hostEqtypeOrderAllParams.getStatus()
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            l_lisHostEqtypeOrderAll = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                            l_sbWhere.toString(),
                                            l_objWhere);
            
            l_adminSwitchOrderRouteServiceImpl.updateEqtypeOrderAll(
                l_hostEqtypeOrderAllParams,l_adminOrderRouteSwitchingInfo);
            
            List l_lisHostEqtypeOrderAllList = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere1 = new StringBuffer();
            l_sbWhere.append(" order_request_number = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere1 =
                {
                    "7"
                };
            QueryProcessor qp1 = Processors.getDefaultProcessor();
            l_lisHostEqtypeOrderAllList = qp1.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                l_sbWhere1.toString(),
                                                l_objWhere1
                                                );
            assertEquals("3",((HostEqtypeOrderAllParams)l_lisHostEqtypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        } 
        
    }
    
    public void testUpdateIfoOrderAll_0001()
    {
        final String STR_METHOD_NAME = " .testUpdateIfoOrderAll_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = 
            new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.changedValidFlag = "1";
        l_adminOrderRouteSwitchingInfo.submitOrderRouteDiv = "6";
        
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        
        try
        {
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            TestDBUtility.deleteAllAndCommit(l_hostFotypeOrderAllParams.getRowType());
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            
            List l_lisHostFotypeOrderAllList1 = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere1 = new StringBuffer();
            l_sbWhere1.append(" account_id = ? ");
            l_sbWhere1.append(" and order_request_number = ? ");
            l_sbWhere1.append(" and status = ? ");
            
            // 検索条件コンテナの生成
            Object[] l_objWhere1 =
                {
                    Long.toString(l_hostFotypeOrderAllParams.getAccountId()),
                    l_hostFotypeOrderAllParams.getOrderRequestNumber(),
                    l_hostFotypeOrderAllParams.getStatus()
                };

            QueryProcessor qp1 = Processors.getDefaultProcessor();
            l_lisHostFotypeOrderAllList1 = qp1.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                                l_sbWhere1.toString(),
                                                l_objWhere1);
            
            
            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateIfoOrderAll",
                new Class[] {HostFotypeOrderAllRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
            method.setAccessible(true);
            method.invoke(l_adminSwitchOrderRouteServiceImpl,new Object[]{l_hostFotypeOrderAllParams,
                l_adminOrderRouteSwitchingInfo});
            
            List l_lisHostFotypeOrderAllList = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? " );
            
            // 検索条件コンテナの生成
            Object[] l_objWhere =
                {
                    Long.toString(l_hostFotypeOrderAllParams.getAccountId())
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            l_lisHostFotypeOrderAllList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                l_sbWhere.toString(),
                                                l_objWhere);
            assertEquals("6",((HostFotypeOrderAllRow)l_lisHostFotypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testUpdateIfoOrderAll_0002()
    {
        final String STR_METHOD_NAME = " .testUpdateIfoOrderAll_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminOrderRouteSwitchingInfo l_adminOrderRouteSwitchingInfo = 
            new WEB3AdminOrderRouteSwitchingInfo();
        l_adminOrderRouteSwitchingInfo.changedValidFlag = "0";
        
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl =
            new WEB3AdminSwitchOrderRouteServiceImpl();
        
        try
        {
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
            TestDBUtility.deleteAllAndCommit(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateIfoOrderAll",
                new Class[] {HostFotypeOrderAllRow.class,WEB3AdminOrderRouteSwitchingInfo.class});
            method.setAccessible(true);
            method.invoke(l_adminSwitchOrderRouteServiceImpl,new Object[]{l_hostFotypeOrderAllParams,
                l_adminOrderRouteSwitchingInfo});
            
            List l_lisHostFotypeOrderAllList = new ArrayList(); 
            // 更新条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? " );
            
            // 検索条件コンテナの生成
            Object[] l_objWhere =
                {
                    Long.toString(l_hostFotypeOrderAllParams.getAccountId())
                };
            QueryProcessor qp = Processors.getDefaultProcessor();
            l_lisHostFotypeOrderAllList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                                l_sbWhere.toString(),
                                                l_objWhere);
            assertEquals("9",((HostFotypeOrderAllParams)l_lisHostFotypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    // ------------------------------->
    public void testValidateOrderRouteChange_0001()
    {
        String STR_METHOD_NAME = "testValidateOrderRouteChange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest());
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());
        
        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        
        WEB3AdminOrderRouteSwitchingInfo infoUnit = new WEB3AdminOrderRouteSwitchingInfo();
        // 発注経路区分を取得
         infoUnit.submitOrderRouteDiv = "3";
        // 変更後有効フラグを取得
        infoUnit.changedValidFlag = "1";
        infoUnit.frontOrderSystemCode = "123";
        infoUnit.productType = "123";
        WEB3AdminSwitchOrderRouteConfirmRequestForTest l_request = new WEB3AdminSwitchOrderRouteConfirmRequestForTest();
        l_request.infoUnit = infoUnit;
        l_request.institutionCode = "123";
        try
        {
            l_adminSwitchOrderRouteServiceImpl.validateOrderRouteChange(l_request);
            String l_expect = " 証券会社コード = 123 フロント発注取引所区分コード = 1 フロント発注システム区分 = 123 銘柄タイプ = 123";
            
            assertEquals(l_expect , WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.toString());
            
            Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
            Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
                new WEB3AdminDirSecFrontOrderCommonServiceImpl());
            Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
                new WEB3LogSysTimeInterceptor());
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testUpdateOrderSwitching_0001()
    {
        String STR_METHOD_NAME = "testUpdateOrderSwitching_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        OrderSwitchingParams l_orderSwitchingParams1 = TestDBUtility.getOrderSwitchingRow();
        //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams1.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.ORDER_STOP);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(l_orderSwitchingParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        infolist.marketCode = "N1";
        infolist.frontOrderSystemCode = "1";
        infolist.productType = "6";
        infolist.submitOrderRouteDiv = "0";
        infolist.changedValidFlag = "1";
        try
        {
            l_adminSwitchOrderRouteServiceImpl.updateOrderSwitching("0D", infolist);
            
            OrderSwitchingPK l_orderSwitchingPK = new OrderSwitchingPK("0D", ProductTypeEnum.IFO, "N1", "0", "1");
            
            QueryProcessor qp = Processors.getDefaultProcessor();
            OrderSwitchingParams l_orderSwitchingParamsTemp = 
                (OrderSwitchingParams)qp.doFindByPrimaryKeyQuery(l_orderSwitchingPK);
            
            assertEquals("1" , l_orderSwitchingParamsTemp.valid_flag);

            OrderSwitchingPK l_orderSwitchingPK1 = new OrderSwitchingPK("0D", ProductTypeEnum.IFO, "N1", "9", "1");
            OrderSwitchingParams l_orderSwitchingParamsTemp1 = 
                (OrderSwitchingParams)qp.doFindByPrimaryKeyQuery(l_orderSwitchingPK1);
            
            assertEquals("0" , l_orderSwitchingParamsTemp1.valid_flag);

        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0001()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //フロント発注取引区分コード 
        l_hostEqtypeOrderAllParams1.setFrontOrderTradeCode("2");
        
        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"1"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getFrontOrderTradeCode());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0002()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //フロント発注取引所区分コード
        l_hostEqtypeOrderAllParams1.setFrontOrderExchangeCode("2");
        
        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"1"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getFrontOrderExchangeCode());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0003()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //処理区分（ステータス）
        l_hostEqtypeOrderAllParams1.setStatus("1");
        
        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"0"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getStatus());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0004()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //全訂正処理区分
        l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("1");
        
        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"0"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getAllOrderChangeDiv());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0005()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams1.setSonarTradedCode("12");

        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"11"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getSonarTradedCode());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0006()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams.setSonarTradedCode("51");

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams1.setSonarTradedCode("12");

        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"51"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getSonarTradedCode());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0007()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams.setSonarTradedCode("52");

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams1.setSonarTradedCode("12");

        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {"52"};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getSonarTradedCode());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSelectEqtypeOrderAll_0008()
    {
        String STR_METHOD_NAME = "testSelectEqtypeOrderAll_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams.setSonarTradedCode(null);

        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = getHostEqtypeOrderAllRow();
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams1.setSonarTradedCode("12");

        try
        {
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams1);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();            
        }

        WEB3AdminSwitchOrderRouteServiceImpl l_adminSwitchOrderRouteServiceImpl = 
            new WEB3AdminSwitchOrderRouteServiceImpl();
        WEB3AdminOrderRouteSwitchingInfo infolist = new WEB3AdminOrderRouteSwitchingInfo();
        //フロント発注システム区分 
        infolist.frontOrderSystemCode = "1";
        //証券会社コード 
        String institutioncode = "0D"; 
        //フロント発注取引所区分コード
        String frotExCode = "1";
        
        String[] l_expectFec = {null};
        try
        {
            List list = l_adminSwitchOrderRouteServiceImpl.selectEqtypeOrderAll(institutioncode, frotExCode, infolist);
            
            for (int i = 0; i < list.size(); i++)
            {
                assertEquals(l_expectFec[i] , ((HostEqtypeOrderAllParams)list.get(i)).getSonarTradedCode());
            }
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitOrderRouteChange_0001()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        String STR_METHOD_NAME = "testSubmitOrderRouteChange_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(OrderSwitchingParams.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);

            OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
            l_orderSwitchingParams.setInstitutionCode("0D");
            l_orderSwitchingParams.setValidFlag("1");
            TestDBUtility.insertWithDel(l_orderSwitchingParams);

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest());
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());
        
        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setInstitutionId(33L);
        l_administratorParams.setPermissionLevel("1");
        l_administratorParams.setAdministratorCode("123");
        l_administratorParams.setLoginId(123);
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        try
        {
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, 
                WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH, 
                true, 
                true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminSwitchOrderRouteServiceImplForTest l_adminSwitchOrderRouteServiceImplForTest = 
            new WEB3AdminSwitchOrderRouteServiceImplForTest();
        
        WEB3AdminSwitchOrderRouteCompleteRequestForTest l_request = 
            new WEB3AdminSwitchOrderRouteCompleteRequestForTest();
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();
        l_switchInfoObj.convertMarketCode = "123";
        l_switchInfoObj.marketCode = "N1";
        l_switchInfoObj.submitOrderRouteDiv = "3";
        l_switchInfoObj.changedValidFlag = "1";
        l_switchInfoObj.frontOrderSystemCode = "1";
        l_switchInfoObj.productType = "6";
        l_request.infoUnit = l_switchInfoObj;
        l_request.password = "1234";
        l_request.institutionCode = "0D";
        try
        {
            l_adminSwitchOrderRouteServiceImplForTest.submitOrderRouteChange(l_request);
            
            String l_expect = " 証券会社コード = 0D フロント発注取引所区分コード = 1 フロント発注システム区分 = 1 銘柄タイプ = 6";
            assertEquals(l_expect , l_sbfInterface.toString());
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(MarketParams.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(OrderSwitchingParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderAllParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    public void testSubmitOrderRouteChange_0002()
    {
        String STR_METHOD_NAME = "testSubmitOrderRouteChange_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "lockAccount",
            new Class[] {String.class,String.class,String.class},
            null);
        
        WEB3GentradeAccountManagerForMock mock = new WEB3GentradeAccountManagerForMock();
        
        Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest());
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());
        
        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setInstitutionId(123);
        l_administratorParams.setPermissionLevel("1");
        l_administratorParams.setAdministratorCode("123");
        l_administratorParams.setLoginId(123);
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = TestDBUtility.getHostEqtypeOrderAllRow();
        l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
        l_hostEqtypeOrderAllParams.setStatus("0");
        l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
        l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
        l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
        l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
        
        try
        {
            mock.lockAccount("0D", "381", "2512246");
            
            TestDBUtility.deleteAll(l_hostEqtypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);
            
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, 
                WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH, 
                true, 
                true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminSwitchOrderRouteCompleteRequestForTest l_request = 
            new WEB3AdminSwitchOrderRouteCompleteRequestForTest();
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();
        l_switchInfoObj.convertMarketCode = "123";
        l_switchInfoObj.submitOrderRouteDiv = "3";
        l_switchInfoObj.changedValidFlag = "0";
        l_switchInfoObj.frontOrderSystemCode = "1";
        l_switchInfoObj.productType = "1";
        l_switchInfoObj.marketCode = WEB3MarketCodeDef.HONGKONG;
        l_request.infoUnit = l_switchInfoObj;
        l_request.password = "1234";
        l_request.institutionCode = "0D";
        
        WEB3AdminSwitchOrderRouteServiceImplForTest l_adminSwitchOrderRouteServiceImplForTest = 
            new WEB3AdminSwitchOrderRouteServiceImplForTest();
        
        try
        {
            l_adminSwitchOrderRouteServiceImplForTest.submitOrderRouteChange(l_request);
            
            assertEquals("updateEqtypeOrder(String, String, WEB3AdminOrderRouteSwitchingInfo)" , 
                WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.toString());
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSubmitOrderRouteChange_0003()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        String STR_METHOD_NAME = "testSubmitOrderRouteChange_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Services.unregisterService(WEB3AdminDirSecFrontOrderCommonService.class);
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImplForTest());
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());
        
        //<----UpdateIfoOrder date begin
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "lockAccount",
            new Class[] {String.class,String.class,String.class},
            null);

        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        //フロント発注取引所区分コード
        l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
        //フロント発注システム区分
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード
        l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
        //処理区分
        l_hostFotypeOrderAllParams.setStatus("0");
        //全訂正処理区分
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setFrontOrderSystemCode("1");
        l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.ON);
        //12  発注エンジン区分    order_engine_div    VARCHAR2   1   NotNull
        l_orderSwitchingParams.setOrderEngineDiv(WEB3OrderEngineDivDef.DEOS);
        
        WEB3GentradeAccountManagerForMock mock = new WEB3GentradeAccountManagerForMock();
        //---->UpdateIfoOrder date end
        
        AdministratorParams l_administratorParams = new AdministratorParams();
        l_administratorParams.setInstitutionCode("0D");
        l_administratorParams.setInstitutionId(123);
        l_administratorParams.setPermissionLevel("1");
        l_administratorParams.setAdministratorCode("123");
        l_administratorParams.setLoginId(123);
        WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
        try
        {
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, 
                WEB3TransactionCategoryDef.ADMIN_FRONT_ROUTE_SWITCH, 
                true, 
                true);
            WEB3AdministratorForMock.mockIsDirAdministrator(l_administrator, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            //<-----UpdateIfoOrder date begin
            mock.lockAccount("0D", "381", "2512246");
            
            TestDBUtility.deleteAllAndCommit(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.deleteAll(l_orderSwitchingParams.getRowType());
            
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            //----->UpdateIfoOrder date end

        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminSwitchOrderRouteServiceImplForTest l_adminSwitchOrderRouteServiceImplForTest = 
            new WEB3AdminSwitchOrderRouteServiceImplForTest();
        
        WEB3AdminSwitchOrderRouteCompleteRequestForTest l_request = 
            new WEB3AdminSwitchOrderRouteCompleteRequestForTest();
        WEB3AdminOrderRouteSwitchingInfo l_switchInfoObj = new WEB3AdminOrderRouteSwitchingInfo();
        l_switchInfoObj.convertMarketCode = "123";
        l_switchInfoObj.submitOrderRouteDiv = "3";
        l_switchInfoObj.changedValidFlag = "0";
        l_switchInfoObj.frontOrderSystemCode = "1";
        l_switchInfoObj.productType = "6";
        l_switchInfoObj.marketCode = WEB3MarketCodeDef.HONGKONG;
        l_request.infoUnit = l_switchInfoObj;
        l_request.password = "1234";
        l_request.institutionCode = "1234";
        try
        {
            l_adminSwitchOrderRouteServiceImplForTest.submitOrderRouteChange(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lstHostFotypeOrderAllRow = 
                l_queryProcessor.doFindAllQuery(HostFotypeOrderAllRow.TYPE);
            
            assertEquals(1 , l_lstHostFotypeOrderAllRow.size());
            assertEquals("9" , ((HostFotypeOrderAllRow)l_lstHostFotypeOrderAllRow.get(0)).getSubmitOrderRouteDiv());
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    
        
    }
    
    //<-------myn begin
    public void test_updateEqtypeOrder_0001()
    {
        String STR_METHOD_NAME = "test_updateEqtypeOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try 
        {
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams.setAccountCode("251224");
            
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams.setStatus("0");
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setAccountId(333812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams1.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams1.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams1.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams1.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams1.setAccountCode("2512246");
            
            l_hostEqtypeOrderAllParams1.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams1.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams1.setStatus("0");
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams1.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams1.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams1.setAccountId(812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = new InstitutionParams();

            l_institutionParams.setInstitutionCode("55");
            l_institutionParams.setInstitutionId(33);
            
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "0";
            ｌ_info.productType = "1";
            ｌ_info.submitOrderRouteDiv = "10";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            
            WEB3MockObjectParamsValue l_ob = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount", 
                new Class[] { String.class,String.class,String.class });
           
            assertEquals("55", l_ob.getCalled(0)[0].toString());
            assertEquals("381", l_ob.getCalled(0)[1].toString());
            assertEquals("2512246", l_ob.getCalled(0)[2].toString());
            
            assertEquals("55", l_ob.getCalled(1)[0].toString());
            assertEquals("381", l_ob.getCalled(1)[1].toString());
            assertEquals("251224", l_ob.getCalled(1)[2].toString());
            
            List l_hostEqtypeOrderAllList = new ArrayList();       
            
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                "55"         
            };
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            l_hostEqtypeOrderAllList = qp.doFindAllQuery(HostEqtypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
            
            assertEquals("9", ((HostEqtypeOrderAllRow)l_hostEqtypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
            assertEquals("9", ((HostEqtypeOrderAllRow)l_hostEqtypeOrderAllList.get(1)).getSubmitOrderRouteDiv());

        } 
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex.getTargetException());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateEqtypeOrder_0002()
    {
        String STR_METHOD_NAME = "test_updateEqtypeOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try 
        {
          TestDBUtility.deleteAllAndCommit(OrderSwitchingRow.TYPE);
          
          OrderSwitchingParams l_orderSwitchingParams = new OrderSwitchingParams();
          //1   証券会社コード    institution_code    VARCHAR2   3   NotNull
          l_orderSwitchingParams.setInstitutionCode("55");
          l_orderSwitchingParams.setProductType(ProductTypeEnum.EQUITY);
          //3   市場コード    market_code    VARCHAR2   2   NotNull
          l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.HONGKONG);
          //4   市場コード（SONAR）    sonar_market_code    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setSonarMarketCode("G");
          //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
          //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.ON);
          //7   訂正取消可能フラグ    change_cancel_enable_flag    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.DISABLE);
          //8   作成日付    created_timestamp    DATE      NotNull
          l_orderSwitchingParams.setCreatedTimestamp(Calendar.getInstance().getTime());
          //9   更新日付    last_updated_timestamp    DATE      NotNull
          l_orderSwitchingParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
          //11  MQトリガ発行    submit_mq_trigger    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.NOT_TRIGGER);
          //12  発注エンジン区分    order_engine_div    VARCHAR2   1   NotNull
          l_orderSwitchingParams.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
          
          TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);
          
          OrderSwitchingParams l_orderSwitchingParams1 = new OrderSwitchingParams();
          //1   証券会社コード    institution_code    VARCHAR2   3   NotNull
          l_orderSwitchingParams1.setInstitutionCode("55");
          l_orderSwitchingParams1.setProductType(ProductTypeEnum.EQUITY);
          //3   市場コード    market_code    VARCHAR2   2   NotNull
          l_orderSwitchingParams1.setMarketCode(WEB3MarketCodeDef.HONGKONG);
          //4   市場コード（SONAR）    sonar_market_code    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setSonarMarketCode("f");
          //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_SUB_FACTION);
          //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setValidFlag(WEB3ValidFlag.ON);
          //7   訂正取消可能フラグ    change_cancel_enable_flag    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.DISABLE);
          //8   作成日付    created_timestamp    DATE      NotNull
          l_orderSwitchingParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
          //9   更新日付    last_updated_timestamp    DATE      NotNull
          l_orderSwitchingParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
          //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
          //11  MQトリガ発行    submit_mq_trigger    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.NOT_TRIGGER);
          //12  発注エンジン区分    order_engine_div    VARCHAR2   1   NotNull
          l_orderSwitchingParams1.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
          
          TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams1);
            
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams.setAccountCode("251224");
            
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams.setStatus("0");
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setAccountId(333812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams1.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams1.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams1.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams1.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams1.setAccountCode("2512246");
            
            l_hostEqtypeOrderAllParams1.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams1.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams1.setStatus("0");
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams1.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams1.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams1.setAccountId(812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = new InstitutionParams();

            l_institutionParams.setInstitutionCode("55");
            l_institutionParams.setInstitutionId(33);
            
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "0";
            ｌ_info.productType = "1";
            ｌ_info.submitOrderRouteDiv = "10";
            ｌ_info.marketCode = "N1";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            fail();

        } 
        catch (InvocationTargetException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02187, ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateEqtypeOrder_0003()
    {
        String STR_METHOD_NAME = "test_updateEqtypeOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try 
        {
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams.setAccountCode("251224");
            
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams.setStatus("0");
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setAccountId(333812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams1.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams1.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams1.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams1.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams1.setAccountCode("2512246");
            
            l_hostEqtypeOrderAllParams1.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams1.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams1.setStatus("0");
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams1.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams1.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams1.setAccountId(812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = new InstitutionParams();

            l_institutionParams.setInstitutionCode("55");
            l_institutionParams.setInstitutionId(33);
            
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradedProductParams.TYPE);
            
            MarketParams l_marketParams = new MarketParams();

            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("55");
            l_marketParams.setMarketCode("10");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512203L);
            l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderUnitParams.setBranchId(33381L);
            l_eqtypeOrderUnitParams.setTraderId(3338111123L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
            l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setQuantity(1200);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(200);
            l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams.setExecutedAmount(2000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(500);
            l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setSonarTradedCode("11");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "1";
            ｌ_info.productType = "1";
            ｌ_info.submitOrderRouteDiv = "5";
            ｌ_info.marketCode = "10";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            
            fail();
        } 
        catch (InvocationTargetException l_ex)
        {
            log.error(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateEqtypeOrder_0004()
    {
        String STR_METHOD_NAME = "test_updateEqtypeOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try 
        {
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams.setAccountCode("251224");
            
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams.setStatus("0");
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setAccountId(333812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams1.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams1.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams1.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams1.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams1.setAccountCode("2512246");
            
            l_hostEqtypeOrderAllParams1.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams1.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams1.setStatus("0");
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams1.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams1.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams1.setAccountId(812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = new InstitutionParams();

            l_institutionParams.setInstitutionCode("55");
            l_institutionParams.setInstitutionId(33);
            
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            
            MarketParams l_marketParams = new MarketParams();

            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("55");
            l_marketParams.setMarketCode("10");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512203L);
            l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderUnitParams.setBranchId(33381L);
            l_eqtypeOrderUnitParams.setTraderId(3338111123L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
            l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setQuantity(1200);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(200);
            l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams.setExecutedAmount(2000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(500);
            l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setSonarTradedCode("11");
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductRow.TYPE);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setMarketId(3303L);
            l_eqtypeTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setListType("1");
            l_eqtypeTradedProductParams.setNewListType("1");
            l_eqtypeTradedProductParams.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductParams.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setLotSize(100.0D);
            l_eqtypeTradedProductParams.setLastClosingPrice(1000.0D);
            l_eqtypeTradedProductParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductParams.setBasePrice(10.0D);
            l_eqtypeTradedProductParams.setOpenOtcDiv("3");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = new TradedProductParams();
         
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setMarginRatio(70.000000D);
            l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(FeqTradedProductRow.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = new FeqTradedProductParams();

            l_feqTradedProductParams.setTradedProductId(330304148080000L);
            l_feqTradedProductParams.setInstitutionCode("0D");
            l_feqTradedProductParams.setProductId(3304148080000L);
            l_feqTradedProductParams.setMarketId(3303L);
            l_feqTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_feqTradedProductParams.setListedDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_feqTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_feqTradedProductParams.setLastClosingPrice(0.000000D);
            l_feqTradedProductParams.setTradeStop(1);
            l_feqTradedProductParams.setBuyStop(0);
            l_feqTradedProductParams.setSellStop(0);
            l_feqTradedProductParams.setBuyLotSize(100.000000D);
            l_feqTradedProductParams.setBuyMinQty(100.000000D);
            l_feqTradedProductParams.setSellLotSize(100.000000D);
            l_feqTradedProductParams.setSellMinQty(100.000000D);
            l_feqTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_feqTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDelAndCommit(l_feqTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("N8080");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            TestDBUtility.deleteAllAndCommit(ProductRow.TYPE);
            ProductParams l_productParams = new ProductParams();

            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "1";
            ｌ_info.productType = "1";
            ｌ_info.submitOrderRouteDiv = "5";
            ｌ_info.marketCode = "10";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            
            List l_eqtypeOrderUnitList = new ArrayList();       
            
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" submit_order_route_div = ? ");
            
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                "2"         
            };
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            l_eqtypeOrderUnitList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
            
            assertEquals("2", ((EqtypeOrderUnitRow)l_eqtypeOrderUnitList.get(0)).getSubmitOrderRouteDiv());
            
            
        } 
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex.getTargetException());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateEqtypeOrder_0005()
    {
        String STR_METHOD_NAME = "test_updateEqtypeOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try 
        {
            TestDBUtility.deleteAllAndCommit(HostEqtypeOrderAllRow.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams.setAccountCode("251224");
            
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams.setStatus("0");
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setAccountId(333812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);
            
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams1 = new HostEqtypeOrderAllParams();
            
            //フロント発注取引所区分コード
            l_hostEqtypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //証券会社コード
            l_hostEqtypeOrderAllParams1.setInstitutionCode("55");
            //社内処理項目
            l_hostEqtypeOrderAllParams1.setCorpCode("21313");
            //全訂正処理区分
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            //取消区分
            l_hostEqtypeOrderAllParams1.setCancelDiv("1");
            
            l_hostEqtypeOrderAllParams1.setBranchCode("381");
            
            l_hostEqtypeOrderAllParams1.setAccountCode("2512246");
            
            l_hostEqtypeOrderAllParams1.setFrontOrderTradeCode("1");
            l_hostEqtypeOrderAllParams1.setFrontOrderSystemCode("1");
            l_hostEqtypeOrderAllParams1.setStatus("0");
            l_hostEqtypeOrderAllParams1.setAllOrderChangeDiv("0");
            l_hostEqtypeOrderAllParams1.setSonarTradedCode("11");
            l_hostEqtypeOrderAllParams1.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams1.setAccountId(812512246L);
            
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams1);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = new InstitutionParams();

            l_institutionParams.setInstitutionCode("55");
            l_institutionParams.setInstitutionId(33);
            
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            
            MarketParams l_marketParams = new MarketParams();

            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("55");
            l_marketParams.setMarketCode("10");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams.setOrderId(123456789L);
            l_eqtypeOrderUnitParams.setAccountId(333812512203L);
            l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderUnitParams.setBranchId(33381L);
            l_eqtypeOrderUnitParams.setTraderId(3338111123L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
            l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setQuantity(1200);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams.setBizDate("20070117");
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(200);
            l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            l_eqtypeOrderUnitParams.setMarketId(3303L);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams.setExecutedAmount(2000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(500);
            l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setSonarTradedCode("11");
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
            
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = new EqtypeOrderUnitParams();
            l_eqtypeOrderUnitParams1.setOrderUnitId(330414808L);
            l_eqtypeOrderUnitParams1.setOrderId(1234567L);
            l_eqtypeOrderUnitParams1.setAccountId(33381251220L);
            l_eqtypeOrderUnitParams1.setSubAccountId(333812512201L);
            l_eqtypeOrderUnitParams1.setBranchId(33381L);
            l_eqtypeOrderUnitParams1.setTraderId(3338111123L);
            l_eqtypeOrderUnitParams1.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_eqtypeOrderUnitParams1.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_eqtypeOrderUnitParams1.setLastOrderActionSerialNo(0126);
            l_eqtypeOrderUnitParams1.setLastExecutionSerialNo(8126);
            l_eqtypeOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams1.setQuantity(1200);
            l_eqtypeOrderUnitParams1.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams1.setDeliveryDate(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams1.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams1.setSwapTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams1.setBizDate("20070117");
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setQuantityType(QuantityTypeEnum.QUANTITY);
            l_eqtypeOrderUnitParams1.setExecutedQuantity(200);
            l_eqtypeOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_eqtypeOrderUnitParams1.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            l_eqtypeOrderUnitParams1.setMarketId(3303L);
            l_eqtypeOrderUnitParams1.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
            l_eqtypeOrderUnitParams1.setExecutedAmount(2000);
            l_eqtypeOrderUnitParams1.setExecutedQuantity(500);
            l_eqtypeOrderUnitParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams1.setSonarTradedCode("11");
            l_eqtypeOrderUnitParams1.setSubmitOrderRouteDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductRow.TYPE);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            l_eqtypeTradedProductParams.setProductId(3304148080001L);
            l_eqtypeTradedProductParams.setInstitutionCode("0D");
            l_eqtypeTradedProductParams.setMarketId(3303L);
            l_eqtypeTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setListType("1");
            l_eqtypeTradedProductParams.setNewListType("1");
            l_eqtypeTradedProductParams.setListedDate(Calendar.getInstance().getTime());
            l_eqtypeTradedProductParams.setMarginableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setShortableFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setMiniStockCanDealt(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setLotSize(100.0D);
            l_eqtypeTradedProductParams.setLastClosingPrice(1000.0D);
            l_eqtypeTradedProductParams.setMiniStockFlag(BooleanEnum.TRUE);
            l_eqtypeTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeTradedProductParams.setBasePrice(10.0D);
            l_eqtypeTradedProductParams.setOpenOtcDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = new TradedProductParams();
         
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setMarginRatio(70.000000D);
            l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(FeqTradedProductRow.TYPE);
            FeqTradedProductParams l_feqTradedProductParams = new FeqTradedProductParams();

            l_feqTradedProductParams.setTradedProductId(330304148080000L);
            l_feqTradedProductParams.setInstitutionCode("0D");
            l_feqTradedProductParams.setProductId(3304148080000L);
            l_feqTradedProductParams.setMarketId(3303L);
            l_feqTradedProductParams.setListFlag(BooleanEnum.TRUE);
            l_feqTradedProductParams.setListedDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_feqTradedProductParams.setUnlistedDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_feqTradedProductParams.setLastClosingPrice(0.000000D);
            l_feqTradedProductParams.setTradeStop(1);
            l_feqTradedProductParams.setBuyStop(0);
            l_feqTradedProductParams.setSellStop(0);
            l_feqTradedProductParams.setBuyLotSize(100.000000D);
            l_feqTradedProductParams.setBuyMinQty(100.000000D);
            l_feqTradedProductParams.setSellLotSize(100.000000D);
            l_feqTradedProductParams.setSellMinQty(100.000000D);
            l_feqTradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_feqTradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDelAndCommit(l_feqTradedProductParams);
            
            TestDBUtility.deleteAllAndCommit(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_eqtypeProductParams.setProductId(3304148080001L);
            l_eqtypeProductParams.setInstitutionCode("0D");
            l_eqtypeProductParams.setProductCode("N8080");
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeProductParams.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqtypeProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_eqtypeProductParams);
            
            TestDBUtility.deleteAllAndCommit(ProductRow.TYPE);
            ProductParams l_productParams = new ProductParams();

            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = new MainAccountParams();

            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("内藤　@四郎");
            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            MainAccountParams l_mainAccountParams1 = new MainAccountParams();
            l_mainAccountParams1.setAccountId(33381251220L);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setInstitutionId(32L);
            l_mainAccountParams1.setAccountCode("2512247");
            l_mainAccountParams1.setBranchId(333L);
            l_mainAccountParams1.setBranchCode("382");
            l_mainAccountParams1.setSonarTraderCode("11124");
            l_mainAccountParams1.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams1.setFamilyName("内藤　@四郎");
            l_mainAccountParams1.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams1.setGivenNameAlt1("Siro");
            l_mainAccountParams1.setZipCode("1001238");
            l_mainAccountParams1.setSubZipCode("1001238");
            l_mainAccountParams1.setAddressLine1("東京都");
            l_mainAccountParams1.setAddressLine2("江東区");
            l_mainAccountParams1.setAddressLine3("深川５");
            l_mainAccountParams1.setTelephone("38201115");
            l_mainAccountParams1.setMobile("901115");
            l_mainAccountParams1.setFax("38202226");
            l_mainAccountParams1.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setResident("0");
            l_mainAccountParams1.setNewAccountDiv("0");
            l_mainAccountParams1.setViaTrustBankDiv("0");
            l_mainAccountParams1.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams1.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams1.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams1.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams1.setPersonIdentify("1");
            l_mainAccountParams1.setEraBorn("3");
            l_mainAccountParams1.setBornDate("101013");
            l_mainAccountParams1.setSex("1");
            l_mainAccountParams1.setYellowCustomer("0");
            l_mainAccountParams1.setHtSettlementWay("0");
            l_mainAccountParams1.setBankAccountRegi("0");
            l_mainAccountParams1.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams1.setExaminLockFlag("0");
            l_mainAccountParams1.setMngLockFlag("0");
            l_mainAccountParams1.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams1.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams1.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams1.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams1.setBranchLock("0");
            l_mainAccountParams1.setOrderPermission("0");
            l_mainAccountParams1.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams1.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams1.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams1.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams1.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams1.setMarginSysAccOpenDiv("0");
            l_mainAccountParams1.setMarginGenAccOpenDiv("0");
            l_mainAccountParams1.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams1.setForeignSecAccOpenDiv("1");
            l_mainAccountParams1.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams1.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams1.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams1.setRuitoAccOpenDiv("0");
            l_mainAccountParams1.setMrfAccOpenDiv("0");
            l_mainAccountParams1.setFxAccOpenDiv("0");
            l_mainAccountParams1.setFeqConAccOpenDiv("0");
            l_mainAccountParams1.setTopPageId("0");
            l_mainAccountParams1.setQuotoType("0");
            l_mainAccountParams1.setTradingReportDiv("1");
            l_mainAccountParams1.setPositionReportDiv("9");
            l_mainAccountParams1.setPositionReportCycleDiv("1");
            l_mainAccountParams1.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setEmailLastUpdater("2512246");
            l_mainAccountParams1.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setTradingPasswordUpdater("2512246");
            l_mainAccountParams1.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setMbOffLastUpdater("2512246");
            l_mainAccountParams1.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams1.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams1.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams1.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setMrfFundCode("1");
            l_mainAccountParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setSpMngAccOpenDiv("0");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams1);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_branchParams = new BranchParams();

            l_branchParams.setBranchId(33381L);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchName("東京支店");
            l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams.setBranchType(BranchTypeEnum.WEB_BRANCH);
            l_branchParams.setMaxHandlingPriceInd(150000000);
            l_branchParams.setMaxHandlingPriceCorp(600000000);
            l_branchParams.setMaxHandlingPriceIndOption(600000000);
            l_branchParams.setMaxHandlingPriceCorpOption(600000000);
            l_branchParams.setMaxHandlingPriceIndFuture(600000000);
            l_branchParams.setMaxHandlingPriceCorpFuture(600000000);
            l_branchParams.setMaxContPriceAllInd(600000000.000000);
            l_branchParams.setMaxContPriceAllCorp(150000000.000000);
            l_branchParams.setMaxContPriceProductInd(600000000.000000);
            l_branchParams.setMaxContPriceProductCorp(150000000.000000);
            l_branchParams.setMaxContPrice1dayInd(600000000.000000D);
            l_branchParams.setMaxContPrice1dayCorp(1.000000D);
            l_branchParams.setHandlingMarketMake(0);
            l_branchParams.setHandlingNotLoanTransStock(0);
            l_branchParams.setEmailAddress("info@@naitou-sec.co.jp");
            l_branchParams.setLoginStopDiv("1");
            l_branchParams.setAccountCodeMin(6);
            l_branchParams.setAccountCodeMax(20);
            l_branchParams.setAccountCodeCheckMode("2");
            l_branchParams.setInsiderDefaultRegistDiv("0");
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            l_branchParams.setFstkDiv("1");
            l_branchParams.setMstkDiv("1");
            l_branchParams.setOptionDiv("1");
            l_branchParams.setFutureDiv("1");
            l_branchParams.setMfDiv("1");
            l_branchParams.setRuitoDiv("0");
            l_branchParams.setQualifiedInvestorConfirmDiv("0");
            l_branchParams.setMarginDepositRate(0.000000D);
            l_branchParams.setCashMarginDepositRate(0.000000D);
            l_branchParams.setMarginDepositRate(0.000000D);
            l_branchParams.setMinMarginDeposit(0.000000D);
            l_branchParams.setMinIfoDeposit(0.000000D);
            l_branchParams.setCalcSubstituteRate(0.000000D);
            l_branchParams.setMarginSecCheckRate(0.000000D);
            l_branchParams.setShortMarginRestrainDiv("0");
            l_branchParams.setShortMarginRestrainUnit(0.000000D);
            l_branchParams.setShortSellOrderValidMinute(0);
            l_branchParams.setMarginSecTransferMaxCount(5);
            l_branchParams.setCloseWorngEquityMargin(10);
            l_branchParams.setCloseWorngOption(5);
            l_branchParams.setCloseWorngFeq(0);
            l_branchParams.setLastUpdater("administrator");
            l_branchParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams.setMaxHandlingPriceCloseDiv("1");
            l_branchParams.setOffFloorDiv("1");
            l_branchParams.setCloseWorngFeq(5);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            BranchParams l_branchParams1 = new BranchParams();

            l_branchParams1.setBranchId(333L);
            l_branchParams1.setInstitutionCode("44");
            l_branchParams1.setInstitutionId(32);
            l_branchParams1.setBranchCode("382");
            l_branchParams1.setBranchName("東京支店");
            l_branchParams1.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams1.setBranchType(BranchTypeEnum.WEB_BRANCH);
            l_branchParams1.setMaxHandlingPriceInd(150000000);
            l_branchParams1.setMaxHandlingPriceCorp(600000000);
            l_branchParams1.setMaxHandlingPriceIndOption(600000000);
            l_branchParams1.setMaxHandlingPriceCorpOption(600000000);
            l_branchParams1.setMaxHandlingPriceIndFuture(600000000);
            l_branchParams1.setMaxHandlingPriceCorpFuture(600000000);
            l_branchParams1.setMaxContPriceAllInd(600000000.000000);
            l_branchParams1.setMaxContPriceAllCorp(150000000.000000);
            l_branchParams1.setMaxContPriceProductInd(600000000.000000);
            l_branchParams1.setMaxContPriceProductCorp(150000000.000000);
            l_branchParams1.setMaxContPrice1dayInd(600000000.000000D);
            l_branchParams1.setMaxContPrice1dayCorp(1.000000D);
            l_branchParams1.setHandlingMarketMake(0);
            l_branchParams1.setHandlingNotLoanTransStock(0);
            l_branchParams1.setEmailAddress("nfo@@naitou-sec.co.jp");
            l_branchParams1.setLoginStopDiv("1");
            l_branchParams1.setAccountCodeMin(6);
            l_branchParams1.setAccountCodeMax(20);
            l_branchParams1.setAccountCodeCheckMode("2");
            l_branchParams1.setInsiderDefaultRegistDiv("0");
            l_branchParams1.setMarginSysDiv("1");
            l_branchParams1.setMarginGenDiv("1");
            l_branchParams1.setFstkDiv("1");
            l_branchParams1.setMstkDiv("1");
            l_branchParams1.setOptionDiv("1");
            l_branchParams1.setFutureDiv("1");
            l_branchParams1.setMfDiv("1");
            l_branchParams1.setRuitoDiv("0");
            l_branchParams1.setQualifiedInvestorConfirmDiv("0");
            l_branchParams1.setMarginDepositRate(0.000000D);
            l_branchParams1.setCashMarginDepositRate(0.000000D);
            l_branchParams1.setMarginDepositRate(0.000000D);
            l_branchParams1.setMinMarginDeposit(0.000000D);
            l_branchParams1.setMinIfoDeposit(0.000000D);
            l_branchParams1.setCalcSubstituteRate(0.000000D);
            l_branchParams1.setMarginSecCheckRate(0.000000D);
            l_branchParams1.setShortMarginRestrainDiv("0");
            l_branchParams1.setShortMarginRestrainUnit(0.000000D);
            l_branchParams1.setShortSellOrderValidMinute(0);
            l_branchParams1.setMarginSecTransferMaxCount(5);
            l_branchParams1.setCloseWorngEquityMargin(10);
            l_branchParams1.setCloseWorngOption(5);
            l_branchParams1.setCloseWorngFeq(0);
            l_branchParams1.setLastUpdater("administrator");
            l_branchParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams1.setMaxHandlingPriceCloseDiv("1");
            l_branchParams1.setOffFloorDiv("1");
            l_branchParams1.setCloseWorngFeq(5);
            TestDBUtility.insertWithDelAndCommit(l_branchParams1);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "1";
            ｌ_info.productType = "1";
            ｌ_info.submitOrderRouteDiv = "5";
            ｌ_info.marketCode = "10";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateEqtypeOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            
            WEB3MockObjectParamsValue l_ob = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount", 
                new Class[] { String.class,String.class,String.class });
           
            assertEquals("55", l_ob.getCalled(2)[0].toString());
            assertEquals("382", l_ob.getCalled(2)[1].toString());
            assertEquals("2512247", l_ob.getCalled(2)[2].toString());
            
            assertEquals("55", l_ob.getCalled(3)[0].toString());
            assertEquals("381", l_ob.getCalled(3)[1].toString());
            assertEquals("2512246", l_ob.getCalled(3)[2].toString());
            
            List l_eqtypeOrderUnitList = new ArrayList();       
            
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" submit_order_route_div = ? ");
            
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                "5"         
            };
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            l_eqtypeOrderUnitList = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
            
            assertEquals("5", ((EqtypeOrderUnitRow)l_eqtypeOrderUnitList.get(0)).getSubmitOrderRouteDiv());
            assertEquals("5", ((EqtypeOrderUnitRow)l_eqtypeOrderUnitList.get(1)).getSubmitOrderRouteDiv());
            
            
        } 
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex.getTargetException());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateIfoOrder_0001()
    {
        String STR_METHOD_NAME = "test_updateIfoOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try 
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("55");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("0");
            //
            l_hostFotypeOrderAllParams.setAccountId(333812512203L);
            l_hostFotypeOrderAllParams.setOrderRequestNumber("00");
            l_hostFotypeOrderAllParams.setBranchCode("382");
            l_hostFotypeOrderAllParams.setAccountCode("251224");
           
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams1.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams1.setInstitutionCode("55");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("0");
            //
            l_hostFotypeOrderAllParams1.setAccountId(33381251220L);
            l_hostFotypeOrderAllParams1.setOrderRequestNumber("00");
            l_hostFotypeOrderAllParams1.setBranchCode("381");
            l_hostFotypeOrderAllParams1.setAccountCode("2512246");
           
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "0";
            ｌ_info.productType = "6";
            ｌ_info.submitOrderRouteDiv = "4";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateIfoOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            
            WEB3MockObjectParamsValue l_ob = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount", 
                new Class[] { String.class,String.class,String.class });
           
            assertEquals("55", l_ob.getCalled(0)[0].toString());
            assertEquals("381", l_ob.getCalled(0)[1].toString());
            assertEquals("2512246", l_ob.getCalled(0)[2].toString());
            
            assertEquals("55", l_ob.getCalled(1)[0].toString());
            assertEquals("382", l_ob.getCalled(1)[1].toString());
            assertEquals("251224", l_ob.getCalled(1)[2].toString());
            
            List l_hostFotypeOrderAllList = new ArrayList();       
            
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");
            
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                "55"         
            };
            
            // 口座ID昇順指定
            String l_strSort = "account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            l_hostFotypeOrderAllList = qp.doFindAllQuery(HostFotypeOrderAllRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
            
            assertEquals("9", ((HostFotypeOrderAllRow)l_hostFotypeOrderAllList.get(0)).getSubmitOrderRouteDiv());
            assertEquals("9", ((HostFotypeOrderAllRow)l_hostFotypeOrderAllList.get(1)).getSubmitOrderRouteDiv());
        }        
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex.getTargetException());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateIfoOrder_0002()
    {
        String STR_METHOD_NAME = "test_updateIfoOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try 
        {
            TestDBUtility.deleteAllAndCommit(OrderSwitchingRow.TYPE);
            
            OrderSwitchingParams l_orderSwitchingParams = new OrderSwitchingParams();
            //1   証券会社コード    institution_code    VARCHAR2   3   NotNull
            l_orderSwitchingParams.setInstitutionCode("55");
            l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
            //3   市場コード    market_code    VARCHAR2   2   NotNull
            l_orderSwitchingParams.setMarketCode(WEB3MarketCodeDef.HONGKONG);
            //4   市場コード（SONAR）    sonar_market_code    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setSonarMarketCode("G");
            //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_MAIN_FACTION);
            //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setValidFlag(WEB3ValidFlag.ON);
            //7   訂正取消可能フラグ    change_cancel_enable_flag    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.DISABLE);
            //8   作成日付    created_timestamp    DATE      NotNull
            l_orderSwitchingParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //9   更新日付    last_updated_timestamp    DATE      NotNull
            l_orderSwitchingParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
            //11  MQトリガ発行    submit_mq_trigger    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.NOT_TRIGGER);
            //12  発注エンジン区分    order_engine_div    VARCHAR2   1   NotNull
            l_orderSwitchingParams.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
            
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams);
            
            OrderSwitchingParams l_orderSwitchingParams1 = new OrderSwitchingParams();
            //1   証券会社コード    institution_code    VARCHAR2   3   NotNull
            l_orderSwitchingParams1.setInstitutionCode("55");
            l_orderSwitchingParams1.setProductType(ProductTypeEnum.IFO);
            //3   市場コード    market_code    VARCHAR2   2   NotNull
            l_orderSwitchingParams1.setMarketCode(WEB3MarketCodeDef.HONGKONG);
            //4   市場コード（SONAR）    sonar_market_code    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setSonarMarketCode("f");
            //5   発注経路区分    submit_order_route_div    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setSubmitOrderRouteDiv(WEB3SubmitOrderRouteDivDef.SONAR_SUB_FACTION);
            //6   有効フラグ    valid_flag    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setValidFlag(WEB3ValidFlag.ON);
            //7   訂正取消可能フラグ    change_cancel_enable_flag    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setChangeCancelEnableFlag(WEB3ChangeCancelEnableFlag.DISABLE);
            //8   作成日付    created_timestamp    DATE      NotNull
            l_orderSwitchingParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            //9   更新日付    last_updated_timestamp    DATE      NotNull
            l_orderSwitchingParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //10  フロント発注システム区分    front_order_system_code    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setFrontOrderSystemCode(WEB3FrontOrderSystemCodeDef.OSAKA_EXCHANGE_STOCK);
            //11  MQトリガ発行    submit_mq_trigger    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setSubmitMqTrigger(WEB3SubmitMqTriggerDef.NOT_TRIGGER);
            //12  発注エンジン区分    order_engine_div    VARCHAR2   1   NotNull
            l_orderSwitchingParams1.setOrderEngineDiv(WEB3OrderEngineDivDef.SONAR);
            
            TestDBUtility.insertWithDelAndCommit(l_orderSwitchingParams1);
            
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("55");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("0");
            //
            l_hostFotypeOrderAllParams.setAccountId(333812512203L);
            l_hostFotypeOrderAllParams.setOrderRequestNumber("00");
            l_hostFotypeOrderAllParams.setBranchCode("382");
            l_hostFotypeOrderAllParams.setAccountCode("251224");
           
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams1.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams1.setInstitutionCode("55");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("0");
            //
            l_hostFotypeOrderAllParams1.setAccountId(33381251220L);
            l_hostFotypeOrderAllParams1.setOrderRequestNumber("00");
            l_hostFotypeOrderAllParams1.setBranchCode("381");
            l_hostFotypeOrderAllParams1.setAccountCode("2512246");
           
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "0";
            ｌ_info.productType = "6";
            ｌ_info.submitOrderRouteDiv = "4";
            ｌ_info.marketCode = "N1";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateIfoOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            fail();
        }        
        catch (InvocationTargetException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02187, ((WEB3SystemLayerException)(l_ex.getTargetException())).getErrorInfo());
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void test_updateIfoOrder_0003()
    {
        String STR_METHOD_NAME = "test_updateIfoOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try 
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams.setInstitutionCode("55");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams.setStatus("0");
            //
            l_hostFotypeOrderAllParams.setAccountId(333812512203L);
            l_hostFotypeOrderAllParams.setOrderRequestNumber("00");
            l_hostFotypeOrderAllParams.setBranchCode("382");
            l_hostFotypeOrderAllParams.setAccountCode("251224");
           
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
            //取消区分
            l_hostFotypeOrderAllParams1.setCancelDiv("0");
            //社内処理項目
            l_hostFotypeOrderAllParams1.setCorpCode("123244444");
            //全訂正処理区分
            l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
            //仮想サーバNo.（JSOES）
            l_hostFotypeOrderAllParams1.setVirtualServerNumberJsoes("654321");
            //証券会社コード
            l_hostFotypeOrderAllParams1.setInstitutionCode("55");
            //フロント発注取引所区分コード
            l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
            //フロント発注システム区分
            l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
            //フロント発注取引区分コード
            l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
            //発注経路区分
            l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
            //処理区分
            l_hostFotypeOrderAllParams1.setStatus("0");
            //
            l_hostFotypeOrderAllParams1.setAccountId(33381251220L);
            l_hostFotypeOrderAllParams1.setOrderRequestNumber("00");
            l_hostFotypeOrderAllParams1.setBranchCode("381");
            l_hostFotypeOrderAllParams1.setAccountCode("2512246");
           
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams1);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            
            InstitutionParams l_institutionParams = new InstitutionParams();

            l_institutionParams.setInstitutionCode("55");
            l_institutionParams.setInstitutionId(33);
            
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MarketParams l_marketParams = new MarketParams();

            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("55");
            l_marketParams.setMarketCode("10");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
            
            l_ifoOrderUnitParams.setOrderUnitId(1);
            l_ifoOrderUnitParams.setAccountId(33381251220L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(333);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setLimitPrice(0);
            l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams.setOrderConditionType("0");
            l_ifoOrderUnitParams.setOrderCondOperator(null);
            l_ifoOrderUnitParams.setStopPriceType(null);
            l_ifoOrderUnitParams.setStopOrderPrice(null);
            l_ifoOrderUnitParams.setWLimitPrice(null);
            l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
            l_ifoOrderUnitParams.setConfirmedOrderRev("2");
            l_ifoOrderUnitParams.setOrderRev("1");
            
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams1 = new IfoOrderUnitParams();
            
            l_ifoOrderUnitParams1.setOrderUnitId(1001);
            l_ifoOrderUnitParams1.setAccountId(333812512203L);
            l_ifoOrderUnitParams1.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams1.setBranchId(33381);
            l_ifoOrderUnitParams1.setTraderId(null);
            l_ifoOrderUnitParams1.setOrderId(1001);
            l_ifoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams1.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams1.setLastOrderActionSerialNo(1);
            l_ifoOrderUnitParams1.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams1.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams1.setFutureOptionDiv("1");
            l_ifoOrderUnitParams1.setMarketId(3303L);
            l_ifoOrderUnitParams1.setQuantity(100);
            l_ifoOrderUnitParams1.setLimitPrice(0);
            l_ifoOrderUnitParams1.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
            l_ifoOrderUnitParams1.setOrderConditionType("0");
            l_ifoOrderUnitParams1.setOrderCondOperator(null);
            l_ifoOrderUnitParams1.setStopPriceType(null);
            l_ifoOrderUnitParams1.setStopOrderPrice(null);
            l_ifoOrderUnitParams1.setWLimitPrice(null);
            l_ifoOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams1.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_ifoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_ifoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_ifoOrderUnitParams1.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoOrderUnitParams1.setBizDate("20040101");
            l_ifoOrderUnitParams1.setProductId(1006169090018L);
            l_ifoOrderUnitParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
            l_ifoOrderUnitParams1.setOrderRequestNumber("000003006");
            l_ifoOrderUnitParams1.setConfirmedOrderRev("2");
            l_ifoOrderUnitParams1.setOrderRev("1");
            
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams1);
            
            MainAccountParams l_mainAccountParams = new MainAccountParams();

            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("内藤　@四郎");
            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            MainAccountParams l_mainAccountParams1 = new MainAccountParams();
            l_mainAccountParams1.setAccountId(33381251220L);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setInstitutionId(32L);
            l_mainAccountParams1.setAccountCode("2512247");
            l_mainAccountParams1.setBranchId(333L);
            l_mainAccountParams1.setBranchCode("382");
            l_mainAccountParams1.setSonarTraderCode("11124");
            l_mainAccountParams1.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams1.setFamilyName("内藤　@四郎");
            l_mainAccountParams1.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams1.setGivenNameAlt1("Siro");
            l_mainAccountParams1.setZipCode("1001238");
            l_mainAccountParams1.setSubZipCode("1001238");
            l_mainAccountParams1.setAddressLine1("東京都");
            l_mainAccountParams1.setAddressLine2("江東区");
            l_mainAccountParams1.setAddressLine3("深川５");
            l_mainAccountParams1.setTelephone("38201115");
            l_mainAccountParams1.setMobile("901115");
            l_mainAccountParams1.setFax("38202226");
            l_mainAccountParams1.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setResident("0");
            l_mainAccountParams1.setNewAccountDiv("0");
            l_mainAccountParams1.setViaTrustBankDiv("0");
            l_mainAccountParams1.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams1.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams1.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams1.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams1.setPersonIdentify("1");
            l_mainAccountParams1.setEraBorn("3");
            l_mainAccountParams1.setBornDate("101013");
            l_mainAccountParams1.setSex("1");
            l_mainAccountParams1.setYellowCustomer("0");
            l_mainAccountParams1.setHtSettlementWay("0");
            l_mainAccountParams1.setBankAccountRegi("0");
            l_mainAccountParams1.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams1.setExaminLockFlag("0");
            l_mainAccountParams1.setMngLockFlag("0");
            l_mainAccountParams1.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams1.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams1.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams1.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams1.setBranchLock("0");
            l_mainAccountParams1.setOrderPermission("0");
            l_mainAccountParams1.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams1.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams1.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams1.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams1.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams1.setMarginSysAccOpenDiv("0");
            l_mainAccountParams1.setMarginGenAccOpenDiv("0");
            l_mainAccountParams1.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams1.setForeignSecAccOpenDiv("1");
            l_mainAccountParams1.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams1.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams1.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams1.setRuitoAccOpenDiv("0");
            l_mainAccountParams1.setMrfAccOpenDiv("0");
            l_mainAccountParams1.setFxAccOpenDiv("0");
            l_mainAccountParams1.setFeqConAccOpenDiv("0");
            l_mainAccountParams1.setTopPageId("0");
            l_mainAccountParams1.setQuotoType("0");
            l_mainAccountParams1.setTradingReportDiv("1");
            l_mainAccountParams1.setPositionReportDiv("9");
            l_mainAccountParams1.setPositionReportCycleDiv("1");
            l_mainAccountParams1.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams1.setEmailLastUpdater("2512246");
            l_mainAccountParams1.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setTradingPasswordUpdater("2512246");
            l_mainAccountParams1.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setMbOffLastUpdater("2512246");
            l_mainAccountParams1.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams1.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams1.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams1.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setMrfFundCode("1");
            l_mainAccountParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams1.setSpMngAccOpenDiv("0");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams1);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_branchParams = new BranchParams();

            l_branchParams.setBranchId(33381L);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchName("東京支店");
            l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams.setBranchType(BranchTypeEnum.WEB_BRANCH);
            l_branchParams.setMaxHandlingPriceInd(150000000);
            l_branchParams.setMaxHandlingPriceCorp(600000000);
            l_branchParams.setMaxHandlingPriceIndOption(600000000);
            l_branchParams.setMaxHandlingPriceCorpOption(600000000);
            l_branchParams.setMaxHandlingPriceIndFuture(600000000);
            l_branchParams.setMaxHandlingPriceCorpFuture(600000000);
            l_branchParams.setMaxContPriceAllInd(600000000.000000);
            l_branchParams.setMaxContPriceAllCorp(150000000.000000);
            l_branchParams.setMaxContPriceProductInd(600000000.000000);
            l_branchParams.setMaxContPriceProductCorp(150000000.000000);
            l_branchParams.setMaxContPrice1dayInd(600000000.000000D);
            l_branchParams.setMaxContPrice1dayCorp(1.000000D);
            l_branchParams.setHandlingMarketMake(0);
            l_branchParams.setHandlingNotLoanTransStock(0);
            l_branchParams.setEmailAddress("info@@naitou-sec.co.jp");
            l_branchParams.setLoginStopDiv("1");
            l_branchParams.setAccountCodeMin(6);
            l_branchParams.setAccountCodeMax(20);
            l_branchParams.setAccountCodeCheckMode("2");
            l_branchParams.setInsiderDefaultRegistDiv("0");
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            l_branchParams.setFstkDiv("1");
            l_branchParams.setMstkDiv("1");
            l_branchParams.setOptionDiv("1");
            l_branchParams.setFutureDiv("1");
            l_branchParams.setMfDiv("1");
            l_branchParams.setRuitoDiv("0");
            l_branchParams.setQualifiedInvestorConfirmDiv("0");
            l_branchParams.setMarginDepositRate(0.000000D);
            l_branchParams.setCashMarginDepositRate(0.000000D);
            l_branchParams.setMarginDepositRate(0.000000D);
            l_branchParams.setMinMarginDeposit(0.000000D);
            l_branchParams.setMinIfoDeposit(0.000000D);
            l_branchParams.setCalcSubstituteRate(0.000000D);
            l_branchParams.setMarginSecCheckRate(0.000000D);
            l_branchParams.setShortMarginRestrainDiv("0");
            l_branchParams.setShortMarginRestrainUnit(0.000000D);
            l_branchParams.setShortSellOrderValidMinute(0);
            l_branchParams.setMarginSecTransferMaxCount(5);
            l_branchParams.setCloseWorngEquityMargin(10);
            l_branchParams.setCloseWorngOption(5);
            l_branchParams.setCloseWorngFeq(0);
            l_branchParams.setLastUpdater("administrator");
            l_branchParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams.setMaxHandlingPriceCloseDiv("1");
            l_branchParams.setOffFloorDiv("1");
            l_branchParams.setCloseWorngFeq(5);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            BranchParams l_branchParams1 = new BranchParams();

            l_branchParams1.setBranchId(333L);
            l_branchParams1.setInstitutionCode("44");
            l_branchParams1.setInstitutionId(32);
            l_branchParams1.setBranchCode("382");
            l_branchParams1.setBranchName("東京支店");
            l_branchParams1.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams1.setBranchType(BranchTypeEnum.WEB_BRANCH);
            l_branchParams1.setMaxHandlingPriceInd(150000000);
            l_branchParams1.setMaxHandlingPriceCorp(600000000);
            l_branchParams1.setMaxHandlingPriceIndOption(600000000);
            l_branchParams1.setMaxHandlingPriceCorpOption(600000000);
            l_branchParams1.setMaxHandlingPriceIndFuture(600000000);
            l_branchParams1.setMaxHandlingPriceCorpFuture(600000000);
            l_branchParams1.setMaxContPriceAllInd(600000000.000000);
            l_branchParams1.setMaxContPriceAllCorp(150000000.000000);
            l_branchParams1.setMaxContPriceProductInd(600000000.000000);
            l_branchParams1.setMaxContPriceProductCorp(150000000.000000);
            l_branchParams1.setMaxContPrice1dayInd(600000000.000000D);
            l_branchParams1.setMaxContPrice1dayCorp(1.000000D);
            l_branchParams1.setHandlingMarketMake(0);
            l_branchParams1.setHandlingNotLoanTransStock(0);
            l_branchParams1.setEmailAddress("nfo@@naitou-sec.co.jp");
            l_branchParams1.setLoginStopDiv("1");
            l_branchParams1.setAccountCodeMin(6);
            l_branchParams1.setAccountCodeMax(20);
            l_branchParams1.setAccountCodeCheckMode("2");
            l_branchParams1.setInsiderDefaultRegistDiv("0");
            l_branchParams1.setMarginSysDiv("1");
            l_branchParams1.setMarginGenDiv("1");
            l_branchParams1.setFstkDiv("1");
            l_branchParams1.setMstkDiv("1");
            l_branchParams1.setOptionDiv("1");
            l_branchParams1.setFutureDiv("1");
            l_branchParams1.setMfDiv("1");
            l_branchParams1.setRuitoDiv("0");
            l_branchParams1.setQualifiedInvestorConfirmDiv("0");
            l_branchParams1.setMarginDepositRate(0.000000D);
            l_branchParams1.setCashMarginDepositRate(0.000000D);
            l_branchParams1.setMarginDepositRate(0.000000D);
            l_branchParams1.setMinMarginDeposit(0.000000D);
            l_branchParams1.setMinIfoDeposit(0.000000D);
            l_branchParams1.setCalcSubstituteRate(0.000000D);
            l_branchParams1.setMarginSecCheckRate(0.000000D);
            l_branchParams1.setShortMarginRestrainDiv("0");
            l_branchParams1.setShortMarginRestrainUnit(0.000000D);
            l_branchParams1.setShortSellOrderValidMinute(0);
            l_branchParams1.setMarginSecTransferMaxCount(5);
            l_branchParams1.setCloseWorngEquityMargin(10);
            l_branchParams1.setCloseWorngOption(5);
            l_branchParams1.setCloseWorngFeq(0);
            l_branchParams1.setLastUpdater("administrator");
            l_branchParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams1.setMaxHandlingPriceCloseDiv("1");
            l_branchParams1.setOffFloorDiv("1");
            l_branchParams1.setCloseWorngFeq(5);
            TestDBUtility.insertWithDelAndCommit(l_branchParams1);
            
            WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
            ｌ_info.frontOrderSystemCode = "1";
            ｌ_info.changedValidFlag = "1";
            ｌ_info.productType = "6";
            ｌ_info.submitOrderRouteDiv = "4";
            ｌ_info.marketCode = "10";
            
            WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
            WEB3AdminSwitchOrderRouteServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            Method method1 = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod(
                "getExpirationOrderRoute",
                new Class[] {String.class, WEB3AdminOrderRouteSwitchingInfo.class});
            method1.setAccessible(true);
            
            WEB3GentradeOrderSwitching l_gentradeOrderSwitching =
                (WEB3GentradeOrderSwitching)method1.invoke(l_switchOrderRouteServiceImpl,
                    new Object[]{"55",ｌ_info});

            Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("updateIfoOrder",
                new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class,WEB3GentradeOrderSwitching.class});
           
            method.setAccessible(true);
            method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"55","1",ｌ_info,l_gentradeOrderSwitching});
            
            WEB3MockObjectParamsValue l_ob = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount", 
                new Class[] { String.class,String.class,String.class});
           
            assertEquals("55", l_ob.getCalled(2)[0].toString());
            assertEquals("382", l_ob.getCalled(2)[1].toString());
            assertEquals("2512247", l_ob.getCalled(2)[2].toString());
            
            assertEquals("55", l_ob.getCalled(3)[0].toString());
            assertEquals("381", l_ob.getCalled(3)[1].toString());
            assertEquals("2512246", l_ob.getCalled(3)[2].toString());
            
            List l_ifoOrderUnitRowList = new ArrayList();       
            
            // 抽出条件文字列の生成
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" sub_account_id = ? ");
            
            // 抽出条件コンテナの生成
            Object[] l_objWhere =
            {
                "10100101001007"         
            };
            
            // 口座ID昇順指定
            String l_strSort = "sub_account_id asc";

            QueryProcessor qp = Processors.getDefaultProcessor();

            // 株式注文単位テーブルを検索する。 
            l_ifoOrderUnitRowList = qp.doFindAllQuery(IfoOrderUnitRow.TYPE,
                                    l_sbWhere.toString(),
                                    l_strSort,
                                    null,
                                    l_objWhere);
            
            assertEquals("4", ((IfoOrderUnitRow)l_ifoOrderUnitRowList.get(0)).getSubmitOrderRouteDiv());
            assertEquals("4", ((IfoOrderUnitRow)l_ifoOrderUnitRowList.get(1)).getSubmitOrderRouteDiv());
        }        
        catch (InvocationTargetException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex.getTargetException());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex) 
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

     public void testSelectEqtypeOrderUnit_0001()
     {
         String STR_METHOD_NAME = "testSelectEqtypeOrderUnit_0002()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try 
         {            
             TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
             l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
             l_eqtypeOrderUnitParams.setOrderId(123456789L);
             l_eqtypeOrderUnitParams.setAccountId(333812512203L);
             l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
             l_eqtypeOrderUnitParams.setBranchId(333L);
             l_eqtypeOrderUnitParams.setTraderId(3338111123L);
             l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
             l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
             l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
             l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
             l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
             l_eqtypeOrderUnitParams.setQuantity(1200);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
             l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
             l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
             l_eqtypeOrderUnitParams.setBizDate("20070117");
             l_eqtypeOrderUnitParams.setProductId(3304148080001L);
             l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
             l_eqtypeOrderUnitParams.setExecutedQuantity(200);
             l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
             l_eqtypeOrderUnitParams.setMarketId(3303L);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setExecutedAmount(2000);
             l_eqtypeOrderUnitParams.setExecutedQuantity(500);
             l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
             l_eqtypeOrderUnitParams.setSonarTradedCode("51");
             
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             TestDBUtility.deleteAll(MarketRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
             
             InstitutionParams l_institutionParams = new InstitutionParams();

             l_institutionParams.setInstitutionCode("0D");
             l_institutionParams.setInstitutionId(33);
             
             TestDBUtility.insertWithDel(l_institutionParams);
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
             ｌ_info.marketCode = "SP";
             ｌ_info.productType = "1";
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectEqtypeOrderUnit",
                 new Class[] {String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"0D",ｌ_info});
             
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"0D",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         } 
         
     }
     
     public void testSelectEqtypeOrderUnit_0002()
     {
         String STR_METHOD_NAME = "testSelectEqtypeOrderUnit_0003()";
         log.entering(TEST_START + STR_METHOD_NAME);

         try 
         {            
             TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
             l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
             l_eqtypeOrderUnitParams.setOrderId(123456789L);
             l_eqtypeOrderUnitParams.setAccountId(333812512203L);
             l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
             l_eqtypeOrderUnitParams.setBranchId(333L);
             l_eqtypeOrderUnitParams.setTraderId(3338111123L);
             l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
             l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
             l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
             l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
             l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
             l_eqtypeOrderUnitParams.setQuantity(1200);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
             l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
             l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
             l_eqtypeOrderUnitParams.setBizDate("20070117");
             l_eqtypeOrderUnitParams.setProductId(3304148080001L);
             l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
             l_eqtypeOrderUnitParams.setExecutedQuantity(200);
             l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
             l_eqtypeOrderUnitParams.setMarketId(3303L);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setExecutedAmount(2000);
             l_eqtypeOrderUnitParams.setExecutedQuantity(500);
             l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
             l_eqtypeOrderUnitParams.setSonarTradedCode("51");
             
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             TestDBUtility.deleteAll(MarketRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
             
             InstitutionParams l_institutionParams = new InstitutionParams();

             l_institutionParams.setInstitutionCode("0D");
             l_institutionParams.setInstitutionId(33);
             
             TestDBUtility.insertWithDel(l_institutionParams);
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
             ｌ_info.marketCode = "SP";
             ｌ_info.productType = "15";
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectEqtypeOrderUnit",
                 new Class[] {String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"0D",ｌ_info});
             assertEquals("0","" + l_lst.size()); 
          
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         } 
     }
     
     public void testSelectEqtypeOrderUnit_0003()
     {
         String STR_METHOD_NAME = "testSelectEqtypeOrderUnit_0004()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try 
         {            
             TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
             l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
             l_eqtypeOrderUnitParams.setOrderId(123456789L);
             l_eqtypeOrderUnitParams.setAccountId(333812512203L);
             l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
             l_eqtypeOrderUnitParams.setBranchId(333L);
             l_eqtypeOrderUnitParams.setTraderId(3338111123L);
             l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
             l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
             l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
             l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
             l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
             l_eqtypeOrderUnitParams.setQuantity(1200);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
             l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
             l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
             l_eqtypeOrderUnitParams.setBizDate("20070117");
             l_eqtypeOrderUnitParams.setProductId(3304148080001L);
             l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
             l_eqtypeOrderUnitParams.setExecutedQuantity(200);
             l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
             l_eqtypeOrderUnitParams.setMarketId(3303L);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setExecutedAmount(2000);
             l_eqtypeOrderUnitParams.setExecutedQuantity(500);
             l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
             l_eqtypeOrderUnitParams.setSonarTradedCode("51");
             
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             TestDBUtility.deleteAll(MarketRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
             
             InstitutionParams l_institutionParams = new InstitutionParams();

             l_institutionParams.setInstitutionCode("0D");
             l_institutionParams.setInstitutionId(33);
             
             TestDBUtility.insertWithDel(l_institutionParams);
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
             ｌ_info.marketCode = "SP";
             ｌ_info.productType = "1";
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectEqtypeOrderUnit",
                 new Class[] {String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"0D",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
          
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         } 
     }
     
     public void testSelectEqtypeOrderUnit_0004()
     {
         String STR_METHOD_NAME = "testSelectEqtypeOrderUnit_0005()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try 
         {            
             TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
             l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
             l_eqtypeOrderUnitParams.setOrderId(123456789L);
             l_eqtypeOrderUnitParams.setAccountId(333812512203L);
             l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
             l_eqtypeOrderUnitParams.setBranchId(333L);
             l_eqtypeOrderUnitParams.setTraderId(3338111123L);
             l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
             l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
             l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
             l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
             l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
             l_eqtypeOrderUnitParams.setQuantity(1200);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
             l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
             l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
             l_eqtypeOrderUnitParams.setBizDate("20070117");
             l_eqtypeOrderUnitParams.setProductId(3304148080001L);
             l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
             l_eqtypeOrderUnitParams.setExecutedQuantity(200);
             l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
             l_eqtypeOrderUnitParams.setMarketId(33L);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setExecutedAmount(2000);
             l_eqtypeOrderUnitParams.setExecutedQuantity(500);
             l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
             l_eqtypeOrderUnitParams.setSonarTradedCode("51");
             
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             TestDBUtility.deleteAll(MarketRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
             
             InstitutionParams l_institutionParams = new InstitutionParams();

             l_institutionParams.setInstitutionCode("0D");
             l_institutionParams.setInstitutionId(33);
             
             TestDBUtility.insertWithDel(l_institutionParams);
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
             ｌ_info.marketCode = "SP";
             ｌ_info.productType = "1";
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectEqtypeOrderUnit",
                 new Class[] {String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"0D",ｌ_info});
             assertEquals("0","" + l_lst.size()); 
          
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         } 
     }
     
     public void testSelectEqtypeOrderUnit_0005() 
     {
         String STR_METHOD_NAME = "testSelectEqtypeOrderUnit_0006()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try 
         {            
             TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
             
             EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();
             l_eqtypeOrderUnitParams.setOrderUnitId(3304148080001L);
             l_eqtypeOrderUnitParams.setOrderId(123456789L);
             l_eqtypeOrderUnitParams.setAccountId(333812512203L);
             l_eqtypeOrderUnitParams.setSubAccountId(33381251220301L);
             l_eqtypeOrderUnitParams.setBranchId(333L);
             l_eqtypeOrderUnitParams.setTraderId(3338111123L);
             l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
             l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
             l_eqtypeOrderUnitParams.setLastOrderActionSerialNo(0126);
             l_eqtypeOrderUnitParams.setLastExecutionSerialNo(8126);
             l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
             l_eqtypeOrderUnitParams.setQuantity(1200);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setDeliveryDate(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
             l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
             l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);
             l_eqtypeOrderUnitParams.setBizDate("20070117");
             l_eqtypeOrderUnitParams.setProductId(3304148080001L);
             l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
             l_eqtypeOrderUnitParams.setExecutedQuantity(200);
             l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
             l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
             l_eqtypeOrderUnitParams.setMarketId(33L);
             l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.LIMIT_PRICE);
             l_eqtypeOrderUnitParams.setExecutedAmount(2000);
             l_eqtypeOrderUnitParams.setExecutedQuantity(500);
             l_eqtypeOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
             l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
             l_eqtypeOrderUnitParams.setSonarTradedCode("51");
             
             TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             TestDBUtility.deleteAll(MarketRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
             
             InstitutionParams l_institutionParams = new InstitutionParams();

             l_institutionParams.setInstitutionCode("4");
             l_institutionParams.setInstitutionId(31);
             
             TestDBUtility.insertWithDel(l_institutionParams);
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();
             ｌ_info.marketCode = "SP";
             ｌ_info.productType = "1";
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             l_switchOrderRouteServiceImpl.selectEqtypeOrderUnit("0D",ｌ_info);
             fail();
         } 
         catch (WEB3BaseException l_ex) 
         {
             log.debug(STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00645, l_ex.getErrorInfo());
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0001() 
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0001()";
         log.entering(TEST_START + STR_METHOD_NAME);

         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("554");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
             assertEquals("555","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getInstitutionCode()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0002() 
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0002()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("2");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
             assertEquals("1","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getFrontOrderExchangeCode()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0003()
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0003()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("2");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
             assertEquals("1","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getFrontOrderSystemCode()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0004() 
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0004()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("2");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
             assertEquals("1","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getFrontOrderTradeCode()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0005() 
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0005()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("1");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
             assertEquals("0","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getAllOrderChangeDiv()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0006() 
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0006()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("1");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("1","" + l_lst.size()); 
             assertEquals("0","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getStatus()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
     
     public void testSelectIfoOrderAll_0007() 
     {
         String STR_METHOD_NAME = "testSelectIfoOrderAll_0007()";
         log.entering(TEST_START + STR_METHOD_NAME);
         try
         {
             TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
             l_hostFotypeOrderAllParams.setAccountId(12);
             //証券会社コード
             l_hostFotypeOrderAllParams.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams.setCorpCode("123244444");
             //全訂正処理区分
             l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
             
             HostFotypeOrderAllParams l_hostFotypeOrderAllParams1 = new HostFotypeOrderAllParams();
             l_hostFotypeOrderAllParams1.setAccountId(13);
             //証券会社コード
             l_hostFotypeOrderAllParams1.setInstitutionCode("555");
             //フロント発注取引所区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderExchangeCode("1");
             //フロント発注システム区分
             l_hostFotypeOrderAllParams1.setFrontOrderSystemCode("1");
             //フロント発注取引区分コード
             l_hostFotypeOrderAllParams1.setFrontOrderTradeCode("1");
             //取消区分
             l_hostFotypeOrderAllParams1.setCancelDiv("0");
             //社内処理項目
             l_hostFotypeOrderAllParams1.setCorpCode("1232");
             //全訂正処理区分
             l_hostFotypeOrderAllParams1.setAllOrderChangeDiv("0");
             //発注経路区分
             l_hostFotypeOrderAllParams1.setSubmitOrderRouteDiv("2");
             //処理区分
             l_hostFotypeOrderAllParams1.setStatus("0");
             
             TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams1);
             
             WEB3AdminSwitchOrderRouteServiceImpl l_switchOrderRouteServiceImpl = new 
             WEB3AdminSwitchOrderRouteServiceImpl();
             
             WEB3AdminOrderRouteSwitchingInfo ｌ_info = new WEB3AdminOrderRouteSwitchingInfo();

             ｌ_info.frontOrderSystemCode = "1";
             
             Method method = WEB3AdminSwitchOrderRouteServiceImpl.class.getDeclaredMethod("selectIfoOrderAll",
                 new Class[] {String.class,String.class,WEB3AdminOrderRouteSwitchingInfo.class});
            
             method.setAccessible(true);
             List l_lst = (List) method.invoke(l_switchOrderRouteServiceImpl,new Object[]{"555","1",ｌ_info});
             assertEquals("2","" + l_lst.size()); 
             assertEquals("123244444","" + ((HostFotypeOrderAllRow)l_lst.get(0)).getCorpCode()); 
             assertEquals("1232","" + ((HostFotypeOrderAllRow)l_lst.get(1)).getCorpCode()); 
             
         } 
         catch (Exception l_ex) 
         {
             log.error(TEST_END + STR_METHOD_NAME , l_ex);
             log.exiting(STR_METHOD_NAME);
             fail();
         }
     }
    //-------->myn end
    
    private HostEqtypeOrderAllParams getHostEqtypeOrderAllRow()
    {
        HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams = new HostEqtypeOrderAllParams();
        //証券会社コード 
        l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
        
        //フロント発注取引所区分コード
        l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode("1");
        
        //フロント発注システム区分
        l_hostEqtypeOrderAllParams.setFrontOrderSystemCode("1");
        //フロント発注取引区分コード 
        l_hostEqtypeOrderAllParams.setFrontOrderTradeCode("1");
        //全訂正処理区分
        l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
        //取引コード（SONAR）
        l_hostEqtypeOrderAllParams.setSonarTradedCode("11");
        
        //識別コード
        l_hostEqtypeOrderAllParams.setOrderRequestNumber("123456789");
        //注文履歴番号
        l_hostEqtypeOrderAllParams.setOrderActionSerialNo(12345678);
        
        //発注経路区分
        l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv("1");
        //処理区分（ステータス）
        l_hostEqtypeOrderAllParams.setStatus("0");
        
        //社内処理項目
        l_hostEqtypeOrderAllParams.setCorpCode("12345");
        
        //全訂正処理区分
        l_hostEqtypeOrderAllParams.setAllOrderChangeDiv("0");
        
        //取消区分
        l_hostEqtypeOrderAllParams.setCancelDiv("1");
        return l_hostEqtypeOrderAllParams;
    }
    
    public class WEB3AdminSwitchOrderRouteServiceImplForTest extends WEB3AdminSwitchOrderRouteServiceImpl
    {
        protected void updateOrderSwitching(
            String institutioncode,
            WEB3AdminOrderRouteSwitchingInfo infolist)
            throws WEB3BaseException
        {
            
        }
        
        protected void updateEqtypeOrderAll(
            HostEqtypeOrderAllRow l_hostEqtypeOrderAllRow,
            WEB3AdminOrderRouteSwitchingInfo infoUnit)
            throws WEB3BaseException, DataNetworkException, DataQueryException, DataCallbackException
        {
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface = new StringBuffer();
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append("updateEqtypeOrder(String, String, WEB3AdminOrderRouteSwitchingInfo)");
        }
    }
    
    public class WEB3AdminSwitchOrderRouteCompleteRequestForTest extends WEB3AdminSwitchOrderRouteCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            
        }
        
        public WEB3GenResponse createResponse()
        {
            return null;
        }
    }
    
    public class WEB3AdminDirSecFrontOrderCommonServiceImplForTest extends WEB3AdminDirSecFrontOrderCommonServiceImpl
    {
        public void validateSonarCheck(String l_institutionCode, String l_frontExCode, 
            String l_frontSystemCode, String l_strProductType) throws WEB3BusinessLayerException, WEB3SystemLayerException 
        {
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface = new StringBuffer();
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" 証券会社コード = " + l_institutionCode);
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" フロント発注取引所区分コード = " + l_frontExCode);
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" フロント発注システム区分 = " + l_frontSystemCode);
            WEB3AdminSwitchOrderRouteServiceImplTest1.l_sbfInterface.append(" 銘柄タイプ = " + l_strProductType);
        }
        
        public String getFrontOrderExchangeCode(String l_strConvertMarketCode) 
        {
            return "1";
        }
        
        public void getVitualServerCount(String l_institutionCode, String l_marketCode, String l_frontSystemCode,String l_strProductType, WEB3AdminFrontProcessNumberInfoUnit l_processInfoUnit) throws WEB3SystemLayerException 
        {
            
        }
    }
    
    public class WEB3AdminSwitchOrderRouteConfirmRequestForTest extends WEB3AdminSwitchOrderRouteConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            
        }
        
        public WEB3GenResponse createResponse()
        {
            return null;
        }
    }

}
@
