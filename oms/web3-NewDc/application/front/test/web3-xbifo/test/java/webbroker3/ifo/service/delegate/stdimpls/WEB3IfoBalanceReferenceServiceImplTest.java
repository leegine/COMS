head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoBalanceReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoBalanceReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/03 孫洪江 (中訊) 新規作成
Revision History : 2007/08/21 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoBalanceReferenceServiceImplTest extends TestBaseForMock
{
    private WEB3IfoBalanceReferenceServiceImpl l_service = null;
    private WEB3FuturesOptionsDetailUnit[] l_balanceReferenceDetailUnit = new WEB3FuturesOptionsDetailUnit[1];
    private WEB3FuturesOptionsSortKey[] l_sortKeys = new WEB3FuturesOptionsSortKey[1];
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3IfoBalanceReferenceServiceImplTest.class);

    public WEB3IfoBalanceReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        //MOCK_MANAGER.setIsMockUsed(true);
        this.l_service = new WEB3IfoBalanceReferenceServiceImpl();

        l_sortKeys[0] = new WEB3FuturesOptionsSortKey();
        l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.OPEN_DATE;
        l_sortKeys[0].ascDesc = "A";
        
    }

    protected void tearDown() throws Exception
    {
        this.l_sortKeys = null;
        this.l_service = null;
        super.tearDown();
    }
    
    /**
     * l_strKeyItem == WEB3IfoKeyItemDef.OPEN_DATE
     */
    public void testSortBalanceReferenceDetailUnit_0001()
    {
        final String STR_METHOD_NAME = "testSortBalanceReferenceDetailUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_service.sortBalanceReferenceDetailUnit(l_balanceReferenceDetailUnit, l_sortKeys);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    /**
     * l_strKeyItem != WEB3IfoKeyItemDef.OPEN_DATE
     */
    public void testSortBalanceReferenceDetailUnit_0002()
    {
        final String STR_METHOD_NAME = "testSortBalanceReferenceDetailUnit_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            l_sortKeys[0].keyItem = WEB3IfoKeyItemDef.BR_PRODUCTCODE;
            this.l_service.sortBalanceReferenceDetailUnit(l_balanceReferenceDetailUnit, l_sortKeys);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * リクエストデータの整合性をチェックする失敗。
     * WEB3ErrorCatalog.BUSINESS_ERROR_01736
     */
    public void testGetBalanceTotal_C0001()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();

            this.l_service.getBalanceTotal(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01736,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * システム売買停止(バッチ中、緊急停止中)チェックを実施する失敗。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testGetBalanceTotal_C0002()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            this.l_service.getBalanceTotal(l_request);

            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * (部店指数別)取扱条件オブジェクトを取得できません。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testGetBalanceTotal_C0003()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            this.l_service.getBalanceTotal(l_request);

            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 取引銘柄を取得する失敗。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testGetBalanceTotal_C0004()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            this.l_service.getBalanceTotal(l_request);

            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.建区分 == "買建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "プット"の場合
     */
    public void testGetBalanceTotal_C0005()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "1";
        l_detailUnit[0].opProductType = "P";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = "15";
        l_detailUnit[0].incomeCost = "20";
        l_detailUnit[0].currentPrice = "25";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);

            assertEquals("2500000", l_response.buyCurrentPrice);
            assertEquals("15", l_response.buyAssetProfitLoss);
            assertEquals("20", l_response.buyAssetProfitLossCost);
            assertEquals("2500000", l_response.putBuyCurrentPrice);
            assertEquals("15", l_response.putBuyAssetProfitLoss);
            assertEquals("20", l_response.putBuyAssetProfitLossCost);
            assertEquals("15", l_response.appraisalProfitLoss);
            assertEquals("20", l_response.appraisalProfitLossCost);
            
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].buyCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].buyAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].buyAssetProfitLossCost);
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].putBuyCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].putBuyAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].putBuyAssetProfitLossCost);
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.建区分 == "買建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "コール"の場合
     */
    public void testGetBalanceTotal_C0006()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "1";
        l_detailUnit[0].opProductType = "C";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = "15";
        l_detailUnit[0].incomeCost = "20";
        l_detailUnit[0].currentPrice = "25";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);

            assertEquals("2500000", l_response.buyCurrentPrice);
            assertEquals("15", l_response.buyAssetProfitLoss);
            assertEquals("20", l_response.buyAssetProfitLossCost);
            assertEquals("2500000", l_response.callBuyCurrentPrice);
            assertEquals("15", l_response.callBuyAssetProfitLoss);
            assertEquals("20", l_response.callBuyAssetProfitLossCost);
            assertEquals("15", l_response.appraisalProfitLoss);
            assertEquals("20", l_response.appraisalProfitLossCost);
            
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].buyCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].buyAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].buyAssetProfitLossCost);
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].callBuyCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].callBuyAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].callBuyAssetProfitLossCost);
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.建区分 == "売建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "プット"の場合
     */
    public void testGetBalanceTotal_C0007()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "2";
        l_detailUnit[0].opProductType = "P";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = "15";
        l_detailUnit[0].incomeCost = "20";
        l_detailUnit[0].currentPrice = "25";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);

            assertEquals("2500000", l_response.sellCurrentPrice);
            assertEquals("15", l_response.sellAssetProfitLoss);
            assertEquals("20", l_response.sellAssetProfitLossCost);
            assertEquals("2500000", l_response.putSellCurrentPrice);
            assertEquals("15", l_response.putSellAssetProfitLoss);
            assertEquals("20", l_response.putSellAssetProfitLossCost);
            assertEquals("15", l_response.appraisalProfitLoss);
            assertEquals("20", l_response.appraisalProfitLossCost);
            
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].sellCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].sellAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].sellAssetProfitLossCost);
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].putSellCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].putSellAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].putSellAssetProfitLossCost);
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.建区分 == "売建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "コール"の場合
     */
    public void testGetBalanceTotal_C0008()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "2";
        l_detailUnit[0].opProductType = "C";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = "15";
        l_detailUnit[0].incomeCost = "20";
        l_detailUnit[0].currentPrice = "25";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);

            assertEquals("2500000", l_response.sellCurrentPrice);
            assertEquals("15", l_response.sellAssetProfitLoss);
            assertEquals("20", l_response.sellAssetProfitLossCost);
            assertEquals("2500000", l_response.callSellCurrentPrice);
            assertEquals("15", l_response.callSellAssetProfitLoss);
            assertEquals("20", l_response.callSellAssetProfitLossCost);
            assertEquals("15", l_response.appraisalProfitLoss);
            assertEquals("20", l_response.appraisalProfitLossCost);
            
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].sellCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].sellAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].sellAssetProfitLossCost);
            assertEquals("2500000", l_response.futuresOptionsBalRefTotalParIndexUnits[0].callSellCurrentPrice);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].callSellAssetProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].callSellAssetProfitLossCost);
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("15", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("20", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.時価 == null && 処理対象の残高照会明細.損益 == null && 処理対sy層の残高照会明細.損益(諸経費込) == nullの場合
     * 処理対象の残高照会明細.建区分 == "買建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "プット"の場合
     */
    public void testGetBalanceTotal_C0009()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "1";
        l_detailUnit[0].opProductType = "P";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = null;
        l_detailUnit[0].incomeCost = null;
        l_detailUnit[0].currentPrice = null;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);
            
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.時価 == null && 処理対象の残高照会明細.損益 == null && 処理対sy層の残高照会明細.損益(諸経費込) == nullの場合
     * 処理対象の残高照会明細.建区分 == "買建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "コール"の場合
     */
    public void testGetBalanceTotal_C00010()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C00010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "1";
        l_detailUnit[0].opProductType = "C";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = null;
        l_detailUnit[0].incomeCost = null;
        l_detailUnit[0].currentPrice = null;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);
            
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.時価 == null && 処理対象の残高照会明細.損益 == null && 処理対sy層の残高照会明細.損益(諸経費込) == nullの場合
     * 処理対象の残高照会明細.建区分 == "売建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "プット"の場合
     */
    public void testGetBalanceTotal_C00011()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C00011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "2";
        l_detailUnit[0].opProductType = "P";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = null;
        l_detailUnit[0].incomeCost = null;
        l_detailUnit[0].currentPrice = null;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);
            
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /*
     * 処理対象の残高照会明細.時価 == null && 処理対象の残高照会明細.損益 == null && 処理対sy層の残高照会明細.損益(諸経費込) == nullの場合
     * 処理対象の残高照会明細.建区分 == "売建"の場合
     * 処理対象の残高照会明細.オプション商品区分 = "コール"の場合
     */
    public void testGetBalanceTotal_C00012()
    {
        final String STR_METHOD_NAME = "testGetBalanceTotal_C00012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = new WEB3FuturesOptionsDetailUnit[1];
        l_detailUnit[0] = new WEB3FuturesOptionsDetailUnit();
        l_detailUnit[0].productCode = "160030005";
        l_detailUnit[0].marketCode = "1";
        l_detailUnit[0].targetProductCode = "0005";
        l_detailUnit[0].contractType = "2";
        l_detailUnit[0].opProductType = "C";
        l_detailUnit[0].contractOrderQuantity = "10";
        l_detailUnit[0].income = null;
        l_detailUnit[0].incomeCost = null;
        l_detailUnit[0].currentPrice = null;
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "createIfoBalanceReferenceDetailUnit",
                new Class[]{ WEB3GentradeSubAccount.class, String.class, String.class, String.class, String[].class },
                l_detailUnit);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.FUTURE_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setMarketCode("33");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams2 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams2.setMarketCode("22");
            l_branchIndexDealtCondParams2.setInstitutionCode("0D");
            l_branchIndexDealtCondParams2.setBranchCode("381");
            l_branchIndexDealtCondParams2.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(333812512246L);
            l_ifoContractParams.setSubAccountId(33381251220301L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006149081018L);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006149081018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006149081018L);
            l_tradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006149081018L);
            l_IfoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            WEB3FuturesOptionsBalanceReferenceTotalRequest l_request =
                new WEB3FuturesOptionsBalanceReferenceTotalRequest();
            l_request.fuOpDiv = "1";

            WEB3FuturesOptionsBalanceReferenceTotalResponse l_response =
                this.l_service.getBalanceTotal(l_request);
            
            assertEquals("10", l_response.futuresOptionsBalRefTotalParIndexUnits[0].totalQuantity);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLoss);
            assertEquals("0", l_response.futuresOptionsBalRefTotalParIndexUnits[0].appraisalProfitLossCost);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

}
@
