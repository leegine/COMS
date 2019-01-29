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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleSendqProcessorManagerServiceImplTransactionCallbackクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/06/7 李 (FLJ) 新規作成
 Revision History : 2006/06/8 李 (FLJ) ソース精査
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
 * SleSendqProcessorManagerServiceImplにおけるトランザクションコールバック
 */
public class WEB3SleSendqProcessorManagerServiceImplTransactionCallback implements TransactionCallback
{

	/**
	 * ログ出力ユーティリティ
	 */
	private static final WEB3LogUtility m_log =WEB3LogUtility.getInstance(WEB3SleSendqProcessorManagerServiceImplTransactionCallback.class);//←修正 2006/10/11

	/**
	 * SLEコネクタクライアントプール(UT用)
	 */
	private static WEB3SleConnectorClientFactory m_connectorfactory = new WEB3SleConnectorClientFactoryImpl();

	/**
	 * DBプロセッサ(UT用)
	 */
	private static WEB3SleProcessors m_wsp = new WEB3SleProcessorsImpl();

	/**
	 * Thread No <BR>
	 */
	private Long m_threadNo;

	/**
	 * (From口座ID)
	 */
	private long m_fromAccountId;

	/**
	 * (To口座ID)
	 */
	private long m_toAccountId;
	
	/**
	 * (市場コード)
	 */
	private String[] m_marketCode;//⇒2007/10/23 深セン市場対応

	/**
	 * From口座ID Setter
	 * @@param fromAccountId
	 */
	public void setFromAccountId(long fromAccountId)
	{
		m_fromAccountId = fromAccountId;
	}

	/**
	 * (To口座ID) Setter
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
	 * @@param code m_marketCode を設定。
	 */
	public void setMarketCode(String[] marketCode) {
		m_marketCode = marketCode;
	}
	//↑2007/10/23 深セン市場対応

	/**
	 * トランザクションのSLEコネクタファ@クトリを設定(UT用)
	 * @@param connectorfactory　@SLEコネクタファ@クトリインスタンス
	 */
	public void setConnectorFactory(WEB3SleConnectorClientFactory connectorfactory)
	{
		m_connectorfactory = connectorfactory;
	}

	/**
	 * トランザクションのDBプロセッサを設定(UT用)
	 * @@param wsp DBプロセッサインスタンス
	 */
	public void setProcessors(WEB3SleProcessors wsp)
	{
		m_wsp = wsp;
	}
	
	/**
	 * (process) <BR>
	 * <BR>
	 * トランザクション処理を実行する。 <BR>
	 * @@return Object
	 * @@throws DataNetworkException,
	 *           DataQueryException, DataCallbackException
	 */
	public Object process()throws DataNetworkException, DataQueryException, DataCallbackException
	{
		m_log.entering("process()");

		WEB3SleSendqProcessorService l_processor =
				(WEB3SleSendqProcessorService)Services.getService(WEB3SleSendqProcessorService.class);
				
		// 市場毎CircuitBrakerフラグの状態をチェックするように改修より削除
		// Circuit Braker 状態中なら、送信プロセスを起動しない
			
			//処理スレッド占有
			if (lockThread(this.m_threadNo.longValue()) == false) {
				final String msgErr = WEB3SleMarketAdapterErrorMessageDef.SEND_THREAD_LOCKED_STATUS;
				m_log.error(msgErr);
			
				m_log.exiting("process()");
				return null;
			}
			String mktCode = null; //送信先市場コード add 2006/10/23
			
			try {
				final List l_lisRows = getRows();
				
				for (int i = 0; i < l_lisRows.size(); i++) {
					final Row r = (Row) l_lisRows.get(i);

					mktCode =  ((SleSendQRow) r).getMarketCode(); 

					// Circuit Breakerされた状態なら送信処理を中止する
					if (WEB3SleMarketAdapterUtils.isStopRequested(mktCode)) {
						//市場毎CircuitBrakerフラグの状態をチェックするように改修
						continue;
					}
					
					m_log.debug (r.toString());

					l_processor.processRow(r,this.m_wsp,this.m_connectorfactory);

				}
			} catch (Exception ex) {
								
				/*市場へ大量無効注文を発送するのを防止するため
				  送信処理中止する*/
				
				m_log.error(ex.getMessage(), ex);
				m_log.exiting("process() ");
				throw new DataCallbackException("Could not successful run a processRow transaction ");
			}
//			finally{
//				//市場SLEコネクタクラスタをクリアする
//				WEB3SleConnectorManager.invalidateGlSleConnectorClientConnection(mktCode);
//			}
//			処理不要 2007/1/8
//		}
		m_log.exiting("process() ");
		
		return null;
	}

	/**
	 * 送信対象のSEND_Qメッセージを抽出
	 * @@return List 送信対象のSEND_Qメッセージリスト
	 */
	private List getRows() throws DataException
	{

		m_log.entering("getRows() ");
		// 送信対象を抽出
		String l_strWhere =
			"(status = ? or status = ?) and  account_id >= ? and account_id <= ? and biz_date = ? ";//'未送信'処理区分の条件を追加　@⇒ 2006/10/20
		//final String l_strOrderBy = "last_updated_timestamp";
		final String l_strOrderBy = "created_timestamp";//sendqメッセージ生成時間によってソートすれば、元々業務上に注文が発生する時間と一致する　@2006/10/31
		Object[] l_ObindVars =
		{
			SleSendqProcStatusEnum.TODO,
			SleSendqProcStatusEnum.NOT_PROCESSED,//'未送信'処理区分の条件を追加 ⇒ 2006/10/20
			Long.valueOf(String.valueOf(m_fromAccountId)),
			Long.valueOf(String.valueOf(m_toAccountId)),
			//発注日の検索条件を追加 2009/8/7
			WEB3SleMarketAdapterUtils.getBizDate(),
		};
		
		//↓2007/10/23 深セン市場対応
		//複数市場がある場合、SQLにwhere子句内容を追加
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
		//↑2007/10/23 深セン市場対応
					
		final int l_pSize = getMaxServiceSizeOneTime();
		final QueryProcessor l_qp = Processors.getDefaultProcessor();
		final List l_lisRows =
			l_qp.doFindAllQuery(SleSendQRow.TYPE, l_strWhere, l_strOrderBy, null, l_ObindVars,getMaxServiceSizeOneTime(),0);
		m_log.debug("total pages:"+ ((ListPage)l_lisRows).totalPages());
		m_log.debug("total size :"+ ((ListPage)l_lisRows).totalSize());

		/*最大数以上の未送信処理メッセージが存在する場合
		  最大数まで送信すること*/
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
	 * スレッドロックを取得する <BR>
	 * <BR>
	 * Thread番号 <BR>
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
	 * 1回で最大送信メッセージ数
	 * @@return int 最大送信メッセージ数
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
