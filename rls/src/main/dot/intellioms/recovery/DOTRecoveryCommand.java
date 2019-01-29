/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3RecoveryAsyncCommandクラス(WEB3RecoveryAsyncCommand.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/22 堀野　和美(FLJ) 新規作成
 */

package jp.co.dir.dot.intellioms.recovery;

/**
 * 障害復旧処理クラス
 * 
 * @author kazumi HORINO(FLJ)
 * @version 1.0
 */
public abstract class DOTRecoveryCommand
{    
    /**
     * 稼動状態
     */
    protected boolean running;
    
    /**
     * 処理名
     */
    protected String name;

    /**
     * 稼動状態を戻します。
     * @return running 
     */
    public boolean isRunning() 
    {
        return running;
    }
    
    /**
     * 稼動状態を設定。
     * @param running 稼動フラグ
     */
    public void setRunning(boolean running) 
    {
        this.running = running;
    }
    
    /**
     * 処理名を戻します。
     * @return name 処理名
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * 処理名を設定する。
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * 処理を実行する。
     *
     */
    public abstract void execute();
    
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer(getClass().getName());
        l_sb.append("[name=" + this.name);
        l_sb.append(",running=" + this.running);
        l_sb.append("]");
        return l_sb.toString();
    }
    
        
}
