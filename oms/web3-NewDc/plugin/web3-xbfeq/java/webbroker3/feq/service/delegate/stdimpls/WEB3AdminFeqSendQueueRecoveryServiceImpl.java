head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueRecoveryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[���J�o���T�[�r�XImpl(WEB3AdminFeqSendQueueRecoveryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ����� (���u) �V�K�쐬
Revesion History : 2008/08/11 ���g (���u) �c�a�X�V�d�lNo.093
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3AdminFeqSendQueueRecoveryCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueRecoveryCompleteResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueRecoveryService;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�XImpl)<BR>
 * �Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X�����N��<BR>
 * 
 * @@author �����
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueRecoveryServiceImpl implements WEB3AdminFeqSendQueueRecoveryService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueRecoveryServiceImpl.class);
        
    /**
     * @@roseuid 42D0CED203D8
     */
    public WEB3AdminFeqSendQueueRecoveryServiceImpl() 
    {
     
    }
    
    /**
     * �Ǘ��ҊO������SEND�L���[���J�o�����������{����B<BR>
     * <BR>
     * submit�X�V()���\�b�h���R�[������B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4214980A032E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqSendQueueRecoveryCompleteRequest)
        {
            //submit�X�V()
            l_response = 
                this.submitUpdate((WEB3AdminFeqSendQueueRecoveryCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * �Ǘ��ҊO������SEND�L���[���J�o���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO������SEND�L���[���J�o���T�[�r�X)submit�X�V�v�Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3AdminFeqSendQueueRecoveryCompleteResponse
     * @@throws WEB3BaseException 
     * @@roseuid 4214980A032E
     */
    protected WEB3AdminFeqSendQueueRecoveryCompleteResponse submitUpdate(
        WEB3AdminFeqSendQueueRecoveryCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitUpdate(WEB3AdminFeqSendQueueRecoveryCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����()
        //���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();       
        
        //1.3 validate����()
        //�Ǘ��Ҍ����`�F�b�N���s���B 
        //[validate����()�Ɏw�肷�����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�O���i�������Ǘ��j 
        //is�X�V�F�@@true(�X�V����) 
        //���@@�\�J�e�S���R�[�h�́ADB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);//WEB3BaseException
        
        //1.4 ArrayList()
        //�G���[����ݒ肷�郊�X�g�𐶐�����B
        List l_lisErrorInfo = new ArrayList();
        
        //1.5 �X�V������0�ŏ���������
        int l_intIncreaseNum = 0;
        
        //1.6 (*) ���N�G�X�g.�L���[ID�ꗗ�̌������A�������J��Ԃ��B
        int l_intQueueIdLength = l_request.queueIdList.length;
        for (int i = 0; i < l_intQueueIdLength; i ++)
        {
            //1.6.1 �Ǘ��ҊO������SEND�L���[���J�o��TransactionCallback(String)
            //�Ǘ��ҊO������SEND�L���[���J�o��TransactionCallback�I�u�W�F�N�g�𐶐�����B 
            //[�R���X�g���N�^�Ɏw�肷�����] 
            //�L���[ID�F�@@���N�G�X�g.�L���[ID�ꗗ[i]
            WEB3AdminFeqSendQueueRecoveryTransactionCallback l_transactionCallback = 
                new WEB3AdminFeqSendQueueRecoveryTransactionCallback(l_request.queueIdList[i]);
            
            //1.6.2 doTransaction(int, TransactionCallback)
            //DB�g�����U�N�V�������������{����B 
            //[doTransaction()�Ɏw�肷�����] 
            //�g�����U�N�V���������F�@@TX_CREATE_NEW 
            //TransactionCallback�F�@@���������Ǘ��ҊO������SEND�L���[���J�o��TransactionCallback�I�u�W�F�N�g
            boolean l_blnCallBackExp = false;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doTransaction(
               		QueryProcessor.TX_CREATE_NEW,
                    l_transactionCallback);
            }
            //1.6.3 doTransaction()��DataCallbackException���X���[����ꍇ
            catch(DataCallbackException l_ex)
            {
                l_lisErrorInfo.add(l_ex.getMessage());
                l_blnCallBackExp = true;
            }
            catch (DataNetworkException l_dataNetworkException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataNetworkException.getMessage(),
                    l_dataNetworkException);
            }
            catch (DataQueryException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            if (!l_blnCallBackExp)
            {
                //1.6.4 doTransaction()������I�������ꍇ
                //1.6.4.1 �X�V�������C���N�������g����
                l_intIncreaseNum ++;            
            }
        }
        
        //1.7 �G���[���̃��X�g.size() > 0 �̏ꍇ�A�G���[�����擾����
        //1.7.1 toArray(Object[])
        //�G���[���̃��X�g�̓��e���AString�̔z��ɐݒ肷��B 
        //[toArray()�Ɏw�肷�����] 
        //�z��F�@@�G���[���i��������String�̔z��j
        String[] l_strErrorInfo = null;
        if (l_lisErrorInfo.size() > 0)
        {
            l_strErrorInfo = new String[l_lisErrorInfo.size()];
            l_lisErrorInfo.toArray(l_strErrorInfo);
        }
        
        //1.8 createResponse()
        WEB3AdminFeqSendQueueRecoveryCompleteResponse l_response = 
            (WEB3AdminFeqSendQueueRecoveryCompleteResponse)l_request.createResponse();
        
        //1.9 �v���p�e�B�Z�b�g
        l_response.errorInfomations = l_strErrorInfo;    
        l_response.updateNumber = l_intIncreaseNum + "";    
      
        log.exiting(STR_METHOD_NAME);
        //1.10 return ���X�|���X�f�[�^
        return l_response;
    }
    
    /**
     * (�Ǘ��ҊO������SEND�L���[���J�o��TransactionCallback)<BR>
     * �g�����U�N�V�������������{��������N���X<BR>
     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
     * 
     * @@roseuid 4214980A032E
     */
    public class WEB3AdminFeqSendQueueRecoveryTransactionCallback implements TransactionCallback
    {
        /**
         * ���O�o�̓��[�e�B���e�B�B
         */
        private WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueRecoveryTransactionCallback.class);
        
        /**
         * (�L���[ID)<BR>
         * �L���[ID<BR>
         */
        private String queueId;
        
        /**
         * �R���X�g���N�^�B<BR>
         * <BR>
         * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
         * @@param l_strQuereId - (�L���[ID)<BR>
         * �L���[ID<BR>
         * @@roseuid 42D0CED203D8
         */
        public WEB3AdminFeqSendQueueRecoveryTransactionCallback(String l_strQueueId) 
        {
            this.queueId = l_strQueueId;
        }
       
        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�Ǘ��ҊO������SEND�L���[���J�o���g�����U�N�V�����jprocess�v�Q��<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4214980A032E
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = " process() ";
            log.entering(STR_METHOD_NAME);
            
            //1.1 �����ɊY������(�O�������)SEND_Q�e�[�u���̃��R�[�h���擾����
            String l_strWhere = " queue_id = ? ";
            String l_strCondition = " for update ";

            Object l_bindVars[] ={new Long(this.queueId)};
            
            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
            List l_lisRows = l_queryProcesser.doFindAllQuery(
                SleSendQRow.TYPE,
                l_strWhere, 
                l_strCondition, 
                l_bindVars);
            DataCallbackException l_DataCallbackException = null;
            //1.2 ���R�[�h���擾�ł��Ȃ������ꍇ�ADataCallbackException���X���[����
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                l_DataCallbackException = 
                    new DataCallbackException(
                        this.queueId + ":���R�[�h�����݂��܂���",
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }
            
            SleSendQRow l_sleSendQRow = (SleSendQRow)l_lisRows.get(0);
            
            //1.3 �擾����(�O�������)SEND_Q�e�[�u��Params.get�d�q���[�����M����() != null �ꍇ�A
            //DataCallbackException���X���[����
            SleSendQParams l_sleSendQParams = new SleSendQParams(l_sleSendQRow);
            if (l_sleSendQParams.getSendProcessDateTime() != null)
            {
                log.exiting(STR_METHOD_NAME);
                l_DataCallbackException = 
                    new DataCallbackException(
                        l_sleSendQParams.getOrderEmpCode() + ":���[�����M�Z��",
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }
            
            //1.4 getOrderUnit
            //�O�����������P�ʃI�u�W�F�N�g���擾����B 
            //[getOrderUnit()�Ɏw�肷�����] 
            //�����P��ID�F (�O�������)SEND_Q�e�[�u��Params.get�����P��ID()�̖߂�l
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = 
                l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            WEB3FeqOrderUnit l_feqOrderUnit = null;
            try
            {
                l_feqOrderUnit = 
                    (WEB3FeqOrderUnit)l_orderMgr.getOrderUnit(l_sleSendQParams.getOrderUnitId());
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
            
            //1.5 get�s��
            //�s��I�u�W�F�N�g���擾����B
            WEB3GentradeMarket l_market = null;
            try
            {
                l_market = l_feqOrderUnit.getMarket();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            //1.6 is�V�X�e���A��
            //1.7 is�V�X�e���A��()�̖߂�l == true �ꍇ�ADataCallbackException���X���[����
            if (l_market.isSystemInterLock())
            {
                log.exiting(STR_METHOD_NAME);
                l_DataCallbackException = 
                    new DataCallbackException(
                        l_sleSendQParams.getOrderEmpCode() + ":" + l_sleSendQParams.getMarketCode() + ":�s��A����",
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }
            
            //1.8 getAttribute
            //�X���b�h���[�J�����A������ԃR���e�L�X�g���擾����B 
            //[getAttribute()�Ɏw�肷�����] 
            //�������F�@@WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH
            WEB3GentradeTradingClendarContext l_tradingClendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            //1.9 set���X�R�[�h
            //���X�R�[�h���擾����B 
            //[get���X�R�[�h()�Ɏw�肷�����] 
            //���X�R�[�h�F�@@(�O�������)SEND_Q�e�[�u��Params.get���X�R�[�h()
            l_tradingClendarContext.setBranchCode(l_sleSendQParams.getBranchCode());
            
            //1.10 set�s��R�[�h
            //�s��R�[�h���擾����B 
            //[get�s��R�[�h()�Ɏw�肷�����] 
            //�s��R�[�h�F�@@(�O�������)SEND_Q�e�[�u��Params.get�s��R�[�h()
            l_tradingClendarContext.setMarketCode(l_sleSendQParams.getMarketCode());
            
            //1.11 setTimestamp
            //���ݓ������Z�b�g����B
            try
            {
                WEB3GentradeTradingTimeManagement.setTimestamp();
            }
            catch (WEB3SystemLayerException l_ex)
            {
                log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);
                throw new WEB3BaseRuntimeException(
                    l_ex.getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            
            WEB3FeqMailSenderService l_mailSenderService = 
                (WEB3FeqMailSenderService)Services.getService(WEB3FeqMailSenderService.class);
            
            try
            {
	            //1.12 (�O�������)SEND_Q�e�[�u��Params.get�I�y���[�^�^�C�v()�̖߂�l == "0�F�V�K" �ꍇ
	            if (SleSendqOpTypeEnum.NEW_ORDER.equals(l_sleSendQParams.getOpType()))
	            {
	                //1.12.1 create�V�K����Mail
	                //�V�K�������[���𐶐�����B 
	                //[create�V�K����Mail()�Ɏw�肷�����] 
	                //�����P�ʁF �擾�����O�����������P��
	                l_mailSenderService.createNewOrderMail(l_feqOrderUnit);
	            }
	            //1.13 (�O�������)SEND_Q�e�[�u��Params.get�I�y���[�^�^�C�v()�̖߂�l == "2�F���" �ꍇ
	            else if (SleSendqOpTypeEnum.CANCEL_ORDER.equals(l_sleSendQParams.getOpType()))
	            {
	                //1.13.1 create�������Mail
	                //����������[���𐶐�����B 
	                //[create�������Mail()�Ɏw�肷�����] 
	                //�����P�ʁF �擾�����O�����������P��
	                l_mailSenderService.createCancelOrderMail(l_feqOrderUnit);
	            }
	            //1.14 (�O�������)SEND_Q�e�[�u��Params.get�I�y���[�^�^�C�v()�̖߂�l == "1�F����" �ꍇ
	            else if (SleSendqOpTypeEnum.CHANGE_ORDER.equals(l_sleSendQParams.getOpType()))
	            {
	                //1.14.1 create��������Mail
	                //�����������[���𐶐�����B 
	                //[create��������Mail()�Ɏw�肷�����] 
	                //�����P�ʁF �擾�����O�����������P�� 
	                l_mailSenderService.createChangeOrderMail(l_feqOrderUnit);
	            }
            }
            //1.15 ���[�����M��������O���X���[����ꍇ�ADataCallbackException���X���[����
            catch (WEB3BaseException l_ex)
            {
                l_DataCallbackException =
                    new DataCallbackException(
                        l_sleSendQParams.getOrderEmpCode() + ":" + l_ex.getErrorInfo().getErrorMessage(),
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                throw l_DataCallbackException;
            }              
            
            //1.16 (�O�������)SEND_Q�e�[�u�����X�V����
            l_sleSendQParams.setSendProcessDateTime(GtlUtils.getSystemTimestamp());
            l_sleSendQParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_sleSendQParams.setStatus(SleSendqProcStatusEnum.PROCESSED);
            l_sleSendQParams.setConfirmedBySleRcvdQ(BooleanEnum.TRUE);
            try 
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_sleSendQParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    l_ex.getMessage(), 
                    l_ex);
            }
            
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
