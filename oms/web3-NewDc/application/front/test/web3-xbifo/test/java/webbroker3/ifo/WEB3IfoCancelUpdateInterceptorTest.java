head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCancelUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取消更新インタセプタクラステスト(WEB3IfoCancelUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/07 趙林鵬 (中訊) 新規作成
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物OP取消更新インタセプタクラステスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3IfoCancelUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoCancelUpdateInterceptorTest.class);

    public WEB3IfoCancelUpdateInterceptorTest(String arg0)
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

    public void test_ifoCancelUpdateInterceptor_mutate_C0001()
    {
        final String STR_METHOD_NAME = " test_ifoCancelUpdateInterceptor_mutate_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoCancelUpdateInterceptor l_interceptor = new WEB3IfoCancelUpdateInterceptor();
        
        l_interceptor.setTraderId(1001);
        
        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        l_ifoOrderUnitParams.setConfirmedOrderPrice(null);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getSessionProperty",
            new Class[] {String.class},
            "9");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeSubmitOrderRouteDiv",
            new Class[] {IfoOrderUnit.class},
            "2");
        
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();
        try
        {
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            
            log.debug("l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv()===" +
                l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            assertEquals("2",l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            
            log.debug("l_AfterIfoOrderUnitParams.getConfirmedOrderRev()===" +
                l_AfterIfoOrderUnitParams.getConfirmedOrderRev());
            assertEquals("2",l_AfterIfoOrderUnitParams.getConfirmedOrderRev());
            
            log.debug("l_AfterIfoOrderUnitParams.getOrderRev()===" +
                l_AfterIfoOrderUnitParams.getOrderRev());
            assertEquals("1",l_AfterIfoOrderUnitParams.getOrderRev());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_ifoCancelUpdateInterceptor_mutate_C0002()
    {
        final String STR_METHOD_NAME = " test_ifoCancelUpdateInterceptor_mutate_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoCancelUpdateInterceptor l_interceptor = new WEB3IfoCancelUpdateInterceptor();
        
        l_interceptor.setTraderId(0);
        
        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        l_ifoOrderUnitParams.setConfirmedOrderPrice(100);
        l_ifoOrderUnitParams.setConfirmedOrderRev("7");
        l_ifoOrderUnitParams.setOrderRev("3");
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getSessionProperty",
            new Class[] {String.class},
            "9");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeSubmitOrderRouteDiv",
            new Class[] {IfoOrderUnit.class},
            "9");
        
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();
        try
        {
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_ifoOrderUnitParams);
            
            log.debug("l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv()===" +
                    l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            assertEquals("9",l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            
            log.debug("l_AfterIfoOrderUnitParams.getConfirmedOrderRev()===" +
                l_AfterIfoOrderUnitParams.getConfirmedOrderRev());
            assertEquals("7",l_AfterIfoOrderUnitParams.getConfirmedOrderRev());
            
            log.debug("l_AfterIfoOrderUnitParams.getOrderRev()===" +
                l_AfterIfoOrderUnitParams.getOrderRev());
            assertEquals("3",l_AfterIfoOrderUnitParams.getOrderRev());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
}
@
