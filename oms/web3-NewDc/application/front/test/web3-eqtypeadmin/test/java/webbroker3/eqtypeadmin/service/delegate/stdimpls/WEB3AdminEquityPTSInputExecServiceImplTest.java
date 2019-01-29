head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSInputExecServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.data.HostEqtypeOrderAcceptRow;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.PtsOrderexecutionEndParams;
import webbroker3.gentrade.data.PtsOrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSInputExecServiceImplTest extends TestBaseForMock
{

    public WEB3AdminEquityPTSInputExecServiceImplTest(String name)
    {
        super(name);
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputExecServiceImplTest.class);
    
    protected void setUp() throws Exception
    {
        super.setUp();
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderExecutionParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptParams.TYPE);
            
            
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderExecutionParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderAcceptParams.TYPE);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }

    /**
     * insert出来通知
     */
    public void test_insertExecInform_0001()
    {
        final String STR_METHOD_NAME = "test_insertExecInform_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("382");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(33L, "381", "2512246");
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            l_serviceImpl.insertExecNotify(l_mainAccount, l_expectAdministrator, l_impl, l_request);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");

            Object[] l_objWheres = {"AI811"};

            List l_lisHostEquityOrderExecNotifyParams = new ArrayList();
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisHostEquityOrderExecNotifyParams = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyParams.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
            HostEquityOrderExecNotifyRow l_row =
                (HostEquityOrderExecNotifyRow)l_lisHostEquityOrderExecNotifyParams.get(0);
            assertEquals("0D", l_row.getInstitutionCode());
            assertEquals("381", l_row.getBranchCode());
            assertEquals("2512246", l_row.getAccountCode());
            assertNull(l_row.getTraderCode());
            assertEquals("256", l_row.getOrderRequestNumber());
            assertEquals("2.0", "" + l_row.getExecQuantity());
            assertEquals("231.0", "" + l_row.getExecPrice());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_row.getExecTimestamp()));
            assertEquals("2", l_row.getDealedType());
            assertNull(l_row.getVirtualServerNumberMarket());
            assertNull(l_row.getNoticeType());
            assertEquals("0", "" + l_row.getNoticeNumber());
            assertEquals("0", "" + l_row.getExecNumber());
            assertEquals("0", l_row.getStatus());
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_row.getCreatedTimestamp()));
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_row.getLastUpdatedTimestamp()));
            assertEquals("330001", l_row.getLastUpdater());

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * insert注文受付
     */
    public void test_insertOrderAccept_0001()
    {
        final String STR_METHOD_NAME = "test_insertOrderAccept_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("382");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(33L, "381", "2512246");
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            l_serviceImpl.insertOrderAccept(l_mainAccount, l_impl);
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");

            Object[] l_objWheres = {"AI80A"};

            List l_lisHostEquityOrderExecNotifyParams = new ArrayList();
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisHostEquityOrderExecNotifyParams = l_processor.doFindAllQuery(
                HostEqtypeOrderAcceptParams.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
            HostEqtypeOrderAcceptRow l_row =
                (HostEqtypeOrderAcceptRow)l_lisHostEquityOrderExecNotifyParams.get(0);
            assertEquals("0D", l_row.getInstitutionCode());
            assertEquals("381", l_row.getBranchCode());
            assertEquals("2512246", l_row.getAccountCode());
            assertNull(l_row.getTraderCode());
            assertEquals("256", l_row.getOrderRequestNumber());
            assertEquals("1", l_row.getAcceptStatus());
            assertEquals("1", l_row.getAcceptStatus());
            assertNull(l_row.getErrorMessage());
            assertEquals("2", l_row.getSubmitOrderRouteDiv());
            assertNull(l_row.getVirtualServerNumberMarket());
            assertNull(l_row.getNoticeType());
            assertEquals("0", "" + l_row.getNoticeNumber());
            assertNull(l_row.getAcceptNumber());
            assertEquals("0", l_row.getStatus());
            String str1 = WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd");
            String str2 = WEB3DateUtility.formatDate(l_row.getCreatedTimestamp(), "yyyyMMdd");
            assertEquals(str1,str2);
            String str3 = WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd");
            String str4 = WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd");
            assertEquals(str3,str4);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get約定履歴（確認・完了）
     */
    public void test_getExecutedHistory_0001()
    {
        final String STR_METHOD_NAME = "test_insertOrderAccept_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("382");
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(33L, "381", "2512246");
              
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            WEB3AdminEquityPTSExecHistory l_history0 = new WEB3AdminEquityPTSExecHistory();
            WEB3AdminEquityPTSExecHistory l_history1 = new WEB3AdminEquityPTSExecHistory();
            WEB3AdminEquityPTSExecHistory l_history2 = new WEB3AdminEquityPTSExecHistory();
            
            WEB3AdminEquityPTSExecHistory[] l_histors = new WEB3AdminEquityPTSExecHistory[]{l_history0, l_history1, l_history2};
            
            WEB3AdminEquityPTSExecHistory[] l_ptsExecHistory = l_serviceImpl.getExecutedHistory(l_expectAdministrator, l_histors, l_request);
            
            assertFalse(l_ptsExecHistory[3].cancelFlag);
            assertEquals("0", "" + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),l_ptsExecHistory[3].executionTimeStamp));
            assertEquals("2", l_ptsExecHistory[3].execQuantity);
            assertEquals("231", l_ptsExecHistory[3].execPrice);
            assertEquals("2", l_ptsExecHistory[3].inputExecCancelExecDiv);
            assertEquals("330001", l_ptsExecHistory[3].updaterCode);
            assertEquals("0", l_ptsExecHistory[3].inputExecCancelExecProcDiv);
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能約定 
     * 注文単位.市場から確認済みの数量 == null
     * 注文単位.注文数量の値を使用する
     * 引数.入力約定株数 ＞ 未約定数量
     * 「入力した約定株数が未約定株数を超えている」の例外をスローする。
     */
    public void test_validateInputExecPossibilityExecuted_0001()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityExecuted_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
//        l_eqtypeOrderUnitParams.setConfirmedQuantity(89);
        //注文数量
        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            l_serviceImpl.validateInputExecPossibilityExecuted(l_impl,"2", Calendar.getInstance().getTime());
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02987, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能約定 
     * 注文単位.市場から確認済みの数量 != null
     * 未約定数量 ＝ 注文単位.市場から確認済みの数量 － 注文単位.約定数量 
     * 引数.入力約定株数 ＞ 未約定数量
     * 「入力した約定株数が未約定株数を超えている」の例外をスローする。
     */
    public void test_validateInputExecPossibilityExecuted_0002()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityExecuted_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(86);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            l_serviceImpl.validateInputExecPossibilityExecuted(l_impl,"2", Calendar.getInstance().getTime());
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02987, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能約定 
     * 注文単位.市場から確認済みの数量 != null
     * 未約定数量 ＝ 注文単位.市場から確認済みの数量 － 注文単位.約定数量 
     * 引数.入力約定株数 = 未約定数量
     * 
     * 引数.入力約定日時 ＜ 注文単位.注文日時 の場合、
     * 「入力した約定日時が、注文日時より過去日時」の例外をスローする。 
     */
    public void test_validateInputExecPossibilityExecuted_0003()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityExecuted_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        //市場から確認済みの数量
        l_eqtypeOrderUnitParams.setConfirmedQuantity(87);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20180122","yyyyMMdd"));
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            l_serviceImpl.validateInputExecPossibilityExecuted(l_impl,"2", Calendar.getInstance().getTime());
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02984, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能約定 
     * 注文単位.市場から確認済みの数量 != null
     * 未約定数量 ＝ 注文単位.市場から確認済みの数量 － 注文単位.約定数量 
     * 引数.入力約定株数 < 未約定数量
     * 
     * 引数.入力約定日時 = 注文単位.注文日時 の場合、
     * 
     */
    public void test_validateInputExecPossibilityExecuted_0004()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityExecuted_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(87);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        l_eqtypeOrderUnitParams.setReceivedDateTime(Calendar.getInstance().getTime());
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            l_serviceImpl.validateInputExecPossibilityExecuted(l_impl,"0", Calendar.getInstance().getTime());
            assertTrue(true);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能約定 
     * 注文単位.市場から確認済みの数量 != null
     * 未約定数量 ＝ 注文単位.市場から確認済みの数量 － 注文単位.約定数量 
     * 引数.入力約定株数 < 未約定数量
     * 
     * 引数.入力約定日時 > 注文単位.注文日時 の場合、
     * 
     */
    public void test_validateInputExecPossibilityExecuted_0005()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityExecuted_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(87);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            l_serviceImpl.validateInputExecPossibilityExecuted(l_impl,"0", Calendar.getInstance().getTime());
            assertTrue(true);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能注文
     * PTS市場でない場合(市場.isPTS市場() == false)、 
     * 「PTS市場でない場合は出来取消不可」の例外をスローする。
     */
    public void test_validateInputExecPossibilityOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityOrder_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(87);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_serviceImpl.validateInputExecPossibilityOrder(l_impl);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02988, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能注文
     * 注文有効状態がCLOSEDの場合、 
     * 「注文状態が出来入力不可」の例外をスローする。 
     */
    public void test_validateInputExecPossibilityOrder_0002()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityOrder_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(87);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
        //注文有効状態
        l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_serviceImpl.validateInputExecPossibilityOrder(l_impl);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02983, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力可能注文
     * 注文有効状態がOPENの場合、 
     */
    public void test_validateInputExecPossibilityOrder_0003()
    {
        final String STR_METHOD_NAME = "test_validateInputExecPossibilityOrder_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        PersistenceManagerImpl pm = new PersistenceManagerImpl();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
        l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
        l_eqtypeOrderUnitParams.setConfirmedQuantity(87);
        //注文数量
//        l_eqtypeOrderUnitParams.setQuantity(85);
        //約定数量
        l_eqtypeOrderUnitParams.setExecutedQuantity(85);
        l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20080122","yyyyMMdd"));
        //注文有効状態
        l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
        try
        {
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_serviceImpl.validateInputExecPossibilityOrder(l_impl);
            assertTrue(true);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set取引カレンダコンテキスト
     */
    public void test_setTradingClendarContext_0001()
    {
        final String STR_METHOD_NAME = "test_setTradingClendarContext_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(WEB3DateUtility.getDate("20080130","yyyyMMdd").getTime()));
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            l_serviceImpl.setTradingClendarContext(l_impl);
            
            WEB3GentradeTradingClendarContext l_context = (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertNull(l_context.getOrderAcceptTransaction());
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set取引カレンダコンテキスト
     * ThreadLocalSystemAttributesRegistry.setAttribute( )抛出異常信息
     */
    public void test_setTradingClendarContext_0002()
    {
        final String STR_METHOD_NAME = "test_setTradingClendarContext_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(WEB3DateUtility.getDate("20080130","yyyyMMdd").getTime()));
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            l_serviceImpl.setTradingClendarContext(l_impl);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate管理者権限
     * 管理者.validate権限()
     * WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void test_validateAdminAuthorities_0001()
    {
        final String STR_METHOD_NAME = "test_validateAdminAuthorities_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_serviceImpl.validateAdminAuthorities(l_expectAdministrator, 33381L);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate管理者権限
     * 管理者.validate部店権限()
     * WEB3ErrorCatalog.BUSINESS_ERROR_01074
     */
    public void test_validateAdminAuthorities_0002()
    {
        final String STR_METHOD_NAME = "test_validateAdminAuthorities_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("382");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0109");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            l_serviceImpl.validateAdminAuthorities(l_expectAdministrator, 33381L);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate管理者権限
     * 正常結束
     */
    public void test_validateAdminAuthorities_0003()
    {
        final String STR_METHOD_NAME = "test_validateAdminAuthorities_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("381");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0109");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            l_serviceImpl.validateAdminAuthorities(l_expectAdministrator, 33381L);
            assertTrue(true);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * execute
     * 管理者・株式（PTS）出来入力リクエストの場合
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
        
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * execute
     * 管理者・株式（PTS）出来入力確認リクエストの場合
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
        
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * execute
     * 管理者・株式（PTS）出来入力完了リクエストの場合
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * execute
     * 管理者・株式（PTS）出来入力リクエストの場合   
     * 管理者・株式（PTS）出来入力確認リクエストの場合
     * 管理者・株式（PTS）出来入力完了リクエストの場合  
     * 以上以外的場合
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityManualLapseConfirmRequest l_request = new WEB3AdminEquityManualLapseConfirmRequest();
        
        try
        {
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * execute
     * l_request = null
     */
    public void test_execute_0005()
    {
        final String STR_METHOD_NAME = "test_execute_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        
        try
        {
            l_serviceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get入力画面
     * 管理者・株式（PTS）出来入力リクエスト.validate()例外をスローする
     * 管理者・株式（PTS）出来入力リクエスト注文ID=null 
     * 「BUSINESS_ERROR_00600」の例外をスローする
     */
    public void test_getInputScreen_0001()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
        try
        {
            l_serviceImpl.getInputScreen(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get入力画面
     * validate管理者権限
     * 
     * 「BUSINESS_ERROR_01056」の例外をスローする
     */
    public void test_getInputScreen_0002()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0002";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
        
        //注文ID
        l_request.orderId = "1";
        try
        {
//            LoginInfoImpl l_LoginInfo = new LoginInfoImpl();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginInfo",
//                 new Class[] {},
//                 l_LoginInfo);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_serviceImpl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get入力画面
     * validate出来入力出来取消可能時間帯
     * 
     * 「SYSTEM_ERROR_80003」の例外をスローする
     */
    public void test_getInputScreen_0003()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0003";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
        
        //注文ID
        l_request.orderId = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setValue("1");
            l_marketPreferencesRow.setName("equity.pts.market.div");
            TestDBUtility.insertWithDel(l_marketPreferencesRow);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080127","yyyyMMdd").getTime()));
            
            l_serviceImpl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03015, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get入力画面
     * validate出来入力可能注文
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02988」の例外をスローする
     */
    public void test_getInputScreen_0004()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
        
        //注文ID
        l_request.orderId = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080130","yyyyMMdd").getTime()));
            
            l_serviceImpl.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02988, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get入力画面
     */
    public void test_getInputScreen_0005()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecInputRequest l_request = new WEB3AdminEquityPTSInputExecInputRequest();
        
        //注文ID
        l_request.orderId = "1";
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("12");
            
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");
            
            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("12");
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20070128", "yyyyMMdd"));

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            WEB3AdminEquityPTSInputExecInputResponse l_response = l_serviceImpl.getInputScreen(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;
            
            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);
            
            assertFalse(l_execHistory.cancelFlag);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力
     * 管理者・株式（PTS）出来入力確認リクエスト.validate()例外をスローする
     * 管理者・株式（PTS）出来入力確認リクエスト約定株数=null 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02989」の例外をスローする
     */
    public void test_validateInputExec_0001()
    {
        final String STR_METHOD_NAME = "test_validateInputExec_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
        try
        {
            l_serviceImpl.validateInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力
     * validate管理者権限
     * 
     * 「BUSINESS_ERROR_01056」の例外をスローする
     */
    public void test_validateInputExec_0002()
    {
        final String STR_METHOD_NAME = "test_validateInputExec_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
//            LoginInfoImpl l_LoginInfo = new LoginInfoImpl();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginInfo",
//                 new Class[] {},
//                 l_LoginInfo);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_serviceImpl.validateInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力
     * validate出来入力出来取消可能時間帯
     * 
     * 「SYSTEM_ERROR_80003」の例外をスローする
     */
    public void test_validateInputExec_0003()
    {
        final String STR_METHOD_NAME = "test_validateInputExec_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setValue("1");
            l_marketPreferencesRow.setName("equity.pts.market.div");
            TestDBUtility.insertWithDel(l_marketPreferencesRow);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080127","yyyyMMdd").getTime()));
            
            l_serviceImpl.validateInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03015, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力
     * validate出来入力可能注文
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02988」の例外をスローする
     */
    public void test_validateInputExec_0004()
    {
        final String STR_METHOD_NAME = "test_validateInputExec_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080130","yyyyMMdd").getTime()));
            
            l_serviceImpl.validateInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02988, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力
     */
    public void test_validateInputExec_0005()
    {
        final String STR_METHOD_NAME = "test_validateInputExec_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecConfirmRequest l_request = new WEB3AdminEquityPTSInputExecConfirmRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");
            
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");
            
            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20070128", "yyyyMMdd"));
            
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            
            WEB3AdminEquityPTSInputExecConfirmResponse l_response = l_serviceImpl.validateInputExec(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;
            
            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);
            
            assertFalse(l_execHistory.cancelFlag);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit出来入力
     * 管理者・株式（PTS）出来入力完了リクエスト.validate()例外をスローする
     * 管理者・株式（PTS）出来入力完了リクエスト約定株数=null 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02989」の例外をスローする
     */
    public void test_submitInputExec_0001()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        try
        {
            l_serviceImpl.submitInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit出来入力
     * validate管理者権限
     * 
     * 「BUSINESS_ERROR_01056」の例外をスローする
     */
    public void test_submitInputExec_0002()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0002";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_serviceImpl.submitInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit出来入力
     * validate取引パスワード
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_00009」の例外をスローする
     */
    public void test_submitInputExec_0003()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0003";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        
        l_request.password = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", false);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080127","yyyyMMdd").getTime()));
            
            l_serviceImpl.submitInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * submit出来入力
     * validate出来入力出来取消可能時間帯
     * 
     * 「SYSTEM_ERROR_80003」の例外をスローする
     */
    public void test_submitInputExec_0004()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0004";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        
        l_request.password = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
//            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setValue("1");
            l_marketPreferencesRow.setName("equity.pts.market.div");
            TestDBUtility.insertWithDel(l_marketPreferencesRow);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080127","yyyyMMdd").getTime()));
            
            l_serviceImpl.submitInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03015, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * submit出来入力
     * validate出来入力可能注文
     * 
     * 「 WEB3ErrorCatalog.BUSINESS_ERROR_02988」の例外をスローする
     */
    public void test_submitInputExec_0005()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            l_eqtypeOrderUnitParams.setAccountId(333812512203L);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080130","yyyyMMdd").getTime()));
            
            l_serviceImpl.submitInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_02988, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit出来入力
     * validate出来入力可能約定
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02987」の例外をスローする。
     */
    public void test_submitInputExec_0006()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "11100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");
            
            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            
            
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            
            l_serviceImpl.submitInputExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02987, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit出来入力
     * 注文が受付未済の場合（注文単位.市場から確認済みの数量 == null）
     */
    public void test_submitInputExec_0007()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0007()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");
            
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");
            
            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20070128", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            
            WEB3AdminEquityPTSInputExecCompleteResponse l_response = l_serviceImpl.submitInputExec(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;
            
            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);
            
            assertFalse(l_execHistory.cancelFlag);
            
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");

            Object[] l_objWheres = {"AI80A"};

            List l_lisHostEquityOrderExecNotifyParams = new ArrayList();
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisHostEquityOrderExecNotifyParams = l_processor.doFindAllQuery(
                HostEqtypeOrderAcceptParams.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
            HostEqtypeOrderAcceptRow l_row =
                (HostEqtypeOrderAcceptRow)l_lisHostEquityOrderExecNotifyParams.get(0);
            assertEquals("0D", l_row.getInstitutionCode());
            assertEquals("381", l_row.getBranchCode());
            assertEquals("2512246", l_row.getAccountCode());
            assertNull(l_row.getTraderCode());
            assertEquals("256", l_row.getOrderRequestNumber());
            assertEquals("1", l_row.getAcceptStatus());
            assertEquals("1", l_row.getAcceptStatus());
            assertNull(l_row.getErrorMessage());
            assertEquals("2", l_row.getSubmitOrderRouteDiv());
            assertNull(l_row.getVirtualServerNumberMarket());
            assertNull(l_row.getNoticeType());
            assertEquals("0", "" + l_row.getNoticeNumber());
            assertNull(l_row.getAcceptNumber());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit出来入力
     * 注文が受付未済の場合（注文単位.市場から確認済みの数量 != null）
     */
    public void test_submitInputExec_0008()
    {
        final String STR_METHOD_NAME = "test_submitInputExec_0008()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSInputExecServiceImpl l_serviceImpl = new WEB3AdminEquityPTSInputExecServiceImpl();
        WEB3AdminEquityPTSInputExecCompleteRequest l_request = new WEB3AdminEquityPTSInputExecCompleteRequest();
        
        //注文ID
        l_request.orderId = "1";
        //約定株数
        l_request.execQuantity = "100";
        //約定単価
        l_request.execPrice = "100";
        //約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");
            
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            
            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);
            
            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");
            
            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setSubmitOrderRouteDiv("2");
            //注文単位.市場から確認済みの数量
            l_eqtypeOrderUnitParams.setConfirmedQuantity(250000);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20070128", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            
            WEB3AdminEquityPTSInputExecCompleteResponse l_response = l_serviceImpl.submitInputExec(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;
            
            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);
            
            assertFalse(l_execHistory.cancelFlag);
            
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");

            Object[] l_objWheres = {"AI80A"};

            List l_lisHostEquityOrderExecNotifyParams = new ArrayList();
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisHostEquityOrderExecNotifyParams = l_processor.doFindAllQuery(
                HostEqtypeOrderAcceptParams.TYPE,
                l_sbWhere.toString(),
                l_objWheres);
            assertEquals("0", l_lisHostEquityOrderExecNotifyParams.size() + "");
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
