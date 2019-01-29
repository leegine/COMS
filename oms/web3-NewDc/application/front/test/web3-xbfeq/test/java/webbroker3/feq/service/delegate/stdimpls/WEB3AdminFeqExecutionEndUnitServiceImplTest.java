head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExecutionEndUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminFeqExecutionEndUnitServiceImplTest extends TestBaseForMock
{
        /**
         * ログ出力ユーティリティ。
         */    
         private static WEB3LogUtility log =
             WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndUnitServiceImplTest.class);
         
    public WEB3AdminFeqExecutionEndUnitServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionEndUnitServiceImpl.execExecEnd(WEB3GentradeMainAccount, String, Date)'
     */
    //（is日計り取引採用() == false　@or　@is日計り市場() == false）　@且つ 買付注文（is買付() == true)
    //is日計り取引採用() == false
    public void testExecExecEndCase1()
    {
        final String STR_METHOD_NAME = "testExecExecEndCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.netting.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(333812512246L);
            
            
            WEB3AdminFeqExecutionEndUnitServiceImpl l_impl = new WEB3AdminFeqExecutionEndUnitServiceImpl();
            l_impl.execExecEnd(l_account,  "SP", null);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //（is日計り取引採用() == false　@or　@is日計り市場() == false）　@且つ 買付注文（is買付() == true)
    //is日計り市場() == false
    public void testExecExecEndCase2()
    {
        final String STR_METHOD_NAME = "testExecExecEndCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(333812512246L);
            
            
            WEB3AdminFeqExecutionEndUnitServiceImpl l_impl = new WEB3AdminFeqExecutionEndUnitServiceImpl();
            l_impl.execExecEnd(l_account,  "SP", null);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //（is日計り取引採用() == false　@or　@is日計り市場() == false）　@且つ 買付注文（is買付() == true)
    //is買付() == false
    public void testExecExecEndCase3()
    {
        final String STR_METHOD_NAME = "testExecExecEndCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            l_feqOrderUnitParams.setOrderType(OrderTypeEnum.FEQ_SELL);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(333812512246L);
            
            
            WEB3AdminFeqExecutionEndUnitServiceImpl l_impl = new WEB3AdminFeqExecutionEndUnitServiceImpl();
            l_impl.execExecEnd(l_account,  "SP", null);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //（is日計り取引採用() == false　@or　@is日計り市場() == false）　@且つ 買付注文（is買付() == true)
    //is日計り取引採用() == true　@or　@is日計り市場() == true
    public void testExecExecEndCase4()
    {
        final String STR_METHOD_NAME = "testExecExecEndCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_SubAccountParams.setAccountId(333812512246L);
            l_SubAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("SP");
            TestDBUtility.insertWithDel(l_marketParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("feq.day.trade.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("feq.day.trade.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfo l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator,
                    WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE,
                    true,
                    true);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(333812512246L);
            
            
            WEB3AdminFeqExecutionEndUnitServiceImpl l_impl = new WEB3AdminFeqExecutionEndUnitServiceImpl();
            l_impl.execExecEnd(l_account,  "SP", null);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
