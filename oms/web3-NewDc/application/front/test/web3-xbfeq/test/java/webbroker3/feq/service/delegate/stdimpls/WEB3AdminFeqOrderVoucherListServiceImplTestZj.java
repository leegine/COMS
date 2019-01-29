head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderVoucherListServiceImplTestZj.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式当日注文伝票一覧サービスImplテスト(WEB3AdminFeqOrderVoucherListServiceImplTestZj.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/18  周捷 (中訊) 新規作成
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderVoucherListInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderVoucherListService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式当日注文伝票一覧サービスImplテスト)<BR>
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListServiceImplTestZj extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminFeqOrderVoucherListServiceImplTestZj.class);
    public WEB3AdminFeqOrderVoucherListServiceImplTestZj(String arg0)
    {
        super(arg0);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForMock());
        Services.overrideService(OpLoginAdminService.class, new OpLoginAdminServiceImplForMock());

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderVoucherListServiceImpl.getInputScreen(WEB3AdminFeqOrderVoucherListInputRequest)'
     */
    public void testGetInputScreen1()
    {
        final String STR_METHOD_NAME = " testNotifyOrder1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
            
        WEB3AdminFeqOrderVoucherListService l_service = 
            (WEB3AdminFeqOrderVoucherListService)Services.getService(
                WEB3AdminFeqOrderVoucherListService.class);
        WEB3AdminFeqOrderVoucherListInputRequest l_request =
            new WEB3AdminFeqOrderVoucherListInputRequest();
        WEB3AdminFeqOrderVoucherListInputResponse l_response = null;
        try
        {
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
            	TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("C0402");
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //FeqBranchMarketDealtCondRow
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            
            l_response = (WEB3AdminFeqOrderVoucherListInputResponse)l_service.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    public void testGetInputScreen2()
    {
        final String STR_METHOD_NAME = " testNotifyOrder2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        
        WEB3AdminFeqOrderVoucherListService l_service = 
            (WEB3AdminFeqOrderVoucherListService)Services.getService(
                WEB3AdminFeqOrderVoucherListService.class);
        WEB3AdminFeqOrderVoucherListInputRequest l_request =
            new WEB3AdminFeqOrderVoucherListInputRequest();
        WEB3AdminFeqOrderVoucherListInputResponse l_response = null;
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
            	TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_admin = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_admin);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_AdminPermissionParams =
                TestDBUtility.getAdminPermissionRow();
            l_AdminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_AdminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_AdminPermissionParams.setTransactionCategory("C0402");
            TestDBUtility.insertWithDel(l_AdminPermissionParams);
            
            //FeqBranchMarketDealtCondRow
            TestDBUtility.deleteAll(FeqBranchMarketDealtCondParams.TYPE);
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams =
            	TestDBUtility.getFeqBranchMarketDealtCondRow();
            l_feqBranchMarketDealtCondParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);

            l_response = (WEB3AdminFeqOrderVoucherListInputResponse)l_service.execute(l_request);
            assertEquals("SP", l_response.marketCodeList[0]);
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderVoucherListServiceImpl.getObject(String, HashMap, Class, FeqOrderUnitRow)'
     */
    public void testGetObject1()
    {
        final String STR_METHOD_NAME = " testGetObject1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        
        HashMap l_hm = new HashMap();
        l_hm.put("111", new String("111"));
        Object l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            l_obj = l_impl.getObject("111", l_hm, WEB3GentradeCurrency.class, l_feqOrderUnitRow);
            assertEquals(new String("111"), l_obj.toString());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetObject2()
    {
        final String STR_METHOD_NAME = " testGetObject2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        HashMap l_hm = new HashMap();
        WEB3GentradeBranch l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(l_feqOrderUnitRow.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_obj = (WEB3GentradeBranch)l_impl.getObject(l_feqOrderUnitRow.getBranchId() + "", l_hm, WEB3GentradeBranch.class, l_feqOrderUnitRow);
            assertEquals("381", l_obj.getBranchCode());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetObject3()
    {
        final String STR_METHOD_NAME = " testGetObject3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        HashMap l_hm = new HashMap();
        WEB3GentradeMainAccount l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
            	TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitRow.getAccountId());
            l_mainAccountParams.setAccountCode("2512203");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_obj = 
                (WEB3GentradeMainAccount)l_impl.getObject(l_feqOrderUnitRow.getAccountId() + "", l_hm, WEB3GentradeMainAccount.class, l_feqOrderUnitRow);
            assertEquals(new String("2512203"), l_obj.getAccountCode());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetObject4()
    {
        final String STR_METHOD_NAME = " testGetObject4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        HashMap l_hm = new HashMap();
        Object l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            l_obj = l_impl.getObject("", l_hm, WEB3GentradeTrader.class, l_feqOrderUnitRow);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetObject5()
    {
        final String STR_METHOD_NAME = " testGetObject5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        HashMap l_hm = new HashMap();
        WEB3FeqProduct l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams =
            	TestDBUtility.getProductRow();
            l_productParams.setProductId(l_feqOrderUnitRow.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProductParams =
            	TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_feqOrderUnitRow.getProductId());
            l_feqProductParams.setProductCode("123");
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            l_obj = (WEB3FeqProduct)l_impl.getObject(l_feqOrderUnitRow.getProductId() + "", l_hm, WEB3FeqProduct.class, l_feqOrderUnitRow);
            assertEquals(new String("123"), l_obj.getProductCode());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetObject6()
    {
        final String STR_METHOD_NAME = " testGetObject6()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        HashMap l_hm = new HashMap();
        WEB3GentradeMarket l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_feqOrderUnitRow.getMarketId());
            l_marketParams.setMarketName("香港");
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_obj = (WEB3GentradeMarket)l_impl.getObject(l_feqOrderUnitRow.getMarketId() + "", l_hm, WEB3GentradeMarket.class, l_feqOrderUnitRow);
            assertEquals(new String("香港"), l_obj.getMarketName());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetObject7()
    {
        final String STR_METHOD_NAME = " testGetObject7()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminFeqOrderVoucherListServiceImpl l_impl = new WEB3AdminFeqOrderVoucherListServiceImpl();
        HashMap l_hm = new HashMap();
        WEB3GentradeCurrency l_obj = null;
        try
        {
            //FeqOrderUnitRow
            FeqOrderUnitParams l_feqOrderUnitRow =
            	TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitRow.setOrderUnitId(101);
            
            //GenCurrencyDao
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams =
            	TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode(l_feqOrderUnitRow.getInstitutionCode());
            l_genCurrencyParams.setCurrencyCode(l_feqOrderUnitRow.getCurrencyCode());
            l_genCurrencyParams.setCurrencyName("香港ドル");
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            l_obj = (WEB3GentradeCurrency)l_impl.getObject("0D" + l_feqOrderUnitRow.getCurrencyCode(), l_hm, WEB3GentradeCurrency.class, l_feqOrderUnitRow);
            assertEquals(new String("香港ドル"), l_obj.getCurrencyName());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>> pass !");
        }
        catch(Exception l_ex)
        {
            log.debug(STR_METHOD_NAME + l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
