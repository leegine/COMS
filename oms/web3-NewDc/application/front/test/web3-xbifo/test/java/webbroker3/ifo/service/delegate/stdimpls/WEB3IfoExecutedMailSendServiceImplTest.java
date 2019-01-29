head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.47.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoExecutedMailSendServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/29 劉剣（中訊）新規作成
Revision History : 2007/08/22 劉剣 (中訊) IFO小数点対応
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.data.IfoOrderExecSendMailParams;
import webbroker3.ifo.data.IfoOrderExecSendMailRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoExecutedMailSendServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoExecutedMailSendServiceImplTest.class);
    
    private WEB3IfoExecutedMailSendServiceImpl l_impl = null;
    private boolean l_blnFullyExecuted = false;
    private boolean l_blnOrderCateg = false;
    private boolean l_blnOrderStatus = false;
    private boolean l_blnPartiallyExecuted = false;
    private boolean l_blnUnexecuted = false;
    private boolean l_blnOrderUnitId = false;

    public WEB3IfoExecutedMailSendServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3IfoExecutedMailSendServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * 失効理由コードがnullでない場合失敗
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testSendMailProcess_C0001()
    {
        final String STR_METHOD_NAME = "testSendMailProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoExecutedMailSendServiceImpl l_serviceImpl = new WEB3IfoExecutedMailSendServiceImplForTest1();
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strCloseReasonCode = "1";
            
            l_serviceImpl.sendMailProcess(l_orderUnit, l_strCloseReasonCode);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 失効理由コードがnullでない場合
     */
    public void testSendMailProcess_C0002()
    {
        final String STR_METHOD_NAME = "testSendMailProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoExecutedMailSendServiceImpl l_serviceImpl = new WEB3IfoExecutedMailSendServiceImplForTest2();
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strCloseReasonCode = "1";
            
            l_serviceImpl.sendMailProcess(l_orderUnit, l_strCloseReasonCode);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 失効理由コード == null失敗
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testSendMailProcess_C0003()
    {
        final String STR_METHOD_NAME = "testSendMailProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoExecutedMailSendServiceImpl l_serviceImpl = new WEB3IfoExecutedMailSendServiceImplForTest1();
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strCloseReasonCode = null;
            
            l_serviceImpl.sendMailProcess(l_orderUnit, l_strCloseReasonCode);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 失効理由コード == null
     */
    public void testSendMailProcess_C0004()
    {
        final String STR_METHOD_NAME = "testSendMailProcess_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3IfoExecutedMailSendServiceImpl l_serviceImpl = new WEB3IfoExecutedMailSendServiceImplForTest2();
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strCloseReasonCode = null;
            
            l_serviceImpl.sendMailProcess(l_orderUnit, l_strCloseReasonCode);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * パラメータ値不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testCreateExecutedMail_C0001()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderUnit l_orderUnit = null;
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 未約定（注文単位.isUnexecuted() == true））の場合
     * 課税区分  0：無関係
     */
    public void testCreateExecutedMail_C0002()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnOrderCateg = false;
            l_blnUnexecuted = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("0", l_row.getTaxationDiv());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 未約定（注文単位.isUnexecuted() == true））の場合
     * 課税区分  1：申告分離
     */
    public void testCreateExecutedMail_C0003()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnOrderCateg = true;
            l_blnUnexecuted = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("1", l_row.getTaxationDiv());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 全部約定または、一部約定の場合
     * 注文単位.注文状態==発注済（変更注文）”約定済”
     * 課税区分  0：無関係
     */
    public void testCreateExecutedMail_C0004()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFullyExecuted = true;
            l_blnOrderStatus = true;
            l_blnUnexecuted = false;
            l_blnPartiallyExecuted = false;
            
            l_blnOrderCateg = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setNetAmount(5.0d);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("2", l_row.getOrderExecStatus());
            assertEquals("0", l_row.getTaxationDiv());
            assertEquals(new BigDecimal(-5D + ""), new BigDecimal(l_row.getDelivalyAmount() + ""));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 全部約定または、一部約定の場合
     * 注文単位.isUnexecuted()==trueの場合は、”未約定”
     * 課税区分  0：無関係
     */
    public void testCreateExecutedMail_C0005()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFullyExecuted = true;
            l_blnOrderStatus = false;
            l_blnUnexecuted = true;
            l_blnPartiallyExecuted = false;
            
            l_blnOrderCateg = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("0", l_row.getOrderExecStatus());
            assertEquals("0", l_row.getTaxationDiv());
            assertEquals(new BigDecimal(0D + ""), new BigDecimal(l_row.getDelivalyAmount() + ""));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 全部約定または、一部約定の場合
     * 注文単位.isPartiallyExecuted()==trueの場合は、”一部約定”
     * 課税区分  1：申告分離
     */
    public void testCreateExecutedMail_C0006()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFullyExecuted = true;
            l_blnOrderStatus = false;
            l_blnUnexecuted = false;
            l_blnPartiallyExecuted = true;
            
            l_blnOrderCateg = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setNetAmount(5.0d);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("1", l_row.getOrderExecStatus());
            assertEquals("1", l_row.getTaxationDiv());
            assertEquals(new BigDecimal(-5D + ""), new BigDecimal(l_row.getDelivalyAmount() + ""));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 全部約定または、一部約定の場合
     * 注文単位.isPartiallyExecuted()==trueの場合は、”約定済”
     * 課税区分  1：申告分離
     */
    public void testCreateExecutedMail_C0007()
    {
        final String STR_METHOD_NAME = "testCreateExecutedMail_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFullyExecuted = true;
            l_blnOrderStatus = false;
            l_blnUnexecuted = false;
            l_blnPartiallyExecuted = false;
            
            l_blnOrderCateg = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoFinTransactionRow.TYPE);
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_ifoFinTransactionParams.setOrderId(1001);
            l_ifoFinTransactionParams.setOrderUnitId(1001);
            l_ifoFinTransactionParams.setDeleteFlag(BooleanEnum.FALSE);
            l_ifoFinTransactionParams.setNetAmount(5.0d);
            TestDBUtility.insertWithDel(l_ifoFinTransactionParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("2", l_row.getOrderExecStatus());
            assertEquals("1", l_row.getTaxationDiv());
            assertEquals(new BigDecimal(-5D + ""), new BigDecimal(l_row.getDelivalyAmount() + ""));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 課税区分  0：無関係
     */
    public void testCreateCloseMail_C0001()
    {
        final String STR_METHOD_NAME = "testCreateCloseMail_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnOrderCateg = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strCloseReasonCode = "1";
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createCloseMail(l_orderUnit, l_strCloseReasonCode);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("0", l_row.getTaxationDiv());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 課税区分  1：申告分離
     */
    public void testCreateCloseMail_C0002()
    {
        final String STR_METHOD_NAME = "testCreateCloseMail_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnOrderCateg = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            String l_strCloseReasonCode = "1";
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createCloseMail(l_orderUnit, l_strCloseReasonCode);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);

            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals("1", l_row.getTaxationDiv());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 先物OP約定メール送信テーブルに引数の内容で行を挿入する。
     */
    public void testInsertMail_C0001()
    {
        final String STR_METHOD_NAME = "testInsertMail_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            IfoOrderExecSendMailParams l_ifoOrderExecSendMailParams = new IfoOrderExecSendMailParams();
            l_ifoOrderExecSendMailParams.setInstitutionCode("0D");
            l_ifoOrderExecSendMailParams.setBranchCode("1");
            l_ifoOrderExecSendMailParams.setAccountCode("1");
            l_ifoOrderExecSendMailParams.setFutureOptionDiv("1");
            l_ifoOrderExecSendMailParams.setOrderRequestNumber("1");
            l_ifoOrderExecSendMailParams.setOrderExecStatus("1");
            l_ifoOrderExecSendMailParams.setOrderActionId(1);

            this.l_impl.insertMail(l_ifoOrderExecSendMailParams);
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            assertEquals(1, l_lstRecords.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * データ不整合エラー。
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testUndoSendMail_C0001()
    {
        final String STR_METHOD_NAME = "testUndoSendMail_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            this.l_impl.undoSendMail(l_orderUnit);
            
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * データ不整合エラー。
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testUndoSendMail_C0002()
    {
        final String STR_METHOD_NAME = "testUndoSendMail_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnOrderCateg = false;
            l_blnUnexecuted = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            l_blnOrderUnitId = true;
            
            this.l_impl.undoSendMail(l_orderUnit);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testUndoSendMail_C0003()
    {
        final String STR_METHOD_NAME = "testUndoSendMail_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnOrderCateg = false;
            l_blnUnexecuted = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            
            this.l_impl.createExecutedMail(l_orderUnit);
            
            l_blnOrderUnitId = false;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(WEB3DateUtility.getDate("20080731", "yyyyMMdd").getTime()));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit",
                    new Class[] {long.class},
                    l_orderUnit);
            
            this.l_impl.undoSendMail(l_orderUnit);
            
            IfoOrderExecSendMailRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "institution_code = ? ";
            Object[] l_objWhere = {"0D"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    IfoOrderExecSendMailRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (IfoOrderExecSendMailRow)l_lstRecords.get(0);
            
            assertEquals(new Timestamp(WEB3DateUtility.getDate("20080731", "yyyyMMdd").getTime()),
                l_row.getLastUpdatedTimestamp());
            assertEquals(1001, l_row.getOrderActionId());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class OrderUnitForTest implements IfoOrderUnit
    {

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isFuturesOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isCallOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPutOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOpenContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSettleContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public IfoOrderExecutionConditionType getExecutionConditionType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            long l_lngOrderUnitId = 1001;
            
            if (l_blnOrderUnitId)
            {
                l_lngOrderUnitId = 1002;
            }
            
            return l_lngOrderUnitId;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 333812512246L;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 33381;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            OrderActionForTest[] l_orderActions = new OrderActionForTest[1];
            l_orderActions[0] = new OrderActionForTest();
            
            return l_orderActions;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            WEB3IfoProductImpl l_product = null;
            try
            {
                TestDBUtility.deleteAll(ProductRow.TYPE);
                TestDBUtility.insertWithDel(TestDBUtility.getProductRow());
                
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setProductId(3304148080000L);
                l_ifoProductParams.setFutureOptionDiv("1");
                TestDBUtility.insertWithDel(l_ifoProductParams);
                
                l_product = new WEB3IfoProductImpl(3304148080000L);
            }
            catch(Exception l_ex)
            {
                fail();
            }            
            return l_product;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            //TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            //TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            OrderStatusEnum l_orderStatusEnum = null;
            if (l_blnOrderStatus)
            {
                l_orderStatusEnum = OrderStatusEnum.MODIFIED;
            }
            
            return l_orderStatusEnum;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            boolean l_isFullyExecuted = false;
            
            if (l_blnFullyExecuted)
            {
                l_isFullyExecuted = true;
            }

            return l_isFullyExecuted;
        }

        public boolean isPartiallyExecuted()
        {
            boolean l_isPartiallyExecuted = false;
            if (l_blnPartiallyExecuted)
            {
                l_isPartiallyExecuted = true;
            }
            
            return l_isPartiallyExecuted;
        }

        public boolean isUnexecuted()
        {
            boolean l_isUnexecuted = false;
            
            if (l_blnUnexecuted)
            {
                l_isUnexecuted = true;
            }
            
            return l_isUnexecuted;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);

                if (l_blnOrderCateg)
                {
                    l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
                }

                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            }
            catch (WEB3BaseException l_ex)
            {
                fail();
            }
            
            return l_ifoOrderUnitParams;
        }
        
    }
    private class WEB3IfoExecutedMailSendServiceImplForTest1 extends WEB3IfoExecutedMailSendServiceImpl
    {
        protected void createCloseMail(OrderUnit l_orderUnit, String l_strCloseReasonCode)
            throws WEB3BaseException
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                "データ不整合エラー。");
        }
        protected void createExecutedMail(OrderUnit l_orderUnit)
            throws WEB3BaseException
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                "データ不整合エラー。");
        }
        
    }
    private class WEB3IfoExecutedMailSendServiceImplForTest2 extends WEB3IfoExecutedMailSendServiceImpl
    {
        protected void createCloseMail(OrderUnit l_orderUnit, String l_strCloseReasonCode)
            throws WEB3BaseException
        {

        }
        protected void createExecutedMail(OrderUnit l_orderUnit)
            throws WEB3BaseException
        {

        }
    }
    private class OrderActionForTest implements OrderAction
    {

        public long getOrderActionId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getOrderActionTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public int getOrderActionSerialNo()
        {
            // TODO Auto-generated method stub
            return 1;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getExecutionQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutionPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderEventTypeEnum getOrderEventType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
@
