head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OnlineRunStatusDao;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest extends
    TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest.class);
    
    WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl l_impl = 
        new WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl();
    
    public WEB3AdminEquityForcedSettleTempOrderCreateServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //リクエストが未指定(null)です。
    public void testExecute01()
    {
        String STR_METHOD_NAME = "testExecute01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.execute(null);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //パラメータタイプ不正。
    public void testExecute02()
    {
        String STR_METHOD_NAME = "testExecute02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_request =
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            l_impl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //リクエストデータ.強制決済処理区分が、"休憩時間帯"かつ、（証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"または、"決済期日到来建＋保証金維持率割れ"）の場合実施する。
    //validate建玉強制決済(EqtypeContractRow)"処理済"
    public void testExecute12()
    {
        String STR_METHOD_NAME = "testExecute12()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

            l_settleResult.resultFlg = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateContractForcedSettleIntermission",
                    new Class[] {WEB3GentradeSubAccount.class}, 
                    l_settleResult);
            
            //WEB3EquityBizLogicProvider.createCommission
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {
                        WEB3GentradeSubAccount.class, 
                        String.class,
                        Date.class, 
                        String.class,
                        String.class, 
                        double.class, 
                        OrderCategEnum.class},
                l_commission);

            //WEB3EquityOrderManager//TODO
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",                
                new Class[] {WEB3GentradeCommission.class,
                        double.class,
                        WEB3GentradeSubAccount.class,
                        WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class,
                        double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class},
                l_lossPrice);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCond =
                TestDBUtility.getBranchMarketRepayDealCondRow();
            l_BranchMarketRepayDealtCond.setBranchCode("381");
            l_BranchMarketRepayDealtCond.setInstitutionCode("10");
            l_BranchMarketRepayDealtCond.setMarketCode("N1");
            l_BranchMarketRepayDealtCond.setRepaymentDiv("1");
            l_BranchMarketRepayDealtCond.setRepaymentNum(0);
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCond);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(RsvEqClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(EqtypeLockedContractDetailsRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(330304148080000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(330304148080000L);
            l_EqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            
            EqtypeTradedProductParams l_EqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_EqtypeTradedProductParams.setProductId(330304148080000L);
            l_EqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_EqtypeTradedProductParams.setMarketId(33381);
            TestDBUtility.insertWithDel(l_EqtypeTradedProductParams);
            
            TradedProductParams l_TradedProductParams = TestDBUtility.getTradedProductRow();
            l_TradedProductParams.setMarketId(33381);
            l_TradedProductParams.setProductId(330304148080000L);
            l_TradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_TradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_TradedProductParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("2");//2：保証金維持率割れ
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setAccountId(333812512203L);
            l_contractParams.setProductId(330304148080000L);
            l_contractParams.setContractId(2134566345L);
            l_contractParams.setQuantity(1000L);
            l_contractParams.setRepaymentType("1");
            TestDBUtility.insertWithDel(l_contractParams);
            
            EqtypeLockedContractDetailsParams l_lockedContractDetailsParams = new EqtypeLockedContractDetailsParams();
            l_lockedContractDetailsParams.setAccountId(333812512203L);
            l_lockedContractDetailsParams.setContractId(2134566345L);
            l_lockedContractDetailsParams.setSubAccountId(33381251220301L);
            l_lockedContractDetailsParams.setLockedQuantity(100L);
            l_lockedContractDetailsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_lockedContractDetailsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_lockedContractDetailsParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(100);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "2";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","B",1);
            assertEquals(l_row.getOnlineServiceDiv(), "B");
            assertEquals(l_row.getRunStatusDiv(), "9");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //強制決済処理区分 = 1  正常
    public void testExecute03()
    {
        String STR_METHOD_NAME = "testExecute03()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 0L;
            l_request.rangeTo = 100L;
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusParams.TYPE);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("2");
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableParams);
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    //2重啓動測試
    public void testExecute04()
    {
        String STR_METHOD_NAME = "testExecute04()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
            l_runStatusParams.setInstitutionCode("10");
            l_runStatusParams.setRunStatusDiv("5");
            l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
            l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
            l_runStatusParams.setAccountIdFrom(0);
            l_runStatusParams.setAccountIdTo(6);
            l_runStatusParams.setOnlineServiceDiv("5");
            l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
            l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 0L;
            l_request.rangeTo = 100L;
            
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_insParams);
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //(*)「連続注文取扱不可」の例外をcatchする。 強制決済処理区分 = 1
    //リクエストデータ.強制決済処理区分 ＝ "オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "決済期日到来建"または、"決済期日到来建＋保証金維持率割れ"）の場合実施する。
    //リクエストデータ.強制決済処理区分 ＝ "オンライン開始前"かつ
    //証券会社.信用強制決済実施区分 ＝ "決済期日到来建＋保証金維持率割れ"
    
    //リクエストデータ.強制決済処理区分が、"オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"または、"決済期日到来建＋保証金維持率割れ"）の場合実施する。
    //リクエストデータ.強制決済処理区分が、"オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "決済期日到来建＋保証金維持率割れ"
    public void testExecute05()
    {
        String STR_METHOD_NAME = "testExecute05()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 0L;
            l_request.rangeTo = 100L;
            
            TestDBUtility.deleteAll(OnlineRunStatusParams.TYPE);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("3");
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableParams);
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","5",0);
            assertEquals(l_row.getRunStatusDiv(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //(*)「連続注文取扱不可」の例外をcatchする。 強制決済処理区分 = 1
    //リクエストデータ.強制決済処理区分 ＝ "オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "決済期日到来建"
    public void testExecute06()
    {
        String STR_METHOD_NAME = "testExecute06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(RsvEqClosingContractSpecRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("1");
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setMarketId(33381L);
            TestDBUtility.insertWithDel(l_contractParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(100);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","5",1);
            assertEquals(l_row.getRunStatusDiv(), "9");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
    //(*)「連続注文取扱不可」の例外をcatchする。 強制決済処理区分 = 1
    //リクエストデータ.強制決済処理区分 ＝ "オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "決済期日到来建"
    public void testExecute07()
    {
        String STR_METHOD_NAME = "testExecute07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(RsvEqClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeLockedContractDetailsRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("1");
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setQuantity(1000L);
            TestDBUtility.insertWithDelAndCommit(l_contractParams);
            
            EqtypeLockedContractDetailsParams l_lockedContractDetailsParams = new EqtypeLockedContractDetailsParams();
            l_lockedContractDetailsParams.setAccountId(333812512203L);
            l_lockedContractDetailsParams.setContractId(2134566345L);
            l_lockedContractDetailsParams.setSubAccountId(33381251220301L);
            l_lockedContractDetailsParams.setLockedQuantity(1000L);
            l_lockedContractDetailsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_lockedContractDetailsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_lockedContractDetailsParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(1000);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","5",1);
            assertEquals("9", l_row.getRunStatusDiv());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //リクエストデータ.強制決済処理区分が、"オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"
    public void testExecute08()
    {
        String STR_METHOD_NAME = "testExecute08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(RsvEqClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeLockedContractDetailsRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("2");
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setAccountId(333812512203L);
            l_contractParams.setSubAccountId(33381251220301L);
            l_contractParams.setQuantity(1000L);
            l_contractParams.setMarketId(33381L);
            TestDBUtility.insertWithDelAndCommit(l_contractParams);
            
            EqtypeLockedContractDetailsParams l_lockedContractDetailsParams = new EqtypeLockedContractDetailsParams();
            l_lockedContractDetailsParams.setAccountId(333812512203L);
            l_lockedContractDetailsParams.setContractId(2134566345L);
            l_lockedContractDetailsParams.setSubAccountId(33381251220301L);
            l_lockedContractDetailsParams.setLockedQuantity(1000L);
            l_lockedContractDetailsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_lockedContractDetailsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDelAndCommit(l_lockedContractDetailsParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(100);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","5",1);
            assertEquals(l_row.getRunStatusDiv(), "9");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //リクエストデータ.強制決済処理区分が、"オンライン開始前"かつ、
    //（証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"
    public void testExecute09()
    {
        String STR_METHOD_NAME = "testExecute09()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(RsvEqClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeLockedContractDetailsRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("2");
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setQuantity(1000L);
            TestDBUtility.insertWithDelAndCommit(l_contractParams);
            
            EqtypeLockedContractDetailsParams l_lockedContractDetailsParams = new EqtypeLockedContractDetailsParams();
            l_lockedContractDetailsParams.setAccountId(333812512203L);
            l_lockedContractDetailsParams.setContractId(2134566345L);
            l_lockedContractDetailsParams.setSubAccountId(33381251220301L);
            l_lockedContractDetailsParams.setLockedQuantity(100L);
            l_lockedContractDetailsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_lockedContractDetailsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_lockedContractDetailsParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(100);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","5",1);
            assertEquals(l_row.getRunStatusDiv(), "9");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    */
    
    //[リクエスト.強制決済処理区分 ＝ "休憩時間帯"の場合] 
    //かつ、（証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"または、
    //"決済期日到来建＋保証金維持率割れ"）の場合実施する。
    public void testExecute10()
    {
        String STR_METHOD_NAME = "testExecute10()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            
            WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

            l_settleResult.resultFlg = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateContractForcedSettleIntermission",
                    new Class[] {WEB3GentradeSubAccount.class}, 
                    l_settleResult);
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAll(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(RsvEqClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(EqtypeLockedContractDetailsRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("11");
            l_mainAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(333812512204L);
            l_mainAccountParams1.setAccountCode("2512247");
            l_mainAccountParams1.setBranchCode("382");
            l_mainAccountParams1.setBranchId(33382);
            l_mainAccountParams1.setInstitutionCode("10");
            l_mainAccountParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("2");//2：保証金維持率割れ
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setAccountId(333812512203L);
            l_contractParams.setContractId(2134566346L);
            l_contractParams.setQuantity(1000L);
            TestDBUtility.insertWithDel(l_contractParams);
            EqtypeContractParams l_contractParams1 = TestDBUtility.getEqtypeContractRow();
            l_contractParams1.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams1.setContractId(2134566345L);
            l_contractParams1.setAccountId(333812512204L);
            l_contractParams1.setQuantity(1000L);
            TestDBUtility.insertWithDel(l_contractParams1);
            
            EqtypeLockedContractDetailsParams l_lockedContractDetailsParams = new EqtypeLockedContractDetailsParams();
            l_lockedContractDetailsParams.setAccountId(333812512203L);
            l_lockedContractDetailsParams.setContractId(2134566345L);
            l_lockedContractDetailsParams.setSubAccountId(33381251220301L);
            l_lockedContractDetailsParams.setLockedQuantity(100L);
            l_lockedContractDetailsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_lockedContractDetailsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_lockedContractDetailsParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(100);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "2";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
//            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
//            
//            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","B",1);
//            assertEquals(l_row.getOnlineServiceDiv(), "B");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //リクエストデータ.強制決済処理区分が、"オンライン開始前"かつ、
    // （証券会社.信用強制決済実施区分 ＝ "保証金維持率割れ"または、"決済期日到来建＋保証金維持率割れ"）の場合実施する。
    //validate建玉強制決済()の戻り値.判定フラグ ＝ trueの場合
    public void testExecute11()
    {
        String STR_METHOD_NAME = "testExecute11()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class}, 
                    null);
            WEB3TPContractForcedSettleResult l_settleResult = new WEB3TPContractForcedSettleResult();

            //判定フラグ　@=　@true
            l_settleResult.resultFlg = true;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateContractForcedSettleBefOnline",
                    new Class[] {WEB3GentradeSubAccount.class}, 
                    l_settleResult);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(RsvEqClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeLockedContractDetailsRow.TYPE);
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("10");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setAccountCode("2512246");
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionCode("10");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(33381L);
            l_marketParams.setMarketCode("N1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
            l_insParams.setInstitutionCode("10");
            l_insParams.setForcedsettleorderDiv("2");//2：保証金維持率割れ
            TestDBUtility.insertWithDel(l_insParams);
            
            EnableOrderConditionParams l_enableParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableParams.setInstitutionCode("10");
            l_enableParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableParams.setFutureOptionDiv("0");
            l_enableParams.setMarginTradingDiv("0");
            l_enableParams.setMarketCode("0");
            l_enableParams.setChainOrder("0");//「連続注文取扱不可」
            TestDBUtility.insertWithDel(l_enableParams);
            
            EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
            l_contractParams.setCloseDate(GtlUtils.getTradingSystem().getBizDate());
            l_contractParams.setQuantity(1000L);
            TestDBUtility.insertWithDelAndCommit(l_contractParams);
            
            EqtypeLockedContractDetailsParams l_lockedContractDetailsParams = new EqtypeLockedContractDetailsParams();
            l_lockedContractDetailsParams.setAccountId(333812512203L);
            l_lockedContractDetailsParams.setContractId(2134566345L);
            l_lockedContractDetailsParams.setSubAccountId(33381251220301L);
            l_lockedContractDetailsParams.setLockedQuantity(100L);
            l_lockedContractDetailsParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_lockedContractDetailsParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_lockedContractDetailsParams);
            
            RsvEqClosingContractSpecParams l_closingContractSpecParams =
                new RsvEqClosingContractSpecParams();
            l_closingContractSpecParams.setAccountId(333812512203L);
            l_closingContractSpecParams.setSubAccountId(33381251220301L);
            l_closingContractSpecParams.setContractId(2134566345L);
            l_closingContractSpecParams.setOrderId(111L);
            l_closingContractSpecParams.setQuantity(100);
            l_closingContractSpecParams.setClosingSerialNo(1);
            l_closingContractSpecParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_closingContractSpecParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_closingContractSpecParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleTempOrderCreateRequest l_request = 
                new WEB3AdminEquityForcedSettleTempOrderCreateRequest();
            
            l_request.institutionCode = "10";
            l_request.forcedSettleType = "1";
            l_request.rangeFrom = 1L;
            l_request.rangeTo = 3338125122030L;
            
            WEB3BackResponse l_response = l_impl.execute(l_request);
            assertEquals(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class, l_response.getClass());
            
            OnlineRunStatusRow l_row = OnlineRunStatusDao.findRowByPk("10",ProductTypeEnum.EQUITY,"0","5",1);
            assertEquals(l_row.getRunStatusDiv(), "9");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);    
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

}
@
