/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[(DOTAccountThreadDbAdaptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.account;

import java.util.List;

import com.fitechlabs.fin.intellioms.account.AccountIDRange;
import com.fitechlabs.fin.intellioms.persist.PersistException;

/**
 * �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadDbAdaptor
{
    /**
     * DB�ɒ�`���Ă���A�J�E���g�����W���擾����B
     * 
     * @return AccountIDRange
     */
    public AccountIDRange getAccountIDRange(DOTAccountThreadDbParams l_context) throws PersistException;

    /**
     * �X���b�h���ƃA�J�E���g�����W�ݒ���擾����B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     * @return List
     */
    public List getAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException;
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�ݒ���폜����B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     */
    public void deleteAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException;
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�ݒ��}������B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     */
    public void insertAccountRangePerThread(DOTAccountThreadDbParams l_context) throws PersistException;
    
}