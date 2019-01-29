head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesCancelOrderServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物取消注文サービスインタセプタ(WEB3ToSuccFuturesCancelOrderServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/25 孟亞南(中訊) 新規作成モデル268 モデル294
*/
package webbroker3.triggerorder;

import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmRequest;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesCancelOrderServiceInterceptorTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3ToSuccFuturesCancelOrderServiceInterceptorTest.class);

    WEB3ToSuccFuturesCancelOrderServiceInterceptor l_interceptor =
        new WEB3ToSuccFuturesCancelOrderServiceInterceptor();

    public WEB3ToSuccFuturesCancelOrderServiceInterceptorTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * oncall
     *
     */
    public void test_onCall_0001()
    {
        final String STR_METHOD_NAME = "test_onCall_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("213123");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(21);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("213123");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();
            l_request.id = "21";
            
            Object[] l_serviceParams = {l_request};
            l_interceptor.onCall(null, l_serviceParams);

            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry
                .getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            Integer l_offset = (Integer)ThreadLocalSystemAttributesRegistry
            .getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG);
            assertEquals("0", l_offset.toString());
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("12", l_context.getTradingTimeType());
            
            assertEquals("213123", l_context.getProductCode());
            
            assertEquals("05", l_context.getOrderAcceptProduct());
            assertEquals("06", l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * oncall
     *
     */
    public void test_onCall_0002()
    {
        final String STR_METHOD_NAME = "test_onCall_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("11");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(21);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("213123");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();
            l_request.id = "21";
            
            Object[] l_serviceParams = {l_request};
            l_interceptor.onCall(null, l_serviceParams);

            // 予想結果と実際結果の比較
//            WEB3GentradeTradingClendarContext l_context =
//                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry
//                .getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
//            
//            Integer l_offset = (Integer)ThreadLocalSystemAttributesRegistry
//            .getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG);
//            assertEquals("0", l_offset.toString());
//            
//            assertEquals("0D", l_context.getInstitutionCode());
//            assertEquals("381", l_context.getBranchCode());
//            assertEquals("0", l_context.getMarketCode());
//            assertEquals("12", l_context.getTradingTimeType());
//            
//            assertEquals("213123", l_context.getProductCode());
//            
//            assertEquals("05", l_context.getOrderAcceptProduct());
//            assertEquals("06", l_context.getOrderAcceptTransaction());
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }


        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onReturn
     * onThrowable
     */
    public void test_onReturnOronThrowable_0001()
    {
        final String STR_METHOD_NAME = "test_onReturnOronThrowable_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
