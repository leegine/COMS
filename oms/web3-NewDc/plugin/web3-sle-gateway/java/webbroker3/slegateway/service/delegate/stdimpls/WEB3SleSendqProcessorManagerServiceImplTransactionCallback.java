head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorManagerServiceImplTransactionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleSendqProcessorManagerServiceImplTransactionCallback�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/06/7 �� (FLJ) �V�K�쐬
 Revision History : 2006/06/8 �� (FLJ) �\�[�X����
**/

package webbroker3.slegateway.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import webbroker3.util.WEB3LogUtility;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorService;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.WEB3SleConnectorClientFactoryImpl;
import webbroker3.slegateway.WEB3SleMarketAdapterUtils;
import webbroker3.slegateway.WEB3SleProcessors;
import webbroker3.slegateway.WEB3SleProcessorsImpl;
import webbroker3.slegateway.define.WEB3SleMarketAdapterErrorMessageDef;

/**
 * SleSendqProcessorManagerServiceImpl�ɂ�����g�����U�N�V�����R�[���o�b�N
 */
public class WEB3SleSendqProcessorManagerServiceImplTransactionCallback implements TransactionCallback
{

	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static final WEB3LogUtility m_log =WEB3LogUtility.getInstance(WEB3SleSendqProcessorManagerServiceImplTransactionCallback.class);//���C�� 2006/10/11

	/**
	 * SLE�R�l�N�^�N���C�A���g�v�[��(UT�p)
	 */
	private static WEB3SleConnectorClientFactory m_connectorfactory = new WEB3SleConnectorClientFactoryImpl();

	/**
	 * DB�v���Z�b�T(UT�p)
	 */
	private static WEB3SleProcessors m_wsp = new WEB3SleProcessorsImpl();

	/**
	 * Thread No <BR>
	 */
	private Long m_threadNo;

	/**
	 * (From����ID)
	 */
	private long m_fromAccountId;

	/**
	 * (To����ID)
	 */
	private long m_toAccountId;
	
	/**
	 * (�s��R�[�h)
	 */
	private String[] m_marketCode;//��2007/10/23 �[�Z���s��Ή�

	/**
	 * From����ID Setter
	 * @@param fromAccountId
	 */
	public void setFromAccountId(long fromAccountId)
	{
		m_fromAccountId = fromAccountId;
	}

	/**
	 * (To����ID) Setter
	 * @@param toAccountId
	 */
	public void setToAccountId(long toAccountId)
	{
		m_toAccountId = toAccountId;
	}

	/**
	 * (ThreadNo) Setter
	 * @@param threadNo
	 */
	public void setThreadNo(Long threadNo)
	{
		m_threadNo = threadNo;
	}
	
	/**
	 * @@param code m_marketCode ��ݒ�B
	 */
	public void setMarketCode(String[] marketCode) {
		m_marketCode = marketCode;
	}
	//��2007/10/23 �[�Z���s��Ή�

	/**
	 * �g�����U�N�V������SLE�R�l�N�^�t�@@�N�g����ݒ�(UT�p)
	 * @@param connectorfactory�@@SLE�R�l�N�^�t�@@�N�g���C���X�^���X
	 */
	public void setConnectorFactory(WEB3SleConnectorClientFactory connectorfactory)
	{
		m_connectorfactory = connectorfactory;
	}

	/**
	 * �g�����U�N�V������DB�v���Z�b�T��ݒ�(UT�p)
	 * @@param wsp DB�v���Z�b�T�C���X�^���X
	 */
	public void setProcessors(WEB3SleProcessors wsp)
	{
		m_wsp = wsp;
	}
	
	/**
	 * (process) <BR>
	 * <BR>
	 * �g�����U�N�V�������������s����B <BR>
	 * @@return Object
	 * @@throws DataNetworkException,
	 *           DataQueryException, DataCallbackException
	 */
	public Object process()throws DataNetworkException, DataQueryException, DataCallbackException
	{
		m_log.entering("process()");

		WEB3SleSendqProcessorService l_processor =
				(WEB3SleSendqProcessorService)Services.getService(WEB3SleSendqProcessorService.class);
				
		// �s�ꖈCircuitBraker�t���O�̏�Ԃ��`�F�b�N����悤�ɉ��C���폜
		// Circuit Braker ��Ԓ��Ȃ�A���M�v���Z�X���N�����Ȃ�
			
			//�����X���b�h��L
			if (lockThread(this.m_threadNo.longValue()) == false) {
				final String msgErr = WEB3SleMarketAdapterErrorMessageDef.SEND_THREAD_LOCKED_STATUS;
				m_log.error(msgErr);
			
				m_log.exiting("process()");
				return null;
			}
			String mktCode = null; //���M��s��R�[�h add 2006/10/23
			
			try {
				final List l_lisRows = getRows();
				
				for (int i = 0; i < l_lisRows.size(); i++) {
					final Row r = (Row) l_lisRows.get(i);

					mktCode =  ((SleSendQRow) r).getMarketCode(); 

					// Circuit Breaker���ꂽ��ԂȂ瑗�M�����𒆎~����
					if (WEB3SleMarketAdapterUtils.isStopRequested(mktCode)) {
						//�s�ꖈCircuitBraker�t���O�̏�Ԃ��`�F�b�N����悤�ɉ��C
						continue;
					}
					
					m_log.debug (r.toString());

					l_processor.processRow(r,this.m_wsp,this.m_connectorfactory);

				}
			} catch (Exception ex) {
								
				/*�s��֑�ʖ��������𔭑�����̂�h�~���邽��
				  ���M�������~����*/
				
				m_log.error(ex.getMessage(), ex);
				m_log.exiting("process() ");
				throw new DataCallbackException("Could not successful run a processRow transaction ");
			}
//			finally{
//				//�s��SLE�R�l�N�^�N���X�^���N���A����
//				WEB3SleConnectorManager.invalidateGlSleConnectorClientConnection(mktCode);
//			}
//			�����s�v 2007/1/8
//		}
		m_log.exiting("process() ");
		
		return null;
	}

	/**
	 * ���M�Ώۂ�SEND_Q���b�Z�[�W�𒊏o
	 * @@return List ���M�Ώۂ�SEND_Q���b�Z�[�W���X�g
	 */
	private List getRows() throws DataException
	{

		m_log.entering("getRows() ");
		// ���M�Ώۂ𒊏o
		String l_strWhere =
			"(status = ? or status = ?) and  account_id >= ? and account_id <= ? and biz_date = ? ";//'�����M'�����敪�̏�����ǉ��@@�� 2006/10/20
		//final String l_strOrderBy = "last_updated_timestamp";
		final String l_strOrderBy = "created_timestamp";//sendq���b�Z�[�W�������Ԃɂ���ă\�[�g����΁A���X�Ɩ���ɒ������������鎞�Ԃƈ�v����@@2006/10/31
		Object[] l_ObindVars =
		{
			SleSendqProcStatusEnum.TODO,
			SleSendqProcStatusEnum.NOT_PROCESSED,//'�����M'�����敪�̏�����ǉ� �� 2006/10/20
			Long.valueOf(String.valueOf(m_fromAccountId)),
			Long.valueOf(String.valueOf(m_toAccountId)),
			//�������̌���������ǉ� 2009/8/7
			WEB3SleMarketAdapterUtils.getBizDate(),
		};
		
		//��2007/10/23 �[�Z���s��Ή�
		//�����s�ꂪ����ꍇ�ASQL��where�q����e��ǉ�
		if(m_marketCode!=null)
		{
			StringBuffer l_sb = new StringBuffer(" and (");
			ArrayList l_lst = new ArrayList();
			for (int i = 0; i < m_marketCode.length; i++) 
			{
				Object object = m_marketCode[i];
				if(object!=null)
				{
					if(l_lst.size() > 0)
					{
						l_sb.append(" or");
					}
					l_sb.append(" market_code = ?");
					l_lst.add(object);
				}
			}
			l_sb.append(")");
			
			if(l_lst.size() > 0)
			{
				l_strWhere = l_sb.insert(0,l_strWhere).toString();
				
				Object[] l_ObindVarsAdd = l_lst.toArray(new Object[0]);
				Object[] l_ObindVarsTemp = new Object[l_ObindVars.length + l_ObindVarsAdd.length];
				System.arraycopy(l_ObindVars,0,l_ObindVarsTemp,0,l_ObindVars.length);
				System.arraycopy(l_ObindVarsAdd,0,l_ObindVarsTemp,l_ObindVars.length,l_ObindVarsAdd.length);
				l_ObindVars = l_ObindVarsTemp;
			}
		}
		//��2007/10/23 �[�Z���s��Ή�
					
		final int l_pSize = getMaxServiceSizeOneTime();
		final QueryProcessor l_qp = Processors.getDefaultProcessor();
		final List l_lisRows =
			l_qp.doFindAllQuery(SleSendQRow.TYPE, l_strWhere, l_strOrderBy, null, l_ObindVars,getMaxServiceSizeOneTime(),0);
		m_log.debug("total pages:"+ ((ListPage)l_lisRows).totalPages());
		m_log.debug("total size :"+ ((ListPage)l_lisRows).totalSize());

		/*�ő吔�ȏ�̖����M�������b�Z�[�W�����݂���ꍇ
		  �ő吔�܂ő��M���邱��*/
		if (l_lisRows.size() > getMaxServiceSizeOneTime()) {
			m_log.debug ("no running here");
			List l_lisRowsofmaxsize = new ArrayList(getMaxServiceSizeOneTime());
			for (int i = 0; i < getMaxServiceSizeOneTime(); i++) {
				final SleSendQRow row = (SleSendQRow) l_lisRows.get(i);
				l_lisRowsofmaxsize.add(row);
			}
			
			m_log.debug("getRows() = " + l_lisRowsofmaxsize.size());
			
			m_log.exiting("getRows() ");
			return l_lisRowsofmaxsize;
		}
		
		m_log.debug("getRows() = " + l_lisRows.size());

		m_log.exiting("getRows() ");
		return l_lisRows;
	}

	/**
	 * �X���b�h���b�N���擾���� <BR>
	 * <BR>
	 * Thread�ԍ� <BR>
	 * @@param l_lngThreadNo
	 * @@return boolean <BR>
	 */
	private boolean lockThread(final long l_lngThreadNo)
	{

		m_log.entering("lockThread(final long)");

		boolean l_blnResult1 = false;
		try {
			String l_strWhere = "thread_no = ? ";
			String l_strCondition = "for update nowait";
			Object l_bindVars[] = { new Long(l_lngThreadNo)};
			final QueryProcessor l_queryProcesser =
				Processors.getDefaultProcessor();
			List l_lisRows =
				l_queryProcesser.doFindAllQuery(
					DaemonTriggerRow.TYPE,
					l_strWhere,
					l_strCondition,
					l_bindVars);
			if (l_lisRows.size() > 0) {
				l_blnResult1 = true;
			} else {
				l_blnResult1 = false;
			}
		} catch (Exception e) {
			m_log.error(e.getMessage(), e);
			l_blnResult1 = false;
		}

		m_log.exiting("lockThread(final long)");
		return l_blnResult1;
	}

	/**
	 * 1��ōő呗�M���b�Z�[�W��
	 * @@return int �ő呗�M���b�Z�[�W��
	 */
	private int getMaxServiceSizeOneTime()
	{
		m_log.entering("getMaxServiceSizeOneTime()");

		final String l_strName = "sendq.max.size";
		final int defaultValue = 100;

		final String l_strValue = GtlUtils.getTradingSystem().getPreference(l_strName);
		if (l_strValue == null)
		{
			final String msg =
				"sle connector max send_q size not defined in the SYSTEM_PREFERENCES with  name:"
					+ l_strName;
			m_log.warn(msg);
			m_log.exiting("getMaxServiceSizeOneTime()");	
			return defaultValue;
		}
		
		m_log.exiting("getMaxServiceSizeOneTime()");
		
		return Integer.valueOf(l_strValue).intValue();
	}

}
@
