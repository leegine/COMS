/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���ƃA�J�E���g�����W�^�C�}�[�^�X�N����(DOTAccountThreadTimerTaskImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 �V���@�h�O(FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;

import com.fitechlabs.fin.intellioms.util.Log;

import jp.co.dir.dot.intellioms.tx.DOTTxConnPoolAdaptor;



/**
 * �X���b�h���ƃA�J�E���g�����W�^�C�}�[�^�X�N����
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public abstract class DOTAccountThreadTimerTaskImpl extends TimerTask implements
        DOTAccountThreadTimerTask
{
    /**
     * ���K�[
     */
    protected final Log log = Log.getLogger(getClass());

    /**
     * �X���b�h���ƃA�J�E���g�����W
     */
    protected AccountRangePerThread accountRange;

    /**
     * �f�[�^�x�[�X�A�_�v�^�[
     */
    protected DOTAccountThreadDbAdaptor dbAdaptor;

    /**
     * Tx�A�_�v�^�[
     */
    protected DOTTxConnPoolAdaptor txAdaptor;

    /**
     * �^�X�N
     */
    protected DOTAccountThreadTask task;

    /**
     * �����̃^�X�N�ɑΉ�����X���b�h���ƃA�J�E���g�����W���R�[�h������
     */
    protected boolean hasOwnRecord = false;

    /**
     * Db�p�����[�^
     */
    protected DOTAccountThreadDbParams dbParams = new DOTAccountThreadDbParams();

    /**
     * cancelled�t���O
     */
    private boolean isCancelled = false;

    /**
     * once���s�t���O
     */
    private boolean isOnceExecute = false;

    /**
     * ���s�s�v�t���O
     */
    private boolean isNeedStopping = false;

    /**
     *
     */
    public DOTAccountThreadTimerTaskImpl()
    {
        super();
    }

    /**
     * @see Runnable#run()
     */
    public void run()
    {
        Connection l_conn = null;

        try
        {
            checkState();

            l_conn = txAdaptor.getConnection();
            dbParams.setConnection(l_conn);

            //for update
            List l_list = dbAdaptor.getAccountRangePerThread(dbParams);

            //�����̃��R�[�h��ێ����Ă��Ȃ�
            if(! hasOwnRecord())
            {
                //�Â����폜
                dbAdaptor.deleteAccountRangePerThread(dbParams);

                //�V�������C���T�[�g
                dbAdaptor.insertAccountRangePerThread(dbParams);
                l_conn.commit();
                setHasOwnRecord(true);

                //for update
                l_list = dbAdaptor.getAccountRangePerThread(dbParams);
            }

            if(l_list.size() > 0)
            {
                try
                {
                    //�^�X�N���e�����s
                    task.execute();
                }
                finally
                {
                    //�P�񂾂����s�̏ꍇ �^�C�}�[��stop����
                    if(isOnceExecute())
                    {
                        log.info("now once executed. timer will be stopped.");
                        setNeedStopping(true);
                    }
                }
            }
        }
        catch(IllegalStateException e)
        {
            if(log.isDebug())
            {
                log.debug(e.getMessage());
            }
        }
        catch(Exception e)
        {
            log.error(e.getMessage() + " happened. ", e);
        }
        finally
        {
            if(l_conn != null)
            {
                try
                {
                    l_conn.rollback();
                }
                catch(SQLException e)
                {
                    log.error(e.getMessage() + " happened. ", e);
                }
                finally
                {
                    txAdaptor.release(l_conn);
                }
            }
        }
    }


    /**
     * @return hasOwnRecord ��߂��܂��B
     */
    public boolean hasOwnRecord()
    {
        return hasOwnRecord;
    }
    /**
     * @param hasOwnRecord hasOwnRecord ��ݒ�B
     */
    public void setHasOwnRecord(boolean hasOwnRecord)
    {
        this.hasOwnRecord = hasOwnRecord;
    }
    /**
     * @see DOTAccountThreadTimerTask#setAccountRangePerThread(AccountRangePerThread)
     */
    public void setAccountRangePerThread(AccountRangePerThread l_accountRange)
    {
        accountRange = l_accountRange;
        dbParams.setAccountRange(accountRange);
    }

    /**
     * @see DOTAccountThreadTimerTask#getAccountRangePerThread()
     */
    public AccountRangePerThread getAccountRangePerThread()
    {
        return new AccountRangePerThread(accountRange);
    }

    /**
     * @see DOTAccountThreadTimerTask#setTask(DOTAccountThreadTask)
     */
    public void setTask(DOTAccountThreadTask l_task)
    {
        task = l_task;
    }
    /**
     * @see DOTAccountThreadTimerTask#setTxConnPoolAdaptor(DOTTxConnPoolAdaptor)
     */
    public void setTxConnPoolAdaptor(DOTTxConnPoolAdaptor l_txAdaptor)
    {
        txAdaptor = l_txAdaptor;
    }
    /**
     * @see DOTAccountThreadTimerTask#setDbAdaptor(DOTAccountThreadDbAdaptor)
     */
    public void setDbAdaptor(DOTAccountThreadDbAdaptor l_dbAdaptor)
    {
        dbAdaptor = l_dbAdaptor;
    }

    /**
     * @see DOTAccountThreadTimerTask#safeStop()
     */
    public void safeStop()
    {
        setCancelled(true);
    }

    public synchronized String toString()
    {
        if(accountRange == null)
        {
            return super.toString();
        }

        StringBuffer sb = new StringBuffer();
        sb.append("DOTAccountThreadTimerTaskImpl[");
        sb.append("task range[start=").append(accountRange.getAccountStart());
        sb.append(", end=").append(accountRange.getAccountEnd());
        sb.append("]");
        return sb.toString();
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

    /**
     * @return isOnceExecute ��߂��܂��B
     */
    protected boolean isOnceExecute()
    {
        return isOnceExecute;
    }
    /**
     * @see DOTAccountThreadTimerTask#setOnceExecute(boolean)
     */
    public void setOnceExecute(boolean isOnceExecute)
    {
        this.isOnceExecute = isOnceExecute;
    }

    /**
     * @see DOTAccountThreadTimerTask#checkState()
     */
    public synchronized void checkState() throws IllegalStateException
    {
        boolean l_isNeedStopping = isNeedStopping();
        boolean l_isCancelled = isCancelled();

        if(l_isNeedStopping || l_isCancelled)
        {
            throw new IllegalStateException("timer task should not continue. isNeedStopping=" + l_isNeedStopping
                                             + ", isCancelled=" + l_isCancelled);
        }
    }

    /**
     * @return isNeedStopping ��߂��܂��B
     */
    public synchronized boolean isNeedStopping()
    {
        return isNeedStopping;
    }
    /**
     * @param isNeedStopping isNeedStopping ��ݒ�B
     */
    protected synchronized void setNeedStopping(boolean isNeedStopping)
    {
        this.isNeedStopping = isNeedStopping;
    }
}
