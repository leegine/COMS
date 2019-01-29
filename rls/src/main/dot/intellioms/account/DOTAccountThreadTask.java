/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : タスク内容(DOTAccountThreadTask.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.account;

/**
 * タスク内容
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadTask
{
    /**
     * スレッドごとアカウントレンジを設定する。
     * 
     * @param スレッドごとアカウントレンジ
     */
    public void setAccountRangePerThread(AccountRangePerThread l_accountRange);
    
    /**
     * スレッドごとアカウントレンジタイマータスクを設定する。
     * 
     * @param スレッドごとアカウントレンジタイマータスク
     */
    public void setTimerTask(DOTAccountThreadTimerTask l_timerTask);

    /**
     * タスク内容を実行する。
     * 
     */
    public void execute() throws Exception;
    
}
