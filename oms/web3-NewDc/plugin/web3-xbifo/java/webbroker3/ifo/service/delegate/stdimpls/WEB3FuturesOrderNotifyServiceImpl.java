head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�����ʒm�T�[�r�X�����N���X(WEB3FuturesOrderNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/02 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.DataRollbackException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifo.data.HostFotypeOrderReceiptParams;
import webbroker3.ifo.data.HostFotypeOrderReceiptRow;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyService;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨�����ʒm�T�[�r�XImpl)<BR>
 * �����w���敨�����ʒm�T�[�r�X�����N���X
 * @@author  : ������
 * @@version : 1.0
 */
public class WEB3FuturesOrderNotifyServiceImpl implements WEB3FuturesOrderNotifyService 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderNotifyServiceImpl.class);
   
    /**
     * @@roseuid 41AD654600DA
     */
    public WEB3FuturesOrderNotifyServiceImpl() 
    {
       
    }
   
    /**
     * �����w���敨�����ʒm�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�敨�T�[�r�X�j�敨�����ʒm�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 417500A802C9
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1.1 getDefaultProcessor()
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //1.2  �敨�����ʒmTransactionCallback()
            WEB3FuturesOrderNotifyTransactionCallback l_orderNotifyTransactionCallback = 
                new WEB3FuturesOrderNotifyTransactionCallback();

            //1.3 �����Ώۃp�����[�^�Z�b�g�i�ڍז���j

            //1.4 doTransaction(TransactionCallback)
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_orderNotifyTransactionCallback);
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
           
        log.exiting(STR_METHOD_NAME);
        return l_request.createResponse();
    }
   
    /**
     * (�敨�����ʒmTransactionCallback)<BR>
     * �敨�����ʒmTransactionCallback<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     */
    public class WEB3FuturesOrderNotifyTransactionCallback implements TransactionCallback 
    {
         
        /**
         * (�敨�����ʒmTransactionCallback)<BR> 
         * �R���X�g���N�^
         * @@roseuid 4174FFA80323
         */
        public WEB3FuturesOrderNotifyTransactionCallback() 
        {
           
        }
      
        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�敨�����ʒm�jprocess�v�Q�ƁB<BR>
         * <BR>
         * ���߂�l�́Anull�Ƃ���B<BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4174FFDB01EC
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException 
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            //1.1  (*1)�敨OP�����ʒm�L���[�e�[�u���Ǎ�
            //(*1) �敨OP�����ʒm�L���[�e�[�u�����A�s���b�N�iselect for update�j�I�v�V�����ɂēǂݍ��ށB
            //�@@[��������]
            //�@@�敨OP�����ʒm�L���[�e�[�u��.�f�[�^�R�[�h == "�敨�����ʒm"(EI822)
            //�@@�敨OP�����ʒm�L���[�e�[�u��.�����敪 == "������"
            StringBuffer l_strWhere = new StringBuffer();
            l_strWhere.append(" request_code = ? ");
            l_strWhere.append(" and status = ? ");

            Object[] l_objWhere = {
                WEB3HostRequestCodeDef.FUTURES_ORDER_NOTICE, // �敨�����ʒm(EI822)
                WEB3StatusDef.NOT_DEAL};                     // ������

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecords = l_QueryProcessor.doFindAllQuery(
                HostFotypeOrderReceiptRow.TYPE,
                l_strWhere.toString(),
                null,
                null,
                l_objWhere);

            int l_intListRecordCnt = 0;
            if (l_lisRecords != null)
            {
                l_intListRecordCnt = l_lisRecords.size();
            }
            log.debug("l_lisRecords.size() = " + l_lisRecords.size());

            //1.2 �敨OP�����ʒm�L���[�e�[�u���������ʃ��R�[�h�e�s���Ƃ�Loop����
            for (int i=0; i<l_intListRecordCnt; i++)
            {
                log.debug("LOOP i = " + i);
                HostFotypeOrderReceiptRow l_receiptRow = (HostFotypeOrderReceiptRow)l_lisRecords.get(i);
                HostFotypeOrderReceiptParams l_notifyParams = new HostFotypeOrderReceiptParams(l_receiptRow);
                try
                {
                    // TransactionCallback����
                    WEB3FuturesOrderNotifyNormalTransactionCallback l_transactionCallback =
                        new WEB3FuturesOrderNotifyNormalTransactionCallback(
                            l_receiptRow,
                            l_notifyParams);

                    // doTransaction()
                    l_QueryProcessor.doTransaction(
                        QueryProcessor.TX_CREATE_NEW,
                        l_transactionCallback);
                }
                catch (Exception l_exp)
                {
                    if (l_exp instanceof WEB3BaseRuntimeException)
                    {
                        WEB3BaseRuntimeException l_wre = (WEB3BaseRuntimeException) l_exp;
                        //�������b�N�G���[�̏ꍇ(�����Ώې敨OP�����ʒm�L���[���R�[�h.�����敪�́h�������h�̂܂�)
                        if (WEB3ErrorCatalog.SYSTEM_ERROR_80076.equals(l_wre.getErrorInfo()))
                        {
                            log.debug("�������b�N���s�F" + l_notifyParams.toString());
                            continue;
                        }
                    }
                    if (l_exp instanceof DataCallbackException)
                    {
                        DataCallbackException l_dce = (DataCallbackException)l_exp;
                        WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
                        log.error("�ꌏ�����ɂăG���[����", l_wbe);
                    }
                    else
                    {
                        log.error("�ꌏ�����ɂăG���[����", l_exp);
                    }
                    //�����Ώې敨OP�����ʒm�L���[���R�[�h�����敪���h�G���[�h�ōX�V
                    l_notifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_notifyParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    l_QueryProcessor.doUpdateQuery(l_notifyParams);
                }
            }
            
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
