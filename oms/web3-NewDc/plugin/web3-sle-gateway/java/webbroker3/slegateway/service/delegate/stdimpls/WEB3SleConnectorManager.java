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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : SleConnectorManager�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : warlu FTL.US �V�K�쐬
 Revision History : 2006/04/24 �� �V�K�쐬
 Revision History : 2006/06/06 ���iFLJ) �o�b�O�Ή�
 Revision History : 2006/06/08 ���iFLJ) �\�[�X���� 
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
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientFactory;//�v�[���N���C�A���g�̃^�C���A�E�g�Ή��� 2006/10/26 �ǉ�


/**
 * SLE�R�l�N�^�N���C�A���g�Ǘ��N���X.
 */
public class WEB3SleConnectorManager
{

	/**
	 * ���O�o�̓��[�e�B���e�B
	 */
	private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3SleConnectorManager.class);
	
	/**
	 * �f�o�b�O���O�o�̓t���O
	 */
	private static final boolean DBG = m_log.ison();
	
	/**
	 * �R���X�g���N�^
	 */
	private WEB3SleConnectorManager()
	{
		;
	}

	/**
	 * �_���s��R�[�h����GL�Ǘ��s��R�[�h�֕ϊ�����
	 * @@param xBlocksMarketCode
	 *          xBlock�r�W�l�X���W�b�N�Ɏ��ʂ����_���s��R�[�h
	 * @@return SLE�R�l�N�^�Ɏ��ʂ���镨���s��R�[�h
	 */
	public static String getLogicalMarketCode(String l_strXBlocksMarketCode)
	{	
		return l_strXBlocksMarketCode;//xBlock�̎s��R�[�h�ɑ΂��ϊ��Ȃ��A���̂܂ܕԂ�
	}

	/**
	 * �w�肵���s��ւ�SLE�R�l�N�^���擾����.
	 * @@param xBlocksMarketCode
	 *          �s��R�[�h.
	 * @@return Conenctor �s��ւ�SLE�R�l�N�^.
	 */
	public static GlSleConnectorClient getSleConnector(
		WEB3SleConnectorClientFactory l_connectorfactory,
		String l_strxBlocksMarketCode,
		GlSleRequest l_glreq)//������standby�֐؂�ւ��悤�ɉ��C�@@2006/10/27
//		throws GlSleConnectorClientInitialException
		throws GlSleConnectorClientException //sendRequest�����ǉ����Ƃɂ���ĕύX 2006/10/27 
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

		//�s��N���X�^����L���ȃR�l�N�^�N���C�A���g�����o����
		for (int i = 0; i < l_cluster.length; i++)
		{

			if (l_cluster[i].isGlSleConnectorClientConnectionOnline()) 
			{

				m_log.exiting(
					"GlSleConnectorClient getSleConnector(String)");
				l_cluster[i].getGlSleConnectorClient().sendRequest(l_glreq);//sendRequest������ǉ�(2006/10/26)
				return l_cluster[i].getGlSleConnectorClient();
			}
		}

		synchronized (WEB3SleConnectorManager.class)
		{

			/*
              �S�ẴR�l�N�^���I�t���C���̎��A
			  �Ď��s����
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
			
			Map l_mException = new HashMap(); //sendRequest�������Ŕ�������Exception��ێ����邽�ߒǉ�(2006/10/26)
			for (int i = 0; i < l_cluster.length; i++)
			{

				if (l_cluster[i].isGlSleConnectorClientConnectionOnline())
				{

					m_log.exiting(
						"GlSleConnectorClient	getSleConnector(String)");
					l_cluster[i].getGlSleConnectorClient().sendRequest(l_glreq);//sendRequest������ǉ�(2006/10/26)
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
							
						//�v�[���N���C�A���g�^�C���A�E�g�Ή�����ɂ͒ǉ� 2006/10/26
						Properties l_pTimeOut = getPoolClientProperties();

						// ��V�K�R�l�N�^�𐶐�����
						final GlSleConnectorClient connector =
							l_connectorfactory.getPoolClient(
								ia,
								l_cluster[i].m_port,
								getSocketPoolSize(),
								l_pTimeOut);
						//���v�[���N���C�A���g�^�C���A�E�g��Ή�����ɂ�timeout�v���p�e�B��ǉ� 2006/10/26	
						connector.sendRequest(l_glreq);//sendRequest�����ǉ��@@2006/10/27
						
						l_cluster[i].setGlSleConnectorClient(connector);
						l_cluster[i].setGlSleConnectorClientConnectionStatusToOnline();
						//�V�K�R�l�N�^���N���X�^�ɓ����
						SleConnectorClusterElementManager
							.setSleConnectorClusterElements(
							l_strxBlocksMarketCode,
							l_cluster);

						m_log.exiting(
							"GlSleConnectorClient	getSleConnector(String)");
						return connector;
					}
					catch(GlSleConnectorClientException gle){//sendRequest�����ǉ��ɂ���� add 2006/10/2
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
			if ( l_mException.get("glsleexception") == null){//GlSleConnectorClientInitialException �D��X���[����� �ǉ� 2006/10/27
				m_log.debug("No GlSleConnector client connections available");//add 2006/10/27
				m_log.exiting("GlSleConnectorClient	getSleConnector(String)");
				throw new GlSleConnectorClientInitialException("No GlSleConnector client connections available");
			}else{//sendRequest�����s���G���[
				m_log.debug("there is a GlSleConnectorClientException happened !");
				m_log.exiting("GlSleConnectorClient	getSleConnector(String)");
				throw (GlSleConnectorClientException)l_mException.get("glsleexception");
			}
		}		
	}

	/**
	 * Sends the given request to Sle Connector
	 * @@param marketCode
	 *          ���`�s��̎s��R�[�h.
	 * @@param req
	 *          SLE���M�d����ێ�����I�u�W�F�N�g
	 * @@throws GlSleConnectorClientException
	 *           SLE�R�l�N�^�N���C�A���g��O
	 */
	public static void send(
		WEB3SleConnectorClientFactory l_connectorfactory,
		String l_strMarketCode,
		GlSleRequest l_glreq)
		throws GlSleConnectorClientException,RuntimeException //��RuntimeException��O��ǉ� 2006/10/11
	{
		try {
			m_log.info("Sending request to SLE:" + l_glreq);
			
			final GlSleConnectorClient sleConnector =
				getSleConnector(l_connectorfactory, l_strMarketCode,l_glreq);//������standby�֐؂�ւ��悤�ɉ��C�@@2006/10/27

			
			//sleConnector.sendRequest(l_glreq);//������standby�֐؂�ւ��悤�ɉ��C 2006/10/27
			
			if (DBG){
				m_log.debug("Sent to GL SleConnector OK");
			}
			
		} catch (GlSleConnectorClientException ex) {
			
			m_log.debug("there is a GlSleConnectorClientException happened !");
			
			WEB3SleConnectorManager.invalidateGlSleConnectorClientConnection(l_strMarketCode);
			//throw new GlSleConnectorClientException(ex.getMessage(), ex);
			/*
			  SleSendqProcessorServiceImpl�G���[�n���h���ŗ�O��ʂ𔻒f�ł��Ȃ�����
			  ���b�p���s�킸�A���M�����ۂŔ�������GlSleConnectorClientException�̗�O�����̂܂܃X���[����
			 */
			throw ex;
		} catch (RuntimeException re){
			m_log.debug("there is a unknow exception happend !");
			throw re;
		}
	}

	/**
	 * �R�l�N�^���N���[�Y����
	 */
	public static void close() {

		m_log.entering("close()");
		synchronized (WEB3SleConnectorManager.class) {

			//�N���X�^�ɑ��݂����S�ăR�l�N�^�N���C�A���g���N���[�Y����
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
							//��(FLJ)�ǉ�
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
	 * ���݂̃A�N�e�B�u�R�l�N�^�𖳌��ɂ���
	 * @@param marketCode
	 *         �s��R�[�h
	 */
	public static void invalidateGlSleConnectorClientConnection(String l_strMarketCode)
	{

		m_log.entering("invalidateGlSleConnectorClientConnection(String)");

		synchronized (WEB3SleConnectorManager.class)
		{

			//�S���̐ڑ������
			final SleConnectorClusterElement[] l_cluster =
				SleConnectorClusterElementManager.getSleConnectorClusterElements(l_strMarketCode);
			
			if (l_cluster == null)  {// �˒ǉ� 2006/10/23
				m_log.warn("the sle connector is not cleared because there is no valid sle-connector cluster.");
				m_log.exiting("invalidateGlSleConnectorClientConnection(String)");
				return;
			}
			for (int i = 0; i < l_cluster.length; i++)
			{
				
				if (l_cluster[i] == null){//�ˁ@@�ǉ� 2006/10/23
					m_log.warn("the sle connector is not cleared because there is no valid sle-connector cluster.");
					continue; 
				}
				
				if (l_cluster[i].isGlSleConnectorClientConnectionOnline())
				{
					l_cluster[i].setGlSleConnectorClientConnectionStatusToOffline();

					//�ڑ��Ɏ��݂���
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

					// �ڑ���null�ɐݒ肷��
					l_cluster[i].setGlSleConnectorClient(null);
					

					WEB3SleMarketAdapterUtils.moveElementToEnd(l_cluster, i); //2007/1/6 �C�� ���[�h

					m_log.exiting(
						"invalidateGlSleConnectorClientConnection(String)");
					return;
				}
			}
		}

		m_log.exiting("invalidateGlSleConnectorClientConnection(String)");

	}
	
	

	/**
	 * SLE�v�[���N���C�A���g�擾�������̃v���p�e�B��Ԃ�
	 * @@return�@@�v�[���N���C�A���g�擾�����v���p�e�B��Ԃ�.
	 * ���v�[���N���C�A���g�擾���̃^�C���A�E�g��Ή����邽�߁A2006/10/26 �ǉ�
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
	 * SLE�R�l�N�^�N���C�A���g�ɑ΂���\�P�b�g�T�C�Y��Ԃ�
	 * @@return �\�P�b�g�T�C�Y��Ԃ�.
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
	 * �w�肵���s��ɑΉ�����R�l�N�^�N���X�^�z���Ԃ�(�N���C�A���g�|�[�g�p)
	 * @@param marketCode
	 *          ���`�s��̎s��R�[�h.
	 * @@return �A�N�e�B�u�܂��̓X�^���o�C�ɑΉ�����SleConnectorClusterElement�I�u�W�F�N�g��ێ�����z��
	 */
	public static SleConnectorClusterElement[] getSleConnectorOrderSubmissionClusterElements(String l_strMarketCode)
	{
		final String[] l_strnames =
		{
			"sleconnector.feq.sleonmc.active.url", 
			"sleconnector.feq.sleonmc.standby.url",
		};//���s���Ή�����ɂ́ASYSTEM_PREFERENCES�̍���Name��ύX 2007/10/24

		return getSleConnectorClusterElements(l_strMarketCode, l_strnames);

	}

	/**
	 * �w�肵���s��ɑΉ�����R�l�N�^�N���X�^�z���Ԃ�(�Ǘ��|�[�g�p)
	 * @@param marketCode
	 *          ���`�s��̎s��R�[�h.
	 * @@return �A�N�e�B�u�܂��̓X�^���o�C�ɑΉ�����SleConnectorClusterElement�I�u�W�F�N�g��ێ�����z��
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
	 * �w�肵���s��ɑΉ�����R�l�N�^�N���X�^�z���Ԃ��B 
	 * ipaddress/port �̃t�H�[�}�b�g�� SLE�R�l�N�^�ڑ����SYSTEM_PREFERENCES�ɕێ������
	 * @@param marketCode
	 *          ���`�s��̎s��R�[�h
	 * @@param names
	 *          SYSTEM_PREFERENCES�ɐݒ肵��SLE�R�l�N�^�C���X�^���X��
	 * @@return SleConnectorClusterElement[]
	 * �@@�@@�@@�@@ �w�肵���s��ɑΉ�����R�l�N�^�N���X�^�z��
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
				//���s���Ή����邽�߁ASYSTEM_PREFERENCES�̍���Name�Ɏs��R�[�h��ǉ��@@2007/10/24
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
	 * �R�l�N�^�N���X�^���Ǘ�����N���X(�C���i�N���X)
	 */
	public static class SleConnectorClusterElementManager
	{
		
		/**
		 * �s��A�s��ɑΉ�����R�l�N�^�N���X�^���Ǘ�����}�b�s���O
		 */
		private static Map m_sleConnectorsTable = new HashMap();

		/**
		 * �R�l�N�^�N���X�^���Ǘ�����Connection��Iterator���擾����
		 * @@return Iterator �R�l�N�^�N���X�^���Ǘ�����Connection��Iterator
		 */
		public static Iterator getSleConnectorClusterElementsEntrySetIterator()
		{
			return m_sleConnectorsTable.entrySet().iterator();
		}

		/**
		 * xBlock�s��R�[�h�ɑΉ�����R�l�N�^�N���X�^���擾����
		 * @@param xBlocksMarketCode
		 *          xBlock�s��R�[�h
		 * @@return �w�肵���s��ɑΉ�����R�l�N�^�N���X�^�z��
		 */
		public static SleConnectorClusterElement[] getSleConnectorClusterElements(final String l_strxBlocksMarketCode)
		{

			final String marketCode = getLogicalMarketCode(l_strxBlocksMarketCode);
			return (SleConnectorClusterElement[]) m_sleConnectorsTable.get(marketCode);
		}

		/**
		 * xBlock�̎s��R�[�h�ɑΉ�����R�l�N�^�N���X�^��ێ�����
		 * @@param xBlocksMarketCode
		 *          xBlock�s��R�[�h�@@
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
	 * �R�l�N�^URL�f�[�^(URL,IP,PORT�Ȃ�)���Ǘ�����N���X
	 */
	public static class SleConnectorClusterElement {
		/**
		 * ��t�|�[�gURL
		 */
		public final String m_url;
		
		/**
		 * ��t�|�[�gIP
		 */
		public final String m_address;
		
		/**
		 * ��t�|�[�g�ԍ�
		 */
		public final int m_port;
	
		/**
		 * ��t�|�[�g�ւ�SLE�R�l�N�^�N���C�A���g
		 */
		private GlSleConnectorClient m_glSleConnectorClient = null;
		
		/**
		 * SLE�R�l�N�^�N���C�A���g�̃X�e�[�^�X�t���O
		 */
		private Boolean m_glSleConnectorClientStatus = null;
		
		/**
		 * �R���X�g���N�^
		 * @@param url URL
		 * @@param address IP
		 * @@param port �|�[�g�ԍ�(int�^)
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
		 * �R���X�g���N�^
		 * @@param url '/'��IP�A�|�[�g�ԍ���؂蕪����URL
		 */
		public SleConnectorClusterElement(String url)
		{
			this(url, url.split("/"));
		}
		
		/**
		 * �R���X�g���N�^
		 * @@param url URL
		 * @@param address IP
		 * @@param port �|�[�g�ԍ�(String�^)
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
		 * �R�l�N�^�N���C�A���g���擾����
		 * @@return �R�l�N�^�N���C�A���g
		 */
		public GlSleConnectorClient getGlSleConnectorClient()
		{
			return m_glSleConnectorClient;
		}

		/**
		 * �R�l�N�^�N���C�A���g��Setter����
		 * @@param �R�l�N�^�N���C�A���g
		 */
		public void setGlSleConnectorClient(GlSleConnectorClient glSleConnectorClient)
		{
			this.m_glSleConnectorClient = glSleConnectorClient;
		}

		/**
		 * �R�l�N�^��tried��Ԃł��邩�ǂ����`�F�b�N����
		 * @@return tried��ԂȂ�Atrue��Ԃ�,�� false��Ԃ��B
		 */
		public boolean isGlSleConnectorClientConnectionTried()
		{
			return m_glSleConnectorClientStatus != null;
		}

		/**
		 * �R�l�N�^���I�����C�����ǂ����`�F�b�N����
		 * @@return �I�����C���Ȃ�Atrue��Ԃ��A��false��Ԃ��B
		 */
		public boolean isGlSleConnectorClientConnectionOnline()
		{
			return isGlSleConnectorClientConnectionTried()
				&& Boolean.TRUE.equals(m_glSleConnectorClientStatus);
		}

		/**
		 * �R�l�N�^���I�t���C����Ԃł��邩�ǂ����`�F�b�N����
		 * @@return �I�t���C����ԂȂ�Atrue��Ԃ��A�� false��Ԃ��B
		 */
		public boolean isGlSleConnectorClientConnectionOffline()
		{
			return isGlSleConnectorClientConnectionTried()
				&& Boolean.FALSE.equals(m_glSleConnectorClientStatus);
		}

		/**
		 * �R�l�N�^�̃X�e�[�^�X���I�����C���ɂ���
		 */
		public void setGlSleConnectorClientConnectionStatusToOnline()
		{
			m_glSleConnectorClientStatus = Boolean.TRUE;
		}

		/**
		 * �R�l�N�^�̃X�e�[�^�X���I�t���C���ɂ���
		 */
		public void setGlSleConnectorClientConnectionStatusToOffline()
		{
			m_glSleConnectorClientStatus = Boolean.FALSE;
		}
	}

}
@
