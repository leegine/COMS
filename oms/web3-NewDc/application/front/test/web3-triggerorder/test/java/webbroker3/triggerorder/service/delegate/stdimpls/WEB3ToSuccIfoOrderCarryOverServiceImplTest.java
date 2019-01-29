head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.20.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccIfoOrderCarryOverServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderCarryOverServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/17 金傑（中訊）新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccIfoOrderCarryOverServiceImplTest  extends TestBaseForMock
{

    private WEB3ToSuccIfoOrderCarryOverServiceImpl l_service = null;
    
    private List l_lisIfoOrderUintResults = null;
    
    private List l_lisRsvIfoOrderResults = null;
    
    private List l_lisRsvIfoOrderActionResults = null;
    
    private List l_lisRsvIfoClosingContractSpecResults = null;
    
    private boolean l_blnIsThrowAble = false;
    
    private boolean l_blnIsInsertOptionOpenContractCarryOrder = false;
    
    private boolean l_blnIsInsertFuturesOpenContractCarryOrder = false;
    
    private boolean l_blnIsInsertOptionCloseContractCarryOrder = false;
    
    private boolean l_blnIsInsertFuturesCloseContractCarryOrder = false;
    
    private boolean l_blnIsReversingTrade = true;

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderCarryOverServiceImplTest.class);

    public WEB3ToSuccIfoOrderCarryOverServiceImplTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
        this.l_service = new WEB3ToSuccIfoOrderCarryOverServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 予約注文単位一覧 = null
     * 抛出異常信息：SYSTEM_ERROR_80017
     *
     */
    public void testExecToSuccOrderCarryOver_C0001()
    {
        final String STR_METHOD_NAME = "testExecToSuccOrderCarryOver_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            IfoOrderUnitForTest l_carryOverOriginalParentOrderUnit = new IfoOrderUnitForTest();
            IfoOrderUnitForTest l_carryOverAfterParentOrderUnit = new IfoOrderUnitForTest();
            List l_lisRsvIfoOrderUnits = null;
            l_service.execToSuccOrderCarryOver(
                l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 予約注文単位一覧 = 0條
     * 
     *「予約注文設定フラグ」="0"
     *
     *
     */
    public void testExecToSuccOrderCarryOver_C0002()
    {
        final String STR_METHOD_NAME = "testExecToSuccOrderCarryOver_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderUnitForTest l_carryOverOriginalParentOrderUnit = new IfoOrderUnitForTest();
            IfoOrderUnitForTest l_carryOverAfterParentOrderUnit = new IfoOrderUnitForTest();
            List l_lisRsvIfoOrderUnits = new ArrayList();
            
            l_service.execToSuccOrderCarryOver(
                l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits);
            
            this.getResult();
            
            assertEquals("0",((IfoOrderUnitRow)this.l_lisIfoOrderUintResults.get(0)).getReserveOrderExistFlag());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 予約注文単位一覧 = 1條
     * 繰越対象外注文(is繰越対象注文() == false)の場合
     * 
     *「予約注文設定フラグ」="0"
     * 更新「RsvIfoOrderUnitRow」的「注文エラー理由コード」="9001"
     *
     */
    public void testExecToSuccOrderCarryOver_C0003()
    {
        final String STR_METHOD_NAME = "testExecToSuccOrderCarryOver_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitRow.setOrderUnitId(1001);
            l_rsvIfoOrderUnitRow.setOrderId(1001);
            l_rsvIfoOrderUnitRow.setExpirationDateType("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitRow);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            
            IfoOrderUnitForTest l_carryOverOriginalParentOrderUnit = new IfoOrderUnitForTest();
            IfoOrderUnitForTest l_carryOverAfterParentOrderUnit = new IfoOrderUnitForTest();
            List l_lisRsvIfoOrderUnits = new ArrayList();
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            
            l_lisRsvIfoOrderUnits.add(l_rsvIfoOrderUnitParams);
            
            l_service.execToSuccOrderCarryOver(
                l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits);
            
            this.getResult();
            
            assertEquals("0",((IfoOrderUnitRow)this.l_lisIfoOrderUintResults.get(0)).getReserveOrderExistFlag());
            assertEquals("9001",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 予約注文単位一覧 = 1條
     * 予約注文.注文カテゴリ == "OP新規建注文"の場合
     * 「insertOP新規建繰越注文」抛出異常信息
     * 
     * 予約注文設定フラグ」="0"
     * 更新「RsvIfoOrderUnitRow」的「注文エラー理由コード」="123"
     * 
     *
     */
    public void testExecToSuccOrderCarryOver_C0004()
    {
        final String STR_METHOD_NAME = "testExecToSuccOrderCarryOver_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", 
                    "isCarryoverReserveIfoOrderUnit",
                    new Class[]{OrderUnit.class},
                    new Boolean(true));
            
            this.l_blnIsThrowAble = true;
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitRow.setOrderUnitId(1001);
            l_rsvIfoOrderUnitRow.setOrderId(1001);
            l_rsvIfoOrderUnitRow.setExpirationDateType("2");
            l_rsvIfoOrderUnitRow.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            // expiration_date
            Calendar l_ca = Calendar.getInstance();
            l_ca.set(2008,05,2);
            l_rsvIfoOrderUnitRow.setExpirationDate(l_ca.getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitRow);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            IfoOrderUnitForTest l_carryOverOriginalParentOrderUnit = new IfoOrderUnitForTest();
            IfoOrderUnitForTest l_carryOverAfterParentOrderUnit = new IfoOrderUnitForTest();
            List l_lisRsvIfoOrderUnits = new ArrayList();
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            
            l_lisRsvIfoOrderUnits.add(l_rsvIfoOrderUnitParams);
            
            this.l_service = new WEB3ToSuccIfoOrderCarryOverServiceImplForTest();
            this.l_service.execToSuccOrderCarryOver(
                l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits);
            
            this.getResult();
            
            assertEquals("0",((IfoOrderUnitRow)this.l_lisIfoOrderUintResults.get(0)).getReserveOrderExistFlag());
            assertEquals("123",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 予約注文単位一覧 = 4條
     * 予約注文.注文カテゴリ == "OP新規建注文"の場合
     * 
     * 
     * 更新「IfoOrderUnitRow」的「予約注文設定フラグ」="1"
     * 更新「RsvIfoOrderUnitRow」的「注文エラー理由コード」= null
     * 
     * 「insertOP新規建繰越注文」執行
     * 「insert先物新規建繰越注文」執行
     * 「insertOP返済繰越注文」執行
     * 「insert先物返済繰越注文」執行
     * 
     * 
     *
     */
    public void testExecToSuccOrderCarryOver_C0005()
    {
        final String STR_METHOD_NAME = "testExecToSuccOrderCarryOver_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createNewOrderId",
                    new Class[]{},
                    new Long(1001));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
                    
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            IfoOrderUnitParams l_ifoOrderUnitParams =TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow1 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitRow1.setOrderUnitId(1001);
            l_rsvIfoOrderUnitRow1.setBranchId(33381L);
            l_rsvIfoOrderUnitRow1.setOrderId(1001);
            l_rsvIfoOrderUnitRow1.setAccountId(333812512201L);
            l_rsvIfoOrderUnitRow1.setProductId(1006149081011L);
            l_rsvIfoOrderUnitRow1.setParentOrderUnitId(1001);
            l_rsvIfoOrderUnitRow1.setExpirationDateType("2");
            l_rsvIfoOrderUnitRow1.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            // expiration_date
            Calendar l_ca = Calendar.getInstance();
            l_ca.set(2008,05,2);
            l_rsvIfoOrderUnitRow1.setExpirationDate(l_ca.getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitRow1);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow2 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitRow2.setOrderUnitId(1002);
            l_rsvIfoOrderUnitRow2.setOrderId(1002);
            l_rsvIfoOrderUnitRow2.setBranchId(33381L);
            l_rsvIfoOrderUnitRow2.setParentOrderUnitId(1002);
            l_rsvIfoOrderUnitRow2.setAccountId(333812512202L);
            l_rsvIfoOrderUnitRow2.setProductId(1006149081012L);
            l_rsvIfoOrderUnitRow2.setExpirationDateType("2");
            l_rsvIfoOrderUnitRow2.setParentOrderUnitId(1002);
            l_rsvIfoOrderUnitRow2.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitRow2.setExpirationDate(l_ca.getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitRow2);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow3 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitRow3.setOrderUnitId(1003);
            l_rsvIfoOrderUnitRow3.setOrderId(1003);
            l_rsvIfoOrderUnitRow3.setParentOrderUnitId(1003);
            l_rsvIfoOrderUnitRow3.setAccountId(333812512203L);
            l_rsvIfoOrderUnitRow3.setProductId(1006149081013L);
            l_rsvIfoOrderUnitRow3.setExpirationDateType("2");
            l_rsvIfoOrderUnitRow3.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitRow3.setExpirationDate(l_ca.getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitRow3);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitRow4 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitRow4.setOrderUnitId(1004);
            l_rsvIfoOrderUnitRow4.setOrderId(1004);
            l_rsvIfoOrderUnitRow4.setParentOrderUnitId(1004);
            l_rsvIfoOrderUnitRow4.setAccountId(333812512204L);
            l_rsvIfoOrderUnitRow4.setProductId(1006149081014L);
            l_rsvIfoOrderUnitRow4.setExpirationDateType("2");
            l_rsvIfoOrderUnitRow4.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            l_rsvIfoOrderUnitRow4.setExpirationDate(l_ca.getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitRow4);
            

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            
            IfoOrderUnitForTest l_carryOverOriginalParentOrderUnit = new IfoOrderUnitForTest();
            IfoOrderUnitForTest l_carryOverAfterParentOrderUnit = new IfoOrderUnitForTest();
            List l_lisRsvIfoOrderUnits = new ArrayList();
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams1 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams1.setOrderId(1001);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams2 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams2.setOrderId(1002);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams3 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams3.setOrderId(1003);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams4 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams4.setOrderId(1004);
            
            
            l_lisRsvIfoOrderUnits.add(l_rsvIfoOrderUnitParams1);
            l_lisRsvIfoOrderUnits.add(l_rsvIfoOrderUnitParams2);
            l_lisRsvIfoOrderUnits.add(l_rsvIfoOrderUnitParams3);
            l_lisRsvIfoOrderUnits.add(l_rsvIfoOrderUnitParams4);

            this.l_service = new WEB3ToSuccIfoOrderCarryOverServiceImplForTest();
            this.l_service.execToSuccOrderCarryOver(
                l_carryOverOriginalParentOrderUnit, l_carryOverAfterParentOrderUnit, l_lisRsvIfoOrderUnits);
            
            this.getResult();
            
            assertEquals("0",((IfoOrderUnitRow)this.l_lisIfoOrderUintResults.get(0)).getReserveOrderExistFlag());
            
            assertFalse(l_blnIsInsertOptionOpenContractCarryOrder);
            assertFalse(l_blnIsInsertFuturesOpenContractCarryOrder);
            assertFalse(l_blnIsInsertOptionCloseContractCarryOrder);
            assertFalse(l_blnIsInsertFuturesCloseContractCarryOrder);
            assertEquals("9001",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());

        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /**
     * (insert先物新規建繰越注文)正常結束
     * 
     * 更新「先物OP予約注文単位テーブルにレコード」插入一條
     * 更新「予約注文履歴」插入一條
     */
    public void testInsertFuturesOpenContractCarryOrder_C0001()
    {
        final String STR_METHOD_NAME = "testInsertFuturesOpenContractCarryOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams =  TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            long l_lngOrderID = 1001;
            
            WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImplForTest(TestDBUtility.getRsvIfoOrderUnitRow());
            
            this.l_service.insertFuturesOpenContractCarryOrder(
                l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, new IfoOrderUnitForTest());
            assertTrue(true);
            this.getResult();
            this.getRsvIfoOrderActionResults(1001);
            assertEquals("0000",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());
            assertEquals(1,this.l_lisRsvIfoOrderActionResults.size());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 繰越元予約注文単位.is反対売買取引() == true
     * 
     * 「予約注文履歴」插入一條
     *
     */
    public void testInsertFuturesCloseContractCarryOrder_C0001()
    {
        final String STR_METHOD_NAME = "testInsertFuturesCloseContractCarryOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            long l_lngOrderID = 1001;
            
            WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImplForTest(TestDBUtility.getRsvIfoOrderUnitRow());
            
            this.l_service.insertFuturesCloseContractCarryOrder(
                l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, new IfoOrderUnitForTest());
            
            this.getResult();
            this.getRsvIfoOrderActionResults(1001);
            assertEquals("0000",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());
            assertEquals(1,this.l_lisRsvIfoOrderActionResults.size());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
    }
    
    /**
     * 繰越元予約注文単位.is反対売買取引() == false
     * 「予約建玉返済指定情報」=0
     * 
     * 「予約注文履歴」插入一條
     *
     */
    public void testInsertFuturesCloseContractCarryOrder_C0002()
    {
        final String STR_METHOD_NAME = "testInsertFuturesCloseContractCarryOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            // rsv_ifo_closing_contract_spec
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            this.l_blnIsReversingTrade = false;
            
            long l_lngOrderID = 1001;
            
            WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImplForTest(TestDBUtility.getRsvIfoOrderUnitRow());
            
            this.l_service.insertFuturesCloseContractCarryOrder(
                l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, new IfoOrderUnitForTest());
            
            this.getResult();
            this.getRsvIfoOrderActionResults(1001);
            assertEquals("0000",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());
            assertEquals(1,this.l_lisRsvIfoOrderActionResults.size());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
    }
    
    /**
     * 繰越元予約注文単位.is反対売買取引() == false
     * 「予約建玉返済指定情報」=1
     * 
     * 「予約注文履歴」插入一條
     *
     */
    public void testInsertFuturesCloseContractCarryOrder_C0003()
    {
        final String STR_METHOD_NAME = "testInsertFuturesCloseContractCarryOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            // rsv_ifo_closing_contract_spec
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            this.l_blnIsReversingTrade = false;
            
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            l_rsvIfoOrderUnitParams.setOrderUnitId(1002);
//            l_rsvIfoOrderUnitParams.setAccountId(333812512201L);
//            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220311L);
//            l_rsvIfoOrderUnitParams.setBranchId(33381L);
//            l_rsvIfoOrderUnitParams.setOrderId(1002);
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            
//            
//            // RsvIfoClosingContractSpecRow
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                TestDBUtility.getRsvIfoClosingContractSpecRow();
            
            l_rsvIfoClosingContractSpecParams.setAccountId(333812512201L);// 333812512203
            l_rsvIfoClosingContractSpecParams.setSubAccountId(33381251220311L);// 33381251220301
            l_rsvIfoClosingContractSpecParams.setOrderId(1001);
            l_rsvIfoClosingContractSpecParams.setContractId(1002);
            l_rsvIfoClosingContractSpecParams.setClosingSerialNo(10);
            l_rsvIfoClosingContractSpecParams.setQuantity(200);
            TestDBUtility.insertWithDel(l_rsvIfoClosingContractSpecParams);
            
            long l_lngOrderID = 1002;
            
            WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImplForTest(TestDBUtility.getRsvIfoOrderUnitRow());
            
            this.l_service.insertFuturesCloseContractCarryOrder(
                l_lngOrderID, l_carryOverOriginalToSuccIfoOrderUnit, new IfoOrderUnitForTest());
            
            this.getResult();
            this.getRsvIfoOrderActionResults(l_lngOrderID);
//            assertEquals("0000",((RsvIfoOrderUnitRow)this.l_lisRsvIfoOrderResults.get(0)).getErrorReasonCode());
            assertEquals(1,this.l_lisRsvIfoOrderActionResults.size());
            assertEquals(1002,((RsvIfoClosingContractSpecRow)this.l_lisRsvIfoClosingContractSpecResults.get(0)).getContractId());
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail(); 
        }
    }
    
    private void getResult() throws Exception, DataNetworkException
    {
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        String l_strWhere = "order_id=?";
        Object[] l_objWhere =
        {
            new Long(1001)
        };
        this.l_lisIfoOrderUintResults = 
            l_processor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhere);
        
        this.l_lisRsvIfoOrderResults =
            l_processor.doFindAllQuery(
                RsvIfoOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhere);

        // RsvIfoClosingContractSpecRow
        
        this.l_lisRsvIfoClosingContractSpecResults =
            l_processor.doFindAllQuery(
                RsvIfoClosingContractSpecRow.TYPE,
                l_strWhere,
                l_objWhere);
    }
    
    private void getRsvIfoOrderActionResults(long l_lngOrderId) throws Exception, DataNetworkException
    {
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        String l_strWhere = "order_id=?";
        Object[] l_objWhere =
        {
            new Long(l_lngOrderId)
        };
        
        this.l_lisRsvIfoOrderActionResults =
            l_processor.doFindAllQuery(
                RsvIfoOrderActionRow.TYPE,
                l_strWhere,
                l_objWhere);

    }
    
    private class IfoOrderUnitForTest implements IfoOrderUnit
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
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 1001;
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

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
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
            // TODO Auto-generated method stub
            return null;
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
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
            return null;
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
            // TODO Auto-generated method stub
            return TestDBUtility.getIfoOrderUnitRow();
        }
        
    }
    
    private class WEB3ToSuccIfoOrderCarryOverServiceImplForTest extends WEB3ToSuccIfoOrderCarryOverServiceImpl
    {
        public void insertOptionOpenContractCarryOrder(long l_lngOrderID,
                WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
                IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
        {
            if(l_blnIsThrowAble)
            {
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                        "getErrorReasonCode", new Class[]
                        { String.class },
                        "123");
                
                        ErrorInfo l_errorInfo = new ErrorInfo();
                        l_errorInfo.setErrorCode("995");
                throw new WEB3BaseException(l_errorInfo, "insertOptionOpenContractCarryOrder");
            }
            else
            {
                l_blnIsInsertOptionOpenContractCarryOrder = true;
            }
            
        }
        
        public void insertFuturesOpenContractCarryOrder(long l_lngOrderID,
                WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
                IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
        {
            l_blnIsInsertFuturesOpenContractCarryOrder = true;
        }
        
        public void insertFuturesCloseContractCarryOrder(long l_lngOrderID,
                WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
                IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
        {
            l_blnIsInsertFuturesCloseContractCarryOrder = true;
        }
        
        public void insertOptionCloseContractCarryOrder(long l_lngOrderID,
                WEB3ToSuccIfoOrderUnitImpl l_carryOverOriginalToSuccIfoOrderUnit,
                IfoOrderUnit l_carryOverAfterParentOrderUnit) throws WEB3BaseException
        {
            l_blnIsInsertOptionCloseContractCarryOrder = true;
        }
    }
    
    private class WEB3ToSuccIfoOrderUnitImplForTest extends WEB3ToSuccIfoOrderUnitImpl
    {

        public WEB3ToSuccIfoOrderUnitImplForTest(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow)
        {
            super(l_rsvIfoOrderUnitRow);
        }
        
        public boolean isReversingTrade() throws WEB3BaseException
        {
            return l_blnIsReversingTrade;
        }
        
    }
}
@
