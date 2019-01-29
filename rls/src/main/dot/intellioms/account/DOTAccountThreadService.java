/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X�N���X(DOTAccountThreadService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.account;

import com.fitechlabs.fin.intellioms.util.Startable;

/**
 * �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadService extends Startable
{
    
    /**
     * �C���^�[�o����ݒ肷��B
     * 
     * @param �C���^�[�o��
     */
    public void setInterval(long l_interval);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�t�@�N�g����ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W�t�@�N�g��
     */
    public void setFactory(DOTAccountThreadFactory l_factory);
    
    /**
     * �X�^�[�g�ς����肷��B
     * 
     */
    public boolean isStarted();

}