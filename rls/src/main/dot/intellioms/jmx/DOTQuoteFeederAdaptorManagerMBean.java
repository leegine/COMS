/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteFeederAdaptorManagerMBean�N���X(DOTQuoteFeederAdaptorManagerMBean.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/09 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx;



/**
 * �����A�_�v�^��JMX�ŊǗ����邽�߂�MBean�C���^�[�t�F�[�X
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFeederAdaptorManagerMBean
{
    
    public void connect();
    
    public void disconnect();
    
    public int getStatus();

}
