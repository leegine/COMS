/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteMonitor�N���X(DOTQuoteMonitor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/09 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote.adaptor.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.enums.IndexType;
import jp.co.dir.dot.intellioms.enums.RealType;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteCacheStore;
import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteData;



/**
 * (�������j�^�[)<BR>
 * <BR>
 * �������Ǘ��e�[�u������NK225�̎��������������j�^�[�ݒ�ɐݒ肵���Ԋu�Ŏ擾����B
 * �������j�^�[���擾����NK225�̎�����񂪍X�V����Ă��Ȃ��񐔂�
 * �������j�^�[�ݒ�ɐݒ肵���x��臒l�𒴂����ꍇ�A���O�Ɍx�����b�Z�[�W
 * ���o�͂����B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
class DOTQuoteMonitor
{

    /** ���K�[ */
    private static final Log log = Log.getLogger(DOTQuoteMonitor.class);

    /** �X���b�hNO */
    private static int threadNo = 1;

    /** �������Ǘ��e�[�u�� */
    private final DOTQuoteCacheStore cacheStore;

    /** �������j�^�[�ݒ� */
    private final DOTQuoteMonitorSettings settings;

    /** �������j�^�[�L������ */
    private final MonitoringTime[] monitoringTimeList;

    /** �������j�^�[�X���b�h */
    private MonitorThread monitorThread;

    /** �ŐV�X�V���� */
    private Timestamp lastUpdatedTime;

    /** ���s�� */
    private int failureCount;

    /**
     * �R���X�g���N�^<BR>
     *
     * @param cacheStore �������Ǘ��e�[�u��
     * @param settings �������j�^�[�ݒ�
     */
    DOTQuoteMonitor(DOTQuoteCacheStore cacheStore, DOTQuoteMonitorSettings settings)
    {

        if (cacheStore == null)
        {
            throw new IllegalArgumentException("cacheStore must be not null.");
        }

        this.cacheStore = cacheStore;
        this.settings = settings;
        this.monitoringTimeList = toMonitoringTimeList(settings.getMonitoringTimeDef());

        log.info("DOTQuoteMonitor initialized. ["
            + "interval=" + settings.getInterval()
            + ",warningThreshold=" + settings.getWarningThreshold()
            + ",enableTime=" + settings.getMonitoringTimeDef()
            + "]");

    }

    //
    // public methods ---------------------------------------------------------
    //

    /**
     * (start)<BR>
     * <BR>
     * NK225�̊Ď����J�n����B<BR>
     */
    synchronized void start()
    {

        if (monitorThread != null)
        {
            throw new IllegalStateException("MonitorThread is already started.");
        }

        monitorThread = new MonitorThread();
        monitorThread.setName("MonitorThread-" + getThreadNo());
        monitorThread.setDaemon(true);
        monitorThread.start();

        log.info("DOTQuoteMonitor started.");

    }

    /**
     * (stop)<BR>
     * <BR>
     * NK225�̊Ď����I������B<BR>
     */
    synchronized void stop()
    {

        if (monitorThread != null)
        {

            monitorThread.isActive = false;
            monitorThread.interrupt();

            try
            {
                monitorThread.join();
            } catch (InterruptedException ie)
            {
                log.warn("Unexpected exception occured while stopping.", ie);
            } finally
            {
                monitorThread = null;
            }

        }

        log.info("DOTQuoteMonitor stopped.");

    }

    //
    // protected methods ------------------------------------------------------
    //

    /**
     * (process)<BR>
     * <BR>
     * NK225�̎�����񂪍X�V����Ă��邩�m�F����B<BR>
     * ���̃��\�b�h�����s�������Ԃ��������j�^�[�L�����Ԃ͈̔͊O��
     * ����ꍇ�͒����ɏI������B<BR>
     */
    protected synchronized void process()
    {

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (!isMonitoringTime(now))
        {
            log.debug("Out of a setting range of monitoring time.");
            return;

        }

        DOTQuoteData quoteData =
            cacheStore.getIndexQuote(RealType.REAL, IndexType.NK225);
        Timestamp currentPriceTime = quoteData.getCurrentPriceTime();

        boolean isUpdated = false;
        if (lastUpdatedTime != null)
        {
            if (currentPriceTime != null
                && lastUpdatedTime.compareTo(currentPriceTime) < 0)
            {
                isUpdated = true;
            }
        } else
        {
            if (currentPriceTime != null)
            {
                isUpdated = true;
            }
        }

        if (isUpdated)
        {

            lastUpdatedTime = currentPriceTime;
            failureCount = 0;

            if (log.isDebug())
            {
                log.debug("NK225 quote data is updated. "
                    + "[lastUpdatedTime=" + lastUpdatedTime + "]");
            }

        } else
        {

            failureCount++;


            if (failureCount >= settings.getWarningThreshold())
            {
                log.error("NK225 quote data is not updated."
                    + "[lastUpdatedTime=" + lastUpdatedTime
                    + ", failureCount=" + failureCount + "]");
            } else
            {

                if (log.isDebug())
                {
                    log.debug("NK225 quote data is not updated."
                        + "[lastUpdatedTime=" + lastUpdatedTime
                        + ", failureCount=" + failureCount + "]");
                }

            }

        }

    }

    //
    // private methods --------------------------------------------------------
    //

    /**
     * (get�X���b�hNO)<BR>
     * <BR>
     * �������j�^�[�X���b�h�̃X���b�h���ɐݒ肷��X���b�hNO���擾����B<BR>
     */
    private int getThreadNo()
    {
        synchronized (DOTQuoteMonitor.class)
        {
            return threadNo++;
        }
    }

    /**
     * (is�������j�^�[�L������)<BR>
     * <BR>
     * �p�����[�^�Ŏw�肵�����Ԃ��������j�^�[�L�����Ԃ͈͓̔������肷��B<BR>
     *
     * @param systemTime ����
     * @return �������j�^�[�L�����Ԃ͈̔͂̏ꍇ��<code>true</code>�A
     *  ����ȊO�̏ꍇ��<code>false</code>��Ԃ��B
     */
    private boolean isMonitoringTime(Timestamp systemTime)
    {
        boolean result = false;
        String hhmm = DOTQuoteUtils.getDateFormat("HH:mm").format(systemTime);
        if (monitoringTimeList != null)
        {
            for (int i = 0; i < monitoringTimeList.length; i++)
            {
                if (monitoringTimeList[i].startTime.compareTo(hhmm) <= 0
                    && monitoringTimeList[i].endTime.compareTo(hhmm) >= 0)
                {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * (to�������j�^�[�L������)<BR>
     * <BR>
     * �p�����[�^�Ŏw�肵���������j�^�[�L�����Ԃ��`�����������
     * �������j�^�[�L�����Ԃɕϊ�����B<BR>
     *
     * @param monitoringTimeDef �������j�^�[�L�����Ԃ��`����������
     * @return �������j�^�[�L������
     */
    private MonitoringTime[] toMonitoringTimeList(String monitoringTimeDef)
    {

        MonitoringTime[] monitoringTimeList = new MonitoringTime[0];

        if (monitoringTimeDef != null)
        {

            List tzList = new ArrayList();
            StringTokenizer st1 =
                new StringTokenizer(monitoringTimeDef.trim(), ",");

            while (st1.hasMoreTokens())
            {

                String timeZoneDef = st1.nextToken();

                if (timeZoneDef != null)
                {

                    StringTokenizer st2 =
                        new StringTokenizer(timeZoneDef.trim(), "-");
                    String startTime = st2.nextToken();
                    String endTime = st2.nextToken();

                    if (startTime != null && endTime != null
                        && startTime.trim().length() > 0
                        && endTime.trim().length() > 0)
                    {

                        MonitoringTime mt = new MonitoringTime();
                        mt.startTime = startTime.trim();
                        mt.endTime = endTime.trim();
                        tzList.add(mt);

                    }

                }

            }

            monitoringTimeList = (MonitoringTime[]) tzList.toArray(monitoringTimeList);

        }

        return monitoringTimeList;

    }

    /**
     * (�������j�^�[�X���b�h)<BR>
     * <BR>
     * �������Ǘ��e�[�u������NK225�̎���������莞�ԊԊu�Ŏ擾
     * NK225�̎������X�V����Ă��邩�Ď�����X���b�h�B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class MonitorThread extends Thread
    {

        private boolean isActive;

        private MonitorThread()
        {
            isActive = true;
        }

        /**
         * NK225�̎�����񂪍X�V����Ă��邱�Ƃ��Ď�����B<BR>
         *
         * @see Runnable#run()
         */
        public void run()
        {
            try
            {
                while (isActive)
                {

                    process();

                    try
                    {
                        Thread.sleep(settings.getInterval());
                    } catch (InterruptedException e)
                    {
                    }
                }

            } catch (Throwable t)
            {
                log.error("monitorThread: Unexpected exception occured while monitoring.", t);
            }
        }
    }

    /**
     * (�������j�^�[�L������)<BR>
     * <BR>
     * �������j�^�[���L���Ȏ��Ԃ͈̔͂�\���N���X�B<BR>
     *
     * @author Takuji Yamada (FLJ)
     * @version 1.0
     */
    private class MonitoringTime
    {

        /** �L�����ԁy���z */
        private String startTime;

        /** �L�����ԁy���z */
        private String endTime;

    }

}
