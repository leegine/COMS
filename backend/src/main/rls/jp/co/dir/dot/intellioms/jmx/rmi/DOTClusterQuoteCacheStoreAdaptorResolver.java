/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ClusterQuoteCacheStoreAdaptorResolver�N���X(DOTClusterQuoteCacheStoreAdaptorResolver.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/08 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx.rmi;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClient;
import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.cluster.ClusterNode;


/**
 * XTier�N���X�^�T�[�r�X���A���B�\�ȃ��[���G���W���ɐڑ�����
 * ���������擾����B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTClusterQuoteCacheStoreAdaptorResolver implements
    DOTClusterQuoteCacheStoreAdaptor
{

    /** ���[���G���W���T�[�o�̃N���X�^�O���[�v�� */
    public static final String RLS_CLUSTER_GRP_NAME = "rule-engines";

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /**
     * ���[���G���W����JMX-RMI�|�[�g�ԍ�
     * �f�t�H���g�FJmxRmiClient.XTIER_RMIREG_PORT
     */
    private int jmxRmiPort;

    /**
     * �R���X�g���N�^
     */
    public DOTClusterQuoteCacheStoreAdaptorResolver()
    {
        this.jmxRmiPort = JmxRmiClient.XTIER_RMIREG_PORT;
    }

    /**
     * JMX-RMI�|�[�g�ԍ���ݒ肷��B
     *
     * @param l_intJmxRmiPort JMX-RMI�|�[�g�ԍ�
     */
    public void setJmxRmiPort(int l_intJmxRmiPort)
    {
        this.jmxRmiPort = l_intJmxRmiPort;
    }

    /**
     * JMX-RMI�|�[�g�ԍ����擾����B
     *
     * @return JMX-RMI�|�[�g�ԍ�
     */
    public int getJmxRmiPort()
    {
        return jmxRmiPort;
    }

    /**
     * �N���X�^�\������Ă��郋�[���G���W���̑��m�[�h���玞�������擾����B
     *
     * xTier-Cluster�T�[�r�X���A�N���X�^�\������Ă��鑼�m�[�h�̈ꗗ���擾���A
     * 1�m�[�h���������̎擾�����݂�B1�̃m�[�h���玞����񂪎擾�ł��Ȃ�
     * �ꍇ�́A���̃m�[�h���玞�����̎擾�����݂�B
     *
     * @see DOTClusterQuoteCacheStoreAdaptor#getQuoteData(Timestamp, Timestamp)
     * @throws IllegalStateException �N���X�^�\������Ă��郋�[���G���W���̃m�[�h�����݂��Ȃ��ꍇ
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to)
        throws JmxRmiClientException
    {

        Set l_clusterNodes = getClusterNodes();
        if (l_clusterNodes.isEmpty())
        {
            throw new IllegalStateException("Clustered node not found.");
        }

        DOTClusterQuoteCacheStoreAdaptorImpl l_adaptor = null;
        for (Iterator l_it = l_clusterNodes.iterator(); l_it.hasNext();)
        {
            ClusterNode l_node = (ClusterNode) l_it.next();
            String l_strHostName = l_node.getAddress().getHostAddress();
            try
            {
                l_adaptor = new DOTClusterQuoteCacheStoreAdaptorImpl(
                    l_strHostName,
                    jmxRmiPort);
                return l_adaptor.getQuoteData(l_from, l_to);
            } catch (JmxRmiClientException l_jrce)
            {
                log.warn(
                    "Failed to get quote data. " + "[host=" + l_strHostName
                        + ", jmxRmiPort=" + jmxRmiPort + "]",
                    l_jrce);
            }
        }

        throw new JmxRmiClientException("Failed to get quote data from all clusters.");

    }

    /**
     * �N���X�^�\������Ă��郋�[���G���W���̃m�[�h�ꗗ����A���m�[�h��
     * �������ꗗ��Ԃ��B
     *
     * @return <code>ClusterNode</code>��<code>Set</code>
     */
    private Set getClusterNodes()
    {
        Set l_results = new HashSet();
        Set l_nodes = XtierKernel.getInstance().cluster().getAllNodes();
        if (l_nodes != null && !l_nodes.isEmpty())
        {
            for (Iterator l_it = l_nodes.iterator(); l_it.hasNext();)
            {
                ClusterNode l_node = (ClusterNode) l_it.next();
                if (!l_node.isLocalNode()
                    && l_node.isGroupMember(RLS_CLUSTER_GRP_NAME))
                {
                    l_results.add(l_node);
                }
            }
        }

        return l_results;
    }

}
