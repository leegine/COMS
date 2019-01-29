head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleRequestSender.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleRequestSender�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 ��(FLJ) �V�K�쐬
 Revision History : 2006/5/19 ��(FLJ)�@@ORDER_BOOK�֑��M�����ۂ��m�F������Ή�
 Revision History : 2006/6/8 ��(FLJ)�@@�\�[�X����
*/
package webbroker3.slegateway.service.delegate.stdimpls;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xbconnector.glbase.gldata.GlSleRequest;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.data.SleSendQErrorsRow;
import webbroker3.slebase.data.SleSendQErrorsPK;
import webbroker3.slebase.enums.SleOpenStatusEnum;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.WEB3SleMarketAdapterUtils;
import webbroker3.slegateway.WEB3SleOrderBookStatusChecker;
import webbroker3.slegateway.WEB3SleOrderBookStatusCheckerInterface;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.WEB3SleProcessorsImpl;

/**
 * �w�肵��SEND_Q���b�Z�[�W�R���e���c��SLE�R�l�N�^�֑��M����
 */
public class WEB3SleRequestSender
{
	
	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleRequestSender.class);
	
	/**
	 * ���O�փf�o�b�O�o�̓t���O
	 */
	private static final boolean DBG = m_log.ison();
	
	/**
	 * DB�v���Z�b�T
	 */
	private static WEB3SleProcessors m_wsp = new WEB3SleProcessorsImpl();
	
	/**
	 * Order_Book�̖₢���킹�c�[��
	 */
	private static WEB3SleOrderBookStatusCheckerInterface m_ordchk = WEB3SleOrderBookStatusChecker.getInstance();

	/**
	 * �R���X�g���N�^(�O������C���X�^���X�����֎~)
	 */
	private WEB3SleRequestSender()
	{
		;
	}
	
	/**
	 * DB�v���Z�b�T��ݒ肷��
	 * @@param wsp DB�v���Z�b�T
	 */
	public static void setWeb3SleProcessor(WEB3SleProcessors wsp)
	{
		m_wsp = wsp;
	}
	
	/**
	 * Order_Book�̖₢���킹�c�[��
	 * @@param ordchk Order_Book�̖₢���킹�c�[��
	 */
	public static void setOrderBookChecker(WEB3SleOrderBookStatusCheckerInterface ordchk)
	{
		m_ordchk = ordchk;
	}

	/**
	 * SEND_Q���b�Z�[�W�R���e���c��GlSleRequest�I�u�W�F�N�g�֕ϊ����A �R�l�N�^�֑��M����
	 * 
	 * @@param sleSendqRow SEND_Q���b�Z�[�W
	 * @@throws GlSleConnectorClientException SLE�R�l�N�^�֑��M�����M�G���[�����������ꍇ�X���[�����
	 */
	public static void send(SleSendQRow l_sleSendqRow, WEB3SleConnectorClientFactory l_connectorfactory) 
		throws GlSleConnectorClientException ,RuntimeException
	{
		
	    m_log.entering("send(SleSendQRow)"); 
		final String l_strMarketCode = l_sleSendqRow.getMarketCode();
		final GlSleRequest l_glSleReq = toGlSleRequest(l_sleSendqRow);
		
		send(l_sleSendqRow, l_connectorfactory, l_strMarketCode, l_glSleReq);
	  
		m_log.exiting("send(SleSendQRow)"); 
	}

	/**
	 * SEND_Q���b�Z�[�W��GlSleRequest�I�u�W�F�N�g�֕ϊ�����
	 * 
	 * @@param sleSendqRow SEND_Q���b�Z�[�W
	 * @@return GlSleRequest�@@GlSleRequest�I�u�W�F�N�g�@@
	 */
	private static GlSleRequest toGlSleRequest(SleSendQRow l_sleSendqRow) throws RuntimeException
	{

//		final WEB3SleRequestPreparer l_preparer =  new WEB3SehkSleRequestPreparer( new WEB3SleProcessorsImpl());
//		final WEB3SleRequestPreparer l_preparer =  WEB3SehkSleRequestPreparer.getInstance();
		//��prepare�N���X��singlton���ɂ��邽�߁A�C���X�^���X���@@�����C 2006/12/4
		
		//���V���Z���s��Ή�2007/10/23
		final WEB3SleRequestPreparer l_preparer =  WEB3SleMarketAdapterUtils.getSleRequestPreparer(l_sleSendqRow.getMarketCode());

		if(l_preparer==null)
		{
			final String errMsg =
				"There is no WEB3SleRequestPreparer available for the marketCode: " + l_sleSendqRow.getMarketCode();
			throw new RuntimeException(errMsg);
		}
		//���V���Z���s��Ή�2007/10/23
		
		return l_preparer.prepare(l_sleSendqRow);
	}

	/**
	 * SLE���N�G�X�g��Ή�����s��̃R�l�N�^�֑��M����
	 * 
	 * @@param sleSendqRow
	 *            SEND_Q���b�Z�[�W
	 * @@param connectorfactory 
	 * 			  �N���C�A���g�񋟂���v�[��
	 * @@param marketCode
	 *           �s��̎s��R�[�h.
	 * @@param req
	 *            SLE���M�d����ێ�����GlSleRequest
	 * @@throws GlSleConnectorClientException
	 *             SLE�R�l�N�^�N���C�A���g��O���X���[����
	 */
	private static void send(SleSendQRow l_sleSendqRow, WEB3SleConnectorClientFactory l_connectorfactory, String l_strMarketCode, GlSleRequest l_glSlereq)
			throws GlSleConnectorClientException,RuntimeException //RuntimeException��O��ǉ� (2006/10/11)
	{		
		m_log.entering("send(SleSendQRow, String, GlSleRequest)"); 
		
		boolean l_bSendToSle = true;
		boolean l_bCloseSleSendqError = false;
		
		if (errorsInSleSendqErrors(l_sleSendqRow.getQueueId())) 
		{
			m_log.info("Will try auto-recovery. Errors found in sle_send_q_errors table for sle_send_q row:" + l_sleSendqRow);
			l_bCloseSleSendqError = true;
			
			/* SLE�֐��������M�����ۂ��m�F
			   ���ɐ��������M�����ꍇ�A�d�����M���Ȃ�*/
			try{
				if(m_ordchk.isAlreadySent(l_sleSendqRow)) 
				{
					m_log.info("Already sent to SLE. sle_send_q row:" + l_sleSendqRow);
					l_bSendToSle = false;
				}
			}catch(RuntimeException re){
//				String errMsg = WEB3SleMarketAdapterErrorMessageDef.SLE_ORDER_BOOK_REQUEST_FAIL; 
//				m_log.error(errMsg); 
				//��WEB3SleOrderBookStatusChecker�N���X�ɂ��ł�order book �₢���킹�G���[���o�͂���邽�߁A�폜�B
				//return;
				m_log.debug("there is a order book queue error happend!");
//				closeSleSendqErrorsRow(l_sleSendqRow.getQueueId());//ORDERBOOK�₢���킹���s����send_q_errors�L���[���N���[�Y����(2006/10/12)�˃R�����g�A�E�g 2007/1/8
				throw re;//���@@��������runtimeexception�𑗐M�T�[�r�X�N���X�փX���[����(2006/10/12)
			}
		}
		
		
		if(l_bSendToSle)
		{	
			// SLE�R�l�N�^�֑��M����
			try{
				WEB3SleConnectorManager.send(l_connectorfactory, l_strMarketCode, l_glSlereq);
			}catch(GlSleConnectorClientException gle){//���M���s��ŃN���[�Y������ǉ�(2006/9/25 FLJ��)
				m_log.debug("there is a GlSleConnectorClientException happend at sending GL request !");
				m_log.error("GlSleConnectorClientException error:",gle);
				if (l_bCloseSleSendqError){ 
					if(DBG) m_log.debug(("closing sle_send_q_errors with queue_id when send failed:" + l_sleSendqRow.getQueueId()));
					closeSleSendqErrorsRow(l_sleSendqRow.getQueueId());
				}
				throw gle;
			}
			catch (RuntimeException re){
				m_log.debug("there is a unknown exception happend at sending GL request !");
				throw re;
			}
//			finally{
//				//�s��SLE�R�l�N�^�N���X�^���N���A����
//				WEB3SleConnectorManager.invalidateGlSleConnectorClientConnection(l_sleSendqRow.getMarketCode());
//			}//��send_q���b�Z�[�W����SLE�R�l�N�^���N���A�͔̂�����ŁA�N���A������callback�����ֈڂ�
			if (DBG) m_log.debug("Sent to GL SleConnector OK");
		}
	
		// ORDER_BOOK�ւ̖₢���킹�Ŋm�F�ς݂�sle_send_q_errors�̃��b�Z�[�W�ɑ΂��X�e�[�^�X���N���[�Y����
		if(l_bCloseSleSendqError)
		{
			if(DBG) m_log.debug(("closing sle_send_q_errors with queue_id:" + l_sleSendqRow.getQueueId()));
			closeSleSendqErrorsRow(l_sleSendqRow.getQueueId());
		}
		
		
		
		m_log.exiting("send(SleSendQRow, String, GlSleRequest)");	
	}
	
	/**
	 * �w�肵���L���[ID��sle_send_q_errors��'OPEN'���̃��b�Z�[�W �ɑ��݂������m�F
	 * @@param queue_id ORDER_BOOK�֖₢���킹�Ώۂ̃L���[ID
	 */
	private static boolean errorsInSleSendqErrors(long l_lngQueueid) {
		
		final String STR_METHOD_NAME = "errorInSleSendqErrors(long)";
		m_log.entering(STR_METHOD_NAME);

		try{
			final QueryProcessor l_qp = m_wsp.getDefaultProcessor();

			final String l_strWhere = "open_status=?";
			final Object[] l_Obvs = { SleOpenStatusEnum.OPEN, };
			final String l_strOrderBy = "queue_id";
			final List l_rows = l_qp.doFindAllQuery(SleSendQErrorsRow.TYPE, l_strWhere,l_strOrderBy, null, l_Obvs);
			for(int i = 0;i < l_rows.size(); i++)
			{
				
				final SleSendQErrorsRow r = (SleSendQErrorsRow )l_rows.get(i);
				if(r.getQueueId() >  l_lngQueueid)
				{
					return false;
				}
				else if(r.getQueueId() ==  l_lngQueueid)
				{
					return true;
				}
			}
			m_log.exiting(STR_METHOD_NAME);
			return false;
		}catch (DataException de){
			final String msg = "Exception while fetching sle_send_q_errors table with queue_id:" +  l_lngQueueid;
			m_log.error(msg, de);
			m_log.exiting(STR_METHOD_NAME);
			throw new RuntimeException(msg, de);
		}
	}
	
	/**
	 * ORDER_BOOK�֖₢���킹�Ŋm�F�ς݂�sle_send_q_errors�̃��b�Z�[�W�̃X�e�[�^�X���N���[�Y����
	 * @@param l_lngQueueid ORDER_BOOK�֖₢���킹�Ŋm�F�ς݂�sle_send_q_errors�̃��b�Z�[�W�̃L���[ID
	 */
	private static void closeSleSendqErrorsRow(long l_lngQueueid)
	{
		
		try {
			final QueryProcessor l_qp = m_wsp.getDefaultProcessor();
			
			final Map l_hmChanges = new HashMap();
			l_hmChanges.put("open_status", SleOpenStatusEnum.CLOSE);
			l_hmChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());	
			// sle_send_q_errors�̃��b�Z�[�W�X�e�[�^�X���N���[�Y����
			l_qp.doUpdateQuery(new SleSendQErrorsPK(l_lngQueueid), l_hmChanges);
		}catch (DataException de){
			final String msg = "Exception while closing sle_send_q_errors table with queue_id:" + l_lngQueueid;
			m_log.error(msg, de);
			throw new RuntimeException(msg, de);
		}
	}
}

@
