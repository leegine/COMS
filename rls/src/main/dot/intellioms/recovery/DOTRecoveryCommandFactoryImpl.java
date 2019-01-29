/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RecoveryAsyncCommandFactoryImplクラス(WEB3RecoveryAsyncCommandFactoryImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery;

import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtier.services.ioc.IocServiceException;

/**
 * 障害復旧処理コマンドファクトリの実装クラス
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public class DOTRecoveryCommandFactoryImpl implements
        DOTRecoveryCommandFactory 
{
    /**
     * ロガー。
     */
    private static final Log log = Log.getLogger(DOTRecoveryCommandFactoryImpl.class);
    
    private static final boolean DBG = log.isDebug();    
    
    /**
     * 処理コマンドを作成する。
     * @param name 処理コマンド名
     * @return WEB3RecoveryAsyncCommand
     */
    public DOTRecoveryCommand createCommand(String name) 
    {
        try
        {
            if(DBG)
            {
                log.debug("command name=" + name);
            }
            return (DOTRecoveryCommand)XtierKernel.getInstance().ioc().makeIocObject(name);            
        }
        catch(IocServiceException l_ise)
        {
            log.error(l_ise.getMessage(), l_ise);
        }
        return null;
    }
    
    public String toString()
    {
        return getClass().getName();
    }
    
}
