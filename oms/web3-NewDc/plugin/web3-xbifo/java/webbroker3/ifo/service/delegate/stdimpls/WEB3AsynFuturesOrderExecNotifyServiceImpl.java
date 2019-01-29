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
filename	WEB3AsynFuturesOrderExecNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �����w���敨�o���ʒm�T�[�r�X�����N���X(WEB3AsynFuturesOrderExecNotifyServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : �����ŗ����F
 Revesion History : 2004/7/23 䈋� (���u) �V�K�쐬
 Revesion History : 2007/04/25 �����Q (���u) �d�l�ύX���f��No.634
 */

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyRow;
import webbroker3.ifo.data.HostOptionOrderExecNotifyParams;
import webbroker3.ifo.data.HostOptionOrderExecNotifyRow;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.util.WEB3LogUtility;

import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;

/**
 * �i�񓯊��Ή������w���敨�o���ʒm�T�[�r�X�����N���X�j�B
 * @@author  : ���u���i���{���u�j
 * @@version : 1.0
 */
public class WEB3AsynFuturesOrderExecNotifyServiceImpl
    implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynFuturesOrderExecNotifyServiceImpl.class);

    /**
     * �����w���敨�o���ʒm���N�G�X�g
     */
    private WEB3FuturesOrderExecNotifyRequest request;


    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3AsynFuturesOrderExecNotifyServiceImpl(WEB3FuturesOrderExecNotifyRequest
        l_request)
    {
        this.request = l_request;
    }

    public void run()
    {
        final String STR_METHOD_NAME =
            "WEB3AsynFuturesOrderExecNotifyServiceImpl�Frun()";
        log.entering(STR_METHOD_NAME);

        try
        {
            try
            {
                WEB3FuturesOrderExecNotifyRequest l_orderExecNotifyRequest =
                    (WEB3FuturesOrderExecNotifyRequest) request;

                //1.1 getDefaultProcessor()
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                //1.2 �敨�o���ʒmTransactionCallback()
                WEB3FuturesOrderExecNotifyTransactionCallback l_orderExecNotifyTransactionCallback =
                    new WEB3FuturesOrderExecNotifyTransactionCallback();

                //set���ʃR�[�h�v���t�B�N�X�ꗗ()
                l_orderExecNotifyTransactionCallback.setOrderRequestNumberPrefixGroup(
                    l_orderExecNotifyRequest.orderRequestNumberPrefixGroup);

                //1.4 doTransaction(�g�����U�N�V�������� : int,
                //  �敨�o���ʒmTransactionCallback : TransactionCallback)
                l_queryProcessor.doTransaction(
                    QueryProcessor.TX_CREATE_NEW,
                    l_orderExecNotifyTransactionCallback);
            }
            catch (DataRollbackException l_dataRollbackException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataRollbackException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataRollbackException.getMessage(),
                    l_dataRollbackException);
            }
            catch (DataFindException l_dataFindException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataFindException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataFindException.getMessage(),
                    l_dataFindException);
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
            catch (DataCallbackException l_dataCallbackException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataCallbackException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataCallbackException.getMessage(),
                    l_dataCallbackException);
            }
            catch (DataQueryException l_dataQueryException)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_dataQueryException.getMessage(),
                    l_dataQueryException);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }

        //�X���b�h�J��
        try
        {
            new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
                longValue());
        }
        catch (WEB3SystemLayerException ex)
        {
            log.error(ex.getMessage(), ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�敨�o���ʒmTransactionCallback)<BR>
     * �敨�o���ʒmTransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3FuturesOrderExecNotifyTransactionCallback
        implements TransactionCallback
    {
        /**
         * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        private String[] orderRequestNumberPrefixGroup;

        /**
         * (set�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * �����̎��ʃR�[�h�v���t�B�N�X�ꗗ���v���p�e�B�ɃZ�b�g����B<BR>
         * @@params String[] - ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
         */
        public void setOrderRequestNumberPrefixGroup(String[]
            l_orderRequestNumberPrefixGroup)
        {
            orderRequestNumberPrefixGroup = l_orderRequestNumberPrefixGroup;
        }

        /**
         * (get�����̎��ʃR�[�h�v���t�B�N�X�ꗗ)<BR>
         * <BR>
         * ���ʃR�[�h�v���t�B�N�X�ꗗ���擾����B<BR>
         * @@return String[]<BR>
         */
        public String[] getOrderRequestNumberPrefixGroup()
        {
            return orderRequestNumberPrefixGroup;
        }

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨�o���ʒm�jprocess�v�Q�ƁB<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 40874E4D02BE
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_orderManager = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            HostFotypeCloseOrderNotifyParams l_closeParams = null;
            HostFotypeOrderClmdNotifyParams l_cancelParams = null;

            //1.1 �敨OP�o���ʒm�L���[�e�[�u���Ǎ�
            //[��������]
            //�敨OP�o���ʒm�L���[�e�[�u��.�f�[�^�R�[�h == �h�����w���o���ʒm�h�iEI815�j
            //�敨OP�o���ʒm�L���[�e�[�u��.�����敪 == �h�������h
            String l_strWhere = " request_code = ? ";       //�f�[�^�R�[�h = ?
            l_strWhere = l_strWhere + " and   status = ? "; //�����敪 = ?

            int l_intLength = 0;
            if (orderRequestNumberPrefixGroup != null)
            {
                l_intLength = orderRequestNumberPrefixGroup.length;
            }
            if (l_intLength > 0)
            {
                l_strWhere = l_strWhere + " and (";
                for (int i = 0; i < l_intLength; i++)
                {
                    if (i > 0)
                    {
                        l_strWhere = l_strWhere + " or ";
                    }
                    l_strWhere = l_strWhere + "order_request_number like ?";
                }
                l_strWhere = l_strWhere + ")";
            }

            String[] l_strBindValues = new String[l_intLength + 2];
            l_strBindValues[0] = WEB3HostRequestCodeDef.FUTURES_EXEC_NOTICE;//�����P��.���ʃR�[�h
            l_strBindValues[1] = WEB3StatusDef.NOT_DEAL; //������

            for (int i = 0; i < l_intLength; i++)
            {
                l_strBindValues[i + 2] = orderRequestNumberPrefixGroup[i] + "%";
            }

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_QueryProcessor.doFindAllQuery(
                HostOptionOrderExecNotifyParams.TYPE,
                l_strWhere,
                null,
                null,
                l_strBindValues);

            if ((l_lisRecords != null) && !l_lisRecords.isEmpty())
            {
                int l_intListRecordCnt = l_lisRecords.size();
                log.debug("l_lisRecords.size() = " + l_intListRecordCnt);

                //1.2 �敨OP�o���ʒm�L���[�e�[�u���������ʃ��R�[�h���Ƃ�LOOP
                for (int i = 0; i < l_intListRecordCnt; i++)
                {
                    log.debug("LOOP i = " + i);
                    HostOptionOrderExecNotifyRow l_notifyRow = (HostOptionOrderExecNotifyRow)l_lisRecords.get(i);
                    HostOptionOrderExecNotifyParams l_notifyParams = new HostOptionOrderExecNotifyParams(l_notifyRow);
                    try
                    {
                        
                        // TransactionCallback����
                        WEB3FuturesOrderExecNotifyNormalTransactionCallback l_transactionCallback =
                            new WEB3FuturesOrderExecNotifyNormalTransactionCallback(
                            l_notifyRow,
                            l_notifyParams);

                        // doTransaction()
                        l_QueryProcessor.doTransaction(
                            QueryProcessor.TX_CREATE_NEW,
                            l_transactionCallback);
                    }
                    catch (DataCallbackException l_exp)     
                    {       
                        ErrorInfo l_errorInfo = (ErrorInfo)l_exp.getDetails();  

                        // �����ʒm���������s�����ꍇ       
                        if ((l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_01961)
                            || (l_errorInfo==WEB3ErrorCatalog.SYSTEM_ERROR_80077))        
                        {       
                            log.debug("�����ʒm�������s");  
                            String l_strWhereClose = " request_code = ? "   
                                + " and institution_code = ? "
                                + " and branch_code = ? "
                                + " and account_code = ? "
                                + " and order_request_number = ? "
                                + " and status = ? ";

                            Object[] l_objWhereClose =  
                            {   
                                WEB3HostRequestCodeDef.FUTURES_CLOSE_NOTICE,
                                l_notifyParams.getInstitutionCode(),
                                l_notifyParams.getBranchCode(),
                                l_notifyParams.getAccountCode(),
                                l_notifyParams.getOrderRequestNumber(),
                                WEB3StatusDef.DEALING
                            };  
        
                            List l_lisSearchResultClose =   
                                l_QueryProcessor.doFindAllQuery(    
                                    HostFotypeCloseOrderNotifyRow.TYPE, 
                                    l_strWhereClose,    
                                    null,   
                                    "for update",   
                                    l_objWhereClose);   
        
                            if (l_lisSearchResultClose.size() > 0)  
                            {   
                                l_closeParams = (HostFotypeCloseOrderNotifyParams)l_lisSearchResultClose.get(0);
                                l_closeParams.setStatus(WEB3StatusDef.DATA_ERROR);
                                l_closeParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                                l_QueryProcessor.doUpdateQuery(l_closeParams);
                            }   
                        }       

                        // ����ʒm���������s�����ꍇ        
                        if ((l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_01962)
                            || (l_errorInfo==WEB3ErrorCatalog.SYSTEM_ERROR_80078))     
                        {        
                            log.debug("����ʒm�������s");   
                            String l_strWhereClose = " request_code = ? "    
                                + " and institution_code = ? "
                                + " and branch_code = ? "
                                + " and account_code = ? "
                                + " and order_request_number = ? "
                                + " and status = ? ";

                            Object[] l_objWhereClose =   
                            {    
                                WEB3HostRequestCodeDef.FUTURES_CHANGE_CANCEL_NOTICE,
                                l_notifyParams.getInstitutionCode(),
                                l_notifyParams.getBranchCode(),
                                l_notifyParams.getAccountCode(),
                                l_notifyParams.getOrderRequestNumber(),
                                WEB3StatusDef.DEALING
                            };   
        
                            List l_lisSearchResultClose =    
                                l_QueryProcessor.doFindAllQuery( 
                                    HostFotypeOrderClmdNotifyRow.TYPE,
                                    l_strWhereClose, 
                                    null,    
                                    "for update",    
                                    l_objWhereClose);    
        
                            if (l_lisSearchResultClose.size() > 0)   
                            {    
                                l_cancelParams = (HostFotypeOrderClmdNotifyParams)l_lisSearchResultClose.get(0);
                                l_cancelParams.setStatus(WEB3StatusDef.DATA_ERROR);
                                l_cancelParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                                l_QueryProcessor.doUpdateQuery(l_cancelParams);
                            }    
                        }        
        
                        //�s�ꂩ��m�F�ς݂̐���==null�̏ꍇ
                        if (l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_01975)
                        {
                            log.debug("�Y�������͎�t���ρ^�ύX�̎�t�ρ^�������̏�ԁB");
                            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_QueryProcessor.doUpdateQuery(l_notifyParams);
                            continue;
                        }

                        //�����P�ʂ��擾�o���Ȃ��̏ꍇ
                        if (l_errorInfo==WEB3ErrorCatalog.BUSINESS_ERROR_02752)
                        {
                            log.debug("�����P�ʂ��擾�o���Ȃ��B");
                            l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                            l_QueryProcessor.doUpdateQuery(l_notifyParams);
                            continue;
                        }

                        //�G���[������ȊO�̏ꍇ�@@(=>�G���[)        
                        log.debug("�ꌏ�����ɂăG���[�����F" + l_notifyParams.toString());       
                        l_notifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_QueryProcessor.doUpdateQuery(l_notifyParams);      
                    }        
                    catch (Exception l_exp)
                    {
                        //--------------------
                        //�����ΏۃL���[UPDATE�@@(�G���[��)
                        //--------------------
                        if (l_exp instanceof WEB3BaseRuntimeException)
                        {
                            WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                            if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                            {
                                log.debug("�������b�N���s�F" + l_notifyParams.toString());
                                continue;
                            }
                        }

                        //�G���[������ȊO�̏ꍇ�@@(=>�G���[)
                        log.debug("�ꌏ�����ɂăG���[�����F" + l_notifyParams.toString());
                        l_notifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                        l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_QueryProcessor.doUpdateQuery(l_notifyParams);
                    }
                }
            }

            log.exiting(STR_METHOD_NAME);
            return null;
        }

        /**
         * (OP�o���ʒmTransactionCallback)<BR>
         * �R���X�g���N�^<BR>
         * @@roseuid 40874FA3035B
         */
        public WEB3FuturesOrderExecNotifyTransactionCallback()
        {
        }
    }
}@
