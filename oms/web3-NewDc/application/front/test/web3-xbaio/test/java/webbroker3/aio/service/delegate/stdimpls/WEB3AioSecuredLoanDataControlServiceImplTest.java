head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.37.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSecuredLoanDataControlServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3SLSortKey;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.CommSerialNumbersParams;
import webbroker3.gentrade.data.CommSerialNumbersRow;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioSecuredLoanDataControlServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecuredLoanDataControlServiceImpl.class);

    WEB3AioSecuredLoanDataControlServiceImpl l_impl = new WEB3AioSecuredLoanDataControlServiceImpl();

    public WEB3AioSecuredLoanDataControlServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.deleteAllRows();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
//        this.deleteAllRows();
    }

    public void testInsertStockSecuredLoan_T01()
    {
        final String STR_METHOD_NAME = "testInsertStockSecuredLoan_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_impl.insertStockSecuredLoan(null, null);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testInsertStockSecuredLoan_T02()
    {
        final String STR_METHOD_NAME = "testInsertStockSecuredLoan_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.insertAllRows();
            
            //StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456l));

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountManager.getMainAccount(123456789l);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            MainAccountParams l_params = new MainAccountParams(l_mainAccountRow);
            
            Timestamp l_tsTemp =  GtlUtils.getTradingSystem().getSystemTimestamp();
            l_impl.insertStockSecuredLoan("123456", l_params);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRow = l_queryProcessor.doFindAllQuery(StockSecuredLoanParams.TYPE);
            assertEquals(1, l_lisRow.size());
            StockSecuredLoanRow l_resultRow = (StockSecuredLoanRow)l_lisRow.get(0);
            assertEquals("123456", l_resultRow.getStockLoanAccountCode());
            assertEquals(l_params.getAccountCode(), l_resultRow.getAccountCode());
            assertEquals(l_params.getInstitutionCode(), l_resultRow.getInstitutionCode());
            assertEquals(l_params.getBranchCode(), l_resultRow.getBranchCode());
            assertEquals(l_params.getAccountId(), l_resultRow.getAccountId());
            assertEquals("0", l_resultRow.getAccountOpenStatus());
            assertNull(l_resultRow.getAccountOpenDate());
            assertNull(l_resultRow.getOrderDataReceptionDate());
            assertNull(l_resultRow.getCancelDataReceptionDate());
            assertNull(l_resultRow.getCloseDate());
            assertEquals(l_params.getYellowCustomer(), l_resultRow.getYCustomerData());
            assertEquals(l_params.getExaminLockFlag(), l_resultRow.getExaminLockFlag());
            assertEquals(l_params.getBranchLock(), l_resultRow.getBranchLock());
            assertEquals(l_params.getMngLockFlag(), l_resultRow.getMngLockFlag());
            assertEquals(l_params.getMngLockFlagAdvance(), l_resultRow.getMngLockFlagAdvance());
            assertEquals(l_params.getMngLockFlagUnpayMargin(), l_resultRow.getMngLockFlagUnpayMargin());
            assertEquals(l_params.getMngLockFlagShortSecurity(), l_resultRow.getMngLockFlagShortSecurity());
            assertEquals(l_params.getMngLockFlagUnsubstitDepo(), l_resultRow.getMngLockFlagUnsubstitDepo());
            assertEquals("123456", l_resultRow.getLastUpdater());

        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetStockSecuredLoanAccInfo_T01()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanAccInfo_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MainAccountParams
            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountRow);

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAppliDate(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setAppliDate(GtlUtils.getSystemTimestamp());
            l_stockSecuredLoanParams.setStockLoanAccountCode("456789123");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
            
            //333333333333
            l_stockSecuredLoanParams.setAppliDate(GtlUtils.getSystemTimestamp());
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123457");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
            
            Date l_datTemp = new Date(2007-1900,10,23);
            List l_lisTemp =
                l_impl.getStockSecuredLoanAccInfo(123456789l, GtlUtils.getSystemTimestamp());
            assertEquals(3, l_lisTemp.size());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanAccInfo_T02()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanAccInfo_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.insertAllRows();
            
            Date l_datTemp = new Date(2007-1900,8,23);
            List l_lisTemp =
                l_impl.getStockSecuredLoanAccInfo(123456789l, new Timestamp(l_datTemp.getTime()));
            assertNull(l_lisTemp);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanAccInfo_T03()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanAccInfo_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.insertAllRows();
            
            List l_lisTemp =
                l_impl.getStockSecuredLoanAccInfo(123456l, GtlUtils.getSystemTimestamp());
            assertNull(l_lisTemp);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testGetStockSecuredLoanList_T01()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = null;
            l_stockSecuredLoans[3] = null;
            l_stockSecuredLoans[4] = null;
            l_stockSecuredLoans[5] = null;
            l_stockSecuredLoans[6] = null;
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T02()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;
            l_stockSecuredLoans[2] = new Long(123l);
            l_stockSecuredLoans[3] = null;
            l_stockSecuredLoans[4] = null;
            l_stockSecuredLoans[5] = null;
            l_stockSecuredLoans[6] = null;
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("123456", l_resultParams[0].getAccountCode());
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testGetStockSecuredLoanList_T03()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;
            l_stockSecuredLoans[2] = null;
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = null;
            l_stockSecuredLoans[5] = null;
            l_stockSecuredLoans[6] = null;
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    
    public void testGetStockSecuredLoanList_T04()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;
            l_stockSecuredLoans[2] = null;
            l_stockSecuredLoans[3] = null;
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = null;
            l_stockSecuredLoans[6] = null;
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testGetStockSecuredLoanList_T05()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare( new Date(2006-1900,1,1), l_resultParams[0].getAppliDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetStockSecuredLoanList_T06()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T06()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(l_resultParams[0].getAppliDate(), GtlUtils.getSystemTimestamp()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T07()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T07()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(l_resultParams[0].getAppliDate(), GtlUtils.getSystemTimestamp()) > 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getAppliDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T08()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T08()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getAccountOpenDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T09()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T09()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getAccountOpenDate()) < 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T10()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T10()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getAccountOpenDate()) < 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getAccountOpenDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T11()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T11()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getOrderDataReceptionDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T12()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T12()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getOrderDataReceptionDate()) < 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetStockSecuredLoanList_T13()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T13()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = null;
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getOrderDataReceptionDate()) < 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getOrderDataReceptionDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T14()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T14()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = null;
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getCancelDataReceptionDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T15()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T15()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getCancelDataReceptionDate()) < 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetStockSecuredLoanList_T16()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T16()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = null;
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getCancelDataReceptionDate()) < 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getCancelDataReceptionDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T17()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T17()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = null;
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getCloseDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T18()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T18()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getCloseDate()) < 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T19()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T19()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = null;//64
            l_stockSecuredLoans[2] = null;//123456
            l_stockSecuredLoans[3] = null;//789
            l_stockSecuredLoans[4] = null;//2
            l_stockSecuredLoans[5] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = null;//new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = null;//GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            if (WEB3DateUtility.compare(GtlUtils.getSystemTimestamp(), l_resultParams[0].getCloseDate()) < 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getCloseDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T20()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T20()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = "123456";
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();

            l_stockSecuredLoans[15] = null;

            this.insertAllRows();

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            assertEquals("123456", l_resultParams[0].getAccountCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());
            if (WEB3DateUtility.compare(l_resultParams[0].getAppliDate(), GtlUtils.getSystemTimestamp()) > 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getAppliDate()) > 0)
            {
                fail();
            }
            if (WEB3DateUtility.compare(l_resultParams[0].getAccountOpenDate(), GtlUtils.getSystemTimestamp()) > 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getAccountOpenDate()) > 0)
            {
                fail();
            }
            if (WEB3DateUtility.compare(l_resultParams[0].getOrderDataReceptionDate(), GtlUtils.getSystemTimestamp()) > 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getOrderDataReceptionDate()) > 0)
            {
                fail();
            }
            if (WEB3DateUtility.compare(l_resultParams[0].getCancelDataReceptionDate(), GtlUtils.getSystemTimestamp()) > 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getCancelDataReceptionDate()) > 0)
            {
                fail();
            }
            if (WEB3DateUtility.compare(l_resultParams[0].getCloseDate(), GtlUtils.getSystemTimestamp()) > 0
                || WEB3DateUtility.compare(new Date(2006-1900,1,1), l_resultParams[0].getCloseDate()) > 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T21()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T21()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = null;//"123456";
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            
            WEB3SLSortKey[] l_sortKey = new WEB3SLSortKey[1];
            l_sortKey[0] = new WEB3SLSortKey();
            l_sortKey[0].keyItem = "accountCode";
            l_sortKey[0].ascDesc = "A";
            l_stockSecuredLoans[15] = l_sortKey;

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123456");
            l_stockSecuredLoanParams.setAccountCode("123455");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            //333333333333
            l_stockSecuredLoanParams.setStockLoanAccountCode("789456789");
            l_stockSecuredLoanParams.setAccountCode("123454");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
            
            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());
            assertEquals("123454", l_resultParams[0].getAccountCode());
            assertEquals("123455", l_resultParams[1].getAccountCode());
            assertEquals("123456", l_resultParams[2].getAccountCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetStockSecuredLoanList_T22()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T22()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = null;//"123456";
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            
            WEB3SLSortKey[] l_sortKey = new WEB3SLSortKey[1];
            l_sortKey[0] = new WEB3SLSortKey();
            l_sortKey[0].keyItem = "stockLoanAccount";
            l_sortKey[0].ascDesc = "D";
            l_stockSecuredLoans[15] = l_sortKey;

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123456");
            l_stockSecuredLoanParams.setAccountCode("123455");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            //333333333333
            l_stockSecuredLoanParams.setStockLoanAccountCode("789456789");
            l_stockSecuredLoanParams.setAccountCode("123454");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());
            assertEquals("789456789", l_resultParams[0].getStockLoanAccountCode());
            assertEquals("789456132", l_resultParams[1].getStockLoanAccountCode());
            assertEquals("789123456", l_resultParams[2].getStockLoanAccountCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T23()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T23()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = "123456";
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            
            WEB3SLSortKey[] l_sortKey = new WEB3SLSortKey[1];
            l_sortKey[0] = new WEB3SLSortKey();
            l_sortKey[0].keyItem = "accountCode";
            l_sortKey[0].ascDesc = "D";
            l_stockSecuredLoans[15] = l_sortKey;

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,12));
            l_stockSecuredLoanParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123456");
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,14));
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            //333333333333
            l_stockSecuredLoanParams.setStockLoanAccountCode("789456789");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,13));
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());
//            assertEquals(new Date(2007-1900,8,12), l_resultParams[0].getAppliDate());
//            assertEquals(new Date(2007-1900,7,13), l_resultParams[1].getAppliDate());
//            assertEquals(new Date(2007-1900,7,12), l_resultParams[2].getAppliDate());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetStockSecuredLoanList_T24()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T24()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = null;//"123456";
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            
            WEB3SLSortKey[] l_sortKey = new WEB3SLSortKey[3];
            l_sortKey[0] = new WEB3SLSortKey();
            l_sortKey[0].keyItem = "accountCode";
            l_sortKey[0].ascDesc = "A";
            l_sortKey[1] = new WEB3SLSortKey();
            l_sortKey[1].keyItem = "stockLoanAccount";
            l_sortKey[1].ascDesc = "A";
            l_sortKey[2] = new WEB3SLSortKey();
            l_sortKey[2].keyItem = "applyDateFrom";
            l_sortKey[2].ascDesc = "D";
            l_stockSecuredLoans[15] = l_sortKey;

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,12));
            l_stockSecuredLoanParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123456");
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,14));
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            //333333333333
            l_stockSecuredLoanParams.setStockLoanAccountCode("789456789");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,13));
            l_stockSecuredLoanParams.setAccountCode("123454");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());
            
            assertEquals("123454", l_resultParams[0].getAccountCode());
            assertEquals("123456", l_resultParams[1].getAccountCode());
            assertEquals("123456", l_resultParams[2].getAccountCode());
            
            assertEquals("789456789", l_resultParams[0].getStockLoanAccountCode());
            assertEquals("789123456", l_resultParams[1].getStockLoanAccountCode());
            assertEquals("789456132", l_resultParams[2].getStockLoanAccountCode());
            
            assertEquals(new Date(2007-1900,7,13), l_resultParams[0].getAppliDate());
            assertEquals(new Date(2007-1900,7,14), l_resultParams[1].getAppliDate());
            assertEquals(new Date(2007-1900,7,12), l_resultParams[2].getAppliDate());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testGetStockSecuredLoanList_T25()
    {
        final String STR_METHOD_NAME = "testGetStockSecuredLoanList_T25()";
        log.entering(STR_METHOD_NAME);
        try
        {
            Object[] l_stockSecuredLoans = new Object[16];
            l_stockSecuredLoans[0] = "0D";
            l_stockSecuredLoans[1] = "64";
            l_stockSecuredLoans[2] = null;//"123456";
            l_stockSecuredLoans[3] = "789";
            l_stockSecuredLoans[4] = "2";
            l_stockSecuredLoans[5] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[6] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[7] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[8] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[9] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[10] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[11] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[12] = GtlUtils.getSystemTimestamp();
            l_stockSecuredLoans[13] = new Date(2006-1900,1,1);
            l_stockSecuredLoans[14] = GtlUtils.getSystemTimestamp();
            
            WEB3SLSortKey[] l_sortKey = new WEB3SLSortKey[1];
            l_sortKey[0] = new WEB3SLSortKey();
            l_sortKey[0].keyItem = "accountCode";
            l_sortKey[0].ascDesc = "A";
            l_stockSecuredLoans[15] = l_sortKey;

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,12));
            l_stockSecuredLoanParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123456");
            l_stockSecuredLoanParams.setAccountCode("123456");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,14));
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            //333333333333
            l_stockSecuredLoanParams.setStockLoanAccountCode("789456789");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,13));
            l_stockSecuredLoanParams.setAccountCode("123454");
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);

            StockSecuredLoanParams[] l_resultParams =
                l_impl.getStockSecuredLoanList(l_stockSecuredLoans);
            assertEquals(3, l_resultParams.length);
            assertEquals("0D", l_resultParams[0].getInstitutionCode());
            assertEquals("64", l_resultParams[0].getBranchCode());
            assertEquals("2", l_resultParams[0].getAccountOpenStatus());

        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testUpdateCommSerialNumbers_T01()
    {
        final String STR_METHOD_NAME = " testUpdateCommSerialNumbers_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            CommSerialNumbersParams l_numbersParams= this.insertCommSerialNumbersParams();
            TestDBUtility.insertWithDel(l_numbersParams);
            l_impl.updateCommSerialNumbers("0D", "jiddk", "789456");
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(
                CommSerialNumbersParams.TYPE,
                " institution_code = ? and serial_number_name = ? ",
                new Object[]{"0D", "jiddk"});
            assertEquals(1, l_lisResult.size());
            assertEquals("789456", ((CommSerialNumbersRow)l_lisResult.get(0)).getSerialNumber());
            int temp = WEB3DateUtility.compareToHour(
                GtlUtils.getSystemTimestamp(),
                ((CommSerialNumbersRow)l_lisResult.get(0)).getLastUpdatedTimestamp());
            if (temp != 0)
            {
                fail();
            }
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void insertAllRows()
    {
        try
        {
            //MainAccountParams
            MainAccountParams l_mainAccountRow = TestDBUtility.getMainAccountRow();
            l_mainAccountRow.setAccountId(123456789l);
            l_mainAccountRow.setSecuredLoanSecAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountRow);

            //StockSecuredLoanParams
            StockSecuredLoanParams l_stockSecuredLoanParams = this.insertStockSecuredLoanRow();
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,12));
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //22222222
            l_stockSecuredLoanParams.setStockLoanAccountCode("789123456");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,14));
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
            
            //333333333333
            l_stockSecuredLoanParams.setStockLoanAccountCode("789456789");
            l_stockSecuredLoanParams.setAppliDate(new Date(2007-1900,7,13));
            l_queryProcessor.doInsertQuery(l_stockSecuredLoanParams);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }


    public void deleteAllRows()
    {
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);

            //StockSecuredLoanRow
            TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
            
            //CommSerialNumbersParams
            TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

    }

    public StockSecuredLoanParams insertStockSecuredLoanRow()
    {
        StockSecuredLoanParams l_params = new StockSecuredLoanParams();
        //ストックローン口座番号   stock_loan_account_code     VARCHAR2    10  NotNull
        l_params.setStockLoanAccountCode("789456132");
        //口座ID  account_id  NUMBER  18  Notnull
        l_params.setAccountId(123456789l);
        //証券会社コード   institution_code    VARCHAR2    3   NotNull
        l_params.setInstitutionCode("0D");
        //部店コード     branch_code     VARCHAR2    3   NotNull
        l_params.setBranchCode("64");
        //口座コード     account_code    VARCHAR2    7   NotNull
        l_params.setAccountCode("123456");
        //開設状況  account_open_status     VARCHAR2    1   Null
        l_params.setAccountOpenStatus("2");
        //申込日時  appli_date  DATE    Null
        l_params.setAppliDate(new Date(2007-1900,7,12));
        //開設日   account_open_date   DATE    Null
        l_params.setAccountOpenDate(new Date(2007-1900,7,12));
        //成約データ受信日時     order_data_reception_date   DATE    Null
        l_params.setOrderDataReceptionDate(new Date(2007-1900,7,12));
        //解約データ受信日時     cancel_data_reception_date  DATE    Null
        l_params.setCancelDataReceptionDate(new Date(2007-1900,7,12));
        //閉鎖日   close_date  DATE    Null
        l_params.setCloseDate(new Date(2007-1900,7,12));
        //申込時Y客情報   y_customer_data     VARCHAR2    1   Null
        //申込時ロック客情報（考査ロック）  examin_lock_flag    VARCHAR2    1   Null
        //申込時ロック客情報（支店ロック）  branch_lock     VARCHAR2    1   Null
        //申込時ロック客情報（管理ロック）  mng_lock_flag   VARCHAR2    1   Null
        //申込時ロック客情報（管理ロック理由・立替金）    mng_lock_flag_advance   NUMBER  1   Null
        //申込時ロック客情報（管理ロック理由・保証金未入）  mng_lock_flag_unpay_margin  NUMBER  1   Null
        //申込時ロック客情報（管理ロック理由・適格担保不足）     mng_lock_flag_short_security    NUMBER  1   Null
        //申込時ロック客情報（管理ロック理由・預り証長期未差換）   mng_lock_flag_unsubstit_depo    NUMBER  1   Null
        //更新者コード    last_updater    VARCHAR2    20  Null
        //作成日時  created_timestamp   DATE    NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日時  last_updated_timestamp  DATE    NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return l_params;
    }

    public CommSerialNumbersParams insertCommSerialNumbersParams()
    {
        CommSerialNumbersParams l_params = new CommSerialNumbersParams();
        try
        {
            
            //証券会社コード   institution_code VARCHAR2   3 Notnull
            l_params.setInstitutionCode("0D");
            //採番項目名     serial_number_name VARCHAR2     32 Notnull
            l_params.setSerialNumberName("jiddk");
            //採番コード     serial_number VARCHAR2  18 Notnull
            l_params.setSerialNumber("123456");
            //作成日時  created_timestamp DATE      NotNull
            l_params.setCreatedTimestamp(new Date(2007-1900, 2, 23));
            //更新日時  last_updated_timestamp DATE     NotNull
            l_params.setLastUpdatedTimestamp(new Date(2007-1900, 2, 23));
           
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        return l_params;
    }
    
}
@
