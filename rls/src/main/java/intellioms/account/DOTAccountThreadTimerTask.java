/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : スレッドごとアカウントレンジタイマータスク(DOTAccountThreadTimerTask.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.account;

import jp.co.dir.dot.intellioms.tx.DOTTxConnPoolAdaptor;


/**
 * スレッドごとアカウントレンジタイマータスク
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadTimerTask
{   
    /**
     * スレッドごとアカウントレンジを設定する。
     * 
     * @param スレッドごとアカウントレンジ
     */
    public void setAccountRangePerThread(AccountRangePerThread l_accountRange);
    
    /**
     * タスク内容を設定する。
     * 
     * @param タスク内容
     */
    public void setTask(DOTAccountThreadTask l_task);
    
    /**
     * コネクションプールアダプターを設定する。
     * 
     * @param コネクションプールアダプター
     */
    public void setTxConnPoolAdaptor(DOTTxConnPoolAdaptor l_txAdaptor);
    
    /**
     * スレッドごとアカウントレンジデータベースアダプターを設定する。
     * 
     * @param スレッドごとアカウントレンジデータベースアダプター
     */
    public void setDbAdaptor(DOTAccountThreadDbAdaptor l_dbAdaptor);
    
    /**
     * スレッドごとアカウントレンジを取得する。
     * 
     */
    public AccountRangePerThread getAccountRangePerThread();
    
    /**
     * タスクを終了する。
     */
    public void safeStop();

    /**
     * タイマータスクの状態をチェックします。<BR>
     * 続行すべきでない場合、IllegalStateExceptionをthrowします。
     */
    public void checkState() throws IllegalStateException;
    
    /**
     * １回だけ実行すべきタスクかを設定する。
     * 
     * @param １回実行フラグ - (true=1回実行, false=複数回実行)
     */
    public void setOnceExecute(boolean isOnceExecute);

    /**
     * タスク停止処理が必要か判定する。
     */
    public boolean isNeedStopping();
}
