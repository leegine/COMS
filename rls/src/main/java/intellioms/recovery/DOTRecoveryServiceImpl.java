/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RecoveryServiceImplクラス(DOTRecoveryServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/29 堀野　和美(FLJ) 新規作成
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
 * 障害復旧サービスの実装クラス。
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryServiceImpl implements DOTRecoveryService 
{
    /**
     * ロガー。
     */
    private static final Log log = Log.getLogger(DOTRecoveryServiceImpl.class);
    
    private static final boolean DBG = log.isDebug();    
            
    /**
     * 障害復旧処理コマンドファクトリ
     */
    private DOTRecoveryCommandFactory commandFactory;
        
    /**
     * 非同期リカバリー処理スレッドプール
     */
    private ThreadPool threadPool;
    
    /**
     * リカバリー処理マップ
     */
    private Map commandMap;
    
    /**
     * コンストラクタ。
     *
     */
    public DOTRecoveryServiceImpl()
    {
        commandMap = new HashMap();
    }
    
    /**
     * commandMap を戻します。
     * @return commandMap
     */
    public Map getCommandMap() 
    {
        return commandMap;
    }
    
    /**
     * commandFactory を戻します。
     * @return commandFactory
     */
    public DOTRecoveryCommandFactory getCommandFactory() 
    {
        return commandFactory;
    }
    
    /**
     * commandFactory を設定。
     * @param commandFactory 
     */
    public void setCommandFactory(DOTRecoveryCommandFactory commandFactory)
    {
        this.commandFactory = commandFactory;
    }
    
    /**
     * threadPool を戻します。
     * @return threadPool 
     */
    public ThreadPool getThreadPool() 
    {
        return threadPool;
    }
    
    /**
     * threadPool を設定する。
     * 
     * @param threadPool 非同期リカバリー処理スレッドプール
     */
    public void setThreadPool(ThreadPool threadPool) 
    {
        this.threadPool = threadPool;        
    }
    
    /**
     * スレッドプールを登録する。<BR>
     * 
     * @param l_threadPoolName スレッドプール名
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
     * 処理コマンドを稼動状態フラグをtrueに設定し管理マップに追加する。
     * 非同期処理の場合、threadPoolに追加する。
     * 同期処理の場合、executeする。
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
     * 非同期処理管理マップから
     * 非同期処理コマンドを取得する。
     * 
     * @param name 非同期処理コマンド名
     * @return 非同期処理コマンド
     */
    protected List getRecoveryCommands(String name)
    {
        return (List)commandMap.get(name);       
    }
    
    /**
     * 時価情報管理テーブルを
     * 当日時価取得開始時点から最新の状態に復旧する。
     * 
     * @param l_dataSource 時価データソース
     */
    public void restoreQuoteCashStoreAll(DOTQuoteDataSource l_dataSource)
    {
        //同期処理でファイルから時価復旧
        DOTRecoveryQuoteFromFile l_recoveryQuoteFromFile = 
            (DOTRecoveryQuoteFromFile)commandFactory
        	.createCommand(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_FROM_FILE);        

        addCommand(l_recoveryQuoteFromFile);        
        
        //非同期で他ノードから時価取得し復旧
        DOTRecoveryQuoteSinceFileLastUpdated l_recoveryQuoteSinceFileLastUpdated = 
            (DOTRecoveryQuoteSinceFileLastUpdated)commandFactory
        	.createCommand(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_SINCE_FILE_LAST_UPDATED);        
        l_recoveryQuoteSinceFileLastUpdated.setQuoteDataSource(l_dataSource);        

        addCommand(l_recoveryQuoteSinceFileLastUpdated);        

    }
    
    /**
     * 時価情報管理テーブルに
     * 時価サービス切断時間帯の時価情報を復旧する。
     * 
     * @param l_dataSource 時価データソース
     */
    public void restoreQuoteCashStoreDuringLostTime(DOTQuoteDataSource l_dataSource) 
    {                
        DOTRecoveryQuoteDuringLostTime l_recoveryQuoteDuringLostTime = (DOTRecoveryQuoteDuringLostTime)commandFactory.createCommand(DOTRecoveryConstants.CMD_RECOVERY_QUOTE_DURING_LOST_TIME);
        l_recoveryQuoteDuringLostTime.setQuoteDataSource(l_dataSource);
        
        addCommand(l_recoveryQuoteDuringLostTime);        
        
    }
    
    /**
     * 時価リカバリー処理が稼動中かどうかを返却する。
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
     * リカバリー処理が稼動中かどうかを返却する。
     * 
     * @param name 処理コマンド
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
            //稼動しているものがあればtrueを返す。
            isRunning |= l_cmd.isRunning();
        }
        
        return isRunning;
    }
    
    /**
     * リカバリー処理管理マップに追加された全ての処理を終了する。
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
