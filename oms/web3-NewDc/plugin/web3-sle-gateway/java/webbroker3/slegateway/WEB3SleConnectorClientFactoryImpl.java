head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleConnectorClientFactoryImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SleSendqProcessorService�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/06/5 ��(FLJ) �V�K�쐬
*/

package webbroker3.slegateway;


import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClient;
import com.fitechlabs.xbconnector.sleconnectorclient.GlSleConnectorClientFactory;
/**
 * xbConnector�̕W��GlSleConnectorClient���擾����N���X
 */
public class WEB3SleConnectorClientFactoryImpl implements WEB3SleConnectorClientFactory{
	
	/**
	 * �R���X�g���N�^
	 */
	public WEB3SleConnectorClientFactoryImpl(){
		super();
	}
	/**
	 * xConnnector���񋟂���W����GlSleConnectorClient�N���X���擾����
	 * @@param ipAddr IP�A�h���X
	 * @@param port �|�[�g
	 * @@param maxPoolSize �v�[���T�C�Y
	 * @@return GlSleConnectorClient
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize){
		return GlSleConnectorClientFactory.getPoolClient(ipAddr, port, maxPoolSize);	
	}
	
	/**
	 * xConnnector���񋟂���W����GlSleConnectorClient�N���X���擾����
	 * @@param ipAddr IP�A�h���X
	 * @@param port �|�[�g
	 * @@param maxPoolSize �v�[���T�C�Y
	 * @@param props �ݒ�v���p�e�B
	 * @@return GlSleConnectorClient
	 * ���^�C���A�E�g�Ή����邽�ߒǉ� 2006/10/26
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize, java.util.Properties props){
		return GlSleConnectorClientFactory.getPoolClient(ipAddr, port, maxPoolSize,props);	
	}

}
@
