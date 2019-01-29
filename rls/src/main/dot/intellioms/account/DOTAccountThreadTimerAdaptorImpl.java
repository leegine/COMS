/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����Ƃ�����^�C�}�[�A�_�v�^�[����(DOTAccountThreadTimerAdaptorImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/10 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.account.DOTAccountThreadTimerAdaptor;
import jp.co.dir.dot.intellioms.account.DOTAccountThreadTimerTask;


/**
 * ����Ƃ�����^�C�}�[�A�_�v�^�[����
 */
public class DOTAccountThreadTimerAdaptorImpl implements DOTAccountThreadTimerAdaptor
{
    /**
     * ���K�[
     */
    private final Log log = Log.getLogger(this.getClass());

    /**
     * (timer)
     */
    private final Timer timer;

    /**
     * (task)
     */
    private final DOTAccountThreadTimerTask task;

    /**
     * (�C���^�[�o��)
     */
    private final long interval;

    /**
     * (�^�C�}�[�X���b�h�T�v)
     */
    private String threadOutline;
    /**
     *
     * @param task
     * @param �C���^�[�o��
     */

    public DOTAccountThreadTimerAdaptorImpl(DOTAccountThreadTimerTask l_task, long l_lngInterval)
    {
        timer = new Timer(true);
        task = l_task;
        interval = l_lngInterval;
        AccountRangePerThread l_range = l_task.getAccountRangePerThread();

        Thread l_thread = null;
        try
        {
            Field l_field1 = timer.getClass().getDeclaredField("thread");
            l_field1.setAccessible(true);
            l_thread = (Thread)l_field1.get(timer);
            threadOutline = l_thread.getName() + " purpose=" + l_range.getPurposeType() + " from=" + l_range.getAccountStart() + " to=" + l_range.getAccountEnd();
        }
        catch(Exception e)
        {
            log.warn(e.getMessage() + " happend. ", e);
        }
    }

    /**
     * �^�C�}�[���J�n����
     */
    public void start()
    {
        log.info("starting ... ");
        timer.schedule((TimerTask)task, 0, interval);
        log.info("started      " + threadOutline);
    }

    /**
     * �^�C�}�[���~����
     */
    public void stop()
    {
        log.info("stopping ... ");
        timer.cancel();
        task.safeStop();
        log.info("stopped      " + threadOutline);

    }

    /**
     * @see DOTAccountThreadTimerAdaptor#isNeedStopping()
     */
    public boolean isNeedStopping()
    {
        return task.isNeedStopping();
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("TimerAdaptor[");
        sb.append("interval=").append(interval);
        sb.append(", threadOutline=").append(threadOutline);
        sb.append("]");
        return sb.toString();
    }
}
