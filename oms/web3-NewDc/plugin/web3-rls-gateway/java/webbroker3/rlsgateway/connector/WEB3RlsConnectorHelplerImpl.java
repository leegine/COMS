head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorHelplerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���G���W���ւ̒������M�w���p�[����(WEB3RlsConnectorHelplerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 �V�� �h�O(FLJ) �V�K�쐬
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.enums.CondOrderType;
import com.fitechlabs.fin.intellioms.enums.OrderExecType;
import com.fitechlabs.fin.intellioms.enums.OrderType;
import com.fitechlabs.fin.intellioms.enums.PriceDirection;
import com.fitechlabs.fin.intellioms.enums.Side;
import com.fitechlabs.fin.intellioms.omsclt.Order;
import com.fitechlabs.fin.intellioms.omsclt.OrderId;
import com.fitechlabs.fin.intellioms.omsclt.Price;
import com.fitechlabs.fin.intellioms.omsclt.Quantity;
import com.fitechlabs.fin.intellioms.omsclt.impl.OrderImpl;
import com.fitechlabs.fin.intellioms.price.PriceCondOrder;
import com.fitechlabs.fin.intellioms.price.PriceCondition;
import com.fitechlabs.fin.intellioms.rulesys.CondOrder;
import com.fitechlabs.fin.intellioms.simple.SimpleCondOrder;
import com.fitechlabs.fin.intellioms.ticker.Ticker;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.rlsgateway.data.OmsConOrderRequestRow;
import webbroker3.rlsgateway.data.RlsCondOrderDao;
import webbroker3.rlsgateway.data.RlsCondOrderParams;
import webbroker3.rlsgateway.data.RlsCondOrderRow;
import webbroker3.rlsgateway.data.RlsOmsOrderDao;
import webbroker3.rlsgateway.data.RlsOmsOrderParams;
import webbroker3.rlsgateway.data.RlsOmsOrderRow;
import webbroker3.rlsgateway.data.RlsPriceCondDao;
import webbroker3.rlsgateway.data.RlsPriceCondParams;
import webbroker3.rlsgateway.data.RlsPriceCondRow;
import webbroker3.rlsgateway.define.WEB3RlsNotifyOrderTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 *
 * ���[���G���W���ւ̒������M�w���p�[����
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsConnectorHelplerImpl implements WEB3RlsConnectorHelpler
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsConnectorHelplerImpl.class);

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorHelpler#createCondOrder(webbroker3.rlsgateway.data.OmsConOrderRequestRow, webbroker3.rlsgateway.connector.WEB3RlsOrders, boolean)
     */
    public CondOrder createCondOrder(OmsConOrderRequestRow l_request, WEB3RlsOrders l_rlsOrders, boolean l_isNew)
    {
        RlsCondOrderRow l_parentRlsCondOrderRow = null;
        RlsCondOrderRow l_rlsCondOrderRow = null;
        RlsOmsOrderRow l_rlsOmsOrderRow = null;

        if(l_isNew)
        {
            l_parentRlsCondOrderRow = l_rlsOrders.getParentRlsCondOrderRow();
            l_rlsCondOrderRow = l_rlsOrders.getRlsCondOrderRow();
            l_rlsOmsOrderRow = l_rlsOrders.getRlsOmsOrderRow();
        }
        else
        {
            l_parentRlsCondOrderRow = l_rlsOrders.getOldParentRlsCondOrderRow();
            l_rlsCondOrderRow = l_rlsOrders.getOldRlsCondOrderRow();
            l_rlsOmsOrderRow = l_rlsOrders.getOldRlsOmsOrderRow();
        }

        int l_intOrderType = l_request.getOrderType();

        CondOrder l_parentCondOrder = null;

        //�t�w�l�̏ꍇ||W�w�l�̏ꍇ
        if(WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_intOrderType||WEB3RlsNotifyOrderTypeDef.W_LlIMIT == l_intOrderType)
        {
            RlsPriceCondRow l_rlsPriceCondRow = null;

            if(l_isNew)
            {
                l_rlsPriceCondRow = l_rlsOrders.getRlsPriceCondRow();
            }
            else
            {
                l_rlsPriceCondRow = l_rlsOrders.getOldRlsPriceCondRow();
            }

            //Price�I�[�_�[�쐬
            l_parentCondOrder = createPriceCondOrder(l_parentRlsCondOrderRow, l_rlsPriceCondRow);

            //Simple�I�[�_�[�쐬
            CondOrder l_simpleOrder = createSimpleCondOrder(l_rlsCondOrderRow, l_rlsOmsOrderRow);

            //�q�����ɒǉ�
            l_parentCondOrder.addSubOrder(l_simpleOrder);
        }
        else
        {
            throw new IllegalArgumentException("No implementation. [OrderType=" + l_intOrderType + "]");
        }

        return l_parentCondOrder;
    }

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorHelpler#createPriceCondOrder(webbroker3.rlsgateway.data.RlsCondOrderRow, webbroker3.rlsgateway.data.RlsPriceCondRow)
     */
    public PriceCondOrder createPriceCondOrder(RlsCondOrderRow l_rlsCondOrderRow, RlsPriceCondRow l_rlsPriceCondRow)
    {
        //�A�J�E���gID
        long l_accountId = l_rlsCondOrderRow.getAccountId();

        //�����t������ID
        long l_condOrderId = l_rlsCondOrderRow.getId();

        //��l
        Price l_price = new Price(l_rlsPriceCondRow.getPrice());

        //��������
        PriceDirection l_priceDirection = (PriceDirection)PriceDirection.getEnum((int)l_rlsPriceCondRow.getDirection());

        //ticker
        Ticker l_ticker = new Ticker(l_rlsPriceCondRow.getProdId(), l_rlsPriceCondRow.getMarketId());

        //�t�w�l����
        PriceCondition l_priceCondition = new PriceCondition(l_price, l_priceDirection, l_ticker);

        //�t�w�l����
        PriceCondOrder l_priceCondOrder = new PriceCondOrder(l_condOrderId, l_accountId, l_priceCondition);

        //�t�w�l������Ԃ�
        return l_priceCondOrder;
    }

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorHelpler#createSimpleCondOrder(webbroker3.rlsgateway.data.RlsCondOrderRow, webbroker3.rlsgateway.data.RlsOmsOrderRow)
     */
    public SimpleCondOrder createSimpleCondOrder(RlsCondOrderRow l_rlsCondOrderRow, RlsOmsOrderRow l_rlsOmsOrderRow)
    {
        //�A�J�E���gID
        long l_accountId = l_rlsCondOrderRow.getAccountId();

        //�����t������ID
        long l_condOrderId = l_rlsCondOrderRow.getId();

        //oms_order�̈�ӏ���
        OrderId l_orderId = new OrderId(l_rlsOmsOrderRow.getOrdId(), (OrderType)OrderType.getEnum((int)l_rlsOmsOrderRow.getOrdType()));

        //�����敪
        Side l_side = (Side)Side.getEnum((int)l_rlsOmsOrderRow.getSide());

        //�����P��
        Price l_price = new Price(l_rlsOmsOrderRow.getPrice());

        //�I���W�i������
        Quantity l_originalQuantity = new Quantity(l_rlsOmsOrderRow.getOrigQty());

        //��萔��
        Quantity l_execQuantity = new Quantity(0);

        //�v�Z�p
        Quantity l_tempQuantity = new Quantity(l_originalQuantity);

        //����萔��
        Quantity l_quantity = (Quantity)l_tempQuantity.subtruct(l_execQuantity);

        //ticker
        Ticker l_ticker = new Ticker(l_rlsOmsOrderRow.getProdId(), l_rlsOmsOrderRow.getMarketId());

        //���s����
        OrderExecType l_orderExecType = OrderExecType.NO_CONDITION;

        //�������e
        Order l_order = new OrderImpl(l_orderId, l_accountId, l_side, l_price, l_quantity,
                                    l_ticker, l_orderExecType, l_execQuantity, l_originalQuantity);

        //�V���v������
        SimpleCondOrder l_simpleCondOrder = new SimpleCondOrder(l_condOrderId, l_accountId, l_order);

        //�V���v��������Ԃ�
        return l_simpleCondOrder;
    }

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorHelpler#addDbOrders(webbroker3.rlsgateway.data.OmsConOrderRequestRow, webbroker3.rlsgateway.connector.WEB3RlsOrders)
     */
    public WEB3RlsOrders addDbOrders(OmsConOrderRequestRow l_request, WEB3RlsOrders l_rlsOrders) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "addDbOrders(OmsConOrderRequestRow, WEB3RlsOrders)";

        try
        {
            int l_intOrderType = l_request.getOrderType();

            //�������e���擾
            RlsOmsOrderParams l_rlsOmsOrderParams = (RlsOmsOrderParams)l_rlsOrders.getRlsOmsOrderRow();
            RlsCondOrderParams l_rlsCondOrderParams = (RlsCondOrderParams)l_rlsOrders.getRlsCondOrderRow();
            RlsCondOrderParams l_parentRlsCondOrderParams = (RlsCondOrderParams)l_rlsOrders.getParentRlsCondOrderRow();

            //RlsOmsOrderRow���擾
            RlsOmsOrderRow l_dbRlsOmsOrderRow = RlsOmsOrderDao.findRowByPk(l_rlsOmsOrderParams.getOrdId(), l_rlsOmsOrderParams.getOrdType());

            //�q������RlsCondOrderRow���擾
            RlsCondOrderRow l_dbRlsCondOrderRow = RlsCondOrderDao.findRowByPk(l_rlsCondOrderParams.getId());

            //�e������RlsCondOrderRow���擾
            RlsCondOrderRow l_dbParentRlsCondOrderRow = RlsCondOrderDao.findRowByPk(l_parentRlsCondOrderParams.getId());

            //�t�w�l�̏ꍇ||W�w�l�̏ꍇ
            if(WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_intOrderType||WEB3RlsNotifyOrderTypeDef.W_LlIMIT == l_intOrderType)
            {
                RlsPriceCondParams l_dbRlsPriceCondParams = (RlsPriceCondParams)RlsPriceCondDao.findRowByPk(l_parentRlsCondOrderParams.getId());
                //�����ꗗ�ɒǉ�
                l_rlsOrders.setOldRlsPriceCondRow(l_dbRlsPriceCondParams);
            }
            else
            {
                throw new IllegalArgumentException("No implementation. [OrderType=" + l_intOrderType + "]");
            }

            //�����ꗗ�ɒǉ�
            l_rlsOrders.setOldParentRlsCondOrderRow(l_dbParentRlsCondOrderRow);
            l_rlsOrders.setOldRlsCondOrderRow(l_dbRlsCondOrderRow);
            l_rlsOrders.setOldRlsOmsOrderRow(l_dbRlsOmsOrderRow);
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        return l_rlsOrders;
    }

    /**
     * @@see webbroker3.rlsgateway.connector.WEB3RlsConnectorHelpler#createRlsOrders(webbroker3.rlsgateway.data.OmsConOrderRequestRow, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit)
     */
    public WEB3RlsOrders createRlsOrders(OmsConOrderRequestRow l_request, OrderUnit l_unit)
    {
        int l_intOrderType = l_request.getOrderType();

        WEB3RlsOrders l_rlsOrders = new WEB3RlsOrders();


        Object l_rowObject = l_unit.getDataSourceObject();

        //����
        if(l_rowObject instanceof EqtypeOrderUnitRow)
        {
            EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_rowObject;

            long productId = WEB3RlsConnectorUtil.toRlsProductId(l_row.getProductId());
            long marketId = WEB3RlsConnectorUtil.toRlsMarketId(l_row.getMarketId());

            //�V���v������
            RlsCondOrderParams l_rlsCondOrderParams = new RlsCondOrderParams();
            l_rlsCondOrderParams.setAccountId(l_row.getAccountId());
            l_rlsCondOrderParams.setType(CondOrderType.SIMPLE.toValue());
            long l_simpleCondOrderId = WEB3RlsConnectorUtil.toRlsCondOrderId(l_row.getProductType(), CondOrderType.SIMPLE, l_row.getOrderId());
            l_rlsCondOrderParams.setId(l_simpleCondOrderId);
            //�����ꗗ�ɒǉ�
            l_rlsOrders.setRlsCondOrderRow(l_rlsCondOrderParams);

            //�V���v���������e
            RlsOmsOrderParams l_rlsOmsOrderParams = new RlsOmsOrderParams();
            l_rlsOmsOrderParams.setAccountId(l_row.getAccountId());
            l_rlsOmsOrderParams.setOrdId(l_row.getOrderId());
            l_rlsOmsOrderParams.setProdId(productId);
            l_rlsOmsOrderParams.setMarketId(marketId);
            l_rlsOmsOrderParams.setOrdType(WEB3RlsConnectorUtil.toOrderType(l_row.getOrderType().intValue()).toValue());
            l_rlsOmsOrderParams.setExecType(OrderExecType.NO_CONDITION.toValue());
            l_rlsOmsOrderParams.setSide(WEB3RlsConnectorUtil.toSide(l_row.getOrderType().intValue()).toValue());
            l_rlsOmsOrderParams.setOrigQty((long)l_row.getQuantity());
            l_rlsOmsOrderParams.setPrice((long)l_row.getPrice());
            //�����ꗗ�ɒǉ�
            l_rlsOrders.setRlsOmsOrderRow(l_rlsOmsOrderParams);

            //�t�w�l�̏ꍇ||W�w�l�̏ꍇ
            if(WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_intOrderType||WEB3RlsNotifyOrderTypeDef.W_LlIMIT == l_intOrderType)
            {
                //�t�w�l����
                RlsCondOrderParams l_parentRlsCondOrderParams = new RlsCondOrderParams();
                l_parentRlsCondOrderParams.setAccountId(l_row.getAccountId());
                l_parentRlsCondOrderParams.setType(CondOrderType.PRICE.toValue());
                long l_priceCondOrderId = WEB3RlsConnectorUtil.toRlsCondOrderId(l_row.getProductType(), CondOrderType.PRICE, l_row.getOrderId());
                l_parentRlsCondOrderParams.setId(l_priceCondOrderId);
                //�����ꗗ�ɒǉ�
                l_rlsOrders.setParentRlsCondOrderRow(l_parentRlsCondOrderParams);

                //�t�w�l����
                RlsPriceCondParams l_rlsPriceCondParams = new RlsPriceCondParams();
                l_rlsPriceCondParams.setPrice((long)l_row.getStopOrderPrice());
                l_rlsPriceCondParams.setProdId(productId);
                l_rlsPriceCondParams.setMarketId(marketId);
                l_rlsPriceCondParams.setDirection(Long.parseLong(l_row.getOrderCondOperator()));
                //�����ꗗ�ɒǉ�
                l_rlsOrders.setRlsPriceCondRow(l_rlsPriceCondParams);
            }
            else
            {
                throw new IllegalArgumentException("No implementation. [OrderType=" + l_intOrderType + "]");
            }
        }
        //IFO
        else if(l_rowObject instanceof IfoOrderUnitRow)
        {
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_rowObject;

            long productId = WEB3RlsConnectorUtil.toRlsProductId(l_row.getProductId());
            long marketId = WEB3RlsConnectorUtil.toRlsMarketId(l_row.getMarketId());

            //�V���v������
            RlsCondOrderParams l_rlsCondOrderParams = new RlsCondOrderParams();
            l_rlsCondOrderParams.setAccountId(l_row.getAccountId());
            l_rlsCondOrderParams.setType(CondOrderType.SIMPLE.toValue());
            long l_simpleCondOrderId = WEB3RlsConnectorUtil.toRlsCondOrderId(l_row.getProductType(), CondOrderType.SIMPLE, l_row.getOrderId());
            l_rlsCondOrderParams.setId(l_simpleCondOrderId);
            //�����ꗗ�ɒǉ�
            l_rlsOrders.setRlsCondOrderRow(l_rlsCondOrderParams);

            //�V���v���������e
            RlsOmsOrderParams l_rlsOmsOrderParams = new RlsOmsOrderParams();
            l_rlsOmsOrderParams.setAccountId(l_row.getAccountId());
            l_rlsOmsOrderParams.setOrdId(l_row.getOrderId());
            l_rlsOmsOrderParams.setProdId(productId);
            l_rlsOmsOrderParams.setMarketId(marketId);
            l_rlsOmsOrderParams.setOrdType(WEB3RlsConnectorUtil.toOrderType(l_row.getOrderType().intValue()).toValue());
            l_rlsOmsOrderParams.setExecType(OrderExecType.NO_CONDITION.toValue());
            l_rlsOmsOrderParams.setSide(WEB3RlsConnectorUtil.toSide(l_row.getOrderType().intValue()).toValue());
            l_rlsOmsOrderParams.setOrigQty((long)l_row.getQuantity());
            l_rlsOmsOrderParams.setPrice((long)l_row.getPrice());
            //�����ꗗ�ɒǉ�
            l_rlsOrders.setRlsOmsOrderRow(l_rlsOmsOrderParams);

            //�t�w�l�̏ꍇ||W�w�l�̏ꍇ
            if(WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT == l_intOrderType||WEB3RlsNotifyOrderTypeDef.W_LlIMIT == l_intOrderType)
            {
                //�t�w�l����
                RlsCondOrderParams l_parentRlsCondOrderParams = new RlsCondOrderParams();
                l_parentRlsCondOrderParams.setAccountId(l_row.getAccountId());
                l_parentRlsCondOrderParams.setType(CondOrderType.PRICE.toValue());
                long l_priceCondOrderId = WEB3RlsConnectorUtil.toRlsCondOrderId(l_row.getProductType(), CondOrderType.PRICE, l_row.getOrderId());
                l_parentRlsCondOrderParams.setId(l_priceCondOrderId);
                //�����ꗗ�ɒǉ�
                l_rlsOrders.setParentRlsCondOrderRow(l_parentRlsCondOrderParams);

                //�t�w�l����
                RlsPriceCondParams l_rlsPriceCondParams = new RlsPriceCondParams();
                l_rlsPriceCondParams.setPrice((long)l_row.getStopOrderPrice());
                l_rlsPriceCondParams.setProdId(productId);
                l_rlsPriceCondParams.setMarketId(marketId);
                l_rlsPriceCondParams.setDirection(Long.parseLong(l_row.getOrderCondOperator()));
                //�����ꗗ�ɒǉ�
                l_rlsOrders.setRlsPriceCondRow(l_rlsPriceCondParams);
            }
            else
            {
                throw new IllegalArgumentException("No implementation. [OrderType=" + l_intOrderType + "]");
            }
        }
        else
        {
            throw new IllegalArgumentException("No implementation. [ClassName=" + l_rowObject.getClass().getName() + "]");
        }
        return l_rlsOrders;

    }
}
@
