head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleOrderBookStatusChecker.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleOrderBookStatusChecker�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/19 ���iFLJ) �V�K�쐬
 Revision History : 2006/06/08 ���iFLJ) �\�[�X����
*/
package webbroker3.slegateway;
import java.math.BigDecimal;
import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.Timestamp;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xbconnector.glbase.admin.AdminAdaptor;
import com.fitechlabs.xbconnector.glbase.admin.objs.AdminServerDescriptor;
import com.fitechlabs.xbconnector.glbase.gldata.GlData;
import webbroker3.util.WEB3LogUtility;
import webbroker3.slebase.data.SleSendQPK;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.data.SleRcvdQRow;
import webbroker3.slebase.enums.SleSendqOpTypeEnum;
import webbroker3.slegateway.define.WEB3SleCallbackGLRejectTypeDef;
import webbroker3.slegateway.define.WEB3SleMarketAdapterErrorMessageDef;
import webbroker3.slegateway.service.delegate.stdimpls.WEB3SleConnectorManager;


/**
 * SLE��ORDER_BOOK�₢���킹���[�e�B���e�B�N���X
 * @@author  : ���iFLJ�j
 * @@version : 1.0
 */
public class WEB3SleOrderBookStatusChecker implements WEB3SleOrderBookStatusCheckerInterface
{

	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static WEB3LogUtility m_log =WEB3LogUtility.getInstance(WEB3SleOrderBookStatusChecker.class);
	
	/**
	 * �f�o�b�O���O�o�̓t���O
	 */
	private static final boolean DBG = m_log.ison();

	/**
	 * SLE�Ǘ��|�[�g�̖K��𒼗񉻂��邽�߂�����ꂽORCLE DB���b�N��
	 */
	public static final String DBMS_LOCK_NAME_FOR_SERIALIZING_ADMIN_OPS = "web3.sle.connector.admin.ops";
	
	/**
	 * Order_Book �₢���킹�c�[����singlton�C���X�^���X
	 */
	private static WEB3SleOrderBookStatusChecker m_checker = null;

	/**
	 * �R���X�g���N�^
	 */
	private WEB3SleOrderBookStatusChecker() {
		;
	}
	
	/**
	 * Singleton�C���X�^���X�擾����
	 */
	public static WEB3SleOrderBookStatusChecker getInstance()
	{
		if (m_checker == null){
			m_checker = new WEB3SleOrderBookStatusChecker();
		}
		return m_checker;
	}

	/**
	 * SEND_Q�������b�Z�[�W������SLE�ɐ��������M����邩�`�F�b�N
	 * @@param l_sleSendqRow SEND_Q�������b�Z�[�W
	 * @@return ���M�ς�:true��Ԃ��@@��:false��Ԃ��B
	 */
	public boolean isAlreadySent(SleSendQRow l_sleSendqRow)
		throws RuntimeException
	{

		final String STR_METHOD_NAME = "isAlreadySent(SleSendQRow)";
		m_log.entering(STR_METHOD_NAME);

		//ORDER_BOOK�擾
		final Map orderbookEntriesTable =
			getOrderBook(
				l_sleSendqRow.getMarketCode(),
				l_sleSendqRow.getProductCode());

		m_log.exiting(STR_METHOD_NAME);
		return isAlreadySent(l_sleSendqRow, orderbookEntriesTable);
	}

	/**
	 * SEND_Q�������b�Z�[�W������SLE�ɐ��������M����邩�`�F�b�N
	 * @@param l_sleSendqRow SEND_Q�������b�Z�[�W
	 * @@param l_mOrderBook �擾��������ORDER_BOOK
	 * @@return ���M�ς�:true��Ԃ��@@��:false��Ԃ��B
	 */
	public boolean isAlreadySent(SleSendQRow l_sleSendqRow, Map l_mOrderBook)
		throws RuntimeException
	{
		
	

		final String STR_METHOD_NAME = "isAlreadySent(SleSendQRow,Map)";
		m_log.entering(STR_METHOD_NAME);

		final SleSendqOpTypeEnum opType = l_sleSendqRow.getOpType();
		final boolean isNewOrder = SleSendqOpTypeEnum.NEW_ORDER.equals(opType);

		final String orderIdStr = "" + l_sleSendqRow.getOrderId();

		m_log.info(
			"Checking whether sle_send_row is already sent or not. Row:"
				+ l_sleSendqRow);

		final GlData l_gldata = (GlData) l_mOrderBook.get(orderIdStr);

		Boolean l_bRetResult = null;
	
		if (isNewOrder)
		{

			// ���M�ς�
			l_bRetResult = Boolean.valueOf(l_gldata != null && isAvailableOrderBookResp( l_gldata) );//������ǉ� 2006/2/9
		}
		else if (l_gldata != null)
		{

			final String memo = l_gldata.getString("memo");
//			final int sendqMsgNoInTheOrderBook = Integer.parseInt(memo);
//			l_bRetResult =
//				Boolean.valueOf(
//					sendqMsgNoInTheOrderBook >= l_sleSendqRow.getQueueId());
			//��r���queue_id�ł͂Ȃ�created_timestamp�Ƃ���悤�ɉ��C 2006/10/31
			final Timestamp createTimestampOfOrderMessageInTheOrderBook = getCreatedTimeByMemo(l_gldata.getString("memo")); 
//			l_bRetResult = 
//			    Boolean.valueOf(
//			    	(createTimestampOfOrderMessageInTheOrderBook.after(l_sleSendqRow.getCreatedTimestamp())
//					    || createTimestampOfOrderMessageInTheOrderBook.equals(l_sleSendqRow.getCreatedTimestamp())) && (isAvailableOrderBookResp(l_gldata))) ;//������ǉ� 2006/2/9
			final int l_sendqQueueId = Integer.parseInt(memo);
			l_bRetResult =
				Boolean.valueOf( 
					( 
						(l_sendqQueueId == l_sleSendqRow.getQueueId()) 
							&& ( isAvailableOrderBookResp(l_gldata))
					)    //order book�ɂ���last �������m�F�����Ώۂł���ꍇ
					|| 
					(createTimestampOfOrderMessageInTheOrderBook.after(l_sleSendqRow.getCreatedTimestamp()))   //order book�ɂ���last �������m�F�����Ώۂ��Ⴂ�̏ꍇ
				);
		}
		final boolean l_bFinalReturnValue = Boolean.TRUE.equals(l_bRetResult);
		m_log.info(
			"sle_send_q  already sent:"
				+ l_bFinalReturnValue
				+ " - for row:"
				+ l_sleSendqRow);

		m_log.exiting(STR_METHOD_NAME);
		return l_bFinalReturnValue;
	}
	
	/**
	 * gl_data.memo�d�����ڂɑΉ�����send_q�������b�Z�[�W��created_timestamp��Ԃ�
	 * @@return 'memo'�Ή�����send_q�������b�Z�[�W��created_timestamp��Ԃ�
	 * ���ǉ� 2006/10/31
	 */
	private Timestamp getCreatedTimeByMemo(String l_strMemo)
	throws RuntimeException
	{
		m_log.entering("getCreatedTimeByMemo()");

		SleSendQRow l_row = null;
		
		try{	
			final QueryProcessor l_qp = Processors.getDefaultProcessor();
			
			l_row = (SleSendQRow)l_qp.doFindByPrimaryKeyQuery( new SleSendQPK(Long.parseLong(l_strMemo))); 
		
		}
		catch (DataException de) {

			throw new RuntimeException("Exception while reading sle_send_q. queue_id:" + l_strMemo);
		} 

		m_log.exiting("getCreatedTimeByMemo()");
		return l_row.getCreatedTimestamp();
	}	
	

	/**
	 * �Ǘ��|�[�g�Ɛڑ��̃\�P�b�g�̃^�C���A�E�g����.
	 * 
	 * @@return  �Ǘ��|�[�g�Ɛڑ��̃\�P�b�g�̃^�C���A�E�g���� .
	 */
	private int getSocketReadTimeoutSecs()
	{
		m_log.entering("getSocketReadTimeoutSecs()");

		final int defaultValue = 5 * 60;
		final String name = "sle2004.orderbook.read.timeout.secs";

		final String l_strValue = GtlUtils.getTradingSystem().getPreference(name);
		if (l_strValue == null)
		{
			final String msg =
				"sle connector timeout time not found in the SYSTEM_PREFERENCES with  name:"
					+ name;
			m_log.warn(msg);
			m_log.exiting("getSocketReadTimeoutSecs()");
			return defaultValue;
		}
		
		m_log.exiting("getSocketReadTimeoutSecs()");
		return Integer.valueOf(l_strValue).intValue();
	}

	/**
	 * �s��R�[�h�A�����R�[�h�ɑΉ�����ORDER BOOK�₢���킹���ʎ擾
	 * @@param marketCode �s��R�[�h
	 * @@param productCode�@@�����R�[�h
	 * @@return�@@ORDER BOOK �̖₢���킹���ʂ�InternalRef (����ID)�̃}�b�s���O�Ή��֌W��ێ�����Map
	 */
	public Map getOrderBook(String l_strMarketCode, String l_strProductCode)
		throws RuntimeException
	{

		final String STR_METHOD_NAME = "getOrderBook(String,String)";
		m_log.entering(STR_METHOD_NAME);

		boolean l_bLockAcquried = false;
		final long startTime = System.currentTimeMillis();
		m_log.info(
			":::: start of getOrderBook for marketCode,productCode:"
				+ l_strMarketCode
				+ ","
				+ l_strProductCode);
		try {
			// �e�N���C�A���g�v���Z�X��SLE�R�l�N�^�|�[�g�𒼗�ŃA�N�Z�X�����邽��
			// �������b�N�擾����B
			acquireLock(l_strMarketCode);
			l_bLockAcquried = true;

			// SLE�R�l�N�^URL�N���X�^���擾
			final WEB3SleConnectorManager.SleConnectorClusterElement[] l_urls =
				WEB3SleConnectorManager.getSleConnectorAdminClusterElements(
					l_strMarketCode);
//                new SleConnectorClusterElement[]{ new SleConnectorClusterElement("localhost","localhost",10050) };                

			for (int i = 0; i < l_urls.length; i++)
			{

				m_log.info(
					"Will try SLEConnector admin interface address:"
						+ l_urls[i].m_url);

				final String address = l_urls[i].m_address;
				final int port = l_urls[i].m_port;

				Socket sock = null;
				ObjectOutputStream out = null;
				ObjectInputStream in = null;

				boolean l_bShouldSendAdminLogoutMessage = false;
				try {
					sock = new Socket(address, port);
					sock.setSoTimeout(getSocketReadTimeoutSecs() * 1000);

					out = new ObjectOutputStream(sock.getOutputStream());
					in = new ObjectInputStream(sock.getInputStream());
 
					l_bShouldSendAdminLogoutMessage = true;

					AdminServerDescriptor adminServerDesc =
						(AdminServerDescriptor) in.readObject();

					// SLE2004�d���𐶐�
					final GlData l_orderConsReqData = new GlData("2004.ORDER");

					m_log.info(
						"Will send 2004 request for marketCode,productCode:"
							+ l_strMarketCode
							+ ","
							+ l_strProductCode);

					l_orderConsReqData.putString("question_type", "2");
					l_orderConsReqData.putString("order_category", "O");
					l_orderConsReqData.putString(
						"stock_code",
						l_strProductCode == null ? "" : l_strProductCode);
					l_orderConsReqData.putBigDecimal(
						"index",
						new BigDecimal("000000"));
					l_orderConsReqData.putBigDecimal(
						"number_of_requested_replies",
						new BigDecimal("00000"));

					// �d���R�}���h��
					out.writeObject("ordercons");
					// �R�}���h�̃p�����[�^
					out.writeObject(new Object[] { l_orderConsReqData });
					out.flush();

					// SLE2004�̃��X�|���X��ǂݍ���
					int l_intChaining = -1;

					final Map l_hmOrderbookEntriesTable = new HashMap();
					do
					{

						final Object l_result = in.readObject();
						if (l_result instanceof Exception)
						{
							m_log.error(
								"Exception read from the response from AdminServer:"
									+ l_result,
								(Exception) l_result);
							throw new RuntimeException(l_result.toString(), (Exception) l_result);
						}

						final GlData respData = (GlData) l_result;
						final String l_strInternalRef =
							respData.getString("internal_reference");
						if (l_strInternalRef != null)
						{
							l_hmOrderbookEntriesTable.put(l_strInternalRef, respData);
						}

						l_intChaining =
							respData.getBigDecimal("chaining").intValue();
					}
					while (l_intChaining != 0);
					
					m_log.exiting(STR_METHOD_NAME);
					return Collections.unmodifiableMap(l_hmOrderbookEntriesTable);
					
				} catch (IOException ioe) {
					final String errMsg =
						WEB3SleMarketAdapterErrorMessageDef.SLE_ORDER_BOOK_REQUEST_FAIL
							+ ":"
							+ l_urls[i];
					m_log.error(errMsg, ioe);
				} catch (ClassNotFoundException cnfe) {
					final String errMsg =
						WEB3SleMarketAdapterErrorMessageDef.SLE_ORDER_BOOK_REQUEST_FAIL
							+ ":"
							+ l_urls[i];
					m_log.error(errMsg, cnfe);
				} finally {
					//�K���Ǘ��|�[�g�փ��O�A�E�g�E���b�Z�[�W�𑗂�
					if (l_bShouldSendAdminLogoutMessage) {
						try {
							out.writeObject(
								AdminAdaptor.ADMIN_CLIENT_LOGOUT_MSG);
							out.flush();
						} catch (IOException ioe) {
							m_log.error(
								"Exception while writing admin client logout msg:"
									+ ioe.getMessage(),
								ioe);
						}
					}
					//�S�Ď��s����Work���~���邽�߁A���\�[�X���J������O�ŉ��b�قǃX���[�v
					try {
						Thread.sleep(2 * 1000);
					}catch (InterruptedException ignore) {
						;
					}
					//���\�[�X���J������
					if (in != null) {
						try {
							in.close();
						} catch (Exception ignore) {
							;
						}
					}

					if (out != null) {
						try {
							out.close();
						} catch (Exception ignore) {
							;
						}
					}

					if (sock != null) {
						try {
							sock.close();
						} catch (Exception ignore) {
							;
						}
					}
				}
			}

			// �S�Ă̊Ǘ��|�[�g����₢���킹���ʂ����Ȃ��ꍇ�AORDER_BOOK�₢���킹���s�Ƃ��ė�O���X���[����
			final String errMsg =
				WEB3SleMarketAdapterErrorMessageDef.SLE_ORDER_BOOK_REQUEST_FAIL;
			m_log.error(errMsg);
				
			throw new RuntimeException("Could not connect to any SLE Connector admin interface");
		
		} catch (DataException de) {
		
			throw new RuntimeException("Could not connect to any SLE Connector admin interface");
		
		} finally {
			if (l_bLockAcquried) {
				try {
					//SLE�Ǘ��|�[�g�ւ̖K��𒼗񉻂ɂ��邽�߂̃��b�N���J������
					releaseLock(l_strMarketCode);
				} catch (Exception ex) {

					m_log.error(
						"Error while releasing the lock. Exception:"
							+ ex.getMessage(),
						ex);
				}
			}
			m_log.info(
				":::: End of getOrderBook for marketCode,productCode:"
					+ l_strMarketCode
					+ ","
					+ l_strProductCode
					+ ", took:"
					+ (System.currentTimeMillis() - startTime)
					+ "(ms)");
			m_log.exiting(STR_METHOD_NAME);
		}
	}

	/**
	 * �e�N���C�A���g�v���Z�X��SLE�R�l�N�^�|�[�g�𒼗�ŃA�N�Z�X�����邽��
	 * �������b�N�擾����B
	 * @@param marketCode �s��R�[�h
	 */
	private void acquireLock(String l_strMarketCode) throws DataException
	{

		final String spName = "sle_utils.acquireLock";
		try {
			final Object[] inArgs =
				new Object[] {
					DBMS_LOCK_NAME_FOR_SERIALIZING_ADMIN_OPS + "." + l_strMarketCode,
					};
			final Object[] outArgs = new Object[] { null };
			Processors.getDefaultProcessor().doReadOnlyProcedureCallQuery(
				spName,
				inArgs,
				outArgs);
		} catch (DataException de) {

			final String msg =
				"Exception while calling stored procedure:" + spName;
			m_log.error(msg, de);
			throw de;
		}
	}

	/**
	 * SLE�R�l�N�^�A�N�Z�X�ւ̒��񃍃b�N���J������
	 * @@param marketCode �s��R�[�h
	 */
	private void releaseLock(String l_strMarketCode) throws DataException
	{

		final String spName = "sle_utils.releaseLock";
		try {
			final Object[] inArgs =
				new Object[] {
					DBMS_LOCK_NAME_FOR_SERIALIZING_ADMIN_OPS + "." + l_strMarketCode,
					};
			final Object[] outArgs = new Object[] { null };
			Processors.getDefaultProcessor().doReadOnlyProcedureCallQuery(
				spName,
				inArgs,
				outArgs);
		} catch (DataException de) {

			final String msg =
				"Exception while calling stored procedure:" + spName;
			m_log.error(msg, de);
			throw de;
		}
	}
	
	/**
	 * ORDERBOOK�₢���킹���ʂ��L�������ł��邩�`�F�b�N���� 
	 * @@param g_gldata 2004���X�|���X�f�[�^
	 * @@return : �L���ł���ꍇ�Atrue��Ԃ� �����ł���ꍇ�Afalse��Ԃ�
	 */
	private boolean isAvailableOrderBookResp(GlData g_gldata) throws RuntimeException
	{
		final String STR_METHOD_NAME = "isRejectedOrder(GlData)";
		m_log.entering(STR_METHOD_NAME);

		
		//order book ��order status ���擾
		String s_orderstatus = g_gldata.getString("order_status0");
		
		//order book��order status �� 'R' �̈ȊO�̏ꍇ�A
		if (!s_orderstatus.equals("R"))
		{
			return true;
		}
		
		try 
		{
			String s_memo = g_gldata.getString("memo");
			final String l_strWhere = "memo = ?";
			final String l_strOrderBy = "created_timestamp desc";
			final Object[] l_ObindVars = { s_memo, };
			
			final QueryProcessor qp = Processors.getDefaultProcessor();
			final List rows =
				qp.doFindAllQuery(
					SleRcvdQRow.TYPE,
					l_strWhere,
					l_strOrderBy,
					null,
					l_ObindVars);
			if (rows.size() > 0)
			{
				SleRcvdQRow row =
					(SleRcvdQRow) (rows.get(0));
				
				String s_rejectcode = row.getRejectCode();
				//�Ή����钍�������̃��X�|���X�� reject code = 'GL013'�ł���ꍇ�Arejected����钍���Ƃ݂Ȃ���Afalse��Ԃ�
				if ( s_rejectcode.equals(WEB3SleCallbackGLRejectTypeDef. EXCHNAGE_PHY_CONN_FAIL))
				{
					m_log.exiting(STR_METHOD_NAME);   
					return false;
				}
				else
				{
					m_log.info ("the order is not a rejected order because the reject code is : " + s_rejectcode);
				
				}
				
			}
			else
			{
				m_log.warn("there is no responses for the reject order message queue_id:" + s_memo);
		
			}
			m_log.exiting(STR_METHOD_NAME);
		    return true;
		    
		} catch (DataException de) {
			m_log.exiting(STR_METHOD_NAME);   
			throw new RuntimeException("Exception while checking isRejectedOrder:" + de);		
		}
	
	}

}
@
