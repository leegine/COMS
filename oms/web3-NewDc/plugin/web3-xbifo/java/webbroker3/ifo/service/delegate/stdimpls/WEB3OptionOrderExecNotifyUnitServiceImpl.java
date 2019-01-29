head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����o���ʒm�P���T�[�r�X����(WEB3OptionOrderExecNotifyUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 羉s (���u) �V�K�쐬
Revesion History : 2004/07/22 ���Ō� (���u) �����̊֘A���e���R�����g
Revesion History : 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
Revesion History : 2004/08/14 ���Ō� �Ή� �y�����w���I�v�V�����z�\�[�X�R�[�h�`�F�b�N�w�E����(JP)20040802 
Revesion History : 2004/07/28 �ęԍg (���u)�@@ �d�l�ύX�@@���f��464
Revesion History : 2006/11/29 ����(���u) �d�l�ύX���f��No.579
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;

import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyUnitService;
import webbroker3.ifo.WEB3IfoExecuteNotifyUpdateInterceptor;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoPositionUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;

/**
 * (OP�o���ʒmUnitServiceImpl)<BR>
 * <BR>
 * �����w���I�v�V�����o���ʒm�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �P�����Ƃ̏o���ʒm���������{����B<BR>
 * <BR>
 * Plugin���Ɏ����g�����U�N�V����TransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)���w�肷��B<BR>
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyUnitServiceImpl implements WEB3OptionOrderExecNotifyUnitService
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOrderExecNotifyUnitServiceImpl.class);

    /**
     * @@roseuid 40C0752F033C
     */
    public WEB3OptionOrderExecNotifyUnitServiceImpl()
    {

    }

    /**
     * (notify���)<BR>
     * <BR>
     * ��菈�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�o���ʒm�jnotify���v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_tsExecDate - ������
     * @@param l_dblExecQuantity - ��萔��
     * @@param l_dblExecPrice - ���P��
     * @@param l_strExecutedNotifyDivision - �o���ʒm�敪
     * @@roseuid 40876047033B
     */
    public void notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecutedNotifyDivision) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "notifyExecute(OrderUnit l_orderUnit, Timestamp l_tsExecDate, double l_dblExecQuantity, double l_dblExecPrice, String l_strExecutedNotifyDivision";
        log.entering(STR_METHOD_NAME);

        if ((l_orderUnit == null) || (l_tsExecDate == null))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();
        //�����P�ʂ��Ď擾����B�i�������b�N���������ꍇ���l���j
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
        //1.1 �����P�ʂh�c���擾����
        long l_lngOrderUnitID = l_reOrderUnit.getOrderUnitId();

        //1.2 validate�������(OrderUnit)
        this.validateOrderStatus(l_reOrderUnit);

        //1.2 �ʖ��Spec�I�u�W�F�N�g�𐶐�����
        DefaultFillOrderUnitSpec l_defaultFillOrderUnitSpec = new DefaultFillOrderUnitSpec(l_lngOrderUnitID, l_dblExecQuantity, l_dblExecPrice);
                
        //1.3 �����h�c���擾����
        long l_lngOrderID = l_reOrderUnit.getOrderId();

        //1.4 �����e�I�u�W�F�N�g�𐶐�����        
        DefaultOrderFillMarketResponseMessage l_defaultOrderFillMarketResponseMessage = new DefaultOrderFillMarketResponseMessage(l_lngOrderID, l_defaultFillOrderUnitSpec);
        
        //1.5 �敨OP�o���ʒm�X�V�C���^�Z�v�^()
        WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

        //�L���[.�o���ʒm�敪���C���^�Z�v�^�ɐݒ�B
        l_interceptor.setDealedType(l_strExecutedNotifyDivision);
        
        //�L���[.���������C���^�Z�v�^�ɐݒ�B
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

        //1.7 process(MarketResponseMessage)(IfoMarketResponseReceiverCallbackServiceImpl::process)
        //  [process()�Ɏw�肷�����] 
        //  �����e�F�@@�i�������������e�I�u�W�F�N�g�j 
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        IfoMarketResponseReceiverCallbackService l_marketResponseReceiveCallbackService = 
            (IfoMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_processingResult = l_marketResponseReceiveCallbackService.process(
            l_defaultOrderFillMarketResponseMessage);
        if (l_processingResult.isFailedResult())
        {
            log.error(l_processingResult.getErrorInfo().getErrorMessage());
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                "�o���ʒm�̓��e�ŁADB���X�V�G���[");
        }

        //1.8 �T�Z��n����̍X�V���s���B
        //  [�����̐ݒ�]
        //  �����P�ʁF�@@�����P��
        try
        {
            l_reOrderUnit = l_orderManager.getOrderUnit(l_lngOrderUnitID);                    
            l_orderManager.updateEstimateDeliveryAmount(l_reOrderUnit);
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

        //1.9 get�⏕����()(�g���A�J�E���g�}�l�[�W��::get�⏕����)
        //  [����] 
        //  ����ID�F �����P��.����ID 
        //  �⏕����ID�F �����P��.�⏕����ID 
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_accountManager.getSubAccount(
            l_reOrderUnit.getAccountId(),
            l_reOrderUnit.getSubAccountId());                
        }
        catch(NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }

        //1.10 �]�͍Čv�Z(�⏕���� : �⏕����)
        //  [����] 
        //  �⏕�����F get�⏕����()�̖߂�l 
        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
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
        
        try
        {
            //1.11 isFullyExecuted()
            //1.12 (*1)�S�o���iisFullyExecuted() == true�j�̏ꍇ�A�������{
            l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());
            if (l_reOrderUnit.isFullyExecuted())
            {
                //1.12.1 sendMailProcess(OrderUnit, String)
                //  [sendMainProcess()�Ɏw�肷�����] 
                //  �����P�ʁF�@@�����P�ʃI�u�W�F�N�g 
                //  �������R�R�[�h�F�@@null 
                WEB3IfoExecutedMailSendService l_web3IfoExeMailSendService =
                    (WEB3IfoExecutedMailSendService)Services.getService(
                    WEB3IfoExecutedMailSendService.class);
                l_web3IfoExeMailSendService.sendMailProcess(l_reOrderUnit, null);
                
                //1.13 notify���[���G���W���T�[�o(IfoOrderUnit, OrderManagerPersistenceContext)
                //     notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
                try
                {
                    l_orderManager.notifyRLS((IfoOrderUnit)l_reOrderUnit, OrderManagerPersistenceContext.FILL_ORDER);
                }
                catch (WEB3BusinessLayerException l_ex)
                {
                    log.debug("notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ�A�����[���o�b�N���Ȃ��B");
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�f�[�^�s�����G���[�B", l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_nfe.getMessage(), 
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (notify�����)<BR>
     * <BR>
     * �o���ʒm���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�o���ʒm�jnotify������v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@param l_tsExecDate - ������
     * @@param l_dblExecQuantity - ��萔��
     * @@param l_dblExecPrice - ���P��
     * @@roseuid 40879DB102B8
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

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingMod.getOrderManager();

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
        int l_intOrderExecutionCnt = 0;
        if (l_orderExecution != null)
        {
            l_intOrderExecutionCnt = l_orderExecution.length;
        }
        log.debug("l_intOrderExecutionCnt = " + l_intOrderExecutionCnt);
        
        Timestamp l_tsExecutionTime = null;
        
        //1.3 ���I�u�W�F�N�g�̐���LOOP
        int l_intOrderIndex = -1;
        for (int i = 0; i < l_intOrderExecutionCnt; i++)
        {
            //1.3.1 getExecutionQuantity()
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

            //1.3.3 ���ʁ^�P������v������I�u�W�F�N�g�����������ꍇ
            if ((l_dblExecPrice == l_dblExecutionPrice) 
                && (l_dblExecQuantity == l_dblExecutionQuantity))
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

        //�������Â��ق��̖��������ΏۂƂ���
        if (l_intOrderIndex != -1)
        {
            long l_lngOrderExecutionId = l_orderExecution[l_intOrderIndex].getOrderExecutionId();
            log.debug("l_lngOrderExecutionId = " + l_lngOrderExecutionId);

            //1.4 �敨OP�o���ʒm�X�V�C���^�Z�v�^()
            WEB3IfoExecuteNotifyUpdateInterceptor l_interceptor = new WEB3IfoExecuteNotifyUpdateInterceptor();

            //�L���[.���������C���^�Z�v�^�ɐݒ�B
            l_interceptor.setExecTimestamp(l_tsExecDate);

            //1.5 setThreadLocalPersistenceEventInterceptor(arg0 : IfoOrderManagerPersistenceEventInterceptor)
            //  [����] 
            //  arg0�F ���������敨OP�o���ʒm�X�V�C���^�Z�v�^
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);  

            //1.6 setThreadLocalPersistenceEventInterceptor(arg0 : IfoPositionManagerPersistenceEventInterceptor)
            //  [����] 
            //  arg0�F ���������敨OP�|�W�V�����X�V�C���^�Z�v�^
            WEB3IfoPositionUpdateInterceptor l_pInterceptor = new WEB3IfoPositionUpdateInterceptor();
            WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            l_positionManager.setThreadLocalPersistenceEventInterceptor(l_pInterceptor);  
        
            //1.7  getOrderId()
            long l_lngOrderID = l_reOrderUnit.getOrderId();
            log.debug("l_lngOrderID = " + l_lngOrderID);

            //1.8 DefaultUndoOrderFillMarketResponseMessage(long, long)
            //  [�R���X�g���N�^�̈���] 
            //  �����h�c�F �����P��.getOuderUnitId() 
            //  ���h�c�F�i�擾�������h�c�j            
            DefaultUndoOrderFillMarketResponseMessage l_undoOrderFillMarketResponseMessage = 
                new DefaultUndoOrderFillMarketResponseMessage(l_lngOrderID, l_lngOrderExecutionId);

            //1.9 process(MarketResponseMessage)
            //  [process()�Ɏw�肷�����] 
            //  ��������e�F�i����������������e�I�u�W�F�N�g�j
            MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
            IfoMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService = 
                (IfoMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            l_marketResponseReceiverCallbackService.process(l_undoOrderFillMarketResponseMessage);

            //1.10 update�g�����U�N�V����(long)(�敨OP�|�W�V�����}�l�[�W��::update�g�����U�N�V����)
            //  [update�g�����U�N�V����()�Ɏw�肷�����] 
            //  �����P�ʂh�c�F�@@�����P��.getOrderUnitId() 
            WEB3IfoPositionManagerImpl l_ifoPositionManagerImpl = (WEB3IfoPositionManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getPositionManager();
            l_ifoPositionManagerImpl.updateTransaction(l_reOrderUnit.getOrderUnitId());

            //1.11 �T�Z��n����̍X�V���s���B
            //  [�����̐ݒ�]
            //  �����P�ʁF�@@�����P��
            try
            {
                l_reOrderUnit = l_orderManager.getOrderUnit(l_reOrderUnit.getOrderUnitId());                    
                l_orderManager.updateEstimateDeliveryAmount(l_reOrderUnit);
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

            //1.12 get�⏕����()(�g���A�J�E���g�}�l�[�W��::get�⏕����)
            //  [����] 
            //  ����ID�F �����P��.����ID 
            //  �⏕����ID�F �����P��.�⏕����ID 
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            SubAccount l_subAccount = null;
            try
            {
                l_subAccount = l_accountManager.getSubAccount(
                l_reOrderUnit.getAccountId(),
                l_reOrderUnit.getSubAccountId());                
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

            //1.13 �]�͍Čv�Z(�⏕���� : �⏕����)
            //  [����] 
            //  �⏕�����F get�⏕����()�̖߂�l 
            if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
            {
                WEB3TPTradingPowerReCalcService l_tpTradingPowerReCalcService =
                (WEB3TPTradingPowerReCalcService) Services.getService(WEB3TPTradingPowerReCalcService.class);
                l_tpTradingPowerReCalcService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
            }

            //1.15 undoSendMail(OrderUnit)
            //  [sendMainProcess()�Ɏw�肷�����] 
            //  �����P�ʁF�@@�����P�ʃI�u�W�F�N�g 
            WEB3IfoExecutedMailSendService l_executedMailSendService = 
                (WEB3IfoExecutedMailSendService)Services.getService(WEB3IfoExecutedMailSendService.class);
            l_executedMailSendService.undoSendMail(l_reOrderUnit);
        }
        
        log.exiting(STR_METHOD_NAME);
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
