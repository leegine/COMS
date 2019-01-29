head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderCarryOverSkipObjectCheckServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityOrderCarryOverSkipObjectCheckServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/10 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderCarryOverSkipObjectCheckServiceImplTest extends
		TestBaseForMock {
	
	private static WEB3LogUtility log = WEB3LogUtility
	.getInstance(WEB3EquityOrderCarryOverSkipObjectCheckServiceImplTest.class);

    private WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl l_impl = null;

	public WEB3EquityOrderCarryOverSkipObjectCheckServiceImplTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		MOCK_MANAGER.setIsMockUsed(true);
		l_impl = new WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//注文単位オブジェクト=null
	public void testIsCarryOverOrderUnit_C0001()
	{
		final String STR_METHOD_NAME = "testIsCarryOverOrderUnit_C0001()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			OrderUnit l_orderUnit=null;
			l_impl.isCarryOverOrderUnit(l_orderUnit);
			fail();
		} 
		catch (WEB3SystemLayerException l_ex)
		{
			assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
				l_ex.getErrorInfo());
		} catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		
		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//引数の注文単位.発注日＝取引時間管理.get発注日( )の前営業日
	//かつ　@引数の注文単位.注文失効日≧取引時間管理.get発注日( )(*2)　@の場合
	public void testIsCarryOverOrderUnit_C0002()
	{
		final String STR_METHOD_NAME = "testIsCarryOverOrderUnit_C0002()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            OrderUnit l_orderUnit =
                l_orderManager.getOrderUnit(3304148080001L);
				
			TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
			EqtypeOrderUnitParams l_params = TestDBUtility.getEqtypeOrderUnitRow();
			l_params.setBizDate("20080710");
			l_params.setExpirationDate(WEB3DateUtility.getDate("20080710", "yyyyMMdd"));
			
			l_impl.isCarryOverOrderUnit(l_orderUnit);
			fail();
		} 
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
		
		log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
		log.exiting(TEST_END + STR_METHOD_NAME);
	}

	//上記以外の場合は、falseを返す
	public void testIsCarryOverOrderUnit_C0003()
	{
		final String STR_METHOD_NAME = "testIsCarryOverOrderUnit_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            OrderManager l_orderManager =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            OrderUnit l_orderUnit =
                l_orderManager.getOrderUnit(3304148080001L);
				
			TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
			EqtypeOrderUnitParams l_params = TestDBUtility.getEqtypeOrderUnitRow();
			l_params.setBizDate("20080710");
			l_params.setExpirationDate(WEB3DateUtility.getDate("20080710", "yyyyMMdd"));
			
			assertTrue(l_impl.isCarryOverOrderUnit(l_orderUnit));
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
