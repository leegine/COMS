/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X�����N���X(DOTAccountThreadServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.services.ioc.IocServiceException;

/**
 * �X���b�h���ƃA�J�E���g�����W�𐧌䂷��T�[�r�X����
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public abstract class DOTAccountThreadServiceImpl implements DOTAccountThreadService
{
    /**
     * ���K�[
     */
    private final Log log = Log.getLogger(this.getClass());

    /**
     * �C���^�[�o��
     */
    private long interval;

    /**
     * �^�C�}�[
     */
    private Timer timer;

    /**
     * �X���b�h���ƃA�J�E���g�}�l�[�W���[
     */
    private DOTAccountThreadManager manager;

    /**
     * �^�C�}�[�t�@�N�g��
     */
    private DOTAccountThreadFactory accountThreadFactory;

    /**
     * �^�C�}�[�X���b�h�T�v
     */
    private String threadOutline;

    /**
     *
     */
    public DOTAccountThreadServiceImpl()
    {
    }

    /**
     * @see DOTAccountThreadService#setInterval(long)
     */
    public void setInterval(long l_interval)
    {
        interval = l_interval;
    }
    /**
     * @see DOTAccountThreadService#setFactory(DOTAccountThreadFactory)
     */
    public void setFactory(DOTAccountThreadFactory l_factory)
    {
        accountThreadFactory = l_factory;
    }
    /**
     *
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public synchronized void start()
    throws InitializationException
    {
        log.info("starting ... ");
        if(timer != null)
        {
            throw new InitializationException(this.getClass().getName() + " already started.");
        }

        Thread l_thread = null;
        try
        {
            manager = accountThreadFactory.createManager();
            timer = new Timer(true);
            timer.schedule((TimerTask)manager, 0, interval);
        }
        catch(IocServiceException e)
        {
            String l_strError = e.getMessage() + " happened. ";
            log.error(l_strError, e);
            throw new InitializationException(l_strError, e);
        }

        try
        {
            Field l_field1 = timer.getClass().getDeclaredField("thread");
            l_field1.setAccessible(true);
            l_thread = (Thread)l_field1.get(timer);
            threadOutline = l_thread.getName() + " manager=" + manager.getClass().getName();
        }
        catch(Exception e)
        {
            log.warn(e.getMessage() + " happend. ", e);
        }
        log.info("started      " + threadOutline);
    }

    /**
     *
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public synchronized void stop()
    {
        log.info("stopping ... ");

        if(timer != null)
        {
            try
            {
                timer.cancel();
                manager.safeStop();
            }
            finally
            {
                timer = null;
                manager = null;
                log.info("stopped      " + threadOutline);
            }
        }
        else
        {
            log.info("service has no objects to stop." );
        }
    }

    public synchronized boolean isStarted()
    {
        return timer != null;
    }
}
