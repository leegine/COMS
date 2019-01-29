head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3BondExecuteReferenceServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 金傑 新規作成
Revesion History : 2007/07/17 周墨洋 (中訊) 仕様変更・モデル197
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.enum.Enumerated;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.message.WEB3BondSortKey;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 債券注文約定照会サービスImplのテスト<BR>
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceServiceImplTest extends TestBaseForMock
{

    private WEB3BondExecuteReferenceServiceImpl l_bondExecuteReferenceServiceImpl = null;
    
    private WEB3BondExecuteReferenceRequest l_request = null;

    private static int n =0;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceServiceImplTest.class); 
    
	public WEB3BondExecuteReferenceServiceImplTest(String name)
	{
		super(name);
	}
	
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest();
        this.l_request = new WEB3BondExecuteReferenceRequestForTest();
        this.getData();
        this.getMock();
    }
    
    protected void tearDown() throws Exception
    {
    	super.tearDown();
    	this.l_bondExecuteReferenceServiceImpl = null;
    	this.l_request = null;
    }
    
    public void test_getExecuteReference_C0001(){
        final String STR_METHOD_NAME = "test_getExecuteReference_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
		{
			this.l_request.pageIndex = "1";
			this.l_request.pageSize = "2";
        	WEB3BondExecuteReferenceResponse l_realResonpse = 
				this.l_bondExecuteReferenceServiceImpl.getExecuteReference(l_request);
        	
        	assertEquals(0,WEB3DateUtility.compareToDay(l_realResonpse.details[0].domesticDeliveryDate,WEB3DateUtility.getDate("2007/02/10", "yyyy/MM/dd")));
        	assertNull(l_realResonpse.details[0].foreignDeliveryDate);
        	

		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_getExecuteReference_C0002(){
        final String STR_METHOD_NAME = "test_getExecuteReference_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
		{
			this.l_request.pageIndex = "1";
			this.l_request.pageSize = "2";
        	WEB3BondExecuteReferenceResponse l_realResonpse = 
				this.l_bondExecuteReferenceServiceImpl.getExecuteReference(l_request);
        	
        	assertEquals(0,WEB3DateUtility.compareToDay(l_realResonpse.details[0].domesticDeliveryDate,WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd")));
        	assertNull(l_realResonpse.details[0].foreignDeliveryDate);
        	

		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_getExecuteReference_C0003()
	{
		final String STR_METHOD_NAME = "test_getExecuteReference_C0003()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			this.l_request.pageIndex = "1";
			this.l_request.pageSize = "2";
			WEB3BondExecuteReferenceResponse l_realResonpse = 
				this.l_bondExecuteReferenceServiceImpl.getExecuteReference(l_request);

			assertEquals(0, WEB3DateUtility.compareToDay(l_realResonpse.details[0].domesticDeliveryDate,
					WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd")));
			assertEquals(0, WEB3DateUtility.compareToDay(l_realResonpse.details[0].foreignDeliveryDate, WEB3DateUtility
					.getDate("2007/02/09", "yyyy/MM/dd")));

		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME, l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME, l_exception);
			fail();
		}

		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    public void test_getExecuteReference_C0004(){
        final String STR_METHOD_NAME = "test_getExecuteReference_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
		{
			this.l_request.pageIndex = "1";
			this.l_request.pageSize = "2";
        	WEB3BondExecuteReferenceResponse l_realResonpse = 
				this.l_bondExecuteReferenceServiceImpl.getExecuteReference(l_request);
        	
        	assertEquals(0,WEB3DateUtility.compareToDay(l_realResonpse.details[0].domesticDeliveryDate,WEB3DateUtility.getDate("2007/02/10", "yyyy/MM/dd")));
        	assertEquals(0,WEB3DateUtility.compareToDay(l_realResonpse.details[0].foreignDeliveryDate,WEB3DateUtility.getDate("2007/02/10", "yyyy/MM/dd")));
        	

		}
		catch (WEB3BaseException l_web3BaseException)
		{
			log.error(STR_METHOD_NAME,l_web3BaseException);
			fail();
		}
		catch (Exception l_exception)
		{
			log.error(STR_METHOD_NAME,l_exception);
			fail();
		}

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0001()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImpl();

        String l_strProductDiv = null;

        String expected = "account_id = ? and sub_account_id = ? and (biz_date >= ? or order_exec_status = ?) ";
        String actual = null;

        actual = l_bondExecuteReferenceServiceImpl.createQueryString(l_strProductDiv);

        assertEquals(expected, actual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0002()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImpl();

        String l_strProductDiv = "1";

        String expected = "account_id = ? and sub_account_id = ? and (biz_date >= ? or order_exec_status = ?) ";
        String actual = null;

        actual = l_bondExecuteReferenceServiceImpl.createQueryString(l_strProductDiv);

        assertEquals(expected, actual);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0003()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImpl();

        String l_strProductDiv = "2";

        String expected =
            "account_id = ? and sub_account_id = ? and (biz_date >= ? or order_exec_status = ?) and bond_type = ? ";
        String actual = null;

        actual = l_bondExecuteReferenceServiceImpl.createQueryString(l_strProductDiv);

        assertEquals(expected, actual);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0004()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImpl();

        String l_strProductDiv = "5";

        String expected =
            "account_id = ? and sub_account_id = ? and (biz_date >= ? or order_exec_status = ?) and bond_type = ? ";
        String actual = null;

        actual = l_bondExecuteReferenceServiceImpl.createQueryString(l_strProductDiv);

        assertEquals(expected, actual);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0005()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImpl();

        String l_strProductDiv = "3";

        String expected =
            "account_id = ? and sub_account_id = ? and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?) ";
        String actual = null;

        actual = l_bondExecuteReferenceServiceImpl.createQueryString(l_strProductDiv);

        assertEquals(expected, actual);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0006()
    {
        final String STR_METHOD_NAME = " testCreateQueryString_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImpl();

        String l_strProductDiv = "4";

        String expected =
            "account_id = ? and sub_account_id = ? and (biz_date >= ? or order_exec_status = ?) and bond_type not in(?, ?) ";
        String actual = null;

        actual = l_bondExecuteReferenceServiceImpl.createQueryString(l_strProductDiv);

        assertEquals(expected, actual);

        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryData_case0001()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0001()";
        log.entering(TEST_END + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getSubAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest_2();

        String l_strProductDiv = null;

        Object[] l_actual = null;

        try
        {
            l_actual = l_bondExecuteReferenceServiceImpl.createQueryData(l_strProductDiv);

            assertEquals(4, l_actual.length);
            Long l_lngExpected;
            l_lngExpected = new Long(333812512203L);
            assertEquals(l_lngExpected, (Long)l_actual[0]);
            l_lngExpected = new Long(33381251220301L);
            assertEquals(l_lngExpected, (Long)l_actual[1]);
            String l_strExpected = null;
            l_strExpected = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            assertEquals(l_strExpected, (String)l_actual[2]);
            assertEquals("0", (String)l_actual[3]);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
                TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateQueryData_case0002()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0002()";
        log.entering(TEST_END + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getSubAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest_2();

        String l_strProductDiv = "1";

        Object[] l_actual = null;

        try
        {
            l_actual = l_bondExecuteReferenceServiceImpl.createQueryData(l_strProductDiv);

            assertEquals(4, l_actual.length);
            Long l_lngExpected;
            l_lngExpected = new Long(333812512203L);
            assertEquals(l_lngExpected, (Long)l_actual[0]);
            l_lngExpected = new Long(33381251220301L);
            assertEquals(l_lngExpected, (Long)l_actual[1]);
            String l_strExpected = null;
            l_strExpected = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            assertEquals(l_strExpected, (String)l_actual[2]);
            assertEquals("0", (String)l_actual[3]);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
                TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateQueryData_case0003()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0003()";
        log.entering(TEST_END + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getSubAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest_2();

        String l_strProductDiv = "2";

        Object[] l_actual = null;

        try
        {
            l_actual = l_bondExecuteReferenceServiceImpl.createQueryData(l_strProductDiv);

            assertEquals(5, l_actual.length);
            Long l_lngExpected;
            l_lngExpected = new Long(333812512203L);
            assertEquals(l_lngExpected, (Long)l_actual[0]);
            l_lngExpected = new Long(33381251220301L);
            assertEquals(l_lngExpected, (Long)l_actual[1]);
            String l_strExpected = null;
            l_strExpected = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            assertEquals(l_strExpected, (String)l_actual[2]);
            assertEquals("0", (String)l_actual[3]);
            assertEquals(BondTypeEnum.FOREIGN_BOND, (Enumerated)l_actual[4]);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
                TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateQueryData_case0004()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0004()";
        log.entering(TEST_END + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getSubAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest_2();

        String l_strProductDiv = "3";

        Object[] l_actual = null;

        try
        {
            l_actual = l_bondExecuteReferenceServiceImpl.createQueryData(l_strProductDiv);

            assertEquals(5, l_actual.length);
            Long l_lngExpected;
            l_lngExpected = new Long(333812512203L);
            assertEquals(l_lngExpected, (Long)l_actual[0]);
            l_lngExpected = new Long(33381251220301L);
            assertEquals(l_lngExpected, (Long)l_actual[1]);
            String l_strExpected = null;
            l_strExpected = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            assertEquals(l_strExpected, (String)l_actual[2]);
            assertEquals("0", (String)l_actual[3]);
            assertEquals(BondTypeEnum.FOREIGN_BOND, (Enumerated)l_actual[4]);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
                TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateQueryData_case0005()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0005()";
        log.entering(TEST_END + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getSubAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest_2();

        String l_strProductDiv = "4";

        Object[] l_actual = null;

        try
        {
            l_actual = l_bondExecuteReferenceServiceImpl.createQueryData(l_strProductDiv);

            assertEquals(6, l_actual.length);
            Long l_lngExpected;
            l_lngExpected = new Long(333812512203L);
            assertEquals(l_lngExpected, (Long)l_actual[0]);
            l_lngExpected = new Long(33381251220301L);
            assertEquals(l_lngExpected, (Long)l_actual[1]);
            String l_strExpected = null;
            l_strExpected = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            assertEquals(l_strExpected, (String)l_actual[2]);
            assertEquals("0", (String)l_actual[3]);
            assertEquals(BondTypeEnum.FOREIGN_BOND, (Enumerated)l_actual[4]);
            assertEquals(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND, (Enumerated)l_actual[5]);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
                TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateQueryData_case0006()
    {
        final String STR_METHOD_NAME = " testcreateQueryData_case0006()";
        log.entering(TEST_END + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getSubAccountRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        this.l_bondExecuteReferenceServiceImpl = new WEB3BondExecuteReferenceServiceImplForTest_2();

        String l_strProductDiv = "5";

        Object[] l_actual = null;

        try
        {
            l_actual = l_bondExecuteReferenceServiceImpl.createQueryData(l_strProductDiv);

            assertEquals(5, l_actual.length);
            Long l_lngExpected;
            l_lngExpected = new Long(333812512203L);
            assertEquals(l_lngExpected, (Long)l_actual[0]);
            l_lngExpected = new Long(33381251220301L);
            assertEquals(l_lngExpected, (Long)l_actual[1]);
            String l_strExpected = null;
            l_strExpected = WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(), "yyyyMMdd");
            assertEquals(l_strExpected, (String)l_actual[2]);
            assertEquals("0", (String)l_actual[3]);
            assertEquals(BondTypeEnum.INDIVIDUAL_GOVERNMENT_BOND, (Enumerated)l_actual[4]);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
                TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
                TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    private void getData()
	{
		final String STR_METHOD_NAME = "getData()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
		{
			WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
			l_context.setInstitutionCode("0D");
			l_context.setBranchCode("381");
			l_context.setProductCode("0");
			l_context.setBizDateType("0");
			l_context.setMarketCode("SP");
			l_context.setTradingTimeType("26");
			l_context.setOrderAcceptTransaction("07");
			WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);

			TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
			TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            TestDBUtility.deleteAllAndCommit(BondOrderUnitParams.TYPE);
			BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams();
			l_bondOrderUnitParams.setOrderUnitId(123456L);
			l_bondOrderUnitParams.setAccountId(333812512203L);
			l_bondOrderUnitParams.setSubAccountId(33381251220301L);
			l_bondOrderUnitParams.setBranchId(33381);
			l_bondOrderUnitParams.setOrderId(65421L);
			l_bondOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
			l_bondOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
			l_bondOrderUnitParams.setDealType("35");
			l_bondOrderUnitParams.setLastOrderActionSerialNo(123);
			l_bondOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRING);
			l_bondOrderUnitParams.setLastExecutionSerialNo(321);
			l_bondOrderUnitParams.setProductType(ProductTypeEnum.BOND);
			l_bondOrderUnitParams.setMarketId(35);
			l_bondOrderUnitParams.setQuantity(2000L);
			l_bondOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
			l_bondOrderUnitParams.setForeignDeliveryDate(WEB3DateUtility.getDate("2007/02/09", "yyyy/MM/dd"));
			l_bondOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
			l_bondOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
			l_bondOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("2007/02/09", "yyyy/MM/dd"));
			l_bondOrderUnitParams.setLockStatus("1");
			l_bondOrderUnitParams.setOrderExecStatus("1");
			l_bondOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
			l_bondOrderUnitParams.setBizDate("20070208");
			l_bondOrderUnitParams.setProductId(123456L);
			l_bondOrderUnitParams.setPrice(23.2);
			l_bondOrderUnitParams.setPaymentDate(WEB3DateUtility.getDate("2007/02/10", "yyyy/MM/dd"));
			l_bondOrderUnitParams.setProductId(1000126L);
			l_bondOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
			l_bondOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
			log.debug(STR_METHOD_NAME+" n = "+n);
			if (n >= 4)
			{
				l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);
			}
			else
			{
				l_bondOrderUnitParams.setBondType(BondTypeEnum.JGB);
			}
			TestDBUtility.insertWithDelAndCommit(l_bondOrderUnitParams);
						
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1000126L);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDelAndCommit(l_productParams);

			BondProductParams l_bondProductParams = new BondProductParams();
			l_bondProductParams.setProductId(1000126L);
			l_bondProductParams.setInstitutionCode("20");
			l_bondProductParams.setProductCode("0126");
			l_bondProductParams.setProductIssueCode("213");
			l_bondProductParams.setBondType(BondTypeEnum.DOMESTIC_BOND);
			l_bondProductParams.setHostProductCode("6666");
			l_bondProductParams.setProductIssueCode("8888");
			l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
			l_bondProductParams.setIssuePrice(20.5);
			l_bondProductParams.setParValue(2.1);
			l_bondProductParams.setMaturityDate(WEB3DateUtility.getDate("2007/02/08", "yyyy/MM/dd"));
			l_bondProductParams.setRedemptionPrice(10.5);
			l_bondProductParams.setCouponType(CouponTypeEnum.FLOATING_COUPON);
			l_bondProductParams.setCoupon(0.5);
			l_bondProductParams.setYearlyInterestPayments(9);
			l_bondProductParams.setTradeHandleDiv("0");
			l_bondProductParams.setTradeUnit(20.3);
			l_bondProductParams.setMinFaceAmount(10.21);
			l_bondProductParams.setAutoExecDiv("1");
			l_bondProductParams.setHostProductIssueCode("2");
			l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
			l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
			TestDBUtility.insertWithDelAndCommit(l_bondProductParams);

			l_bondProductParams.setCreatedTimestamp(Calendar.getInstance().getTime());
			l_bondProductParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
			TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
            
            
			TraderParams l_traderParams = TestDBUtility.getTraderRow();
			l_traderParams.setTraderId(3338111132L);
			l_traderParams.setLoginId(3338111132L);
			TestDBUtility.insertWithDelAndCommit(l_traderParams);
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    private void getMock()
	{
		final String STR_METHOD_NAME = "getMock()";
		log.entering(TEST_START + STR_METHOD_NAME);
		try
    	{
    	TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
    			"webbroker3.bd.WEB3BondOrderManager",
				"validateOrderCancelPossibleStatus", 
				new Class[] { WEB3BondOrderUnit.class }, 
				null);
		
    	WEB3GentradeTrader l_genTrader = new WEB3GentradeTrader(3338111132L,false);
		TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
				"webbroker3.gentrade.WEB3GentradeFinObjectManager", 
				"getTrader", 
				new Class[] { long.class },
				l_genTrader);
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
		}
		log.exiting(TEST_END + STR_METHOD_NAME);
	}
    
    private class WEB3BondExecuteReferenceRequestForTest extends WEB3BondExecuteReferenceRequest
	{
		public void validate() throws WEB3BaseException
		{

		}
	}
    
    private class WEB3BondExecuteReferenceServiceImplForTest extends WEB3BondExecuteReferenceServiceImpl
	{
		public String createQueryString(String l_strProductDiv)
		{
			return "createQueryString";
		}

		public Object[] createQueryData(String l_strProductDiv) throws WEB3BaseException
		{
			return null;
		}
		public String createSortCond(WEB3BondSortKey[] l_sortKeys) throws WEB3BaseException
	    {
	    	return "createSortCond";
	    }
		public ArrayList getOrderUnitList(String l_strQueryString, Object[] l_objQueryDatas, String l_strSortCond)
				throws WEB3BaseException
		{
			final String STR_METHOD_NAME = "WEB3BondExecuteReferenceServiceImplForTest.getOrderUnitList()";
			log.entering(TEST_START + STR_METHOD_NAME);
			ArrayList l_lis = new ArrayList();
			try
			{
				WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnitForTest(123456L);
				l_lis.add(l_orderUnit);
			}
			catch (DataQueryException l_ex)
			{
				log.error(STR_METHOD_NAME, l_ex);
			}
			catch (DataNetworkException l_ex)
			{
				log.error(STR_METHOD_NAME, l_ex);
			}
			log.exiting(TEST_END + STR_METHOD_NAME);
			return l_lis;
		}
		
	    public Trader getTrader() throws WEB3SystemLayerException
	    {
	    	return null;
	    }
	}

    private class WEB3BondExecuteReferenceServiceImplForTest_2 extends WEB3BondExecuteReferenceServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
            long l_lngAccountId = 333812512203L;

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount  l_subAccount = null;
            try
            {
                SubAccountTypeEnum l_subAccountType = null;

                l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;

                l_subAccount = l_accountManager.getSubAccount(l_lngAccountId,l_subAccountType);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName());
            }
            return l_subAccount;
        }
    }

    private class WEB3BondOrderUnitForTest extends WEB3BondOrderUnit{

		public WEB3BondOrderUnitForTest(long l_lngOrderUnitId) throws DataQueryException, DataNetworkException
		{
			super(l_lngOrderUnitId);
		}
	    public String getDealDiv() throws WEB3BaseException
	    {
	    	n++;
	    	log.debug("n = "+n);
	    	if (n == 1 || n == 2 || n==7 || n ==8)
			{
				return "1";
			}
	    	else
			{
				return "2";
			}
	    }
    	
    }

}
@
