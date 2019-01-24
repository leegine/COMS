/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3QuoteFeederAdaptorImpl�N���X(DOTQuoteFeederAdaptorImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/11/28 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;


import com.fitechlabs.fin.intellioms.event.Event;
import com.fitechlabs.fin.intellioms.event.EventException;
import com.fitechlabs.fin.intellioms.event.EventSystem;
import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptorException;
import com.fitechlabs.fin.intellioms.quote.QuoteProcessor;
import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.QuoteStatus;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;

/**
 * (�g�������A�_�v�^Impl)<BR>
 * <BR>
 * �g�������A�_�v�^�̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteFeederAdaptorImpl implements DOTQuoteFeederAdaptor
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** EventSystem */
    private EventSystem eventSystem;

    /** �g�������v���Z�b�T */
    private DOTQuoteProcessor quoteProcessor;

    /** �����f�[�^�\�[�X */
    private DOTQuoteDataSource dataSource;

    /** �ڑ���ԃ��j�^�[ */
    private DOTQuoteConnectionMonitor connectionMonitor;

    /**
     * �R���X�g���N�^
     */
    public DOTQuoteFeederAdaptorImpl()
    {
    }

    /**
     * (register�ڑ���ԃ��j�^�[)<BR>
     * <BR>
     * �ڑ���ԃ��j�^�[��o�^����B<BR>
     *
     * @param l_connectionMonitor �ڑ���ԃ��j�^�[
     * @throws IllegalArgumentException <code>�p�����[�^.�ڑ���ԃ��j�^�[ == null</code>�̏ꍇ
     */
    public void registerQuoteConnectionMonitor(DOTQuoteConnectionMonitor l_connectionMonitor)
    {

        if (l_connectionMonitor == null)
        {
            throw new IllegalArgumentException("l_connectionMonitor must be not null.");
        }

        this.connectionMonitor = l_connectionMonitor;

        log.info("QuoteConnectionMonitor registered. ["
            + l_connectionMonitor.getClass().getName() + "]");

    }

    /**
     * (register�����f�[�^�\�[�X)<BR>
     * <BR>
     * �����f�[�^�\�[�X��o�^����B<BR>
     *
     * @param l_connectionMonitor �����f�[�^�\�[�X
     * @throws IllegalArgumentException <code>�p�����[�^.�����f�[�^�\�[�X == null</code>�̏ꍇ
     * @see DOTQuoteFeederAdaptor#registerQuoteDataSource(DOTQuoteDataSource)
     */
    public void registerQuoteDataSource(DOTQuoteDataSource l_dataSource)
    {

        if (l_dataSource == null)
        {
            throw new IllegalArgumentException("dataSource must be not null.");
        }

        this.dataSource = l_dataSource;

        log.info("QuoteDataSource registered. ["
            + l_dataSource.getClass().getName() + "]");

    }

    /**
     * @see DOTQuoteFeederAdaptor#connect()
     */
    public boolean connect() throws QuoteFeederAdaptorException
    {

        if (dataSource == null || connectionMonitor == null)
        {
            throw new QuoteFeederAdaptorException("QuoteDataSource or QuoteConnectionMonitor is not registered.");
        }

        try
        {
            dataSource.start();
        } catch (Exception l_ex)
        {
            log.error("Unexpected exception occured while connecting.", l_ex);
            throw new QuoteFeederAdaptorException(l_ex.getMessage(), l_ex);
        }

        return connectionMonitor.waitForConnectionToBeEstablished(dataSource);

    }

    /**
     * @see DOTQuoteFeederAdaptor#getStatus()
     */
    public QuoteStatus getStatus()
    {

        if (dataSource == null)
        {
            throw new IllegalStateException("QuoteDataSource is not registred.");
        }

        return dataSource.getStatus();

    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#connect(String)
     */
    public boolean connect(String l_url) throws QuoteFeederAdaptorException
    {
        return connect();
    }

    /**
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#disconnect()
     */
    public boolean disconnect() throws QuoteFeederAdaptorException
    {

        if (dataSource == null)
        {
            throw new QuoteFeederAdaptorException("QuoteDataSource is not registered.");
        }

        try
        {
            dataSource.stop();
        } catch (Exception l_ex)
        {
            log.error("Unexpected exception occured while disconnecting.", l_ex);
            throw new QuoteFeederAdaptorException(l_ex.getMessage(), l_ex);
        }

        return true;

    }

    /**
     * �������B<BR>
     *
     * @return <code>true</code>
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#subscribe(String)
     */
    public boolean subscribe(String l_ticker) throws QuoteFeederAdaptorException
    {
        return true;
    }

    /**
     * �������B<BR>
     *
     * @return <code>true</code>
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#subscribe(String[])
     */
    public boolean subscribe(String[] l_tickers) throws QuoteFeederAdaptorException
    {
        return true;
    }

    /**
     * �������B<BR>
     *
     * @return <code>true</code>
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#unsubscribe(String)
     */
    public boolean unsubscribe(String l_ticker) throws QuoteFeederAdaptorException
    {
        return true;
    }

    /**
     * �������B<BR>
     *
     * @return <code>true</code>
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#unsubscribe(String[])
     */
    public boolean unsubscribe(String[] l_tickers)
        throws QuoteFeederAdaptorException
    {
        return true;
    }

    /**
     * �p�����[�^.QuoteProcessor��ݒ肵�A
     * QuoteProcessor#onRegister(QuoteFeederAdaptor)���Ăяo���B<BR>
     *
     * @param l_quoteProcessor QuoteProcessor
     * @throws IllegalArgumentException
     *         <code>�p�����[�^.QuoteProcessor == null</code>�̏ꍇ�A�܂��́A
     *         �p�����[�^.QuoteProcessor���g�������v���Z�b�T�̃C���X�^���X�łȂ��ꍇ
     * @see com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor#registerQuoteProcessor(com.fitechlabs.fin.intellioms.quote.QuoteProcessor)
     */
    public void registerQuoteProcessor(QuoteProcessor l_quoteProcessor)
    {

        if (l_quoteProcessor == null)
        {
            throw new IllegalArgumentException("QuoteProcessor must be not null.");
        }

        if (!(l_quoteProcessor instanceof DOTQuoteProcessor))
        {
            throw new IllegalArgumentException("QuoteProcessor must be instance of DOTQuoteProcessor");
        }

        quoteProcessor = (DOTQuoteProcessor) l_quoteProcessor;
        quoteProcessor.onRegister(this);

        log.info("QuoteProcessor registered. ["
            + quoteProcessor.getClass().getName() + "]");

    }

    /**
     * ������<BR>
     *
     * @see com.fitechlabs.fin.intellioms.event.EventSource#start()
     */
    public void start() throws EventException
    {
    }

    /**
     * ������<BR>
     *
     * @see com.fitechlabs.fin.intellioms.event.EventSource#pause()
     */
    public void pause() throws EventException
    {
    }

    /**
     * ������<BR>
     *
     * @see com.fitechlabs.fin.intellioms.event.EventSource#stop()
     */
    public void stop() throws EventException
    {
    }

    /**
     * �p�����[�^.EventSystem��ݒ肷��B<BR>
     *
     * @param l_eventSystem EventSystem
     * @throws IllegalArgumentException <code>�p�����[�^.EventSystem == null</code>�̏ꍇ
     * @see com.fitechlabs.fin.intellioms.event.EventSource#onRegister(com.fitechlabs.fin.intellioms.event.EventSystem)
     */
    public void onRegister(EventSystem l_eventSystem)
    {

        if (l_eventSystem == null)
        {
            throw new IllegalArgumentException("eventSystem must be not null.");
        }

        this.eventSystem = l_eventSystem;

        log.info("QuoteFeederAdaptor registered to eventSystem. ["
            + l_eventSystem.getClass().getName() + "]");

    }

    /**
     * ���������C�x���g���AEventSystem�Ɋ��蓖�Ă�B<BR>
     *
     * @throws EventException EventSystem���o�^����Ă��Ȃ��ꍇ
     * @see com.fitechlabs.fin.intellioms.event.EventSource#raiseEvent(com.fitechlabs.fin.intellioms.event.Event)
     */
    public void raiseEvent(Event l_event) throws EventException
    {

        if (eventSystem == null)
        {
            throw new EventException("EventSystem was not registered.");
        }

        eventSystem.dispatch(l_event);

    }

}
