head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoMarketRequestSenderServiceImplTest_ES_send.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP市場リクエスト送信サービス 
Author Name      : Daiwa Institute of Research
Revesion History : 孟亜南
*/
package webbroker3.ifo.marketadaptor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.DefaultIfoChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.DefaultIfoChangeSettleContractOrderMarketRequestMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 先物OP市場リクエスト送信サービス 
 * @@author 孟亜南
 */
public class WEB3IfoMarketRequestSenderServiceImplTest_ES_send extends TestBaseForMock
{

    public WEB3IfoMarketRequestSenderServiceImplTest_ES_send(String name)
    {
        super(name);
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoMarketRequestSenderServiceImplTest.class);
    
    /**
     * 新規建訂正注文送信
     * 
     * is取引所休憩時間帯() = true
     */
    public void test_send_0001()
    {
        final String STR_METHOD_NAME = "test_send_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplAForTest l_service = new WEB3IfoMarketRequestSenderServiceImplAForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_TradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            DefaultMarketRequestSendResult l_defaultMarketRequestSendResult =
                (DefaultMarketRequestSendResult)l_service.send(l_request,l_blnIsMarketNotSendMessage);
            assertEquals(0L,l_defaultMarketRequestSendResult.getMessageTokenId());
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 新規建訂正注文送信
     * 
     * is取引所休憩時間帯() = false
     * 市場通知要の訂正注文 = false
     * トリガー発行あり = true
     */
    public void test_send_0002()
    {
        final String STR_METHOD_NAME = "test_send_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplAForTest l_service = new WEB3IfoMarketRequestSenderServiceImplAForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        //市場通知要の訂正注文 100 = true 1 = false
        l_ifoOrderUnitParams.setConfirmedQuantity(1);
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        
        //is取引所休憩時間帯() 0 = true 1 = false
        //トリガー発行あり 1 = true 0 = false
        l_TradingTimeParams.setSubmitMarketTrigger("1");
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            l_service.send(l_request,l_blnIsMarketNotSendMessage);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");

            List l_lstHostFotypeOrderAllWhere = new ArrayList();
            l_lstHostFotypeOrderAllWhere.add("101001010010");

            Object[] l_objTradingTimeWhere = 
                new Object[l_lstHostFotypeOrderAllWhere.size()];
            l_lstHostFotypeOrderAllWhere.toArray(l_objTradingTimeWhere);

            List l_lstRecords;

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lstRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objTradingTimeWhere);
                
                HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRecords.get(0);
                assertEquals("101001010010","" + l_row.getAccountId());

            }
            catch (DataException de)
            {
                log.debug("",de);
                fail();
            }
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 新規建訂正注文送信
     * 
     * is取引所休憩時間帯() = true
     * 市場通知要の訂正注文(is内容通知済注文) = false
     * トリガー発行あり = false
     * 
     */
    public void test_send_0003() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "test_send_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplCForTest l_service = new WEB3IfoMarketRequestSenderServiceImplCForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        //市場通知要の訂正注文 100 = true 1 = false
        l_ifoOrderUnitParams.setConfirmedQuantity(1);
        
        l_ifoOrderUnitParams.setSessionType(null);
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        //
        l_TradingTimeParams.setTradingTimeType("10");
        //夕場時間帯
        l_TradingTimeParams.setSessionType("1");
        
        //is取引所休憩時間帯() 0 = true 1 = false
        //トリガー発行あり 1 = true 0 = false
        l_TradingTimeParams.setSubmitMarketTrigger("0");
        try
        {          
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(ProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_TradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            DefaultMarketRequestSendResult l_defaultMarketRequestSendResult =
            (DefaultMarketRequestSendResult)l_service.send(l_request,l_blnIsMarketNotSendMessage);;
            fail();
            
        }
        catch (WEB3BaseRuntimeException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,e.getErrorInfo());
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 返済訂正注文送信
     * 
     * is取引所休憩時間帯() = true
     */
    public void test_send_0011()
    {
        final String STR_METHOD_NAME = "test_send_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplAForTest l_service = new WEB3IfoMarketRequestSenderServiceImplAForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_TradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        IfoClosingContractSpecRow closingContractRows[] = {};
                                                      
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                    l_subAccount,
                    l_lngOrderId,
                    l_ifoOrderUnitParams,
                    closingContractRows
                    );
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            DefaultMarketRequestSendResult l_defaultMarketRequestSendResult =
                (DefaultMarketRequestSendResult)l_service.send(l_request,l_blnIsMarketNotSendMessage);
            assertEquals(0L,l_defaultMarketRequestSendResult.getMessageTokenId());
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 返済訂正注文送信
     * 
     * is取引所休憩時間帯() = false
     * 市場通知要の訂正注文 = false
     * トリガー発行あり = true
     */
    public void test_send_0012()
    {
        final String STR_METHOD_NAME = "test_send_0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplAForTest l_service = new WEB3IfoMarketRequestSenderServiceImplAForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        //市場通知要の訂正注文 100 = true 1 = false
        l_ifoOrderUnitParams.setConfirmedQuantity(1);
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        
        //is取引所休憩時間帯() 0 = true 1 = false
        //トリガー発行あり 1 = true 0 = false
        l_TradingTimeParams.setSubmitMarketTrigger("1");
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow closingContractRows[] = {};
        
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                    l_subAccount,
                    l_lngOrderId,
                    l_ifoOrderUnitParams,
                    closingContractRows
                    );
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            l_service.send(l_request,l_blnIsMarketNotSendMessage);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");

            List l_lstHostFotypeOrderAllWhere = new ArrayList();
            l_lstHostFotypeOrderAllWhere.add("101001010010");

            Object[] l_objTradingTimeWhere = 
                new Object[l_lstHostFotypeOrderAllWhere.size()];
            l_lstHostFotypeOrderAllWhere.toArray(l_objTradingTimeWhere);

            List l_lstRecords;

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lstRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objTradingTimeWhere);
                
                HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRecords.get(0);
                assertEquals("101001010010","" + l_row.getAccountId());

            }
            catch (DataException de)
            {
                log.debug("",de);
                fail();
            }
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 返済訂正注文送信
     * 
     * is取引所休憩時間帯() = true
     * 市場通知要の訂正注文(is内容通知済注文) = false
     * トリガー発行あり = false
     * 
     */
    public void test_send_0013() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "test_send_0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplCForTest l_service = new WEB3IfoMarketRequestSenderServiceImplCForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        //市場通知要の訂正注文 100 = true 1 = false
        l_ifoOrderUnitParams.setConfirmedQuantity(1);
        
        l_ifoOrderUnitParams.setSessionType(null);
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        //
        l_TradingTimeParams.setTradingTimeType("10");
        //夕場時間帯
        l_TradingTimeParams.setSessionType("1");
        
        //is取引所休憩時間帯() 0 = true 1 = false
        //トリガー発行あり 1 = true 0 = false
        l_TradingTimeParams.setSubmitMarketTrigger("0");
        try
        {          
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(ProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_TradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow closingContractRows[] = {};
        
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                    l_subAccount,
                    l_lngOrderId,
                    l_ifoOrderUnitParams,
                    closingContractRows
                    );
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            DefaultMarketRequestSendResult l_defaultMarketRequestSendResult =
            (DefaultMarketRequestSendResult)l_service.send(l_request,l_blnIsMarketNotSendMessage);;
            fail();
            
        }
        catch (WEB3BaseRuntimeException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,e.getErrorInfo());
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 取消注文送信
     * 
     * is取引所休憩時間帯() = true
     */
    public void test_send_0111()
    {
        final String STR_METHOD_NAME = "test_send_0111()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplAForTest l_service = new WEB3IfoMarketRequestSenderServiceImplAForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_TradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(l_subAccount, l_lngOrderId);
        boolean l_blnIsMarketNotSendMessage = false;
        
//        try
//        {
//            DefaultMarketRequestSendResult l_defaultMarketRequestSendResult =
//                (DefaultMarketRequestSendResult)l_service.send(l_request,l_blnIsMarketNotSendMessage);
//            assertEquals(0L,l_defaultMarketRequestSendResult.getMessageTokenId());
//        }
//        catch (TooLateException e)
//        {
//            log.debug("",e);
//            fail();
//        }
        
        try
        {
            l_service.send(l_request,l_blnIsMarketNotSendMessage);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");

            List l_lstHostFotypeOrderAllWhere = new ArrayList();
            l_lstHostFotypeOrderAllWhere.add("101001010010");

            Object[] l_objTradingTimeWhere = 
                new Object[l_lstHostFotypeOrderAllWhere.size()];
            l_lstHostFotypeOrderAllWhere.toArray(l_objTradingTimeWhere);

            List l_lstRecords;

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lstRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objTradingTimeWhere);
                
                HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRecords.get(0);
                assertEquals("101001010010","" + l_row.getAccountId());

            }
            catch (DataException de)
            {
                log.debug("",de);
                fail();
            }
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 取消注文送信
     * 
     * is取引所休憩時間帯() = false
     * トリガー発行あり = true
     */
    public void test_send_0112()
    {
        final String STR_METHOD_NAME = "test_send_0112()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplAForTest l_service = new WEB3IfoMarketRequestSenderServiceImplAForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        //市場通知要の訂正注文 100 = true 1 = false
        l_ifoOrderUnitParams.setConfirmedQuantity(1);
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        
        //is取引所休憩時間帯() 0 = true 1 = false
        //トリガー発行あり 1 = true 0 = false
        l_TradingTimeParams.setSubmitMarketTrigger("1");
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(l_subAccount, l_lngOrderId);
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            l_service.send(l_request,l_blnIsMarketNotSendMessage);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? ");

            List l_lstHostFotypeOrderAllWhere = new ArrayList();
            l_lstHostFotypeOrderAllWhere.add("101001010010");

            Object[] l_objTradingTimeWhere = 
                new Object[l_lstHostFotypeOrderAllWhere.size()];
            l_lstHostFotypeOrderAllWhere.toArray(l_objTradingTimeWhere);

            List l_lstRecords;

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lstRecords = l_queryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbWhere.toString(),
                    l_objTradingTimeWhere);
                
                HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRecords.get(0);
                assertEquals("101001010010","" + l_row.getAccountId());

            }
            catch (DataException de)
            {
                log.debug("",de);
                fail();
            }
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    /**
     * 取消注文送信
     * 
     * is市場未送信 == false
     * is市場開局時間帯() == true
     * is市場未送信 == false && 取引時間管理.is夕場時間帯() == true && 注文単位.立会区分 == null
     * 
     */
    public void test_send_0113() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "test_send_0113()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoMarketRequestSenderServiceImplCForTest l_service = new WEB3IfoMarketRequestSenderServiceImplCForTest();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        //市場通知要の訂正注文 100 = true 1 = false
        l_ifoOrderUnitParams.setConfirmedQuantity(1);
        
        l_ifoOrderUnitParams.setSessionType(null);
        
        ProductParams l_productParams = this.getProductRow();
        IfoProductParams l_ifoProductParams = this.getIfoProductRow();
        
        MarketParams l_marketParams = this.getMarketRow();
        TradingTimeParams l_TradingTimeParams = this.getTradingTimeRow();
        //
        l_TradingTimeParams.setTradingTimeType("10");
        //夕場時間帯
        l_TradingTimeParams.setSessionType("1");
        
        //is取引所休憩時間帯() 0 = true 1 = false
        //トリガー発行あり 1 = true 0 = false
        l_TradingTimeParams.setSubmitMarketTrigger("0");
        try
        {          
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(ProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(IfoOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_ifoProductParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_TradingTimeParams);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(l_subAccount, l_lngOrderId);
        boolean l_blnIsMarketNotSendMessage = false;
        
        try
        {
            l_service.send(l_request,l_blnIsMarketNotSendMessage);;
            fail();    
        }
        catch (WEB3BaseRuntimeException e)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,e.getErrorInfo());
        }
        catch (TooLateException e)
        {
            log.debug("",e);
            fail();
        }
    }
    
    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("2");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        
        l_params.setConfirmedExecConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setConfirmedQuantity(100);
        return l_params;
    }

    /**
     * 銘柄Rowを作成.<BR>
     */
    public static ProductParams getProductRow()
    {
        ProductParams l_productParams = new ProductParams();

        l_productParams.setProductId(1006169090018L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        l_productParams.setStandardName("シンセンテルス");
        l_productParams.setLotSize(0.000000D);
        l_productParams.setCalcSize(1.000000D);
        l_productParams.setEstimationPrice(0.000000D);
        l_productParams.setMarginRatio(0.000000D);
        l_productParams.setSecuritiesEstimationRatio(0.000000D);
        l_productParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_productParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_productParams;
    }
    
    /**
     * 先物OP銘柄テーブル (ifo_product)
     */
    public static IfoProductParams getIfoProductRow()
    {
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setInstitutionCode("10");
        l_ifoProductParams.setProductCode("160030005");
        l_ifoProductParams.setUnderlyingProductCode("0005");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(0);
        l_ifoProductParams.setMonthOfDelivery("200503");
        l_ifoProductParams.setExerciseDate(WEB3DateUtility.getDate("20070112","yyyyMMdd"));
        l_ifoProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_ifoProductParams;
    }
    
    /**
     * 市場Rowを作成.<BR>
     */
    public static MarketParams getMarketRow()
    {
        MarketParams l_marketParams = new MarketParams();

        l_marketParams.setMarketId(1002);
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

        return l_marketParams;
    }
    
    /**
     * 取引時間Rowを作成.<BR>
     */
    public static TradingTimeParams getTradingTimeRow()
    {
        TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("235959");
        l_tradingTimeParams.setSubmitMarketTrigger("0");//TODO 1 or 0
        l_tradingTimeParams.setEnableOrder("0");
        l_tradingTimeParams.setBizdateCalcParameter("1");
        l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        
        l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_tradingTimeParams;
    }

    public class WEB3IfoMarketRequestSenderServiceImplAForTest extends WEB3IfoMarketRequestSenderServiceImpl
    {
        /**
         * (訂正確定)<BR>
         * <BR>
         * 訂正を確定させる。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP市場ﾘｸｴｽﾄ）訂正確定」参照。<BR>
         * @@param l_orderUnit - 注文単位オブジェクト<BR>
         * @@roseuid 40A463D90009
         */
        protected void updateOrderModified(OrderUnit l_orderUnit)
        throws WEB3BaseException
        {
            assertEquals("101001010010", "" + l_orderUnit.getAccountId());
        }
    }
    
    public class WEB3IfoMarketRequestSenderServiceImplForTestB extends WEB3IfoMarketRequestSenderServiceImpl
    {
        /**
         * (訂正確定)<BR>
         * <BR>
         * 訂正を確定させる。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP市場ﾘｸｴｽﾄ）訂正確定」参照。<BR>
         * @@param l_orderUnit - 注文単位オブジェクト<BR>
         * @@roseuid 40A463D90009
         */
        protected void updateOrderModified(OrderUnit l_orderUnit)
        throws WEB3BaseException
        {
            assertEquals("100.0", "" + l_orderUnit.getConfirmedQuantity());
        }
    }
    
    public class WEB3IfoMarketRequestSenderServiceImplCForTest extends WEB3IfoMarketRequestSenderServiceImpl
    {
        /**
         * (訂正確定)<BR>
         * <BR>
         * 訂正を確定させる。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP市場ﾘｸｴｽﾄ）訂正確定」参照。<BR>
         * @@param l_orderUnit - 注文単位オブジェクト<BR>
         * @@roseuid 40A463D90009
         */
        protected void updateOrderModified(OrderUnit l_orderUnit)
        throws WEB3BaseException
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,"");
//            assertEquals("100.0", "" + l_orderUnit.getConfirmedQuantity());
        }
        
        /**
         * (取消確定)<BR>
         * <BR>
         * 取消を確定させる。<BR>
         * <BR>
         * シーケンス図<BR>
         * 「（先物OP市場ﾘｸｴｽﾄ）取消確定」参照。<BR>
         * @@param l_orderUnit - 注文単位オブジェクト<BR>
         * @@roseuid 40A81D830072
         */
        protected void updateOrderCancelled(OrderUnit l_orderUnit)
            throws WEB3BaseException
        {
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,"");
        }
    }
}
@
