head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SehkSleRepliesCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SehkSleRepliesCallbackクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/2  李(FLJ) 新規作成
                    2006/05/18 孫(FLJ) 変更
                    2006/09/04 孫(FLJ) 市場状態に関する処理を削除
 */
package webbroker3.slegateway.callback;

import com.fitechlabs.xbconnector.sleconnector.SleRepliesCallback;
import com.fitechlabs.xbconnector.glbase.gldata.*;
import com.fitechlabs.xbconnector.sleconnector.GlSleCallbackException;
import com.fitechlabs.xbconnector.sleconnector.SleResponseIdentifier;
import java.math.BigDecimal;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;
import java.sql.Timestamp;
import org.apache.log4j.Category ;

/**
 * SLE コネクタ受信コールバック処理を実行するクラス。
 * 
 * @@author      孫（FLJ）
 * @@version     V1.0  
 */
public class WEB3SehkSleRepliesCallback implements SleRepliesCallback
{

    /**
     * DBアクセス用ツール
     */
    private WEB3CallBackDataAccessUtil m_dao;
    
	/**
	 * SYSTEM_PREFERENCES設定を保持するAdaptor
	 * ↑ 2006/12/6 追加
	 */
	private WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor;
    
	/**
	 * BizDateなどの情報を変換するためのBizDateProvider
	 * ↑ 2006/12/6 追加
	 */
	private WEB3CallbackBizDateProviderUtils m_DateProvider;

    /**
     * ログ出力オブジェクト
     */
	private static final Category m_log =  Category.getInstance(WEB3SehkSleRepliesCallback.class);
	private static  final boolean DBG   = m_log.isDebugEnabled();
	
    /**
     * インスタンスごとでGLID固定値
     * 起動時で、Configの設定によって設定する
     */
//   private final String m_glid = WEB3SleCallbackConstantsDef.Markets.SEHK.GLID;
	private String m_glid = null;
	
    /** 日付変換のフォーマット*/
    public static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";
    
    /** 電文の項目日付変換のフォーマット*/
    public static final String GL_YYYYMMDD_DATE_FORMAT = "yyyyMMddHHmmss";    

    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.url */
    public static final String CONFIG_PARAM_NAME_JDBC_URL = "jdbc.url";
    
    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.driver */
    public static final String CONFIG_PARAM_NAME_JDBC_DRIVER = "jdbc.driver";
    
    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.user */
    public static final String CONFIG_PARAM_NAME_JDBC_USER = "jdbc.user";
    
    /** CONFIGファ@イルで設定されたパラメータの名： jdbc.password */
    public static final String CONFIG_PARAM_NAME_JDBC_PASSWORD = "jdbc.password";
    
    private static final Integer ORACLE_TYPES_VARCHAR = new Integer(java.sql.Types.VARCHAR);
    private static final Integer ORACLE_TYPES_NUMERIC = new Integer(java.sql.Types.NUMERIC);
    private static final Integer ORACLE_TYPES_DATE = new Integer(java.sql.Types.DATE);
	private static final Integer ORACLE_TYPES_TIMESTAMP = new Integer(java.sql.Types.TIMESTAMP);   
    
    /** 売買区分：買付 */
    private static final String BUY_SIDE_DIV = "1";
    /** 売買区分：売付 */
    private static final String SELL_SIDE_DIV = "2";
    
    /** 市場コードの設定TAG */
	private static final String CONFIG_CALLBACK_GL_ID = "callback.glid"; 
    
    /**
     * SLE コネクタ受信コールバックオブジェクトを作成
     * 
     * @@param l_hmParams　@configファ@イルから取得した設定内容
     */
    public WEB3SehkSleRepliesCallback(Map l_hmParams)
    {

        m_log.info("||||| Starting replies callback ");

        final String l_strJdbcDriver = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_DRIVER);
        final String l_strJdbcUrl = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_URL);
        final String l_strJdbcUser = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_USER);
        final String l_strJdbcPassword = (String) l_hmParams.get(CONFIG_PARAM_NAME_JDBC_PASSWORD);
        
        //GLIDの設定
		m_glid = (String) l_hmParams.get(CONFIG_CALLBACK_GL_ID);
		m_log.info("the sle connect callback being starting as glid:" + m_glid);
		if (m_glid == null)
		{
			  final String errMsg = "Config param:[" + CONFIG_CALLBACK_GL_ID
					  + "] is not defined correctly in the SLE Connector configuration file."
					  ;
			  m_log.error(errMsg);
			  throw new RuntimeException(errMsg);
		}

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
        	//SYSTEM_PREFERENCES adaptorインスタンスを生成 2006/12/6 追加
			m_adaptor = WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao);
			//BizDateProviderインスタンスを生成 2006/12/7 追加
			m_DateProvider = WEB3CallbackBizDateProviderUtils.getInstance(m_adaptor);
        }
        catch(Exception e)
        {
			m_log.error("Error while initializing system preferences adaptor or date provider.");
			throw new RuntimeException("Error while initializing adaptor or date provider. "+e);   
        }
    }

    /**
     * SLEサーバーの返答を処理する。このコールバックの入り口です。
     * 
     * @@param l_glData　@SLE電文オブジェクト
     * @@param l_bytMsgArr　@メッセージ
     * @@param l_posDupFlag　@このデータがすでに処理されるかどうか
     * @@throws GlSleCallbackException
     *             TBD:
     */
    public void process(GlData l_glData, byte[] l_bytMsgArr, boolean l_posDupFlag) throws GlSleCallbackException
    {

        m_log.info("||||| Starting replies callback processing ");//コーティング改良　@追加⇒2006/10/18
        
		//SYSTEM_PREFERENCESの設定をm_adaptorのプロパティにインストールする
		//↑追加 2006/12/6
		//SYSTEM_PREFERENCES adaptorインスタンスを生成 2006/12/6 追加
		if ( m_adaptor == null )
		{
			m_adaptor = WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao);
			m_log.debug("loaded system_preferences properties is :" + m_adaptor.getProperties());
		}
		
		//BizDateProviderインスタンスを生成 2006/12/7 追加
		if ( m_DateProvider == null)
		{
			m_DateProvider = WEB3CallbackBizDateProviderUtils.getInstance(m_adaptor);
			m_log.debug("the system current time set based by the properties:" + m_DateProvider.getAdaptor().getProperties());
		}

		final String l_strDataName = l_glData.getName();
				    
//		if(!l_strDataName.startsWith("2008")) m_log.info("Received the following response at SLE.callback:" + l_glData);//コーティング改良 追加⇒2006/10/18
//		//↑2008レスポンス電文ログ出力回避 2006/10/26追加
//
//
//        // check for ignorable messages
//        if (l_strDataName.startsWith("2004"))
//        {
//
//            // ignore all heartbeats and 2004 (order book consultation requests)
//            return;
//        }
		
		// 受信したデータをログへ出力
		if(l_strDataName.startsWith("5200")) {	// heartbeat
			m_log.info("Heartbeat received. Gl Data Name:" + l_strDataName);
		}else {
			m_log.info("Received data with posDupFlag: " + l_posDupFlag + ", Data:"  + l_glData );
		}
		

		// 5200,2004レスポンスを無視
		if(l_strDataName.startsWith("5200") || l_strDataName.startsWith("2004")) {
			return;
		}

        if (l_strDataName.startsWith("2019") || l_strDataName.startsWith("2008")) //⇒リカバリー処理ため2008に対するコールバック処理を追加 2006/11/8
        {	
        	if ( l_glData.getString("replies_type").equals("J")){//'J'型の電文をコールバック処理回避するため、処理追加 2006/11/15
        		m_log.info("the message's replies_type is  'J' and hence ignoring.Replies_number,index=" 
							+ l_glData.getBigDecimal("number_of_replies")
							+ ", "
							+ l_glData.getBigDecimal("index"));
        		return;
        	}
            //電文によって、処理されたデータを保存し、データベースにインサートするときに利用
            Map l_hmAdditionData = new HashMap();
            
            try{
            //同じ‘ｒeplies_number’かつ同じ‘replies_index’
            // を持つRCVD_Qレスポンスメッセージが既に存在するかチェックする。
            //既に存在する場合、受信レスポンスコールバック処理を行わなく、ログに電文情報の内容を出力する。
            if (l_posDupFlag && isAlreadyExists(l_glData))
            {
                m_log.info("posDupFlag is true, but the message is already found and hence ignoring. Replies_number, index="
                                + l_glData.getBigDecimal("number_of_replies")
                                + ", "
                                + l_glData.getBigDecimal("index")
                                + ",data contents=" + l_glData.toString());
                return;
            }

            //アカウントID、オペレータタイプについて、対応する上りキューSEND_Qの注文メッセージから取得する。
            //失敗するとプロセスを中止
            if (!WEB3BaseSleRepliesCallback.getSendQMessage(m_dao, l_glData, l_hmAdditionData))
            {
                m_log.warn("Can not found send_q message corresponds to the rvcd_q message! send queue_id="+l_glData.getString("memo"));
                return;
            }
            
            //対応する注文単位テーブルの運用コードを取得する。
            //①@対応する注文単位テーブルの運用コード、証券会社コード、部店コード、識別コードを取得する。
            WEB3BaseSleRepliesCallback.getOrderUnitFields(m_dao,l_hmAdditionData.get("internal_ref").toString(), l_hmAdditionData);//DB仕様変更に従って変更(2006/10/3)
            if (l_hmAdditionData.get("order_emp_code")==null || l_hmAdditionData.get("institution_code") == null || l_hmAdditionData.get("branch_code") == null || l_hmAdditionData.get("order_request_number") == null)
            {
                m_log.warn("Can not found some information of the order unit that corresponds to this rvcd_q message! set null. rvcd internal_reference = " + l_hmAdditionData.get("internal_ref").toString());
            }
            
            //市場レスポンスから‘受付区分’、‘処理区分’、‘経路区分’を設定し、処理内容を判断する。
            final int l_intOprate = WEB3BaseSleRepliesCallback.getOrderStatusProcByGlData(m_dao, l_glData, l_hmAdditionData);
            //処理内容によって、実行
            switch (l_intOprate)
            {
            case WEB3BaseSleRepliesCallback.OP_ORDER_ACCEPTED:
                doNewOrderAcceptedOpretion(l_glData, l_hmAdditionData);
                break;

            case WEB3BaseSleRepliesCallback.OP_CHANGE_ACCEPTED:
                doChangeOrderAcceptedOpretion(l_glData, l_hmAdditionData);
                break;

            default:
                doDefaultOpretion(l_glData, l_hmAdditionData);
                break;
            }
            }
            catch(RuntimeException re){
            	m_log.error("there is a runtime exception happened!",re);//add 2006/10/16
            	
            }
            catch(SQLException e)
            {   
                try
                {
                    m_dao.releaseResource(true);
                }
                catch(SQLException sqle)
                {
                    m_log.error("DB Error while closing ResultSet,Statement or Connection.");
                }
                throw new GlSleCallbackException("SQLException Occur :", e);
            }

        }

    }


    /**
     * すべてのリソースを釈放する
     */
    public void close()
    {

        if (m_dao != null)
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

        m_log.info("|||| Closing resources done ....");
    }

    /**
     * 最新の応答の標識を取得。
     * 
     * @@return 応答のindex
     */
    public SleResponseIdentifier getLastReponseIdentifier()
    {
        SleResponseIdentifier l_sleResponseIdentifier = null;

        final String l_strSql = "select max(replies_index) as LASTINDEX from sle_rcvd_q where gl_id ='" + m_glid
                + "'  and to_char(created_timestamp,'YYYYMMDD') >= ?";

        List l_lisParams = new ArrayList(1);
		
		final String today = new SimpleDateFormat(YYYYMMDD_DATE_FORMAT).format(new java.util.Date()).toString() ;
		//unix環境に発生したタイムアウトエラーを対応するためにはStringへ変換 2006/11/8
        l_lisParams.add(today);

        List l_lisQueryResult = new ArrayList(0);
        try
        {
            l_lisQueryResult = m_dao.executeQuery(l_strSql, l_lisParams);
        }
        catch (SQLException e)
        {
            m_log.error("SQLException happend when Gets identifier of the last response :" + e);
        }
        finally{
            try
            {
                m_dao.releaseResource(false);
            }
            catch(SQLException sqle)
            {
                m_log.error("DB Error while closing ResultSet,Statement or Connection.");
            }
        }
        int l_intLastIndex = 0;

		BigDecimal l_big = (BigDecimal)((HashMap)l_lisQueryResult.get(0)).get("LASTINDEX");
        if (l_lisQueryResult.size() > 0 && l_big !=null)//l_lisQueryResult.size() > 0 の条件を追加 2006/10/12
        {
            l_intLastIndex = l_big.intValue();
        }
        
		if ( l_intLastIndex != 0){//⇒　@下りキューにレコードが存在する場合のみ、Response Identifierを設定する 追加 2006/10/23
        	l_sleResponseIdentifier = new SleResponseIdentifier(l_intLastIndex, 1);
		}
        return l_sleResponseIdentifier;
    }

    /**
     * デフォルト処理
     * 
     * @@param l_glData
     *            SLE電文データオブジェクト
     * @@param l_hmAdditionData 電文によって、処理されたデータを保存し、データベースにインサートするときに利用
     * @@return コールバック処理が成功時:trueを返す 他:false返す
     */
    protected void doDefaultOpretion(GlData l_glData, Map l_hmAdditionData) throws SQLException
    {
		m_log.info("||||| Starting doDefaultOperation !!");
        try
        {
            m_dao.setAutoCommit(false);

            //RCVD_Qインサートする
            doRcvdqInsert(l_glData, l_hmAdditionData);
            //対応する上りキューSEND_Qの注文メッセージの‘受信確認フラグ’を’確認済’に更新する。
            Integer rcvdconfirmed = (Integer)l_hmAdditionData.get("rcvd_confirmed");
            if ( rcvdconfirmed.equals(WEB3BaseSleRepliesCallback.SENDQ_CONFIRMED_BY_RCVD)){//仕様変更ため(2006/10/3)追加
				doUpdateSendqConfirmFlag(l_glData);
            }

            m_dao.commit();
            m_dao.setAutoCommit(true);
        }
        catch (SQLException e)
        {
            m_log.debug("SQLException Catched! when doDefaultOpretion() :" + e);
            try
            {
                m_dao.rollback();
            }
            catch (SQLException e1)
            {
            }
            throw e;
        }
        finally
        {
            m_dao.releaseResource(false);
        }
    }

    /**
     * 注文受付済処理
     * 
     * @@param l_glData
     *            SLE電文データオブジェクト
     * @@param l_hmAdditionData　@電文によって、処理されたデータを保存し、データベースにインサートするときに利用
     * @@return コールバック処理が成功時:trueを返す 他:false返す
     */
    protected void doNewOrderAcceptedOpretion(GlData l_glData, Map l_hmAdditionData) throws SQLException
    {
		m_log.info("||||| Starting doNewOrderAcceptedOpration !!");
        try
        {
            m_dao.setAutoCommit(false);

            //RCVD_Qインサートする
            doRcvdqInsert(l_glData, l_hmAdditionData);

            //対応する上りキューSEND_Qの注文メッセージの‘受信確認フラグ’を’確認済’に更新する。
            //doUpdateSendqConfirmFlag(l_glData);
			Integer rcvdconfirmed = (Integer)l_hmAdditionData.get("rcvd_confirmed");
			if ( rcvdconfirmed.equals(WEB3BaseSleRepliesCallback.SENDQ_CONFIRMED_BY_RCVD)){//仕様変更ため(2006/10/3)追加
				doUpdateSendqConfirmFlag(l_glData);
			}

            //①@‘exchange
            //_reference’が ‘NULL’であるかチェッする。
            //‘exchange_reference’ が ‘NULL’である場合、インサート処理を行わない。
            final String l_strExchangeReference = l_glData.getString("exchange_reference");
            if (l_strExchangeReference != null)
            {
                //②’internal_reference’に対応する’注文単位ID’を取得する。
                //final BigDecimal l_bdOrderUnitId = (BigDecimal) WEB3BaseSleRepliesCallback.getOrderUnitId(m_dao, l_glData.getString("internal_reference"));
				final BigDecimal l_bdOrderUnitId = (BigDecimal) WEB3BaseSleRepliesCallback.getOrderUnitId(m_dao, l_hmAdditionData.get("internal_ref").toString());//DB仕様変更のため、2006/10/6
                if (l_bdOrderUnitId != null)
                {
                    //③ ②で取得した‘注文単位ID’に対応する市場取引番号情報が既に存在するかチェッする。
                    //対応する市場取引番号情報が既に存在する場合、インサート処理を行わない。
//                    String l_strSql = "select xblocks_product_type from SLE_EXCHANGE_ORDER_KEY_MNG where order_unit_id = ? and xblocks_product_type = ?";
					String l_strSql = "select xblocks_product_type from SLE_EXCHANGE_ORDER_KEY_MNG where xblocks_product_type = ? and order_unit_id = ?";
                    List l_lisParams = new ArrayList(1);
					l_lisParams.add(WEB3BaseSleRepliesCallback.PRODUCT_TYPE);
                    l_lisParams.add(l_bdOrderUnitId);
                    final int count = m_dao.executeCount(l_strSql, l_lisParams);
                    if (count == 0)
                    {
                        //④銘柄タイプ(‘外国株式’の固定値を指定する)、注文単位ID、’exchange_reference’を市場取引番号管理テーブルにインサートする。
                        l_strSql = "insert into SLE_EXCHANGE_ORDER_KEY_MNG (xblocks_product_type,order_unit_id,exchange_order_key,created_timestamp,last_updated_timestamp) values (?,?,?,SYSDATE,SYSDATE)";
                        l_lisParams = new ArrayList(3);
                        l_lisParams.add(WEB3BaseSleRepliesCallback.PRODUCT_TYPE);
                        l_lisParams.add(l_bdOrderUnitId);
                        l_lisParams.add(l_strExchangeReference);
						//l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));//追加 2006/12/7
						//l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));//追加 2006/12/7
                        m_dao.executeUpdate(l_strSql, l_lisParams);
                    }
                    else
                    {
                        m_log.debug("There is a record with the same xblocks_product_type and order_unit_id in SLE_EXCHANGE_ORDER_KEY_MNG, "
                                        + "the operation after insert to rcvd_q will not be excuted.(doNewOrderAcceptedOpretion)");
                    }

                }
                else
                {
                    m_log.debug("orderUnitId is null ! the operation after insert to rcvd_q will not be excuted.(doNewOrderAcceptedOpretion)");
                }

            }
            else
            {
                m_log.debug("exchange_reference is null ! the operation after insert to rcvd_q will not be excuted.(doNewOrderAcceptedOpretion).");
            }

            m_dao.commit();
            m_dao.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            m_log.debug("Exception Catched! when doNewOrderAcceptedOpretion() ! RollBack:" + e);
            try
            {
                m_dao.rollback();
            }
            catch (SQLException ex)
            {
            }
            throw e;
        }
        finally
        {
            m_dao.releaseResource(false);
        }

    }

    /**
     * 訂正済処理
     * 
     * @@param l_glData
     *            SLE電文データオブジェクト
     * @@param l_hmAdditionData　@電文によって、処理されたデータを保存し、データベースにインサートするときに利用
     */
    protected void doChangeOrderAcceptedOpretion(GlData l_glData, Map l_hmAdditionData) throws SQLException
    {
		m_log.info("||||| Starting doChangeOrderAcceptedOpration !!");
        try
        {
            m_dao.setAutoCommit(false);

            //RCVD_Qインサートする
            doRcvdqInsert(l_glData, l_hmAdditionData);
            

            //対応する上りキューSEND_Qの注文メッセージの‘受信確認フラグ’を’確認済’に更新する。
            //doUpdateSendqConfirmFlag(l_glData);
			Integer rcvdconfirmed = (Integer)l_hmAdditionData.get("rcvd_confirmed");
			if ( rcvdconfirmed.equals(WEB3BaseSleRepliesCallback.SENDQ_CONFIRMED_BY_RCVD)){//仕様変更ため(2006/10/3)追加
				doUpdateSendqConfirmFlag(l_glData);
			}
			
            m_dao.commit();
            m_dao.setAutoCommit(true);

        }
        catch (SQLException e)
        {
            m_log.debug("Exception Catched! when doChangeOrderAcceptedOpretion() ! RollBack:" + e);
            try
            {
                m_dao.rollback();
            }
            catch (SQLException ex)
            {
            }
            throw e;
        }
        finally
        {
            m_dao.releaseResource(false);
        }
    }

    
    /**
     * 同じ‘ｒeplies_number’かつ同じ‘replies_index’ を持つRCVD_Qレスポンスメッセージが既に存在するかチェックする。
     * 
     * @@param l_glData SLE電文オブジェクト
     * @@return true ある場合：trueを返す　@ない場合：falseを返す
     */
    private boolean isAlreadyExists(GlData l_glData) throws SQLException,RuntimeException
    {
        boolean l_blnResult = false;

        if (l_glData.getBigDecimal("number_of_replies") != null && l_glData.getBigDecimal("index") != null)
        {
            final String sql = "select replies_index from sle_rcvd_q where gl_id ='" + m_glid
                    + "' and replies_number=? and replies_index=? and to_char(created_timestamp,'YYYYMMDD')=?";

            List params = new ArrayList(2);
            
            if (l_glData.getBigDecimal("number_of_replies") == null){//←2006/10/16 追加
            	throw new RuntimeException("the 'number_of_replies' field of response data is null");
            }
            params.add(l_glData.getBigDecimal("number_of_replies"));
			
			if (l_glData.getBigDecimal("index") == null){//←2006/10/16 追加
				throw new RuntimeException("the 'index' field of response data is null");
			}
			            
            params.add(l_glData.getBigDecimal("index"));
            final String today = new SimpleDateFormat(YYYYMMDD_DATE_FORMAT).format(new java.util.Date()).toString();
            //unix 環境に発生したタイムアウトエラーを対応するためにstringへ変換 2006/11/8
            params.add(today);

            try
            {
                final int l_intCount = m_dao.executeCount(sql, params);
                if (l_intCount > 0)
                {
                    l_blnResult = true;
                }
            }
            catch (SQLException e)
            {
                m_log.error("SQLException when Checks whether we are already received the message :" + e);
                throw e;
            }
            finally
            {
                m_dao.releaseResource(false);
            }
        }
        return l_blnResult;

    }    
    

    /**
     * RCVD_Qインサート処理
     * 
     * @@param l_glData
     *            SLE電文データオブジェクト
     * @@param l_hmAdditionData
     * @@return インサート処理が成功時:trueを返す 他:false返す
     */
    private boolean doRcvdqInsert(GlData l_glData, Map l_hmAdditionData) throws SQLException
    {
        boolean l_blnResult = false;
        

        final String l_strSqlRcvdQInsert = "INSERT INTO SLE_RCVD_Q ("
        + "queue_id,"    
        + "xblocks_product_type,"
        + "replies_type,"
        + "replies_number,"
        + "replies_index,"
        + "sub_status,"
        + "internal_ref,"
        + "exchange_no,"
        + "trade_number,"
        + "gl_id,"
        + "stock_code,"
        + "side,"
        + "modality,"
        + "price,"
        + "quantity,"
        + "trading_phase,"
        + "exec_price,"
        + "avg_price,"
        + "exec_qty,"
        + "remaining_qty,"
        + "number_of_trades,"
        + "order_time,"
        + "trade_booking_time,"
        + "exec_timestamp,"
        + "order_emp_code,"
        + "execution_date,"
        + "f_delivery_date,"
        + "fx_rate,"
        + "exec_serial_no,"
        + "route_div,"
        + "ack_type,"
        + "ackd_command,"
        + "old_qty,"
        + "old_price,"
      	+ "cancelled_qty,"
        + "trigger_param,"
        + "reject_cmd_type,"
        + "reject_code,"
        + "reject_time,"
        + "exchange_msg_type,"
        + "exchange_msg_code,"
        + "user_number,"
        + "memo,"
        + "account_id,"
        + "op_type,"
        + "accept_div,"
        + "institution_code,"
        + "branch_code,"
        + "order_request_number,"
        + "elimination_message,"
        //↑DBレイアウト更新ため追加 2006/11/10
        + "last_updater,"
        + "status,"
		+ "created_timestamp,"
        + "last_updated_timestamp)"
        + " VALUES (SLE_RCVD_Q_QUEUE_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";        

        Timestamp l_timestamp = null;
        if(l_glData.getString("trade_time")!=null)
        {
            Date l_date;
            try
            {
                l_date = new SimpleDateFormat(GL_YYYYMMDD_DATE_FORMAT).parse(l_glData.getString("trade_time"));
                l_timestamp = new Timestamp(l_date.getTime());
            }
            catch (ParseException e)
            {
               ;
            }            
        }       
        
        final Object[][] l_aryValues = {
                {WEB3BaseSleRepliesCallback.PRODUCT_TYPE,            null},
                { l_glData.getString("replies_type"), ORACLE_TYPES_VARCHAR },
                { l_glData.getBigDecimal("number_of_replies"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("index"), ORACLE_TYPES_NUMERIC },
                { l_glData.getString("sub_status"), ORACLE_TYPES_VARCHAR },                
                //{ l_glData.getString("internal_reference"), ORACLE_TYPES_VARCHAR },//仕様変更ため変更(2006/10/3)
				{ l_hmAdditionData.get("internal_ref"), ORACLE_TYPES_VARCHAR },
                { l_glData.getString("exchange_reference"), ORACLE_TYPES_VARCHAR },
                { l_glData.getString("trade_number"),    ORACLE_TYPES_VARCHAR },
                { m_glid                           , ORACLE_TYPES_VARCHAR },
                { l_glData.getString("stock_code"), ORACLE_TYPES_VARCHAR },
                //{ l_glData.getBigDecimal("side"), ORACLE_TYPES_NUMERIC },
				{ getSideDiv(l_glData), ORACLE_TYPES_NUMERIC },//DB仕様変更に従って変更(2006/10/2)
                { "L",                        ORACLE_TYPES_VARCHAR },
                { l_glData.getBigDecimal("price"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("quantity"), ORACLE_TYPES_NUMERIC },
                { l_glData.getString("market_trading_status"), ORACLE_TYPES_VARCHAR },
                { l_glData.getBigDecimal("execution_price"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("average_price"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("executed_quantity"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("remaining_quantity"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("number_of_executions"), ORACLE_TYPES_NUMERIC },
                { l_glData.getString("sle_reception_time"), ORACLE_TYPES_VARCHAR },
                { l_glData.getBigDecimal("order_reception_time"), ORACLE_TYPES_NUMERIC },//←NUMへ変更 2006/10/15
                { l_timestamp                                       , ORACLE_TYPES_DATE },
                { l_hmAdditionData.get("order_emp_code")         , ORACLE_TYPES_VARCHAR },
                { null         , ORACLE_TYPES_DATE },
                { null         , ORACLE_TYPES_DATE },
                { null         , ORACLE_TYPES_NUMERIC },
                { null         , ORACLE_TYPES_VARCHAR },
                { l_hmAdditionData.get("route_div")              , ORACLE_TYPES_VARCHAR },                
                { l_glData.getBigDecimal("acknowledgement_type"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("type_of_acknowledged_command"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("previous_quantity"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("previous_price"), ORACLE_TYPES_NUMERIC },
                { l_glData.getBigDecimal("cancelled_qty"), ORACLE_TYPES_NUMERIC }, //⇒電文仕様変更ため、削除(2006/10/13)
                { l_glData.getString("trigger_param"), ORACLE_TYPES_VARCHAR },
                { l_glData.getBigDecimal("type_of_rejected_command"), ORACLE_TYPES_NUMERIC },
                { l_glData.getString("reject_code"), ORACLE_TYPES_VARCHAR },//←CHARへ修正 (2006/10/12)               
                { l_glData.getBigDecimal("reject_time"), ORACLE_TYPES_NUMERIC },//←NUMへ修正 (2006/10/12) ←CHARへ修正(2006/10/13)
                { l_glData.getString("exchange_message_type"), ORACLE_TYPES_VARCHAR },
                { l_glData.getString("exchange_message_code"), ORACLE_TYPES_VARCHAR },
                { l_glData.getBigDecimal("user_number"),       ORACLE_TYPES_NUMERIC },
                { l_glData.getString("memo"),           ORACLE_TYPES_VARCHAR},                
                { l_hmAdditionData.get("account_id"),       ORACLE_TYPES_NUMERIC },
                { l_hmAdditionData.get("op_type"),       ORACLE_TYPES_NUMERIC },
                { l_hmAdditionData.get("accept_div"),           ORACLE_TYPES_VARCHAR},
                { l_hmAdditionData.get("institution_code"),     ORACLE_TYPES_VARCHAR},
                { l_hmAdditionData.get("branch_code"),          ORACLE_TYPES_VARCHAR},
                { l_hmAdditionData.get("order_request_number"), ORACLE_TYPES_VARCHAR},
				{ l_glData.getString("elimination_message"), ORACLE_TYPES_VARCHAR},
                {"SLE"                          ,           ORACLE_TYPES_VARCHAR},
                { l_hmAdditionData.get("status"),       ORACLE_TYPES_NUMERIC },
                { new Timestamp(m_DateProvider.getCurrentTimeMillis()) ,ORACLE_TYPES_TIMESTAMP},//追加 2006/12/7
			    { new Timestamp(m_DateProvider.getCurrentTimeMillis()) ,ORACLE_TYPES_TIMESTAMP} //追加 2006/12/7
            };        
        
        ArrayList l_lisParams = new ArrayList(53);//ArrayListサイズ変更 2006/12/7

        for(int i = 0; i< l_aryValues.length; i++)
        {
            l_lisParams.add(l_aryValues[i]);
        }
        
        int cnt = m_dao.executeUpdate(l_strSqlRcvdQInsert,l_lisParams);
        
        if(cnt >0)
        {
            l_blnResult = true;
        }
            
        return l_blnResult;
    }


    /**
     * データ対応する上りキューSEND_Qの注文メッセージの‘受信確認フラグ’を’確認済’に更新する。
     * @@param l_glData SLE電文データオブジェクト
     */
    private void doUpdateSendqConfirmFlag(GlData l_glData) throws SQLException
    {

            final String l_strSql = "update SLE_SEND_Q set confirmed_by_sle_rcvd_q = ?,last_updated_timestamp = ?  where queue_id =?";
            List l_lisParams = new ArrayList(2);
            l_lisParams.add(new Boolean(true));
            l_lisParams.add(new Timestamp(m_DateProvider.getCurrentTimeMillis()));//追加 2006/12/7
            l_lisParams.add(l_glData.getString("memo"));            
            
            m_dao.executeUpdate(l_strSql, l_lisParams);

    }
    
	/**
	 * 2019電文の'side'項目から売買区分を取得する。
	 * @@param l_glData SLE電文データオブジェクト
	 * DBレイアウト仕様変更に従って追加(2006/10/2)
	 */
	private BigDecimal getSideDiv(GlData l_glData) throws SQLException
	{

		
			BigDecimal bSide = l_glData.getBigDecimal("side");
			
			if (l_glData.getBigDecimal("side") == null) return null;//処理ロジックを厳密にするため　@2006/10/17 追加
			
			if ( bSide.equals( new BigDecimal(0)))//買区分
			{
				return new BigDecimal(BUY_SIDE_DIV);
			}else{
				return new BigDecimal(SELL_SIDE_DIV);
			}
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
	
	
    
}

@
