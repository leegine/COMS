/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3RecoveryServiceImpl�N���X(DOTRecoveryServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/29 �x��@�a��(FLJ) �V�K�쐬
 */

package jp.co.dir.dot.intellioms.recovery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.objpool.threads.ThreadPool;

import jp.co.dir.dot.intellioms.quote.adaptor.DOTQuoteDataSource;

/**
 * ��Q�����T�[�r�X�̎����N���X�B
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryServiceImpl implements DOTRecoveryService 
{
    /**
     * ���K�[�B
     */
    private static final Log log = Log.getLogger(DOTRecoveryServiceImpl.class);
    
    private static final boolean DBG = log.isDebug();    
            
    /**
     * ��Q���������R�}���h�t�@�N�g��
     */
    private DOTRecoveryCommandFactory commandFactory;
        
    /**
     * �񓯊����J�o���[�����X���b�h�v�[��
     */
    private ThreadPool threadPool;
    
    /**
     * ���J�o���[�����}�b�v
     */
    private Map commandMap;
    
    /**
     * �R���X�g���N�^�B
     *
     */
    public DOTRecoveryServiceImpl()
    {
        commandMap = new HashMap();
    }
    
    /**
     * commandMap ��߂��܂��B
     * @return commandMap
     */
    public Map getCommandMap() 
    {
        return commandMap;
    }
    
    /**
     * commandFactory ��߂��܂��B
     * @return commandFactory
     */
    public DOTRecoveryCommandFactory getCommandFactory() 
    {
        return commandFactory;
    }
    
    /**
     * commandFactory ��ݒ�B
     * @param commandFactory 
     */
    public void setCommandFactory(DOTRecoveryCommandFactory commandFactory)
    {
        this.commandFactory = commandFactory;
    }
    
    /**
     * threadPool ��߂��܂��B
     * @return threadPool 
     */
    public ThreadPool getThreadPool() 
    {
        return threadPool;
    }
    
    /**
     * threadPool ��ݒ肷��B
     * 
     * @param threadPool �񓯊����J�o���[�����X���b�h�v�[��
     */
    public void setThreadPool(ThreadPool threadPool) 
    {
        this.threadPool = threadPool;        
    }
    
    /**
     * �X���b�h�v�[����o�^����B<BR>
     * 
     * @param l_threadPoolName �X���b�h�v�[����
     */
    public void registerThreadPool(String l_threadPoolName)
    {
        
        if (l_threadPoolName == null)
        {
            throw new IllegalArgumentException("threadPoolName must be not null.");
        }
        
        ThreadPool l_threadPool = 
            XtierKernel.getInstance().objpool().getThreadPool(l_threadPoolName);
        
        if(l_threadPool == null)
        {
            throw new IllegalArgumentException("could not get threadPool by threadPoolName.");
        }
        
        this.setThreadPool(l_threadPool);
        
        if(DBG)
        {
            log.debug("registered threadPool=" + threadPool);            
        }

        
    }
    
    /**
     * �����R�}���h���ғ���ԃt���O��true�ɐݒ肵�Ǘ��}�b�v�ɒǉ�����B
     * �񓯊������̏ꍇ�AthreadPool�ɒǉ�����B
     * ���������̏ꍇ�Aexecute����B
     * @param l_command
     */
    protected synchronized void addCommand(DOTRecoveryCommand l_command)
    {
        l_command.setRunning(true);
        List l_commands = (List)commandMap.get(l_command.getName());
        if(l_commands == null)
        {
            l_commands = new ArrayList();            
        }
        l_commands.add(l_command);
        commandMap.put(l_command.getName() , l_commands);        
        
        if(DBG)
        {
            log.debug("added command=" + l_command.toString());
            log.debug("instance size(in command list)=" + l_commands.size() + " command lists size(in commandMap)=" + commandMap.size());
        }
        
        if(l_command instanceof Runnable)
        {            
          threadPool.addTask((Runnable)l_command);
        }
        else
        {
            l_command.execute();
        }
        
    }
    
    /**
     * �񓯊������Ǘ��}�b�v����
     * �񓯊������R�}���h���擾����B
     * 
     * @param name �񓯊������R�}���h��
     * @return �񓯊������R�}���h
     */
    protected List getRecoveryCommands(String name)
    {
        return (List)commandMap.get(name);       
    }
    
    /**
     * �������Ǘ��e�[�u����
     * ���������擾�J�n���_����ŐV�̏�Ԃɕ�������B
     * 
     * @param l_dataSource �����f�[�^�\�[�X
     */
    public void restoreQuoteCashStoreAll(DOTQuoteDataSource l_dataSource)
    {
        //���������Ńt�@�C�����玞������
        DOTRecoveryQuoteFromFile l_recoveryQuoteFromFile = 
            (DOTRecoveryQuoteFromFile)commandFactory
        	.createCommand(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_FROM_FILE);        

        addCommand(l_recoveryQuoteFromFile);        
        
        //�񓯊��ő��m�[�h���玞���擾������
        DOTRecoveryQuoteSinceFileLastUpdated l_recoveryQuoteSinceFileLastUpdated = 
            (DOTRecoveryQuoteSinceFileLastUpdated)commandFactory
        	.createCommand(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_SINCE_FILE_LAST_UPDATED);        
        l_recoveryQuoteSinceFileLastUpdated.setQuoteDataSource(l_dataSource);        

        addCommand(l_recoveryQuoteSinceFileLastUpdated);        

    }
    
    /**
     * �������Ǘ��e�[�u����
     * �����T�[�r�X�ؒf���ԑт̎������𕜋�����B
     * 
     * @param l_dataSource �����f�[�^�\�[�X
     */
    public void restoreQuoteCashStoreDuringLostTime(DOTQuoteDataSource l_dataSource) 
    {                
        DOTRecoveryQuoteDuringLostTime l_recoveryQuoteDuringLostTime = (DOTRecoveryQuoteDuringLostTime)commandFactory.createCommand(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_DURING_LOST_TIME);
        l_recoveryQuoteDuringLostTime.setQuoteDataSource(l_dataSource);
        
        addCommand(l_recoveryQuoteDuringLostTime);        
        
    }
    
    /**
     * �������J�o���[�������ғ������ǂ�����ԋp����B
     * 
     * @see jp.co.dir.dot.intellioms.recovery.WEB3RecoveryManager#isRecoveryQuoteRunning()
     */
    public boolean isRecoveryQuoteRunning() 
    {
        boolean isRecoveryQuoteRunning = 
         isCommandRunning(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_FROM_FILE) || 
        isCommandRunning(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_SINCE_FILE_LAST_UPDATED) ||
        isCommandRunning(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_DURING_LOST_TIME);        
        
        if(DBG)
        {
            log.debug("isRecoveryQuoteRunning=" + isRecoveryQuoteRunning);
        }

        return isRecoveryQuoteRunning;        
    }

    /**
     * ���J�o���[�������ғ������ǂ�����ԋp����B
     * 
     * @param name �����R�}���h
     * @return boolean
     */
    protected synchronized boolean isCommandRunning(String name) 
    {
        boolean isRunning = false;
        List l_commands = getRecoveryCommands(name);
        if(l_commands == null)
        {
            return isRunning;
        }
        
        for(Iterator l_iter = l_commands.iterator(); l_iter.hasNext();)
        {
            DOTRecoveryCommand l_cmd = (DOTRecoveryCommand)l_iter.next();
            //�ғ����Ă�����̂������true��Ԃ��B
            isRunning |= l_cmd.isRunning();
        }
        
        return isRunning;
    }
    
    /**
     * ���J�o���[�����Ǘ��}�b�v�ɒǉ����ꂽ�S�Ă̏������I������B
     */
    public void cancel()
    {    
        for(Iterator l_iter = commandMap.values().iterator(); l_iter.hasNext();)
        {            
            List l_commands = (List)l_iter.next();
            for(Iterator l_commandsIter = l_commands.iterator(); l_commandsIter.hasNext();)
            {
                DOTRecoveryCommand l_command = (DOTRecoveryCommand)l_commandsIter.next();
                    l_command.setRunning(false);            
            }            
        }        
    }    
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(getClass().getName());
        l_sb.append("[commandMap=" + this.commandMap);
        l_sb.append(",commandFactory=" + this.commandFactory);
        l_sb.append(",threadPool=" + this.threadPool);
        l_sb.append("]");
        return l_sb.toString();
        
    }
    
}
