head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3AsynSleSendqProcessorManagerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AsynSleSendqProcessorManagerServiceImpl�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/10/26 ��(FLJ) �V�K�쐬 Asyn�񓯊����X���b�h��send_q���M����������
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.slegateway.message.WEB3ProcessSleSendqRequest;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.affinity.*;

/**
 * �i�񓯊��Ή�send_q���b�Z�[�W���M�������C���T�[�r�X�����N���X�j�B
 * @@author  : ���iFTL�j
 * send_q���M��񓯊����ɂ��邽�ߒǉ� 2006/10/26
 */
public class WEB3AsynSleSendqProcessorManagerServiceImpl implements Runnable
{
	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3AsynSleSendqProcessorManagerServiceImpl.class);//���C�� 2006/10/11

	/**
	 * �f�o�b�O���O�o�̓t���O
	 */
	private static final boolean DBG = m_log.ison();
	
	/**
	 * send_q���M�ʒm���C�����N�G�X�g
	 */
	private WEB3ProcessSleSendqRequest request;
	
	/**
	 * �f�t�H���g�R���X�g���N�^�B<BR>
	 */
	public WEB3AsynSleSendqProcessorManagerServiceImpl(WEB3ProcessSleSendqRequest
		l_request)
	{
		this.request = l_request;
	}

	/**
	 * ���M�v���Z�X���N������
	 */
	public void run()
	{
		final String STR_METHOD_NAME = "WEB3AsynSleSendqProcessorManagerServiceImpl�Frun()";
		m_log.entering(STR_METHOD_NAME);
		
		// RAC-CTX�Ή��̂��� �ǉ� 2006/11/7
		WEB3DescendRacCtxService l_sv = (WEB3DescendRacCtxService) Services.getService(
			WEB3DescendRacCtxService.class);
		l_sv.setAccountIdCtx(this.request.fromAccountId);
		
		try {
			WEB3ProcessSleSendqRequest l_request = (WEB3ProcessSleSendqRequest)request;
			
			QueryProcessor l_queryProcesser = null;
			// �f�t�H���g�v���Z�b�T�擾
			try
			{
				l_queryProcesser = Processors.getDefaultProcessor();
			}
			catch (DataFindException l_dfe)
			{
				m_log.error(l_dfe.getMessage(), l_dfe);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_dfe.getMessage(), l_dfe);
			}
			catch (DataNetworkException l_dne)
			{
				m_log.error(l_dne.getMessage(), l_dne);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_dne.getMessage(), l_dne);
			}
						
			// send_q���M�ʒm���C��TransactionCallback�𐶐�
			WEB3SleSendqProcessorManagerServiceImplTransactionCallback callback_service =
				new WEB3SleSendqProcessorManagerServiceImplTransactionCallback();
			
			// set�R�[���o�b�N�p�����[�^�ꗗ()
			callback_service.setThreadNo(l_request.threadNo);
			callback_service.setFromAccountId(l_request.fromAccountId);
			callback_service.setToAccountId(l_request.toAccountId);
			callback_service.setMarketCode(l_request.marketCode);//2007/10/23 �[�Z���s��Ή�
			
			// �g�����U�N�V���������s
			try{
			
				l_queryProcesser.doTransaction(
					QueryProcessor.TX_CREATE_NEW,
					callback_service);
			}
            catch (DataFindException l_dfe)
			{
				m_log.error(l_dfe.getMessage(), l_dfe);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_dfe.getMessage(), l_dfe);
			}
			catch (DataNetworkException l_dne)
			{
				m_log.error(l_dne.getMessage(), l_dne);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_dne.getMessage(), l_dne);
			}
			catch (DataCallbackException l_dce)
			{
				m_log.error(l_dce.getMessage(), l_dce);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_dce.getMessage(), l_dce);
			}
			catch (DataQueryException l_dqe)
			{
				m_log.error(l_dqe.getMessage(), l_dqe);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_dqe.getMessage(), l_dqe);
			}
		}         
		catch (Exception e)
		{

			m_log.error(e.getMessage(), e);

		}
		catch (Throwable te)
		{

			m_log.error(te.getMessage(), te);

		}


		//�X���b�h�J��
		try
		{
			new WEB3GentradeDaemonTriggerManager().releaseThread(request.threadNo.
				longValue());
		}
		catch (WEB3SystemLayerException ex)
		{
			m_log.error(ex.getMessage(), ex);
		}
		
		// RAC-CTX�Ή��̂��� �ǉ� 2006/11/7
		l_sv.clearAccountIdCtx();
		m_log.exiting(STR_METHOD_NAME);
	}
}@
