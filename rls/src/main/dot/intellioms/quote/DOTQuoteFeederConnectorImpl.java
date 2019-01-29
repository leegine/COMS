/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteFeederConnectorImpl�N���X(DOTQuoteFeederConnectorImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/10 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptorException;
import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteStatusManager;

/**
 * (�����T�[�o�ڑ��T�[�r�XImpl)<BR>
 * <BR>
 * �����T�[�o�ڑ��T�[�r�X�̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteFeederConnectorImpl implements DOTQuoteFeederConnector
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �����A�_�v�^ */
    private DOTQuoteFeederAdaptor feederAdaptor;

    /** �ڑ���ԃ}�l�[�W�� */
    private DOTQuoteStatusManager statusManager;

    /** �R���X�g���N�^ */
    public DOTQuoteFeederConnectorImpl()
    {
    }

    /**
     * @throws IllegalArgumentException
     *         <code>�p�����[�^.�����A�_�v�^ == null</code>�̏ꍇ
     * @see DOTQuoteFeederConnector#registerQuoteFeederAdaptor(DOTQuoteFeederAdaptor)
     */
    public void registerQuoteFeederAdaptor(DOTQuoteFeederAdaptor l_feederAdaptor)
    {

        if (l_feederAdaptor == null)
        {
            throw new IllegalArgumentException("l_feederAdaptor must be not null.");
        }

        this.feederAdaptor = l_feederAdaptor;

        log.info("QuoteFeederAdaptor registered. ["
            + l_feederAdaptor.getClass().getName() + "]");

    }

    /**
     * @throws IllegalArgumentException
     *         <code>�p�����[�^.�ڑ���ԃ}�l�[�W�� == null</code>�̏ꍇ
     * @see DOTQuoteFeederConnector#registerQuoteStatusManager(DOTQuoteStatusManager)
     */
    public void registerQuoteStatusManager(
        DOTQuoteStatusManager l_statusManager)
    {

        if (l_statusManager == null)
        {
            throw new IllegalArgumentException("l_statusManager must be not null.");
        }

        this.statusManager = l_statusManager;

        log.info("QuoteStatusManager registered. ["
            + l_statusManager.getClass().getName() + "]");

    }

    /**
     * ���݂̐ڑ��X�e�[�^�X���u�ڑ����v�̏ꍇ�A�����T�[�o�ւ̐ڑ����J�n����B<BR>
     *
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public void start() throws InitializationException
    {

        log.info("QuoteFeederConnector starting.");

        if (feederAdaptor == null || statusManager == null)
        {
            throw new IllegalStateException("QuoteFeederAdaptor or QuoteStatusManager is not registred.");
        }

        QuoteStatus l_status = statusManager.getCurrentStatus();

        if (QuoteStatus.CONNECTING.equals(l_status))
        {
            try
            {
                feederAdaptor.connect();
            } catch (QuoteFeederAdaptorException l_qfae)
            {
                log.error("Failed to connect.", l_qfae);
                throw new InitializationException(l_qfae);
            }

        }

        log.info("QuoteFeederConnector started.");

    }

    /**
     * ���݂̐ڑ��X�e�[�^�X���A�u�ڑ����v�܂��́u�ڑ��ρv�̏ꍇ�A
     * �����T�[�o�ւ̐ڑ����~���A�ċN�����Ɏ����I�Ɏ����T�[�o�ւ�
     * �ڑ����J�n����悤�ɁA�ڑ��X�e�[�^�X���u�ڑ����v�ɕύX����B<BR>
     *
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public void stop()
    {

        log.info("QuoteFeederConnector stopping.");

        if (feederAdaptor == null || statusManager == null)
        {
            throw new IllegalStateException("QuoteFeederAdaptor or QuoteStatusManager is not registred.");
        }

        QuoteStatus l_status = statusManager.getCurrentStatus();

        if (QuoteStatus.CONNECTING.equals(l_status)
            || QuoteStatus.CONNECTED.equals(l_status))
        {

            try
            {
                feederAdaptor.disconnect();
            } catch (QuoteFeederAdaptorException l_qfae)
            {
                log.error("Failed to disconnect.", l_qfae);
            } finally
            {
                statusManager.modifyStatus(QuoteStatus.CONNECTING);
            }

        }

        log.info("QuoteFeederConnector stopped.");

    }

}
