head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/16　@孟亜南(中訊) 
*/
package webbroker3.tradingpower;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeOpenContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.feq.WEB3FeqNewOrderSpec;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.SecurityCashoutRestraintParams;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.data.SecurityShortageAccountRow;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.ifo.WEB3IfoUpdateInterceptor;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.EqtypeFixedContractParams;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceFrgnParams;
import webbroker3.tradingpower.data.TpCashBalanceFrgnRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;
import webbroker3.tradingpoweradmin.data.DepositAutotransferStopRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3TPTradingPowerServiceImplTest extends TestBaseForMock
{
    
    public WEB3TPTradingPowerServiceImplTest(String name)
    {
        super(name);
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImplTest.class);

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    class WEB3TPTradingPowerServiceImplAForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return 30.123D;
       }
    }
    
    class WEB3TPTradingPowerServiceImplBForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return -30.123D;
       }
    }
    
    class WEB3TPTradingPowerServiceImplCForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return 2.0D;
       }
    }
    
    public void testGetOtherTradingPowerForCheckC0001()
    {
        final String STR_METHOD_NAME =
            "testGetOtherTradingPowerForCheckC0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingpowerCalcConditionParam);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultMarginRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultMarginParams);
            
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            l_tpCalcResultMarginDetailParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultMarginDetailRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultMarginDetailParams);
            
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngMainAccountId = 101001010010L;
            long l_lngSubAccountId = 10100101001007L;
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);

            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            Date l_datDeliveryDate = WEB3GentradeUtils.getBizDate(l_tsSystemTime);;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl(); 
            
            double l_dblTradingPower = l_impl.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
            
            assertEquals("-1", WEB3StringTypeUtility.formatNumber(l_dblTradingPower));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testGetOtherTradingPowerForCheckC0002()
    {
        final String STR_METHOD_NAME =
            "testGetOtherTradingPowerForCheckC0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingpowerCalcConditionParam);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultEquityRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultEquityParams);
            
            TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams =
                TestDBUtility.getTpCalcResultEquityDetailRow();
            l_tpCalcResultEquityDetailParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultMarginDetailRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultEquityDetailParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngMainAccountId = 101001010010L;
            long l_lngSubAccountId = 10100101001007L;
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);

            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            Date l_datDeliveryDate = WEB3GentradeUtils.getBizDate(l_tsSystemTime);;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl(); 
            
            double l_dblTradingPower = l_impl.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
            
            assertEquals("-1", WEB3StringTypeUtility.formatNumber(l_dblTradingPower));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    
    public void testGetOtherTradingPowerForCheckC0003()
    {
        final String STR_METHOD_NAME =
            "testGetOtherTradingPowerForCheckC0003()";
        log.entering(STR_METHOD_NAME);

        try
        {    
            
            WEB3GentradeSubAccount l_subAccount = null;

            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            Date l_datDeliveryDate = WEB3GentradeUtils.getBizDate(l_tsSystemTime);;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl(); 
            
            l_impl.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);

            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testGetOtherTradingPowerForCheckC0004()
    {
        final String STR_METHOD_NAME =
            "testGetOtherTradingPowerForCheckC0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingpowerCalcConditionParam);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngMainAccountId = 101001010010L;
            long l_lngSubAccountId = 10100101001007L;
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);


            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040211", "yyyyMMdd");
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl(); 
            
            l_impl.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
            fail();

        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
 
    }
    
    public void testGetOtherTradingPowerForCheckC0005()
    {
        final String STR_METHOD_NAME =
            "testGetOtherTradingPowerForCheckC0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingpowerCalcConditionParam);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultEquityRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultEquityParams);
            
            TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams =
                TestDBUtility.getTpCalcResultEquityDetailRow();
            l_tpCalcResultEquityDetailParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultMarginDetailRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultEquityDetailParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngMainAccountId = 101001010010L;
            long l_lngSubAccountId = 10100101001007L;
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datDeliveryDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(10);
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl(); 
            
            double l_dblTradingPower = l_impl.getOtherTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
            
            assertEquals("-1", WEB3StringTypeUtility.formatNumber(l_dblTradingPower));

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testGetOtherTradingPowerC0001()
    {
        final String STR_METHOD_NAME =
            "testGetOtherTradingPowerC0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TradingpowerCalcConditionRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingpowerCalcConditionParam);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultEquityRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultEquityParams);
            
            TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams =
                TestDBUtility.getTpCalcResultEquityDetailRow();
            l_tpCalcResultEquityDetailParams.setAccountId(101001010010L);
            TestDBUtility.deleteAllAndCommit(TpCalcResultMarginDetailRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tpCalcResultEquityDetailParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            long l_lngMainAccountId = 101001010010L;
            long l_lngSubAccountId = 10100101001007L;
            
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                    l_lngMainAccountId,
                    l_lngSubAccountId);

            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            Date l_datDeliveryDate = WEB3GentradeUtils.getBizDate(l_tsSystemTime);;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl(); 
            
            double l_dblTradingPower = l_impl.getOtherTradingPower(l_subAccount, l_datDeliveryDate);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_dblTradingPower));

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
     * get外貨残高情報
     * 引数.補助口座がnullの場合
     *
     */
    public void testGetForeignPositionContract_C0001()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionContract_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeSubAccount l_subAccount = null;
            String l_strCurrencyCode = "A0";

            WEB3TPTradingPowerServiceImpl l_tradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3ForeignPositionContract l_foreignPositionContract =
                l_tradingPowerServiceImpl.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
            
            fail();

        }
        catch (WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get外貨残高情報
     * 外貨残高情報を取得がnullの場合
     *
     */
    public void testGetForeignPositionContract_C0002()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionContract_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceFrgnRow.TYPE);
            String l_strCurrencyCode = "A0";
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                        l_subAccountParams.account_id,
                        l_subAccountParams.sub_account_id);

            WEB3TPTradingPowerServiceImpl l_tradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3ForeignPositionContract l_foreignPositionContract =
                l_tradingPowerServiceImpl.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
            
            assertNull(l_foreignPositionContract.tpCashBalanceFrgnParams);
        }

        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get外貨残高情報
     * 外貨残高情報を取得!= nullの場合
     *
     */
    public void testGetForeignPositionContract_C0003()
    {
        final String STR_METHOD_NAME = "testGetForeignPositionContract_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TpCashBalanceFrgnRow.TYPE);
            TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams = new TpCashBalanceFrgnParams();
            
            l_tpCashBalanceFrgnParams.setTpCashBalanceFrgnId(9000);
            l_tpCashBalanceFrgnParams.setAccountId(333812512203L);
            l_tpCashBalanceFrgnParams.setSubAccountId(33381251220301L);
            l_tpCashBalanceFrgnParams.setCurrencyCode("A0");
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn0(1000);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn1(1001);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn2(1002);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn3(1003);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn4(1004);
            l_tpCashBalanceFrgnParams.setCashBalanceFrgn5(1005);
            l_tpCashBalanceFrgnParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
            l_tpCashBalanceFrgnParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070802", "yyyyMMdd"));
            
            TestDBUtility.insertWithDel(l_tpCashBalanceFrgnParams);
            
            String l_strCurrencyCode = "A0";
            WEB3GentradeSubAccount l_subAccount =
                new WEB3GentradeSubAccount(
                        l_subAccountParams.account_id,
                        l_subAccountParams.sub_account_id);

            WEB3TPTradingPowerServiceImpl l_tradingPowerServiceImpl = new WEB3TPTradingPowerServiceImpl();
            WEB3ForeignPositionContract l_foreignPositionContract =
                l_tradingPowerServiceImpl.getForeignPositionContract(l_subAccount, l_strCurrencyCode);
            
            assertEquals(9000,l_foreignPositionContract.tpCashBalanceFrgnParams.getTpCashBalanceFrgnId());
            assertEquals(333812512203L,l_foreignPositionContract.tpCashBalanceFrgnParams.getAccountId());
            assertEquals(33381251220301L,l_foreignPositionContract.tpCashBalanceFrgnParams.getSubAccountId());
            assertEquals("A0",l_foreignPositionContract.tpCashBalanceFrgnParams.getCurrencyCode());
            assertEquals(1000,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn0(), 0);
            assertEquals(1001,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn1(), 0);
            assertEquals(1002,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn2(), 0);
            assertEquals(1003,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn3(), 0);
            assertEquals(1004,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn4(), 0);
            assertEquals(1005,l_foreignPositionContract.tpCashBalanceFrgnParams.getCashBalanceFrgn5(), 0);
            assertEquals(WEB3DateUtility.getDate("20070801", "yyyyMMdd"), 
                    l_foreignPositionContract.tpCashBalanceFrgnParams.getCreatedTimestamp());
            assertEquals(WEB3DateUtility.getDate("20070802", "yyyyMMdd"), 
                    l_foreignPositionContract.tpCashBalanceFrgnParams.getLastUpdatedTimestamp());
        }

        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     * get担保ローン振替可能額
//     * 參數補助口座為空
//     */
//    public void testGetSLChangePossAmtCase0001()
//    {
//        final String STR_METHOD_NAME = "testGetSLChangePossAmtCase0001";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = null;
//            Date l_datDeliveryDate = null;
//            
//            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
////            l_impl.getSLChangePossAmt(l_subAccount, l_datDeliveryDate);
//            fail();
//        }
////        catch(WEB3BaseException l_ex)
////        {
////            log.debug(STR_METHOD_NAME , l_ex);
////            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
////            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
////        }
//        catch(Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        } 
//    }
    
//    /**
//     * get担保ローン振替可能額
//     * is信用口座開設()の戻り値 = true
//     */
//    public void testGetSLChangePossAmtCase0002()
//    {
//        final String STR_METHOD_NAME = "testGetSLChangePossAmtCase0002";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//
//            Date l_datDeliveryDate = null;
//            
//            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
////            l_impl.getSLChangePossAmt(l_subAccount, l_datDeliveryDate);
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.debug(STR_METHOD_NAME , l_ex);
//            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
//            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        }
//        catch(Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        } 
//    }
    
    /**
     * get担保ローン振替可能額
     * 返却値=MAX(余力計算結果.取引可能額, 0)
     */
    public void testGetSLChangePossAmtCase0003()
    {
        final String STR_METHOD_NAME = "testGetSLChangePossAmtCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("1");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);
            
            WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(GtlUtils.getSystemTimestamp());
            Date l_datDeliveryDate = l_bizDate.roll(1);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(
//                l_impl.getSLChangePossAmt(l_subAccount, l_datDeliveryDate)));

        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * )validate取引余力<その他商品>
     * 出金注文、証拠金への振替、為替保証金への振替、中国株式への振替、ｵﾘｯｸｽｸﾚｼﾞｯﾄへの振替 かつ 出金余力停止中の場合，
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("1");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("6", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 出金注文、証拠金への振替、為替保証金への振替、中国株式への振替、ｵﾘｯｸｽｸﾚｼﾞｯﾄへの振替 かつ
     * 預り金担保出金余力停止中の場合
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("1");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            SecurityShortageAccountParams l_securityShortageAccountParams =
                TestDBUtility.getSecurityShortageAccountRow();
            l_securityShortageAccountParams.setAccountId(333812512203L);
            l_securityShortageAccountParams.setPaymentStopDiv("1");
            TestDBUtility.insertWithDel(l_securityShortageAccountParams);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("10", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    
    /**
     * )validate取引余力<その他商品>
     * 出金注文、証拠金への振替、為替保証金への振替、中国株式への振替、ｵﾘｯｸｽｸﾚｼﾞｯﾄへの振替以外
     * かつ　@その他商品買付余力停止中の場合
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0003()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("1");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            SecurityShortageAccountParams l_securityShortageAccountParams =
                TestDBUtility.getSecurityShortageAccountRow();
            l_securityShortageAccountParams.setAccountId(333812512203L);
            l_securityShortageAccountParams.setPaymentStopDiv("1");
            TestDBUtility.insertWithDel(l_securityShortageAccountParams);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CASH_IN;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("4", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 証券担保ローン口座開設済顧客（出金拘束金レコード有） かつ 出金注文、証拠金への振替、
     * 為替保証金への振替、中国株式への振替 かつ 証券担保ローン金額ロック中の場合
     * 注文種別 = 1001：出金注文
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0004()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CASH_OUT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("11", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 証券担保ローン口座開設済顧客（出金拘束金レコード有） かつ 出金注文、証拠金への振替、
     * 為替保証金への振替、中国株式への振替 かつ 証券担保ローン金額ロック中の場合
     * 注文種別 = 1007：振替注文(預り金から株先証拠金)
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0005()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("11", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 証券担保ローン口座開設済顧客（出金拘束金レコード有） かつ 出金注文、証拠金への振替、
     * 為替保証金への振替、中国株式への振替 かつ 証券担保ローン金額ロック中の場合
     * 注文種別 = 1011：為替保証金振替注文（預り金から為替保証金）
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0006()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("11", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 証券担保ローン口座開設済顧客（出金拘束金レコード有） かつ 出金注文、証拠金への振替、
     * 為替保証金への振替、中国株式への振替 かつ 証券担保ローン金額ロック中の場合
     * 注文種別 = 1013：外国株式振替注文（預り金から外国株式口座）
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOtherCase0007()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOtherCase0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            l_newOrderSpecs[0] = new WEB3TPNewOrderSpec();
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TRANSFER_TO_FEQ;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("11", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 預り金担保出金余力停止中
     * 注文種別 = 1021：CFD振替注文（預り金からCFD口座））
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOther_C001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOther_C001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            SecurityShortageAccountParams l_securityShortageAccountParams =
                TestDBUtility.getSecurityShortageAccountRow();
            l_securityShortageAccountParams.setAccountId(333812512203L);
            //預り金担保出金余力停止中
            l_securityShortageAccountParams.setPaymentStopDiv("1");
            TestDBUtility.insertWithDel(l_securityShortageAccountParams);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            l_newOrderSpecs[0] = new WEB3TPNewOrderSpec();
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("10", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    /**
     * )validate取引余力<その他商品>
     * 出金余力停止中
     * その他商品買付余力停止中
     * 注文種別 = 1021：CFD振替注文（預り金からCFD口座））
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOther_C002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOther_C002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            //その他商品買付余力停止区分 == true
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            //出金余力停止中
            l_tradingpowerCalcConditionParams.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            l_newOrderSpecs[0] = new WEB3TPNewOrderSpec();
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("6", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    
    /**
     * )validate取引余力<その他商品>
     * 出金余力停止中
     * 注文種別 = 1021：CFD振替注文（預り金からCFD口座））
     * 取引余力結果を返却する
     */
    public void testValidateTradingPowerOther_C003()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerOther_C003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
            
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            l_newOrderSpecs[0] = new WEB3TPNewOrderSpec();
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPowerOther(l_subAccount, l_newOrderSpecs, l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("11", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    
    /**
     * validate取引余力
     * 引数.注文種別 = 1020：振替注文（預り金からオリックスクレジット）
     * 取引余力結果オブジェクトを返却する。
     */
    public void testValidateTradingPowerCase0001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);

            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoUpdateInterceptor l_interceptor = new WEB3IfoUpdateInterceptor();
            Object[] l_orderSpecIntercepters = new WEB3IfoUpdateInterceptor[1];
            l_orderSpecIntercepters[0] = l_interceptor;
            
            IfoChangeOpenContractOrderSpec l_spec = new IfoChangeOpenContractOrderSpec(
                1002, 1001, 200, 300);
            Object[] l_orderSpecs = new IfoChangeOpenContractOrderSpec[1];
            l_orderSpecs[0] = l_spec;
            
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.TO_ORIX_CREDIT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPower(
                    l_subAccount, l_orderSpecIntercepters, l_orderSpecs,l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("3", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    public void testValidateTradingPowerCase0011()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerCase0011";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);

            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoUpdateInterceptor l_interceptor = new WEB3IfoUpdateInterceptor();
            Object[] l_orderSpecIntercepters = new WEB3IfoUpdateInterceptor[1];
            l_orderSpecIntercepters[0] = l_interceptor;
            
            IfoChangeOpenContractOrderSpec l_spec = new IfoChangeOpenContractOrderSpec(
                1002, 1001, 200, 300);
            Object[] l_orderSpecs = new IfoChangeOpenContractOrderSpec[1];
            l_orderSpecs[0] = l_spec;
            
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FEQ_BUY;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPower(
                    l_subAccount, l_orderSpecIntercepters, l_orderSpecs,l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("3", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    public void testValidateTradingPowerCase0012()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerCase0012";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);

            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoUpdateInterceptor l_interceptor = new WEB3IfoUpdateInterceptor();
            Object[] l_orderSpecIntercepters = new WEB3IfoUpdateInterceptor[1];
            l_orderSpecIntercepters[0] = l_interceptor;
            
            IfoChangeOpenContractOrderSpec l_spec = new IfoChangeOpenContractOrderSpec(
                1002, 1001, 200, 300);
            Object[] l_orderSpecs = new IfoChangeOpenContractOrderSpec[1];
            l_orderSpecs[0] = l_spec;
            
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FEQ_SELL;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPower(
                    l_subAccount, l_orderSpecIntercepters, l_orderSpecs,l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(!l_result.isResultFlg());
//            assertEquals("3", l_result.getTpErrorInfo().tradinPowerErrorDiv);
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
//            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    public void testValidateTradingPowerCase0013()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerCase0013";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);

            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3FeqNewOrderSpec l_interceptor = new WEB3FeqNewOrderSpec(
                null, 
                true, 
                "1", 
                "1", 
                0, 
                0, 
                FeqExecutionConditionType.AT_MARKET_CLOSE, 
                new Date(), 
                TaxTypeEnum.NORMAL, 
                "1");
            Object[] l_orderSpecIntercepters = new WEB3FeqNewOrderSpec[1];
            l_orderSpecIntercepters[0] = l_interceptor;
            
            IfoChangeOpenContractOrderSpec l_spec = new IfoChangeOpenContractOrderSpec(
                1002, 1001, 200, 300);
            Object[] l_orderSpecs = new IfoChangeOpenContractOrderSpec[1];
            l_orderSpecs[0] = l_spec;
            
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.FEQ_SELL;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
//            WEB3TPTradingPowerResult l_result =
//                l_impl.validateTradingPower(
//                    l_subAccount, l_orderSpecIntercepters, l_orderSpecs,l_orderTypeEnum, l_blnUpdateFlg);
//            
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
//            assertFalse(!l_result.isResultFlg());
//            assertEquals("3", l_result.getTpErrorInfo().tradinPowerErrorDiv);
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
//            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
//            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    /**
     * get会社部店別余力計算条件の返却値がnullの場合
     */
    
    public void testGetTransferAmountToDeposit_case0001()
    {
        final String STR_METHOD_NAME = "testGetTransferAmountToDeposit_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImpl l_serviceImpl = new WEB3TPTradingPowerServiceImpl();
        try
        {
            WEB3TPCalcConditionForMock.mockCreateCalcConditionStandard(true,333812512246L,33381251220301L,"asdvasdas","3");
            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountId(33381251220301L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setAccountId(333812512246L);
            l_tpCalcResultMarginParams.setMarkToMarketDiv("0");
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1001);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(33L);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchCode("381");
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20071012", "yyyyMMdd");
            double l_dblPayAmount = 5;
            
            double l_dblResult = l_serviceImpl.getTransferAmountToDeposit(l_subAccount, l_datDeliveryDate, l_dblPayAmount);
            
            assertEquals(new Double(0.0), new Double(l_dblResult));
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get会社部店別余力計算条件の返却値が 1 の場合
     */
    
    public void testGetTransferAmountToDeposit_case0002()
    {
        final String STR_METHOD_NAME = "testGetTransferAmountToDeposit_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImpl l_serviceImpl = new WEB3TPTradingPowerServiceImplForTest();
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setAccountId(333812512246L);
            l_tpCalcResultMarginParams.setMarkToMarketDiv("0");
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1001);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20071012", "yyyyMMdd");
            double l_dblPayAmount = 5;
            
            double l_dblResult = l_serviceImpl.getTransferAmountToDeposit(l_subAccount, l_datDeliveryDate, l_dblPayAmount);
            
            assertEquals(new Double(5.0), new Double(l_dblResult));
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get会社部店別余力計算条件の返却値=2 の場合
     */
    
    public void testGetTransferAmountToDeposit_case0003()
    {
        final String STR_METHOD_NAME = "testGetTransferAmountToDeposit_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImpl l_serviceImpl = new WEB3TPTradingPowerServiceImpl();
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setAccountId(333812512246L);
            l_tpCalcResultMarginParams.setMarkToMarketDiv("0");
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1001);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("second.deposit.rate");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Date l_datDeliveryDate = Calendar.getInstance().getTime();
            double l_dblPayAmount = 16;
            
            double l_dblResult = l_serviceImpl.getTransferAmountToDeposit(l_subAccount, l_datDeliveryDate, l_dblPayAmount);
            
            assertEquals(new Double(0.0), new Double(l_dblResult));
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get会社部店別余力計算条件の返却値 = 3の場合
     */
    
    public void testGetTransferAmountToDeposit_case0004()
    {
        final String STR_METHOD_NAME = "testGetTransferAmountToDeposit_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImpl l_serviceImpl = new WEB3TPTradingPowerServiceImpl();
        try
        {
            WEB3TPCalcConditionForMock.mockCreateCalcConditionStandard(true,333812512246L,33381251220301L,"deposit.real.transfer.enforce.div","3");
            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountId(33381251220301L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setAccountId(333812512246L);
            l_tpCalcResultMarginParams.setMarkToMarketDiv("0");
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1001);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(33L);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchCode("381");
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_TradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//            l_TradingpowerCalcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_TradingpowerCalcConditionParams);
            
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            Date l_datDeliveryDate = WEB3DateUtility.getDate("20071012", "yyyyMMdd");
            double l_dblPayAmount = 5;
            
            double l_dblResult = l_serviceImpl.getTransferAmountToDeposit(l_subAccount, l_datDeliveryDate, l_dblPayAmount);
            
            assertEquals(new Double(0.0), new Double(l_dblResult));
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     參數補助口座為null 
     */
    public void testValidateContractForcedSettleBefOnlinetCase0001()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnlinetCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            WEB3GentradeSubAccount l_subAccount = null;

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();

            l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
    }
    
    /**
        validate建玉強制決済～オンライン開始前～  
        is信用口座開設 = false    
     */
    public void testValidateContractForcedSettleBefOnlinetCase0002()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnlinetCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();

            l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME , l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02887);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }  
    }
    
    /**
        validate建玉強制決済～オンライン開始前～  
        get会社部店別余力計算条件("first.margin.pass.day2")の戻り値 = null
        and get会社部店別余力計算条件("first.margin.pass.day1")の戻り値 = nullの場合
        建玉強制決済結果を返却
     */
    public void testValidateContractForcedSettleBefOnlinetCase0003()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnlinetCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512203L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);

            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setName("marginforcedsettle.seconddeposit2.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            l_branchPreferencesParams.setName("deposit.clear.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
 
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
 
            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);
 
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setAccountId(333812512203L);
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
 
            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
 
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_tpCalcResultMarginDetailParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
 
            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512203L);
            l_tpCashBalanceParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
 
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512203L);
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();

            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            assertFalse(l_result.resultFlg);
            assertNull(l_result.forcedSettleReason);
            assertNull(l_result.additionalMarginDate);
            assertNull(l_result.additionalMarginAccruedDays);
            assertNull(l_result.marginMaintenanceRate);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }

//    /**
//        validate建玉強制決済～オンライン開始前～  
//        get会社部店別余力計算条件("first.margin.pass.day2")の戻り値 != null and
//        入金請求管理.get第一水準追証経過日数(設定2) >
//        get会社部店別余力計算条件("first.margin.pass.day2")の戻り値の場合
//        建玉強制決済結果を返却
//     */
//    public void testValidateContractForcedSettleBefOnlinetCase0004()
//    {
//        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnlinetCase0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//       
//        
//        try
//        {
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//            
//            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
//            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
//            l_paymentRequisitMngParams.setAccountId(333812512203L);
//            l_paymentRequisitMngParams.setAccountCode("2512246");
//            l_paymentRequisitMngParams.setBranchCode("381");
//            l_paymentRequisitMngParams.setInstitutionCode("0D");
//            l_paymentRequisitMngParams.setFamilyName("Family Name");
//            l_paymentRequisitMngParams.setAccountAttribute("0");
//            l_paymentRequisitMngParams.setFirstMarginPassDay2(2);
//            l_paymentRequisitMngParams.setFirstMarginPassDay1(2);
//            l_paymentRequisitMngParams.setFirstMarginDate1(WEB3DateUtility.getDate("20071019","yyyyMMdd"));
//            l_paymentRequisitMngParams.setFirstMarginDate2(WEB3DateUtility.getDate("20071018","yyyyMMdd"));
//            l_paymentRequisitMngParams.setDepositPrebizdateRate(7);
//            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
//                TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
//            l_tradingpowerCalcConditionParams.setTradingStop("1");
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
//            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//            l_stockSecuredLoanParams.setAccountId(333812512203L);
//            l_stockSecuredLoanParams.setAccountOpenStatus("2");
//            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//            
//            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
//            
//            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
//            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
//                TestDBUtility.getSecurityCashoutRestraintRow();
//            l_securityCashoutRestraintRow.setAccountId(333812512203L);
//            l_securityCashoutRestraintRow.setAmountLockFlg("1");
//            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
//
//            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            l_ifoOrderParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(IfoProductParams.TYPE);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            l_branchPreferencesParams.setName("first.margin.pass.day2");
//            l_branchPreferencesParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
// 
//            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
// 
//            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            l_qrderexecutionEndParams.setInstitutionCode("0D");
//            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
//            l_qrderexecutionEndParams.setFutureOptionDiv("0");
//            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);
// 
//            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
//            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
//            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
//            l_tpCalcResultMarginParams.setAccountId(333812512203L);
//            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
// 
//            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
// 
//            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
//            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
//            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
//            l_tpCalcResultMarginDetailParams.setAccountId(333812512203L);
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
// 
//            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
//            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
//            l_tpCashBalanceParams.setAccountId(333812512203L);
//            l_tpCashBalanceParams.setSubAccountId(l_subAccountParams.getSubAccountId());
//            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
// 
//            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
//            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
//            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
//            l_aioOrderUnitParams.setAccountId(333812512203L);
//            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
//            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
//            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
//            l_aioOrderUnitParams.setOrderUnitId(0L);
//            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
//            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
//            
//            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
//
//            WEB3TPContractForcedSettleResult l_result = 
//                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
//            
//            assertTrue(l_result.resultFlg);
//            assertEquals(l_result.forcedSettleReason, "1");
//            assertEquals(WEB3DateUtility.formatDate(l_result.additionalMarginDate, "yyyyMMdd"), "20071018");
//            assertEquals(l_result.additionalMarginAccruedDays.intValue(), 2);
//            assertEquals(WEB3StringTypeUtility.formatNumber(l_result.marginMaintenanceRate.doubleValue()), "7");
//        }
//        catch(Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        } 
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//    }
//    
//    /**
//    validate建玉強制決済～オンライン開始前～  
//    get会社部店別余力計算条件("first.margin.pass.day1")の戻り値 != null and
//    入金請求管理.get第一水準追証経過日数(設定1) >
//    get会社部店別余力計算条件("first.margin.pass.day1")の戻り値の場合
//    建玉強制決済結果を返却
//     */
//    public void testValidateContractForcedSettleBefOnlinetCase0005()
//    {
//        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnlinetCase0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//       
//        
//        try
//        {
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//            
//            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
//            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
//            l_paymentRequisitMngParams.setAccountId(333812512203L);
//            l_paymentRequisitMngParams.setAccountCode("2512246");
//            l_paymentRequisitMngParams.setBranchCode("381");
//            l_paymentRequisitMngParams.setInstitutionCode("0D");
//            l_paymentRequisitMngParams.setFamilyName("Family Name");
//            l_paymentRequisitMngParams.setAccountAttribute("0");
//            l_paymentRequisitMngParams.setFirstMarginPassDay2(2);
//            l_paymentRequisitMngParams.setFirstMarginPassDay1(3);
//            l_paymentRequisitMngParams.setFirstMarginDate1(WEB3DateUtility.getDate("20071019","yyyyMMdd"));
//            l_paymentRequisitMngParams.setFirstMarginDate2(WEB3DateUtility.getDate("20071018","yyyyMMdd"));
//            l_paymentRequisitMngParams.setDepositPrebizdateRate(7);
//            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
//                TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
//            l_tradingpowerCalcConditionParams.setTradingStop("1");
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
//            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//            l_stockSecuredLoanParams.setAccountId(333812512203L);
//            l_stockSecuredLoanParams.setAccountOpenStatus("2");
//            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//            
//            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
//            
//            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
//            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
//                TestDBUtility.getSecurityCashoutRestraintRow();
//            l_securityCashoutRestraintRow.setAccountId(333812512203L);
//            l_securityCashoutRestraintRow.setAmountLockFlg("1");
//            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
//
//            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            l_ifoOrderParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(IfoProductParams.TYPE);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            l_branchPreferencesParams.setName("first.margin.pass.day1");
//            l_branchPreferencesParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
// 
//            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
// 
//            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            l_qrderexecutionEndParams.setInstitutionCode("0D");
//            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
//            l_qrderexecutionEndParams.setFutureOptionDiv("0");
//            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);
// 
//            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
//            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
//            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
//            l_tpCalcResultMarginParams.setAccountId(333812512203L);
//            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
// 
//            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
// 
//            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
//            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
//            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
//            l_tpCalcResultMarginDetailParams.setAccountId(333812512203L);
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
// 
//            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
//            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
//            l_tpCashBalanceParams.setAccountId(333812512203L);
//            l_tpCashBalanceParams.setSubAccountId(l_subAccountParams.getSubAccountId());
//            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
// 
//            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
//            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
//            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
//            l_aioOrderUnitParams.setAccountId(333812512203L);
//            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
//            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
//            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
//            l_aioOrderUnitParams.setOrderUnitId(0L);
//            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
//            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
//            
//            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
//
//            WEB3TPContractForcedSettleResult l_result = 
//                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
//            
//            assertTrue(l_result.resultFlg);
//            assertEquals(l_result.forcedSettleReason, "2");
//            assertEquals(WEB3DateUtility.formatDate(l_result.additionalMarginDate, "yyyyMMdd"), "20071019");
//            assertEquals(l_result.additionalMarginAccruedDays.intValue(), 3);
//            assertEquals(WEB3StringTypeUtility.formatNumber(l_result.marginMaintenanceRate.doubleValue()), "7");
//        }
//        catch(Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        } 
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//    }
//    
//    /**
//        validate建玉強制決済～オンライン開始前～  
//        get会社部店別余力計算条件("first.margin.pass.day2")の戻り値 != null and
//        入金請求管理.get第一水準追証経過日数(設定2) <
//        get会社部店別余力計算条件("first.margin.pass.day2")の戻り値の場合
//        建玉強制決済結果を返却
//     */
//    public void testValidateContractForcedSettleBefOnlinetCase0006()
//    {
//        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnlinetCase0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//       
//        
//        try
//        {
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//            
//            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
//            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
//            l_paymentRequisitMngParams.setAccountId(333812512203L);
//            l_paymentRequisitMngParams.setAccountCode("2512246");
//            l_paymentRequisitMngParams.setBranchCode("381");
//            l_paymentRequisitMngParams.setInstitutionCode("0D");
//            l_paymentRequisitMngParams.setFamilyName("Family Name");
//            l_paymentRequisitMngParams.setAccountAttribute("0");
//            l_paymentRequisitMngParams.setFirstMarginPassDay2(0);
//            l_paymentRequisitMngParams.setFirstMarginPassDay1(3);
//            l_paymentRequisitMngParams.setFirstMarginDate1(WEB3DateUtility.getDate("20071019","yyyyMMdd"));
//            l_paymentRequisitMngParams.setFirstMarginDate2(WEB3DateUtility.getDate("20071018","yyyyMMdd"));
//            l_paymentRequisitMngParams.setDepositPrebizdateRate(7);
//            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
//                TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
//            l_tradingpowerCalcConditionParams.setTradingStop("1");
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
//            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//            l_stockSecuredLoanParams.setAccountId(333812512203L);
//            l_stockSecuredLoanParams.setAccountOpenStatus("2");
//            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//            
//            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
//            
//            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
//            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
//                TestDBUtility.getSecurityCashoutRestraintRow();
//            l_securityCashoutRestraintRow.setAccountId(333812512203L);
//            l_securityCashoutRestraintRow.setAmountLockFlg("1");
//            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);
//
//            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
//            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//            l_ifoOrderParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(IfoProductParams.TYPE);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            l_branchPreferencesParams.setName("first.margin.pass.day2");
//            l_branchPreferencesParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
// 
//            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE);
// 
//            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            l_qrderexecutionEndParams.setInstitutionCode("0D");
//            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
//            l_qrderexecutionEndParams.setFutureOptionDiv("0");
//            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);
// 
//            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
//            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
//            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
//            l_tpCalcResultMarginParams.setAccountId(333812512203L);
//            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
// 
//            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
// 
//            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
//            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
//            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
//            l_tpCalcResultMarginDetailParams.setAccountId(333812512203L);
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
// 
//            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
//            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
//            l_tpCashBalanceParams.setAccountId(333812512203L);
//            l_tpCashBalanceParams.setSubAccountId(l_subAccountParams.getSubAccountId());
//            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
// 
//            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
//            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
//            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
//            l_aioOrderUnitParams.setAccountId(333812512203L);
//            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
//            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
//            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
//            l_aioOrderUnitParams.setOrderUnitId(0L);
//            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
//            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
//            
//            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
//
//            WEB3TPContractForcedSettleResult l_result = 
//                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
//            
//            assertFalse(l_result.resultFlg);
//            assertNull(l_result.forcedSettleReason);
//            assertNull(l_result.additionalMarginDate);
//            assertNull(l_result.additionalMarginAccruedDays);
//            assertNull(l_result.marginMaintenanceRate);
//        }
//        catch(Exception l_ex)
//        {
//            log.error(ERROR + l_ex.getMessage(), l_ex);
//            fail();
//        } 
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
//    }
//
//    public void testValidateContractForcedSettleIntermission_0001()
//    {
//        try
//        {
//            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
//            WEB3TPContractForcedSettleResult l_result = l_impl.validateContractForcedSettleIntermission(null);
//
//            assertFalse(l_result.resultFlg);
//            assertNull(l_result.forcedSettleReason);
//            assertNull(l_result.additionalMarginDate);
//            assertNull(l_result.additionalMarginAccruedDays);
//            assertNull(l_result.marginMaintenanceRate);
//        }
//        catch (Exception l_ex)
//        {
//            fail();
//        }
//    }

    /**
     * validate取引余力
     * 引数.注文種別 = 1021：CFD振替注文（預り金からCFD口座）
     * 取引余力結果オブジェクトを返却する。
     */
    public void testValidateTradingPowerCase0002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            l_tradingpowerCalcConditionParams.setTradingStop("1");
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAccountId(333812512203L);
            l_stockSecuredLoanParams.setAccountOpenStatus("2");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            
            TestDBUtility.deleteAll(SecurityShortageAccountRow.TYPE);
            
            TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
            SecurityCashoutRestraintParams l_securityCashoutRestraintRow = 
                TestDBUtility.getSecurityCashoutRestraintRow();
            l_securityCashoutRestraintRow.setAccountId(333812512203L);
            l_securityCashoutRestraintRow.setAmountLockFlg("1");
            TestDBUtility.insertWithDel(l_securityCashoutRestraintRow);

            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1002);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoUpdateInterceptor l_interceptor = new WEB3IfoUpdateInterceptor();
            Object[] l_orderSpecIntercepters = new WEB3IfoUpdateInterceptor[1];
            l_orderSpecIntercepters[0] = l_interceptor;
            
            IfoChangeOpenContractOrderSpec l_spec = new IfoChangeOpenContractOrderSpec(
                1002, 1001, 200, 300);
            Object[] l_orderSpecs = new IfoChangeOpenContractOrderSpec[1];
            l_orderSpecs[0] = l_spec;
            
            boolean l_blnUpdateFlg = false;
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPTradingPowerResult l_result =
                l_impl.validateTradingPower(
                    l_subAccount, l_orderSpecIntercepters, l_orderSpecs,l_orderTypeEnum, l_blnUpdateFlg);
            
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTradingPower()));
            assertFalse(l_result.isResultFlg());
            assertEquals("3", l_result.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().lackAccountBalance));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().buyOrderPossibleAmount));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_result.getTpErrorInfo().sellOrderPossibleQuantity));
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch(Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        } 
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");  
    }
    
    
    private class WEB3TPTradingPowerServiceImplForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
            WEB3GentradeSubAccount l_subAccount,
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
            Date l_datDeliveryDate)
        {
            return 30.2;
        }
    }
    
    /*
     * 引数.補助口座がnullの場合
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testValidateContractForcedSettleBefOnline_C0001()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeSubAccount l_subAccount = null;

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * is信用口座開設()の戻り値　@=　@false(現物顧客)の場合
     * WEB3ErrorCatalog.BUSINESS_ERROR_02887
     */
    public void testValidateContractForcedSettleBefOnline_C0002()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02887, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * 第一水準追証経過日数　@>　@(*2)第一水準追証有効経過日数
     */
    public void testValidateContractForcedSettleBefOnline_C0003()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);


            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(100);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            l_paymentRequisitMngParams.setFirstDepositPassDay(2);
            l_paymentRequisitMngParams.setFirstDepositOccurredDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("first.margin.pass.day1");
            l_branchPreferencesParams.setValue("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            assertTrue(l_result.resultFlg);
            assertEquals("2", l_result.forcedSettleReason);
            assertEquals(WEB3DateUtility.getDate("20080808", "yyyyMMdd"), l_result.additionalMarginDate);
            assertEquals(new Integer(3), l_result.additionalMarginAccruedDays);
            assertEquals(new Double(0), l_result.marginMaintenanceRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * 第二水準追証金額(未入金)　@>　@0　@の場合
     */
    public void testValidateContractForcedSettleBefOnline_C0004()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);


            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(100);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setName("marginforcedsettle.seconddeposit2.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            assertTrue(l_result.resultFlg);
            assertEquals("4", l_result.forcedSettleReason);
            assertNull(l_result.additionalMarginDate);
            assertNull(l_result.additionalMarginAccruedDays);
            assertNull(l_result.marginMaintenanceRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * (第一水準追証経過日数　@>　@(*2)第一水準追証有効経過日数, 第二水準追証金額(未入金)　@>　@0　@の場合)以外場合
     */
    public void testValidateContractForcedSettleBefOnline_C0005()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);


            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(-1);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setName("marginforcedsettle.seconddeposit2.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            assertTrue(l_result.resultFlg);
            assertEquals("4", l_result.forcedSettleReason);
            assertNull(l_result.additionalMarginDate);
            assertNull(l_result.additionalMarginAccruedDays);
            assertNull(l_result.marginMaintenanceRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    public void testValidateContractForcedSettleBefOnline_C0006()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);


            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(-1);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams.setName("marginforcedsettle.seconddeposit2.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setBranchId(33381);
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleBefOnline(l_subAccount);
            
            assertFalse(l_result.resultFlg);
            assertNull(l_result.forcedSettleReason);
            assertNull(l_result.additionalMarginDate);
            assertNull(l_result.additionalMarginAccruedDays);
            assertNull(l_result.marginMaintenanceRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    /*
     * 引数.補助口座がnullの場合
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testValidateContractForcedSettleIntermission_C0001()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleIntermission_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeSubAccount l_subAccount = null;

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            l_impl.validateContractForcedSettleIntermission(l_subAccount);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * is信用口座開設()の戻り値　@=　@false(現物顧客)の場合
     * WEB3ErrorCatalog.BUSINESS_ERROR_02887
     */
    public void testValidateContractForcedSettleIntermission_C0002()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleIntermission_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            l_impl.validateContractForcedSettleIntermission(l_subAccount);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02887, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * 第二水準追証金額(請求2)　@>　@0の場合
     */
    public void testValidateContractForcedSettleIntermission_C0003()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);


            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(100);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleIntermission(l_subAccount);
            
            assertTrue(l_result.resultFlg);
            assertEquals("3", l_result.forcedSettleReason);
            assertEquals(WEB3DateUtility.getDate("20070312", "yyyyMMdd"), l_result.additionalMarginDate);
            assertEquals(new Integer(3), l_result.additionalMarginAccruedDays);
            assertEquals(new Double(0), l_result.marginMaintenanceRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    /*
     * 第二水準追証金額(請求2)　@>　@0以外の場合
     */
    public void testValidateContractForcedSettleIntermission_C0004()
    {
        final String STR_METHOD_NAME = "testValidateContractForcedSettleBefOnline_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);


            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(0);
            l_paymentRequisitMngParams.setSecondDeposit2(0);
            l_paymentRequisitMngParams.setSecondDeposit1(0);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_mainAccount, l_subAccountParams);

            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPContractForcedSettleResult l_result = 
                l_impl.validateContractForcedSettleIntermission(l_subAccount);
            
            assertFalse(l_result.resultFlg);
            assertNull(l_result.forcedSettleReason);
            assertNull(l_result.additionalMarginDate);
            assertNull(l_result.additionalMarginAccruedDays);
            assertNull(l_result.marginMaintenanceRate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);  
    }
    
    
    //is第二水準追証発生  = true的場合
    //正確的取引余力結果を返却する。
    public void testValidateTradingPowerMargin_C001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerMargin_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
           
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = new MarketParams();
            l_marketParams = TestDBUtility.getMarketRow();
            l_processor.doDeleteAllQuery(MarketParams.TYPE);
            l_marketParams.setMarketId(0);
            TestDBUtility.insertWithDel(l_marketParams);
            
            ProductParams l_productParams = new ProductParams();
            l_processor.doDeleteAllQuery(ProductParams.TYPE);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(0);
            l_productParams.setProductType(ProductTypeEnum.AIO);
            TestDBUtility.insertWithDel(l_productParams);
            
            FeqProductParams l_feqProductParams = new FeqProductParams();
            l_processor.doDeleteAllQuery(FeqProductParams.TYPE);
            l_feqProductParams = TestDBUtility.getFeqProductRow();
            l_feqProductParams.setProductId(0);
            
            TestDBUtility.insertWithDel(l_feqProductParams);
            
            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);
                       
            TradedProductParams l_tradedProductParams = new TradedProductParams();
            l_processor.doDeleteAllQuery(TradedProductParams.TYPE);
            l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(0);
            l_tradedProductParams.setMarketId(0);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            EqtypeTradedProductParams l_eqtypeTradedProductParams = new EqtypeTradedProductParams();
            l_processor.doDeleteAllQuery(EqtypeTradedProductParams.TYPE);
            l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(0);
            l_eqtypeTradedProductParams.setMarketId(0);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            EqtypeFixedContractParams l_eqtypeFixedContractParams =  new EqtypeFixedContractParams();
            l_processor.doDeleteAllQuery(EqtypeFixedContractParams.TYPE);
//            l_eqtypeFixedContractParams.setFixedContractId(11);
//            l_eqtypeFixedContractParams.setAccountId(333812512246L);
//            l_eqtypeFixedContractParams.setSubAccountId(33381251220301L);
//            l_eqtypeFixedContractParams.setMarketId(1);
//            l_eqtypeFixedContractParams.setOriginalQuantity(1);
//            l_eqtypeFixedContractParams.setQuantity(1);
//            l_eqtypeFixedContractParams.setOriginalContractPrice(1);
//            l_eqtypeFixedContractParams.setContractPrice(1);
//            l_eqtypeFixedContractParams.setContractType(ContractTypeEnum.LONG);
//            l_eqtypeFixedContractParams.setOpenDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
//            l_eqtypeFixedContractParams.setCloseDate(WEB3DateUtility.getDate("20070828","yyyyMMdd"));
//            l_eqtypeFixedContractParams.setSetupFee(0);
//            l_eqtypeFixedContractParams.setSetupFeeTax(0);
//            l_eqtypeFixedContractParams.setManagementFee(0);
//            l_eqtypeFixedContractParams.setManagementFeeTax(0);
//            l_eqtypeFixedContractParams.setInterestFee(0);
//            l_eqtypeFixedContractParams.setInterestFeeTax(0);
//            l_eqtypeFixedContractParams.setProductId(0);
//            l_eqtypeFixedContractParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_eqtypeFixedContractParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_eqtypeFixedContractParams.setFirstOpenDate(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_eqtypeFixedContractParams);
            
            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            FeqTradedProductParams l_feqTradedProductParams = new FeqTradedProductParams();
            l_processor.doDeleteAllQuery(FeqTradedProductParams.TYPE);
            l_feqTradedProductParams = TestDBUtility.getFeqTradedProductRow();
            l_feqTradedProductParams.setTradedProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_feqTradedProductParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(100);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccountParams l_subAccountParams1 = new SubAccountParams();
            l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(33381251220302L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);


            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
            l_processor.doDeleteAllQuery(EqtypeProductParams.TYPE);
            l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(0);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

//            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams = new EqtypeClosingContractSpecParams();
            l_processor.doDeleteAllQuery(EqtypeClosingContractSpecParams.TYPE);
//            l_eqtypeClosingContractSpecParams = TestDBUtility.getEqtypeClosingContractSpecRow();
//            l_eqtypeClosingContractSpecParams.setAccountId(333812512246L);
//            l_eqtypeClosingContractSpecParams.setSubAccountId(33381251220301L);
//            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);
            //実行
//            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
//                createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            
            WEB3TPNewOrderSpec l = new WEB3TPNewOrderSpec();
            l.setOrderCategory(OrderCategEnum.ASSET);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[]{l};
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            WEB3TPTradingPowerResult l_tpResult =
                l_impl.validateTradingPowerMargin(l_subAccount, l_newOrderSpecs, true);
            assertEquals(false, l_tpResult.isResultFlg());
            assertEquals(0, l_tpResult.getTradingPower(), 0);
            assertEquals("7", l_tpResult.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals(0, l_tpResult.getTpErrorInfo().lackAccountBalance, 0);
            assertEquals(0, l_tpResult.getTpErrorInfo().buyOrderPossibleAmount, 0);
            assertEquals(0, l_tpResult.getTpErrorInfo().sellOrderPossibleQuantity, 0);
            assertEquals(null, l_tpResult.getTpErrorInfo().marginSecInfo);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //get会社部店別余力計算条件()の戻り値が 1：実施する場合  
    public void testValidateTradingPowerMargin_C002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerMargin_C002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(100);
            l_paymentRequisitMngParams.setSecondDeposit2(200);
            l_paymentRequisitMngParams.setSecondDeposit1(300);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("second.deposit.marginopen.tpstop");
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
//            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
//                createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            WEB3TPTradingPowerResult l_tpResult =
                l_impl.validateTradingPowerMargin(l_subAccount, l_newOrderSpecs, true);
            assertEquals(false, l_tpResult.isResultFlg());
            assertEquals(0, l_tpResult.getTradingPower(), 0);
            assertEquals("5", l_tpResult.getTpErrorInfo().tradinPowerErrorDiv);
            assertEquals(0, l_tpResult.getTpErrorInfo().lackAccountBalance, 0);
            assertEquals(0, l_tpResult.getTpErrorInfo().buyOrderPossibleAmount, 0);
            assertEquals(0, l_tpResult.getTpErrorInfo().sellOrderPossibleQuantity, 0);
            assertEquals(null, l_tpResult.getTpErrorInfo().marginSecInfo);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    get会社部店別余力計算条件()の戻り値 = "2"
//    不足(T+0) = 不足(T+1)の場合
//    ・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+0), 0)
    
    public void testGetTransferAmountToDeposit_C001()
    {
        final String STR_METHOD_NAME =
            "testGetTransferAmountToDeposit_C001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImplForTest1 l_impl = new WEB3TPTradingPowerServiceImplForTest1();

        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2356D;
        
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
            assertEquals(51.0356, l_dbl, 4);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
//  get会社部店別余力計算条件()の戻り値 = "2"
//  不足(T+0) < 不足(T+1)の場合
//  ・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+0), 0)
    public void testGetTransferAmountToDeposit_C002()
    {
        final String STR_METHOD_NAME =
            "testGetTransferAmountToDeposit_C002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImplForTest1 l_impl = new WEB3TPTradingPowerServiceImplForTest1();
        //l_impl.l_strMethodName = "testGetTransferAmountToDeposit_C002";
        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2356D;
        
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
            assertEquals(51.0356, l_dbl, 4);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
//    get会社部店別余力計算条件()の戻り値 = "2"
//        不足(T+1) > 不足(T+1)の場合
//              a）引数.保証金振替額 + 不足(T+0) - 追証未解消金額 < 0
//                 ・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+0), 0)
    public void testGetTransferAmountToDeposit_C003()
    {
        final String STR_METHOD_NAME =
            "testGetTransferAmountToDeposit_C003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImplForTest2 l_impl = new WEB3TPTradingPowerServiceImplForTest2();
        //l_impl.l_strMethodName = "testGetTransferAmountToDeposit_C002";
        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2356D;
        
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
            assertEquals(81.2356, l_dbl, 4);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
//    get会社部店別余力計算条件()の戻り値 = "2"
//        不足(T+1) > 不足(T+1)の場合
//              a）引数.保証金振替額 + 不足(T+0) - 追証未解消金額 =0
//                 ・保証金振替額 = MAX(引数.保証金振替額 + 不足(T+0), 0)
    public void testGetTransferAmountToDeposit_C004()
    {
        final String STR_METHOD_NAME =
            "testGetTransferAmountToDeposit_C004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImplForTest2 l_impl = new WEB3TPTradingPowerServiceImplForTest2();
        //l_impl.l_strMethodName = "testGetTransferAmountToDeposit_C002";
        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2356D;
        
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(80.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
            assertEquals(81.2356, l_dbl, 4);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
//    get会社部店別余力計算条件()の戻り値 = "2"
//        不足(T+1) > 不足(T+1)の場合
//              b）引数.保証金振替額 + (*1)実質顧客勘定残高 - 追証未解消金額 > 0
//                 ・保証金振替額 = MAX(引数.保証金振替額 - 不足(T+1), 追証未解消金額, 0)
    public void testGetTransferAmountToDeposit_C005()
    {
        final String STR_METHOD_NAME =
            "testGetTransferAmountToDeposit_C005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImplForTest2 l_impl = new WEB3TPTradingPowerServiceImplForTest2();
        //l_impl.l_strMethodName = "testGetTransferAmountToDeposit_C002";
        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2356D;
        
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
            assertEquals(51.2356, l_dbl, 4);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
      
//    受渡日 < 余力計算条件.get営業日（0）の場合
//    例外をスローする
    public void testGetPaymentTradingPowerForCheck_C001()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C001";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate =WEB3DateUtility.getDate("20010716", "yyyyMMdd");
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        fail();
    }
    catch (WEB3BusinessLayerException l_ex)
    {
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02336, l_ex.getErrorInfo());
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
    }
    
//    受渡日 > 余力計算条件.get営業日（5）の場合
//    余力計算基準日 = 5
    public void testGetPaymentTradingPowerForCheck_C002()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C002";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
//    受渡日が上記以外の場
//    余力計算基準日 = calc指定日の戻り値
    public void testGetPaymentTradingPowerForCheck_C003()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C003";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
    
    //現物顧客の場合
    public void testGetPaymentTradingPowerForCheck_C004()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C004";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TestDBUtility.deleteAll(TpCalcResultEquityDetailRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductId(0L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("deposit.real.transfer.enforce.div");
            l_branchPreferencesParams.setValue("2");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
    
//    信用顧客の場合
//    出金日の出金可能額を取得する。場合
//    is取引停止区分()がtrueの場合
//    -1を返却する。
    public void testGetPaymentTradingPowerForCheck_C005()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C005";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("cashout.tradingpower.check");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
    
//    信用顧客の場合
//    出金日の出金可能額を取得する。場合
//    isその他商品買付余力区分()、がtrueの場合
//    -1を返却する。
    public void testGetPaymentTradingPowerForCheck_C006()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C006";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("cashout.tradingpower.check");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
    
//    信用顧客の場合
//    出金日の出金可能額を取得する。場合
//    is出金余力区分()の戻り値のいずれかがtrueの場合
//    -1を返却する。
    public void testGetPaymentTradingPowerForCheck_C007()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C007";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("cashout.tradingpower.check");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
    
//    信用顧客の場合
//    出金日の出金可能額を取得する。場合
//    calcその他商品買付可能額()の戻り値 < 0 の場合
//    -1を返却する。
    public void testGetPaymentTradingPowerForCheck_C008()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C008";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("cashout.tradingpower.check");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(-1, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }
    
//    信用顧客の場合
//    出金日の出金可能額を取得する。場合
//    calcその他商品買付可能額()の戻り値 >= 0 の場合
//    -1を返却する。
    public void testGetPaymentTradingPowerForCheck_C009()
    {
        final String STR_METHOD_NAME = "testGetPaymentTradingPowerForCheck_C009";
        log.entering(STR_METHOD_NAME);
        Date l_datDeliveryDate = WEB3DateUtility.getDate("20081028", "yyyyMMdd");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2008);
            l_calendar.set(Calendar.MONTH,9);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,9);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
            l_tradingpowerCalcConditionParams.setPaymentStop("0");
            l_tradingpowerCalcConditionParams.setTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);   
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(99999999);
            l_tpCalcResultMarginParams.setAccountBalance0(100);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("cashout.tradingpower.check");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
               
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setSecondDepositNonPay(1);
            l_paymentRequisitMngParams.setSecondDeposit2(50.2356);
            l_paymentRequisitMngParams.setSecondDeposit1(81.2356);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
        double l_dblReturn = l_impl.getPaymentTradingPowerForCheck(l_subAccount, l_datDeliveryDate);
        assertEquals(95, l_dblReturn, 0);
    }
    catch (Exception l_ex)
    {
        fail();
        log.exiting(STR_METHOD_NAME);
    }
        log.exiting(STR_METHOD_NAME);
   }

    //test isReceiptDepositRateOver
    public void testIsReceiptDepositRateOver_001()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            long l_lngProductId = 0L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(false, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //test isReceiptDepositRateOver
    public void testIsReceiptDepositRateOver_002()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_002";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "0");
            long l_lngProductId = 0L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(false, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //test isReceiptDepositRateOver
    public void testIsReceiptDepositRateOver_003()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            WEB3TPContractInfo l_tpContractInfo = new WEB3TPContractInfo();
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "20000");
            long l_lngProductId = 0L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            l_tpUpd.contractInfo = l_tpContractInfo;
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            TpCalcResultMarginParams l_calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            l_calcResultMargin.setAccountBalance5(145);
            l_tpCalcMargin.setCalcResultMargin(l_calcResultMargin);
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(false, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug("" + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //test isReceiptDepositRateOver
    public void testIsReceiptDepositRateOver_004()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("20000000");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_tsBizDate = new Timestamp(WEB3DateUtility.getDate("20040716", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    l_tsBizDate);

            
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "20139.87");
            Date[] l_datBizDate = new Date[9];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040706", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20040709", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20040712", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20040713", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20040714", "yyyyMMdd");
            l_datBizDate[7] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[8] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_tpCalcCondition.setBizDate(l_datBizDate);
            WEB3TPContractInfo l_tpContractInfo = new WEB3TPContractInfo();
            l_tpContractInfo.setCalcCondition(l_tpCalcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setProductId(10001L);
            l_targetContractDetail.setContractPrice(120);
            l_targetContractDetail.setQuantity(100);
            l_targetContractDetail.setOriginalQuantity(200);
            l_targetContractDetail.setMarginDepositRate(120);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_historyPerContract.setContractInfo(l_tpContractInfo);
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_tpContractInfo, l_targetContracts);
            
            Method l_method =
                WEB3TPContractInfo.class.getDeclaredMethod(
                    "addHistoryPerContract",
                    new Class[]{WEB3TPTargetContract.class, WEB3TPHistoryPerContract.class});
            l_method.setAccessible(true);
            l_method.invoke(l_tpContractInfo, new Object[]{l_targetContract, l_historyPerContract});
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            long l_lngProductId = 10001L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            l_tpUpd.contractInfo = l_tpContractInfo;
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            TpCalcResultMarginParams l_calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            l_calcResultMargin.setAccountBalance5(145);
            l_tpCalcMargin.setCalcResultMargin(l_calcResultMargin);
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(false, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //test isReceiptDepositRateOver
    public void testIsReceiptDepositRateOver_005()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("20000000");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_tsBizDate = new Timestamp(WEB3DateUtility.getDate("20040716", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    l_tsBizDate);

            
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "201");
            Date[] l_datBizDate = new Date[9];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040706", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20040709", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20040712", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20040713", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20040714", "yyyyMMdd");
            l_datBizDate[7] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[8] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_tpCalcCondition.setBizDate(l_datBizDate);
            WEB3TPContractInfo l_tpContractInfo = new WEB3TPContractInfo();
            l_tpContractInfo.setCalcCondition(l_tpCalcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setProductId(10001L);
            l_targetContractDetail.setContractPrice(120);
            l_targetContractDetail.setQuantity(100);
            l_targetContractDetail.setOriginalQuantity(200);
            l_targetContractDetail.setMarginDepositRate(120);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_historyPerContract.setContractInfo(l_tpContractInfo);
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_tpContractInfo, l_targetContracts);
            
            Method l_method =
                WEB3TPContractInfo.class.getDeclaredMethod(
                    "addHistoryPerContract",
                    new Class[]{WEB3TPTargetContract.class, WEB3TPHistoryPerContract.class});
            l_method.setAccessible(true);
            l_method.invoke(l_tpContractInfo, new Object[]{l_targetContract, l_historyPerContract});
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            long l_lngProductId = 10001L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            l_tpUpd.contractInfo = l_tpContractInfo;
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            TpCalcResultMarginParams l_calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            l_calcResultMargin.setAccountBalance5(145);
            l_tpCalcMargin.setCalcResultMargin(l_calcResultMargin);
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(true, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * calc受入保証金() <= 0 の場合(calc受入保証金() < 0)
     * calc銘柄ごと必要保証金() > 0 の場合
     */
    public void testIsReceiptDepositRateOver_006()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("20000000");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_tsBizDate = new Timestamp(WEB3DateUtility.getDate("20040716", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    l_tsBizDate);

            
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "201");
            Date[] l_datBizDate = new Date[9];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040706", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20040709", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20040712", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20040713", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20040714", "yyyyMMdd");
            l_datBizDate[7] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[8] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_tpCalcCondition.setBizDate(l_datBizDate);
            WEB3TPContractInfo l_tpContractInfo = new WEB3TPContractInfo();
            l_tpContractInfo.setCalcCondition(l_tpCalcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setProductId(10001L);
            l_targetContractDetail.setContractPrice(120);
            l_targetContractDetail.setQuantity(100);
            l_targetContractDetail.setOriginalQuantity(200);
            l_targetContractDetail.setMarginDepositRate(120);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_historyPerContract.setContractInfo(l_tpContractInfo);
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_tpContractInfo, l_targetContracts);
            
            Method l_method =
                WEB3TPContractInfo.class.getDeclaredMethod(
                    "addHistoryPerContract",
                    new Class[]{WEB3TPTargetContract.class, WEB3TPHistoryPerContract.class});
            l_method.setAccessible(true);
            l_method.invoke(l_tpContractInfo, new Object[]{l_targetContract, l_historyPerContract});
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            long l_lngProductId = 10001L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            l_tpUpd.contractInfo = l_tpContractInfo;
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            TpCalcResultMarginParams l_calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            l_calcResultMargin.setAccountBalance5(0);
            l_tpCalcMargin.setCalcResultMargin(l_calcResultMargin);
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(true, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * calc受入保証金() <= 0 の場合(calc受入保証金() = 0)
     * calc銘柄ごと必要保証金() <= 0 の場合(calc銘柄ごと必要保証金() = 0)
     */
    public void testIsReceiptDepositRateOver_007()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_007";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("20000000");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_tsBizDate = new Timestamp(WEB3DateUtility.getDate("20040716", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    l_tsBizDate);

            
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "201");
            Date[] l_datBizDate = new Date[9];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040706", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20040709", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20040712", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20040713", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20040714", "yyyyMMdd");
            l_datBizDate[7] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[8] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_tpCalcCondition.setBizDate(l_datBizDate);
            WEB3TPContractInfo l_tpContractInfo = new WEB3TPContractInfo();
            l_tpContractInfo.setCalcCondition(l_tpCalcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setProductId(10001L);
            l_targetContractDetail.setContractPrice(0);
            l_targetContractDetail.setQuantity(100);
            l_targetContractDetail.setOriginalQuantity(0);
            l_targetContractDetail.setMarginDepositRate(0);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_historyPerContract.setContractInfo(l_tpContractInfo);
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_tpContractInfo, l_targetContracts);
            
            Method l_method =
                WEB3TPContractInfo.class.getDeclaredMethod(
                    "addHistoryPerContract",
                    new Class[]{WEB3TPTargetContract.class, WEB3TPHistoryPerContract.class});
            l_method.setAccessible(true);
            l_method.invoke(l_tpContractInfo, new Object[]{l_targetContract, l_historyPerContract});
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            long l_lngProductId = 10001L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            l_tpUpd.contractInfo = l_tpContractInfo;
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            TpCalcResultMarginParams l_calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            l_calcResultMargin.setAccountBalance5(0);
            l_calcResultMargin.setTodayUnexecutedAmount5(0);
            l_calcResultMargin.setTodayExecutedAmount5(0);
            l_calcResultMargin.setOtherRestraint5(1);
            l_tpCalcMargin.setCalcResultMargin(l_calcResultMargin);
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(false, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * calc受入保証金() <= 0 の場合(calc受入保証金() = 0)
     * calc銘柄ごと必要保証金() <= 0 の場合(calc銘柄ごと必要保証金() < 0)
     */
    public void testIsReceiptDepositRateOver_008()
    {
        final String STR_METHOD_NAME = "testIsReceiptDepositRateOver_008";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("receipt.margin.deposit.highlimit.rate");
            l_branchPreferencesParams.setValue("20000000");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_tsBizDate = new Timestamp(WEB3DateUtility.getDate("20040716", "yyyyMMdd").getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    l_tsBizDate);

            
            WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcCondition();
            l_tpCalcCondition.addInstBranCalcCondition("receipt.margin.deposit.highlimit.rate", "201");
            Date[] l_datBizDate = new Date[9];
            l_datBizDate[0] = WEB3DateUtility.getDate("20040706", "yyyyMMdd");
            l_datBizDate[1] = WEB3DateUtility.getDate("20040707", "yyyyMMdd");
            l_datBizDate[2] = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_datBizDate[3] = WEB3DateUtility.getDate("20040709", "yyyyMMdd");
            l_datBizDate[4] = WEB3DateUtility.getDate("20040712", "yyyyMMdd");
            l_datBizDate[5] = WEB3DateUtility.getDate("20040713", "yyyyMMdd");
            l_datBizDate[6] = WEB3DateUtility.getDate("20040714", "yyyyMMdd");
            l_datBizDate[7] = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            l_datBizDate[8] = WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            l_tpCalcCondition.setBizDate(l_datBizDate);
            WEB3TPContractInfo l_tpContractInfo = new WEB3TPContractInfo();
            l_tpContractInfo.setCalcCondition(l_tpCalcCondition);
            
            WEB3TPTargetContractDetail l_targetContractDetail = new WEB3TPTargetContractDetail();
            l_targetContractDetail.setProductId(10001L);
            l_targetContractDetail.setContractPrice(-120);
            l_targetContractDetail.setQuantity(100);
            l_targetContractDetail.setOriginalQuantity(200);
            l_targetContractDetail.setMarginDepositRate(120);
            WEB3TPTargetContract l_targetContract = new WEB3TPTargetContract();
            l_targetContract.setTargetContractDetail(l_targetContractDetail);
            List l_targetContracts = new ArrayList();
            l_targetContracts.add(l_targetContract);
            
            WEB3TPHistoryPerContract l_historyPerContract = new WEB3TPHistoryPerContract();
            l_historyPerContract.setContractInfo(l_tpContractInfo);
            l_historyPerContract.setTargetContract(l_targetContract);
            
            Field field = WEB3TPContractInfo.class.getDeclaredField("targetContracts");
            field.setAccessible(true);
            field.set(l_tpContractInfo, l_targetContracts);
            
            Method l_method =
                WEB3TPContractInfo.class.getDeclaredMethod(
                    "addHistoryPerContract",
                    new Class[]{WEB3TPTargetContract.class, WEB3TPHistoryPerContract.class});
            l_method.setAccessible(true);
            l_method.invoke(l_tpContractInfo, new Object[]{l_targetContract, l_historyPerContract});
            
            WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();
            long l_lngProductId = 10001L;
            WEB3TPTradingPowerUpd l_tpUpd = new WEB3TPTradingPowerUpd();
            l_tpUpd.contractInfo = l_tpContractInfo;
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            TpCalcResultMarginParams l_calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            l_calcResultMargin.setAccountBalance5(0);
            l_calcResultMargin.setTodayUnexecutedAmount5(0);
            l_calcResultMargin.setTodayExecutedAmount5(0);
            l_calcResultMargin.setOtherRestraint5(1);
            l_tpCalcMargin.setCalcResultMargin(l_calcResultMargin);
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            boolean l_blnReturn = l_impl.isReceiptDepositRateOver(l_lngProductId, l_tpUpd, l_tpCalcMargin);
            assertEquals(false, l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void test_getNextBizDateShortfall_C0001()
    {
        final String STR_METHOD_NAME =
            "test_getNextBizDateShortfall_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImpl l_impl = new WEB3TPTradingPowerServiceImpl();

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(20);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,10L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getNextBizDateShortfall(l_subAccount);
            assertEquals("" + 1.0D,"" + l_dbl);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    private class WEB3TPTradingPowerServiceImplForTest1 extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
            WEB3GentradeSubAccount l_subAccount,
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
            Date l_datDeliveryDate)
        {
            if (WEB3DateUtility.compare(l_datDeliveryDate, Calendar.getInstance().getTime()) == 1)
            {
                return 32;
            }
            else
            {
                return -30.2;
            }
        }
        public String l_strMethodName;
    }
    private class WEB3TPTradingPowerServiceImplForTest2 extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
            WEB3GentradeSubAccount l_subAccount,
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
            Date l_datDeliveryDate)
        {
            if (!(WEB3DateUtility.compare(l_datDeliveryDate, Calendar.getInstance().getTime()) == 1))
            {
                return 32;
            }
            else
            {
                return -30.2;
            }
        }
        public String l_strMethodName;
    }

//
//    /**
//     * 【WEB3】(信用新規建)必要保証金拘束日変更依頼(T＋２⇒T)
//     *
//     */
//    public void testCalcNextBizDateShortfall001()
//    {
//        final String STR_METHOD_NAME = "testCalcNextBizDateShortfall001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAcc = TestDBUtility.getSubAccountRow();
//            l_subAcc.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_subAcc);
//
//            TestDBUtility.deleteAll(DepositAutotransferStopRow.TYPE);
//            DepositAutotransferStopParams l_depositAutotransferStopParams =
//                TestDBUtility.getDepositAutotransferStopRow();
//            TestDBUtility.insertWithDel(l_depositAutotransferStopParams);
//            
//            l_depositAutotransferStopParams =
//                TestDBUtility.getDepositAutotransferStopRow();
//            l_depositAutotransferStopParams.setDepositAutotransferStopId(223456789123456789l);
//            l_depositAutotransferStopParams.setAutotransferStopStart(WEB3DateUtility.getDate("20030924","yyyyMMdd"));
//            TestDBUtility.insertWithDel(l_depositAutotransferStopParams);
//            
//            WEB3GentradeSubAccount l_subAccont = 
//                new WEB3GentradeSubAccount(333812512246L,33381251220301L);
//            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
//
//            WEB3TPCalcCondition wtpcc = new WEB3TPCalcCondition();
//            wtpcc.setLegalMinMarginDeposit(32.1);
//            l_margin.setCalcCondition(wtpcc);
//            WEB3TPTradingPowerServiceImpl l_impl =
//                new WEB3TPTradingPowerServiceImpl();
//            double result = l_impl.calcNextBizDateShortfall(l_subAccont, l_margin);
//
//            this.assertEquals("0.0", String.valueOf(result));
//        }
//        catch(Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();  
//        }   
//    }
//    
    
    public void testCalcNextBizDateShortfall002()
    {
        final String STR_METHOD_NAME = "testCalcNextBizDateShortfall002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAcc = TestDBUtility.getSubAccountRow();
            l_subAcc.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAcc);

            TestDBUtility.deleteAll(DepositAutotransferStopRow.TYPE);

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            //余力計算結果テーブル（信用顧客）
            TpCalcResultMarginParams tpcp =  TestDBUtility.getTpCalcResultMarginRow();
            //余力計算結果テーブル（信用顧客）.預り金（T+0）             1.1
            tpcp.setAccountBalance0(1.1);
            //余力計算結果テーブル（信用顧客）.預り金（T+1） 2.2
            tpcp.setAccountBalance1(2.2);
            //余力計算結果テーブル（信用顧客）.当日約定済代金（T+0） 3.3 today_executed_amount_0
            tpcp.setTodayExecutedAmount0(3.3);
            //余力計算結果テーブル（信用顧客）.当日約定済代金（T+1） 4.1  today_executed_amount_1
            tpcp.setTodayExecutedAmount1(4.1);
            //余力計算結果テーブル（信用顧客）.当日未約定代金（T+0） 5.2  today_unexecuted_amount_0
            tpcp.setTodayUnexecutedAmount0(5.2);
            //余力計算結果テーブル（信用顧客）.当日未約定代金（T+1） today_unexecuted_amount_1 6.4
            tpcp.setTodayUnexecutedAmount1(6.4);
            //余力計算結果テーブル（信用顧客）.日計り拘束金（T+1） day_trade_restraint_1 7.2
            tpcp.setDayTradeRestraint1(7.2);
            //余力計算結果テーブル（信用顧客）.代用証券評価額（T+1） 8.5  substitute_security_asset_1
            tpcp.setSubstituteSecurityAsset1(8.5);
            //余力計算結果テーブル（信用顧客）.未決済建玉代金（T+1） 9.1  contract_amount_1
            tpcp.setContractAmount1(9.1);
            //余力計算結果テーブル（信用顧客）.未受渡建玉代金（T+1） 10.2  undeli_contract_amount_1
            tpcp.setUndeliContractAmount1(10.2);
            //余力計算結果テーブル（信用顧客）.現金必要保証金（T+1） 32.0  cash_margin_deposit_1
            tpcp.setCashMarginDeposit1(32.0);
            //余力計算結果テーブル（信用顧客）.未決済建玉評価損益（T+1）   31.2   contract_asset_profit_loss_1
            tpcp.setContractAssetProfitLoss1(31.2);
            //余力計算結果テーブル（信用顧客）.建玉諸経費（T+1）   22.14    contract_total_cost_1
            tpcp.setContractTotalCost1(22.14);
            //余力計算結果テーブル（信用顧客）.未受渡建玉決済損（T+1）        67.23   undeli_contract_loss_1
            tpcp.setUndeliContractLoss1(67.23);
            //TestDBUtility.insertWithDel(tpcp);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            //余力計算結果詳細テーブル（信用顧客）(tp_calc_result_margin_detail)
            TpCalcResultMarginDetailParams tpdetail = TestDBUtility.getTpCalcResultMarginDetailRow();
            //余力計算結果詳細テーブル（信用顧客）.即日入金対象銘柄拘束金(T+0)64.01  today_dep_fund_restraint_0
            tpdetail.setTodayDepFundRestraint0(64.01);
            //余力計算結果詳細テーブル（信用顧客）.即日入金対象銘柄拘束金(T+1) 3.2   today_dep_fund_restraint_1
            tpdetail.setTodayDepFundRestraint1(3.2);
            //TestDBUtility.insertWithDel(tpdetail);
            
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            //顧客勘定残高(マスタ情報)
            TpCashBalanceParams  tcbp = TestDBUtility.getTpCashBalanceRow();
            tcbp.setAccountId(333812512246L);
            tcbp.setSubAccountId(33381251220301L);
            //顧客勘定残高（マスタ情報）.残高（T+0） cash_balance0
            tcbp.setCashBalance0(321.123);
            //顧客勘定残高（マスタ情報）.残高（T+1） cash_balance1
            tcbp.setCashBalance1(256.555);
            TestDBUtility.insertWithDel(tcbp);
            
            //１）    部店オブジェクトを取得する。
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchPar = TestDBUtility.getBranchRow();
            //部店.保証金維持率 / 100  margin_maintenance_rate
            branchPar.setMarginMaintenanceRate(53);
            //最低必要保証金   = 部店.最低必要保証金  min_margin_deposit
            branchPar.setMinMarginDeposit(300);
            
            TestDBUtility.insertWithDel(branchPar);

            //１）    入出金注文単位テーブル                            Record 1
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams aioOrderUnit1 = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnit1.setOrderUnitId(123456l);
            aioOrderUnit1.setAccountId(333812512246L);
            aioOrderUnit1.setSubAccountId(33381251220301L);
            //注文種別 IN(“1005:預り金から信用保証金”
            aioOrderUnit1.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            //発注日 >= システム日付（当日）
            aioOrderUnit1.setBizDate("20140924");
            //注文状態 NOT IN(“6:発注失敗(新規注文)”,“14:発注済(取消注文)”)
            aioOrderUnit1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            //注文単位テーブル.注文数量
            aioOrderUnit1.setQuantity(60.5);
            TestDBUtility.insertWithDel(aioOrderUnit1);
            
            //入出金注文単位テーブル                            Record 2
            AioOrderUnitParams aioOrderUnit2 = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnit2.setOrderUnitId(56321l);
            aioOrderUnit2.setAccountId(333812512246L);
            aioOrderUnit2.setSubAccountId(33381251220301L);
            //注文種別 IN(“1006:
            aioOrderUnit2.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            //発注日 >= システム日付（当日）(=)
            aioOrderUnit2.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            //注文状態 NOT IN(“6:発注失敗(新規注文)”,“14:発注済(取消注文)”)
            aioOrderUnit2.setOrderStatus(OrderStatusEnum.ACCEPTED);
            //注文単位テーブル.注文数量
            aioOrderUnit2.setQuantity(20.1);
            TestDBUtility.insertWithDel(aioOrderUnit2);

            //---------------------   入出金注文単位テーブル.受渡日 != システム日付（翌営業日） の場合     ---------------------------------
            
            // 振替金(T+1) ：0  
            
            WEB3GentradeSubAccount l_subAccont = 
                new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            
            l_margin.setCalcResultMargin(tpcp);
            l_margin.setCalcResultDetailMargin(tpdetail);
            
            
            
            WEB3TPCalcCondition wtpcc = new WEB3TPCalcCondition();
            wtpcc.setLegalMinMarginDeposit(32.1);
            l_margin.setCalcCondition(wtpcc);
            WEB3TPTradingPowerServiceImpl l_impl =
                new WEB3TPTradingPowerServiceImpl();
            double result = l_impl.calcNextBizDateShortfall(l_subAccont, l_margin);

            this.assertEquals("0.0", String.valueOf(result));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }
    
    public void testCalcNextBizDateShortfall003()
    {
        final String STR_METHOD_NAME = "testCalcNextBizDateShortfall003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAcc = TestDBUtility.getSubAccountRow();
            l_subAcc.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAcc);

            TestDBUtility.deleteAll(DepositAutotransferStopRow.TYPE);

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            //余力計算結果テーブル（信用顧客）
            TpCalcResultMarginParams tpcp =  TestDBUtility.getTpCalcResultMarginRow();
            //余力計算結果テーブル（信用顧客）.預り金（T+0）             1.1
            tpcp.setAccountBalance0(1.1);
            //余力計算結果テーブル（信用顧客）.預り金（T+1） 2.2
            tpcp.setAccountBalance1(2.2);
            //余力計算結果テーブル（信用顧客）.当日約定済代金（T+0） 1000.55 today_executed_amount_0
            tpcp.setTodayExecutedAmount0(1000.55);
            //余力計算結果テーブル（信用顧客）.当日約定済代金（T+1） 4.1  today_executed_amount_1
            tpcp.setTodayExecutedAmount1(4.1);
            //余力計算結果テーブル（信用顧客）.当日未約定代金（T+0） 5.2  today_unexecuted_amount_0
            tpcp.setTodayUnexecutedAmount0(5.2);
            //余力計算結果テーブル（信用顧客）.当日未約定代金（T+1） today_unexecuted_amount_1 6.4
            tpcp.setTodayUnexecutedAmount1(6.4);
            //余力計算結果テーブル（信用顧客）.日計り拘束金（T+1） day_trade_restraint_1 2000.33
            tpcp.setDayTradeRestraint1(2000.33);
            //余力計算結果テーブル（信用顧客）.代用証券評価額（T+1） 8.5  substitute_security_asset_1
            tpcp.setSubstituteSecurityAsset1(8.5);
            //余力計算結果テーブル（信用顧客）.未決済建玉代金（T+1） 9.1  contract_amount_1
            tpcp.setContractAmount1(9.1);
            //余力計算結果テーブル（信用顧客）.未受渡建玉代金（T+1） 10.2  undeli_contract_amount_1 9.1
            tpcp.setUndeliContractAmount1(-9.1);
            //余力計算結果テーブル（信用顧客）.現金必要保証金（T+1） 32.0  cash_margin_deposit_1
            tpcp.setCashMarginDeposit1(32.0);
            //余力計算結果テーブル（信用顧客）.未決済建玉評価損益（T+1）   31.2   contract_asset_profit_loss_1
            tpcp.setContractAssetProfitLoss1(31.2);
            //余力計算結果テーブル（信用顧客）.建玉諸経費（T+1）   22.14    contract_total_cost_1
            tpcp.setContractTotalCost1(22.14);
            //余力計算結果テーブル（信用顧客）.未受渡建玉決済損（T+1）        67.23   undeli_contract_loss_1
            tpcp.setUndeliContractLoss1(67.23);
            //TestDBUtility.insertWithDel(tpcp);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            //余力計算結果詳細テーブル（信用顧客）(tp_calc_result_margin_detail)
            TpCalcResultMarginDetailParams tpdetail = TestDBUtility.getTpCalcResultMarginDetailRow();
            //余力計算結果詳細テーブル（信用顧客）.即日入金対象銘柄拘束金(T+0)0  today_dep_fund_restraint_0
            tpdetail.setTodayDepFundRestraint0(0);
            //余力計算結果詳細テーブル（信用顧客）.即日入金対象銘柄拘束金(T+1) 3.2   today_dep_fund_restraint_1
            tpdetail.setTodayDepFundRestraint1(3.2);
            //TestDBUtility.insertWithDel(tpdetail);
            
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            //顧客勘定残高(マスタ情報)
            TpCashBalanceParams  tcbp = TestDBUtility.getTpCashBalanceRow();
            tcbp.setAccountId(333812512246L);
            tcbp.setSubAccountId(33381251220301L);
            //顧客勘定残高（マスタ情報）.残高（T+0） cash_balance0
            tcbp.setCashBalance0(321.123);
            //顧客勘定残高（マスタ情報）.残高（T+1） cash_balance1
            tcbp.setCashBalance1(256.555);
            TestDBUtility.insertWithDel(tcbp);
            
            //１）    部店オブジェクトを取得する。
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchPar = TestDBUtility.getBranchRow();
            //部店.保証金維持率 / 100  margin_maintenance_rate
            branchPar.setMarginMaintenanceRate(53);
            //最低必要保証金   = 部店.最低必要保証金  min_margin_deposit
            branchPar.setMinMarginDeposit(300);
            
            TestDBUtility.insertWithDel(branchPar);

            //１）    入出金注文単位テーブル                            Record 1
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams aioOrderUnit1 = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnit1.setOrderUnitId(123456l);
            aioOrderUnit1.setAccountId(333812512246L);
            aioOrderUnit1.setSubAccountId(33381251220301L);
            //注文種別 IN(“1005:預り金から信用保証金”
            aioOrderUnit1.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            //発注日 >= システム日付（当日）
            aioOrderUnit1.setBizDate("20140924");
            //入出金注文単位テーブル.受渡日 = システム日付（翌営業日） の場合
            aioOrderUnit1.setDeliveryDate(WEB3DateUtility.toDay(new WEB3GentradeBizDate(
                    GtlUtils.getSystemTimestamp()).roll(1)));
            
            //注文状態 NOT IN(“6:発注失敗(新規注文)”,“14:発注済(取消注文)”)
            aioOrderUnit1.setOrderStatus(OrderStatusEnum.ACCEPTED);
            //注文単位テーブル.注文数量
            aioOrderUnit1.setQuantity(20.3);
            TestDBUtility.insertWithDel(aioOrderUnit1);
            
            //入出金注文単位テーブル                            Record 2
            AioOrderUnitParams aioOrderUnit2 = TestDBUtility.getAioOrderUnitRow();
            aioOrderUnit2.setOrderUnitId(56321l);
            aioOrderUnit2.setAccountId(333812512246L);
            aioOrderUnit2.setSubAccountId(33381251220301L);
            //注文種別 IN(“1006:
            aioOrderUnit2.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            //発注日 >= システム日付（当日）(=)
            aioOrderUnit2.setBizDate(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"));
            aioOrderUnit2.setDeliveryDate(WEB3DateUtility.toDay(new WEB3GentradeBizDate(
                    GtlUtils.getSystemTimestamp()).roll(1)));
            //注文状態 NOT IN(“6:発注失敗(新規注文)”,“14:発注済(取消注文)”)
            aioOrderUnit2.setOrderStatus(OrderStatusEnum.ACCEPTED);
            //注文単位テーブル.注文数量
            aioOrderUnit2.setQuantity(30.4);
            TestDBUtility.insertWithDel(aioOrderUnit2);

            //---------------------   入出金注文単位テーブル.受渡日 != システム日付（翌営業日） の場合     ---------------------------------
            
            // 振替金(T+1) ：0  
            
            WEB3GentradeSubAccount l_subAccont = 
                new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            
            l_margin.setCalcResultMargin(tpcp);
            l_margin.setCalcResultDetailMargin(tpdetail);
            
            
            
            WEB3TPCalcCondition wtpcc = new WEB3TPCalcCondition();
            wtpcc.setLegalMinMarginDeposit(32.1);
            l_margin.setCalcCondition(wtpcc);
            WEB3TPTradingPowerServiceImpl l_impl =
                new WEB3TPTradingPowerServiceImpl();
            double result = l_impl.calcNextBizDateShortfall(l_subAccont, l_margin);

            this.assertEquals("92.0", String.valueOf(result));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    public void testGetTodayDepositWithdrawTradingPower()
    {
        final String STR_METHOD_NAME = "testGetTodayDepositWithdrawTradingPower()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAcc = TestDBUtility.getSubAccountRow();
            l_subAcc.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAcc);
            WEB3GentradeSubAccount l_subAccont = 
                new WEB3GentradeSubAccount(333812512246L,33381251220301L);
            WEB3TPTradingPowerServiceImpl l_impl =
                new WEB3TPTradingPowerServiceImpl();
            assertEquals("0.0", l_impl.getTodayDepositWithdrawTradingPower(l_subAccont, "0") + "");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }


    public void testGetTodayDepositWithdrawTradingPower1()
    {
        final String STR_METHOD_NAME = "testGetTodayDepositWithdrawTradingPower1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(20);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            WEB3GentradeSubAccount l_subAccont = 
                new WEB3GentradeSubAccount(333812512246L,20L);
            WEB3TPTradingPowerServiceImpl l_impl =
                new WEB3TPTradingPowerServiceImpl();
            log.debug("STR_METHOD_NAME=" + l_impl.getTodayDepositWithdrawTradingPower(l_subAccont, "0"));
            assertEquals("0.0", l_impl.getTodayDepositWithdrawTradingPower(l_subAccont, "0") + "");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }

    public void testGetTodayDepositWithdrawTradingPower2()
    {
        final String STR_METHOD_NAME = "testGetTodayDepositWithdrawTradingPower2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(20);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            WEB3GentradeSubAccount l_subAccont = 
                new WEB3GentradeSubAccount(333812512246L,20L);
            WEB3TPTradingPowerServiceImpl l_impl =
                new WEB3TPTradingPowerServiceImpl();
            log.debug("STR_METHOD_NAME=" + l_impl.getTodayDepositWithdrawTradingPower(l_subAccont, "1"));
            assertEquals("0.0", l_impl.getTodayDepositWithdrawTradingPower(l_subAccont, "0") + "");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }

    public void testgetPaymentTradingPowerAioCashoutInputCase0001()
    {
        final String STR_METHOD_NAME = "testgetPaymentTradingPowerAioCashoutInputCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(20);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccont = 
                new WEB3GentradeSubAccount(333812512246L,20L);
            WEB3TPTradingPowerServiceImpl l_impl =
                new WEB3TPTradingPowerServiceImpl();
            assertEquals("0.0", l_impl.getPaymentTradingPowerAioCashoutInput(l_subAccont, new Date()) + "");
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }

}
@
