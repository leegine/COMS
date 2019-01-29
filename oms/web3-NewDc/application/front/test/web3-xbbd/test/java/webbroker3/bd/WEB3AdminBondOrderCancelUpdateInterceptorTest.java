head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondOrderCancelUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondOrderCancelUpdateInterceptorTest extends
    TestBaseForMock
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderCancelUpdateInterceptorTest.class);
    
    public WEB3AdminBondOrderCancelUpdateInterceptorTest(String arg0)
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

    public void testCreateBalanceReferenceDetailList01()
    {
        final String STR_METHOD_NAME = "testCreateBalanceReferenceDetailList01()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminBondOrderCancelUpdateInterceptor l_interceptor = 
            new WEB3AdminBondOrderCancelUpdateInterceptor();
        try
        {
            AdministratorParams l_adminParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_adminParams);
            l_interceptor.setAdministrator(l_admin);
            Timestamp l_tsNow = GtlUtils.getSystemTimestamp();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsNow);
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;
            BondOrderUnitParams l_orderUnitRow = TestDBUtility.getBondOrderUnitRow();
            l_orderUnitRow.setBizDate("20070311");
            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            l_interceptor.mutate(l_type, l_context, l_orderUnitRow);
            assertEquals("0", l_orderUnitRow.getHostSendDiv());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testCreateBalanceReferenceDetailList02()
    {
        final String STR_METHOD_NAME = "testCreateBalanceReferenceDetailList02()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3AdminBondOrderCancelUpdateInterceptor l_interceptor = 
            new WEB3AdminBondOrderCancelUpdateInterceptor();
        try
        {
            AdministratorParams l_adminParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_adminParams);
            WEB3Administrator l_admin = new WEB3Administrator(l_adminParams);
            l_interceptor.setAdministrator(l_admin);
            Timestamp l_tsNow = GtlUtils.getSystemTimestamp();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsNow);
            OrderManagerPersistenceType l_type = OrderManagerPersistenceType.UPDATE;
            BondOrderUnitParams l_orderUnitRow = TestDBUtility.getBondOrderUnitRow();
            l_orderUnitRow.setBizDate("22220313");
            OrderManagerPersistenceContext l_context = OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER;
            l_interceptor.mutate(l_type, l_context, l_orderUnitRow);
            assertEquals("2", l_orderUnitRow.getHostSendDiv());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error("Exception l_ex == " + l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
}
@
