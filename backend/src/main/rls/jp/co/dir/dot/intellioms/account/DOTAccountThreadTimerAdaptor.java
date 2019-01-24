/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����Ƃ�����^�C�}�[�A�_�v�^�[(DOTAccountThreadTimerAdaptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

/**
 * ����Ƃ�����^�C�}�[�A�_�v�^�[
 */
public interface DOTAccountThreadTimerAdaptor
{
    
    /**
     * �^�C�}�[���J�n����
     */
    public void start();

    /**
     * �^�C�}�[���~����
     */
    public void stop();

    /**
     * �^�C�}�[��~�������K�v�����肷��
     */
    public boolean isNeedStopping();
}
