/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�^�C�}�[�^�X�N(DOTAccountThreadTimerTask.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import jp.co.dir.dot.intellioms.tx.DOTTxConnPoolAdaptor;


/**
 * �X���b�h���ƃA�J�E���g�����W�^�C�}�[�^�X�N
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadTimerTask
{   
    /**
     * �X���b�h���ƃA�J�E���g�����W��ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     */
    public void setAccountRangePerThread(AccountRangePerThread l_accountRange);
    
    /**
     * �^�X�N���e��ݒ肷��B
     * 
     * @param �^�X�N���e
     */
    public void setTask(DOTAccountThreadTask l_task);
    
    /**
     * �R�l�N�V�����v�[���A�_�v�^�[��ݒ肷��B
     * 
     * @param �R�l�N�V�����v�[���A�_�v�^�[
     */
    public void setTxConnPoolAdaptor(DOTTxConnPoolAdaptor l_txAdaptor);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[��ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[
     */
    public void setDbAdaptor(DOTAccountThreadDbAdaptor l_dbAdaptor);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W���擾����B
     * 
     */
    public AccountRangePerThread getAccountRangePerThread();
    
    /**
     * �^�X�N���I������B
     */
    public void safeStop();

    /**
     * �^�C�}�[�^�X�N�̏�Ԃ��`�F�b�N���܂��B<BR>
     * ���s���ׂ��łȂ��ꍇ�AIllegalStateException��throw���܂��B
     */
    public void checkState() throws IllegalStateException;
    
    /**
     * �P�񂾂����s���ׂ��^�X�N����ݒ肷��B
     * 
     * @param �P����s�t���O - (true=1����s, false=��������s)
     */
    public void setOnceExecute(boolean isOnceExecute);

    /**
     * �^�X�N��~�������K�v�����肷��B
     */
    public boolean isNeedStopping();
}
