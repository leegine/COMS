head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MiniStockCancelUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MiniStockCancelUpdateInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/08 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MiniStockCancelUpdateInterceptorTest extends TestBaseForMock {

	/**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3MiniStockCancelUpdateInterceptorTest.class);

    WEB3MiniStockCancelUpdateInterceptor l_interceptor = null;
    
	public WEB3MiniStockCancelUpdateInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    //  ※インタセプタのプロパティ.代理入力者==nullの場合は、nullをセット。
	public void testMutate_C0001()
	{
		final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	String l_strOrderRootdiv=null;
        	WEB3GentradeTrader l_trader=null;
        	l_interceptor = new WEB3MiniStockCancelUpdateInterceptor(l_strOrderRootdiv,l_trader);

        	TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        	
        	assertEquals(0,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getTraderId());
        	assertEquals(WEB3ModifyCancelTypeDef.CANCELED,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getModifyCancelType());
        	assertNull(l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getOrderRootDiv());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//扱者 = インタセプタのプロパティ.代理入力者.取引者ID
	public void testMutate_C0002()
	{
		final String STR_METHOD_NAME = "testMutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	String l_strOrderRootdiv=null;

        	TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(0001); 
            l_traderParams.setTraderCode("001");
            l_traderParams.setBranchCode("001");
            l_traderParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_traderParams);
        	
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(001); 
            TestDBUtility.insertWithDel(l_institutionParams);
            
            long l_lnginstId = 001;
            InstitutionImpl l_institution= new InstitutionImpl(l_lnginstId);
            String l_strTraderCode = "001";
            String l_strBranchCode = "001";
            WEB3GentradeTrader l_trader= new WEB3GentradeTrader(l_institution, l_strTraderCode, l_strBranchCode);
            
            l_interceptor = new WEB3MiniStockCancelUpdateInterceptor(l_strOrderRootdiv,l_trader);
            
        	TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
        	
            assertEquals(1,l_interceptor.mutate(null,null,l_eqtypeOrderUnitParams).getTraderId());
	    }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

}
@
