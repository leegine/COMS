head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�T�[�r�XImpl(WEB3AdminBondExecuteNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 �����(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultFillOrderUnitSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;

import webbroker3.bd.WEB3AdminBondDefaultInterceptor;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondFinTransactionManager;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����ʒm�T�[�r�XImpl)<BR>
 * �����ʒm�T�[�r�XImpl�N���X
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AdminBondExecuteNotifyServiceImpl implements WEB3AdminBondExecuteNotifyService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondExecuteNotifyServiceImpl.class);
    
    /**
     * @@roseuid 44E3362F0242
     */
    public WEB3AdminBondExecuteNotifyServiceImpl() 
    {
     
    }
    
    /**
     * (notify���)<BR>
     * notify���<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�unotify���v�Q�� <BR>
     * ------------------------------------------------<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    public void notifyExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyExecute(BondOrderUnit, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null || l_adminBondDefaultInterceptor == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //1.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g���� 
        //�����F���Ǘ��҃f�t�H���g�C���^�Z�v�^
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
        
        //1.2 get����n����v�Z����()
        //����n����v�Z���ʂ��擾 
        WEB3BondEstimatedPriceCalcResult l_calcResult = 
            l_adminBondDefaultInterceptor.getBondEstimatedPriceCalcResult();
        
        //1.3 get�P��()
        //�P�����擾
        double l_dblPrice = 0D;
        if (l_calcResult != null)
        {
            l_dblPrice = l_calcResult.getPrice().doubleValue();
        }
        
        double l_dblQuantity = 0D;
        //1.4 get����()
        //���ʂ��擾
        if (l_calcResult != null)
        {
            l_dblQuantity = l_calcResult.getQuantity().doubleValue();
        }
        
        //1.5 DefaultFillOrderUnitSpec(arg0 : long, arg1 : double, arg2 : double)
        //�����������e�𐶐� 
        //[DefaultFillOrderUnitSpec()�̈���] 
        //�����P��ID�F�g���������P��.get�����P��ID 
        //��萔�ʁFget�P�� 
        //���P���Fget���� 
        DefaultFillOrderUnitSpec l_defaultFillOrderUnitSpec =
            new DefaultFillOrderUnitSpec(
                l_bondOrderUnit.getOrderUnitId(),
                l_dblQuantity,
                l_dblPrice);
        
        //1.6 DefaultOrderFillMarketResponseMessage(arg0 : long, arg1 : FillOrderUnitSpec)
        //����胁�b�Z�[�W�𐶐� 
        //[DefaultOrderFillMarketResponseMessage()�̈���] 
        //����ID�F�g���������P��.get����ID 
        //FillOrderUnitSpec�FDefaultFillOrderUnitSpec
        DefaultOrderFillMarketResponseMessage l_defaultOrderFillMarketResponseMessage =
            new DefaultOrderFillMarketResponseMessage(
                l_bondOrderUnit.getOrderId(),
                l_defaultFillOrderUnitSpec);
        
        //1.7 process(arg0 : OrderFillMarketResponseMessage)
        //��菈�������s 
        //[process()�̈���] 
        //DefaultOrderFillMarketResponseMessage
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
        ProcessingResult l_processingResult = 
            l_marketResponseReceiverCallbackService.process(
                l_defaultOrderFillMarketResponseMessage);     
       log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (undo���)<BR>
     * undo���<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�uundo���v�Q�� <BR>
     * ------------------------------------------------<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777026E
     */
    public void undoExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "undoExecute(BondOrderUnit, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //1.1 getExecutions()
        //�������P�ʂ����������z����擾
        OrderExecution[] l_orderExcution = l_bondOrderUnit.getExecutions();
        
        //1.2 ��茏�������[�v
        int l_intLength = 0;
        if (l_orderExcution != null)
        {
            l_intLength = l_orderExcution.length;
        }
        
        for (int i = 0;i < l_intLength; i++)
        {
            //1.2.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor) 
            //�C���^�Z�v�^���Z�b�g���� 
            //�����F���Ǘ��҃f�t�H���g�C���^�Z�v�^
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3BondOrderManager l_bondOrderManager = 
                (WEB3BondOrderManager ) l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getOrderManager(); 
            l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
            
            //1.2.2 DefaultUndoOrderFillMarketResponseMessage(arg0 : long, arg1 : long)
            //��������������b�Z�[�W�𐶐� 
            //[DefaultUndoOrderFillMarketResponseMessage()�̈���] 
            //����ID�F����.�������P��.get����ID() 
            //�������ID�F���[�v���̍����.getOrderExecutionId()
            DefaultUndoOrderFillMarketResponseMessage l_undoOrderFillMarketResponseMessage =
                new DefaultUndoOrderFillMarketResponseMessage(
                    l_bondOrderUnit.getOrderId(),
                    l_orderExcution[i].getOrderExecutionId());
           
            //1.2.3 process(arg0 : UndoOrderFillMarketResponseMessage)
            //������������s�B 
            //[process()�̈���] 
            //DefaultUndoOrderFillMarketResponseMessage
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
            MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
            BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
                (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService();
            ProcessingResult l_processingResult = 
                l_marketResponseReceiverCallbackService.process(
                    l_undoOrderFillMarketResponseMessage);     
           log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
           
           //1.3 undo�m��g�����U�N�V����By�����P��ID(long)
           WEB3BondFinTransactionManager l_bondFinTransactionManager = 
               (WEB3BondFinTransactionManager) l_tradingMod.getFinTransactionManager();
           l_bondFinTransactionManager.undoTransactionByOrderUnitId(l_bondOrderUnit.getOrderUnitId());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (accept�V�K����)<BR>
     * accept�V�K���������s<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�uaccept�V�K�����v�Q�� <BR>
     * ------------------------------------------------<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^
     * @@throws WEB3BaseException
     * @@roseuid 44D852EA01B7
     */
    public void acceptNewOrder(long l_lngOrderId, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException
    {  
        final String STR_METHOD_NAME = "acceptNewOrder(long, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g���� 
        //�����F���Ǘ��҃f�t�H���g�C���^�Z�v�^
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
        
        //1.2 DefaultNewOrderAcceptedMarketResponseMessage(arg0 : long)
        //�V�K������t���b�Z�[�W�𐶐� 
        //[DefaultNewOrderAcceptedMarketResponseMessage()�̈���] 
        //����ID�F����.����ID() 
        DefaultNewOrderAcceptedMarketResponseMessage l_newAcceptedResponseMessage =
            new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
        
        //1.3 process(arg0 : NewOrderAcceptedMarketResponseMessage)
        //�V�K������t���������s 
        //[process�̈���] 
        //DefaultNewOrderAcceptedMarketResponseMessage
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService(); 
        ProcessingResult l_processingResult = 
            l_marketResponseReceiverCallbackService.process(
                l_newAcceptedResponseMessage);     
       log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (accept�������)<BR>
     * accept����������������s<BR>
     * <BR>
     * <BR>
     * �V�[�P���X�}�uaccept��������v�Q�� <BR>
     * ------------------------------------------------<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^
     * @@throws WEB3BaseException
     * @@roseuid 44D94E1F00CC
     */
    public void acceptOrderCancel(long l_lngOrderId,
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "acceptOrderCancel(long, WEB3AdminBondDefaultInterceptor)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 setThreadLocalPersistenceEventInterceptor(arg0 : BondOrderManagerPersistenceEventInterceptor)
        //�C���^�Z�v�^���Z�b�g���� 
        //�����F���Ǘ��҃f�t�H���g�C���^�Z�v�^
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager ) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager(); 
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_adminBondDefaultInterceptor);
        
        //1.2 DefaultCancelOrderAcceptedMarketResponseMessage(arg0 : long)
        //�����������t���b�Z�[�W�𐶐� 
        //[DefaultCancelOrderAcceptedMarketResponseMessage()�̈���] 
        //����ID�F����.����ID
        DefaultCancelOrderAcceptedMarketResponseMessage l_cancelResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);

        //1.3 process(arg0 : CancelOrderAcceptedMarketResponseMessage)
        //���������t���������s 
        //[process�̈���] 
        //DefaultCancelOrderAcceptedMarketResponseMessage
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        MarketAdapter l_marketAdapter = l_tradingMod.getMarketAdapter();
        BondMarketResponseReceiverCallbackService l_marketResponseReceiverCallbackService =
            (BondMarketResponseReceiverCallbackService)l_marketAdapter.getMarketResponseReceiverCallbackService(); 
        ProcessingResult l_processingResult = 
            l_marketResponseReceiverCallbackService.process(
                l_cancelResponseMessage);     
       log.debug("l_processingResult.isSuccessfulResult() = " + l_processingResult.isSuccessfulResult());
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
