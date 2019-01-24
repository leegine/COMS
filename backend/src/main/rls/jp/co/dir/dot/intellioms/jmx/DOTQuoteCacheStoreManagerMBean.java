/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteCacheManagerMBean�N���X(WEB3QuoteCacheManagerMBean.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/27 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx;

import java.sql.Timestamp;
import java.util.List;


/**
 * �������Ǘ��e�[�u����JMX�ŊǗ����邽�߂�MBean�C���^�t�F�[�X
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteCacheStoreManagerMBean
{
    
    /**
     * �������Ǘ��e�[�u�����ێ����鎞�������擾����B
     * 
     * @param l_from �X�V���ԁiFROM�j
     * @param l_to �X�V���ԁiTO�j
     * @return <code>WEB3Quotes</code>�̃��X�g
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to);
    
    /**
     * ���������擾����B
     * 
     * @param l_strFrom �X�V���ԁiFROM�j�y�t�H�[�}�b�g�Fyyyy/MM/dd HH:mm:ss�z
     * @param l_strTo �X�V���ԁiTO�j�y�t�H�[�}�b�g�Fyyyy/MM/dd HH:mm:ss�z
     * @return <code>DOTQuoteData</code>�̃��X�g
     */
    public List getQuoteData(String l_strFrom, String l_strTo);

}
