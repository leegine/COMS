head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.34.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioOrderManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金注文マネージャテスド(WEB3AioOrderManager)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/10/16  何文敏 (中訊)
*/
package webbroker3.aio;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioOrderManagerTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOrderManagerTest.class);

    WEB3AioOrderManager l_orderManager = new WEB3AioOrderManager();

    public WEB3AioOrderManagerTest(String arg0)
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

    public void testValidateSLRepayDuplicateOrder_case0001()
    {
        final String STR_METHOD_NAME = "testValidateSLRepayDuplicateOrder_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            SubAccount l_subAccount = null;
            l_orderManager.validateSLRepayDuplicateOrder(l_subAccount , WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateSLRepayDuplicateOrder_case0002()
    {
        final String STR_METHOD_NAME = "testValidateSLRepayDuplicateOrder_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);
            l_orderManager.validateSLRepayDuplicateOrder(l_subAccount , null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateSLRepayDuplicateOrder_case0003()
    {
        final String STR_METHOD_NAME = "testValidateSLRepayDuplicateOrder_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            MainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = 
                new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);
            l_orderManager.validateSLRepayDuplicateOrder(l_subAccount , WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateSLRepayDuplicateOrder_case0004()
    {
        final String STR_METHOD_NAME = "testValidateSLRepayDuplicateOrder_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
     
            MainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = 
                new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);
            l_orderManager.validateSLRepayDuplicateOrder(l_subAccount , WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00757, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateSLRepayDuplicateOrder_case0005()
    {
        final String STR_METHOD_NAME = "testValidateSLRepayDuplicateOrder_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderUnitParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ORDERING);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
     
            MainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = 
                new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);
            l_orderManager.validateSLRepayDuplicateOrder(l_subAccount , WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);  
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00757, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateSLRepayDuplicateOrder_case0006()
    {
        final String STR_METHOD_NAME = "testValidateSLRepayDuplicateOrder_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderUnitParams1.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams1.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams1.setOrderStatus(OrderStatusEnum.ORDERING);
            l_aioOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams1);
            
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams2.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderUnitParams2.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams2.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams2.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams2.setDeliveryDate(WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_aioOrderUnitParams2);
     
            MainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            SubAccount l_subAccount = 
                new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);
            l_orderManager.validateSLRepayDuplicateOrder(l_subAccount , WEB3DateUtility.getDate("20071016", "yyyyMMdd"));
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);;
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00757, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCFDChangePoss_C0001()
    {
        final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0001()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();
        
        WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("2");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_aioOrderManager.validateCFDChangePoss(l_subAccount, "10");
        	fail();
        }
        catch (WEB3BaseException l_ex)
        {
        	assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02440);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateCFDChangePoss_C0002()
    {
        final String STR_METHOD_NAME = "testValidateCFDChangePoss_C0002()";
        log.entering(STR_METHOD_NAME);

        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accMgr = l_finApp.getAccountManager();

        WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();

        try
        {
        	TestDBUtility.deleteAll(MainAccountRow.TYPE);
        	MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        	l_mainAccountParams.setAccountId(123);
        	l_mainAccountParams.setMrfAccOpenDiv("0");
        	l_mainAccountParams.setCfdAccOpenDiv("1");
        	TestDBUtility.insertWithDel(l_mainAccountParams);

        	TestDBUtility.deleteAll(BranchRow.TYPE);
        	BranchParams l_branchParams = TestDBUtility.getBranchRow();
        	l_branchParams.setBranchId(33381L);
        	TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

        	TestDBUtility.deleteAll(SubAccountRow.TYPE);
        	SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        	l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        	l_subAccountParams.setAccountId(123);
        	TestDBUtility.insertWithDel(l_subAccountParams);

        	TestDBUtility.deleteAll(FxAccountRow.TYPE);
        	FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        	l_fxAccountParams.setBranchCode("381");
        	l_fxAccountParams.setAccountCode("2512246");
        	l_fxAccountParams.setFxSystemCode("10");
        	l_fxAccountParams.setFxAccountDiv("1");
        	TestDBUtility.insertWithDel(l_fxAccountParams);

        	TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        	BranchPreferencesParams l_branchPreferencesParams =
        		TestDBUtility.getBranchPreferencesRow();
        	l_branchPreferencesParams.setBranchId(33381L);
        	l_branchPreferencesParams.setName("fx.deliverydate.insert.check");
        	l_branchPreferencesParams.setNameSerialNo(1);
        	l_branchPreferencesParams.setValue("0");
        	TestDBUtility.insertWithDel(l_branchPreferencesParams);

        	l_mainAccount = l_accMgr.getMainAccount(123);
            l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            l_aioOrderManager.validateCFDChangePoss(l_subAccount, "10");

            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
        	log.error(l_ex.getMessage(), l_ex);
        	log.exiting(STR_METHOD_NAME);
        	fail();
        }
    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書NO:1091
     *
     */
    public void testGetMarginTransferDesignatedDate_0001()
    {
    	String STR_METHOD_NAME = "testGetMarginTransferDesignatedDate_0001";
    	 WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
     	//発注日:20090317
     	Date l_datBizDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
    	try
    	{
    	    SystemPreferencesParams  l_sysPrePar = TestDBUtility.getSystemPreferencesRow();
    	    TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
    	    //業務日付:20090317
    	    l_sysPrePar.setValue("20090317");
    	    TestDBUtility.insertWithDel(l_sysPrePar);

    	     int l_intResult = l_aioOrderManager.getMarginTransferDesignatedDate(l_datBizDate);
    	     assertEquals(0,l_intResult);
    	 }
    	 catch(Exception l_ex)
    	 {
    	     log.error(STR_METHOD_NAME, l_ex);
    	     log.exiting(STR_METHOD_NAME);
    	     fail();
    	 }
    	 log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書NO:1092
     *
     */
    public void testGetMarginTransferDesignatedDate_0002()
    {
    	String STR_METHOD_NAME = "testGetMarginTransferDesignatedDate_0002";
    	 WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
     	//発注日:20090315
     	Date l_datBizDate = WEB3DateUtility.getDate("20090315", "yyyyMMdd");
    	try
    	{
    	    SystemPreferencesParams  l_sysPrePar = TestDBUtility.getSystemPreferencesRow();
    	    TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
    	    //業務日付:20090317
    	    l_sysPrePar.setValue("20090317");
    	    TestDBUtility.insertWithDel(l_sysPrePar);

    	     int l_intResult = l_aioOrderManager.getMarginTransferDesignatedDate(l_datBizDate);
    	     assertEquals(1,l_intResult);
    	 }
    	 catch(Exception l_ex)
    	 {
    	     log.error(STR_METHOD_NAME, l_ex);
    	     log.exiting(STR_METHOD_NAME);
    	     fail();
    	 }
    	 log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 【WEB3】【入出金】単体テスト仕様兼報告書NO:1093
     *
     */
    public void testGetMarginTransferDesignatedDate_0003()
    {
    	String STR_METHOD_NAME = "testGetMarginTransferDesignatedDate_0003";
    	 WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
     	//発注日:null;
     	Date l_datBizDate = null;
    	try
    	{
    	    SystemPreferencesParams  l_sysPrePar = TestDBUtility.getSystemPreferencesRow();
    	    TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
    	    //業務日付:20090317
    	    l_sysPrePar.setValue("20090317");
    	    TestDBUtility.insertWithDel(l_sysPrePar);

    	     int l_intResult = l_aioOrderManager.getMarginTransferDesignatedDate(l_datBizDate);
    	     assertEquals(1,l_intResult);
    	 }
    	 catch(Exception l_ex)
    	 {
    	     log.error(STR_METHOD_NAME, l_ex);
    	     log.exiting(STR_METHOD_NAME);
    	     fail();
    	 }
    	 log.exiting(STR_METHOD_NAME);
    }

    /**
     * 入出金発注審査個別チェック.validate振替取引可能()メソッドに委譲する
     *  getFX顧客()でFX顧客Paramsが取得できなかった場合(FX口座未開設）、例外をthrowする。
     */
    public void testＶalidateTransferTradePossible_C0001()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("02");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setFxSystemCode("01");

            WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
            l_aioOrderManager.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01866, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 入出金発注審査個別チェック.validate振替取引可能()メソッドに委譲する
     * 正常
     */
    public void testＶalidateTransferTradePossible_C0002()
    {
        final String STR_METHOD_NAME = "testＶalidateTransferTradePossible_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxAccountDiv("1");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(124);
            l_subAccountParams.setAccountId(100);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(100);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            AccOpenDivParams l_accOpenDivParams = TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(100);
            l_accOpenDivParams.setAccType("03");
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);

            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setMrfAllowDiv("1");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setAccType("03");

            WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
            l_aioOrderManager.validateTransferTradePossible(
                l_subAccount,
                l_compFxConditionParams);

        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * FXデータ制御サービス.getFX顧客()でFX顧客Paramsが取得できない
     * 引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合
     */
    public void testValidateFXAccOpenPossible_C0001()
    {
        final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("02");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(100);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101);
            l_mainAccountParams.setBranchId(100);
            l_mainAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(100);
            l_subAccountParams.setAccountId(101);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setFxSystemDiv("2");

            WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
            l_aioOrderManager.validateFXAccOpenPossible(
                l_subAccount,
                l_compFxConditionParams);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03133, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * FXデータ制御サービス.getFX顧客()でFX顧客Paramsが取得できない
     * 引数.会社別FXシステム条件Params.FXシステム区分==2（CFDシステム）の場合
     */
    public void testValidateFXAccOpenPossible_C0002()
    {
        final String STR_METHOD_NAME = "testValidateFXAccOpenPossible_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(100);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101);
            l_mainAccountParams.setBranchId(100);
            l_mainAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(100);
            l_subAccountParams.setAccountId(101);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setOnlineAccOpen("1");

            WEB3AioOrderManager l_aioOrderManager = new WEB3AioOrderManager();
            l_aioOrderManager.validateFXAccOpenPossible(
                l_subAccount,
                l_compFxConditionParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
