head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.34.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ɒʒm�T�[�r�XImpl(WEB3AioOutputNotifyServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;

import webbroker3.aio.data.HostSecDeliveryTransferParams;
import webbroker3.aio.data.HostSecDeliveryTransferRow;
import webbroker3.aio.data.HostTransferPaymentParams;
import webbroker3.aio.data.HostTransferPaymentRow;
import webbroker3.aio.define.WEB3HostTransferPaymentStatusDef;
import webbroker3.aio.define.WEB3AioHostTransferPaymentTransferFlagDef;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;

/**
 * (�o�ɒʒm�T�[�r�XImpl)<BR>
 * �o�ɒʒm�T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyServiceImpl implements WEB3AioOutputNotifyService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOutputNotifyServiceImpl.class); 
    
    /**
     * �o�ɒʒm�������s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �u�i�o�ɒʒm�jexecute�v �Q�� <BR>
     * <BR> 
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 41579661009E
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //If (l_request == null)
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        try
        {
            //�P.1�j�o�ɒʒmTransactionCallback( )
            WEB3AioOutputNotifyTransactionCallBack
                l_aioOutputNotifyTransactionCallBack =
                    new WEB3AioOutputNotifyTransactionCallBack();
            
            //�P.2�j�N�G���v���Z�b�T�̃C���X�^���X���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // �P.3�jDB�g�����U�N�V�������������{����B
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_aioOutputNotifyTransactionCallBack);
            
            //1.4�j�o�ɒʒm���N�G�X�g.create���X�|���X()���R�[�����A
             // �o�ɒʒm���X�|���X�I�u�W�F�N�g�𐶐�����B
            WEB3BackResponse l_backResponse = l_request.createResponse();
            //�|���������o�ɒʒm���X�|���X��Ԃ��B
            log.exiting(STR_METHOD_NAME);
            return l_backResponse;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̃C���X�^���X���擾���s");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�N�G���v���Z�b�T�̃C���X�^���X���擾���s");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (�o�ɒʒmTransactionCallback)<BR>
     *  �o�ɒʒmTransactionCallback�N���X
     */
    public class WEB3AioOutputNotifyTransactionCallBack implements TransactionCallback 
    {
        /**
         *  ���O���[�e�B���e�B<BR>
         */
        private WEB3LogUtility m_log =
            WEB3LogUtility.getInstance(WEB3AioOutputNotifyTransactionCallBack.class);
        
        /**
         * (�o�ɒʒmTransactionCallback)<BR>
         * �f�t�H���g�R���X�g���N�^
         * @@return WEB3AioOutputNotifyTransactionCallBack
         * @@roseuid 415796810178
         */
        public WEB3AioOutputNotifyTransactionCallBack() 
        {
         
        }
        
        /**
         * �o�ɒʒm���������{����B  <BR>
         * <BR>
         * �V�[�P���X�}  <BR>
         * �u�i�o�ɒʒm�jprocess�v �Q��<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 415796B5005F
         */
        public Object process() 
        	throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            m_log.entering(STR_METHOD_NAME);

            //1.1�j�i���P�j�U�֐����`�[�L���[�e�[�u���Ǎ���
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            StringBuffer l_strWhereA = new StringBuffer();
            l_strWhereA.append(" request_code = ? ");
            l_strWhereA.append(" and transfer_flag = ? ");
            l_strWhereA.append(" and status = ? ");   
            String l_strCondition = null;
            
            Object[] l_objWhereValuesA = { 
             	WEB3HostRequestCodeDef.AIO_REQUEST_SLIP, 
             	WEB3AioHostTransferPaymentTransferFlagDef.TRANSFER_STORAGE_OUT,
             	WEB3HostTransferPaymentStatusDef.NOT_DEAL};     
            /*����*/
            List l_lisOrderNotifyresultsA =
                l_queryProcessor.doFindAllQuery(
                    HostTransferPaymentRow.TYPE,
                    l_strWhereA.toString(),
                    l_strCondition,
                    l_objWhereValuesA);
             
            //1.2�j�擾�����L���[�e�[�u���̃��R�[�h����LOOP����            
            int l_intSizeA = 0;
            if(l_lisOrderNotifyresultsA != null && !l_lisOrderNotifyresultsA.isEmpty())
            {
                l_intSizeA = l_lisOrderNotifyresultsA.size();
            }                      
            //�����敪��ݒ�
            HashMap l_map = new HashMap();
            //�U�֐����`�[�L���[Params
            HostTransferPaymentRow l_transferPaymentParams = null;
            for (int i = 0; i < l_intSizeA; i++)
            {
                l_transferPaymentParams =
                    (HostTransferPaymentParams) l_lisOrderNotifyresultsA.get(i);
                                
                try
                {
                    // TransactionCallback����
                    WEB3AioOutputNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AioOutputNotifyNormalTransactionCallback(
                            l_transferPaymentParams, true);
    
                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }

                catch (Exception l_ex)
                {
                    if(l_ex instanceof WEB3BaseRuntimeException)
                    {
                        //�������b�N�Ɏ��s���ăG���[�����������ꍇ�A
                        //�����ΏۃL���[���X�V���Ȃ��B
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_wre.getErrorInfo()))
                        {
                            continue;
                        } 
                    }
                    log.error("__an Exception__ ", l_ex);
                    l_map.put("status", WEB3HostTransferPaymentStatusDef.DATA_ERROR);
                                    
					// �����Ώۂ̐U�֐����`�[�L���[�e�[�u��.�����敪��ݒ�
					String l_strUpdateWhereA = "rowid = ? ";

					String[] l_objUpdateWhereValuesA = {l_transferPaymentParams.getRowid()};
             
                    //  1.2.2 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
                    l_queryProcessor.doUpdateAllQuery(
                        HostTransferPaymentRow.TYPE,
                        l_strUpdateWhereA.toString(),
                        l_objUpdateWhereValuesA,
                        l_map);
                }
            }
            
            //1.3 �i���R�j�،��o�ɐ����`�[�L���[�e�[�u���Ǎ���
            StringBuffer l_strWhereB = new StringBuffer();
            l_strWhereB.append(" request_code = ? ");
            l_strWhereB.append(" and status = ? ");   

            Object[] l_objWhereValuesB = { 
	            WEB3HostRequestCodeDef.SECURITIES_OUT_REQUEST_SLIP, 
               	WEB3HostTransferPaymentStatusDef.NOT_DEAL}; 
                
            /*����*/
            List l_lisOrderNotifyresultsB =
                l_queryProcessor.doFindAllQuery(
                    HostSecDeliveryTransferRow.TYPE,
                    l_strWhereB.toString(),
                    l_strCondition,
                    l_objWhereValuesB);
            
            //1.4�j�擾�����L���[�e�[�u���̃��R�[�h����LOOP����      
            int l_intSizeB = 0;
            if(l_lisOrderNotifyresultsB != null && !l_lisOrderNotifyresultsB.isEmpty())
            {
                l_intSizeB = l_lisOrderNotifyresultsB.size();
            }
            //�،��o�ɐ����`�[�L���[Params
            HostSecDeliveryTransferParams l_secDeliveryTransferParams = null;
            for (int i = 0; i < l_intSizeB; i++)
            {
                l_secDeliveryTransferParams =
                    (HostSecDeliveryTransferParams) l_lisOrderNotifyresultsB.get(i);
                
                try
                {
                    // TransactionCallback����
                    WEB3AioOutputNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3AioOutputNotifyNormalTransactionCallback(
                            l_secDeliveryTransferParams, false);
    
                    // doTransaction()
                    l_queryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_ex)
                {
                    if(l_ex instanceof WEB3BaseRuntimeException)
                    {
                        //�������b�N�Ɏ��s���ăG���[�����������ꍇ�A
                        //�����ΏۃL���[���X�V���Ȃ��B
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_ex;
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80003.equals(l_wre.getErrorInfo()))
                        {
                            continue;
                        } 
                    }
                    log.error("__an Exception__ ", l_ex);
                    l_map.put("status", WEB3HostTransferPaymentStatusDef.DATA_ERROR);
                     
					// �����Ώۂ̏،��o�ɐ����`�[�L���[�e�[�u��.�����敪��ݒ�p
					String l_strUpdateWhereB = "rowid = ? ";

					String[] l_objUpdateWhereValuesB = {l_secDeliveryTransferParams.getRowid()};
             
                    //  1.4.2 �L���[�e�[�u���̃��R�[�h�̏����敪�̍X�V
                    l_queryProcessor.doUpdateAllQuery(
                        HostSecDeliveryTransferRow.TYPE,
                        l_strUpdateWhereB.toString(),
                        l_objUpdateWhereValuesB,
                        l_map);
                } 

            }
            m_log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
