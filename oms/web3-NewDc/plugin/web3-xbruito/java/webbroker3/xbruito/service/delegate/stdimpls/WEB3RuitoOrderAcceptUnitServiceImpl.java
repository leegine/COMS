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
filename	WEB3RuitoOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�������tUnitServiceImpl (WEB3RuitoOrderAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  ���E (���u) �V�K�쐬
                   2004/12/07 ��O�� (���u) �c�Ή�
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;
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
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;
/**
 * �ݓ�������t�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �����P�����Ƃ̎�t���������{����B<BR>
 */
public class WEB3RuitoOrderAcceptUnitServiceImpl
    implements WEB3RuitoOrderAcceptUnitService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoOrderAcceptUnitServiceImpl.class);
    /**
     * ������t���s�������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ݓ�������t�jnotify������t���s�v�Q�ƁB <BR>
     * <BR>
     * �@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��<BR>
     *       true�̏ꍇ�́A��O���X���[����B�B<BR>
     *        class    : WEB3BusinessLayerException<BR>
     *        tag      : BUSINESS_ERROR_00239<BR>
     * <BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F66602ED
     */
    public void notifyOrderAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptFail(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptDecisionInterceptor == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        //1.1 �g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[�����A�C���^�Z�v�^��ݒ肷��
        WEB3RuitoOrderManager l_web3RuitoOrderManager = null; //�g���ݓ������}�l�[�W��
        l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.RUITO).getOrderManager();
        
        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);
        
        //1.2 RuitoMarketResponseReceiverCallbackService���擾����
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        RuitoMarketResponseReceiverCallbackService l_service =
            (RuitoMarketResponseReceiverCallbackService) 
                l_marketAdapter.getMarketResponseReceiverCallbackService();
        
        //1.3 DefaultNewOrderRejectedMarketResponseMessage�I�u�W�F�N�g�𐶐�����
        long l_lnOrderId = 0;
        l_lnOrderId = l_ruitoOrderUnit.getOrderId();
        DefaultNewOrderRejectedMarketResponseMessage l_defaultNewOrderRejectedMarketResponseMessage =
            null;
        l_defaultNewOrderRejectedMarketResponseMessage =
            new DefaultNewOrderRejectedMarketResponseMessage(l_lnOrderId);
        
        //1.4 RuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������
        ProcessingResult l_processingResult = null;
        log.debug("begin ProcessingResult");
        l_processingResult =
            l_service.process(
                l_defaultNewOrderRejectedMarketResponseMessage);
        log.debug(" l_processingResult = " + l_processingResult);
        
        //process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A��O���X���[����B
        log.debug("l_processingResult.isFailedResult() = " + l_processingResult.isFailedResult());
        if (l_processingResult.isFailedResult())
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00239,
                STR_METHOD_NAME);
        }
        
        log.debug("end if (l_processingResult.isFailedResult())");
        
        //�g���A�J�E���g�}�l�[�W�����擾����B
        WEB3GentradeAccountManager l_gentradeAccountManaer = null;
        l_gentradeAccountManaer =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        log.debug("l_gentradeAccountManaer =" + l_gentradeAccountManaer);
        
        //�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[�����A�⏕�����I�u�W�F�N�g���擾����.        
        SubAccount l_subAccount = null;
        long l_lnAccountId = l_ruitoOrderUnit.getAccountId();//����ID
        long l_lnSubAccountId = l_ruitoOrderUnit.getSubAccountId();//�⏕����ID
        log.debug("l_lnAccountId  = " + l_lnAccountId); 
        log.debug("l_lnSubAccountId  = " + l_lnSubAccountId);
        try{
        l_subAccount =
            l_gentradeAccountManaer.getSubAccount(
                l_lnAccountId,
                l_lnSubAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ with �⏕�����I�u�W�F�N�g���擾����", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "."+ STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("l_subAccount = " + l_subAccount);
        
        //����.�ݓ������P��.getDataSourceObject().getMRF�������ʃR�[�h()�̖߂�l��null�Ŗ����ꍇ�A�ȉ��̏������s��
        RuitoOrderUnitRow l_ruitoOrderUnitRow =
            (RuitoOrderUnitRow) l_ruitoOrderUnit.getDataSourceObject();
        log.debug("l_ruitoOrderUnitRow  = " + l_ruitoOrderUnitRow);
        
        // ����.�ݓ������P��.getDataSourceObject().getMRF�������ʃR�[�h()
        String l_StrMrfRequstNumber = l_ruitoOrderUnitRow.getMrfOrderRequestNumber();
        log.debug("l_StrMrfRequstNumber  = " + l_StrMrfRequstNumber);
        log.debug("entry  if (l_StrMrfRequstNumber != null)");
        
        //1.5 
        if (l_StrMrfRequstNumber != null)
        {
            //1.5.1 �ݓ������P�ʃI�u�W�F�N�g���擾����B 
            RuitoOrderUnit l_ruitoOrderUnitEx = null;
            try
            {
                l_ruitoOrderUnitEx = 
                    l_web3RuitoOrderManager.getRuitoOrderUnit(
                        l_lnAccountId,
                        l_lnSubAccountId,
                        l_StrMrfRequstNumber);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "."+ STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //1.5.2 ����.�ݓ���t�m��C���^�Z�v�^.set�����G���[���R�R�[�h()���R�[�X����
            l_ruitoAcceptDecisionInterceptor.setOrderErrorReasonCode(null);
            log.debug(
                "l_ruitoAcceptDecisionInterceptor.getOrderErrorReasonCode() = "
                    + l_ruitoAcceptDecisionInterceptor.getOrderErrorReasonCode());
            //1.5.3 �g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()���R�[�����A�C���^�Z�v�^��ݒ肷��
            l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);
            log.debug(
                "l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor() = "
                    + l_web3RuitoOrderManager
                        .getThreadLocalPersistenceEventInterceptor());

            // 1.5.4 �|CancelOrderSpec�I�u�W�F�N�g�𐶐�����B
            // �@@�@@[�R���X�g���N�^�ɓn���p�����^]<BR>
            // �@@�@@�@@����ID�F<BR>
            //        �擾�����ݓ������P�ʃI�u�W�F�N�g.getOrderId()�̖߂�l<BR>
            CancelOrderSpec l_cancelOrderSpec = new CancelOrderSpec(l_ruitoOrderUnitEx.getOrderId());

            log.debug("l_cancelOrderSpec =" + l_cancelOrderSpec);

            MainAccount l_mainAccount = null;
            try
            {
                log.debug("l_lnAccountId = " + l_lnAccountId);
                log.debug("l_lnSubAccountId = " + l_lnSubAccountId);
                //1.5.5 �ڋq�I�u�W�F�N�g���擾����B 
                l_mainAccount =
                    l_gentradeAccountManaer.getMainAccount(l_lnAccountId);
                log.debug("l_mainAccount = " + l_mainAccount);
            }
            catch (NotFoundException l_ex)
            {
                log.error("__NotFoundException__ ", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "."+ STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //1.5.7 ����p�X���[�h�̎擾
            String l_tradingPassword = null;
            l_tradingPassword = l_mainAccount.getTradingPassword();
            WEB3Crypt l_crypt = new WEB3Crypt();
            log.debug("l_tradingPassword =" + l_tradingPassword);
            //�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s��
            WEB3RuitoMarketRequestSubmitServiceImpl l_ruitoMarketRequestSubmitServiceImpl =
                null;
            
            //1.5.8 �ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X
            l_ruitoMarketRequestSubmitServiceImpl =
                (WEB3RuitoMarketRequestSubmitServiceImpl) l_marketAdapter
                    .getMarketRequestSenderServce();
            l_ruitoMarketRequestSubmitServiceImpl.setMarketSubmit(true);
            
            //1.5.9 �g���ݓ������}�l�[�W��.submitCancelOrder()���R�[������
            OrderSubmissionResult l_orderSubmissionResult = null;
                l_orderSubmissionResult =
                    l_web3RuitoOrderManager.submitCancelOrder(
                        l_subAccount,
                        l_cancelOrderSpec,
                        l_crypt.decrypt(l_tradingPassword),
                        true);
            log.debug("l_orderSubmissionResult = " + l_orderSubmissionResult);
            
            //�g���ݓ������}�l�[�W��.submitCancelOrde()�̖߂�l����

            if (!l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                log.debug("����������s");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00240,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�g���ݓ������}�l�[�W��.submitCancelOrde()�̖߂�l." +
                        "getProcessingResult().isSuccessfulResult()==false�̏ꍇ");
            }
        }
        // 1.6�@@�]�͎c�����X�V
        //�]�͍Čv�Z�T�[�r�X.�]�͍Čv�Z()���R�[������B
        WEB3TPTradingPowerReCalcService l_tPReCalcService = 
            (WEB3TPTradingPowerReCalcService) Services.getService(
                    WEB3TPTradingPowerReCalcService.class);
        l_tPReCalcService.reCalcTradingPower(
                (WEB3GentradeSubAccount)l_subAccount);
        
        log.exiting(STR_METHOD_NAME);
    }
    /**
     * �ݓ�������t���������������Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�ݓ�������t�jnotify������t�����v�Q�ƁB <BR>
     * <BR>
     * �@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��<BR>
     *       false�̏ꍇ�́A<BR>
     *       ��O���X���[����B<BR>
     *        class    : WEB3BusinessLayerException<BR>
     *        tag      : BUSINESS_ERROR_00239<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F66602EE
     */
    public void notifyOrderAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyOrderAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit,"
                + "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor))";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null
            || l_ruitoAcceptDecisionInterceptor == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l��NULL");
        }

        //1.1 �g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor() ���R�[�����A�C���^�Z�v�^��ݒ肷��
        WEB3RuitoOrderManager l_web3RuitoOrderManager = null;
        //�g���ݓ������}�l�[�W��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        l_web3RuitoOrderManager =
            (WEB3RuitoOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getOrderManager();

        l_web3RuitoOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);
        //1.2 RuitoMarketResponseReceiverCallbackService���擾����
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.RUITO);
        
        MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
        MarketResponseReceiverCallbackService l_service =
            l_marketAdapter.getMarketResponseReceiverCallbackService();
        //1.3 DefaultNewOrderAcceptedMarketResponseMessage�I�u�W�F�N�g�𐶐�����
        DefaultNewOrderAcceptedMarketResponseMessage l_defaultNewCashBasedOrderMarketRequestMessage =
            new DefaultNewOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());
        //RuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������
        ProcessingResult l_presessingResult = null;
        l_presessingResult =
            l_service.process(l_defaultNewCashBasedOrderMarketRequestMessage);

        //1.4 process()���\�b�h�̖߂�l.isFailedResult()�̒l��false�̏ꍇ�́A��O���X���[����
        log.debug("l_presessingResult.isFailedResult() = " + l_presessingResult.isFailedResult());

        if (l_presessingResult.isFailedResult())
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00239,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "process()���\�b�h�̖߂�l.isFailedResult()�̒l��false�̏ꍇ");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
