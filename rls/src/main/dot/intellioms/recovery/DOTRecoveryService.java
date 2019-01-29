/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3RecoveryManager�N���X(WEB3RecoveryManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;

/**
 * ��Q�����T�[�r�X
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public interface DOTRecoveryService 
{    
    /**
     * �������Ǘ��e�[�u����
     * ���������擾�J�n���_����ŐV�̏�Ԃɕ�������B
     */
    public void restoreQuoteCashStoreAll(DOTQuoteDataSource l_dataSource);
    
    /**
     * �������Ǘ��e�[�u����
     * �����T�[�r�X�ؒf���ԑт̎������𕜋�����B
     */
    public void restoreQuoteCashStoreDuringLostTime(DOTQuoteDataSource l_dataSource); 
    
    /**
     * �������J�o���[�ғ������ǂ�����ԋp����B
     * @return boolean
     */
    public boolean isRecoveryQuoteRunning();
    
    /**
     * ���J�o���[���������ׂăL�����Z������B
     *
     */
    public void cancel();
    
}
