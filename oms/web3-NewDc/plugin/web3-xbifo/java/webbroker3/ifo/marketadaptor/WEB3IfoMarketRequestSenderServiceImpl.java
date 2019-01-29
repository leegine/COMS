head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoMarketRequestSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�s�ꃊ�N�G�X�g���M�T�[�r�X(WEB3IfoMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 ����(���u)  �V�K���������M       �V�K�쐬
Revesion History : 2004/06/16 ����(���u)  �ԍϒ������M         �V�K�쐬
Revesion History : 2004/06/16 ����(���u)  ����������M         �V�K�쐬
Revesion History : 2004/06/16 ����(���u)  �����m��             �V�K�쐬
Revesion History : 2004/06/16 ����(���u)  ����m��             �V�K�쐬
Revesion History : 2004/06/17 ����(���u)  �V�K�������������M    �V�K�쐬
Revesion History : 2004/06/17 ����(���u)  �ԍϒ����������M      �V�K�쐬
Revesion History : 2006/07/06 ���G�� (���u) �y�敨�I�v�V�����z�d�l�ύX���f��511
Revesion History : 2007/01/25 ����� (���u) �d�l�ύX588,595,597,598,602,603,607
Revesion History : 2007/03/27 ����� (���u) ��Q�Ή�U02998
Revesion History : 2007/03/27 ����� (���u) �c�a�X�V�d�l165
Revesion History : 2007/03/28 ����� (���u) ��Q�Ή�U02999
Revesion History : 2007/06/08 �Ј��� (���u) �d�l�ύX���f��No.657 No.664 No.698
*/
package webbroker3.ifo.marketadaptor;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketResponseReceiverCallbackService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoMarketRequestSenderService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoChangeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoProductImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketAdapter;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostStatusDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.WEB3MQSendResult;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderSwitching;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.WEB3IfoAcceptedUpdateInterceptor;
import webbroker3.ifo.WEB3IfoChangeConfirmUpdateInterceptor;
import webbroker3.ifo.WEB3IfoCancelConfirmUpdateInterceptor;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;

/**
 * (�敨OP�s�ꃊ�N�G�X�g���M�T�[�r�X )<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3IfoMarketRequestSenderServiceImpl implements IfoMarketRequestSenderService
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IfoMarketRequestSenderServiceImpl.class);

    /**
     * (�ԍϒ������M)<BR>
     * <BR>
     * �isend(IfoSettleContractOrderMarketRequestMessage)�̎����j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj�ԍϒ������M�v�Q�ƁB<BR>
     * @@param l_request - �ԍϒ������N�G�X�g���b�Z�[�W<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 40611D9201E3
     */
    public MarketRequestSendResult send(
        IfoSettleContractOrderMarketRequestMessage l_request) //cannot throw ToolateException
    {
        final String METHOD_NAME = "send(IfoSettleContractOrderMarketRequestMessage)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String l_strInstitutionCode = null;//�،���ЃR�[�h

            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            String l_strOrderConditionType = null;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            IfoProductImpl l_ifoProductImpl = null;
            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
           
            //(*1) �����ʒm�i�����P��.�����o�H�敪 == �hHOST�h�j�̏ꍇ�̂ݏ������{�B
            if (WEB3OrderRootDivDef.HOST.equals(l_ifoOrderUnitRow.getOrderRootDiv()))
            {
                //�C���^�Z�v�^�𐶐�����B
                WEB3IfoAcceptedUpdateInterceptor l_interceptor =
                    new WEB3IfoAcceptedUpdateInterceptor(WEB3ErrorReasonCodeDef.NORMAL, null);
                
                //�C���^�Z�v�^��OrderManager�ɃZ�b�g����B
                l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
                
                //��t���ʁi��t�����j�I�u�W�F�N�g�𐶐�����B
                DefaultNewOrderAcceptedMarketResponseMessage l_newAcceptedResponseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    
                MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                
                IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();
                
                //��t�����𒍕��ɍX�V����B
                l_processingResult = l_callBackService.process(l_newAcceptedResponseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult.newFailedResultInstance(
                        l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //notify���[���G���W���T�[�o()
            l_orderMgr.notifyRLS(
                (IfoOrderUnit)l_orderUnit,
                OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);

            //�����������擾
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

            //(*1) �t�w�l�����̏ꍇ�͏����I��
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                log.debug("�t�w�l�����̏ꍇ�͏����I��");
                log.exiting(METHOD_NAME);
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //insert�ԍϒ����L���[()
            l_orderMgr.insertSettleContractHostOrder(l_lngOrderId);
            
            //�����I�u�W�F�N�g���擾����
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                                    l_ifoOrderUnitRow.getProductId());

            //is�g���K���s()���R�[��
            l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    l_strOrderConditionType);

            l_strInstitutionCode = l_ifoProductImpl.getInstitution().getInstitutionCode();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            //WEB3IfoOrderService
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

            HostFotypeOrderAllParams l_orderAllParams =
                l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

            //isMQ�g���K���s�o�H()
            //MQ�g���K���s�v�ۂ��擾����B
            //[isMQ�g���K���s�o�H()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
            //�����o�H�敪�F�@@�����P��.�����o�H�敪
            //�t�����g�����V�X�e���敪�F�@@�敨OP��������L���[.�t�����g�����V�X�e���敪
            boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                l_strMarketCode,
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                l_orderAllParams.getFrontOrderSystemCode());
            //�g���K�[���s���ԑ�&&MQ�g���K���s�o�H�̏ꍇ�̂ݏ������{
            if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
            {
                String l_strMQDataCode =
                    l_orderService.getOrderMQDataCode((IfoOrderUnit)l_orderUnit);
                if (l_strMQDataCode == null)
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                //WEB3MQMessageSpec(�،���ЃR�[�h : String, �f�[�^�R�[�h : String)
                //WEB3MQMessageSpec�𐶐�����B
                //[�R���X�g���N�^�Ɏw�肷�����]
                //�،���ЃR�[�h�F
                //�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                //�f�[�^�R�[�h�F
                //�@@�敨OP�����T�[�r�X.get������MQ�f�[�^�R�[�h()�̖߂�l
                l_web3MQMessageSpec =
                    new WEB3MQMessageSpec(
                        l_strInstitutionCode,
                        l_strMQDataCode);

                //MQ�T�[�r�X���擾����
                l_web3MQGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);

                //send(MQ���b�Z�[�W���e : WEB3MQMessageSpec)
                l_web3MQSendResult =
                    l_web3MQGatewayService.send(l_web3MQMessageSpec);

                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    l_processingResult =
                        ProcessingResult.newFailedResultInstance(
                            l_web3MQSendResult.getErrorInfo());

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {

            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }

        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }

    /**
     * (�V�K���������M)<BR>
     * <BR>
     * �isend(IfoOpenContractOrderMarketRequestMessage)�̎����j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj�V�K���������M�v�Q�ƁB<BR>
     * @@param l_request - (�V�K���������N�G�X�g���b�Z�[�W)<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@roseuid 40611E250389
     */
    public MarketRequestSendResult send(
        IfoOpenContractOrderMarketRequestMessage l_request) //cannot throw ToolateException
    {
        final String METHOD_NAME = "send(IfoOpenContractOrderMarketRequestMessage)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String l_strInstitutionCode = null;//�،���ЃR�[�h

            IfoOrderImpl l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            String l_strOrderConditionType = null;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //(*1) �����ʒm�i�����P��.�����o�H�敪 == �hHOST�h�j�̏ꍇ�̂ݏ������{�B
            if (WEB3OrderRootDivDef.HOST.equals(l_ifoOrderUnitRow.getOrderRootDiv()))
            {
                //�C���^�Z�v�^�𐶐�����B
                WEB3IfoAcceptedUpdateInterceptor l_interceptor =
                    new WEB3IfoAcceptedUpdateInterceptor(WEB3ErrorReasonCodeDef.NORMAL, null);
                
                //�C���^�Z�v�^��OrderManager�ɃZ�b�g����B
                l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_interceptor);
                
                //��t���ʁi��t�����j�I�u�W�F�N�g�𐶐�����B
                DefaultNewOrderAcceptedMarketResponseMessage l_newResponseMessage =
                    new DefaultNewOrderAcceptedMarketResponseMessage(l_lngOrderId);
                    
                MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();
                
                IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();
                
                //��t�����𒍕��ɍX�V����B
                l_processingResult = l_callBackService.process(l_newResponseMessage);
                
                if (l_processingResult.isFailedResult())
                {
                    return DefaultMarketRequestSendResult.newFailedResultInstance(
                        l_processingResult);
                }
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //notify���[���G���W���T�[�o()
            l_orderMgr.notifyRLS(
                (IfoOrderUnit)l_orderUnit,
                OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER);

            //�����������擾
            //(*1) �t�w�l�����i�����P��.�������� == �h�t�w�l�h�j�̏ꍇ�͏����I��
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
            {
                log.debug("�t�w�l�����̏ꍇ�͏����I��");
                log.exiting(METHOD_NAME);
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
            }

            //insert�V�K�������L���[()
            l_orderMgr.insertOpenContractHostOrder(l_lngOrderId);

            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                    l_ifoOrderUnitRow.getProductId());

            //is�g���K���s()���R�[��
            l_isTriggerIssue =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                    l_strOrderConditionType);

            l_strInstitutionCode = l_ifoProductImpl.getInstitution().getInstitutionCode();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            //WEB3IfoOrderService
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

            HostFotypeOrderAllParams l_orderAllParams =
                l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

            //isMQ�g���K���s�o�H()
            //MQ�g���K���s�v�ۂ��擾����B
            //[isMQ�g���K���s�o�H()�Ɏw�肷�����]
            //�،���ЃR�[�h�F�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
            //�����^�C�v�F�@@�����P��.�����^�C�v
            //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
            //�����o�H�敪�F�@@�����P��.�����o�H�敪
            //�t�����g�����V�X�e���敪�F�@@�敨OP��������L���[.�t�����g�����V�X�e���敪
            boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                l_strInstitutionCode,
                l_ifoOrderUnitRow.getProductType(),
                l_strMarketCode,
                l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                l_orderAllParams.getFrontOrderSystemCode());
            //�g���K�[���s���ԑ�&&MQ�g���K���s�o�H�̏ꍇ�̂ݏ������{
            if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
            {
                String l_strMQDataCode =
                    l_orderService.getOrderMQDataCode((IfoOrderUnit)l_orderUnit);
                if (l_strMQDataCode == null)
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(
                        l_lngMsgTokenId);
                }
                //WEB3MQMessageSpec(�،���ЃR�[�h : String, �f�[�^�R�[�h : String)
                //WEB3MQMessageSpec�𐶐�����B
                //[�R���X�g���N�^�Ɏw�肷�����]
                //�،���ЃR�[�h�F
                //�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                //�f�[�^�R�[�h�F
                //�@@�敨OP�����T�[�r�X.get������MQ�f�[�^�R�[�h()�̖߂�l
                l_web3MQMessageSpec =
                    new WEB3MQMessageSpec(
                        l_strInstitutionCode,
                        l_strMQDataCode);

                //MQ�T�[�r�X���擾����
                l_web3MQGatewayService =
                    (WEB3MQGatewayService) Services.getService(
                        WEB3MQGatewayService.class);

                //send(MQ���b�Z�[�W���e : WEB3MQMessageSpec)
                l_web3MQSendResult =
                    l_web3MQGatewayService.send(l_web3MQMessageSpec);

                if (l_web3MQSendResult.isSuccessResult())
                {
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                else
                {
                    l_processingResult =
                        ProcessingResult.newFailedResultInstance(
                            l_web3MQSendResult.getErrorInfo());

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }

        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);
    }

    /**
     * (�ԍϒ����������M)<BR>
     * <BR>
     * �isend(IfoChangeSettleContractOrderMarketRequestMessage,
     * <BR>boolean) �̎����j <BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj�ԍϒ����������M�v�Q�ƁB<BR>
     * @@param l_request - �ԍϒ����������N�G�X�g���b�Z�[�W<BR>
     * @@param l_blnIsMarketNotSendMessage - (is�s�ꖢ���M)<BR>
     * ���������s�ꖢ���M�̏ꍇ��true�A���������s�ꑗ�M�ς̏ꍇ<BR>
     * ��false���w�肷��B<BR>
     * false�̏ꍇ�ASONAR�֕ύX��ʒm����B<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 40611EBB01E3
     */
    public MarketRequestSendResult send(
        IfoChangeSettleContractOrderMarketRequestMessage l_request,
        boolean l_blnIsMarketNotSendMessage)
        throws TooLateException
    {
        final String METHOD_NAME = "send(IfoChangeSettleContractOrderMarketRequestMessage,boolean)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String ORDER_REQUEST_CODE       = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;// ���N�G�X�g�f�[�^�R�[�h
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//�����敪 "0�F������"
            String l_strInstitutionCode     = null;//�،���ЃR�[�h
            String l_strBranchCode          = null;//���X�R�[�h
            String l_strTraderCode          = null;//���҃R�[�h
            String l_strOrderRequestNumber  = null;//���ʃR�[�h
            double l_dblSellOrderQuantity  = 0;//���t����
            double l_dblBuyOrderQuantity   = 0;//���t����
            double l_dblSellOrderQuantity1  = 0;//���t����
            double l_dblBuyOrderQuantity1   = 0;//���t����
            double l_dblLimitPrice         = 0;//�w�l
            String l_strExecutionCondition  = null;//���s����
            String l_strConfirmedExecutionCondition  = null;//���s����
            String l_strOrderConditionType  = null;//��������
            Timestamp l_tsCreateDateTime    = null;//�󒍓���
            String l_strAccountCode         = null;//�ڋq�R�[�h
            String l_strOrderDateDiv        = null;//�󒍓��敪
            String l_strProductCode         = null;//�����R�[�h
            double l_dblQuantity           = 0;//��������
            String l_strCancelDiv           = null;//����敪
            String l_strFuturesOptionDiv    = null;
            int l_intOrderActionSerialNo = 0;//��������ԍ�
            String  l_strOrder_action_serial_no =null;
            String l_strFutureOptionProductType = null;
            boolean l_blnIsInsert = false;
           
            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //notify���[���G���W���T�[�o()
            if (l_blnIsMarketNotSendMessage)
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);
            }
            else
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);
            }

            //�Ώۃf�[�^���擾����----------------------------[START]
            //�敨�I�v�V�����敪���擾
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
            
            //throw NotFoundException
            //�������擾����
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                    l_ifoOrderUnitRow.getProductId());
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();

            //���X���擾����
            Branch l_banch =
                   l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());
            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                       l_banch.getInstitution().getInstitutionCode();
            //���X�R�[�h���擾����
            l_strBranchCode = l_banch.getBranchCode();
            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();
            //���҃R�[�h���擾����
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();
            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                    l_ifoOrderUnitRow.getOrderRequestNumber();
            //�����R�[�h���擾����
            l_strProductCode = l_ifoProductImpl.getProductCode();
            
            //���t/���t���ʂ��擾����
            //�������ʂ��擾����
            l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            //���t/���t���ʂ��擾����
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType) || 
                OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                //608�FOP�����ԍϒ����i���ԍρj/604�F�敨�����ԍϒ����i���ԍρj
            {   //���t�̏ꍇ
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
                l_dblSellOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
                //607�FOP�����ԍϒ����i���ԍρj/603�F�敨�����ԍϒ����i���ԍρj
            {
               //���t�̏ꍇ
               l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
               l_dblBuyOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }

            //�w�l���擾����
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();
            //���s�������擾����
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                    l_ifoOrderUnitRow.getExecutionConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(
                    l_ifoExecutionConditionType))
            {
                //��t
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(
                l_ifoExecutionConditionType))
            {
                //����
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(
                    l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strExecutionCondition =
                    WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getConfirmedExecConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(
                l_ifoExecutionConditionType))
            {
                //��t
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(
                l_ifoExecutionConditionType))
            {
                //����
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(
                l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strConfirmedExecutionCondition =
                    WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }
            //�����������擾����
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

            //�󒍓����Ɍ��ݎ�����ݒ肷��
            l_tsCreateDateTime = GtlUtils.getSystemTimestamp();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsCreateDateTime);

            //�󒍓��敪���擾����
            l_strOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strOrderDateDiv = WEB3OrderDateDivDef.TODAY;

            }

            //���������ŏI�ʔԂ��擾����
            l_intOrderActionSerialNo =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();
            l_strOrder_action_serial_no = String.valueOf(l_intOrderActionSerialNo);
            
            //�敨�I�v�V�������i���擾����
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);

            //�f�[�^�R�[�h���擾����
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
            }

            //�Ώۃf�[�^���擾����----------------------------[END]

            //(*1) �s�ꖢ���M�����̏ꍇ�̂ݏ������{
            if (l_blnIsMarketNotSendMessage)
            {
                //�t�w�l�����̏ꍇ�A�����I�� 
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType) 
                    && !WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
                {
                    log.debug("�t�w�l�����̏ꍇ�͏����I��");
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                
                //�،���ЁA���X�A���ʃR�[�h�A�Г��������ځA�����敪�Ő�OP�ԍϒ���_��OP��������L���[�e�[�u������
                //throw DataFindException, DataNetworkException, DataQueryException
                try
                {
                    l_queryProcessor = Processors.getDefaultProcessor();
                    int l_intIndex = l_orderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_orderService.getFigureOfOrderRev();
                    StringBuffer l_sbWhereOrder = new StringBuffer();
                    l_sbWhereOrder.append(" institution_code = ? "); 
                    l_sbWhereOrder.append(" and branch_code = ? ");
                    l_sbWhereOrder.append(" and order_request_number = ? ");
                    l_sbWhereOrder.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? ");
                    l_sbWhereOrder.append(" and status = ? ");
            
                    Object[] l_objWhereOrder = { 
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber,
                        l_ifoOrderUnitRow.getOrderRev(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS};

                    List l_lisSearchResult =
                        l_queryProcessor.doFindAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            null,
                            "FOR UPDATE",
                            l_objWhereOrder);

                    int l_intUpdateCnt = 0;
                    if (l_lisSearchResult.isEmpty() || l_lisSearchResult.size() == 0)
                    {
                        return DefaultMarketRequestSendResult
                            .newFailedResultInstance(
                                ProcessingResult.newFailedResultInstance(
                                    WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }
                    else
                    {
                        l_intUpdateCnt = l_lisSearchResult.size();
                    }
                    log.debug("updated rows count:" + l_intUpdateCnt + "  rows");

                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("order_action_serial_no", l_strOrder_action_serial_no);
                    l_mapChanges.put("submit_order_route_div", l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    l_mapChanges.put("buy_order_quantity", new Double(l_dblBuyOrderQuantity1));
                    l_mapChanges.put("sell_order_quantity", new Double(l_dblSellOrderQuantity1));
                    l_mapChanges.put("limit_price", new Double(l_dblLimitPrice));
                    l_mapChanges.put("execution_condition", l_strExecutionCondition);
                    l_mapChanges.put("last_updated_timestamp", l_tsCreateDateTime);

                    //��v����s�̓��e�������DB�ɍX�V����B
                    if (l_intUpdateCnt > 0)
                    {
                        l_queryProcessor.doUpdateAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            l_objWhereOrder,
                            l_mapChanges);
                    }

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                catch (DataException l_de)
                {
                    WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + METHOD_NAME,
                        l_de.getMessage(),
                        l_de);

                    log.error("__an unexpected error__",l_sysException);
                    l_processingResult = ProcessingResult.newFailedResultInstance(
                        l_sysException.getErrorInfo());

                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }

            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

            // is���e�ʒm�ϒ���
            boolean l_blnIsNotifyEndOrder = l_orderManager.isNotifyEndOrder(l_orderUnit);

            // is�s��J�ǎ��ԑ�
            boolean l_blnIsOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            
            // is������x�e���ԑ�
            boolean l_blnIsTradeCloseTimeZone =
                WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

            //�g���K�[���s����
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(l_strOrderConditionType);

            //�i*2�j�i�x�e���ԑсj�@@or�@@�i�s��ʒm�v�̒��������@@&&�@@�g���K�[���s����j�̏ꍇ
            if (l_blnIsTradeCloseTimeZone || (!l_blnIsNotifyEndOrder && l_blnIsSubmitMarketTrigger))
            {
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;

                //�x�e���ԑт̏ꍇ
                if (l_blnIsTradeCloseTimeZone)
                {
                    //get�敨OP��������L���[(IfoOrderUnit)
                    //�敨OP��������L���[���擾����B
                    //[�敨OP�����T�[�r�X.get�敨OP��������L���[()�Ɏw�肷�����]
                    //�����P�ʁF�@@�����P��
                    l_hostFotypeOrderAllParams =
                        l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

                    //�L���[�f�[�^���݂��Ȃ��ꍇ
                    if (l_hostFotypeOrderAllParams == null)
                    {
                        //�s��ʒm�s�v�̏ꍇ�A�����m��(�����P��:OrderUnit)���s��
                        if (l_orderManager.isNotifyEndOrder(l_orderUnit))
                        {
                            //�����m��
                            this.updateOrderModified(l_orderUnit);

                            //�������I������
                            log.exiting(METHOD_NAME);
                            return DefaultMarketRequestSendResult.newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }

                        //�s��ʒm�v�̏ꍇ�A�L���[�f�[�^insert
                        else
                        {
                            l_blnIsInsert = true;
                        }
                    }
                    //�L���[�f�[�^���݂���ꍇ�A�L���[�f�[�^update
                    else
                    {
                        try
                        {
                            QueryProcessor l_processor = Processors.getDefaultProcessor();

                            //�󒍓���
                            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                            //set��������ԍ�
                            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                            //set�����o�H�敪
                            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                            //��������
                            l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                            //set�w�l
                            l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                            //set���s����
                            l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                            //set�X�V���t
                            l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(l_tsCreateDateTime);

                            l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                        }
                        catch (DataException l_ex)
                        {
                            WEB3SystemLayerException l_sysException =
                                new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);
    
                            log.error("__an unexpected error__",l_sysException);
                            l_processingResult = ProcessingResult.newFailedResultInstance(
                                l_sysException.getErrorInfo());
    
                            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                        }
                    }
                }
                else
                {
                    l_blnIsInsert = true;
                }
                if (l_blnIsInsert)
                {
                    l_hostFotypeOrderAllParams =
                        new HostFotypeOrderAllParams();

                    //�f�[�^�R�[�h
                    l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);
                    //�����h�c
                    l_hostFotypeOrderAllParams.setAccountId(l_orderUnit.getAccountId());
                    //�،���ЃR�[�h
                    l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
                    //���X�R�[�h
                    l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
                    //�ڋq�R�[�h
                    l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
                    //���҃R�[�h
                    l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
                    //�󒍓��敪
                    l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strOrderDateDiv);
                    //���ʃR�[�h
                    l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
                    //�s��R�[�h�iSONAR�j
                    l_hostFotypeOrderAllParams.setSonarMarketCode(l_ifoOrderUnitRow.getSonarMarketCode());
                    //�����R�[�h
                    l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);
                    //�󒍓���
                    l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                    //��������ԍ�
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                    //�����o�H�敪
                    l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                        l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    //�����Y�����R�[�h
                    l_hostFotypeOrderAllParams.setTargetProductCode(
                        l_ifoProductImpl.getUnderlyingProductCode());
                    //�����i�N�j
                    l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(
                        l_ifoProductImpl.getMonthOfDelivery().substring(0, 4));
                    //�����i���j
                    l_hostFotypeOrderAllParams.setDeliveryMonthMm(
                        l_ifoProductImpl.getMonthOfDelivery().substring(4, 6));
                    //�敨�I�v�V�������i
                    l_hostFotypeOrderAllParams.setFutureOptionProductType(l_strFutureOptionProductType);
                    //�s�g���i
                    l_hostFotypeOrderAllParams.setStrikePrice(l_ifoProductImpl.getStrikePrice());
                    //����
                    l_hostFotypeOrderAllParams.setSplitType(l_ifoProductRow.getSplitType());
                    //���t����
                    l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
                    //���t����
                    l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
                    //�w�l
                    l_hostFotypeOrderAllParams.setLimitPrice(l_ifoOrderUnitRow.getConfirmedPrice());
                    //���s����
                    l_hostFotypeOrderAllParams.setExecutionCondition(l_strConfirmedExecutionCondition);
                    //�t�w�l��l
                    l_hostFotypeOrderAllParams.setStopOrderPrice(null);
                    //�iW�w�l�j�����w�l
                    l_hostFotypeOrderAllParams.setWLimitPrice(null);
                    //����敪
                    l_hostFotypeOrderAllParams.setTransactionType(null);
                    //�`�[��
                    l_hostFotypeOrderAllParams.setTicketNumber(null);
                    //���ʃ`�F�b�N
                    l_hostFotypeOrderAllParams.setContractCheck(null);
                    //�����`���l��
                    l_hostFotypeOrderAllParams.setOrderChanel(null);
                    //�萔����
                    l_hostFotypeOrderAllParams.setCommisionNumber(null);
                    //�萔�����}��
                    l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                    //�萔�����i�R�[�h
                    l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                    //��������
                    l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                    //�����w�l
                    l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                    //�������s����
                    l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                    //�����t�w�l��l
                    l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                    //�����iW�w�l�j�����w�l
                    l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                    //����敪
                    l_strCancelDiv = WEB3CancelDivDef.EXCEPT_CANCEL;
                    l_hostFotypeOrderAllParams.setCancelDiv(l_strCancelDiv);
                    //�t�����g����������敪�R�[�h
                    l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                        l_orderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
                    //�t�����g�����V�X�e���敪
                    l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                        l_orderService.getFrontOrderSystemCode(l_market.getMarketCode()));
                    //�t�����g��������敪�R�[�h
                    l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    //���Ȉϑ��敪
                    l_hostFotypeOrderAllParams.setTradeauditCode(null);
                    //�Г���������
                    l_hostFotypeOrderAllParams.setCorpCode(
                        l_orderService.getCorpCode((IfoOrderUnit)l_orderUnit));
                    //�i������j�Г���������
                    l_hostFotypeOrderAllParams.setOrgCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //���z�T�[�oNo.�iJSOES�j
                    l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);
                    //�s�ꔭ��No.
                    l_hostFotypeOrderAllParams.setMarketOrderNumber(null);
                    //AMG���M����
                    l_hostFotypeOrderAllParams.setAmgSendTime(null);
                    //AMG���͕ۏ؎�M����
                    l_hostFotypeOrderAllParams.setAmgAckTime(null);
                    //�s����͕ۏ؎�M����
                    l_hostFotypeOrderAllParams.setMarketAckTime(null);
                    //�S���������敪
                    l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    //�����敪
                    l_hostFotypeOrderAllParams.setStatus(l_strStatus);
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }

                //is�g���K���s()���R�[��
                l_isTriggerIssue =
                    WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                        null);

                String l_strMarketCode = l_market.getMarketCode();

                //isMQ�g���K���s�o�H()
                //MQ�g���K���s�v�ۂ��擾����B
                //[isMQ�g���K���s�o�H()�Ɏw�肷�����]
                //�،���ЃR�[�h�F�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                //�����^�C�v�F�@@�����P��.�����^�C�v
                //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
                //�����o�H�敪�F�@@�����P��.�����o�H�敪
                //�t�����g�����V�X�e���敪�F�@@�敨OP��������L���[.�t�����g�����V�X�e���敪
                boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_strInstitutionCode,
                    l_ifoOrderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                    l_hostFotypeOrderAllParams.getFrontOrderSystemCode());
                //�g���K�[���s���ԑ�&&MQ�g���K���s�o�H�̏ꍇ�̂ݏ������{
                if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
                {
                    String l_strMQDataCode =
                        l_orderService.getChangeCancelMQDataCode(
                            (IfoOrderUnit)l_orderUnit);
                    if (l_strMQDataCode == null)
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }
                    //WEB3MQMessageSpec(�،���ЃR�[�h : String, �f�[�^�R�[�h : String)
                    //WEB3MQMessageSpec�𐶐�����B
                    //[�R���X�g���N�^�Ɏw�肷�����]
                    //�،���ЃR�[�h�F
                    //�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                    //�f�[�^�R�[�h�F
                    //�@@�敨OP�����T�[�r�X.get���������MQ�f�[�^�R�[�h()�̖߂�l
                    l_web3MQMessageSpec =
                        new WEB3MQMessageSpec(
                            l_strInstitutionCode,
                            l_strMQDataCode);

                    //MQ�T�[�r�X���擾����
                    l_web3MQGatewayService =
                        (WEB3MQGatewayService) Services.getService(
                            WEB3MQGatewayService.class);

                    //send(MQ���b�Z�[�W���e : WEB3MQMessageSpec)
                    l_web3MQSendResult =
                        l_web3MQGatewayService.send(l_web3MQMessageSpec);

                    if (l_web3MQSendResult.isSuccessResult())
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }
                    else
                    {
                        l_processingResult =
                            ProcessingResult.newFailedResultInstance(
                                l_web3MQSendResult.getErrorInfo());

                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
            }
            else if (l_orderManager.isNotifyEndOrder(l_orderUnit)
                || !l_blnIsOpenTimeZone
                || (!l_blnIsMarketNotSendMessage
                    && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                    && WEB3StringTypeUtility.isEmpty(l_ifoOrderUnitRow.getSessionType())))
            {
                //�����m��
                this.updateOrderModified(l_orderUnit);
            }
        }
        catch (WEB3SystemLayerException l_sle)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_sle.getMessage(),
                l_sle);
            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (DataException l_de)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + METHOD_NAME,
                l_de.getMessage(),
                l_de);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);

    }

    /**
     * (�V�K�������������M)<BR>
     * <BR>
     * �isend(IfoChangeOrderMarketRequestMessage, boolean)�̎����j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj�V�K�������������M�v�Q�ƁB<BR>
     * @@param l_request - �V�K�������������N�G�X�g���b�Z�[�W<BR>
     * @@param l_blnIsMarketNotSendMessage - (is�s�ꖢ���M)<BR>
     * ���������s�ꖢ���M�̏ꍇ��true�A���������s�ꑗ�M�ς̏ꍇ<BR>��false���w�肷��B<
     * BR>
     * false�̏ꍇ�ASONAR�֕ύX��ʒm����B<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 4061218303D7
     */
    public MarketRequestSendResult send(
        IfoChangeOrderMarketRequestMessage l_request,
        boolean l_blnIsMarketNotSendMessage)
        throws TooLateException
    {
        final String METHOD_NAME = "send(IfoChangeOrderMarketRequestMessage,boolean)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String ORDER_REQUEST_CODE       = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;// ���N�G�X�g�f�[�^�R�[�h
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//�����敪 "0�F������"
            String l_strInstitutionCode     = null;//�،���ЃR�[�h
            String l_strBranchCode          = null;//���X�R�[�h
            String l_strTraderCode          = null;//���҃R�[�h
            String l_strOrderRequestNumber  = null;//���ʃR�[�h
            double l_dblSellOrderQuantity  = 0;//���t����
            double l_dblBuyOrderQuantity   = 0;//���t����
            double l_dblSellOrderQuantity1  = 0;//���t����
            double l_dblBuyOrderQuantity1   = 0;//���t����
            double l_dblLimitPrice         = 0;//�w�l
            String l_strExecutionCondition  = null;//���s����
            String l_strConfirmedExecutionCondition  = null;//���s����
            String l_strOrderConditionType = null;//��������
            Timestamp l_tsCreateDateTime    = null;//�󒍓���
            String l_strAccountCode         = null;//�ڋq�R�[�h
            String l_strOrderDateDiv        = null;//�󒍓��敪
            String l_strProductCode         = null;//�����R�[�h
            double l_dblQuantity           = 0;//��������
            String l_strCancelDiv           = null;//����敪
            String l_strFuturesOptionDiv    = null;
            int l_intOrderActionSerialNo = 0;//��������ԍ�
            String  l_strOrderActionSerialNo =null;
            String l_strFutureOptionProductType = null;
            boolean l_blnIsInsert = false;

            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            boolean l_isTriggerIssue = false;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //notify���[���G���W���T�[�o()
            if (l_blnIsMarketNotSendMessage)
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT);
            }
            else
            {
                l_orderMgr.notifyRLS(
                    (IfoOrderUnit)l_orderUnit,
                    OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED);
            }

            //�Ώۃf�[�^���擾����----------------------------[START]
            //�敨�I�v�V�����敪���擾
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
            
            //throw NotFoundException
            //�������擾����
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager.getProduct(
                                l_ifoOrderUnitRow.getProductId());
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();

            //���X���擾����
            Branch l_banch =
                   l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());

            //�،���ЃR�[�h���擾����
            l_strInstitutionCode =
                       l_banch.getInstitution().getInstitutionCode();

            //���X�R�[�h���擾����
            l_strBranchCode = l_banch.getBranchCode();

            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();

            //���҃R�[�h���擾����
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();
            
            //���ʃR�[�h���擾����
            l_strOrderRequestNumber =
                    l_ifoOrderUnitRow.getOrderRequestNumber();
            
            //�����R�[�h���擾����
            l_strProductCode = l_ifoProductImpl.getProductCode();
            
            //���t/���t���ʂ��Z�b�g
            //�������ʂ��Z�b�g
            l_dblQuantity = l_ifoOrderUnitRow.getQuantity();
            //���t/���t���ʂ��擾����
            OrderTypeEnum l_orderType = l_orderUnit.getOrderType();
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType))
                //605�FOP�V�K��������/601�F�敨�V�K��������
            {   //���t�̏ꍇ
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
                l_dblBuyOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }
            else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
                //606�FOP�V�K��������/602�F�敨�V�K��������
            {
                //���t�̏ꍇ
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
                l_dblSellOrderQuantity1 = l_ifoOrderUnitRow.getQuantity();
            }

            //�w�l���擾
            l_dblLimitPrice = l_ifoOrderUnitRow.getLimitPrice();
            //���s������ݒ�
            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getExecutionConditionType();

            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //��t
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //����
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getConfirmedExecConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //��t
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //����
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }
            //�����������擾����
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

			//�󒍓����Ɍ��ݎ�����ݒ肷��
			l_tsCreateDateTime = GtlUtils.getSystemTimestamp();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsCreateDateTime);

            //�󒍓��敪���擾����
            l_strOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }

            //���������ŏI�ʔԂ��擾����
            l_intOrderActionSerialNo =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();
            l_strOrderActionSerialNo = String.valueOf(l_intOrderActionSerialNo);
            
            //�f�[�^�R�[�h���擾����
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
            }

            //�敨�I�v�V�������i���擾����
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            //�Ώۃf�[�^���擾����----------------------------[END]

            //(*1) �s�ꖢ���M�����̏ꍇ�̂ݏ������{
            if (l_blnIsMarketNotSendMessage)
            {
                log.debug("�s�ꖢ���M�����̏ꍇ�̂ݏ������{");
                
				//�i�����P��.�������� == �h�t�w�l�h�@@���@@�����P��.���N�G�X�g�^�C�v!=�h�����T�[�o�h�j�̏ꍇ�͏����I���B
				if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && !WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
				{
					log.debug("�t�w�l�����̏ꍇ�͏����I��");
					log.exiting(METHOD_NAME);
					return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
				}

                //�،���ЁA���X�A���ʃR�[�h�A�����敪�Ő敨OP��������L���[�e�[�u������
                try
                {   //throw DataFindException, DataNetworkException, DataQueryException
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    int l_intIndex = l_orderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_orderService.getFigureOfOrderRev();
                    StringBuffer l_sbWhereOrder = new StringBuffer();
                    l_sbWhereOrder.append(" institution_code = ? "); 
                    l_sbWhereOrder.append(" and branch_code = ? ");
                    l_sbWhereOrder.append(" and order_request_number = ? ");
                    l_sbWhereOrder.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? ");
                    l_sbWhereOrder.append(" and status = ? ");
            
                    Object[] l_objWhereOrder = { 
                        l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber,
                        l_ifoOrderUnitRow.getOrderRev(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS};

                    List l_lisSearchResult =
                        l_processor.doFindAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            null,
                            "FOR UPDATE",
                            l_objWhereOrder);
      
                    int l_intUpdateCnt = 0;
					if (l_lisSearchResult.isEmpty() || l_lisSearchResult.size() == 0)
					{
						return DefaultMarketRequestSendResult
							.newFailedResultInstance(
								ProcessingResult.newFailedResultInstance(
									WEB3ErrorCatalog.BUSINESS_ERROR_00010));
					}
                    else
                    {
                        l_intUpdateCnt = l_lisSearchResult.size();
                    }

                    Map l_mapChanges = new HashMap();
                    l_mapChanges.put("order_action_serial_no", l_strOrderActionSerialNo);
                    l_mapChanges.put("submit_order_route_div", l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    l_mapChanges.put("buy_order_quantity", new Double(l_dblBuyOrderQuantity1));
                    l_mapChanges.put("sell_order_quantity", new Double(l_dblSellOrderQuantity1));
                    l_mapChanges.put("limit_price", new Double(l_dblLimitPrice));
                    l_mapChanges.put("execution_condition", l_strExecutionCondition);
                    l_mapChanges.put("last_updated_timestamp", l_tsCreateDateTime);
                    //��v����s�̓��e�������DB�ɍX�V����B
                    if (l_intUpdateCnt > 0)
                    {
                        l_processor.doUpdateAllQuery(
                            HostFotypeOrderAllRow.TYPE,
                            l_sbWhereOrder.toString(),
                            l_objWhereOrder,
                            l_mapChanges);
                    }

                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }
                catch (DataException l_de)
                {
                    WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + METHOD_NAME,
                        l_de.getMessage(),
                        l_de);

                    log.error("__an unexpected error__",l_sysException);
                    l_processingResult = ProcessingResult.newFailedResultInstance(
                        l_sysException.getErrorInfo());

                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }
            }

            //(*2) ���e�ʒm�ϒ����̏ꍇ
            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            //�s��J�ǎ��ԑтł��邩�𔻒肷��B
            boolean l_blnIsOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            //�g���K�[���s����
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(l_strOrderConditionType);
            //is������x�e���ԑ�()
            boolean l_blnIsTradeCloseTimeZone = WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

            //�i�x�e���ԑсjor�i�s��ʒm�v�̒������� && �g���K�[���s����j�̏ꍇ
            if (l_blnIsTradeCloseTimeZone
                || (!l_orderManager.isNotifyEndOrder(l_orderUnit)
                        && l_blnIsSubmitMarketTrigger))
            {
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
                //�x�e���ԑт̏ꍇ
                if (l_blnIsTradeCloseTimeZone)
                {
                    //get�敨OP��������L���[(IfoOrderUnit)
                    //�敨OP��������L���[���擾����B
                    //[�敨OP�����T�[�r�X.get�敨OP��������L���[()�Ɏw�肷�����]
                    //�����P�ʁF�@@�����P��
                    l_hostFotypeOrderAllParams =
                        l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);

                    //�L���[�f�[�^���݂��Ȃ��ꍇ
                    if (l_hostFotypeOrderAllParams == null)
                    {
                        //�s��ʒm�s�v�̏ꍇ�A�����m��(�����P��:OrderUnit)���s��
                        if (l_orderManager.isNotifyEndOrder(l_orderUnit))
                        {
                            //�����m��
                            this.updateOrderModified(l_orderUnit);

                            //�������I������
                            log.exiting(METHOD_NAME);
                            return DefaultMarketRequestSendResult.newSuccessResultInstance(
                                l_lngMsgTokenId);
                        }
                        //�s��ʒm�v�̏ꍇ�A�L���[�f�[�^insert
                        else
                        {
                            l_blnIsInsert = true;
                        }
                    }
                    //�L���[�f�[�^���݂���ꍇ�A�L���[�f�[�^update
                    else
                    {
                        try
                        {
                            QueryProcessor l_processor = Processors.getDefaultProcessor();

                            //�󒍓���
                            l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                            //set��������ԍ�
                            l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                            //set�����o�H�敪
                            l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                            //��������
                            l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                            //set�w�l
                            l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                            //set���s����
                            l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                            //set�X�V���t
                            l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(l_tsCreateDateTime);
                            l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                        }
                        catch (DataException l_ex)
                        {
                            WEB3SystemLayerException l_sysException =
                                new WEB3SystemLayerException(
                                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                    this.getClass().getName() + "." + METHOD_NAME,
                                    l_ex.getMessage(),
                                    l_ex);

                            log.error("__an unexpected error__",l_sysException);
                            l_processingResult = ProcessingResult.newFailedResultInstance(
                                l_sysException.getErrorInfo());

                            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                        }
                    }
                }
                else
                {
                    l_blnIsInsert = true;
                }
                if (l_blnIsInsert)
                {
                    l_hostFotypeOrderAllParams =
                        new HostFotypeOrderAllParams();

                    //�f�[�^�R�[�h
                    l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);
                    //�����h�c
                    l_hostFotypeOrderAllParams.setAccountId(l_orderUnit.getAccountId());
                    //�،���ЃR�[�h
                    l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
                    //���X�R�[�h
                    l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
                    //�ڋq�R�[�h
                    l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
                    //���҃R�[�h
                    l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
                    //�󒍓��敪
                    l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strOrderDateDiv);
                    //���ʃR�[�h
                    l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
                    //�s��R�[�h�iSONAR�j
                    l_hostFotypeOrderAllParams.setSonarMarketCode(l_ifoOrderUnitRow.getSonarMarketCode());
                    //�����R�[�h
                    l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);
                    //�󒍓���
                    l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                    //��������ԍ�
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                    //�����o�H�敪
                    l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    //�����Y�����R�[�h
                    l_hostFotypeOrderAllParams.setTargetProductCode(l_ifoProductImpl.getUnderlyingProductCode());
                    //�����i�N�j
                    l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(
                        l_ifoProductImpl.getMonthOfDelivery().substring(0, 4));
                    //�����i���j
                    l_hostFotypeOrderAllParams.setDeliveryMonthMm(
                        l_ifoProductImpl.getMonthOfDelivery().substring(4, 6));
                    //�敨�I�v�V�������i
                    l_hostFotypeOrderAllParams.setFutureOptionProductType(l_strFutureOptionProductType);
                    //�s�g���i
                    l_hostFotypeOrderAllParams.setStrikePrice(l_ifoProductImpl.getStrikePrice());
                    //����
                    l_hostFotypeOrderAllParams.setSplitType(l_ifoProductRow.getSplitType());
                    //���t����
                    l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
                    //���t����
                    l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
                    //�w�l
                    l_hostFotypeOrderAllParams.setLimitPrice(l_ifoOrderUnitRow.getConfirmedPrice());
                    //���s����
                    l_hostFotypeOrderAllParams.setExecutionCondition(l_strConfirmedExecutionCondition);
                    //�t�w�l��l
                    l_hostFotypeOrderAllParams.setStopOrderPrice(null);
                    //�iW�w�l�j�����w�l
                    l_hostFotypeOrderAllParams.setWLimitPrice(null);
                    //����敪
                    l_hostFotypeOrderAllParams.setTransactionType(null);
                    //�`�[��
                    l_hostFotypeOrderAllParams.setTicketNumber(null);
                    //���ʃ`�F�b�N
                    l_hostFotypeOrderAllParams.setContractCheck(null);
                    //�����`���l��
                    l_hostFotypeOrderAllParams.setOrderChanel(null);
                    //�萔����
                    l_hostFotypeOrderAllParams.setCommisionNumber(null);
                    //�萔�����}��
                    l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                    //�萔�����i�R�[�h
                    l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                    //��������
                    l_hostFotypeOrderAllParams.setChangeQuantity(l_dblQuantity);
                    //�����w�l
                    l_hostFotypeOrderAllParams.setChangeLimitPrice(l_dblLimitPrice);
                    //�������s����
                    l_hostFotypeOrderAllParams.setChangeExecutionCondition(l_strExecutionCondition);
                    //�����t�w�l��l
                    l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                    //�����iW�w�l�j�����w�l
                    l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                    //����敪
                    l_strCancelDiv = WEB3CancelDivDef.EXCEPT_CANCEL;
                    l_hostFotypeOrderAllParams.setCancelDiv(l_strCancelDiv);
                    //�t�����g����������敪�R�[�h
                    l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                        l_orderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
                    //�t�����g�����V�X�e���敪
                    l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                        l_orderService.getFrontOrderSystemCode(l_market.getMarketCode()));
                    //�t�����g��������敪�R�[�h
                    l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    //���Ȉϑ��敪
                    l_hostFotypeOrderAllParams.setTradeauditCode(null);
                    //�Г���������
                    l_hostFotypeOrderAllParams.setCorpCode(l_orderService.getCorpCode((IfoOrderUnit)l_orderUnit));
                    //�i������j�Г���������
                    l_hostFotypeOrderAllParams.setOrgCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //���z�T�[�oNo.�iJSOES�j
                    l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);
                    //�s�ꔭ��No.
                    l_hostFotypeOrderAllParams.setMarketOrderNumber(null);
                    //AMG���M����
                    l_hostFotypeOrderAllParams.setAmgSendTime(null);
                    //AMG���͕ۏ؎�M����
                    l_hostFotypeOrderAllParams.setAmgAckTime(null);
                    //�s����͕ۏ؎�M����
                    l_hostFotypeOrderAllParams.setMarketAckTime(null);
                    //�S���������敪
                    l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    //�����敪
                    l_hostFotypeOrderAllParams.setStatus(l_strStatus);
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
                //is�g���K���s()���R�[��
                l_isTriggerIssue =
                    WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                        null);

                String l_strMarketCode = l_market.getMarketCode();

                //isMQ�g���K���s�o�H()
                //MQ�g���K���s�v�ۂ��擾����B
                //[isMQ�g���K���s�o�H()�Ɏw�肷�����]
                //�،���ЃR�[�h�F�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                //�����^�C�v�F�@@�����P��.�����^�C�v
                //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
                //�����o�H�敪�F�@@�����P��.�����o�H�敪
                //�t�����g�����V�X�e���敪�F�@@�敨OP��������L���[.�t�����g�����V�X�e���敪
                boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_strInstitutionCode,
                    l_ifoOrderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                    l_hostFotypeOrderAllParams.getFrontOrderSystemCode());
                //�g���K�[���s���ԑ�&&MQ�g���K���s�o�H�̏ꍇ�̂ݏ������{
                if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
                {
                    String l_strMQDataCode =
                        l_orderService.getChangeCancelMQDataCode(
                            (IfoOrderUnit)l_orderUnit);
                    if (l_strMQDataCode == null)
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }
                    //WEB3MQMessageSpec(�،���ЃR�[�h : String, �f�[�^�R�[�h : String)
                    //WEB3MQMessageSpec�𐶐�����B
                    //[�R���X�g���N�^�Ɏw�肷�����]
                    //�،���ЃR�[�h�F
                    //�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                    //�f�[�^�R�[�h�F
                    //�@@�敨OP�����T�[�r�X.get���������MQ�f�[�^�R�[�h()�̖߂�l
                    l_web3MQMessageSpec =
                        new WEB3MQMessageSpec(
                            l_strInstitutionCode,
                            l_strMQDataCode);

                    //MQ�T�[�r�X���擾����
                    l_web3MQGatewayService =
                        (WEB3MQGatewayService) Services.getService(
                            WEB3MQGatewayService.class);

                    //send(MQ���b�Z�[�W���e : WEB3MQMessageSpec)
                    l_web3MQSendResult =
                        l_web3MQGatewayService.send(l_web3MQMessageSpec);

                    if (l_web3MQSendResult.isSuccessResult())
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }
                    else
                    {
                        l_processingResult =
                            ProcessingResult.newFailedResultInstance(
                                l_web3MQSendResult.getErrorInfo());

                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
            }
            else if (l_orderManager.isNotifyEndOrder(l_orderUnit)
                || !l_blnIsOpenTimeZone
                || (!l_blnIsMarketNotSendMessage
                    && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                    && WEB3StringTypeUtility.isEmpty(l_ifoOrderUnitRow.getSessionType())))
            {
                //�����m��
                this.updateOrderModified(l_orderUnit);
            }
        }
		catch (WEB3SystemLayerException l_sle)
		{
			WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + METHOD_NAME,
				l_sle.getMessage(),
				l_sle);
			log.error("__an unexpected error__",l_sysException);
			ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
				l_sysException.getErrorInfo());

			return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
		}
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);

    }

    /**
     * (����������M)<BR>
     * <BR>
     * �isend(CancelOrderMarketRequestMessage, boolean)�̎����j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj����������M�v�Q�ƁB<BR>
     * @@param l_request - ����������N�G�X�g���b�Z�[�W<BR>
     *
     * @@param l_blnIsMarketNotSendMessage - (is�s�ꖢ���M)<BR>
     * ���������s�ꖢ���M�̏ꍇ��true�A���������s�ꑗ�M�ς̏ꍇ<BR>��false���w�肷��B<
     * BR>
     * false�̏ꍇ�ASONAR�֎����ʒm����B<BR>
     *
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException
     * @@roseuid 4061223901C4
     */
    public MarketRequestSendResult send(
        CancelOrderMarketRequestMessage l_request,
        boolean l_blnIsMarketNotSendMessage)
        throws TooLateException
    {
        final String METHOD_NAME = "send(CancelOrderMarketRequestMessage,boolean)";
        log.entering(METHOD_NAME);

        try
        {
            if (l_request == null)
            {
                log.error("parameter is null type");
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + METHOD_NAME);
            }

            String ORDER_REQUEST_CODE       = WEB3HostRequestCodeDef.OPTION_ORDER_CHANGE_CANCEL;//�f�[�^�R�[�h
            String l_strStatus = WEB3StatusDef.NOT_DEAL;//�����敪 "0�F������"
            String l_strInstitutionCode     = null;//�،���ЃR�[�h
            String l_strBranchCode          = null;//���X�R�[�h
            String l_strAccountCode         = null;//�ڋq�R�[�h
            String l_strTraderCode          = null;//���҃R�[�h
            String l_strOrderDateDiv        = null;//�󒍓��敪
            String l_strOrderRequestNumber  = null;//���ʃR�[�h
            String l_strProductCode         = null;//�����R�[�h
            Timestamp l_tsCreateDateTime    = null;//�󒍓���
            String l_strCancelDiv           = null;//����敪
            String l_strFuturesOptionDiv    = null;//�敨�^�I�v�V�����敪
            String l_strOrderConditionType = null;//��������
            Order l_order = null;
            IfoOrderUnitRow l_ifoOrderUnitRow = null;
            int l_intOrderActionSerialNo = 0;//��������ԍ�
            String l_strFutureOptionProductType = null;
            double l_dblSellOrderQuantity  = 0;//���t����
            double l_dblBuyOrderQuantity   = 0;//���t����
            String l_strConfirmedExecutionCondition  = null;//���s����

            boolean l_isTriggerIssue = false;
            long l_lngMsgTokenId = 0;
            long l_lngOrderId = l_request.getOrderId();

            WEB3MQMessageSpec l_web3MQMessageSpec = null;
            WEB3MQGatewayService l_web3MQGatewayService;
            WEB3MQSendResult l_web3MQSendResult;
            ProcessingResult l_processingResult;

            AccountManager l_accountManager =GtlUtils.getAccountManager();
            TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
            IfoProductManager l_ifoProductManager =
                (IfoProductManager) l_tradingModule.getProductManager();

            IfoProductImpl l_ifoProductImpl = null;

            //�����擾
            WEB3OptionOrderManagerImpl l_orderMgr =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_order = (IfoOrderImpl) l_orderMgr.getOrder(l_lngOrderId);

            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];

            l_ifoOrderUnitRow =
                (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //notify���[���G���W���T�[�o()
            //1.5 (*)notify���[���G���W���T�[�o()�ɂċƖ��G���[���X���[���ꂽ�ꍇ
            try
            {
	            if (l_blnIsMarketNotSendMessage)
	            {
	                l_orderMgr.notifyRLS(
	                    (IfoOrderUnit)l_orderUnit,
	                    OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT);
	            }
	            else
	            {
	                l_orderMgr.notifyRLS(
	                    (IfoOrderUnit)l_orderUnit,
	                    OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED);
	            }
            }
            catch (WEB3BusinessLayerException l_ex)
            {
				log.debug("error in  l_orderMgr.notifyRLS", l_ex);
            }

            //�Ώۃf�[�^���擾����----------------------------[START]
            //�敨�I�v�V�����敪���擾
            l_strFuturesOptionDiv = l_ifoOrderUnitRow.getFutureOptionDiv();
            
            //throw NotFoundException
            //�������擾����
            l_ifoProductImpl = (IfoProductImpl) l_ifoProductManager
                              .getProduct(l_ifoOrderUnitRow.getProductId());
            IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProductImpl.getDataSourceObject();

            //���X���擾����
            Branch l_banch =
                   l_accountManager.getBranch(l_ifoOrderUnitRow.getBranchId());
            //�،���ЃR�[�h���擾����
            l_strInstitutionCode = l_banch.getInstitution().getInstitutionCode();

            //���X�R�[�h���擾����
            l_strBranchCode = l_banch.getBranchCode();
            //���ʃR�[�h���擾����
            l_strOrderRequestNumber = l_ifoOrderUnitRow.getOrderRequestNumber();
            //�ڋq�R�[�h���擾����
            l_strAccountCode =
                l_accountManager.getMainAccount(
                    l_ifoOrderUnitRow.getAccountId()).getAccountCode();
            //���҃R�[�h���擾����
            l_strTraderCode = l_ifoOrderUnitRow.getSonarTraderCode();
            
			//�����������擾����
            l_strOrderConditionType = l_ifoOrderUnitRow.getOrderConditionType();

			//�󒍓����Ɍ��ݎ�����ݒ肷��
			l_tsCreateDateTime = GtlUtils.getSystemTimestamp();
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            String l_strCreateDate = l_format.format(l_tsCreateDateTime);
                        
            //�󒍓��敪���擾����
            l_strOrderDateDiv = WEB3OrderDateDivDef.YESTERDAY;
            if (l_ifoOrderUnitRow.getBizDate().compareTo(l_strCreateDate) == 0)
            {
                l_strOrderDateDiv = WEB3OrderDateDivDef.TODAY;
            }
            
            //�����R�[�h���擾����
            l_strProductCode = l_ifoProductImpl.getProductCode();

            //���������ŏI�ʔԂ��擾����
            l_intOrderActionSerialNo =
                    l_ifoOrderUnitRow.getLastOrderActionSerialNo();

            //�f�[�^�R�[�h���擾����
            if (l_strFuturesOptionDiv.equals(WEB3FuturesOptionDivDef.FUTURES))
            {
                ORDER_REQUEST_CODE = WEB3HostRequestCodeDef.FUTURES_ORDER_CHANGE_CANCEL;
            }

            //�敨�I�v�V�������i���擾����
            if (IfoDerivativeTypeEnum.FUTURES.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.FUTURES;
            }
            else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
            }
            else if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductImpl.getDerivativeType()))
            {
                l_strFutureOptionProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
            }

            IfoOrderExecutionConditionType l_ifoExecutionConditionType =
                l_ifoOrderUnitRow.getConfirmedExecConditionType();
            if (IfoOrderExecutionConditionType.AT_MARKET_OPEN.equals(l_ifoExecutionConditionType))
            {
                //��t
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE.equals(l_ifoExecutionConditionType))
            {
                //����
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
            }
            else if (IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_ifoExecutionConditionType))
            {
                //�o�����Έ���(�s��)
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
            }
            else if (!l_ifoOrderUnitRow.getFirstOrderUnitIdIsNull())
            {
                // �o����܂Œ���(�����P��.���񒍕��̒����P��ID��null)�̏ꍇ�A�Q�F�o���B
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                //������
                l_strConfirmedExecutionCondition = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
            }

            //���t/���t���ʂ��擾����
            SideEnum l_sideEnum = l_orderUnit.getSide();
            if (SideEnum.BUY.equals(l_sideEnum))
            //�����P��.getSide()=="����"�̏ꍇ
            {
                //�����P��.�s�ꂩ��m�F�ς݂̐���
                l_dblBuyOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
            }
            else if (SideEnum.SELL.equals(l_sideEnum))
            //�����P��.getSide()=="����"�̏ꍇ
            {
                //�����P��.�s�ꂩ��m�F�ς݂̐���
                l_dblSellOrderQuantity = l_ifoOrderUnitRow.getConfirmedQuantity();
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finOjbectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_finOjbectManager.getMarket(l_ifoOrderUnitRow.getMarketId());
            WEB3IfoFrontOrderService l_orderService =
                (WEB3IfoFrontOrderService)Services.getService(WEB3IfoFrontOrderService.class);
            //�Ώۃf�[�^���擾����----------------------------[END]

            //(*1) �s�ꖢ���M�����̏ꍇ�̂ݏ������{
            if (l_blnIsMarketNotSendMessage)
            {
                log.debug("�s�ꖢ���M�����̏ꍇ�̂ݏ������{" );

                //�i�����P��.�������� == �h�t�w�l�h�@@���@@�����P��.���N�G�X�g�^�C�v!=�h�����T�[�o�h�j�̏ꍇ�͏����I���B
                if(WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType)
                    && !WEB3RequestTypeDef.QUOTE_SERVER.equals(l_ifoOrderUnitRow.getRequestType()))
                {
                    log.debug("�t�w�l�����̏ꍇ�͏����I��");
                    log.exiting(METHOD_NAME);
                    return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                }

                //�،���ЁA���X�A���ʃR�[�h�A�����敪��OP���_�敨OP��������L���[�e�[�u������
                try
                {   //throw DataFindException, DataNetworkException, DataQueryException
                    QueryProcessor l_processor = Processors.getDefaultProcessor();
                    int l_intIndex = l_orderService.getIndexOfOrderRevInCorpCode();
                    int l_intFigure = l_orderService.getFigureOfOrderRev();
                    StringBuffer l_sbWhere = new StringBuffer();
                    l_sbWhere.append(" institution_code = ? ");
                    l_sbWhere.append(" and branch_code = ? ");
                    l_sbWhere.append(" and order_request_number = ? ");
                    l_sbWhere.append(" and substr(corp_code," + l_intIndex + "," + l_intFigure + ")=? ");
                    l_sbWhere.append(" and status = ? ");

                    Object[] l_objParams =
                    {   l_strInstitutionCode,
                        l_strBranchCode,
                        l_strOrderRequestNumber,
                        l_ifoOrderUnitRow.getOrderRev(),
                        WEB3HostStatusDef.NOT_STARTED_PROCESS};

                    //�Ώۃf�[�^�폜
                    int l_intDeleteCnt = l_processor.doDeleteAllQuery(
                        HostFotypeOrderAllRow.TYPE,
                        l_sbWhere.toString(),
                        l_objParams);
                        
                    if (l_intDeleteCnt == 0)
                    {
						return DefaultMarketRequestSendResult
							.newFailedResultInstance(
								ProcessingResult.newFailedResultInstance(
									WEB3ErrorCatalog.BUSINESS_ERROR_00010));
                    }

                }
                catch (DataException l_de)
                {
                    WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + METHOD_NAME,
                        l_de.getMessage(),
                        l_de);

                    log.error("__an unexpected error__",l_sysException);
                    l_processingResult = ProcessingResult.newFailedResultInstance(
                        l_sysException.getErrorInfo());

                    return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                }

                //�������I������
                log.exiting(METHOD_NAME);
                return DefaultMarketRequestSendResult.newSuccessResultInstance(
                    l_lngMsgTokenId);

            }
            //�s��J�ǎ��ԑтł��邩�𔻒肷��B
            boolean l_blnIsOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
            //is������x�e���ԑ�()
            boolean l_blnIsTradeCloseTimeZone =
                WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();

            //�g���K�[���s����
            boolean l_blnIsSubmitMarketTrigger =
                WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(l_strOrderConditionType);

            //�i*2�j�i�x�e���ԑсj�@@or�@@�i�g���K�[���s����j�̏ꍇ
            if (l_blnIsTradeCloseTimeZone || l_blnIsSubmitMarketTrigger)
            {
                HostFotypeOrderAllParams l_hostFotypeOrderAllParams = null;
                //�x�e���ԑсiis������x�e���ԑ�==true�j�̏ꍇ
                if (l_blnIsTradeCloseTimeZone)
                {
                    //get�敨OP��������L���[(IfoOrderUnit)
                    //�敨OP��������L���[���擾����B
                    //[�敨OP�����T�[�r�X.get�敨OP��������L���[()�Ɏw�肷�����]
                    //�����P�ʁF�@@�����P��
                    l_hostFotypeOrderAllParams =
                        l_orderService.getHostFotypeOrderAll((IfoOrderUnit)l_orderUnit);
                }

                //�L���[�f�[�^���݂��Ȃ��ꍇ�Ainsert
                if (l_hostFotypeOrderAllParams == null)
                {
                    l_hostFotypeOrderAllParams =
                        new HostFotypeOrderAllParams();

                    //�f�[�^�R�[�h
                    l_hostFotypeOrderAllParams.setRequestCode(ORDER_REQUEST_CODE);
                    //�����h�c
                    l_hostFotypeOrderAllParams.setAccountId(l_orderUnit.getAccountId());
                    //�،���ЃR�[�h
                    l_hostFotypeOrderAllParams.setInstitutionCode(l_strInstitutionCode);
                    //���X�R�[�h
                    l_hostFotypeOrderAllParams.setBranchCode(l_strBranchCode);
                    //�ڋq�R�[�h
                    l_hostFotypeOrderAllParams.setAccountCode(l_strAccountCode);
                    //���҃R�[�h
                    l_hostFotypeOrderAllParams.setTraderCode(l_strTraderCode);
                    //�󒍓��敪
                    l_hostFotypeOrderAllParams.setReceivedDateTimeDiv(l_strOrderDateDiv);
                    //���ʃR�[�h
                    l_hostFotypeOrderAllParams.setOrderRequestNumber(l_strOrderRequestNumber);
                    //�s��R�[�h�iSONAR�j
                    l_hostFotypeOrderAllParams.setSonarMarketCode(l_ifoOrderUnitRow.getSonarMarketCode());
                    //�����R�[�h
                    l_hostFotypeOrderAllParams.setProductCode(l_strProductCode);
                    //�󒍓���
                    l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                    //��������ԍ�
                    l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                    //�����o�H�敪
                    l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                    //�����Y�����R�[�h
                    l_hostFotypeOrderAllParams.setTargetProductCode(l_ifoProductImpl.getUnderlyingProductCode());
                    //�����i�N�j
                    l_hostFotypeOrderAllParams.setDeliveryMonthYyyy(
                        l_ifoProductImpl.getMonthOfDelivery().substring(0, 4));
                    //�����i���j
                    l_hostFotypeOrderAllParams.setDeliveryMonthMm(
                        l_ifoProductImpl.getMonthOfDelivery().substring(4, 6));
                    //�敨�I�v�V�������i
                    l_hostFotypeOrderAllParams.setFutureOptionProductType(l_strFutureOptionProductType);
                    //�s�g���i
                    l_hostFotypeOrderAllParams.setStrikePrice(l_ifoProductImpl.getStrikePrice());
                    //����
                    l_hostFotypeOrderAllParams.setSplitType(l_ifoProductRow.getSplitType());
                    //���t����
                    l_hostFotypeOrderAllParams.setSellOrderQuantity(l_dblSellOrderQuantity);
                    //���t����
                    l_hostFotypeOrderAllParams.setBuyOrderQuantity(l_dblBuyOrderQuantity);
                    //�w�l
                    l_hostFotypeOrderAllParams.setLimitPrice(l_ifoOrderUnitRow.getConfirmedPrice());
                    //���s����
                    l_hostFotypeOrderAllParams.setExecutionCondition(l_strConfirmedExecutionCondition);
                    //�t�w�l��l
                    l_hostFotypeOrderAllParams.setStopOrderPrice(null);
                    //�iW�w�l�j�����w�l
                    l_hostFotypeOrderAllParams.setWLimitPrice(null);
                    //����敪
                    l_hostFotypeOrderAllParams.setTransactionType(null);
                    //�`�[��
                    l_hostFotypeOrderAllParams.setTicketNumber(null);
                    //���ʃ`�F�b�N
                    l_hostFotypeOrderAllParams.setContractCheck(null);
                    //�����`���l��
                    l_hostFotypeOrderAllParams.setOrderChanel(null);
                    //�萔����
                    l_hostFotypeOrderAllParams.setCommisionNumber(null);
                    //�萔�����}��
                    l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                    //�萔�����i�R�[�h
                    l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                    //��������
                    l_hostFotypeOrderAllParams.setChangeQuantity(null);
                    //�����w�l
                    l_hostFotypeOrderAllParams.setChangeLimitPrice(null);
                    //�������s����
                    l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);
                    //�����t�w�l��l
                    l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                    //�����iW�w�l�j�����w�l
                    l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                    //����敪
                    l_strCancelDiv = WEB3CancelDivDef.CANCEL;
                    l_hostFotypeOrderAllParams.setCancelDiv(l_strCancelDiv);
                    //�t�����g����������敪�R�[�h
                    l_hostFotypeOrderAllParams.setFrontOrderExchangeCode(
                        l_orderService.getFrontOrderExchangeCode(l_market.getMarketCode()));
                    //�t�����g�����V�X�e���敪
                    l_hostFotypeOrderAllParams.setFrontOrderSystemCode(
                        l_orderService.getFrontOrderSystemCode(l_market.getMarketCode()));
                    //�t�����g��������敪�R�[�h
                    l_hostFotypeOrderAllParams.setFrontOrderTradeCode(
                        WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    //���Ȉϑ��敪
                    l_hostFotypeOrderAllParams.setTradeauditCode(null);
                    //�Г���������
                    l_hostFotypeOrderAllParams.setCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //�i������j�Г���������
                    l_hostFotypeOrderAllParams.setOrgCorpCode(
                        l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                    //���z�T�[�oNo.�iJSOES�j
                    l_hostFotypeOrderAllParams.setVirtualServerNumberJsoes(null);
                    //�s�ꔭ��No.
                    l_hostFotypeOrderAllParams.setMarketOrderNumber(null);
                    //AMG���M����
                    l_hostFotypeOrderAllParams.setAmgSendTime(null);
                    //AMG���͕ۏ؎�M����
                    l_hostFotypeOrderAllParams.setAmgAckTime(null);
                    //�s����͕ۏ؎�M����
                    l_hostFotypeOrderAllParams.setMarketAckTime(null);
                    //�S���������敪
                    l_hostFotypeOrderAllParams.setAllOrderChangeDiv(
                        WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    //�����敪
                    l_hostFotypeOrderAllParams.setStatus(l_strStatus);
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        l_processor.doInsertQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
                //�L���[�f�[�^���݂���ꍇ�A�L���[�f�[�^update
                else
                {
                    try
                    {
                        QueryProcessor l_processor = Processors.getDefaultProcessor();

                        //�󒍓���
                        l_hostFotypeOrderAllParams.setReceivedDateTime(l_tsCreateDateTime);
                        //set��������ԍ�
                        l_hostFotypeOrderAllParams.setOrderActionSerialNo(l_intOrderActionSerialNo);
                        //set�����o�H�敪
                        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv(
                            l_ifoOrderUnitRow.getSubmitOrderRouteDiv());
                        //����敪
                        l_hostFotypeOrderAllParams.setTransactionType(null);
                        //�`�[��
                        l_hostFotypeOrderAllParams.setTicketNumber(null);
                        //���ʃ`�F�b�N
                        l_hostFotypeOrderAllParams.setContractCheck(null);
                        //�����`���l��
                        l_hostFotypeOrderAllParams.setOrderChanel(null);
                        //�萔����
                        l_hostFotypeOrderAllParams.setCommisionNumber(null);
                        //�萔�����}��
                        l_hostFotypeOrderAllParams.setCommisionBranchNumber(null);
                        //�萔�����i�R�[�h
                        l_hostFotypeOrderAllParams.setCommisionProductCode(null);
                        //��������
                        l_hostFotypeOrderAllParams.setChangeQuantity(null);
                        //�����w�l
                        l_hostFotypeOrderAllParams.setChangeLimitPrice(null);
                        //�������s����
                        l_hostFotypeOrderAllParams.setChangeExecutionCondition(null);
                        //�����t�w�l��l
                        l_hostFotypeOrderAllParams.setChangeStopOrderPrice(null);
                        //�����iW�w�l�j�����w�l
                        l_hostFotypeOrderAllParams.setChangeWLimitPrice(null);
                        //����敪
                        l_hostFotypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.CANCEL);
                        //�Г���������
                        l_hostFotypeOrderAllParams.setCorpCode(
                            l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                        //�i������j�Г���������
                        l_hostFotypeOrderAllParams.setOrgCorpCode(
                            l_orderService.getOrgCorpCode((IfoOrderUnit)l_orderUnit));
                        //�X�V���t
                        l_hostFotypeOrderAllParams.setLastUpdatedTimestamp(l_tsCreateDateTime);
                        l_processor.doUpdateQuery(l_hostFotypeOrderAllParams);
                    }
                    catch (DataException l_ex)
                    {
                        WEB3SystemLayerException l_sysException =
                            new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + METHOD_NAME,
                                l_ex.getMessage(),
                                l_ex);

                        log.error("__an unexpected error__",l_sysException);
                        l_processingResult = ProcessingResult.newFailedResultInstance(
                            l_sysException.getErrorInfo());

                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
                //is�g���K���s()���R�[��
                l_isTriggerIssue =
                    WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
                        null);

                String l_strMarketCode = l_market.getMarketCode();

                //isMQ�g���K���s�o�H()
                //MQ�g���K���s�v�ۂ��擾����B
                //[isMQ�g���K���s�o�H()�Ɏw�肷�����]
                //�،���ЃR�[�h�F�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                //�����^�C�v�F�@@�����P��.�����^�C�v
                //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��R�[�h
                //�����o�H�敪�F�@@�����P��.�����o�H�敪
                //�t�����g�����V�X�e���敪�F�@@�敨OP��������L���[.�t�����g�����V�X�e���敪
                boolean l_blnIsSubmitMQTriggerEnable = WEB3GentradeOrderSwitching.isSubmitMQTriggerEnable(
                    l_strInstitutionCode,
                    l_ifoOrderUnitRow.getProductType(),
                    l_strMarketCode,
                    l_ifoOrderUnitRow.getSubmitOrderRouteDiv(),
                    l_hostFotypeOrderAllParams.getFrontOrderSystemCode());
                //�g���K�[���s���ԑ�&&MQ�g���K���s�o�H�̏ꍇ�̂ݏ������{
                if (l_isTriggerIssue && l_blnIsSubmitMQTriggerEnable)
                {
                    String l_strMQDataCode =
                        l_orderService.getChangeCancelMQDataCode(
                            (IfoOrderUnit)l_orderUnit);
                    if (l_strMQDataCode == null)
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(
                            l_lngMsgTokenId);
                    }
                    //WEB3MQMessageSpec(�،���ЃR�[�h : String, �f�[�^�R�[�h : String)
                    //WEB3MQMessageSpec�𐶐�����B
                    //[�R���X�g���N�^�Ɏw�肷�����]
                    //�،���ЃR�[�h�F
                    //�@@�����P��.�،����ID�ɊY������،���ЃR�[�h
                    //�f�[�^�R�[�h�F
                    //�@@�敨OP�����T�[�r�X.get���������MQ�f�[�^�R�[�h()�̖߂�l
                    l_web3MQMessageSpec =
                        new WEB3MQMessageSpec(
                            l_strInstitutionCode,
                            l_strMQDataCode);

                    //MQ�T�[�r�X���擾����
                    l_web3MQGatewayService =
                        (WEB3MQGatewayService) Services.getService(
                            WEB3MQGatewayService.class);

                    //send(MQ���b�Z�[�W���e : WEB3MQMessageSpec)
                    l_web3MQSendResult =
                        l_web3MQGatewayService.send(l_web3MQMessageSpec);

                    if (l_web3MQSendResult.isSuccessResult())
                    {
                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newSuccessResultInstance(l_lngMsgTokenId);
                    }
                    else
                    {
                        l_processingResult =
                            ProcessingResult.newFailedResultInstance(
                                l_web3MQSendResult.getErrorInfo());

                        log.exiting(METHOD_NAME);
                        return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
                    }
                }
            }
            //�s�ꑗ�M�ϒ����Ŏs��ǎ��ԑсiis�s�ꖢ���M == false && is�s��J�ǎ��ԑ�() == false)�̏ꍇ�A
            //�܂��́A�s�ꑗ�M�ϒ����ŗ[�ꎞ�ԑтœ����o�^����
            //�iis�s�ꖢ���M == false && ������ԊǗ�.is�[�ꎞ�ԑ�() == true && �����P��.����敪 == null�j�̏ꍇ�A
            //������m�肳���ď������I������B
            if ((!l_blnIsMarketNotSendMessage
                && !l_blnIsOpenTimeZone)
                || (!l_blnIsMarketNotSendMessage
                    && WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone()
                    && WEB3StringTypeUtility.isEmpty(l_ifoOrderUnitRow.getSessionType())))
            {
                //����m��
                this.updateOrderCancelled(l_orderUnit);
            }
        }
		catch (WEB3SystemLayerException l_sle)
		{
			WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + METHOD_NAME,
			    l_sle.getMessage(),
                l_sle);
			log.error("__an unexpected error__",l_sysException);
			ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
				l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
		}
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException = new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);

            log.error("__an unexpected error__",l_sysException);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                l_sysException.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error("__an unexpected error__",l_wbe);
            ProcessingResult l_processingResult = ProcessingResult.newFailedResultInstance(
                    l_wbe.getErrorInfo());

            return DefaultMarketRequestSendResult.newFailedResultInstance(l_processingResult);
        }
        log.exiting(METHOD_NAME);
        return DefaultMarketRequestSendResult.newSuccessResultInstance(0L);

   }


    /**
     * (�����m��)<BR>
     * <BR>
     * �������m�肳����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj�����m��v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@roseuid 40A463D90009
     */
    protected void updateOrderModified(OrderUnit l_orderUnit)
    throws WEB3BaseException
    {
        final String METHOD_NAME = "validateChangeOrder(OrderUnit)";
        log.entering(METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error("parameter is null type");
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);//throw NotInstalledException

            WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();


            //�敨OP�����m��X�V�C���^�Z�v�^
            WEB3IfoChangeConfirmUpdateInterceptor l_interceptor =
                             new WEB3IfoChangeConfirmUpdateInterceptor();
            //OP�����}�l�[�W��
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                             l_interceptor);

            long l_lngOrderId = l_orderUnit.getOrderId();

            DefaultChangeOrderAcceptedMarketResponseMessage l_changeResponseMessage =
                new DefaultChangeOrderAcceptedMarketResponseMessage(l_lngOrderId);

            MarketAdapter l_markertAdapter = l_tradingModule.getMarketAdapter();

            IfoMarketResponseReceiverCallbackService l_callBackService =
            (IfoMarketResponseReceiverCallbackService) l_markertAdapter.getMarketResponseReceiverCallbackService();

            ProcessingResult l_processingResult = l_callBackService.process(l_changeResponseMessage);

            if (l_processingResult.isFailedResult())
            {
                throw new WEB3SystemLayerException(l_processingResult.getErrorInfo(),
                    this.getClass().getName() + "." + METHOD_NAME);
            }
        }
        catch (NotInstalledException l_nie)
        {
            log.error(l_nie.getMessage(),l_nie);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nie.toString(),
                l_nie);
        }
    }

    /**
     * (����m��)<BR>
     * <BR>
     * ������m�肳����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨OP�s��ظ��āj����m��v�Q�ƁB<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g<BR>
     * @@roseuid 40A81D830072
     */
    protected void updateOrderCancelled(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String METHOD_NAME = "validateCancelOrder(OrderUnit)";
        log.entering(METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.error("parameter is null type");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);

            WEB3OptionOrderManagerImpl l_orderManager =
                (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();


            MarketAdapter l_marketAdapter = l_tradingModule.getMarketAdapter();
            //�敨OP����m��X�V�C���^�Z�v�^
            WEB3IfoCancelConfirmUpdateInterceptor l_interceptor =
                       new WEB3IfoCancelConfirmUpdateInterceptor();
            //OP�����}�l�[�W��
            l_orderManager.setThreadLocalPersistenceEventInterceptor(
                       l_interceptor);

            long l_lngOrderId = l_orderUnit.getOrderId();

            DefaultCancelOrderAcceptedMarketResponseMessage l_cancelResponseMessage =
                new DefaultCancelOrderAcceptedMarketResponseMessage(l_lngOrderId);


            IfoMarketResponseReceiverCallbackService l_callBackService =
                (IfoMarketResponseReceiverCallbackService) l_marketAdapter.getMarketResponseReceiverCallbackService();

            ProcessingResult l_processingResult = l_callBackService.process(l_cancelResponseMessage);
            
            if (l_processingResult.isFailedResult())
            {
                throw new WEB3SystemLayerException(l_processingResult.getErrorInfo(),
                    this.getClass().getName() + "." + METHOD_NAME);
            }
        }
        catch (NotInstalledException l_nie)
        {
            log.error(l_nie.getMessage(),l_nie);
            log.exiting(METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + METHOD_NAME,
                l_nie.toString(),
                l_nie);
        }
    }

    /**
      * @@param arg0
      * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult
      * @@roseuid 40C0D45800DA
      */
    public MarketRequestSendResult send(MarketRequestMessage arg0)
    {
        return null;
    }
}
@
