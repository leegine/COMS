head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundPositionManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信ポジションマネージャテスト(WEB3MutualFundPositionManagerTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/10 趙林鵬 (中訊) 新規作成
*/

package webbroker3.mf;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.mf.data.MfSubAssetParams;
import webbroker3.mf.data.MfSubAssetRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （拡張投信ポジションマネージャテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3MutualFundPositionManagerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3MutualFundPositionManagerTest.class);
    
    public WEB3MutualFundPositionManagerTest(String arg0)
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
     * Test method for 'webbroker3.mf.WEB3MutualFundPositionManager.calcMarketValue(SubAccount, WEB3MutualFundProduct, String)'
     */
    public void test_calcMarketValue_C0001()
    {
        final String STR_METHOD_NAME = " test_calcMarketValue_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setCurrencyCode("A0");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A0");

            this.deleteAll();
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_assetParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = new SubAccountImpl(101001010010L,10100101001007L);
            
            MutualFundProductRow l_mutualFundProductRow = MutualFundProductDao.findRowByPk(1006169090018L);
            WEB3MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductRow);
            
            String l_strAssetId = "1001";
            
            double l_dblCalcMarketValue = 
                l_mutualFundPositionManager.calcMarketValue(
                    l_subAccount,
                    l_mutualFundProduct,
                    l_strAssetId);
            assertEquals("100", WEB3StringTypeUtility.formatNumber(l_dblCalcMarketValue));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    /*
     * Test method for 'webbroker3.mf.WEB3MutualFundPositionManager.calcAppraisalProfitLoss(SubAccount, WEB3MutualFundProduct, String)'
     */
    public void test_calcAppraisalProfitLoss_C0001()
    {
        final String STR_METHOD_NAME = " test_calcAppraisalProfitLoss_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setCurrencyCode("A0");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A0");

            this.deleteAll();
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_assetParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = new SubAccountImpl(101001010010L,10100101001007L);
            
            MutualFundProductRow l_mutualFundProductRow = MutualFundProductDao.findRowByPk(1006169090018L);
            WEB3MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductRow);
            
            String l_strAssetId = "1001";
            
            double l_dblCalcAppraisalProfitLoss = 
                l_mutualFundPositionManager.calcAppraisalProfitLoss(
                    l_subAccount,
                    l_mutualFundProduct,
                    l_strAssetId);

            assertTrue(Double.isNaN(l_dblCalcAppraisalProfitLoss));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    /*
     * Test method for 'webbroker3.mf.WEB3MutualFundPositionManager.getAssets(SubAccount, String)'
     */
    public void test_getAssets_C0001()
    {
        final String STR_METHOD_NAME = " test_getAssets_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mutualFundProductParams.setProductId(1006169090018L);

            this.deleteAll();
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_assetParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = new SubAccountImpl(101001010010L,10100101001007L);
            
            String l_strMutualFrgnMmfDisplayDiv = "2";
            
            List l_lisAssets = l_mutualFundPositionManager.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
            
            log.debug("l_lisAssets.size() ===== " + l_lisAssets.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getAssets_C0002()
    {
        final String STR_METHOD_NAME = " test_getAssets_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.OTHER);

            this.deleteAll();
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_assetParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = new SubAccountImpl(101001010010L,10100101001007L);
            
            String l_strMutualFrgnMmfDisplayDiv = "0";
            
            List l_lisAssets = l_mutualFundPositionManager.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
            
            log.debug("l_lisAssets.size() ===== " + l_lisAssets.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getAssets_C0003()
    {
        final String STR_METHOD_NAME = " test_getAssets_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

            this.deleteAll();
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_assetParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = new SubAccountImpl(101001010010L,10100101001007L);
            
            String l_strMutualFrgnMmfDisplayDiv = "1";
            
            List l_lisAssets = l_mutualFundPositionManager.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
            
            log.debug("l_lisAssets.size() ===== " + l_lisAssets.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getAssets_C0004()
    {
        final String STR_METHOD_NAME = " test_getAssets_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = null;
            
            String l_strMutualFrgnMmfDisplayDiv = "1";
            
            l_mutualFundPositionManager.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getAssets_C0005()
    {
        final String STR_METHOD_NAME = " test_getAssets_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            
            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.MUTUAL_FUND_SUB_ACCOUNT);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

            l_assetParams.setProductId(1006169090020L);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_assetParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();
            
            SubAccount l_subAccount = new SubAccountImpl(101001010010L,10100101001007L);
            
            String l_strMutualFrgnMmfDisplayDiv = "1";
            
            l_mutualFundPositionManager.getAssets(l_subAccount, l_strMutualFrgnMmfDisplayDiv);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    /*
     * Test method for 'webbroker3.mf.WEB3MutualFundPositionManager.getUnreceiveDist(String)'
     */
    public void test_getUnreceiveDist_C0001()
    {
        final String STR_METHOD_NAME = " test_getUnreceiveDist_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            MfSubAssetParams l_mfSubAssetParams = TestDBUtility.getMfSubAssetRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mfSubAssetParams.setUnreceiveDist(456.12);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A0");
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setCurrencyCode("A0");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_mfSubAssetParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            String l_strAssetId = "1001";
            
            MutualFundProductRow l_mutualFundProductRow = MutualFundProductDao.findRowByPk(1006169090018L);
            WEB3MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductRow);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();

            long l_lngUnreceiveDist = l_mutualFundPositionManager.getUnreceiveDist(l_strAssetId, l_mutualFundProduct);

            assertEquals(456, l_lngUnreceiveDist);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void test_getUnreceiveDist_C0002()
    {
        final String STR_METHOD_NAME = " test_getUnreceiveDist_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
    
        try
        {
            MfSubAssetParams l_mfSubAssetParams = TestDBUtility.getMfSubAssetRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mfSubAssetParams.setUnreceiveDist(456.12);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A0");
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setCurrencyCode("A0");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_mfSubAssetParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            String l_strAssetId = "1002";
            
            MutualFundProductRow l_mutualFundProductRow = MutualFundProductDao.findRowByPk(1006169090018L);
            WEB3MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductRow);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();

            long l_lngUnreceiveDist = l_mutualFundPositionManager.getUnreceiveDist(l_strAssetId, l_mutualFundProduct);
            
            assertEquals(0, l_lngUnreceiveDist);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }    
    
    public void test_getUnreceiveDist_C0003()
    {
        final String STR_METHOD_NAME = " test_getUnreceiveDist_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MfSubAssetParams l_mfSubAssetParams = TestDBUtility.getMfSubAssetRow();
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            FrgnMmfExchangeRateParams l_frgnMmfExchangeRateParams = TestDBUtility.getFrgnMmfExchangeRateRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
            
            l_mfSubAssetParams.setUnreceiveDist(456.12);
            
            l_frgnMmfExchangeRateParams.setCurrencyCode("A0");
            
            l_mutualFundProductParams.setProductId(1006169090018L);
            l_mutualFundProductParams.setCurrencyCode("A0");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_mfSubAssetParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_frgnMmfExchangeRateParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            String l_strAssetId = null;
            
            MutualFundProductRow l_mutualFundProductRow = MutualFundProductDao.findRowByPk(1006169090018L);
            WEB3MutualFundProduct l_mutualFundProduct = new WEB3MutualFundProduct(l_mutualFundProductRow);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3MutualFundPositionManager l_mutualFundPositionManager =
                (WEB3MutualFundPositionManager)l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND).getPositionManager();

            l_mutualFundPositionManager.getUnreceiveDist(l_strAssetId, l_mutualFundProduct);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(AssetRow.TYPE);
            TestDBUtility.deleteAll(MfSubAssetRow.TYPE);
            TestDBUtility.deleteAll(FrgnMmfExchangeRateRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
}
@
