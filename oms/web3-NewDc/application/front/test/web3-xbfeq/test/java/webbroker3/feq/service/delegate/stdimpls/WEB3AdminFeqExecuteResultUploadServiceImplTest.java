head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqExecuteResultUploadServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqRateDivDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqExecuteResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptResultUploadConfirmResponse;
import webbroker3.feq.message.WEB3FeqExchangeUnit;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3AdminFeqExecuteResultUploadServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqExecuteResultUploadServiceImplTest.class);

    WEB3AdminFeqExecuteResultUploadServiceImpl l_impl =
        new WEB3AdminFeqExecuteResultUploadServiceImpl();
    
    public WEB3AdminFeqExecuteResultUploadServiceImplTest(String arg0)
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


    public void testUndoUpload_T01()
    {
        final String STR_METHOD_NAME = "testUndoUpload_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //GenCurrencyParams
            TestDBUtility.deleteAll(GenCurrencyParams.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            l_genCurrencyParams.setInstitutionCode("0D");
            l_genCurrencyParams.setCurrentSellExecRate(123);
            l_genCurrencyParams.setCurrentBuyExecRate(456);
            TestDBUtility.insertWithDel(l_genCurrencyParams);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqProductManager l_productManager = 
                (WEB3FeqProductManager)l_tradingModule.getProductManager();
            WEB3GentradeCurrency[] l_genCurrencys = 
                l_productManager.getCurrency("0D");
            
            //1.15 get為替()戻り値の要素数分LOOP処理
            int l_intUnitCount = 0;
            if (l_genCurrencys != null)
            {
                l_intUnitCount = l_genCurrencys.length;
            }
            WEB3FeqExchangeUnit[] l_feqExchangeUnits = new WEB3FeqExchangeUnit[l_intUnitCount];
            for (int i = 0; i < l_intUnitCount; i++)
            {
                WEB3GentradeCurrency l_genCurrency = l_genCurrencys[i];
                GenCurrencyParams l_currencyParmas = (GenCurrencyParams)l_genCurrency.getDataSourceObject();
                //1.15.1 外国株式為替情報( )
                WEB3FeqExchangeUnit l_feqExchangeUnit = new WEB3FeqExchangeUnit();
            
                //1.15.2 プロパティセット
                //通貨コード
                l_feqExchangeUnit.currencyCode = l_currencyParmas.getCurrencyCode();
                //レート区分
                l_feqExchangeUnit.rateDiv = WEB3FeqRateDivDef.EXECUTED_EXCHANGE;
                //売付為替レート
                l_feqExchangeUnit.sellExchangeRate = 
                    WEB3StringTypeUtility.formatNumber(l_genCurrency.getSellExecRate());
                //買付為替レート
                l_feqExchangeUnit.buyExchangeRate = 
                    WEB3StringTypeUtility.formatNumber(l_genCurrency.getBuyExecRate());
                //登録日時
                l_feqExchangeUnit.registDatetime = l_currencyParmas.getExecRateUpdateTimestamp();
                
                l_feqExchangeUnits[i] = l_feqExchangeUnit;
            }
            
            assertEquals(1, l_feqExchangeUnits.length);
            
            WEB3FeqExchangeUnit l_feqExchangeUnit = l_feqExchangeUnits[0];
            assertEquals("123", l_feqExchangeUnit.sellExchangeRate);
            assertEquals("456", l_feqExchangeUnit.buyExchangeRate);

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidateUpload_Case001()
    {
        final String STR_METHOD_NAME = "testValidateUpload_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminFeqExecuteResultUploadConfirmRequest l_request =
                new WEB3AdminFeqExecuteResultUploadConfirmRequest();
            String[] l_uploadFileList = new String[1];
            l_uploadFileList[0] = "NVjidd0,NVjiddk5,NVjiddk1,123456,123456,123456,NVjiddk6,20080202124512";
            l_request.uploadFileList = l_uploadFileList;
            
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateAuthority(
                l_administrator, "C0402", true, true);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //InstitutionPreferencesRow
            TestDBUtility.deleteAll(InstitutionPreferencesRow.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName("feq.order.emp.code.div");
            l_institutionPreferencesParams.setNameSerialNo(1);
            l_institutionPreferencesParams.setValue("NC");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_clendarContext.setBranchCode("624");
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_clendarContext);
            
            //FeqOrderUnitRow
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams = 
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERING);
            l_feqOrderUnitParams.setConfirmedQuantity(0);
            l_feqOrderUnitParams.setExecEndTimestamp(null);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            OrderManager l_orderManager = l_tradingModule.getOrderManager();
            FeqOrderUnit l_feqOrderUnit =
                (FeqOrderUnit)l_orderManager.getOrderUnit(l_feqOrderUnitParams.getOrderUnitId());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "getValidOrderUnitByOrderEmpCode",
                new Class[] {Date.class, String.class},
                l_feqOrderUnit);
            
            TestDBUtility.deleteAllAndCommit(AdministratorUploadRow.TYPE);
            WEB3AdminFeqExecuteResultUploadConfirmResponse l_response =
                l_impl.validateUpload(l_request);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02188);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
