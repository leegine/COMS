head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginSwapMarginServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MarginSwapMarginServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/12/11 金傑（中訊）新規作成
 */
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡サービスImplテスト）<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3MarginSwapMarginServiceImplTest extends TestBaseForMock
{

    private WEB3MarginSwapMarginServiceImpl l_serviceImpl = null;

    private WEB3MarginSwapMarginConfirmRequest l_request = null;
    
    private boolean l_blnIsPass = true;
    
    private WEB3MarginSwapMarginCompleteRequest l_submitRequest = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginServiceImplTest.class);

    public WEB3MarginSwapMarginServiceImplTest(String l_strName)
    {
        super(l_strName);
    }

    public void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.getData();
        this.getMock();
        this.l_serviceImpl = new WEB3MarginSwapMarginServiceImplForTest();
        this.l_request = new WEB3MarginSwapMarginConfirmRequestForTest();
        this.l_submitRequest = new WEB3MarginSwapMarginCompleteRequestForTest();
    }

    public void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_request = null;
        this.l_submitRequest = null;
        super.tearDown();
    }

    /**
     * 「validate機@構預託同意」抛出異常
     * 抛出異常信息：BUSINESS_ERROR_02964
     *
     */
    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testProcessCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_02964,STR_METHOD_NAME));
            
            this.l_serviceImpl.validateOrder(this.l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02964, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate機@構預託同意」不抛出異常
     * 
     *
     */
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testProcessCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_blnIsPass = false;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class },
                    null);
            
            this.l_serviceImpl.validateOrder(this.l_request);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("2000.0",l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate機@構預託同意」正常通過
     *
     */
    public void testValidateOrder_C0003()
    {
        final String STR_METHOD_NAME = "testProcessCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(330304148080000L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setNewListType("7");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            l_blnIsPass = false;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class },
                    null);
            
            this.l_serviceImpl.validateOrder(this.l_request);
            fail();
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("2000.0",l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate機@構預託同意」抛出異常
     * 抛出異常信息：BUSINESS_ERROR_02964
     *
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class },
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_02964,STR_METHOD_NAME));
            
            this.l_serviceImpl.submitOrder(this.l_submitRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02964, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate機@構預託同意」不抛出異常
     *
     *
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.l_blnIsPass = false;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class },
                    null);
            
            this.l_serviceImpl.submitOrder(this.l_submitRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("2000.0",l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「validate機@構預託同意」正常通過
     *
     *
     */
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(330304148080000L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setNewListType("7");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            this.l_blnIsPass = false;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "validateMechanismDepositAgree", new Class[]
                    { SubAccount.class },
                    null);
            
            this.l_serviceImpl.submitOrder(this.l_submitRequest);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals("2000.0",l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void getData()
    {
        final String STR_METHOD_NAME = "getData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccount = TestDBUtility.getMainAccountRow();
            l_mainAccount.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccount);
            
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getEqtypeContractRow());
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());
            
            Calendar l_ca = Calendar.getInstance();
            l_ca.set(2007,13,11,14,47,25);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_ca.getTime());
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams =TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(330304148080000L);
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setNewListType("9");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);
            
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(l_ca.getTimeInMillis()));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getMock()
    {
        final String STR_METHOD_NAME = "getMock()";
        try
        {
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeFinObjectManager",
                "getMarket",
                new Class[]{ long.class },
                l_market);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    private class WEB3MarginSwapMarginConfirmRequestForTest extends WEB3MarginSwapMarginConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {

        }
    }
    
    private class WEB3MarginSwapMarginServiceImplForTest extends WEB3MarginSwapMarginServiceImpl
    {
        public WEB3GentradeSubAccount getSubAccount() throws WEB3BusinessLayerException, WEB3SystemLayerException
        {
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END);
                fail();
            }
            return l_subAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
        
        public WEB3MarginSwapMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        {
            return new WEB3MarginSwapMarginRequestAdapterForTest();
        }
        
        public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
                WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
                WEB3MarginSwapMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException
        {
            EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys = new EqTypeSettleContractOrderEntry[1];
            l_eqTypeSettleContractOrderEntrys[0] = new EqTypeSettleContractOrderEntry(2134566345L,2000);
            return l_eqTypeSettleContractOrderEntrys;
        }
        
        public void validateSwapContractOrder(WEB3GentradeSubAccount l_subAccount,
                WEB3MarginSwapContractOrderSpec l_orderSpec, WEB3MarginSwapMarginRequestAdapter l_requestAdaptor)
                throws WEB3BaseException
        {

        }
        
        protected double getEstimatedSwapPrice(EqTypeSettleContractOrderEntry[] l_entrys, double l_dblQuantity,
                WEB3MarginSwapMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException
        {
            if(!l_blnIsPass)
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02281,"",String.valueOf(l_requestAdaptor.getOrderQuantity()));
            }
            return 0;
        }
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(long l_lngAccountId, long l_lngSubAccountId) throws DataQueryException, DataNetworkException
        {
            super(l_lngAccountId, l_lngSubAccountId);
        }
        
    }
    
    private class WEB3MarginSwapMarginRequestAdapterForTest extends WEB3MarginSwapMarginRequestAdapter
    {
        public WEB3MarginSwapMarginRequestAdapterForTest()
        {
            
        }
        
        public WEB3EquityContract getContract() throws WEB3BaseException
        {
            WEB3EquityContract l_contract = null;
            try
            {
                l_contract = new WEB3EquityContractForTest(2134566345L);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END);
                fail();
            }
            return l_contract;
        }
        
        public double getOrderQuantity() 
        {
            return 2000;
        }
    }
    
    private class WEB3EquityContractForTest extends WEB3EquityContract
    {

        public WEB3EquityContractForTest(long l_lngContractId) throws DataQueryException, DataNetworkException
        {
            super(l_lngContractId);
        }
        
        public TradedProduct getTradedProduct()
        {
            WEB3EquityTradedProduct l_equityTradedProduct = null;
            try
            {
                l_equityTradedProduct = new WEB3EquityTradedProduct(TestDBUtility.getTradedProductRow());
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END);
                fail();
            }
            return l_equityTradedProduct;
        }
        
    }
    
    private class WEB3MarginSwapMarginCompleteRequestForTest extends WEB3MarginSwapMarginCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            
        }
    }

}
@
