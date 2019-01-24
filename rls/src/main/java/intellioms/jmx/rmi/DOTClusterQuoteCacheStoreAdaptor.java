/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ClusterQuoteCacheStoreAdaptor�N���X(DOTClusterQuoteCacheStoreAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/07 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx.rmi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;


/**
 * �N���X�^�\�����ꂽ���[���G���W���̑��m�[�h���玞�������擾����A�_�v�^�N���X�B
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTClusterQuoteCacheStoreAdaptor
{
    
    /**
     * �N���X�^�\�����ꂽ���[���G���W���̑��m�[�h���玞�������擾����B
     * 
     * @param l_from �X�V���ԁiFROM�j
     * @param l_to �X�V���ԁiTO�j
     * @return <code>DOTQuoteData</code>�̃��X�g
     * @throws JmxRmiClientException JMX-RMI�ڑ����ɔ���������O
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to) throws JmxRmiClientException;

}
