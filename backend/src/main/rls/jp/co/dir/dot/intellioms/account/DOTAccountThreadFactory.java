/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�}�l�[�W���[�Ŏg�p����t�@�N�g��(DOTAccountThreadFactory.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import com.fitechlabs.xtier.services.ioc.IocServiceException;

/**
 * �X���b�h���ƃA�J�E���g�����W�}�l�[�W���[�Ŏg�p����t�@�N�g��
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadFactory
{
    /**
     * �^�C�}�[�}�l�[�W���[���쐬����B
     * 
     */
    public DOTAccountThreadManager createManager() throws IocServiceException;
    
    /**
     * �X���b�h���ƃA�J�E���g�����W���쐬����B
     * 
     */
    public AccountRangePerThread createAccountRangePerThread(long l_start, long l_end);

    /**
     * �^�C�}�[�^�X�N���쐬����B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     */
    public DOTAccountThreadTimerTask createTimerTask(AccountRangePerThread l_accountRange) throws IocServiceException;

    /**
     * �^�C�}�[���쐬����B
     * 
     * @param �^�C�}�[�^�X�N
     * @param �C���^�[�o��
     */
    public DOTAccountThreadTimerAdaptor createTimer(DOTAccountThreadTimerTask l_task, long l_interval);

    /**
     * �^�X�N���e���쐬����B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     */
    public DOTAccountThreadTask createTask(AccountRangePerThread l_accountRange) throws IocServiceException;

}
