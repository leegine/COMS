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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleRequestSenderクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 李(FLJ) 新規作成
 Revision History : 2006/5/19 李(FLJ)　@ORDER_BOOKへ送信した否か確認処理を対応
 Revision History : 2006/6/8 李(FLJ)　@ソース精査
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
 * 指定したSEND_QメッセージコンテンツをSLEコネクタへ送信する
 */
public class WEB3SleRequestSender
{
	
	/**
	 * ログ出力ユーティリティ
	 */
	private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleRequestSender.class);
	
	/**
	 * ログへデバッグ出力フラグ
	 */
	private static final boolean DBG = m_log.ison();
	
	/**
	 * DBプロセッサ
	 */
	private static WEB3SleProcessors m_wsp = new WEB3SleProcessorsImpl();
	
	/**
	 * Order_Bookの問い合わせツール
	 */
	private static WEB3SleOrderBookStatusCheckerInterface m_ordchk = WEB3SleOrderBookStatusChecker.getInstance();

	/**
	 * コンストラクタ(外部からインスタンス生成禁止)
	 */
	private WEB3SleRequestSender()
	{
		;
	}
	
	/**
	 * DBプロセッサを設定する
	 * @@param wsp DBプロセッサ
	 */
	public static void setWeb3SleProcessor(WEB3SleProcessors wsp)
	{
		m_wsp = wsp;
	}
	
	/**
	 * Order_Bookの問い合わせツール
	 * @@param ordchk Order_Bookの問い合わせツール
	 */
	public static void setOrderBookChecker(WEB3SleOrderBookStatusCheckerInterface ordchk)
	{
		m_ordchk = ordchk;
	}

	/**
	 * SEND_QメッセージコンテンツをGlSleRequestオブジェクトへ変換し、 コネクタへ送信する
	 * 
	 * @@param sleSendqRow SEND_Qメッセージ
	 * @@throws GlSleConnectorClientException SLEコネクタへ送信時送信エラーが発生した場合スローされる
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
	 * SEND_QメッセージをGlSleRequestオブジェクトへ変換する
	 * 
	 * @@param sleSendqRow SEND_Qメッセージ
	 * @@return GlSleRequest　@GlSleRequestオブジェクト　@
	 */
	private static GlSleRequest toGlSleRequest(SleSendQRow l_sleSendqRow) throws RuntimeException
	{

//		final WEB3SleRequestPreparer l_preparer =  new WEB3SehkSleRequestPreparer( new WEB3SleProcessorsImpl());
//		final WEB3SleRequestPreparer l_preparer =  WEB3SehkSleRequestPreparer.getInstance();
		//↑prepareクラスをsinglton化にするため、インスタンス方法@を改修 2006/12/4
		
		//↓シンセン市場対応2007/10/23
		final WEB3SleRequestPreparer l_preparer =  WEB3SleMarketAdapterUtils.getSleRequestPreparer(l_sleSendqRow.getMarketCode());

		if(l_preparer==null)
		{
			final String errMsg =
				"There is no WEB3SleRequestPreparer available for the marketCode: " + l_sleSendqRow.getMarketCode();
			throw new RuntimeException(errMsg);
		}
		//↑シンセン市場対応2007/10/23
		
		return l_preparer.prepare(l_sleSendqRow);
	}

	/**
	 * SLEリクエストを対応する市場のコネクタへ送信する
	 * 
	 * @@param sleSendqRow
	 *            SEND_Qメッセージ
	 * @@param connectorfactory 
	 * 			  クライアント提供するプール
	 * @@param marketCode
	 *           市場の市場コード.
	 * @@param req
	 *            SLE送信電文を保持するGlSleRequest
	 * @@throws GlSleConnectorClientException
	 *             SLEコネクタクライアント例外をスローする
	 */
	private static void send(SleSendQRow l_sleSendqRow, WEB3SleConnectorClientFactory l_connectorfactory, String l_strMarketCode, GlSleRequest l_glSlereq)
			throws GlSleConnectorClientException,RuntimeException //RuntimeException例外を追加 (2006/10/11)
	{		
		m_log.entering("send(SleSendQRow, String, GlSleRequest)"); 
		
		boolean l_bSendToSle = true;
		boolean l_bCloseSleSendqError = false;
		
		if (errorsInSleSendqErrors(l_sleSendqRow.getQueueId())) 
		{
			m_log.info("Will try auto-recovery. Errors found in sle_send_q_errors table for sle_send_q row:" + l_sleSendqRow);
			l_bCloseSleSendqError = true;
			
			/* SLEへ正しく送信した否か確認
			   既に正しく送信した場合、重複送信しない*/
			try{
				if(m_ordchk.isAlreadySent(l_sleSendqRow)) 
				{
					m_log.info("Already sent to SLE. sle_send_q row:" + l_sleSendqRow);
					l_bSendToSle = false;
				}
			}catch(RuntimeException re){
//				String errMsg = WEB3SleMarketAdapterErrorMessageDef.SLE_ORDER_BOOK_REQUEST_FAIL; 
//				m_log.error(errMsg); 
				//↑WEB3SleOrderBookStatusCheckerクラスにすでにorder book 問い合わせエラーが出力されるため、削除。
				//return;
				m_log.debug("there is a order book queue error happend!");
//				closeSleSendqErrorsRow(l_sleSendqRow.getQueueId());//ORDERBOOK問い合わせ失敗時でsend_q_errorsキューをクローズする(2006/10/12)⇒コメントアウト 2007/1/8
				throw re;//←　@発生したruntimeexceptionを送信サービスクラスへスローする(2006/10/12)
			}
		}
		
		
		if(l_bSendToSle)
		{	
			// SLEコネクタへ送信する
			try{
				WEB3SleConnectorManager.send(l_connectorfactory, l_strMarketCode, l_glSlereq);
			}catch(GlSleConnectorClientException gle){//送信失敗後でクローズ処理を追加(2006/9/25 FLJ李)
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
//				//市場SLEコネクタクラスタをクリアする
//				WEB3SleConnectorManager.invalidateGlSleConnectorClientConnection(l_sleSendqRow.getMarketCode());
//			}//⇒send_qメッセージ毎でSLEコネクタをクリアのは非効率で、クリア処理はcallback処理へ移す
			if (DBG) m_log.debug("Sent to GL SleConnector OK");
		}
	
		// ORDER_BOOKへの問い合わせで確認済みのsle_send_q_errorsのメッセージに対しステータスをクローズする
		if(l_bCloseSleSendqError)
		{
			if(DBG) m_log.debug(("closing sle_send_q_errors with queue_id:" + l_sleSendqRow.getQueueId()));
			closeSleSendqErrorsRow(l_sleSendqRow.getQueueId());
		}
		
		
		
		m_log.exiting("send(SleSendQRow, String, GlSleRequest)");	
	}
	
	/**
	 * 指定したキューIDをsle_send_q_errorsの'OPEN'中のメッセージ に存在したか確認
	 * @@param queue_id ORDER_BOOKへ問い合わせ対象のキューID
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
	 * ORDER_BOOKへ問い合わせで確認済みのsle_send_q_errorsのメッセージのステータスをクローズする
	 * @@param l_lngQueueid ORDER_BOOKへ問い合わせで確認済みのsle_send_q_errorsのメッセージのキューID
	 */
	private static void closeSleSendqErrorsRow(long l_lngQueueid)
	{
		
		try {
			final QueryProcessor l_qp = m_wsp.getDefaultProcessor();
			
			final Map l_hmChanges = new HashMap();
			l_hmChanges.put("open_status", SleOpenStatusEnum.CLOSE);
			l_hmChanges.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());	
			// sle_send_q_errorsのメッセージステータスをクローズする
			l_qp.doUpdateQuery(new SleSendQErrorsPK(l_lngQueueid), l_hmChanges);
		}catch (DataException de){
			final String msg = "Exception while closing sle_send_q_errors table with queue_id:" + l_lngQueueid;
			m_log.error(msg, de);
			throw new RuntimeException(msg, de);
		}
	}
}

@
