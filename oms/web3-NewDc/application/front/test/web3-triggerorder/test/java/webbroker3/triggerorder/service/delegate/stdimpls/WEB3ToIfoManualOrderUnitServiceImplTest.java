head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToIfoManualOrderUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToIfoManualOrderUnitServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/04 金傑(中訊) 対応モデルNo.237
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchForMock;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.stdimpls.WEB3IfoQuoteDataImplForMock;
import webbroker3.triggerorder.message.WEB3FuturesOptionsManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToIfoManualOrderUnitServiceImplTest extends TestBaseForMock
{

    private WEB3ToIfoManualOrderUnitServiceImpl l_serviceImpl = null;
    
    private String l_strProductType = null;

    private String l_strTriggerOrderType = null;

    private String l_strOrderId = null;

    private boolean l_blnIsUpdated;

    private Long l_lngSubmitterLoginId = null;

    private String l_strSubmitnotifyType = null;
    
    private WEB3FuturesOptionsManualUnit l_manualUnit = null;
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToIfoManualOrderUnitServiceImplTest.class);
    
    public WEB3ToIfoManualOrderUnitServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.deleteAllTabel();
        this.initData();
        this.setMockMethod();
        this.l_serviceImpl = new WEB3ToIfoManualOrderUnitServiceImplForTest();
        this.l_strOrderId = "1005";
        this.l_strProductType = "01";
    }

    protected void tearDown() throws Exception
    {
        super.checkMethodValue();
        this.l_serviceImpl = null;
        super.tearDown();
    }
    
    /**
     * is立会時間帯がtrueを返却した場合
     * 手動発注エラーコード = "99"
     */
    public void testExecManualOrder_C0001()
    {
        final String STR_METHOD_NAME = "testExecManualOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = 
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setSubmitMarketTrigger("0");
            l_clendarContext.setOrderAcceptProduct("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            this.l_manualUnit = this.l_serviceImpl.execManualOrder
                (this.l_strProductType,
                 this.l_strTriggerOrderType,
                 this.l_strOrderId,
                 this.l_blnIsUpdated,
                 this.l_lngSubmitterLoginId,
                 this.l_strSubmitnotifyType);

            // その他エラー
            assertEquals("99",l_manualUnit.manualOrderErrorCode);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", 
                    new Class[]{long.class});
            assertEquals(1005L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch", 
                    new Class[]{long.class});
            assertEquals(33381,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
        }
        catch(WEB3BaseException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is立会時間帯がtrueを返却した場合
     * 先物OP手動発注Unit.注文期限区分 = 1
     * 先物OP手動発注Unit.注文有効期限 = null
     * 立会区分 = "1"
     */
    public void testExecManualOrder_C0002()
    {
        final String STR_METHOD_NAME = "testExecManualOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.setMockForExecManualOrder();
            this.l_manualUnit = this.l_serviceImpl.execManualOrder
                (this.l_strProductType,
                 this.l_strTriggerOrderType,
                 this.l_strOrderId,
                 this.l_blnIsUpdated,
                 this.l_lngSubmitterLoginId,
                 this.l_strSubmitnotifyType);


            assertEquals("1",this.l_manualUnit.expirationDateType);
            assertNull(this.l_manualUnit.expirationDate);
            assertEquals("1",this.l_manualUnit.sessionType);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", 
                    new Class[]{long.class});
            assertEquals(1005L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch", 
                    new Class[]{long.class});
            assertEquals(33381,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[]{long.class});
            assertEquals(101001010010L,((Long)l_paramsValue3.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getQuote", 
                    new Class[]{TradedProduct.class, RealType.class});
            assertEquals(null,((TradedProduct)l_paramsValue4.getFirstCalled()[0]));
            assertEquals(2,((RealType)l_paramsValue4.getFirstCalled()[1]).intValue());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", 
                    new Class[]{long.class});
            assertEquals(1005,((Long)l_paramsValue5.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount", 
                    new Class[]{long.class, SubAccountTypeEnum.class});
            assertEquals(101001010010L,((Long)l_paramsValue6.getFirstCalled()[0]).longValue());
            assertEquals(7,((SubAccountTypeEnum)l_paramsValue6.getFirstCalled()[1]).intValue());
            
            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[]{long.class});
            assertEquals(1002,((Long)l_paramsValue7.getFirstCalled()[0]).longValue());
        }
        catch(WEB3BaseException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * is立会時間帯がtrueを返却した場合
     * 先物OP手動発注Unit.注文期限区分 = 2(出来るまで注文)
     * 先物OP手動発注Unit.注文有効期限 = 注文単位.注文失効日
     * 立会区分 = "1"
     */
    public void testExecManualOrder_C0003()
    {
        final String STR_METHOD_NAME = "testExecManualOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.setMockForExecManualOrder();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", new Class[]
                    { IfoOrderUnit.class },
                    new Boolean(true));
                    
            this.l_manualUnit = this.l_serviceImpl.execManualOrder
                (this.l_strProductType,
                 this.l_strTriggerOrderType,
                 this.l_strOrderId,
                 this.l_blnIsUpdated,
                 this.l_lngSubmitterLoginId,
                 this.l_strSubmitnotifyType);

            Calendar ca =  Calendar.getInstance();
            ca.set(2007,7-1,5);
            
            Date date = ca.getTime();
            
            assertEquals("2",this.l_manualUnit.expirationDateType);
            assertEquals(0,WEB3DateUtility.compareToDay(date,this.l_manualUnit.expirationDate));
            assertEquals("1",this.l_manualUnit.sessionType);
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrderUnits", 
                    new Class[]{long.class});
            assertEquals(1005L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getBranch", 
                    new Class[]{long.class});
            assertEquals(33381,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[]{long.class});
            assertEquals(101001010010L,((Long)l_paramsValue3.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getQuote", 
                    new Class[]{TradedProduct.class, RealType.class});
            assertEquals(null,((TradedProduct)l_paramsValue4.getFirstCalled()[0]));
            assertEquals(2,((RealType)l_paramsValue4.getFirstCalled()[1]).intValue());
            
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", 
                    new Class[]{long.class});
            assertEquals(1005,((Long)l_paramsValue5.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount", 
                    new Class[]{long.class, SubAccountTypeEnum.class});
            assertEquals(101001010010L,((Long)l_paramsValue6.getFirstCalled()[0]).longValue());
            assertEquals(7,((SubAccountTypeEnum)l_paramsValue6.getFirstCalled()[1]).intValue());
            
            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[]{long.class});
            assertEquals(1002,((Long)l_paramsValue7.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue8 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "isCarriedOrderUnit", 
                    new Class[]{IfoOrderUnit.class});
            assertEquals(IfoContractOpenOrderUnitImplForTest.class,l_paramsValue8.getFirstCalled()[0].getClass());
        }
        catch(WEB3BaseException l_web3BusinessLayerException)
        {
            log.error("", l_web3BusinessLayerException);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1005);
            l_ifoOrderUnitParams.setProductId(1006160060008L);
            l_ifoOrderUnitParams.setSessionType("1");
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,7-1,5);
            
            Date date = ca.getTime();
            l_ifoOrderUnitParams.setExpirationDate(new Timestamp(date.getTime()));
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060008L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060008L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.insertWithDel(TestDBUtility.getMarketRow());

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void deleteAllTabel()
    {
        final String STR_METHOD_NAME = "deleteAllTabel()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            // MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        { 
            IfoOrderUnit[] l_IfoOrderUnits = new IfoOrderUnit[1];
            l_IfoOrderUnits[0] = new IfoContractOpenOrderUnitImplForTest(1005);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
              "getOrderUnits", 
              new Class[]{ long.class },
              l_IfoOrderUnits);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("0D");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.gentrade.WEB3GentradeBranch", 
              "getInstitution", 
               new Class[]{},
               l_institution);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockForExecManualOrder()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getQuote", new Class[]{ TradedProduct.class, RealType.class },
                    new WEB3IfoQuoteDataImplForMock());
            
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[1];
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", 
                    new Class[]{ long.class },
                    l_contractUnits);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, SubAccountTypeEnum.class },
                    new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow()));
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class IfoContractOpenOrderUnitImplForTest extends IfoContractOpenOrderUnitImpl
    {

        public IfoContractOpenOrderUnitImplForTest(long arg0) throws DataQueryException, DataNetworkException
        {
            super(arg0);
        }
        
        public Product getProduct()
        {
            WEB3IfoProductImplForTest l_test = null;
            try
            {

                l_test = new WEB3IfoProductImplForTest(1006160060008L);
            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_test;
            
        }
        
        public TradedProduct getTradedProduct()
        {
            return null;
        }
        
    }
    
    private class WEB3IfoProductImplForTest extends WEB3IfoProductImpl
    {


        public WEB3IfoProductImplForTest(long l_lngProductID) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_lngProductID);
        }

        public String getProductCode()
        {
            return "5725";
        }
    }
    
    private class WEB3ToIfoManualOrderUnitServiceImplForTest extends WEB3ToIfoManualOrderUnitServiceImpl
    {
        public void setTradingCalendarContext(String l_strInstitutionCode, String l_strBranchCode,
                String l_strProductCode, String l_strOrderAccProduct, String l_strOrderAcceptTransaction)
                throws WEB3BaseException
        {
            log.debug("WEB3ToIfoManualOrderUnitServiceImplForTest.setTradingCalendarContext");
        }
        
        public WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
                IfoOrderUnit l_orderUnit, 
                SubAccount l_subAccount) throws WEB3BaseException
        {
            return new WEB3ManualCommissionInfoUnit();
        }
    }
}
@
