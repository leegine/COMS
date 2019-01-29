head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoCancelAcceptedUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������t�P���T�[�r�X�����N���X  
                   (WEB3RuitoCancelAcceptedUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 �����F (���u) �V�K�쐬
                   2004/12/07 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;

/**
 * �ݓ������t�P���T�[�r�X�����N���X<BR>
 * <BR>
 * ��������P�����Ƃ̎�t���������{����B<BR>
 */
public class WEB3RuitoCancelAcceptedUnitServiceImpl
    implements WEB3RuitoCancelAcceptedUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3RuitoCancelAcceptedUnitServiceImpl.class);

    /**
     * �ݓ������t���������������Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * <BR>
     * �@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A<BR>
     *      ��O���X���[����B<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00305<BR>
     * <BR>
     * �@@�|�g���ݓ������}�l�[�W��.submitCancelOrder()�̖߂�l����<BR>
     * �@@ �@@�g���ݓ������}�l�[�W��.submitCancelOrder()�̖߂�l<BR>
     *           .getProcessingResult().isSuccessfulResult()==false<BR>
     * �@@   �̏ꍇ�A��O���X���[����B<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00240<BR>
     * <BR>
     * @@param l_ruitoOrderUnit - �ݓ������P�� <BR>
     * @@param l_ruitoAcceptedDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408891B40241
     */
    public void notifyCancelAcceptedComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptedComplete(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor "
                + "l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptedDecisionInterceptor == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class); 
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        WEB3RuitoOrderManager l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_tm.getOrderManager();
        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptedDecisionInterceptor);
      
        //1.2�@@RuitoMarketResponseReceiverCallbackService���擾���� 
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_ruitoMarketService =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

        
        //1.3�@@DefaultCancelOrderAcceptedMarketResponseMessage�I�u�W�F�N�g
        //�𐶐�����B
        long l_lngOrderId = 0L;
        l_lngOrderId = l_ruitoOrderUnit.getOrderId();
        DefaultCancelOrderAcceptedMarketResponseMessage 
        l_defaultCancelOrderAcceptedMarketResponseMessage = null;
        l_defaultCancelOrderAcceptedMarketResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);

        //1.4 RuitoMarketResponseReceiverCallbackService.process()
        //    ���\�b�h���R�[������B 
        log.debug("l_ruitoMarketResponseReceiverCallbackService.process()");
        ProcessingResult l_processingResult = null;
        l_processingResult =
            l_ruitoMarketService.process(
                l_defaultCancelOrderAcceptedMarketResponseMessage);
        
        //process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A��O���X���[����B
        boolean l_blnResult1 = l_processingResult.isFailedResult();        
        if (l_blnResult1)
        {
            log.debug("process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00305,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ");
        }

        // �g���A�J�E���g�}�l�[�W�����擾����B
        MainAccount l_mainAccount = null;                
        WEB3GentradeAccountManager l_gentradeAccMgr =
                    (WEB3GentradeAccountManager) l_finApp.getAccountManager();
                    
        long l_accountId = 0; //����ID
        long l_subAccountId = 0; //�⏕����ID
        l_accountId = l_ruitoOrderUnit.getAccountId();
        l_subAccountId = l_ruitoOrderUnit.getSubAccountId();
        SubAccount l_subAccount = null;
        
        //�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[�����A 
        //�⏕�����I�u�W�F�N�g���擾���� 
        try{  
            l_subAccount = l_gentradeAccMgr.getSubAccount(
                l_accountId ,l_subAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__",l_ex);
            throw new WEB3BaseException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80006,
               this.getClass().getName() + STR_METHOD_NAME,
               l_ex);                               
        }

        //����.�ݓ������P��.getDataSourceObject().getMRF�������ʃR�[�h()
        //�̖߂�l��null�Ŗ����ꍇ�A�ȉ��̏������s��
        String l_mrfRequstNumber = null;
        
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        l_mrfRequstNumber = l_ruitoOrderUnitRow.getMrfOrderRequestNumber();

        if (l_mrfRequstNumber != null)
        {
            try
            {
                RuitoOrderUnit l_ruitoOrderUnitMrf = 
                    l_web3RuitoOrderManager.getRuitoOrderUnit(
                        l_accountId,
                        l_subAccountId,
                        l_mrfRequstNumber);

                //1.6 ����.�ݓ���t�m��C���^�Z�v�^.set�����G���[���R�R�[�h()���R�[�X����
                l_ruitoAcceptedDecisionInterceptor.setOrderErrorReasonCode(null);
        
                //1.7 �g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor() 
                //���R�[�����A�C���^�Z�v�^��ݒ肷��B
                l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                    l_ruitoAcceptedDecisionInterceptor);
        
                //1.8 CancelOrderSpec�I�u�W�F�N�g�𐶐�����B
                CancelOrderSpec l_cancelOrderSpec = null;
                l_cancelOrderSpec = new CancelOrderSpec(l_ruitoOrderUnitMrf.getOrderId());
        
                //1.10 �g���A�J�E���g�}�l�[�W��.getMainAccount()���R�[�����A
                //�ڋq�I�u�W�F�N�g���擾����
                l_mainAccount = l_gentradeAccMgr.getMainAccount(l_ruitoOrderUnitMrf.getAccountId());
                
                //1.11 ����p�X���[�h�̎擾
                String l_tradingPassword = null;
                l_tradingPassword = l_mainAccount.getTradingPassword();
                WEB3Crypt l_crypt = new WEB3Crypt();
                
                //1.12 �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s��
                TradingModule l_tradingModule = 
                        l_finApp.getTradingModule(ProductTypeEnum.RUITO);
                MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
                WEB3RuitoMarketRequestSubmitServiceImpl l_web3RuitoMarketRequestSubmitService =
                        (WEB3RuitoMarketRequestSubmitServiceImpl)l_marketAdapter
                        .getMarketRequestSenderServce();
                
                l_web3RuitoMarketRequestSubmitService.setMarketSubmit(true);
                
                //1.13 �g���ݓ������}�l�[�W��.submitCancelOrder()���R�[������
                OrderSubmissionResult l_orderSubmissionResult = null;
                l_orderSubmissionResult =
                    l_web3RuitoOrderManager.submitCancelOrder(
                        l_subAccount,
                        l_cancelOrderSpec,
                        l_crypt.decrypt(l_tradingPassword),
                        true);
    
                //�g���ݓ������}�l�[�W��.submitCancelOrde()�̖߂�l����
                boolean l_blnResult = 
                    l_orderSubmissionResult.getProcessingResult().isSuccessfulResult();
                
                if (!l_blnResult)
                {
                    log.debug("����������s");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�g���ݓ������}�l�[�W��.submitCancelOrder()�̖߂�l" +
                        ".getProcessingResult().isSuccessfulResult()==false�̏ꍇ");
                }
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__",l_ex);
                throw new WEB3BaseException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                   this.getClass().getName() + STR_METHOD_NAME,
                   l_ex);                               
            }
        }
        
        //1.14 �|�]�͎c�����X�V 
        //�]�͍Čv�Z�T�[�r�X.�]�͍Čv�Z()���R�[������B
        WEB3TPTradingPowerReCalcService l_tPReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
        l_tPReCalcService.reCalcTradingPower(
                (WEB3GentradeSubAccount)l_subAccount);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ݓ������t���s�����������Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ݓ������t�jnotify�����t���s�v�Q�ƁB <BR>
     * <BR>
     *    �@@process���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A<BR>
     *      ��O���X���[����B<BR>
     *      class:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00305<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P�� <BR>
     * @@param l_ruitoAcceptedDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^ <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408891B50176
     */
    public void notifyCancelAcceptedFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptedDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCancelAcceptedFail"
                + "(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor "
                + "l_ruitoAcceptedDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptedDecisionInterceptor == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //1.1  �g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        // ���R�[�����A�C���^�Z�v�^��ݒ肷�� 
        OrderManager l_orderManager = null; //�����}�l�[�W��
        WEB3RuitoOrderManager l_web3RuitoOrderManager = null;

        //�g���ݓ������}�l�[�W��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        l_orderManager = l_tm.getOrderManager();
        l_web3RuitoOrderManager = (WEB3RuitoOrderManager) l_orderManager;
        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
        l_ruitoAcceptedDecisionInterceptor);

        //RuitoMarketResponseReceiverCallbackService���擾���� 

        //1.2 RuitoMarketResponseReceiverCallbackService���擾����B
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_ruitoMarketService =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();
        
        //1.3 DefaultNewOrderAcceptedMarketResponseMessage�I�u�W�F�N�g�𐶐�����    
        DefaultCancelOrderRejectedMarketResponseMessage 
        l_defaultNewOrderAcceptedMarketResponseMessage =
            new DefaultCancelOrderRejectedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        //1.4 RuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������    
        ProcessingResult l_presessingResult =
            l_ruitoMarketService.process(l_defaultNewOrderAcceptedMarketResponseMessage);

        //process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A
        //��O���X���[����
        boolean l_blnResult = l_presessingResult.isFailedResult();   
        log.debug("process()���\�b�h�̖߂�l.isFailedResult()�̒l = " + l_blnResult);
        
        if (l_blnResult)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00305,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ݓ������t���s�������s");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
