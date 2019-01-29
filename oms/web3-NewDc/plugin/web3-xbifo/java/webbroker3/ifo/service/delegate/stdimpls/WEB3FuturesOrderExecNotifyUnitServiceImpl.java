head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�o���ʒm�P���T�[�r�X�����N���X(WEB3FuturesOrderExecNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
Revesion History : 2006/8/15 �s�p (���u) �d�l�ύX ���f��501
Revesion History : 2006/11/29 ����(���u) �d�l�ύX���f��No.580
Revesion History : 2007/04/25 �����Q (���u) �d�l�ύX���f��No.634
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoExecuteNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoPositionUpdateInterceptor;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨�o���ʒmUnitServiceImpl)<BR>
 * �����w���敨�o���ʒm�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �P�����Ƃ̏o���ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)���w�肷��B<BR>
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FuturesOrderExecNotifyUnitServiceImpl
    implements WEB3FuturesOrderExecNotifyUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderExecNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40F7A2C402DE
     */
    public WEB3FuturesOrderExecNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify���)<BR>
     * ��菈�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�o���ʒm�jnotify���v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_tsExecDate - ������<BR>
     * @@param l_dblExecQuantity - ��萔��<BR>
     * @@param l_dblExecPrice - ���P��<BR>
     * @@param l_strExecNotifyDiv - �o���ʒm�敪<BR>
     * @@roseuid 40A843180021
     */
    public void notifyExecute(
        OrderUnit l_orderUnit,
        Timestamp l_tsExecDate,
        double l_dblExecQuantity,
        double l_dblExecPrice,
        String l_strExecNotifyDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecute()";
        log.entering(STR_METHOD_NAME);

        if ((l_orderUnit == null) || (l_tsExecDate == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if ((l_dblExecQuantity <= 0) || (l_dblExecPrice <= 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();

        //1.1 �����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
        OrderUnit l_reOrderUnit = l_orderUnit;
        try
        {
            l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        //1.2 getOrderUnitId()
        long l_lngOrderUnitID = l_reOrderUnit.getOrderUnitId();

        //1.3 validate�������(OrderUnit)
        this.validateOrderStatus(l_reOrderUnit);

        //1.3 DefaultFillOrderUnitSpec(long, double, double)
        //  [�R���X�g���N�^�̈���]
        //  �����P�ʂh�c�F�@@�����P��.getOuderUnitId()
        //  ��萔�ʁF�@@����.��萔��
        //  ���P���F�@@����.���P��
        DefaultFillOrderUnitSpec l_defaultFillOrderUnitSpec =
            new DefaultFillOrderUnitSpec(
                l_lngOrderUnitID,
                l_dblExecQuantity,
                l_dblExecPrice);

        //1.4 getOrderId()
        long l_lngOrderID = l_reOrderUnit.getOrderId();

        //1.5 �敨OP�o���ʒm�X�V�C���^�Z�v�^()
        WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

        //1.7 �L���[.�o���ʒm�敪���C���^�Z�v�^�ɐݒ�
        l_interceptor.setDealedType(l_strExecNotifyDiv);
        
        //1.8 �L���[.���������C���^�Z�v�^�ɐݒ�
        l_interceptor.setExecTimestamp(l_tsExecDate);

        //1.8 setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
        //  [����]
        //  arg0�F ���������敨OP�o���ʒm�X�V�C���^�Z�v�^
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //setThreadLocalPersistenceEventInterceptor(arg0 : IfoPositionManagerPersistenceEventInterceptor)
        //  [����] 
        //  arg0�F ���������敨OP�|�W�V�����X�V�C���^�Z�v�^
        WEB3IfoPositionUpdateInterceptor l_pInterceptor = new WEB3IfoPositionUpdateInterceptor();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
        l_positionManager.setThreadLocalPersistenceEventInterceptor(l_pInterceptor); 

        //1.9  DefaultOrderFillMarketResponseMessage(long, FillOrderUnitSpec)
        DefaultOrderFillMarketResponseMessage l_defaultOrderFillMarketResponseMessage =
            new DefaultOrderFillMarketResponseMessage(
                l_lngOrderID,
                l_defaultFillOrderUnitSpec);

        //1.10 process(MarketResponseMessage)
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        l_marketResponseReceiverCallbackService.process(
            l_defaultOrderFillMarketResponseMessage);

        //1.11 �T�Z��n����̍X�V���s���B
        //  [�����̐ݒ�]
        //  �����P�ʁF�@@�����P��
        try
        {
            l_reOrderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitID);
            l_orderManager.updateFuturesEstimateDeliveryAmount(l_reOrderUnit);
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        try
        {
            l_reOrderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitID);
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //1.12  isFullyExecuted( )
        boolean l_blnisFullyExecuted = l_reOrderUnit.isFullyExecuted();
        
        //1.13 (*1)�S�o���iisFullyExecuted() == true�j�̏ꍇ
        if (l_blnisFullyExecuted)
        {
            //1.13.1 sendMailProcess(OrderUnit, String)
            WEB3IfoExecutedMailSendService l_executedMailSendServiceImpl =
                (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
            l_executedMailSendServiceImpl.sendMailProcess(l_reOrderUnit, null);
        
            //1.13.2 notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
            try
            {
                l_orderManager.notifyRLS(
                    (IfoOrderUnit) l_reOrderUnit, 
                    OrderManagerPersistenceContext.FILL_ORDER);
            }
            //1.13.3 notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
            catch (WEB3BusinessLayerException l_ex)
            {
                log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�����)<BR>
     * �o���ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�o���ʒm�jnotify������v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@param l_tsExecDate - ������<BR>
     * @@param l_dblExecQuantity - ��萔��<BR>
     * @@param l_dblExecPrice - ���P��<BR>
     * @@roseuid 40A843180041
     */
    public void notifyExecuteCancel(
        OrderUnit l_orderUnit,
        Timestamp l_tsExecDate,
        double l_dblExecQuantity,
        double l_dblExecPrice)
        throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "notifyExecuteCancel()";
        log.entering(STR_METHOD_NAME);

        if ((l_orderUnit == null) || (l_tsExecDate == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if ((l_dblExecQuantity <= 0) || (l_dblExecPrice <= 0))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingMod.getOrderManager();

        //1.1 �����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
        OrderUnit l_reOrderUnit = l_orderUnit;
        try
        {
            l_reOrderUnit =  l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        }
        catch(NotFoundException l_nfe)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        //1.2 validate�������(OrderUnit)
        this.validateOrderStatus(l_reOrderUnit);

        //1.2 getExecutions()
        OrderExecution[] l_orderExecution = l_reOrderUnit.getExecutions();

        int l_intOrderExecutionCnt = l_orderExecution.length;
        Timestamp l_tsExecutionTime = null;
        int l_intOrderIndex = -1;
        //1.3 ���I�u�W�F�N�g�̐���LOOP
        for (int i = 0; i < l_intOrderExecutionCnt; i++)
        {
            //1.3.1getExecutionQuantity()
            double l_dblExecutionQuantity = l_orderExecution[i].getExecutionQuantity();
            if (Double.isNaN(l_dblExecutionQuantity))
            {
                l_dblExecutionQuantity = 0D;
            }

            //1.3.2 getExecutionPrice()
            double l_dblExecutionPrice = l_orderExecution[i].getExecutionPrice();
            if (Double.isNaN(l_dblExecutionPrice))
            {
                l_dblExecutionPrice = 0D;
            }

            if ((l_dblExecPrice == l_dblExecutionPrice) && (l_dblExecQuantity == l_dblExecutionQuantity))
            {
                //�������ʁ^�P���̖�肪�������������ꍇ�́A�������Â��ق��̖��������ΏۂƂ���B
                Timestamp l_tsExecutionTimeTemp = l_orderExecution[i].getExecutionTimestamp();
                if ((l_tsExecutionTime == null) || (l_tsExecutionTimeTemp.before(l_tsExecutionTime)))
                {
                    l_tsExecutionTime = l_tsExecutionTimeTemp;
                    l_intOrderIndex = i;
                }
            }
        }

        if (l_intOrderIndex != -1)
        {
            //1.3.3.1 getOrderExecutionId()
            long l_lngOrderExecutionId = l_orderExecution[l_intOrderIndex].getOrderExecutionId();

            //1.4 �敨OP�o���ʒm�X�V�C���^�Z�v�^()
            WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

            //1.5 �L���[.���������C���^�Z�v�^�ɐݒ�
            l_interceptor.setExecTimestamp(l_tsExecDate);

            //1.6 setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            //  [����]
            //  arg0�F ���������敨OP�o���ʒm�X�V�C���^�Z�v�^
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
            
            //setThreadLocalPersistenceEventInterceptor(arg0 : IfoPositionManagerPersistenceEventInterceptor)
            //  [����] 
            //  arg0�F ���������敨OP�|�W�V�����X�V�C���^�Z�v�^
            WEB3IfoPositionUpdateInterceptor l_pInterceptor = new WEB3IfoPositionUpdateInterceptor();
            WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            l_positionManager.setThreadLocalPersistenceEventInterceptor(l_pInterceptor); 

            //1.7 getOrderId()
            long l_lngOrderID = l_reOrderUnit.getOrderId();

            //1.8 DefaultUndoOrderFillMarketResponseMessage(long, long)
            DefaultUndoOrderFillMarketResponseMessage l_undoOrderFillMarketResponseMessage =
                new DefaultUndoOrderFillMarketResponseMessage(
                    l_lngOrderID,
                    l_lngOrderExecutionId);

            //1.9 process(MarketResponseMessage)
            MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
            IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackServiceImpl =
                (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            l_marketResponseReceiverCallbackServiceImpl.process(l_undoOrderFillMarketResponseMessage);

            //1.10 update�g�����U�N�V����(long)(�敨OP�|�W�V�����}�l�[�W��::update�g�����U�N�V����)
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl =
                (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getPositionManager();
            l_ifoPositionManagerImpl.updateTransaction(
            l_reOrderUnit.getOrderUnitId());
            
            //1.11 �T�Z��n����̍X�V���s���B
            //  [�����̐ݒ�]
            //  �����P�ʁF�@@�����P��
            try
            {
                l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
                l_orderManager.updateFuturesEstimateDeliveryAmount(l_reOrderUnit);
            }
            catch(NotFoundException l_nfe)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }

            //1.13 undoSendMail(OrderUnit)(�敨OP��胁�[�����M�T�[�r�XImpl::undoSendMail)
            //  [sendMainProcess()�Ɏw�肷�����]
            //  �����P�ʁF�@@�����P�ʃI�u�W�F�N�g
            WEB3IfoExecutedMailSendService l_executedMailSendServiceImpl =
                (WEB3IfoExecutedMailSendService)Services.getService(
                    WEB3IfoExecutedMailSendService.class);
            l_executedMailSendServiceImpl.undoSendMail(l_reOrderUnit);
        }
    }

    /**
     * (validate�������)<BR>
     * �Ώے������A���^������ɂ��X�V���s���ėǂ���Ԃł��邩�`�F�b�N����B<BR>
     * <BR>
     * �i�`�F�b�N���e�j<BR>
     * �P�D�s�ꂩ��m�F�ς݂̐���==null�̏ꍇ�A<BR>
     * �@@�u�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁv�̗�O��throw����B<BR>
     * <BR>
     * �Q�D������Ԃ̃`�F�b�N�F�ȉ��̂����ꂩ�ɊY������ꍇ�A�P�D�Ɠ�����O��throw����B<BR>
     * �@@�@@�@@�@@ACCEPTED�F��t�ρi�V�K�����j<BR>
     * �@@�@@�@@�@@MODIFY_ACCEPTED�F��t�ρi�ύX�����j<BR>
     * �@@�@@�@@�@@MODIFYING�F�������i�ύX�����j<BR>
     * <BR>
     * �ȊO�A���̂܂�return����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void validateOrderStatus(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".validateOrderStatus(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        IfoOrderUnitRow l_orderUnitRow =
            (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (l_orderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) ||
            OrderStatusEnum.MODIFYING.equals(l_orderStatus))
        {
            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01975,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
