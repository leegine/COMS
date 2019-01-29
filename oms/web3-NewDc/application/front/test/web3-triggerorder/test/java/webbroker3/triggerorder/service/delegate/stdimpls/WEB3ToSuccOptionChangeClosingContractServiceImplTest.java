head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeClosingContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : (株)大和総研 証券ソリューションシステム第二部 
 File Name        : WEB3ToSuccOptionChangeClosingContractServiceImplTest.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/04/22 楊夫志 (中訊) 新規作成  
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCommonRequest;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeClosingContractServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3ToSuccOptionChangeClosingContractServiceImplTest.class);

    public WEB3ToSuccOptionChangeClosingContractServiceImplTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(IfoProductRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(MarketRow.TYPE);

        MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_request == null的場合
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();
        try
        {
            l_impl.execute(null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccOptionsCloseChangeConfirmRequest的場合
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();

        try
        {
            WEB3SuccOptionsCloseChangeConfirmResponse l_response = (WEB3SuccOptionsCloseChangeConfirmResponse) l_impl
                    .execute(l_request);
            assertEquals("123", l_response.checkPrice);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccOptionsCloseChangeConfirmRequest的場合
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();

        try
        {
            WEB3SuccOptionsCloseChangeCompleteResponse l_response = (WEB3SuccOptionsCloseChangeCompleteResponse) l_impl
                    .execute(l_request);
            assertEquals("456", l_response.orderActionId);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80018
     * 
     * l_request instanceof WEB3SuccOptionsOpenChangeConfirmRequest的場合
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();

        try
        {
            l_impl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    class WEB3SubToSuccOptionChangeClosingContractServiceImpl extends WEB3ToSuccOptionChangeClosingContractServiceImpl
    {
        protected WEB3SuccOptionsCloseChangeConfirmResponse validateOrder(
                WEB3SuccOptionsCloseChangeConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3SuccOptionsCloseChangeConfirmResponse l_response = new WEB3SuccOptionsCloseChangeConfirmResponse();
            l_response.checkPrice = "123";
            return l_response;
        }

        protected WEB3SuccOptionsCloseChangeCompleteResponse submitOrder(
                WEB3SuccOptionsCloseChangeCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3SuccOptionsCloseChangeCompleteResponse l_response = new WEB3SuccOptionsCloseChangeCompleteResponse();
            l_response.orderActionId = "456";
            return l_response;
        }

        protected WEB3OptionsCommonRequest setOptionsCommonRequest(WEB3OptionsCommonRequest l_outputCommonRequest,
                WEB3OptionsCommonRequest l_inputCommonRequest)
        {
            return null;
        }
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_request == null的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0001()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(null);

        try
        {
            l_impl.validateRequestDataAtReversingTrade(null, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_orderUnit == null的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0002()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();

        try
        {
            l_impl.validateRequestDataAtReversingTrade(l_request, null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccOptionsCloseChangeConfirmRequest
     * l_orderUnit.isReversingTrade() == true的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0003()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionCloseChangeConfirmRequest l_request = new WEB3SubSuccOptionCloseChangeConfirmRequest();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(true));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccOptionsCloseChangeConfirmRequest
     * l_orderUnit.isReversingTrade() == false的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0004()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionCloseChangeConfirmRequest l_request = new WEB3SubSuccOptionCloseChangeConfirmRequest();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(false));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccOptionsCloseChangeCompleteRequest
     * l_orderUnit.isReversingTrade() == false的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0005()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionsCloseChangeCompleteRequest l_request = new WEB3SubSuccOptionsCloseChangeCompleteRequest();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(false));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccOptionsCloseChangeCompleteRequest
     * l_orderUnit.isReversingTrade() == true的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0006()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionsCloseChangeCompleteRequest l_request = new WEB3SubSuccOptionsCloseChangeCompleteRequest();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(true));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80018
     * 
     * l_request instanceof WEB3SuccOptionsCancelConfirmRequest的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0007()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0007()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SuccOptionsCancelConfirmRequest l_request = new WEB3SuccOptionsCancelConfirmRequest();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_03060
     * 
     * l_strQuantity == null的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0008()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0008()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionCloseChangeConfirmRequest l_request = new WEB3SubSuccOptionCloseChangeConfirmRequest();
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(true));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_03060
     * 
     * l_strQuantity == asd的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0009()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0009()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionCloseChangeConfirmRequest l_request = new WEB3SubSuccOptionCloseChangeConfirmRequest();
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "asd";
        l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(true));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_03060
     * 
     * l_strQuantity == 123456789的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0010()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0009()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        WEB3SubSuccOptionCloseChangeConfirmRequest l_request = new WEB3SubSuccOptionCloseChangeConfirmRequest();
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "123456789";
        l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, new Boolean(true));

            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03060, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    class WEB3SubSuccOptionCloseChangeConfirmRequest extends WEB3SuccOptionsCloseChangeConfirmRequest
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public void validateATReserveOrder() throws WEB3BaseException
        {

        }

        public void validateATExistingRemainderTrading() throws WEB3BaseException
        {

        }

        public void validate() throws WEB3BaseException
        {

        }
    }

    class WEB3SubSuccOptionsCloseChangeCompleteRequest extends WEB3SuccOptionsCloseChangeCompleteRequest
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public void validateATExistingRemainderTrading() throws WEB3BaseException
        {

        }

        public void validateATReserveOrder() throws WEB3BaseException
        {

        }

        public void validate() throws WEB3BaseException
        {

        }
    }

    /**
     * 正常終了
     */
    public void testCreateConfirmRequest_C0001()
    {
        final String STR_METHOD_NAME = "testCreateConfirmRequest_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        // param 1
        WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();

        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        l_futuresOptionsCloseMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnits[0].contractOrderQuantity = "123";
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;

        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_succPriceAdjustmentValueInfo.priceAdjustmentValue = "456";
        l_request.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;

        // param 2
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder("1");
        l_rsvIfoOrderUnitParams.setParentOrderId(2);
        l_rsvIfoOrderUnitParams.setReserveOrderTradingType("3");
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_succOptionsCloseConfirmRequest = l_impl.createConfirmRequest(
                    l_request, l_toSuccIfoOrderUnitImpl);

            assertEquals("123", l_succOptionsCloseConfirmRequest.closeMarginContractUnits[0].contractOrderQuantity);
            assertEquals("456", l_succOptionsCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue);

            assertEquals("1", l_succOptionsCloseConfirmRequest.closingOrder);
            assertEquals("2", l_succOptionsCloseConfirmRequest.succCommonInfo.parentOrderId);
            assertEquals("3", l_succOptionsCloseConfirmRequest.succCommonInfo.succTradingType);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     */
    public void testCreateCompleteRequest_C0001()
    {
        final String STR_METHOD_NAME = "testCreateCompleteRequest_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        // param 1
        WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();

        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
        l_futuresOptionsCloseMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnits[0].contractOrderQuantity = "2";
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
        l_request.password = "4";
        l_request.checkPrice = "5";
        Date l_datExcept = WEB3DateUtility.getDate("20040808", "yyyyMMdd");
        l_request.checkDate = l_datExcept;
        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_succPriceAdjustmentValueInfo.priceAdjustmentValue = "7";
        l_request.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;
        l_request.afterAdjustmentPrice = "8";

        // param 2
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setOrderId(1);
        l_rsvIfoOrderUnitParams.setClosingOrder("3");
        l_rsvIfoOrderUnitParams.setParentOrderId(6);

        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            WEB3SuccOptionsCloseCompleteRequest l_succOptionsCloseCompleteRequest = l_impl.createCompleteRequest(
                    l_request, l_toSuccIfoOrderUnitImpl);
            assertEquals("2", l_succOptionsCloseCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity);
            assertEquals("3", l_succOptionsCloseCompleteRequest.closingOrder);
            assertEquals("4", l_succOptionsCloseCompleteRequest.password);
            assertEquals("5", l_succOptionsCloseCompleteRequest.checkPrice);
            Date l_datTrue = WEB3DateUtility.getDate("20040808", "yyyyMMdd");
            assertEquals(l_datTrue, l_succOptionsCloseCompleteRequest.checkDate);
            assertEquals("6", l_succOptionsCloseCompleteRequest.succCommonInfo.parentOrderId);
            assertEquals("7", l_succOptionsCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            assertEquals("8", l_succOptionsCloseCompleteRequest.afterAdjustmentPrice);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_adapter.isReversingOrder() == false的場合
     */
    public void testCreateSettleContractEntry_C0001()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntry_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            // param 1
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter = new WEB3ToSuccOptionSettleContractOrderRequestAdapter();

            // param 2
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;

            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[1];
            SettleContractEntry l_settleContractEntry = new SettleContractEntry(1, 2);
            l_settleContractEntries[0] = l_settleContractEntry;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_settleContractEntries);

            SettleContractEntry[] l_settleContractEntryResults = l_impl.createSettleContractEntry(l_adapter,
                    l_futuresOptionsCloseMarginContractUnits);
            assertEquals(1, l_settleContractEntryResults[0].getContractId());
            assertEquals(2, l_settleContractEntryResults[0].getQuantity(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    // STOP
    /**
     * 正常終了
     * 
     * l_adapter.isReversingOrder() == true的場合
     */
    public void testCreateSettleContractEntry_C0002()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntry_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            // param 1
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter = new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER;
            l_adapter.request = l_request;

            // param 2
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;

            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "1";

            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setQuantity(2);
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(
                    l_rsvIfoOrderUnitParams);
            l_adapter.parentOrderUnit = l_toSuccIfoOrderUnitImpl;
            // getOrderQuantity

            // result
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[1];
            SettleContractEntry l_settleContractEntry = new SettleContractEntry(1, 2);
            l_settleContractEntries[0] = l_settleContractEntry;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_settleContractEntries);

            // execute
            SettleContractEntry[] l_settleContractEntryResults = l_impl.createSettleContractEntry(l_adapter,
                    l_futuresOptionsCloseMarginContractUnits);
            assertEquals(1, l_settleContractEntryResults[0].getQuantity(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常結束
     * 
     * 結果為1
     */
    public void testGetOrderQuantity_C0001()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            // param 1
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter = new WEB3ToSuccOptionSettleContractOrderRequestAdapter();

            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.closingOrder = "1";
            l_request.opOrderQuantity = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            l_adapter.request = l_request;

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setQuantity(2);
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(
                    l_rsvIfoOrderUnitParams);
            l_adapter.parentOrderUnit = l_toSuccIfoOrderUnitImpl;

            // execute
            Method l_method = WEB3ToSuccOptionChangeClosingContractServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", new Class[]
                    {WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            Double l_dblOrderQuantity = (Double) l_method.invoke(l_impl, new Object[]
            {l_adapter});

            assertEquals(1, l_dblOrderQuantity.doubleValue(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_03065
     */
    public void testGetOrderQuantity_C0002()
    {
        final String STR_METHOD_NAME = "testGetOrderQuantity_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SubToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3SubToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            // param 1
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter = new WEB3ToSuccOptionSettleContractOrderRequestAdapter();

            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            l_request.closingOrder = "0";
            l_request.opOrderQuantity = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "2";
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            l_adapter.request = l_request;

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setQuantity(1);
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(
                    l_rsvIfoOrderUnitParams);
            l_adapter.parentOrderUnit = l_toSuccIfoOrderUnitImpl;

            // execute
            Method l_method = WEB3ToSuccOptionChangeClosingContractServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", new Class[]
                    {WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]
            {l_adapter});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            WEB3BusinessLayerException l_exception = (WEB3BusinessLayerException) l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03065, l_exception.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testsetOptionsCommonRequest_C0001()
    {
        final String STR_METHOD_NAME = "testsetOptionsCommonRequest_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            WEB3OptionsCommonRequest l_inputCommonRequest = new WEB3OptionsCommonRequest();
            l_inputCommonRequest.opOrderQuantity = "1";
            l_inputCommonRequest.orderPriceDiv = "2";
            l_inputCommonRequest.limitPrice = "3";
            l_inputCommonRequest.execCondType = "4";
            l_inputCommonRequest.expirationDateType = "5";
            Date l_datExcept = WEB3DateUtility.getDate("20020202", "yyyyMMdd");
            l_inputCommonRequest.expirationDate = l_datExcept;
            l_inputCommonRequest.orderCondType = "6";
            l_inputCommonRequest.stopOrderCondPrice = "7";
            l_inputCommonRequest.stopOrderCondOperator = "8";
            l_inputCommonRequest.wlimitOrderCondPrice = "9";
            l_inputCommonRequest.wlimitOrderCondOperator = "10";
            l_inputCommonRequest.wLimitOrderPriceDiv = "11";
            l_inputCommonRequest.wLimitPrice = "12";
            l_inputCommonRequest.wlimitExecCondType = "13";
            l_inputCommonRequest.wlimitEnableStatusDiv = "14";
            WEB3OptionsCommonRequest l_outputCommonRequest = new WEB3OptionsCommonRequest();
            WEB3OptionsCommonRequest l_result = l_impl.setOptionsCommonRequest(l_outputCommonRequest,
                    l_inputCommonRequest);
            assertEquals("1", l_result.opOrderQuantity);
            assertEquals("2", l_result.orderPriceDiv);
            assertEquals("3", l_result.limitPrice);
            assertEquals("4", l_result.execCondType);
            assertEquals("5", l_result.expirationDateType);
            Date l_datResult = WEB3DateUtility.getDate("20020202", "yyyyMMdd");
            assertEquals(l_datResult, l_result.expirationDate);
            assertEquals("6", l_result.orderCondType);
            assertEquals("7", l_result.stopOrderCondPrice);
            assertEquals("8", l_result.stopOrderCondOperator);
            assertEquals("9", l_result.wlimitOrderCondPrice);
            assertEquals("10", l_result.wlimitOrderCondOperator);
            assertEquals("11", l_result.wLimitOrderPriceDiv);
            assertEquals("12", l_result.wLimitPrice);
            assertEquals("13", l_result.wlimitExecCondType);
            assertEquals("14", l_result.wlimitEnableStatusDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_03066
     */
    public void testValidateSettledContract_C0001()
    {
        final String STR_METHOD_NAME = "testValidateSettledContract_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

        try
        {
            l_impl.validateSettledContract(l_toSuccIfoOrderUnitImpl);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03066, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * リクエストデータの整合性をチェックする
     */
    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = null;
            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02243, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 
     * validateリクエストデータat反対取引(WEB3GenRequest, 先物OP予約注文単位Impl)
     */
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.priceAdjustmentValueInfo = null;
            l_request.id = "1001";

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validate訂正可能状態( )
     * 
     */
    public void testValidateOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImplForTest1 l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest1();

        try
        {
            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.priceAdjustmentValueInfo = null;

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);

            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validate決済済建玉(先物OP予約注文単位Impl)
     * 
     */
    public void testValidateOrder_C0004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.priceAdjustmentValueInfo = null;

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.insertWithDel(l_branchParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            

//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03066, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
     * 
     */
    public void testValidateOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3FuturesOptionsContractUnit l_unit = new WEB3FuturesOptionsContractUnit();
            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[]{l_unit};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class},
                    l_units);
                    
            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_contractUnit.contractOrderQuantity = "123";
            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.expirationDateType = "3";
            l_request.priceAdjustmentValueInfo = null;
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.orderCondType = "0";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{l_contractUnit};

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("17");
            l_rsvIfoOrderUnitParams.setClosingOrder("0");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams0 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams0.setInstitutionCode("0D");
            l_tradingTimeParams0.setBranchCode("123");
            l_tradingTimeParams0.setMarketCode("N1");
            l_tradingTimeParams0.setTradingTimeType("01");
            l_tradingTimeParams0.setProductCode("000005");
            l_tradingTimeParams0.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams0);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * success
     * 
     */
    public void testValidateOrder_C0006()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3FuturesOptionsContractUnit l_unit = new WEB3FuturesOptionsContractUnit();
            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[]{l_unit};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class},
                    l_units);
                    
            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_contractUnit.contractOrderQuantity = "123";
            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.expirationDateType = "3";
            l_request.priceAdjustmentValueInfo = null;
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.orderCondType = "0";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{l_contractUnit};

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("17");
            l_rsvIfoOrderUnitParams.setClosingOrder("0");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams0 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams0.setInstitutionCode("0D");
            l_tradingTimeParams0.setBranchCode("123");
            l_tradingTimeParams0.setMarketCode("N1");
            l_tradingTimeParams0.setTradingTimeType("01");
            l_tradingTimeParams0.setProductCode("000005");
            l_tradingTimeParams0.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams0);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateEveningSessionOrderPossibleChange", new Class[]
                    {String.class, WEB3ToSuccIfoOrderUnitImpl.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3SuccOptionsCloseChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            
            assertEquals("18", "" + l_response.contractUnits[0].contractQuantity);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
//    
//    /**
//     * リクエスト.単価調整値情報=null（±指値指定）の場合 validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
//     */
//    public void testValidateOrder_C0006()
//    {
//        final String STR_METHOD_NAME = "testValidateOrder_C0006()";
//        log.entering(STR_METHOD_NAME);
//
//        WEB3ToSuccOptionChangeClosingContractServiceImplForTest1 l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest1();
//
//        try
//        {
//            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
//            l_request.id = "1001";
//            l_request.expirationDateType = "1";
//            l_request.priceAdjustmentValueInfo = null;
//
//            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            l_rsvIfoOrderUnitParams.setOrderId(1001);
//            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
//            l_rsvIfoOrderUnitParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
//                    (WEB3DateUtility.getDate("20080424", "yyyyMMdd")).getTime()));
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
//            l_orderexecutionEndParams.setInstitutionCode("0D");
//            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
//            l_orderexecutionEndParams.setFutureOptionDiv("2");
//            l_orderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
//
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1001);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            WEB3SuccOptionsCloseChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
//
//            assertEquals("0", l_response.contractUnits[0].contractCommission);
//
//            assertEquals("1", l_response.estimatedPrice);
//
//            assertEquals("2", l_response.commissionCourse);
//
//            assertEquals("3", l_response.commission);
//
//            assertEquals("4", l_response.commissionConsumptionTax);
//
//            assertEquals("5", l_response.messageSuspension[0]);
//
//            assertEquals("6", l_response.checkPrice);
//
//            Date l_datResult1 = WEB3DateUtility.getDate("20050421", "yyyyMMdd");
//            assertEquals(l_datResult1, l_response.checkDate);
//            Date l_datResult2 = WEB3DateUtility.getDate("20050321", "yyyyMMdd");
//            assertEquals(l_datResult2, l_response.expirationDate);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
//
//    /**
//     * リクエスト.単価調整値情報≠null（±指値指定）の場合 validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
//     */
//    public void testValidateOrder_C0007()
//    {
//        final String STR_METHOD_NAME = "testValidateOrder_C0007()";
//        log.entering(STR_METHOD_NAME);
//
//        WEB3ToSuccOptionChangeClosingContractServiceImplForTest1 l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest1();
//
//        try
//        {
//            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
//            l_request.id = "1001";
//            l_request.expirationDateType = "1";
//            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
//            l_request.priceAdjustmentValueInfo.sign = "+";
//            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
//            l_request.orderPriceDiv = "0";
//
//            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            l_rsvIfoOrderUnitParams.setOrderId(1001);
//            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
//            l_rsvIfoOrderUnitParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
//                    (WEB3DateUtility.getDate("20080424", "yyyyMMdd")).getTime()));
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
//            l_orderexecutionEndParams.setInstitutionCode("0D");
//            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
//            l_orderexecutionEndParams.setFutureOptionDiv("2");
//            l_orderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
//
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1001);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            WEB3SuccOptionsCloseChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
//
//            assertEquals("0", l_response.contractUnits[0].contractCommission);
//
//            assertEquals("1", l_response.estimatedPrice);
//
//            assertEquals("2", l_response.commissionCourse);
//
//            assertEquals("3", l_response.commission);
//
//            assertEquals("4", l_response.commissionConsumptionTax);
//
//            assertEquals("5", l_response.messageSuspension[0]);
//
//            assertEquals("6", l_response.checkPrice);
//
//            Date l_datResult1 = WEB3DateUtility.getDate("20050421", "yyyyMMdd");
//            assertEquals(l_datResult1, l_response.checkDate);
//            Date l_datResult2 = WEB3DateUtility.getDate("20050321", "yyyyMMdd");
//            assertEquals(l_datResult2, l_response.expirationDate);
//
//            assertEquals("7", l_response.afterAdjustmentPrice);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }

    class WEB3SubSuccOptionCloseConfirmRequest extends WEB3SuccOptionsCloseConfirmRequest
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public void validateATReserveOrder() throws WEB3BaseException
        {

        }

        public void validate() throws WEB3BaseException
        {

        }
    }

    /**
     * リクエストデータの整合性をチェックする
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.estimatedPrice = null;

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02292, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validateリクエストデータat反対取引(WEB3GenRequest, 先物OP予約注文単位Impl)
     * 
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImpl();

        try
        {
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.estimatedPrice = "1000";
            l_request.priceAdjustmentValueInfo = null;
            l_request.id = "1001";

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validate訂正可能状態( )
     * 
     */
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImplForTest2 l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest2();

        try
        {
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.estimatedPrice = "1000";
            l_request.priceAdjustmentValueInfo = null;
            l_request.id = "1001";

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validate決済済建玉(先物OP予約注文単位Impl)
     * 
     */
    public void testSubmitOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.id = "1001";
            l_request.estimatedPrice="1000";
            l_request.priceAdjustmentValueInfo = null;
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.insertWithDel(l_branchParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            

//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03066, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
     * 
     */
    public void testSubmitOrder_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3FuturesOptionsContractUnit l_unit = new WEB3FuturesOptionsContractUnit();
            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[]{l_unit};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class},
                    l_units);
                    
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.estimatedPrice = "1000";
            l_request.priceAdjustmentValueInfo = null;
            l_request.expirationDateType = "3";
            l_request.id = "1001";
                    
            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_contractUnit.contractOrderQuantity = "123";
//            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.expirationDateType = "3";
            l_request.priceAdjustmentValueInfo = null;
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.orderCondType = "0";
            l_request.estimatedPrice = "1000";
            l_request.checkPrice = "1";
            l_request.checkDate = WEB3DateUtility.getDate("20080423", "yyyyMMdd");
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{l_contractUnit};

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("17");
            l_rsvIfoOrderUnitParams.setClosingOrder("0");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams0 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams0.setInstitutionCode("0D");
            l_tradingTimeParams0.setBranchCode("123");
            l_tradingTimeParams0.setMarketCode("N1");
            l_tradingTimeParams0.setTradingTimeType("01");
            l_tradingTimeParams0.setProductCode("000005");
            l_tradingTimeParams0.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams0);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 
     * リクエストデータ.注文単価区分 != "成行"の場合 リクエストデータ.注文期限区分 == "出来るまで注文"の場合
     * リクエストデータ.注文単価区分 =指値 リクエストデータ.単価調整値情報 != nullの場合
     */
    public void testSubmitOrder_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0006()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3FuturesOptionsContractUnit l_unit = new WEB3FuturesOptionsContractUnit();
            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[]{l_unit};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class},
                    l_units);
                    
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.estimatedPrice = "1000";
            l_request.priceAdjustmentValueInfo = null;
            l_request.expirationDateType = "3";
            l_request.id = "1001";
                    
            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_contractUnit.contractOrderQuantity = "123";
//            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.expirationDateType = "3";
            l_request.priceAdjustmentValueInfo = null;
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.orderCondType = "0";
            l_request.estimatedPrice = "1000";
            l_request.checkPrice = "1";
            l_request.checkDate = WEB3DateUtility.getDate("20080423", "yyyyMMdd");
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{l_contractUnit};

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("17");
            l_rsvIfoOrderUnitParams.setClosingOrder("0");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams0 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams0.setInstitutionCode("0D");
            l_tradingTimeParams0.setBranchCode("123");
            l_tradingTimeParams0.setMarketCode("N1");
            l_tradingTimeParams0.setTradingTimeType("01");
            l_tradingTimeParams0.setProductCode("000005");
            l_tradingTimeParams0.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams0);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeSettleContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateEveningSessionOrderPossibleChange", new Class[]
                    {String.class, WEB3ToSuccIfoOrderUnitImpl.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3SuccOptionsCloseChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertFalse(l_response.succSettingFlag);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    /**
     * 
     * リクエストデータ.注文単価区分 = "成行"の場合 リクエストデータ.注文期限区分 == "出来るまで注文"の場合
     * リクエストデータ.単価調整値情報 = nullの場合
     */
    public void testSubmitOrder_C0007()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0007()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOptionChangeClosingContractServiceImpl l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest();

        try
        {
            WEB3FuturesOptionsContractUnit l_unit = new WEB3FuturesOptionsContractUnit();
            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[]{l_unit};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContractUnitByOrder", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class},
                    l_units);
                    
            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
            l_request.estimatedPrice = "1000";
            l_request.priceAdjustmentValueInfo = null;
            l_request.expirationDateType = "3";
            l_request.id = "1001";
                    
            WEB3FuturesOptionsCloseMarginContractUnit l_contractUnit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_contractUnit.contractOrderQuantity = "123";
//            WEB3SuccOptionsCloseChangeConfirmRequest l_request = new WEB3SuccOptionsCloseChangeConfirmRequest();
            l_request.id = "1001";
            l_request.expirationDateType = "1";
            WEB3SuccPriceAdjustmentValueInfo l_value = new WEB3SuccPriceAdjustmentValueInfo();
            l_value.sign = "+";
            l_value.priceAdjustmentValue = "10";
            l_request.priceAdjustmentValueInfo = null;
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "10";
            l_request.execCondType = "1";
            l_request.orderCondType = "0";
            l_request.estimatedPrice = "1000";
            l_request.checkPrice = "1";
            l_request.checkDate = WEB3DateUtility.getDate("20080423", "yyyyMMdd");
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{l_contractUnit};

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setSessionType("1");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("17");
            l_rsvIfoOrderUnitParams.setClosingOrder("0");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
                    (WEB3DateUtility.getDate("20080324", "yyyyMMdd")).getTime()));

//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080324", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080324", "yyyyMMdd").getTime()), "1");
            
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams0 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams0.setInstitutionCode("0D");
            l_tradingTimeParams0.setBranchCode("123");
            l_tradingTimeParams0.setMarketCode("N1");
            l_tradingTimeParams0.setTradingTimeType("01");
            l_tradingTimeParams0.setProductCode("000005");
            l_tradingTimeParams0.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams0);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);

            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeSettleContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateEveningSessionOrderPossibleChange", new Class[]
                    {String.class, WEB3ToSuccIfoOrderUnitImpl.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3SuccOptionsCloseChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            assertFalse(l_response.succSettingFlag);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

//    /**
//     * 
//     * リクエストデータ.注文単価区分 == "成行"の場合 リクエストデータ.注文期限区分 != "出来るまで注文"の場合
//     * リクエストデータ.注文単価区分 =指値 リクエストデータ.単価調整値情報 != nullの場合
//     */
//    public void testSubmitOrder_C0008()
//    {
//        final String STR_METHOD_NAME = "testSubmitOrder_C0008()";
//        log.entering(STR_METHOD_NAME);
//
//        WEB3ToSuccOptionChangeClosingContractServiceImplForTest2 l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest2();
//
//        try
//        {
//            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
//            l_request.estimatedPrice = "1000";
//            l_request.checkPrice = "2000";
//            l_request.password = "00000";
//            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
//            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
//            l_request.priceAdjustmentValueInfo.sign="+";
//            l_request.orderPriceDiv = "0";
//            l_request.limitPrice = "1000";
//            l_request.checkDate = WEB3DateUtility.getDate("20050112", "yyyyMMdd");
//            l_request.expirationDateType = "1";
//            l_request.id = "1001";
//
//            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl(); 
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
//                    new Class[] {},
//                    l_loginInfoImpl);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountType(MainAccountTypeEnum.JOINT_OWNERSHIP);
//            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
//                    "submitIfoChangeSettleContractOrder",
//                    new Class[]
//                    {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
//                            WEB3ToSuccIfoOrderUnitImpl.class},null);
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            l_rsvIfoOrderUnitParams.setOrderId(1001);
//            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
//            l_rsvIfoOrderUnitParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
//                    (WEB3DateUtility.getDate("20080424", "yyyyMMdd")).getTime()));
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
//            l_orderexecutionEndParams.setInstitutionCode("0D");
//            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
//            l_orderexecutionEndParams.setFutureOptionDiv("2");
//            l_orderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
//
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1001);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            WEB3SuccOptionsCloseChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
//
//            assertEquals("0001", l_response.orderActionId);
//            Date l_datResult1 = WEB3DateUtility.getDate("20050421", "yyyyMMdd");
//            assertEquals(l_datResult1, l_response.lastUpdatedTimestamp);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }
//
//    /**
//     * 
//     * リクエストデータ.注文単価区分 = "成行"の場合 リクエストデータ.注文期限区分 == "出来るまで注文"の場合 リクエストデータ.注文単価区分
//     * =指値 リクエストデータ.単価調整値情報 = nullの場合
//     */
//    public void testSubmitOrder_C0009()
//    {
//        final String STR_METHOD_NAME = "testSubmitOrder_C0009()";
//        log.entering(STR_METHOD_NAME);
//
//        WEB3ToSuccOptionChangeClosingContractServiceImplForTest2 l_impl = new WEB3ToSuccOptionChangeClosingContractServiceImplForTest2();
//
//        try
//        {
//            WEB3SuccOptionsCloseChangeCompleteRequest l_request = new WEB3SuccOptionsCloseChangeCompleteRequest();
//            l_request.estimatedPrice = "1000";
//            l_request.checkPrice = "2000";
//            l_request.password = "00000";
//            l_request.priceAdjustmentValueInfo = null;
//            l_request.orderPriceDiv = "0";
//            l_request.limitPrice = "1000";
//            l_request.expirationDate = WEB3DateUtility.getDate("20050112", "yyyyMMdd");
//            l_request.expirationDateType = "2";
//            l_request.id = "1001";
//
//            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl(); 
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
//                    new Class[] {},
//                    l_loginInfoImpl);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountType(MainAccountTypeEnum.JOINT_OWNERSHIP);
//            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
//                    "submitIfoChangeSettleContractOrder",
//                    new Class[]
//                    {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
//                            WEB3ToSuccIfoOrderUnitImpl.class},null);
//            
//            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            l_rsvIfoOrderUnitParams.setOrderId(1001);
//            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
//            l_rsvIfoOrderUnitParams.setSessionType("1");
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(
//                    (WEB3DateUtility.getDate("20080424", "yyyyMMdd")).getTime()));
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.attributes.basetimestampfororderbizdate", null);
//
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
//            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
//            l_orderexecutionEndParams.setInstitutionCode("0D");
//            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
//            l_orderexecutionEndParams.setFutureOptionDiv("2");
//            l_orderexecutionEndParams.setOrderexecutionEndType("0");
//            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
//
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1001);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            WEB3SuccOptionsCloseChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
//
//            assertEquals("0001", l_response.orderActionId);
//            Date l_datResult1 = WEB3DateUtility.getDate("20050421", "yyyyMMdd");
//            assertEquals(l_datResult1, l_response.lastUpdatedTimestamp);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//    }

    private class WEB3ToSuccOptionChangeClosingContractServiceImplForTest2 extends
            WEB3ToSuccOptionSettleContractOrderServiceImplForTest2
    {
        protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request,
                WEB3ToSuccIfoOrderUnitImpl l_rsvOrderUnit) throws WEB3BaseException
        {

        }

        protected void validateSettledContract(WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
                throws WEB3BaseException
        {
        }

        protected WEB3SuccOptionsCloseChangeCompleteResponse submitOrder(
                WEB3SuccOptionsCloseChangeCompleteRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsCloseChangeCompleteRequest)";
            log.entering(STR_METHOD_NAME);

            // リクエストデータの整合性をチェックする。
            l_request.validate();

            WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel
                    .getOrderManager();

            // get先物OP予約注文単位(long)
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(Long
                    .parseLong(l_request.id));

            // validateリクエストデータat反対取引(WEB3GenRequest, 先物OP予約注文単位Impl)
            this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

            // validate訂正可能状態( )
            l_ifoOrderUnit.validateOrderForChangeability();

            // validate決済済建玉(先物OP予約注文単位Impl)
            this.validateSettledContract(l_ifoOrderUnit);

            // create完了リクエスト(（連続）株価指数オプション訂正返済完了リクエスト, 先物OP予約注文単位Impl)
            WEB3SuccOptionsCloseCompleteRequest l_completeRequest = this.createCompleteRequest(l_request,
                    l_ifoOrderUnit);

            // submit注文(（連続）株価指数オプション返済完了リクエスト)
            WEB3SuccOptionsCloseCompleteResponse l_completeResponse = super.submitOrder(l_completeRequest);

            // validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
            l_orderManager.validateEveningSessionOrderPossibleChange(l_request.expirationDateType, l_ifoOrderUnit);

            // createリクエストアダプタ
            WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_completeRequest);

            // create返済建玉エントリ(先物返済注文リクエストアダプタ, 返済建玉[])
            SettleContractEntry[] l_settleContractEntries = this.createSettleContractEntry(
                    l_requestAdapter,
                    l_request.closeMarginContractUnits);

            // 訂正後指値
            double l_dblModifiedLimitPrice = 0.0D;
            // リクエストデータ.注文単価区分 != "成行"の場合
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_dblModifiedLimitPrice = Double.parseDouble(l_request.limitPrice);
            }

            // 訂正後注文失効日
            Date l_datModifiedExpirationDate = null;
            // リクエストデータ.注文期限区分 == "出来るまで注文"の場合
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request.expirationDateType))
            {
                l_datModifiedExpirationDate = l_request.expirationDate;
            }
            else
            {
                l_datModifiedExpirationDate = l_request.checkDate;
            }

            // 代理入力者
            WEB3GentradeTrader l_trader = (WEB3GentradeTrader) this.getTrader();

            // 訂正後単価調整値
            Double l_modifiedPriceAdjustValue = null;
            // リクエストデータ.単価調整値情報 != nullの場合
            if (l_request.priceAdjustmentValueInfo != null)
            {
                l_modifiedPriceAdjustValue = new Double(l_request.priceAdjustmentValueInfo.getPriceAdjustmentValue());
            }

            // create先物OP予約返済注文訂正内容(long, double, SettleContractEntry[],
            // double, double, Date, 扱者, Double, String)
            WEB3ToSuccIfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec = WEB3ToSuccIfoChangeSettleContractOrderSpec
                    .createIfoChangeSettleContractOrderSpec(l_ifoOrderUnit.getOrderId(), l_dblModifiedLimitPrice,
                            l_settleContractEntries, Double.parseDouble(l_request.estimatedPrice), Double
                                    .parseDouble(l_request.checkPrice), l_datModifiedExpirationDate, l_trader,
                            l_modifiedPriceAdjustValue, l_request.expirationDateType);

            // submit先物OP訂正予約返済注文(SubAccount, 先物OP予約返済注文訂正内容, String,
            // 先物OP予約注文単位Impl)
            l_orderManager.submitIfoChangeSettleContractOrder(this.getSubAccount(), l_changeSettleContractOrderSpec,
                    l_request.password, l_ifoOrderUnit);

            // createResponse( )
            WEB3SuccOptionsCloseChangeCompleteResponse l_response = (WEB3SuccOptionsCloseChangeCompleteResponse) l_request
                    .createResponse();

            // 更新時間： super.submit注文()のレスポンスから同名プロパティをセット
            l_response.lastUpdatedTimestamp = l_completeResponse.lastUpdatedTimestamp;

            // 識別番号： super.submit注文()のレスポンスから同名プロパティをセット
            l_response.orderActionId = l_completeResponse.orderActionId;

            // 連続注文設定フラグ： false（固定）
            l_response.succSettingFlag = false;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        protected SettleContractEntry[] createSettleContractEntry(
                WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
                WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
        {
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[]
            {new SettleContractEntry(0, 0), new SettleContractEntry(0, 0)};
            return l_settleContractEntries;
        }

        protected WEB3SuccOptionsCloseCompleteRequest createCompleteRequest(
                WEB3SuccOptionsCloseChangeCompleteRequest l_request, WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
        {
            final String STR_METHOD_NAME = "createCompleteRequest(WEB3SuccOptionsCloseChangeCompleteRequest, WEB3ToSuccIfoOrderUnitImpl)";
            log.entering(STR_METHOD_NAME);

            // 戻り値のインスタンスを生成する。
            WEB3SuccOptionsCloseCompleteRequest l_completeRequest = new WEB3SuccOptionsCloseCompleteRequest();

            // 共通プロパティセット。
            // this.set株価指数オプション共通入力リクエスト()をコールする。
            this.setOptionsCommonRequest(l_completeRequest, l_request);

            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow) l_toSuccIfoOrderUnit.getDataSourceObject();

            // 生成したインスタンス特有のプロパティをセットする。
            // 注文ID = 予約注文単位.注文ID
            l_completeRequest.orderId = String.valueOf(l_rsvIfoOrderUnitRow.getOrderId());

            // 返済建玉 = パラメータ.リクエストデータ.返済建玉
            l_completeRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;

            // 決済順序 = 予約注文単位.決済順序
            l_completeRequest.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

            // 暗証番号 = パラメータ.リクエストデータ.暗証番号
            l_completeRequest.password = l_request.password;

            // 確認時単価 = パラメータ.リクエストデータ.確認時単価
            l_completeRequest.checkPrice = l_request.checkPrice;

            // 確認時発注日 = パラメータ.リクエストデータ.確認時発注日
            l_completeRequest.checkDate = l_request.checkDate;

            // 連続注文共通情報 = 予約注文単位.create連続注文共通情報()
            l_completeRequest.succCommonInfo = l_toSuccIfoOrderUnit.createSuccCommonInfo();

            // 単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報
            l_completeRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

            // 調整後単価 = パラメータ.リクエストデータ.調整後単価
            l_completeRequest.afterAdjustmentPrice = l_request.afterAdjustmentPrice;

            log.exiting(STR_METHOD_NAME);
            return l_completeRequest;
        }

        protected WEB3OptionsCommonRequest setOptionsCommonRequest(WEB3OptionsCommonRequest l_outputCommonRequest,
                WEB3OptionsCommonRequest l_inputCommonRequest)
        {
            final String STR_METHOD_NAME = "setOptionsCommonRequest(WEB3OptionsCommonRequest, WEB3OptionsCommonRequest)";
            log.entering(STR_METHOD_NAME);

            // 注文数量
            l_outputCommonRequest.opOrderQuantity = l_inputCommonRequest.opOrderQuantity;

            // 注文単価区分
            l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;

            // 注文単価
            l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;

            // 執行条件
            l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;

            // 注文期限区分
            l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

            // 注文有効期限
            l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;

            // 発注条件区分
            l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;

            // 逆指値用プレミアム／原資産価格
            l_outputCommonRequest.stopPremium_underlyingAssets = l_inputCommonRequest.stopPremium_underlyingAssets;

            // 逆指値用発注条件単価
            l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

            // 逆指値用発注条件演算子
            l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

            // W指値用プレミアム／原資産価格
            l_outputCommonRequest.wlimitPremium_underlyingAssets = l_inputCommonRequest.wlimitPremium_underlyingAssets;

            // W指値用発注条件単価
            l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;

            // W指値用発注条件演算子
            l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;

            // W指値用注文単価区分
            l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;

            // W指値用注文単価
            l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;

            // W指値用執行条件
            l_outputCommonRequest.wlimitExecCondType = l_inputCommonRequest.wlimitExecCondType;

            // W指値用有効状態区分
            l_outputCommonRequest.wlimitEnableStatusDiv = l_inputCommonRequest.wlimitEnableStatusDiv;

            log.exiting(STR_METHOD_NAME);
            return l_outputCommonRequest;
        }
    }

    private class WEB3ToSuccOptionChangeClosingContractServiceImplForTest extends
            WEB3ToSuccOptionChangeClosingContractServiceImpl
    {
        protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request,
                WEB3ToSuccIfoOrderUnitImpl l_rsvOrderUnit) throws WEB3BaseException
        {

        }
        
        protected SettleContractEntry[] createSettleContractEntry(
                WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
                WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
       {
            SettleContractEntry l_entry = new SettleContractEntry(12,123);
            return new SettleContractEntry[]{l_entry};
       }
    }

    private class WEB3ToSuccOptionChangeClosingContractServiceImplForTest1 extends
            WEB3ToSuccOptionSettleContractOrderServiceImplForTest2
    {
        protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request,
                WEB3ToSuccIfoOrderUnitImpl l_rsvOrderUnit) throws WEB3BaseException
        {

        }

        protected void validateSettledContract(WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
                throws WEB3BaseException
        {
        }

        protected WEB3SuccOptionsCloseChangeConfirmResponse validateOrder(
                WEB3SuccOptionsCloseChangeConfirmRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsCloseChangeConfirmRequest)";
            log.entering(STR_METHOD_NAME);

            // リクエストデータの整合性をチェックする。
            l_request.validate();

            WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel
                    .getOrderManager();

            // get先物OP予約注文単位(long)
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit = l_orderManager.getReserveIfoOrderUnit(Long
                    .parseLong(l_request.id));

            // validateリクエストデータat反対取引(WEB3GenRequest, 先物OP予約注文単位Impl)
            this.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);

            // validate訂正可能状態( )
            l_ifoOrderUnit.validateOrderForChangeability();

            // validate決済済建玉(先物OP予約注文単位Impl)
            this.validateSettledContract(l_ifoOrderUnit);

            // create確認リクエスト(（連続）株価指数オプション訂正返済確認リクエスト, 先物OP予約注文単位Impl)
            WEB3SuccOptionsCloseConfirmRequest l_confirmRequest = this.createConfirmRequest(l_request, l_ifoOrderUnit);

            // validate注文(（連続）株価指数オプション返済注文確認リクエスト)
            WEB3SuccOptionsCloseConfirmResponse l_confirmResponse = super.validateOrder(l_confirmRequest);

            // validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
            l_orderManager.validateEveningSessionOrderPossibleChange(l_request.expirationDateType, l_ifoOrderUnit);

            // createResponse( )
            WEB3SuccOptionsCloseChangeConfirmResponse l_response = (WEB3SuccOptionsCloseChangeConfirmResponse) l_request
                    .createResponse();

            // 建玉明細： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.contractUnits = l_confirmResponse.contractUnits;

            // 概算受渡代金： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.estimatedPrice = l_confirmResponse.estimatedPrice;

            // 手数料コース： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.commissionCourse = l_confirmResponse.commissionCourse;

            // 手数料： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.commission = l_confirmResponse.commission;

            // 手数料消費税： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.commissionConsumptionTax = l_confirmResponse.commissionConsumptionTax;

            // 取引終了警告文言： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.messageSuspension = l_confirmResponse.messageSuspension;

            // 確認時単価： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.checkPrice = l_confirmResponse.checkPrice;

            // 確認時発注日： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.checkDate = l_confirmResponse.checkDate;

            // 注文有効期限： super.validate注文()のレスポンスから同名プロパティをセット
            l_response.expirationDate = l_confirmResponse.expirationDate;

            // 調整後単価 ：
            // リクエスト.単価調整値情報≠null（±指値指定）の場合
            if (l_request.priceAdjustmentValueInfo != null)
            {
                // super.validate注文()のレスポンスから同名プロパティをセット
                l_response.afterAdjustmentPrice = l_confirmResponse.afterAdjustmentPrice;
            }

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        protected WEB3SuccOptionsCloseConfirmRequest createConfirmRequest(
                WEB3SuccOptionsCloseChangeConfirmRequest l_request, WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnit)
        {
            final String STR_METHOD_NAME = "createConfirmRequest(WEB3SuccOptionsCloseChangeConfirmRequest, WEB3ToSuccIfoOrderUnitImpl)";
            log.entering(STR_METHOD_NAME);

            // 戻り値のインスタンスを生成する。
            WEB3SuccOptionsCloseConfirmRequest l_confirmRequest = new WEB3SuccOptionsCloseConfirmRequest();

            // 共通プロパティセット。
            // this.set株価指数先物共通入力リクエスト()をコールする。
            this.setOptionsCommonRequest(l_confirmRequest, l_request);

            // 生成したインスタンス特有のプロパティをセットする。
            // 返済建玉 = パラメータ.リクエストデータ.返済建玉
            l_confirmRequest.closeMarginContractUnits = l_request.closeMarginContractUnits;

            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow) l_toSuccIfoOrderUnit.getDataSourceObject();

            // 決済順序 = 予約注文単位.決済順序
            l_confirmRequest.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

            // 連続注文共通情報 = 予約注文単位.create連続注文共通情報()
            l_confirmRequest.succCommonInfo = l_toSuccIfoOrderUnit.createSuccCommonInfo();

            // 単価調整値情報 = パラメータ.リクエストデータ.単価調整値情報
            l_confirmRequest.priceAdjustmentValueInfo = l_request.priceAdjustmentValueInfo;

            log.exiting(STR_METHOD_NAME);
            return l_confirmRequest;
        }

        protected WEB3OptionsCommonRequest setOptionsCommonRequest(WEB3OptionsCommonRequest l_outputCommonRequest,
                WEB3OptionsCommonRequest l_inputCommonRequest)
        {
            final String STR_METHOD_NAME = "setOptionsCommonRequest(WEB3OptionsCommonRequest, WEB3OptionsCommonRequest)";
            log.entering(STR_METHOD_NAME);

            // 注文数量
            l_outputCommonRequest.opOrderQuantity = l_inputCommonRequest.opOrderQuantity;

            // 注文単価区分
            l_outputCommonRequest.orderPriceDiv = l_inputCommonRequest.orderPriceDiv;

            // 注文単価
            l_outputCommonRequest.limitPrice = l_inputCommonRequest.limitPrice;

            // 執行条件
            l_outputCommonRequest.execCondType = l_inputCommonRequest.execCondType;

            // 注文期限区分
            l_outputCommonRequest.expirationDateType = l_inputCommonRequest.expirationDateType;

            // 注文有効期限
            l_outputCommonRequest.expirationDate = l_inputCommonRequest.expirationDate;

            // 発注条件区分
            l_outputCommonRequest.orderCondType = l_inputCommonRequest.orderCondType;

            // 逆指値用プレミアム／原資産価格
            l_outputCommonRequest.stopPremium_underlyingAssets = l_inputCommonRequest.stopPremium_underlyingAssets;

            // 逆指値用発注条件単価
            l_outputCommonRequest.stopOrderCondPrice = l_inputCommonRequest.stopOrderCondPrice;

            // 逆指値用発注条件演算子
            l_outputCommonRequest.stopOrderCondOperator = l_inputCommonRequest.stopOrderCondOperator;

            // W指値用プレミアム／原資産価格
            l_outputCommonRequest.wlimitPremium_underlyingAssets = l_inputCommonRequest.wlimitPremium_underlyingAssets;

            // W指値用発注条件単価
            l_outputCommonRequest.wlimitOrderCondPrice = l_inputCommonRequest.wlimitOrderCondPrice;

            // W指値用発注条件演算子
            l_outputCommonRequest.wlimitOrderCondOperator = l_inputCommonRequest.wlimitOrderCondOperator;

            // W指値用注文単価区分
            l_outputCommonRequest.wLimitOrderPriceDiv = l_inputCommonRequest.wLimitOrderPriceDiv;

            // W指値用注文単価
            l_outputCommonRequest.wLimitPrice = l_inputCommonRequest.wLimitPrice;

            // W指値用執行条件
            l_outputCommonRequest.wlimitExecCondType = l_inputCommonRequest.wlimitExecCondType;

            // W指値用有効状態区分
            l_outputCommonRequest.wlimitEnableStatusDiv = l_inputCommonRequest.wlimitEnableStatusDiv;

            log.exiting(STR_METHOD_NAME);
            return l_outputCommonRequest;
        }

    }

    private class WEB3ToSuccOptionSettleContractOrderServiceImplForTest2 extends
            WEB3ToSuccOptionSettleContractOrderServiceImpl implements WEB3ToSuccOptionSettleContractOrderService
    {
        protected WEB3SuccOptionsCloseConfirmResponse validateOrder(WEB3SuccOptionsCloseConfirmRequest l_request)
                throws WEB3BaseException
        {
            WEB3SuccOptionsCloseConfirmResponse l_response = new WEB3SuccOptionsCloseConfirmResponse();

            WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnits = new WEB3FuturesOptionsContractUnit[1];
            WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit = new WEB3FuturesOptionsContractUnit();
            l_futuresOptionsContractUnit.contractCommission = "0";
            l_futuresOptionsContractUnits[0] = l_futuresOptionsContractUnit;

            l_response.contractUnits = l_futuresOptionsContractUnits;
            l_response.estimatedPrice = "1";
            l_response.commissionCourse = "2";
            l_response.commission = "3";
            l_response.commissionConsumptionTax = "4";
            String[] l_strObj = new String[1];
            l_strObj[0] = "5";

            l_response.messageSuspension = l_strObj;
            l_response.checkPrice = "6";
            Date l_datExcept1 = WEB3DateUtility.getDate("20050421", "yyyyMMdd");
            l_response.checkDate = l_datExcept1;
            Date l_datExcept2 = WEB3DateUtility.getDate("20050321", "yyyyMMdd");
            l_response.expirationDate = l_datExcept2;
            l_response.afterAdjustmentPrice = "7";
            return l_response;
        }

        protected WEB3SuccOptionsCloseCompleteResponse submitOrder(WEB3SuccOptionsCloseCompleteRequest l_request)
                throws WEB3BaseException
        {
            WEB3SuccOptionsCloseCompleteResponse l_response = new WEB3SuccOptionsCloseCompleteResponse();
            Date l_datExcept1 = WEB3DateUtility.getDate("20050421", "yyyyMMdd");
            l_response.lastUpdatedTimestamp = l_datExcept1;
            l_response.orderActionId = "0001";
            return l_response;
        }
    }
}
@
