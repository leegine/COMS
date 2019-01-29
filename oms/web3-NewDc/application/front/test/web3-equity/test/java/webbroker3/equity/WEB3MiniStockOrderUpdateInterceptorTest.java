head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MiniStockOrderUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : (WEB3MarginExecuteReferenceServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  âΩï∂ïq(íÜêu)Å@@êVãKçÏê¨
 */
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3MiniStockOrderUpdateInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MiniStockOrderUpdateInterceptorTest.class);
    WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
    WEB3EquityEstimatedDeliveryPrice estimatedDeliveryPrice =
        new WEB3EquityEstimatedDeliveryPrice();
    WEB3MiniStockOrderUpdateInterceptor l_interceptor =
        new WEB3MiniStockOrderUpdateInterceptor(null, l_commission, estimatedDeliveryPrice);
    public WEB3MiniStockOrderUpdateInterceptorTest(String arg0)
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

    public void testMutate()
    {
        final String STR_METHOD_NAME = "testMutate()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]
                    { String.class, String.class, ProductTypeEnum.class }, 
                    "12345");
            
            EqtypeOrderUnitParams l_params = 
                l_interceptor.mutate(null, null, l_eqtypeOrderUnitParams);
            assertEquals(null, l_params.getForcedSettleReasonType());
            assertEquals(null, l_params.getApproveStatusType());
            assertEquals(null, l_params.getApproverCode());
            assertEquals(null, l_params.getApproveTimestamp());
            assertTrue(l_params.getMarginMaintenanceRateIsNull());
            assertEquals(null, l_params.getAdditionalMarginDate());
            assertTrue(l_params.getAdditionalMarginAccruedDaysIsNull());
            assertNull(l_params.getForcedExpireType());
            
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
