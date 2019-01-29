head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityBookValuePriceRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityBookValuePriceRegistServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/25 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.message.WEB3EquityBookPriceInputRequest;
import webbroker3.equity.message.WEB3EquityBookPriceInputResponse;
import webbroker3.equity.message.WEB3EquityBookPriceRegistRequest;
import webbroker3.equity.message.WEB3EquityBookPriceRegistResponse;
import webbroker3.equity.message.WEB3EquityCommonRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityBookValuePriceRegistServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log = WEB3LogUtility
    .getInstance(WEB3EquityBookValuePriceRegistServiceImplTest.class);
    
    private WEB3EquityBookValuePriceRegistServiceImpl  l_serviceImpl= null;

    public WEB3EquityBookValuePriceRegistServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3EquityBookValuePriceRegistServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //l_request == null
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest =null;
            l_serviceImpl.execute(inputRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_request instanceof 現物株式簿価単価登録入力リクエスト
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest = new WEB3EquityBookPriceInputRequest();
            WEB3EquityBookValuePriceRegistServiceImplForTest forTest = new WEB3EquityBookValuePriceRegistServiceImplForTest();
            assertEquals(((WEB3EquityBookPriceInputResponse)forTest.execute(inputRequest)).assetId, "1111");
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_request instanceof 現物株式簿価単価登録リクエスト
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceRegistRequest registRequest = new WEB3EquityBookPriceRegistRequest();
            WEB3EquityBookValuePriceRegistServiceImplForTest forTest = new WEB3EquityBookValuePriceRegistServiceImplForTest();
            assertEquals(((WEB3EquityBookPriceRegistResponse)forTest.execute(registRequest)).errorMessage, "1111");
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //l_request instanceof 現物株式簿価単価登録リクエスト
    //現物株式簿価単価登録入力リクエスト以外的場合
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityCommonRequest commonRequest = new WEB3EquityCommonRequest();
            WEB3EquityBookValuePriceRegistServiceImplForTest forTest =
                new WEB3EquityBookValuePriceRegistServiceImplForTest();
            forTest.execute(commonRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate
    public void testGetInputScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest = new WEB3EquityBookPriceInputRequest();
            l_serviceImpl.getInputScreen(inputRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_01919, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //getAsset取得失敗
    //SYSTEM_ERROR_80005
    public void testGetInputScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest = new WEB3EquityBookPriceInputRequest();
            inputRequest.assetId = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1112);
            TestDBUtility.insertWithDel(assetParams);

            l_serviceImpl.getInputScreen(inputRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //一般口座チェック
    //SYSTEM_ERROR_80006
    public void testGetInputScreen_C0003()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest = new WEB3EquityBookPriceInputRequest();
            inputRequest.assetId = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(assetParams);

            l_serviceImpl.getInputScreen(inputRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //入力簿価単価&簿価単価入力日時
    //入力簿価value is null
    public void testGetInputScreen_C0004()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest = new WEB3EquityBookPriceInputRequest();
            inputRequest.assetId = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setTaxType(TaxTypeEnum.NORMAL);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setInputBookValue(null);
            TestDBUtility.insertWithDel(assetParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            eqtypeProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice", new Class[]
                    { long.class, SubAccount.class, TaxTypeEnum.class },
                    new Long(1111));
            
            assertNull(l_serviceImpl.getInputScreen(inputRequest).inputBookPrice);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //入力簿価単価&簿価単価入力日時
    //入力簿価value is not null
    public void testGetInputScreen_C0005()
    {
        final String STR_METHOD_NAME = "testGetInputScreen_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceInputRequest inputRequest = new WEB3EquityBookPriceInputRequest();
            inputRequest.assetId = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setTaxType(TaxTypeEnum.NORMAL);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setInputBookValue(100);
            TestDBUtility.insertWithDel(assetParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            eqtypeProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(productParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcEstimatedBookPrice", new Class[]
                    { long.class, SubAccount.class, TaxTypeEnum.class },
                    new Long(1111));
            
            assertEquals(l_serviceImpl.getInputScreen(inputRequest).inputBookPrice, ""+100);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //validate
    public void testSubmitBookValuePrice_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceRegistRequest registRequest = new WEB3EquityBookPriceRegistRequest();
            l_serviceImpl.submitBookValuePrice(registRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_01919, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //getAsset取得失敗
    public void testSubmitBookValuePrice_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceRegistRequest registRequest = new WEB3EquityBookPriceRegistRequest();
            registRequest.assetId = "1111";
            registRequest.aftBookPrice = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1112);
            TestDBUtility.insertWithDel(assetParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L)); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);
            
            l_serviceImpl.submitBookValuePrice(registRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //計算した簿価の桁数チェック
    //入力した簿価単価の値が大きすぎます。(計算後の簿価の桁数が12桁以上)
    //BUSINESS_ERROR_01921
    public void testSubmitBookValuePrice_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceRegistRequest registRequest = new WEB3EquityBookPriceRegistRequest();
            registRequest.assetId = "1111";
            registRequest.aftBookPrice = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            assetParams.setQuantity(111111111111.00);
            assetParams.setQuantityCannotSell(111111111111.00);
            TestDBUtility.insertWithDel(assetParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L)); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);
            
            l_serviceImpl.submitBookValuePrice(registRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_01921, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    // 一般口座チェック
    //SYSTEM_ERROR_80006
    public void testSubmitBookValuePrice_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceRegistRequest registRequest = new WEB3EquityBookPriceRegistRequest();
            registRequest.assetId = "1111";
            registRequest.aftBookPrice = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setTaxType(TaxTypeEnum.UNDEFINED);
            TestDBUtility.insertWithDel(assetParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L)); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);
            
            l_serviceImpl.submitBookValuePrice(registRequest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testSubmitBookValuePrice_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitBookValuePrice_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3EquityBookPriceRegistRequest registRequest = new WEB3EquityBookPriceRegistRequest();
            registRequest.assetId = "1111";
            registRequest.aftBookPrice = "1111";
            
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setTaxType(TaxTypeEnum.NORMAL);
            TestDBUtility.insertWithDel(assetParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L)); 
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);
            
            l_serviceImpl.submitBookValuePrice(registRequest);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //数量（簿価単価計算用）
    // 簿価（簿価単価計算用）
    //入力簿価単価
    //簿価単価入力日時
    //更新日付
    public void testCreateAssetParams_C0001()
    {
        final String STR_METHOD_NAME = "testCreateAssetParams_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams assetParams = TestDBUtility.getAssetRow();
            assetParams.setAssetId(1111);
            assetParams.setProductType(ProductTypeEnum.EQUITY);
            assetParams.setTaxType(TaxTypeEnum.NORMAL);
            TestDBUtility.insertWithDel(assetParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            WEB3EquityAsset l_asset =
                (WEB3EquityAsset)l_positionManager.getAsset(1111L);
            AssetParams assetParams2 = l_serviceImpl.createAssetParams(l_asset, 100);
            Timestamp l_currentTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            assertEquals("" + 200.0, "" + assetParams2.quantity_for_book_value);
            assertEquals("" + 20000.0, "" + assetParams2.book_value);
            assertEquals("" + 100.0, "" + assetParams2.input_book_value);
            assertEquals(l_currentTime, assetParams2.input_timestamp);
            assertEquals(l_currentTime, assetParams2.last_updated_timestamp);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    private class WEB3EquityBookValuePriceRegistServiceImplForTest extends WEB3EquityBookValuePriceRegistServiceImpl
    {
        protected WEB3EquityBookPriceInputResponse getInputScreen(WEB3EquityBookPriceInputRequest l_request) 
        throws WEB3BaseException
        {
            WEB3EquityBookPriceInputResponse l_response = new WEB3EquityBookPriceInputResponse();
            l_response.assetId = "1111";
            return l_response;
        }
        protected WEB3EquityBookPriceRegistResponse submitBookValuePrice(WEB3EquityBookPriceRegistRequest l_request) 
        throws WEB3BaseException
        {
            WEB3EquityBookPriceRegistResponse l_response = new WEB3EquityBookPriceRegistResponse();
            l_response.errorMessage = "1111";
            return l_response;
        }
    }
}
@
