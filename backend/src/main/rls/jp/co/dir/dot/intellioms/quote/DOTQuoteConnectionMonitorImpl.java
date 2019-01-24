/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3QuoteConnectionMonitorImpl�N���X(DOTQuoteConnectionMonitorImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/15 �R�c�@��i(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.quote;

import java.util.Timer;
import java.util.TimerTask;

import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.fin.intellioms.util.ServicesManager;
import com.fitechlabs.xtier.kernel.KernelException;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.ioc.IocServiceException;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;



/**
 * (�ڑ���ԃ��j�^�[Impl)<BR>
 * <BR>
 * �ڑ���ԃ��j�^�[�̎����N���X�B<BR>
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTQuoteConnectionMonitorImpl implements DOTQuoteConnectionMonitor
{

    /** ���K�[ */
    private final Log log = Log.getLogger(getClass());

    /** �ڑ����m������܂őҋ@���鎞�ԁy�b�z */
    private final long timeout;

    /** �ڑ����m�����Ȃ��ꍇ�A���[���G���W�����~����܂ł̒x�����ԁy�b�z */
    private final long delay;

    /**
     * �R���X�g���N�^<BR>
     *
     * @param l_lngTimeout �ڑ����m������܂őҋ@���鎞�ԁy�b�z
     * @param l_lngDelay �ڑ����m�����Ȃ��ꍇ�A���[���G���W�����~����܂ł̒x�����ԁy�b�z
     */
    public DOTQuoteConnectionMonitorImpl(long l_lngTimeout, long l_lngDelay)
    {
        this.timeout = l_lngTimeout;
        this.delay = l_lngDelay;
    }

    /**
     * (get�^�C���A�E�g����)<BR>
     * <BR>
     * �ڑ����m������܂őҋ@���鎞�ԁy�b�z���擾����B<BR>
     *
     * @return �^�C���A�E�g����
     */
    public long getTimeout()
    {
        return timeout;
    }

    /**
     * (get�x������)<BR>
     * <BR>
     * �ڑ����m�����Ȃ��ꍇ�A���[���G���W�����~����܂ł̒x�����ԁy�b�z���擾����B<BR>
     *
     * @return �x������
     */
    public long getDelay()
    {
        return delay;
    }

    /**
     * @see DOTQuoteConnectionMonitor#waitForConnectionToBeEstablished(DOTQuoteDataSource)
     */
    public synchronized boolean waitForConnectionToBeEstablished(
        DOTQuoteDataSource l_dataSource)
    {

        if (l_dataSource == null)
        {
            throw new IllegalArgumentException("l_dataSource must be not null.");
        }

        boolean l_isConnectionEstablished = false;
        long l_lngStartTime = System.currentTimeMillis();

        log.info("Waiting for connection to be established.");

        while (true)
        {

            if (l_dataSource.isConnected())
            {
                log.info("Connection is established.");
                l_isConnectionEstablished = true;
                break;
            }

            long l_lngCurrentTime = System.currentTimeMillis();
            if ((l_lngCurrentTime - l_lngStartTime) > (timeout * 1000))
            {
                log.warn("Rule-engine will stop because connection to quote server counld not be established.");
                break;
            }

            try
            {

                log.debug("Connection is not established.");

                Thread.sleep(1000);

            } catch (InterruptedException l_ie)
            {
            }

        }

        if (!l_isConnectionEstablished)
        {
            stopRuleEngine();
        }

        return l_isConnectionEstablished;

    }

    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("DOTQuoteConnectionMonitorImpl[");
        l_sb.append("timeout=").append(timeout);
        l_sb.append(",delay=").append(delay);
        l_sb.append("]");
        return l_sb.toString();
    }

    /**
     * (stop���[���G���W��)
     *
     * ���[���G���W�����V���b�g�_�E������B
     */
    private void stopRuleEngine()
    {

        // ���[���G���W����~�pTimerTask�𐶐�����B
        TimerTask l_task = new TimerTask()
        {

            public void run()
            {

                log.warn("Stopping rule-engine because connection to quote server counld not be established.");

                try
                {
                    getServicesManager().terminate();
                } catch (KernelException l_ke)
                {
                    log.error("Failed to terminating rule-engine.", l_ke);
                } catch (IocServiceException l_ise)
                {
                    log.error("Failed to terminating rule-engine.", l_ise);
                } catch (Throwable l_th)
                {
                    log.error("Failed to terminating rule-engine.", l_th);
                }

            }
        };

        // ��������TimerTask���X�P�W���[�����O����B
        Timer l_timer = new Timer(true);
        l_timer.schedule(l_task, (delay * 1000));

    }

    /**
     * (getServicesManager)<BR>
     * <BR>
     * ServicesManager���擾����B<BR>
     *
     * @return ServicesManager
     * @throws IocServiceException
     */
    private ServicesManager getServicesManager() throws IocServiceException
    {
        return (ServicesManager) XtierKernel.getInstance().ioc().makeIocObject(
            "services.manager");
    }

}
