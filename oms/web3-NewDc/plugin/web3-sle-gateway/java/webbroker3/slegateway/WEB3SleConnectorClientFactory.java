head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleConnectorClientFactory.java;


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

/**
 * GlSleConnectorClientFactory���b�v�����C���^�t�F�[�X
 */
public interface WEB3SleConnectorClientFactory {
	/**
	 * �S�Ă�Sle�R�l�N�^�ɗ��p�ł���N���C�A���g���擾���܂�
	 * @@param ipAddr�@@IP�A�h���X
	 * @@param port�@@�|�[�g�ԍ�
	 * @@param maxPoolSize�@@�ő�v�[���T�C�Y
	 * @@return Sle�R�l�N�^�ɗ��p����N���C�A���g
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize); 
	
	/**
	 * xConnnector���񋟂���W����GlSleConnectorClient�N���X���擾����
	 * @@param ipAddr IP�A�h���X
	 * @@param port �|�[�g
	 * @@param maxPoolSize �v�[���T�C�Y
	 * @@param props �ݒ�v���p�e�B
	 * @@return GlSleConnectorClient
	 * ���^�C���A�E�g�Ή����邽�ߒǉ� 2006/10/26
	 */
	public GlSleConnectorClient getPoolClient(java.net.InetAddress ipAddr, int port, int maxPoolSize, java.util.Properties props);
}
@
