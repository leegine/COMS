/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RecoveryQuoteConnectionErrorHandlerImpl�N���X(DOTRecoveryQuoteConnectionErrorHandlerImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/16 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteConnectionErrorHandler;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;


/**
 * �����ؒf���G���[�n���h���|�̃��J�o���[����
 *
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryQuoteConnectionErrorHandlerImpl implements
DOTQuoteConnectionErrorHandler
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryQuoteConnectionErrorHandlerImpl.class);

    private static final boolean DBG = log.isDebug();


    /**
     * �����f�[�^�\�[�X
     */
    private DOTQuoteDataSource quoteDataSource;

    /**
     * ��Q�����T�[�r�X
     */
    private DOTRecoveryService recoveryService;

    /**
     * @see DOTQuoteConnectionErrorHandler#handle()
     */
    public void handle()
    {
        recoveryService.restoreQuoteCashStoreDuringLostTime(quoteDataSource);

    }

    /**
     * @see DOTQuoteConnectionErrorHandler#onRegister(DOTQuoteDataSource)
     */
    public void onRegister(DOTQuoteDataSource l_dataSource)
    {
        this.quoteDataSource = l_dataSource;
    }

    /**
     * recoveryService ��ݒ�B
     * @param recoveryService ��Q�����T�[�r�X
     */
    public void setRecoveryService(DOTRecoveryService recoveryService)
    {
        this.recoveryService = recoveryService;
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
