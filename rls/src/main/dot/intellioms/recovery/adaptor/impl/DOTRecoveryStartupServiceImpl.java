/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RecoveryStartupServiceImpl�N���X(DOTRecoveryStartupServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/07 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery.adaptor.impl;

import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;
import jp.co.dir.dot.intellioms.recovery.DOTRecoveryService;
import jp.co.dir.dot.intellioms.recovery.adaptor.DOTRecoveryStartupService;


/**
 *  �N������Q�����T�[�r�X�̎����N���X�B
 * 
 * @author kazumi HORINO (FLJ)
 * @version 1.0
 */
public class DOTRecoveryStartupServiceImpl implements DOTRecoveryStartupService
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryStartupServiceImpl.class);
    
    private static final boolean DBG = log.isDebug();    
    
    /**
     * ��Q�����T�[�r�X
     */
    private DOTRecoveryService recoveryService;

    /**
     * �����f�[�^�\�[�X
     */
    private DOTQuoteDataSource quoteDataSource;
    
    /**
     * recoveryService ��ݒ�B
     * @param recoveryService�@��Q�����T�[�r�X
     */
    public void setRecoveryService(DOTRecoveryService recoveryService)
    {
        this.recoveryService = recoveryService;
    }
    
    /**
     * quoteDataSource ��ݒ肷��B
     * @param quoteDataSource �����f�[�^�\�[�X
     */
    public void setQuoteDataSource(DOTQuoteDataSource quoteDataSource) 
    {
        this.quoteDataSource = quoteDataSource;
    }            
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public void start() throws InitializationException 
    {
        //�ċN�������J�o���[�X���b�h�J�n
        recoveryService.restoreQuoteCashStoreAll(quoteDataSource);
    }
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public void stop() 
    {
        //���J�o���[�X���b�h���~��Ԃɂ���B
        recoveryService.cancel();        
    }
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(getClass().getName());
        l_sb.append("[recoveryService=" + this.recoveryService);
        l_sb.append(",quoteDataSource=" + this.quoteDataSource);
        l_sb.append("]");
        return l_sb.toString();
        
    }

    
}
