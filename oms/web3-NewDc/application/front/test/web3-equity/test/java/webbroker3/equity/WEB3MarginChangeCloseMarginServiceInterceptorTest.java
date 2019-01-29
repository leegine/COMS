head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginChangeCloseMarginServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3MarginChangeCloseMarginServiceInterceptorTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginChangeCloseMarginServiceInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginServiceInterceptorTest.class);
    
    WEB3MarginChangeCloseMarginServiceInterceptor l_interceptor =
        new WEB3MarginChangeCloseMarginServiceInterceptor();

    public WEB3MarginChangeCloseMarginServiceInterceptorTest(String arg0)
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

    public void testOnCall0001()
    {
        final String STR_METHOD_NAME = "testOnCall0001()";
        log.exiting(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(WEB3DateUtility.getDate("20080214", "yyyyMMdd").getTime()));
            
            OrderUnit[] l_orderUnits = new OrderUnit[1];
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqTypeOrderUnit l_eqtypeOrderUnit = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            l_orderUnits[0] = (OrderUnit)l_eqtypeOrderUnit;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "getOrderUnits",
                    new Class[]{long.class},
                    l_orderUnits);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3MarginCloseMarginChangeConfirmRequest l_request = new WEB3MarginCloseMarginChangeConfirmRequest();
            l_request.id = "1111";
            
            Object[] l_obj = {l_request};

            l_interceptor.onCall(null, l_obj);
 //??更点已?除
//            BooleanEnum l_blnEnum = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
//                "web3.forcedsettleordervalidationskip");
//            
//            assertEquals(BooleanEnum.TRUE, l_blnEnum);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    /*
     * Test method for 'webbroker3.equity.WEB3MarginChangeCloseMarginServiceInterceptor.onReturn(Object, Object)'
     */
    public void testOnReturn()
    {
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
    }

    /*
     * Test method for 'webbroker3.equity.WEB3MarginChangeCloseMarginServiceInterceptor.onThrowable(Object, Throwable)'
     */
    public void testOnThrowable()
    {
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
    }

}
@
