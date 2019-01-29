head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccReservationIfoOrderUpdateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder.base.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecDao;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitPK;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccReservationIfoOrderUpdateServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccReservationIfoOrderUpdateServiceImplTest.class);
    
    WEB3ToSuccReservationIfoOrderUpdateServiceImpl l_impl= new WEB3ToSuccReservationIfoOrderUpdateServiceImpl();

    public WEB3ToSuccReservationIfoOrderUpdateServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl.insertReserveOrderAction(long)'
     */
    /**
     * 指値 単価調整値 概算受渡代金  取引者ＩＤ不為null
     */
    public void testInsertReserveOrderActionCase1()
    {
        final String STR_METHOD_NAME = "testInsertReserveOrderActionCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLimitPrice(100);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(101);
            l_rsvIfoOrderUnitParams.setEstimatedPrice(1500);
            l_rsvIfoOrderUnitParams.setTraderId(1115);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_impl.insertReserveOrderAction(1001);
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1005);
            
            //口座ＩＤ                   先物OP予約注文単位テーブルの同項目  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //補助口座ＩＤ               先物OP予約注文単位テーブルの同項目  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
            //注文ＩＤ                   先物OP予約注文単位テーブルの同項目 
            assertEquals(1001, l_actionRow.getOrderId());
            //注文数量                   先物OP予約注文単位テーブルの同項目  
            assertEquals(new Double(60000), new Double(l_actionRow.getQuantity()));
            //指値                       先物OP予 約注文単位テーブルの同項目  
            assertEquals(new Double(100), new Double(l_actionRow.getLimitPrice()));
            //単価調整値                 先物OP予約注文単位テーブルの同項目         
            assertEquals(new Double(101), new Double(l_actionRow.getPriceAdjustValue()));
            //注文失効日付               先物OP予約注文単位テーブルの同項目   
            assertNull(l_actionRow.getExpirationDate());
            //注文状態                   先物OP予約注文単位テーブルの同項目   
            assertEquals(OrderStatusEnum.ACCEPTED, l_actionRow.getOrderStatus());
            //注文有効状態               先物OP予約注文単位テーブルの同項目                     
            assertEquals(OrderOpenStatusEnum.OPEN, l_actionRow.getOrderOpenStatus());
            //失効区分                   先物OP予約注文単位テーブルの同項目    
            assertEquals(OrderExpirationStatusEnum.OPEN, l_actionRow.getExpirationStatus());
            //注文履歴番号               先物OP予約注文単位テーブル.注文履歴最終通番      
            assertEquals(1005, l_actionRow.getOrderActionSerialNo());
            //概算受渡代金               先物OP予約注文単位テーブルの同項目 
            assertEquals(new Double(1500), new Double(l_actionRow.getEstimatedPrice()));
            //注文経路区分               先物OP予約注文単位テーブルの同項目    
            assertNull(l_actionRow.getOrderRootDiv());
            //取引者ＩＤ                 先物OP予約注文単位テーブルの同項目      
            assertEquals(1115, l_actionRow.getTraderId());
            //扱者コード（SONAR）        先物OP予約注文単位テーブルの同項目      
            assertNull(l_actionRow.getSonarTraderCode());
            //注文期限区分               先物OP予約注文単位テーブルの同項目 
            assertNull(l_actionRow.getExpirationDateType());
            //作成日付                   現在時刻（SystemTimestamp）  
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_actionRow.getCreatedTimestamp(),"yyyyMMdd"));
            //更新日付                   現在時刻（SystemTimestamp）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_actionRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 指値 単価調整値 概算受渡代金  取引者ＩＤ  0
     */
    public void testInsertReserveOrderActionCase2()
    {
        final String STR_METHOD_NAME = "testInsertReserveOrderActionCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_impl.insertReserveOrderAction(1001);
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1005);
            
            //口座ＩＤ                   先物OP予約注文単位テーブルの同項目  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //補助口座ＩＤ               先物OP予約注文単位テーブルの同項目  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
            //注文ＩＤ                   先物OP予約注文単位テーブルの同項目 
            assertEquals(1001, l_actionRow.getOrderId());
            //注文数量                   先物OP予約注文単位テーブルの同項目  
            assertEquals(new Double(60000), new Double(l_actionRow.getQuantity()));
            //指値                       先物OP予 約注文単位テーブルの同項目
            assertEquals(new Double(0), new Double(l_actionRow.getLimitPrice()));
            //単価調整値                 先物OP予約注文単位テーブルの同項目         
            assertEquals(new Double(0), new Double(l_actionRow.getPriceAdjustValue()));
            //注文失効日付               先物OP予約注文単位テーブルの同項目   
            assertNull(l_actionRow.getExpirationDate());
            //注文状態                   先物OP予約注文単位テーブルの同項目   
            assertEquals(OrderStatusEnum.ACCEPTED, l_actionRow.getOrderStatus());
            //注文有効状態               先物OP予約注文単位テーブルの同項目                     
            assertEquals(OrderOpenStatusEnum.OPEN, l_actionRow.getOrderOpenStatus());
            //失効区分                   先物OP予約注文単位テーブルの同項目    
            assertEquals(OrderExpirationStatusEnum.OPEN, l_actionRow.getExpirationStatus());
            //注文履歴番号               先物OP予約注文単位テーブル.注文履歴最終通番      
            assertEquals(1005, l_actionRow.getOrderActionSerialNo());
            //概算受渡代金               先物OP予約注文単位テーブルの同項目 
            assertEquals(new Double(0),new Double(l_actionRow.getEstimatedPrice()));
            //注文経路区分               先物OP予約注文単位テーブルの同項目    
            assertNull(l_actionRow.getOrderRootDiv());
            //取引者ＩＤ                 先物OP予約注文単位テーブルの同項目      
            assertEquals(new Long(0),new Long(l_actionRow.getTraderId()));
            //扱者コード（SONAR）        先物OP予約注文単位テーブルの同項目      
            assertNull(l_actionRow.getSonarTraderCode());
            //注文期限区分               先物OP予約注文単位テーブルの同項目 
            assertNull(l_actionRow.getExpirationDateType());
            //作成日付                   現在時刻（SystemTimestamp）  
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_actionRow.getCreatedTimestamp(),"yyyyMMdd"));
            //更新日付                   現在時刻（SystemTimestamp）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_actionRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  驗證 注文状態 注文有効状態 失効区分 発注エラーコード 更新日付
     * 
     */
    public void testInvalidateOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testInvalidateOrderUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_impl.invalidateOrderUnit(l_rsvIfoOrderUnitParams, "123");
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            //注文状態 注文有効状態 失効区分 発注エラーコード
            assertEquals(OrderStatusEnum.NOT_ORDERED, l_orderUnitRow.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            assertEquals(OrderExpirationStatusEnum.INVALIDATED_BY_MKT, l_orderUnitRow.getExpirationStatus());
            assertEquals("123", l_orderUnitRow.getOrderErrorCode());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *  驗證 注文状態 注文有効状態 失効区分 発注エラーコード 更新日付
     *  注文状態 発注エラーコード為既存値
     * 
     */
    public void testInvalidateOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testInvalidateOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_impl.invalidateOrderUnit(l_rsvIfoOrderUnitParams, null);
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            //注文状態 注文有効状態 失効区分 発注エラーコード
            assertEquals(OrderStatusEnum.ACCEPTED, l_orderUnitRow.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            assertEquals(OrderExpirationStatusEnum.INVALIDATED_BY_MKT, l_orderUnitRow.getExpirationStatus());
            assertNull(l_orderUnitRow.getOrderErrorCode());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 該当データなしの場合、falseを返却する。
     */
    public void testInvalidateAllOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testInvalidateOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            boolean l_blnResult = l_impl.invalidateAllOrderUnit(1001);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２）　@１）の戻り値の要素数分、以下の処理を行う。
     * ２－１）　@this.invalidate予約注文単位(処理対象の要素, null)をコールする。
     * 　@trueを返却する。
     */
    public void testInvalidateAllOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testInvalidateOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            boolean l_blnResult = l_impl.invalidateAllOrderUnit(1001);
            assertTrue(l_blnResult);
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            //注文状態 注文有効状態 失効区分 発注エラーコード
            assertEquals(OrderStatusEnum.ACCEPTED, l_orderUnitRow.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            assertEquals(OrderExpirationStatusEnum.INVALIDATED_BY_MKT, l_orderUnitRow.getExpirationStatus());
            assertNull(l_orderUnitRow.getOrderErrorCode());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * cancel予約注文単位
     * 取引者ＩＤ(trader_id) :
     * 注文経路区分(order_root_div) :
     * 注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
     * 注文状態(order_status) :14:発注済（取消注文）
     * 注文有効状態(order_open_status) :2:クローズ
     * 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
     * 
     * ログインセキュリティサービスが取得不可な場合：（既存値）
     */
    public void testCancelOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testCancelOrderUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setTraderId(1234);
            l_rsvIfoOrderUnitParams.setOrderRootDiv("5");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            l_impl.cancelOrderUnit(l_rsvIfoOrderUnitParams);
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            // 取引者ＩＤ(trader_id) :
            assertEquals(1234, l_orderUnitRow.getTraderId());
            // 注文経路区分(order_root_div) :
            assertEquals("5", l_orderUnitRow.getOrderRootDiv());
            // 注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // 注文状態(order_status) :14:発注済（取消注文）
            assertEquals(OrderStatusEnum.CANCELLED, l_orderUnitRow.getOrderStatus());
            // 注文有効状態(order_open_status) :2:クローズ
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1006);
            
            //口座ＩＤ                   先物OP予約注文単位テーブルの同項目  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //補助口座ＩＤ               先物OP予約注文単位テーブルの同項目  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * cancel予約注文単位
     * 取引者ＩＤ(trader_id) :
     * 注文経路区分(order_root_div) :
     * 注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
     * 注文状態(order_status) :14:発注済（取消注文）
     * 注文有効状態(order_open_status) :2:クローズ
     * 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
     * 
     * ログインセキュリティサービスが取得可能な場合
     * 
     */
    public void testCancelOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testCancelOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "7");

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setTraderId(1234);
            l_rsvIfoOrderUnitParams.setOrderRootDiv("5");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            l_impl.cancelOrderUnit(l_rsvIfoOrderUnitParams);
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            RsvIfoOrderUnitParams l_params = new RsvIfoOrderUnitParams(l_orderUnitRow);
            // 取引者ＩＤ(trader_id) :
            assertNull(l_params.trader_id);
            // 注文経路区分(order_root_div) :
            assertEquals("7", l_orderUnitRow.getOrderRootDiv());
            // 注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // 注文状態(order_status) :14:発注済（取消注文）
            assertEquals(OrderStatusEnum.CANCELLED, l_orderUnitRow.getOrderStatus());
            // 注文有効状態(order_open_status) :2:クローズ
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1006);
            
            //口座ＩＤ                   先物OP予約注文単位テーブルの同項目  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //補助口座ＩＤ               先物OP予約注文単位テーブルの同項目  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * cancel予約注文単位
     * 取引者ＩＤ(trader_id) :
     * 注文経路区分(order_root_div) :
     * 注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
     * 注文状態(order_status) :14:発注済（取消注文）
     * 注文有効状態(order_open_status) :2:クローズ
     * 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
     * 
     * ログインセキュリティサービスが取得不可な場合：（既存値）
     */
    public void testCancelOrderUnitCase3()
    {
        final String STR_METHOD_NAME = "testCancelOrderUnitCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
         
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "7");
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setTraderId(3338111123L);
            l_rsvIfoOrderUnitParams.setOrderRootDiv("5");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);
            l_impl.cancelOrderUnit(l_rsvIfoOrderUnitParams);
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            
            // 取引者ＩＤ(trader_id) :
            assertEquals(3338111123L, l_orderUnitRow.getTraderId());
            // 注文経路区分(order_root_div) :
            assertEquals("7", l_orderUnitRow.getOrderRootDiv());
            // 注文履歴最終通番(last_order_action_serial_no) :（既存値） + 1
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // 注文状態(order_status) :14:発注済（取消注文）
            assertEquals(OrderStatusEnum.CANCELLED, l_orderUnitRow.getOrderStatus());
            // 注文有効状態(order_open_status) :2:クローズ
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1006);
            
            //口座ＩＤ                   先物OP予約注文単位テーブルの同項目  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //補助口座ＩＤ               先物OP予約注文単位テーブルの同項目  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 該当データなしの場合、falseを返却する
     */
    public void testCancelAllOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testInvalidateOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            boolean l_blnResult = l_impl.cancelAllOrderUnit(1001);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２）　@１）の戻り値の要素数分、以下の処理を行う
     * ２－１）　@this.cancel予約注文単位(処理対象の要素)をコールする。
     * ３）　@trueを返却する。
     */
    public void testCancelAllOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testCancelAllOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            boolean l_blnResult = l_impl.cancelAllOrderUnit(1001);
            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * expire予約注文単位
     * 先OP出来終了_先物OP予約注文単位テーブル
     */
    public void testExpireOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testCancelAllOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_impl.expireOrderUnit(l_rsvIfoOrderUnitParams);
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            //注文履歴最終通番
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // 注文有効状態(order_open_status) :2:クローズ
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // 失効区分
            assertEquals(OrderExpirationStatusEnum.EXPIRED, l_orderUnitRow.getExpirationStatus());
            // 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 　@　@　@this.get有効予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。
     * 　@　@　@該当データなしの場合、falseを返却する。
     */
    public void testExpireAllOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testExpireAllOrderUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            boolean l_blnResult = l_impl.expireAllOrderUnit(1001);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireAllOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testExpireAllOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            boolean l_blnResult = l_impl.expireAllOrderUnit(1001);
            assertTrue(l_blnResult);
            
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            //注文履歴最終通番
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // 注文有効状態(order_open_status) :2:クローズ
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // 失効区分
            assertEquals(OrderExpirationStatusEnum.EXPIRED, l_orderUnitRow.getExpirationStatus());
            // 更新日付(last_updated_timestamp) :現在日時（GtlUtils.getSystemTimestamp()）
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 検索結果が取得できなかった場合、nullを返却する
     */
    public void testGetOpenReserveIfoOrderUnitsCase1()
    {
        final String STR_METHOD_NAME = "testGetOpenReserveIfoOrderUnitsCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            List l_lisOrderUnits = l_impl.getOpenReserveIfoOrderUnits(1001);
            assertNull(l_lisOrderUnits);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 検索結果を返却する
     */
    public void testGetOpenReserveIfoOrderUnitsCase2()
    {
        final String STR_METHOD_NAME = "testGetOpenReserveIfoOrderUnitsCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            List l_lisOrderUnits = l_impl.getOpenReserveIfoOrderUnits(1001);
            RsvIfoOrderUnitRow l_lisRsvIfoOrderUnitRows = (RsvIfoOrderUnitRow)l_lisOrderUnits.get(0);
            assertEquals(333812512203L, l_lisRsvIfoOrderUnitRows.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * deleteAll予約注文単位
     * this.get予約注文単位一覧(パラメータ.親注文の注文ID)をコールする。<BR>
     * 　@　@　@該当データなしの場合、falseを返却する。<BR>
     */
    public void testDeleteAllOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testDeleteAllOrderUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            boolean l_blnResult = l_impl.deleteAllOrderUnit(1001);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２－１）　@該当予約注文が既存建に対する返済注文の場合
     *（処理対象の要素.連続注文取引区分=="先物返済（既存残）"
     * 【先物OP予約建玉返済指定情報テーブル】よりdeleteする
     */
    public void testDeleteAllOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testDeleteAllOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("14");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_RsvIfoClosingContractSpecParams =
                TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_RsvIfoClosingContractSpecParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_RsvIfoClosingContractSpecParams);
            
            boolean l_blnResult = l_impl.deleteAllOrderUnit(1001);
            assertTrue(l_blnResult);
            
            RsvIfoClosingContractSpecRow l_RsvIfoClosingContractSpecRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1001, 1001);
            assertNull(l_RsvIfoClosingContractSpecRow);
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1005);
            assertNull(l_actionRow);
            
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            assertNull(l_orderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２－１）　@該当予約注文が既存建に対する返済注文の場合
     *（処理対象の要素.連続注文取引区分=="OP返済（既存残）"）、
     * 【先物OP予約建玉返済指定情報テーブル】よりdeleteする
     */
    public void testDeleteAllOrderUnitCase3()
    {
        final String STR_METHOD_NAME = "testDeleteAllOrderUnitCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("18");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_RsvIfoClosingContractSpecParams =
                TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_RsvIfoClosingContractSpecParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_RsvIfoClosingContractSpecParams);
            
            boolean l_blnResult = l_impl.deleteAllOrderUnit(1001);
            assertTrue(l_blnResult);
            
            RsvIfoClosingContractSpecRow l_RsvIfoClosingContractSpecRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1001, 1001);
            assertNull(l_RsvIfoClosingContractSpecRow);
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1005);
            assertNull(l_actionRow);
            
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            assertNull(l_orderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 該当予約注文が既存建に対する返済注文でない場合
     */
    public void testDeleteAllOrderUnitCase4()
    {
        final String STR_METHOD_NAME = "testDeleteAllOrderUnitCase4()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_RsvIfoClosingContractSpecParams =
                TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_RsvIfoClosingContractSpecParams.setOrderId(1001);
            l_RsvIfoClosingContractSpecParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_RsvIfoClosingContractSpecParams);
            
            boolean l_blnResult = l_impl.deleteAllOrderUnit(1001);
            assertTrue(l_blnResult);
            
            RsvIfoClosingContractSpecRow l_RsvIfoClosingContractSpecRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1001, 1001);
            assertEquals(1001L, l_RsvIfoClosingContractSpecRow.getOrderId());
            assertEquals(101001010010L, l_RsvIfoClosingContractSpecRow.getAccountId());
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1005);
            assertNull(l_actionRow);
            
            RsvIfoOrderUnitRow l_orderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
            assertNull(l_orderUnitRow);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get予約注文単位一覧
     * 検索結果が取得できなかった場合、nullを返却する
     */
    public void testGetReserveIfoOrderUnitsCase1()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderUnitsCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            List l_lisOrderUnits = l_impl.getReserveIfoOrderUnits(1001);
            assertNull(l_lisOrderUnits);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 　@検索結果を返却する。
     *
     */
    public void testGetReserveIfoOrderUnitsCase2()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderUnitsCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setMarketId(1002);
            l_rsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_rsvIfoOrderUnitParams.setSubAccountId(33381251220301L);
            l_rsvIfoOrderUnitParams.setBranchId(33381L);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1005);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(60000);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_rsvIfoOrderUnitParams.setBizDate("20080326");
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(12345678);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            List l_lisOrderUnits = l_impl.getReserveIfoOrderUnits(1001);
            RsvIfoOrderUnitRow l_lisRsvIfoOrderUnitRows = (RsvIfoOrderUnitRow)l_lisOrderUnits.get(0);
            assertEquals(333812512203L, l_lisRsvIfoOrderUnitRows.getAccountId());
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ２）　@引数の先物OP予約注文履歴Rowが"null"の場合
     *
     */
    public void testUpdateReserveOrderData_0001()
    {
        final String STR_METHOD_NAME = "testUpdateReserveOrderData_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccReservationIfoOrderUpdateServiceImpl l_serviceImpl =
            new WEB3ToSuccReservationIfoOrderUpdateServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setLimitPrice(20);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_rsvIfoOrderUnitParams.setLimitPrice(30);
            l_serviceImpl.updateReserveOrderData(l_rsvIfoOrderUnitParams, null);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                (RsvIfoOrderUnitRow)l_queryProcessor.doFindByPrimaryKeyQuery(new RsvIfoOrderUnitPK(1001));
            
            assertEquals("30.0", "" + l_rsvIfoOrderUnitRow.getLimitPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ２）　@引数の先物OP予約注文履歴Rowが"null"でない場合
     */
    public void testUpdateReserveOrderData_0002()
    {
        final String STR_METHOD_NAME = "testUpdateReserveOrderData_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccReservationIfoOrderUpdateServiceImpl l_serviceImpl =
            new WEB3ToSuccReservationIfoOrderUpdateServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setLimitPrice(20);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_rsvIfoOrderUnitParams.setLimitPrice(30);
            
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            RsvIfoOrderActionParams l_rsvIfoOrderActionParams = new RsvIfoOrderActionParams();
            l_rsvIfoOrderActionParams.setAccountId(1000215155151L);
            l_rsvIfoOrderActionParams.setSubAccountId(1000215155151L);
            l_rsvIfoOrderActionParams.setOrderId(1001);
            l_rsvIfoOrderActionParams.setQuantity(20);
            l_rsvIfoOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            l_rsvIfoOrderActionParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderActionParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderActionParams.setOrderActionSerialNo(2);
            l_rsvIfoOrderActionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080408", "yyyyMMdd"));
            l_rsvIfoOrderActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080408", "yyyyMMdd"));
            
            l_serviceImpl.updateReserveOrderData(l_rsvIfoOrderUnitParams, l_rsvIfoOrderActionParams);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                (RsvIfoOrderUnitRow)l_queryProcessor.doFindByPrimaryKeyQuery(new RsvIfoOrderUnitPK(1001));
            
            List l_lisRecordes = l_queryProcessor.doFindAllQuery(RsvIfoOrderActionRow.TYPE);
            
            RsvIfoOrderActionRow l_rsvIfoOrderActionRow =
                (RsvIfoOrderActionRow)l_lisRecordes.get(0);
            
            assertEquals("30.0", "" + l_rsvIfoOrderUnitRow.getLimitPrice());
            assertEquals("20.0", "" + l_rsvIfoOrderActionRow.getQuantity());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
