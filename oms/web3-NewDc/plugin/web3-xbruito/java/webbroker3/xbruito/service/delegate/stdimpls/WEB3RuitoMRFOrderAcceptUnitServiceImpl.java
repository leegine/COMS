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
filename	WEB3RuitoMRFOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ�MRF������t�P���T�[�r�X�����N���X(WEB3RuitoMRFOrderAcceptUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ��O�� (���u) �V�K�쐬
*/
package webbroker3.xbruito.service.delegate.stdimpls;

import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.market.messages.DefaultRuitoNewOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderRejectedMarketResponseMessage;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketRequestSubmitServiceImpl;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.WEB3RuitoOrderManager;
import webbroker3.xbruito.WEB3RuitoProductManager;
import webbroker3.xbruito.WEB3RuitoProduct;
import webbroker3.xbruito.WEB3RuitoTradedProduct;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * �ݓ�MRF������t�P���T�[�r�X�����N���X<BR>
 * <BR>
 * �����P�����Ƃ̎�t���������{����B<BR>
 */
public class WEB3RuitoMRFOrderAcceptUnitServiceImpl
    implements WEB3RuitoMRFOrderAcceptUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoMRFOrderAcceptUnitServiceImpl.class);

    /**
     * �ݓ�MRF������t���s�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ�MRF������t�jnotify������t���s�v�Q�ƁB<BR>
     * <BR>
     * �P�j�@@�g���ݓ������}�l�[�W��.<BR>
     *    setThreadLocalPersistenceEventInterceptor()<BR>
     * �@@���R�[�����A�C���^�Z�v�^��ݒ肷��B<BR>
     * �@@[setThreadLocalPersistenceEventInterceptor��<BR>
     *   �n���p�����^]<BR>
     * �@@�C���^�Z�v�^�F ����.�ݓ���t�m��C���^�Z�v�^<BR>
     * <BR>
     * �Q�j RuitoMarketResponseReceiverCallbackService<BR>
     *   ���擾����B<BR>
     * <BR>
     * �R�j�@@DefaultNewOrderRejectedMarketResponseMessage<BR>
     *   �I�u�W�F�N�g�𐶐�����B<BR>
     * �@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@����ID�F ����.�ݓ������P��.getOrderId()�̖߂�l<BR>
     * <BR>
     * �S�j�@@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *    ���\�b�h���R�[������B<BR>
     * �@@[process�ɓn���p�����^]<BR>
     * �@@����������ێs�ꉞ�����b�Z�[�W�F<BR>
     * �@@��������<BR>
     *   DefaultNewOrderRejectedMarketResponseMessage<BR>
     *   �I�u�W�F�N�g<BR>
     * <BR>
     * �T�j�@@process()���\�b�h�̖߂�l.isFailedResult()��<BR>
     *   �l��true�̏ꍇ�́A��O���X���[����B<BR>
     *   �ݓ�MRF������t�G���[:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00238<BR>
     *     code:252<BR>
     * <BR>
     * �U�j�@@����.�ݓ������P��.getDataSourceObject().<BR>
     *    getMRF�������ʃR�[�h()�̖߂�l��<BR>
     * �@@���������ʃR�[�h�����ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@[get�����P�ʂɓn���p�����^]<BR>
     * �@@�@@�⏕����ID�F ����.�ݓ������P��.getSubAccountId()�̖߂�l<BR>
     * �@@�@@���ʃR�[�h�F �擾����MRF�������ʃR�[�h<BR>
     * <BR>
     * �V�j�@@�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()<BR>
     * �@@���R�[�����A�C���^�Z�v�^��ݒ肷��B<BR>
     * �@@[setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]<BR>
     * �@@�@@�C���^�Z�v�^�F ����.�ݓ���t�m��C���^�Z�v�^<BR>
     * <BR>
     * �W�j�@@DefaultNewOrderRejectedMarketResponseMessage<BR>
     *   �I�u�W�F�N�g�𐶐�����B<BR>
     * �@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@����ID�F �擾�����ݓ������P��.getOrderId()�̖߂�l<BR>
     * <BR>
     * �X�j�@@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *   ���\�b�h���R�[������B<BR>
     * �@@[process�ɓn���p�����^]<BR>
     * �@@�@@����������ێs�ꉞ�����b�Z�[�W�F<BR>
     * �@@�@@��������<BR>
     *   DefaultNewOrderRejectedMarketResponseMessage�I�u�W�F�N�g<BR>
     * <BR>
     * �P�O�j�@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��false�̏ꍇ�́A<BR>
     *   ��O���X���[����B<BR>
     *   �ݓ�MRF������t�G���[:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00238<BR>
     *     code:252<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408760DE006C
     */
    public void notifyOrderAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyOrderAcceptFail(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)";
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
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

            WEB3RuitoOrderManager l_ruitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();
            MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();


            //�P�j�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
            //���R�[�����A�C���^�Z�v�^��ݒ肷��B
            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //�Q�j RuitoMarketResponseReceiverCallbackService���擾����B
            RuitoMarketResponseReceiverCallbackService l_service =
               (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor
               .getMarketResponseReceiverCallbackService();

            //�R�jDefaultNewOrderRejectedMarketResponseMessage�I�u�W�F�N�g�𐶐�����B
            DefaultNewOrderRejectedMarketResponseMessage
                l_defaultNewOrderRejectedMarketResponseMessage =
                new DefaultNewOrderRejectedMarketResponseMessage(
                    l_ruitoOrderUnit.getOrderId());

            //�S�jRuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������B
            ProcessingResult l_processingResult =
                    l_service.process(
                    l_defaultNewOrderRejectedMarketResponseMessage);
            //�T�jprocess()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ
            if (l_processingResult.isFailedResult())
            {
                log.debug("�ݓ�MRF������t�G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00238,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "process()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ");
            }

            RuitoOrderUnitParams l_ruitoOrderUnitParams =
                ((RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject());
            //�U�jgetMRF�������ʃR�[�h()
            String l_lngMRFOrderRequestNumber =
                l_ruitoOrderUnitParams.getMrfOrderRequestNumber();
            log.debug("RuitoOrderUnit.MRF�������ʃR�[�h = " + l_lngMRFOrderRequestNumber);

            //���ʃR�[�h�����ݓ������P�ʃI�u�W�F�N�g���擾����B
            RuitoOrderUnit l_ruitoOrderUnitMRF =
                l_ruitoOrderMgr.getRuitoOrderUnit(
                l_ruitoOrderUnit.getAccountId(),
                l_ruitoOrderUnit.getSubAccountId(),
                l_lngMRFOrderRequestNumber);

            //�V�j�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
            //���R�[�����A�C���^�Z�v�^��ݒ肷��B
            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //�W�jDefaultNewOrderRejectedMarketResponseMessage�I�u�W�F�N�g�𐶐�����B
            DefaultNewOrderRejectedMarketResponseMessage
                l_marketResponseMessage =
                new DefaultNewOrderRejectedMarketResponseMessage(
                    l_ruitoOrderUnitMRF.getOrderId());
            log.debug("ruitoOrderUnit.getOrderId() = " + l_ruitoOrderUnitMRF.getOrderId());

            //�X�jRuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������B
            l_processingResult = l_service.process(l_marketResponseMessage);

            //�P�O�jprocess()���\�b�h�̖߂�l.isFailedResult()�̒l��false�̏ꍇ
            boolean l_blnProcessResult = l_processingResult.isFailedResult();
            log.debug("process()���\�b�h�̖߂�l = " + l_blnProcessResult);
            if (l_blnProcessResult)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00238,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (NotFoundException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ݓ�MRF������t�����������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݓ�MRF������t�jnotify������t�����v�Q�ƁB<BR>
     * <BR>
     * �P�j�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()<BR>
     * �@@���R�[�����A�C���^�Z�v�^��ݒ肷��B<BR>
     * �@@[setThreadLocalPersistenceEventInterceptor�ɓn���p�����^]<BR>
     * �@@�@@�C���^�Z�v�^�F ����.�ݓ���t�m��C���^�Z�v�^<BR>
     * <BR>
     * �Q�j�@@RuitoMarketResponseReceiverCallbackService���擾����B<BR>
     *
     * �R�jDefaultNewOrderAcceptedMarketResponseMessage<BR>
     *     �I�u�W�F�N�g�𐶐�����B<BR>
     * �@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@����ID�F ����.�ݓ������P��.getOrderId()�̖߂�l<BR>
     * <BR>
     * �S�j�@@RuitoMarketResponseReceiverCallbackService.process()<BR>
     *    ���\�b�h���R�[������B<BR>
     *  �@@[process�ɓn���p�����^]<BR>
     * �@@ ���������t�ώs�ꉞ�����b�Z�[�W�F<BR>
     * �@@ ��������<BR>
     *    DefaultNewOrderAcceptedMarketResponseMessage<BR>
     *    �I�u�W�F�N�g<BR>
     * <BR>
     * �T�j�@@process()���\�b�h�̖߂�l.isFailedResult()�̒l��<BR>
     *    true�̏ꍇ�́A��O���X���[����B<BR>
     *    �ݓ�MRF������t�G���[:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00238<BR>
     *     code:252<BR>
     * <BR>
     * �U�j����.�ݓ������P��.getDataSourceObject().getMRF�������ʃR�[�h()<BR>
     *    �̖߂�l�ɑΉ�����<BR>
     *  �@@�ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�|�g���ݓ������}�l�[�W��.get�����P��()���R�[�����A<BR>
     *    �擾����MRF�������ʃR�[�h�Ɠ�����<BR>
     * �@@ ���ʃR�[�h�����ݓ������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@ [get�����P�ʂɓn���p�����^]<BR>
     * �@@ �⏕����ID�F ����.�ݓ������P��.getSubAccountId()�̖߂�l<BR>
     * �@@�@@�@@���ʃR�[�h�F <BR>
     *     ����.�ݓ������P��.getDataSourceObject().getMRF�������ʃR�[�h()<BR>
     *     �̖߂�l<BR>
     * <BR>
     * �V�j�s��A�_�v�^���A�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X���擾����B<BR>
     *
     * �W�j�@@�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA<BR>
     *     �s�ꑗ�M���������{����Ƃ����ݒ���s���B<BR>
     * �@@�@@�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X.set�s�ꑗ�M��()���R�[������B<BR>
     * �@@�@@[.set�s�ꑗ�M�ۂɓn���p�����^]<BR>
     * �@@�@@is�s�ꑗ�M�F true<BR>
     * <BR>
     * �X�j�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[�����A<BR>
     *     �⏕�����I�u�W�F�N�g���擾����<BR>
     * �@@�@@[getSubAccount�ɓn���p�����^]<BR>
     * �@@�@@����ID�F <BR>
     *      �擾�����ݓ������P�ʃI�u�W�F�N�g.getAccountId()�̖߂�l<BR>
     * �@@�@@�⏕����ID�F <BR>
     *     �擾�����ݓ������P�ʃI�u�W�F�N�g.getSubAccountId()�̖߂�l<BR>
     * <BR>
     * �P�O�j�@@�g���ݓ������I�u�W�F�N�g���擾����B<BR>
     *     �g���ݓ������}�l�[�W��.get�ݓ�����()���R�[�����A<BR>
     *     �g���ݓ������I�u�W�F�N�g���擾����B<BR>
     *   �@@[get�ݓ������ɓn���p�����^]<BR>
     * �@@�@@����ID�F <BR>
     *     �擾����<BR>
     *     �ݓ������P�ʃI�u�W�F�N�g.getDataSourceObject().getProductId()<BR>
     *     �̖߂�l<BR>
     * <BR>
     * �P�P�j�@@�g���ݓ������}�l�[�W��.get�ݓ��������()���R�[�����A<BR>
     *     �g���ݓ�����������擾����B<BR>
     *   �@@[get�ݓ���������ɓn���p�����^]<BR>
     * �@@�@@�،���ЁF<BR>
     *      �擾�����⏕�����I�u�W�F�N�g.getInstitution()�̖߂�l<BR>
     * �@@�@@�����R�[�h�F <BR>
     *     �擾�����g���ݓ������I�u�W�F�N�g.getProductCode()<BR>
     *     �̖߂�l<BR>
     * <BR>
     * �P�Q�j�@@DefaultRuitoNewOrderMarketRequestMessage<BR>
     *     �I�u�W�F�N�g�𐶐�����B<BR>
     *   �@@[�R���X�g���N�^�ɓn���p�����^]<BR>
     * �@@�@@�⏕�����F �擾�����⏕�����I�u�W�F�N�g<BR>
     * �@@�@@��������F �擾�����g���ݓ���������I�u�W�F�N�g<BR>
     * �@@�@@�ݓ������P��Row�F<BR>
     *     �擾�����ݓ������P��.getDataSourceObject()�̖߂�l<BR>
     * <BR>
     * �P�R�j�@@�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X.<BR>
     *     �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M()���\�b�h���R�[������B<BR>
     *   �@@[�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M�ɓn���p�����^]<BR>
     * �@@�@@�ݓ��V�K�����s�ꃊ�N�G�X�g���b�Z�[�W�F <BR>
     * �@@�@@��������<BR>
     *     DefaultRuitoNewOrderMarketRequestMessage�I�u�W�F�N�g<BR>
     * <BR>
     * �P�S�j�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X.<BR>
     *      �V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M()<BR>
     *     �̖߂�l����<BR>
     * �@@�@@�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M()��<BR>
     *     �߂�l.getProcessingResult().isSuccessfulResult()==false<BR>
     * �@@�@@�̏ꍇ�A��O���X���[����B<BR>
     *     �V�K�����s�ꃁ�b�Z�[�W���M�G���[:<BR>
     *     classpath:WEB3BusinessLayerException<BR>
     *     tag:BUSINESS_ERROR_00237<BR>
     * @@param l_ruitoOrderUnit - �ݓ������P��<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - �ݓ���t�m��C���^�Z�v�^<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 408760DE008C
     */
    public void notifyOrderAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "notifyOrderAcceptComplete(RuitoOrderUnit l_ruitoOrderUnit, " +
            "WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)";
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

        try
        {
            //�P�j�g���ݓ������}�l�[�W��.setThreadLocalPersistenceEventInterceptor()
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tm = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

            WEB3RuitoOrderManager l_ruitoOrderMgr =
                (WEB3RuitoOrderManager) l_tm.getOrderManager();
            MarketAdapter l_marketAdaptor = l_tm.getMarketAdapter();

            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //�Q�j�@@RuitoMarketResponseReceiverCallbackService���擾����B
            RuitoMarketResponseReceiverCallbackService l_service =
               (RuitoMarketResponseReceiverCallbackService)l_marketAdaptor
               .getMarketResponseReceiverCallbackService();

            log.debug("l_ruitoOrderUnit.getOrderId()=" + l_ruitoOrderUnit.getOrderId());
            //�R�jDefaultNewOrderAcceptedMarketResponseMessage�u�W�F�N�g�𐶐�����B
            DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(
                l_ruitoOrderUnit.getOrderId());

            log.debug("=========test========");
            //�S�jRuitoMarketResponseReceiverCallbackService.process()���\�b�h���R�[������B
            ProcessingResult l_processingResult =
                l_service.process(l_responseMessage);

            //�T�jprocess()���\�b�h�̖߂�l.isFailedResult()�̒l��true�̏ꍇ
            boolean l_blnProcessResult = l_processingResult.isFailedResult();
            log.debug("process()���\�b�h�̖߂�l = " + l_blnProcessResult);
            if (l_blnProcessResult)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00238,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            //�U�j����.�ݓ������P��.getDataSourceObject().getMRF�������ʃR�[�h()
            RuitoOrderUnitParams l_ruitoOrderUnitParams =
                ((RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject());

            //getMRF�������ʃR�[�h()
            String l_lngMRFOrderRequestNumber =
                l_ruitoOrderUnitParams.getMrfOrderRequestNumber();

            log.debug("l_ruitoOrderMgr.getRuitoOrderUnit()");
            log.debug("AccountId = " + l_ruitoOrderUnit.getAccountId());
            log.debug("SubAccountId = " + l_ruitoOrderUnit.getSubAccountId());
            log.debug("RuitoOrderUnit.MRF�������ʃR�[�h = " + l_lngMRFOrderRequestNumber);

            RuitoOrderUnit l_ruitoOrderUnitMRF =
            l_ruitoOrderMgr.getRuitoOrderUnit(
                l_ruitoOrderUnit.getAccountId(),
                l_ruitoOrderUnit.getSubAccountId(),
                l_lngMRFOrderRequestNumber);        //NotFoundException

            l_ruitoOrderMgr.setThreadLocalPersistenceEventInterceptor(
                l_ruitoAcceptDecisionInterceptor);

            //�V�j�s��A�_�v�^���A�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X���擾����B
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            WEB3RuitoMarketRequestSubmitServiceImpl l_marketRequestSubmitService =
                (WEB3RuitoMarketRequestSubmitServiceImpl)
                l_marketAdapter.getMarketRequestSenderServce();

            //�W�j�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X�ɁA�s�ꑗ�M���������{����Ƃ����ݒ���s���B
            l_marketRequestSubmitService.setMarketSubmit(true);

            //�X�j�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[����
            WEB3GentradeAccountManager l_accMgr =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();

            //NotFoundException
            SubAccount l_subAccount =
                    l_accMgr.getSubAccount(
                    l_ruitoOrderUnitMRF.getAccountId(),
                    l_ruitoOrderUnitMRF.getSubAccountId());

            //�P�O�j�g���ݓ������I�u�W�F�N�g���擾����B
            WEB3RuitoProductManager l_ruitoProductManager =
                (WEB3RuitoProductManager) l_finApp
                    .getTradingModule(ProductTypeEnum.RUITO)
                    .getProductManager();

            RuitoOrderUnitRow l_ruitoOrderUnitRow =
                (RuitoOrderUnitRow) l_ruitoOrderUnitMRF.getDataSourceObject();

            log.debug("l_ruitoProductManager.getRuitoProduct()");
            log.debug("l_ruitoOrderUnitRow.getProductId()=" + l_ruitoOrderUnitRow.getProductId());
            WEB3RuitoProduct l_ruitoProduct =
                (WEB3RuitoProduct) l_ruitoProductManager.getRuitoProduct(
                l_ruitoOrderUnitRow.getProductId());

            //�P�P�j�g���ݓ������}�l�[�W��.get�ݓ��������()���R�[�����A
            //�g���ݓ�����������擾����B
            log.debug("InstitutionCode = " + l_subAccount.getInstitution().getInstitutionCode());
            log.debug("ProductCode = " + l_ruitoProduct.getProductCode());
            log.debug("l_ruitoProductManager.getRuitoTradedProduct() begin");
            WEB3RuitoTradedProduct l_ruitoTradedProduct =
                    (WEB3RuitoTradedProduct) l_ruitoProductManager
                    .getRuitoTradedProduct(
                    l_subAccount.getInstitution(),
                    l_ruitoProduct.getProductCode());
            log.debug("l_ruitoProductManager.getRuitoTradedProduct() end");
            //�P�Q�jDefaultRuitoNewOrderMarketRequestMessage�I�u�W�F�N�g�𐶐�����B
            DefaultRuitoNewOrderMarketRequestMessage l_marketRequestMessage =
                new DefaultRuitoNewOrderMarketRequestMessage(
                    l_subAccount,
                    l_ruitoTradedProduct,
                    l_ruitoOrderUnitRow);

            //�P�R�j�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X.�V�K�����s�ꃊ�N�G�X�g
            //���b�Z�[�W���M()���\�b�h���R�[������B
            MarketRequestSendResult l_marketRequestSendResult =
                l_marketRequestSubmitService.send(l_marketRequestMessage);

            //�P�S�j�ݓ��s�ꃊ�N�G�X�g���M�T�[�r�X.�V�K�����s�ꃊ�N�G�X�g
            //���b�Z�[�W���M()�̖߂�l����
            boolean l_blnSendResult = l_marketRequestSendResult
                    .getProcessingResult().isSuccessfulResult();

            if (!l_blnSendResult)
            {
                log.debug("__�V�K�����s�ꃁ�b�Z�[�W���M�G���[__");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00237,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�V�K�����s�ꃊ�N�G�X�g���b�Z�[�W���M()�̖߂�l." +
                    "getProcessingResult().isSuccessfulResult()==false�̏ꍇ");
            }
        }
        catch(NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
