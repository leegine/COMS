head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeCompleteUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.market.messages.AioTransferDoneMarketResponseMessage;

import test.util.TestDBUtility;
import webbroker3.aio.WEB3AioCashTransUpdateInterceptor;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeCompleteUnitServiceImplTest extends
        TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccTransChangeCompleteUnitServiceImplTest.class);

    private WEB3AccTransChangeCompleteUnitServiceImpl l_serviceImpl = null;

    public WEB3AccTransChangeCompleteUnitServiceImplTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3AccTransChangeCompleteUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * à¯êî.íçï∂íPà  = null
     */
    public void testCompleteChange_C0001()
    {
        final String STR_METHOD_NAME = "testCompleteChange_C0001()";
        log.entering(STR_METHOD_NAME);

        AioOrderUnit l_orderUnit = null;

        try
        {
            l_serviceImpl.completeChange(l_orderUnit);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * à¯êî.íçï∂íPà  != null
     * ê≥èÌ
     */
    public void testCompleteChange_C0002()
    {
        final String STR_METHOD_NAME = "testCompleteChange_C0001()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_orderManager = 
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketResponseReceiverCallbackServiceImpl",
                "process",
                new Class[] {AioTransferDoneMarketResponseMessage.class},
                null);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderId(132L);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(132L);
            AioOrderUnit l_orderUnit = (AioOrderUnit) l_orderUnits[0];

            l_serviceImpl.completeChange(l_orderUnit);

            assertTrue(
                    l_orderManager.getThreadLocalPersistenceEventInterceptor() instanceof WEB3AioCashTransUpdateInterceptor);
        }
        catch (Exception l_ex)
        {
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
