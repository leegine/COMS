head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleConnectionCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SehkSleConnectionCallbackクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/18 孫(FLJ) 新規作成
 */
package webbroker3.slegateway.callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.sql.Timestamp;
import org.apache.log4j.Category;
import webbroker3.slegateway.define.WEB3SleConnCallbackConstantsDef;
import com.fitechlabs.xbconnector.sleconnector.SleConnectionCallback;

/**
 * SLE コネクタの接続状態をコールバックし、接続状態テーブルに更新するクラス。
 *
 * @@author      孫（FLJ）
 * @@version     V1.0
 */
public class WEB3SleConnectionCallback implements SleConnectionCallback
{
    /**
     * DBアクセス用ツール
     */
    public static WEB3CallBackDataAccessUtil m_dao;

    /**
     * SYSTEM_PREFERENCES設定を保持するAdaptor
     * ↑ 2006/12/6 追加
     */
    private WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor;

    /**
     * BizDateなどの情報を変換するためのBizDateProvider
     * ↑ 2006/12/7 追加
     */
	private WEB3CallbackBizDateProviderUtils m_DateProvider;

    /** 日付変換のフォーマット*/
    public static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";

    private final String[] m_handlingXblocksMarketCodes;

    //複数の市場コードを指定できますが、SLEの場合は一つしか使いません
    private final String m_commaSeparatedListOfHandlingMarketCodes;

    /**
     * ログ出力オブジェクト
     */
	private static final Category m_log =  Category.getInstance(WEB3SleConnectionCallback.class);


    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.url */
    public static final String CONFIG_PARAM_NAME_JDBC_URL = "jdbc.url";

    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.driver */
    public static final String CONFIG_PARAM_NAME_JDBC_DRIVER = "jdbc.driver";

    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.user */
    public static final String CONFIG_PARAM_NAME_JDBC_USER = "jdbc.user";

    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.password */
    public static final String CONFIG_PARAM_NAME_JDBC_PASSWORD = "jdbc.password";
    /** CONFIGファ@イルで設定されたパラメータの名： handling.xblocks.marketcodes  市場コード設定名 */
    public static final String CONFIG_PARAM_NAME_HANDLING_MARKET_CODES = "handling.xblocks.marketcodes";


	/** CONFIGファ@イルで設定されたパラメータ名: activeサーバか、StandByサーバか表すフラグ　@*/
	public static final String CONFIG_PARAM_NAME_FLAG_ACTIV_SERVER = "active.server.flag";
	/** Activeサーバの設定値 */
	public static final String CONFIG_PARAM_VALUE_ACTIV_SERVER = "A";

	/** Active コネクタサーバか、Standby コネクタサーバか を表すフラグ*/
	private int FLAG_CONN_DIV;

	/** Propertiesアダプタ */
	private static Properties properties = null;

    /**
     * 接続状態コールバックオブジェクトを作る
     *
     * @@param l_hmParams　@configファ@イルから取得した設定内容
     */
    public WEB3SleConnectionCallback(Map l_hmParams)
    {

        m_log.info("||||| Starting DbLoaderSleConnectionCallback ");

        final String l_strJdbcDriver = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_DRIVER);
        final String l_strJdbcUrl = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_URL);
        final String l_strJdbcUser = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_USER);
        final String l_strJdbcPassword = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_PASSWORD);

        if (l_strJdbcUrl == null || l_strJdbcUser == null || l_strJdbcPassword == null)
        {

            final String msg = "Config param:[" + CONFIG_PARAM_NAME_JDBC_URL + "] or [" + CONFIG_PARAM_NAME_JDBC_USER
                    + "] or [" + CONFIG_PARAM_NAME_JDBC_PASSWORD + "] not defined in the config file.";
            m_log.error(msg);
            throw new RuntimeException(msg);
        }

        m_log.info("||||| Will use JDBC driver:" + l_strJdbcDriver);
        m_log.info("||||| Will use JDBC url:" + l_strJdbcUrl);

        try
        {
            m_dao = new WEB3CallBackDataAccessUtil(l_strJdbcDriver, l_strJdbcUrl, l_strJdbcUser, l_strJdbcPassword);
        }catch(SQLException e){
            m_log.error("Error while initializing JDBC connection.");
            throw new RuntimeException("Error while initializing JDBC connection. "+e);
        }
        try
        {
        	//アダプタインスタンスを生成 2006/12/7 追加
			m_adaptor = WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao);

			//BizDateProviderインスタンスを生成 2006/12/7 追加
			m_DateProvider = WEB3CallbackBizDateProviderUtils.getInstance(m_adaptor);
        }
        catch(Exception e){
			m_log.error("Error while initializing system preferences adaptor or date provider.");
			throw new RuntimeException("Error while initializing adaptor or date provider. "+e);
        }
        m_commaSeparatedListOfHandlingMarketCodes = (String) l_hmParams.get(CONFIG_PARAM_NAME_HANDLING_MARKET_CODES);
        if (m_commaSeparatedListOfHandlingMarketCodes == null)
        {
            final String errMsg = "Config param:[" + CONFIG_PARAM_NAME_HANDLING_MARKET_CODES
                    + "] is not defined in the SLE Connector configuration file."
                    + "Please set it to the correct list(comma separated) of xBlocks market codes.";
            m_log.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        else
        {
            m_log.info("||||| config param:" + CONFIG_PARAM_NAME_HANDLING_MARKET_CODES + " is set to:"
                    + m_commaSeparatedListOfHandlingMarketCodes);
            final String[] tokens = m_commaSeparatedListOfHandlingMarketCodes.split(",");
            m_handlingXblocksMarketCodes = new String[tokens.length];
            for (int i = 0; i < tokens.length; i++)
            {
                m_handlingXblocksMarketCodes[i] = tokens[i];
            }
        }

        //以下は　@Activeコネクタサーバが接続中か、Standbyコネクタサーバが接続中か判断するための処理
        final String l_strActiveServerFlag = (String) l_hmParams.get(CONFIG_PARAM_NAME_FLAG_ACTIV_SERVER );

		if (l_strActiveServerFlag.equals(CONFIG_PARAM_VALUE_ACTIV_SERVER)){
			FLAG_CONN_DIV = WEB3SleConnCallbackConstantsDef.CONN_DIV.ACTIVE_CONN;
		}else{
			FLAG_CONN_DIV = WEB3SleConnCallbackConstantsDef.CONN_DIV.STANDBY_CONN;
		}

    }

    /**
     * 接続が失い時の処理
     *
     * @@see SleConnectionCallback#onConnectionLost(long)
     */
    public void onConnectionLost(long l_lngTime)
    {

        m_log.info(":::: Connection lost notification received. Timestamp:" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.CONNECTION_LOST);
    }

    /**
     * 接続が回復する時の処理
     *
     * @@see SleConnectionCallback#onReconnected(long)
     */
    public void onReconnected(long l_lngTime)
    {

        m_log.info(":::: Connection re-established notification received. Timestamp:" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.RECONNECTED);
    }

    /**
     * SLE Connector が正しく起動すると、このメソッドを実行されます。
     *
     * @@param l_lngTime　@起動時間
     */
    public void onStartConnector(long l_lngTime)
    {

        m_log.info(":::: SLE Connector is started and connection is ready:" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.STARTED);

		//SYSTEM_PREFERENCESの設定をm_adaptorのプロパティにインストールする
		//↑追加 2006/12/6
		//SYSTEM_PREFERENCES adaptorインスタンスを生成 2006/12/6 追加
		if ( m_adaptor == null )
		{
			m_adaptor = WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao);
		}

		//BizDateProviderインスタンスを生成 2006/12/7 追加
		if ( m_DateProvider == null)
		{
			m_DateProvider = WEB3CallbackBizDateProviderUtils.getInstance(m_adaptor);
		}
		m_log.debug("loaded system_preferences properties is :" + m_adaptor.getProperties());
//    	m_log.debug("the system current time set based by the properties:" + m_DateProvider.getAdaptor().getProperties());
		
        for (int i = 0; i < m_handlingXblocksMarketCodes.length; i++)
        {
        	updateGenMultiThreadedProcStatus(m_handlingXblocksMarketCodes[i], "true");//処理順番を調整 2006/12/8
        }
    }

    /**
     * SLE Connector が正しく停止すると、このメソッドを実行されます。
     *
     * @@param l_lngTime
     *            Time of stop.
     */
    public void onStopConnector(long l_lngTime)
    {

        m_log.info(":::: SLE Connector is stopped :" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.STOPPED);
    }

	/**
	 * マルチスレッドフラグをアップデート
	 * ↑sle-gateway送信スレッドを再起動するため追加 (2006/11/07)
	 * SLEコネクタインスタンスの市場コード設定に従って、市場毎のCircuitBrakerフラグを更新できるように改修(2008/02/06)
	 * @@param l_strMarketCode　@市場コード
	 * @@param l_strStatus　@新しいsend_qマルチスレッドフラグ
	 */
    private void updateGenMultiThreadedProcStatus(String l_strMarketCode, String l_strStatus)
   	{
		final String l_strSql = "update system_preferences set value = ?,last_updated_timestamp = ?  where name =?";

		List l_lisParams = new ArrayList(2);
		try{


			l_lisParams.add(l_strStatus);
			l_lisParams.add(new Timestamp( m_DateProvider.getCurrentTimeMillis()));//追加 2006/12/7
			l_lisParams.add("GenMultiThreadedRowQProc.sle_send_q." + l_strMarketCode);

			m_dao.executeUpdate(l_strSql, l_lisParams);
		}
		catch (SQLException sqle)
		{

			try
			{
				m_dao.rollback();
			}
			catch (SQLException e1)
			{
			}
			final String msg = "Exception while inserting into system_preferences for GenMultiThreadedRowQProc.sle_send_q."
				+ l_strMarketCode + ",new_status:"
				+ l_strStatus;
			m_log.error(msg, sqle);
			throw new RuntimeException(msg, sqle);
		}
		finally
		{
			try
			{
				m_dao.releaseResource(true);
			}
			catch(SQLException sqle)
			{
				m_log.error("DB Error while closing ResultSet,Statement or Connection.");
			}
		}
   	}

    /**
     * 新しいステータスをデータベースにインサート
     *
     * @@param l_intNewStatus　@新しいステータス
     */
    private void insertNewStatus(int l_intNewStatus)
    {
        final String l_strSql = "insert into sle_conn_status_changes("
            + "que_id,"
            + "market_code,"
            + "new_status,"
            + "proc_status,"
            + "event_acked_div,"
            + "sle_conn_div,"
            + "created_timestamp,"
            + "last_updated_timestamp"
            + ") values(SLE_CONN_STATUS_CHANGES_QUE_ID.NEXTVAL,?,?,?,?,?,SYSDATE,SYSDATE)";


        List l_lisParams = null;
        try
        {
            m_dao.setAutoCommit(false);

            //複数の市場コードを指定できますが、SLEの場合は一つしか使いませんので
            //この配列の長さは1です。
            for (int i = 0; i < m_handlingXblocksMarketCodes.length; i++)
            {
                l_lisParams = new ArrayList();
                l_lisParams.add(m_handlingXblocksMarketCodes[i]);
                l_lisParams.add(new Integer(l_intNewStatus));
				l_lisParams.add(new Integer(WEB3SleConnCallbackConstantsDef.PROC_STATUS.TODO));
                l_lisParams.add(new Integer(WEB3SleConnCallbackConstantsDef.EVENT_ACK_DIV.UN_ACKED));//初期状態としては'OV未通知'
                l_lisParams.add(new Integer(FLAG_CONN_DIV));//Activeサーバの場合'0';他 '1'
//                long l_lcurrent = m_DateProvider.getCurrentTimeMillis();
//                l_lisParams.add(new Timestamp(l_lcurrent));//追加 2006/12/7
//                l_lisParams.add(new Timestamp(l_lcurrent));//追加 2006/12/7
                m_dao.executeUpdate(l_strSql, l_lisParams);
            }

            m_dao.commit();
            m_dao.setAutoCommit(true);

        }
        catch (SQLException sqle)
        {

            try
            {
                m_dao.rollback();
            }
            catch (SQLException e1)
            {
            }
            final String msg = "Exception while inserting into sle_conn_status_changes for market_code(s),new_status:"
                    + m_commaSeparatedListOfHandlingMarketCodes + ", " + l_intNewStatus;
            m_log.error(msg, sqle);
            throw new RuntimeException(msg, sqle);
        }
        finally
        {
            try
            {
                m_dao.releaseResource(true);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
    }

	/**
	 * DAOアクセサを取得
	 * @@return Callbackに保持するDAOインスタンスを返す
	 */
	public WEB3CallBackDataAccessUtil getDao()
	{
		return this.m_dao;
	}

	/**
	 * Adaptorインスタンスをゲットする
	 * ↑UT用メソッド　@追加 2006/12/7
	 * @@return Callbackに保持するadaptorインスタンスを返す
	 */
	public WEB3CallbackSystemPreferencesAdaptorUtils getAdaptor()
	{
		return this.m_adaptor;
	}

	/**
	 * BizDateProviderインスタンスをゲットする
	 * ↑UT用メソッド　@追加 2006/12/7
	 * @@return Callbackに保持するDateProviderインスタンスを返す
	 */
	public WEB3CallbackBizDateProviderUtils getBizDateProvider()
	{
		return this.m_DateProvider;
	}
	
	/**
	 * MarketCode配列をゲットする
	 * @@return Callbackに保持するDateProviderインスタンスを返す
	 */
	public String[] getMarketCodes()
	{
		return this.m_handlingXblocksMarketCodes;
    
	}


}@
