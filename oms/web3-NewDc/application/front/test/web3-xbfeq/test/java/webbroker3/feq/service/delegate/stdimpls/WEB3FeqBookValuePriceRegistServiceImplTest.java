head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.18.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBookValuePriceRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;
import com.fitechlabs.xtrade.kernel.enum.Enumerated.*;

import test.util.TestDBUtility;
import webbroker3.feq.message.*;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqBookValuePriceRegistServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3FeqBookValuePriceRegistServiceImplTest.class);

     WEB3FeqBookValuePriceRegistServiceImpl l_impl =
         new WEB3FeqBookValuePriceRegistServiceImpl();

    public WEB3FeqBookValuePriceRegistServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        deletRow();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        deletRow();
    }

    public void testValidateBalanceQuantity_T001()
    {
        final String STR_METHOD_NAME = "testValidateBalanceQuantity_T001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Asset l_asset = null;
            l_impl.validateBalanceQuantity(null, 0);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBalanceQuantity_T002()
    {
        final String STR_METHOD_NAME = "testValidateBalanceQuantity_T002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqPositionManager l_feqPositionManager =
                (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            TestDBUtility.insertWithDel(l_assetParams);
            
            Asset l_asset =
                l_feqPositionManager.getAsset(l_assetParams.getAssetId());
            l_impl.validateBalanceQuantity(l_asset, 0);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02982 , l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBalanceQuantity_T003()
    {
        final String STR_METHOD_NAME = "testValidateBalanceQuantity_T003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqPositionManager l_feqPositionManager =
                (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            TestDBUtility.insertWithDel(l_assetParams);
            
            Asset l_asset =
                l_feqPositionManager.getAsset(l_assetParams.getAssetId());
            l_impl.validateBalanceQuantity(l_asset, 444);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCreateAssetParams_T001()
    {
        final String STR_METHOD_NAME = "testCreateAssetParams_T001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqPositionManager l_feqPositionManager =
                (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            TestDBUtility.insertWithDel(l_assetParams);
            
            Asset l_asset =
                l_feqPositionManager.getAsset(l_assetParams.getAssetId());
            AssetParams l_assetP = l_impl.createAssetParams(
                l_asset,
                123,
                456.23,
                789.12);
            assertEquals(789.12, l_assetP.getQuantityForBookValue(), 0);
            assertEquals(456.23, l_assetP.getBookValue(), 0);
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testgetInputScreen_T001()
    {
        final String STR_METHOD_NAME = "testgetInputScreen_T001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqPositionManager l_feqPositionManager =
                (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setBookValue(2);
            l_assetParams.setQuantityForBookValue(3);
            l_assetParams.setTaxType(TaxTypeEnum.NORMAL);
            TestDBUtility.insertWithDel(l_assetParams);

            //ProductParams
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_productParams.setProductId(l_assetParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            //FeqProductParams
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(l_productParams.getProductId());
            l_feqProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            WEB3FeqBookPriceInputRequest l_request = new WEB3FeqBookPriceInputRequest();
            l_request.assetId = l_assetParams.getAssetId() + "";
            
            WEB3FeqBookPriceInputResponse l_response =
                l_impl.getInputScreen(l_request);
            assertEquals("0.66667", l_response.estimatedBookPrice);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitBookValuePrice_T001()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_T001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqBookValuePriceRegistServiceImplForTest l_implTest = 
                new WEB3FeqBookValuePriceRegistServiceImplForTest();
            WEB3FeqBookPriceRegistRequest l_request = new WEB3FeqBookPriceRegistRequest();
            l_request.assetId = "123";
            l_request.aftBookPrice = "1000";
            l_request.aftBookAmount = "444";

            //InstitutionParams
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(123456);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(456789);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setInstitutionId(123456);
            l_mainAccountParams.setBranchId(456789);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            TestDBUtility.insertWithDel(l_assetParams);
            
            l_implTest.submitBookValuePrice(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02982 , l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitBookValuePrice_T002()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_T002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqBookValuePriceRegistServiceImplForTest l_implTest = 
                new WEB3FeqBookValuePriceRegistServiceImplForTest();
            WEB3FeqBookPriceRegistRequest l_request = new WEB3FeqBookPriceRegistRequest();
            l_request.assetId = "123";
            l_request.aftBookPrice = "1000";
            l_request.aftBookAmount = "200";
            l_request.balanceQuantity = "444";

            //InstitutionParams
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(123456);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(456789);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MainAccountParams
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setInstitutionId(123456);
            l_mainAccountParams.setBranchId(456789);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            TestDBUtility.insertWithDel(l_assetParams);
            
            l_implTest.submitBookValuePrice(l_request);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBookValuePrice_T01()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_impl.validateBookValuePrice(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01919, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBookValuePrice_T02()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "1000";
            l_impl.validateBookValuePrice(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBookValuePrice_T03()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setQuantity(123);
            l_assetParams.setQuantityCannotSell(321);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "1000";
            l_impl.validateBookValuePrice(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02982 , l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateBookValuePrice_T04()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setQuantity(2);
            l_assetParams.setQuantityCannotSell(1);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "2";
            l_request.balanceQuantity = "3";
            WEB3FeqBookPriceConfirmResponse l_response =
                l_impl.validateBookValuePrice(l_request);
            assertEquals("0.66667", l_response.aftBookPrice);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateBookValuePrice_T05()
    {
        final String STR_METHOD_NAME = "testValidateBookValuePrice_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setQuantity(2);
            l_assetParams.setQuantityCannotSell(1);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "2000000000";
            l_request.balanceQuantity = "3";
            WEB3FeqBookPriceConfirmResponse l_response =
                l_impl.validateBookValuePrice(l_request);
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02110, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testExcute_T01()
    {
        final String STR_METHOD_NAME = "testExcute_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AssetParams
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_assetParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_assetParams.setAssetId(123456);
            l_assetParams.setQuantity(2);
            l_assetParams.setQuantityCannotSell(1);
            TestDBUtility.insertWithDel(l_assetParams);
            
            WEB3FeqBookPriceConfirmRequest l_request = new WEB3FeqBookPriceConfirmRequest();
            l_request.assetId = "123456";
            l_request.aftBookAmount = "2";
            l_request.balanceQuantity = "3";
            WEB3FeqBookPriceConfirmResponse l_response =
                (WEB3FeqBookPriceConfirmResponse)l_impl.execute(l_request);
            assertEquals("0.66667", l_response.aftBookPrice);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void deletRow()
    {
        try
        {
            //AssetParams
            TestDBUtility.deleteAll(AssetParams.TYPE);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
        }
        catch(Exception l_ex)
        {
            fail();
        }
    }
    
    public class WEB3FeqBookValuePriceRegistServiceImplForTest
        extends WEB3FeqBookValuePriceRegistServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            SubAccount l_subAcccount = null;
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3GentradeAccountManager l_accountManager =
                    (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
                //SubAccountParams
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setInstitutionCode("0D");
                l_subAccountParams.setInstitutionId(123456);
                l_subAccountParams.setBranchId(456789);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                l_subAcccount = l_accountManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());
                
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            
            return l_subAcccount;
        }
    }
}
@
