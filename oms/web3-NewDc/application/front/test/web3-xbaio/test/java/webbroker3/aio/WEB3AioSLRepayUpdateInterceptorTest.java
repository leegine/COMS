head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.32.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済更新インタセプタのテストクラス(WEB3AioSLRepayUpdateInterceptorTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 何文敏 (中訊) 新規作成
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSLRepayUpdateInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSLRepayUpdateInterceptorTest.class);
    WEB3AioNewOrderSpec aioOrderSpec = new WEB3AioNewOrderSpec(
        null,
        OrderTypeEnum.MINI_STOCK_BUY,
        AssetTransferTypeEnum.CASH_IN,
        12,
        14D,
        "",
        WEB3DateUtility.getDate("20070405","yyyyMMdd"),
        "",
        new Long(12));
    WEB3AioSLRepayUpdateInterceptor l_interceptor = new WEB3AioSLRepayUpdateInterceptor(aioOrderSpec);
    public WEB3AioSLRepayUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testMutate_case0001()
    {
        final String STR_METHOD_NAME = "testMutate_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.INSERT;
            OrderManagerPersistenceContext l_persistenceContext =
                OrderManagerPersistenceContext.ASSET_TRANSFER_DONE;
            AioOrderUnitParams l_aioOrderUnitParams = null;
            l_interceptor.mutate(
                l_type,
                l_persistenceContext,
                l_aioOrderUnitParams);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutate_case0002()
    {
        final String STR_METHOD_NAME = "testMutate_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.INSERT;
            OrderManagerPersistenceContext l_persistenceContext =
                OrderManagerPersistenceContext.ASSET_TRANSFER_DONE;
            
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParam = TestDBUtility.getAioOrderUnitRow();
            TestDBUtility.insertWithDel(l_aioOrderUnitParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            AioOrderUnitParams l_aioOrderUnitParams = l_aioOrderUnitParam;
            l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20070908", "yyyyMMdd"));
            l_interceptor.setOrderBizDate(WEB3DateUtility.getDate("20070908", "yyyyMMdd"));
            AioOrderUnitParams l_mutate = l_interceptor.mutate(
                l_type,
                l_persistenceContext,
                l_aioOrderUnitParams);
            assertEquals("9", l_mutate.getOrderCateg().intValue() + "");
            assertEquals(WEB3DateUtility.getDate("20070908", "yyyyMMdd"),
                WEB3DateUtility.getDate(l_mutate.getBizDate(), "yyyyMMdd"));
            assertEquals(WEB3DateUtility.getDate("20070908", "yyyyMMdd"),l_mutate.getDeliveryDate());
            assertEquals("0", l_mutate.getTaxType().intValue() + "");
            assertEquals("0", l_mutate.getMiniStockDiv());
            assertEquals(GtlUtils.getSystemTimestamp(), l_mutate.getReceivedDateTime());
            assertEquals(null, l_mutate.getSonarTraderCode());
            assertEquals(null, l_mutate.getOrderRequestNumber());
            assertEquals(null, l_mutate.getComDebitNumber());
            assertEquals(null, l_mutate.getGuaranteeDiv());
            assertEquals(null, l_mutate.getPaymentApplicationDiv());
            assertEquals("0", l_mutate.getCancelType());
            assertEquals("1", l_mutate.getOrderRootDiv());
            assertEquals("0000", l_mutate.getErrorReasonCode());
            assertEquals("0", l_mutate.getMqStatus());
            assertEquals(null, l_mutate.getCurrencyCode());
            assertEquals("0.0", new Double(l_mutate.getConvertAmount()) + "");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
