head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesChangeClosingContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccFuturesChangeClosingContractServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/03 于瀟（中訊）新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
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
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCommonRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeSettleContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesChangeClosingContractServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ToSuccFuturesChangeClosingContractServiceImplTest.class);
    
    public WEB3ToSuccFuturesChangeClosingContractServiceImplTest(String arg0)
    {
        super(arg0);
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
        
        WEB3ToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3ToSuccFuturesChangeClosingContractServiceImpl();
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
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccFuturesCloseChangeConfirmRequest的場合
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SuccFuturesCloseChangeConfirmRequest();
        
        try
        {
            WEB3SuccFuturesCloseChangeConfirmResponse l_response =
                (WEB3SuccFuturesCloseChangeConfirmResponse)l_impl.execute(l_request);
            assertEquals("123",l_response.checkPrice);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccFuturesCloseChangeConfirmRequest的場合
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SuccFuturesCloseChangeCompleteRequest l_request =
            new WEB3SuccFuturesCloseChangeCompleteRequest();
        
        try
        {
            WEB3SuccFuturesCloseChangeCompleteResponse l_response =
                (WEB3SuccFuturesCloseChangeCompleteResponse)l_impl.execute(l_request);
            assertEquals("456",l_response.orderActionId);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80018
     * 
     * l_request instanceof WEB3SuccFuturesOpenChangeConfirmRequest的場合
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SuccFuturesOpenChangeConfirmRequest l_request =
            new WEB3SuccFuturesOpenChangeConfirmRequest();
        
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
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    class WEB3SubToSuccFuturesChangeClosingContractServiceImpl extends WEB3ToSuccFuturesChangeClosingContractServiceImpl
    {
        protected WEB3SuccFuturesCloseChangeConfirmResponse validateOrder(
            WEB3SuccFuturesCloseChangeConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3SuccFuturesCloseChangeConfirmResponse l_response =
                new WEB3SuccFuturesCloseChangeConfirmResponse();
            l_response.checkPrice = "123";
            return l_response;
        }
        
        protected WEB3SuccFuturesCloseChangeCompleteResponse submitOrder(
            WEB3SuccFuturesCloseChangeCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3SuccFuturesCloseChangeCompleteResponse l_response =
                new WEB3SuccFuturesCloseChangeCompleteResponse();
            l_response.orderActionId = "456";
            return l_response;
        }
        protected WEB3FuturesCommonRequest setFuturesCommonRequest(
                WEB3FuturesCommonRequest l_outputCommonRequest,
                WEB3FuturesCommonRequest l_inputCommonRequest)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(null);
        
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
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SuccFuturesOpenChangeConfirmRequest l_request =
            new WEB3SuccFuturesOpenChangeConfirmRequest();
        
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
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccFuturesCloseChangeConfirmRequest
     * l_orderUnit.isReversingTrade() == true的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0003()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SubSuccFuturesCloseChangeConfirmRequest();
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(true));
            
            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccFuturesCloseChangeConfirmRequest
     * l_orderUnit.isReversingTrade() == false的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0004()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SubSuccFuturesCloseChangeConfirmRequest();
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(false));
            
            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccFuturesCloseChangeCompleteRequest
     * l_orderUnit.isReversingTrade() == false的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0005()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeCompleteRequest l_request =
            new WEB3SubSuccFuturesCloseChangeCompleteRequest();
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(false));
            
            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanceof WEB3SuccFuturesCloseChangeCompleteRequest
     * l_orderUnit.isReversingTrade() == true的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0006()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeCompleteRequest l_request =
            new WEB3SubSuccFuturesCloseChangeCompleteRequest();
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(true));
            
            l_impl.validateRequestDataAtReversingTrade(l_request, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80018
     * 
     * l_request instanceof WEB3SuccFuturesCancelConfirmRequest的場合
     */
    public void testValidateRequestDataAtReversingTrade_C0007()
    {
        final String STR_METHOD_NAME = "testValidateRequestDataAtReversingTrade_C0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SuccFuturesCancelConfirmRequest l_request =
            new WEB3SuccFuturesCancelConfirmRequest();

        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
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
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SubSuccFuturesCloseChangeConfirmRequest();
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
            new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(true));
            
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
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SubSuccFuturesCloseChangeConfirmRequest();
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
            new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "asd";
        l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(true));
            
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
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SubSuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SubSuccFuturesCloseChangeConfirmRequest();
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[1];
        WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
            new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "123456789";
        l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                {String.class, OrderUnit.class},
                new Boolean(true));
            
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
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    class WEB3SubSuccFuturesCloseChangeConfirmRequest extends WEB3SuccFuturesCloseChangeConfirmRequest
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public void validateAtReverseOrder() throws WEB3BaseException 
        {
            
        }
        public void validateATExistingRemainderTrading() throws WEB3BaseException
        {
            
        }
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    class WEB3SubSuccFuturesCloseChangeCompleteRequest extends WEB3SuccFuturesCloseChangeCompleteRequest
    {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public void validateATExistingRemainderTrading() throws WEB3BaseException
        {
            
        }
        public void validateAtReverseOrder() throws WEB3BaseException 
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        //param 1
        WEB3SuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SuccFuturesCloseChangeConfirmRequest();
        
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[1];
        l_futuresOptionsCloseMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnits[0].contractOrderQuantity = "123";
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
        
        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo =
            new WEB3SuccPriceAdjustmentValueInfo();
        l_succPriceAdjustmentValueInfo.priceAdjustmentValue ="456";
        l_request.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;
        
        //param 2
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setClosingOrder("1");
        l_rsvIfoOrderUnitParams.setParentOrderId(2);
        l_rsvIfoOrderUnitParams.setReserveOrderTradingType("3");
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            WEB3SuccFuturesCloseConfirmRequest l_succFuturesCloseConfirmRequest =
                l_impl.createConfirmRequest(l_request, l_toSuccIfoOrderUnitImpl);
            
            assertEquals("123", l_succFuturesCloseConfirmRequest.closeMarginContractUnits[0].contractOrderQuantity);
            assertEquals("456", l_succFuturesCloseConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            
            assertEquals("1", l_succFuturesCloseConfirmRequest.closingOrder);
            assertEquals("2", l_succFuturesCloseConfirmRequest.succCommonInfo.parentOrderId);
            assertEquals("3", l_succFuturesCloseConfirmRequest.succCommonInfo.succTradingType);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        //param 1
        WEB3SuccFuturesCloseChangeCompleteRequest l_request =
            new WEB3SuccFuturesCloseChangeCompleteRequest();
        
        WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[1];
        l_futuresOptionsCloseMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_futuresOptionsCloseMarginContractUnits[0].contractOrderQuantity = "2";
        l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
        l_request.password = "4";
        l_request.checkPrice = "5";
        Date l_datExcept = WEB3DateUtility.getDate("20040808", "yyyyMMdd");
        l_request.checkDate = l_datExcept;
        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo =
            new WEB3SuccPriceAdjustmentValueInfo();
        l_succPriceAdjustmentValueInfo.priceAdjustmentValue = "7";
        l_request.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;
        l_request.afterAdjustmentPrice = "8";

        //param 2
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setOrderId(1);
        l_rsvIfoOrderUnitParams.setClosingOrder("3");
        l_rsvIfoOrderUnitParams.setParentOrderId(6);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            WEB3SuccFuturesCloseCompleteRequest l_succFuturesCloseCompleteRequest =
                l_impl.createCompleteRequest(l_request, l_toSuccIfoOrderUnitImpl);
            assertEquals("2", l_succFuturesCloseCompleteRequest.closeMarginContractUnits[0].contractOrderQuantity);
            assertEquals("3", l_succFuturesCloseCompleteRequest.closingOrder);
            assertEquals("4", l_succFuturesCloseCompleteRequest.password);
            assertEquals("5", l_succFuturesCloseCompleteRequest.checkPrice);
            Date l_datTrue = WEB3DateUtility.getDate("20040808", "yyyyMMdd");
            assertEquals(l_datTrue, l_succFuturesCloseCompleteRequest.checkDate);
            assertEquals("6", l_succFuturesCloseCompleteRequest.succCommonInfo.parentOrderId);
            assertEquals("7", l_succFuturesCloseCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            assertEquals("8", l_succFuturesCloseCompleteRequest.afterAdjustmentPrice);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        try
        {
            //param 1
            WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
                new WEB3ToSuccFuturesSettleContractOrderRequestAdapter();

            //param 2
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
            
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[1];
            SettleContractEntry l_settleContractEntry = new SettleContractEntry(1, 2);
            l_settleContractEntries[0] = l_settleContractEntry;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "createSettleContractEntry", new Class[]
                { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_settleContractEntries);
            
            SettleContractEntry[] l_settleContractEntryResults =
                l_impl.createSettleContractEntry(l_adapter, l_futuresOptionsCloseMarginContractUnits);
            assertEquals(1, l_settleContractEntryResults[0].getContractId());
            assertEquals(2, l_settleContractEntryResults[0].getQuantity(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    //STOP
    /**
     * 正常終了
     * 
     * l_adapter.isReversingOrder() == true的場合
     */
    public void testCreateSettleContractEntry_C0002()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntry_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        try
        {
            //param 1
            WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
                new WEB3ToSuccFuturesSettleContractOrderRequestAdapter();
            WEB3SuccFuturesCloseConfirmRequest l_request =
                new WEB3SuccFuturesCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType =
                WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER;
            l_adapter.request = l_request;
            
            //param 2
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
            
            l_request.closingOrder = "1";
            l_request.futOrderQuantity = "1";
            
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setQuantity(2);
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            l_adapter.parentOrderUnit = l_toSuccIfoOrderUnitImpl;
            //getOrderQuantity
            
            //result
            SettleContractEntry[] l_settleContractEntries = new SettleContractEntry[1];
            SettleContractEntry l_settleContractEntry = new SettleContractEntry(1, 2);
            l_settleContractEntries[0] = l_settleContractEntry;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "createSettleContractEntry", new Class[]
                { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                l_settleContractEntries);
            
            //execute
            SettleContractEntry[] l_settleContractEntryResults =
                l_impl.createSettleContractEntry(l_adapter, l_futuresOptionsCloseMarginContractUnits);
            assertEquals(1, l_settleContractEntryResults[0].getQuantity(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        try
        {
            //param 1
            WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
                new WEB3ToSuccFuturesSettleContractOrderRequestAdapter();
            
            WEB3SuccFuturesCloseConfirmRequest l_request =
                new WEB3SuccFuturesCloseConfirmRequest();
            l_request.closingOrder = "1";
            l_request.futOrderQuantity = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            l_adapter.request = l_request;
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setQuantity(2);
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            l_adapter.parentOrderUnit = l_toSuccIfoOrderUnitImpl;

            //execute
            Method l_method =
                WEB3ToSuccFuturesChangeClosingContractServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", 
                    new Class[]{WEB3ToSuccFuturesSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            Double l_dblOrderQuantity = (Double)l_method.invoke(l_impl, new Object[]{l_adapter});

            assertEquals(1, l_dblOrderQuantity.doubleValue(), 0);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
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
        
        WEB3SubToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3SubToSuccFuturesChangeClosingContractServiceImpl();
        
        try
        {
            //param 1
            WEB3ToSuccFuturesSettleContractOrderRequestAdapter l_adapter =
                new WEB3ToSuccFuturesSettleContractOrderRequestAdapter();
            
            WEB3SuccFuturesCloseCompleteRequest l_request =
                new WEB3SuccFuturesCloseCompleteRequest();
            l_request.closingOrder = "0";
            l_request.futOrderQuantity = "1";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_futuresOptionsCloseMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_futuresOptionsCloseMarginContractUnit =
                new WEB3FuturesOptionsCloseMarginContractUnit();
            l_futuresOptionsCloseMarginContractUnit.contractOrderQuantity = "2";
            l_futuresOptionsCloseMarginContractUnits[0] = l_futuresOptionsCloseMarginContractUnit;
            l_request.closeMarginContractUnits = l_futuresOptionsCloseMarginContractUnits;
            l_adapter.request = l_request;
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setQuantity(1);
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            l_adapter.parentOrderUnit = l_toSuccIfoOrderUnitImpl;

            //execute
            Method l_method =
                WEB3ToSuccFuturesChangeClosingContractServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", 
                    new Class[]{WEB3ToSuccFuturesSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            l_method.invoke(l_impl, new Object[]{l_adapter});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            WEB3BusinessLayerException l_exception =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03065, l_exception.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testSetFuturesCommonRequest_C0001()
    {
        final String STR_METHOD_NAME = "testSetFuturesCommonRequest_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3ToSuccFuturesChangeClosingContractServiceImpl();
        
        try
        {
            WEB3FuturesCommonRequest l_inputCommonRequest = new WEB3FuturesCommonRequest();
            l_inputCommonRequest.futOrderQuantity = "1";
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
            l_inputCommonRequest.wlimitExecCondType= "13";
            l_inputCommonRequest.wlimitEnableStatusDiv ="14";
            WEB3FuturesCommonRequest l_outputCommonRequest = new WEB3FuturesCommonRequest();
            WEB3FuturesCommonRequest l_result = l_impl.setFuturesCommonRequest(l_outputCommonRequest, l_inputCommonRequest);
            assertEquals("1",l_result.futOrderQuantity);
            assertEquals("2",l_result.orderPriceDiv);
            assertEquals("3",l_result.limitPrice);
            assertEquals("4",l_result.execCondType);
            assertEquals("5",l_result.expirationDateType);
            Date l_datResult = WEB3DateUtility.getDate("20020202", "yyyyMMdd");
            assertEquals(l_datResult,l_result.expirationDate);
            assertEquals("6",l_result.orderCondType);
            assertEquals("7",l_result.stopOrderCondPrice);
            assertEquals("8",l_result.stopOrderCondOperator);
            assertEquals("9",l_result.wlimitOrderCondPrice);
            assertEquals("10",l_result.wlimitOrderCondOperator);
            assertEquals("11",l_result.wLimitOrderPriceDiv);
            assertEquals("12",l_result.wLimitPrice);
            assertEquals("13",l_result.wlimitExecCondType);
            assertEquals("14",l_result.wlimitEnableStatusDiv);
        }
        catch(Exception l_ex)
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
        
        WEB3ToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3ToSuccFuturesChangeClosingContractServiceImpl();
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
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
     *正常終了
     */
    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl();
        
        WEB3SuccFuturesCloseChangeConfirmRequest l_request =
            new WEB3SuccFuturesCloseChangeConfirmRequest();
        l_request.id = "1001";
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.sign = "+";
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
        l_request.orderPriceDiv = "0";
        l_request.execCondType = "1";
        l_request.expirationDateType = "1";
        l_request.orderCondType = "0";
        WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
        l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[]{l_unit};
        
        l_request.futOrderQuantity = "2";
//        l_request.succCommonInfo
//        l_request.closingOrder = "1";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                  
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
//            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_rsvIfoOrderUnitParams.setClosingOrder("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(1006169090018L);
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            Date l_datExcept = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_datExcept.getTime()), "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExcept);
            
            
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
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
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                      WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                      boolean.class, WEB3IfoContractImpl.class },
                      l_result);
            
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
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.commit();
            WEB3SuccFuturesCloseChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            
            assertNull(l_response.contractUnits[0].contractCommission);

            assertEquals("0", l_response.estimatedSettleIncome);

            assertNull(l_response.commissionCourse);

            assertEquals("0", l_response.commission);

            assertEquals("0", l_response.commissionConsumptionTax);

//            assertEquals("5", l_response.messageSuspension[0]);
//
//            assertEquals("6", l_response.checkPrice);
//
//            Date l_datResult1 = WEB3DateUtility.getDate("2002002", "yyyyMMdd");
//            assertEquals(l_datResult1, l_response.checkDate);
//
//            Date l_datResult2 = WEB3DateUtility.getDate("20030303", "yyyyMMdd");
//            assertEquals(l_datResult2, l_response.expirationDate);
//
//            assertEquals("8", l_response.afterAdjustmentPrice);
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
    class WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl extends WEB3ToSuccFuturesChangeClosingContractServiceImpl
    {
        protected void validateRequestDataAtReversingTrade(WEB3GenRequest l_request, WEB3ToSuccIfoOrderUnitImpl l_orderUnit)
            throws WEB3BaseException
        {
            
        }
        protected void validateSettledContract(WEB3ToSuccIfoOrderUnitImpl l_orderUnit) throws WEB3BaseException
        {
            
        }
        protected WEB3SuccFuturesCloseConfirmResponse validateOrder(WEB3SuccFuturesCloseConfirmRequest l_request)
            throws WEB3BaseException
        {
            WEB3SuccFuturesCloseConfirmResponse l_response =
                new WEB3SuccFuturesCloseConfirmResponse();
            
            WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnits =
                new WEB3FuturesOptionsContractUnit[1];
            WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit =
                new WEB3FuturesOptionsContractUnit();
            l_futuresOptionsContractUnit.contractCommission = "0";
            l_futuresOptionsContractUnits[0] = l_futuresOptionsContractUnit;
            
            l_response.contractUnits = l_futuresOptionsContractUnits;

            l_response.estimatedSettleIncome = "1";

            l_response.commissionCourse = "2";

            l_response.commission = "3";

            l_response.commissionConsumptionTax = "4";

            String[] l_strObj = new String[1];
            l_strObj[0] = "5";

            l_response.messageSuspension = l_strObj;

            l_response.checkPrice = "6";

            Date l_datExcept1 = WEB3DateUtility.getDate("2002002", "yyyyMMdd");
            l_response.checkDate = l_datExcept1;

            Date l_datExcept2 = WEB3DateUtility.getDate("20030303", "yyyyMMdd");
            l_response.expirationDate = l_datExcept2;

            l_response.afterAdjustmentPrice = "8";
            return l_response;
        }
        protected WEB3SuccFuturesCloseCompleteResponse submitOrder(WEB3SuccFuturesCloseCompleteRequest l_request)
            throws WEB3BaseException
        {
            WEB3SuccFuturesCloseCompleteResponse l_response =
                new WEB3SuccFuturesCloseCompleteResponse();
            Date l_datResult = WEB3DateUtility.getDate("20020202", "yyyyMMdd");
            l_response.lastUpdatedTimestamp = l_datResult;
            l_response.orderActionId = "1";
            return l_response;
        }
        protected SettleContractEntry[] createSettleContractEntry(
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits)
            throws WEB3BaseException
        {
            SettleContractEntry l_entry = new SettleContractEntry(23,20);
            return new SettleContractEntry[]{l_entry};
        }
        protected NewOrderValidationResult validateFuturesSettleContractOrder(
                SubAccount l_subAccount,
                WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
                WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
                throws WEB3BaseException
        {
            return new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance());
        }
    }
    
    /**
     *正常終了
     *
     *l_request.expirationDateType != WEB3OrderExpirationDateTypeDef.CARRIED_ORDER的場合
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl();
        WEB3SuccFuturesCloseChangeCompleteRequest l_request = new WEB3SuccFuturesCloseChangeCompleteRequest();
//        l_request.succCommonInfo = new WEB3SuccCommonInfo();
//        l_request.succCommonInfo.parentOrderId = "1001";
//        l_request.succCommonInfo.succTradingType = "14";
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.sign = "+";
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
        l_request.estimatedSettleIncome = "1";
        l_request.orderPriceDiv = "0";
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
        l_request.wlimitEnableStatusDiv = null;
        l_request.closeMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit()};
        l_request.closeMarginContractUnits[0].id = "1001";
//        l_request.closingOrder ="1";
        l_request.futOrderQuantity = "12";
        l_request.id = "1001";
        l_request.checkPrice = "20";
        l_request.checkDate = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
        l_request.orderPriceDiv = "0";
        l_request.limitPrice = null;
        l_request.afterAdjustmentPrice = "15";
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_rsvIfoOrderUnitParams.setClosingOrder("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            Date l_datExcept = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_datExcept.getTime()), "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExcept);
            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
//            l_request.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
//            l_request.limitPrice = "1";
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl(); 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImpl);
            
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
            l_request.priceAdjustmentValueInfo.sign = "+";
//            l_request.estimatedSettleIncome = "1";
            l_request.checkPrice = "1";
            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountType(MainAccountTypeEnum.JOINT_OWNERSHIP);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.FUTURES_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoChangeSettleContractOrder",
                new Class[]
                {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                        WEB3ToSuccIfoOrderUnitImpl.class},null);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
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
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class},
                    null);
            
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
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
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
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                      WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                      boolean.class, WEB3IfoContractImpl.class },
                      l_result);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3SuccFuturesCloseChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
//            Date l_datResult = WEB3DateUtility.getDate("20020202", "yyyyMMdd");
//            assertEquals(l_datResult, l_response.lastUpdatedTimestamp);
            assertEquals("1001", l_response.orderActionId);
            assertFalse(l_response.succSettingFlag);
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
     *正常終了
     *
     *l_request.expirationDateType == WEB3OrderExpirationDateTypeDef.CARRIED_ORDER的場合
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl l_impl =
            new WEB3Sub1ToSuccFuturesChangeClosingContractServiceImpl();
        WEB3SuccFuturesCloseChangeCompleteRequest l_request = new WEB3SuccFuturesCloseChangeCompleteRequest();
//        l_request.succCommonInfo = new WEB3SuccCommonInfo();
//        l_request.succCommonInfo.parentOrderId = "1001";
//        l_request.succCommonInfo.succTradingType = "14";
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.sign = "+";
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
        l_request.estimatedSettleIncome = "1";
        l_request.orderPriceDiv = "0";
        l_request.execCondType = "1";
        l_request.expirationDateType = "2";
        l_request.expirationDate = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
        l_request.orderCondType = "0";
        l_request.stopOrderCondPrice = null;
        l_request.stopOrderCondOperator = null;
        l_request.wlimitOrderCondPrice = null;
        l_request.wlimitOrderCondOperator = null;
        l_request.wLimitOrderPriceDiv = null;
        l_request.wLimitPrice = null;
        l_request.wlimitExecCondType = null;
        l_request.wlimitEnableStatusDiv = null;
        l_request.closeMarginContractUnits =
            new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit()};
        l_request.closeMarginContractUnits[0].id = "1001";
//        l_request.closingOrder ="1";
        l_request.futOrderQuantity = "12";
        l_request.id = "1001";
        l_request.checkPrice = "20";
        l_request.checkDate = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
        l_request.orderPriceDiv = "0";
        l_request.limitPrice = null;
        l_request.afterAdjustmentPrice = "15";
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_rsvIfoOrderUnitParams.setClosingOrder("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            Date l_datExcept = WEB3DateUtility.getDate("20060606", "yyyyMMdd");
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_datExcept.getTime()), "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExcept);
            
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
//            l_request.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
//            l_request.limitPrice = "1";
            LoginInfoImpl l_loginInfoImpl = new LoginInfoImpl(); 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImpl);
            
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
            l_request.priceAdjustmentValueInfo.sign = "+";
//            l_request.estimatedSettleIncome = "1";
            l_request.checkPrice = "1";
            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountType(MainAccountTypeEnum.JOINT_OWNERSHIP);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.FUTURES_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "submitIfoChangeSettleContractOrder",
                new Class[]
                {SubAccount.class, WEB3ToSuccIfoChangeSettleContractOrderSpec.class, String.class,
                        WEB3ToSuccIfoOrderUnitImpl.class},null);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
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
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class},
                    null);
            
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
                    "getExpirationDate",
                    new Class[] {Date.class, String.class, String.class, String.class},
                    l_datExcept);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
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
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateSettlementIncome",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                      WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                      boolean.class, WEB3IfoContractImpl.class },
                      l_result);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3SuccFuturesCloseChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
//            Date l_datResult = WEB3DateUtility.getDate("20020202", "yyyyMMdd");
//            assertEquals(l_datResult, l_response.lastUpdatedTimestamp);
            assertEquals("1001", l_response.orderActionId);
            assertFalse(l_response.succSettingFlag);
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
}
@
