head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoAcceptedUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付更新インタセプタテスト(WEB3IfoAcceptedUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/07 趙林鵬 (中訊) 新規作成
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
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
 * （先物OP注文受付更新インタセプタテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3IfoAcceptedUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3IfoAcceptedUpdateInterceptorTest.class);
    
    public WEB3IfoAcceptedUpdateInterceptorTest(String arg0)
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

    public void test_ifoAcceptedUpdateInterceptor_mutate_C0001()
    {
        final String STR_METHOD_NAME = " test_ifoAcceptedUpdateInterceptor_mutate_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoAcceptedUpdateInterceptor l_interceptor = new WEB3IfoAcceptedUpdateInterceptor();

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
        l_ifoOrderUnitParams.setOrderConditionType("2");
        l_ifoOrderUnitParams.setPrice(null);
        l_ifoOrderUnitParams.setEstimatedPrice(null);
 
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();
        
        try
        {
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                l_updateType, l_dealing, l_ifoOrderUnitParams);
            
            log.debug("l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv()===" +
                    l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            assertNull(l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            
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
    
    public void test_ifoAcceptedUpdateInterceptor_mutate_C0002()
    {
        final String STR_METHOD_NAME = " test_ifoAcceptedUpdateInterceptor_mutate_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoAcceptedUpdateInterceptor l_interceptor = new WEB3IfoAcceptedUpdateInterceptor("0000", "9","","","");

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();

        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setPrice(100D);
        l_ifoOrderUnitParams.setEstimatedPrice(100D);
     
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();
        
        try
        {
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                l_updateType, l_dealing, l_ifoOrderUnitParams);
            
            log.debug("l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv()===" +
                    l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            assertEquals("9",l_AfterIfoOrderUnitParams.getSubmitOrderRouteDiv());
            
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
    
    public void test_ifoAcceptedUpdateInterceptor_mutate_C0003()
    {
        final String STR_METHOD_NAME = " test_ifoAcceptedUpdateInterceptor_mutate_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoAcceptedUpdateInterceptor l_interceptor = new WEB3IfoAcceptedUpdateInterceptor("0001", "2","","","");

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
 
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();
        
        try
        {
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                l_updateType, l_dealing, l_ifoOrderUnitParams);
            
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
