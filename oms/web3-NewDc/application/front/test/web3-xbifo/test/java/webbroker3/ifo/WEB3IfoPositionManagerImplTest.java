head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoPositionManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OPポジションマネージャTest(WEB3IfoPositionManagerImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/13 張騰宇 (中訊) 新規作成
Revision History : 2007/06/24 金傑 モデル723、727
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoPositionManagerImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoPositionManagerImplTest.class);

    public WEB3IfoPositionManagerImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        TradingCalendarDetails tradingCalendarDetails =
            new WEB3GentradeTradingClendarDetailsImpl();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getTradingCalendarDetails",
                new Class[] {long.class},
                tradingCalendarDetails);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                WEB3DateUtility.getDate("20070619","yyyyMMdd"));

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * 先物OP残高）createOP決済済建玉照会明細
     */
    public void testCreateOptionSettledContractInquiryDetailsCase1()
    {
        final String STR_METHOD_NAME = "testCreateOptionSettledContractInquiryDetailsCase1()";
        log.entering(STR_METHOD_NAME);


        
        
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate(null);
        
        Calendar l_date =  Calendar.getInstance();
        Date l_dat = l_date.getTime();
        
//        WEB3GentradeBizDate l_bizDate =
//            new WEB3GentradeBizDate(new Timestamp(l_dat.getTime()));
//        try
//        {
//            l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_bizDate.roll(1), "yyyyMMdd"));
//        }
//        catch (WEB3SystemLayerException e)
//        {
//            log.error("", e);
//            fail();
//        }
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            TestDBUtility.deleteAll(IfoFinTransactionParams.TYPE);
            
            Date l_dat0 = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
            Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
            Date l_dat2 = WEB3DateUtility.getDate("20070103", "yyyyMMdd");
            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
            l_ifoFinTransactionParams.setContractId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setFinTransactionCateg(FinTransactionCateg.EQTYPE_IDX_OPTIONS_CLOSE);
            l_ifoFinTransactionParams.setFinTransactionTimestamp(Calendar.getInstance().getTime());
            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
            l_ifoFinTransactionParams.setNetAmount(9);
            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
            l_ifoFinTransactionParams.setQuantity(21);
            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat0);
            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
            l_ifoFinTransactionParams.setOrderExecutionId(123);
            l_ifoFinTransactionParams.setSubAccountId(2);
            l_ifoFinTransactionParams.setAccountId(1);
            l_ifoFinTransactionParams.setMarketId(3);
            l_ifoFinTransactionParams.setProductId(4);
            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.DEBIT);
            l_ifoFinTransactionParams.setPrice(1);
            l_ifoFinTransactionParams.setOrderUnitId(3333);
            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
            //l_ifoFinTransactionParams.setCloseDate(l_dat);
            l_ifoFinTransactionParams.setImportedSetupFee(56);
            l_ifoFinTransactionParams.setImportedSetupFeeTax(98544);
            l_ifoFinTransactionParams.setDeliveryDate(l_dat2);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            ArrayList l_list = new ArrayList();
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(1001L);
            
            l_positionManager.createOptionSettledContractInquiryDetails(l_list, l_ifoContractImpl);
            WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();
            l_contractReferenceUnit = (WEB3OptionsContractReferenceUnit)l_list.get(0);
            assertEquals(l_contractReferenceUnit.sessionType, "1");
            assertEquals("98600", "" + l_contractReferenceUnit.contractCommission);
            assertEquals("9", "" + l_contractReferenceUnit.income);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * （先物OP残高）createOP未決済建玉照会明細
     */
    public void testCreateOptionUnSettledContractInquiryDetailsCase1()
    {
        final String STR_METHOD_NAME = "testCreateOptionUnSettledContractInquiryDetailsCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType(null);
        l_ifoContractParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setQuantity(0);
        l_ifoContractParams.setSetupFee(2000);
        IfoLockedContractDetailsParams l_IfoLockedContractDetailsParams = TestDBUtility.getIfoLockedContractDetailsRow();
        l_IfoLockedContractDetailsParams.setLockedQuantity(10);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setUnitSize(1);
        l_ifoTradedProductParams.setValidForBizDate(null);

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(IfoLockedContractDetailsParams.TYPE);
            TestDBUtility.insertWithDel(l_IfoLockedContractDetailsParams);
            TestDBUtility.commit();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            ArrayList l_list = new ArrayList();
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(1001L);
            
            l_positionManager.createOptionUnSettledContractInquiryDetails(l_list, l_ifoContractImpl);
            
            WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();
            l_contractReferenceUnit = (WEB3OptionsContractReferenceUnit)l_list.get(0);
            assertNull(l_contractReferenceUnit.sessionType);
            assertEquals("2100", "" + l_contractReferenceUnit.contractCommission);
            assertEquals("37120", "" + l_contractReferenceUnit.income);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * （先物OP残高）createOP決済中建玉照会明細
     */
    public void testCreateOptionSettlingContractInquiryDetailsCase1()
    {
        final String STR_METHOD_NAME = "testCreateOptionSettlingContractInquiryDetailsCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate(null);
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            ArrayList l_list = new ArrayList();
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(1001L);
            
            l_positionManager.createOptionSettlingContractInquiryDetails(l_list, l_ifoContractImpl);
            
            WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();
            l_contractReferenceUnit = (WEB3OptionsContractReferenceUnit)l_list.get(0);
            assertEquals(l_contractReferenceUnit.sessionType, "1");  
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * （先物OP残高）create返済建玉一覧
     */
    public void testCreateSettleContractsCase1()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractsCase1()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.LONG);//[引数.建区分 == ”買建”の場合]
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
//        l_ifoContractParams.setContractPrice(100);/////
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        
        IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams1.setSessionType("1");
        l_ifoContractParams1.setDeliveryDate(WEB3DateUtility.getDate("20050702","yyyyMMdd"));////
        l_ifoContractParams1.setProductId(1006160060005L);
        l_ifoContractParams1.setMarketId(3303L);
        l_ifoContractParams1.setContractType(ContractTypeEnum.LONG);
        l_ifoContractParams1.setContractId(1002);
        l_ifoContractParams1.setAccountId(333812512203L);
        l_ifoContractParams1.setSubAccountId(33381251220301L);
//        l_ifoContractParams1.setContractPrice(100);/////
        l_ifoContractParams1.setCloseDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
        
        IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams2.setSessionType("1");
        l_ifoContractParams2.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));////
        l_ifoContractParams2.setProductId(1006160060005L);
        l_ifoContractParams2.setMarketId(3303L);
        l_ifoContractParams2.setContractType(ContractTypeEnum.LONG);
        l_ifoContractParams2.setContractId(1003);
        l_ifoContractParams2.setAccountId(333812512203L);
        l_ifoContractParams2.setSubAccountId(33381251220301L);
//        l_ifoContractParams2.setContractPrice(100);/////
        l_ifoContractParams2.setCloseDate(WEB3DateUtility.getDate("20040703","yyyyMMdd"));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoContractRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams2);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams1);
           
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits =
                l_positionManager.createSettleContracts(
                l_subAccount,
                ContractTypeEnum.LONG,
                3303L,
                1006160060005L);
            assertEquals(l_contractUnits[0].id, "1003");
            assertEquals(l_contractUnits[1].id, "1002");
            assertEquals(l_contractUnits[2].id, "1001");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSettleContractsCase2()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractsCase2()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);//[引数.建区分 == ”売建”の場合]
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
//        l_ifoContractParams.setContractPrice(100);/////
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        
        IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams1.setSessionType("1");
        l_ifoContractParams1.setDeliveryDate(WEB3DateUtility.getDate("20050702","yyyyMMdd"));////
        l_ifoContractParams1.setProductId(1006160060005L);
        l_ifoContractParams1.setMarketId(3303L);
        l_ifoContractParams1.setContractType(ContractTypeEnum.SHORT);
        l_ifoContractParams1.setContractId(1002);
        l_ifoContractParams1.setAccountId(333812512203L);
        l_ifoContractParams1.setSubAccountId(33381251220301L);
//        l_ifoContractParams1.setContractPrice(100);/////
        l_ifoContractParams1.setCloseDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
        
        IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams2.setSessionType("1");
        l_ifoContractParams2.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));////
        l_ifoContractParams2.setProductId(1006160060005L);
        l_ifoContractParams2.setMarketId(3303L);
        l_ifoContractParams2.setContractType(ContractTypeEnum.SHORT);
        l_ifoContractParams2.setContractId(1003);
        l_ifoContractParams2.setAccountId(333812512203L);
        l_ifoContractParams2.setSubAccountId(33381251220301L);
//        l_ifoContractParams2.setContractPrice(100);/////
        l_ifoContractParams2.setCloseDate(WEB3DateUtility.getDate("20040703","yyyyMMdd"));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoContractRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams2);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams1);
            
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits =
                l_positionManager.createSettleContracts(
                l_subAccount,
                ContractTypeEnum.SHORT,
                3303L,
                1006160060005L);
            assertEquals(l_contractUnits[0].id, "1003");
            assertEquals(l_contractUnits[1].id, "1002");
            assertEquals(l_contractUnits[2].id, "1001");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateSettleContractsCase3()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractsCase3()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
//        l_ifoContractParams.setContractPrice(100);/////
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        
        IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams1.setSessionType("1");
        l_ifoContractParams1.setDeliveryDate(WEB3DateUtility.getDate("20050702","yyyyMMdd"));////
        l_ifoContractParams1.setProductId(1006160060005L);
        l_ifoContractParams1.setMarketId(3303L);
        l_ifoContractParams1.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams1.setContractId(1002);
        l_ifoContractParams1.setAccountId(333812512203L);
        l_ifoContractParams1.setSubAccountId(33381251220301L);
//        l_ifoContractParams1.setContractPrice(100);/////
        l_ifoContractParams1.setCloseDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
        
        IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
        l_ifoContractParams2.setSessionType("1");
        l_ifoContractParams2.setDeliveryDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));////
        l_ifoContractParams2.setProductId(1006160060005L);
        l_ifoContractParams2.setMarketId(3303L);
        l_ifoContractParams2.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams2.setContractId(1003);
        l_ifoContractParams2.setAccountId(333812512203L);
        l_ifoContractParams2.setSubAccountId(33381251220301L);
//        l_ifoContractParams2.setContractPrice(100);/////
        l_ifoContractParams2.setCloseDate(WEB3DateUtility.getDate("20040703","yyyyMMdd"));
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512203L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(IfoContractRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams2);
            TestDBUtility.insertWithDelAndCommit(l_ifoContractParams1);
            
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
           
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            WEB3FuturesOptionsCloseMarginContractUnit[] l_contractUnits =
                l_positionManager.createSettleContracts(
                l_subAccount,
                ContractTypeEnum.UNDEFINED,
                3303L,
                1006160060005L);
            assertEquals(l_contractUnits[0].id, "1002");
            assertEquals(l_contractUnits[1].id, "1001");
            assertEquals(l_contractUnits[2].id, "1003");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  create先物未決済建玉照会明細
     * 「建玉.立会区分」を取得する
     *
     */
    public void testCreateFuturesUnSettledContractInquiryDetails_C0001()
    {
        final String STR_METHOD_NAME = "testCreateFuturesUnSettledContractInquiryDetails_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesUnSettledContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", 
                    new Class[] { long.class, long.class });
            assertEquals(1006160060009L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            assertEquals(0,((Long)l_paramsValue1.getFirstCalled()[1]).longValue());
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            assertEquals("2",l_contractReferenceUnit.sessionType);
            

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateFuturesUnSettledContractInquiryDetails_C0002()
    {
        final String STR_METHOD_NAME = "testCreateFuturesUnSettledContractInquiryDetails_C0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setSessionType("2");
            l_ifoContractParams.setProductId(1006160060009L);
            l_ifoContractParams.setSetupFee(6.1);
            l_ifoContractParams.setSetupFeeTax(2.5);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock2(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesUnSettledContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", 
                    new Class[] { long.class, long.class });
            assertEquals(1006160060009L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            assertEquals(0,((Long)l_paramsValue1.getFirstCalled()[1]).longValue());
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            assertEquals("2",l_contractReferenceUnit.sessionType);
            assertEquals("2",l_contractReferenceUnit.contractCommission);
            assertEquals("5.9",l_contractReferenceUnit.incomeCost);
            

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  create先物決済済建玉照会明細
     * 「建玉.立会区分」を取得する
     *
     */
    public void testCreateFuturesSettledContractInquiryDetails_C0001()
    {
        final String STR_METHOD_NAME = "testCreateFuturesSettledContractInquiryDetails_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesSettledContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", 
                    new Class[] { long.class, long.class });
            assertEquals(1006160060009L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            assertEquals(0,((Long)l_paramsValue1.getFirstCalled()[1]).longValue());
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            assertEquals("2",l_contractReferenceUnit.sessionType);
            

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateFuturesSettledContractInquiryDetails_C0002()
    {
        final String STR_METHOD_NAME = "testCreateFuturesSettledContractInquiryDetails_C0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setNetAmount(1.3d);
            l_ifoFinTransactionParams.setImportedSetupFee(2.2d);
            l_ifoFinTransactionParams.setImportedSetupFeeTax(4.4d);
            List l_lisTransactions = new ArrayList();
            l_lisTransactions.add(l_ifoFinTransactionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoFinTransactionManagerImpl",
                "getTransactions",
                new Class[]{long.class, FinTransactionCateg.class, Date.class},
                l_lisTransactions);
            
            
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesSettledContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", 
                    new Class[] { long.class, long.class });
            assertEquals(1006160060009L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            assertEquals(0,((Long)l_paramsValue1.getFirstCalled()[1]).longValue());
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            assertEquals("2",l_contractReferenceUnit.sessionType);
            
            assertEquals("1.3",l_contractReferenceUnit.income);
            assertEquals("6.6",l_contractReferenceUnit.contractCommission);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  create先物決済中建玉照会明細
     * 「建玉.立会区分」を取得する
     *
     */
    public void testCreateFuturesSettlingContractInquiryDetails_C0001()
    {
        final String STR_METHOD_NAME = "testCreateFuturesSettlingContractInquiryDetails_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesSettlingContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", 
                    new Class[] { long.class, long.class });
            assertEquals(1006160060009L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            assertEquals(0,((Long)l_paramsValue1.getFirstCalled()[1]).longValue());
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit = (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            assertEquals("2",l_contractReferenceUnit.sessionType);
            

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateFuturesSettlingContractInquiryDetails_C0002()
    {
        final String STR_METHOD_NAME = "testCreateFuturesSettlingContractInquiryDetails_C0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest2();
            this.initData();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            
            
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock2(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesSettlingContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit =
                (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            
            assertEquals("6.6", l_contractReferenceUnit.contractCommission);
            assertEquals("1.3", l_contractReferenceUnit.incomeCost);
            

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateFuturesSettlingContractInquiryDetails_C0003()
    {
        final String STR_METHOD_NAME = "testCreateFuturesSettlingContractInquiryDetails_C0003()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest3();
            this.initData();
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ArrayList l_lisContractInquiryDetails = new ArrayList();
            
            
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock2(1005);
            
            WEB3IfoTradedProductImplForMock l_ifoTradedProductImplForMock = new WEB3IfoTradedProductImplForMock(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_ifoTradedProductImplForMock);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            l_positionManager.createFuturesSettlingContractInquiryDetails(
                l_lisContractInquiryDetails,l_ifoContract);
            
            WEB3FuturesContractReferenceUnit l_contractReferenceUnit =
                (WEB3FuturesContractReferenceUnit)l_lisContractInquiryDetails.get(0);
            
            assertEquals("15.8", l_contractReferenceUnit.income);
            

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *  create先物OP決済中残高照会明細
     *  残高照会明細.立会区分   ＝　@先物OP建玉.立会区分
     *
     */
    public void testCreateIfoSettlingBalanceReferenceDetailUnit_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoSettlingBalanceReferenceDetailUnit_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = l_positionManager.createIfoSettlingBalanceReferenceDetailUnit(l_subAccount,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class });
            assertEquals(12345,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());          
            assertEquals("2",l_referenceDetailUnit.sessionType);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateIfoSettlingBalanceReferenceDetailUnit_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoSettlingBalanceReferenceDetailUnit_C0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImpl();
            this.initData();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setSessionType("2");
            l_ifoContractParams.setProductId(1006160060009L);
            l_ifoContractParams.setSetupFee(6.6);
            l_ifoContractParams.setSetupFeeTax(2.5);
            l_ifoContractParams.setQuantity(10);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            IfoLockedContractDetailsParams l_IfoLockedContractDetailsParams = TestDBUtility.getIfoLockedContractDetailsRow();
            l_IfoLockedContractDetailsParams.setContractId(1005);
            l_IfoLockedContractDetailsParams.setLockedQuantity(10);
            TestDBUtility.insertWithDel(l_IfoLockedContractDetailsParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecRow =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecRow.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoClosingContractSpecRow.setExecutedQuantity(120);
            l_ifoClosingContractSpecRow.setContractId(1005);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecRow);
            TestDBUtility.commit();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock2(1005);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = l_positionManager.createIfoSettlingBalanceReferenceDetailUnit(l_subAccount,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class });
            assertEquals(3306,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());          
            assertEquals("2",l_referenceDetailUnit.sessionType);
            assertEquals("1.3",l_referenceDetailUnit.incomeCost);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  create先物OP未決済残高照会明細
     *  残高照会明細.立会区分   ＝　@先物OP建玉.立会区分
     *
     */
    public void testCreateIfoUnSettledBalanceReferenceDetailUnit_C0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoUnSettledBalanceReferenceDetailUnit_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = l_positionManager.createIfoUnSettledBalanceReferenceDetailUnit(l_subAccount,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class });
            assertEquals(12345,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());          
            assertEquals("2",l_referenceDetailUnit.sessionType);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateIfoUnSettledBalanceReferenceDetailUnit_C0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoUnSettledBalanceReferenceDetailUnit_C0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest();
            this.initData();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setSessionType("2");
            l_ifoContractParams.setProductId(1006160060009L);
            l_ifoContractParams.setSetupFee(6.6);
            l_ifoContractParams.setSetupFeeTax(2.5);
            l_ifoContractParams.setQuantity(10);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock2(1005);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = l_positionManager.createIfoUnSettledBalanceReferenceDetailUnit(l_subAccount,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class });
            assertEquals(12345,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());          
            assertEquals("2",l_referenceDetailUnit.sessionType);
            assertEquals("5.4",l_referenceDetailUnit.incomeCost);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateIfoUnSettledBalanceReferenceDetailUnit_C0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoUnSettledBalanceReferenceDetailUnit_C0003()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            
            WEB3IfoPositionManagerImpl l_positionManager = new WEB3IfoPositionManagerImplForTest4();
            this.initData();
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImplForMock(1005);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class },
                    l_market);
                    
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            WEB3FuturesOptionsDetailUnit l_referenceDetailUnit = l_positionManager.createIfoUnSettledBalanceReferenceDetailUnit(l_subAccount,l_ifoContract);
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeFinObjectManager", 
                    "getMarket", 
                    new Class[] { long.class });
            assertEquals(12345,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());          
            assertEquals("2",l_referenceDetailUnit.sessionType);
            assertNull(l_referenceDetailUnit.incomeCost);
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(l_web3BaseException.getMessage(), l_web3BaseException);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get決済状態
     * 建玉元数量==0、かつ建玉数量==0の場合、-2（新規建約定取消）を返却する。
     */
    public void testGetSettlementStatusCase0001()
    {
        final String STR_METHOD_NAME = "testGetSettlementStatusCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setOriginalQuantity(0);
            l_ifoContractParams.setQuantity(0);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
            
            assertEquals(-2, l_managerImpl.getSettlementStatus(l_impl));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get決済状態
     * 更新日付(yyyymmdd)<システム日付(yyyymmdd)で、かつ建玉数量==0の場合、-1（前日以前決済済）を返却する。
     */
    public void testGetSettlementStatusCase0002()
    {
        final String STR_METHOD_NAME = "testGetSettlementStatusCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setOriginalQuantity(1);
            l_ifoContractParams.setQuantity(0);
            l_ifoContractParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070120", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
            
            assertEquals(-1, l_managerImpl.getSettlementStatus(l_impl));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get決済状態
     *  更新日付(yyyymmdd)==システム日付(yyyymmdd)で、かつ建玉数量==0の場合、0（決済済）を返却する
     */
    public void testGetSettlementStatusCase0003()
    {
        final String STR_METHOD_NAME = "testGetSettlementStatusCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setOriginalQuantity(1);
            l_ifoContractParams.setQuantity(0);
            l_ifoContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
            
            assertEquals(0, l_managerImpl.getSettlementStatus(l_impl));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get決済状態
     *   決済注文中数量≧1の場合
     *    当日返済約定数量==0の場合
     *        2-1-1. 決済注文中数量==建玉数量の場合、2（決済中）を返却する。
     */
    public void testGetSettlementStatusCase0004()
    {
        final String STR_METHOD_NAME = "testGetSettlementStatusCase0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setOriginalQuantity(0);
            l_ifoContractParams.setQuantity(100);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_IfoLockedContractDetailsRow =
                TestDBUtility.getIfoLockedContractDetailsRow();
            TestDBUtility.insertWithDel(l_IfoLockedContractDetailsRow);
            
            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
            
            assertEquals(2, l_managerImpl.getSettlementStatus(l_impl));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get決済状態
     *   決済注文中数量≧1の場合
     *    2. 当日返済約定数量≧1の場合
     *      2-1. 決済注文中数量==建玉数量の場合、6（決済済と決済中）を返却する。
     */
    public void testGetSettlementStatusCase0005()
    {
        final String STR_METHOD_NAME = "testGetSettlementStatusCase0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecRow =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecRow.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoClosingContractSpecRow.setExecutedQuantity(120);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecRow);

            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(100);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_IfoLockedContractDetailsRow =
                TestDBUtility.getIfoLockedContractDetailsRow();
            TestDBUtility.insertWithDel(l_IfoLockedContractDetailsRow);

            WEB3IfoContractImpl l_impl = new WEB3IfoContractImpl(l_ifoContractParams);
            
            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
            
            assertEquals(6, l_managerImpl.getSettlementStatus(l_impl));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
//    //連續注文對應
//    //get建玉ListBy注文単位
//    public void testGetContractListByOrderUnitCase1()
//    {
//        final String STR_METHOD_NAME = "testGetContractListByOrderUnitCase1()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {           
//
//            Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
//            Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
//            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
//            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
//            l_ifoFinTransactionParams.setFinTransactionCateg(new FinTransactionCateg(0, "Categ"));
//            l_ifoFinTransactionParams.setFinTransactionTimestamp(l_dat);
//            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_ifoFinTransactionParams.setNetAmount(9);
//            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoFinTransactionParams.setQuantity(21);
//            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat);
//            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
//            l_ifoFinTransactionParams.setOrderExecutionId(123);
//            l_ifoFinTransactionParams.setSubAccountId(2);
//            l_ifoFinTransactionParams.setAccountId(1);
//            l_ifoFinTransactionParams.setMarketId(3);
//            l_ifoFinTransactionParams.setProductId(4);
//            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.ASSET_IN);
//            l_ifoFinTransactionParams.setPrice(1.0);
//            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
//            //l_ifoFinTransactionParams.setCloseDate(l_dat);
//            l_ifoFinTransactionParams.setDeliveryDate(l_dat1);
//            l_ifoFinTransactionParams.setContractId(123456L);
//            l_ifoFinTransactionParams.setOrderId(123L);
//            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
//            
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            l_ifoContractParams.setSubAccountId(2);
//            l_ifoContractParams.setAccountId(1);
//            l_ifoContractParams.setMarketId(3);
//            l_ifoContractParams.setProductId(4);
//            l_ifoContractParams.setContractType(new ContractTypeEnum(1, "abc"));
//            l_ifoContractParams.setContractPrice(1);
//            l_ifoContractParams.setOpenDate(l_dat);
//            l_ifoContractParams.setCloseDate(l_dat);
//            l_ifoContractParams.setDeliveryDate(l_dat1);
//            l_ifoContractParams.setContractId(123456);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
//            
//            List l_lisResults = l_managerImpl.getContractListByOrderUnit(123);
//            IfoContractParams l_ifoContractParams1 = (IfoContractParams)l_lisResults.get(0);
//            assertEquals(l_ifoContractParams1.getContractId(), 123456);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
//    
//    //    get取引勘定明細ListBy注文単位Plus建玉 
//    public void testGetTransactionsListByOrderUnitPlusContractCase1()
//    {
//        final String STR_METHOD_NAME = "testGetContractListByOrderUnitCase1()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {            
//            Date l_dat = WEB3DateUtility.getDate("20070101", "yyyyMMdd");
//            Date l_dat1 = WEB3DateUtility.getDate("20070102", "yyyyMMdd");
//            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
//            IfoFinTransactionParams l_ifoFinTransactionParams = new  IfoFinTransactionParams();
//            l_ifoFinTransactionParams.setFinTransactionCateg(new FinTransactionCateg(0, "Categ"));
//            l_ifoFinTransactionParams.setFinTransactionTimestamp(l_dat);
//            l_ifoFinTransactionParams.setTaxType(TaxTypeEnum.NORMAL);
//            l_ifoFinTransactionParams.setNetAmount(9);
//            l_ifoFinTransactionParams.setProductType(ProductTypeEnum.IFO);
//            l_ifoFinTransactionParams.setQuantity(21);
//            l_ifoFinTransactionParams.setCreatedTimestamp(l_dat);
//            l_ifoFinTransactionParams.setLastUpdatedTimestamp(l_dat1);
//            l_ifoFinTransactionParams.setOrderExecutionId(123);
//            l_ifoFinTransactionParams.setSubAccountId(2);
//            l_ifoFinTransactionParams.setAccountId(1);
//            l_ifoFinTransactionParams.setMarketId(3);
//            l_ifoFinTransactionParams.setProductId(4);
//            l_ifoFinTransactionParams.setFinTransactionType(FinTransactionType.ASSET_IN);
//            l_ifoFinTransactionParams.setPrice(1.0);
//            //l_ifoFinTransactionParams.setExecTimestamp(l_dat);
//            //l_ifoFinTransactionParams.setCloseDate(l_dat);
//            l_ifoFinTransactionParams.setDeliveryDate(l_dat1);
//            l_ifoFinTransactionParams.setContractId(123456L);
//            l_ifoFinTransactionParams.setOrderUnitId(123L);
//            l_ifoFinTransactionParams.setOrderId(123L);
//            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
//            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
//            
//            TestDBUtility.deleteAll(IfoContractRow.TYPE);
//            
//            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
//            l_ifoContractParams.setSubAccountId(2);
//            l_ifoContractParams.setAccountId(1);
//            l_ifoContractParams.setMarketId(3);
//            l_ifoContractParams.setProductId(4);
//            l_ifoContractParams.setContractType(new ContractTypeEnum(1, "abc"));
//            l_ifoContractParams.setContractPrice(1);
//            l_ifoContractParams.setOpenDate(l_dat);
//            l_ifoContractParams.setCloseDate(l_dat);
//            l_ifoContractParams.setDeliveryDate(l_dat1);
//            l_ifoContractParams.setContractId(123456);
//            TestDBUtility.insertWithDel(l_ifoContractParams);
//            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
//            
//            List l_lisResults = l_managerImpl.getTransactionsListByOrderUnitPlusContract(123, 123456);
//            IfoFinTransactionParams l_ifoFinTransactionParams1 = (IfoFinTransactionParams)l_lisResults.get(0);
//            assertEquals(l_ifoFinTransactionParams1.getOrderId(), 123);
//            assertEquals(l_ifoFinTransactionParams1.getContractId(), 123456);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_START + STR_METHOD_NAME);
//    }
    
    /**
     * get当日返済約定数量
     * 返済指定情報.get返済約定数量()の値を加算していく
     */
    public void testGetDayClosingContractExecutionCntCase0001()
    {
        final String STR_METHOD_NAME = "testGetDayClosingContractExecutionCntCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            IfoClosingContractSpecParams l_ifoClosingContractSpecRow =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecRow.setClosingContractSpecId(1111);
            l_ifoClosingContractSpecRow.setContractId(1001);
            l_ifoClosingContractSpecRow.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoClosingContractSpecRow.setExecutedQuantity(100);
            l_ifoClosingContractSpecRow.setOrderUnitId(123);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecRow);

            IfoClosingContractSpecParams l_ifoClosingContractSpecRow1 =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecRow1.setClosingContractSpecId(2222);
            l_ifoClosingContractSpecRow1.setContractId(1001);
            l_ifoClosingContractSpecRow1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoClosingContractSpecRow1.setExecutedQuantity(120);
            l_ifoClosingContractSpecRow1.setOrderUnitId(456);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecRow1);
            
            IfoClosingContractSpecParams l_ifoClosingContractSpecRow2 =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecRow2.setClosingContractSpecId(3333);
            l_ifoClosingContractSpecRow2.setContractId(1001);
            l_ifoClosingContractSpecRow2.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_ifoClosingContractSpecRow2.setExecutedQuantity(150);
            l_ifoClosingContractSpecRow2.setOrderUnitId(789);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecRow2);

            WEB3IfoPositionManagerImpl l_managerImpl = new WEB3IfoPositionManagerImpl();
            
            long l_lngContractID = 1001;
            Date l_datLastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            double l_dblDayClosingContractExecutionCnt =
                l_managerImpl.getDayClosingContractExecutionCnt(l_lngContractID, l_datLastUpdatedTimestamp);
            assertEquals("370.0", l_dblDayClosingContractExecutionCnt + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * createOP未決済建玉照会明細
     * 建手数料＝(未決済＋決済中の建手数料)－決済中の建手数料
     * 建手数料消費税＝(未決済＋決済中の建手数料消費税)－決済中の建手数料消費税
     * 建手数料＋建手数料消費税
     *
     */
    public void testCreateOptionUnSettledContractInquiryDetailsCase0001()
    {
        final String STR_METHOD_NAME = "testCreateOptionUnSettledContractInquiryDetailsCase0001()";
        log.entering(STR_METHOD_NAME);
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType(null);
        l_ifoContractParams.setDeliveryDate(GtlUtils.getSystemTimestamp());
        l_ifoContractParams.setProductId(1006160060005L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setQuantity(20);
        l_ifoContractParams.setSetupFee(310);
        l_ifoContractParams.setSetupFeeTax(20);
        l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
        l_ifoContractParams.setContractPrice(5);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setUnitSize(10);
        l_ifoTradedProductParams.setValidForBizDate(null);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        try
        {
            
            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_IfoLockedContractDetailsRow =
                TestDBUtility.getIfoLockedContractDetailsRow();
            l_IfoLockedContractDetailsRow.setLockedQuantity(10);
            TestDBUtility.insertWithDel(l_IfoLockedContractDetailsRow);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getTradedProduct",
                    new Class[]{ long.class, long.class},
                    l_tradedProduct);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
            ArrayList l_list = new ArrayList();
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(1001L);
            
            l_positionManager.createOptionUnSettledContractInquiryDetails(l_list, l_ifoContractImpl);
            
            WEB3OptionsContractReferenceUnit l_contractReferenceUnit = new WEB3OptionsContractReferenceUnit();
            l_contractReferenceUnit = (WEB3OptionsContractReferenceUnit)l_list.get(0);

            assertEquals("165", l_contractReferenceUnit.contractCommission);
            assertEquals("135", l_contractReferenceUnit.incomeCost);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setSessionType("2");
            l_ifoContractParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setValidForBizDate(null);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,21);
            
            Date date = ca.getTime();
            l_IfoTradedProductParams.setLastTradingDate(date);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);

            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    private class WEB3IfoPositionManagerImplForTest extends WEB3IfoPositionManagerImpl
    {
        public double getContractCurrentPrice(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
        {
            return 0;
        }
        
        public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID) throws WEB3BaseException
        {
            IfoClosingContractSpec[] l_ifoClosingContractSpecs = new IfoClosingContractSpec[0];
            return l_ifoClosingContractSpecs;
        }
        
        public WEB3IfoProductQuote getContractCurrentInfo(WEB3GentradeSubAccount l_subAccount,
                WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
        {
            WEB3IfoProductQuote l_currentInfo = new WEB3IfoProductQuote();
            return l_currentInfo;
        }
    }
    
    private class WEB3IfoPositionManagerImplForTest2 extends WEB3IfoPositionManagerImpl
    {
        public double getContractCurrentPrice(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
        {
            return 2.0;
        }
        
        public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID) throws WEB3BaseException
        {
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setConfirmedQuantity(2.4);
            l_ifoClosingContractSpecParams.setExecutedQuantity(1.1);
            
            
            IfoClosingContractSpec[] l_ifoClosingContractSpecs = new IfoClosingContractSpec[1];
            l_ifoClosingContractSpecs[0] = new WEB3IfoClosingContractSpecImpl(l_ifoClosingContractSpecParams);
            return l_ifoClosingContractSpecs;
        }
    }
    
    private class WEB3IfoPositionManagerImplForTest3 extends WEB3IfoPositionManagerImpl
    {
        public double getContractCurrentPrice(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
        {
            return 2.0;
        }
        
        public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID) throws WEB3BaseException
        {
            IfoClosingContractSpecParams l_ifoClosingContractSpecParams =
                TestDBUtility.getIfoClosingContractSpecRow();
            l_ifoClosingContractSpecParams.setConfirmedQuantity(2.4);
            l_ifoClosingContractSpecParams.setExecutedQuantity(1.1);
            
            
            IfoClosingContractSpec[] l_ifoClosingContractSpecs = new IfoClosingContractSpec[2];
            l_ifoClosingContractSpecs[0] = new WEB3IfoClosingContractSpecImpl(l_ifoClosingContractSpecParams);
            l_ifoClosingContractSpecs[1] = new WEB3IfoClosingContractSpecImpl(l_ifoClosingContractSpecParams);
            return l_ifoClosingContractSpecs;
        }
    }
    
    private class WEB3IfoPositionManagerImplForTest4 extends WEB3IfoPositionManagerImpl
    {
        public double getContractCurrentPrice(WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
        {
            return 0;
        }
        
        public IfoClosingContractSpec[] getClosingContractSpecs(long l_lngContractID) throws WEB3BaseException
        {
            IfoClosingContractSpec[] l_ifoClosingContractSpecs = new IfoClosingContractSpec[0];
            return l_ifoClosingContractSpecs;
        }
        
        public WEB3IfoProductQuote getContractCurrentInfo(WEB3GentradeSubAccount l_subAccount,
                WEB3IfoContractImpl l_ifoContract) throws WEB3BaseException
        {
            WEB3IfoProductQuote l_currentInfo = new WEB3IfoProductQuote();
            return null;
        }
    }
    
    private class WEB3IfoContractImplForMock extends WEB3IfoContractImpl
    {

        public WEB3IfoContractImplForMock(long l_lngContractId) throws DataQueryException, DataNetworkException
        {
            super(l_lngContractId);
        }
        
        public Product getProduct()
        {
            WEB3IfoProductImplForTest l_test = null;
            try
            {
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setUnderlyingProductCode("002");
                l_test = new WEB3IfoProductImplForTest(l_ifoProductParams);
                return l_test;
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_test;
        }
        
        public TradedProduct getTradedProduct()
        {
            WEB3IfoTradedProductImplForTest l_test = null;
            try
            {
                l_test = new WEB3IfoTradedProductImplForTest(330304148080002L);
            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_test;
        }
        
        public long getMarketId()
        {
            return 12345L;
        }
        
        public double getEvaluateIncome(double l_dblSettlementUnitPrice, double l_dblSettlementCnt)
                throws WEB3BaseException
        {
            return 0;
        }
        
        public double getContractCommission(double l_dblContractCnt)
        {
            return 0;
        }
        
        public double getContractCommissionConsumptionTax(double l_dblContractCnt)
        {
            return 0;
        }
        
        public double getContractExecutedAmount(double l_dblCount) throws WEB3BaseException
        {
            return 0;
        }
    }
    
    private class WEB3IfoContractImplForMock2 extends WEB3IfoContractImpl
    {

        public WEB3IfoContractImplForMock2(long l_lngContractId) throws DataQueryException, DataNetworkException
        {
            super(l_lngContractId);
        }
        
        public Product getProduct()
        {
            WEB3IfoProductImplForTest l_test = null;
            try
            {
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setUnderlyingProductCode("002");
                l_test = new WEB3IfoProductImplForTest(l_ifoProductParams);
                return l_test;
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_test;
        }
        
        public TradedProduct getTradedProduct()
        {
            WEB3IfoTradedProductImplForTest l_test = null;
            try
            {
                l_test = new WEB3IfoTradedProductImplForTest(330304148080002L);
            }
            catch(Exception l_ex)
            {
                log.error("", l_ex);
                fail();
            }
            return l_test;
        }
        
        public long getMarketId()
        {
            return 12345L;
        }
        
        public double getEvaluateIncome(double l_dblSettlementUnitPrice, double l_dblSettlementCnt)
                throws WEB3BaseException
        {
            return 7.9;
        }
        
        public double getContractCommission(double l_dblContractCnt)
        {
            return 2.2;
        }
        
        public double getContractCommissionConsumptionTax(double l_dblContractCnt)
        {
            return 4.4;
        }
        
        public double getContractExecutedAmount(double l_dblCount) throws WEB3BaseException
        {
            return 0;
        }
    }
    
    private class WEB3IfoProductImplForTest extends WEB3IfoProductImpl
    {

        public WEB3IfoProductImplForTest(IfoProductRow l_row) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_row);
            // TODO Auto-generated constructor stub
        }
        
        public String getProductCode()
        {
            return "3856";
        }
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
            // TODO Auto-generated constructor stub
        }
        
    }
}
@
