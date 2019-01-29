head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.57.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccIfoOrderUnitImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�\�񒍕��P��Impl(WEB3ToSuccIfoOrderUnitImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/11 ��іQ(���u) �V�K�쐬 ���f��No.250,252
*/

package webbroker3.triggerorder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccMessageTradingTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨OP�\�񒍕��P��Impl)<BR>
 * �敨OP�\�񒍕��P�ʃe�[�u����1-Row��\������N���X�B<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitImpl implements IfoOrderUnit
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderUnitImpl.class);

    /**
     * �敨OP�\�񒍕��P��Row<BR>
     */
    private RsvIfoOrderUnitRow rsvIfoOrderUnitRow;

    /**
     * (�敨OP�\�񒍕��P��Impl)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3ToSuccIfoOrderUnitImpl(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow)
    {
        this.rsvIfoOrderUnitRow = l_rsvIfoOrderUnitRow;
    }

    /**
     * �igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row��ԋp����B<BR>
     * @@return Object
     * @@roseuid 431E765B01F0
     */
    public Object getDataSourceObject()
    {
        return this.rsvIfoOrderUnitRow;
    }

    /**
     * (is�w�l����)<BR>
     * �iisLimitOrder�j<BR>
     * <BR>
     * �w�l�����ł����true���A���s�����ł����false��Ԃ��B<BR>
     * <BR>
     * �ithis.�敨OP�\�񒍕��P��Row.�w�l != 0�A�܂���<BR>
     * this.�敨OP�\�񒍕��P��Row.�P�������l != null�j<BR>
     * �ł����true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4321814201CC
     */
    public boolean isLimitOrder()
    {
        final String STR_METHOD_NAME = "isLimitOrder()";
        log.entering(STR_METHOD_NAME);

        //this.�敨OP�\�񒍕��P��Row.�w�l != 0�A�܂���
        //this.�敨OP�\�񒍕��P��Row.�P�������l != null
        if (this.rsvIfoOrderUnitRow.getLimitPrice() != 0
            || !this.rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is���s����)<BR>
     * �iisMarketOrder�̎����j<BR>
     * <BR>
     * ���s�����ł����true���A�w�l�����ł����false��Ԃ��B<BR>
     * <BR>
     * this.is�w�l����()�̖߂�l�𔽓]���ĕԂ��B<BR>
     * �itrue�̏ꍇ��false���Afalse�̏ꍇ��true��Ԃ��j<BR>
     * @@return boolean
     * @@roseuid 432194D40380
     */
    public boolean isMarketOrder()
    {
        final String STR_METHOD_NAME = "isMarketOrder()";
        log.entering(STR_METHOD_NAME);

        //this.is�w�l����()�̖߂�l�𔽓]���ĕԂ��B
        //true�̏ꍇ��false���Afalse�̏ꍇ��true��Ԃ�
        if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (get�����P��ID)<BR>
     * �igetOrderUnitId�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����P��ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 43218C5F01FB
     */
    public long getOrderUnitId()
    {
        return this.rsvIfoOrderUnitRow.getOrderUnitId();
    }

    /**
     * (get����ID)<BR>
     * �igetOrderId�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.����ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 43218C5F021B
     */
    public long getOrderId()
    {
        return this.rsvIfoOrderUnitRow.getOrderId();
    }

    /**
     * (get����ID)<BR>
     * �igetAccountId�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.����ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 43218C5F023A
     */
    public long getAccountId()
    {
        return this.rsvIfoOrderUnitRow.getAccountId();
    }

    /**
     * (get�⏕����ID)<BR>
     * �igetSubAccountId�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�⏕����ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 43218C5F0259
     */
    public long getSubAccountId()
    {
        return this.rsvIfoOrderUnitRow.getSubAccountId();
    }

    /**
     * (get���XID)<BR>
     * �igetBranchId�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.���XID��ԋp����B<BR>
     * @@return long
     * @@roseuid 43218C5F0278
     */
    public long getBranchId()
    {
        return this.rsvIfoOrderUnitRow.getBranchId();
    }

    /**
     * (get�����ID)<BR>
     * �igetTraderId�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 43218C5F0298
     */
    public long getTraderId()
    {
        return this.rsvIfoOrderUnitRow.getTraderId();
    }

    /**
     * (is�I����)<BR>
     * �iisExpired�̎����j<BR>
     * <BR>
     * �������o���I���^�����ɂ��I���ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����敪 = "�I�[�v��"�̏ꍇ�́A<BR>
     * false��ԋp����B<BR>
     * <BR>
     * �ȊO�Atrue��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C5F02C6
     */
    public boolean isExpired()
    {
        final String STR_METHOD_NAME = "isExpired()";
        log.entering(STR_METHOD_NAME);

        //this.�敨OP�\�񒍕��P��Row.�����敪 = "�I�[�v��"�̏ꍇ��
        if (OrderExpirationStatusEnum.OPEN.equals(
            this.rsvIfoOrderUnitRow.getExpirationStatus()))
        {
            //false��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * �i�������j<BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C5F02E6
     */
    public boolean isFullyExecuted()
    {
        return false;
    }

    /**
     * �i�������j<BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C5F0305
     */
    public boolean isPartiallyExecuted()
    {
        return false;
    }

    /**
     * �i�������j<BR>
     * true��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C5F0324
     */
    public boolean isUnexecuted()
    {
        return true;
    }

    /**
     * �i�������j<BR>
     * 0��ԋp����B<BR>
     * @@return double
     * @@roseuid 43218C5F0343
     */
    public double getConfirmedPrice()
    {
        return 0;
    }

    /**
     * �i�������j<BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C5F0363
     */
    public boolean isConfirmedPriceMarketOrder()
    {
        return false;
    }

    /**
     * �i�������j<BR>
     * 0��ԋp����B<BR>
     * @@return double
     * @@roseuid 43218C5F0382
     */
    public double getConfirmedQuantity()
    {
        return 0;
    }

    /**
     * (get��������)<BR>
     * �igetQuantity�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�������ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 43218C5F03A1
     */
    public double getQuantity()
    {
        return this.rsvIfoOrderUnitRow.getQuantity();
    }

    /**
     * �i�������j<BR>
     * 0��ԋp����B<BR>
     * @@return double
     * @@roseuid 43218C5F03C0
     */
    public double getExecutedAmount()
    {
        return 0;
    }

    /**
     * �i�������j<BR>
     * 0��ԋp����B<BR>
     * @@return double
     * @@roseuid 43218C600007
     */
    public double getExecutedQuantity()
    {
        return 0;
    }

    /**
     * (get�w�l)<BR>
     * �igetLimitPrice�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�w�l��ԋp����B<BR>
     * @@return double
     * @@roseuid 43218C600027
     */
    public double getLimitPrice()
    {
        if (this.rsvIfoOrderUnitRow.getLimitPriceIsNull())
        {
            return Double.NaN;
        }

        return this.rsvIfoOrderUnitRow.getLimitPrice();
    }

    /**
     * �i�������j<BR>
     * null��ԋp����B<BR>
     * @@return OrderAction[]
     * @@roseuid 43218C600046
     */
    public OrderAction[] getOrderActions()
    {
        return null;
    }

    /**
     * (get�敨OP�\�񒍕�����)<BR>
     * �igetRsvIfoOrderActions�j<BR>
     * <BR>
     * �敨OP�\�񒍕������e�[�u�����A�ȉ��̏����ɍ��v���郌�R�[�h��<BR>
     * ��������ԍ� �����Ŏ擾���ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * ����ID = this.�敨OP�\�񒍕��P��Row.����ID<BR>
     * @@return RsvIfoOrderActionRow[]
     * @@throws WEB3BaseException
     * @@roseuid 4337B448010C
     */
    public RsvIfoOrderActionRow[] getRsvIfoOrderActions() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRsvIfoOrderActions()";
        log.entering(STR_METHOD_NAME);

        //[����]
        //����ID = this.�敨OP�\�񒍕��P��Row.����ID
        String l_strWhere = " order_id = ? ";
        Object[] l_objs = {new Long(this.rsvIfoOrderUnitRow.getOrderId())};

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    l_strWhere,
                    "order_action_serial_no asc",
                    null,
                    l_objs);

            RsvIfoOrderActionRow[] l_rsvIfoActionRows = null;

            if (l_lisRsvIfoOrderActionRows != null && !l_lisRsvIfoOrderActionRows.isEmpty())
            {
                l_rsvIfoActionRows = new RsvIfoOrderActionRow[l_lisRsvIfoOrderActionRows.size()];

                l_lisRsvIfoOrderActionRows.toArray(l_rsvIfoActionRows);
            }

            log.exiting(STR_METHOD_NAME);
            return l_rsvIfoActionRows;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get�쐬����)<BR>
     * �igetReceivedTimestamp�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�쐬���t��ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 43218C600084
     */
    public Timestamp getReceivedTimestamp()
    {
        return this.rsvIfoOrderUnitRow.getCreatedTimestamp();
    }

    /**
     * (get�����������t)<BR>
     * �igetExpirationTimestamp�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����������t��ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 43218C6000A4
     */
    public Timestamp getExpirationTimestamp()
    {
        return this.rsvIfoOrderUnitRow.getExpirationDate();
    }

    /**
     * (get����)<BR>
     * �igetProduct�̎����j<BR>
     * <BR>
     * �敨OP�v���_�N�g�}�l�[�W��.getProduct()�ɂ��A<BR>
     * this.�敨OP�\�񒍕��P��Row.����ID�ɊY������<BR>
     * �敨OP�����I�u�W�F�N�g���擾���ԋp����B<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * ����ID�@@�Fthis.�敨OP�\�񒍕��P��Row.����ID<BR>
     * @@return Product
     * @@roseuid 43218C6000C3
     */
    public Product getProduct()
    {
        final String STR_METHOD_NAME = "getProduct()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        try
        {
            //�敨OP�v���_�N�g�}�l�[�W��.getProduct()�ɂ��Athis.�敨OP�\�񒍕��P��Row.����ID�ɊY������
            Product l_product =
                l_productManager.getProduct(this.rsvIfoOrderUnitRow.getProductId());

            //�敨OP�����I�u�W�F�N�g���擾���ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_product;
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * �i�������j<BR>
     * null��ԋp����B<BR>
     * @@return Order
     * @@roseuid 43218C6000E2
     */
    public Order getOrder()
    {
        return null;
    }

    /**
     * �i�������j<BR>
     * null��ԋp����B<BR>
     * @@return OrderExecution[]
     * @@roseuid 43218C600101
     */
    public OrderExecution[] getExecutions()
    {
        return null;
    }

    /**
     * (get�����L�����)<BR>
     * �igetOrderOpenStatus�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����L����Ԃ�ԋp����B<BR>
     * @@return OrderOpenStatusEnum
     * @@roseuid 43218C600140
     */
    public OrderOpenStatusEnum getOrderOpenStatus()
    {
        return this.rsvIfoOrderUnitRow.getOrderOpenStatus();
    }

    /**
     * (get�������)<BR>
     * �igetOrderType�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.������ʂ�ԋp����B<BR>
     * @@return OrderTypeEnum
     * @@roseuid 43218C60015F
     */
    public OrderTypeEnum getOrderType()
    {
        return this.rsvIfoOrderUnitRow.getOrderType();
    }

    /**
     * (get�����J�e�S��)<BR>
     * �igetOrderCateg�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����J�e�S����ԋp����B<BR>
     * @@return OrderCategEnum
     * @@roseuid 43218C60017E
     */
    public OrderCategEnum getOrderCateg()
    {
        return this.rsvIfoOrderUnitRow.getOrderCateg();
    }

    /**
     * (get�ŋ敪)<BR>
     * �igetTaxType�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�ŋ敪��ԋp����B<BR>
     * @@return TaxTypeEnum
     * @@roseuid 43218C60019E
     */
    public TaxTypeEnum getTaxType()
    {
        return this.rsvIfoOrderUnitRow.getTaxType();
    }

    /**
     * (get����)<BR>
     * �igetSide�̎����j<BR>
     * <BR>
     * SideEnum.getSide()�̖߂�l��ԋp����B<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * ������ʁ@@�Fthis.�敨OP�\�񒍕��P��Row.�������<BR>
     * @@return SideEnum
     * @@roseuid 43218C6001BD
     */
    public SideEnum getSide()
    {
        return SideEnum.getSide(this.rsvIfoOrderUnitRow.getOrderType());
    }

    /**
     * (get�������)<BR>
     * �igetOrderStatus�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.������Ԃ�ԋp����B<BR>
     * @@return OrderStatusEnum
     * @@roseuid 43218C6001DC
     */
    public OrderStatusEnum getOrderStatus()
    {
        return this.rsvIfoOrderUnitRow.getOrderStatus();
    }

    /**
     * (get�����敪)<BR>
     * �igetExpirationStatus�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����敪��ԋp����B<BR>
     * @@return OrderExpirationStatusEnum
     * @@roseuid 43218C6001FB
     */
    public OrderExpirationStatusEnum getExpirationStatus()
    {
        return this.rsvIfoOrderUnitRow.getExpirationStatus();
    }

    /**
     * �i�������j<BR>
     * null��ԋp����B<BR>
     * @@return Date
     * @@roseuid 43218C60022A
     */
    public Date getDeliveryDate()
    {
        return null;
    }

    /**
     * (get�������)<BR>
     * �igetTradedProduct�̎����j<BR>
     * <BR>
     * �敨OP�v���_�N�g�}�l�[�W��.getTradedProduct()�ɂ��A<BR>
     * this.�敨OP�\�񒍕��P��Row.����ID,�s��ID�ɊY������<BR>
     * �敨��������I�u�W�F�N�g���擾���ԋp����B<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * ����ID�@@�Fthis.�敨OP�\�񒍕��P��Row.����ID<BR>
     * �s��ID�@@�Fthis.�敨OP�\�񒍕��P��Row.�s��ID<BR>
     * @@return TradedProduct
     * @@roseuid 43218C600249
     */
    public TradedProduct getTradedProduct()
    {
        final String STR_METHOD_NAME = "getTradedProduct() ";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3IfoProductManagerImpl l_productManager =
            (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();

        try
        {
            //�敨OP�v���_�N�g�}�l�[�W��.getTradedProduct()�ɂ��A
            //this.�敨OP�\�񒍕��P��Row.����ID,�s��ID�ɊY������
            TradedProduct l_tradedProduct =
                l_productManager.getTradedProduct(
                    this.rsvIfoOrderUnitRow.getProductId(),
                    this.rsvIfoOrderUnitRow.getMarketId());

            //�敨��������I�u�W�F�N�g���擾���ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_tradedProduct;
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (is�敨����)<BR>
     * �iisFuturesOrder�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ�A<BR>
     * �ł����true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C600257
     */
    public boolean isFuturesOrder()
    {
        final String STR_METHOD_NAME = "isFuturesOrder()";
        log.entering(STR_METHOD_NAME);
        //this.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ�A
        if (WEB3FuturesOptionDivDef.FUTURES.equals(this.rsvIfoOrderUnitRow.getFutureOptionDiv()))
        {
            //�ł����true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isOP����)<BR>
     * �iisOptionsOrder�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪��"�I�v�V����"�̏ꍇ�A<BR>
     * �ł����true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C6055468
     */
    public boolean isOptionsOrder()
    {
        final String STR_METHOD_NAME = "isOptionsOrder()";
        log.entering(STR_METHOD_NAME);
        //this.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪��"�I�v�V����"�̏ꍇ
        if (WEB3FuturesOptionDivDef.OPTION.equals(this.rsvIfoOrderUnitRow.getFutureOptionDiv()))
        {
            //�ł����true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�R�[��OP����)<BR>
     * (isCallOptionsOrder)<BR>
     * <BR>
     * �I�v�V�����̃R�[�������ł����true���A<BR>
     * ����ȊO�ł����false��Ԃ��B<BR>
     * <BR>
     * �@@��this.isOP����()��true�̏ꍇ<BR>
     * �@@�@@this.get����()�̖߂�l�I�u�W�F�N�g.�敨�I�v�V�������i=="�R�[���I�v�V����"<BR>
     * �@@�@@�ł���ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@����L�ȊO�̏ꍇ<BR>
     * �@@�@@false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C6055425
     */
    public boolean isCallOptionsOrder()
    {
        final String STR_METHOD_NAME = "isCallOptionsOrder()";
        log.entering(STR_METHOD_NAME);

        //this.isOP����()��true�̏ꍇ
        if (this.isOptionsOrder())
        {
            //this.get����()�̖߂�l�I�u�W�F�N�g.�敨�I�v�V�������i=="�R�[���I�v�V����"
            IfoProductRow l_ifoProductRow = (IfoProductRow)this.getProduct().getDataSourceObject();

            if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                //true��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //��L�ȊO�̏ꍇfalse��ԋp����
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�v�b�gOP����)<BR>
     * �iisPutOptionsOrder�j<BR>
     * <BR>
     * �I�v�V�����̃v�b�g�����ł����true���A<BR>
     * ����ȊO�ł����false��Ԃ��B<BR>
     * <BR>
     * �@@��this.isOP����()��true�̏ꍇ<BR>
     * �@@�@@this.get����()�̖߂�l�I�u�W�F�N�g.�敨�I�v�V�������i=="�v�b�g�I�v�V����" <BR>
     * �@@�@@�ł���ꍇ�Atrue��ԋp����B<BR>
     * <BR>
     * �@@����L�ȊO�̏ꍇ<BR>
     * �@@�@@false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C6025468
     */
    public boolean isPutOptionsOrder()
    {
        final String STR_METHOD_NAME = "isPutOptionsOrder()";
        log.entering(STR_METHOD_NAME);

        //this.isOP����()��true�̏ꍇ
        if (this.isOptionsOrder())
        {
            //this.get����()�̖߂�l�I�u�W�F�N�g.�敨�I�v�V�������i=="�v�b�g�I�v�V����"
            IfoProductRow l_ifoProductRow = (IfoProductRow)this.getProduct().getDataSourceObject();

            if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
            {
                //true��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        //��L�ȊO�̏ꍇfalse��ԋp����
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�V�K������)<BR>
     * �iisOpenContractOrder�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����J�e�S����"�敨�V�K������"�̏ꍇ�A<BR>
     * �܂��� this.�敨OP�\�񒍕��P��Row.�����J�e�S����"OP�V�K������" <BR>
     * �ł����true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C6025125
     */
    public boolean isOpenContractOrder()
    {
        final String STR_METHOD_NAME = "isOpenContractOrder()";
        log.entering(STR_METHOD_NAME);

        //this.�敨OP�\�񒍕��P��Row.�����J�e�S����"�敨�V�K������"�̏ꍇ
        //�܂��� this.�敨OP�\�񒍕��P��Row.�����J�e�S����"OP�V�K������"
        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(this.rsvIfoOrderUnitRow.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(this.rsvIfoOrderUnitRow.getOrderCateg()))
        {
            //true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�ԍϒ���)<BR>
     * �iisSettleContractOrder�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����J�e�S����"�敨�ԍϒ���"�̏ꍇ�A<BR>
     * �܂��� this.�敨OP�\�񒍕��P��Row.�����J�e�S����"OP�ԍϒ���"<BR>
     * �ł����true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43218C6021455
     */
    public boolean isSettleContractOrder()
    {
        final String STR_METHOD_NAME = "isSettleContractOrder()";
        log.entering(STR_METHOD_NAME);

        //this.�敨OP�\�񒍕��P��Row.�����J�e�S����"�敨�ԍϒ���"�̏ꍇ
        //�܂��� this.�敨OP�\�񒍕��P��Row.�����J�e�S����"OP�ԍϒ���"
        if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg()))
        {
            //true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * �i�������j<BR>
     * "1:�����Ȃ�"��ԋp����B<BR>
     * @@return IfoOrderExecutionConditionType
     * @@roseuid 43218C6021457
     */
    public IfoOrderExecutionConditionType getExecutionConditionType()
    {
        return IfoOrderExecutionConditionType.NONE;
    }

    /**
     * (get�\�񒍕����s�P��)<BR>
     * �igetRsvOrderExecPrice�j<BR>
     * <BR>
     * �\�񒍕��i�q�����j�̎��s�P�����擾���ԋp����B<BR>
     * �@@�@@�E�w�l�����^���s�����̏ꍇ�i�}�w�l�w��Ȃ��̏ꍇ�j�A�w�l�܂���0��ԋp����B<BR>
     * �@@�@@�E�}�w�l�w�蒍���̏ꍇ�́A<BR>
     * �@@�@@�@@�e�����̎w�l�^�����ɒP�������l�����������P����ԋp����B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.get�敨OP�\�񒍕����s�P��()��delegate����B<BR>
     * �����ݒ�d�l�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * �����P�ʁ@@�@@�@@�@@�F�@@this.get�e�����̒����P��()<BR>
     * �w�l�@@�@@�@@�@@�@@�@@�@@�F�@@this.�敨OP�\�񒍕��P��Row.�w�l<BR>
     * �P�������l�@@�@@�@@�F�@@this.�敨OP�\�񒍕��P��Row.�P�������l�i���j<BR>
     * �敨OP��������F�@@this.get�������()<BR>
     * <BR>
     * �i���jnull�̏ꍇ�́Anull�����̂܂܃Z�b�g�B<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4324EDFB02B5
     */
    public double getRsvOrderExecPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRsvOrderExecPrice() ";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        Double l_priceAdjustValue = null;
        if (!this.rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
        {
            l_priceAdjustValue = new Double(this.rsvIfoOrderUnitRow.getPriceAdjustValue());
        }

        //�A�������}�l�[�W��Impl.get�敨OP�\�񒍕����s�P��()��delegate����B
        //[�����ݒ�]
        // �����P�ʁ@@�@@�@@�@@�F�@@this.get�e�����̒����P��()
        // �w�l�@@�@@�@@�@@�@@�@@�@@�F�@@this.�敨OP�\�񒍕��P��Row.�w�l
        // �P�������l�@@�@@�@@�F�@@this.�敨OP�\�񒍕��P��Row.�P�������l�i���j
        // �敨OP��������F�@@this.get�������()
        double l_dblRsvExecPrice = l_orderManager.getReserveIfoOrderExecPrice(
            this.getParentOrderUnit(),
            this.rsvIfoOrderUnitRow.getLimitPrice(),
            l_priceAdjustValue,
            (WEB3IfoTradedProductImpl)this.getTradedProduct());

        log.exiting(STR_METHOD_NAME);
        return l_dblRsvExecPrice;
    }

    /**
     * (is������)<BR>
     * �iisBuyOrder�j<BR>
     * <BR>
     * �������ł����"true"���A<BR>
     * �������ł����"false"��ԋp����B<BR>
     * <BR>
     * this.get����()=="��"�̏ꍇ��true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43250C1300BD
     */
    public boolean isBuyOrder()
    {
        final String STR_METHOD_NAME = "isBuyOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get����()=="��"�̏ꍇ��true��ԋp����B
        if (SideEnum.BUY.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is������)<BR>
     * �iisSellOrder�j<BR>
     * <BR>
     * �������ł����"true"���A<BR>
     * �������ł����"false"��ԋp����B<BR>
     * <BR>
     * this.get����()=="��"�̏ꍇ��true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43250C76011B
     */
    public boolean isSellOrder()
    {
        final String STR_METHOD_NAME = "isSellOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get����()=="��"�̏ꍇ��true��ԋp����B
        if (SideEnum.SELL.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�}�w�l�w��)<BR>
     * (isExecPriceOrder)<BR>
     * <BR>
     * �}�w�l���w�肳��Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�P�������l��null �̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 43253BA10395
     */
    public boolean isExecPriceOrder()
    {
        final String STR_METHOD_NAME = "isExecPriceOrder()";
        log.entering(STR_METHOD_NAME);

        //this.�敨OP�\�񒍕��P��Row.�P�������l��null �̏ꍇ�A
        if (!this.rsvIfoOrderUnitRow.getPriceAdjustValueIsNull())
        {
            //true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (get�����^�C�v)<BR>
     * �igetProductType�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����^�C�v��ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 4326377F03D3
     */
    public ProductTypeEnum getProductType()
    {
        return this.rsvIfoOrderUnitRow.getProductType();
    }

    /**
     * (get�s��)<BR>
     * �igetMarket�̎����j<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�s��ID�ɊY������s��I�u�W�F�N�g���擾���ԋp����B<BR>
     * <BR>
     * �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket()<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * �s��ID�@@�Fthis.�敨OP�\�񒍕��P��Row.�s��ID<BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 433B716B0354
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        if (this.rsvIfoOrderUnitRow.getMarketIdIsNull())
        {
            return null;
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //this.�敨OP�\�񒍕��P��Row.�s��ID�ɊY������s��I�u�W�F�N�g���擾���ԋp����B
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finObjManager.getMarket(
                this.rsvIfoOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (get�e�����̒����P��)<BR>
     * (getParentOrderUnit)<BR>
     * <BR>
     * �e�����̒����P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.get�e�����̒����P��()��delegate����B<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * �����^�C�v�@@�F"�敨�I�v�V����" <BR>
     * ����ID�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�e�����̒���ID<BR>
     * @@return IfoOrderUnit
     * @@roseuid 43279DFC00A8
     */
    public IfoOrderUnit getParentOrderUnit()
    {
        final String STR_METHOD_NAME = "getParentOrderUnit()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //�A�������}�l�[�W��Impl.get�e�����̒����P��()��delegate����B
        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderManager.getParentOrderUnit(
            ProductTypeEnum.IFO,
            this.rsvIfoOrderUnitRow.getParentOrderId());

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (is���Δ������)<BR>
     * �iisReversingTrade�j<BR>
     * <BR>
     * ���Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.is���Δ������()��delegate����B<BR>
     * <BR>
     * [�����ݒ�]<BR>
     * �A����������敪�Fthis.�敨OP�\�񒍕��P��Row.�A����������敪<BR>
     * �����P�ʁ@@�@@�@@�@@�Fthis.get�e�����̒����P��<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43279F53003A
     */
    public boolean isReversingTrade() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isReversingTrade()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        //�A�������}�l�[�W��Impl.is���Δ������()��delegate����B
        //[�����ݒ�]
        // �A����������敪�Fthis.�敨OP�\�񒍕��P��Row.�A����������敪
        // �����P��             �Fthis.get�e�����̒����P��
        boolean l_blnIsOppoDealingTrade = l_orderManager.isReversingTrade(
            this.rsvIfoOrderUnitRow.getReserveOrderTradingType(),
            this.getParentOrderUnit());

        log.exiting(STR_METHOD_NAME);
        return l_blnIsOppoDealingTrade;
    }

    /**
     * (get�敨OP�\�񌚋ʕԍώw����ꗗ)<BR>
     * �igetContractsToClose�j<BR>
     * <BR>
     * �\�񒍕��i�q�����j�́A�敨OP�\�񌚋ʕԍώw����̍s�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �@@�E�\�񒍕����A�������ɑ΂��錈�ϒ����̏ꍇ�A<BR>
     * �@@�@@�敨OP�\�񌚋ʕԍώw����e�[�u���ɓo�^����Ă��郌�R�[�h���擾���Ԃ��B<BR>
     * <BR>
     * �@@�E�\�񒍕����A�e�����ɑ΂��锽�Ύ���̌��ϒ����̏ꍇ�A<BR>
     * �@@�@@�e�����������^�ꕔ���ł���΁A<BR>
     * �@@�@@���z�̐敨OP�\�񌚋ʕԍώw����s�I�u�W�F�N�g[]���쐬���Ԃ��B<BR>
     * <BR>
     * �@@�@@�e�������S����肵�Ă���ꍇ�́A<BR>
     * �@@�@@�e�����̖��ɂ��쐬���ꂽ���ʂ����ɂ��āA<BR>
     * �@@�@@���z�̐敨OP�\�񌚋ʕԍώw����s�I�u�W�F�N�g[]���쐬���Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�A�������jget�敨OP�\�񌚋ʕԍώw����ꗗ�v�Q�ƁB<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�i�A�������jget�敨OP�\�񌚋ʕԍώw����ꗗ) <BR>
     * ��̈ʒu�Fthis.�敨OP�\�񒍕��P��Row.�����J�e�S����<BR>
     * �@@�@@�@@�@@�@@���L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@OrderCategEnum."�敨�ԍϒ���"<BR>
     * �@@�@@�@@�@@�@@�@@�@@OrderCategEnum."OP�ԍϒ���"<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00653 <BR>
     * ========================================================== <BR>
     * <BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F(�i�A�������jget�敨OP�\�񌚋ʕԍώw����ꗗ) <BR>
     * ��̈ʒu�F�e�̖�茚�͂��邪�A�ԍϊ����\���ʕs���i�������ʁi�c���j������j�ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�ԍϐ��ʕs���̗�O��throw����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00299<BR>
     * ========================================================== <BR>
     * <BR>
     * @@return RsvIfoClosingContractSpecRow[]
     * @@throws WEB3BaseException
     * @@roseuid 433A4F820186
     */
    public RsvIfoClosingContractSpecRow[] getContractsToClose() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContractsToClose()";
        log.entering(STR_METHOD_NAME);

        //�����J�e�S���`�F�b�N
        //this.�敨OP�\�񒍕��P��Row.�����J�e�S����
        //���L�ȊO�̏ꍇ�́A��O��throw����B
        //OrderCategEnum."�敨�ԍϒ���"
        //OrderCategEnum."OP�ԍϒ���"
        if (!(OrderCategEnum.IDX_FUTURES_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderCateg())))
        {
            log.debug("�����J�e�S���̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�e�S���̒l���s���ł��B");
        }

        //is���Δ������( )
        boolean l_blnIsReserveTrade = this.isReversingTrade();

        //����t���[�F�@@�e�����ɑ΂��锽�Ύ���łȂ��iis���Δ�������i�j==false�j�̏ꍇ�j
        if (!l_blnIsReserveTrade)
        {
            //�ȉ��̏����ɂĐ敨OP�\�񌚋ʕԍώw����e�[�u����������
            //[����]
            //����ID = this.get����ID()
            //[�\�[�g����]
            //�ԍϘA�� ����
            String l_strWhere = " order_id = ? ";
            Object[] l_objs = {new Long(this.getOrderId())};

            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvIfoClosingContractSpecRows =
                    l_queryProcessor.doFindAllQuery(
                        RsvIfoClosingContractSpecRow.TYPE,
                        l_strWhere,
                        " closing_serial_no asc ",
                        null,
                        l_objs);

                if (l_lisRsvIfoClosingContractSpecRows != null
                    && !l_lisRsvIfoClosingContractSpecRows.isEmpty())
                {
                    RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows =
                        new RsvIfoClosingContractSpecRow[l_lisRsvIfoClosingContractSpecRows.size()];
                    l_lisRsvIfoClosingContractSpecRows.toArray(l_rsvIfoClosingContractSpecRows);

                    //�擾���ʂ�ԋp����B
                    log.exiting(STR_METHOD_NAME);
                    return l_rsvIfoClosingContractSpecRows;
                }

                //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //�i����t���[�F�@@�e�����ɑ΂��锽�Ύ���̏ꍇ
        //get�e�����̒����P��()
        IfoOrderUnit l_parentOrderUnit = this.getParentOrderUnit();

        //isFullyExecuted( )
        boolean l_blnIsFullyExecuted = l_parentOrderUnit.isFullyExecuted();

        //�����^�ꕔ���̏ꍇ�iisFullyExecuted()==false�j
        if (!l_blnIsFullyExecuted)
        {
            //�i�C���X�^���X�����A�v���p�e�B�Z�b�g�j
            //[�v���p�e�B�Z�b�g�d�l]
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                new RsvIfoClosingContractSpecParams();

            //����ID�F�@@this.�敨OP�\�񒍕��P��Row.����ID
            l_rsvIfoClosingContractSpecParams.setAccountId(
                this.rsvIfoOrderUnitRow.getAccountId());

            //�@@�⏕����ID�F�@@this.�敨OP�\�񒍕��P��Row.�⏕����ID
            l_rsvIfoClosingContractSpecParams.setSubAccountId(
                this.rsvIfoOrderUnitRow.getSubAccountId());

            //����ID�F�@@this.�敨OP�\�񒍕��P��Row.����ID
            l_rsvIfoClosingContractSpecParams.setOrderId(
                this.rsvIfoOrderUnitRow.getOrderId());

            //�@@����ID�F�@@0�i�Œ�j
            l_rsvIfoClosingContractSpecParams.setContractId(0);

            //�@@�ԍϘA�ԁF�@@1�i�Œ�j
            l_rsvIfoClosingContractSpecParams.setClosingSerialNo(1);

            //�ԍϒ������ʁF�@@this.�敨OP�\�񒍕��P��Row.��������
            l_rsvIfoClosingContractSpecParams.setQuantity(
                this.rsvIfoOrderUnitRow.getQuantity());

            // �@@�쐬���t�F�@@this.�敨OP�\�񒍕��P��Row.�쐬���t
            l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(
                this.rsvIfoOrderUnitRow.getCreatedTimestamp());

            //�X�V���t�F�@@this.�敨OP�\�񒍕��P��Row.�X�V���t
            l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(
                this.rsvIfoOrderUnitRow.getLastUpdatedTimestamp());

            //return �쐬�����\�񌚋ʕԍώw����s[]
            return new RsvIfoClosingContractSpecParams[]{l_rsvIfoClosingContractSpecParams};
        }

        //�S�����̏ꍇ�iisFullyExecuted()==true�j
        //get����ListBy�����P��(����ID : long)
        //����ID�F�@@�擾�����e�����̒����P��.����ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IfoPositionManagerImpl l_positionManager =
            (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();

        List l_lisContracts = l_positionManager.getContractListByOrderUnit(l_parentOrderUnit.getOrderId());

        //�i�擾�������ʂ�List���\�[�g����j
        int l_intCnt = 0;
        if (l_lisContracts != null && !l_lisContracts.isEmpty())
        {
            l_intCnt = l_lisContracts.size();
        }

        if (l_intCnt > 1)
        {
            int l_intFlag = 0;
            //this.�敨OP�\�񒍕��P��Row.���Ϗ���==�i"�����_��"or"�P���v��"�j�̏ꍇ�F
            if (WEB3ClosingOrderDef.RANDOM.equals(this.rsvIfoOrderUnitRow.getClosingOrder())
                || WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.rsvIfoOrderUnitRow.getClosingOrder()))
            {
                //this.�敨OP�\�񒍕��P��Row.get�������()=="�敨�����ԍρi���ԍρj"or"OP�����ԍρi���ԍρj"�ł���΁A
                //���P���̏����iasc�j�Ń\�[�g�B
                if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType())
                    || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType()))
                {
                    l_intFlag = 1;
                }
                //�@@��L�ȊO�̏ꍇ�́A���P���̍~���idesc�j�Ń\�[�g�B
                else
                {
                    l_intFlag = -1;
                }
            }

            //this.�敨OP�\�񒍕��P��Row.���Ϗ���=="�P������"�̏ꍇ�F
            if (WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.rsvIfoOrderUnitRow.getClosingOrder()))
            {
                //this.�敨OP�\�񒍕��P��Row.get�������()=="�敨�����ԍρi���ԍρj"or"OP�����ԍρi���ԍρj"�ł���΁A
                //���P���̍~���idesc�j�Ń\�[�g�B
                if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType())
                    || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(this.rsvIfoOrderUnitRow.getOrderType()))
                {
                    l_intFlag = -1;
                }
                //��L�ȊO�̏ꍇ�́A���P���̏����iasc�j�Ń\�[�g�B
                else
                {
                    l_intFlag = 1;
                }
            }

            //���P���̏����iasc�j�Ń\�[�g�B
            if (l_intFlag == 1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    IfoContractRow l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        IfoContractRow l_ifoContractRowJ = (IfoContractRow)l_lisContracts.get(j);

                        if (l_ifoContractRowJ.getContractPrice() < l_ifoContractRowI.getContractPrice())
                        {
                            l_lisContracts.set(i, l_ifoContractRowJ);
                            l_lisContracts.set(j, l_ifoContractRowI);

                            l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            }
            //���P���̍~���idesc�j�Ń\�[�g�B
            else if (l_intFlag == -1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    IfoContractRow l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        IfoContractRow l_ifoContractRowJ = (IfoContractRow)l_lisContracts.get(j);

                        if (l_ifoContractRowJ.getContractPrice() > l_ifoContractRowI.getContractPrice())
                        {
                            l_lisContracts.set(i, l_ifoContractRowJ);
                            l_lisContracts.set(j, l_ifoContractRowI);

                            l_ifoContractRowI = (IfoContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            }
        }

        //�������ʁi�c���j���A�\�񒍕��P��.�������� �ŏ���������B
        //�i�������ʁi�c���j = �\�񒍕��P��.��������;�j
        double l_dblOrderQuantity = this.rsvIfoOrderUnitRow.getQuantity();

        List l_lisArrayList = new ArrayList();
        //LOOP�F�@@�擾�E�\�[�g�������ʂ̗v�f�iindex�j�����A�J��Ԃ�
        for (int i = 0; i < l_intCnt; i++)
        {
            IfoContractRow l_ifoContractRow = (IfoContractRow)l_lisContracts.get(i);

            //get������薾��ListBy�����P��Plus����(�����P��ID : long, ����ID : long)
            //�����P��ID�F�@@�擾�����e�����̒����P��.�����P��ID
            //����ID�F�@@���ʁiindex�j.����ID
            List l_lisTransactions = l_positionManager.getTransactionsListByOrderUnitPlusContract(
                l_parentOrderUnit.getOrderUnitId(),
                l_ifoContractRow.getContractId());

            int l_intTransactionsCnt = 0;
            if (l_lisTransactions != null && !l_lisTransactions.isEmpty())
            {
                l_intTransactionsCnt = l_lisTransactions.size();
            }

            double l_dblExecQuantitySum = 0.0D;
            //LOOP�F�@@�擾����������薾�ׂ̗v�f�iindex2�j�����A�J��Ԃ�
            for (int j = 0; j < l_intTransactionsCnt; j++)
            {
                IfoFinTransactionRow l_ifoFinTransactionRow =
                    (IfoFinTransactionRow)l_lisTransactions.get(j);

                //������薾��[index2].��萔�� �̏W�v�l�i���ʇ@@�j�����߂�B
                //��萔�ʂ�SUM�l�@@���@@�i��萔�ʂ�SUM�l�{������薾��[index2].��萔�ʁj
                l_dblExecQuantitySum = l_dblExecQuantitySum + l_ifoFinTransactionRow.getQuantity();
            }

            //���Y���̌��ω\���ʁi���ʇA�j�����߂�

            //���Y���̕ԍω\���ʁ@@���@@����.���ʐ��ʁigetQuantity()�j �| ����.���b�N���̐��ʁigetLockedQuantity()�j
            WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImpl(l_ifoContractRow);
            double l_dblCanSettleQuantity = l_ifoContract.getQuantity() - l_ifoContract.getLockedQuantity();

            //���Y���ւ̌��ϊ������ʂ����肷��
            //�i���ʇA�̂ق������������ʂ̏ꍇ�A���ʇA�����ϊ������ʂƂ��Ďg�p����j
            double l_dblSettleQuantity = l_dblExecQuantitySum;
            if (l_dblCanSettleQuantity < l_dblExecQuantitySum)
            {
                l_dblSettleQuantity = l_dblCanSettleQuantity;
            }

            //�i���Y���ւ̌��ϊ������� > 0�̏ꍇ�̂݁A�C���X�^���X�����A�v���p�e�B�Z�b�g�j
            if (l_dblSettleQuantity > 0)
            {
                RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                    new RsvIfoClosingContractSpecParams();

                //[�v���p�e�B�Z�b�g�d�l]
                //����ID�F�@@this.�敨OP�\�񒍕��P��Row.����ID
                l_rsvIfoClosingContractSpecParams.setAccountId(this.rsvIfoOrderUnitRow.getAccountId());

                //�⏕����ID�F�@@this.�敨OP�\�񒍕��P��Row.�⏕����ID
                l_rsvIfoClosingContractSpecParams.setSubAccountId(this.rsvIfoOrderUnitRow.getSubAccountId());

                //����ID�F�@@this.�敨OP�\�񒍕��P��Row.����ID
                l_rsvIfoClosingContractSpecParams.setOrderId(this.rsvIfoOrderUnitRow.getOrderId());

                //����ID�F�@@����[index].����ID
                l_rsvIfoClosingContractSpecParams.setContractId(l_ifoContractRow.getContractId());

                //�@@�ԍϘA�ԁF�@@�iindex�{�P�j
                l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                //�ԍϒ������ʁF
                //�i�������ʁi�c���j >= ���Y���ւ̕ԍϊ������ʁj�̏ꍇ�́A���Y���ւ̕ԍϊ������ʁB
                if (l_dblOrderQuantity >= l_dblSettleQuantity)
                {
                    l_rsvIfoClosingContractSpecParams.setQuantity(l_dblSettleQuantity);
                }
                //�i�������ʁi�c���j < ���Y���ւ̕ԍϊ������ʁj�̏ꍇ�́A�������ʁi�c���j�B
                else
                {
                    l_rsvIfoClosingContractSpecParams.setQuantity(l_dblOrderQuantity);
                }

                //�@@�쐬���t�F�@@this.�敨OP�\�񒍕��P��Row.�쐬���t
                l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(
                    this.rsvIfoOrderUnitRow.getCreatedTimestamp());

                //�X�V���t�F�@@this.�敨OP�\�񒍕��P��Row.�X�V���t
                l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(
                    this.rsvIfoOrderUnitRow.getLastUpdatedTimestamp());

                l_lisArrayList.add(l_rsvIfoClosingContractSpecParams);
            }
            // �������ʁi�c���j -= ���Y���ւ̕ԍϊ�������;
            l_dblOrderQuantity -= l_dblSettleQuantity;

            //���Z�̌��ʁA
            //�������ʁi�c���j <= 0 �ƂȂ����ꍇ�́A
            //���ʂ�LOOP�iindex�j�𔲂���B
            if (l_dblOrderQuantity <= 0)
            {
                break;
            }
        }

        //return �쐬�����\�񌚋ʕԍώw����s[]
        RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = null;

        if (l_lisArrayList != null && !l_lisArrayList.isEmpty())
        {
            //�e�̖�茚�͂��邪�A�ԍϊ����\���ʕs���i�������ʁi�c���j������j�ꍇ�́A
            //�ԍϐ��ʕs���̗�O��throw����B
            if (l_dblOrderQuantity > 0)
            {
                log.debug("�������ʂ��ԍω\�c�����ʂ𒴂��Ă��܂��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������ʂ��ԍω\�c�����ʂ𒴂��Ă��܂��B");
            }
            l_rsvIfoClosingContractSpecRows =
                new RsvIfoClosingContractSpecRow[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_rsvIfoClosingContractSpecRows);
        }

        log.exiting(STR_METHOD_NAME);
        return l_rsvIfoClosingContractSpecRows;
    }

    /**
     * (get���������敪)<BR>
     * �igetExpirationDateType�j<BR>
     * <BR>
     * ��this.�敨OP�\�񒍕��P��Row.���������敪()��ԋp����B<BR>
     * @@return String
     * @@roseuid 433A4F820158
     */
    public String getExpirationDateType()
    {
        return this.rsvIfoOrderUnitRow.getExpirationDateType();
    }

    /**
     * (is�o����܂Œ���)<BR>
     * �iisCarriedOrder�j<BR>
     * <BR>
     * ��this.get���������敪()���擾����B<BR>
     * <BR>
     * �@@"�o����܂Œ���"�̏ꍇ�A<BR>
     * �@@true��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 433A4F820569
     */
    public boolean isCarriedOrder()
    {
        final String STR_METHOD_NAME = "isCarriedOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get���������敪()���擾����B
        //"�o����܂Œ���"�̏ꍇ�A
        if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(this.getExpirationDateType()))
        {
            //true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //��L�ȊO�̏ꍇ
        //false��ԋp����B
        return false;
    }

    /**
     * (is�[��܂Œ���)<BR>
     * �iisEveningSessionOrder�j<BR>
     * <BR>
     * ��this.get���������敪()���擾����B<BR>
     * <BR>
     * �@@"�[��܂Œ���"�̏ꍇ�A<BR>
     * �@@true��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 433A4F820354
     */
    public boolean isEveningSessionOrder()
    {
        final String STR_METHOD_NAME = "isEveningSessionOrder()";
        log.entering(STR_METHOD_NAME);

        //this.get���������敪()���擾����B
        //"�[��܂Œ���"�̏ꍇ�A
        if (WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(this.getExpirationDateType()))
        {
            //true��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //��L�ȊO�̏ꍇ
        //false��ԋp����B
        return false;
    }

    /**
     * (is������)<BR>
     * �iisOrdered�j<BR>
     * �\�񒍕��������ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�敨OP�\�񒍕��P��Row.�����P��ID��null�̏ꍇ�A<BR>
     * true�i�������ρj��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrdered()
    {
        final String STR_METHOD_NAME = "isOrdered()";
        log.entering(STR_METHOD_NAME);

        //this.�敨OP�\�񒍕��P��Row.�����P��ID��null�̏ꍇ�A
        if (!this.rsvIfoOrderUnitRow.getOrderUnitIdIsNull())
        {
            //true�i�������ρj��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�������s��)<BR>
     * �iisOrderExecuted�j<BR>
     * �\�񒍕����������������s�ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.is�����ρi�j==true�i����OK�j�A�܂���<BR>
     * this.�敨OP�\�񒍕��P��Row.�����G���[�R�[�h��null�i�����G���[�j�̏ꍇ�A<BR>
     * true�i���������s�ρj��ԋp����B<BR>
     * <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrderExecuted()
    {
        final String STR_METHOD_NAME = "isOrderExecuted()";
        log.entering(STR_METHOD_NAME);

        //this.is������()==true�i����OK�j�܂���
        //this.�敨OP�\�񒍕��P��Row.�����G���[�R�[�h��null�i�����G���[�j�̏ꍇ
        if (this.isOrdered() || this.rsvIfoOrderUnitRow.getOrderErrorCode() != null)
        {
            //true�i���������s�ρj��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is�J�z�\�񒍕��P��)<BR>
     * �iisCarryoverRsvOrderUnit�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P��Row�𔻒肵�āA<BR>
     * �J�z���Ă��Ȃ����"false"��ԋp���A<BR>
     * �J�z���Ă����"true"��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.�敨OP�\�񒍕��P��Row.���񒍕��̒���ID==�inull �܂��� 0�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@"false"�i�J�z���Ă��Ȃ��j��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@"true"�i�J�z���Ă���j��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 4341EA9B0010
     */
    public boolean isCarryoverRsvOrderUnit()
    {
        final String STR_METHOD_NAME = "isCarryoverRsvOrderUnit()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@this.�敨OP�\�񒍕��P��Row.���񒍕��̒���ID==�inull �܂��� 0�j�̏ꍇ
        if (this.rsvIfoOrderUnitRow.getFirstOrderIdIsNull()
            || this.rsvIfoOrderUnitRow.getFirstOrderId() == 0)
        {
            //false"�i�J�z���Ă��Ȃ��j��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j�@@��L�ȊO�̏ꍇ�A
        //"true"�i�J�z���Ă���j��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (validate�����\���)<BR>
     * �ivalidateOrderForChangeability�j<BR>
     * <BR>
     * �\�񒍕��������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �O������F����J�����_�R���e�L�X�g���A�������擾�ȏ�Ԃɐݒ肳��Ă��邱�ƁB<BR>
     * <BR>
     * �@@�P�j�@@�����L����ԃ`�F�b�N<BR>
     * �@@�@@�@@�@@this.�敨OP�\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02287<BR>
     * <BR>
     * �@@�Q�j�@@�������ԑу`�F�b�N<BR>
     * �@@�@@�@@�@@������ԊǗ�.validate�ǌ���������t�\()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[�����ݒ�]<BR>
     * �@@�@@�@@�@@�����^�C�v�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�����^�C�v<BR>
     * �@@�@@�@@�@@�敨�^�I�v�V�����敪�Fthis.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@�@@���X�@@�@@�@@�@@�Fthis.�\�񒍕��P��Row.���XID�����ɐ����������X�I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@����敪�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.����敪<BR>
     * �@@�@@�@@�@@�������@@�@@�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.������<BR>
     * <BR>
     * �@@�R�j�@@�e�����̃`�F�b�N<BR>
     * �@@�@@�R�|�P�j�@@�e�����̎擾<BR>
     * �@@�@@�@@�@@�@@�@@�A�������}�l�[�W��Impl.get�敨OP�e�����̒����P��()�Ŏ擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�����ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@this.�敨OP�\�񒍕��P��Row.�e�����̒���ID<BR>
     * <BR>
     * �@@�@@�R�|�Q�j�@@�e�����̃`�F�b�N<BR>
     * �@@�@@�@@�@@�@@�@@�@@�A�������}�l�[�W��Impl.validate�g���K�[�����ݒ�To�e�����i�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@��delegate����B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@[�����ݒ�]<BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʁ@@�F�擾�����e�����̒����P��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4628039A
     */
    public void validateOrderForChangeability() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����L����ԃ`�F�b�N
        //this.�敨OP�\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A
        //�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvIfoOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("�w��̗\�񒍕��̓N���[�Y��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��̗\�񒍕��̓N���[�Y�ςł��B");
        }

        //this.�\�񒍕��P��Row.���XID�����ɐ����������X�I�u�W�F�N�g
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(this.rsvIfoOrderUnitRow.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�������ԑу`�F�b�N
        //������ԊǗ�.validate�ǌ���������t�\()���\�b�h���R�[������B
        //[�����ݒ�]
        //�����^�C�v�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�����^�C�v
        //�敨�^�I�v�V�����敪�Fthis.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪
        //���X�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Fthis.�\�񒍕��P��Row.���XID�����ɐ����������X�I�u�W�F�N�g
        //����敪�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.����敪
        //�������@@�@@�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.������
        WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
            this.rsvIfoOrderUnitRow.getProductType(),
            this.rsvIfoOrderUnitRow.getFutureOptionDiv(),
            l_branch,
            this.rsvIfoOrderUnitRow.getSessionType(),
            WEB3DateUtility.getDate(this.rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        // �R�j�@@�e�����̃`�F�b�N
        //�R�|�P�j�@@�e�����̎擾
        //�A�������}�l�[�W��Impl.get�敨OP�e�����̒����P��()�Ŏ擾����B
        WEB3ToSuccOrderManagerImpl l_orderMgr =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();

        IfoOrderUnit l_ifoOrderUnit =
            l_orderMgr.getIfoParentOrderUnit(this.rsvIfoOrderUnitRow.getParentOrderId());

        //�R�|�Q�j�@@�e�����̃`�F�b�N
        //�A�������}�l�[�W��Impl.validate�g���K�[�����ݒ�To�e����
        l_orderMgr.validateTriggerOrderSettingToParentOrder(l_ifoOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate����\���)<BR>
     * �ivalidateOrderForCancellation�j<BR>
     * <BR>
     * �\�񒍕�������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �O������F����J�����_�R���e�L�X�g���A�������擾�ȏ�Ԃɐݒ肳��Ă��邱�ƁB<BR>
     * <BR>
     * �@@�P�j�@@�����L����ԃ`�F�b�N<BR>
     * �@@�@@�@@�@@this.�敨OP�\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B<BR>
     * �@@�@@�@@�@@�@@class: WEB3BusinessLayerException <BR>
     * �@@�@@�@@�@@�@@tag�@@: BUSINESS_ERROR_02287<BR>
     * <BR>
     * �@@�Q�j�@@������ԑу`�F�b�N<BR>
     * �@@�@@�@@�@@������ԊǗ�.validate�ǌ���������t�\()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@�@@�@@[�����ݒ�]<BR>
     * �@@�@@�@@�@@�����^�C�v�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�����^�C�v<BR>
     * �@@�@@�@@�@@�敨�^�I�v�V�����敪�Fthis.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�@@�@@���X�@@�@@�@@�@@�@@�Fthis.�\�񒍕��P��Row.���XID�����ɐ����������X�I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@����敪�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.����敪<BR>
     * �@@�@@�@@�@@�������@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4A3F006E
     */
    public void validateOrderForCancellation() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation()";
        log.entering(STR_METHOD_NAME);

        //�@@�P�j�@@�����L����ԃ`�F�b�N
        //this.�敨OP�\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A
        //�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvIfoOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("�w��̗\�񒍕��̓N���[�Y��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�w��̗\�񒍕��̓N���[�Y�ςł��B");
        }

        //this.�\�񒍕��P��Row.���XID�����ɐ����������X�I�u�W�F�N�g
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(this.rsvIfoOrderUnitRow.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
             throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�@@�Q�j�@@������ԑу`�F�b�N
        //������ԊǗ�.validate�ǌ���������t�\()���\�b�h���R�[������
        //[�����ݒ�]
        //�����^�C�v�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�����^�C�v
        //�敨�^�I�v�V�����敪�Fthis.�敨OP�\�񒍕��P��Row.�敨�^�I�v�V�����敪
        //���X�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Fthis.�\�񒍕��P��Row.���XID�����ɐ����������X�I�u�W�F�N�g
        //����敪�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.����敪
        //�������@@�@@�@@�@@�@@�@@�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.������
        WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
            this.rsvIfoOrderUnitRow.getProductType(),
            this.rsvIfoOrderUnitRow.getFutureOptionDiv(),
            l_branch,
            this.rsvIfoOrderUnitRow.getSessionType(),
            WEB3DateUtility.getDate(this.rsvIfoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�A���������ʏ��)<BR>
     * �icreateSuccCommonInfo�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A<BR>
     * �A���������ʏ��̃C���X�^���X��ԋp����B<BR>
     * <BR>
     * �@@�P�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�Q�j�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�i�e�����j����ID�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�e�����̒���ID<BR>
     * �@@�@@�A����������敪�@@�Fthis.�敨OP�\�񒍕��P��Row.�A����������敪<BR>
     * <BR>
     * �@@�R�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@return WEB3SuccCommonInfo
     * @@roseuid 433B5AA002E6
     */
    public WEB3SuccCommonInfo createSuccCommonInfo()
    {
        final String STR_METHOD_NAME = "createSuccCommonInfo()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccCommonInfo l_commonInfo = new WEB3SuccCommonInfo();

        //�Q�j�@@�v���p�e�B���Z�b�g����B
        //�i�e�����j����ID�@@�@@�@@�Fthis.�敨OP�\�񒍕��P��Row.�e�����̒���ID
        l_commonInfo.parentOrderId = this.rsvIfoOrderUnitRow.getParentOrderId() + "";

        //�A����������敪�@@�Fthis.�敨OP�\�񒍕��P��Row.�A����������敪
        l_commonInfo.succTradingType = this.rsvIfoOrderUnitRow.getReserveOrderTradingType();

        //�R�j�@@�߂�l�C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_commonInfo;
    }

    /**
     * (create�P�������l���)<BR>
     * �icreateSuccPriceAdjustmentValueInfo�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A<BR>
     * �A�������P�������l���̃C���X�^���X��ԋp����B<BR>
     * <BR>
     * �@@�P�j�@@�P�������l�̎w��L���𔻒肷��B<BR>
     * <BR>
     * �@@�@@�@@this.is�}�w�l�w��()==false�̏ꍇ�́A<BR>
     * �@@�@@�@@�P�������w��Ȃ��Ɣ��肵�Anull��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �@@�Q�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�j�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�����F�@@this.�敨OP�\�񒍕��P��Row.�P�������l�̕�������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���}�C�i�X�l�̏ꍇ�́A�}�C�i�X���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��0�ȏ�̏ꍇ�́A�v���X���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@�P�������l�F�@@this.�敨OP�\�񒍕��P��Row.�P�������l�̒P������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@���}�C�i�X�l�̏ꍇ�́A�l�𔽓]�����i���������������j�l���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@��0�ȏ�̏ꍇ�A���̂܂܂̒l���Z�b�g�B<BR>
     * <BR>
     * �@@�S�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@return WEB3SuccPriceAdjustmentValueInfo
     * @@roseuid 433B5B14020C
     */
    public WEB3SuccPriceAdjustmentValueInfo createSuccPriceAdjustmentValueInfo()
    {
        final String STR_METHOD_NAME = "createSuccPriceAdjustmentValueInfo()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�P�������l�̎w��L���𔻒肷��B
        //  this.is�}�w�l()==false�̏ꍇ�́A
        //  �P�������w��Ȃ��Ɣ��肵�Anull��ԋp����B
        if (!this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //  �ȊO�A�ȉ��̏������s���B
        //�Q�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccPriceAdjustmentValueInfo l_piceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();

        //�R�j�@@�v���p�e�B���Z�b�g����B
        //  �����F�@@this.�敨OP�\�񒍕��P��Row.�P�������l�̕�������
        //�@@�@@�@@    ���}�C�i�X�l�̏ꍇ�́A�}�C�i�X���Z�b�g�B
        //�@@�@@�@@    ��0�ȏ�̏ꍇ�́A�v���X���Z�b�g�B
        //�P�������l�F�@@this.�敨OP�\�񒍕��P��Row.�P�������l�̒P������
        //�@@�@@�@@    ���}�C�i�X�l�̏ꍇ�́A�l�𔽓]�����i���������������j�l���Z�b�g�B
        //�@@�@@�@@    ��0�ȏ�̏ꍇ�A���̂܂܂̒l���Z�b�g�B
        double l_dblPriceAdjustValue = this.rsvIfoOrderUnitRow.getPriceAdjustValue();
        if (l_dblPriceAdjustValue < 0)
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.SUBTRACT;
            l_piceAdjustmentValueInfo.priceAdjustmentValue = WEB3StringTypeUtility.formatNumber(
                Math.abs(l_dblPriceAdjustValue));
        }
        else
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.ADD;
            l_piceAdjustmentValueInfo.priceAdjustmentValue =
                WEB3StringTypeUtility.formatNumber(l_dblPriceAdjustValue);
        }

        //�S�j�@@�߂�l�C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_piceAdjustmentValueInfo;
    }

    /**
     * (get���b�Z�[�W�p�����P���敪)<BR>
     * �igetMsgOrderPriceDiv�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�����P���敪��ԋp����B<BR>
     * <BR>
     * �@@��this.is�}�w�l�w��()==true�̏ꍇ<BR>
     * �@@�@@"���s"��ԋp����B<BR>
     * <BR>
     * �@@����L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@this.is�w�l����()==true�̏ꍇ�́A<BR>
     * �@@�@@"�w�l"��ԋp����B<BR>
     * <BR>
     * �@@�@@�ȊO�A"���s"��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B6AA30325
     */
    public String getMsgOrderPriceDiv()
    {
        final String STR_METHOD_NAME = "getMsgOrderPriceDiv()";
        log.entering(STR_METHOD_NAME);

        // this.is�}�w�l�w��()==true�̏ꍇ
        if (this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"���s"��ԋp����B
            return WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        //����L�ȊO�̏ꍇ
        //this.is�w�l����()==true�̏ꍇ
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"�w�l"��ԋp����B
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //�ȊO�A"���s"��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return WEB3OrderPriceDivDef.MARKET_PRICE;
    }

    /**
     * (get���b�Z�[�W�p�����P��)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�����P����ԋp����B<BR>
     * <BR>
     * ��this.is�}�w�l�w��()==true�̏ꍇ<BR>
     * �@@�@@null��ԋp����B<BR>
     * �@@�@@���P�������l�w�莞�́A���b�Z�[�W�N���X�ɂ�"���s"���Z�b�g����悤<BR>
     * �@@�@@��AP��PR�Ԃ�IF���K�肵�����߁B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�@@this.is�w�l����()==true�̏ꍇ�́A<BR>
     * �@@�@@this.�敨OP�\�񒍕��P��Row.�w�l��ԋp����B<BR>
     * �@@�@@�ȊO�Anull��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B6BFC0008
     */
    public String getMsgLimitPrice()
    {
        final String STR_METHOD_NAME = "getMsgLimitPrice()";
        log.entering(STR_METHOD_NAME);

        //��this.is�}�w�l�w��()==true�̏ꍇ
        if (this.isExecPriceOrder())
        {
            //null��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //����L�ȊO�̏ꍇ
        //this.is�w�l����()==true�̏ꍇ
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //this.�敨OP�\�񒍕��P��Row.�w�l��ԋp����B
            return WEB3StringTypeUtility.formatNumber(this.rsvIfoOrderUnitRow.getLimitPrice());
        }

        log.exiting(STR_METHOD_NAME);
        //�ȊO�Anull��ԋp����B
        return null;
    }

    /**
     * (get���b�Z�[�W�p�����L������)<BR>
     * �igetMsgExpirationDate�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�����L��������ԋp����B<BR>
     * <BR>
     * �@@��this.is�o����܂Œ���()==true�̏ꍇ<BR>
     * �@@�@@this.�敨OP�\�񒍕��P��Row.���������� ��ԋp����B<BR>
     * <BR>
     * �@@����L�ȊO�̏ꍇ<BR>
     * �@@�@@�@@null��ԋp����B<BR>
     * @@return Date
     * @@roseuid 433B6E800279
     */
    public Date getMsgExpirationDate()
    {
        final String STR_METHOD_NAME = "getMsgExpirationDate()";
        log.entering(STR_METHOD_NAME);

        //��this.is�o����܂Œ���()==true�̏ꍇ
        if (this.isCarriedOrder())
        {
            //this.�敨OP�\�񒍕��P��Row.���������� ��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return this.rsvIfoOrderUnitRow.getExpirationDate();
        }

        // ����L�ȊO�̏ꍇ
        //null��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (get���b�Z�[�W�p���s����)<BR>
     * �igetMsgExecCondType�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A���s������ԋp����B<BR>
     * <BR>
     * "������"��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B77070306
     */
    public String getMsgExecCondType()
    {
        return WEB3ExecutionConditionDef.NO_CONDITION;
    }

    /**
     * (get���b�Z�[�W�p���������敪)<BR>
     * �igetMsgOrderCondType�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A���������敪��ԋp����B<BR>
     * <BR>
     * "�w��Ȃ�"��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B771C0363
     */
    public String getMsgOrderCondType()
    {
        return WEB3OrderingConditionDef.DEFAULT;
    }

    /**
     * (get���b�Z�[�W�p����敪)<BR>
     * �igetMsgTradingType�j<BR>
     * <BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A����敪��ԋp����B<BR>
     * <BR>
     * this.get�������()���A<BR>
     * �@@��OrderTypeEnum.�敨�V�K���������A<BR>
     * �@@�@@�܂��́A<BR>
     * �@@�@@OrderTypeEnum.OP�V�K���������̏ꍇ<BR>
     * <BR>
     * �@@�@@"�V�K��������"��ԋp����B<BR>
     * <BR>
     * �@@��OrderTypeEnum.�敨�V�K���������A<BR>
     * �@@�@@�܂��́A<BR>
     * �@@�@@OrderTypeEnum.OP�V�K���������̏ꍇ<BR>
     * <BR>
     * �@@�@@"�V�K��������"��ԋp����B<BR>
     * <BR>
     * �@@��OrderTypeEnum.�敨�������ԍϒ����i���ԍρj�̏ꍇ�A<BR>
     * �@@�@@�܂��́A <BR>
     * �@@�@@OrderTypeEnum.OP�������ԍϒ����i���ԍρj�̏ꍇ<BR>
     * <BR>
     * �@@�@@"�����ԍϒ����i���ԍρj"��ԋp����B<BR>
     * <BR>
     * �@@��OrderTypeEnum.�敨�������ԍϒ����i���ԍρj�̏ꍇ�A<BR>
     * �@@�@@�܂��́A<BR>
     * �@@�@@OrderTypeEnum.OP�������ԍϒ����i���ԍρj�̏ꍇ<BR>
     * <BR>
     * �@@�@@"�����ԍϒ����i���ԍρj"��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B7D160381
     */
    public String getMsgTradingType()
    {
        final String STR_METHOD_NAME = "getMsgTradingType";
        log.entering(STR_METHOD_NAME);

        //this.get�������()���AOrderTypeEnum.�敨�V�K���������A
        //�܂��́AOrderTypeEnum.OP�V�K���������̏ꍇ
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(this.getOrderType()))
        {
            //"�V�K��������"��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.OPEN_LONG_MARGIN;
        }

        //�@@��OrderTypeEnum.�敨�V�K��������
        //�@@�@@�܂��́A �@@�@@OrderTypeEnum.OP�V�K���������̏ꍇ
        if (OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(this.getOrderType()))
        {
            //"�V�K��������"��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.OPEN_SHORT_MARGIN;
        }

        //�@@��OrderTypeEnum.�敨�������ԍϒ����i���ԍρj�̏ꍇ�A
        //�@@�@@�܂��́A�@@�@@OrderTypeEnum.OP�������ԍϒ����i���ԍρj�̏ꍇ
        if (OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(this.getOrderType()))
        {
            //"�����ԍϒ����i���ԍρj"��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_LONG_MARGIN;
        }

        //�@@��OrderTypeEnum.�敨�������ԍϒ����i���ԍρj�̏ꍇ�A
        //�@@�@@�܂��́A�@@�@@�@@�@@OrderTypeEnum.OP�������ԍϒ����i���ԍρj�̏ꍇ
        if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(this.getOrderType())
            || OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(this.getOrderType()))
        {
            //"�����ԍϒ����i���ԍρj"��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_SHORT_MARGIN;
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }
}
@
