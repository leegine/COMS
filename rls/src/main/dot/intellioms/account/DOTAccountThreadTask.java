/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �^�X�N���e(DOTAccountThreadTask.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

/**
 * �^�X�N���e
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadTask
{
    /**
     * �X���b�h���ƃA�J�E���g�����W��ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W
     */
    public void setAccountRangePerThread(AccountRangePerThread l_accountRange);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�^�C�}�[�^�X�N��ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W�^�C�}�[�^�X�N
     */
    public void setTimerTask(DOTAccountThreadTimerTask l_timerTask);

    /**
     * �^�X�N���e�����s����B
     * 
     */
    public void execute() throws Exception;
    
}
