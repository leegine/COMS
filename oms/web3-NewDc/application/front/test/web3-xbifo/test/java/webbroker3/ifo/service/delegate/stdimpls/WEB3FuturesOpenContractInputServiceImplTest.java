head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOpenContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FuturesOpenContractInputServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/23 孫洪江 (中訊) 新規作成
Revision History : 2008/07/28 陸文靜 (中訊) 仕様変更 モデル889,890,894
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeInstitutionForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.BranchIndexDealtCondRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductImplForMock;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.data.IfoDeliveryMonthMasterParams;
import webbroker3.ifo.data.IfoDeliveryMonthMasterRow;
import webbroker3.ifo.data.IfoIndexMasterParams;
import webbroker3.ifo.data.IfoIndexMasterRow;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginInputResponse;
import webbroker3.ifo.message.WEB3FuturesProductSelectRequest;
import webbroker3.ifo.message.WEB3FuturesProductSelectResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOpenContractInputServiceImplTest extends TestBaseForMock
{
    private WEB3FuturesOpenMarginInputRequest l_request = null;
    private WEB3FuturesOpenMarginInputResponse l_reponse = null;
    private WEB3FuturesOpenContractInputServiceImpl l_service = null;
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3FuturesOpenContractInputServiceImplTest.class);

    public WEB3FuturesOpenContractInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {        
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_request = new WEB3FuturesOpenMarginInputRequestForTest();
        this.l_service = new WEB3FuturesOpenContractInputServiceImplForTest();

    }

    protected void tearDown() throws Exception
    {
        this.l_service = null;
        this.l_request = null;
        super.tearDown();
    }

    /**
     * set取引最終日(取引最終日 : Date)
     * ［引数］取引銘柄.getLastTradingDate()の戻り値
     *
     * is出来るまで注文取扱可能<取引最終日考慮>( )
     *
     * レスポンス．立会区分 = 取引時間管理.get立会区分()
     */
    public void testCreateInput_0001()
    {
        final String STR_METHOD_NAME = "testCreateInput_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.futProductCode = "160030005";
            Double  l_dblTradingPower = null;
            long l_lngMessageSuspension = 0;
            this.initData();
            
            IfoProductParams l_ipp = TestDBUtility.getIfoProductRow();
            l_ipp.setProductId(1006139070605L);
            TestDBUtility.insertWithDel(l_ipp);
            
            ProductParams l_pp = TestDBUtility.getProductRow();
            l_pp.setProductId(1006139070605L);
            TestDBUtility.insertWithDel(l_pp);
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImplForMock(1006160060005L);
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060005L);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[]{ SubAccount.class, String.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct", new Class[] { Institution.class, String.class },
                l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                "getIfoTradedProduct",
                new Class[] { Institution.class, String.class, String.class },
                l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductImpl",
                "getPrimaryMarket",
                new Class[]{},
                l_market);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex",
                new Class[]{String.class,
                WEB3IfoTradedProductImpl.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getFuturesOptionTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class },
                l_dblTradingPower);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "getMarketMessageSuspension",
                new Class[] {ProductTypeEnum.class,String.class,String.class},
                new Long(l_lngMessageSuspension));
            
            this.l_reponse = (WEB3FuturesOpenMarginInputResponse) this.l_service.createInput(this.l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[]{ SubAccount.class, String.class });
            assertEquals(WEB3GentradeSubAccount.class, l_paramsValue1.getFirstCalled()[0].getClass());
            assertEquals("1", (String)l_paramsValue1.getFirstCalled()[1]);
        
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct", new Class[] {Institution.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, l_paramsValue2.getFirstCalled()[0].getClass());
            assertEquals( "160030005",((String)l_paramsValue2.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                "getIfoTradedProduct",
                new Class[] { Institution.class, String.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, l_paramsValue3.getFirstCalled()[0].getClass());
            assertEquals("160030005", (String)l_paramsValue3.getFirstCalled()[1]);
            assertEquals("SP", (String)l_paramsValue3.getFirstCalled()[2]);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex",
                new Class[]{String.class,WEB3IfoTradedProductImpl.class});
            assertEquals( "381", (String)l_paramsValue4.getFirstCalled()[0]);
            assertEquals(WEB3IfoTradedProductImpl.class, l_paramsValue4.getFirstCalled()[1].getClass());    
                       
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getFuturesOptionTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class });
            assertEquals(WEB3GentradeSubAccount.class, l_paramsValue5.getFirstCalled()[0].getClass());
            assertEquals(false, ((Boolean)l_paramsValue5.getFirstCalled()[1]).booleanValue());
            assertEquals(WEB3IfoProductImplForMock.class, l_paramsValue5.getFirstCalled()[2].getClass());
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,String.class,String.class});
            assertEquals(6, ((ProductTypeEnum)l_paramsValue6.getFirstCalled()[0]).intValue());
            assertEquals("0", (String)l_paramsValue6.getFirstCalled()[1]);
            assertEquals("1", (String)l_paramsValue6.getFirstCalled()[2]);
            
            assertEquals(null, l_reponse.expirationEndDate);
            
            assertTrue(true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /**
     *get限月一覧(（部店指数別）取扱条件, String)
     * 引数の取扱可能な原資産銘柄コード、先物/オプション区分に該当する、限月項目を取得する。
     */
    public void testCreateInput_0002()
    {
        final String STR_METHOD_NAME = "testCreateInput_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_request.futProductCode = "160030005";
            Double  l_dblTradingPower = null;
            long l_lngMessageSuspension = 0;
            this.initData();
            
            IfoProductParams l_ipp = TestDBUtility.getIfoProductRow();
            l_ipp.setProductId(1006139070605L);
            TestDBUtility.insertWithDel(l_ipp);
            
            ProductParams l_pp = TestDBUtility.getProductRow();
            l_pp.setProductId(1006139070605L);
            TestDBUtility.insertWithDel(l_pp);
            
            TestDBUtility.deleteAll(IfoDeliveryMonthMasterRow.TYPE);
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams = TestDBUtility.getIfoDeliveryMonthMasterRow();
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams);
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImplForMock(1006160060005L);
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060005L);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[]{ SubAccount.class, String.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct", new Class[] { Institution.class, String.class },
                l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                "getIfoTradedProduct",
                new Class[] { Institution.class, String.class, String.class },
                l_ifoTradedProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductImpl",
                "getPrimaryMarket",
                new Class[]{},
                l_market);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex",
                new Class[]{String.class,
                WEB3IfoTradedProductImpl.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getFuturesOptionTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class },
                l_dblTradingPower);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20080702","yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeBranch",
                "getMarketMessageSuspension",
                new Class[] {ProductTypeEnum.class,String.class,String.class},
                new Long(l_lngMessageSuspension));

            this.l_reponse = (WEB3FuturesOpenMarginInputResponse) this.l_service.createInput(this.l_request);
            
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[]{ SubAccount.class, String.class });
            assertEquals(WEB3GentradeSubAccount.class, l_paramsValue1.getFirstCalled()[0].getClass());
            assertEquals("1", (String)l_paramsValue1.getFirstCalled()[1]);
        
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getIfoProduct", new Class[] {Institution.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, l_paramsValue2.getFirstCalled()[0].getClass());
            assertEquals( "160030005",((String)l_paramsValue2.getFirstCalled()[1]));
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                "getIfoTradedProduct",
                new Class[] { Institution.class, String.class, String.class });
            assertEquals(WEB3GentradeInstitutionForMock.class, l_paramsValue3.getFirstCalled()[0].getClass());
            assertEquals("160030005", (String)l_paramsValue3.getFirstCalled()[1]);
            assertEquals("SP", (String)l_paramsValue3.getFirstCalled()[2]);
            
            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                "validateHandlingIndex",
                new Class[]{String.class,WEB3IfoTradedProductImpl.class});
            assertEquals( "381", (String)l_paramsValue4.getFirstCalled()[0]);
            assertEquals(WEB3IfoTradedProductImpl.class, l_paramsValue4.getFirstCalled()[1].getClass());    
                       
            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "getFuturesOptionTradingPower",
                new Class[]{ WEB3GentradeSubAccount.class, boolean.class, IfoProduct.class });
            assertEquals(WEB3GentradeSubAccount.class, l_paramsValue5.getFirstCalled()[0].getClass());
            assertEquals(false, ((Boolean)l_paramsValue5.getFirstCalled()[1]).booleanValue());
            assertEquals(WEB3IfoProductImplForMock.class, l_paramsValue5.getFirstCalled()[2].getClass());
            
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeBranch",
                    "getMarketMessageSuspension",
                    new Class[] {ProductTypeEnum.class,String.class,String.class});
            assertEquals(6, ((ProductTypeEnum)l_paramsValue6.getFirstCalled()[0]).intValue());
            assertEquals("0", (String)l_paramsValue6.getFirstCalled()[1]);
            assertEquals("1", (String)l_paramsValue6.getFirstCalled()[2]);
            assertEquals("200807",  l_reponse.delivaryMonthList[0]);

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    /**
     *get限月一覧(（部店指数別）取扱条件, String)
     * 引数の取扱可能な原資産銘柄コード、先物/オプション区分に該当する、限月項目を取得する。
     */
    public void testCreateProductSelect_C0001()
    {
        final String STR_METHOD_NAME = "testCreateProductSelect_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3FuturesProductSelectRequestForTest l_request = new WEB3FuturesProductSelectRequestForTest();
            
            this.initData();
            
            IfoProductParams l_ipp = TestDBUtility.getIfoProductRow();
            l_ipp.setProductId(1006139070605L);
            TestDBUtility.insertWithDel(l_ipp);
            
            ProductParams l_pp = TestDBUtility.getProductRow();
            l_pp.setProductId(1006139070605L);
            TestDBUtility.insertWithDel(l_pp);
            
            TestDBUtility.deleteAll( IfoDeliveryMonthMasterRow.TYPE);
            IfoDeliveryMonthMasterParams l_ifoDeliveryMonthMasterParams = TestDBUtility.getIfoDeliveryMonthMasterRow();
            TestDBUtility.insertWithDel(l_ifoDeliveryMonthMasterParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[]{ SubAccount.class, String.class },
                null);

            WEB3FuturesProductSelectResponse l_reponse =
                (WEB3FuturesProductSelectResponse) this.l_service.createProductSelect(l_request);

            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[]{ SubAccount.class, String.class });
            assertEquals(WEB3GentradeSubAccount.class, l_paramsValue1.getFirstCalled()[0].getClass());
            assertEquals("1", (String)l_paramsValue1.getFirstCalled()[1]);
         
            assertEquals("200807",  l_reponse.delivaryMonthList[0]);

        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
        
    public class WEB3FuturesOpenMarginInputRequestForTest extends WEB3FuturesOpenMarginInputRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3FuturesOpenMarginInputRequestForTest.validate()");
        }
    }
    public class WEB3FuturesProductSelectRequestForTest extends WEB3FuturesProductSelectRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3FuturesProductSelectRequest.validate()");
        }
    }
    
    public class WEB3FuturesOpenContractInputServiceImplForTest extends WEB3FuturesOpenContractInputServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {            
            WEB3GentradeSubAccount l_subAccount = null;
            l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            
            return l_subAccount;
        }
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //ProductRow
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams =TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_productParams.setStandardName("シンセンテルス");
            l_productParams.setLotSize(0.000000D);
            l_productParams.setCalcSize(1.000000D);
            l_productParams.setEstimationPrice(0.000000D);
            l_productParams.setMarginRatio(0.000000D);
            l_productParams.setSecuritiesEstimationRatio(0.000000D);
            l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_productParams);
            
            //EnableOrderConditionRow
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setInstitutionCode("1D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_enableOrderConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 =TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            l_enableOrderConditionParams1.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_enableOrderConditionParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);

            //IfoTradedProductRow
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setTradedProductId(1006160060005L);
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);

            //TradedProductRow
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(1006160060005L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setMarginRatio(70.000000D);
            l_tradedProductParams.setSuspensionFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setBaseDate(WEB3DateUtility.getDate("20040917","yyyyMMdd"));
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20040924","yyyyMMdd"));
            l_tradedProductParams.setProductType(ProductTypeEnum.FOREIGN_EQUITY);
            l_tradedProductParams.setCollateralFlag(BooleanEnum.FALSE);
            l_tradedProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradedProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //IfoTradedProductUpdqRow
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
                //l_ifoTradedProductUpdqParams.setValidForBizDate("20070629");
            l_ifoTradedProductUpdqParams.setInstitutionCode("10");
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
            l_ifoTradedProductUpdqParams.setUnitSize(10000D);
            l_ifoTradedProductUpdqParams.setUnitMargin(0L);
            l_ifoTradedProductUpdqParams.setPerOrderMaxUnits(1000D);
            l_ifoTradedProductUpdqParams.setOrderCloseTime("0");
            l_ifoTradedProductUpdqParams.setLastClosingPrice(28);
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setTradeStopFlag(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setBuyToOpenStopFlag(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setSellToOpenStopFlag(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setSellToCloseStopFlag(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setBuyToCloseStopFlag(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setListFlag(BooleanEnum.TRUE);
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setExerciseStop(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setActualDelivaryStop(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setActualRecieveStop(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setReserveStop(BooleanEnum.FALSE);
            l_ifoTradedProductUpdqParams.setIndicationPrice(30.8D);
            l_ifoTradedProductUpdqParams.setLastLiquidationPrice(0D);
            l_ifoTradedProductUpdqParams.setTargetSpotPrice(1212D);
            l_ifoTradedProductUpdqParams.setLiquidationPrice(8.13D);
            l_ifoTradedProductUpdqParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070628","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);

            //IfoProductRow
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("05");//10
            l_ifoProductParams.setProductCode("160030005");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(0);
            l_ifoProductParams.setMonthOfDelivery("200503");
            l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
            l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoProductParams);

            //MarketRow
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketCode("SP");
            l_marketParams.setSonarMarketCode("G");
            l_marketParams.setMarketName("シンガポール");
            l_marketParams.setOpenTime("09:00");
            l_marketParams.setCloseTime("15:00");
            l_marketParams.setSuspension("0");
            l_marketParams.setChangeableType("1");
            l_marketParams.setFeqOrderEmpDiv("7");
            l_marketParams.setFeqOrderRequestDiv("1");
            l_marketParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_marketParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_marketParams);

            //BranchIndexDealtCondRow
            TestDBUtility.deleteAll(BranchIndexDealtCondRow.TYPE);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setTargetProductCode("0005");
            l_branchIndexDealtCondParams.setInstitutionCode("1D");
            l_branchIndexDealtCondParams.setBranchCode("381");
            l_branchIndexDealtCondParams.setMarketCode("12");
            l_branchIndexDealtCondParams.setFutureOptionDiv("1");
            l_branchIndexDealtCondParams.setOpenContLimit(1234556L);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit0(14D);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit0Red(11D);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit1(11D);
            l_branchIndexDealtCondParams.setIfoDepositPerUnit1Red(11D);
            l_branchIndexDealtCondParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams.setLastUpdatedDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);
            BranchIndexDealtCondParams l_branchIndexDealtCondParams1 = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams1.setTargetProductCode("0005");
            l_branchIndexDealtCondParams1.setInstitutionCode("0D");
            l_branchIndexDealtCondParams1.setBranchCode("381");
            l_branchIndexDealtCondParams1.setMarketCode("12");
            l_branchIndexDealtCondParams1.setFutureOptionDiv("1");
            l_branchIndexDealtCondParams1.setOpenContLimit(1234556L);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit0(14D);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit0Red(11D);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit1(11D);
            l_branchIndexDealtCondParams1.setIfoDepositPerUnit1Red(11D);
            l_branchIndexDealtCondParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams1.setLastUpdatedDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_branchIndexDealtCondParams1.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams1);

            //IfoIndexMasterRow
            TestDBUtility.deleteAll(IfoIndexMasterRow.TYPE);
            IfoIndexMasterParams l_ifoIndexMasterParams = TestDBUtility.getIfoIndexMasterRow();
            l_ifoIndexMasterParams.setIndexId(123L);
            l_ifoIndexMasterParams.setUnderlyingProductCode("0005");
            l_ifoIndexMasterParams.setFutureOptionDiv("1");
            l_ifoIndexMasterParams.setStandardName("日経300");
            TestDBUtility.insertWithDel(l_ifoIndexMasterParams);

            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("内藤　@四郎");
            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0005");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            l_branchParams.setInstitutionCode("0D");
            l_branchParams.setInstitutionId(33);
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchName("東京支店");
            l_branchParams.setBranchNameAlt1("TOKYO BRANCH");
            l_branchParams.setBranchType(BranchTypeEnum.WEB_BRANCH);
            l_branchParams.setMaxHandlingPriceInd(150000000);
            l_branchParams.setMaxHandlingPriceCorp(600000000);
            l_branchParams.setMaxHandlingPriceIndOption(600000000);
            l_branchParams.setMaxHandlingPriceCorpOption(600000000);
            l_branchParams.setMaxHandlingPriceIndFuture(600000000);
            l_branchParams.setMaxHandlingPriceCorpFuture(600000000);
            l_branchParams.setMaxContPriceAllInd(600000000.000000);
            l_branchParams.setMaxContPriceAllCorp(150000000.000000);
            l_branchParams.setMaxContPriceProductInd(600000000.000000);
            l_branchParams.setMaxContPriceProductCorp(150000000.000000);
            l_branchParams.setMaxContPrice1dayInd(600000000.000000D);
            l_branchParams.setMaxContPrice1dayCorp(1.000000D);
            l_branchParams.setHandlingMarketMake(0);
            l_branchParams.setHandlingNotLoanTransStock(0);
            l_branchParams.setEmailAddress("info@@naitou-sec.co.jp");
            l_branchParams.setLoginStopDiv("1");
            l_branchParams.setAccountCodeMin(6);
            l_branchParams.setAccountCodeMax(20);
            l_branchParams.setAccountCodeCheckMode("2");
            l_branchParams.setInsiderDefaultRegistDiv("1");
            l_branchParams.setMarginSysDiv("1");
            l_branchParams.setMarginGenDiv("1");
            l_branchParams.setFstkDiv("1");
            l_branchParams.setMstkDiv("1");
            l_branchParams.setOptionDiv("1");
            l_branchParams.setFutureDiv("1");
            l_branchParams.setMfDiv("1");
            l_branchParams.setRuitoDiv("1");
            l_branchParams.setQualifiedInvestorConfirmDiv("1");
            l_branchParams.setMarginDepositRate(0.000000D);
            l_branchParams.setCashMarginDepositRate(0.000000D);
            l_branchParams.setMarginDepositRate(0.000000D);
            l_branchParams.setMinMarginDeposit(0.000000D);
            l_branchParams.setMinIfoDeposit(0.000000D);
            l_branchParams.setCalcSubstituteRate(0.000000D);
            l_branchParams.setMarginSecCheckRate(0.000000D);
            l_branchParams.setShortMarginRestrainDiv("1");
            l_branchParams.setShortMarginRestrainUnit(0.000000D);
            l_branchParams.setShortSellOrderValidMinute(0);
            l_branchParams.setMarginSecTransferMaxCount(5);
            l_branchParams.setCloseWorngEquityMargin(10);
            l_branchParams.setCloseWorngOption(5);
            l_branchParams.setCloseWorngFeq(0);
            l_branchParams.setLastUpdater("administrator");
            l_branchParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_branchParams.setMaxHandlingPriceCloseDiv("1");
            l_branchParams.setOffFloorDiv("1");
            l_branchParams.setCloseWorngFeq(5);
            TestDBUtility.insertWithDel(l_branchParams);
            
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            //証券会社コード
            l_subAccountParams.setInstitutionCode("0D");
            //証券会社ID
            l_subAccountParams.setInstitutionId(33);
            //部店ＩＤ
            l_subAccountParams.setBranchId(33381L);
            //補助口座ステータス
            l_subAccountParams.setSubAccountStatus(SubAccountStatusEnum.ACTIVE);
            //口座登録日
            l_subAccountParams.setOpenDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //口座閉鎖日
            l_subAccountParams.setCloseDate(WEB3DateUtility.getDate("20061211","yyyyMMdd"));
            //残高(当日）
            l_subAccountParams.setCashBalance(13456.0);
            //作成日付
            l_subAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            //更新日付
            l_subAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
}
@
