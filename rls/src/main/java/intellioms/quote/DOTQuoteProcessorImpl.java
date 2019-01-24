/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteProcessorImpl�N���X(DOTQuoteProcessorImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/05 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.fitechlabs.fin.intellioms.enums.EventType;
import com.fitechlabs.fin.intellioms.event.Event;
import com.fitechlabs.fin.intellioms.event.EventException;
import com.fitechlabs.fin.intellioms.event.impl.EventImpl;
import com.fitechlabs.fin.intellioms.quote.Quote;
import com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor;
import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.objpool.threads.ThreadPool;



/**
 * (�g�������v���Z�b�TImpl)<BR>
 * <BR>
 * �g�������v���Z�b�T�̎����N���X<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteProcessorImpl implements DOTQuoteProcessor
{

    /** �������v�[�����玞�������擾����Ԋu�̃f�t�H���g�l */
    private static final long DEFAULT_INTERVAL = 1000;

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �X���b�h�v�[�� */
    private ThreadPool threadPool;

    /** �����A�_�v�^�[ */
    private QuoteFeederAdaptor adaptor;

    /** �������v�[�� */
    private DOTQuotePool quotePool;

    /** �X���b�h���[�J���Ȏ����C�x���g�Ǘ��N���X */
    private DOTThreadLocalQuoteEventPool eventPool;

    /** �^�C�}�[ */
    private Timer timer;

    /** �������v�[�����玞�������擾����Ԋu */
    private long interval;

    /**
     * �R���X�g���N�^
     */
    public DOTQuoteProcessorImpl()
    {
        this.interval = DEFAULT_INTERVAL;
    }

    /**
     * (register�X���b�h�v�[��)<BR>
     * <BR>
     * �p�����[�^.�X���b�h�v�[�����Ŏw�肳�ꂽ�X���b�h�v�[����
     * <code>ObjectPoolService</code>����擾���ēo�^����B<BR>
     *
     * @param l_threadPoolName �X���b�h�v�[����
     * @throws IllegalArgumentException
     *         �p�����[�^.�X���b�h�v�[������<code>null</code>�̏ꍇ
     * @see com.fitechlabs.xtier.services.ObjectPoolService
     */
    public void registerThreadPool(String l_threadPoolName)
    {

        if (l_threadPoolName == null)
        {
            throw new IllegalArgumentException("threadPoolName must be not null.");
        }

        ThreadPool l_threadPool =
            XtierKernel.getInstance().objpool().getThreadPool(l_threadPoolName);
        registerThreadPool(l_threadPool);
    }

    /**
     * (register�X���b�h�v�[��)<BR>
     * <BR>
     * �X���b�h�v�[����o�^����B<BR>
     *
     * @param l_threadPool �X���b�h�v�[��
     * @throws IllegalArgumentException
     *         �p�����[�^.�X���b�h�v�[����<code>null</code>�̏ꍇ
     */
    public void registerThreadPool(ThreadPool l_threadPool)
    {

        if (l_threadPool == null)
        {
            throw new IllegalArgumentException("threadPool must be not null.");
        }

        this.threadPool = l_threadPool;

        log.info("threadPool registered. [" + l_threadPool.toString() + "]");

    }

    /**
     * (register�������v�[��)<BR>
     * <BR>
     * �������v�[����o�^����B<BR>
     *
     * @param l_quotePool �������v�[��
     * @throws IllegalArgumentException
     *         �p�����[�^.�������v�[����<code>null</code>�̏ꍇ
     */
    public void registerQuotePool(DOTQuotePool l_quotePool)
    {

        if (l_quotePool == null)
        {
            throw new IllegalArgumentException("l_quotePool must be not null.");
        }

        this.quotePool = l_quotePool;

        log.info("QuotePool registered. [" + l_quotePool.getClass().getName() + "]");

    }

    /**
     * (register�����C�x���g�v�[��)<BR>
     * <BR>
     * �����C�x���g�v�[����o�^����B<BR>
     *
     * @param l_eventPool �����C�x���g�v�[��
     * @throws IllegalArgumentException
     *         �p�����[�^.�����C�x���g�v�[����<code>null</code>�̏ꍇ
     */
    public void registerEventPool(DOTThreadLocalQuoteEventPool l_eventPool)
    {

        if (l_eventPool == null)
        {
            throw new IllegalArgumentException("l_eventPool must be not null.");
        }

        this.eventPool = l_eventPool;

        log.info("EventPool registered. [" + l_eventPool.getClass().getName() + "]");

    }

    /**
     * (set�C���^�[�o��)<BR>
     * <BR>
     * �����v���Z�b�T���������v�[�����玞�������擾����Ԋu��ݒ肷��B<BR>
     *
     * @param l_lngInterval ���������擾����Ԋu�i�~���b�P�ʁj
     */
    public void setInterval(long l_lngInterval)
    {
        this.interval = l_lngInterval;
    }

    /**
     * �������擾�^�X�N�����s����<code>Timer</code>���쐬���A�X�P�W���[�����O����B
     *
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     * @throws IllegalStateException
     *         ��L��<code>Timer</code>�����ɃX�P�W���[�����O����Ă���ꍇ�A
     *         �܂��́A�������v�[�����o�^����Ă��Ȃ��ꍇ
     */
    public synchronized void start() throws InitializationException
    {

        if (timer != null)
        {
            throw new IllegalStateException("QuoteProcessor already started.");
        }

        if (quotePool == null)
        {
            throw new IllegalStateException("QuotePool is not registered.");
        }

        timer = new Timer(true);
        timer.scheduleAtFixedRate(new GetQuotesTask(), interval, interval);

        log.info("QuoteProcessor started. [interval=" + interval + "]");

    }

    /**
     * �������擾�^�X�N�����s����<code>Timer</code>���L�����Z������B
     *
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public synchronized void stop()
    {

        if (timer != null)
        {
            timer.cancel();
            timer = null;
        }

        log.info("QuoteProcessor stopped.");

    }

    /**
     * ��������������B<BR>
     * <BR>
     * �y�V�[�P���X�}�z
     * �H�H�H�H�H�H�H�H�H�H�H�H�H�H�H�H�H���Q�ƁB
     * <BR>
     * @throws IllegalStateException
     * �@�X���b�h�v�[�����ݒ肳��Ă��Ȃ��ꍇ�A
     * �@�����A�_�v�^�[���ݒ肳��Ă��Ȃ��ꍇ�A
     *   �܂��́A�����C�x���g�v�[�����ݒ肳��Ă��Ȃ��ꍇ
     * @see com.fitechlabs.fin.intellioms.quote.QuoteProcessor#processQuote(com.fitechlabs.fin.intellioms.quote.Quote)
     */
    public void processQuote(Quote l_quote)
    {

        if (threadPool == null)
        {
            throw new IllegalStateException("ThreadPool is not registered.");
        }

        if (adaptor == null)
        {
            throw new IllegalStateException("QuoteFeederAdaptor is not registered.");
        }

        if (eventPool == null)
        {
            throw new IllegalStateException("EventPool is not registered.");
        }

        RaiseEventTask l_task = new RaiseEventTask(l_quote);
        threadPool.addTask(l_task);

    }

    /**
     * �����A�_�v�^�[�Ɏ����v���Z�b�T���o�^���ꂽ�Ƃ��̃R�[���o�b�N���\�b�h<BR>
     *
     * @see com.fitechlabs.fin.intellioms.quote.QuoteProcessor#onRegister(com.fitechlabs.fin.intellioms.quote.QuoteFeederAdaptor)
     * @throws IllegalArgumentException
     *         �p�����[�^.QuoteFeederAdaptor��<code>null</code>�̏ꍇ
     */
    public void onRegister(QuoteFeederAdaptor l_adaptor)
    {

        if (l_adaptor == null)
        {
            throw new IllegalArgumentException("QuoteFeederAdaptor must be not null.");
        }

        this.adaptor = l_adaptor;

        log.info("QuoteProcessor registered to QuoteFeederAdaptor. ["
            + l_adaptor.getClass().getName() + "]");

    }

    /**
     * (�C�x���g�����^�X�N)<BR>
     * <BR>
     * ���[���G���W���Ɏ����C�x���g��ʒm����Runnable�̎����N���X�B<BR>
     * <BR>
     * �C�x���g�����^�X�N�̃R���X�g���N�^�Őݒ肳�ꂽ�������X�i�b�v�V���b�g��
     * �ێ����鎞���C�x���g���쐬�����[���G���W���ɒʒm����B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class RaiseEventTask implements Runnable
    {

        /** �������X�i�b�v�V���b�g */
        private final Quote quote;

        /**
         * �R���X�g���N�^
         *
         * @param l_quote
         */
        private RaiseEventTask(Quote l_quote)
        {
            this.quote = l_quote;
        }

        /**
         * @see Runnable#run()
         */
        public void run()
        {

            Event l_event = new EventImpl(EventType.QUOTE, quote);
            eventPool.setQuoteEvent(l_event);

            try
            {

                if (log.isDebug())
                {
                    log.debug("Processing event started. [event=" + l_event + "]");
                }

                adaptor.raiseEvent(l_event);

            } catch (EventException l_ee)
            {
                log.error("EventException occured while processing quote event.", l_ee);
            } catch (Exception l_ex)
            {
                log.error("Unexpected exception occured while processing quote event.", l_ex);
            } finally
            {


                if (log.isDebug())
                {
                    log.debug("Processing event finished. [event=" + l_event + "]");
                }

                eventPool.removeQuoteEvent();
                l_event = null;

            }
        }

    }

    /**
     * (�������擾�^�X�N)<BR>
     * <BR>
     * �������v�[���������I�Ɏ��������擾����TimerTask�̎����N���X�B<BR>
     * <BR>
     * �g�������v���Z�b�TImpl#set�C���^�[�o���Őݒ肵�����ԊԊu����
     * �������v�[�����玞�������擾����B
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class GetQuotesTask extends TimerTask
    {

        /**
         * @see TimerTask#run()
         */
        public void run()
        {
            try
            {

                List l_quotes = quotePool.getQuotes();

                if (l_quotes != null && !l_quotes.isEmpty())
                {
                    for (Iterator l_it = l_quotes.iterator(); l_it.hasNext();)
                    {
                        Quote l_quote = (Quote) l_it.next();
                        processQuote(l_quote);
                    }
                }

                if (log.isDebug())
                {
                    int l_intCount = l_quotes != null ? l_quotes.size() : 0;
                    log.debug("Quotes count processed. [" + l_intCount + "]");
                }

            } catch (Exception l_ex)
            {
                log.error("Unexpected exception occured while getting quotes from QuotePool.", l_ex);
            }
        }

    }

}
