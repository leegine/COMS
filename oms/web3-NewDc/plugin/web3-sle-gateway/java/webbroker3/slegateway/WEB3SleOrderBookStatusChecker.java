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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleOrderBookStatusCheckerクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/19 李（FLJ) 新規作成
 Revision History : 2006/06/08 李（FLJ) ソース精査
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
 * SLEのORDER_BOOK問い合わせユーティリティクラス
 * @@author  : 李（FLJ）
 * @@version : 1.0
 */
public class WEB3SleOrderBookStatusChecker implements WEB3SleOrderBookStatusCheckerInterface
{

	/**
	 * ログ出力ユーティリティ
	 */
	private static WEB3LogUtility m_log =WEB3LogUtility.getInstance(WEB3SleOrderBookStatusChecker.class);
	
	/**
	 * デバッグログ出力フラグ
	 */
	private static final boolean DBG = m_log.ison();

	/**
	 * SLE管理ポートの訪問を直列化するためかけられたORCLE DBロック名
	 */
	public static final String DBMS_LOCK_NAME_FOR_SERIALIZING_ADMIN_OPS = "web3.sle.connector.admin.ops";
	
	/**
	 * Order_Book 問い合わせツールのsingltonインスタンス
	 */
	private static WEB3SleOrderBookStatusChecker m_checker = null;

	/**
	 * コンストラクタ
	 */
	private WEB3SleOrderBookStatusChecker() {
		;
	}
	
	/**
	 * Singletonインスタンス取得する
	 */
	public static WEB3SleOrderBookStatusChecker getInstance()
	{
		if (m_checker == null){
			m_checker = new WEB3SleOrderBookStatusChecker();
		}
		return m_checker;
	}

	/**
	 * SEND_Q注文メッセージが既にSLEに正しく送信されるかチェック
	 * @@param l_sleSendqRow SEND_Q注文メッセージ
	 * @@return 送信済み:trueを返す　@他:falseを返す。
	 */
	public boolean isAlreadySent(SleSendQRow l_sleSendqRow)
		throws RuntimeException
	{

		final String STR_METHOD_NAME = "isAlreadySent(SleSendQRow)";
		m_log.entering(STR_METHOD_NAME);

		//ORDER_BOOK取得
		final Map orderbookEntriesTable =
			getOrderBook(
				l_sleSendqRow.getMarketCode(),
				l_sleSendqRow.getProductCode());

		m_log.exiting(STR_METHOD_NAME);
		return isAlreadySent(l_sleSendqRow, orderbookEntriesTable);
	}

	/**
	 * SEND_Q注文メッセージが既にSLEに正しく送信されるかチェック
	 * @@param l_sleSendqRow SEND_Q注文メッセージ
	 * @@param l_mOrderBook 取得した注文ORDER_BOOK
	 * @@return 送信済み:trueを返す　@他:falseを返す。
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

			// 送信済み
			l_bRetResult = Boolean.valueOf(l_gldata != null && isAvailableOrderBookResp( l_gldata) );//条件を追加 2006/2/9
		}
		else if (l_gldata != null)
		{

			final String memo = l_gldata.getString("memo");
//			final int sendqMsgNoInTheOrderBook = Integer.parseInt(memo);
//			l_bRetResult =
//				Boolean.valueOf(
//					sendqMsgNoInTheOrderBook >= l_sleSendqRow.getQueueId());
			//比較基準をqueue_idではなくcreated_timestampとするように改修 2006/10/31
			final Timestamp createTimestampOfOrderMessageInTheOrderBook = getCreatedTimeByMemo(l_gldata.getString("memo")); 
//			l_bRetResult = 
//			    Boolean.valueOf(
//			    	(createTimestampOfOrderMessageInTheOrderBook.after(l_sleSendqRow.getCreatedTimestamp())
//					    || createTimestampOfOrderMessageInTheOrderBook.equals(l_sleSendqRow.getCreatedTimestamp())) && (isAvailableOrderBookResp(l_gldata))) ;//条件を追加 2006/2/9
			final int l_sendqQueueId = Integer.parseInt(memo);
			l_bRetResult =
				Boolean.valueOf( 
					( 
						(l_sendqQueueId == l_sleSendqRow.getQueueId()) 
							&& ( isAvailableOrderBookResp(l_gldata))
					)    //order bookにあるlast 注文が確認注文対象である場合
					|| 
					(createTimestampOfOrderMessageInTheOrderBook.after(l_sleSendqRow.getCreatedTimestamp()))   //order bookにあるlast 注文が確認注文対象より若いの場合
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
	 * gl_data.memo電文項目に対応するsend_q注文メッセージのcreated_timestampを返す
	 * @@return 'memo'対応するsend_q注文メッセージのcreated_timestampを返す
	 * ↑追加 2006/10/31
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
	 * 管理ポートと接続のソケットのタイムアウト時間.
	 * 
	 * @@return  管理ポートと接続のソケットのタイムアウト時間 .
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
	 * 市場コード、銘柄コードに対応するORDER BOOK問い合わせ結果取得
	 * @@param marketCode 市場コード
	 * @@param productCode　@銘柄コード
	 * @@return　@ORDER BOOK の問い合わせ結果とInternalRef (注文ID)のマッピング対応関係を保持するMap
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
			// 各クライアントプロセスをSLEコネクタポートを直列でアクセスさせるため
			// 処理ロック取得する。
			acquireLock(l_strMarketCode);
			l_bLockAcquried = true;

			// SLEコネクタURLクラスタを取得
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

					// SLE2004電文を生成
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

					// 電文コマンド名
					out.writeObject("ordercons");
					// コマンドのパラメータ
					out.writeObject(new Object[] { l_orderConsReqData });
					out.flush();

					// SLE2004のレスポンスを読み込み
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
					//必ず管理ポートへログアウト・メッセージを送る
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
					//全て実行中のWorkを停止するため、リソースを開放する前で何秒ほどスリープ
					try {
						Thread.sleep(2 * 1000);
					}catch (InterruptedException ignore) {
						;
					}
					//リソースを開放する
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

			// 全ての管理ポートから問い合わせ結果がこない場合、ORDER_BOOK問い合わせ失敗として例外をスローする
			final String errMsg =
				WEB3SleMarketAdapterErrorMessageDef.SLE_ORDER_BOOK_REQUEST_FAIL;
			m_log.error(errMsg);
				
			throw new RuntimeException("Could not connect to any SLE Connector admin interface");
		
		} catch (DataException de) {
		
			throw new RuntimeException("Could not connect to any SLE Connector admin interface");
		
		} finally {
			if (l_bLockAcquried) {
				try {
					//SLE管理ポートへの訪問を直列化にするためのロックを開放する
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
	 * 各クライアントプロセスをSLEコネクタポートを直列でアクセスさせるため
	 * 処理ロック取得する。
	 * @@param marketCode 市場コード
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
	 * SLEコネクタアクセスへの直列ロックを開放する
	 * @@param marketCode 市場コード
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
	 * ORDERBOOK問い合わせ結果より有効注文であるかチェックする 
	 * @@param g_gldata 2004レスポンスデータ
	 * @@return : 有効である場合、trueを返す 無効である場合、falseを返す
	 */
	private boolean isAvailableOrderBookResp(GlData g_gldata) throws RuntimeException
	{
		final String STR_METHOD_NAME = "isRejectedOrder(GlData)";
		m_log.entering(STR_METHOD_NAME);

		
		//order book のorder status を取得
		String s_orderstatus = g_gldata.getString("order_status0");
		
		//order bookのorder status が 'R' の以外の場合、
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
				//対応する注文注文のレスポンスが reject code = 'GL013'である場合、rejectedされる注文とみなされ、falseを返す
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
