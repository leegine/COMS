head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������}�l�[�W��Impl(WEB3ToSuccOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 �s�p (���u) �V�K�쐬 
Revesion History : 2007/06/05 �đo�g (���u) �d�l�ύX���f��234
Revesion History : 2008/03/20 �����Q (���u) �d�l�ύX���f��250�A251�A260�A292�A299�A300�A301
Revesion History : 2008/04/07 �����Q (���u) DB�X�V�d�lNo.044�A046�A047�A048�A057 ����
Revesion History : 2008/04/14 ��іQ (���u) ���f��No.279,322
Revesion History : 2008/04/22 ��іQ (���u) ���f��No.324,336
Revesion History : 2008/04/22 ��іQ (���u) ���f��No.342
Revesion History : 2008/04/28 �����Q (���u) ���f��No.345
Revesion History : 2008/05/15 ���z (���u) ���f��No.350
Revesion History : 2008/05/19 ����(���u) �d�l�ύX���f��No.318
Revesion History : 2008/08/18 ���� (���u) IFO�����_�Ή�
*/

package webbroker3.triggerorder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3SucOrderTradingPowerCheckDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�A�������}�l�[�W��Impl)<BR>
 * �A�������p�̒����}�l�[�W���B<BR>
 * �iOrderManager�̎����N���X�j
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccOrderManagerImpl implements OrderManager 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccOrderManagerImpl.class);
        
    /**
     * @@roseuid 4348CDDF008C
     */
    public WEB3ToSuccOrderManagerImpl() 
    {
     
    }
    
    /**
     * �igetOrder�̎����F���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param arg0
     * @@return Order
     * @@throws NotFoundException
     * @@roseuid 431D5C6E035A
     */
    public Order getOrder(long arg0) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * �itoOrder�̎����F���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param arg0
     * @@return Order
     * @@roseuid 431E8661026D
     */
    public Order toOrder(Row arg0) 
    {
        return null;
    }
    
    /**
     * �igetOrderUnit�̎����F���g�p�j<BR>
     * <BR>
     * null��ԋp����B
     * @@param l_lngOrderUnitId - �����P��ID�B
     * @@return OrderUnit
     * @@throws NotFoundException
     * @@roseuid 431E867B01D1
     */
    public OrderUnit getOrderUnit(long l_lngOrderUnitId) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * �igetOrderAction�i�������j�j
     * @@param arg0
     * @@return OrderAction
     * @@throws NotFoundException
     * @@roseuid 43328EC00205
     */
    public OrderAction getOrderAction(long arg0) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * �itoOrderAction�i�������j�j
     * @@param arg0
     * @@return OrderAction
     * @@roseuid 43328EC00234
     */
    public OrderAction toOrderAction(Row arg0) 
    {
        return null;
    }
    
    /**
     * �igetOrderExecution�i�������j�j
     * @@param arg0
     * @@return OrderExecution
     * @@throws NotFoundException
     * @@roseuid 43328EC00262
     */
    public OrderExecution getOrderExecution(long arg0) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * �itoOrderExecution�i�������j�j
     * @@param arg0
     * @@return OrderExecution
     * @@roseuid 43328EC00282
     */
    public OrderExecution toOrderExecution(Row arg0) 
    {
        return null;
    }
    
    /**
     * �ivalidateNewOrder�i�������j�j
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return NewOrderValidationResult
     * @@roseuid 43328F3F01B7
     */
    public NewOrderValidationResult validateNewOrder(SubAccount arg0, ProductTypeEnum arg1, NewOrderSpec arg2) 
    {
        return null;
    }
    
    /**
     * �isubmitNewOrder�i�������j�j
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B
     * @@param l_orderSpec - (�������e)<BR>
     * �������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_blnIsSkipOrderCheck - (isSkip�����R��)<BR>
     * �����R�����X�L�b�v���邩�ǂ����̃t���O�B<BR>
     * �i���g�p�B���true�����ꂽ�ꍇ�Ɠ�������ƂȂ�j
     * @@return OrderSubmissionResult
     * @@roseuid 432918AD015F
     */
    public OrderSubmissionResult submitNewOrder(
        SubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        NewOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        boolean l_blnIsSkipOrderCheck) 
    {
        return null;
    }
    
    /**
     * �ivalidateChangeOrder�i�������j�j
     * @@param arg0
     * @@param arg1
     * @@return OrderValidationResult
     * @@roseuid 43328F3F01D6
     */
    public OrderValidationResult validateChangeOrder(SubAccount arg0, ChangeOrderSpec arg1) 
    {
        return null;
    }
    
    /**
     * �isubmitChangeOrder�i�������j�j
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@return OrderSubmissionResult
     * @@roseuid 43328F3F0205
     */
    public OrderSubmissionResult submitChangeOrder(
        SubAccount arg0, 
        ChangeOrderSpec arg1, 
        String arg2, 
        boolean arg3) 
    {
        return null;
    }
    
    /**
     * �ivalidateCancelOrder�i�������j�j
     * @@param arg0
     * @@param arg1
     * @@return OrderValidationResult
     * @@roseuid 43328F3F0234
     */
    public OrderValidationResult validateCancelOrder(SubAccount arg0, CancelOrderSpec arg1) 
    {
        return null;
    }
    
    /**
     * �isubmitCancelOrder�i�������j�j
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@return OrderSubmissionResult
     * @@roseuid 43328F3F0253
     */
    public OrderSubmissionResult submitCancelOrder(
        SubAccount arg0, 
        CancelOrderSpec arg1, 
        String arg2, 
        boolean arg3) 
    {
        return null;
    }
    
    /**
     * �igetOrderValidator�i�������j�j <BR>
     * <BR>
     * @@return OrderValidator
     * @@roseuid 43328F3F0282
     */
    public OrderValidator getOrderValidator() 
    {
        return null;
    }
    
    /**
     * �ioverrideOrderValidator�i�������j�j
     * @@param arg0
     * @@roseuid 43328F3F02B1
     */
    public void overrideOrderValidator(OrderValidator arg0) 
    {     
    }
    
    /**
     * �igetOrderUnit�j<BR>
     * <BR>
     * �w�肳�ꂽ�\�񒍕��P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̖����^�C�v=="����"�̏ꍇ�A<BR>
     * �@@�@@�@@this.get�����\�񒍕��P��(�����̒���ID)��delegate����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@this.get�敨OP�\�񒍕��P��(�����̒���ID)��delegate����B
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@return OrderUnit
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 431E95F20319
     */
    public OrderUnit getOrderUnit(
        ProductTypeEnum l_productType, 
        long l_lngOrderId) throws NotFoundException,WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnit(ProductTypeEnum, long) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����̖����^�C�v=="����"�̏ꍇ�A
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            //this.get�����\�񒍕��P��(�����̒���ID)��delegate����B
            log.exiting(STR_METHOD_NAME);
            return this.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        //��L�ȊO�̏ꍇ�A
        else
        {
            //this.get�敨OP�\�񒍕��P��(�����̒���ID)��delegate����B
            log.exiting(STR_METHOD_NAME);
            return this.getReserveIfoOrderUnit(l_lngOrderId);
        }
    }
    
    /**
     * (get�����\�񒍕��P��)<BR>
     * �igetReserveEqtypeOrderUnit�j<BR>
     * <BR>
     * �w�肳�ꂽ�����\�񒍕��P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID = �����̒���ID <BR>
     * <BR>
     * �Q�j�@@this.toOrderUnit(�擾���������\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A<BR>
     * �@@�@@�@@�����\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@���������I�u�W�F�N�g��ԋp����B
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@return webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl
     * @@throws NotFoundException,WEB3BaseException
     * @@roseuid 431E97670348
     */
    public WEB3ToSuccEqTypeOrderUnitImpl getReserveEqtypeOrderUnit(long l_lngOrderId) 
        throws NotFoundException, WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getReserveEqtypeOrderUnit(long) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B
            //[����]
            //����ID = �����̒���ID
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = RsvEqOrderUnitDao.findRowByPk(l_lngOrderId);
            //DataFindException, DataNetworkException, DataQueryException
              
            //�Q�j�@@this.toOrderUnit(�擾���������\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A
            //�����\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B
            //���������I�u�W�F�N�g��ԋp����B 
            WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnitImpl = 
                (WEB3ToSuccEqTypeOrderUnitImpl)this.toOrderUnit(l_rsvEqOrderUnitRow);
                
            log.exiting(STR_METHOD_NAME);
                
            return l_eqTypeOrderUnitImpl;
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);      
            
            log.error("�������ʂɈ�v����s�����݂��Ȃ��B", l_ex);      
            throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�: OrderId = " + l_lngOrderId);
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
     * (get�敨OP�\�񒍕��P��)<BR>
     * �w�肳�ꂽ�敨OP�\�񒍕��P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * �igetReserveIfoOrderUnit�j<BR>
     * <BR>
     * �w�肳�ꂽ�敨OP�\�񒍕��P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�@@����ID = �����̒���ID<BR>
     * <BR>
     * �Q�j�@@this.toOrderUnit(�擾�����敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �\�񒍕��̒���ID�B
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 431E97E5025D
     */
    public WEB3ToSuccIfoOrderUnitImpl getReserveIfoOrderUnit(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReserveIfoOrderUnit(long) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B
            //[����]
            //����ID = �����̒���ID
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(l_lngOrderId);

            //�Q�jthis.toOrderUnit(�擾�����敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A
            //�敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B
            //���������I�u�W�F�N�g��ԋp����B
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
                (WEB3ToSuccIfoOrderUnitImpl)this.toOrderUnit(l_rsvIfoOrderUnitRow);

            log.exiting(STR_METHOD_NAME);

            return l_ifoOrderUnitImpl;
        }
        catch (DataFindException l_ex)
        {
            log.error("�������ʂɈ�v����s�����݂��Ȃ��B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
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
     * �itoOrderUnit�̎����j<BR>
     * <BR>
     * �w��̒����P��Row�I�u�W�F�N�g����A<BR>
     * �\�񒍕��P�ʃI�u�W�F�N�g�i�����^�敨OP�j�𐶐�����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��Row�̌^�������\�񒍕��P��Row�̏ꍇ<BR>
     * <BR>
     * �@@�@@�����̒����P��Row�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �@@�@@�����\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@���������I�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�����̒����P��Row�̌^���敨OP�\�񒍕��P��Row�̏ꍇ<BR>
     * <BR>
     * �@@�@@�����̒����P��Row�I�u�W�F�N�g�������Ɏw�肵�āA<BR>
     * �@@�@@�敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_orderUnitRow - (�����P��Row)<BR>
     * �����P��Row�B
     * @@return OrderUnit
     * @@roseuid 431E8719025D
     */
    public OrderUnit toOrderUnit(Row l_orderUnitRow) 
    {
        final String STR_METHOD_NAME = " toOrderUnit(Row) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����̒����P��Row�̌^�������\�񒍕��P��Row�̏ꍇ
        if (l_orderUnitRow instanceof RsvEqOrderUnitRow)
        {                        
            //�����̒����P��Row�I�u�W�F�N�g�������Ɏw�肵�āA    
            //�����\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B
            //���������I�u�W�F�N�g��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return new WEB3ToSuccEqTypeOrderUnitImpl((RsvEqOrderUnitRow)l_orderUnitRow);
        }
        else if (l_orderUnitRow instanceof RsvIfoOrderUnitRow)
        {
            //�Q�j�@@�����̒����P��Row�̌^���敨OP�\�񒍕��P��Row�̏ꍇ
            //�����̒����P��Row�I�u�W�F�N�g�������Ɏw�肵�āA
            //�敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B
            //���������I�u�W�F�N�g��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return new WEB3ToSuccIfoOrderUnitImpl((RsvIfoOrderUnitRow)l_orderUnitRow);
        }
        else
        {   
            log.debug("�p�����[�^�̒l�������\�񒍕��P��Row'�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);            
            throw new IllegalArgumentException(
                "�p�����[�^�̒l�������\�񒍕��P��Row'�ȊO�ł��B");
        }
    }
    
    /**
     * (get�L�������q�����P�ʈꗗ)<BR>
     * �igetOpenReserveEqtypeOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȋ����\�񒍕��P�ʃI�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���ȗ\�񒍕��P�ʂ��擾����B<BR>
     * �@@�@@�@@�����\�񒍕��X�V�T�[�r�X.get�L���\�񒍕��P�ʈꗗ(����.�e�����̒���ID)<BR>
     * ���R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A<BR>
     * �@@�@@�@@this.toOrderUnit(�擾���������\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A<BR>
     * �@@�@@�@@�����\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@���������I�u�W�F�N�g��ArrayList��z��ɂ��ĕԋp����B
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl[]
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 432163F0035A
     */
    public WEB3ToSuccEqTypeOrderUnitImpl[] getOpenReserveEqtypeOrderUnits(
        long l_lngParentOrderId) throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenReserveEqtypeOrderUnits(long) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        
        //�P�j�@@�L���ȗ\�񒍕��P�ʂ��擾����B
        //�����\�񒍕��X�V�T�[�r�X.get�L���\�񒍕��P�ʈꗗ(����.�e�����̒���ID)
        //���R�[������B
        List l_lisRsvEqOrderUnitRow = 
            l_rsvEqOrderUnitUpdateService.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A
        //this.toOrderUnit(�擾���������\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A
        //�����\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B
        //���������I�u�W�F�N�g��ArrayList��z��ɂ��ĕԋp����B
        int l_intListSize = 0;
        if (l_lisRsvEqOrderUnitRow != null && !l_lisRsvEqOrderUnitRow.isEmpty())
        {
            l_intListSize = l_lisRsvEqOrderUnitRow.size();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);            
            return null;
        }
        
        WEB3ToSuccEqTypeOrderUnitImpl[] l_eqOrderUnitImpls = new WEB3ToSuccEqTypeOrderUnitImpl[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_eqOrderUnitImpls[i] = (WEB3ToSuccEqTypeOrderUnitImpl)
                this.toOrderUnit((RsvEqOrderUnitRow)l_lisRsvEqOrderUnitRow.get(i));
        }

        log.exiting(STR_METHOD_NAME);
            
        return l_eqOrderUnitImpls;
    }
    
    /**
     * (get�L���敨OP�q�����P�ʈꗗ)<BR>
     * �igetOpenReserveIfoOrderUnits�j<BR>
     * <BR>
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȑ敨OP�\�񒍕��P�ʃI�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * �P�j�@@�L���ȗ\�񒍕��P�ʂ��擾����B<BR>
     * �@@�@@�@@�敨OP�\�񒍕��X�V�T�[�r�X.get�L���\�񒍕��P�ʈꗗ(����.�e�����̒���ID)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�̗v�f�����A<BR>
     * �@@�@@�@@this.toOrderUnit(�擾�����敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A<BR>
     * �@@�@@�@@�敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B<BR>
     * �@@�@@�@@���������I�u�W�F�N�g��ArrayList��z��ɂ��ĕԋp����B<BR>
     * @@param l_lngParentOrderId - (�e�����̒���ID)<BR>
     * �e�����̒���ID�B
     * @@return WEB3ToSuccIfoOrderUnitImpl[]
     * @@throws WEB3BaseException
     */
    public WEB3ToSuccIfoOrderUnitImpl[] getOpenReserveIfoOrderUnits(
        long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOpenReserveIfoOrderUnits(long) ";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�L���ȗ\�񒍕��P�ʂ��擾����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.get�L���\�񒍕��P�ʈꗗ(����.�e�����̒���ID)���R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);
        List l_lisRsvIfoOrderUnitRow =
            l_rsvIfoOrderUnitUpdateService.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //�Q�j�@@�P�j�̖߂�l�̗v�f�����A
        //this.toOrderUnit(�擾�����敨OP�\�񒍕��P�ʍs�I�u�W�F�N�g)�ɂ��A
        //�敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B
        //���������I�u�W�F�N�g��ArrayList��z��ɂ��ĕԋp����B
        int l_intListSize = 0;
        if (l_lisRsvIfoOrderUnitRow != null && !l_lisRsvIfoOrderUnitRow.isEmpty())
        {
            l_intListSize = l_lisRsvIfoOrderUnitRow.size();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = new WEB3ToSuccIfoOrderUnitImpl[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_ifoOrderUnitImpls[i] = (WEB3ToSuccIfoOrderUnitImpl)
                this.toOrderUnit((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRow.get(i));
        }

        log.exiting(STR_METHOD_NAME);

        return l_ifoOrderUnitImpls;
    }

    /**
     * (get�����\�񒍕����s�P��)<BR>
     * �\�񒍕��i�q�����j�̎��s�P�����擾���ԋp����B<BR>
     * �|�w�l�����^���s�����̏ꍇ�i�}�w�l�w��Ȃ��̏ꍇ�j�A�w�l�܂���0��ԋp����B<BR>
     * �|�}�w�l�w�蒍���̏ꍇ�́A�e�����̎w�l�^�����ɒP�������l�����������P����<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒P�������l == null �̏ꍇ�́A�����̎w�l�����̂܂ܕԋp����B<BR>
     * �@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@��ƂȂ���P�����擾����B<BR>
     * �@@�@@�@@�ȉ��A�e�����̖��̏�Ԃɂ��A���򂷂�B<BR>
     * <BR>
     * �Q�|�P�j�@@�e�������S����肵�Ă���ꍇ<BR>
     * �i�e�����̒����P��.isFullyExecuted()==true�̏ꍇ�j<BR>
     * <BR>
     * �Q�|�P�|�P�j�@@�e�����̖��I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@�e�����̒����P��.getExecutions()���R�[������B<BR>
     * <BR>
     * �Q�|�P�|�Q�j�@@��̖��P�������肷��B<BR>
     * <BR>
     * �@@�@@���q�������}�C�i�X�w��̏ꍇ�i�����̒P�������l�̕���==�}�C�i�X�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A<BR>
     * �@@�@@�@@��̖��P���Ƃ���B<BR>
     * <BR>
     * �@@�@@���q�������v���X�w��̏ꍇ�i�����̒P�������l�̕������}�C�i�X�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A<BR>
     * �@@�@@�@@��̖��P���Ƃ���B<BR>
     * <BR>
     * �Q�|�Q�j�@@�e�������S�����ȊO�̏ꍇ<BR>
     * �i�e�����̒����P��.isFullyExecuted()==false�̏ꍇ�j<BR>
     * <BR>
     * �Q�|�Q�|�P�j�@@�e�������w�l�����̏ꍇ<BR>
     * �i�e�����̒����P��.isLimitPrice()==true�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�e�����̒����P��.�w�l ����̖��P���Ƃ���B<BR>
     * <BR>
     * �Q�|�Q�|�Q�j�@@�e���������s�����̏ꍇ<BR>
     * �i�e�����̒����P��.isLimitPrice()==false�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@EQTYPE�̊g���v���_�N�g�}�l�[�W��.get����(�����̊����������)�ɂ��A<BR>
     * �@@�@@�@@�������擾����B<BR>
     * �@@�@@�@@���߂��������A��̖��P���Ƃ���B<BR>
     * <BR>
     * �R�j�@@���s�P�������߂�B<BR>
     * <BR>
     * �@@�@@�Q�j�ŋ��߂���̖��P�� �ɁA�����̒P�������l�����Z�����l��ԋp����B<BR>
     * <BR>
     * �@@�@@�����߂����s�P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B<BR>
     * �@@ �@@ �@@class: WEB3BusinessLayerException<BR>
     * �@@ �@@ �@@tag:   BUSINESS_ERROR_02298<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �q�����̎w�l�B
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B<BR>
     * �����\�񒍕��P�ʃe�[�u���̓����ځA�������͍��L�ɏ�����l��ݒ肷��B<BR>
     * �i�P�������l�̎w�肪�Ȃ��ꍇ�́Anull���ݒ肳���B�j
     * @@param l_equityTradedProduct - (�����������)<BR>
     * �e�����̖����{�s��ɊY�����銔����������I�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4324EE5300E1
     */
    public double getReserveEqtypeOrderExecPrice(
        EqTypeOrderUnit l_parentOrderUnit, 
        double l_dblLimitPrice, 
        Double l_priceAdjustValue, 
        WEB3EquityTradedProduct l_equityTradedProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getReserveEqtypeOrderExecPrice(EqTypeOrderUnit, double, Double, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);            
            
        //�P�j�@@�����̒P�������l == null �̏ꍇ�́A�����̎w�l�����̂܂ܕԋp����B 
        if (l_priceAdjustValue == null)
        {
            log.exiting(STR_METHOD_NAME);
             return l_dblLimitPrice;
        }

        //�ȊO�A�ȉ��̏������s���B
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        double l_dblPriceAdjustValue = l_priceAdjustValue.doubleValue();
        double l_dblBasePrice = 0.0D; 
        
        //�Q�j�@@��ƂȂ���P�����擾����B 
        //�ȉ��A�e�����̖��̏�Ԃɂ��A���򂷂�B 
        //�Q�|�P�j�@@�e�������S����肵�Ă���ꍇ�i�e�����̒����P��.isFullyExecuted()==true�̏ꍇ�j
        if (l_parentOrderUnit.isFullyExecuted())
        {
            //�Q�|�P�|�P�j�@@�e�����̖��I�u�W�F�N�g���擾����B 
            //�e�����̒����P��.getExecutions()���R�[������B
            OrderExecution[] l_orderExecs = l_parentOrderUnit.getExecutions();
            
            int l_intCnt = 0;
            
            double l_dblMaxPrice = 0.0D; 
            double l_dblMinPrice = 0.0D;
            
            if (l_orderExecs != null && l_orderExecs.length != 0)
            {
                l_intCnt = l_orderExecs.length;
                l_dblMaxPrice = l_orderExecs[0].getExecutionPrice(); 
                l_dblMinPrice = l_orderExecs[0].getExecutionPrice();
            }
            else
            {
                log.debug("�e�����̖��I�u�W�F�N�g�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�e�����̖��I�u�W�F�N�g�����݂��Ȃ��B");
            }
                              
            for (int i = 0; i < l_intCnt; i++)
            {
                double l_dblExecPrice = l_orderExecs[i].getExecutionPrice();
                if (l_dblExecPrice > l_dblMaxPrice)
                {
                    l_dblMaxPrice = l_dblExecPrice;
                }
                if (l_dblExecPrice < l_dblMinPrice)
                {
                    l_dblMinPrice = l_dblExecPrice;
                }
            }

            //�Q�|�P�|�Q�j�@@��̖��P�������肷��B 
            //���q�������}�C�i�X�w��̏ꍇ�i�����̒P�������l�̕���==�}�C�i�X�̏ꍇ�j
            if (l_dblPriceAdjustValue < 0) 
            {
                //�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A 
                //��̖��P���Ƃ���B
                l_dblBasePrice = l_dblMinPrice;

            }
            //���q�������v���X�w��̏ꍇ�i�����̒P�������l�̕������}�C�i�X�̏ꍇ�j
            else 
            {
                //�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A 
                //��̖��P���Ƃ���B
                l_dblBasePrice = l_dblMaxPrice;
            }                  
        }            
        //�Q�|�Q�j�@@�e�������S�����ȊO�̏ꍇ�i�e�����̒����P��.isFullyExecuted()==false�̏ꍇ�j 
        else
        {
            //�Q�|�Q�|�P�j�@@�e�������w�l�����̏ꍇ�i�e�����̒����P��.isLimitPrice()==true�̏ꍇ�j                
            if (!l_parentOrderUnit.isMarketOrder())
            {
                //�e�����̒����P��.�w�l ����̖��P���Ƃ���B
                l_dblBasePrice = l_parentOrderUnit.getLimitPrice();
            }
            //�Q�|�Q�|�Q�j�e���������s�����̏ꍇ�i�e�����̒����P��.isLimitPrice()==false�̏ꍇ�j 
            else
            {
                //EQTYPE�̊g���v���_�N�g�}�l�[�W��.get����(�����̊����������)�ɂ��A 
                //�������擾����B 
                //���߂��������A��̖��P���Ƃ���B
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3EquityProductManager l_productMgr = 
                    (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                l_dblBasePrice = l_productMgr.getCurrentPrice(l_equityTradedProduct);                    
            } 
        }

        //�R�j�@@���s�P�������߂�B 
        //�Q�j�ŋ��߂���̖��P�� �ɁA�����̒P�������l�����Z�����l��ԋp����B 
        double l_dblExecPrice = l_dblBasePrice + l_dblPriceAdjustValue;             
        
        //�����߂����s�P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B 
        if (l_dblExecPrice <= 0)
        {
            log.debug("���s�P����0�ȉ��B " + l_dblExecPrice);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                this.getClass().getName() + STR_METHOD_NAME,
                "���s�P����0�ȉ��B " + l_dblExecPrice);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblExecPrice;
    }
    
    /**
     * (get�敨OP�\�񒍕����s�P��)<BR>
     * �igetReserveIfoOrderExecPrice�j<BR>
     * <BR>
     * �\�񒍕��i�q�����j�̎��s�P�����擾���ԋp����B<BR>
     * �@@�E�w�l�����^���s�����̏ꍇ�i�}�w�l�w��Ȃ��̏ꍇ�j�A�w�l�܂���0��ԋp����B<BR>
     * �@@�E�}�w�l�w�蒍���̏ꍇ�́A�e�����̎w�l�^�����ɒP�������l�����������P����ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒P�������l == null �̏ꍇ�́A�����̎w�l�����̂܂ܕԋp����B<BR>
     * �@@�@@�ȊO�A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@��ƂȂ���P�����擾����B<BR>
     * �@@�@@�ȉ��A�e�����̖��̏�Ԃɂ��A���򂷂�B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�e�������S����肵�Ă���ꍇ<BR>
     * �i�e�����̒����P��.isFullyExecuted()==true�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j�@@�e�����̖��I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�����̒����P��.getExecutions()���R�[������B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�@@��̖��P�������肷��B<BR>
     * <BR>
     * �@@�@@�@@���q�������}�C�i�X�w��̏ꍇ�i�����̒P�������l�̕���==�}�C�i�X�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A<BR>
     * �@@�@@�@@�@@�@@��̖��P���Ƃ���B<BR>
     * <BR>
     * �@@�@@�@@���q�������v���X�w��̏ꍇ�i�����̒P�������l�̕������}�C�i�X�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A<BR>
     * �@@�@@�@@�@@�@@��̖��P���Ƃ���B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�e�������S�����ȊO�̏ꍇ<BR>
     * �i�e�����̒����P��.isFullyExecuted()==false�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�P�j�@@�e�������w�l�����̏ꍇ�i�e�����̒����P��.isLimitPrice()==true�̏ꍇ�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�e�����̒����P��.�w�l ����̖��P���Ƃ���B<BR>
     * <BR>
     * �@@�@@�Q�|�Q�|�Q�j�@@�e���������s�����̏ꍇ�i�e�����̒����P��.isLimitPrice()==false�̏ꍇ�j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�敨OP�v���_�N�g�}�l�[�W��.get����(�����̐敨OP�������)�ɂ��A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�������擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@���߂��������A��̖��P���Ƃ���B<BR>
     * <BR>
     * �R�j�@@���s�P�������߂�B<BR>
     * <BR>
     * �@@�Q�j�ŋ��߂���̖��P�� �ɁA�����̒P�������l�����Z�����l��ԋp����B<BR>
     * <BR>
     * �@@�����߂����s�P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02298 <BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �q�����̎w�l�B<BR>
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B <BR>
     * �敨OP�\�񒍕��P�ʃe�[�u���̓����ځA�������͍��L�ɏ�����l��ݒ肷��B<BR>
     * �i�P�������l�̎w�肪�Ȃ��ꍇ�́Anull���ݒ肳���B�j<BR>
     * @@param l_ifoTradedProductImpl - (�敨OP�������)<BR>
     * �e�����̖����{�s��ɊY������敨OP��������I�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getReserveIfoOrderExecPrice(
        IfoOrderUnit l_parentOrderUnit,
        double l_dblLimitPrice,
        Double l_priceAdjustValue,
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReserveIfoOrderExecPrice(IfoOrderUnit, double, Double, WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�����̒����P�� = null�B");
        }

        //�P�j�@@�����̒P�������l == null �̏ꍇ�́A�����̎w�l�����̂܂ܕԋp����B
        //�ȊO�A�ȉ��̏������s���B
        if (l_priceAdjustValue == null)
        {
             log.exiting(STR_METHOD_NAME);
             return l_dblLimitPrice;
        }

        double l_dblPriceAdjustValue = l_priceAdjustValue.doubleValue();
        double l_dblBasePrice = 0.0D;

        //�Q�j�@@��ƂȂ���P�����擾����B
        //�ȉ��A�e�����̖��̏�Ԃɂ��A���򂷂�B
        //�Q�|�P�j�@@�e�������S����肵�Ă���ꍇ�i�e�����̒����P��.isFullyExecuted()==true�̏ꍇ�j
        if (l_parentOrderUnit.isFullyExecuted())
        {
            //�Q�|�P�|�P�j�@@�e�����̖��I�u�W�F�N�g���擾����B
            //�e�����̒����P��.getExecutions()���R�[������B
            OrderExecution[] l_orderExecs = l_parentOrderUnit.getExecutions();

            int l_intCnt = 0;

            double l_dblMaxPrice = 0.0D;
            double l_dblMinPrice = 0.0D;

            if (l_orderExecs != null && l_orderExecs.length != 0)
            {
                l_intCnt = l_orderExecs.length;
                l_dblMaxPrice = l_orderExecs[0].getExecutionPrice();
                l_dblMinPrice = l_orderExecs[0].getExecutionPrice();
            }
            else
            {
                log.debug("�e�����̖��I�u�W�F�N�g�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�e�����̖��I�u�W�F�N�g�����݂��Ȃ��B");
            }

            for (int i = 0; i < l_intCnt; i++)
            {
                double l_dblExecPrice = l_orderExecs[i].getExecutionPrice();
                if (l_dblExecPrice > l_dblMaxPrice)
                {
                    l_dblMaxPrice = l_dblExecPrice;
                }
                if (l_dblExecPrice < l_dblMinPrice)
                {
                    l_dblMinPrice = l_dblExecPrice;
                }
            }

            //�Q�|�P�|�Q�j�@@��̖��P�������肷��B
            //���q�������}�C�i�X�w��̏ꍇ�i�����̒P�������l�̕���==�}�C�i�X�̏ꍇ�j
            if (l_dblPriceAdjustValue < 0)
            {
                //�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A
                //��̖��P���Ƃ���B
                l_dblBasePrice = l_dblMinPrice;

            }
            //���q�������v���X�w��̏ꍇ�i�����̒P�������l�̕������}�C�i�X�̏ꍇ�j
            else
            {
                //�擾�����e�����̖��I�u�W�F�N�g.���P�� �̒��ōł������P�����A
                //��̖��P���Ƃ���B
                l_dblBasePrice = l_dblMaxPrice;
            }
        }
        //�Q�|�Q�j�@@�e�������S�����ȊO�̏ꍇ�i�e�����̒����P��.isFullyExecuted()==false�̏ꍇ�j
        else
        {
            //�Q�|�Q�|�P�j�@@�e�������w�l�����̏ꍇ�i�e�����̒����P��.isLimitPrice()==true�̏ꍇ�j
            if (!l_parentOrderUnit.isMarketOrder())
            {
                //�e�����̒����P��.�w�l ����̖��P���Ƃ���B
                l_dblBasePrice = l_parentOrderUnit.getLimitPrice();
            }
            //�Q�|�Q�|�Q�j�e���������s�����̏ꍇ�i�e�����̒����P��.isLimitPrice()==false�̏ꍇ�j
            else
            {
                //�敨OP�v���_�N�g�}�l�[�W��.get����(�����̐敨OP�������)�ɂ��A
                //�������擾����B
                //���߂��������A��̖��P���Ƃ���B
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                l_dblBasePrice = l_productMgr.getCurrentPrice(l_ifoTradedProductImpl);
            }
        }

        //�R�j�@@���s�P�������߂�B
        //�Q�j�ŋ��߂���̖��P�� �ɁA�����̒P�������l�����Z�����l��ԋp����B
        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdPriceAdjustValue = new BigDecimal(l_dblPriceAdjustValue + "");
        double l_dblExecPrice = l_bdBasePrice.add(l_bdPriceAdjustValue).doubleValue();

        //�����߂����s�P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B
        if (l_dblExecPrice <= 0)
        {
            log.debug("���s�P����0�ȉ��B ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���s�P����0�ȉ��B ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblExecPrice;
    }

    /**
     * (is���Δ������)<BR>
     * �w�肳�ꂽ�A����������敪���A�e�����ɑ΂��锽�Δ���������ǂ����𔻒肷��B<BR>
     * <BR>
     * �P�j�@@this.get���Δ������()���R�[������B<BR>
     * �@@[get���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�e�����̒����P��.����ID�ɊY������ڋq<BR>
     * �@@�@@������ʁF�@@�p�����[�^.�e�����̒����P��.�������<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l��null�̏ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̖߂�l�Ƀp�����[�^.�A����������敪��<BR>
     * �@@�܂܂��ꍇtrue�A�ȊOfalse��ԋp����B
     * @@param l_strRsvOrderTradingDiv - (�A����������敪)<BR>
     * �A����������敪�B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43279E7B002B
     */
    public boolean isReversingTrade(
        String l_strRsvOrderTradingDiv, 
        OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isReversingTrade(String, OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        WEB3GentradeMainAccount l_mainAccount = null;
        
        try
        {
            //�P�j�@@this.get���Δ������()���R�[������B
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                    l_parentOrderUnit.getAccountId());//NotFoundException        
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ڋq�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        String[] l_strReturns = this.getReversingTrades(l_mainAccount, l_parentOrderUnit.getOrderType());
        
        //���\�b�h�̖߂�l��null�̏ꍇ�Afalse��ԋp����B
        if (l_strReturns == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
                
        //�Q�j�@@�P�j�̖߂�l�Ƀp�����[�^.�A����������敪��
        //�܂܂��ꍇtrue�A�ȊOfalse��ԋp����B
        if (WEB3Toolkit.contain(l_strReturns, l_strRsvOrderTradingDiv))
        {                            
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (validate�A���������)<BR>
     * �w��̘A������������A�w��\���ǂ������`�F�b�N����B<BR>
     * �w��s�̏ꍇ�́u�w��̘A����������́A���Y�����ɐݒ�s�v�̗�O���X���[����B<BR>
     * <BR>
     * �P�j�@@this.get�A����������ꗗ()���R�[������B<BR>
     * �@@[get�A����������ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�e�����̒����P��.����ID�ɊY������ڋq<BR>
     * �@@�@@������ʁF�@@�p�����[�^.�e�����̒����P��.�������<BR>
     * �@@�@@���i�敪�ꗗ�F<BR>
     * �@@�@@�@@�p�����[�^.�e�����̒����P�ʂ̌^���A<BR>
     * �@@�@@�@@[���������P�ʂ̏ꍇ]<BR>
     * �@@�@@�@@�@@"��������"�A"�M�p���"�̔z����Z�b�g�B<BR>
     * <BR>
     * �@@�@@�@@[�敨OP�����P�ʂ̏ꍇ]<BR>
     * �@@�@@�@@�@@"�敨"�A"�I�v�V����"�̔z����Z�b�g�B<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02245<BR>
     * <BR>
     * �Q�j�@@this.get���Δ������()���R�[������B<BR>
     * �@@[get���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�ڋq�F�@@�p�����[�^.�e�����̒����P��.����ID�ɊY������ڋq<BR>
     * �@@�@@������ʁF�@@�p�����[�^.�e�����̒����P��.�������<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l��null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02245<BR>
     * <BR>
     * �R�j�@@�P�j�A�Q�j�̖߂�l�̂ǂ���ɂ��p�����[�^.�A����������敪��<BR>
     * �@@�܂܂�Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02245<BR>
     * �@@
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     * @@roseuid 4327A3960154
     */
    public void validateSuccOrderTrade(
        String l_strRsvOrderTradingType, 
        OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSuccOrderTrade(String, OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        //�P�j�@@this.get�A����������ꗗ()���R�[������B
        WEB3GentradeMainAccount l_mainAccount = null;
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                    l_parentOrderUnit.getAccountId());//NotFoundException        
        }
        catch (NotFoundException l_ex)
        {
            log.error("�ڋq�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //���i�敪�ꗗ�F
        String[] l_strCommodityDivs = new String[2];
        
        //�p�����[�^.�e�����̒����P�ʂ̌^���A[���������P�ʂ̏ꍇ]
        //�@@"��������"�A"�M�p���"�̔z����Z�b�g�B
        if (l_parentOrderUnit instanceof EqTypeOrderUnit)
        {            
            l_strCommodityDivs[0] = WEB3CommodityDivDef.EQUITY;
            l_strCommodityDivs[1] = WEB3CommodityDivDef.MARGIN;
        }
        else if (l_parentOrderUnit instanceof IfoOrderUnit)
        //[�敨OP�����P�ʂ̏ꍇ]
        //"�敨"�A"�I�v�V����"�̔z����Z�b�g�B
        {  
            l_strCommodityDivs[0] = WEB3CommodityDivDef.FUTURE;
            l_strCommodityDivs[1] = WEB3CommodityDivDef.OPTION;
        }
        else
        {
            log.debug("�p�����[�^�̒l�������^�敨OP�����P��'�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�̒l�������^�敨OP�����P��'�ȊO�ł��B");          
        }
        
        String[] l_strReturns1 = this.getSuccOrderTradeList(
            l_mainAccount, 
            l_parentOrderUnit.getOrderType(), 
            l_strCommodityDivs);

        //���\�b�h�̖߂�l��null�̏ꍇ�A��O���X���[����B
        //�܂܂�Ȃ��ꍇ�A��O���X���[����B
        if (l_strReturns1 == null)
        {
            log.debug("���\�b�h�̖߂�l��null�̏ꍇ�A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02245,
                this.getClass().getName() + STR_METHOD_NAME,
                "�w��̘A����������́A���Y�����ɐݒ�s�ł��B");
        }
        
        //�Q�j�@@this.get���Δ������()���R�[������B
        String[] l_strReturns2 = this.getReversingTrades(l_mainAccount, l_parentOrderUnit.getOrderType());
        
        //���\�b�h�̖߂�l��null�̏ꍇ�A��O���X���[����B
        if (l_strReturns2 == null)
        {
            log.debug("���\�b�h�̖߂�l��null�̏ꍇ�A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02245,
                this.getClass().getName() + STR_METHOD_NAME,
                "�w��̘A����������́A���Y�����ɐݒ�s�ł��B");
        }
        
        //�R�j�@@�P�j�A�Q�j�̖߂�l�̂ǂ���ɂ��p�����[�^.�A����������敪��
        //�܂܂�Ȃ��ꍇ�A��O���X���[����B
        if (!(WEB3Toolkit.contain(l_strReturns1, l_strRsvOrderTradingType) || 
            WEB3Toolkit.contain(l_strReturns2, l_strRsvOrderTradingType)))
        {
            log.debug("�w��̘A����������́A���Y�����ɐݒ�s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02245,
                this.getClass().getName() + STR_METHOD_NAME,
                "�w��̘A����������́A���Y�����ɐݒ�s�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�A�������ő�ݒ萔)<BR>
     * �A�������ő�ݒ萔�𒴉߂��Ă��Ȃ����ǂ������ʂ���B<BR>
     * ���߂��Ă���ꍇ�́A<BR>
     * �u�A�������ő�ݒ萔�𒴉߁v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02247<BR>
     * <BR>
     * �P�j�@@���ݗL���ȗ\�񒍕����擾���A���̌������J�E���g����B<BR>
     * <BR>
     * �@@�p�����[�^.�e�����̒����P�ʂ̌^���A<BR>
     * �@@[���������P�ʂ̏ꍇ]<BR>
     * �@@�@@this.get�L�������q�����P�ʈꗗ()���R�[������B<BR>
     * <BR>
     * �@@�@@[get�L�������q�����P�ʈꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�e�����̒���ID�F<BR>
     * �@@�@@�@@�@@�p�����[�^.�e�����̒����P��.����ID<BR>
     * <BR>
     * �@@[�敨OP�����P�ʂ̏ꍇ]<BR>
     * �@@�@@this.get�L���敨OP�q�����P�ʈꗗ()���R�[������B<BR>
     * <BR>
     * �@@�@@[get�L���敨OP�q�����P�ʈꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�e�����̒���ID�F<BR>
     * �@@�@@�@@�@@�p�����[�^.�e�����̒����P��.����ID<BR>
     * �Q�j�@@�ő�ݒ�\�����̎擾<BR>
     * �@@this.get�A�������ő�ݒ茏��()���R�[������B<BR>
     * <BR>
     * �@@[get�A�������ő�ݒ茏��()�Ɏw�肷�����]<BR>
     * �@@�@@���XID�F�@@�p�����[�^.�e�����̒����P��.���XID<BR>
     * <BR>
     * �R�j�@@�A�������ݒ萔���߃`�F�b�N<BR>
     * �@@�P�j�ɂĎ擾�������� + 1 > �Q�j�̖߂�l�̏ꍇ�A<BR>
     * �@@�u�A�������ő�ݒ萔�𒴉߁v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02247<BR>
     * <BR>
     * �@@���ő�ݒ萔�ɂ́A�P�̐e�����ɑ΂��\�񒍕��i�q�����j<BR>
     *     �������܂Őݒ�\����<BR>
     * �@@���ݒ肳��Ă���B<BR>
     * �@@���{�P�́A����ǉ��Őݒ肵�悤�Ƃ��Ă���\�񒍕��̕��̉��Z�B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * @@throws WEB3BaseException
     * �e�����̒����P�ʃI�u�W�F�N�g�B
     * @@roseuid 4327A87C0007
     */
    public void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        int l_intCnt = 0;
        
        //�P�j�@@���ݗL���ȗ\�񒍕����擾���A���̌������J�E���g����B 
        //�p�����[�^.�e�����̒����P�ʂ̌^���A [���������P�ʂ̏ꍇ] 
        if (l_parentOrderUnit instanceof EqTypeOrderUnit)
        {
            //this.get�L�������q�����P�ʈꗗ()���R�[������B 
            WEB3ToSuccEqTypeOrderUnitImpl[] l_eqOrderUnitImpls = null;
            
            try
            {
                l_eqOrderUnitImpls = 
                    this.getOpenReserveEqtypeOrderUnits(l_parentOrderUnit.getOrderId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                //do nothing
            }

            if (l_eqOrderUnitImpls != null && l_eqOrderUnitImpls.length != 0)
            {
                l_intCnt = l_eqOrderUnitImpls.length;
            }            
        }
        else if (l_parentOrderUnit instanceof IfoOrderUnit)
        //[�敨OP�����P�ʂ̏ꍇ]
        {
             //this.get�L���敨OP�q�����P�ʈꗗ()���R�[������B
            WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = null;

            l_ifoOrderUnitImpls = this.getOpenReserveIfoOrderUnits(l_parentOrderUnit.getOrderId());

            if (l_ifoOrderUnitImpls != null && l_ifoOrderUnitImpls.length != 0)
            {
                l_intCnt = l_ifoOrderUnitImpls.length;
            }
        }
        else
        {
            log.debug("�p�����[�^�̒l�������^�敨OP�����P��'�ȊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�̒l�������^�敨OP�����P��'�ȊO�ł��B");
        }

        //�Q�j�@@�ő�ݒ�\�����̎擾 
        //this.get�A�������ő�ݒ茏��()���R�[������B 
        double l_dblSuccOrderMaxQuantity = this.getSuccOrderMaxQuantity(l_parentOrderUnit);


        //�R�j�@@�A�������ݒ萔���߃`�F�b�N 
        //�P�j�ɂĎ擾�������� + 1 > �Q�j�̖߂�l�̏ꍇ�A 
        //�A�������ő�ݒ萔�𒴉߁v�̗�O���X���[����B 
        if (l_intCnt + 1 > l_dblSuccOrderMaxQuantity)
        {
            log.debug("�A�������ő�ݒ萔�𒴉߂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02247,
                this.getClass().getName() + STR_METHOD_NAME,
                "�A�������ő�ݒ萔�𒴉߂ł��B" + l_intCnt + " + 1 > " + l_dblSuccOrderMaxQuantity);
        }        
    }
    
    /**
     * (get�A�������ő�ݒ茏��)<BR>
     * �A�������ő�ݒ茏�����擾����B<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@���X�v���t�@@�����X�e�[�u�����ȉ��̏�����<BR>
     * �@@��������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �p�����[�^.���XID And<BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.�A�������ő�ݒ萔 And <BR>
     * �@@�@@�v���t�@@�����X���̘A�� =<BR>
     * �@@�@@�@@�p�����[�^.�e�����̒����P�ʂ̌^���A���������P�ʂ̏ꍇ�A"1"<BR>
     * �@@�@@�@@�p�����[�^.�e�����̒����P�ʂ̌^���A�敨OP�����P�ʂ̏ꍇ�A"2"<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�Y���f�[�^�Ȃ��̗�O���X���[����B<BR>
     * �@@class: WEB3SystemLayerException<BR>
     * �@@tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * �Q�j�@@��������.�v���t�@@�����X�̒l��ԋp����B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4327AD680372
     */
    protected double getSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getSuccOrderMaxQuantity(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@DB����
            //���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B
            //[����]
            //���XID = �p�����[�^.���XID And
            //�v���t�@@�����X�� = �v���t�@@�����X��.�A�������ő�ݒ萔 And 
            //�v���t�@@�����X���̘A�� =
            //�p�����[�^.�e�����̒����P�ʂ̌^���A���������P�ʂ̏ꍇ�A"1"
            //�p�����[�^.�e�����̒����P�ʂ̌^���A�敨OP�����P�ʂ̏ꍇ�A"2"
            int l_intNameSerialNo = 0;
            if (l_parentOrderUnit instanceof EqTypeOrderUnit)
            {
                l_intNameSerialNo = 1;
            }
            else if (l_parentOrderUnit instanceof IfoOrderUnit)
            {
                l_intNameSerialNo = 2;
            }
            BranchPreferencesRow l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_parentOrderUnit.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_SUC_ORDER_MAX_ORDER_COUNT,
                    l_intNameSerialNo);
            
            if (l_branchReferencesRow != null)
            {
                //�Q�j�@@��������.�v���t�@@�����X�̒l��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return Double.parseDouble(l_branchReferencesRow.getValue());
            }
            //�������ʂ��擾�ł��Ȃ������ꍇ�A�Y���f�[�^�Ȃ��̗�O���X���[����B
            else
            {
                String l_strErrorMsg = "���X�v���t�@@�����X�e�[�u���ɁA�v���t�@@�����X�� "+ 
                    "= \"" + WEB3BranchPreferencesNameDef.TRIGGERORDER_SUC_ORDER_MAX_ORDER_COUNT + "\"�̐ݒ�Ȃ�";                    
                
                log.debug(l_strErrorMsg);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMsg);
            }
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
     * (validate�A�������戵�\)<BR>
     * �A���������戵�\���ǂ������ʂ���B<BR>
     * �戵�s�̏ꍇ�A�u�A�������戵�s�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02248<BR>
     * <BR>
     * �P�j�@@�戵�\���������C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@�p�����[�^.�����^�C�v<BR>
     * �@@�@@�敨�^�I�v�V�����敪�F�@@�p�����[�^.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�M�p����敪�F�@@"DEFAULT"�i�Œ�j<BR>
     * �@@�@@�s��R�[�h�F�@@"DEFAULT"�i�Œ�j<BR>
     * <BR>
     * �Q�j�@@�P�j�ɂĐ��������C���X�^���X���擾����<BR>
     * �@@�戵�\���������s.�A������ == "�戵�s��"�̏ꍇ�A<BR>
     * �@@�u�A�������戵�s�v�̗�O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02248<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * 0�F DEFAULT�i�敨OP�ȊO�j<BR>
     * 1�F �敨<BR>
     * 2�F �I�v�V����
     * @@throws WEB3BaseException
     * @@roseuid 4327E92501A5
     */
    public void validateSuccOrderHandling(
        String l_strInstitutionCode, 
        ProductTypeEnum l_productType, 
        String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSuccOrderHandling(String, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�戵�\���������C���X�^���X�𐶐�����B
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_productType,
                l_strFutureOptionDiv,
                WEB3MarginTradingDivDef.DEFAULT,
                WEB3MarketCodeDef.DEFAULT);
        
        //�Q�j�@@�P�j�ɂĐ��������C���X�^���X���擾����
        //�戵�\���������s.�A������ == "�戵�s��"�̏ꍇ�A
        //�u�A�������戵�s�v�̗�O���X���[����B
        EnableOrderConditionRow l_enableOrderConditionRow = 
            (EnableOrderConditionRow)l_handlingOrderCond.getDataSourceObject();
        
        if (WEB3DealtDef.CAN_NOT_DEALT.equals(l_enableOrderConditionRow.getChainOrder()))
        {
            log.debug("�A�������戵�s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02248,
                this.getClass().getName() + STR_METHOD_NAME,
                "�A�������戵�s�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�A�������戵���i�ꗗ)<BR>
     * �����̏،���Ђ��戵���Ă���A�������̏��i�ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���i�敪(*1)�̗v�f�����ȉ����J��Ԃ��B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@this.validate�A�������戵�\()��call����B<BR>
     * <BR>
     * �@@�@@[����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�����^�C�v�F<BR>
     * �@@�@@�@@�@@�@@�v�f��"��������"or"�M�p���"�̏ꍇ�A"����"��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�v�f��"�敨"or"�I�v�V����"�̏ꍇ�A"�敨�I�v�V����"��ݒ肷��B<BR>
     * �@@�@@�@@�敨�^�I�v�V�����敪�F<BR>
     * �@@�@@�@@�@@�@@�v�f��"��������"or"�M�p���"�̏ꍇ�A"DEFAULT"��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�v�f��"�敨"�̏ꍇ�A"�敨"��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�v�f��"�I�v�V����"�̏ꍇ�A"�I�v�V����"��ݒ肷��B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@this.validate�A�������戵�\()����O���X���[����ꍇ�A���̗v�f�ֈڍs����B(continue;)<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�Y���v�f�̏��i�敪��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�@@�@@�@@�@@�ithis.validate�A�������戵�\()����O���X���[���Ȃ��ꍇ�j<BR>
     * <BR>
     * �R�j�쐬����ArrayList��Ԃ��B<BR>
     * <BR>
     * (*1)WEB3CommodityDivDef<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     */
    public String[] getToSuccOrderDealtCommodityList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getToSuccOrderDealtCommodityList(String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�𐶐�����B
        List l_lisOrderDealtCommodityList = new ArrayList();

        //�Q�j�@@���i�敪(*1)�̗v�f�����ȉ����J��Ԃ��B
        //(*1)WEB3CommodityDivDef
        String[] l_strCommodityDivs = {
            WEB3CommodityDivDef.EQUITY,
            WEB3CommodityDivDef.MARGIN,
            WEB3CommodityDivDef.FUTURE,
            WEB3CommodityDivDef.OPTION};
        int l_intCommodityDivsSize = l_strCommodityDivs.length;
        for (int i = 0; i < l_intCommodityDivsSize; i++)
        {
            //�Q�|�P�j�@@this.validate�A�������戵�\()��call����B
            //[����]
            //�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h

            //�����^�C�v�F
            //�v�f��"��������"or"�M�p���"�̏ꍇ�A"����"��ݒ肷��B
            //�v�f��"�敨"or"�I�v�V����"�̏ꍇ�A"�敨�I�v�V����"��ݒ肷��B
            ProductTypeEnum l_productType = null;
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDivs[i])
                || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDivs[i]))
            {
                l_productType = ProductTypeEnum.EQUITY;
            }
            else
            {
                l_productType = ProductTypeEnum.IFO;
            }

            //�敨�^�I�v�V�����敪�F
            //�v�f��"��������"or"�M�p���"�̏ꍇ�A"DEFAULT"��ݒ肷��B
            //�v�f��"�敨"�̏ꍇ�A"�敨"��ݒ肷��B
            //�v�f��"�I�v�V����"�̏ꍇ�A"�I�v�V����"��ݒ肷��B
            String l_strFutureOptionDiv = null;
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDivs[i])
                || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDivs[i]))
            {
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDivs[i]))
            {
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
            }
            else
            {
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.OPTION;
            }

            try
            {
                this.validateSuccOrderHandling(l_strInstitutionCode, l_productType, l_strFutureOptionDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                //�Q�|�Q�j�@@this.validate�A�������戵�\()����O���X���[����ꍇ�A���̗v�f�ֈڍs����B(continue;)
                continue;
            }

            //�Q�|�R�j�@@�Y���v�f�̏��i�敪��ArrayList�ɒǉ�����B<BR>
            //�ithis.validate�A�������戵�\()����O���X���[���Ȃ��ꍇ�j
            l_lisOrderDealtCommodityList.add(l_strCommodityDivs[i]);
        }

        //�R�j�쐬����ArrayList��Ԃ��B
        String[] l_strOrderDealtCommodityLists = new String[l_lisOrderDealtCommodityList.size()];
        l_lisOrderDealtCommodityList.toArray(l_strOrderDealtCommodityLists);

        log.exiting(STR_METHOD_NAME);
        return l_strOrderDealtCommodityLists;
    }
    
    /**
     * (get���Δ������)<BR>
     * �����̒�����ʂ̔��Δ��������ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get�A�������戵�\�ꗗ()��call���āA<BR>
     * �@@�����̏،���Ђ���舵���Ă���A�������̏��i�ꗗ���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * <BR>
     * �@@���ڋq���������ł́A�敨�^�I�v�V�����̎戵�𔻕ʂł��Ȃ��ׁA<BR>
     * �@@���敨�^�I�v�V�����̏ꍇ�̂݁A�����\�b�h�̖߂�l���g�p����B<BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@���Δ�������敪��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�p�����[�^.������ʂ��A<BR>
     * �@@�@@["����������"�̏ꍇ]<BR>
     * �@@�@@�@@�E"���t�i�O�񒍕��j<BR>
     * �@@�@@["����������"�̏ꍇ]<BR>
     * �@@�@@�@@�E"���t�i�O�񒍕��j"<BR>
     * <BR>
     * �@@�@@�R�|�P�j�@@�M�p�q(*1)�̏ꍇ�A<BR>
     * �@@�@@�@@�ȉ��̕���ɂ������ǉ�����B<BR>
     * �@@�@@�@@["�M�p�V�K��������" or<BR>
     * �@@�@@�@@�@@"�M�p�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"�M�p�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�E"�������n�i�O�񒍕��j"<BR>
     * �@@�@@�@@["�M�p�����ԍϒ����i���ԍρj" or<BR>
     * �@@�@@�@@�@@"�M�p�����ԍϒ����i���ԍρj"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"�M�p�V�K���i�O�񒍕��j<BR>
     * �@@�@@�@@["�M�p��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�E"�M�p�V�K���i�O�񒍕��j"<BR>
     * �@@�@@�@@["�M�p���n����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"���t�i�O�񒍕��j"<BR>
     * �@@�@@�@@�@@�E"�M�p�V�K���i�O�񒍕��j"<BR>
     * <BR>
     * �@@�@@�R�|�Q�j�@@�؋��������J�ݍ�(*2)�̏ꍇ ����<BR>
     * �@@�@@�@@�P�j�̖߂�l��"�敨"���܂܂�Ă���ꍇ<BR>
     * �@@�@@�@@�ȉ��̕���ɂ������ǉ�����B<BR>
     * �@@�@@�@@["�敨�V�K��������" or<BR>
     * �@@�@@�@@�@@"�敨�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"�敨�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@["�敨�������ԍϒ����i���ԍρj" or<BR>
     * �@@�@@�@@�@@"�敨�������ԍϒ����i���ԍρj"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"�敨�V�K���i�O�񒍕��j<BR>
     * <BR>
     * �@@�@@�R�|�R�j�@@OP�����J�ݍ�(*3)�̏ꍇ ����<BR>
     * �@@�@@�@@�P�j�̖߂�l��"�I�v�V����"���܂܂�Ă���ꍇ<BR>
     * �@@�@@�@@�ȉ��̕���ɂ������ǉ�����B<BR>
     * �@@�@@�@@["OP�V�K��������" or<BR>
     * �@@�@@�@@�@@"OP�V�K��������"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"OP�ԍρi�O�񒍕��j"<BR>
     * �@@�@@�@@["OP�������ԍϒ����i���ԍρj" or<BR>
     * �@@�@@�@@�@@"OP�������ԍϒ����i���ԍρj"�̏ꍇ]<BR>
     * �@@�@@�@@�@@�E"OP�V�K���i�O�񒍕��j<BR>
     * <BR>
     * �@@(*1)�M�p�q<BR>
     * �@@�@@�p�����[�^.�ڋq.is�M�p�����J��("�w��Ȃ�") == true�̏ꍇ<BR>
     * �@@(*2)�؋��������J�ݍ�<BR>
     * �@@�@@�p�����[�^.�ڋq.is�敨OP�����J��("�敨") == true�̏ꍇ<BR>
     * �@@(*3)OP�����J�ݍ�<BR>
     * �@@�@@�p�����[�^.�ڋq.is�敨OP�����J��("�I�v�V����") == true�̏ꍇ<BR>
     * <BR>
     * �S�j�@@��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * �@@�ȊO�A��������ArrayList.toArray()�̖߂�l��ԋp����B
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@param l_orderType - (�������)<BR>
     * �������
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4327AFED0372
     */
    public String[] getReversingTrades(
        WEB3GentradeMainAccount l_mainAccount, 
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReversingTrades(WEB3GentradeMainAccount, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("�ڋq = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�ڋq = null�B");
        }
        
        //�P�j�@@this.get�A�������戵�\�ꗗ()��call���āA
        //�����̏،���Ђ���舵���Ă���A�������̏��i�ꗗ���擾����B
        //[����]
        //�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h
        String[] l_strOrderDealtCommodityList =
            this.getToSuccOrderDealtCommodityList(l_mainAccount.getInstitution().getInstitutionCode());

        //�P�j�@@ArrayList�𐶐�����B 
        List l_lisStrs = new ArrayList();

        //�Q�j�@@���Δ�������敪��ArrayList�ɒǉ�����B 
        //�p�����[�^.������ʂ��A 
        //  ["����������"�̏ꍇ] 
        //�E"���t�i�O�񒍕��j
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType)) 
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER);
        }
        // ["����������"�̏ꍇ] 
        //�E"���t�i�O�񒍕��j" 
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType)) 
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER);
        }
        
        //�Q�|�P�|�P�j�@@�M�p�q(*1)�̏ꍇ�A 
        //�@@�@@�@@�ȉ��̕���ɂ������ǉ�����B 
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            //["�M�p�V�K��������" or 
            //"�M�p�V�K��������"�̏ꍇ] 
            //�E"�M�p�ԍρi�O�񒍕��j" 
            //�E"�������n�i�O�񒍕��j" 
            if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) || 
                OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER);
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER);
            }
            //["�M�p�����ԍϒ����i���ԍρj" or 
            //"�M�p�����ԍϒ����i���ԍρj"�̏ꍇ] 
            //�E"�M�p�V�K���i�O�񒍕��j 
            else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType) || 
                OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER);
            }
            // ["�M�p��������"�̏ꍇ] 
            //�E"���t�i�O�񒍕��j" 
            //�E"�M�p�V�K���i�O�񒍕��j" 
            else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER);
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER);
            }
            //["�M�p���n����"�̏ꍇ] 
            //�E"���t�i�O�񒍕��j" 
            //�E"�M�p�V�K���i�O�񒍕��j" 
            else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER);
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER);
            }
        }

        //�Q�|�P�|�Q�j�@@�؋��������J�ݍ�(*2)�̏ꍇ�A ���� �P�j�̖߂�l��"�敨"���܂܂�Ă���ꍇ
        //�ȉ��̕���ɂ������ǉ�����B
        if (l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.FUTURE))
        {
            //["�敨�V�K��������" or 
            //"�敨�V�K��������"�̏ꍇ] 
            //�E"�敨�ԍρi�O�񒍕��j" 
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) || 
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER);
            }
            
            //["�敨�������ԍϒ����i���ԍρj" or 
            //"�敨�������ԍϒ����i���ԍρj"�̏ꍇ] 
            //�E"�敨�V�K���i�O�񒍕��j 
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType) || 
                OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER);
            }
        }

        //�Q�|�P�|�R�j�@@OP�����J�ݍ�(*3)�̏ꍇ�A ���� �P�j�̖߂�l��"�I�v�V����"���܂܂�Ă���ꍇ
        //�ȉ��̕���ɂ������ǉ�����B
        if (l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.OPTION))
        {
            //["OP�V�K��������" or 
            // "OP�V�K��������"�̏ꍇ] 
            //�E"OP�ԍρi�O�񒍕��j"
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) || 
                OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER);
            }
             
            //["OP�������ԍϒ����i���ԍρj" or 
            // "OP�������ԍϒ����i���ԍρj"�̏ꍇ] 
            //�E"OP�V�K���i�O�񒍕��j 
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) || 
                OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER);
            }
        }
        
        //�R�j�@@��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B 
        if (l_lisStrs.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�ȊO�A��������ArrayList.toArray()�̖߂�l��ԋp����B
        else
        {
            String[] l_strs = new String[l_lisStrs.size()];
            l_lisStrs.toArray(l_strs);
            
            log.exiting(STR_METHOD_NAME);
            return l_strs;
        }        
    }
    
    /**
     * (get�A����������ꗗ)<BR>
     * �ڋq���I���\�ȘA����������̈ꗗ��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@this.get�A�������戵�\�ꗗ()��call���āA<BR>
     * �@@�����̏،���Ђ���舵���Ă���A�������̏��i�ꗗ���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h<BR>
     * <BR>
     * �@@���ڋq���������ł́A�敨�^�I�v�V�����̎戵�𔻕ʂł��Ȃ��ׁA<BR>
     * �@@���敨�^�I�v�V�����̏ꍇ�̂݁A�����\�b�h�̖߂�l���g�p����B<BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�e���i�̘A����������敪��ǉ�����B<BR>
     * �@@�R�|�P�j�@@�p�����[�^.���i�敪�ꗗ��"��������"��<BR>
     * �@@�@@�܂܂��ꍇ�A���������̎���敪��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E"���t"<BR>
     * �@@�@@�E"���t�i�����c�j"<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�p�����[�^.���i�敪�ꗗ��"�M�p���"��<BR>
     * �@@�@@�܂܂��ꍇ ���� �M�p�q(*1)�̏ꍇ�A<BR>
     * �@@�@@�M�p����̎���敪��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E"�M�p�V�K��"<BR>
     * �@@�@@�E"�M�p�ԍρi�����c�j"<BR>
     * �@@�@@�E"�������n�i�����c�j"<BR>
     * <BR>
     * �@@�R�|�R�j�@@�p�����[�^.���i�敪�ꗗ��"�敨"��<BR>
     * �@@�@@�܂܂��ꍇ ���� �敨�����J�ݍ�(*2)�̏ꍇ�A����<BR>
     * �@@�@@�P�j�̖߂�l��"�敨"���܂܂�Ă���ꍇ�A<BR>
     * �@@�@@�敨�̎���敪��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E"�敨�V�K��"<BR>
     * �@@�@@�E"�敨�ԍρi�����c�j"<BR>
     * <BR>
     * �@@�R�|�S�j�@@�p�����[�^.���i�敪�ꗗ��"�I�v�V����"��<BR>
     * �@@�@@�܂܂��ꍇ ���� OP�����J�ݍ�(*3)�̏ꍇ�A����<BR>
     * �@@�@@�P�j�̖߂�l��"�I�v�V����"���܂܂�Ă���ꍇ�A<BR>
     * �@@�@@�敨�̎���敪��ArrayList�ɒǉ�����B<BR>
     * �@@�@@�E"OP�V�K��"<BR>
     * �@@�@@�E"OP�ԍρi�����c�j"<BR>
     * <BR>
     * �@@(*1)�M�p�q<BR>
     * �@@�@@�p�����[�^.�ڋq.is�M�p�����J��("�w��Ȃ�") == true�̏ꍇ<BR>
     * �@@(*2)�敨�����J�ݍ�<BR>
     * �@@�@@�p�����[�^.�ڋq.is�敨OP�����J��("�敨") == true�̏ꍇ<BR>
     * �@@(*3)OP�����J�ݍ�<BR>
     * �@@�@@�p�����[�^.�ڋq.is�敨OP�����J��("�I�v�V����") == true�̏ꍇ<BR>
     * <BR>
     * �S�j�@@��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * �@@�ȊO�A��������ArrayList.toArray()�̖߂�l��ԋp����B
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g
     * @@param l_orderType - (�������)<BR>
     * �������
     * @@param l_strProductDivList - (���i�敪�ꗗ)<BR>
     * ���i�敪�ꗗ<BR>
     * �ȉ��̒l�̔z��<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����
     * 
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4327B9CB0074
     */
    public String[] getSuccOrderTradeList(
        WEB3GentradeMainAccount l_mainAccount, 
        OrderTypeEnum l_orderType, 
        String[] l_strProductDivList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getSuccOrderTradeList(WEB3GentradeMainAccount, OrderTypeEnum, String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("�ڋq = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�ڋq = null�B");
        }
        
        //�P�j�@@this.get�A�������戵�\�ꗗ()��call���āA
        //�����̏،���Ђ���舵���Ă���A�������̏��i�ꗗ���擾����B
        //[����]
        //�،���ЃR�[�h�F�@@�p�����[�^.�ڋq.�،���ЃR�[�h
        String[] l_strOrderDealtCommodityList =
            this.getToSuccOrderDealtCommodityList(l_mainAccount.getInstitution().getInstitutionCode());

        //�P�j�@@ArrayList�𐶐�����B 
        List l_lisStrs = new ArrayList();
        
        //�Q�j�@@�e���i�̘A����������敪��ǉ�����B 
        //�Q�|�P�j�@@�p�����[�^.���i�敪�ꗗ��"��������"�� 
        //�܂܂��ꍇ�A���������̎���敪��ArrayList�ɒǉ�����B 
        //�E"���t" 
        //�E"���t�i�����c�j" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.EQUITY))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.BUY);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER);
        }        

        //�Q�|�Q�j�@@�p�����[�^.���i�敪�ꗗ��"�M�p���"�� 
        //�܂܂��ꍇ ���� �M�p�q(*1)�̏ꍇ�A 
        //�M�p����̎���敪��ArrayList�ɒǉ�����B 
        //�E"�M�p�V�K��" 
        //�E"�M�p�ԍρi�����c�j" 
        //�E"�������n�i�����c�j" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.MARGIN) &&
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER);
        }    

        //�Q�|�R�j�@@�p�����[�^.���i�敪�ꗗ��"�敨"�� 
        //�܂܂��ꍇ ���� �敨�����J�ݍ�(*2)�̏ꍇ�A����
        //�P�j�̖߂�l��"�敨"���܂܂�Ă���ꍇ�A
        //�敨�̎���敪��ArrayList�ɒǉ�����B 
        //�E"�敨�V�K��" 
        //�E"�敨�ԍρi�����c�j" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.FUTURE) &&
            l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.FUTURE))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER);
        } 

        //�Q�|�S�j�@@�p�����[�^.���i�敪�ꗗ��"�I�v�V����"�� 
        //�܂܂��ꍇ ���� OP�����J�ݍ�(*3)�̏ꍇ�A����
        //�P�j�̖߂�l��"�I�v�V����"���܂܂�Ă���ꍇ�A
        //�敨�̎���敪��ArrayList�ɒǉ�����B 
        //�E"OP�V�K��" 
        //�E"OP�ԍρi�����c�j" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.OPTION) &&
            l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.OPTION))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_OP);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER);
        }         

        //�R�j�@@��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B 
        if (l_lisStrs.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //�ȊO�A��������ArrayList.toArray()�̖߂�l��ԋp����B
        else
        {
            String[] l_strs = new String[l_lisStrs.size()];
            l_lisStrs.toArray(l_strs);
            
            log.exiting(STR_METHOD_NAME);
            return l_strs;
        }   
    }
    
    /**
     * (validate�A������)<BR>
     * �A�������̓��́^�m�F�^�����ɋ��ʂ̃`�F�b�N���s���B<BR>
     * �V�[�P���X�}�u�i�A�������jvalidate�A�������v���Q�ƁB
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B
     * @@param l_strFutureOptionDiv - (�敨�^�I�v�V�����敪)<BR>
     * �敨�^�I�v�V�����敪�B
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 4327C8BC0154
     */
    public void validateSuccOrder(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strFutureOptionDiv, 
        String l_strRsvOrderTradingType, 
        OrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateSuccOrder(WEB3GentradeSubAccount, ProductTypeEnum, String, String, OrderUnit)";
            
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        //1.1: validate�A�������戵�\(String, ProductTypeEnum, String)
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();    
        
        if (l_institution == null)       
        {
            log.error("�،���Ѓe�[�u���ɊY������f�[�^������܂���B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،���Ѓe�[�u���ɊY������f�[�^������܂���B");
        }
        
        this.validateSuccOrderHandling(
            l_institution.getInstitutionCode(), 
            l_productType, 
            l_strFutureOptionDiv);
            
        //�o���I���敪�F
        String l_strOrderExecutionEndType = "";

        //�����̖����^�C�v == "��"�̏ꍇ�A"�o���I���i�ŏI�j"
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            l_strOrderExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
        }
        //�����̖����^�C�v == "�敨OP"�̏ꍇ�A
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //�����̕⏕����.get����X().is�[����{() == true and
            //�����̐e�����̒����P��.����敪 == "���̑�"�̏ꍇ�A"�[��O�o���I��"
            boolean l_blnIsEveningSessionEnforcemented =
                l_subAccount.getWeb3GenBranch().isEveningSessionEnforcemented(l_productType);
            IfoOrderUnitRow l_ifoParentOrderUnit = (IfoOrderUnitRow)l_parentOrderUnit.getDataSourceObject();
            String l_strSessionType = l_ifoParentOrderUnit.getSessionType();

            if (l_blnIsEveningSessionEnforcemented && WEB3StringTypeUtility.isEmpty(l_strSessionType))
            {
                l_strOrderExecutionEndType = WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
            }
            //�ȊO�A"�o���I���i�ŏI�j"
            else
            {
                l_strOrderExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
            }
        }

        //1.2: validate�A��������t�\
        //(�،���� : �،����, �����^�C�v : ProductTypeEnum,
        //�敨�^�I�v�V�����敪 : String, �o���I���敪 : String)
        WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
            l_institution,
            l_productType,
            l_strFutureOptionDiv,
            l_strOrderExecutionEndType);
            
        //1.3: validate�A���������(String, OrderUnit)
        this.validateSuccOrderTrade(l_strRsvOrderTradingType, l_parentOrderUnit);
        
        //1.4: validate�g���K�[�����ݒ�To�e����(OrderUnit)
        this.validateTriggerOrderSettingToParentOrder(l_parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�g���K�[�����ݒ�To�e����)<BR>
     * �w�肳�ꂽ�e�������g���K�[������ݒ�\�ȏ�Ԃ��ǂ����𔻒肷��B<BR>
     * �i���e�������������̏ꍇ�́A�ݒ�\�Ƃ���j<BR>
     * <BR>
     * �P�j�@@�e�������N���[�Y��(*1)�̏ꍇ�A<BR>
     * �@@�@@�u�e�������N���[�Y�ς̂��߃g���K�[�����ݒ�s�v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02249<BR>
     * <BR>
     * �Q�j�@@�e�����������(*2)�̏ꍇ�A<BR>
     * �@@�@@�u�e������������̂��߃g���K�[�����ݒ�s�v�̗�O��throw����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02343<BR>
     * <BR>
     * �R�j�@@�e�����̒����P�ʂ̌^���A���������P�ʂ̏ꍇ<BR>
     * �@@�@@�R�|�P�j�@@�e�������������ϒ���(*3)�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�u�e�������������ϒ����̂��߃g���K�[�����ݒ�s�v��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@��O��throw����B<BR>
     * �@@class:WEB3BusinessLayerException<BR>
     * �@@tag:BUSINESS_ERROR_02808<BR>
     * <BR>
     * (*1)�e�������N���[�Y��<BR>
     * �@@�@@�����̐e�����̒����P��.�����L����� == �N���[�Y �̏ꍇ�A<BR>
     * �@@�@@�e�������N���[�Y�ςƔ��肷��B
     * 
     * (*2)�e�����������<BR>
     * �@@�@@�����̐e�����̒����P��.������� == �i"��t�ρi��������j" or "�������i��������j"�j�̏ꍇ�A<BR>
     * �@@�@@�e������������Ɣ��肷��B<BR>
     * <BR>
     * (*3)�e�������������ϒ���<BR>
     * �@@�g�����������}�l�[�W��.is�������ϒ���(�����̐e�����̒����P��) == true�̏ꍇ�A<BR>
     * �@@�������ϒ����Ɣ��肷��B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 432FB2C702F0
     */
    public void validateTriggerOrderSettingToParentOrder(OrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateTriggerOrderSettingToParentOrder(OrderUnit)";
        log.entering(STR_METHOD_NAME);
            
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }            

        if (OrderOpenStatusEnum.CLOSED.equals(l_parentOrderUnit.getOrderOpenStatus()))
        {
            log.debug("�e�������N���[�Y�ς̂��߃g���K�[�����ݒ�s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02249,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_parentOrderUnit.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_parentOrderUnit.getOrderStatus()))
        {
            log.debug("�e������������̂��߃g���K�[�����ݒ�s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02343,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_parentOrderUnit instanceof EqTypeOrderUnit)
        {
            //�e�������������ϒ���
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_parentOrderUnit;
            boolean l_blnIsForcedSettleOrder =
                l_orderManager.isForcedSettleOrder(l_eqTypeOrderUnit);

            if (l_blnIsForcedSettleOrder)
            {
                log.debug("�e�������������ϒ����̂��߃g���K�[�����ݒ�s��");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02808,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�e�������������ϒ����̂��߃g���K�[�����ݒ�s��");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�@@�����̖����^�C�v=="����"�̏ꍇ�A<BR>
     * �@@�@@�@@this.get�����e�����̒����P��(�����́i�e�����j����ID)��delegate����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@this.get�敨OP�e�����̒����P��(�����́i�e�����j����ID)��delegate����B
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B
     * @@param l_lngParentOrderId - (�i�e�����j����ID)<BR>
     * �e�����̒���ID�B
     * @@return OrderUnit
     * @@roseuid 4327E1490069
     */
    public OrderUnit getParentOrderUnit(ProductTypeEnum l_productType, long l_lngParentOrderId) 
    {
        final String STR_METHOD_NAME = 
            " getParentOrderUnit(ProductTypeEnum, long)";
        log.entering(STR_METHOD_NAME);
            
        //�P�j�@@�����̖����^�C�v=="����"�̏ꍇ�A
        //this.get�����e�����̒����P��(�����́i�e�����j����ID)��delegate����B
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            log.exiting(STR_METHOD_NAME);
            return this.getEqtypeParentOrderUnit(l_lngParentOrderId);
        }
        //�Q�j�@@��L�ȊO�̏ꍇ�A
        //this.get�敨OP�e�����̒����P��(�����́i�e�����j����ID)��delegate����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.getIfoParentOrderUnit(l_lngParentOrderId);
        }        
    }
    
    /**
     * (get�����e�����̒����P��)<BR>
     * EQTYPE�̐e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * EQTYPE�̊g�����������}�l�[�W��.getOrderUnits(�����́i�e�����j����ID)<BR>
     * ���R�[������B<BR>
     * �߂�l�̍ŏ��̗v�f��e�����̒����P�ʃI�u�W�F�N�g�Ƃ��ĕԋp����B
     * @@param l_lngParentOrderId - (�i�e�����j����ID)<BR>
     * �e�����̒���ID�B
     * @@return EqTypeOrderUnit
     * @@roseuid 4327E2120134
     */
    public EqTypeOrderUnit getEqtypeParentOrderUnit(long l_lngParentOrderId) 
    {
        final String STR_METHOD_NAME = 
            " getEqtypeParentOrderUnit(long)";
        log.entering(STR_METHOD_NAME);    
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        //EQTYPE�̊g�����������}�l�[�W��.getOrderUnits(�����́i�e�����j����ID)
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_lngParentOrderId);
        
        //�߂�l�̍ŏ��̗v�f��e�����̒����P�ʃI�u�W�F�N�g�Ƃ��ĕԋp����B
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            log.exiting(STR_METHOD_NAME);    
            return (EqTypeOrderUnit)l_orderUnits[0];
        }
        else
        {
            log.exiting(STR_METHOD_NAME);    
            return null;
        }
    }
    
    /**
     * (get�敨OP�e�����̒����P��)<BR>
     * IFO�̐e�����̒����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * OP�����}�l�[�W��.getOrderUnits(�����́i�e�����j����ID)���R�[������B<BR>
     * �߂�l�̍ŏ��̗v�f��e�����̒����P�ʃI�u�W�F�N�g�Ƃ��ĕԋp����B<BR>
     * @@param l_parentOrderId - (�i�e�����j����ID)<BR>
     * �e�����̒���ID�B
     * @@return IfoOrderUnit
     * @@roseuid 4327E33302CB
     */
    public IfoOrderUnit getIfoParentOrderUnit(long l_parentOrderId)
    {
        final String STR_METHOD_NAME = "getIfoParentOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //OP�����}�l�[�W��.getOrderUnits(�����́i�e�����j����ID)���R�[������B
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_parentOrderId);

        //�߂�l�̍ŏ��̗v�f��e�����̒����P�ʃI�u�W�F�N�g�Ƃ��ĕԋp����B
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return (IfoOrderUnit)l_orderUnits[0];
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (submit���������V�K�\�񒍕�)<BR>
     * �isubmitEqtypeNewOrder�j<BR>
     * <BR>
     * �����\�񒍕��P�ʃe�[�u���ɁA���������̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null<BR>
     *               �ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�������������o�^_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B<BR>
     * <BR>
     * �S�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �@@this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_orderSpc - (�������e)<BR>
     * �����������e�I�u�W�F�N�g�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B<BR>
     * �i�w��Ȃ��̏ꍇ��null���Z�b�g�j
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 4327FE2901FE
     */
    public void submitEqtypeNewOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpc, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        Double l_priceAdjustValue, 
        EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeNewOrder(WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec," +
            " long, String, String, Double, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_orderSpc == null)
        {
            log.debug("�������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�������e = null�B");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        //�P�j�@@����p�X���[�h���`�F�b�N����B
        //�����`�F�b�N.validate����p�X���[�h()���R�[������B
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���`�F�b�N�G���[");
        }
        
        //�Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
        //�o�^�̎d�l�́ADB�X�V�d�l
        //�u�i�A���j�������������o�^_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
        
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
        
        //�����P�ʂh�c  (   order_unit_id   ):  null
                                                                                
        //�����h�c    (   account_id  ):  �����̕⏕����.����ID
        l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                
        //�⏕�����h�c  (   sub_account_id  ):  �����̕⏕����.�⏕����ID 
        l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                             
        //���X�h�c    (   branch_id   ):  �����̕⏕����.���XID      
        l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                          
        //����҂h�c
        //�����̒������e.getTraderIdAsObject( )
        //�������̒������e.getTrader()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
        Trader l_trader = l_orderSpc.getTrader();
        if (l_trader != null)
        {
            l_rsvEqOrderUnitParams.setTraderId(l_orderSpc.getTraderIdAsObject());
        }        
                                                                             
        //�����h�c    (   order_id    ):  �����̒���ID
        l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                             
        //�������    (   order_type  ):  "�����̒������e.isBuyOrder()==true�̏ꍇ�́A""����������""�B
        //�ȊO�A""����������""�B"
        if (l_orderSpc.isBuyOrder())
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
        }
        else
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
        }
                                                                                   
        //�����J�e�S��  (   order_categ ):   "��������"        
        l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                                                                     
        //���������ŏI�ʔ�    (   last_order_action_serial_no ):  1     
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                          
        //�A����������敪    (   reserve_order_trading_type  ):  �����̘A����������敪    
        l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                         
        //�����^�C�v   (   product_type    ):  1�F����                                                                        
        l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        
        //�s��h�c    (   market_id   ):  �����̒������e.�s��R�[�h�ɊY������s��.�s��ID   
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        Market l_market = null;
        Institution l_institution = l_subAccount.getInstitution();
        try
        {
            l_market = l_finObjectManager.getMarket(l_institution, l_orderSpc.getMarketCode());
            //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                
        l_rsvEqOrderUnitParams.setMarketId(l_market.getMarketId());
        
        //��������    (   quantity    ):  �����̒������e.getQuantity()
        l_rsvEqOrderUnitParams.setQuantity(l_orderSpc.getQuantity());
                                                                               
        //�w�l  (   limit_price ):  �����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
        //�ȊO�A�����̒������e.getLimitPrice()        
        if (l_priceAdjustValue != null)
        {
            l_rsvEqOrderUnitParams.setLimitPrice(null);                                                 
        }
        else
        {
            l_rsvEqOrderUnitParams.setLimitPrice(l_orderSpc.getLimitPrice());
        }        
        
        //�P�������l   (   price_adjust_value  ):  "�����̒P�������l
        //��null�̏ꍇ��null���Z�b�g" 
        if (l_priceAdjustValue != null)
        {
            l_rsvEqOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);                                                       
        }
        else
        {
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
        }
        
        
        //�����������t  (   expiration_date ):  �����̒������e.getOrderExpDate()                                                                       
        l_rsvEqOrderUnitParams.setExpirationDate(l_orderSpc.getOrderExpDate());
        
        //�������    (   order_status    ):  1:��t�ρi�V�K�����j                                                                     
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        
        //�����L�����  (   order_open_status   ):  1:�I�[�v��                                                                      
        l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        
        //�����敪    (   expiration_status   ):  1:�I�[�v�� 
        l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                             
        //�ŋ敪 (   tax_type    ):  �����̒������e.getTaxType()                                                                        
        l_rsvEqOrderUnitParams.setTaxType(l_orderSpc.getTaxType());
        
        //�ŋ敪�i�������n�j   (   swap_tax_type   ):  0�F���̑�
        l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                                                                               
        //�ٍϋ敪    (   repayment_type  ):  null                                                                                
        //�ٍϊ����l   (   repayment_num   ):  null                                                                        
        
        //������ (   biz_date    ):  ������ԊǗ�.get������()                                                                     
        l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
        
        //�����h�c    (   product_id  ):  �����̒������e.getProductCode()�ɊY�����銔������.����ID 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        
        EqTypeProduct l_product = null;
        try
        {
            l_product = l_productMgr.getProduct(l_institution, l_orderSpc.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                 
        l_rsvEqOrderUnitParams.setProductId(l_product.getProductId());
        
        //���񒍕��̒����`���l�� (   order_chanel    ):  ���O�C���Z�b�V�������擾���ăZ�b�g    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                          
        l_rsvEqOrderUnitParams.setOrderChanel(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
        
        //�󒍓���    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
        l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //�����P��    (   price   ):  �����̒������e.�����P��                                                                        
        l_rsvEqOrderUnitParams.setPrice(l_orderSpc.getOrderUnitPrice());
        
        //�T�Z��n���  (   estimated_price ):  �����̒������e.�T�Z��n���                                                                      
        l_rsvEqOrderUnitParams.setEstimatedPrice(l_orderSpc.getEstimateDeliveryAmount());
        
        //���n�v���z   (   capital_gain    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGain(0);
        
        //���n�v�Ŋz   (   capital_gain_tax    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGainTax(0);
        
        //�����o�H�敪  (   order_root_div  ):  ���O�C���Z�b�V�������擾���ăZ�b�g                                                                      
        l_rsvEqOrderUnitParams.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
        
        //���Ϗ����敪  (   closing_order_type  ):  null                                                                        
        
        //���񒍕��̒����P�ʂh�c (   first_order_unit_id ):  �����̒������e.���񒍕��̒����P��ID                                                                     
        l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_orderSpc.getFirstOrderUnitId());
        
        //�����G���[�R�[�h    (   order_error_code    ):  null                                                                        
        
        //�e�����̒����h�c    (   parent_order_id ):  �����̐e�����̒����P��.����ID                                                                        
        l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
        
        //�e�����̒����P�ʂh�c  (   parent_order_unit_id    ):  �����̐e�����̒����P��.�����P��ID                                                                      
        l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
        
        //�e�������A��  (   serial_no_in_parent ):  �A�������}�l�[�W��.get�����e�������A��(�����̐e�����̒����P��.�����P��ID)                                                                       
        l_rsvEqOrderUnitParams.setSerialNoInParent(
            (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
        
        //�쐬���t    (   created_timestamp   ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
        l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�X�V���t    (   last_updated_timestamp  ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
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
        
        //�R�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //�S�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B
        //this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�V�K���V�K�\�񒍕�)<BR>
     * �isubmitEqtypeOpenContractNewOrder�j<BR>
     * <BR>
     * �����\�񒍕��P�ʃe�[�u���ɁA�M�p�V�K���̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�V�K�������o�^_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B<BR>
     * <BR>
     * �S�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �@@this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_marginOpenContractOrderSpec - (�������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B<BR>
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B<BR>
     * �i�w��Ȃ��̏ꍇ��null���Z�b�g�j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4330EB480031
     */
    public void submitEqtypeOpenContractNewOrder(
        SubAccount l_subAccount, 
        WEB3MarginOpenContractOrderSpec l_marginOpenContractOrderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        Double l_priceAdjustValue, 
        EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeOpenContractNewOrder(SubAccount, WEB3MarginOpenContractOrderSpec," +
            " long, String, String, Double, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_marginOpenContractOrderSpec == null)
        {
            log.debug("�������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�������e = null�B");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        //�P�j�@@����p�X���[�h���`�F�b�N����B
        //�����`�F�b�N.validate����p�X���[�h()���R�[������B 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
    
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
    
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���`�F�b�N�G���[");
        }
        
        //�Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B 
        //�o�^�̎d�l�́ADB�X�V�d�l
        //�u�i�A���j�M�p�V�K�������o�^_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
        
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
        
        //�����P�ʂh�c  (   order_unit_id   ):  null
        l_rsvEqOrderUnitParams.setOrderUnitId(null);          
                                                                                
        //�����h�c    (   account_id  ):  �����̕⏕����.����ID
        l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                
        //�⏕�����h�c  (   sub_account_id  ):  �����̕⏕����.�⏕����ID 
        l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                             
        //���X�h�c    (   branch_id   ):  �����̕⏕����.���XID      
        l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                          
        //����҂h�c
        //�N���C�A���g���N�G�X�g�T�[�r�X.get�㗝���͎�().�����ID
        //��get�㗝���͎�()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
        WEB3ToSuccClientRequestService l_clientRequestService = new WEB3ToSuccClientRequestService();
        Trader l_trader = l_clientRequestService.getTrader();
        if (l_trader != null)
        {
            l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
        }  
        else
        {
            l_rsvEqOrderUnitParams.setTraderId(null);    
        }
                                                                             
        //�����h�c    (   order_id    ):  �����̒���ID
        l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                             
        //�������    (   order_type  ):  �����̒������e.isLongOrder()==true�̏ꍇ�́A"�V�K��������"�B
        //�ȊO�A"�V�K��������"�B
        if (l_marginOpenContractOrderSpec.isLongOrder())
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
        }
        else
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_SHORT);
        }
                                                                                   
        //�����J�e�S��  (   order_categ ):    "�V�K������"       
        l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
                                                                     
        //���������ŏI�ʔ�    (   last_order_action_serial_no ):  1     
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                          
        //�A����������敪    (   reserve_order_trading_type  ):  �����̘A����������敪    
        l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                         
        //�����^�C�v   (   product_type    ):  1�F����                                                                        
        l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        
        //�s��h�c    (   market_id   ):  �����̕⏕����.�،���ЃR�[�h�A
        //�y�ш����̒������e.�s��R�[�h�ɊY������s��.�s��ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        Market l_market = null;
        Institution l_institution = l_subAccount.getInstitution();
        try
        {
            l_market = l_finObjectManager.getMarket(
                l_institution.getInstitutionCode(), 
                l_marginOpenContractOrderSpec.getMarketCode());//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.error("�s��e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                
        l_rsvEqOrderUnitParams.setMarketId(l_market.getMarketId());
        
        //��������    (   quantity    ):  �����̒������e.getQuantity()
        l_rsvEqOrderUnitParams.setQuantity(l_marginOpenContractOrderSpec.getQuantity());
                                                                               
        //�w�l  (   limit_price ):  �����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
        //�ȊO�A�����̒������e.getLimitPrice()  
        if (l_priceAdjustValue != null)
        {
            l_rsvEqOrderUnitParams.setLimitPrice(null);                                
        }
        else
        {
            l_rsvEqOrderUnitParams.setLimitPrice(l_marginOpenContractOrderSpec.getLimitPrice());
        }       
        
        //�P�������l   (   price_adjust_value  ):  "�����̒P�������l
        //��null�̏ꍇ��null���Z�b�g" 
        l_rsvEqOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);       
        
        //�����������t  (   expiration_date ):  �����̒������e.getOrderExpDate()                                                                       
        l_rsvEqOrderUnitParams.setExpirationDate(l_marginOpenContractOrderSpec.getOrderExpDate());
        
        //�������    (   order_status    ):  1:��t�ρi�V�K�����j                                                                     
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        
        //�����L�����  (   order_open_status   ):  1:�I�[�v��                                                                      
        l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        
        //�����敪    (   expiration_status   ):  1:�I�[�v�� 
        l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                             
        //�ŋ敪 (   tax_type    ):  �����̒������e.getTaxType()                                                                        
        l_rsvEqOrderUnitParams.setTaxType(l_marginOpenContractOrderSpec.getTaxType());
        
        //�ŋ敪�i�������n�j   (   swap_tax_type   ):  0�F���̑�
        l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                                                                               
        //�ٍϋ敪    (   repayment_type  ):  �����̒������e.get�ٍϋ敪() 
        l_rsvEqOrderUnitParams.setRepaymentType(
            l_marginOpenContractOrderSpec.getRepaymentType());
                                                                                       
        //�ٍϊ����l   (   repayment_num   ):  �����̒������e.get�ٍϊ����l()    
        l_rsvEqOrderUnitParams.setRepaymentNum(
            (int)l_marginOpenContractOrderSpec.getRepaymentNum());                                                                    
        
        //������ (   biz_date    ):  ������ԊǗ�.get������()                                                                     
        l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
        
        //�����h�c    (   product_id  ):  �����̕⏕����.�،���ЃR�[�h�A
        //�y�ш����̒������e.getProductCode()�ɊY�����銔������.����ID
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        
        EqTypeProduct l_product = null;
        try
        {
            l_product = l_productMgr.getProduct(
                l_institution, 
                l_marginOpenContractOrderSpec.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                 
        l_rsvEqOrderUnitParams.setProductId(l_product.getProductId());
        
        //���񒍕��̒����`���l�� (   order_chanel    ):  ���O�C���Z�b�V�������擾���ăZ�b�g    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                          
        l_rsvEqOrderUnitParams.setOrderChanel(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
        
        //�󒍓���    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
        l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //�����P��    (   price   ):  �����̒������e.get�v�Z�P��()                                                                        
        l_rsvEqOrderUnitParams.setPrice(l_marginOpenContractOrderSpec.getCalcUnitPrice());
        
        //�T�Z��n���  (   estimated_price ):  �����̒������e.get�����()                                                                      
        l_rsvEqOrderUnitParams.setEstimatedPrice(l_marginOpenContractOrderSpec.getContractAmount());
        
        //���n�v���z   (   capital_gain    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGain(0);
        
        //���n�v�Ŋz   (   capital_gain_tax    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGainTax(0);
        
        //�����o�H�敪  (   order_root_div  ):  ���O�C���Z�b�V�������擾���ăZ�b�g                                                                      
        l_rsvEqOrderUnitParams.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
        
        //���Ϗ����敪  (   closing_order_type  ):  null 
        l_rsvEqOrderUnitParams.setClosingOrderType(null);                                                                       
        
        //���񒍕��̒����P�ʂh�c (   first_order_unit_id ):  �����̒������e.���񒍕��̒����P��ID                                                                     
        l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_marginOpenContractOrderSpec.getFirstOrderUnitId());
        
        //�����G���[�R�[�h    (   order_error_code    ):  null     
        l_rsvEqOrderUnitParams.setOrderErrorCode(null);                                                                   
        
        //�e�����̒����h�c    (   parent_order_id ):  �����̐e�����̒����P��.����ID                                                                        
        l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
        
        //�e�����̒����P�ʂh�c  (   parent_order_unit_id    ):  �����̐e�����̒����P��.�����P��ID                                                                      
        l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
        
        //�e�������A��  (   serial_no_in_parent ):  �A�������}�l�[�W��.get�����e�������A��(�����̐e�����̒����P��.�����P��ID)                                                                       
        l_rsvEqOrderUnitParams.setSerialNoInParent(
            (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
        
        //�쐬���t    (   created_timestamp   ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
        l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�X�V���t    (   last_updated_timestamp  ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
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
        
        //�R�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //�S�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B
        //this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�ԍϐV�K�\�񒍕�)<BR>
     * �isubmitEqtypeCloseContractNewOrder�j<BR>
     * <BR>
     * �����\�񒍕��P�ʃe�[�u���ɁA�M�p�ԍς̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�ԍϒ����o�^_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@���Δ����łȂ��ꍇ(*1)�A<BR>
     * �@@�p�����[�^.�������e.getSettleContractOrderEntries()��<BR>
     * �@@�߂�l�̗v�f�����A�����\�񌚊��ԍώw����e�[�u����<BR>
     * �@@���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�ԍϒ����o�^_�����\�񌚊��ԍώw����e�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �S�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B<BR>
     * <BR>
     * �T�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �@@this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B<BR>
     * <BR>
     * (*1)���Δ����łȂ��ꍇ�E�E�E<BR>
     * �@@�A�������}�l�[�W��Impl.is���Δ������() == false�B<BR>
     * �@@[is���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�����̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�����̓�����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_marginSettleContractOrderSpec - (�������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B<BR>
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B<BR>
     * �i�w��Ȃ��̏ꍇ��null���Z�b�g�j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@param l_equityRealizedProfitAndLossPrice - (�v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g�B<BR>
     * @@param l_equityContract - (����)<BR>
     * �����I�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43325BCB01CE
     */
    public void submitEqtypeCloseContractNewOrder(
        SubAccount l_subAccount, 
        WEB3MarginSettleContractOrderSpec l_marginSettleContractOrderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        Double l_priceAdjustValue, 
        EqTypeOrderUnit l_parentOrderUnit, 
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice, 
        WEB3EquityContract l_equityContract) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeCloseContractNewOrder(SubAccount, WEB3MarginSettleContractOrderSpec," +
            " long, String, String, Double, EqTypeOrderUnit, WEB3EquityRealizedProfitAndLossPrice, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_marginSettleContractOrderSpec == null)
        {
            log.debug("�������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�������e = null�B");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        if (l_equityRealizedProfitAndLossPrice == null)
        {
            log.debug("�v�Z���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�v�Z���� = null�B");
        }
        
        if (l_equityContract == null)
        {
            log.debug("���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���� = null�B");
        }
        
        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B 
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }
            
            //�Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B 
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�M�p�ԍϒ����o�^_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
            
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
            
            //�����P�ʂh�c  (   order_unit_id   ):  null
                                                                                    
            //�����h�c    (   account_id  ):  �����̕⏕����.����ID
            l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                    
            //�⏕�����h�c  (   sub_account_id  ):  �����̕⏕����.�⏕����ID 
            l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                 
            //���X�h�c    (   branch_id   ):  �����̕⏕����.���XID      
            l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                              
            //����҂h�c
            //�N���C�A���g���N�G�X�g�T�[�r�X.get�㗝���͎�().�����ID
            //��get�㗝���͎�()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
            WEB3ToSuccClientRequestService l_clientRequestService = new WEB3ToSuccClientRequestService();
            Trader l_trader = l_clientRequestService.getTrader();
            if (l_trader != null)
            {
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }      
                                                                                         
            //�����h�c    (   order_id    ):  �����̒���ID
            l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                                 
            //�������    (   order_type  ):  �����̌���.isLong()==true�̏ꍇ�́A"�����ԍϒ����i���ԍρj"�B
            //�ȊO�A"�����ԍϒ����i���ԍρj"�B
            if (l_equityContract.isLong())
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
            }
            else
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_SHORT);
            }
                                                                                       
            //�����J�e�S��  (   order_categ ):     "�ԍϒ���"       
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
                                                                         
            //���������ŏI�ʔ�    (   last_order_action_serial_no ):  1     
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                              
            //�A����������敪    (   reserve_order_trading_type  ):  �����̘A����������敪    
            l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                             
            //�����^�C�v   (   product_type    ):  1�F����                                                                        
            l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            
            //�s��h�c    (   market_id   ):  �����̌���.�s��ID                                                                                
            l_rsvEqOrderUnitParams.setMarketId(l_equityContract.getMarketId());
            
            //��������    (   quantity    ):  �����̒������e.getTotalQuantity()
            //(* �ԍό������ʂ̍��v)
            l_rsvEqOrderUnitParams.setQuantity(l_marginSettleContractOrderSpec.getTotalQuantity());
                                                                                   
            //�w�l  (   limit_price ):  �����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
            //�ȊO�A�����̒������e.getLimitPrice() 
            if (l_priceAdjustValue != null)
            {
                l_rsvEqOrderUnitParams.setLimitPrice(null);                                                               
            }
            else
            {
                l_rsvEqOrderUnitParams.setLimitPrice(l_marginSettleContractOrderSpec.getLimitPrice());
            }            
            
            //�P�������l   (   price_adjust_value  ):  "�����̒P�������l
            //��null�̏ꍇ��null���Z�b�g" 
            if (l_priceAdjustValue != null)
            {
                l_rsvEqOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);                                                       
            }        
            
            //�����������t  (   expiration_date ):  �����̒������e.getOrderExpDate()                                                                       
            l_rsvEqOrderUnitParams.setExpirationDate(l_marginSettleContractOrderSpec.getOrderExpDate());
            
            //�������    (   order_status    ):  1:��t�ρi�V�K�����j                                                                     
            l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            //�����L�����  (   order_open_status   ):  1:�I�[�v��                                                                      
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            //�����敪    (   expiration_status   ):  1:�I�[�v�� 
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                                 
            //�ŋ敪 (   tax_type    ):  �����̒������e.getTaxType()                                                                        
            l_rsvEqOrderUnitParams.setTaxType(l_marginSettleContractOrderSpec.getTaxType());
            
            //�ŋ敪�i�������n�j   (   swap_tax_type   ):  0�F���̑�
            l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                                                                                   
            //�ٍϋ敪    (   repayment_type  ):  �����̌���.�ٍϋ敪
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_equityContract.getDataSourceObject();
            
            l_rsvEqOrderUnitParams.setRepaymentType(
                l_eqtypeContractRow.getRepaymentType());
                                                                                           
            //�ٍϊ����l   (   repayment_num   ): �����̌���.�ٍϊ����l()    
            l_rsvEqOrderUnitParams.setRepaymentNum(
                l_eqtypeContractRow.getRepaymentNum());                                                                    
            
            //������ (   biz_date    ):  ������ԊǗ�.get������()                                                                     
            l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            
            //�����h�c    (   product_id  ):  �����̌���.����ID                                                                                
            l_rsvEqOrderUnitParams.setProductId(l_eqtypeContractRow.getProductId());
            
            //���񒍕��̒����`���l�� (   order_chanel    ):  ���O�C���Z�b�V�������擾���ăZ�b�g    
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                              
            l_rsvEqOrderUnitParams.setOrderChanel(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
            
            //�󒍓���    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
            l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //�����P��    (   price   ):  �����̌v�Z����.get�v�Z�P��()                                                                 
            l_rsvEqOrderUnitParams.setPrice(l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());
            
            //�T�Z��n���  (   estimated_price ):  �����̌v�Z����.get�T�Z���ϑ��v���()                                                               
            l_rsvEqOrderUnitParams.setEstimatedPrice(
                l_equityRealizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());
            
            //���n�v���z   (   capital_gain    ):  0                                                                       
            l_rsvEqOrderUnitParams.setCapitalGain(0);
            
            //���n�v�Ŋz   (   capital_gain_tax    ):  0                                                                       
            l_rsvEqOrderUnitParams.setCapitalGainTax(0);
            
            //�����o�H�敪  (   order_root_div  ):  ���O�C���Z�b�V�������擾���ăZ�b�g                                                                      
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
            
            //���Ϗ����敪  (   closing_order_type  ):  �����̒������e.get���Ϗ����敪                                                                       
            l_rsvEqOrderUnitParams.setClosingOrderType(
                l_marginSettleContractOrderSpec.getClosingOrderType());
                       
            //���񒍕��̒����P�ʂh�c (   first_order_unit_id ):  �����̒������e.���񒍕��̒����P��ID                                                                     
            l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_marginSettleContractOrderSpec.getFirstOrderUnitId());
            
            //�����G���[�R�[�h    (   order_error_code    ):  null                                                                        
            
            //�e�����̒����h�c    (   parent_order_id ):  �����̐e�����̒����P��.����ID                                                                        
            l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
            
            //�e�����̒����P�ʂh�c  (   parent_order_unit_id    ):  �����̐e�����̒����P��.�����P��ID                                                                      
            l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
            
            //�e�������A��  (   serial_no_in_parent ):  �A�������}�l�[�W��.get�����e�������A��(�����̐e�����̒����P��.�����P��ID)                                                                       
            l_rsvEqOrderUnitParams.setSerialNoInParent(
                (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
            
            //�쐬���t    (   created_timestamp   ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
            l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�X�V���t    (   last_updated_timestamp  ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        
            //�R�j�@@���Δ����łȂ��ꍇ(*1)�A 
            //�p�����[�^.�������e.getSettleContractOrderEntries()�� 
            //�߂�l�̗v�f�����A�����\�񌚊��ԍώw����e�[�u���� 
            //  ���R�[�h��o�^����B 
            boolean l_blnIsReverseTrade = 
                this.isReversingTrade(l_strRsvOrderTradingType, l_parentOrderUnit);
        
            if (!l_blnIsReverseTrade)
            {
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries = 
                    l_marginSettleContractOrderSpec.getSettleContractOrderEntries();
                
                int l_intCnt = 0;
                if (l_eqTypeSettleContractOrderEntries != null && 
                    l_eqTypeSettleContractOrderEntries.length > 0)
                {
                    l_intCnt = l_eqTypeSettleContractOrderEntries.length;
                }
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                        new RsvEqClosingContractSpecParams();
                    
                    //�����h�c(account_id): �����̕⏕����.����ID
                    l_rsvEqClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());
                                                                                          
                    //�⏕�����h�c(sub_account_id): �����̕⏕����.�⏕����ID 
                    l_rsvEqClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                         
                    //�����h�c(order_id): �����̒���ID 
                    l_rsvEqClosingContractSpecParams.setOrderId(l_lngOrderId);
                                                                                        
                    //����ID(contract_id): getSettleContractOrderEntries()�̖߂�l[index].getContractId() 
                    l_rsvEqClosingContractSpecParams.setContractId(
                        l_eqTypeSettleContractOrderEntries[i].getContractId());
                                                                                          
                    //�ԍϘA��(closing_serial_no): index + 1          
                    l_rsvEqClosingContractSpecParams.setClosingSerialNo(i + 1);
                                                                                
                    //�ԍϒ�������(quantity): getSettleContractOrderEntries()�̖߂�l[index].getQuantity()   
                    l_rsvEqClosingContractSpecParams.setQuantity(
                        l_eqTypeSettleContractOrderEntries[i].getQuantity());
                                                                                       
                    //�쐬���t(created_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j 
                    l_rsvEqClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                                                                                           
                    //�X�V���t(last_updated_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j
                    l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doInsertQuery(l_rsvEqClosingContractSpecParams); //DataNetworkException,DataQueryException      
                }
            }        
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
        
        //�S�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //�T�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B
        //this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�M�p�������n�V�K�\�񒍕�)<BR>
     * �isubmitEqtypeSwapContractNewOrder�j<BR>
     * <BR>
     * �����\�񒍕��P�ʃe�[�u���ɁA�M�p�������n�̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�������n�����o�^_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@���Δ����łȂ��ꍇ(*1)�A<BR>
     * �@@�p�����[�^.�������e.getSettleContractOrderEntries()��<BR>
     * �@@�߂�l�̗v�f�����A�����\�񌚊��ԍώw����e�[�u����<BR>
     * �@@���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�������n�����o�^_�����\�񌚊��ԍώw����e�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �S�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B<BR>
     * <BR>
     * �T�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �@@this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B<BR>
     * <BR>
     * (*1)���Δ����łȂ��ꍇ�E�E�E<BR>
     * �@@�A�������}�l�[�W��Impl.is���Δ������() == false�B<BR>
     * �@@[is���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�����̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�����̓�����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_marginSwapContractOrderSpec - (�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@param l_dblEstimatedPrice - (�T�Z��n���)<BR>
     * �T�Z��n����B<BR>
     * @@param l_dblCapitalGain - (���n�v���z)<BR>
     * ���n�v���z�B<BR>
     * @@param l_dblCapitalGainTax - (���n�v�Ŋz)<BR>
     * ���n�v�Ŋz�B<BR>
     * @@param l_equityContract - (����)<BR>
     * �����I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4340E44D0302
     */
    public void submitEqtypeSwapContractNewOrder(
        SubAccount l_subAccount, 
        WEB3MarginSwapContractOrderSpec l_marginSwapContractOrderSpec, 
        long l_lngOrderId,         
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        EqTypeOrderUnit l_parentOrderUnit, 
        double l_dblEstimatedPrice, 
        double l_dblCapitalGain, 
        double l_dblCapitalGainTax, 
        WEB3EquityContract l_equityContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeSwapContractNewOrder(SubAccount, WEB3MarginSwapContractOrderSpec," +
            " long, String, String, EqTypeOrderUnit, double, double, double, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_marginSwapContractOrderSpec == null)
        {
            log.debug("�������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�������e = null�B");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }        
        
        if (l_equityContract == null)
        {
            log.debug("���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���� = null�B");
        }
        
        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B 
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }
            
            //�Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B 
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�M�p�������n�����o�^_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
            
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
            
            //�����P�ʂh�c  (   order_unit_id   ):  null
                                                                                    
            //�����h�c    (   account_id  ):  �����̕⏕����.����ID
            l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                    
            //�⏕�����h�c  (   sub_account_id  ):  �����̕⏕����.�⏕����ID 
            l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                 
            //���X�h�c    (   branch_id   ):  �����̕⏕����.���XID      
            l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                              
            //����҂h�c
            //�N���C�A���g���N�G�X�g�T�[�r�X.get�㗝���͎�().�����ID
            //��get�㗝���͎�()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
            WEB3ToSuccClientRequestService l_clientRequestService = new WEB3ToSuccClientRequestService();
            Trader l_trader = l_clientRequestService.getTrader();
            if (l_trader != null)
            {
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }      
                                                                                         
            //�����h�c    (   order_id    ):  �����̒���ID
            l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                                 
            //�������    (   order_type  ):  �����̌���.isLong()==true�̏ꍇ�́A"��������"�B
            //�ȊO�A"���n����"�B
            if (l_equityContract.isLong())
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            }
            else
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_SHORT);
            }
                                                                                       
            //�����J�e�S��  (   order_categ ):      "�����E���n����"       
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
                                                                         
            //���������ŏI�ʔ�    (   last_order_action_serial_no ):  1     
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                              
            //�A����������敪    (   reserve_order_trading_type  ):  �����̘A����������敪    
            l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                             
            //�����^�C�v   (   product_type    ):  1�F����                                                                        
            l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            
            //�s��h�c    (   market_id   ):  �����̌���.�s��ID                                                                                
            l_rsvEqOrderUnitParams.setMarketId(l_equityContract.getMarketId());
            
            //��������    (   quantity    ):  �����̒������e.getTotalQuantity()
            //((* ���ό������ʂ̍��v))
            l_rsvEqOrderUnitParams.setQuantity(l_marginSwapContractOrderSpec.getTotalQuantity());
                                                                                   
            //�w�l  (   limit_price ):  null                                                                                
            //�P�������l   (   price_adjust_value  ): null
            
            //�����������t  (   expiration_date ):  this.�������Ɠ����l
            //�i* ������ԊǗ�.get������()�j                                                                       
            l_rsvEqOrderUnitParams.setExpirationDate(WEB3GentradeTradingTimeManagement.getOrderBizDate());
            
            //�������    (   order_status    ):  1:��t�ρi�V�K�����j                                                                     
            l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            //�����L�����  (   order_open_status   ):  1:�I�[�v��                                                                      
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            //�����敪    (   expiration_status   ):  1:�I�[�v�� 
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                                 
            //�ŋ敪 (   tax_type    ):  �����̒������e.getTaxType()                                                                        
            l_rsvEqOrderUnitParams.setTaxType(l_marginSwapContractOrderSpec.getTaxType());
            
            //�ŋ敪�i�������n�j   (   swap_tax_type   ):  �����̒������e.get�ŋ敪�i�������n�j()
            l_rsvEqOrderUnitParams.setSwapTaxType(l_marginSwapContractOrderSpec.getSwapTaxType());
                                                                                   
            //�ٍϋ敪    (   repayment_type  ):  �����̌���.�ٍϋ敪
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_equityContract.getDataSourceObject();
            
            l_rsvEqOrderUnitParams.setRepaymentType(
                l_eqtypeContractRow.getRepaymentType());
                                                                                           
            //�ٍϊ����l   (   repayment_num   ): �����̌���.�ٍϊ����l()    
            l_rsvEqOrderUnitParams.setRepaymentNum(
                l_eqtypeContractRow.getRepaymentNum());                                                                    
            
            //������ (   biz_date    ):  ������ԊǗ�.get������()                                                                     
            l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            
            //�����h�c    (   product_id  ):  �����̌���.����ID                                                                                
            l_rsvEqOrderUnitParams.setProductId(l_eqtypeContractRow.getProductId());
            
            //���񒍕��̒����`���l�� (   order_chanel    ):  ���O�C���Z�b�V�������擾���ăZ�b�g    
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                              
            l_rsvEqOrderUnitParams.setOrderChanel(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
            
            //�󒍓���    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
            l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //�����P��    (   price   ):  null                                                                
            
            //�T�Z��n���  (   estimated_price ):  �����̊T�Z��n���                                                              
            l_rsvEqOrderUnitParams.setEstimatedPrice(l_dblEstimatedPrice);
            
            //���n�v���z   (   capital_gain    ):  �����̏��n�v���z                                                                       
            l_rsvEqOrderUnitParams.setCapitalGain(l_dblCapitalGain);
            
            //���n�v�Ŋz   (   capital_gain_tax    ):  �����̏��n�v�Ŋz                                                                       
            l_rsvEqOrderUnitParams.setCapitalGainTax(l_dblCapitalGainTax);
            
            //�����o�H�敪  (   order_root_div  ):  ���O�C���Z�b�V�������擾���ăZ�b�g                                                                      
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
            
            //���Ϗ����敪  (   closing_order_type  ):  �����̒������e.get���Ϗ����敪                                                                       
            l_rsvEqOrderUnitParams.setClosingOrderType(
                l_marginSwapContractOrderSpec.getClosingOrderType());
                       
            //���񒍕��̒����P�ʂh�c (   first_order_unit_id ):  null                          
            //�����G���[�R�[�h    (   order_error_code    ):  null                                                                        
            
            //�e�����̒����h�c    (   parent_order_id ):  �����̐e�����̒����P��.����ID                                                                        
            l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
            
            //�e�����̒����P�ʂh�c  (   parent_order_unit_id    ):  �����̐e�����̒����P��.�����P��ID                                                                      
            l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
            
            //�e�������A��  (   serial_no_in_parent ):  �A�������}�l�[�W��.get�����e�������A��(�����̐e�����̒����P��.�����P��ID)                                                                       
            l_rsvEqOrderUnitParams.setSerialNoInParent(
                (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
            
            //�쐬���t    (   created_timestamp   ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
            l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //�X�V���t    (   last_updated_timestamp  ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        
            //�R�j�@@���Δ����łȂ��ꍇ(*1)�A 
            //�p�����[�^.�������e.getSettleContractOrderEntries()�� 
            //�߂�l�̗v�f�����A�����\�񌚊��ԍώw����e�[�u���� 
            //  ���R�[�h��o�^����B 
            boolean l_blnIsReverseTrade = 
                this.isReversingTrade(l_strRsvOrderTradingType, l_parentOrderUnit);
        
            if (!l_blnIsReverseTrade)
            {
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries = 
                    l_marginSwapContractOrderSpec.getSettleContractOrderEntries();
                
                int l_intCnt = 0;
                if (l_eqTypeSettleContractOrderEntries != null && 
                    l_eqTypeSettleContractOrderEntries.length > 0)
                {
                    l_intCnt = l_eqTypeSettleContractOrderEntries.length;
                }
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                        new RsvEqClosingContractSpecParams();
                    
                    //�����h�c(account_id): �����̕⏕����.����ID
                    l_rsvEqClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());
                                                                                          
                    //�⏕�����h�c(sub_account_id): �����̕⏕����.�⏕����ID 
                    l_rsvEqClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                         
                    //�����h�c(order_id): �����̒���ID 
                    l_rsvEqClosingContractSpecParams.setOrderId(l_lngOrderId);
                                                                                        
                    //����ID(contract_id): getSettleContractOrderEntries()�̖߂�l[index].getContractId() 
                    l_rsvEqClosingContractSpecParams.setContractId(
                        l_eqTypeSettleContractOrderEntries[i].getContractId());
                                                                                          
                    //�ԍϘA��(closing_serial_no): index + 1          
                    l_rsvEqClosingContractSpecParams.setClosingSerialNo(i + 1);
                                                                                
                    //�ԍϒ�������(quantity): getSettleContractOrderEntries()�̖߂�l[index].getQuantity()   
                    l_rsvEqClosingContractSpecParams.setQuantity(
                        l_eqTypeSettleContractOrderEntries[i].getQuantity());
                                                                                       
                    //�쐬���t(created_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j 
                    l_rsvEqClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                                                                                           
                    //�X�V���t(last_updated_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j
                    l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doInsertQuery(l_rsvEqClosingContractSpecParams); //DataNetworkException,DataQueryException      
                }
            }        
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
        
        //�S�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒���ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //�T�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B
        //this.set�\�񒍕��ݒ�To�����e����(�����̐e�����̒����P��)���R�[������B
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�敨OP�V�K���V�K�\�񒍕�)<BR>
     * �isubmitIfoOpenContractNewOrder�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P�ʃe�[�u���ɁA�敨OP�V�K���̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�A����������敪��<BR>
     * �@@�@@�@@�@@�@@�@@"�敨�V�K���i�O�񒍕��j" ���� "�敨�V�K��" �̏ꍇ<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���j�敨�V�K�������o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�p�����[�^.�A����������敪��<BR>
     * �@@�@@�@@�@@�@@�@@"OP�V�K���i�O�񒍕��j" ���� "OP�V�K��" �̏ꍇ<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���jOP�V�K�������o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒���ID)���R�[������B<BR>
     * <BR>
     * �S�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �@@this.set�\�񒍕��ݒ�To�敨OP�e����(�����̐e�����̒����P��)���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoOpenContractOrderSpec - (�������e)<BR>
     * �V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B<BR>
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B <BR>
     * �i�w��Ȃ��̏ꍇ��null���Z�b�g�j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@param l_ifoEstimateDeliveryAmountCalcResult - (�v�Z����)<BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public void submitIfoOpenContractNewOrder(
        SubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        String l_strRsvOrderTradingType,
        Double l_priceAdjustValue,
        IfoOrderUnit l_parentOrderUnit,
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoOpenContractNewOrder(SubAccount, WEB3IfoOpenContractOrderSpec," +
            " long, String, String, Double, IfoOrderUnit, WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }

        if (l_ifoOpenContractOrderSpec == null)
        {
            log.debug("�������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�������e = null�B");
        }

        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }

        if (l_ifoEstimateDeliveryAmountCalcResult == null)
        {
            log.debug("�v�Z���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�v�Z���� = null�B");
        }

        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }

            //�Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
            //�Q�|�P�j�@@�p�����[�^.�A����������敪�� "�敨�V�K���i�O�񒍕��j" ���� "�敨�V�K��" �̏ꍇ
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�敨�V�K�������o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();

            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)(l_finApp.getTradingModule(ProductTypeEnum.IFO)).getProductManager();
            Market l_market = null;
            IfoProduct l_ifoProduct = null;
            try
            {
                l_market = l_finObjectManager.getMarket(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_ifoOpenContractOrderSpec.getMarketCode());
                l_ifoProduct = l_productManager.getIfoProduct(
                    l_subAccount.getInstitution(), l_ifoOpenContractOrderSpec.getProductCode());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

            if (WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(l_strRsvOrderTradingType))
            {
                //�����h�c(account_id):�����̕⏕����.����ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //�⏕�����h�c(sub_account_id):�����̕⏕����.�⏕����ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //���X�h�c(branch_id):�����̕⏕����.���XID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //����҂h�c(trader_id):�����̒������e.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoOpenContractOrderSpec.getTraderIdAsObject());

                //�����h�c(order_id):�����̒���ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //�������(order_type):�����̒������e.isBuyToOpenOrder() == true�̏ꍇ�A"�敨�V�K��������"
                //�ȊO�A"�敨�V�K��������"
                if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
                }

                //�����J�e�S��(order_categ):"�敨�V�K������"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);

                //���������ŏI�ʔ�(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //�A����������敪(reserve_order_trading_type):�����̘A����������敪
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //�����^�C�v(product_type):6�F�敨�I�v�V����
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //�敨�^�I�v�V�����敪(future_option_div):1�F�敨
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);

                //�s��h�c(market_id):�����̒������e.�s��R�[�h�ɊY������s��.�s��ID
                l_rsvIfoOrderUnitParams.setMarketId(l_market.getMarketId());

                //��������(quantity):�����̒������e.getQuantity()
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoOpenContractOrderSpec.getQuantity());

                //�w�l(limit_price):�����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
                //�ȊO�A�����̒������e.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoOpenContractOrderSpec.getLimitPrice());
                }

                //�P�������l(price_adjust_value):�����̒P�������l
                //��null�̏ꍇ��null���Z�b�g
                l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);

                //�����������t(expiration_date):�����̒������e.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoOpenContractOrderSpec.getOrderExpDate());

                //�������(order_status):1:��t�ρi�V�K�����j
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //�����L�����(order_open_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //�����敪(expiration_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //�ŋ敪(tax_type):0�F���̑�
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //������(biz_date):������ԊǗ�.get������()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //�����h�c(product_id):�����̒������e.get�����R�[�h( )�ɑΉ��������ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoProduct.getProductId());

                //���񒍕��̒����`���l��(order_chanel):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderChanel(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //�󒍓���(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //���҃R�[�h�iSONAR�j(sonar_trader_code):�ڋq.���҃R�[�h�iSONAR�j
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //�����P��(price):�v�Z����.get�v�Z�P��()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //�T�Z��n���(estimated_price):�v�Z����.get�T�Z��n���()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //�����o�H�敪(order_root_div):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //�����G���[���R�R�[�h(error_reason_code):0000�F����
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //���񒍕��̒����P�ʂh�c(first_order_unit_id):�����̒������e.get���񒍕��̒����P��ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoOpenContractOrderSpec.getFirstOrderUnitId());

                //�e�����̒����h�c(parent_order_id):�����̐e�����̒����P��.����ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //�e�����̒����P�ʂh�c(parent_order_unit_id):�����̐e�����̒����P��.�����P��ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //�e�������A��(serial_no_in_parent):�A�������}�l�[�W��.get�敨OP�e�������A��(�����̐e�����̒����P��.�����P��ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //�[��O�J�z�Ώۃt���O(evening_session_carryover_flag):�����̒������e.get�[��O�J�z�Ώۃt���O()
                if (l_ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
                }

                //����敪(session_type):������ԊǗ�.get����敪()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //���������敪(expiration_date_type):�����̒������e.get���������敪()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoOpenContractOrderSpec.getExpirationDateType());

                //�쐬���t(created_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            //�Q�|�Q�j�@@�p�����[�^.�A����������敪��"OP�V�K���i�O�񒍕��j" ���� "OP�V�K��" �̏ꍇ
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���jOP�V�K�������o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            else if (WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(l_strRsvOrderTradingType))
            {
                //�����h�c(account_id):�����̕⏕����.����ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //�⏕�����h�c(sub_account_id):�����̕⏕����.�⏕����ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //���X�h�c(branch_id):�����̕⏕����.���XID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //����҂h�c(trader_id):�����̒������e.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoOpenContractOrderSpec.getTraderIdAsObject());

                //�����h�c(order_id):�����̒���ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //�������(order_type):�����̒������e.isBuyToOpenOrder() == true�̏ꍇ�A
                //"OP�V�K��������" �ȊO�A"OP�V�K��������"
                if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
                }

                //�����J�e�S��(order_categ): "OP�V�K������"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);

                //���������ŏI�ʔ�(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //�A����������敪(reserve_order_trading_type):�����̘A����������敪
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //�����^�C�v(product_type):6�F�敨�I�v�V����
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //�敨�^�I�v�V�����敪(future_option_div):2�F�I�v�V����
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);

                //�s��h�c(market_id):�����̒������e.�s��R�[�h�ɊY������s��.�s��ID
                l_rsvIfoOrderUnitParams.setMarketId(l_market.getMarketId());

                //��������(quantity):�����̒������e.getQuantity()
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoOpenContractOrderSpec.getQuantity());

                //�w�l(limit_price):�����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
                //�ȊO�A�����̒������e.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoOpenContractOrderSpec.getLimitPrice());
                }

                //�P�������l(price_adjust_value):�����̒P�������l
                //��null�̏ꍇ��null���Z�b�g
                l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);

                //�����������t(expiration_date):�����̒������e.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoOpenContractOrderSpec.getOrderExpDate());

                //�������(order_status):1:��t�ρi�V�K�����j
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //�����L�����(order_open_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //�����敪(expiration_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //�ŋ敪(tax_type):0�F���̑�
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //������(biz_date):������ԊǗ�.get������()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //�����h�c(product_id):�����̒������e.get�����R�[�h( )�ɑΉ��������ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoProduct.getProductId());

                //���񒍕��̒����`���l��(order_chanel):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderChanel(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //�󒍓���(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //���҃R�[�h�iSONAR�j(sonar_trader_code):�ڋq.���҃R�[�h�iSONAR�j
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //�����P��(price):�v�Z����.get�v�Z�P��()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //�T�Z��n���(estimated_price):�v�Z����.get�T�Z��n���()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //�����o�H�敪(order_root_div):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //�����G���[���R�R�[�h(error_reason_code):0000�F����
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //���񒍕��̒����P�ʂh�c(first_order_unit_id):�����̒������e.get���񒍕��̒����P��ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoOpenContractOrderSpec.getFirstOrderUnitId());

                //�e�����̒����h�c(parent_order_id):�����̐e�����̒����P��.����ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //�e�����̒����P�ʂh�c(parent_order_unit_id):�����̐e�����̒����P��.�����P��ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //�e�������A��(serial_no_in_parent):�A�������}�l�[�W��.get�敨OP�e
                //�������A��(�����̐e�����̒����P��.�����P��ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //�[��O�J�z�Ώۃt���O(evening_session_carryover_flag):�����̒������e.get�[��O�J�z�Ώۃt���O()
                if (l_ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
                }

                //����敪(session_type):������ԊǗ�.get����敪()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //���������敪(expiration_date_type):�����̒������e.get���������敪()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoOpenContractOrderSpec.getExpirationDateType());

                //�쐬���t(created_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);
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

        //�R�j�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒���ID)���R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        //�S�j�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B
        //this.set�\�񒍕��ݒ�To�敨OP�e����(�����̐e�����̒����P��)���R�[������B
        this.setReserveOrderSettingToIfoParentOrder(l_parentOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�敨OP�ԍϐV�K�\�񒍕�)<BR>
     * �isubmitIfoCloseContractNewOrder�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P�ʃe�[�u���ɁA�敨OP�ԍς̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�p�����[�^.�A����������敪��<BR>
     * �@@�@@�@@�@@�@@�@@"�敨�ԍρi�O�񒍕��j" ���� "�敨�ԍρi�����c�j" �̏ꍇ<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���j�敨�ԍϒ����o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�p�����[�^.�A����������敪��<BR>
�@@�@@�@@�@@�@@�@@"OP�ԍρi�O�񒍕��j" ���� "OP�ԍρi�����c�j" �̏ꍇ<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���jOP�ԍϒ����o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �R�j�@@���Δ����łȂ��ꍇ(*1)�A<BR>
     * �@@�p�����[�^.�������e.getSettleContractEntries()��<BR>
     * �@@�߂�l�̗v�f�����A�敨OP�\�񌚋ʕԍώw����e�[�u����<BR>
     * �@@���R�[�h��o�^����B<BR>
     * <BR>
     * �@@�R�|�P�j�@@�p�����[�^.�A����������敪��"�敨�ԍρi�����c�j"�̏ꍇ
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���j�敨�ԍϒ����o�^_�敨OP�\�񌚋ʕԍώw����e�[�u���d�l.xls�v���Q�ƁB<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�p�����[�^.�A����������敪��"OP�ԍρi�����c�j"�̏ꍇ
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���jOP�ԍϒ����o�^_�敨OP�\�񌚋ʕԍώw����e�[�u���d�l.xls�v���Q�ƁB<BR>
     * <BR>
     * �S�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒���ID)���R�[������B<BR>
     * <BR>
     * �T�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �@@this.set�\�񒍕��ݒ�To�敨OP�e����(�����̐e�����̒����P��)���R�[������B<BR>
     * <BR>
     * (*1)���Δ����łȂ��ꍇ�E�E�E<BR>
     * �@@�A�������}�l�[�W��Impl.is���Δ������() == false�B<BR>
     * �@@[is���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�A����������敪�F�@@�����̓�����<BR>
     * �@@�@@�e�����̒����P�ʁF�@@�����̓�����<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoSettleContractOrderSpec - (�������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * �o�^�p�̒���ID�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_strRsvOrderTradingType - (�A����������敪)<BR>
     * �A����������敪�B<BR>
     * @@param l_priceAdjustValue - (�P�������l)<BR>
     * �P�������l�B<BR>
     * �i�w��Ȃ��̏ꍇ��null���Z�b�g�j<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB<BR>
     * @@param l_ifoEstimateDeliveryAmountCalcResult - (�v�Z����)<BR>
     * �T�Z��n����v�Z���ʃI�u�W�F�N�g�B<BR>
     * @@param l_ifoContractImpl - (����)<BR>
     * ���ʃI�u�W�F�N�g<BR>
     * @@param l_strClosingOrder - (���Ϗ���)<BR>
     * ���Ϗ���
     * @@throws WEB3BaseException
     */
    public void submitIfoCloseContractNewOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        String l_strRsvOrderTradingType,
        Double l_priceAdjustValue,
        IfoOrderUnit l_parentOrderUnit,
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult,
        WEB3IfoContractImpl l_ifoContractImpl,
        String l_strClosingOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoCloseContractNewOrder(SubAccount, WEB3IfoSettleContractOrderSpec," +
            " long, String, String, Double, IfoOrderUnit, WEB3IfoEstimateDeliveryAmountCalcResult," +
            "WEB3IfoContractImpl, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�⏕���� = null�B");
        }

        if (l_ifoSettleContractOrderSpec == null)
        {
            log.debug("�������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������e = null�B");
        }

        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�����̒����P�� = null�B");
        }

        if (l_ifoEstimateDeliveryAmountCalcResult == null)
        {
            log.debug("�v�Z���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v�Z���� = null�B");
        }

        if (l_ifoContractImpl == null)
        {
            log.debug("���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���� = null�B");
        }

        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }

            //�Q�j�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
            //�Q�|�P�j�@@�p�����[�^.�A����������敪��"�敨�ԍρi�O�񒍕��j" ���� "�敨�ԍρi�����c�j" �̏ꍇ
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�敨�ԍϒ����o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();

            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();

            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(l_strRsvOrderTradingType))
            {
                //�����h�c(account_id):�����̕⏕����.����ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //�⏕�����h�c(sub_account_id):�����̕⏕����.�⏕����ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //���X�h�c(branch_id):�����̕⏕����.���XID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //����҂h�c(trader_id):�����̒������e.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoSettleContractOrderSpec.getTraderIdAsObject());

                //�����h�c(order_id):�����̒���ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //�������(order_type):�����̌���.isLong()==true�̏ꍇ�́A"�敨�������ԍϒ����i���ԍρj"�B
                //�ȊO�A"�敨�������ԍϒ����i���ԍρj"�B
                if (l_ifoContractImpl.isLong())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
                }

                //�����J�e�S��(order_categ): "�敨�ԍϒ���"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);

                //���������ŏI�ʔ�(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //�A����������敪(reserve_order_trading_type):�����̘A����������敪
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //�����^�C�v(product_type):6�F�敨�I�v�V����
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //�敨�^�I�v�V�����敪(future_option_div):1�F�敨
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);

                //�s��h�c(market_id):�����̌���.�s��ID
                l_rsvIfoOrderUnitParams.setMarketId(l_ifoContractImpl.getMarketId());

                //��������(quantity):�����̒������e.getTotalQuantity()
                //(* �ԍό��ʐ��ʂ̍��v)
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoSettleContractOrderSpec.getTotalQuantity());

                //�w�l(limit_price):�����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
                //�ȊO�A�����̒������e.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoSettleContractOrderSpec.getLimitPrice());
                }

                //�P�������l(price_adjust_value):�����̒P�������l
                //��null�̏ꍇ��null���Z�b�g
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);
                }

                //�����������t(expiration_date):�����̒������e.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoSettleContractOrderSpec.getOrderExpDate());

                //�������(order_status):1:��t�ρi�V�K�����j
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //�����L�����(order_open_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //�����敪(expiration_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //�ŋ敪(tax_type):0�F���̑�
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //������(biz_date):������ԊǗ�.get������()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //�����h�c(product_id):�����̌���.����ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoContractImpl.getProduct().getProductId());

                //���񒍕��̒����`���l��(order_chanel):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderChanel(
                        l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //�󒍓���(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //���҃R�[�h�iSONAR�j(sonar_trader_code):�ڋq.���҃R�[�h�iSONAR�j
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //�����P��(price):�v�Z����.get�v�Z�P��()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //�T�Z��n���(estimated_price):�v�Z����.get�T�Z��n���()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //�����o�H�敪(order_root_div):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //���Ϗ���(closing_order):�����̌��Ϗ���
                l_rsvIfoOrderUnitParams.setClosingOrder(l_strClosingOrder);

                //�����G���[���R�R�[�h(error_reason_code):0000�F����
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //���񒍕��̒����P�ʂh�c(first_order_unit_id):�����̒������e.get���񒍕��̒����P��ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoSettleContractOrderSpec.getFirstOrderUnitId());

                //�e�����̒����h�c(parent_order_id):�����̐e�����̒����P��.����ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //�e�����̒����P�ʂh�c(parent_order_unit_id):�����̐e�����̒����P��.�����P��ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //�e�������A��(serial_no_in_parent):�A�������}�l�[�W��.get�敨OP�e�������A��(�����̐e�����̒����P��.�����P��ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //�[��O�J�z�Ώۃt���O(evening_session_carryover_flag):�����̒������e.get�[��O�J�z�Ώۃt���O()
                if (l_ifoSettleContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.FALSE);
                }

                //����敪(session_type):������ԊǗ�.get����敪()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //���������敪(expiration_date_type):�����̒������e.get���������敪()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoSettleContractOrderSpec.getExpirationDateType());

                //�쐬���t(created_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            //�Q�|�Q�j�@@�p�����[�^.�A����������敪��"OP�ԍρi�O�񒍕��j" ���� "OP�ԍρi�����c�j" �̏ꍇ
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���jOP�ԍϒ����o�^_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            else if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(l_strRsvOrderTradingType))
            {
                //�����h�c(account_id):�����̕⏕����.����ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //�⏕�����h�c(sub_account_id):�����̕⏕����.�⏕����ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //���X�h�c(branch_id):�����̕⏕����.���XID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //����҂h�c(trader_id):�����̒������e.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoSettleContractOrderSpec.getTraderIdAsObject());

                //�����h�c(order_id):�����̒���ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //�����̌���.isLong()==true�̏ꍇ�́A"OP�������ԍϒ����i���ԍρj"�B
                //�ȊO�A"OP�������ԍϒ����i���ԍρj"�B
                if (l_ifoContractImpl.isLong())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
                }

                //�����J�e�S��(order_categ):"OP�ԍϒ���"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);

                //���������ŏI�ʔ�(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //�A����������敪(reserve_order_trading_type):�����̘A����������敪
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //�����^�C�v(product_type):6�F�敨�I�v�V����
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //�敨�^�I�v�V�����敪(future_option_div):2�F�I�v�V����
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);

                //�s��h�c(market_id):�����̌���.�s��ID
                l_rsvIfoOrderUnitParams.setMarketId(l_ifoContractImpl.getMarketId());

                //��������(quantity):�����̒������e.getTotalQuantity()
                //(* �ԍό��ʐ��ʂ̍��v)
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoSettleContractOrderSpec.getTotalQuantity());

                //�w�l(limit_price):�����̒P�������l != null�̏ꍇ�Anull���Z�b�g�B
                //�ȊO�A�����̒������e.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoSettleContractOrderSpec.getLimitPrice());
                }

                //�P�������l(price_adjust_value):�����̒P�������l
                //��null�̏ꍇ��null���Z�b�g
                l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);

                //�����������t(expiration_date):�����̒������e.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoSettleContractOrderSpec.getOrderExpDate());

                //�������(order_status):1:��t�ρi�V�K�����j
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //�����L�����(order_open_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //�����敪(expiration_status):1:�I�[�v��
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //�ŋ敪(tax_type):0�F���̑�
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //������(biz_date):������ԊǗ�.get������()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //�����h�c(product_id):�����̌���.����ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoContractImpl.getProduct().getProductId());

                //���񒍕��̒����`���l��(order_chanel):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderChanel(
                        l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //�󒍓���(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //���҃R�[�h�iSONAR�j(sonar_trader_code):�ڋq.���҃R�[�h�iSONAR�j
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //�����P��(price):�v�Z����.get�v�Z�P��()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //�T�Z��n���(estimated_price):�v�Z����.get�T�Z��n���()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //�����o�H�敪(order_root_div):���O�C���Z�b�V�������擾���ăZ�b�g
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //���Ϗ���(closing_order):�����̌��Ϗ���
                l_rsvIfoOrderUnitParams.setClosingOrder(l_strClosingOrder);

                //�����G���[���R�R�[�h(error_reason_code):0000�F����
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //���񒍕��̒����P�ʂh�c(first_order_unit_id):�����̒������e.get���񒍕��̒����P��ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoSettleContractOrderSpec.getFirstOrderUnitId());

                //�e�����̒����h�c(parent_order_id):�����̐e�����̒����P��.����ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //�e�����̒����P�ʂh�c(parent_order_unit_id):�����̐e�����̒����P��.�����P��ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //�e�������A��(serial_no_in_parent):�A�������}�l�[�W��.get�敨OP�e�������A��(�����̐e�����̒����P��.�����P��ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //�[��O�J�z�Ώۃt���O(evening_session_carryover_flag):�����̒������e.get�[��O�J�z�Ώۃt���O()
                if (l_ifoSettleContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.FALSE);
                }

                //����敪(session_type):������ԊǗ�.get����敪()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //���������敪(expiration_date_type):�����̒������e.get���������敪()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoSettleContractOrderSpec.getExpirationDateType());

                //�쐬���t(created_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);

            //�R�j�@@���Δ����łȂ��ꍇ(*1)�A
            //�p�����[�^.�������e.getSettleContractEntries()��
            //�߂�l�̗v�f�����A�敨OP�\�񌚋ʕԍώw����e�[�u����
            //���R�[�h��o�^����B
            boolean l_blnIsReverseTrade =
                this.isReversingTrade(l_strRsvOrderTradingType, l_parentOrderUnit);

            if (!l_blnIsReverseTrade)
            {
                SettleContractEntry[] l_settleContractEntry =
                    l_ifoSettleContractOrderSpec.getSettleContractEntries();

                int l_intCnt = 0;
                if (l_settleContractEntry != null && l_settleContractEntry.length > 0)
                {
                    l_intCnt = l_settleContractEntry.length;
                }

                //�R�|�P�j�@@�p�����[�^.�A����������敪��"�敨�ԍρi�����c�j"�̏ꍇ
                //�o�^�̎d�l�́ADB�X�V�d�l
                //�u�i�A���j�敨�ԍϒ����o�^_�敨OP�\�񌚋ʕԍώw����e�[�u���d�l.xls�v���Q�ƁB
                if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(l_strRsvOrderTradingType))
                {
                    for (int i = 0; i < l_intCnt; i++)
                    {
                        RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                            new RsvIfoClosingContractSpecParams();

                        //�����h�c(account_id): �����̕⏕����.����ID
                        l_rsvIfoClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());

                        //�⏕�����h�c(sub_account_id): �����̕⏕����.�⏕����ID 
                        l_rsvIfoClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());

                        //�����h�c(order_id): �����̒���ID 
                        l_rsvIfoClosingContractSpecParams.setOrderId(l_lngOrderId);

                        //���ʂh�c(contract_id): getSettleContractOrderEntries()�̖߂�l[index].getContractId()
                        l_rsvIfoClosingContractSpecParams.setContractId(
                            l_settleContractEntry[i].getContractId());

                        //�ԍϘA��(closing_serial_no): index + 1
                        l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                        //�ԍϒ�������(quantity): getSettleContractOrderEntries()�̖߂�l[index].getQuantity()
                        l_rsvIfoClosingContractSpecParams.setQuantity(
                            l_settleContractEntry[i].getQuantity());

                        //�쐬���t(created_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j
                        l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                        //�X�V���t(last_updated_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j
                        l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        l_queryProcessor.doInsertQuery(l_rsvIfoClosingContractSpecParams);
                    }
                }
                //�R�|�Q�j�@@�p�����[�^.�A����������敪��"OP�ԍρi�����c�j"�̏ꍇ
                //�o�^�̎d�l�́ADB�X�V�d�l
                //�u�i�A���jOP�ԍϒ����o�^_�敨OP�\�񌚋ʕԍώw����e�[�u���d�l.xls�v���Q�ƁB
                else if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                    l_strRsvOrderTradingType))
                {
                    for (int i = 0; i < l_intCnt; i++)
                    {
                        RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                            new RsvIfoClosingContractSpecParams();

                        //�����h�c(account_id):�����̕⏕����.����ID
                        l_rsvIfoClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());

                        //�⏕�����h�c(sub_account_id):�����̕⏕����.�⏕����ID
                        l_rsvIfoClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());

                        //�����h�c(order_id):�����̒���ID
                        l_rsvIfoClosingContractSpecParams.setOrderId(l_lngOrderId);

                        //���ʂh�c(contract_id):getSettleContractOrderEntries()�̖߂�l[index].getContractId()
                        l_rsvIfoClosingContractSpecParams.setContractId(l_settleContractEntry[i].getContractId());

                        //�ԍϘA��(closing_serial_no):index + 1
                        l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                        //�ԍϒ�������(quantity):getSettleContractOrderEntries()�̖߂�l[index].getQuantity()
                        l_rsvIfoClosingContractSpecParams.setQuantity(l_settleContractEntry[i].getQuantity());

                        //�쐬���t(created_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                        l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                        //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
                        l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        l_queryProcessor.doInsertQuery(l_rsvIfoClosingContractSpecParams);
                    }
                }
            }
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

        //�S�j�@@�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒���ID)���R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService) Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        //�T�j�@@�e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B
        //this.set�\�񒍕��ݒ�To�敨OP�e����(�����̐e�����̒����P��)���R�[������B
        this.setReserveOrderSettingToIfoParentOrder(l_parentOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit���������\�񒍕�)<BR>
     * �isubmitEqtypeChangeOrder�j<BR>
     * <BR>
     * �����\�񒍕��P�ʃe�[�u���ɁA�\�񒍕��ɑ΂�������𔽉f����B<BR>
     * �ΏہF�@@�������������A�M�p�V�K������<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B<BR>
     * <BR>
     * �@@�X�V�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j����������������_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����<BR>
     * �@@(�����̒����O�\�񒍕��P��.����ID)���R�[������B<BR>
     * <BR>
     * �S�j�@@return����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_eqtypeChangeOrderSpec - (�\�񒍕��������e)<BR>
     * �\�񒍕��������e�I�u�W�F�N�g�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_changingBeforeRsvEqOrderUnit - (�����O�\�񒍕��P��)<BR>
     * �����O�̗\�񒍕��P�ʁB<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B8C570102
     */
    public void submitEqtypeChangeOrder(
        SubAccount l_subAccount, 
        WEB3ToSuccEqtypeChangeOrderSpec l_eqtypeChangeOrderSpec, 
        String l_strTradingPassword, 
        WEB3ToSuccEqTypeOrderUnitImpl l_changingBeforeRsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeChangeOrder(SubAccount, WEB3ToSuccEqtypeChangeOrderSpec," +
            " String, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_eqtypeChangeOrderSpec == null)
        {
            log.debug("�\�񒍕��������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񒍕��������e = null�B");
        }
        
        if (l_changingBeforeRsvEqOrderUnit == null)
        {
            log.debug("�����O�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����O�\�񒍕��P�� = null�B");
        }
        
        //�P�j�@@����p�X���[�h���`�F�b�N����B
        //�����`�F�b�N.validate����p�X���[�h()���R�[������B
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���`�F�b�N�G���[");
        }
        
        //�Q�j�@@�����\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
        //�X�V�̎d�l�́ADB�X�V�d�l 
        //�u�i�A���j����������������_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(
            (RsvEqOrderUnitRow)l_changingBeforeRsvEqOrderUnit.getDataSourceObject());
                                                                          
        //����҂h�c
        //�����̗\�񒍕��������e.get����().�����ID
        //��get����()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
        Trader l_trader = l_eqtypeChangeOrderSpec.getTrader();
        if (l_trader != null)
        {
            l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
        }
        else
        {
            l_rsvEqOrderUnitParams.setTraderId(null);
        }
                                                                             
        //���������ŏI�ʔ�    (   last_order_action_serial_no ):  �i�����l�j�{�P     
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(
            l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);

        //��������    (   quantity    ):  �����������e�ڍ�.getAfterChangeOriginalQuantity()
        EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = l_eqtypeChangeOrderSpec.getChangeOrderUnitEntry();
        l_rsvEqOrderUnitParams.setQuantity(
            l_eqTypeChangeOrderUnitEntry.getAfterChangeOriginalQuantity());
                                                                               
        //�w�l  (   limit_price ):  �����̗\�񒍕��������e.get������P�������l()==null�̏ꍇ�F
        //�����������e�ڍ�.getAfterChangePrice()
        //��L�ȊO�̏ꍇ�F�@@null 
        if (l_eqtypeChangeOrderSpec.getModifiedPriceAdjustValue() == null)
        {
            l_rsvEqOrderUnitParams.setLimitPrice(l_eqTypeChangeOrderUnitEntry.getAfterChangePrice());                                      
        }
        else
        {
            l_rsvEqOrderUnitParams.setLimitPrice(null);    
        }
        
        //�P�������l   (   price_adjust_value  ):  �����̗\�񒍕��������e.get������P�������l()
        //��null�̏ꍇ��null���Z�b�g
        l_rsvEqOrderUnitParams.setPriceAdjustValue(l_eqtypeChangeOrderSpec.getModifiedPriceAdjustValue());        
        
        //�����������t  (   expiration_date ):  �����̗\�񒍕��������e.get�����㒍��������()                                                                  
        l_rsvEqOrderUnitParams.setExpirationDate(l_eqtypeChangeOrderSpec.getModifiedExpirationDate());
        
        //�������    (   order_status    ):  10:�����ρi�ύX�����j                                                                 
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        
        //�����P��    (   price   ):  �����̗\�񒍕��������e.get������v�Z�P��()                                                                      
        l_rsvEqOrderUnitParams.setPrice(l_eqtypeChangeOrderSpec.getModifiedCalcUnitPrice());
        
        //�T�Z��n���  (   estimated_price ):�����̗\�񒍕��������e.get������T�Z��n���()                                                                      
        l_rsvEqOrderUnitParams.setEstimatedPrice(l_eqtypeChangeOrderSpec.getModifiedEstimatedPrice());
        
        //�����o�H�敪  (   order_root_div  ):  ���O�C���Z�b�V�������擾���ăZ�b�g     
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);                                                                 
        l_rsvEqOrderUnitParams.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //���񒍕��̒����P�ʂh�c
        Long l_firstOrderUnitId = null;
        if (l_eqtypeChangeOrderSpec.isModifiedCarriedOrder())
        {
            l_firstOrderUnitId = new Long(0);
        }
        l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_firstOrderUnitId);
        
        //�X�V���t    (   last_updated_timestamp  ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
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
        
        //�R�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����̒����O�\�񒍕��P��.����ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvEqOrderUnit.getOrderId());     
        
        //�S�j�@@return����B
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (submit���������\��ԍϒ���)<BR>
     * �isubmitEqtypeChangeSettleContractOrder�j<BR>
     * <BR>
     * �����\�񒍕��P�ʃe�[�u���ɁA�\�񒍕��ɑ΂�������𔽉f����B<BR>
     * �ΏہF�@@�M�p�ԍϒ���<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�����\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B<BR>
     * <BR>
     * �@@�X�V�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�ԍϒ�������_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�����c�ɑ΂���ԍς̏ꍇ�A<BR>
     * �@@�i�p�����[�^.�����O�\�񒍕��P��.is���Δ������() == false�j<BR>
     * �@@�����\�񌚊��ԍώw����e�[�u���̃��R�[�h���X�V����B<BR>
     * �@@���p�����[�^.�\��ԍϒ����������e.get�����\��ԍϒ����������e�ڍ�().<BR>
     * �@@�@@getAfterChangeSettleContractOrderEntries()�̗v�f�����A�X�V����B<BR>
     * �@@<BR>
     * �@@�X�V�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�u�i�A���j�M�p�ԍϒ�������_�����\�񌚊��ԍώw����e�[�u���d�l.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �S�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����<BR>
     * �@@(�����̒����O�\�񒍕��P��.����ID)���R�[������B<BR>
     * <BR>
     * �T�j�@@return����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_marginChangeSettleContractOrderSpec - (�\��ԍϒ����������e)<BR>
     * �\��ԍϒ����������e�I�u�W�F�N�g�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_changingBeforeRsvEqOrderUnit - (�����O�\�񒍕��P��)<BR>
     * �����O�̗\�񒍕��P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 4342586D0249
     */
    public void submitEqtypeChangeSettleContractOrder(
        SubAccount l_subAccount, 
        WEB3ToSuccMarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec, 
        String l_strTradingPassword, 
        WEB3ToSuccEqTypeOrderUnitImpl l_changingBeforeRsvEqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeChangeSettleContractOrder(SubAccount, WEB3ToSuccMarginChangeSettleContractOrderSpec," +
            " String, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_marginChangeSettleContractOrderSpec == null)
        {
            log.debug("�\��ԍϒ����������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\��ԍϒ����������e = null�B");
        }
        
        if (l_changingBeforeRsvEqOrderUnit == null)
        {
            log.debug("�����O�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����O�\�񒍕��P�� = null�B");
        }        
        
        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B 
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }
            
            //�Q�j�@@�����\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B 
            //�o�^�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�M�p�ԍϒ�������_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(
                (RsvEqOrderUnitRow)l_changingBeforeRsvEqOrderUnit.getDataSourceObject());
            
            //����҂h�c
            //�����̗\��ԍϒ����������e.get����().�����ID
            //��get����()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
            Trader l_trader = l_marginChangeSettleContractOrderSpec.getTrader();
            if (l_trader != null)
            {
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            else
            {
                l_rsvEqOrderUnitParams.setTraderId(null);
            }
                                                                             
            //���������ŏI�ʔ�    (   last_order_action_serial_no ):  �i�����l�j�{�P     
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //��������    (   quantity    ):  ���������ڍ�.AfterChangeTotalQuantity()
            EqTypeContractSettleChangeOrderUnitEntry l_eqTypeContractSettleChangeOrderUnitEntry = 
                l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry();
            l_rsvEqOrderUnitParams.setQuantity(
                l_eqTypeContractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity());
                                                                               
            //�w�l  (   limit_price ):  �����̗\��ԍϒ����������e.get������P�������l()==null�̏ꍇ�F
            //���������ڍ�.getAfterChangePrice()
            //��L�ȊO�̏ꍇ�F�@@null
            if (l_marginChangeSettleContractOrderSpec.getModifiedPriceAdjustValue() == null)
            {
                l_rsvEqOrderUnitParams.setLimitPrice(l_eqTypeContractSettleChangeOrderUnitEntry.getAfterChangePrice());                                      
            }
            else
            {
                l_rsvEqOrderUnitParams.setLimitPrice(null);    
            }
        
            //�P�������l   (   price_adjust_value  ):  �����̗\��ԍϒ����������e.get������P�������l()
            //��null�̏ꍇ��null���Z�b�g
            l_rsvEqOrderUnitParams.setPriceAdjustValue(
                l_marginChangeSettleContractOrderSpec.getModifiedPriceAdjustValue());        
        
            //�����������t  (   expiration_date ):  �����̗\��ԍϒ����������e.get�����㒍��������()                                                                  
            l_rsvEqOrderUnitParams.setExpirationDate(
                l_marginChangeSettleContractOrderSpec.getModifiedExpirationDate());
        
            //�������    (   order_status    ):  10:�����ρi�ύX�����j                                                                 
            l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        
            //�����P��    (   price   ):  �����̗\��ԍϒ����������e.get������v�Z�P��()                                                                      
            l_rsvEqOrderUnitParams.setPrice(
                l_marginChangeSettleContractOrderSpec.getModifiedCalcUnitPrice());
        
            //�T�Z��n���  (   estimated_price ):�����̗\��ԍϒ����������e.get������T�Z��n���()                                                                      
            l_rsvEqOrderUnitParams.setEstimatedPrice(
                l_marginChangeSettleContractOrderSpec.getModifiedEstimatedPrice());
        
            //�����o�H�敪  (   order_root_div  ):  ���O�C���Z�b�V�������擾���ăZ�b�g     
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);                                                                 
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));        

            //���񒍕��̒����P�ʂh�c
            Long l_firstOrderUnitId = null;
            if (l_marginChangeSettleContractOrderSpec.isModifiedCarriedOrder())
            {
                l_firstOrderUnitId = new Long(0);
            }
            l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_firstOrderUnitId);

            //�X�V���t    (   last_updated_timestamp  ):  ���ݓ����iGtlUtils.getSystemTimestamp()�j                                                                     
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        
            //�R�j�@@�����c�ɑ΂���ԍς̏ꍇ�A 
            //���p�����[�^.�\��ԍϒ����������e.get�����\��ԍϒ����������e�ڍ�().  
            //getAfterChangeSettleContractOrderEntries()�̗v�f�����A�X�V����B 
            //�X�V�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�M�p�ԍϒ�������_�����\�񌚊��ԍώw����e�[�u���d�l.xls�v 
            //���Q�ƁB
            boolean l_blnIsReverseTrade = 
                l_changingBeforeRsvEqOrderUnit.isReversingTrade();
        
            if (!l_blnIsReverseTrade)
            {
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries = 
                    l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry().getAfterChangeSettleContractOrderEntries();
                
                int l_intCnt = 0;
                if (l_eqTypeSettleContractOrderEntries != null && 
                l_eqTypeSettleContractOrderEntries.length > 0)
                {
                    l_intCnt = l_eqTypeSettleContractOrderEntries.length;
                }
                
                for (int i = 0; i < l_intCnt; i++)
                {                    
                    String l_strWhere = " order_id = ? and contract_id = ? ";                
                    Object[] l_objs = {
                        new Long(l_changingBeforeRsvEqOrderUnit.getOrderId()), 
                        new Long(l_eqTypeSettleContractOrderEntries[i].getContractId())};
                        
                    Map l_map = new HashMap();    
                    //�ԍϒ�������(quantity):�����Ώۂ̗v�f.getQuantity()
                    l_map.put("quantity", new Double(l_eqTypeSettleContractOrderEntries[i].getQuantity()));
                    
                    //�X�V���t(last_updated_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j
                    l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doUpdateAllQuery(
                        RsvEqClosingContractSpecRow.TYPE,
                        l_strWhere,
                        l_objs,
                        l_map); //DataNetworkException,DataQueryException      
                }
            }        
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
        
        //�S�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����(�����O�\�񒍕��P��.����ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvEqOrderUnit.getOrderId());        
        
        //�T�j�@@return����B
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (submit�敨OP�����\��V�K������)<BR>
     * �isubmitIfoChangeOpenContractOrder�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P�ʃe�[�u���ɁA�\�񒍕��ɑ΂�������𔽉f����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B<BR>
     * <BR>
     * �@@�@@�X�V�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���j�V�K������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒����O�\�񒍕��P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �S�j�@@return����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_ifoChangeOrderSpec - (�\�񒍕��������e)<BR>
     * �\�񒍕��������e�I�u�W�F�N�g�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_changingBeforeRsvIfoOrderUnit - (�����O�\�񒍕��P��)<BR>
     * �����O�̗\�񒍕��P�ʁB
     * @@throws WEB3BaseException
     */
    public void submitIfoChangeOpenContractOrder(
        SubAccount l_subAccount,
        WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOrderSpec,
        String l_strTradingPassword,
        WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoChangeOpenContractOrder(SubAccount, WEB3ToSuccIfoChangeOpenContractOrderSpec," +
            " String, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�⏕���� = null�B");
        }

        if (l_ifoChangeOrderSpec == null)
        {
            log.debug("�\�񒍕��������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񒍕��������e = null�B");
        }

        if (l_changingBeforeRsvIfoOrderUnit == null)
        {
            log.debug("�����O�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����O�\�񒍕��P�� = null�B");
        }

        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }

            //�Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
            //�X�V�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�V�K������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(
                (RsvIfoOrderUnitRow)l_changingBeforeRsvIfoOrderUnit.getDataSourceObject());

            //����҂h�c(trader_id):�����̗\�񒍕��������e.get����().�����ID
            //��get����()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
            WEB3GentradeTrader l_trader = l_ifoChangeOrderSpec.getTrader();
            if (l_trader != null)
            {
                l_rsvIfoOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setTraderId(null);
            }

            //���������ŏI�ʔ�(last_order_action_serial_no):�i�����l�j�{�P
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvIfoOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //��������(quantity):�����̗\�񒍕��������e.getAfterChangeOriginalQuantity
            l_rsvIfoOrderUnitParams.setQuantity(l_ifoChangeOrderSpec.getAfterChangeOriginalQuantity());

            //�w�l(limit_price):�����̗\�񒍕��������e.get������P�������l()==null�̏ꍇ�F
            //�\�񒍕��������e.getAfterChangePrice()
            //��L�ȊO�̏ꍇ�F�@@null
            if (l_ifoChangeOrderSpec.getModifiedPriceAdjustValue() == null)
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoChangeOrderSpec.getAfterChangePrice());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(null);
            }

            //�P�������l(price_adjust_value):�����̗\�񒍕��������e.get������P�������l()
            //��null�̏ꍇ��null���Z�b�g
            Double l_modifiedPriceAdjustValue = l_ifoChangeOrderSpec.getModifiedPriceAdjustValue();
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_modifiedPriceAdjustValue);

            //�����������t(expiration_date):�����̗\�񒍕��������e.get�����㒍��������()
            l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoChangeOrderSpec.getModifiedExpirationDate());

            //�������(order_status):10:�����ρi�ύX�����j
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);

            //�����P��(price):�����̗\�񒍕��������e.get������v�Z�P��()
            l_rsvIfoOrderUnitParams.setPrice(l_ifoChangeOrderSpec.getModifiedCalcUnitPrice());

            //�T�Z��n���(estimated_price):�����̗\�񒍕��������e.get������T�Z��n���()
            l_rsvIfoOrderUnitParams.setEstimatedPrice(l_ifoChangeOrderSpec.getModifiedEstimatedPrice());

            //�����o�H�敪(order_root_div):���O�C���Z�b�V�������擾���ăZ�b�g
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            l_rsvIfoOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //���񒍕��̒����P�ʂh�c(first_order_unit_id):�����̗\�񒍕��������e.get���񒍕��̒����P��ID()
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoChangeOrderSpec.getFirstOrderUnitId());

            //�[��O�J�z�Ώۃt���O(evening_session_carryover_flag):�����̗\�񒍕��������e.get�[��O�J�z�Ώۃt���O()
            if (l_ifoChangeOrderSpec.getEveningSessionCarryOverFlag())
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            }
            else
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            }

            //���������敪(expiration_date_type):�����̗\�񒍕��������e.get���������敪()
            l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoChangeOrderSpec.getExpirationDateType());

            //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);
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

        //�R�j�@@�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒����O�\�񒍕��P��.����ID)��
        //�R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvIfoOrderUnit.getOrderId());

        //�S�j�@@return����B
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (submit�敨OP�����\��ԍϒ���)<BR>
     * �isubmitIfoChangeSettleContractOrder�j<BR>
     * <BR>
     * �敨OP�\�񒍕��P�ʃe�[�u���ɁA�\�񒍕��ɑ΂�������𔽉f����B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B<BR>
     * <BR>
     * �@@�@@�X�V�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���j�ԍϒ�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�����c�ɑ΂���ԍς̏ꍇ�A<BR>
     * �@@�i�p�����[�^.�����O�\�񒍕��P��.is���Δ������() == false�j<BR>
     * �@@�敨OP�\�񌚋ʕԍώw����e�[�u���̃��R�[�h���X�V����B<BR>
     * �@@���p�����[�^.�\��ԍϒ����������e.getAfterChangeSettleContractEntries()<BR>
     * �̗v�f�����A�X�V����B<BR>
     * <BR>
     * �@@�@@�X�V�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�i�A���j�ԍϒ�������_�敨OP�\�񌚋ʕԍώw����e�[�u���d�l.xls�v���Q�ƁB<BR>
     * <BR>
     * �S�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒����O�\�񒍕��P��.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �T�j�@@return����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_toSuccIfoChangeSettleContractOrderSpec - (�\��ԍϒ����������e)<BR>
     * �\�񒍕��������e�I�u�W�F�N�g�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@param l_changingBeforeRsvIfoOrderUnit - (�����O�\�񒍕��P��)<BR>
     * �����O�̗\�񒍕��P�ʁB
     * @@throws WEB3BaseException
     */
    public void submitIfoChangeSettleContractOrder(
        SubAccount l_subAccount,
        WEB3ToSuccIfoChangeSettleContractOrderSpec l_toSuccIfoChangeSettleContractOrderSpec,
        String l_strTradingPassword,
        WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoChangeSettleContractOrder(SubAccount, WEB3ToSuccIfoChangeOpenContractOrderSpec," +
            " String, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�⏕���� = null�B");
        }

        if (l_toSuccIfoChangeSettleContractOrderSpec == null)
        {
            log.debug("�\�񒍕��������e = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񒍕��������e = null�B");
        }

        if (l_changingBeforeRsvIfoOrderUnit == null)
        {
            log.debug("�����O�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����O�\�񒍕��P�� = null�B");
        }

        try
        {
            //�P�j�@@����p�X���[�h���`�F�b�N����B
            //�����`�F�b�N.validate����p�X���[�h()���R�[������B
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("����p�X���[�h���`�F�b�N�G���[");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����p�X���[�h���`�F�b�N�G���[");
            }

            //�Q�j�@@�敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
            //�X�V�̎d�l�́ADB�X�V�d�l
            //�u�i�A���j�ԍϒ�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(
                (RsvIfoOrderUnitRow)l_changingBeforeRsvIfoOrderUnit.getDataSourceObject());

            //����҂h�c(trader_id):�����̗\�񒍕��������e.get����().�����ID
            //��get����()�̖߂�l==null�̏ꍇ�́Anull���Z�b�g
            WEB3GentradeTrader l_trader = l_toSuccIfoChangeSettleContractOrderSpec.getTrader();
            if (l_trader != null)
            {
                l_rsvIfoOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setTraderId(null);
            }

            //���������ŏI�ʔ�(last_order_action_serial_no):�i�����l�j�{�P
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvIfoOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //��������(quantity):�\��ԍϒ����������e.getAfterChangeTotalQuantity()
            l_rsvIfoOrderUnitParams.setQuantity(
                l_toSuccIfoChangeSettleContractOrderSpec.getAfterChangeTotalQuantity());

            //�w�l(limit_price):�����̗\�񒍕��������e.get������P�������l()==null�̏ꍇ�F
            //�\�񒍕��������e.getAfterChangePrice()
            //��L�ȊO�̏ꍇ�F�@@null
            if (l_toSuccIfoChangeSettleContractOrderSpec.getModifiedPriceAdjustValue() == null)
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(l_toSuccIfoChangeSettleContractOrderSpec.getAfterChangePrice());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(null);
            }

            //�P�������l(price_adjust_value):�����̗\�񒍕��������e.get������P�������l()
            //��null�̏ꍇ��null���Z�b�g
            Double l_modifiedPriceAdjustValue =
                l_toSuccIfoChangeSettleContractOrderSpec.getModifiedPriceAdjustValue();
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_modifiedPriceAdjustValue);

            //�����������t(expiration_date):�����̗\�񒍕��������e.get�����㒍��������()
            l_rsvIfoOrderUnitParams.setExpirationDate(
                l_toSuccIfoChangeSettleContractOrderSpec.getModifiedExpirationDate());

            //�������(order_status):10:�����ρi�ύX�����j
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);

            //�����P��(price):�����̗\�񒍕��������e.get������v�Z�P��()
            l_rsvIfoOrderUnitParams.setPrice(l_toSuccIfoChangeSettleContractOrderSpec.getModifiedCalcUnitPrice());

            //�T�Z��n���(estimated_price):�����̗\�񒍕��������e.get������T�Z��n���()
            l_rsvIfoOrderUnitParams.setEstimatedPrice(
                l_toSuccIfoChangeSettleContractOrderSpec.getModifiedEstimatedPrice());

            //�����o�H�敪(order_root_div):���O�C���Z�b�V�������擾���ăZ�b�g
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            l_rsvIfoOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //���񒍕��̒����P�ʂh�c(first_order_unit_id):�����̗\�񒍕��������e.get���񒍕��̒����P��ID()
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(
                l_toSuccIfoChangeSettleContractOrderSpec.getFirstOrderUnitId());

            //�[��O�J�z�Ώۃt���O(evening_session_carryover_flag):�����̗\�񒍕��������e.get�[��O�J�z�Ώۃt���O()
            if (l_toSuccIfoChangeSettleContractOrderSpec.getEveningSessionCarryOverFlag())
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            }
            else
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            }

            //���������敪(expiration_date_type):�����̗\�񒍕��������e.get���������敪()
            l_rsvIfoOrderUnitParams.setExpirationDateType(
                l_toSuccIfoChangeSettleContractOrderSpec.getExpirationDateType());

            //�X�V���t(last_updated_timestamp):���ݓ����iGtlUtils.getSystemTimestamp()�j
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);
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

        //�R�j�@@�����c�ɑ΂���ԍς̏ꍇ�A
        //�i�p�����[�^.�����O�\�񒍕��P��.is���Δ������() == false�j
        //�敨OP�\�񌚋ʕԍώw����e�[�u���̃��R�[�h���X�V����B
        if (!l_changingBeforeRsvIfoOrderUnit.isReversingTrade())
        {
            try
            {
                //���p�����[�^.�\��ԍϒ����������e.getAfterChangeSettleContractEntries()�̗v�f�����A�X�V����B
                //�X�V�̎d�l�́ADB�X�V�d�l
                //�u�i�A���j�ԍϒ�������_�敨OP�\�񌚋ʕԍώw����e�[�u���d�l.xls�v���Q�ƁB
                SettleContractEntry[] l_settleContractEntry =
                    l_toSuccIfoChangeSettleContractOrderSpec.getAfterChangeSettleContractEntries();

                int l_intCnt = 0;
                if (l_settleContractEntry != null && l_settleContractEntry.length > 0)
                {
                    l_intCnt = l_settleContractEntry.length;
                }

                for (int i = 0; i < l_intCnt; i++)
                {
                    String l_strWhere = " order_id = ? and contract_id = ? ";
                    Object[] l_objs = {
                        new Long(l_changingBeforeRsvIfoOrderUnit.getOrderId()),
                        new Long(l_settleContractEntry[i].getContractId())};

                    Map l_map = new HashMap();
                    //�ԍϒ�������(quantity):�����Ώۂ̗v�f.getQuantity()
                    l_map.put("quantity", new Double(l_settleContractEntry[i].getQuantity()));

                    //�X�V���t(last_updated_timestamp): ���ݓ����iGtlUtils.getSystemTimestamp()�j
                    l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateAllQuery(
                        RsvIfoClosingContractSpecRow.TYPE,
                        l_strWhere,
                        l_objs,
                        l_map);
                }
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

        //�S�j�@@�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̒����O�\�񒍕��P��.����ID)��
        //�R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvIfoOrderUnit.getOrderId());

        //�T�j�@@return����B
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (submit�����\�񒍕����)<BR>
     * �isubmitEqtypeCancelOrder�j<BR>
     * <BR>
     * �����̊����\�񒍕��P�ʂ��������B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�\�񒍕����������B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.cancel�\�񒍕��P��(�����̗\�񒍕��P��)��<BR>
     * �@@�R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P��Impl�I�u�W�F�N�g�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B78F100C2
     */
    public void submitEqtypeCancelOrder(
        SubAccount l_subAccount, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit, 
        String l_strTradingPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeCancelOrder(SubAccount, WEB3ToSuccEqTypeOrderUnitImpl, String)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񒍕��P�� = null�B");
        }        
        
        //�P�j�@@����p�X���[�h���`�F�b�N����B
        //�����`�F�b�N.validate����p�X���[�h()���R�[������B 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
    
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
    
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���`�F�b�N�G���[");
        }
        
        //�Q�j�@@�\�񒍕����������B 
        //�����\�񒍕��X�V�T�[�r�X.cancel�\�񒍕��P��(�����̗\�񒍕��P��)�� 
        //�R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqTypeOrderUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqTypeOrderUpdateService.cancelOrderUnit(
            (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject());
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit�敨OP�\�񒍕����)<BR>
     * �isubmitIfoCancelOrder�j<BR>
     * �����̐敨OP�\�񒍕��P�ʂ��������B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h���`�F�b�N����B<BR>
     * <BR>
     * �@@�����`�F�b�N.validate����p�X���[�h()���R�[������B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��validate����p�X���[�h()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�㗝���͎ҁF�@@null�ivalidate����p�X���[�h()���ŁA���O�C����񂩂�擾�����j<BR>
     * �@@�⏕�����F�@@�����̕⏕����<BR>
     * �@@�p�X���[�h�F�@@�����̎���p�X���[�h<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�\�񒍕����������B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.cancel�\�񒍕��P��(�����̗\�񒍕��P��)��<BR>
     * �@@�R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_toSuccIfoOrderUnitImpl - (�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P��Impl�I�u�W�F�N�g�B<BR>
     * @@param l_strTradingPassword - (����p�X���[�h)<BR>
     * ����p�X���[�h�B
     * @@throws WEB3BaseException
     */
    public void submitIfoCancelOrder(
        SubAccount l_subAccount,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl,
        String l_strTradingPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoCancelOrder(SubAccount, WEB3ToSuccIfoOrderUnitImpl, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�⏕���� = null�B");
        }

        if (l_toSuccIfoOrderUnitImpl == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񒍕��P�� = null�B");
        }

        //�P�j�@@����p�X���[�h���`�F�b�N����B
        //�����`�F�b�N.validate����p�X���[�h()���R�[������B
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����p�X���[�h���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����p�X���[�h���`�F�b�N�G���[");
        }

        //�Q�j�@@�\�񒍕����������B
        //�敨OP�\�񒍕��X�V�T�[�r�X.cancel�\�񒍕��P��(�����̗\�񒍕��P��)��
        //�R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.cancelOrderUnit(
            (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnitImpl.getDataSourceObject());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�����������)<BR>
     * ��������������������R�����s���B<BR>
     * <BR>
     * �P�j�@@��t���ԃ`�F�b�N�E�V�X�e�������~�`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@������ԊǗ�.validate������t�\()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�ڋq�ʎ����~�`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�����`�F�b�N.validate����\�ڋq(�����̕⏕����)���R�[������B<BR>
     * <BR>
     * �R�j�@@�w��\�񒍕�������\���ǂ������`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�p�����[�^.�\�񒍕��P��.validate����\���()���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4340BD450302
     */
    public void validateEqtypeCancelOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateEqtypeCancelOrder(WEB3GentradeSubAccount, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񒍕��P�� = null�B");
        } 
        
        //�P�j�@@��t���ԃ`�F�b�N�E�V�X�e�������~�`�F�b�N���s���B
        //������ԊǗ�.validate������t�\()���R�[������B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //�Q�j�@@�ڋq�ʎ����~�`�F�b�N���s���B
        //�����`�F�b�N.validate����\�ڋq(�����̕⏕����)���R�[������B
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
    
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�ڋq�ʎ����~���`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�ʎ����~���`�F�b�N�G���[");
        }
        
        //�R�j�@@�w��\�񒍕�������\���ǂ������`�F�b�N����B
        //�p�����[�^.�\�񒍕��P��.validate����\���()���R�[������B
        l_rsvEqOrderUnit.validateOrderForCancellation();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�M�p�������)<BR>
     * �M�p���������������R�����s���B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.validate�M�p����()��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@�@@[validate�M�p����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�@@�@@�ٍϋ敪�F�@@�p�����[�^.�\�񒍕��P��.�ٍϋ敪<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�񒍕��P��.validate����\���()<BR>
     * �@@�@@�@@���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A34D40271
     */
    public void validateMarginCancelOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateMarginCancelOrder(WEB3GentradeSubAccount, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�⏕���� = null�B");
        }
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񒍕��P�� = null�B");
        } 
        
        //�P�j�@@�g�����������}�l�[�W��.validate�M�p����()���R�[������B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
        l_orderManager.validateMarginOrder(l_subAccount, l_rsvEqOrderUnitRow.getRepaymentType());
        
        //�Q�j�@@�p�����[�^.�\�񒍕��P��.validate����\���()���R�[������B
        l_rsvEqOrderUnit.validateOrderForCancellation();
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    
    /**
     * (validate�敨OP�������)<BR>
     * �敨OP������������R�����s���B<BR>
     * <BR>
     * �P�j�@@OP�����}�l�[�W��.validate����()��<BR>
     * �@@�@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@�@@[validate����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�@@�@@�@@�敨�^�I�v�V�����敪�F�@@�p�����[�^.�\�񒍕��P��.�敨�^�I�v�V�����敪<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�񒍕��P��.validate����\���()<BR>
     * �@@�@@�@@���R�[������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_toSuccIfoOrderUnitImpl - (�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P�ʃI�u�W�F�N�g
     * @@throws WEB3BaseException
     */
    public void validateIfoCancelOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateIfoCancelOrder(WEB3GentradeSubAccount, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�⏕���� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�⏕���� = null�B");
        }

        if (l_toSuccIfoOrderUnitImpl == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񒍕��P�� = null�B");
        }

        //�P�j�@@OP�����}�l�[�W��.validate����()��
        //�R�[������B
        //[validate����()�Ɏw�肷�����]
        //�⏕�����F�@@�p�����[�^.�⏕����
        //�敨�^�I�v�V�����敪�F�@@�p�����[�^.�\�񒍕��P��.�敨�^�I�v�V�����敪
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnitImpl.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        l_orderMgr.validateOrder(l_subAccount, l_rsvIfoOrderUnitRow.getFutureOptionDiv());

        //�Q�j�@@�p�����[�^.�\�񒍕��P��.validate����\���()
        //���R�[������B
        l_toSuccIfoOrderUnitImpl.validateOrderForCancellation();
    }

    /**
     * (get�����e�������A��)<BR>
     * �w��̐e�������ɂ�����q�����̘A�Ԃ��擾����B<BR>
     * �i�\�񒍕��o�^���́A�e�������A�Ԑݒ�Ɏg�p�j<BR>
     * <BR>
     * �P�j�@@DB����<BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@------------------------------- <BR>
     * �@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@�@@�e�����̒����P��ID = ����.�e�����̒����P��ID <BR>
     * <BR>
     * �@@�@@���u�e�������A�ԁv�ō~���\�[�g�w�肷��B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �Q�j�@@�擪���R�[�h�́i�e�������A�ԁ{�P�j�������ʂ��A�e�������A�ԂƂ���<BR>
     * �ԋp����B<BR>
     * �@@�@@�@@���Y�����R�[�h�Ȃ��̏ꍇ�́A�P��ԋp����B
     * @@param l_lngParentOrderUnitId - (�e�����̒����P��ID)<BR>
     * �e�����̒����P��ID�B
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 432FAA0D0216
     */
    protected long getEqtypeSerialNoInParent(long l_lngParentOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEqtypeSerialNoInParent(long)";
        log.entering(STR_METHOD_NAME); 
        
        try 
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�����\�񒍕��P�ʃe�[�u���z����������B
            //������������
            //�e�����̒����P��ID = ����.�e�����̒����P��ID
            //���u�e�������A�ԁv�ō~���\�[�g�w�肷��B
            String l_strWhere = " parent_order_unit_id = ? ";                
            Object[] l_objs = {new Long(l_lngParentOrderUnitId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    " serial_no_in_parent desc ",
                    null,
                    l_objs);//DataNetworkException,DataQueryException
                    
            //�Q�j�@@�擪���R�[�h�́i�e�������A�ԁ{�P�j�������ʂ��A�e�������A�ԂƂ���
            //�ԋp����
            if (l_lisRsvEqOrderUnitRows != null && !l_lisRsvEqOrderUnitRows.isEmpty())
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_lisRsvEqOrderUnitRows.get(0);
                
                log.exiting(STR_METHOD_NAME);
                return l_rsvEqOrderUnitRow.getSerialNoInParent() + 1;
            }
            //���Y�����R�[�h�Ȃ��̏ꍇ�́A�P��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
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
     * (get�敨OP�e�������A��)<BR>
     * �w��̐e�������ɂ�����q�����̘A�Ԃ��擾����B<BR>
     * �i�\�񒍕��o�^���́A�e�������A�Ԑݒ�Ɏg�p�j<BR>
     * <BR>
     * �P�j�@@DB���� <BR>
     * �@@�@@�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B<BR>
     * <BR>
     * �@@�@@-------------------------------<BR>
     * �@@�@@������������ <BR>
     * <BR>
     * �@@�@@�@@�@@�e�����̒����P��ID = ����.�e�����̒����P��ID<BR>
     * <BR>
     * �@@�@@���u�e�������A�ԁv�ō~���\�[�g�w�肷��B<BR>
     * �@@�@@-------------------------------<BR>
     * <BR>
     * �Q�j�@@�擪���R�[�h�́i�e�������A�ԁ{�P�j�������ʂ��A�e�������A�ԂƂ��ĕԋp����B<BR>
     * �@@�@@�@@���Y�����R�[�h�Ȃ��̏ꍇ�́A�P��ԋp����B<BR>
     * @@param l_lngParentOrderUnitId - (�e�����̒����P��ID)<BR>
     * �e�����̒����P��ID�B
     * @@return long
     * @@throws WEB3BaseException
     */
    protected long getIfoSerialNoInParent(long l_lngParentOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getIfoSerialNoInParent(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�P�j�@@DB����
            //�ȉ��̏������w�肵�āA�y�敨OP�\�񒍕��P�ʃe�[�u���z����������B
            //������������
            //�e�����̒����P��ID = ����.�e�����̒����P��ID
            //���u�e�������A�ԁv�ō~���\�[�g�w�肷��B
            String l_strWhere = " parent_order_unit_id = ? ";
            Object[] l_objs = {new Long(l_lngParentOrderUnitId)};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    " serial_no_in_parent desc ",
                    null,
                    l_objs);

            //�Q�j�@@�擪���R�[�h�́i�e�������A�ԁ{�P�j�������ʂ��A�e�������A�ԂƂ���
            //�ԋp����
            if (l_lisRsvIfoOrderUnitRows != null && !l_lisRsvIfoOrderUnitRows.isEmpty())
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0);

                log.exiting(STR_METHOD_NAME);
                return l_rsvIfoOrderUnitRow.getSerialNoInParent() + 1;
            }
            //���Y�����R�[�h�Ȃ��̏ꍇ�́A�P��ԋp����B
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
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
     * (set������To�\�񒍕��P��)<BR>
     * �isetOrderedToOrderUnit�j<BR>
     * <BR>
     * �w�肳�ꂽ�\�񒍕��P�ʃI�u�W�F�N�g���A�����ς̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j�@@�X�V�Ώۂ̗\�񒍕��P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@this.getOrderUnit(�����̖����^�C�v, ����ID)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����ςւ̍X�V�������s���B<BR>
     * <BR>
     * �Q�|�P�j�@@�����̖����^�C�v=="����"�̏ꍇ�A<BR>
     * �@@�@@�@@this.set������To�����\�񒍕��P��( �P�j�Ŏ擾���������\�񒍕��P��)<BR>
     *       ���R�[������B<BR>
     * <BR>
     * �Q�|�Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@this.set������To�敨OP�\�񒍕��P��( �P�j�Ŏ擾�����敨OP�\�񒍕��P��)<BR>
     *       ���R�[������B
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B
     * @@param l_lngOrderId - (����ID)<BR>
     * �I���Ώۂ́A�\�񒍕��̒���ID�B
     * @@throws WEB3BaseException
     * @@roseuid 4328DDD3038E
     */
    public void setOrderedToOrderUnit(ProductTypeEnum l_productType, long l_lngOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setOrderedToOrderUnit(ProductTypeEnum, long)";
        log.entering(STR_METHOD_NAME); 
                
        try
        {
            //�P�j�@@�X�V�Ώۂ̗\�񒍕��P�ʃI�u�W�F�N�g���擾����B
            OrderUnit l_orderUnit = this.getOrderUnit(l_productType, l_lngOrderId);
            //NotFoundException
        
            //�Q�j�@@�����ςւ̍X�V�������s���B
            //�Q�|�P�j�@@�����̖����^�C�v=="����"�̏ꍇ�A
            //this.set������To�����\�񒍕��P��( �P�j�Ŏ擾���������\�񒍕��P��)
            //���R�[������B
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                log.exiting(STR_METHOD_NAME);
                this.setOrderedToEqtypeOrderUnit((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
            }
            //�Q�|�Q�j�@@��L�ȊO�̏ꍇ�A
            //this.set������To�敨OP�\�񒍕��P��( �P�j�Ŏ擾�����敨OP�\�񒍕��P��)
            //���R�[������B
            else
            {
                log.exiting(STR_METHOD_NAME);
                this.setOrderedToIfoOrderUnit((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("�\�񒍕��P�ʃe�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set������To�����\�񒍕��P��)<BR>
     * �isetOrderedToEqtypeOrderUnit�j<BR>
     * <BR>
     * �w�肳�ꂽ�����\�񒍕��P�ʃI�u�W�F�N�g���A�����ς̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j�@@���������ɂ��o�^���ꂽ�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@EQTYPE�̊g�����������}�l�[�W��.getOrderUnits(�����̗\�񒍕��P��.����ID)��<BR>
     * �R�[������B<BR>
     * �@@�ȍ~�A�擾���������P�ʃI�u�W�F�N�g�̍ŏ��̗v�f���g�p����B<BR>
     * <BR>
     * �Q�j�@@�����ςւ̍X�V�������s���B<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�A�����������iOK�j_�����\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����<BR>
     * (�����̊����\�񒍕��P��.����ID)���R�[������B
     * @@param l_rsvEqOrderUnit - (�����\�񒍕��P��)<BR>
     * �\�񒍕��P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     * @@roseuid 4328E52201D0
     */
    protected void setOrderedToEqtypeOrderUnit(
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setOrderedToEqtypeOrderUnit(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("�����\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����\�񒍕��P�� = null�B");
        }
        
        //�P�j�@@���������ɂ��o�^���ꂽ�����P�ʃI�u�W�F�N�g���擾����B
        //EQTYPE�̊g�����������}�l�[�W��.getOrderUnits(�����̗\�񒍕��P��.����ID)���R�[������B
        //�ȍ~�A�擾���������P�ʃI�u�W�F�N�g�̍ŏ��̗v�f���g�p����B        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_rsvEqOrderUnit.getOrderId()); 
        
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            String l_strErrorMsg = "�����P�ʃe�[�u���ɊY������f�[�^������܂���BOrderId = " + 
                l_rsvEqOrderUnit.getOrderId();
            
            log.error(l_strErrorMsg);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMsg);
        }
        
        OrderUnit l_orderUnit = l_orderUnits[0];       
        
        //�Q�j�@@�����ςւ̍X�V�������s���B
        //DB�X�V�d�l
        //�u�A�����������iOK�j_�����\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
        RsvEqOrderUnitParams l_rsvEqOrderParams = 
            new RsvEqOrderUnitParams((RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject());
        
        //�����P�ʂh�c  (   order_unit_id   ):  ���������ɂ��o�^���ꂽ���������P�ʃI�u�W�F�N�g.�����P��ID
        l_rsvEqOrderParams.setOrderUnitId(l_orderUnit.getOrderUnitId());
                                                                             
        //���������ŏI�ʔ�    (   last_order_action_serial_no ):  �i�����l�j�{�P
        l_rsvEqOrderParams.setLastOrderActionSerialNo(
            l_rsvEqOrderParams.getLastOrderActionSerialNo() + 1);
                                                                             
        //�������    (   order_status    ):  "3:�����ρi�V�K�����j
        //�iOrderStatusEnum�ɂĒ�`�j" 
        l_rsvEqOrderParams.setOrderStatus(OrderStatusEnum.ORDERED);
                                                                             
        //�����L�����  (   order_open_status   ):  "2:�N���[�Y
        //�iOrderOpenStatusEnum�ɂĒ�`�j"          
        l_rsvEqOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                                                                    
        //�����敪    (   expiration_status   ):  "2:�I��
        //�iOrderExpirationStatusEnum�ɂĒ�`�j"             
        l_rsvEqOrderParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
                                                                   
        //�X�V���t    (   last_updated_timestamp  ):  ���ݓ���
        l_rsvEqOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
            
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderParams); 
            //DataNetworkException,DataQueryException       
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
        
        //�R�j�@@�\�񒍕��������쐬����B
        //�����\�񒍕��X�V�T�[�r�X.insert�����\�񒍕�����
        //(�����̊����\�񒍕��P��.����ID)���R�[������B
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqTypeOrderUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService)
                Services.getService(WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqTypeOrderUpdateService.insertReserveOrderAction(l_rsvEqOrderUnit.getOrderId());
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set������To�敨OP�\�񒍕��P��)<BR>
     * �isetOrderedToIfoOrderUnit�j<BR>
     * <BR>
     * �w�肳�ꂽ�敨OP�\�񒍕��P�ʃI�u�W�F�N�g���A�����ς̏�ԂɍX�V����B<BR>
     * <BR>
     * �P�j�@@���������ɂ��o�^���ꂽ�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@OP�����}�l�[�W��.getOrderUnits(�����̗\�񒍕��P��.����ID)���R�[������B<BR>
     * �@@�ȍ~�A�擾���������P�ʃI�u�W�F�N�g�̍ŏ��̗v�f���g�p����B<BR>
     * <BR>
     * �Q�j�@@�����ςւ̍X�V�������s���B<BR>
     * <BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�A�����������iOK�j_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v<BR>
     * �@@���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̗\�񒍕��P��.����ID)<BR>
     * �@@���R�[������B<BR>
     * @@param l_rsvIfoOrderUnit - (�\�񒍕��P��)<BR>
     * �\�񒍕��P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    protected void setOrderedToIfoOrderUnit(
        WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setOrderedToIfoOrderUnit(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnit == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�񒍕��P�� = null�B");
        }

        //�P�j�@@���������ɂ��o�^���ꂽ�����P�ʃI�u�W�F�N�g���擾����B
        //OP�����}�l�[�W��.getOrderUnits(�����̗\�񒍕��P��.����ID)���R�[������B
        //�ȍ~�A�擾���������P�ʃI�u�W�F�N�g�̍ŏ��̗v�f���g�p����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_rsvIfoOrderUnit.getOrderId());

        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        OrderUnit l_orderUnit = l_orderUnits[0];

        //�Q�j�@@�����ςւ̍X�V�������s�� 
        //DB�X�V�d�l 
        //�u�A�����������iOK�j_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v
        //���Q�ƁB
        RsvIfoOrderUnitParams l_rsvIfoOrderParams =
            new RsvIfoOrderUnitParams((RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject());

        //�����P�ʂh�c(order_unit_id):���������ɂ��o�^���ꂽ�敨OP�����P�ʃI�u�W�F�N�g.�����P��ID
        l_rsvIfoOrderParams.setOrderUnitId(l_orderUnit.getOrderUnitId());

        //���������ŏI�ʔ�(last_order_action_serial_no):�i�����l�j�{�P
        l_rsvIfoOrderParams.setLastOrderActionSerialNo(l_rsvIfoOrderParams.getLastOrderActionSerialNo() + 1);

        //�������(order_status):3:�����ρi�V�K�����j�iOrderStatusEnum�ɂĒ�`�j
        l_rsvIfoOrderParams.setOrderStatus(OrderStatusEnum.ORDERED);

        //�����L�����(order_open_status):2:�N���[�Y�iOrderOpenStatusEnum�ɂĒ�`�j
        l_rsvIfoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        //�����敪(expiration_status):2:�I���iOrderExpirationStatusEnum�ɂĒ�`�j
        l_rsvIfoOrderParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);

        //�X�V���t(last_updated_timestamp):���ݓ���
        l_rsvIfoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderParams);
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

        //�R�j�@@�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�����̗\�񒍕��P��.����ID)���R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUpdateService.insertReserveOrderAction(l_rsvIfoOrderUnit.getOrderId());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�\�񒍕��ݒ�To�����e����)<BR>
     * EQTYPE�̐e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �P�j�@@�����̐e�����̒����P�ʂ��Aclone�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �Q�j�@@��������clone�I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���ăZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�\�񒍕��ݒ�t���O�F�@@"�ݒ�̉\������"<BR>
     * �@@�@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �R�j�@@EQTYPE�̊g�����������}�l�[�W��.update�����f�[�^()�ɂ��A<BR>
     * �@@�@@�@@�@@�@@�e�����̒����P�ʂ�update����B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��update�����f�[�^()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�����P�ʁF�@@�쐬���������P�ʂ�clone�I�u�W�F�N�g <BR>
     * �@@is�����쐬�F�@@false�i�������쐬���Ȃ��j <BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �S�j�@@return����B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʁB
     * @@throws WEB3BaseException
     * @@roseuid 432A23490069
     */
    protected void setReserveOrderSettingToEqtypeParentOrder(
        EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setReserveOrderSettingToEqtypeParentOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        //�P�j�@@�����̐e�����̒����P�ʂ��Aclone�I�u�W�F�N�g���쐬����B
        EqtypeOrderUnitParams l_eqOrderUnitParams = 
            new EqtypeOrderUnitParams((EqtypeOrderUnitRow)l_parentOrderUnit.getDataSourceObject());  
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        EqTypeOrderUnit l_cloneEqOrderUnit = 
            (EqTypeOrderUnit)l_orderMgr.toOrderUnit(l_eqOrderUnitParams);                      
        
        //�Q�j�@@��������clone�I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���ăZ�b�g����B
        //�\�񒍕��ݒ�t���O�F�@@"�ݒ�̉\������"
        //�X�V���t�F�@@GtlUtils.getSystemTimestamp() 
        l_eqOrderUnitParams.setReserveOrderExistFlag(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);
        l_eqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�R�j�@@EQTYPE�̊g�����������}�l�[�W��.update�����f�[�^()�ɂ��A
        //�e�����̒����P�ʂ�update����B            
        l_orderMgr.updateOrderData(l_cloneEqOrderUnit, false);
        
        //�S�j�@@return����B
        log.exiting(STR_METHOD_NAME); 
        return;        
    }

    /**
     * (set�\�񒍕��ݒ�To�敨OP�e����)<BR>
     * �e�����̒����P�ʂɁA�\�񒍕��o�^���L�^����B<BR>
     * <BR>
     * �P�j�@@�����̐e�����̒����P�ʂ��Aclone�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �Q�j�@@��������clone�I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���ăZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�@@�\�񒍕��ݒ�t���O�F�@@"�ݒ�̉\������"<BR>
     * �@@�@@�@@�@@�X�V���t�F�@@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �R�j�@@OP�����}�l�[�W��.update�����f�[�^()�ɂ��A<BR>
     * �@@�@@�@@�@@�@@�e�����̒����P�ʂ�update����B<BR>
     * <BR>
     * �@@-------------------------------------------------------------<BR>
     * �@@��update�����f�[�^()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�����P�ʁF�@@�쐬���������P�ʂ�clone�I�u�W�F�N�g<BR>
     * �@@is�����쐬�F�@@false�i�������쐬���Ȃ��j<BR>
     * �@@-------------------------------------------------------------<BR>
     * <BR>
     * �S�j�@@return����B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P��<BR>
     * @@throws WEB3BaseException
     */
    public void setReserveOrderSettingToIfoParentOrder(
        IfoOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setReserveOrderSettingToIfoParentOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�����̒����P�� = null�B");
        }

        //�P�j�@@�����̐e�����̒����P�ʂ��Aclone�I�u�W�F�N�g���쐬����B
        IfoOrderUnitParams l_ifoOrderUnitParams =
            new IfoOrderUnitParams((IfoOrderUnitRow)l_parentOrderUnit.getDataSourceObject());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        IfoOrderUnit l_cloneIfoOrderUnit =
            (IfoOrderUnit)l_orderMgr.toOrderUnit(l_ifoOrderUnitParams);

        //�Q�j�@@��������clone�I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���ăZ�b�g����B
        //�\�񒍕��ݒ�t���O�F�@@"�ݒ�̉\������"
        //�X�V���t�F�@@GtlUtils.getSystemTimestamp()
        l_ifoOrderUnitParams.setReserveOrderExistFlag(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�R�j�@@OP�����}�l�[�W��.update�����f�[�^()�ɂ��A
        //�e�����̒����P�ʂ�update����B
        l_orderMgr.updateOrderData(l_cloneIfoOrderUnit, false);

        //�S�j�@@return����B
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (create����)<BR>
     * �����̒����P�ʂ�茚���i���z�j���쐬����B<BR>
     * <BR>
     * �����Δ������Ɏg�p����B<BR>
     * �@@��L�ȊO�̏ꍇ�̎g�p�͋֎~�B<BR>
     * <BR>
     * �P�j�@@����Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@����ID = 0<BR>
     * �@@����ID = �����P�ʂ̓�����<BR>
     * �@@�⏕����ID = �����P�ʂ̓�����<BR>
     * �@@�s��ID = �����P�ʂ̓�����<BR>
     * �@@�������� = �����P��.��������<BR>
     * �@@������ = �����P��.��������<BR>
     * �@@�����P�� = �����P��.�����P��<BR>
     * �@@���P�� = �����P��.�����P��<BR>
     * �@@���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����"<BR>
     * �@@�@@�ȊO�A"����"<BR>
     * �@@���� = �����P��.������<BR>
     * �@@���� = �����|�W�V�����}�l�[�W��.get��������(����, �����P��.�ٍϊ����l)<BR>
     * �@@�����萔�� = (*1)<BR>
     * �@@���萔�� = (*1)<BR>
     * �@@�����萔������� = (*1)<BR>
     * �@@���萔������� = (*1)<BR>
     * �@@���`������ = 0<BR>
     * �@@���`����������� = 0<BR>
     * �@@�Ǘ��� = 0<BR>
     * �@@�Ǘ������� = 0<BR>
     * �@@������ = 0<BR>
     * �@@����������� = 0<BR>
     * �@@�t���� = 0<BR>
     * �@@�t��������� = 0<BR>
     * �@@�݊��� = 0<BR>
     * �@@���̑� = 0<BR>
     * �@@�ۏ؋��� = 0<BR>
     * �@@�����ۏ؋��� = 0<BR>
     * �@@�����]�����v = 0<BR>
     * �@@����ID = �����P�ʂ̓�����<BR>
     * �@@�����^�C�v = �����P�ʂ̓�����<BR>
     * �@@�ŋ敪 = �����P�ʂ̓�����<BR>
     * �@@�ٍϋ敪 = �����P�ʂ̓�����<BR>
     * �@@�ٍϊ����l = �����P�ʂ̓�����<BR>
     * �@@�쐬���t = GtlUtils.getSystemTimestamp()<BR>
     * �@@�X�V���t = GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �@@(*1)�ȉ��̎菇�ɂĐV�K���萔�����擾����B<BR>
     * �@@�@@�@@�萔���I�u�W�F�N�g���쐬����B<BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[create�萔��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@����ID�F�@@�����P��.����ID<BR>
     * <BR>
     * �@@�@@�A�쐬�����萔���I�u�W�F�N�g�ɏ��o��v�Z�p�����<BR>
     * �@@�@@�@@�Z�b�g����B<BR>
     * �@@�@@�@@�萔��.set���o��v�Z�p���()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[set���o��v�Z�p���()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@���o��v�Z�p����F�@@�����P��.�T�Z��n���<BR>
     * <BR>
     * �@@�@@�B�ϑ��萔�����Z�o����B<BR>
     * �@@�@@�@@�����v�Z�T�[�r�X.calc�ϑ��萔��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[calc�ϑ��萔��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�����P��.�⏕����ID�ɊY������⏕����<BR>
     * <BR>
     * �@@�@@�C�B�̖߂�l���g�p���A�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�����萔���A���萔�� = �B�̖߂�l.�萔�����z<BR>
     * �@@�@@�@@�����萔������ŁA���萔������� = �����v�Z�T�[�r�X.calc�����()<BR>
     * <BR>
     * �@@�@@�@@[calc�����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@���z�F�@@�B�̖߂�l.�萔�����z<BR>
     * �@@�@@�@@�@@����F�@@�����P��.������<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�B�ɂĎ擾�����⏕����<BR>
     * <BR>
     * �R�j�@@�������쐬����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�v���p�e�B�Z�b�g�����C���X�^���X<BR>
     * <BR>
     * �S�j�@@�쐬����������ԋp����B<BR>
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 4329526402DD
     */
    public WEB3EquityContract createContract(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createContract(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_parentOrderUnit == null )
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        //�P�j�@@����Params�C���X�^���X�𐶐�����B 
        EqtypeContractParams l_eqContractParams = new EqtypeContractParams();

        //�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_parentOrderUnit.getDataSourceObject();
        
        //  ����ID = 0 
        l_eqContractParams.setContractId(0);
        
        //  ����ID = �����P�ʂ̓����� 
        l_eqContractParams.setAccountId(l_parentOrderUnit.getAccountId());
        
        //  �⏕����ID = �����P�ʂ̓����� 
        l_eqContractParams.setSubAccountId(l_parentOrderUnit.getSubAccountId());
        
        //  �s��ID = �����P�ʂ̓����� 
        l_eqContractParams.setMarketId(l_eqOrderUnitRow.getMarketId());        
        
        //  �������� = �����P��.�������� 
        l_eqContractParams.setOriginalQuantity(l_parentOrderUnit.getQuantity());
        
        //  ������ = �����P��.�������� 
        l_eqContractParams.setQuantity(l_parentOrderUnit.getQuantity());
        
        //  �����P�� = �����P��.�����P�� 
        l_eqContractParams.setOriginalContractPrice(l_eqOrderUnitRow.getPrice());                
        
        //  ���P�� = �����P��.�����P�� 
        l_eqContractParams.setContractPrice(l_eqOrderUnitRow.getPrice());          
        
        //  ���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����" 
        if (SideEnum.BUY.equals(l_parentOrderUnit.getSide()))
        {
            l_eqContractParams.setContractType(ContractTypeEnum.LONG);      
        }
        //    �ȊO�A"����" 
        else
        {
            l_eqContractParams.setContractType(ContractTypeEnum.SHORT);
        }      
        
        //  ���� = �����P��.������ 
        Date l_datBizDate = WEB3DateUtility.getDate(l_eqOrderUnitRow.getBizDate(), "yyyyMMdd");
        l_eqContractParams.setOpenDate(l_datBizDate);
        
        //  ���� = �����|�W�V�����}�l�[�W��.get��������(����, �����P��.�ٍϊ����l) 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        Date l_datCloseDate = l_positionManager.getContractCloseDate(
            l_eqContractParams.getOpenDate(), l_eqOrderUnitRow.getRepaymentNum());
        
        l_eqContractParams.setCloseDate(l_datCloseDate);
        
        //  (*1)�ȉ��̎菇�ɂĐV�K���萔�����擾����B 
        //    �@@�萔���I�u�W�F�N�g���쐬����B 
        //      �����v�Z�T�[�r�X.create�萔��()���R�[������B 
        //
        //      [create�萔��()�Ɏw�肷�����] 
        //        ����ID�F�@@�����P��.����ID 
        WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission = 
            l_eqBizLogicProvider.createCommission(l_parentOrderUnit.getOrderId());        
        
        //    �A�쐬�����萔���I�u�W�F�N�g�ɏ��o��v�Z�p����� 
        //      �Z�b�g����B 
        //      �萔��.set���o��v�Z�p���()���R�[������B 
        //
        //      [set���o��v�Z�p���()�Ɏw�肷�����] 
        //        ���o��v�Z�p����F�@@�����P��.�T�Z��n��� 
        l_commission.setExpensesCalcAmount(l_eqOrderUnitRow.getEstimatedPrice());

        //    �B�ϑ��萔�����Z�o����B 
        //      �����v�Z�T�[�r�X.calc�ϑ��萔��()���R�[������B 
        //
        //      [calc�ϑ��萔��()�Ɏw�肷�����] 
        //        �萔���F�@@�쐬�����萔���I�u�W�F�N�g 
        //        �⏕�����F�@@�����P��.�⏕����ID�ɊY������⏕���� 
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_parentOrderUnit.getAccountId(),
                    l_parentOrderUnit.getSubAccountId());
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
        
        l_eqBizLogicProvider.calcCommission(l_commission, l_subAccount);

        //    �C�B�̖߂�l���g�p���A�v���p�e�B���Z�b�g����B 
        //      �����萔���A���萔�� = �B�̖߂�l.�萔�����z 
        //      �����萔������ŁA���萔������� = �����v�Z�T�[�r�X.calc�����() 
        //
        //      [calc�����()�Ɏw�肷�����] 
        //        ���z�F�@@�B�̖߂�l.�萔�����z 
        //        ����F�@@�����P��.������ 
        //        �⏕�����F�@@�B�ɂĎ擾�����⏕����         
        
        //  �����萔�� = (*1)
        double l_dblCommission = l_commission.getCommission(); 
        l_eqContractParams.setOriginalSetupFee(l_dblCommission);
        
        //  ���萔�� = (*1) 
        l_eqContractParams.setSetupFee(l_dblCommission);
        
        //  �����萔������� = (*1) 
        double l_dblSalesTax = l_eqBizLogicProvider.calcSalesTax(
            l_dblCommission, 
            new Timestamp(l_datBizDate.getTime()), 
            l_subAccount);
        l_eqContractParams.setOriginalSetupFeeTax(l_dblSalesTax);
        
        //  ���萔������� = (*1) 
        l_eqContractParams.setSetupFeeTax(l_dblSalesTax);
        
        //  ���`������ = 0
        l_eqContractParams.setNameTransferFee(0);
         
        //  ���`����������� = 0 
        l_eqContractParams.setNameTransferFeeTax(0);
        
        //  �Ǘ��� = 0 
        l_eqContractParams.setManagementFee(0);
        
        //  �Ǘ������� = 0 
        l_eqContractParams.setManagementFeeTax(0);
        
        //  ������ = 0 
        l_eqContractParams.setInterestFee(0);
        
        //  ����������� = 0 
        l_eqContractParams.setInterestFeeTax(0);
        
        //  �t���� = 0 
        l_eqContractParams.setPayInterestFee(0);
        
        //  �t��������� = 0 
        l_eqContractParams.setPayInterestFeeTax(0);
        
        //  �݊��� = 0 
        l_eqContractParams.setLoanEquityFee(0);
        
        //  ���̑� = 0 
        l_eqContractParams.setOther(0);
        
        //  �ۏ؋��� = 0 
        l_eqContractParams.setMarginDepositRate(0);
        
        //  �����ۏ؋��� = 0 
        l_eqContractParams.setCashMarginDepositRate(0);
        
        //  �����]�����v = 0
        l_eqContractParams.setContractAssetProfitLoss(0);         
        
        //  ����ID = �����P�ʂ̓����� 
        l_eqContractParams.setProductId(l_eqOrderUnitRow.getProductId());
        
        //  �����^�C�v = �����P�ʂ̓�����
        l_eqContractParams.setProductType(l_eqOrderUnitRow.getProductType());
         
        //  �ŋ敪 = �����P�ʂ̓�����
        l_eqContractParams.setTaxType(l_parentOrderUnit.getTaxType());
         
        //  �ٍϋ敪 = �����P�ʂ̓�����
        l_eqContractParams.setRepaymentType(l_eqOrderUnitRow.getRepaymentType());
         
        //  �ٍϊ����l = �����P�ʂ̓����� 
        if (!l_eqOrderUnitRow.getRepaymentNumIsNull())
        {
            l_eqContractParams.setRepaymentNum(l_eqOrderUnitRow.getRepaymentNum());
        }        
        
        //  �쐬���t = GtlUtils.getSystemTimestamp() 
        l_eqContractParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //  �X�V���t = GtlUtils.getSystemTimestamp() 
        l_eqContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�R�j�@@�������쐬����B 
        //
        //  [�R���X�g���N�^�Ɏw�肷�����] 
        //    arg0�F�@@�v���p�e�B�Z�b�g�����C���X�^���X 
        WEB3EquityContract l_eqContract = new WEB3EquityContract(l_eqContractParams);

        //�S�j�@@�쐬����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_eqContract;        
    }

    /**
     * (create����)<BR>
     * �����̒����P�ʂ�茚�ʁi���z�j���쐬����B<BR>
     * <BR>
     * �����Δ������Ɏg�p����B<BR>
     * �@@��L�ȊO�̏ꍇ�̎g�p�͋֎~�B<BR>
     * <BR>
     * �P�j�@@�����P�ʂ���敨OP��������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@����ID = 0<BR>
     * �@@����ID = �����P�ʂ̓�����<BR>
     * �@@�⏕����ID = �����P�ʂ̓�����<BR>
     * �@@�s��ID = �����P�ʂ̓�����<BR>
     * �@@1�P�ʓ���搔 = �P�j�Ŏ擾�����敨OP�������.getUnitSize()<BR>
     * �@@���ʌ����� = �����P��.��������<BR>
     * �@@���ʐ��� = �����P��.��������<BR>
     * �@@�����P�� = �����P��.�����P��<BR>
     * �@@���P�� = �����P��.�����P��<BR>
     * �@@���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����"<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ȊO�A"����"<BR>
     * �@@���� = �����P��.������<BR>
     * �@@���� = �P�j�Ŏ擾�����敨OP�������.getLastTradingDate()<BR>
     * �@@���ϑ��萔�� = (*1)<BR>
     * �@@���ϑ��萔������� = (*1)<BR>
     * �@@�Ǘ��� = 0<BR>
     * �@@�Ǘ������� = 0<BR>
     * �@@���q = 0<BR>
     * �@@���q����� = 0<BR>
     * �@@����ID = �����P�ʂ̓�����<BR>
     * �@@�����^�C�v = �����P�ʂ̓�����<BR>
     * �@@�쐬���t = GtlUtils.getSystemTimestamp()<BR>
     * �@@�X�V���t = GtlUtils.getSystemTimestamp()<BR>
     * �@@��n�� = �����P�ʂ̓�����<BR>
     * �@@����敪 = �����P�ʂ̓�����<BR>
     * <BR>
     * <BR>
     * �@@(*1)�ȉ��̎菇�ɂĐV�K���萔�����擾����B<BR>
     * �@@�@@�@@�萔���I�u�W�F�N�g���쐬����B<BR>
     * �@@�@@�@@�敨OP�v�Z�T�[�r�X.create�萔��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�@@�����P��ID�F�@@�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@�A�쐬�����萔���I�u�W�F�N�g�ɏ��o��v�Z�p�����<BR>
     * �@@�@@�@@�Z�b�g����B<BR>
     * �@@�@@�@@�萔��.set���o��v�Z�p���()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�@@���o��v�Z�p����F<BR>
     * �@@�@@�@@�@@�@@�����P��.�������� �~ �����P��.�����P�� �~ �P�j<BR>
     * �@@�@@�@@�@@�@@�Ŏ擾�����敨OP�������.getUnitSize()<BR>
     * <BR>
     * �@@�@@�B�ϑ��萔�����Z�o����B<BR>
     * �@@�@@�@@�敨OP�v�Z�T�[�r�X.calc�ϑ��萔��()���R�[������B<BR>
     * <BR>
     * �@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�@@�萔���F�@@�쐬�����萔���I�u�W�F�N�g<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�����P��.�⏕����ID�ɊY������⏕����<BR>
     * <BR>
     * �@@�@@�C�B�̖߂�l���g�p���A�v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@���ϑ��萔�� = �B�̖߂�l.�萔�����z<BR>
     * �@@�@@�@@���ϑ��萔������� = �敨OP�v�Z�T�[�r�X.calc�����()<BR>
     * <BR>
     * �@@�@@�@@[�w�肷�����]<BR>
     * �@@�@@�@@�@@���z�F�@@�B�̖߂�l.�萔�����z<BR>
     * �@@�@@�@@�@@����F�@@�����P��.������<BR>
     * �@@�@@�@@�@@�⏕�����F�@@�B�ɂĎ擾�����⏕����<BR>
     * <BR>
     * �S�j�@@���ʂ��쐬����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�v���p�e�B�Z�b�g�����C���X�^���X<BR>
     * <BR>
     * �T�j�@@�쐬�������ʂ�ԋp����B<BR>
     * @@param l_parentOrderUnit - (�����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoContractImpl createIfoContract(IfoOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoContract(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_parentOrderUnit == null )
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�����̒����P�� = null�B");
        }

        //�P�j�@@�����P�ʂ���敨OP��������I�u�W�F�N�g�𐶐�����B
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
            (WEB3IfoTradedProductImpl)l_parentOrderUnit.getTradedProduct();

        //�Q�j�@@����Params�C���X�^���X�𐶐�����B
        IfoContractParams l_ifoContractParams = new IfoContractParams();
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_parentOrderUnit.getDataSourceObject();

        //�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //����ID = 0
        l_ifoContractParams.setContractId(0L);

        //����ID = �����P�ʂ̓�����
        l_ifoContractParams.setAccountId(l_parentOrderUnit.getAccountId());

        //�⏕����ID = �����P�ʂ̓�����
        l_ifoContractParams.setSubAccountId(l_parentOrderUnit.getSubAccountId());

        //�s��ID = �����P�ʂ̓�����
        l_ifoContractParams.setMarketId(l_ifoOrderUnitRow.getMarketId());

        //1�P�ʓ���搔 = �P�j�Ŏ擾�����敨OP�������.getUnitSize()
        l_ifoContractParams.setUnitSize(l_ifoTradedProductImpl.getUnitSize());

        //���ʌ����� = �����P��.��������
        l_ifoContractParams.setOriginalQuantity(l_parentOrderUnit.getQuantity());

        //���ʐ��� = �����P��.��������
        l_ifoContractParams.setQuantity(l_parentOrderUnit.getQuantity());

        //�����P�� = �����P��.�����P��
        l_ifoContractParams.setOriginalContractPrice(l_ifoOrderUnitRow.getPrice());

        //���P�� = �����P��.�����P��
        l_ifoContractParams.setContractPrice(l_ifoOrderUnitRow.getPrice());

        //���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����"
        //�ȊO�A"����"
        if (SideEnum.BUY.equals(l_parentOrderUnit.getSide()))
        {
            l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
        }
        else
        {
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        }

        //���� = �����P��.������
        Date l_datBizDate = WEB3DateUtility.getDate(
            l_ifoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        l_ifoContractParams.setOpenDate(l_datBizDate);

        //���� = �P�j�Ŏ擾�����敨OP�������.getLastTradingDate()
        l_ifoContractParams.setCloseDate(l_ifoTradedProductImpl.getLastTradingDate());

        //(*1)�ȉ��̎菇�ɂĐV�K���萔�����擾����B
        //�@@�萔���I�u�W�F�N�g���쐬����B
        //�敨OP�v�Z�T�[�r�X.create�萔��()���R�[������B
        //[�w�肷�����]
         //�����P��ID�F�@@�����P��.�����P��ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider =
            (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_ifoBizLogicProvider.createCommission(l_parentOrderUnit.getOrderUnitId());

        //�A�쐬�����萔���I�u�W�F�N�g�ɏ��o��v�Z�p�����
        //�Z�b�g����B
        //�萔��.set���o��v�Z�p���()���R�[������B
        //[�w�肷�����]
        //���o��v�Z�p����F
        //�����P��.�������� �~ �����P��.�����P�� �~ �P�j�Ŏ擾�����敨OP�������.getUnitSize()
        BigDecimal l_bdQuantity = new BigDecimal("" + l_parentOrderUnit.getQuantity());
        BigDecimal l_bdPrice = new BigDecimal("" + l_ifoOrderUnitRow.getPrice());
        BigDecimal l_bdUnitSize = new BigDecimal("" + l_ifoTradedProductImpl.getUnitSize());
        double l_dblSetupFee = l_bdQuantity.multiply(l_bdPrice).multiply(l_bdUnitSize).doubleValue();
        l_commission.setExpensesCalcAmount(l_dblSetupFee);

        //�B�ϑ��萔�����Z�o����B
        //�敨OP�v�Z�T�[�r�X.calc�ϑ��萔��()���R�[������B
        //[�w�肷�����]
        //�萔���F�@@�쐬�����萔���I�u�W�F�N�g
        //�⏕�����F�@@�����P��.�⏕����ID�ɊY������⏕����
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_parentOrderUnit.getAccountId(),
                    l_parentOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�⏕�����e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);

        //�C�B�̖߂�l���g�p���A�v���p�e�B���Z�b�g����B
        //���ϑ��萔�� = �B�̖߂�l.�萔�����z
        //���ϑ��萔������� = �敨OP�v�Z�T�[�r�X.calc�����()
        //[�w�肷�����]
        //���z�F�@@�B�̖߂�l.�萔�����z
        //����F�@@�����P��.������
        //�⏕�����F�@@�B�ɂĎ擾�����⏕����

        //���ϑ��萔�� = (*1)
        double l_dblCommission = l_commission.getCommission();
        l_ifoContractParams.setSetupFee(l_dblCommission);

        //���ϑ��萔������� = (*1)
        double l_dblSetupFeeTax = l_ifoBizLogicProvider.calcSalesTax(
            l_dblCommission, new Timestamp(l_datBizDate.getTime()), l_subAccount);
        l_ifoContractParams.setSetupFeeTax(l_dblSetupFeeTax);

        //�Ǘ��� = 0
        l_ifoContractParams.setManagementFee(0.0d);

        //�Ǘ������� = 0
        l_ifoContractParams.setManagementFeeTax(0.0d);

        //���q = 0
        l_ifoContractParams.setInterestFee(0.0d);

        //���q����� = 0
        l_ifoContractParams.setInterestFeeTax(0.0d);

        //����ID = �����P�ʂ̓�����
        l_ifoContractParams.setProductId(l_ifoOrderUnitRow.getProductId());

        //�����^�C�v = �����P�ʂ̓�����
        l_ifoContractParams.setProductType(l_parentOrderUnit.getProductType());

        //�쐬���t = GtlUtils.getSystemTimestamp()
        l_ifoContractParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //�X�V���t = GtlUtils.getSystemTimestamp()
        l_ifoContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //��n�� = �����P�ʂ̓�����
        l_ifoContractParams.setDeliveryDate(l_parentOrderUnit.getDeliveryDate());

        //����敪 = �����P�ʂ̓�����
        l_ifoContractParams.setSessionType(l_ifoOrderUnitRow.getSessionType());

        //�S�j�@@���ʂ��쐬����B
        //[�R���X�g���N�^�Ɏw�肷�����]
        //arg0�F�@@�v���p�e�B�Z�b�g�����C���X�^���X
        WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);

        //�T�j�@@�쐬�������ʂ�ԋp����B
        return l_ifoContractImpl;
    }

    /**
     * (create�ۗL���Y)<BR>
     * �����̒����P�ʂ��ۗL���Y���쐬����B<BR>
     * <BR>
     * �����Δ������Ɏg�p����B<BR>
     * �@@��L�ȊO�̏ꍇ�̎g�p�͋֎~�B<BR>
     * <BR>
     * �P�j�@@�ۗL���YParams�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@���YID = 0<BR>
     * �@@����ID = �����P�ʂ̓�����<BR>
     * �@@�⏕����ID = �����P�ʂ̓�����<BR>
     * �@@�����^�C�v = �����P�ʂ̓�����<BR>
     * �@@���� = �����P��.��������<BR>
     * �@@���t�s�\���� = 0<BR>
     * �@@���ʁi�뉿�P���v�Z�p�j = �����P��.��������<BR>
     * �@@�뉿�i�뉿�P���v�Z�p�j = 0<BR>
     * �@@���͕뉿�P�� = null<BR>
     * �@@�뉿�P�����͓��� = null<BR>
     * �@@���`������ = 0<BR>
     * �@@���`����������� = 0<BR>
     * �@@�����Ǘ��� = 0<BR>
     * �@@�����Ǘ������� = 0<BR>
     * �@@����ID = �����P�ʂ̓�����<BR>
     * �@@�ŋ敪 = �����P�ʂ̓�����<BR>
     * �@@�~�j���敪 = "DEFAULT�i�~�j���łȂ��j"<BR>
     * �@@���z�� = 0<BR>
     * �@@30�����o�ߎc������ = 0<BR>
     * �@@�쐬���t = GtlUtils.getSystemTimestamp()<BR>
     * �@@�X�V���t = GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * �R�j�@@�ۗL���Y���쐬����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�v���p�e�B�Z�b�g�����C���X�^���X<BR>
     * <BR>
     * �S�j�@@�쐬�����ۗL���Y��ԋp����B
     * @@param l_parentOrderUnit - (�e�����̒����P��)<BR>
     * �e�����̒����P�ʃI�u�W�F�N�g�B
     * @@return WEB3EquityAsset
     * @@throws WEB3BaseException
     * @@roseuid 4329599D009A
     */
    public WEB3EquityAsset createAsset(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAsset(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_parentOrderUnit == null)
        {
            log.debug("�e�����̒����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�e�����̒����P�� = null�B");
        }
        
        //�P�j�@@�ۗL���YParams�C���X�^���X�𐶐�����B
        AssetParams l_assetParams = new AssetParams();
        
        Product l_product = l_parentOrderUnit.getProduct();
        
        //�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        //  ���YID = 0 
        l_assetParams.setAssetId(0);
        
        //  ����ID = �����P�ʂ̓����� 
        l_assetParams.setAccountId(l_parentOrderUnit.getAccountId());
        
        //  �⏕����ID = �����P�ʂ̓����� 
        l_assetParams.setSubAccountId(l_parentOrderUnit.getSubAccountId());
        
        //  �����^�C�v = �����P�ʂ̓����� 
        l_assetParams.setProductType(l_product.getProductType());
        
        //  ���� = �����P��.�������� 
        l_assetParams.setQuantity(l_parentOrderUnit.getQuantity());
        
        //  ���t�s�\���� = 0 
        l_assetParams.setQuantityCannotSell(0);
        
        //  ���ʁi�뉿�P���v�Z�p�j = �����P��.�������� 
        l_assetParams.setQuantityForBookValue(l_parentOrderUnit.getQuantity());
        
        //  �뉿�i�뉿�P���v�Z�p�j = 0 
        l_assetParams.setBookValue(0);
        
        //  ���͕뉿�P�� = null         
        //  �뉿�P�����͓��� = null 
        
        //  ���`������ = 0 
        l_assetParams.setSetupFee(0);
        
        //  ���`����������� = 0 
        l_assetParams.setSetupFeeTax(0);
        
        //  �����Ǘ��� = 0 
        l_assetParams.setManagementFee(0);
        
        //  �����Ǘ������� = 0 
        l_assetParams.setManagementFeeTax(0);
        
        //  ����ID = �����P�ʂ̓����� 
        l_assetParams.setProductId(l_product.getProductId());
        
        //  �ŋ敪 = �����P�ʂ̓����� 
        l_assetParams.setTaxType(l_parentOrderUnit.getTaxType());
        
        //  �~�j���敪 = "DEFAULT�i�~�j���łȂ��j" 
        l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        
        //  ���z�� = 0 
        l_assetParams.setProfitDistribution(0);
        
        //  30�����o�ߎc������ = 0 
        l_assetParams.setCountBeforePenalty(0);
        
        //  �쐬���t = GtlUtils.getSystemTimestamp() 
        l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //  �X�V���t = GtlUtils.getSystemTimestamp() 
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�R�j�@@�ۗL���Y���쐬����B 
        //
        //  [�R���X�g���N�^�Ɏw�肷�����] 
        //    arg0�F�@@�v���p�e�B�Z�b�g�����C���X�^���X 
        WEB3EquityAsset l_eqAsset = new WEB3EquityAsset(l_assetParams);
        
        log.exiting(STR_METHOD_NAME); 
        return l_eqAsset;
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ���ό����G���g���̔z����쐬����B<BR>
     * <BR>
     * EqtypeSettleContractOrderEntry�C���X�^���X��<BR>
     * �쐬���A���̃C���X�^���X�݂̂�v�f�Ƃ���z���ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@0(�Œ�)<BR>
     * �@@arg1�F�@@�p�����[�^.���ό������׈ꗗ[0].����<BR>
     * @@param l_marginCloseMarginContractUnitList - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 43313EE402FF
     */
    public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_marginCloseMarginContractUnitList) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createClosingContractEntry(WEB3MarginCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME); 
        
        if (l_marginCloseMarginContractUnitList == null || 
            l_marginCloseMarginContractUnitList.length == 0)
        {
            log.debug("���ό������׈ꗗ = null or length = 0�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���ό������׈ꗗ = null or length = 0�B");
        }
        
        //EqtypeSettleContractOrderEntry�C���X�^���X�� 
        //�쐬���A���̃C���X�^���X�݂̂�v�f�Ƃ���z���ԋp����B
        EqTypeSettleContractOrderEntry l_eqTypeSettleContractOrderEntry = new EqTypeSettleContractOrderEntry(
            0, 
            Double.parseDouble(l_marginCloseMarginContractUnitList[0].orderQuantity));
                    
        log.exiting(STR_METHOD_NAME);             
        return new EqTypeSettleContractOrderEntry[]{l_eqTypeSettleContractOrderEntry};
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g���̔z����쐬����B<BR>
     * <BR>
     * SettleContractEntry�C���X�^���X��<BR>
     * <BR>
     * �쐬���A���̃C���X�^���X�݂̂�v�f�Ƃ���z���ԋp����B<BR>
     * <BR>
     * [�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@arg0�F�@@0(�Œ�)<BR>
     * �@@arg1�F�@@�p�����[�^.�ԍό���[0].����<BR>
     * @@param l_CloseMarginContractUnit  - (�ԍό���)<BR>
     * �ԍό��ʃI�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     */
    public SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesOptionsCloseMarginContractUnit[] l_CloseMarginContractUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry(WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_CloseMarginContractUnit == null || l_CloseMarginContractUnit.length == 0)
        {
            log.debug("�ԍό��� = null or length = 0�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ԍό��� = null or length = 0�B");
        }

        //SettleContractEntry�C���X�^���X��
        //�쐬���A���̃C���X�^���X�݂̂�v�f�Ƃ���z���ԋp����B
        SettleContractEntry l_settleContractEntry = new SettleContractEntry(
            0, Double.parseDouble(l_CloseMarginContractUnit[0].contractOrderQuantity));

        log.exiting(STR_METHOD_NAME);
        return new SettleContractEntry[]{l_settleContractEntry};
    }

    /**
     * (create��������ByOrder)<BR>
     * �����Ɋ֘A�����M�p����������ׁi�Ɖ�p�j��z��Ŏ擾����B<BR>
     * �w�蒍�����V�K�������̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A�������jcreate��������ByOrder�v�Q�ƁB<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 433A4AC202BF
     */
    public WEB3MarginContractUnit[] createContractUnitByOrder(
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createContractUnitByOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񒍕��P�� = null�B");
        }
        
        //1.1:�V�K�������i�\�񒍕��P��.�����J�e�S�� == "�V�K������"�j�̏ꍇ
        if (OrderCategEnum.OPEN_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            //�������I������B
            log.exiting(STR_METHOD_NAME);
            return null;
        }
            
        //1.2:ArrayList( )
        //�M�p����������ׂ��i�[����ArrayList�𐶐�����B
        List l_lisMarginContractUnits = new ArrayList();
        
        //1.3: is���Δ������( )
        boolean l_blnIsRerverseTrade = l_rsvEqOrderUnit.isReversingTrade();
        
        //1.4:���Δ����iis���Δ������()�̖߂�l == true�j�̏ꍇ
        if (l_blnIsRerverseTrade)
        {
            //1.4.1:get�e�����̒����P��( )
            EqTypeOrderUnit l_eqOrderUnit = l_rsvEqOrderUnit.getParentOrderUnit();
            
            //1.4.2:create��������(�����P��)
            WEB3MarginContractUnit l_marginContractUnit = this.createMarginContractUnit(l_eqOrderUnit);
            
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow =
                (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            // �\�񒍕��P��.���Ϗ����敪 == "�����_��"�̏ꍇ
            if (WEB3ClosingOrderDef.RANDOM.equals(l_rsvEqOrderUnitRow.getClosingOrderType()))
            {
                l_marginContractUnit.orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getQuantity());
            }
            
            //1.4.3:add(arg0 : Object)
            l_lisMarginContractUnits.add(l_marginContractUnit);
        }
        //1.5:(*)���Δ����łȂ��ꍇ
        else
        {
            //1.5.1:get�����\�񌚊��ԍώw����ꗗ( )
            RsvEqClosingContractSpecRow[] l_rsvEqClosingContractSpecRows = 
                l_rsvEqOrderUnit.getContractsToClose();
                
            //1.5.2:get�����\�񌚊��ԍώw����ꗗ()�̖߂�l�̗v�f����Loop����
            int l_intCnt = 0;
            if (l_rsvEqClosingContractSpecRows != null && 
                l_rsvEqClosingContractSpecRows.length > 0)
            {
                l_intCnt = l_rsvEqClosingContractSpecRows.length;
            }
            
            for (int i = 0; i < l_intCnt; i++)
            {
                RsvEqClosingContractSpecRow l_rsvEqClosingContractSpecRow = 
                    l_rsvEqClosingContractSpecRows[i];
                
                //1.5.2.1:get����(����ID : long)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityPositionManager l_positionManager =
                    (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                
                WEB3EquityContract l_contract = null;    
                try
                {
                    l_contract = 
                        (WEB3EquityContract) l_positionManager.getContract(l_rsvEqClosingContractSpecRow.getContractId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�����e�[�u���ɊY������f�[�^������܂���B", l_ex);

                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                
                //1.5.2.2:�������ύσ`�F�b�N
                //(*)�������ύσ`�F�b�N
                //get����()�̖߂�l.������ < �����Ώۂ̗v�f.�ԍϒ������ʂ̏ꍇ�Anull��ԋp���ďI������B
                if (l_contract.getQuantity() < l_rsvEqClosingContractSpecRow.getQuantity())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                
                //1.5.2.3:get�����(���� : double)
                double l_dblContractAmount = 
                    l_contract.getContractAmount(l_rsvEqClosingContractSpecRow.getQuantity());
                
                //1.5.2.4:getTradedProduct( )
                TradedProduct l_tradedProduct = l_contract.getTradedProduct();
                
                //1.5.2.5:get����(������� : EqTypeTradedProduct)
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                
                double l_dblCalcPrice = 
                    l_productManager.getCurrentPrice((EqTypeTradedProduct) l_tradedProduct);
                
                //1.5.2.6:get�]�����v�i�������o��l���j(�v�Z�P�� : double, ���� : double)
                double l_dblAppraisalProfitOrLoss = l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCalcPrice, 
                    l_rsvEqClosingContractSpecRow.getQuantity());
                
                //1.5.2.7:�M�p�����������( )
                WEB3MarginContractUnit l_marginContractUnit = new WEB3MarginContractUnit();
                
                //1.5.2.8:�v���p�e�B�Z�b�g
                //ID      ���@@����.����ID
                l_marginContractUnit.id = WEB3StringTypeUtility.formatNumber(l_contract.getContractId());
                
                //����      ���@@����.����
                l_marginContractUnit.openDate = l_contract.getOpenDate();
                
                //���P��     ���@@����.���P��
                l_marginContractUnit.contractPrice = 
                    WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
                
                //������     ���@@����.������
                l_marginContractUnit.contractQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_contract.getQuantity());
                
                //�����     ���@@get�����()�̖߂�l
                l_marginContractUnit.contractExecPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblContractAmount);
                
                //�]�����v    ���@@get�]�����v�i�������o��l���j()�̖߂�l
                l_marginContractUnit.appraisalProfitLoss = 
                    WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitOrLoss);
                
                //��������    ���@@�����Ώۂ̗v�f.�ԍϒ�������
                l_marginContractUnit.orderQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_rsvEqClosingContractSpecRow.getQuantity());
                
                //���o������   ���@@null
                l_marginContractUnit.partContQuantity = null;
                
                //���Ϗ���    ���@@�����Ώۂ̗v�f.�ԍϘA��
                l_marginContractUnit.settlePriority = 
                    WEB3StringTypeUtility.formatNumber(l_rsvEqClosingContractSpecRow.getClosingSerialNo());
                
                //1.5.2.9:add(arg0 : Object)
                l_lisMarginContractUnits.add(l_marginContractUnit);
            }           
        }
        
        //1.6:toArray( )
        if (l_lisMarginContractUnits.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3MarginContractUnit[] l_marginContractUnits = 
            new WEB3MarginContractUnit[l_lisMarginContractUnits.size()];
            
        l_lisMarginContractUnits.toArray(l_marginContractUnits);
            
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }

    /**
     * (create���ʖ���ByOrder)<BR>
     * �����Ɋ֘A�������ʖ��ׁi�Ɖ�p�j��z��Ŏ擾����B<BR>
     * �w�蒍�����V�K�������̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A�������jcreate���ʖ���ByOrder�v�Q�ƁB<BR>
     * @@param l_rsvIfoOrderUnit  - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P�ʃI�u�W�F�N�g<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FuturesOptionsContractUnit[] createIfoContractUnitByOrder(
        WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoContractUnitByOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg()))
        {
            //�������I������B
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ArrayList( )
        //���ʖ��ׂ��i�[����ArrayList�𐶐�����B
        List l_lisFuturesOptionsContractUnits = new ArrayList();

        //is���Δ������( )
        boolean l_blnIsRerverseTrade = l_rsvIfoOrderUnit.isReversingTrade();

        if (l_blnIsRerverseTrade)
        {
            //get�e�����̒����P��
            IfoOrderUnit l_ifoOrderUnit = l_rsvIfoOrderUnit.getParentOrderUnit();

            //create���ʖ���
            WEB3FuturesOptionsContractUnit l_ifoContractUnit = createContractUnit(l_ifoOrderUnit);

            //�\�񒍕��P��.���Ϗ����敪 == "�����_��"�̏ꍇ
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();

            if (WEB3ClosingOrderDef.RANDOM.equals(l_rsvIfoOrderUnitRow.getClosingOrder()))
            {
                //�ԍϐ���
                l_ifoContractUnit.contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_rsvIfoOrderUnit.getQuantity());
            }

            l_lisFuturesOptionsContractUnits.add(l_ifoContractUnit);
        }
        //���Δ����łȂ��ꍇ
        else
        {
            //get�敨OP�\�񌚋ʕԍώw����ꗗ
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows =
                l_rsvIfoOrderUnit.getContractsToClose();

            int l_intCnt = 0;
            if (l_rsvIfoClosingContractSpecRows != null && l_rsvIfoClosingContractSpecRows.length > 0)
            {
                l_intCnt = l_rsvIfoClosingContractSpecRows.length;
            }

            for (int i = 0; i < l_intCnt; i++)
            {
                RsvIfoClosingContractSpecRow l_rsvIfoClosingContractSpecRow =
                    l_rsvIfoClosingContractSpecRows[i];

                //�敨OP����(���ʂh�c)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoPositionManagerImpl l_positionManager =
                    (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

                WEB3IfoContractImpl l_contract = null;
                try
                {
                    l_contract =
                        (WEB3IfoContractImpl)l_positionManager.getContract(
                            l_rsvIfoClosingContractSpecRow.getContractId());
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
                //�����Ώۂ̗v�f.�ԍϒ�������
                double l_dblQuantity = l_rsvIfoClosingContractSpecRow.getQuantity();

                //�敨OP����.���ʐ��� < �����Ώۂ̗v�f.�ԍϒ������ʂ̏ꍇ�Anull��ԋp���ďI������B
                if (l_contract.getQuantity() < l_dblQuantity)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //get�������
                double l_dblContractAmount =
                    l_contract.getContractExecutedAmount(l_dblQuantity);

                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_contract.getTradedProduct();

                //get�������(�⏕���� : �⏕����)
                WEB3IfoProductQuote l_ifoProductQuote = l_ifoTradedProduct.getCurrentInfo(null);

                //get���萔��(����)
                double l_dblContractCommission = l_contract.getContractCommission(l_dblQuantity);

                //get���萔�������()
                double l_ContractCommissionConsumptionTax =
                    l_contract.getContractCommissionConsumptionTax(l_dblQuantity);

                //get�]�����v(�ԍϒP�� : double, ���� : double)
                double l_dblEvaluateIncome = l_contract.getEvaluateIncome(
                    l_ifoProductQuote.getCurrentPrice(),
                    l_dblQuantity);

                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
                BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
                BigDecimal l_bdContractCommissionConsumptionTax = new BigDecimal(l_ContractCommissionConsumptionTax + "");

                //���ʖ���
                WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit = new WEB3FuturesOptionsContractUnit();

                //�v���p�e�B�Z�b�g
                //ID = ����.����ID
                l_futuresOptionsContractUnit.id = WEB3StringTypeUtility.formatNumber(l_contract.getContractId());

                //���� = ����.����
                l_futuresOptionsContractUnit.openDate = l_contract.getOpenDate();

                //���ʐ� = ����.���ʐ���
                l_futuresOptionsContractUnit.contractQuantity =
                    WEB3StringTypeUtility.formatNumber(l_contract.getQuantity());

                //���P�� = ����.���P��
                l_futuresOptionsContractUnit.contractPrice =
                    WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());

                //�������z = get�������()
                l_futuresOptionsContractUnit.contractExecPrice =
                    WEB3StringTypeUtility.formatNumber(l_dblContractAmount);

                //���萔��
                l_futuresOptionsContractUnit.contractCommission = WEB3StringTypeUtility.formatNumber(
                    l_bdContractCommission.add(l_bdContractCommissionConsumptionTax).doubleValue());

                //���v
                l_futuresOptionsContractUnit.income =
                    WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);

                //���v(���o�)
                l_futuresOptionsContractUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                    l_bdEvaluateIncome.subtract(l_bdContractCommission.add(
                        l_bdContractCommissionConsumptionTax)).doubleValue());

                //�ԍϐ���
                l_futuresOptionsContractUnit.contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblQuantity);

                //�ԍϖ�萔��
                l_futuresOptionsContractUnit.contractExecQuantity = null;

                //���Ϗ���
                l_futuresOptionsContractUnit.settlePriority =
                    l_rsvIfoClosingContractSpecRow.getClosingSerialNo() + "";

                //����敪
                IfoContractRow l_ifoContracRow =
                    (IfoContractRow)l_contract.getDataSourceObject();
                l_futuresOptionsContractUnit.sessionType = l_ifoContracRow.getSessionType();

                l_lisFuturesOptionsContractUnits.add(l_futuresOptionsContractUnit);
            }
        }

        if (l_lisFuturesOptionsContractUnits.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnits =
            new WEB3FuturesOptionsContractUnit[l_lisFuturesOptionsContractUnits.size()];
        l_lisFuturesOptionsContractUnits.toArray(l_futuresOptionsContractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_futuresOptionsContractUnits;
    }

    /**
     * (create���������P��)<BR>
     * �����̗\�񒍕��P�ʂ�芔�������P�ʃI�u�W�F�N�g�i���z�j��<BR>
     * ��������B<BR>
     * <BR>
     * ���\�񒍕��̒������Ɏg�p����B<BR>
     * <BR>
     * �P�j�@@���������P��Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�\�񒍕��P��.getDataSourceObject()����<BR>
     * �@@�@@�@@�\�񒍕��P��Row�𐶐�����B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�����P��ID = -1�i�\�񒍕��������̏�Ԃ�\���B�j<BR>
     * �@@����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�⏕����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���XID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@������� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����J�e�S�� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���������ŏI�ʔ� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���ŏI�ʔ� = 0<BR>
     * �@@�����^�C�v = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�s��ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�������� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�w�l = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���s���� = EqTypeExecutionConditionType.�����Ȃ�<BR>
     * �@@�l�i���� = "�����Ȃ�"<BR>
     * �@@�������� = "�����Ȃ�"<BR>
     * �@@��n�� = �����̗\�񒍕��P��.get�������().��n��<BR>
     * �@@�����������t = �\�񒍕��P�ʂ̓�����<BR>
     * �@@������� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����L����� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�ŋ敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�ŋ敪�i�������n�j = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�ٍϋ敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�ٍϊ����l = �\�񒍕��P�ʂ̓�����<BR>
     *   �ٍϋ敪(SONAR) = (*1)
     * �@@�@@�@@�@@�@@�@@�i���X�s��ٍϕʁj�戵����Row.�ٍϋ敪(SONAR)
     * �@@������ = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�������ʃ^�C�v = QuantityTypeEnum.����<BR>
     * �@@���񒍕��̒����`���l�� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�󒍓��� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����P�� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�T�Z��n��� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���n�v���z = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���n�v�Ŋz = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����R�[�h(SONAR) = (*2)<BR>
     *   �s��R�[�h(SONAR) = (*3)�s��Row.�s��R�[�h(SONAR)
     * �@@�萔�����i�R�[�h = (*4)<BR>
     * �@@�����o�H�敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���Ϗ����敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���񒍕��̒����P��ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�쐬���t = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�X�V���t = �\�񒍕��P�ʂ̓�����<BR>
     * <BR>
     * <BR>
     * (*1)�ٍϋ敪(SONAR)<BR>
     * �@@�\�񒍕��P��.�ٍϋ敪��null�̏ꍇ�݈̂ȉ��������s���B<BR>
     * �@@��L�ȊO�Anull���Z�b�g�B<BR>
     * <BR>
     * �@@�@@�D���X�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�g���A�J�E���g�}�l�[�W��.get���X()���R�[������B<BR>
     * �@@�@@�@@�@@�@@[get���X()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@arg0�F�@@�����f�[�^.get���XId()�̖߂�l<BR>
     * <BR>
     * �@@�A�D�،���ЃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@get���X()�̖߂�l.get�،����()���R�[������B<BR>
     * <BR>
     * �@@�B�D�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * �@@�@@�@@�@@[get�s��()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�\�񒍕��P��Row.get�s��Id()<BR>
     * <BR>
     * �@@�C�D�i���X�s��ٍϕʁj�戵����()���R�[������B<BR>
     * �@@�@@�@@�@@�@@[�i���X�s��ٍϕʁj�戵����()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�،���ЃR�[�h�F�@@get�،����().get�،���ЃR�[�h<BR>
     * �@@�@@�@@�@@�@@���X�R�[�h�F�@@get���X().get���X�R�[�h<BR>
     * �@@�@@�@@�@@�@@�s��R�[�h�F�@@get�s��().get�s��R�[�h<BR>
     * �@@�@@�@@�@@�@@�M�p����敪�F�@@�\�񒍕��P��.�ٍϋ敪<BR>
     * �@@�@@�@@�@@�@@�ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l<BR>
     * �@@�D�D�C�Ŏ擾����<BR>
     * �@@�@@�i���X�s��ٍϕʁj�戵�����I�u�W�F�N�g.getDataSourceObject()����<BR>
     * �@@�@@�i���X�s��ٍϕʁj�戵����Row�𐶐�����B<BR>
     * <BR>
     * (*2)����R�[�h(SONAR)<BR>
     * �@@�E�\�񒍕��P��.�����J�e�S�������������̏ꍇ�A<BR>
     * �@@�@@�@@�@@����R�[�h�iSONAR�j.���ʊ������Z�b�g<BR>
     * �@@�E�\�񒍕��P��.�����J�e�S�����V�K�������̏ꍇ�A<BR>
     * �@@�@@�@@�@@����R�[�h�iSONAR�j.�M�p�����Z�b�g<BR>
     * �@@�E�\�񒍕��P��.�����J�e�S�����ԍϒ����̏ꍇ�A<BR>
     * �@@�@@�@@�@@����R�[�h�iSONAR�j.�M�p�ԍς��Z�b�g<BR>
     * �@@�E�\�񒍕��P��.�����J�e�S���������E���n�����̏ꍇ�A<BR>
     * �@@�@@�@@�@@����R�[�h�iSONAR�j.�������n���Z�b�g<BR>
     * �@@�������J�e�S������L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * <BR>
     * (*3)�s��R�[�h(SONAR)<BR>
     * �@@(*1)�B�Ŏ擾�����s��I�u�W�F�N�g.getDataSourceObject()����<BR>
     * �@@�s��Row�𐶐�����B<BR>
     * <BR>
     * (*4)�萔�����i�R�[�h<BR>
     * �@@�\�񒍕��P��.�����J�e�S����"�����E���n����"�̏ꍇ�A<BR>
     * �@@�@@�@@null���Z�b�g����B<BR>
     * �@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�萔�����i�R�[�h."��ꊔ��"���Z�b�g����B<BR>
     * <BR>
     * �S�j�@@���������P�ʃI�u�W�F�N�g���쐬����B<BR>
     * �@@�g�����������}�l�[�W��.toOrderUnit(�v���p�e�B�Z�b�g����Params)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �T�j�@@�쐬�������������P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_rsvEqOrderUnit - (�\�񒍕��P��)<BR>
     * �����\�񒍕��P��Impl�I�u�W�F�N�g�B<BR>
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433B9E0C0287
     */
    public EqTypeOrderUnit createEqtypeOrderUnit(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createEqtypeOrderUnit(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("�\�񒍕��P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�\�񒍕��P�� = null�B");
        }
        
        //�P�j�@@���������P��Params�C���X�^���X�𐶐�����B 
        EqtypeOrderUnitParams l_eqOrderUnitParams = new EqtypeOrderUnitParams();
        
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow) l_rsvEqOrderUnit.getDataSourceObject();
            
        //�@@���X�I�u�W�F�N�g���擾����B
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
        Branch l_branch = null;                
        try
        {
            l_branch = l_accountManager.getBranch(l_rsvEqOrderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        //�@@�،���ЃI�u�W�F�N�g���擾�B 
        //�@@getBranch()�̖߂�l.getInstitution()���R�[������B 
        Institution l_institution = l_branch.getInstitution();
        
        long l_lngMarketId = l_rsvEqOrderUnitRow.getMarketId();        
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = 
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        //  �����P��ID = -1�i�\�񒍕��������̏�Ԃ�\���B�j 
        l_eqOrderUnitParams.setOrderUnitId(-1);
        
        //  ����ID = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setAccountId(l_rsvEqOrderUnit.getAccountId());
        
        //  �⏕����ID = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setSubAccountId(l_rsvEqOrderUnit.getSubAccountId());
        
        //  ���XID = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setBranchId(l_rsvEqOrderUnit.getBranchId());
        
        //  �����ID = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
        {
            l_eqOrderUnitParams.setTraderId(l_rsvEqOrderUnit.getTraderId());
        }
        
        //  ����ID = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setOrderId(l_rsvEqOrderUnit.getOrderId());
        
        //  ������� = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setOrderType(l_rsvEqOrderUnit.getOrderType());
        
        //  �����J�e�S�� = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setOrderCateg(l_rsvEqOrderUnit.getOrderCateg());
        
        //  ���������ŏI�ʔ� = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setLastOrderActionSerialNo(
            l_rsvEqOrderUnitRow.getLastOrderActionSerialNo());
        
        //  ���ŏI�ʔ� = 0 
        l_eqOrderUnitParams.setLastExecutionSerialNo(0);
        
        //  �����^�C�v = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setProductType(l_rsvEqOrderUnit.getProductType());
        
        //  �s��ID = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getMarketIdIsNull())
        {
            l_eqOrderUnitParams.setMarketId(l_rsvEqOrderUnitRow.getMarketId());
        }
        
        //  �������� = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setQuantity(l_rsvEqOrderUnit.getQuantity());
        
        //  �w�l = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getLimitPriceIsNull())
        {
            l_eqOrderUnitParams.setLimitPrice(l_rsvEqOrderUnit.getLimitPrice());
        }
        
        //  ���s���� = EqTypeExecutionConditionType.�����Ȃ� 
        l_eqOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
        
        //  �l�i���� = "�����Ȃ�" 
        l_eqOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.DEFAULT);
        
        //  �������� = "�����Ȃ�" 
        l_eqOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
        
        //  ��n�� = �����̗\�񒍕��P��.get�������().��n��
        TradedProduct l_tradedProduct = l_rsvEqOrderUnit.getTradedProduct();
        if (l_tradedProduct == null)
        {
            log.debug("��������e�[�u���ɊY������f�[�^������܂���B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "��������e�[�u���ɊY������f�[�^������܂���B");
        }
        
        Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        l_eqOrderUnitParams.setDeliveryDate(new Timestamp(l_datDeliveryDate.getTime()));       
        
        //  �����������t = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setExpirationDate(l_rsvEqOrderUnitRow.getExpirationDate());
        
        //  ������� = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setOrderStatus(l_rsvEqOrderUnit.getOrderStatus());
        
        //  �����L����� = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setOrderOpenStatus(l_rsvEqOrderUnit.getOrderOpenStatus());
        
        //  �����敪 = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setExpirationStatus(l_rsvEqOrderUnit.getExpirationStatus());
        
        //  �ŋ敪 = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setTaxType(l_rsvEqOrderUnit.getTaxType());
        
        //  �ŋ敪�i�������n�j = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setSwapTaxType(l_rsvEqOrderUnitRow.getSwapTaxType());
        
        //  �ٍϋ敪 = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setRepaymentType(l_rsvEqOrderUnitRow.getRepaymentType());
        
        //  �ٍϊ����l = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getRepaymentNumIsNull())
        {
            l_eqOrderUnitParams.setRepaymentNum(l_rsvEqOrderUnitRow.getRepaymentNum());
        }
        
        //�@@�ٍϋ敪(SONAR) = �i���X�s��ٍϕʁj�戵����Row.�ٍϋ敪(SONAR)
        //�@@�\�񒍕��P��.�ٍϋ敪��null�̏ꍇ�݈̂ȉ��������s���B
        l_eqOrderUnitParams.setSonarRepaymentType(null);
        if (l_rsvEqOrderUnitRow.getRepaymentType() != null)
        {
            //  �i���X�s��ٍϕʁj�戵����()���R�[������B
            //�@@�@@�@@[�i���X�s��ٍϕʁj�戵����()�ɐݒ肷�����]
            //�@@�@@�@@�،���ЃR�[�h�F�@@get�،����().get�،���ЃR�[�h
            //�@@�@@�@@���X�R�[�h�F�@@get���X().get���X�R�[�h
            //�@@�@@�@@�s��R�[�h�F�@@get�s��().get�s��R�[�h
            //�@@�@@�@@�M�p����敪�F�@@�\�񒍕��P��.�ٍϋ敪�@@�@@�@@�@@�@@
            //�@@�@@�@@�ٍϊ����l�F�@@�\�񒍕��P��.�ٍϊ����l 
            WEB3GentradeBranchMarketRepayDealtCond l_genBranchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_market.getMarketCode(),
                    l_rsvEqOrderUnitRow.getRepaymentType(),
                    l_rsvEqOrderUnitRow.getRepaymentNum()
                    );
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_genBranchMarketRepayDealtCond.getDataSourceObject();

            l_eqOrderUnitParams.setSonarRepaymentType(
                l_branchMarketRepayDealtCondRow.getSonarRepaymentType());
        }   
        
        //  ������ = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setBizDate(l_rsvEqOrderUnitRow.getBizDate());
        
        //  ����ID = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setProductId(l_rsvEqOrderUnit.getProduct().getProductId());
        
        //  �������ʃ^�C�v = QuantityTypeEnum.����
        l_eqOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
         
        //  ���񒍕��̒����`���l�� = �\�񒍕��P�ʂ̓����� ]
        l_eqOrderUnitParams.setOrderChanel(l_rsvEqOrderUnitRow.getOrderChanel());
        
        //  �󒍓��� = �\�񒍕��P�ʂ̓�����
        l_eqOrderUnitParams.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());
         
        //  �����P�� = �\�񒍕��P�ʂ̓�����
        if (!l_rsvEqOrderUnitRow.getPriceIsNull())
        {
            l_eqOrderUnitParams.setPrice(l_rsvEqOrderUnitRow.getPrice());
        }        
         
        //  �T�Z��n��� = �\�񒍕��P�ʂ̓�����
        if (!l_rsvEqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_eqOrderUnitParams.setEstimatedPrice(l_rsvEqOrderUnitRow.getEstimatedPrice());
        }  
         
        //  ���n�v���z = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getCapitalGainIsNull())
        {
            l_eqOrderUnitParams.setCapitalGain(l_rsvEqOrderUnitRow.getCapitalGain());
        }
        
        //  ���n�v�Ŋz = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getCapitalGainTaxIsNull())
        {
            l_eqOrderUnitParams.setCapitalGainTax(l_rsvEqOrderUnitRow.getCapitalGainTax());
        }
        
        //�@@����R�[�h�iSONAR�j
        String l_strSonarTradedCode;
        if(OrderCategEnum.ASSET.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        else if(OrderCategEnum.OPEN_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
        }
        else if(OrderCategEnum.CLOSE_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
        }
        else if(OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.SWAP_CONTRACT;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�e�S�� = " + l_rsvEqOrderUnit.getOrderCateg());
        }
        l_eqOrderUnitParams.setSonarTradedCode(l_strSonarTradedCode);
        
        //�@@�s��R�[�h(SONAR)
        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        l_eqOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        
        //�@@�萔�����i�R�[�h        
        if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_eqOrderUnitParams.setCommProductCode(null);
        }
        else
        {
            l_eqOrderUnitParams.setCommProductCode(
                WEB3CommisionProductCodeDef.LISTING_STOCK);
        }
        
        //  �����o�H�敪 = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setOrderRootDiv(l_rsvEqOrderUnitRow.getOrderRootDiv());
        
        //  ���Ϗ����敪 = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setClosingOrderType(l_rsvEqOrderUnitRow.getClosingOrderType());
        
        //  ���񒍕��̒����P��ID = �\�񒍕��P�ʂ̓����� 
        if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_eqOrderUnitParams.setFirstOrderUnitId(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
        }        
        
        //  �쐬���t = �\�񒍕��P�ʂ̓����� 
        l_eqOrderUnitParams.setCreatedTimestamp(l_rsvEqOrderUnitRow.getCreatedTimestamp());
        
        //  �X�V���t = �\�񒍕��P�ʂ̓�����
        l_eqOrderUnitParams.setLastUpdatedTimestamp(l_rsvEqOrderUnitRow.getLastUpdatedTimestamp());
         
        //�S�j�@@���������P�ʃI�u�W�F�N�g���쐬����B 
        //  �g�����������}�l�[�W��.toOrderUnit(�v���p�e�B�Z�b�g����Params)�� 
        //  �R�[������B         
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit) l_orderManager.toOrderUnit(l_eqOrderUnitParams);
        
        //�T�j�@@�쐬�������������P�ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_eqOrderUnit;
    }

    /**
     * (create�敨OP�����P��)<BR>
     * �����̗\�񒍕��P�ʂ��敨OP�����P�ʃI�u�W�F�N�g�i���z�j��<BR>
     * ��������B<BR>
     * <BR>
     * ���\�񒍕��̒������Ɏg�p����B<BR>
     * <BR>
     * �P�j�@@�敨OP�����P��Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�\�񒍕��P��.getDataSourceObject()����<BR>
     * �@@�@@�@@�\�񒍕��P��Row�𐶐�����B<BR>
     * <BR>
     * �R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�����P��ID = -1�i�\�񒍕��������̏�Ԃ�\���B�j<BR>
     * �@@����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�⏕����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���XID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@������� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����J�e�S�� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���������ŏI�ʔ� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���ŏI�ʔ� = 0<BR>
     * �@@�����^�C�v = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�敨�^�I�v�V�����敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�s��ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�������� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�w�l = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���s���� = IfoOrderExecutionConditionType.�����Ȃ�<BR>
     * �@@�������� = "�����Ȃ�"<BR>
     * �@@��n�� = �����̗\�񒍕��P��.get�������.get��n��()<BR>
     * �@@�����������t = �\�񒍕��P�ʂ̓�����<BR>
     * �@@������� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����L����� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�ŋ敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@������ = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���񒍕��̒����`���l�� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�󒍓��� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���҃R�[�h(SONAR) = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�����P�� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�T�Z��n��� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����R�[�h(SONAR) = (*1)<BR>
     * �@@�s��R�[�h(SONAR) = (*2)�s��Row.�s��R�[�h(SONAR)<BR>
     * �@@�萔�����i�R�[�h = (*3)<BR>
     * �@@�����o�H�敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���Ϗ��� = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���񒍕��̒����P��ID = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�쐬���t = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�X�V���t = �\�񒍕��P�ʂ̓�����<BR>
     * �@@�[��O�J�z�Ώۃt���O = �\�񒍕��P�ʂ̓�����<BR>
     * �@@����敪 = �\�񒍕��P�ʂ̓�����<BR>
     * �@@���v��敪 = null<BR>
     * <BR>
     * <BR>
     * �@@(*1)����R�[�h(SONAR)<BR>
     * �@@�@@�E�\�񒍕��P��.�����J�e�S�����敨�V�K�������A����OP�V�K�������̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@����R�[�h�iSONAR�j.�M�p�����Z�b�g<BR>
     * �@@�@@�E�\�񒍕��P��.�����J�e�S�����敨�ԍϒ����A����OP�ԍϒ���<BR>
     * �@@�@@�@@�@@�@@����R�[�h�iSONAR�j.�M�p�ԍς��Z�b�g<BR>
     * �@@�@@�������J�e�S������L�ȊO�̏ꍇ�́A��O��throw����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag: BUSINESS_ERROR_00653 <BR>
     * <BR>
     * �@@(*2)�s��R�[�h(SONAR)<BR>
     * �@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[������B<BR>
     * �@@�@@�@@[get�s��()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�\�񒍕��P��Row.get�s��Id()<BR>
     * <BR>
     * �@@�@@��L�Ŏ擾�����s��I�u�W�F�N�g.getDataSourceObject()����<BR>
     * �@@�@@�s��Row�𐶐�����B<BR>
     * <BR>
     * �@@(*3)�萔�����i�R�[�h<BR>
     * �@@�@@�\�񒍕��P��.�敨�^�I�v�V�����敪���敨�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�萔�����i�R�[�h.�����w���敨���Z�b�g����B<BR>
     * �@@�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�萔�����i�R�[�h.�����w��OP���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �S�j�@@�敨OP�����P�ʃI�u�W�F�N�g���쐬����B<BR>
     * �@@OP�����}�l�[�W��.toOrderUnit(�v���p�e�B�Z�b�g����Params)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * �T�j�@@�쐬�����敨OP�����P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_rsvIfoOrderUnit  - (�\�񒍕��P��)<BR>
     * �敨OP�\�񒍕��P��Impl�I�u�W�F�N�g�B<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    public IfoOrderUnit createIfoOrderUnit(
        WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoOrderUnit(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�敨OP�����P��Params�C���X�^���X�𐶐�����B
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();

        //�Q�j�@@�\�񒍕��P��.getDataSourceObject()����
        //�@@�@@�@@�\�񒍕��P��Row�𐶐�����B
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();

        //�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //�@@�����P��ID = -1�i�\�񒍕��������̏�Ԃ�\���B�j
        l_ifoOrderUnitParams.setOrderUnitId(-1);

        //�@@����ID = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setAccountId(l_rsvIfoOrderUnit.getAccountId());

        //�@@�⏕����ID = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setSubAccountId(l_rsvIfoOrderUnit.getSubAccountId());

        //�@@���XID = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setBranchId(l_rsvIfoOrderUnit.getBranchId());

        //�@@�����ID = �\�񒍕��P�ʂ̓�����
        if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
        {
            l_ifoOrderUnitParams.setTraderId(l_rsvIfoOrderUnit.getTraderId());
        }

        //�@@����ID = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderId(l_rsvIfoOrderUnit.getOrderId());

        //�@@������� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderType(l_rsvIfoOrderUnit.getOrderType());

        //�@@�����J�e�S�� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderCateg(l_rsvIfoOrderUnit.getOrderCateg());

        //�@@���������ŏI�ʔ� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(
            l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());

        //�@@���ŏI�ʔ� = 0
        l_ifoOrderUnitParams.setLastExecutionSerialNo(0);

        //�@@�����^�C�v = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setProductType(l_rsvIfoOrderUnit.getProductType());

        //�@@�敨�^�I�v�V�����敪 = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setFutureOptionDiv(l_rsvIfoOrderUnitRow.getFutureOptionDiv());

        //�@@�s��ID = �\�񒍕��P�ʂ̓�����
        if (!l_rsvIfoOrderUnitRow.getMarketIdIsNull())
        {
            l_ifoOrderUnitParams.setMarketId(l_rsvIfoOrderUnitRow.getMarketId());
        }
        
        //�@@�������� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setQuantity(l_rsvIfoOrderUnit.getQuantity());

        //�@@�w�l = �\�񒍕��P�ʂ̓�����
        if (!l_rsvIfoOrderUnitRow.getLimitPriceIsNull())
        {
            l_ifoOrderUnitParams.setLimitPrice(l_rsvIfoOrderUnit.getLimitPrice());
        }

        //�@@���s���� = IfoOrderExecutionConditionType.�����Ȃ�
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);

        //�@@�������� = "�����Ȃ�"
        l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

        //�@@��n�� = �����̗\�񒍕��P��.get�������.get��n��()
        TradedProduct l_tradedProduct = l_rsvIfoOrderUnit.getTradedProduct();
        if (l_tradedProduct == null)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }
        Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        l_ifoOrderUnitParams.setDeliveryDate(new Timestamp(l_datDeliveryDate.getTime()));

        //�@@�����������t = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setExpirationDate(l_rsvIfoOrderUnitRow.getExpirationDate());

        //�@@������� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderStatus(l_rsvIfoOrderUnit.getOrderStatus());

        //�@@�����L����� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderOpenStatus(l_rsvIfoOrderUnit.getOrderOpenStatus());

        //�@@�����敪 = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setExpirationStatus(l_rsvIfoOrderUnit.getExpirationStatus());

        //�@@�ŋ敪 = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setTaxType(l_rsvIfoOrderUnit.getTaxType());

        //�@@������ = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setBizDate(l_rsvIfoOrderUnitRow.getBizDate());

        //�@@����ID = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setProductId(l_rsvIfoOrderUnit.getProduct().getProductId());

        //�@@���񒍕��̒����`���l�� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());

        //�@@�󒍓��� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setReceivedDateTime(l_rsvIfoOrderUnitRow.getReceivedDateTime());

        //�@@���҃R�[�h(SONAR) = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());

        //�@@�����P�� = �\�񒍕��P�ʂ̓�����
        if (!l_rsvIfoOrderUnitRow.getPriceIsNull())
        {
            l_ifoOrderUnitParams.setPrice(l_rsvIfoOrderUnitRow.getPrice());
        }

        //�@@�T�Z��n��� = �\�񒍕��P�ʂ̓�����
        if (!l_rsvIfoOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_ifoOrderUnitParams.setEstimatedPrice(l_rsvIfoOrderUnitRow.getEstimatedPrice());
        }
        //�@@����R�[�h(SONAR) = (*1)
        String l_strSonarTradedCode = null;

        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg()))
        {
            //�\�񒍕��P��.�����J�e�S�����敨�V�K�������A����OP�V�K�������̏ꍇ
            //����R�[�h�iSONAR�j.�M�p�����Z�b�g
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
        }
        else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_rsvIfoOrderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_rsvIfoOrderUnit.getOrderCateg()))
        {
            //�\�񒍕��P��.�����J�e�S�����敨�ԍϒ����A����OP�ԍϒ����̏ꍇ
            //����R�[�h�iSONAR�j.�M�p�ԍς��Z�b�g
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
        }
        else
        {
            //�����J�e�S������L�ȊO�̏ꍇ�́A��O��throw����
            log.debug("�����J�e�S���̒l���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����J�e�S���̒l���s���ł��B");
        }
        l_ifoOrderUnitParams.setSonarTradedCode(l_strSonarTradedCode);

        //�@@�s��R�[�h(SONAR) = (*2)�s��Row.�s��R�[�h(SONAR)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        WEB3GentradeMarket l_market = null;
        try
        {
            l_market =
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_rsvIfoOrderUnitRow.getMarketId());
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

        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        l_ifoOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());

        //�@@�萔�����i�R�[�h = (*3)
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_rsvIfoOrderUnitRow.getFutureOptionDiv()))
        {
            //�\�񒍕��P��.�敨�^�I�v�V�����敪���敨�̏ꍇ
            l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        }
        else
        {
            //OP�̏ꍇ
            l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        }

        //�@@�����o�H�敪 = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());
        
        //�@@���Ϗ��� = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setClosingOrder(l_rsvIfoOrderUnitRow.getClosingOrder());

        //�@@���񒍕��̒����P��ID = �\�񒍕��P�ʂ̓�����
        if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_ifoOrderUnitParams.setFirstOrderUnitId(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
        }

        //�@@�쐬���t = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setCreatedTimestamp(l_rsvIfoOrderUnitRow.getCreatedTimestamp());
        //�@@�X�V���t = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp());

        //�@@�[��O�J�z�Ώۃt���O = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
            l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag());

        //�@@����敪 = �\�񒍕��P�ʂ̓�����
        l_ifoOrderUnitParams.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());

        //�@@���v��敪 = null
        l_ifoOrderUnitParams.setDayTradeType(null);

        //�S�j�@@�敨OP�����P�ʃI�u�W�F�N�g���쐬����B
        //�@@OP�����}�l�[�W��.toOrderUnit(�v���p�e�B�Z�b�g����Params)��
        //�@@�R�[������B
        WEB3OptionOrderManagerImpl l_opOrderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_opOrderManager.toOrderUnit(l_ifoOrderUnitParams);

        //�T�j�@@�쐬�����敨OP�����P�ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (create��������)<BR>
     * �����̒����P�ʂ��M�p����������ׂ��쐬����B<BR>
     * <BR>
     * �����Δ������Ɏg�p����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̎菇�ɂĐM�p����������ׂ��쐬����B<BR>
     * �@@�P�|�P�j�@@�M�p����������׃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@���ȉ��̍��ڈȊO��null���Z�b�g����B<BR>
     * �@@�@@���� = �����P��.������<BR>
     * �@@�@@������ = �����P��.��������<BR>
     * <BR>
     * �@@�P�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3MarginContractUnit
     * @@throws WEB3BaseException
     * @@roseuid 43424F710266
     */
    public WEB3MarginContractUnit createMarginContractUnit(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnit(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_orderUnit == null)
        {
            log.debug("�����P�� = null�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����P�� = null�B");
        }
        
        //�P�j�@@�ȉ��̎菇�ɂĐM�p����������ׂ��쐬����B
        //�P�|�P�j�@@�M�p����������׃C���X�^���X�𐶐�����B
        WEB3MarginContractUnit l_marginContractUnit = new WEB3MarginContractUnit();
        
        //�P�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        l_marginContractUnit.openDate = WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        l_marginContractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        
        //�P�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnit;
    }

    /**
     * (create���ʖ���)<BR>
     * �����̒����P�ʂ�茚�ʖ��ׂ��쐬����B<BR>
     * <BR>
     * �����Δ������Ɏg�p����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̎菇�ɂČ��ʖ��ׂ��쐬����B<BR>
     * �@@�P�|�P�j�@@���ʖ��׃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�P�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@�@@�@@�@@�@@���ȉ��̍��ڈȊO��null���Z�b�g����B<BR>
     * �@@�@@���� = �����P��.������<BR>
     * �@@�@@���ʐ� = �����P��.��������<BR>
     * <BR>
     * �@@�P�|�R�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��ԋp����B<BR>
     * @@param l_ifoOrderUnit  - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@return WEB3FuturesOptionsContractUnit
     * @@throws WEB3BaseException
     */
    public WEB3FuturesOptionsContractUnit createContractUnit(
        IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnit(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���ʖ��׃C���X�^���X�𐶐�����
        WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit =
            new WEB3FuturesOptionsContractUnit();

        //���� = �����P��.������
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        l_futuresOptionsContractUnit.openDate =
            WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //���ʐ� = �����P��.��������
        l_futuresOptionsContractUnit.contractQuantity =
            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());

        log.exiting(STR_METHOD_NAME);
        return l_futuresOptionsContractUnit;
    }

    /**
     * (is�]�̓`�F�b�N���{���X)<BR>
     * �A�����������ɂ����āA�]�̓`�F�b�N�����{���镔�X���ǂ�����ԋp����B<BR>
     * <BR>
     * �P�j�@@���X�v���t�@@�����X�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@���XID = �����̕⏕����.���XID And <BR>
     * �@@�@@�v���t�@@�����X�� = �v���t�@@�����X��.�]�̓`�F�b�N���{ And<BR>
     * �@@�@@�v���t�@@�����X���̘A�� = 1<BR>
     * <BR>
     * �Q�j�@@��������.�v���t�@@�����X�̒l == "�]�̓`�F�b�N�v"�̏ꍇ�́Atrue��ԋp����B<BR>
     * �@@�@@�@@�ȊO�Afalse��ԋp����B<BR>
     * <BR>
     * �@@�@@���������ʂ��擾�ł��Ȃ������ꍇ�́A<BR>
     * �@@�@@���u�]�̓`�F�b�N�����{���Ȃ��v���X�Ɣ��肵�Afalse��ԋp����B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCheckTradingPowerBranch(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isCheckTradingPower(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME); 
        
        try
        {
            SubAccountRow l_subAccountRow =
                (SubAccountRow)l_subAccount.getDataSourceObject();
            //�P�j�@@DB����
            BranchPreferencesRow l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccountRow.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_SUCORDER_CHECKTP,
                    1);

            if ((l_branchReferencesRow != null) &&
                WEB3SucOrderTradingPowerCheckDef.CHECK_TRADING_POWER.equals(
                l_branchReferencesRow.getValue()))
            {
                log.debug("�]�̓`�F�b�N�v�̕��X");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.debug("�]�̓`�F�b�N�Ȃ��̕��X");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
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
     * (is�J�z�Ώۗ\�񒍕�)<BR>
     * �J�z�Ώے����ł��邩���肷��B<BR>
     * <BR>
     * �P�j�@@������ԊǗ�.get����敪()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P�� == �敨OP�\�񒍕��P��Impl���A<BR>
     * �@@�@@�@@�@@�[��O�����J�z�iget����敪() == �h�[��h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�����o�^�����������蒍���i*1�j�͌J�z�ΏۊO�����Ɣ��f���邽�߁A<BR>
     * �@@�@@�@@false��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�i*1�j�����o�^�����������蒍��<BR>
     * �@@�@@�@@�@@�p�����[�^.�敨OP�\�񒍕��P��Impl.get���������敪() == �h��������h���A<BR>
     * �@@�@@�@@�@@�p�����[�^.�敨OP�\�񒍕��P��Impl.����敪 == null<BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ�A<BR>
     * �@@�@@�@@����.�����P��.���������� <= �Ɩ����t�i*2�j �ɊY�����钍���́A<BR>
     * �@@�@@�@@�J�z�ΏۊO�����Ɣ��f���邽�߁A false ��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�i*2�j �Ɩ����t�́AGtlUtils.getTradingSystem().getBizDate()�Ŏ擾�B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCarryoverReserveIfoOrderUnit(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarryoverReserveIfoOrderUnit(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�P�j�@@������ԊǗ�.get����敪()���R�[������B
        String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();

        //�Q�j�@@�p�����[�^.�����P�� == �敨OP�\�񒍕��P��Impl���A
        //�[��O�����J�z�iget����敪() == �h�[��h�j�̏ꍇ�A
        if ((l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
            && WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType))
        {
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_orderUnit;
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                (RsvIfoOrderUnitRow)l_ifoOrderUnitImpl.getDataSourceObject();

            //�@@�p�����[�^.�敨OP�\�񒍕��P��Impl.get���������敪() == �h��������h����
            //�p�����[�^.�敨OP�\�񒍕��P��Impl.����敪 == null
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_ifoOrderUnitImpl.getExpirationDateType())
                && l_rsvIfoOrderUnitRow.getSessionType() == null)
            {
                //�����o�^�����������蒍���i*1�j�͌J�z�ΏۊO�����Ɣ��f���邽�߁Afalse��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //�R�j�@@��L�ȊO�̏ꍇ
        //����.�����P��.���������� <= �Ɩ����t�i*2�j �ɊY�����钍���́A
        //�J�z�ΏۊO�����Ɣ��f���邽�߁A false ��ԋp����B
        else if (WEB3DateUtility.compareToDay(
            l_orderUnit.getExpirationTimestamp(), GtlUtils.getTradingSystem().getBizDate()) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (submit�敨OP�V�K���J�z�\�񒍕�)<BR>
     * �J�z��̐敨OP�V�K���̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�����J�z_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�p�����[�^.����ID)���R�[������B<BR>
     * <BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_carryBeforeReserveIfoOrderUnit - (�J�z���\�񒍕��P��)<BR>
     * �J�z���̗\�񒍕��P��<BR>
     * @@param l_carryAfterParentIfoOrderUnit - (�J�z��̐e�����P��)<BR>
     * �J�z��̐e�����P��<BR>
     * @@throws WEB3BaseException
     */
    public void submitIfoOpenContractCarryReserveOrder(
        long l_lngOrderId,
        WEB3ToSuccIfoOrderUnitImpl l_carryBeforeReserveIfoOrderUnit,
        IfoOrderUnit l_carryAfterParentIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoOpenContractCarryReserveOrder(long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_carryBeforeReserveIfoOrderUnit == null || l_carryAfterParentIfoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_carryBeforeReserveIfoOrderUnit.getDataSourceObject();
        //�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        //�o�^�̎d�l�́ADB�X�V�d�l
        //�u�����J�z_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q��
        //�����P�ʂh�c          null
        l_rsvIfoOrderUnitParams.setOrderUnitId(null);

        //�����h�c �擾��������ID�i�����̔ԁj
        l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

        //���������ŏI�ʔ�     1
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

        //�������               1:��t�ρi�V�K�����j
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

        //�����L�����          1:�I�[�v��
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

        //�����敪            1:�I�[�v��
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

        //������
        l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //�����G���[���R�R�[�h     0000�F����
        l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //���񒍕��̒����h�c
        //�J�z���\�񒍕��P��.���񒍕��̒����h�c = null �̏ꍇ�A�J�z���\�񒍕��P��.�����h�c
        //����ȊO�̏ꍇ�A�J�z���\�񒍕��P�ʂ̓�����
        if (l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull())
        {
            l_rsvIfoOrderUnitParams.setFirstOrderId(l_rsvIfoOrderUnitRow.getOrderId());
        }

        //�����G���[�R�[�h    null
        l_rsvIfoOrderUnitParams.setOrderErrorCode(null);

        //�e�����̒����h�c          ����.�J�z��̐e�����P��.����ID
        l_rsvIfoOrderUnitParams.setParentOrderId(l_carryAfterParentIfoOrderUnit.getOrderId());

        //�e�����̒����P�ʂh�c      ����.�J�z��̐e�����P��.�����P��ID
        l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_carryAfterParentIfoOrderUnit.getOrderUnitId());

        //����敪             ������ԊǗ�.get����敪()
        l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //�쐬���t             ���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //�X�V���t             ���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);
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

        //�Q�j�@@�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�p�����[�^.����ID)���R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService) Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit�敨OP�ԍόJ�z�\�񒍕�)<BR>
     * �J�z��̐敨OP�ԍς̗\�񒍕���V�K�o�^����B<BR>
     * <BR>
     * �P�j�@@�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�����J�z_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �Q�j�@@�����c�ɑ΂���ԍς̏ꍇ�A<BR>
     * �@@�i�p�����[�^.�J�z���\�񒍕��P��.is���Δ������() == false�j<BR>
     * �@@�敨OP�\�񌚋ʕԍώw����e�[�u���̃��R�[�h���X�V����B<BR>
     * �@@���p�����[�^.�ԍό��ʃG���g���̗v�f�����A�X�V����B<BR>
     * <BR>
     * �@@�@@�o�^�̎d�l�́ADB�X�V�d�l<BR>
     * �@@�@@�u�����J�z_�\�񌚋ʕԍώw����e�[�u��.xls�v���Q�ƁB<BR>
     * <BR>
     * �R�j�@@�\�񒍕��������쐬����B<BR>
     * <BR>
     * �@@�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�p�����[�^.����ID)��<BR>
     * �@@�R�[������B<BR>
     * <BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_carryBeforeReserveIfoOrderUnit - (�J�z���\�񒍕��P��)<BR>
     * �J�z���̗\�񒍕��P��<BR>
     * @@param l_carryAfterParentIfoOrderUnit - (�J�z��̐e�����P��)<BR>
     * �J�z��̐e�����P��<BR>
     * @@param l_carryAfterParentIfoOrderUnit - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@throws WEB3BaseException
     */
    public void submitIfoCloseContractCarryReserveOrder(
        long l_lngOrderId,
        WEB3ToSuccIfoOrderUnitImpl l_carryBeforeReserveIfoOrderUnit,
        IfoOrderUnit l_carryAfterParentIfoOrderUnit,
        SettleContractEntry[] l_settleContractEntrys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoCloseContractCarryReserveOrder(" +
            "long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit, SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        if (l_carryBeforeReserveIfoOrderUnit == null || l_carryAfterParentIfoOrderUnit == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_carryBeforeReserveIfoOrderUnit.getDataSourceObject();
        //�敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        //�o�^�̎d�l�́ADB�X�V�d�l
        //�u�����J�z_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q��
        //�����P�ʂh�c          null
        l_rsvIfoOrderUnitParams.setOrderUnitId(null);

        //�����h�c �擾��������ID�i�����̔ԁj
        l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

        //���������ŏI�ʔ�     1
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

        //�������               1:��t�ρi�V�K�����j
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

        //�����L�����          1:�I�[�v��
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

        //�����敪            1:�I�[�v��
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

        //������
        l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //�����G���[���R�R�[�h     0000�F����
        l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //���񒍕��̒����h�c
        //�J�z���\�񒍕��P��.���񒍕��̒����h�c = null �̏ꍇ�A�J�z���\�񒍕��P��.�����h�c
        //����ȊO�̏ꍇ�A�J�z���\�񒍕��P�ʂ̓�����
        if (l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull())
        {
            l_rsvIfoOrderUnitParams.setFirstOrderId(l_rsvIfoOrderUnitRow.getOrderId());
        }

        //�����G���[�R�[�h    null
        l_rsvIfoOrderUnitParams.setOrderErrorCode(null);

        //�e�����̒����h�c          ����.�J�z��̐e�����P��.����ID
        l_rsvIfoOrderUnitParams.setParentOrderId(l_carryAfterParentIfoOrderUnit.getOrderId());

        //�e�����̒����P�ʂh�c      ����.�J�z��̐e�����P��.�����P��ID
        l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_carryAfterParentIfoOrderUnit.getOrderUnitId());

        //����敪             ������ԊǗ�.get����敪()
        l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //�쐬���t             ���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //�X�V���t             ���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);
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

        //�����c�ɑ΂���ԍς̏ꍇ
        //�p�����[�^.�J�z���\�񒍕��P��.is���Δ������() == false
        if (!l_carryBeforeReserveIfoOrderUnit.isReversingTrade())
        {
            int l_intCnt = 0;
            if (l_settleContractEntrys != null && l_settleContractEntrys.length > 0)
            {
                l_intCnt = l_settleContractEntrys.length;
            }

            //�p�����[�^.�ԍό��ʃG���g���̗v�f�����A�X�V����B
            for (int i = 0; i < l_intCnt; i++)
            {
                //�o�^�̎d�l�́ADB�X�V�d�l
                //�u�����J�z_�\�񌚋ʕԍώw����e�[�u��.xls�v���Q�ƁB
                RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                    new RsvIfoClosingContractSpecParams();

                //�����h�c   �����̌J�z���\�񒍕��P��.����ID
                l_rsvIfoClosingContractSpecParams.setAccountId(l_carryBeforeReserveIfoOrderUnit.getAccountId());

                //�⏕�����h�c     �����̌J�z���\�񒍕��P��.�⏕����ID
                l_rsvIfoClosingContractSpecParams.setSubAccountId(
                    l_carryBeforeReserveIfoOrderUnit.getSubAccountId());

                //�����h�c         �����̒���ID
                l_rsvIfoClosingContractSpecParams.setOrderId(l_lngOrderId);

                //���ʂh�c               �����̕ԍό��ʃG���g��[index].getContractId()
                l_rsvIfoClosingContractSpecParams.setContractId(l_settleContractEntrys[i].getContractId());

                //�ԍϘA��
                l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                //�ԍϒ�������           �����̕ԍό��ʃG���g��[index].getQuantity()
                l_rsvIfoClosingContractSpecParams.setQuantity(l_settleContractEntrys[i].getQuantity());

                //�쐬���t             ���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //�X�V���t             ���ݓ����iGtlUtils.getSystemTimestamp()�j
                l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                    l_queryProcessor.doInsertQuery(l_rsvIfoClosingContractSpecParams);
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
        }

        //�R�j�@@�\�񒍕��������쐬����B
        //�敨OP�\�񒍕��X�V�T�[�r�X.insert�\�񒍕�����(�p�����[�^.����ID)��
        //�R�[������B
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService) Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate�[��܂Œ��������\)<BR>
     * �[��܂Œ����ɒ����\���ǂ����̃`�F�b�N���s���B<BR>
     * <BR>
     * �|�u�[��܂Œ����v�戵�\��Ђł��邱�ƁB<BR>
     * �|���݂̎��ԑт��[��ȊO�ł��邱�ƁB<BR>
     * <BR>
     * �P�j�@@�ȉ��̂����ꂩ�ɊY������ꍇ�́A����������return����B<BR>
     * <BR>
     * �@@�@@�E�p�����[�^.���������敪��"�[��܂Œ���"�̏ꍇ<BR>
     * <BR>
     * �@@�@@�E�p�����[�^.�\�񒍕��P��.get���������敪()=="�[��܂Œ���"�̏ꍇ<BR>
     * �@@�@@�@@���V�K�����o�^���ɐR���ς݂Ȃ̂Ń`�F�b�N�s�v�B<BR>
     * <BR>
     * �Q�j�@@�戵�\���������C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@[�R���X�g���N�^�Ɏw�肷�����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�\�񒍕��P��.get���XID()�ɊY�����镔�X.�،���ЃR�[�h<BR>
     * �@@�@@�����^�C�v�F�@@ProductTypeEnum.�h�敨�I�v�V�����h<BR>
     * �@@�@@�敨�^�I�v�V�����敪�F�@@�p�����[�^.�\�񒍕��P��.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�M�p����敪�F�@@"DEFAULT"�i�Œ�j<BR>
     * <BR>
     * �R�j�@@�[��܂Œ����戵�\�`�F�b�N���s���B<BR>
     * <BR>
     * �@@(�Q�j�Ŏ擾�����戵�\��������.is�[��܂Œ����戵�\==false)<BR>
     * �@@�܂���<BR>
     * �@@(������ԊǗ�.is�[�ꎞ�ԑ�()==true)<BR>
     * �@@�̂����ꂩ�ɂ��Ă͂܂�ꍇ�A<BR>
     * �@@�w�[��܂Œ����͎�舵���܂���B�x�̗�O���X���[����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag�@@: BUSINESS_ERROR_02816<BR>
     * <BR>
     * @@param l_strExpirationDateType - (���������敪)<BR>
     * ���������敪<BR>
     * @@param l_toSuccIfoOrderUnitImpl - (�\�񒍕��P��)<BR>
     * �\�񒍕��P��<BR>
     * @@throws WEB3BaseException
     */
    public void validateEveningSessionOrderPossibleChange(
        String l_strExpirationDateType,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEveningSessionOrderPossibleChange(String, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_toSuccIfoOrderUnitImpl == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�p�����[�^�l�s���B");
        }

        //�E�p�����[�^.���������敪��"�[��܂Œ���"�̏ꍇ
        //�E�p�����[�^.�\�񒍕��P��.get���������敪()=="�[��܂Œ���"�̏ꍇ
        if (!WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType)
            || WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(
                l_toSuccIfoOrderUnitImpl.getExpirationDateType()))
        {
            //����������return����B
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //�p�����[�^.�\�񒍕��P��.get���XID()�ɊY�����镔�X
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_toSuccIfoOrderUnitImpl.getBranchId());
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

        //�\�񒍕��P��Row
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnitImpl.getDataSourceObject();

        //�戵�\���������C���X�^���X�𐶐�����B
        //[�R���X�g���N�^�Ɏw�肷�����]
        //�،���ЃR�[�h�F�@@�p�����[�^.�\�񒍕��P��.get���XID()�ɊY�����镔�X.�،���ЃR�[�h
        //�����^�C�v�F�@@ProductTypeEnum.�h�敨�I�v�V�����h
        //�敨�^�I�v�V�����敪�F�@@�p�����[�^.�\�񒍕��P��.�敨�^�I�v�V�����敪
        //�M�p����敪�F�@@"DEFAULT"�i�Œ�j
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_branch.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                l_rsvIfoOrderUnitRow.getFutureOptionDiv(),
                WEB3MarginTradingDivDef.DEFAULT);

        //�[��܂Œ����戵�\�`�F�b�N���s���B
        //�擾�����戵�\��������.is�[��܂Œ����戵�\==false)
        //�܂���(������ԊǗ�.is�[�ꎞ�ԑ�()==true)�̂����ꂩ�ɂ��Ă͂܂�ꍇ�A
        //�w�[��܂Œ����͎�舵���܂���B�x�̗�O���X���[����B
        if (!l_handlingOrderCond.isEveningSessionOrderPossibleHandling()
            || WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
        {
            log.debug("�[��܂Œ����͎�舵���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02816,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�[��܂Œ����͎�舵���܂���B");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
