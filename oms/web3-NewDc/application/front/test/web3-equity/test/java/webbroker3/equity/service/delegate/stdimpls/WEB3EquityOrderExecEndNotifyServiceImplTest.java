head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderExecEndNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出来終了通知サービスImplTest(WEB3EquityOrderExecEndNotifyServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/02 張騰宇(中訊)
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
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

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.equity.data.EqtypeOrderExecSendMailParams;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecEndNotifyServiceImpl.ServiceTransactionCallback;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecEndNotifyServiceImpl.ServiceTransactionCallback.AccTransactionCallback;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderExecEndNotifyServiceImplTest extends TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderExecEndNotifyServiceImplTest.class);

    public WEB3EquityOrderExecEndNotifyServiceImplTest(String arg0)
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
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecEndNotifyServiceImpl.execute(WEB3BackRequest)'
     */
    public void testServiceTransactionCallbackGetMainAccounts_0001()
    {
        final String STR_METHOD_NAME = "testServiceTransactionCallbackGetMainAccounts_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {            
            MOCK_MANAGER.setIsMockUsed(true);
          Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
              "getSystemTimestamp",
              new Class[] {},
              new Timestamp(l_datBizDate.getTime()));
          ThreadLocalSystemAttributesRegistry.setAttribute(
                  "xblocks.gtl.attributes.systemtimestamp",
                  new Timestamp(l_datBizDate.getTime()));
          ThreadLocalSystemAttributesRegistry.setAttribute(
                  "web3.attributes.basetimestampfororderbizdate",
                  new Timestamp(l_datBizDate.getTime()));
          
            TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);
  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
            l_institutionParams1.setInstitutionCode("1D");
            l_institutionParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_institutionParams1);
            InstitutionParams l_institutionParams2 = TestDBUtility.getInstitutionRow();
            l_institutionParams2.setInstitutionId(35);
            l_institutionParams2.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(l_institutionParams2);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("1D");
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("2D");
            l_branchParams2.setBranchId(33383);
            l_branchParams2.setBranchCode("383");
            TestDBUtility.insertWithDel(l_branchParams2);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_BranchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_BranchMarketDealtCondParams.setInstitutionCode("0D");
            l_BranchMarketDealtCondParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams);
//            BranchMarketDealtCondParams l_BranchMarketDealtCondParams1 =
//                TestDBUtility.getBranchMarketDealCondRow();
//            l_BranchMarketDealtCondParams1.setInstitutionCode("0D");
//            l_BranchMarketDealtCondParams1.setMarketCode("12");
//            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams1);
            BranchMarketDealtCondParams l_BranchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_BranchMarketDealtCondParams2.setInstitutionCode("0D");
            l_BranchMarketDealtCondParams2.setMarketCode("13");
            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
//            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
//            l_marketParams1.setMarketCode("12");
//            l_marketParams1.setInstitutionCode("0D");
//            l_marketParams1.setMarketId(3304L);
//            TestDBUtility.insertWithDel(l_marketParams1);
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketCode("13");
            l_marketParams2.setInstitutionCode("0D");
            l_marketParams2.setMarketId(3305L);
            TestDBUtility.insertWithDel(l_marketParams2);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(333812512204L);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setBranchId(33382);
            l_mainAccountParams1.setBranchCode("382");
            l_mainAccountParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(333812512205L);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setBranchId(33383);
            l_mainAccountParams2.setBranchCode("383");
            l_mainAccountParams2.setInstitutionId(35);
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(333812512203L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512204L);
            l_subAccountParams1.setSubAccountId(333812512204L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512205L);
            l_subAccountParams2.setSubAccountId(333812512205L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams.setBranchId(33381);
            l_orderUnitParams.setMarketId(3303L);
            l_orderUnitParams.setOrderId(1001);
            l_orderUnitParams.setOrderUnitId(1001);
            l_orderUnitParams.setAccountId(333812512203L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setSubAccountId(333812512203L);
            l_orderUnitParams.setOrderRequestNumber("1");
            l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams);
            EqtypeOrderUnitParams l_orderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams1.setBranchId(33381);
            l_orderUnitParams1.setOrderId(1002);
            l_orderUnitParams1.setOrderUnitId(1002);
            l_orderUnitParams1.setAccountId(333812512204L);
            l_orderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams1.setSubAccountId(333812512204L);
            l_orderUnitParams1.setMarketId(3304L);
            l_orderUnitParams1.setExecutedQuantity(null);
            l_orderUnitParams1.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_orderUnitParams1);
            EqtypeOrderUnitParams l_orderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams2.setBranchId(33381);
            l_orderUnitParams2.setOrderId(1003);
            l_orderUnitParams2.setAccountId(333812512205L);
            l_orderUnitParams2.setOrderUnitId(1003);
            l_orderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams2.setSubAccountId(333812512205L);
            l_orderUnitParams2.setMarketId(3305L);
            l_orderUnitParams2.setConfirmedQuantity(null);
            l_orderUnitParams2.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_orderUnitParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_orderParams = TestDBUtility.getEqtypeOrderRow();
            l_orderParams.setOrderId(1001);
            l_orderParams.setAccountId(333812512203L);
            l_orderParams.setSubAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_orderParams);
            EqtypeOrderParams l_orderParams1 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams1.setOrderId(1002);
            l_orderParams1.setAccountId(333812512204L);
            l_orderParams1.setSubAccountId(333812512204L);
            TestDBUtility.insertWithDel(l_orderParams1);
            EqtypeOrderParams l_orderParams2 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams2.setOrderId(1003);
            l_orderParams2.setAccountId(333812512205L);
            l_orderParams2.setSubAccountId(333812512205L);
            TestDBUtility.insertWithDel(l_orderParams2);
            
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            OnlineRunStatusParams l_runStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_runStatusParams.setInstitutionCode("0D");
            l_runStatusParams.setRunStatusDiv("5");
            l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
            l_runStatusParams.setAccountIdFrom(0);
            l_runStatusParams.setAccountIdTo(433812512203L);
            l_runStatusParams.setOnlineServiceDiv("1");
            TestDBUtility.insertWithDel(l_runStatusParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3EquityOrderExecEndNotifyServiceImpl l_impl =  new WEB3EquityOrderExecEndNotifyServiceImpl();
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = new WEB3GentradeOnlineRunStatus(l_runStatusParams);
            ServiceTransactionCallback l_transactionCallback =
                l_impl.new ServiceTransactionCallback("0D", 0,433812512203L,l_onlineRunStatus);
           
            WEB3GentradeMainAccount[] l_mainAccounts = l_transactionCallback.getMainAccounts();

            assertEquals(l_mainAccounts.length, 2);
            assertEquals(l_mainAccounts[0].getAccountId(), 333812512203L);
            assertEquals(l_mainAccounts[1].getAccountId(), 333812512205L);
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
                TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testServiceTransactionCallbackGetMainAccounts_0002()
    {
        final String STR_METHOD_NAME = "testServiceTransactionCallbackGetMainAccounts_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {            
            MOCK_MANAGER.setIsMockUsed(true);
          Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
              "getSystemTimestamp",
              new Class[] {},
              new Timestamp(l_datBizDate.getTime()));
          ThreadLocalSystemAttributesRegistry.setAttribute(
                  "xblocks.gtl.attributes.systemtimestamp",
                  new Timestamp(l_datBizDate.getTime()));
          ThreadLocalSystemAttributesRegistry.setAttribute(
                  "web3.attributes.basetimestampfororderbizdate",
                  new Timestamp(l_datBizDate.getTime()));
          
            TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);

            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);
  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
            l_institutionParams1.setInstitutionCode("1D");
            l_institutionParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_institutionParams1);
            InstitutionParams l_institutionParams2 = TestDBUtility.getInstitutionRow();
            l_institutionParams2.setInstitutionId(35);
            l_institutionParams2.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(l_institutionParams2);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("1D");
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("2D");
            l_branchParams2.setBranchId(33383);
            l_branchParams2.setBranchCode("383");
            TestDBUtility.insertWithDel(l_branchParams2);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
            l_marketParams1.setMarketCode("12");
            l_marketParams1.setInstitutionCode("0D");
            l_marketParams1.setMarketId(3304L);
            TestDBUtility.insertWithDel(l_marketParams1);
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketCode("13");
            l_marketParams2.setInstitutionCode("0D");
            l_marketParams2.setMarketId(3305L);
            TestDBUtility.insertWithDel(l_marketParams2);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams1.setAccountId(333812512204L);
            l_mainAccountParams1.setInstitutionCode("0D");
            l_mainAccountParams1.setBranchId(33382);
            l_mainAccountParams1.setBranchCode("382");
            l_mainAccountParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_mainAccountParams1);
            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
            l_mainAccountParams2.setAccountId(333812512205L);
            l_mainAccountParams2.setInstitutionCode("0D");
            l_mainAccountParams2.setBranchId(33383);
            l_mainAccountParams2.setBranchCode("383");
            l_mainAccountParams2.setInstitutionId(35);
            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(333812512203L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512204L);
            l_subAccountParams1.setSubAccountId(333812512204L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512205L);
            l_subAccountParams2.setSubAccountId(333812512205L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams.setBranchId(33381);
            l_orderUnitParams.setMarketId(3303L);
            l_orderUnitParams.setOrderId(1001);
            l_orderUnitParams.setOrderUnitId(1001);
            l_orderUnitParams.setAccountId(333812512203L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setSubAccountId(333812512203L);
            l_orderUnitParams.setOrderRequestNumber("1");
            l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams);
            EqtypeOrderUnitParams l_orderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams1.setBranchId(33381);
            l_orderUnitParams1.setOrderId(1002);
            l_orderUnitParams1.setOrderUnitId(1002);
            l_orderUnitParams1.setAccountId(333812512204L);
            l_orderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams1.setSubAccountId(333812512204L);
            l_orderUnitParams1.setMarketId(3304L);
            l_orderUnitParams1.setExecutedQuantity(null);
            l_orderUnitParams1.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_orderUnitParams1);
            EqtypeOrderUnitParams l_orderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams2.setBranchId(33381);
            l_orderUnitParams2.setOrderId(1003);
            l_orderUnitParams2.setAccountId(333812512205L);
            l_orderUnitParams2.setOrderUnitId(1003);
            l_orderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams2.setSubAccountId(333812512205L);
            l_orderUnitParams2.setMarketId(3305L);
            l_orderUnitParams2.setConfirmedQuantity(null);
            l_orderUnitParams2.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_orderUnitParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_orderParams = TestDBUtility.getEqtypeOrderRow();
            l_orderParams.setOrderId(1001);
            l_orderParams.setAccountId(333812512203L);
            l_orderParams.setSubAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_orderParams);
            EqtypeOrderParams l_orderParams1 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams1.setOrderId(1002);
            l_orderParams1.setAccountId(333812512204L);
            l_orderParams1.setSubAccountId(333812512204L);
            TestDBUtility.insertWithDel(l_orderParams1);
            EqtypeOrderParams l_orderParams2 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams2.setOrderId(1003);
            l_orderParams2.setAccountId(333812512205L);
            l_orderParams2.setSubAccountId(333812512205L);
            TestDBUtility.insertWithDel(l_orderParams2);
            
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            OnlineRunStatusParams l_runStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_runStatusParams.setInstitutionCode("0D");
            l_runStatusParams.setRunStatusDiv("5");
            l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
            l_runStatusParams.setAccountIdFrom(0);
            l_runStatusParams.setAccountIdTo(433812512203L);
            l_runStatusParams.setOnlineServiceDiv("1");
            TestDBUtility.insertWithDel(l_runStatusParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3EquityOrderExecEndNotifyServiceImpl l_impl =  new WEB3EquityOrderExecEndNotifyServiceImpl();
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = new WEB3GentradeOnlineRunStatus(l_runStatusParams);
            ServiceTransactionCallback l_transactionCallback =
                l_impl.new ServiceTransactionCallback("0D", 0,433812512203L,l_onlineRunStatus);
           
            WEB3GentradeMainAccount[] l_mainAccounts = l_transactionCallback.getMainAccounts();

            assertEquals(l_mainAccounts.length, 3);
            assertEquals(l_mainAccounts[0].getAccountId(), 333812512203L);
            assertEquals(l_mainAccounts[1].getAccountId(), 333812512204L);
            assertEquals(l_mainAccounts[2].getAccountId(), 333812512205L);
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
                TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testAccTransactionCallbackProcess_0001()
    {
        final String STR_METHOD_NAME = "testAccTransactionCallbackProcess_0001()";
        log.entering(STR_METHOD_NAME);
        try
        {            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_datBizDate.getTime()));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(l_datBizDate.getTime()));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    new Timestamp(l_datBizDate.getTime()));
            
            TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager", "expireOrder",
                new Class[] {long.class},
                ProcessingResult.SUCCESS_RESULT);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);
  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
            l_institutionParams1.setInstitutionCode("1D");
            l_institutionParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_institutionParams1);
            InstitutionParams l_institutionParams2 = TestDBUtility.getInstitutionRow();
            l_institutionParams2.setInstitutionId(35);
            l_institutionParams2.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(l_institutionParams2);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("1D");
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("2D");
            l_branchParams2.setBranchId(33383);
            l_branchParams2.setBranchCode("383");
            TestDBUtility.insertWithDel(l_branchParams2);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            BranchMarketDealtCondParams l_BranchMarketDealtCondParams =
                TestDBUtility.getBranchMarketDealCondRow();
            l_BranchMarketDealtCondParams.setInstitutionCode("0D");
            l_BranchMarketDealtCondParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams);
//            BranchMarketDealtCondParams l_BranchMarketDealtCondParams1 =
//                TestDBUtility.getBranchMarketDealCondRow();
//            l_BranchMarketDealtCondParams1.setInstitutionCode("0D");
//            l_BranchMarketDealtCondParams1.setMarketCode("12");
//            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams1);
            BranchMarketDealtCondParams l_BranchMarketDealtCondParams2 =
                TestDBUtility.getBranchMarketDealCondRow();
            l_BranchMarketDealtCondParams2.setInstitutionCode("0D");
            l_BranchMarketDealtCondParams2.setMarketCode("13");
            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
//            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
//            l_marketParams1.setMarketCode("12");
//            l_marketParams1.setInstitutionCode("0D");
//            l_marketParams1.setMarketId(3304L);
//            TestDBUtility.insertWithDel(l_marketParams1);
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketCode("13");
            l_marketParams2.setInstitutionCode("0D");
            l_marketParams2.setMarketId(3305L);
            TestDBUtility.insertWithDel(l_marketParams2);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_mainAccountParams);
//            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams1.setAccountId(333812512204L);
//            l_mainAccountParams1.setInstitutionCode("0D");
//            l_mainAccountParams1.setBranchId(33382);
//            l_mainAccountParams1.setBranchCode("382");
//            l_mainAccountParams1.setInstitutionId(34);
//            TestDBUtility.insertWithDel(l_mainAccountParams1);
//            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams2.setAccountId(333812512205L);
//            l_mainAccountParams2.setInstitutionCode("0D");
//            l_mainAccountParams2.setBranchId(33383);
//            l_mainAccountParams2.setBranchCode("383");
//            l_mainAccountParams2.setInstitutionId(35);
//            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(333812512203L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512204L);
            l_subAccountParams1.setSubAccountId(333812512204L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512205L);
            l_subAccountParams2.setSubAccountId(333812512205L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams.setBranchId(33381);
            l_orderUnitParams.setOrderId(1001);
            l_orderUnitParams.setOrderUnitId(1001);
            l_orderUnitParams.setAccountId(333812512203L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setSubAccountId(333812512203L);
            l_orderUnitParams.setOrderRequestNumber("1");
            l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams);
            EqtypeOrderUnitParams l_orderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams1.setBranchId(33381);
            l_orderUnitParams1.setOrderId(1002);
            l_orderUnitParams1.setOrderUnitId(1002);
            l_orderUnitParams1.setAccountId(333812512203L);
            l_orderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams1.setSubAccountId(333812512203L);
            l_orderUnitParams1.setMarketId(3304L);
            l_orderUnitParams1.setExecutedQuantity(null);
            l_orderUnitParams1.setOrderRequestNumber("1");
            l_orderUnitParams1.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams1);
            EqtypeOrderUnitParams l_orderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams2.setBranchId(33381);
            l_orderUnitParams2.setOrderId(1003);
            l_orderUnitParams2.setAccountId(333812512203L);
            l_orderUnitParams2.setOrderUnitId(1003);
            l_orderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams2.setSubAccountId(333812512203L);
            l_orderUnitParams2.setMarketId(3305L);
            l_orderUnitParams2.setConfirmedQuantity(null);
            l_orderUnitParams2.setOrderRequestNumber("1");
            l_orderUnitParams2.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_orderParams = TestDBUtility.getEqtypeOrderRow();
            l_orderParams.setOrderId(1001);
            l_orderParams.setAccountId(333812512203L);
            l_orderParams.setSubAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_orderParams);
            EqtypeOrderParams l_orderParams1 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams1.setOrderId(1002);
            l_orderParams1.setAccountId(333812512204L);
            l_orderParams1.setSubAccountId(333812512204L);
            TestDBUtility.insertWithDel(l_orderParams1);
            EqtypeOrderParams l_orderParams2 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams2.setOrderId(1003);
            l_orderParams2.setAccountId(333812512205L);
            l_orderParams2.setSubAccountId(333812512205L);
            TestDBUtility.insertWithDel(l_orderParams2);
            
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            OnlineRunStatusParams l_runStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_runStatusParams.setInstitutionCode("0D");
            l_runStatusParams.setRunStatusDiv("5");
            l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
            l_runStatusParams.setAccountIdFrom(0);
            l_runStatusParams.setAccountIdTo(433812512203L);
            l_runStatusParams.setOnlineServiceDiv("1");
            TestDBUtility.insertWithDel(l_runStatusParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            WEB3EquityOrderExecEndNotifyServiceImpl l_impl =  new WEB3EquityOrderExecEndNotifyServiceImpl();
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = new WEB3GentradeOnlineRunStatus(l_runStatusParams);
            ServiceTransactionCallback l_transactionCallback =
                l_impl.new ServiceTransactionCallback("0D", 0,433812512203L,l_onlineRunStatus);
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(l_mainAccountParams);
            AccTransactionCallback l_AccTransactionCallback =
                l_transactionCallback.new AccTransactionCallback(l_account);
           
            l_AccTransactionCallback.process();
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
                TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
//                TestDBUtility.deleteAll(InstitutionRow.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testAccTransactionCallbackProcess_0002()
    {
        final String STR_METHOD_NAME = "testAccTransactionCallbackProcess_0002()";
        log.entering(STR_METHOD_NAME);
        try
        {            
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);
            Date l_datBizDate = WEB3DateUtility.getDate("20080130", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_datBizDate.getTime()));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(l_datBizDate.getTime()));
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    new Timestamp(l_datBizDate.getTime()));
            
            TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityPTSOrderManager", "expireOrder",
                new Class[] {long.class},
                ProcessingResult.SUCCESS_RESULT);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setMarketCode("11");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);
  
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            InstitutionParams l_institutionParams1 = TestDBUtility.getInstitutionRow();
            l_institutionParams1.setInstitutionCode("1D");
            l_institutionParams1.setInstitutionId(34);
            TestDBUtility.insertWithDel(l_institutionParams1);
            InstitutionParams l_institutionParams2 = TestDBUtility.getInstitutionRow();
            l_institutionParams2.setInstitutionId(35);
            l_institutionParams2.setInstitutionCode("2D");
            TestDBUtility.insertWithDel(l_institutionParams2);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setInstitutionCode("1D");
            l_branchParams1.setBranchId(33382);
            l_branchParams1.setBranchCode("382");
            TestDBUtility.insertWithDel(l_branchParams1);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            BranchParams l_branchParams2 = TestDBUtility.getBranchRow();
            l_branchParams2.setInstitutionCode("2D");
            l_branchParams2.setBranchId(33383);
            l_branchParams2.setBranchCode("383");
            TestDBUtility.insertWithDel(l_branchParams2);
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
//            BranchMarketDealtCondParams l_BranchMarketDealtCondParams =
//                TestDBUtility.getBranchMarketDealCondRow();
//            l_BranchMarketDealtCondParams.setInstitutionCode("0D");
//            l_BranchMarketDealtCondParams.setMarketCode("11");
//            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams);
////            BranchMarketDealtCondParams l_BranchMarketDealtCondParams1 =
////                TestDBUtility.getBranchMarketDealCondRow();
////            l_BranchMarketDealtCondParams1.setInstitutionCode("0D");
////            l_BranchMarketDealtCondParams1.setMarketCode("12");
////            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams1);
//            BranchMarketDealtCondParams l_BranchMarketDealtCondParams2 =
//                TestDBUtility.getBranchMarketDealCondRow();
//            l_BranchMarketDealtCondParams2.setInstitutionCode("0D");
//            l_BranchMarketDealtCondParams2.setMarketCode("13");
//            TestDBUtility.insertWithDel(l_BranchMarketDealtCondParams2);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setInstitutionCode("0D");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            MarketParams l_marketParams1 = TestDBUtility.getMarketRow();
            l_marketParams1.setMarketCode("12");
            l_marketParams1.setInstitutionCode("0D");
            l_marketParams1.setMarketId(3304L);
            TestDBUtility.insertWithDel(l_marketParams1);
            MarketParams l_marketParams2 = TestDBUtility.getMarketRow();
            l_marketParams2.setMarketCode("13");
            l_marketParams2.setInstitutionCode("0D");
            l_marketParams2.setMarketId(3305L);
            TestDBUtility.insertWithDel(l_marketParams2);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_mainAccountParams);
//            MainAccountParams l_mainAccountParams1 = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams1.setAccountId(333812512204L);
//            l_mainAccountParams1.setInstitutionCode("0D");
//            l_mainAccountParams1.setBranchId(33382);
//            l_mainAccountParams1.setBranchCode("382");
//            l_mainAccountParams1.setInstitutionId(34);
//            TestDBUtility.insertWithDel(l_mainAccountParams1);
//            MainAccountParams l_mainAccountParams2 = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams2.setAccountId(333812512205L);
//            l_mainAccountParams2.setInstitutionCode("0D");
//            l_mainAccountParams2.setBranchId(33383);
//            l_mainAccountParams2.setBranchCode("383");
//            l_mainAccountParams2.setInstitutionId(35);
//            TestDBUtility.insertWithDel(l_mainAccountParams2);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(333812512203L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512204L);
            l_subAccountParams1.setSubAccountId(333812512204L);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            SubAccountParams l_subAccountParams2 = TestDBUtility.getSubAccountRow();
            l_subAccountParams2.setAccountId(333812512205L);
            l_subAccountParams2.setSubAccountId(333812512205L);
            l_subAccountParams2.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams.setBranchId(33381);
            l_orderUnitParams.setOrderId(1001);
            l_orderUnitParams.setOrderUnitId(1001);
            l_orderUnitParams.setAccountId(333812512203L);
            l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams.setSubAccountId(333812512203L);
            l_orderUnitParams.setOrderRequestNumber("1");
            l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams);
            EqtypeOrderUnitParams l_orderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams1.setBranchId(33381);
            l_orderUnitParams1.setOrderId(1002);
            l_orderUnitParams1.setOrderUnitId(1002);
            l_orderUnitParams1.setAccountId(333812512203L);
            l_orderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams1.setSubAccountId(333812512203L);
            l_orderUnitParams1.setMarketId(3304L);
            l_orderUnitParams1.setOrderRequestNumber("1");
            l_orderUnitParams1.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams1);
            EqtypeOrderUnitParams l_orderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams2.setBranchId(33381);
            l_orderUnitParams2.setOrderId(1003);
            l_orderUnitParams2.setAccountId(333812512203L);
            l_orderUnitParams2.setOrderUnitId(1003);
            l_orderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_orderUnitParams2.setSubAccountId(333812512203L);
            l_orderUnitParams2.setMarketId(3305L);
            l_orderUnitParams2.setOrderRequestNumber("1");
            l_orderUnitParams2.setExpirationDate(WEB3DateUtility.getDate("22220924","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_orderUnitParams2);
            
            TestDBUtility.deleteAll(EqtypeOrderRow.TYPE);
            EqtypeOrderParams l_orderParams = TestDBUtility.getEqtypeOrderRow();
            l_orderParams.setOrderId(1001);
            l_orderParams.setAccountId(333812512203L);
            l_orderParams.setSubAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_orderParams);
            EqtypeOrderParams l_orderParams1 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams1.setOrderId(1002);
            l_orderParams1.setAccountId(333812512204L);
            l_orderParams1.setSubAccountId(333812512204L);
            TestDBUtility.insertWithDel(l_orderParams1);
            EqtypeOrderParams l_orderParams2 = TestDBUtility.getEqtypeOrderRow();
            l_orderParams2.setOrderId(1003);
            l_orderParams2.setAccountId(333812512205L);
            l_orderParams2.setSubAccountId(333812512205L);
            TestDBUtility.insertWithDel(l_orderParams2);
            
            TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
            OnlineRunStatusParams l_runStatusParams = TestDBUtility.getOnlineRunStatusRow();
            l_runStatusParams.setInstitutionCode("0D");
            l_runStatusParams.setRunStatusDiv("5");
            l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);
            l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
            l_runStatusParams.setAccountIdFrom(0);
            l_runStatusParams.setAccountIdTo(433812512203L);
            l_runStatusParams.setOnlineServiceDiv("1");
            TestDBUtility.insertWithDel(l_runStatusParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_productParams);
            
            
            WEB3EquityOrderExecEndNotifyServiceImpl l_impl =  new WEB3EquityOrderExecEndNotifyServiceImpl();
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = new WEB3GentradeOnlineRunStatus(l_runStatusParams);
            ServiceTransactionCallback l_transactionCallback =
                l_impl.new ServiceTransactionCallback("0D", 0,433812512203L,l_onlineRunStatus);
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(l_mainAccountParams);
            AccTransactionCallback l_AccTransactionCallback =
                l_transactionCallback.new AccTransactionCallback(l_account);
           
            l_AccTransactionCallback.process();
        }

        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(OnlineRunStatusRow.TYPE);
                TestDBUtility.deleteAll(EqtypeOrderExecSendMailParams.TYPE);
//                TestDBUtility.deleteAll(InstitutionRow.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
