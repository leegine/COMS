head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�o���I���ʒm�T�[�r�X�����N���X(WEB3IfoExecuteEndNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/22 䈋� (���u) �V�K�쐬
              001: 2004/07/29 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000063 execute()���C��
              002: 2004/07/29 ���Ō� (���u) �Ή��o�b�O WEB3_IFO_UT-000068�A70�A71�A72�A73 execute()���C��
Revesion History : 2007/06/08 ��іQ (���u) ���f��No.694
Revesion History : 2007/11/19 �И��� �d�l�ύX���f��No.802,813,821 �c�a�X�V�d�lNo.194
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyRequest;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyResponse;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (�敨OP�o���I���ʒm�T�[�r�X����)<BR>
 * �敨OP�o���I���ʒm�T�[�r�X�����N���X<BR>
 */
public class WEB3IfoExecuteEndNotifyServiceImpl
    implements WEB3IfoExecuteEndNotifyService
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoExecuteEndNotifyServiceImpl.class);

    /**
    * @@roseuid 40C0753000CB
    */
    public WEB3IfoExecuteEndNotifyServiceImpl()
    {

    }

    /**
     * �敨OP�o���I���ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�iOP�T�[�r�X�j�敨OP�o���I���ʒm�v�Q��<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 4057BA0E0169
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3IfoExecEndNotifyRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //�敨OP�o���I���ʒm���N�G�X�g.validate()
        WEB3IfoExecEndNotifyRequest l_ifoExecEndNotifyRequest =
            (WEB3IfoExecEndNotifyRequest)l_request;
        l_ifoExecEndNotifyRequest.validate();

        //���b�Z�[�W getDefaultProcessor( )(Processors::getDefaultProcessor)
        QueryProcessor l_queryProcessor = null;

        //���b�Z�[�W doTransaction(TransactionCallback)(QueryProcessor::doTransaction)
        try
        {
            l_queryProcessor = Processors.getDefaultProcessor();
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME);
        }
        //�I�����C�����s����TransactionCallback
        WEB3OnlineRunStatusTransactionCallback l_onlineCallBack =
            new WEB3OnlineRunStatusTransactionCallback(
                l_ifoExecEndNotifyRequest.institutionCode,
                l_ifoExecEndNotifyRequest.rangeFrom,
                l_ifoExecEndNotifyRequest.rangeTo,
                l_ifoExecEndNotifyRequest.fuOpDiv,
                l_ifoExecEndNotifyRequest.execEndDiv);
        WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
        try
        {
            //�I�����C�����s����TransactionCallback::�I�����C�����s����TransactionCallback)
            l_onlineRunStatus =
                (WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_onlineCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            // set������()�����d�N���̗�O��throw���ꂽ�ꍇ
            Object l_exception = l_dce.getDetails();
            if (l_exception instanceof WEB3BaseException)
            {
                WEB3BaseException l_baseException = (WEB3BaseException)l_exception;
                if (WEB3ErrorCatalog.BUSINESS_ERROR_01992.equals(l_baseException.getErrorInfo()))
                {
                    log.debug("�w��AP�N�����i��d�N���G���[�j�B", l_baseException);

                    //���������ɂ��̂܂�return����B
                    WEB3IfoExecEndNotifyResponse l_response =
                        (WEB3IfoExecEndNotifyResponse)l_request.createResponse();

                    log.exiting(STR_METHOD_NAME);
                    return l_response;
                }
                throw l_baseException;
            }
        }
        catch (DataRollbackException l_drbe)
        {
            log.error(STR_METHOD_NAME, l_drbe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_drbe.getMessage(),
                l_drbe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        try
        {
            //�敨OP�o���I���ʒmTransactionCallback::�敨OP�o���I���ʒmTransactionCallback)
            WEB3IfoExecuteEndNotifyTransactionCallback l_ifoCallBack =
                new WEB3IfoExecuteEndNotifyTransactionCallback(
                    l_ifoExecEndNotifyRequest.institutionCode,
                    l_ifoExecEndNotifyRequest.rangeFrom,
                    l_ifoExecEndNotifyRequest.rangeTo,
                    l_ifoExecEndNotifyRequest.fuOpDiv,
                    l_ifoExecEndNotifyRequest.execEndDiv,
                    l_onlineRunStatus);

            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_ifoCallBack);
        }
        catch (DataCallbackException l_dce)
        {
            log.error(STR_METHOD_NAME, l_dce);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dce.getMessage(),
                l_dce);
        }
        catch (DataRollbackException l_drbe)
        {
            log.error(STR_METHOD_NAME, l_drbe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_drbe.getMessage(),
                l_drbe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }

        WEB3IfoExecEndNotifyResponse l_response =
            (WEB3IfoExecEndNotifyResponse)l_request.createResponse();

        return l_response;
    }


    /**
     * �敨OP�o���I���ʒmTransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3IfoExecuteEndNotifyTransactionCallback
        implements TransactionCallback
    {

        /**
         * (�،���ЃR�[�h)<BR>
         */
        private String institutionCode;

        /**
         * (From����ID)<BR>
         */
        private long rangeFrom;

        /**
         * (To����ID)<BR>
         */
        private long rangeTo;

        /**
         * (�敨�^�I�v�V�����敪)<BR>
         */
        private String futureOpDiv;

        /**
         * (�o���I���敪)<BR>
         */
        private String execEndDiv;

        /**
         * (�I�����C�����s����)<BR>
         * �I�����C�����s���ʃN���X�B<BR>
         */
        private WEB3GentradeOnlineRunStatus onlineRunStatus;

        /**
         * �f�t�H���g�R���X�g���N�^
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h
         * @@param l_lngFangeFrom - (From����ID)<BR>
         * From����ID
         * @@param l_longRangeTo - (To����ID)<BR>
         * To����ID
         * @@param l_strFutureOpDiv - (�敨�^�I�v�V�����敪)<BR>
         * �敨�^�I�v�V�����敪
         * @@param l_strExecEndDiv - (�o���I���敪)<BR>
         * �o���I���敪
         * @@param l_onlineRunStatus - (�I�����C�����s����)<BR>
         * �I�����C�����s���ʃN���X�B
         * @@roseuid 408C92B00113
         */
        public WEB3IfoExecuteEndNotifyTransactionCallback(
            String l_strInstitutionCode,
            long l_lngFangeFrom,
            long l_longRangeTo,
            String l_strFutureOpDiv,
            String l_strExecEndDiv,
            WEB3GentradeOnlineRunStatus l_onlineRunStatus)
        {
            //�،���ЃR�[�h
            this.institutionCode = l_strInstitutionCode;
            //From����ID
            this.rangeFrom = l_lngFangeFrom;
            //To����ID
            this.rangeTo = l_longRangeTo;
            //�敨�^�I�v�V�����敪
            this.futureOpDiv = l_strFutureOpDiv;
            //�o���I���敪
            this.execEndDiv = l_strExecEndDiv;
            //�I�����C�����s����
            this.onlineRunStatus = l_onlineRunStatus;
        }

        /**
         * �o���I���ʒm���������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨OP�o���I���ʒm�jprocess�v�Q�ƁB<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 408C92B00112
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            //OP�����}�l�[�W��
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = null;
            l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            WEB3IfoExecuteEndNotifyUnitService l_unitservice =
                (WEB3IfoExecuteEndNotifyUnitService)Services.getService(WEB3IfoExecuteEndNotifyUnitService.class);
            Institution l_institution = null;

            try
            {
                l_institution =
                    l_finApp.getAccountManager().getInstitution(this.institutionCode);
            }
            catch (NotFoundException l_ex)
            {
                ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80005;
                l_errorInfo.setErrorClass(l_ex.getClass().getName());
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_errorInfo);
            }

            OrderUnit[] l_orderUnits = null;
            try
            {
                //get�����L�������P��
                l_orderUnits = l_optionOrderManagerImpl.getTodayOpenOrderUnits(
                    this.futureOpDiv,
                    l_institution,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_exp)
            {
                ErrorInfo l_errorInfo = l_exp.getErrorInfo();
                l_errorInfo.setErrorClass(l_exp.getClass().getName());
                throw new DataCallbackException(
                    l_exp.getErrorMessage(),
                    l_errorInfo);
            }

            //get�����L�������P��() != null�̏ꍇ�A�擾���������P�ʂ��Ƃ�Loop
            boolean l_blnIsCarryOverAllAccountsSuccess = true;

            if (l_orderUnits != null)
            {
                int l_intOrderUnitLength = l_orderUnits.length;
                for (int i = 0; i < l_intOrderUnitLength; i++)
                {
                    try
                    {
                        l_unitservice.notifyExecuteEnd(l_orderUnits[i], this.execEndDiv);
                    }
                    catch (Exception l_ex)
                    {
                        log.debug(STR_METHOD_NAME, l_ex);
                        l_blnIsCarryOverAllAccountsSuccess = false;
                    }
                }
            }

            //update���s�X�e�[�^�X�敪
            //���s�X�e�[�^�X�敪�F
            //�J�z�������S�ڋq�Ő���I�������ꍇ��"������"
            //1�ڋq�ł��V�X�e���G���[���X���[���ꂽ�ꍇ��"�G���["���Z�b�g�B
            String l_strRunStatusDiv = null;
            if (l_blnIsCarryOverAllAccountsSuccess)
            {
                //�J�z�������S�ڋq�Ő���I�������ꍇ��"������"
                l_strRunStatusDiv = WEB3RunStatusDivDef.DEALED;
            }
            else
            {
                //1�ڋq�ł��V�X�e���G���[���X���[���ꂽ�ꍇ��"�G���["���Z�b�g�B
                l_strRunStatusDiv = WEB3RunStatusDivDef.ERROR;
            }

            try
            {
                //update���s�X�e�[�^�X�敪
                this.onlineRunStatus.updateRunStatusDiv(l_strRunStatusDiv);
            }
            catch (WEB3BaseException l_exp)
            {
                ErrorInfo l_errorInfo = l_exp.getErrorInfo();
                l_errorInfo.setErrorClass(l_exp.getClass().getName());
                throw new DataCallbackException(
                    l_exp.getErrorMessage(),
                    l_errorInfo);
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * �I�����C�����s����TransactionCallback�N���X�B<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3OnlineRunStatusTransactionCallback
        implements TransactionCallback
    {
        /**
         * (�،���ЃR�[�h)<BR>
         */
        private String institutionCode;

        /**
         * (From����ID)<BR>
         */
        private long rangeFrom;

        /**
         * (To����ID)<BR>
         */
        private long rangeTo;

        /**
         * (�敨�^�I�v�V�����敪)<BR>
         */
        private String futureOpDiv;

        /**
         * (�o���I���敪)<BR>
         */
        private String execEndDiv;

        /**
         * �f�t�H���g�R���X�g���N�^
         * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
         * �،���ЃR�[�h
         * @@param l_lngFangeFrom - (From����ID)<BR>
         * From����ID
         * @@param l_longRangeTo - (To����ID)<BR>
         * To����ID
         * @@param l_strfutureOpDiv - (�敨�^�I�v�V�����敪)<BR>
         * �敨�^�I�v�V�����敪
         * @@param l_strExecEndDiv - (�o���I���敪)<BR>
         * �o���I���敪
         */
        public WEB3OnlineRunStatusTransactionCallback(
            String l_strInstitutionCode,
            long l_lngFangeFrom,
            long l_longRangeTo,
            String l_strfutureOpDiv,
            String l_strExecEndDiv)
        {
            //�،���ЃR�[�h
            this.institutionCode = l_strInstitutionCode;
            //From����ID
            this.rangeFrom = l_lngFangeFrom;
            //To����ID
            this.rangeTo = l_longRangeTo;
            //�敨�^�I�v�V�����敪
            this.futureOpDiv = l_strfutureOpDiv;
            //�o���I���敪
            this.execEndDiv = l_strExecEndDiv;
        }
        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨OP�T�[�r�X�j�敨OP�o���I���ʒm�v�Q�ƁB<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //�I�����C���T�[�r�X�敪
            String l_strOnlineServiceDiv = null;
            // �I�����C�����s����.set������()���\�b�h���R�[������B
            // �،���ЃR�[�h�F�@@this.�،���ЃR�[�h
            // �����^�C�v�F�@@"�敨�I�v�V����"
            // �敨�^�I�v�V�����敪�F�@@this.�敨�^�I�v�V�����敪
            // �I�����C���T�[�r�X�敪�F�@@�ȉ��̂Ƃ���ɐݒ肷��B
            //  this.�o���I���敪 == "1�F�[��O�o���I��"�̏ꍇ�A"9�F�[��O�o���I���ʒm"
            //  this.�o���I���敪 == "0�F�i�ŏI�j�o���I��"�̏ꍇ�A"1�F�o���I���ʒm"
            // From����ID�F�@@this.From����ID
            // To����ID�F�@@this.To����ID
            if (WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END.equals(this.execEndDiv))
            {
                //"9�F�[��O�o���I���ʒm"
                l_strOnlineServiceDiv = WEB3OnlineServiceDiv.BEFORE_EVENING_SESSION_ORDER_EXEC_END;
            }
            else if (WEB3OrderexecutionEndTypeDef.DEFAULT.equals(this.execEndDiv))
            {
                //"1�F�o���I���ʒm"
                l_strOnlineServiceDiv = WEB3OnlineServiceDiv.ORDER_EXEC_END;
            }

            WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
            try
            {
                l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
                    this.institutionCode,
                    ProductTypeEnum.IFO,
                    this.futureOpDiv,
                    l_strOnlineServiceDiv,
                    this.rangeFrom,
                    this.rangeTo);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(l_ex.getErrorMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getMessage(),
                    l_ex);
            }
            //set������()�̖߂�l��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_onlineRunStatus;
        }
    }
}
@
