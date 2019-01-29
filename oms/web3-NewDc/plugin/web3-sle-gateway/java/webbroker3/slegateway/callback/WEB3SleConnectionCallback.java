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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SehkSleConnectionCallback�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/18 ��(FLJ) �V�K�쐬
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
 * SLE �R�l�N�^�̐ڑ���Ԃ��R�[���o�b�N���A�ڑ���ԃe�[�u���ɍX�V����N���X�B
 *
 * @@author      ���iFLJ�j
 * @@version     V1.0
 */
public class WEB3SleConnectionCallback implements SleConnectionCallback
{
    /**
     * DB�A�N�Z�X�p�c�[��
     */
    public static WEB3CallBackDataAccessUtil m_dao;

    /**
     * SYSTEM_PREFERENCES�ݒ��ێ�����Adaptor
     * �� 2006/12/6 �ǉ�
     */
    private WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor;

    /**
     * BizDate�Ȃǂ̏���ϊ����邽�߂�BizDateProvider
     * �� 2006/12/7 �ǉ�
     */
	private WEB3CallbackBizDateProviderUtils m_DateProvider;

    /** ���t�ϊ��̃t�H�[�}�b�g*/
    public static final String YYYYMMDD_DATE_FORMAT = "yyyyMMdd";

    private final String[] m_handlingXblocksMarketCodes;

    //�����̎s��R�[�h���w��ł��܂����ASLE�̏ꍇ�͈�����g���܂���
    private final String m_commaSeparatedListOfHandlingMarketCodes;

    /**
     * ���O�o�̓I�u�W�F�N�g
     */
	private static final Category m_log =  Category.getInstance(WEB3SleConnectionCallback.class);


    /** CONFIG�t�@@�C���Őݒ肳�ꂽ�p�����[�^�̖��F jdbc.url */
    public static final String CONFIG_PARAM_NAME_JDBC_URL = "jdbc.url";

    /** CONFIG�t�@@�C���Őݒ肳�ꂽ�p�����[�^�̖��F jdbc.driver */
    public static final String CONFIG_PARAM_NAME_JDBC_DRIVER = "jdbc.driver";

    /** CONFIG�t�@@�C���Őݒ肳�ꂽ�p�����[�^�̖��F jdbc.user */
    public static final String CONFIG_PARAM_NAME_JDBC_USER = "jdbc.user";

    /** CONFIG�t�@@�C���Őݒ肳�ꂽ�p�����[�^�̖��F jdbc.password */
    public static final String CONFIG_PARAM_NAME_JDBC_PASSWORD = "jdbc.password";
    /** CONFIG�t�@@�C���Őݒ肳�ꂽ�p�����[�^�̖��F handling.xblocks.marketcodes  �s��R�[�h�ݒ薼 */
    public static final String CONFIG_PARAM_NAME_HANDLING_MARKET_CODES = "handling.xblocks.marketcodes";


	/** CONFIG�t�@@�C���Őݒ肳�ꂽ�p�����[�^��: active�T�[�o���AStandBy�T�[�o���\���t���O�@@*/
	public static final String CONFIG_PARAM_NAME_FLAG_ACTIV_SERVER = "active.server.flag";
	/** Active�T�[�o�̐ݒ�l */
	public static final String CONFIG_PARAM_VALUE_ACTIV_SERVER = "A";

	/** Active �R�l�N�^�T�[�o���AStandby �R�l�N�^�T�[�o�� ��\���t���O*/
	private int FLAG_CONN_DIV;

	/** Properties�A�_�v�^ */
	private static Properties properties = null;

    /**
     * �ڑ���ԃR�[���o�b�N�I�u�W�F�N�g�����
     *
     * @@param l_hmParams�@@config�t�@@�C������擾�����ݒ���e
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
        	//�A�_�v�^�C���X�^���X�𐶐� 2006/12/7 �ǉ�
			m_adaptor = WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao);

			//BizDateProvider�C���X�^���X�𐶐� 2006/12/7 �ǉ�
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

        //�ȉ��́@@Active�R�l�N�^�T�[�o���ڑ������AStandby�R�l�N�^�T�[�o���ڑ��������f���邽�߂̏���
        final String l_strActiveServerFlag = (String) l_hmParams.get(CONFIG_PARAM_NAME_FLAG_ACTIV_SERVER );

		if (l_strActiveServerFlag.equals(CONFIG_PARAM_VALUE_ACTIV_SERVER)){
			FLAG_CONN_DIV = WEB3SleConnCallbackConstantsDef.CONN_DIV.ACTIVE_CONN;
		}else{
			FLAG_CONN_DIV = WEB3SleConnCallbackConstantsDef.CONN_DIV.STANDBY_CONN;
		}

    }

    /**
     * �ڑ����������̏���
     *
     * @@see SleConnectionCallback#onConnectionLost(long)
     */
    public void onConnectionLost(long l_lngTime)
    {

        m_log.info(":::: Connection lost notification received. Timestamp:" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.CONNECTION_LOST);
    }

    /**
     * �ڑ����񕜂��鎞�̏���
     *
     * @@see SleConnectionCallback#onReconnected(long)
     */
    public void onReconnected(long l_lngTime)
    {

        m_log.info(":::: Connection re-established notification received. Timestamp:" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.RECONNECTED);
    }

    /**
     * SLE Connector ���������N������ƁA���̃��\�b�h�����s����܂��B
     *
     * @@param l_lngTime�@@�N������
     */
    public void onStartConnector(long l_lngTime)
    {

        m_log.info(":::: SLE Connector is started and connection is ready:" + new java.util.Date(l_lngTime));
        insertNewStatus(WEB3SleConnCallbackConstantsDef.NEW_STATUS.STARTED);

		//SYSTEM_PREFERENCES�̐ݒ��m_adaptor�̃v���p�e�B�ɃC���X�g�[������
		//���ǉ� 2006/12/6
		//SYSTEM_PREFERENCES adaptor�C���X�^���X�𐶐� 2006/12/6 �ǉ�
		if ( m_adaptor == null )
		{
			m_adaptor = WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao);
		}

		//BizDateProvider�C���X�^���X�𐶐� 2006/12/7 �ǉ�
		if ( m_DateProvider == null)
		{
			m_DateProvider = WEB3CallbackBizDateProviderUtils.getInstance(m_adaptor);
		}
		m_log.debug("loaded system_preferences properties is :" + m_adaptor.getProperties());
//    	m_log.debug("the system current time set based by the properties:" + m_DateProvider.getAdaptor().getProperties());
		
        for (int i = 0; i < m_handlingXblocksMarketCodes.length; i++)
        {
        	updateGenMultiThreadedProcStatus(m_handlingXblocksMarketCodes[i], "true");//�������Ԃ𒲐� 2006/12/8
        }
    }

    /**
     * SLE Connector ����������~����ƁA���̃��\�b�h�����s����܂��B
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
	 * �}���`�X���b�h�t���O���A�b�v�f�[�g
	 * ��sle-gateway���M�X���b�h���ċN�����邽�ߒǉ� (2006/11/07)
	 * SLE�R�l�N�^�C���X�^���X�̎s��R�[�h�ݒ�ɏ]���āA�s�ꖈ��CircuitBraker�t���O���X�V�ł���悤�ɉ��C(2008/02/06)
	 * @@param l_strMarketCode�@@�s��R�[�h
	 * @@param l_strStatus�@@�V����send_q�}���`�X���b�h�t���O
	 */
    private void updateGenMultiThreadedProcStatus(String l_strMarketCode, String l_strStatus)
   	{
		final String l_strSql = "update system_preferences set value = ?,last_updated_timestamp = ?  where name =?";

		List l_lisParams = new ArrayList(2);
		try{


			l_lisParams.add(l_strStatus);
			l_lisParams.add(new Timestamp( m_DateProvider.getCurrentTimeMillis()));//�ǉ� 2006/12/7
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
     * �V�����X�e�[�^�X���f�[�^�x�[�X�ɃC���T�[�g
     *
     * @@param l_intNewStatus�@@�V�����X�e�[�^�X
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

            //�����̎s��R�[�h���w��ł��܂����ASLE�̏ꍇ�͈�����g���܂���̂�
            //���̔z��̒�����1�ł��B
            for (int i = 0; i < m_handlingXblocksMarketCodes.length; i++)
            {
                l_lisParams = new ArrayList();
                l_lisParams.add(m_handlingXblocksMarketCodes[i]);
                l_lisParams.add(new Integer(l_intNewStatus));
				l_lisParams.add(new Integer(WEB3SleConnCallbackConstantsDef.PROC_STATUS.TODO));
                l_lisParams.add(new Integer(WEB3SleConnCallbackConstantsDef.EVENT_ACK_DIV.UN_ACKED));//������ԂƂ��Ă�'OV���ʒm'
                l_lisParams.add(new Integer(FLAG_CONN_DIV));//Active�T�[�o�̏ꍇ'0';�� '1'
//                long l_lcurrent = m_DateProvider.getCurrentTimeMillis();
//                l_lisParams.add(new Timestamp(l_lcurrent));//�ǉ� 2006/12/7
//                l_lisParams.add(new Timestamp(l_lcurrent));//�ǉ� 2006/12/7
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
	 * DAO�A�N�Z�T���擾
	 * @@return Callback�ɕێ�����DAO�C���X�^���X��Ԃ�
	 */
	public WEB3CallBackDataAccessUtil getDao()
	{
		return this.m_dao;
	}

	/**
	 * Adaptor�C���X�^���X���Q�b�g����
	 * ��UT�p���\�b�h�@@�ǉ� 2006/12/7
	 * @@return Callback�ɕێ�����adaptor�C���X�^���X��Ԃ�
	 */
	public WEB3CallbackSystemPreferencesAdaptorUtils getAdaptor()
	{
		return this.m_adaptor;
	}

	/**
	 * BizDateProvider�C���X�^���X���Q�b�g����
	 * ��UT�p���\�b�h�@@�ǉ� 2006/12/7
	 * @@return Callback�ɕێ�����DateProvider�C���X�^���X��Ԃ�
	 */
	public WEB3CallbackBizDateProviderUtils getBizDateProvider()
	{
		return this.m_DateProvider;
	}
	
	/**
	 * MarketCode�z����Q�b�g����
	 * @@return Callback�ɕێ�����DateProvider�C���X�^���X��Ԃ�
	 */
	public String[] getMarketCodes()
	{
		return this.m_handlingXblocksMarketCodes;
    
	}


}@
