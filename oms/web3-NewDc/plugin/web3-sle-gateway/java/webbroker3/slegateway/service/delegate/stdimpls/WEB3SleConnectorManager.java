head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleConnectorManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleConnectorManagerクラス
 Author Name      : Daiwa Institute of Research
 Revision History : warlu FTL.US 新規作成
 Revision History : 2006/04/24 李 新規作成
 Revision History : 2006/06/06 李（FLJ) バッグ対応
 Revision History : 2006/06/08 李（FLJ) ソース精査 
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xbconnector.glbase.gldata.GlSleRequest;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClient;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientException;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientInitialException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.slegateway.WEB3SleConnectorClientFactory;
import webbroker3.slegateway.WEB3SleMarketAdapterUtils;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientFactory;//プールクライアントのタイムアウト対応に 2006/10/26 追加


/**
 * SLEコネクタクライアント管理クラス.
 */
public class WEB3SleConnectorManager
{

	/**
	 * ログ出力ユーティリティ
	 */
	private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleConnectorManager.class);
	
	/**
	 * デバッグログ出力フラグ
	 */
	private static final boolean DBG = m_log.ison();
	
	/**
	 * コンストラクタ
	 */
	private WEB3SleConnectorManager()
	{
		;
	}

	/**
	 * 論理市場コードからGL管理市場コードへ変換する
	 * @@param xBlocksMarketCode
	 *          xBlockビジネスロジックに識別される論理市場コード
	 * @@return SLEコネクタに識別される物理市場コード
	 */
	public static String getLogicalMarketCode(String l_strXBlocksMarketCode)
	{	
		return l_strXBlocksMarketCode;//xBlockの市場コードに対し変換なし、そのまま返す
	}

	/**
	 * 指定した市場へのSLEコネクタを取得する.
	 * @@param xBlocksMarketCode
	 *          市場コード.
	 * @@return Conenctor 市場へのSLEコネクタ.
	 */
	public static GlSleConnectorClient getSleConnector(
		WEB3SleConnectorClientFactory l_connectorfactory,
		String l_strxBlocksMarketCode,
		GlSleRequest l_glreq)//自動でstandbyへ切り替えように改修　@2006/10/27
//		throws GlSleConnectorClientInitialException
		throws GlSleConnectorClientException //sendRequest処理追加ことによって変更 2006/10/27 
	{

		m_log.entering("GlSleConnectorClient	getSleConnector(String)");
		
		SleConnectorClusterElement[] l_cluster =
			SleConnectorClusterElementManager.getSleConnectorClusterElements(l_strxBlocksMarketCode);
		if (l_cluster == null)
		{
			synchronized (WEB3SleConnectorManager.class) {

				l_cluster =
					SleConnectorClusterElementManager.getSleConnectorClusterElements(l_strxBlocksMarketCode);
				if (l_cluster == null) 
				{

					l_cluster =
						getSleConnectorOrderSubmissionClusterElements(l_strxBlocksMarketCode);
					if (l_cluster.length == 0)
					{
						final String errMsg =
							"No SLE connector URLs found for market code: "
								+ l_strxBlocksMarketCode;
						m_log.error(errMsg);
						throw new RuntimeException(errMsg);
					}
					SleConnectorClusterElementManager.setSleConnectorClusterElements(l_strxBlocksMarketCode, l_cluster);
				}
			}
		}

		//市場クラスタから有効なコネクタクライアントを検出する
		for (int i = 0; i < l_cluster.length; i++)
		{

			if (l_cluster[i].isGlSleConnectorClientConnectionOnline()) 
			{

				m_log.exiting(
					"GlSleConnectorClient getSleConnector(String)");
				l_cluster[i].getGlSleConnectorClient().sendRequest(l_glreq);//sendRequest処理を追加(2006/10/26)
				return l_cluster[i].getGlSleConnectorClient();
			}
		}

		synchronized (WEB3SleConnectorManager.class)
		{

			/*
              全てのコネクタがオフラインの時、
			  再試行する
			 */
			int offlineCount = 0;
			for (int i = 0; i < l_cluster.length; i++)
			{

				if (l_cluster[i].isGlSleConnectorClientConnectionTried()
					&& l_cluster[i].isGlSleConnectorClientConnectionOffline())
				{
					offlineCount++;
				}
			}

			final boolean retryOnOffline = (offlineCount == l_cluster.length);
			
			Map l_mException = new HashMap(); //sendRequest処理時で発生したExceptionを保持するため追加(2006/10/26)
			for (int i = 0; i < l_cluster.length; i++)
			{

				if (l_cluster[i].isGlSleConnectorClientConnectionOnline())
				{

					m_log.exiting(
						"GlSleConnectorClient	getSleConnector(String)");
					l_cluster[i].getGlSleConnectorClient().sendRequest(l_glreq);//sendRequest処理を追加(2006/10/26)
					return l_cluster[i].getGlSleConnectorClient();
				}

				final boolean connectionNotTriedYet =
					!l_cluster[i].isGlSleConnectorClientConnectionTried();

				if (connectionNotTriedYet || retryOnOffline)
				{

					m_log.info(
						"::: Opening SLE connector client connection for marketCode:"
							+ l_strxBlocksMarketCode
							+ ", target:"
							+ l_cluster[i]);
					try {
						final InetAddress ia =
							InetAddress.getByName(l_cluster[i].m_address);
							
						//プールクライアントタイムアウト対応するには追加 2006/10/26
						Properties l_pTimeOut = getPoolClientProperties();

						// 一つ新規コネクタを生成する
						final GlSleConnectorClient connector =
							l_connectorfactory.getPoolClient(
								ia,
								l_cluster[i].m_port,
								getSocketPoolSize(),
								l_pTimeOut);
						//↑プールクライアントタイムアウトを対応するにはtimeoutプロパティを追加 2006/10/26	
						connector.sendRequest(l_glreq);//sendRequest処理追加　@2006/10/27
						
						l_cluster[i].setGlSleConnectorClient(connector);
						l_cluster[i].setGlSleConnectorClientConnectionStatusToOnline();
						//新規コネクタをクラスタに入れる
						SleConnectorClusterElementManager
							.setSleConnectorClusterElements(
							l_strxBlocksMarketCode,
							l_cluster);

						m_log.exiting(
							"GlSleConnectorClient	getSleConnector(String)");
						return connector;
					}
					catch(GlSleConnectorClientException gle){//sendRequest処理追加によって add 2006/10/2
						final String errMsg =
							"Sle Connector Client exception. Perhaps the Sle Connector is invalid. Url specified:"
								+ l_cluster[i].m_address + "/" + l_cluster[i].m_port;
						m_log.warn(errMsg);

						l_cluster[i].setGlSleConnectorClientConnectionStatusToOffline();
						l_mException.put("glsleexception",gle);						
					}
					catch (UnknownHostException uhe) {

						final String errMsg =
							"Unknownhost exception. Perhaps the URL is wrong. Url address specified:"
								+ l_cluster[i].m_address;
						m_log.warn(errMsg);

						l_cluster[i].setGlSleConnectorClientConnectionStatusToOffline();
					} catch (Exception e) {
						final String errMsg = "Unknown exception happend.";
						m_log.error(errMsg);
						l_cluster[i].setGlSleConnectorClientConnectionStatusToOffline();
					}
				}
			}
			if ( l_mException.get("glsleexception") == null){//GlSleConnectorClientInitialException 優先スローされる 追加 2006/10/27
				m_log.debug("No GlSleConnector client connections available");//add 2006/10/27
				m_log.exiting("GlSleConnectorClient	getSleConnector(String)");
				throw new GlSleConnectorClientInitialException("No GlSleConnector client connections available");
			}else{//sendRequestを実行時エラー
				m_log.debug("there is a GlSleConnectorClientException happened !");
				m_log.exiting("GlSleConnectorClient	getSleConnector(String)");
				throw (GlSleConnectorClientException)l_mException.get("glsleexception");
			}
		}		
	}

	/**
	 * Sends the given request to Sle Connector
	 * @@param marketCode
	 *          香港市場の市場コード.
	 * @@param req
	 *          SLE送信電文を保持するオブジェクト
	 * @@throws GlSleConnectorClientException
	 *           SLEコネクタクライアント例外
	 */
	public static void send(
		WEB3SleConnectorClientFactory l_connectorfactory,
		String l_strMarketCode,
		GlSleRequest l_glreq)
		throws GlSleConnectorClientException,RuntimeException //←RuntimeException例外を追加 2006/10/11
	{
		try {
			m_log.info("Sending request to SLE:" + l_glreq);
			
			final GlSleConnectorClient sleConnector =
				getSleConnector(l_connectorfactory, l_strMarketCode,l_glreq);//自動でstandbyへ切り替えように改修　@2006/10/27

			
			//sleConnector.sendRequest(l_glreq);//自動でstandbyへ切り替えように改修 2006/10/27
			
			if (DBG){
				m_log.debug("Sent to GL SleConnector OK");
			}
			
		} catch (GlSleConnectorClientException ex) {
			
			m_log.debug("there is a GlSleConnectorClientException happened !");
			
			WEB3SleConnectorManager.invalidateGlSleConnectorClientConnection(l_strMarketCode);
			//throw new GlSleConnectorClientException(ex.getMessage(), ex);
			/*
			  SleSendqProcessorServiceImplエラーハンドルで例外種別を判断できないため
			  ラッパを行わず、送信処理際で発生したGlSleConnectorClientExceptionの例外をそのままスローする
			 */
			throw ex;
		} catch (RuntimeException re){
			m_log.debug("there is a unknow exception happend !");
			throw re;
		}
	}

	/**
	 * コネクタをクローズする
	 */
	public static void close() {

		m_log.entering("close()");
		synchronized (WEB3SleConnectorManager.class) {

			//クラスタに存在した全てコネクタクライアントをクローズする
			final Iterator it =
				SleConnectorClusterElementManager.getSleConnectorClusterElementsEntrySetIterator();
			
			if ( it == null){//add 2006/10/23
				m_log.warn("the sle connector is not closed because there is no valid sle-connector cluster.");
				m_log.exiting("close()");
				return;
			}
			
			while (it.hasNext()) {

				final Map.Entry e = (Map.Entry) it.next();
				final String l_strMarketCode = (String) e.getKey();

				m_log.info(
					"Closing SleConnectorClient connection(s) for marketCode:"
						+ l_strMarketCode);
						
				final SleConnectorClusterElement[] l_cluster = (SleConnectorClusterElement[]) e.getValue();
				
				if ( l_cluster == null){//add 2006/10/23
					m_log.warn("the sle connector is not closed because there is no valid sle-connector cluster.");
					continue;
				}
				
				for (int i = 0; i < l_cluster.length; i++)
				{
					if ( l_cluster[i] == null){//add 2006/10/23
						m_log.warn("the sle connector is not closed because there is no valid sle-connector cluster.");
						continue;
					}
					
					final GlSleConnectorClient slecc = l_cluster[i].getGlSleConnectorClient();
					
					if (slecc != null)
					{
						try {
							slecc.close();
							//李(FLJ)追加
							l_cluster[i].setGlSleConnectorClientConnectionStatusToOffline();
						} catch (Throwable t) {

							final String errMsg =
								"Exception while closing SleConnectorClient connection for marketCode:"
									+ l_strMarketCode;
							m_log.warn(errMsg, t);
						}
					}
				}
			}
		}

		m_log.exiting("close()");
	}

	/**
	 * 現在のアクティブコネクタを無効にする
	 * @@param marketCode
	 *         市場コード
	 */
	public static void invalidateGlSleConnectorClientConnection(String l_strMarketCode)
	{

		m_log.entering("invalidateGlSleConnectorClientConnection(String)");

		synchronized (WEB3SleConnectorManager.class)
		{

			//全部の接続を閉じる
			final SleConnectorClusterElement[] l_cluster =
				SleConnectorClusterElementManager.getSleConnectorClusterElements(l_strMarketCode);
			
			if (l_cluster == null)  {// ⇒追加 2006/10/23
				m_log.warn("the sle connector is not cleared because there is no valid sle-connector cluster.");
				m_log.exiting("invalidateGlSleConnectorClientConnection(String)");
				return;
			}
			for (int i = 0; i < l_cluster.length; i++)
			{
				
				if (l_cluster[i] == null){//⇒　@追加 2006/10/23
					m_log.warn("the sle connector is not cleared because there is no valid sle-connector cluster.");
					continue; 
				}
				
				if (l_cluster[i].isGlSleConnectorClientConnectionOnline())
				{
					l_cluster[i].setGlSleConnectorClientConnectionStatusToOffline();

					//接続に試みして
					final GlSleConnectorClient l_slecc =
						l_cluster[i].getGlSleConnectorClient();
					if (l_slecc != null) {

						m_log.info(
							"Closing SleConnectorClient connection for marketCode:"
								+ l_strMarketCode);
						try {
							l_slecc.close();
						} catch (Throwable t) {
							final String errMsg =
								"Exception while closing SleConnectorClient connection for marketCode:"
									+ l_strMarketCode;
							m_log.warn(errMsg, t);
						}
					}

					// 接続をnullに設定する
					l_cluster[i].setGlSleConnectorClient(null);
					

					WEB3SleMarketAdapterUtils.moveElementToEnd(l_cluster, i); //2007/1/6 修正 ロード

					m_log.exiting(
						"invalidateGlSleConnectorClientConnection(String)");
					return;
				}
			}
		}

		m_log.exiting("invalidateGlSleConnectorClientConnection(String)");

	}
	
	

	/**
	 * SLEプールクライアント取得時処理のプロパティを返す
	 * @@return　@プールクライアント取得処理プロパティを返す.
	 * ↑プールクライアント取得時のタイムアウトを対応するため、2006/10/26 追加
	 */

	private static Properties getPoolClientProperties()
	{
		m_log.entering("getPoolClientProperties");
		
		Properties l_pPoolClient = new Properties();
		
		final int l_iDefaultTimeOut = GlSleConnectorClientFactory.DEFAULT_TIMEOUT;
		final String l_strTimeOutName = "sle.poolclient.timeout";
		

		final String l_strValueTimeout = GtlUtils.getTradingSystem().getPreference(l_strTimeOutName);
		if (l_strValueTimeout == null)
		{
			final String msg =
						"sle pool-client's time-out not found in the SYSTEM_PREFERENCES with  name:"
							+ l_strTimeOutName;
			m_log.warn(msg);
			l_pPoolClient.setProperty(l_strTimeOutName,String.valueOf(l_iDefaultTimeOut));
			m_log.exiting("getPoolClientProperties()");
			return l_pPoolClient;
		}
		
		l_pPoolClient.setProperty(l_strTimeOutName,l_strValueTimeout);
		m_log.exiting("getPoolClientProperties");
		return l_pPoolClient;
	}

	/**
	 * SLEコネクタクライアントに対するソケットサイズを返す
	 * @@return ソケットサイズを返す.
	 */

	private static int getSocketPoolSize()
	{
		m_log.entering("getSocketPoolSize");
		
		final int defaultValue = 2;
		final String name = "socket.pool.size";


		final String l_strValue = GtlUtils.getTradingSystem().getPreference(name);
		if (l_strValue == null)
		{
			final String msg =
						"sle connector pool size not found in the SYSTEM_PREFERENCES with  name:"
							+ name;
			m_log.warn(msg);
			m_log.exiting("getSocketPoolSize()");
			return defaultValue;
		}
		
		m_log.exiting("getSocketPoolSize()");
		return Integer.valueOf(l_strValue).intValue();
	}

	/**
	 * 指定した市場に対応するコネクタクラスタ配列を返す(クライアントポート用)
	 * @@param marketCode
	 *          香港市場の市場コード.
	 * @@return アクティブまたはスタンバイに対応するSleConnectorClusterElementオブジェクトを保持する配列
	 */
	public static SleConnectorClusterElement[] getSleConnectorOrderSubmissionClusterElements(String l_strMarketCode)
	{
		final String[] l_strnames =
		{
			"sleconnector.feq.sleonmc.active.url", 
			"sleconnector.feq.sleonmc.standby.url",
		};//多市場を対応するには、SYSTEM_PREFERENCESの項目Nameを変更 2007/10/24

		return getSleConnectorClusterElements(l_strMarketCode, l_strnames);

	}

	/**
	 * 指定した市場に対応するコネクタクラスタ配列を返す(管理ポート用)
	 * @@param marketCode
	 *          香港市場の市場コード.
	 * @@return アクティブまたはスタンバイに対応するSleConnectorClusterElementオブジェクトを保持する配列
	 */
	public static SleConnectorClusterElement[] getSleConnectorAdminClusterElements(String l_strMarketCode)
	{

		final String[] l_strnames =
		{
			"sleconnector.feq.management.active.url",
			"sleconnector.feq.management.standby.url",
		};

		return getSleConnectorClusterElements(l_strMarketCode, l_strnames);
	}

	/**
	 * 指定した市場に対応するコネクタクラスタ配列を返す。 
	 * ipaddress/port のフォーマットで SLEコネクタ接続情報がSYSTEM_PREFERENCESに保持される
	 * @@param marketCode
	 *          香港市場の市場コード
	 * @@param names
	 *          SYSTEM_PREFERENCESに設定したSLEコネクタインスタンス名
	 * @@return SleConnectorClusterElement[]
	 * 　@　@　@　@ 指定した市場に対応するコネクタクラスタ配列
	 */
	private static SleConnectorClusterElement[] getSleConnectorClusterElements(
		String l_strMarketCode,
		String[] l_strnames)
	{

		m_log.entering("getSleConnectorClusterElements(String,String[])");

		final SleConnectorClusterElement[] urls =
			new SleConnectorClusterElement[l_strnames.length];
		for (int i = 0; i < l_strnames.length; i++) 
		{

			final String url =
				GtlUtils.getTradingSystem().getPreference(l_strnames[i] + "." + l_strMarketCode);
				//多市場を対応するため、SYSTEM_PREFERENCESの項目Nameに市場コードを追加　@2007/10/24
			if (url == null)
			{

				final String msg =
					"sle connector URL not found in the SYSTEM_PREFERENCES with name:"
						+ l_strnames[i] + "." + l_strMarketCode;
				m_log.error(msg);
				throw new IllegalStateException(msg);

			}
			urls[i] = new SleConnectorClusterElement(url);

		}

		m_log.exiting("getSleConnectorClusterElements(String,String[])");
		return urls;
	}

	/**
	 * コネクタクラスタを管理するクラス(インナクラス)
	 */
	public static class SleConnectorClusterElementManager
	{
		
		/**
		 * 市場、市場に対応するコネクタクラスタを管理するマッピング
		 */
		private static Map m_sleConnectorsTable = new HashMap();

		/**
		 * コネクタクラスタを管理するConnectionのIteratorを取得する
		 * @@return Iterator コネクタクラスタを管理するConnectionのIterator
		 */
		public static Iterator getSleConnectorClusterElementsEntrySetIterator()
		{
			return m_sleConnectorsTable.entrySet().iterator();
		}

		/**
		 * xBlock市場コードに対応するコネクタクラスタを取得する
		 * @@param xBlocksMarketCode
		 *          xBlock市場コード
		 * @@return 指定した市場に対応するコネクタクラスタ配列
		 */
		public static SleConnectorClusterElement[] getSleConnectorClusterElements(final String l_strxBlocksMarketCode)
		{

			final String marketCode = getLogicalMarketCode(l_strxBlocksMarketCode);
			return (SleConnectorClusterElement[]) m_sleConnectorsTable.get(marketCode);
		}

		/**
		 * xBlockの市場コードに対応するコネクタクラスタを保持する
		 * @@param xBlocksMarketCode
		 *          xBlock市場コード　@
		 */
		public static void setSleConnectorClusterElements(
			String l_strxBlocksMarketCode,
			SleConnectorClusterElement[] l_clusterElements)
		{

			final String marketCode = getLogicalMarketCode(l_strxBlocksMarketCode);
			m_sleConnectorsTable.put(marketCode, l_clusterElements);

			return;
		}
	}

	/**
	 * コネクタURLデータ(URL,IP,PORTなど)を管理するクラス
	 */
	public static class SleConnectorClusterElement {
		/**
		 * 受付ポートURL
		 */
		public final String m_url;
		
		/**
		 * 受付ポートIP
		 */
		public final String m_address;
		
		/**
		 * 受付ポート番号
		 */
		public final int m_port;
	
		/**
		 * 受付ポートへのSLEコネクタクライアント
		 */
		private GlSleConnectorClient m_glSleConnectorClient = null;
		
		/**
		 * SLEコネクタクライアントのステータスフラグ
		 */
		private Boolean m_glSleConnectorClientStatus = null;
		
		/**
		 * コンストラクタ
		 * @@param url URL
		 * @@param address IP
		 * @@param port ポート番号(int型)
		 */
		public SleConnectorClusterElement(
			String url,
			String address,
			int port)
		{
			this.m_url = url;
			this.m_address = address;
			this.m_port = port;
		}
		
		/**
		 * コンストラクタ
		 * @@param url '/'でIP、ポート番号を切り分けたURL
		 */
		public SleConnectorClusterElement(String url)
		{
			this(url, url.split("/"));
		}
		
		/**
		 * コンストラクタ
		 * @@param url URL
		 * @@param address IP
		 * @@param port ポート番号(String型)
		 */
		private SleConnectorClusterElement(
			String url,
			String address,
			String port)
		{
			this.m_url = url;
			this.m_address = address;
			this.m_port = Integer.parseInt(port);
		}

		private SleConnectorClusterElement(String url, String[] addrElements) {
			this(url, addrElements[0], addrElements[1]);
		}

		public String toString() {
			return m_url + "[addr:" + m_address + "], port[" + m_port + "]";
		}

		/**
		 * コネクタクライアントを取得する
		 * @@return コネクタクライアント
		 */
		public GlSleConnectorClient getGlSleConnectorClient()
		{
			return m_glSleConnectorClient;
		}

		/**
		 * コネクタクライアントをSetterする
		 * @@param コネクタクライアント
		 */
		public void setGlSleConnectorClient(GlSleConnectorClient glSleConnectorClient)
		{
			this.m_glSleConnectorClient = glSleConnectorClient;
		}

		/**
		 * コネクタがtried状態であるかどうかチェックする
		 * @@return tried状態なら、trueを返す,他 falseを返す。
		 */
		public boolean isGlSleConnectorClientConnectionTried()
		{
			return m_glSleConnectorClientStatus != null;
		}

		/**
		 * コネクタがオンラインかどうかチェックする
		 * @@return オンラインなら、trueを返す、他falseを返す。
		 */
		public boolean isGlSleConnectorClientConnectionOnline()
		{
			return isGlSleConnectorClientConnectionTried()
				&& Boolean.TRUE.equals(m_glSleConnectorClientStatus);
		}

		/**
		 * コネクタがオフライン状態であるかどうかチェックする
		 * @@return オフライン状態なら、trueを返す、他 falseを返す。
		 */
		public boolean isGlSleConnectorClientConnectionOffline()
		{
			return isGlSleConnectorClientConnectionTried()
				&& Boolean.FALSE.equals(m_glSleConnectorClientStatus);
		}

		/**
		 * コネクタのステータスをオンラインにする
		 */
		public void setGlSleConnectorClientConnectionStatusToOnline()
		{
			m_glSleConnectorClientStatus = Boolean.TRUE;
		}

		/**
		 * コネクタのステータスをオフラインにする
		 */
		public void setGlSleConnectorClientConnectionStatusToOffline()
		{
			m_glSleConnectorClientStatus = Boolean.FALSE;
		}
	}

}
@
