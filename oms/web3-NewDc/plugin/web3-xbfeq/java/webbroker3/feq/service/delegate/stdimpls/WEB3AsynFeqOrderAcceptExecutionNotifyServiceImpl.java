head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �񓯊��Ή��O������������t�o���ʒm�T�[�r�XImpl(WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 ꎉ� (���u) �V�K�쐬
                   2006/11/24 ����� (���u) ���f��No.307
                   2006/12/14 ꎉ� (���u) ���f��No.311
Revesion History : 2007/07/04 �đo�g(���u) ���f��No.351
Revesion History : 2008/10/02 ���V(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.467
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqTypeOrderManagerReusableValidations;
import webbroker3.feq.define.WEB3FeqOrderExecRouteDivDef;
import webbroker3.feq.message.WEB3FeqOrderAcceptExecNotifyRequest;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.slebase.data.SleRcvdQParams;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleRcvdqProcStatusEnum;
import webbroker3.util.WEB3LogUtility;

/**
 * (�񓯊��Ή��O������������t�o���ʒm�T�[�r�XImpl)<BR>
 * �O������������t�o���ʒm�񓯊��T�[�r�X�����N���X<BR>
 * 
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl implements Runnable
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl.class);
    
    /**
     * (�O������������t�o���ʒm���N�G�X�g)<BR>
     * �O������������t�o���ʒm���N�G�X�g<BR>
     */
    private WEB3FeqOrderAcceptExecNotifyRequest orderAcceptExecNotifyRequest;
    
    /**
     * (�񓯊��Ή��O������������t�o���ʒm�T�[�r�XImpl)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@roseuid 4214980A032E
     */
    WEB3AsynFeqOrderAcceptExecutionNotifyServiceImpl(WEB3FeqOrderAcceptExecNotifyRequest l_request)
    {
        this.orderAcceptExecNotifyRequest = l_request;
    }
    
    /**
     * �񓯊����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�O������������t�o���ʒm�T�[�r�X�j�񓯊������v�Q�ƁB<BR>
     * <BR>
     * @@roseuid 4214980A032E
     */
    public void run()
    {
        final String STR_METHOD_NAME = " run()";
        log.entering(STR_METHOD_NAME);

    	//�񓯊��Ή��O������������t�o���ʒmTransactionCallback�C���X�^���X�𐶐�����B
        WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback 
	        l_orderAcceptExecutionNotifyTransactionCallback = null;
        
        if (this.orderAcceptExecNotifyRequest != null)
        {    	
    	    l_orderAcceptExecutionNotifyTransactionCallback = 
    		    new WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback(
    		    	this.orderAcceptExecNotifyRequest.fromAccountId,
    		    	this.orderAcceptExecNotifyRequest.toAccountId,
    		       	this.orderAcceptExecNotifyRequest.threadNo);
        }
        
        try
		{
	        //getDefaultProcessor()
	        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
	        
	        //doTransaction(arg0 : int, arg1 : TransactionCallback)
			l_queryProcessor.doTransaction(
			    QueryProcessor.TX_CREATE_NEW,
			    l_orderAcceptExecutionNotifyTransactionCallback);
		}
        catch (DataNetworkException l_dataNetworkException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataNetworkException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataNetworkException.getMessage(),
                l_dataNetworkException);
        }
        catch (DataQueryException l_dataQueryException)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_dataQueryException);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_dataQueryException.getMessage(),
                l_dataQueryException);
        }
        
        try
        {
            //�X���b�h���J������
            WEB3GentradeDaemonTriggerManager l_triggerManager = 
            	new WEB3GentradeDaemonTriggerManager();
            l_triggerManager.releaseThread(
            	this.orderAcceptExecNotifyRequest.threadNo.longValue());
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (�񓯊��Ή��O������������t�o���ʒmTransactionCallback)<BR>
     * �g�����U�N�V�������������{��������N���X<BR>
     * <BR>
     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
     * 
     * @@roseuid 4214980A032E
     */
    public class WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback implements TransactionCallback
    {
        /**
         * (From����ID)<BR>
         * From����ID<BR>
         */
        private Long fromAccountId;
        
        /**
         * (To����ID)<BR>
         * To����ID<BR>
         */
        private Long toAccountId;
        
        /**
         * (Thread�ԍ�)<BR>
         * Thread�ԍ�<BR>
         */
        private Long threadNo;
        
        /**
         * (�񓯊��Ή��O������������t�o���ʒmTransactionCallback)<BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * �����Ŏw�肳�ꂽ�I�u�W�F�N�g���A�C���X�^���X�ϐ��ɃZ�b�g����B<BR>
         * <BR>
         * @@param l_lngFromAccountId - (From����ID)<BR>
         * From����ID<BR>
         * @@param l_lngToAccountId - (To����ID)<BR>
         * To����ID<BR>
         * @@param l_lngThreadNo - (Thread�ԍ�)<BR>
         * Thread�ԍ�<BR>
         * @@roseuid 4214980A032E
         */
        WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback(
            Long l_lngFromAccountId, 
            Long l_lngToAccountId, 
            Long l_lngThreadNo)
        {
            final String STR_METHOD_NAME = " WEB3AsynFeqOrderAcceptExecutionNotifyTransactionCallback" +
            		"(Long, Long, Long)";
            log.entering(STR_METHOD_NAME);
            
        	//From����ID
            this.fromAccountId = l_lngFromAccountId;
            
            //To����ID
            this.toAccountId = l_lngToAccountId;
            
            //Thread�ԍ�
            this.threadNo = l_lngThreadNo;
            
            log.exiting(STR_METHOD_NAME);
        }
        
        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �V�[�P���X�}<BR>
         * �u�i�񓯊��Ή��O������������t�o���ʒm�g�����U�N�V�����jprocess�v�Q�ƁB<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException
         * @@throws DataQueryException
         * @@throws DataCallbackException
         * @@roseuid 4214980A032E
         */
        public Object process() throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            if(lockThread(this.threadNo.longValue())==false)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //�O�������RCVD_Q�e�[�u���ǂݍ���
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" ( status = ? or status = ? ) ");
            l_sbWhere.append(" and ( account_id >= ? and account_id <= ? ) ");

            Object[] l_objWhere = new Object[4];
            l_objWhere[0] = SleRcvdqProcStatusEnum.TODO.intValue() + "";
            l_objWhere[1] = SleRcvdqProcStatusEnum.EXEC_PROCESSING.intValue() + "";
            l_objWhere[2] = this.fromAccountId + "";
            l_objWhere[3] = this.toAccountId + "";

            String l_strConditon = "replies_index asc";

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisSearchResult = l_queryProcessor.doFindAllQuery(
            	SleRcvdQRow.TYPE,
                l_sbWhere.toString(),
                l_strConditon,
                null,
                l_objWhere);
            
            // �擾�����L���[�e�[�u���̃��R�[�h�����ALoop
            FeqOrderUnit l_orderUnit = null;
            SleRcvdQParams l_params = null;
            
            int l_intNum = 0;
            if (l_lisSearchResult != null && !l_lisSearchResult.isEmpty())
            {
                l_intNum = l_lisSearchResult.size();
            }

            
            boolean l_blnDayExchange = false;
            String l_strRouteDiv = null;
            for (int i = 0; i < l_intNum; i++)
            {
            	SleRcvdQRow l_row = (SleRcvdQRow) l_lisSearchResult.get(i);
            	l_params = new SleRcvdQParams(l_row);
            	l_strRouteDiv = l_params.getRouteDiv();
            	
                try
                {
					FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
					WEB3FeqOrderManager l_orderManager =
					    (WEB3FeqOrderManager) l_finApp.getTradingModule(
					    ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
					
					//get�����P��ByOrderId
					l_orderUnit = l_orderManager.getOrderUnitByOrderId(
						new Long(l_params.getInternalRef()).longValue());
					
					// validate�����בփ��[�g
					WEB3FeqTypeOrderManagerReusableValidations l_orderMgrResVal =
						(WEB3FeqTypeOrderManagerReusableValidations)
							WEB3FeqTypeOrderManagerReusableValidations.getInstance();
					l_blnDayExchange = 
						l_orderMgrResVal.validateDayExchange((WEB3FeqOrderUnit)l_orderUnit);

					// �����בփ��[�g���o�^�ł��A��菈�����̖��L���[�̏ꍇ
					//  �O�������RCVD_Q.�����敪 == "��菈����"����
					//  validate�����בփ��[�g( ) == false����
					//  �i�O�������RCVD_Q.�o�H�敪 == "�o���ʒm" or "�o������" or "��茋�ʈꊇ����"�j�̏ꍇ
					if (!l_blnDayExchange && 
						SleRcvdqProcStatusEnum.EXEC_PROCESSING.equals(l_params.getStatus()) && 
						(WEB3FeqOrderExecRouteDivDef.EXEC_NOTIFY.equals(l_strRouteDiv) || 
						WEB3FeqOrderExecRouteDivDef.EXEC_INPUT.equals(l_strRouteDiv) || 
						WEB3FeqOrderExecRouteDivDef.EXECUTED_RESULT_UPLOAD.equals(l_strRouteDiv)))
					{
						continue;
					}
					
					//�O������������t�o���ʒm�ꌏTransactionCallback�C���X�^���X�𐶐�����B 
					WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback l_transactionCallback =
					    new WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallback(
					    	(WEB3FeqOrderUnit)l_orderUnit,
					        l_params);
					  
					// doTransaction()
					l_queryProcessor.doTransaction(
					    QueryProcessor.TX_CREATE_NEW,
					    l_transactionCallback);
                }
                //�V�[�P���X���ŗ�O���X���[���ꂽ�ꍇ
                catch (Exception l_ex)
                {
                    if (!(WEB3ErrorCatalog.BUSINESS_ERROR_01975.getErrorMessage().equals(l_ex.getMessage())))
                    {
                        log.error(l_ex.getMessage(), l_ex);
                        l_params.setStatus(SleRcvdqProcStatusEnum.SKIP_PROCESSING_ERROR);
                        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                        l_queryProcessor.doUpdateQuery(l_params);
                    }
                }
            }
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        /**
         * (���b�N�X���b�h)<BR>
         * Thread�����b�N����B<BR>
         * �f�[�����g���K�[�e�[�u�����ȉ��̏����ŁA����"for update nowait"<BR>
         * �œǂݍ��݁A���R�[�h�����b�N����B<BR>
         * [��������]<BR>
         * �@@�X���b�h�ԍ�������.Thread�ԍ�<BR>
         * <BR>
         * �Y�����郌�R�[�h�����݂��Ȃ��ꍇ�A��O�����������ꍇ��false��Ԃ��B<BR>
         * ����ȊO��true��Ԃ��B<BR>
         * <BR>
         * @@param l_lngThreadNo - (Thread�ԍ�)<BR>
         * Thread�ԍ�<BR>
         * @@return boolean
         * @@roseuid 4214980A032E
         */
        private boolean lockThread(long l_lngThreadNo)
        {
            final String STR_METHOD_NAME = "lockThread(long)";
            log.entering(STR_METHOD_NAME);
            boolean l_blnResult = false;
            try
            {
                String l_strWhere = " thread_no = ? ";
                String l_strCondition = " for update nowait ";

                Object l_bindVars[] ={new Long(l_lngThreadNo)};
                
                QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
                List l_lisRows = l_queryProcesser.doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere, 
                    l_strCondition, 
                    l_bindVars);
                if (l_lisRows != null && !l_lisRows.isEmpty())
                {
                    l_blnResult = true;
                }
                else
                {
                    l_blnResult = false;
                }
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                l_blnResult = false;
            }
            log.exiting(STR_METHOD_NAME);
            return l_blnResult;
        }
    }
}
@
