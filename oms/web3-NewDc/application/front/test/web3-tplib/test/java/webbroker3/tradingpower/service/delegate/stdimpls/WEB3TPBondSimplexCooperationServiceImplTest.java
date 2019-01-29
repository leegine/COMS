head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPBondSimplexCooperationServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
 Copyright        : (株)大和総研 証券ソリューションシステム第二部 
 File Name        : WEB3TPBondSimplexCooperationServiceImplTest.java
 Author Name      : Daiwa Institute of Research  
 Revesion History : 2008/05/28 楊夫志 (中訊) 新規作成  
 */
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3RestraintDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.data.TpOtherTempRestraintParams;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPBondSimplexCooperationServiceImplTest extends
		TestBaseForMock {

	/**
	 * ログユーティリティ<BR>
	 */
	private static WEB3LogUtility log = WEB3LogUtility
			.getInstance(WEB3TPBondSimplexCooperationServiceImplTest.class);

	WEB3TPBondSimplexCooperationServiceImpl l_impl = new WEB3TPBondSimplexCooperationServiceImpl();
	public WEB3TPBondSimplexCooperationServiceImplTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
		TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for
	 * 'webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl.saveBondBuyAmount(long,
	 * double, Date, Date, String)'
	 */
    //isExist注文No()の戻り値が、trueの場合
    //例外「注文No重複エラー」をスローする。
	public void testSaveBondBuyAmount_C0001()
	{
		final String STR_METHOD_NAME = " testSaveBondBuyAmount_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
        	TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
        	l_tpOtherTempRestraintParams.setAccountId(11111);
        	l_tpOtherTempRestraintParams.setDeleteKey1("aaaaa");
        	l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.TRUE);
        	l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        	l_tpOtherTempRestraintParams.setRestraintDiv(WEB3RestraintDivDef.BOND_BUY_AMOUNT);
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);

        	long l_lngAccountId = 0001;
        	double l_dblRestraint = 1111;
        	Date l_datFinTransactionDate = WEB3DateUtility.getDate("20080528", "yyyyMMdd");
        	Date l_datDeliveryDate = WEB3DateUtility.getDate("20080527", "yyyyMMdd");
        	String l_strOrderNo = "aaaaa";
        	l_impl.saveBondBuyAmount(
    			l_lngAccountId, l_dblRestraint, l_datFinTransactionDate, l_datDeliveryDate, l_strOrderNo);
        	fail();
        }
        catch(WEB3BaseException l_ex)
        {
        	log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03095);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//isExist注文No()の戻り値が、falseの場合
	//correct case
	public void testSaveBondBuyAmount_C0002()
	{
		final String STR_METHOD_NAME = " testSaveBondBuyAmount_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	long l_lngAccountId = 0001;
        	double l_dblRestraint = 1111;
        	Date l_datFinTransactionDate = WEB3DateUtility.getDate("20080528", "yyyyMMdd");
        	Date l_datDeliveryDate = WEB3DateUtility.getDate("20080527", "yyyyMMdd");
        	String l_strOrderNo = "";
        	l_impl.saveBondBuyAmount(
    			l_lngAccountId,l_dblRestraint,l_datFinTransactionDate,l_datDeliveryDate,l_strOrderNo);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}
	/*
	 * Test method for
	 * 'webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl.cancelBondBuyAmount(String)'
	 */
	//isExist注文No()の戻り値が、falseの場合は
	//例外「取消データなしエラー」をスローする。
	public void testCancelBondBuyAmount_C0001()
	{
		final String STR_METHOD_NAME = " testCancelBondBuyAmount_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	String l_strOrderNo = "";
        	l_impl.cancelBondBuyAmount(l_strOrderNo);
        	fail();
        }
        catch(WEB3BaseException l_ex)
        {
        	log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03096);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//isExist注文No()の戻り値が、trueの場合は
	//correct case
	public void testCancelBondBuyAmount_C0002()
	{
		final String STR_METHOD_NAME = " testCancelBondBuyAmount_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
        	String l_strOrderNo = "1";

        	TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
        	TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
        	l_tpOtherTempRestraintParams.setAccountId(11111);
        	l_tpOtherTempRestraintParams.setDeleteKey1("1");
        	l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.TRUE);
        	l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        	l_tpOtherTempRestraintParams.setRestraintDiv(WEB3RestraintDivDef.BOND_BUY_AMOUNT);
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);

        	l_impl.cancelBondBuyAmount(l_strOrderNo);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	/**
	 * getDepositBalance
	 *
	 */
	//顧客勘定残高(マスタ情報)Row.残高残高（当日+０日）
	public void testGetDepositBalance_C0001()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0001";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_0;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 1000);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//顧客勘定残高(マスタ情報)Row.残高残高（当日+１日）
	public void testGetDepositBalance_C0002()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0002";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_1;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 2000);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//顧客勘定残高(マスタ情報)Row.残高残高（当日+２日）
	public void testGetDepositBalance_C0003()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0003";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_2;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 3000);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//顧客勘定残高(マスタ情報)Row.残高残高（当日+３日）
	public void testGetDepositBalance_C0004()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0004";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_3;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 4000);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//顧客勘定残高(マスタ情報)Row.残高残高（当日+４日）
	public void testGetDepositBalance_C0005()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0005";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_4;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 5000);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//顧客勘定残高(マスタ情報)Row.残高残高（当日+５日以降）
	public void testGetDepositBalance_C0006()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0006";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_5;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 6000);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//l_tpCashBalanceRow == null
	public void testGetDepositBalance_C0007()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0007";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_5;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0003);
            l_tpCashBalanceParams.setSubAccountId(0002);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});

            assertEquals(l_double.intValue(), 0);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//補助口座ID
	//catch NotFoundException
	public void testGetDepositBalance_C0008()
	{
		final String STR_METHOD_NAME = "testGetDepositBalance_C0008";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			int l_intReservedDate = WEB3TPSpecifiedPointDef.T_5;

			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0003);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.FUTURES_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setAccountId(0001);
            l_tpCashBalanceParams.setSubAccountId(0L);
            l_tpCashBalanceParams.setCashBalance0(1000);
            l_tpCashBalanceParams.setCashBalance1(2000);
            l_tpCashBalanceParams.setCashBalance2(3000);
            l_tpCashBalanceParams.setCashBalance3(4000);
            l_tpCashBalanceParams.setCashBalance4(5000);
            l_tpCashBalanceParams.setCashBalance5(6000);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            Method l_method = null;

            l_method = WEB3TPBondSimplexCooperationServiceImpl.class.getDeclaredMethod(
			"getDepositBalance",
			new Class[] {
				MainAccount.class,
				int.class });
            l_method.setAccessible(true);

            l_method.invoke(l_impl, new Object[] {
                l_mainAccount, new Integer(l_intReservedDate)});

            Double l_double = (Double)l_method.invoke(l_impl, new Object[] {
            		l_mainAccount, new Integer(l_intReservedDate)});
            assertEquals(l_double.intValue(), 0);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	/*
	 * Test method for
	 * 'webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl.getAssetList(WEB3GentradeSubAccount)'
	 */
	//引数.補助口座がnullの場合
	public void testGetAssetList_C0001()
	{
		final String STR_METHOD_NAME = "testGetAssetList_C0001";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			WEB3GentradeSubAccount l_subAccount = null;
			l_impl.getAssetList(l_subAccount);
		}
		catch(WEB3BaseException l_ex)
		{
			log.debug(STR_METHOD_NAME, l_ex);
			assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//is信用口座開設()の戻り値 = false(現物顧客)
	public void testGetAssetList_C0002()
	{
		final String STR_METHOD_NAME = "testGetAssetList_C0002";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
			l_accountParams.setInstitutionCode("0D");
			l_accountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode("0D");
            //証券会社ID
            l_subAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();;
            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(1000);
            l_tradingpowerCalcConditionParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            TpCalcResultEquityParams l_tpCalcResultEquityParams= TestDBUtility.getTpCalcResultEquityRow();;
            l_tpCalcResultEquityParams.setAccountId(0001);
            l_tpCalcResultEquityParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);
            
			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
	                "webbroker3.gentrade.WEB3GentradeMainAccount",
	                "isMarginAccountEstablished",
	                new Class[] {String.class,},new Boolean(false));
			
			String l_strAssetList = "0" + ":" + "0" + ":" + "0";

			assertEquals(l_impl.getAssetList(l_subAccount), l_strAssetList);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

	//is信用口座開設()の戻り値 = true(信用顧客)
	public void testGetAssetList_C0003()
	{
		final String STR_METHOD_NAME = "testGetAssetList_C0003";
		log.entering(TEST_START + STR_METHOD_NAME);

		try
		{
			TestDBUtility.deleteAll(MainAccountRow.TYPE);
			MainAccountParams l_accountParams = TestDBUtility.getMainAccountRow();
			l_accountParams.setAccountId(0001);
			l_accountParams.setInstitutionCode("0D");
			l_accountParams.setInstitutionId(33);
			l_accountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_accountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(0001);
            l_subAccountParams.setSubAccountId(0002);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode("0D");
            //証券会社ID
            l_subAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();;
            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(1000);
            l_tradingpowerCalcConditionParams.setAccountId(0001);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams= TestDBUtility.getTpCalcResultMarginRow();;
            l_tpCalcResultMarginParams.setAccountId(0001);
            l_tpCalcResultMarginParams.setCalcResultMarginId(1001);
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070828","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailRow.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams= TestDBUtility.getTpCalcResultMarginDetailRow();;
            l_tpCalcResultMarginDetailParams.setAccountId(0001);
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1001);
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070828","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAccount = (MainAccount)l_accountManager.getMainAccount(0001);

            WEB3GentradeSubAccount l_subAccount =  new WEB3GentradeSubAccount(l_mainAccount,l_subAccountParams);
            
			TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
	                "webbroker3.gentrade.WEB3GentradeMainAccount",
	                "isMarginAccountEstablished",
	                new Class[] {String.class,},new Boolean(true));
			
			String l_strAssetList = "1" + ":" + "0" + ":" + "0";

			assertEquals(l_impl.getAssetList(l_subAccount), l_strAssetList);
		}
		catch(Exception l_ex)
		{
			log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
	}

}
@
