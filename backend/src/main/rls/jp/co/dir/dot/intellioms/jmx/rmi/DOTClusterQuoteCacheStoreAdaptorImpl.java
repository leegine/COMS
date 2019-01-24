/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3ClusterQuoteCacheStoreAdaptorImpl�N���X(DOTClusterQuoteCacheStoreAdaptorImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/08 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx.rmi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClient;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;

/**
 * WEB3ClusterQuoteCacheStoreAdaptor�̎����N���X
 *
 * �R���X�g���N�^�Őݒ肳�ꂽ���[���G���W����JMX-RMI�Őڑ����Ď��������擾����B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTClusterQuoteCacheStoreAdaptorImpl implements
    DOTClusterQuoteCacheStoreAdaptor
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �ڑ��惋�[���G���W���̃z�X�g�� */
    private String hostName;

    /** �ڑ��惋�[���G���W����JMX-RMI�|�[�g�ԍ� */
    private int jmxRmiPort;

    /**
     * �R���X�g���N�^
     *
     * @param l_strHostName �ڑ��惋�[���G���W���̃z�X�g��
     * @param l_intJmxRmiPort �ڑ��惋�[���G���W����JMX-RMI�|�[�g�ԍ�
     */
    public DOTClusterQuoteCacheStoreAdaptorImpl(String l_strHostName,
        int l_intJmxRmiPort)
    {

        this.hostName = l_strHostName;
        this.jmxRmiPort = l_intJmxRmiPort;

        log.info("DOTClusterQuoteCacheStoreAdaptorImpl initailized. "
            + "[host=" + l_strHostName + ", jmxRmiPort=" + l_intJmxRmiPort
            + "]");

    }

    /**
     * @return �ڑ��惋�[���G���W���̃z�X�g�����擾����B
     */
    public String getHostName()
    {
        return hostName;
    }

    /**
     * @return �ڑ��惋�[���G���W����JMX-RMI�|�[�g�ԍ����擾����B
     */
    public int getJmxRmiPort()
    {
        return jmxRmiPort;
    }

    /**
     * @see DOTClusterQuoteCacheStoreAdaptor#getQuoteData(Timestamp,
     *      Timestamp)
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to)
        throws JmxRmiClientException
    {

        JmxRmiClient l_jmxClient = new JmxRmiClient(hostName, jmxRmiPort);

        final String l_strObjectName = "WEB3IntelliOMS:name=QuoteCacheStore";
        final String l_strOperationName = "getQuoteData";
        final Object[] l_objParams = new Object[]
        { l_from, l_to };
        final String[] l_strSignature = new String[]
        { "java.sql.Timestamp", "java.sql.Timestamp" };

        return (List) l_jmxClient.invoke(
            l_strObjectName,
            l_strOperationName,
            l_objParams,
            l_strSignature);

    }

}
