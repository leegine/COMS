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
     * ���O�o�̓��[�e�B���e�B�B<BR>
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
     * �w�l �P�������l �T�Z��n���  ����҂h�c�s��null
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
            
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //�⏕�����h�c               �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓����� 
            assertEquals(1001, l_actionRow.getOrderId());
            //��������                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(new Double(60000), new Double(l_actionRow.getQuantity()));
            //�w�l                       �敨OP�\ �񒍕��P�ʃe�[�u���̓�����  
            assertEquals(new Double(100), new Double(l_actionRow.getLimitPrice()));
            //�P�������l                 �敨OP�\�񒍕��P�ʃe�[�u���̓�����         
            assertEquals(new Double(101), new Double(l_actionRow.getPriceAdjustValue()));
            //�����������t               �敨OP�\�񒍕��P�ʃe�[�u���̓�����   
            assertNull(l_actionRow.getExpirationDate());
            //�������                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����   
            assertEquals(OrderStatusEnum.ACCEPTED, l_actionRow.getOrderStatus());
            //�����L�����               �敨OP�\�񒍕��P�ʃe�[�u���̓�����                     
            assertEquals(OrderOpenStatusEnum.OPEN, l_actionRow.getOrderOpenStatus());
            //�����敪                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����    
            assertEquals(OrderExpirationStatusEnum.OPEN, l_actionRow.getExpirationStatus());
            //��������ԍ�               �敨OP�\�񒍕��P�ʃe�[�u��.���������ŏI�ʔ�      
            assertEquals(1005, l_actionRow.getOrderActionSerialNo());
            //�T�Z��n���               �敨OP�\�񒍕��P�ʃe�[�u���̓����� 
            assertEquals(new Double(1500), new Double(l_actionRow.getEstimatedPrice()));
            //�����o�H�敪               �敨OP�\�񒍕��P�ʃe�[�u���̓�����    
            assertNull(l_actionRow.getOrderRootDiv());
            //����҂h�c                 �敨OP�\�񒍕��P�ʃe�[�u���̓�����      
            assertEquals(1115, l_actionRow.getTraderId());
            //���҃R�[�h�iSONAR�j        �敨OP�\�񒍕��P�ʃe�[�u���̓�����      
            assertNull(l_actionRow.getSonarTraderCode());
            //���������敪               �敨OP�\�񒍕��P�ʃe�[�u���̓����� 
            assertNull(l_actionRow.getExpirationDateType());
            //�쐬���t                   ���ݎ����iSystemTimestamp�j  
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_actionRow.getCreatedTimestamp(),"yyyyMMdd"));
            //�X�V���t                   ���ݎ����iSystemTimestamp�j
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
     * �w�l �P�������l �T�Z��n���  ����҂h�c  0
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
            
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //�⏕�����h�c               �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(33381251220301L, l_actionRow.getSubAccountId());
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓����� 
            assertEquals(1001, l_actionRow.getOrderId());
            //��������                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(new Double(60000), new Double(l_actionRow.getQuantity()));
            //�w�l                       �敨OP�\ �񒍕��P�ʃe�[�u���̓�����
            assertEquals(new Double(0), new Double(l_actionRow.getLimitPrice()));
            //�P�������l                 �敨OP�\�񒍕��P�ʃe�[�u���̓�����         
            assertEquals(new Double(0), new Double(l_actionRow.getPriceAdjustValue()));
            //�����������t               �敨OP�\�񒍕��P�ʃe�[�u���̓�����   
            assertNull(l_actionRow.getExpirationDate());
            //�������                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����   
            assertEquals(OrderStatusEnum.ACCEPTED, l_actionRow.getOrderStatus());
            //�����L�����               �敨OP�\�񒍕��P�ʃe�[�u���̓�����                     
            assertEquals(OrderOpenStatusEnum.OPEN, l_actionRow.getOrderOpenStatus());
            //�����敪                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����    
            assertEquals(OrderExpirationStatusEnum.OPEN, l_actionRow.getExpirationStatus());
            //��������ԍ�               �敨OP�\�񒍕��P�ʃe�[�u��.���������ŏI�ʔ�      
            assertEquals(1005, l_actionRow.getOrderActionSerialNo());
            //�T�Z��n���               �敨OP�\�񒍕��P�ʃe�[�u���̓����� 
            assertEquals(new Double(0),new Double(l_actionRow.getEstimatedPrice()));
            //�����o�H�敪               �敨OP�\�񒍕��P�ʃe�[�u���̓�����    
            assertNull(l_actionRow.getOrderRootDiv());
            //����҂h�c                 �敨OP�\�񒍕��P�ʃe�[�u���̓�����      
            assertEquals(new Long(0),new Long(l_actionRow.getTraderId()));
            //���҃R�[�h�iSONAR�j        �敨OP�\�񒍕��P�ʃe�[�u���̓�����      
            assertNull(l_actionRow.getSonarTraderCode());
            //���������敪               �敨OP�\�񒍕��P�ʃe�[�u���̓����� 
            assertNull(l_actionRow.getExpirationDateType());
            //�쐬���t                   ���ݎ����iSystemTimestamp�j  
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_actionRow.getCreatedTimestamp(),"yyyyMMdd"));
            //�X�V���t                   ���ݎ����iSystemTimestamp�j
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
     *  �� ������� �����L����� �����敪 �����G���[�R�[�h �X�V���t
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
            //������� �����L����� �����敪 �����G���[�R�[�h
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
     *  �� ������� �����L����� �����敪 �����G���[�R�[�h �X�V���t
     *  ������� �����G���[�R�[�h�׊����l
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
            //������� �����L����� �����敪 �����G���[�R�[�h
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
     * �Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
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
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s���B
     * �Q�|�P�j�@@this.invalidate�\�񒍕��P��(�����Ώۂ̗v�f, null)���R�[������B
     * �@@true��ԋp����B
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
            //������� �����L����� �����敪 �����G���[�R�[�h
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
     * cancel�\�񒍕��P��
     * ����҂h�c(trader_id) :
     * �����o�H�敪(order_root_div) :
     * ���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
     * �������(order_status) :14:�����ρi��������j
     * �����L�����(order_open_status) :2:�N���[�Y
     * �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
     * 
     * ���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�i�����l�j
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
            // ����҂h�c(trader_id) :
            assertEquals(1234, l_orderUnitRow.getTraderId());
            // �����o�H�敪(order_root_div) :
            assertEquals("5", l_orderUnitRow.getOrderRootDiv());
            // ���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // �������(order_status) :14:�����ρi��������j
            assertEquals(OrderStatusEnum.CANCELLED, l_orderUnitRow.getOrderStatus());
            // �����L�����(order_open_status) :2:�N���[�Y
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1006);
            
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //�⏕�����h�c               �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
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
     * cancel�\�񒍕��P��
     * ����҂h�c(trader_id) :
     * �����o�H�敪(order_root_div) :
     * ���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
     * �������(order_status) :14:�����ρi��������j
     * �����L�����(order_open_status) :2:�N���[�Y
     * �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
     * 
     * ���O�C���Z�L�����e�B�T�[�r�X���擾�\�ȏꍇ
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
            // ����҂h�c(trader_id) :
            assertNull(l_params.trader_id);
            // �����o�H�敪(order_root_div) :
            assertEquals("7", l_orderUnitRow.getOrderRootDiv());
            // ���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // �������(order_status) :14:�����ρi��������j
            assertEquals(OrderStatusEnum.CANCELLED, l_orderUnitRow.getOrderStatus());
            // �����L�����(order_open_status) :2:�N���[�Y
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1006);
            
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //�⏕�����h�c               �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
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
     * cancel�\�񒍕��P��
     * ����҂h�c(trader_id) :
     * �����o�H�敪(order_root_div) :
     * ���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
     * �������(order_status) :14:�����ρi��������j
     * �����L�����(order_open_status) :2:�N���[�Y
     * �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
     * 
     * ���O�C���Z�L�����e�B�T�[�r�X���擾�s�ȏꍇ�F�i�����l�j
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
            
            // ����҂h�c(trader_id) :
            assertEquals(3338111123L, l_orderUnitRow.getTraderId());
            // �����o�H�敪(order_root_div) :
            assertEquals("7", l_orderUnitRow.getOrderRootDiv());
            // ���������ŏI�ʔ�(last_order_action_serial_no) :�i�����l�j + 1
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // �������(order_status) :14:�����ρi��������j
            assertEquals(OrderStatusEnum.CANCELLED, l_orderUnitRow.getOrderStatus());
            // �����L�����(order_open_status) :2:�N���[�Y
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                    WEB3DateUtility.formatDate(l_orderUnitRow.getLastUpdatedTimestamp(),"yyyyMMdd"));
            
            RsvIfoOrderActionRow l_actionRow =
                RsvIfoOrderActionDao.findRowByOrderIdOrderActionSerialNo(1001, 1006);
            
            //�����h�c                   �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
            assertEquals(333812512203L, l_actionRow.getAccountId());
            //�⏕�����h�c               �敨OP�\�񒍕��P�ʃe�[�u���̓�����  
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
     * �Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����
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
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������s��
     * �Q�|�P�j�@@this.cancel�\�񒍕��P��(�����Ώۂ̗v�f)���R�[������B
     * �R�j�@@true��ԋp����B
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
     * expire�\�񒍕��P��
     * ��OP�o���I��_�敨OP�\�񒍕��P�ʃe�[�u��
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
            //���������ŏI�ʔ�
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // �����L�����(order_open_status) :2:�N���[�Y
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // �����敪
            assertEquals(OrderExpirationStatusEnum.EXPIRED, l_orderUnitRow.getExpirationStatus());
            // �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
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
     * �@@�@@�@@this.get�L���\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B
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
            //���������ŏI�ʔ�
            assertEquals(1006, l_orderUnitRow.getLastOrderActionSerialNo());
            // �����L�����(order_open_status) :2:�N���[�Y
            assertEquals(OrderOpenStatusEnum.CLOSED, l_orderUnitRow.getOrderOpenStatus());
            // �����敪
            assertEquals(OrderExpirationStatusEnum.EXPIRED, l_orderUnitRow.getExpirationStatus());
            // �X�V���t(last_updated_timestamp) :���ݓ����iGtlUtils.getSystemTimestamp()�j
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
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����
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
     * �������ʂ�ԋp����
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
     * deleteAll�\�񒍕��P��
     * this.get�\�񒍕��P�ʈꗗ(�p�����[�^.�e�����̒���ID)���R�[������B<BR>
     * �@@�@@�@@�Y���f�[�^�Ȃ��̏ꍇ�Afalse��ԋp����B<BR>
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
     * �Q�|�P�j�@@�Y���\�񒍕����������ɑ΂���ԍϒ����̏ꍇ
     *�i�����Ώۂ̗v�f.�A����������敪=="�敨�ԍρi�����c�j"
     * �y�敨OP�\�񌚋ʕԍώw����e�[�u���z���delete����
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
     * �Q�|�P�j�@@�Y���\�񒍕����������ɑ΂���ԍϒ����̏ꍇ
     *�i�����Ώۂ̗v�f.�A����������敪=="OP�ԍρi�����c�j"�j�A
     * �y�敨OP�\�񌚋ʕԍώw����e�[�u���z���delete����
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
     * �Y���\�񒍕����������ɑ΂���ԍϒ����łȂ��ꍇ
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
     * get�\�񒍕��P�ʈꗗ
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����
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
     * �@@�������ʂ�ԋp����B
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
     * �Q�j�@@�����̐敨OP�\�񒍕�����Row��"null"�̏ꍇ
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
     * �Q�j�@@�����̐敨OP�\�񒍕�����Row��"null"�łȂ��ꍇ
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
