head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEqTypeOrderUnitImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����\�񒍕��P��Impl(WEB3ToSuccEqTypeOrderUnitImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �s�p (���u) �V�K�쐬 
                 : 2006/08/30 �����F(���u) ���f�� 165
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
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderActionRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccMessageTradingTypeDef;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����\�񒍕��P��Impl)<BR>
 * �����\�񒍕��P�ʃe�[�u����1-Row��\������N���X�B
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccEqTypeOrderUnitImpl implements EqTypeOrderUnit 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccEqTypeOrderUnitImpl.class);
        
    /**
     * (�����\�񒍕��P��Row)
     */
    private RsvEqOrderUnitRow rsvEqOrderUnitRow;
    
    /**
     * �R���X�g���N�^<BR>
     */
    protected WEB3ToSuccEqTypeOrderUnitImpl(RsvEqOrderUnitRow l_rsvEqOrderUnitRow)
    {
        this.rsvEqOrderUnitRow = l_rsvEqOrderUnitRow;
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR> 
     * <BR>
     * this.�����\�񒍕��P��Row��ԋp����B
     * @@return Object
     * @@roseuid 431E765B01F0
     */
    public Object getDataSourceObject() 
    {        
        return this.rsvEqOrderUnitRow;
    }
    
    /**
     * (is�w�l����)<BR>
     * �iisLimitOrder�j<BR>
     * <BR>
     * �w�l�����ł����true���A���s�����ł����false��Ԃ��B<BR>
     * <BR>
     * �ithis.�����\�񒍕��P��Row.�w�l != 0�A<BR>
     * �@@�܂��� this.�����\�񒍕��P��Row.�P�������l != null�j<BR>
     * �ł����true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 4321814201CC
     */
    public boolean isLimitOrder() 
    {
        final String STR_METHOD_NAME = " isLimitOrder() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.rsvEqOrderUnitRow.getLimitPrice() != 0 ||
            this.rsvEqOrderUnitRow.getPriceAdjustValueIsNull() == false)
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
     * �itrue�̏ꍇ��false���Afalse�̏ꍇ��true��Ԃ��j
     * @@return boolean
     * @@roseuid 432194D40380
     */
    public boolean isMarketOrder() 
    {
        final String STR_METHOD_NAME = " isMarketOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.is�w�l����()�̖߂�l�𔽓]���ĕԂ��B
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
     * this.�����\�񒍕��P��Row.�����P��ID��ԋp����B
     * @@return long
     * @@roseuid 43218C5F01FB
     */
    public long getOrderUnitId() 
    {
        return this.rsvEqOrderUnitRow.getOrderUnitId();
    }
    
    /**
     * (get����ID)<BR>
     * �igetOrderId�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.����ID��ԋp����B
     * @@return long
     * @@roseuid 43218C5F021B
     */
    public long getOrderId() 
    {
        return this.rsvEqOrderUnitRow.getOrderId();
    }
    
    /**
     * (get����ID)<BR>
     * �igetAccountId�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.����ID��ԋp����B
     * @@return long
     * @@roseuid 43218C5F023A
     */
    public long getAccountId() 
    {
        return this.rsvEqOrderUnitRow.getAccountId();
    }
    
    /**
     * (get�⏕����ID)<BR>
     * �igetSubAccountId�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�⏕����ID��ԋp����B
     * @@return long
     * @@roseuid 43218C5F0259
     */
    public long getSubAccountId() 
    {
        return this.rsvEqOrderUnitRow.getSubAccountId();
    }
    
    /**
     * (get���XID)<BR>
     * �igetBranchId�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.���XID��ԋp����B
     * @@return long
     * @@roseuid 43218C5F0278
     */
    public long getBranchId() 
    {
        return this.rsvEqOrderUnitRow.getBranchId();
    }
    
    /**
     * (get�����ID)<BR>
     * �igetTraderId�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�����ID��ԋp����B
     * @@return long
     * @@roseuid 43218C5F0298
     */
    public long getTraderId() 
    {
        return this.rsvEqOrderUnitRow.getTraderId();
    }
    
    /**
     * (is�I����)<BR>
     * �iisExpired�̎����j<BR>
     * <BR>
     * �������o���I���^�����ɂ��I���ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�����敪 = OrderExpirationStatusEnum.OPEN�̏ꍇ�́A<BR>
     * false��ԋp����B<BR>
     * �ȊO�Atrue��ԋp����B
     * @@return boolean
     * @@roseuid 43218C5F02C6
     */
    public boolean isExpired() 
    {
        final String STR_METHOD_NAME = " isExpired() ";
        log.entering(STR_METHOD_NAME);
        
        //this.�����\�񒍕��P��Row.�����敪 = OrderExpirationStatusEnum.OPEN�̏ꍇ�́A
        if (OrderExpirationStatusEnum.OPEN.equals(
            this.rsvEqOrderUnitRow.getExpirationStatus()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //false��ԋp����B
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return true;
    }
    
    /**
     * �i�������j<BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 43218C5F02E6
     */
    public boolean isFullyExecuted() 
    {
        return false;
    }
    
    /**
     * �i�������j<BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 43218C5F0305
     */
    public boolean isPartiallyExecuted() 
    {
        return false;
    }
    
    /**
     * �i�������j<BR>
     * true��ԋp����B
     * @@return boolean
     * @@roseuid 43218C5F0324
     */
    public boolean isUnexecuted() 
    {
        return true;
    }
    
    /**
     * �i�������j<BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 43218C5F0343
     */
    public double getConfirmedPrice() 
    {
        return 0;
    }
    
    /**
     * �i�������j<BR>
     * false��ԋp����B
     * @@return boolean
     * @@roseuid 43218C5F0363
     */
    public boolean isConfirmedPriceMarketOrder() 
    {
        return false;
    }
    
    /**
     * �i�������j<BR>
     * 0��ԋp����B
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
     * this.�����\�񒍕��P��Row.�������ʂ�ԋp����B
     * @@return double
     * @@roseuid 43218C5F03A1
     */
    public double getQuantity() 
    {
        return this.rsvEqOrderUnitRow.getQuantity();
    }
    
    /**
     * �i�������j<BR>
     * 0��ԋp����B
     * @@return double
     * @@roseuid 43218C5F03C0
     */
    public double getExecutedAmount() 
    {
        return 0;
    }
    
    /**
     * �i�������j<BR>
     * 0��ԋp����B
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
     * this.�����\�񒍕��P��Row.�w�l��ԋp����B
     * @@return double
     * @@roseuid 43218C600027
     */
    public double getLimitPrice() 
    {
        if (this.rsvEqOrderUnitRow.getLimitPriceIsNull())
        {
            return Double.NaN;
        }
        
        return this.rsvEqOrderUnitRow.getLimitPrice();
    }
    
    /**
     * �i�������j<BR>
     * null��ԋp����B
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction[]
     * @@roseuid 43218C600046
     */
    public OrderAction[] getOrderActions() 
    {
        return null;
    }
    
    /**
     * (get�����\�񒍕�����)<BR>
     * �igetRsvEqOrderActions�j<BR>
     * <BR>
     * �����\�񒍕������e�[�u�����A�ȉ��̏����ɍ��v���郌�R�[�h��<BR>
     * ��������ԍ� �����Ŏ擾���ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * ����ID = this.�����\�񒍕��P��Row.����ID
     * @@return webbroker3.triggerorder.data.RsvEqOrderActionRow[]
     * @@throws WEB3BaseException
     * @@roseuid 4337B448010C
     */
    public RsvEqOrderActionRow[] getRsvEqOrderActions() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getRsvEqOrderActions() ";
        log.entering(STR_METHOD_NAME);
        
        //[����]
        //����ID = this.�����\�񒍕��P��Row.����ID
        String l_strWhere = " order_id = ? ";                
        Object[] l_objs = {new Long(this.rsvEqOrderUnitRow.getOrderId())};
        
        try
        {            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderActionRow.TYPE, 
                    l_strWhere, 
                    "order_action_serial_no asc",
                    null,
                    l_objs);//DataNetworkException,DataQueryException
                
            RsvEqOrderActionRow[] l_rsvEqActionRows = null;
            
            if (l_lisRsvEqOrderActionRows != null && !l_lisRsvEqOrderActionRows.isEmpty())
            {
                l_rsvEqActionRows = new RsvEqOrderActionRow[l_lisRsvEqOrderActionRows.size()];
                
                l_lisRsvEqOrderActionRows.toArray(l_rsvEqActionRows);
            }
        
            log.exiting(STR_METHOD_NAME);
            
            return l_rsvEqActionRows;                    
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }                  
    }
    
    /**
     * (get�쐬����)<BR>
     * �igetReceivedTimestamp�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.created_timestamp��ԋp����B
     * @@return Timestamp
     * @@roseuid 43218C600084
     */
    public Timestamp getReceivedTimestamp() 
    {
        return this.rsvEqOrderUnitRow.getCreatedTimestamp();
    }
    
    /**
     * (get�����������t)<BR>
     * �igetExpirationTimestamp�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�����������t��ԋp����B
     * @@return Timestamp
     * @@roseuid 43218C6000A4
     */
    public Timestamp getExpirationTimestamp() 
    {
        return this.rsvEqOrderUnitRow.getExpirationDate();
    }
    
    /**
     * (get����)<BR>
     * �igetProduct�̎����j<BR>
     * <BR>
     * EQTYPE�̊g���v���_�N�g�}�l�[�W��.getProduct(this.�����\�񒍕��P��Row.����ID)<BR>
     * �ɂ��Athis.�����\�񒍕��P��Row.����ID�ɊY�����銔�������I�u�W�F�N�g<BR>
     * ���擾���ԋp����B
     * @@return Product
     * @@roseuid 43218C6000C3
     */
    public Product getProduct()
    {
        final String STR_METHOD_NAME = " getProduct() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                        
        try
        {
            Product l_product = 
                l_productManager.getProduct(this.rsvEqOrderUnitRow.getProductId());//NotFoundException
            
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
     * null��ԋp����B
     * @@return Order
     * @@roseuid 43218C6000E2
     */
    public Order getOrder() 
    {
        return null;
    }
    
    /**
     * �i�������j<BR>
     * null��ԋp����B
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution[]
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
     * this.�����\�񒍕��P��Row.�����L����Ԃ�ԋp����B
     * @@return OrderOpenStatusEnum
     * @@roseuid 43218C600140
     */
    public OrderOpenStatusEnum getOrderOpenStatus() 
    {
        return this.rsvEqOrderUnitRow.getOrderOpenStatus();
    }
    
    /**
     * (get�������)<BR>
     * �igetOrderType�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.������ʂ�ԋp����B
     * @@return OrderTypeEnum
     * @@roseuid 43218C60015F
     */
    public OrderTypeEnum getOrderType() 
    {
        return this.rsvEqOrderUnitRow.getOrderType();
    }
    
    /**
     * (get�����J�e�S��)<BR>
     * �igetOrderCateg�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�����J�e�S����ԋp����B
     * @@return OrderCategEnum
     * @@roseuid 43218C60017E
     */
    public OrderCategEnum getOrderCateg() 
    {
        return this.rsvEqOrderUnitRow.getOrderCateg();
    }
    
    /**
     * (get�ŋ敪)<BR>
     * �igetTaxType�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�ŋ敪��ԋp����B
     * @@return TaxTypeEnum
     * @@roseuid 43218C60019E
     */
    public TaxTypeEnum getTaxType() 
    {
        return this.rsvEqOrderUnitRow.getTaxType();
    }
    
    /**
     * (get����)<BR>
     * �igetSide�̎����j<BR>
     * <BR>
     * SideEnum.getSide(this.�����\�񒍕��P��Row.�������)�̖߂�l��ԋp����B<BR>
     * @@return SideEnum
     * @@roseuid 43218C6001BD
     */
    public SideEnum getSide() 
    {
        return SideEnum.getSide(this.rsvEqOrderUnitRow.getOrderType());
    }
    
    /**
     * (get�������)<BR>
     * �igetOrderStatus�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.������Ԃ�ԋp����B
     * @@return OrderStatusEnum
     * @@roseuid 43218C6001DC
     */
    public OrderStatusEnum getOrderStatus() 
    {
        return this.rsvEqOrderUnitRow.getOrderStatus();
    }
    
    /**
     * (get�����敪)<BR>
     * �igetExpirationStatus�̎����j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�����敪��ԋp����B
     * @@return OrderExpirationStatusEnum
     * @@roseuid 43218C6001FB
     */
    public OrderExpirationStatusEnum getExpirationStatus() 
    {
        return this.rsvEqOrderUnitRow.getExpirationStatus();
    }
    
    /**
     * �i�������j<BR>
     * null��ԋp����B
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
     * EQTYPE�̊g���v���_�N�g�}�l�[�W��.getTradedProduct(<BR>
     * this.�����\�񒍕��P��Row.����ID, this.�����\�񒍕��P��Row.�s��ID)�ɂ��A<BR>
     * this.�����\�񒍕��P��Row.����ID�A�s��ID�ɊY�����銔����������I�u�W�F�N�g<BR>
     * ���擾���ԋp����B
     * @@return TradedProduct
     * @@roseuid 43218C600249
     */
    public TradedProduct getTradedProduct() 
    {
        final String STR_METHOD_NAME = " getTradedProduct() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                        
        try
        {
            TradedProduct l_tradedProduct = 
                l_productManager.getTradedProduct(
                    this.rsvEqOrderUnitRow.getProductId(),
                    this.rsvEqOrderUnitRow.getMarketId());//NotFoundException
            
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
     * �i�������j<BR>
     * null��ԋp����B
     * @@return EqTypeOrder
     * @@roseuid 43218C600278
     */
    public EqTypeOrder getEqTypeOrder() 
    {
        return null;
    }
    
    /**
     * (get�\�񒍕����s�P��)<BR>
     * �\�񒍕��i�q�����j�̎��s�P�����擾���ԋp����B<BR>
     * �|�w�l�����^���s�����̏ꍇ�i�}�w�l�w��Ȃ��̏ꍇ�j�A�w�l�܂���0��ԋp����B<BR>
     * �|�}�w�l�w�蒍���̏ꍇ�́A�e�����̎w�l�^�����ɒP�������l�����������P����<BR>
     * �ԋp����B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.get�����\�񒍕����s�P��()��delegate����B<BR>
     * �����ݒ�d�l�͈ȉ��̒ʂ�B<BR>
     * <BR>
     * �e�����̒����P�ʁF�@@this.get�e�����̒����P��()�̖߂�l<BR>
     * �w�l�F�@@this.�����\�񒍕��P��Row�̓�����<BR>
     * �P�������l�F�@@this.�����\�񒍕��P��Row�̓�����<BR>
     * �@@��null�̏ꍇ�́Anull�����̂܂܃Z�b�g�B<BR>
     * ������������F�@@this.get�������()�̖߂�l
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4324EDFB02B5
     */
    public double getRsvOrderExecPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getRsvOrderExecPrice() ";
        log.entering(STR_METHOD_NAME);
                
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        double l_dblRsvExecPrice = l_orderManager.getReserveEqtypeOrderExecPrice(
            this.getParentOrderUnit(),
            this.rsvEqOrderUnitRow.getLimitPrice(),
            this.rsvEqOrderUnitRow.getPriceAdjustValueIsNull() ? 
                null : new Double(this.rsvEqOrderUnitRow.getPriceAdjustValue()),
            (WEB3EquityTradedProduct)this.getTradedProduct());
                        
        log.exiting(STR_METHOD_NAME);        
        return l_dblRsvExecPrice;
    }
    
    /**
     * (is������)<BR>
     * �iisBuyOrder�j<BR>
     * <BR>
     * �������ł����true���A�������ł����false��ԋp����B<BR>
     * <BR>
     * this.get����()=="��"�̏ꍇ��true��ԋp����B�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 43250C1300BD
     */
    public boolean isBuyOrder() 
    {
        final String STR_METHOD_NAME = " isBuyOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.get����()=="��"�̏ꍇ��true��ԋp����B
        if (SideEnum.BUY.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //�ȊO�Afalse��ԋp����B
        return false;
    }
    
    /**
     * (is������)<BR>
     * �iisSellOrder�j<BR>
     * <BR>
     * �������ł����true���A�������ł����false��ԋp����B<BR>
     * <BR>
     * this.get����()=="��"�̏ꍇ��true��ԋp����B�ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 43250C76011B
     */
    public boolean isSellOrder() 
    {
        final String STR_METHOD_NAME = " isSellOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.get����()=="��"�̏ꍇ��true��ԋp����B
        if (SideEnum.SELL.equals(this.getSide()))
        {
            log.exiting(STR_METHOD_NAME);
            
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //�ȊO�Afalse��ԋp����B
        return false;
    }
    
    /**
     * (is�}�w�l�w��)<BR>
     * �}�w�l���w�肳��Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�P�������l��null �̏ꍇ�A<BR>
     * true��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 43253BA10395
     */
    public boolean isExecPriceOrder() 
    {
        final String STR_METHOD_NAME = " isExecPriceOrder() ";
        log.entering(STR_METHOD_NAME);
        
        //this.�����\�񒍕��P��Row.�P�������l��null �̏ꍇ�A
        if (this.rsvEqOrderUnitRow.getPriceAdjustValueIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            
            //�ȊO�Afalse��ԋp����B
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //true��ԋp����B
        return true;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * this.�����\�񒍕��P��Row.�����^�C�v��ԋp����B
     * @@return ProductTypeEnum
     * @@roseuid 4326377F03D3
     */
    public ProductTypeEnum getProductType() 
    {
        return this.rsvEqOrderUnitRow.getProductType();
    }
    
    /**
     * (get�s��)<BR>
     * �igetMarket�j<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�s��ID�ɊY������s��I�u�W�F�N�g���擾���ԋp����B<BR>
     * �i�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(this.�����\�񒍕��P��Row.�s��ID)�j
     * @@return WEB3GentradeMarket
     * @@return NotFoundException
     * @@roseuid 433B716B0354
     */
    public WEB3GentradeMarket getMarket() throws NotFoundException
    {
        final String STR_METHOD_NAME = " getMarket() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.rsvEqOrderUnitRow.getMarketIdIsNull())
        {
            return null;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        WEB3GentradeMarket l_market =
            (WEB3GentradeMarket)l_finObjManager.getMarket(
                this.rsvEqOrderUnitRow.getMarketId());
                
        log.exiting(STR_METHOD_NAME);            
        return l_market;               
    }
    
    /**
     * (get�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.get�����e�����̒����P��<BR>
     * (this.�����\�񒍕��P��Row.�e�����̒���ID)��delegate����B
     * @@return EqTypeOrderUnit
     * @@roseuid 43279DFC00A8
     */
    public EqTypeOrderUnit getParentOrderUnit() 
    {
        final String STR_METHOD_NAME = " getParentOrderUnit() ";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        EqTypeOrderUnit l_eqOrderUnit = l_orderManager.getEqtypeParentOrderUnit(
            this.rsvEqOrderUnitRow.getParentOrderId());
                        
        log.exiting(STR_METHOD_NAME);
        
        return l_eqOrderUnit;
    }
    
    /**
     * (is���Δ������)<BR>
     * ���Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �A�������}�l�[�W��Impl.is���Δ������(<BR>
     * this.�����\�񒍕��P��Row.�A����������敪, this.get�e�����̒����P��)<BR>
     * ��delegate����B
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43279F53003A
     */
    public boolean isReversingTrade() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " isReversingTrade() ";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_orderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        boolean l_blnIsOppoDealingTrade = l_orderManager.isReversingTrade(
            this.rsvEqOrderUnitRow.getReserveOrderTradingType(),
            this.getParentOrderUnit());
                        
        log.exiting(STR_METHOD_NAME);
        
        return l_blnIsOppoDealingTrade;
    }
    
    /**
     * (get�����\�񌚊��ԍώw����ꗗ)<BR>
     * �igetContractsToClose�j<BR>
     * �\�񒍕��i�q�����j�́A�����\�񌚊��ԍώw����̍s�I�u�W�F�N�g��Ԃ��B<BR>
     * �|�\�񒍕����A�������ɑ΂��錈�ϒ����̏ꍇ�A<BR>
     * �@@�����\�񌚊��ԍώw����e�[�u���ɓo�^����Ă��郌�R�[�h���擾���Ԃ��B<BR>
     * �|�\�񒍕����A�e�����ɑ΂��锽�Ύ���̌��ϒ����̏ꍇ�A<BR>
     * �@@�e�����������^�ꕔ���ł���΁A<BR>
     * �@@���z�̊����\�񌚊��ԍώw����s�I�u�W�F�N�g���쐬���Ԃ��B<BR>
     * �@@�e�������S����肵�Ă���ꍇ�́A<BR>
     * �@@�e�����̖��ɂ��쐬���ꂽ���������ɂ��āA<BR>
     * �@@���z�̊����\�񌚊��ԍώw����s�I�u�W�F�N�g[]���쐬���Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�A�������jget�����\�񌚊��ԍώw����ꗗ�v�Q�ƁB<BR>
     *  ======================================================== <BR>
     * �E�����J�e�S���`�F�b�N<BR>
     * �@@this.�����\�񒍕��P��Row.�����J�e�S���� <BR>
     * �@@���L�ȊO�̏ꍇ�́A��O��throw����B <BR>
     *  <BR>
     * �@@�@@�@@OrderCategEnum."�ԍϒ���" <BR>
     * �@@�@@�@@OrderCategEnum."�������n" <BR>
     * �@@class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00653<BR>
     * <BR>
     * �E���ϐ��ʃ`�F�b�N
     * �@@�e�̖�茚�͂��邪�A���ϊ����\���ʕs���̏ꍇ�́A<BR>
     * �@@���ϐ��ʕs���̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_00299<BR>
     *  ========================================================== <BR>
     * @@return webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow[]
     * @@throws WEB3BaseException
     * @@roseuid 433A4F820186
     */
    public RsvEqClosingContractSpecRow[] getContractsToClose() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContractsToClose() ";
        log.entering(STR_METHOD_NAME);
        
        //�����J�e�S���`�F�b�N
        //this.�����\�񒍕��P��Row.�����J�e�S����
        //���L�ȊO�̏ꍇ�́A��O��throw����B
        //OrderCategEnum."�ԍϒ���"
        //OrderCategEnum."�������n"
        if (!(OrderCategEnum.CLOSE_MARGIN.equals(this.rsvEqOrderUnitRow.getOrderCateg()) ||
            OrderCategEnum.SWAP_MARGIN.equals(this.rsvEqOrderUnitRow.getOrderCateg())))
        {
            log.debug("�����J�e�S�����ԍϒ���/�������n�ȊO�̏ꍇ�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����J�e�S�����ԍϒ���/�������n�ȊO�̏ꍇ�ł��B");
        }
        
        //is���Δ������( )
        boolean l_blnIsReserveTrade = this.isReversingTrade();
        
        //����t���[�F�@@�e�����ɑ΂��锽�Ύ���łȂ��iis���Δ������()==false�j�ꍇ�j
        if (!l_blnIsReserveTrade)
        {
            //�ȉ��̏����ɂĊ����\�񌚊��ԍώw����e�[�u�����������A�擾���ʂ�ԋp����B
            //[����]
            //����ID = this.get����ID()
            //[�\�[�g����]
            //�ԍϘA�� ����
            String l_strWhere = " order_id = ? ";                
            Object[] l_objs = {
                new Long(this.getOrderId())};
        
            try
            {            
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_lisRsvEqClosingContractSpecRows =
                    l_queryProcessor.doFindAllQuery(
                        RsvEqClosingContractSpecRow.TYPE, 
                        l_strWhere, 
                        " closing_serial_no asc ",
                        null,
                        l_objs);//DataNetworkException,DataQueryException
        
                RsvEqClosingContractSpecRow[] l_rsvRsvEqClosingContractSpecRows = null;
                if (l_lisRsvEqClosingContractSpecRows != null && 
                    !l_lisRsvEqClosingContractSpecRows.isEmpty())
                {
                    l_rsvRsvEqClosingContractSpecRows = 
                        new RsvEqClosingContractSpecRow[l_lisRsvEqClosingContractSpecRows.size()];
                    l_lisRsvEqClosingContractSpecRows.toArray(l_rsvRsvEqClosingContractSpecRows);

                    //�ireturn �擾�����\�񌚊��ԍώw����s[];�j
                    log.exiting(STR_METHOD_NAME);
                    return l_rsvRsvEqClosingContractSpecRows;   
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
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }                              
        }

        //�i����t���[�F�@@�e�����ɑ΂��锽�Ύ���̏ꍇ�j
        //get�e�����̒����P��( )
        EqTypeOrderUnit l_parentOrderUnit = this.getParentOrderUnit();
        
        //isFullyExecuted( )  
        boolean l_blnIsFullyExecuted = l_parentOrderUnit.isFullyExecuted();
        
        //�����^�ꕔ���̏ꍇ�iisFullyExecuted()==false�j
        if (!l_blnIsFullyExecuted)
        {
            //�i�C���X�^���X�����A�v���p�e�B�Z�b�g�j
            //[�v���p�e�B�Z�b�g�d�l]                                                           
            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                new RsvEqClosingContractSpecParams();
            
            //  ����ID�F�@@this.�����\�񒍕��P��Row.����ID
            l_rsvEqClosingContractSpecParams.setAccountId(
                this.rsvEqOrderUnitRow.getAccountId());
                                 
            //  �⏕����ID�F�@@this.�����\�񒍕��P��Row.�⏕����ID
            l_rsvEqClosingContractSpecParams.setSubAccountId(
                this.rsvEqOrderUnitRow.getSubAccountId());
                         
            //  ����ID�F�@@this.�����\�񒍕��P��Row.����ID 
            l_rsvEqClosingContractSpecParams.setOrderId(
                this.rsvEqOrderUnitRow.getOrderId());
                                
            //  ����ID�F�@@0�i�Œ�j
            l_rsvEqClosingContractSpecParams.setContractId(0);
                                                       
            //  �ԍϘA�ԁF�@@1�i�Œ�j 
            l_rsvEqClosingContractSpecParams.setClosingSerialNo(1);
                                                    
            //  �ԍϒ������ʁF�@@this.�����\�񒍕��P��Row.��������
            l_rsvEqClosingContractSpecParams.setQuantity(
                this.rsvEqOrderUnitRow.getQuantity());
                         
            //  �쐬���t�F�@@this.�����\�񒍕��P��Row.�쐬���t    
            l_rsvEqClosingContractSpecParams.setCreatedTimestamp(
                this.rsvEqOrderUnitRow.getCreatedTimestamp());
                
            //  �X�V���t�F�@@this.�����\�񒍕��P��Row.�X�V���t
            l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(
                this.rsvEqOrderUnitRow.getLastUpdatedTimestamp());
            
            //�ireturn �쐬�����\�񌚊��ԍώw����s;�j
            return new RsvEqClosingContractSpecRow[]{l_rsvEqClosingContractSpecParams};
        }

        //�S�����̏ꍇ�iisFullyExecuted()==true�j
        //get����ListBy�����P��(�����P��ID : long)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();    
            
        List l_lisContracts = l_positionManager.getContractListByOrderUnit(l_parentOrderUnit.getOrderUnitId());
        
        //�i�擾����������List���\�[�g����j
        int l_intCnt = 0;
        if (l_lisContracts != null && !l_lisContracts.isEmpty())
        {
            l_intCnt = l_lisContracts.size();
        }
        
        if (l_intCnt > 1)
        {
            int l_intFlag = 0;
            //this.�����\�񒍕��P��Row.���Ϗ����敪==�i"�����_��"or"�P���v��"�j�̏ꍇ�F
            if (WEB3ClosingOrderDef.RANDOM.equals(this.rsvEqOrderUnitRow.getClosingOrderType()) || 
                WEB3ClosingOrderDef.UNIT_PRICE_PROFIT.equals(this.rsvEqOrderUnitRow.getClosingOrderType()))
            {
                //    this.�����\�񒍕��P��Row.get�������()=="�����ԍρi���ԍρj"or"��������"�ł���΁A
                //    ���P���̏����iasc�j�Ń\�[�g�B
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()) ||
                    OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()))                    
                {
                    l_intFlag = 1;
                }
                //    ��L�ȊO�̏ꍇ�́A���P���̍~���idesc�j�Ń\�[�g�B  
                else
                {
                    l_intFlag = -1;
                }
            }
        
            //this.�����\�񒍕��P��Row.���Ϗ����敪=="�P������"�̏ꍇ�F
            if (WEB3ClosingOrderDef.UNIT_PRICE_LOSS.equals(this.rsvEqOrderUnitRow.getClosingOrderType()))
            {
                //    this.�����\�񒍕��P��Row.get�������()=="�����ԍρi���ԍρj"or"��������"�ł���΁A
                //    ���P���̍~���idesc�j�Ń\�[�g�B
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()) ||
                    OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.rsvEqOrderUnitRow.getOrderType()))                    
                {
                    l_intFlag = -1;
                }
                //    ��L�ȊO�̏ꍇ�́A���P���̏����iasc�j�Ń\�[�g�B
                else
                {
                    l_intFlag = 1;
                }
            }
        
            //�����Ύ���w��̏ꍇ�A���Ϗ����敪==�i"������"or null�j�̃P�[�X�͍l���s�v�B�i���肦�Ȃ����߁j
            if (this.rsvEqOrderUnitRow.getClosingOrderType() == null ||
                WEB3ClosingOrderDef.OPEN_DATE.equals(this.rsvEqOrderUnitRow.getClosingOrderType()))
            {
                l_intFlag = 0;
            }
        
            if (l_intFlag == 1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    EqtypeContractRow l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        EqtypeContractRow l_eqtypeContractRowJ= (EqtypeContractRow)l_lisContracts.get(j);
                                                
                        if (l_eqtypeContractRowJ.getContractPrice() < l_eqtypeContractRowI.getContractPrice())
                        {
                            EqtypeContractRow l_eqtypeContractRowTemp = l_eqtypeContractRowI;
                        
                            l_lisContracts.set(i, l_eqtypeContractRowJ);
                            l_lisContracts.set(j, l_eqtypeContractRowTemp);
                            
                            l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            }
            else if (l_intFlag == -1)
            {
                for (int i = 0; i < l_intCnt; i++)
                {
                    EqtypeContractRow l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                    for (int j = i + 1; j < l_intCnt; j++)
                    {
                        EqtypeContractRow l_eqtypeContractRowJ= (EqtypeContractRow)l_lisContracts.get(j);
                                                
                        if (l_eqtypeContractRowJ.getContractPrice() > l_eqtypeContractRowI.getContractPrice())
                        {
                            EqtypeContractRow l_eqtypeContractRowTemp = l_eqtypeContractRowI;
                        
                            l_lisContracts.set(i, l_eqtypeContractRowJ);
                            l_lisContracts.set(j, l_eqtypeContractRowTemp);
                            
                            l_eqtypeContractRowI = (EqtypeContractRow)l_lisContracts.get(i);
                        }
                    }
                }
            } 
        }
        
        //���������i�c���j���A�\�񒍕��P��.�������� �ŏ���������B
        //�i���������i�c���j = �\�񒍕��P��.��������;�j
        double l_dblOrderQuantity = this.rsvEqOrderUnitRow.getQuantity();
        
        List l_lisArrayList = new ArrayList();
        //�iLOOP�F�@@�擾�E�\�[�g���������̗v�f�iindex�j�����A�J��Ԃ��j
        for (int i = 0; i < l_intCnt; i++)
        {
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisContracts.get(i);
            double l_dblSettleQuantity = 0.0D;
            
            //get�����ڋq���薾��ListBy�����P��Plus����(�����P��ID : long, ����ID : long)
            List l_lisEqtypeFinTransactions = l_positionManager.getFinTransactionListByOrderUnitPlusContract(
                l_parentOrderUnit.getOrderUnitId(),
                l_eqtypeContractRow.getContractId());
            
            int l_intCnt2 = 0;
            if (l_lisEqtypeFinTransactions != null && !l_lisEqtypeFinTransactions.isEmpty())
            {
                l_intCnt2 = l_lisEqtypeFinTransactions.size();
            }
            
            double l_dblExecQuantitySum = 0.0D;
            //�iLOOP�F�@@�擾���������ڋq���薾�ׂ̗v�f�iindex2�j�����A�J��Ԃ��j
            for (int j = 0; j < l_intCnt2; j++)
            {
                EqtypeFinTransactionRow l_eqtypeFinTransactionRow = 
                    (EqtypeFinTransactionRow) l_lisEqtypeFinTransactions.get(j);
                
                //�����ڋq���薾��[index2].��萔�� �̏W�v�l�i���ʇ@@�j�����߂�B
                //��萔�ʂ�SUM�l�@@���@@�i��萔�ʂ�SUM�l�{�����ڋq���薾��[index2].��萔�ʁj
                l_dblExecQuantitySum = l_dblExecQuantitySum + l_eqtypeFinTransactionRow.getQuantity();                            
            }

            //���Y���̌��ω\���ʁi���ʇA�j�����߂�
            WEB3EquityContract l_contract = new WEB3EquityContract(l_eqtypeContractRow);
            double l_dblCanSettleQuantity = l_contract.getQuantity() - l_contract.getLockedQuantity();
            
            //���Y���ւ̌��ϊ������ʂ����肷��
            //�i���ʇA�̂ق������������ʂ̏ꍇ�A���ʇA�����ϊ������ʂƂ��Ďg�p����j
            l_dblSettleQuantity = l_dblExecQuantitySum;
            if (l_dblCanSettleQuantity < l_dblExecQuantitySum)
            {
                l_dblSettleQuantity = l_dblCanSettleQuantity;
            }
            
            //�i���Y���ւ̌��ϊ������� > 0�̏ꍇ�̂݁A�C���X�^���X�����A�v���p�e�B�Z�b�g�j
            if (l_dblSettleQuantity > 0)
            {
                RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                    new RsvEqClosingContractSpecParams();
                    
                //[�v���p�e�B�Z�b�g�d�l]
                //����ID�F�@@this.�����\�񒍕��P��Row.����ID
                l_rsvEqClosingContractSpecParams.setAccountId(this.rsvEqOrderUnitRow.getAccountId());
                
                //�⏕����ID�F�@@this.�����\�񒍕��P��Row.�⏕��ID
                l_rsvEqClosingContractSpecParams.setSubAccountId(this.rsvEqOrderUnitRow.getSubAccountId());
                
                //����ID�F�@@this.�����\�񒍕��P��Row.����ID
                l_rsvEqClosingContractSpecParams.setOrderId(this.rsvEqOrderUnitRow.getOrderId());
                
                //����ID�F�@@����[index].����ID
                l_rsvEqClosingContractSpecParams.setContractId(l_eqtypeContractRow.getContractId());
                               
                //�ԍϘA�ԁF�@@�iindex�{�P�j
                l_rsvEqClosingContractSpecParams.setClosingSerialNo(i + 1);
                                   
                //�ԍϒ������ʁF
                //    �i���������i�c���j >= ���Y���ւ̌��ϊ������ʁj�̏ꍇ�́A���Y���ւ̌��ϊ������ʁB
                if (l_dblOrderQuantity >= l_dblSettleQuantity)
                {
                    l_rsvEqClosingContractSpecParams.setQuantity(l_dblSettleQuantity);
                }                        
                //    �i���������i�c���j < ���Y���ւ̌��ϊ������ʁj�̏ꍇ�́A���������i�c���j�B
                else
                {
                    l_rsvEqClosingContractSpecParams.setQuantity(l_dblOrderQuantity);
                }
                
                //�쐬���t�F�@@this.�����\�񒍕��P��Row.�쐬���t
                l_rsvEqClosingContractSpecParams.setCreatedTimestamp(
                    this.rsvEqOrderUnitRow.getCreatedTimestamp());
                
                //�X�V���t�F�@@this.�����\�񒍕��P��Row.�X�V���t
                l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(
                    this.rsvEqOrderUnitRow.getLastUpdatedTimestamp()); 
                    
                l_lisArrayList.add(l_rsvEqClosingContractSpecParams);                           
            }
            //���������i�c���j -= ���Y���ւ̌��ϊ�������;
            l_dblOrderQuantity -= l_dblSettleQuantity;
            
            //���Z�̌��ʁA
            //�������ʁi�c���j <= 0 �ƂȂ����ꍇ�́A
            //������LOOP�iindex�j�𔲂���B
            if (l_dblOrderQuantity <= 0)
            {
                break;
            }
        }

        //1.4.4.5:�ireturn �쐬�����\�񌚊��ԍώw����s[];�j    
        RsvEqClosingContractSpecRow[] l_rsvRsvEqClosingContractSpecRows = null;

        if (l_lisArrayList != null && !l_lisArrayList.isEmpty())
        {
            if (l_dblOrderQuantity > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_rsvRsvEqClosingContractSpecRows = 
                new RsvEqClosingContractSpecRow[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_rsvRsvEqClosingContractSpecRows);
        }

        log.exiting(STR_METHOD_NAME);
        return l_rsvRsvEqClosingContractSpecRows;
    }
    
    /**
     * (is�o����܂Œ����P��)<BR>
     * �u�o����܂Œ����v�̒������ǂ����𔻒肷��B<BR>
     * �u�o����܂Œ����v�̏ꍇ��true���A�u�o����܂Œ����v�ł͂Ȃ��ꍇ��false���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.�����\�񒍕��P��Row.���񒍕��̒����P��ID��null�̏ꍇ�́A<BR>
     * true��Ԃ��B<BR>
     * �@@�@@�@@�ȊO�Afalse��Ԃ��B
     * @@return boolean
     * @@roseuid 433A673F0232
     */
    public boolean isCarriedOrderUnit() 
    {   
        final String STR_METHOD_NAME = " isCarriedOrderUnit() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@this.�����\�񒍕��P��Row.���񒍕��̒����P��ID��null�̏ꍇ�́A     
        if (this.rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            
            //�ȊO�Afalse��Ԃ��B
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        // true��Ԃ��B
        return true;
    }
    
    /**
     * (is������)<BR>
     * �iisOrdered�j<BR>
     * �\�񒍕��������ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.�����\�񒍕��P��Row.�����P��ID��null�̏ꍇ�A<BR>
     * true�i�������ρj��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrdered() 
    {
        final String STR_METHOD_NAME = " isOrdered() ";
        log.entering(STR_METHOD_NAME);
        
        //this.�����\�񒍕��P��Row.�����P��ID��null�̏ꍇ�A
        if (this.rsvEqOrderUnitRow.getOrderUnitIdIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            
            //�ȊO�Afalse��ԋp����B
            return false;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //true�i�������ρj��ԋp����B
        return true;
    }
    
    /**
     * (is�������s��)<BR>
     * �iisOrderExecuted�j<BR>
     * �\�񒍕����������������s�ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * this.is������()==true�i����OK�j�A<BR>
     * �܂��� this.�����\�񒍕��P��Row.�����G���[�R�[�h��null�i�����G���[�j�̏ꍇ�A
     * true�i���������s�ρj��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B
     * @@return boolean
     * @@roseuid 4341EA9B00F0
     */
    public boolean isOrderExecuted() 
    {
        final String STR_METHOD_NAME = " isOrderExecuted() ";
        log.entering(STR_METHOD_NAME);
        
        if (this.isOrdered() ||
            (this.rsvEqOrderUnitRow.getOrderErrorCode() != null))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (validate�����\���)<BR>
     * �ivalidateOrderForChangeability�j<BR>
     * �\�񒍕��������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �O������F����J�����_�R���e�L�X�g���A�������擾�ȏ�Ԃɐݒ肳��Ă��邱�ƁB<BR>
     * <BR>
     * �P�j�@@�����L����ԃ`�F�b�N<BR>
     * <BR>
     * �@@�@@this.�����\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A<BR>
     * �@@�@@�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02287<BR>
     * <BR>
     * �Q�j�@@�������`�F�b�N<BR>
     * <BR>
     * �@@�@@this.�����\�񒍕��P��Row.��������������ԊǗ�.get������(void)�̏ꍇ�́A<BR>
     * �@@�@@�u�w��̗\�񒍕��͔������𒴉߁v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02288<BR>
     * <BR>
     * �R�j�@@�e�����̃`�F�b�N<BR>
     * <BR>
     * �R�|�P�j�@@�e�����̎擾<BR>
     * <BR>
     * �@@�@@�A�������}�l�[�W��Impl.get�����e�����̒����P��<BR>
     * �@@�@@(this.�����\�񒍕��P��Row.�e�����̒���ID)�Ŏ擾����B<BR>
     * <BR>
     * �R�|�Q�j�@@�e�����̃`�F�b�N<BR>
     * <BR>
     * �@@�@@�A�������}�l�[�W��Impl.validate�g���K�[�����ݒ�To�e����<BR>
     * �@@�@@(�擾�����e�����̒����P��)��delegate����B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4628039A
     */
    public void validateOrderForChangeability() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderForChangeability() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����L����ԃ`�F�b�N
        //this.�����\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A
        //�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvEqOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("�����L����ԁ��I�[�v��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����L����ԁ��I�[�v��");
        }
        
        //�Q�j�@@�������`�F�b�N
        //this.�����\�񒍕��P��Row.��������������ԊǗ�.get������(void)�̏ꍇ�́A
        //�u�w��̗\�񒍕��͔������𒴉߁v�̗�O��throw����B
        Date l_datBizdate = WEB3DateUtility.getDate(this.rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        int l_intCompareValue = WEB3DateUtility.compareToDay(
            l_datBizdate, 
            WEB3GentradeTradingTimeManagement.getOrderBizDate());
        
        if (l_intCompareValue != 0)
        {
            log.debug("��������������ԊǗ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02288,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������������ԊǗ�");
        }
        
        // �R�j�@@�e�����̃`�F�b�N
        //�R�|�P�j�@@�e�����̎擾
        //�A�������}�l�[�W��Impl.get�����e�����̒����P��
        WEB3ToSuccOrderManagerImpl l_orderMgr = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_eqOrderUnit = 
            l_orderMgr.getEqtypeParentOrderUnit(this.rsvEqOrderUnitRow.getParentOrderId());
        
        //�R�|�Q�j�@@�e�����̃`�F�b�N
        //�A�������}�l�[�W��Impl.validate�g���K�[�����ݒ�To�e����
        l_orderMgr.validateTriggerOrderSettingToParentOrder(l_eqOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate����\���)<BR>
     * �ivalidateOrderForCancellation�j<BR>
     * �\�񒍕�������\�ȏ�Ԃ��ǂ������`�F�b�N����B<BR>
     * �O������F����J�����_�R���e�L�X�g���A�������擾�ȏ�Ԃɐݒ肳��Ă��邱�ƁB<BR>
     * <BR>
     * �P�j�@@�����L����ԃ`�F�b�N<BR>
     * <BR>
     * �@@�@@this.�����\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A<BR>
     * �@@�@@�u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02287<BR>
     * <BR>
     * �Q�j�@@�������`�F�b�N<BR>
     * <BR>
     * �@@�@@this.�����\�񒍕��P��Row.��������������ԊǗ�.get������(void)�̏ꍇ�́A<BR>
     * �@@�@@�u�w��̗\�񒍕��͔������𒴉߁v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException <BR>
     *     tag: BUSINESS_ERROR_02288<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B4A3F006E
     */
    public void validateOrderForCancellation() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrderForCancellation() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����L����ԃ`�F�b�N                                                                                                                   
        //    this.�����\�񒍕��P��Row.�����L����ԁ�"�I�[�v��"�̏ꍇ�́A             
        //    �u�w��̗\�񒍕��̓N���[�Y�ρv�̗�O��throw����B                       
        if (!OrderOpenStatusEnum.OPEN.equals(this.rsvEqOrderUnitRow.getOrderOpenStatus()))
        {
            log.debug("�����L����ԁ��I�[�v��");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02287,
                this.getClass().getName() + STR_METHOD_NAME,
                "�����L����ԁ��I�[�v��");
        }
                                                                  
        //�Q�j�@@�������`�F�b�N                                                                                                                        
        //    this.�����\�񒍕��P��Row.��������������ԊǗ�.get������(void)�̏ꍇ�́A 
        //    �u�w��̗\�񒍕��͔������𒴉߁v�̗�O��throw����B  
        Date l_datBizdate = WEB3DateUtility.getDate(this.rsvEqOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        int l_intCompareValue = WEB3DateUtility.compareToDay(
            l_datBizdate, 
            WEB3GentradeTradingTimeManagement.getOrderBizDate());
        
        if (l_intCompareValue != 0)
        {
            log.debug("��������������ԊǗ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02288,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������������ԊǗ�");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P����ԋp����B<BR>
     * �i���X�|���X.�T�Z�뉿�P�� �ݒ�Ɏg�p����j<BR>
     * <BR>
     * �P�j�@@this.is������()==false�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �@@�@@�A�������}�l�[�W��Impl.is���Δ������()==true�i���Δ����j�̏ꍇ�́A<BR>
     * �@@�@@null��ԋp����B<BR>
     * <BR>
     * �@@�@@-------------------------------------------------------<BR>
     * �@@�@@��is���Δ������()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�A����������敪�F�@@this.�����\�񒍕��P��Row.�A����������敪<BR>
     * �@@�@@�e�����̒����P�ʁF�@@this.get�e�����̒����P��()<BR>
     * �@@�@@-------------------------------------------------------<BR>
     * <BR>
     * �R�j�@@���Δ����łȂ��ꍇ�A�T�Z�뉿�P�������߂�B<BR>
     * <BR>
     * �@@�����v�Z�T�[�r�X.calc�T�Z�뉿�P��(this.�����\�񒍕��P��Row.����ID,<BR>
     * �@@�@@�ithis.�����\�񒍕��P��Row.����ID�A�⏕����ID�j�ɊY������⏕����,<BR>
     * �@@�@@this.�����\�񒍕��P��Row.�ŋ敪)<BR>
     * �@@���R�[�����A�߂�l��ԋp����B<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 434094D20321
     */
    public String getEstimatedBookPrice() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getEstimatedBookPrice() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@this.is������()==false�̏ꍇ�́Anull��ԋp����B 
        if (!this.isSellOrder())
        {
            log.exiting(STR_METHOD_NAME);   
            return null;
        }
        //    �ȊO�A�ȉ��̏������s���B 
        else
        {
            //�Q�j�@@�e�����̔��Δ���������ǂ����𔻒肷��B 
            //    �A�������}�l�[�W��Impl.is���Δ������()==true�i���Δ����j�̏ꍇ�́A 
            //    null��ԋp����B 
            WEB3ToSuccOrderManagerImpl l_orderMgr = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            boolean l_blnIsReverseTrade = l_orderMgr.isReversingTrade(
                this.rsvEqOrderUnitRow.getReserveOrderTradingType(),
                this.getParentOrderUnit());
            if (l_blnIsReverseTrade)
            {
                log.exiting(STR_METHOD_NAME);   
                return null;
            }
                
            //�R�j�@@���Δ����łȂ��ꍇ�A�T�Z�뉿�P�������߂�B 
            //  �����v�Z�T�[�r�X.calc�T�Z�뉿�P��(this.�����\�񒍕��P��Row.����ID, 
            //    �ithis.�����\�񒍕��P��Row.����ID�A�⏕����ID�j�ɊY������⏕����,  
            //    this.�����\�񒍕��P��Row.�ŋ敪) 
            //  ���R�[�����A�߂�l��ԋp����B 
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
               (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount =
                    l_finApp.getAccountManager().getSubAccount(
                        this.rsvEqOrderUnitRow.getAccountId(),
                        this.rsvEqOrderUnitRow.getSubAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            double l_dblEstimatedBookPrice = l_eqBizLogicProvider.calcEstimatedBookPrice(
                this.rsvEqOrderUnitRow.getProductId(),
                l_subAccount,
                this.rsvEqOrderUnitRow.getTaxType());
            
            log.exiting(STR_METHOD_NAME);   
            return WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }
    }
    
    /**
     * (create�A���������ʏ��)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�A���������ʏ��̃C���X�^���X��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�i�e�����j����ID�F�@@this.�����\�񒍕��P��Row.�e�����̒���ID<BR>
     * �@@�@@�A����������敪�F�@@this.�����\�񒍕��P��Row.�A����������敪<BR>
     * <BR>
     * �R�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@return WEB3SuccCommonInfo
     * @@roseuid 433B5AA002E6
     */
    public WEB3SuccCommonInfo createSuccCommonInfo() 
    {
        final String STR_METHOD_NAME = " createSuccCommonInfo() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�߂�l�C���X�^���X�𐶐�����B
        WEB3SuccCommonInfo l_commonInfo = new WEB3SuccCommonInfo();
        
        //�Q�j�@@�v���p�e�B���Z�b�g����B
        //�i�e�����j����ID�F�@@this.�����\�񒍕��P��Row.�e�����̒���ID
        l_commonInfo.parentOrderId = 
            WEB3StringTypeUtility.formatNumber(this.rsvEqOrderUnitRow.getParentOrderId());
        
        //�A����������敪�F�@@this.�����\�񒍕��P��Row.�A����������敪
        l_commonInfo.succTradingType = this.rsvEqOrderUnitRow.getReserveOrderTradingType();
        
        //�R�j�@@�߂�l�C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);      
        return l_commonInfo;
    }
    
    /**
     * (create�P�������l���)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�A�������P�������l����<BR>
     * �C���X�^���X��ԋp����B<BR>
     * <BR>
     * �P�j�@@�P�������l�̎w��L���𔻒肷��B<BR>
     * <BR>
     * �@@�@@this.is�}�w�l()==false�̏ꍇ�́A<BR>
     * �@@�@@�P�������w��Ȃ��Ɣ��肵�Anull��ԋp����B<BR>
     * <BR>
     * �@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@�߂�l�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�����F�@@this.�����\�񒍕��P��Row.�P�������l�̕�������<BR>
     * �@@�@@�@@�@@�@@�@@�@@���}�C�i�X�l�̏ꍇ�́A�}�C�i�X���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@��0�ȏ�̏ꍇ�́A�v���X���Z�b�g�B<BR>
     * �@@�@@�P�������l�F�@@this.�����\�񒍕��P��Row.�P�������l�̒P������<BR>
     * �@@�@@�@@�@@�@@�@@�@@���}�C�i�X�l�̏ꍇ�́A�l�𔽓]�����i���������������j�l���Z�b�g�B<BR>
     * �@@�@@�@@�@@�@@�@@�@@��0�ȏ�̏ꍇ�A���̂܂܂̒l���Z�b�g�B<BR>
     * <BR>
     * �S�j�@@�߂�l�C���X�^���X��ԋp����B<BR>
     * @@return WEB3SuccPriceAdjustmentValueInfo
     * @@roseuid 433B5B14020C
     */
    public WEB3SuccPriceAdjustmentValueInfo createSuccPriceAdjustmentValueInfo() 
    {
        final String STR_METHOD_NAME = " createSuccPriceAdjustmentValueInfo() ";
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
        //  �����F�@@this.�����\�񒍕��P��Row.�P�������l�̕������� 
        //�@@�@@�@@    ���}�C�i�X�l�̏ꍇ�́A�}�C�i�X���Z�b�g�B 
        //�@@�@@�@@    ��0�ȏ�̏ꍇ�́A�v���X���Z�b�g�B 
        //  �P�������l�F�@@this.�����\�񒍕��P��Row.�P�������l�̒P������ 
        //�@@�@@�@@    ���}�C�i�X�l�̏ꍇ�́A�l�𔽓]�����i���������������j�l���Z�b�g�B 
        //�@@�@@�@@    ��0�ȏ�̏ꍇ�A���̂܂܂̒l���Z�b�g�B
        double l_dblPriceAdjustValue = this.rsvEqOrderUnitRow.getPriceAdjustValue(); 
        if (l_dblPriceAdjustValue < 0)
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.SUBTRACT;
            l_piceAdjustmentValueInfo.priceAdjustmentValue = WEB3StringTypeUtility.formatNumber(
                Math.abs(l_dblPriceAdjustValue));
        }
        else
        {
            l_piceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.ADD;
            l_piceAdjustmentValueInfo.priceAdjustmentValue = WEB3StringTypeUtility.formatNumber(l_dblPriceAdjustValue);
        }      
                         
        //�S�j�@@�߂�l�C���X�^���X��ԋp����B         
        log.exiting(STR_METHOD_NAME);      
        return l_piceAdjustmentValueInfo;
    }
    
    /**
     * (get���b�Z�[�W�p�����P���敪)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�����P���敪��ԋp����B<BR>
     * <BR>
     * ��this.is�}�w�l�w��()==true�̏ꍇ<BR>
     * �@@�@@"���s"��ԋp����B<BR>
     * <BR>
     * ��this.get�����J�e�S��()=="�����E���n����"�̏ꍇ<BR>
     * �@@�@@"�w�l"��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�@@this.is�w�l����()==true�̏ꍇ�́A"�w�l"��ԋp����B<BR>
     * �@@�@@�ȊO�A"���s"��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B6AA30325
     */
    public String getMsgOrderPriceDiv() 
    {
        final String STR_METHOD_NAME = " getMsgOrderPriceDiv() ";
        log.entering(STR_METHOD_NAME);
        
        // ��this.is�}�w�l�w��()==true�̏ꍇ
        if (this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"���s"��ԋp����B
            return WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        //��this.get�����J�e�S��()=="�����E���n����"�̏ꍇ
        else if (OrderCategEnum.SWAP_MARGIN.equals(this.getOrderCateg()))
        {
            log.exiting(STR_METHOD_NAME);
            //"�w�l"��ԋp����B
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        //����L�ȊO�̏ꍇ
        //this.is�w�l����()==true�̏ꍇ
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //"�w�l"��ԋp����B
            return WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        
        log.exiting(STR_METHOD_NAME);
        //�ȊO�A"���s"��ԋp����B
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
     * ��this.get�����J�e�S��()=="�����E���n����"�̏ꍇ<BR>
     * �@@�@@this.�����\�񒍕��P��Row.�w�l��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�@@this.is�w�l����()==true�̏ꍇ�́Athis.�����\�񒍕��P��Row.�w�l 
     * ��ԋp����B<BR>
     * �@@�@@�ȊO�Anull��ԋp����B<BR>
     * @@return String
     * @@roseuid 433B6BFC0008
     */
    public String getMsgLimitPrice() 
    {
        final String STR_METHOD_NAME = " getMsgLimitPrice() ";
        log.entering(STR_METHOD_NAME);
        
        //��this.is�}�w�l�w��()==true�̏ꍇ
        if (this.isExecPriceOrder())
        {
            log.exiting(STR_METHOD_NAME);
            
            return null;
        }
        //this.get�����J�e�S��()=="�����E���n����"�̏ꍇ
        else if (OrderCategEnum.SWAP_MARGIN.equals(this.getOrderCateg()))
        {
            log.exiting(STR_METHOD_NAME);
            //this.�����\�񒍕��P��Row.�w�l��ԋp����B
            return WEB3StringTypeUtility.formatNumber(this.rsvEqOrderUnitRow.getLimitPrice());
        }
        //����L�ȊO�̏ꍇ
        //this.is�w�l����()==true�̏ꍇ 
        else if (this.isLimitOrder())
        {
            log.exiting(STR_METHOD_NAME);
            //this.�����\�񒍕��P��Row.�w�l��ԋp����B
            return WEB3StringTypeUtility.formatNumber(this.rsvEqOrderUnitRow.getLimitPrice());
        }
        
        log.exiting(STR_METHOD_NAME);
        //�ȊO�Anull��ԋp����B
        return null;
    }
    
    /**
     * (get���b�Z�[�W�p���������敪)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A���������敪��ԋp����B<BR>
     * <BR>
     * ��this.is�o����܂Œ����P��()==true�̏ꍇ<BR>
     * �@@�@@"�o����܂Œ���"��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�@@"��������"��ԋp����B
     * @@return String
     * @@roseuid 433B6E2B0150
     */
    public String getMsgExpirationDateType() 
    {
        final String STR_METHOD_NAME = " getMsgExpirationDateType() ";
        log.entering(STR_METHOD_NAME);
        
        //��this.is�o����܂Œ����P��()==true�̏ꍇ
        if (this.isCarriedOrderUnit())
        {
            log.exiting(STR_METHOD_NAME);
            //"�o����܂Œ���"��ԋp����B
            return WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        // ����L�ȊO�̏ꍇ
        //"��������"��ԋp����B
        return  WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
    }
    
    /**
     * (get���b�Z�[�W�p�����L������)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�����L��������ԋp����B<BR>
     * <BR>
     * ��this.is�o����܂Œ����P��()==true�̏ꍇ<BR>
     * �@@�@@this.�����\�񒍕��P��Row.���������� ��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�@@null��ԋp����B
     * @@return Date
     * @@roseuid 433B6E800279
     */
    public Date getMsgExpirationDate() 
    {
        final String STR_METHOD_NAME = " getMsgExpirationDate() ";
        log.entering(STR_METHOD_NAME);
        
        //��this.is�o����܂Œ����P��()==true�̏ꍇ
        if (this.isCarriedOrderUnit())
        {
            log.exiting(STR_METHOD_NAME);
            
            //this.�����\�񒍕��P��Row.���������� ��ԋp����B
            return this.rsvEqOrderUnitRow.getExpirationDate();
        }
        
        log.exiting(STR_METHOD_NAME);
        
        //����L�ȊO�̏ꍇ
        //null��ԋp����B
        return null;
    }
    
    /**
     * (get���b�Z�[�W�p�����挻�n�������敪)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�����挻�n�������敪��ԋp����B<BR>
     * <BR>
     * ��this.�����\�񒍕��P��Row.�ŋ敪�i�������n�j=="���̑�"�̏ꍇ<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * ��this.�����\�񒍕��P��Row.�ŋ敪�i�������n�j=="���"�̏ꍇ<BR>
     * �@@�@@"���"��ԋp����B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ<BR>
     * �@@�@@"����"��ԋp����B
     * @@return String
     * @@roseuid 433CACD202AA
     */
    public String getMsgSwapTaxType() 
    {
        final String STR_METHOD_NAME = " getMsgSwapTaxType() ";
        log.entering(STR_METHOD_NAME);
        
        TaxTypeEnum l_swapTaxType = this.rsvEqOrderUnitRow.getSwapTaxType();
        
        //��this.�����\�񒍕��P��Row.�ŋ敪�i�������n�j=="���̑�"�̏ꍇ
        if (TaxTypeEnum.UNDEFINED.equals(l_swapTaxType))
        {
            log.exiting(STR_METHOD_NAME);
            
            //null��ԋp����B
            return null;
        }
        //��this.�����\�񒍕��P��Row.�ŋ敪�i�������n�j=="���"�̏ꍇ
        else if (TaxTypeEnum.NORMAL.equals(l_swapTaxType))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"���"��ԋp����B
            return WEB3TaxTypeSpecialDef.NORMAL;
        }
        
        //����L�ȊO�̏ꍇ
        //"����"��ԋp����B
        return WEB3TaxTypeSpecialDef.SPECIAL;
    }
    
    /**
     * (get���b�Z�[�W�p�l�i����)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A�l�i������ԋp����B<BR>
     * <BR>
     * "�����Ȃ�"��ԋp����B
     * @@return String
     * @@roseuid 433B76E4018F
     */
    public String getMsgPriceCondType() 
    {
        return WEB3PriceConditionDef.DEFAULT;
    }
    
    /**
     * (get���b�Z�[�W�p���s����)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A���s������ԋp����B<BR>
     * <BR>
     * "������"��ԋp����B
     * @@return String
     * @@roseuid 433B77070306
     */
    public String getMsgExecCondType() 
    {
        return WEB3ExecutionConditionDef.NO_CONDITION;
    }
    
    /**
     * (get���b�Z�[�W�p���������敪)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A���������敪��ԋp����B<BR>
     * <BR>
     * "�w��Ȃ�"��ԋp����B
     * @@return String
     * @@roseuid 433B771C0363
     */
    public String getMsgOrderCondType() 
    {
        return WEB3OrderingConditionDef.DEFAULT;
    }
    
    /**
     * (get���b�Z�[�W�p����敪)<BR>
     * AP�w��PR�w�Ԃ̃��b�Z�[�W�N���X�Ŏg�p����A����敪��ԋp����B<BR>
     * <BR>
     * this.get�������()���A<BR>
     * ��OrderTypeEnum.������������"�̏ꍇ<BR>
     * �@@"����������"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.�������蒍��"�̏ꍇ<BR>
     * �@@"����������"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.�V�K���������̏ꍇ<BR>
     * �@@"�V�K��������"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.�V�K���������j�̏ꍇ<BR>
     * �@@"�V�K��������"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.�����ԍϒ����i���ԍρj�j�̏ꍇ<BR>
     * �@@"�����ԍϒ����i���ԍρj"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.�����ԍϒ����i���ԍρj�j�̏ꍇ<BR>
     * �@@"�����ԍϒ����i���ԍρj"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.���������j�̏ꍇ<BR>
     * �@@"��������"��ԋp����B<BR>
     * <BR>
     * ��OrderTypeEnum.���n�����j�̏ꍇ<BR>
     * �@@"���n����"��ԋp����B
     * @@return String
     * @@roseuid 433B7D160381
     */
    public String getMsgTradingType() 
    {
        final String STR_METHOD_NAME = " getMsgTradingType() ";
        log.entering(STR_METHOD_NAME);
        
        //this.get�������()���A
        //��OrderTypeEnum.������������"�̏ꍇ
        if (OrderTypeEnum.EQUITY_BUY.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"����������"��ԋp����B 
            return WEB3ToSuccMessageTradingTypeDef.BUY_ORDER;
        }
        //��OrderTypeEnum.�������蒍��"�̏ꍇ
        else if (OrderTypeEnum.EQUITY_SELL.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"����������"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.SELL_ORDER;
        }
        //��OrderTypeEnum.�V�K���������̏ꍇ
        else if (OrderTypeEnum.MARGIN_LONG.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"�V�K��������"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.OPEN_LONG_MARGIN;
        }
        //��OrderTypeEnum.�V�K���������j�̏ꍇ
        else if (OrderTypeEnum.MARGIN_SHORT.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"�V�K��������"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.OPEN_SHORT_MARGIN;
        }
        //��OrderTypeEnum.�����ԍϒ����i���ԍρj�j�̏ꍇ
        else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"�����ԍϒ����i���ԍρj"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_LONG_MARGIN;
        }
        //��OrderTypeEnum.�����ԍϒ����i���ԍρj�j�̏ꍇ
        else if (OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"�����ԍϒ����i���ԍρj"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.CLOSE_SHORT_MARGIN;
        }
        //��OrderTypeEnum.���������j�̏ꍇ
        else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"��������"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.SWAP_MARGIN_LONG;
        }
        //��OrderTypeEnum.���n�����j�̏ꍇ
        else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(this.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            
            //"���n����"��ԋp����B
            return WEB3ToSuccMessageTradingTypeDef.SWAP_MARGIN_SHORT;
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return null;
    }
}
@
