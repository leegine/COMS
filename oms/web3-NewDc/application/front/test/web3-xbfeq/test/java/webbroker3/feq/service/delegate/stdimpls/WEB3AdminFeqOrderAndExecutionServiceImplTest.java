head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAndExecutionServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAndExecutionInputRequest;
import webbroker3.feq.message.WEB3FeqExecDetailInfoUnit;
import webbroker3.feq.message.WEB3FeqOrderAndExecutionUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

public class WEB3AdminFeqOrderAndExecutionServiceImplTest extends TestBaseForMock
{

    /**
     * <p>（ログ出力ユーティリティ）。</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFeqOrderAndExecutionServiceImplTest.class);

    WEB3AdminFeqOrderAndExecutionServiceImpl l_impl =
        new WEB3AdminFeqOrderAndExecutionServiceImpl();
    
    public WEB3AdminFeqOrderAndExecutionServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        deleteAllDB();
        insertAllDB();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        deleteAllDB();
    }

    public void testValidateExec_T01()
    {
        final String STR_METHOD_NAME="testValidateExec_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution("0D");
            WEB3FeqOrderAndExecutionUnit l_execInputInfo = 
                new WEB3FeqOrderAndExecutionUnit();
            l_execInputInfo.branchCode = "381";
            l_execInputInfo.accountCode = "2512246";
            l_execInputInfo.traderCode = null;
            WEB3FeqExecDetailInfoUnit l_detailInfoUnit = new WEB3FeqExecDetailInfoUnit();
            l_detailInfoUnit.execPrice = "1000";
            l_detailInfoUnit.executionTimestamp = null;
            l_detailInfoUnit.execQuantity = "200";
            l_execInputInfo.execDetailInfoUnit = l_detailInfoUnit;
            l_execInputInfo.taxType = null;
            l_execInputInfo.localProductCode = "N8080";
            l_execInputInfo.settleDiv = "0";
            l_execInputInfo.dealingType = "1";
            l_execInputInfo.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_execInputInfo.execExchangeRate = "123c";

            //CurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.not.get.number.flag", Boolean.TRUE);
            
            //private  excuted
            Object[] obj = new Object[]{l_institution, l_execInputInfo};
            Method method = WEB3AdminFeqOrderAndExecutionServiceImpl.class.getDeclaredMethod(
                "validateHostOrderExec",
                new Class[]{WEB3GentradeInstitution.class,
                    WEB3FeqOrderAndExecutionUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
            fail();
        }
        catch(Exception l_ex)
        {
            InvocationTargetException exce = (InvocationTargetException)l_ex;
            log.error(exce.getMessage(), exce);
            WEB3BusinessLayerException syse = (WEB3BusinessLayerException)exce.getTargetException();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02113, syse.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateExec_T02()
    {
        final String STR_METHOD_NAME="testValidateExec_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution("0D");
            WEB3FeqOrderAndExecutionUnit l_execInputInfo = 
                new WEB3FeqOrderAndExecutionUnit();
            l_execInputInfo.branchCode = "381";
            l_execInputInfo.accountCode = "2512246";
            l_execInputInfo.traderCode = null;
            WEB3FeqExecDetailInfoUnit l_detailInfoUnit = new WEB3FeqExecDetailInfoUnit();
            l_detailInfoUnit.executionTimestamp = null;
            l_execInputInfo.execDetailInfoUnit = l_detailInfoUnit;
            l_detailInfoUnit.execPrice = null;
            l_execInputInfo.taxType = "0";
            l_execInputInfo.localProductCode = "N8080";
            l_execInputInfo.settleDiv = "0";
            l_execInputInfo.dealingType = "1";
            l_execInputInfo.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_execInputInfo.execExchangeRate = null;
            l_execInputInfo.currencyCode = "01";
            l_execInputInfo.productCode = null;
            l_execInputInfo.localProductCode = null;
            
            
            
            //private  excuted
            Object[] obj = new Object[]{l_institution, l_execInputInfo};
            Method method = WEB3AdminFeqOrderAndExecutionServiceImpl.class.getDeclaredMethod(
                "validateHostOrderExec",
                new Class[]{WEB3GentradeInstitution.class,
                    WEB3FeqOrderAndExecutionUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
            fail();
        }
        catch(Exception l_ex)
        {
            InvocationTargetException exce = (InvocationTargetException)l_ex;
            log.error(exce.getMessage(), exce);
            WEB3BusinessLayerException syse = (WEB3BusinessLayerException)exce.getTargetException();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02148, syse.getErrorInfo());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateExec_T03()
    {
        final String STR_METHOD_NAME="testValidateExec_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            WEB3GentradeInstitution l_institution =
                new WEB3GentradeInstitution("0D");
            WEB3FeqOrderAndExecutionUnit l_execInputInfo = 
                new WEB3FeqOrderAndExecutionUnit();
            l_execInputInfo.branchCode = "381";
            l_execInputInfo.accountCode = "2512246";
            l_execInputInfo.traderCode = null;
            //WEB3FeqExecDetailInfoUnit l_detailInfoUnit = new WEB3FeqExecDetailInfoUnit();
            //l_detailInfoUnit.executionTimestamp = null;
            //l_detailInfoUnit.execPrice = "7";
            //l_execInputInfo.execDetailInfoUnit = l_detailInfoUnit;
            l_execInputInfo.taxType = "0";
            l_execInputInfo.localProductCode = "N8080";
            l_execInputInfo.settleDiv = "0";
            l_execInputInfo.dealingType = "2";
            l_execInputInfo.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            l_execInputInfo.execExchangeRate = null;
            l_execInputInfo.currencyCode = "01";
            l_execInputInfo.execDetailInfoUnit = null;
            
            
            //private  excuted
            Object[] obj = new Object[]{l_institution, l_execInputInfo};
            Method method = WEB3AdminFeqOrderAndExecutionServiceImpl.class.getDeclaredMethod(
                "validateHostOrderExec",
                new Class[]{WEB3GentradeInstitution.class,
                    WEB3FeqOrderAndExecutionUnit.class});
            method.setAccessible(true);
            method.invoke(l_impl, obj);
            fail();
        }
        catch(Exception l_ex)
        {
            InvocationTargetException exce = (InvocationTargetException)l_ex;
            log.error(exce.getMessage(), exce);
            WEB3SystemLayerException syse = (WEB3SystemLayerException)exce.getTargetException();
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, syse.getErrorInfo());
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_getInputScreen_c0001()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_c0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminFeqOrderAndExecutionInputRequest l_request = 
            new WEB3AdminFeqOrderAndExecutionInputRequest();
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NW");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            //admin
            AdministratorParams l_adminParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_adminParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            
            //AdminPermission
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE);
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            //FeqBranchMarketDealtCond 1
            FeqBranchMarketDealtCondParams l_params1 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_params1);
            
            //market 1
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //FeqBranchMarketDealtCond 2
            FeqBranchMarketDealtCondParams l_params2 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params2.setBranchCode("382");
            l_params2.setMarketCode("PS");           
            TestDBUtility.insertWithDel(l_params2);
            
            //market 2
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketId(3304L);
            l_marketParams2.setMarketCode("PS");
            TestDBUtility.insertWithDel(l_marketParams2);
            
            //FeqBranchMarketDealtCond 3
            FeqBranchMarketDealtCondParams l_params3 = TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_params3.setBranchCode("383");
            l_params3.setMarketCode("PP");           
            TestDBUtility.insertWithDel(l_params3);
            
            //MarketPreferences
            MarketPreferencesParams l_MPParams = TestDBUtility.getMarketPreferencesRow();
            TestDBUtility.insertWithDel(l_MPParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_request.managementCode = "12345";
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
            l_feqOrderUnitParams.setOrderEmpCode("NW12345");
            l_feqOrderUnitParams.setInstitutionCode("0D");
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setConfirmedQuantity(120);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("1");
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setConfirmedPrice(0);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02143, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    public void insertAllDB()
    {
        final String STR_METHOD_NAME="insertAllDB()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //FeqProductParams
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setCurrencyCode("01");
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            //CurrencyParams
            GenCurrencyParams l_currencyParams = TestDBUtility.getGenCurrencyRow();
            TestDBUtility.insertWithDel(l_currencyParams);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void deleteAllDB()
    {
        final String STR_METHOD_NAME="deleteAllDB()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            
            //CurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
           
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
