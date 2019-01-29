/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3StopRlsQuoteConnectionErrorHandler�N���X(DOTStopRlsQuoteConnectionErrorHandler.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/20 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteConnectionErrorHandler;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;


/**
 * (���[���G���W����~�ڑ��G���[�n���h��)<BR>
 * <BR>
 * �����T�[�o�Ƃ̍Đڑ����m�����Ȃ��ꍇ�ɁA
 * ���[���G���W�����~����ڑ��G���[�n���h��<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTStopRlsQuoteConnectionErrorHandler implements
    DOTQuoteConnectionErrorHandler
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �ڑ���ԃ��j�^�[ */
    private DOTQuoteConnectionMonitor connectionMonitor;

    /** �����f�[�^�\�[�X */
    private DOTQuoteDataSource dataSource;

    /**
     * (register�ڑ���ԃ��j�^�[)<BR>
     * <BR>
     * �ڑ���ԃ��j�^�[��o�^����B<BR>
     *
     * @param l_connectionMonitor �ڑ���ԃ��j�^�[
     * @throws IllegalArgumentException �p�����[�^.�ڑ���ԃ��j�^�[��<code>null</code>�̏ꍇ
     */
    public void registerQuoteConnectionMonitor(
        DOTQuoteConnectionMonitor l_connectionMonitor)
    {

        if (l_connectionMonitor == null)
        {
            throw new IllegalArgumentException(
                "l_connectionMonitor must be not null.");
        }

        this.connectionMonitor = l_connectionMonitor;

        log.info("QuoteConnectionMonitor is registered [QuoteConnectionMonitor="
                + l_connectionMonitor + "]");

    }

    /**
     * �o�^���ꂽ�ڑ���ԃ��j�^�[���g���Ď����T�[�o�Ƃ̐ڑ����m������܂�
     * �ҋ@���A�ڑ����m�����ꂽ�Ȃ��ꍇ�ɂ́A���[���G���W�����~����
     * �X���b�h���N������B
     *
     * @see DOTQuoteConnectionErrorHandler#handle()
     */
    public void handle()
    {

        if (connectionMonitor == null || dataSource == null)
        {
            throw new IllegalStateException(
                "QuoteConnectionMonitor or QuoteDataSource is not registered.");
        }

        Thread l_t = new MonitoringConnectionThread();
        l_t.setName("MonitoringConnectionThread");
        l_t.setDaemon(true);
        l_t.start();

    }

    /**
     * �����f�[�^�\�[�X��ݒ肷��B
     *
     * @see DOTQuoteConnectionErrorHandler#onRegister(DOTQuoteDataSource)
     */
    public void onRegister(DOTQuoteDataSource l_dataSource)
    {

        if (l_dataSource == null)
        {
            throw new IllegalArgumentException("l_dataSource must be not null.");
        }

        this.dataSource = l_dataSource;

        log.info("DOTStopRlsQuoteConnectionErrorHandler registered to QuoteDataSource."
            + "[QuoteDataSource=" + l_dataSource + "]");

    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("DOTStopRlsQuoteConnectionErrorHandler[");
        l_sb.append("connectionMonitor=").append(connectionMonitor);
        l_sb.append("]");
        return l_sb.toString();
    }

    /**
     * (�ڑ���ԊĎ��X���b�h)<BR>
     * <BR>
     * �ݒ肳�ꂽ�ڑ���ԃ��j�^�[���g���Ď����T�[�o�Ƃ̐ڑ���Ԃ��Ď�����X���b�h�B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class MonitoringConnectionThread extends Thread
    {

        /**
         * @see Runnable#run()
         */
        public void run()
        {

            log.debug("MonitoringConnectionThread started.");

            boolean l_result = connectionMonitor
                .waitForConnectionToBeEstablished(dataSource);

            if (!l_result)
            {
                log.warn("Rule-engine will stop because connection to quote server counld not be established.");
            }

            log.debug("MonitoringConnectionThread stopped. "
                + "[result=" + l_result + "]");

            return;

        }
    }

}
