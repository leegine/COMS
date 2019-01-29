head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccEquityChangeOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.service.delegate.stdimpls;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityConfirmCommonResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

/**
 *
 */
public class WEB3ToSuccEquityChangeOrderServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityChangeOrderServiceImplTest.class);

    /**
     *
     */
    public WEB3ToSuccEquityChangeOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    private WEB3ToSuccEquityChangeOrderServiceImplInner l_equityChangeOrderServiceImplInner =
        new WEB3ToSuccEquityChangeOrderServiceImplInner();

    /**
     *
     */
    public class WEB3ToSuccEquityChangeOrderServiceImplInner extends WEB3ToSuccEquityChangeOrderServiceImpl
    {
        protected WEB3GenResponse validateOrder(WEB3GenRequest l_request) throws WEB3BaseException 
        {
            WEB3EquityConfirmCommonResponse l_response = new WEB3EquityConfirmCommonResponse();
            l_response.estimatedPrice = "1.1";
            return l_response;
        }

        protected WEB3SuccEquityBuyConfirmRequest createBuyConfirmRequest(
            WEB3SuccEquityChangeConfirmRequest l_request, 
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) throws WEB3BaseException 
        {
            WEB3SuccEquityBuyConfirmRequest l_equityBuyConfirmRequest = new WEB3SuccEquityBuyConfirmRequest();
            return l_equityBuyConfirmRequest;
        }

        /**
         * @@param l_request - (l_request)<BR>
         * l_request<BR>
         * @@return WEB3SuccMarginCloseConfirmResponse
         * @@throws WEB3BaseException
         */
        protected WEB3SuccMarginCloseConfirmResponse validateOrder(
            WEB3SuccMarginCloseConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3SuccMarginCloseConfirmResponse l_response = new WEB3SuccMarginCloseConfirmResponse();
            l_response.expirationDate = WEB3DateUtility.getDate("20040130", "yyyyMMdd");
            return l_response;
        }

        /**
         * @@param l_request - (l_request)<BR>
         * l_request<BR>
         * @@return WEB3SuccMarginCloseConfirmResponse
         * @@throws WEB3BaseException
         */
        protected WEB3SuccMarginCloseCompleteResponse submitOrder(
            WEB3SuccMarginCloseCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3SuccMarginCloseCompleteResponse l_response = new WEB3SuccMarginCloseCompleteResponse();
            l_response.expirationDate = WEB3DateUtility.getDate("20040230", "yyyyMMdd");
            return l_response;
        }

        protected WEB3TPTradingPowerResult validateChangeTradingPower(
            WEB3GentradeSubAccount l_subAccount,
            double l_dblEstimatedPrice, 
            WEB3EquityTradedProduct l_tradedProduct, 
            WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit) 
            throws WEB3BaseException
        {
            return null;
        }
    }


    /**
     * <BR>
     */
    public void test_validateOrder_0001()
    {
        final String STR_METHOD_NAME =
            " test_validateOrder_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001L));
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070101", "yyyyMMdd"));
            WEB3SuccEquityChangeConfirmRequest l_request = new WEB3SuccEquityChangeConfirmRequest();
            l_request.priceAdjustmentValueInfo = null;
            l_request.id = "123";
            l_request.orderQuantity = "1";
            l_request.orderPriceDiv = "1";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.priceCondType = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.orderQuantity = "1";

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);

            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(123L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setSex("0");
            l_mainAccountParams.setAccountId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountRow = TestDBUtility.getSubAccountRow();
            l_subAccountRow.setAccountId(1001L);
            l_subAccountRow.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountRow);

            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(123L);
            l_rsvEqOrderUnitParams.setParentOrderId(123L);
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setBizDate("20070101");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            EqtypeOrderUnitParams l_eqtypeOrderUnitRow = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitRow.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitRow.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitRow.setForcedSettleReasonType(null);
            l_eqtypeOrderUnitRow.setOrderId(123L);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitRow);

            WEB3SuccEquityChangeConfirmResponse l_response =
                l_equityChangeOrderServiceImplInner.validateChangeOrder(l_request);
            assertNull(l_response.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

}
@
