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
filename	WEB3RuitoMRFCancelAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�MRF�����t�P���T�[�r�X�����N���X(WEB3RuitoMRFCancelAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptUnitService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݓ�MRF�����t�P���T�[�r�X�����N���X<BR>
 * <BR>
 * ��������P�����Ƃ̎�t���������{����B<BR>
 */
public class WEB3RuitoMRFCancelAcceptUnitServiceImpl implements 
    WEB3RuitoMRFCancelAcceptUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFCancelAcceptUnitServiceImpl.class);

    /**
     * �ݓ�MRF�����t���������������Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ�MRF�����t�jnotify�����t�����v�Q�ƁB<BR>
     * 
     * �P�j�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()<BR>
     * �@@  ���R�[�����A�C���^�Z�v�^��ݒ肷��B<BR>
     *  �@@ [setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]<BR>
     * �@@  �C���^�Z�v�^�F ����.�ݓ���t�m��C���^�Z�v�^<BR>
     * 
     * �Q�j�@@RuitoMarketResponseReceiverCallbackService<BR>
     *       ���擾����B<BR>
     * <BR>
     * �R�j�@@DefaultCancelOrderAcceptedMarketResponseMessage<BR>
     *      �I�u�W�F�N�g�𐶐�����B<BR>
     *    �@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@����ID�F ����.�ݓ������P��.getOrderId()�̖߂�l<BR>
     * 
     * �S�j RuitoMarketResponseReceiverCallbackService.process()<BR>
     *      ���\�b�h<BR>
     *      ���R�[������B<BR>
     *    �@@[process�ɓn���p�����^]<BR>
     * �@@�@@���������t�ώs�ꉞ�����b�Z�[�W�F��������<BR>
     *      DefaultCancelOrderAcceptedMarketResponseMessage<BR>
     *      �I�u�W�F�N�g<BR>
     * <BR>
     * �T�j�@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A<BR>
     *      ��O���X���[����B<BR>
     *      �ݓ�MRF�����t�G���[:<BR>
     *      classpath:WEB3BusinessLayerException<BR>
     *      tag:BUSINESS_ERROR_00196<BR>
     *      code:210<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40890B9B0186
     */
    public void notifyCancelAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyCancelAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor " +
            "l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
    
        if (l_ruitoOrderUnit == null)
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

        WEB3RuitoOrderManager l_ruitoOrderMgr = 
        (WEB3RuitoOrderManager) l_tm.getOrderManager();
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();


        //�P�j�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);
        
        RuitoMarketResponseReceiverCallbackService l_service =
           (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor
           .getMarketResponseReceiverCallbackService();
            
        log.debug("OrderId = " + l_ruitoOrderUnit.getOrderId());
        
        DefaultCancelOrderAcceptedMarketResponseMessage 
            l_defaultCancelOrderAcceptedMarketResponseMessage =
            new DefaultCancelOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        ProcessingResult l_processingResult = null;
        l_processingResult =
            l_service.process(
                l_defaultCancelOrderAcceptedMarketResponseMessage);
                
        log.debug("l_processingResult.isFailedResult()=" + 
            l_processingResult.isFailedResult());
        
        //process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ
        if (l_processingResult.isFailedResult())
        {
            log.debug("__�ݓ�MRF�����t�G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00196,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ݓ�MRF�����t���s�����������Ȃ��B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ�MRF�����t�jnotify�����t���s�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()<BR>
     *    �@@���R�[�����A�C���^�Z�v�^��ݒ肷��B<BR>
     *    �@@[setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]<BR>
     * �@@�@@�C���^�Z�v�^�F ����.�ݓ���t�m��C���^�Z�v�^<BR>
     * <BR>
     * �Q�j�@@RuitoMarketResponseReceiverCallbackService���擾����B<BR>
     * <BR>
     * �R�j�@@DefaultCancelOrderRejectedMarketResponseMessage<BR>
     *      �I�u�W�F�N�g�𐶐�����B<BR>
     *  �@@  [�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@����ID�F ����.����ID<BR>
     * <BR>
     * �S�j�@@RuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������B
     * <BR>
     * �@@  [process�ɓn���p�����^]<BR>
     * �@@�@@����������ێs�ꉞ�����b�Z�[�W�F<BR>
     * �@@�@@��������DefaultCancelOrderRejectedMarketResponseMessage<BR>
     *     �I�u�W�F�N�g<BR>
     * <BR>
     * �T�j�@@process���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ�́A<BR>
     *    ��O���X���[����B<BR>
     *    �ݓ�MRF�����t�G���[:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00196<BR>
     *     code:210<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40890B9B0188
     */
    public void notifyCancelAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyCancelAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor " +
            "l_ruitoAcceptDecisionInterceptor)";
        log.entering(STR_METHOD_NAME);
    
        if (l_ruitoOrderUnit == null)
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

        WEB3RuitoOrderManager l_ruitoOrderMgr = 
            (WEB3RuitoOrderManager) l_tm.getOrderManager();
        MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();
                
        //�P�j�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
        l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
            l_ruitoAcceptDecisionInterceptor);

        //�Q�j�@@RuitoMarketResponseReceiverCallbackService���擾����B
        RuitoMarketResponseReceiverCallbackService l_service =
            (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor.getMarketResponseReceiverCallbackService();

        //�R�j�@@DefaultCancelOrderRejectedMarketResponseMessage
        //�I�u�W�F�N�g�𐶐�����B
        DefaultCancelOrderRejectedMarketResponseMessage 
            l_defaultCancelOrderRejectedMarketResponseMessage =
            new DefaultCancelOrderRejectedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

        ProcessingResult l_processingResult = null;
        //�S�j�@@RuitoMarketResponseReceiverCallbackService.process()
        //���\�b�h���R�[������B
        l_processingResult =
            l_service.process(
                l_defaultCancelOrderRejectedMarketResponseMessage);

        //�T�j�@@process���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ
        if (l_processingResult.isFailedResult())
        {
            log.debug("__�ݓ�MRF�����t�G���[__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00196,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "process���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇs");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
