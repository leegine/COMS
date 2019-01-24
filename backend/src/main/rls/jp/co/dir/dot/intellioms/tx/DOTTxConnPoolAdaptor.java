/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �R�l�N�V�����v�[���A�_�v�^�[(DOTTxConnPoolAdaptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.tx;

import java.sql.Connection;

/**
 * �R�l�N�V�����v�[���A�_�v�^�[
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTTxConnPoolAdaptor
{
    /**
     * �R�l�N�V�����v�[������R�l�N�V�������擾����B
     * 
     */
    public Connection getConnection() throws DOTTxConnPoolAdaptorException;
    
    /**
     * �R�l�N�V�������R�l�N�V�����v�[���ɕԋp����B
     * 
     * @param Connection
     */
    public void release(Connection connection);
}
