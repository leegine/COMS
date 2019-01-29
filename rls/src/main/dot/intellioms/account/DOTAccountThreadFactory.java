/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : スレッドごとアカウントレンジマネージャーで使用するファクトリ(DOTAccountThreadFactory.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.account;

import com.fitechlabs.xtier.services.ioc.IocServiceException;

/**
 * スレッドごとアカウントレンジマネージャーで使用するファクトリ
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTAccountThreadFactory
{
    /**
     * タイマーマネージャーを作成する。
     * 
     */
    public DOTAccountThreadManager createManager() throws IocServiceException;
    
    /**
     * スレッドごとアカウントレンジを作成する。
     * 
     */
    public AccountRangePerThread createAccountRangePerThread(long l_start, long l_end);

    /**
     * タイマータスクを作成する。
     * 
     * @param スレッドごとアカウントレンジ
     */
    public DOTAccountThreadTimerTask createTimerTask(AccountRangePerThread l_accountRange) throws IocServiceException;

    /**
     * タイマーを作成する。
     * 
     * @param タイマータスク
     * @param インターバル
     */
    public DOTAccountThreadTimerAdaptor createTimer(DOTAccountThreadTimerTask l_task, long l_interval);

    /**
     * タスク内容を作成する。
     * 
     * @param スレッドごとアカウントレンジ
     */
    public DOTAccountThreadTask createTask(AccountRangePerThread l_accountRange) throws IocServiceException;

}
