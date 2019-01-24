/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �X���b�h���ƃA�J�E���g�����W�𐧌䂷��}�l�[�W���[�N���X(DOTAccountThreadManager.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.account;

import com.fitechlabs.fin.intellioms.rulesys.BizDateProvider;
import com.fitechlabs.fin.intellioms.rulesys.OmsRuleEngine;

import jp.co.dir.dot.intellioms.tx.DOTTxConnPoolAdaptor;


/**
 * �X���b�h���ƃA�J�E���g�����W�𐧌䂷��}�l�[�W���[
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadManager
{

    /**
     * �^�X�N�^�C�}�[�C���^�[�o����ݒ肷��B
     * 
     * @param �^�X�N�^�C�}�[�C���^�[�o��
     */
    public void setTaskTimerInterval(long l_taskTimerInterval);

    /**
     * �f�t�H���g�X���b�h�T�C�Y��ݒ肷��B
     * 
     * @param �f�t�H���g�X���b�h�T�C�Y
     */
    public void setDefaultThreadSize(long l_defaultThreadSize);

    /**
     * �ő�X���b�h�T�C�Y��ݒ肷��B
     * 
     * @param �ő�X���b�h�T�C�Y
     */
    public void setMaxThreadSize(long l_maxThreadSize);

    /**
     * ���[���G���W����ݒ肷��B
     * 
     * @param ���[���G���W��
     */
    public void setOmsRuleEngine(OmsRuleEngine l_ruleEngine);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[��ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W�f�[�^�x�[�X�A�_�v�^�[
     */
    public void setAdaptor(DOTAccountThreadDbAdaptor l_adaptor);
    
    /**
     * bizDate�v���o�C�_�[��ݒ肷��B
     * 
     * @param bizDate�v���o�C�_�[
     */
    public void setBizDateProvider(BizDateProvider l_bizDateProvider);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�t�@�N�g����ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W�t�@�N�g��
     */
    public void setFactory(DOTAccountThreadFactory l_factory);
    
    /**
     * �X���b�h���ƃA�J�E���g�����W�}�l�[�W���[���I������B
     */
    public void safeStop();

    /**
     * �R�l�N�V�����v�[���A�_�v�^�[��ݒ肷��B
     * 
     * @param �R�l�N�V�����v�[���A�_�v�^�[
     */
    public void setTxAdaptor(DOTTxConnPoolAdaptor txAdaptor);

    /**
     * �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X��ݒ肷��B
     * 
     * @param �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X
     */
    public void setAccountThreadService(DOTAccountThreadService l_accountThreadService);

    /**
     * ���ׂĂ�TimerTask����~������K�v�Ƃ��Ă��邩���肷��B
     */
    public boolean isAllTimerNeedStopping();
}