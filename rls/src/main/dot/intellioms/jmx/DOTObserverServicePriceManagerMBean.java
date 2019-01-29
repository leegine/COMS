/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ObserverServicePriceManagerMBean�N���X(DOTObserverServicePriceManagerMBean.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/28 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.util.InitializationException;


/**
 * �t�w�l�Ď��T�[�r�X��JMX�ŊǗ����邽�߂�MBean�C���^�t�F�[�X
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTObserverServicePriceManagerMBean
{
    
    public void start() throws InitializationException;
    
    public void stop();

    public String getStatus();
}
