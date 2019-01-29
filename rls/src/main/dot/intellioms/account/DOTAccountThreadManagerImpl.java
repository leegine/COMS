/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�𐧌䂷��}�l�[�W���[�����N���X(DOTAccountThreadManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.fitechlabs.fin.intellioms.account.AccountIDRange;
import com.fitechlabs.fin.intellioms.account.AccountsInfo;
import com.fitechlabs.fin.intellioms.rulesys.BizDateProvider;
import com.fitechlabs.fin.intellioms.rulesys.OmsRuleEngine;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.services.ioc.IocServiceException;

import jp.co.dir.dot.intellioms.tx.DOTTxConnPoolAdaptor;


/**
 * �X���b�h���ƃA�J�E���g�����W�𐧌䂷��}�l�[�W���[����
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTAccountThreadManagerImpl extends TimerTask implements DOTAccountThreadManager
{

    /**
     * ���K�[
     */
    private final Log log = Log.getLogger(this.getClass());

    /**
     * Min�A�J�E���g
     */
    private final static long MIN_ACCOUNT_ID = 0L;

    /**
     * Max�A�J�E���g
     */
    private final static long MAX_ACCOUNT_ID = 999999999999999999L;

    /**
     * No.0�X���b�h
     */
    private final static int THREAD_NO_ZERO = 0;

    /**
     * ���[���G���W��
     */
    private OmsRuleEngine ruleEngine;

    /**
     * �f�[�^�x�[�X�A�_�v�^�[
     */
    private DOTAccountThreadDbAdaptor accountThreadDbAdaptor;

    /**
     * bizDate�v���o�C�_�[
     */
    private BizDateProvider bizDateProvider;

    /**
     * �^�C�}�[�t�@�N�g��
     */
    private DOTAccountThreadFactory accountThreadFactory;

    /**
     * �^�C�}�[�C���^�[�o��
     */
    private long taskTimerInterval;

    /**
     * default�X���b�h�T�C�Y
     */
    private long defaultThreadSize;

    /**
     * max�X���b�h�T�C�Y
     */
    private long maxThreadSize;

    /**
     * �X���b�h���ƃA�J�E���g�����WList
     */
    private List accountRangePerThreads = new ArrayList();

    /**
     * �^�C�}�[Map
     */
    private Map timers = new HashMap();

    /**
     * �O��̃X�^�[�g�A�J�E���g
     */
    private long prevStartAccountId = Long.MIN_VALUE;

    /**
     * �O��̃G���h�A�J�E���g
     */
    private long prevEndAccountId = Long.MAX_VALUE;

    /**
     * Tx�A�_�v�^�[
     */
    private DOTTxConnPoolAdaptor txAdaptor;

    /**
     * cancelled�t���O
     */
    private boolean isCancelled = false;

    /**
     * �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X
     */
    private DOTAccountThreadService accountThreadService;

    /**
     *
     */
    public DOTAccountThreadManagerImpl()
    {
    }


    /**
     * @see DOTAccountThreadManager#setDefaultThreadSize(long)
     */
    public void setDefaultThreadSize(long l_defaultThreadSize)
    {
        defaultThreadSize = l_defaultThreadSize;
    }
    /**
     * @see DOTAccountThreadManager#setMaxThreadSize(long)
     */
    public void setMaxThreadSize(long l_maxThreadSize)
    {
        maxThreadSize = l_maxThreadSize;
    }
    /**
     * @see DOTAccountThreadManager#setTaskTimerInterval(long)
     */
    public void setTaskTimerInterval(long l_taskTimerInterval)
    {
        taskTimerInterval = l_taskTimerInterval;
    }
    /**
     *
     * @see jp.co.dir.dot.intellioms.account.WEB3AccountThreadTimerManager#setOmsRuleEngine(OmsRuleEngine)
     */
    public void setOmsRuleEngine(OmsRuleEngine l_ruleEngine)
    {
        ruleEngine = l_ruleEngine;
    }

    /**
     *
     * @see jp.co.dir.dot.intellioms.account.WEB3AccountThreadTimerManager#setDbAdaptor(DOTAccountThreadDbAdaptor)
     */
    public void setAdaptor(DOTAccountThreadDbAdaptor l_accountThreadDbAdaptor)
    {
        accountThreadDbAdaptor = l_accountThreadDbAdaptor;
    }

    /**
     *
     * @see jp.co.dir.dot.intellioms.account.WEB3AccountThreadTimerManager#setBizDateProvider(BizDateProvider)
     */
    public void setBizDateProvider(BizDateProvider l_bizDateProvider)
    {
        bizDateProvider = l_bizDateProvider;
    }

    /**
     * @see jp.co.dir.dot.intellioms.account.WEB3AccountThreadTimerManager#setFactory(jp.co.dir.dot.intellioms.account.WEB3AccountThreadTimerFactory)
     */
    public void setFactory(DOTAccountThreadFactory l_accountThreadFactory)
    {
        accountThreadFactory = l_accountThreadFactory;
    }

    /**
     * @param txAdaptor txAdaptor ��ݒ�B
     */
    public void setTxAdaptor(DOTTxConnPoolAdaptor txAdaptor)
    {
        this.txAdaptor = txAdaptor;
    }

    /**
     * @see DOTAccountThreadManager#setAccountThreadService(DOTAccountThreadService)
     */
    public void setAccountThreadService(DOTAccountThreadService l_accountThreadService)
    {
        accountThreadService = l_accountThreadService;
    }
    /**
     * @see Runnable#run()
     */
    public void run()
    {
        Connection l_conn = null;

        try
        {
            //��~������K�v�Ƃ��Ă邩
            if(isAllTimerNeedStopping())
            {
                log.info("all timers need stopping. service will be stopped.");
                accountThreadService.stop();
                return;
            }

            l_conn = txAdaptor.getConnection();

            //�A�J�E���gINFO�擾
            AccountsInfo l_accountsInfo = ruleEngine.getAccountsInfo();

            if(l_accountsInfo == null)
            {
                log.info("AccountsInfo is null");
                return;
            }

            //�A�J�E���gID�����W�擾
            AccountIDRange l_accountIdRange = l_accountsInfo.getRange();

            if(l_accountIdRange == null)
            {
                log.info("AccountIDRange is null");
                return;
            }

            //����̃X�^�[�g�A�J�E���g
            long l_lngStartAccountId = l_accountIdRange.getStart();

            //����̃G���h�A�J�E���g
            long l_lngEndAccountId = l_accountIdRange.getEnd();

            //�O��ƈقȂ�AccountRange�̏ꍇ(����N�� or �t�F�C���I�[�o�[ or �m�[�hJOIN)
            if(prevStartAccountId != l_lngStartAccountId
                    || prevEndAccountId != l_lngEndAccountId)
            {
                log.info("***** account_range_per_thread will be managed [start=" + l_lngStartAccountId + ", end=" + l_lngEndAccountId + "] *****");

                //�R�l�N�V�������Z�b�g
                DOTAccountThreadDbParams l_dbParams = new DOTAccountThreadDbParams();
                l_dbParams.setConnection(l_conn);

                //DB�ݒ�l���擾
                AccountIDRange l_dbAccountRange = accountThreadDbAdaptor.getAccountIDRange(l_dbParams);

                long l_dbAccountStart = l_dbAccountRange.getStart();
                long l_dbAccountEnd = l_dbAccountRange.getEnd();

                List l_ranges = new ArrayList();

                //�ŏ������W���Ǘ�����
                if(l_lngStartAccountId == l_dbAccountStart)
                {
                    long l_dbAccountStartMinusOne = l_dbAccountStart - 1;

                    //�����W�X�^�[�g��0����
                    if(MIN_ACCOUNT_ID <= l_dbAccountStartMinusOne)
                    {
                        AccountRangePerThread l_accountRangePerThread = accountThreadFactory.createAccountRangePerThread(MIN_ACCOUNT_ID, l_dbAccountStartMinusOne);
                        l_accountRangePerThread.setThreadNo(THREAD_NO_ZERO);
                        l_accountRangePerThread.setCreatedTimestamp(new Timestamp(bizDateProvider.getCurrentTimeMillis()));
                        l_accountRangePerThread.setLastUpdatedTimestamp(l_accountRangePerThread.getCreatedTimestamp());
                        l_ranges.add(l_accountRangePerThread);
                    }
                }

                //�ő僌���W���Ǘ�����
                if(l_lngEndAccountId == l_dbAccountEnd)
                {
                    //�����W�G���h��Max
                    if(l_dbAccountEnd < MAX_ACCOUNT_ID)
                    {
                        AccountRangePerThread l_accountRangePerThread = accountThreadFactory.createAccountRangePerThread(l_dbAccountEnd + 1, MAX_ACCOUNT_ID);
                        l_accountRangePerThread.setThreadNo(THREAD_NO_ZERO);
                        l_accountRangePerThread.setCreatedTimestamp(new Timestamp(bizDateProvider.getCurrentTimeMillis()));
                        l_accountRangePerThread.setLastUpdatedTimestamp(l_accountRangePerThread.getCreatedTimestamp());
                        l_ranges.add(l_accountRangePerThread);
                    }
                }

                long l_threadSize = 1;

                //�͈͂��L���Ȃ����ꍇ(�t�F�C���I�[�o�[)
                if(l_lngStartAccountId < prevStartAccountId
                       || prevEndAccountId < l_lngEndAccountId)
                {
                    l_threadSize = defaultThreadSize * 2;
                    if(l_threadSize > maxThreadSize)
                    {
                        l_threadSize = maxThreadSize;
                    }
                }
                //�͈͂������Ȃ����ꍇ(����N��,�m�[�hJOIN)
                else
                {
                    l_threadSize = defaultThreadSize;
                }

                //�A�J�E���g�͈�
                long l_margin = l_lngEndAccountId - l_lngStartAccountId + 1;

                //�A�J�E���g�͈͂��ݒ�X���b�h����菬�����ꍇ�A�^�C�}�[�X���b�h�͂P�ɂ���
                if(l_margin < l_threadSize)
                {
                    l_threadSize = 1;
                }

                //�X���b�h���ƂɒS������A�J�E���g�͈͑���
                long l_increment = l_margin / l_threadSize - 1;
                long l_start = 0;
                long l_end = l_lngStartAccountId;

                //�X���b�h���ƃA�J�E���g�����W���X���b�h�̐����쐬����
                for(int i=1;i <= l_threadSize;i++)
                {
                    l_start = l_end;
                    l_end = l_start + l_increment;

                    //�Ō�̃X���b�h���S������A�J�E���gEND�͎��ۂ̒l�ɒ���
                    if(i == l_threadSize)
                    {
                        l_end = l_lngEndAccountId;
                    }

                    AccountRangePerThread l_accountRangePerThread = accountThreadFactory.createAccountRangePerThread(l_start, l_end);
                    l_accountRangePerThread.setThreadNo(i);
                    l_accountRangePerThread.setCreatedTimestamp(new Timestamp(bizDateProvider.getCurrentTimeMillis()));
                    l_accountRangePerThread.setLastUpdatedTimestamp(l_accountRangePerThread.getCreatedTimestamp());
                    l_ranges.add(l_accountRangePerThread);

                    l_end++;

                }

                //���^�C�}�[�X�g�b�v
                stopAllTimer();

                //�V�^�C�}�[���s
                startAllTimer(l_ranges);

                log.info("timer size=" + l_ranges.size());
                log.info("start timers. " + this.toString());

                prevStartAccountId = l_lngStartAccountId;
                prevEndAccountId = l_lngEndAccountId;
            }
        }
        catch(Throwable e)
        {
            log.error(e.getMessage() + " happened. ", e);
            stopAllTimer();
        }
        finally
        {
            txAdaptor.release(l_conn);
        }

    }

    /**
     * �N���[���A�b�v����������B
     *
     */
    public void safeStop()
    {
        log.info("stopping ... ");
        setCancelled(true);
        stopAllTimer();
        log.info("stopped");
    }

    /**
     * ���ׂĂ�Timer���~����B
     *
     */
    public synchronized void stopAllTimer()
    {
        //Timer��S�Ē�~
        Iterator l_iter = timers.values().iterator();
        while(l_iter.hasNext())
        {
            DOTAccountThreadTimerAdaptor l_timer = (DOTAccountThreadTimerAdaptor)l_iter.next();

            l_timer.stop();
        }

        accountRangePerThreads.clear();
        timers.clear();

    }

    /**
     * Timer���쐬���A���s����B
     *
     */
    public synchronized void startAllTimer(List l_list) throws IocServiceException
    {
        if(l_list == null || isCancelled())
        {
            return;
        }

        Iterator l_iter = l_list.iterator();

        //���R�[�h���ATimer�����s����
        while(l_iter.hasNext())
        {
            AccountRangePerThread l_accountRange = (AccountRangePerThread)l_iter.next();

            DOTAccountThreadTimerTask l_task = accountThreadFactory.createTimerTask(l_accountRange);

            DOTAccountThreadTimerAdaptor l_timer = accountThreadFactory.createTimer(l_task, taskTimerInterval);

            timers.put(l_accountRange, l_timer);

            accountRangePerThreads.add(l_accountRange);

            l_timer.start();
        }
    }

    /**
     * @return isCancelled ��߂��܂��B
     */
    protected synchronized boolean isCancelled()
    {
        return isCancelled;
    }

    /**
     * @param isCancelled isCancelled ��ݒ�B
     */
    protected synchronized void setCancelled(boolean isCancelled)
    {
        this.isCancelled = isCancelled;
    }

    public synchronized String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName() + "[");
        int i = 1;
        Iterator l_iter = accountRangePerThreads.iterator();
        while(l_iter.hasNext())
        {
            AccountRangePerThread l_accountRange = (AccountRangePerThread)l_iter.next();
            DOTAccountThreadTimerAdaptor l_timer = (DOTAccountThreadTimerAdaptor)timers.get(l_accountRange);

            sb.append(" timer_no[" + i + "]:");
            sb.append("timer=").append(l_timer);
            i += 1;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * @see DOTAccountThreadManager#isAllTimerNeedStopping()
     *
     */
    public synchronized boolean isAllTimerNeedStopping()
    {
        boolean l_blnRet = false;

        //timer�����݂��Ȃ��ꍇ
        if(timers.size() == 0)
        {
            return l_blnRet;
        }

        l_blnRet = true;

        Iterator l_iter = timers.values().iterator();
        while(l_iter.hasNext())
        {
            DOTAccountThreadTimerAdaptor l_timer = (DOTAccountThreadTimerAdaptor)l_iter.next();

            //�^�C�}�[����~������K�v�Ƃ��Ă��Ȃ��ꍇ
            if(! l_timer.isNeedStopping())
            {
                l_blnRet = false;
                break;
            }
        }

        return l_blnRet;
    }
}
